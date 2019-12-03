package com.uxnux.boot.security;

import com.uxnux.boot.filter.JwtAuthenticationTokenFilter;
import com.uxnux.boot.filter.UxnuxLoginFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: 10785
 * @Date: 2019/11/14 19:16
 * @Version: 1.0
 */
@Slf4j
@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled=true)
public class UxnuxSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors() // 支持跨域
                .and()
                .sessionManagement().disable() // 关闭session
                .csrf().disable() // 关闭csrf
                .formLogin().disable() // 禁用form登录
                // 对所有的路径进行保护
                .authorizeRequests()
                // 不需要保护的请求
                .antMatchers("/*.html",
                        "/swagger-resources/**",
                        "/**/springfox-swagger-ui/**",
                        "/v2/api-docs/**",
                        "/favicon.ico")
                .permitAll() // 不需要认证的资源
                //.antMatchers("/admin")
                // 只有某个角色可以访问的资源: /admin 只有admin角色的可以访问
                //hasAnyRole("admin")
                .anyRequest() // 除了上面过滤的请求，全部保护
                .authenticated()
                .and()
                .logout()
                // 退出url
                .logoutUrl("/logout");
                // .addLogoutHandler() // 退出拦截
                // .logoutSuccessUrl("/"); // 退出成功

        // 禁止用缓存
        httpSecurity.headers().cacheControl();
        // 添加自定义filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterAt(uxnuxLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                // 异常 没加
                .exceptionHandling()
                // 没有访问权限
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // 认证失败
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置userDetailsService
        auth.userDetailsService(uxnuxUserDetailsService())
                // 加密
                .passwordEncoder(uxnuxPasswordEncoder());
    }

    @Bean
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    UxnuxUserDetailsService uxnuxUserDetailsService() {
        return new UxnuxUserDetailsService();
    }

    @Bean
    UxnuxPasswordEncoder uxnuxPasswordEncoder() {
        return new UxnuxPasswordEncoder();
    }

    @Bean
    UxnuxLoginFilter uxnuxLoginFilter() {
        UxnuxLoginFilter uxnuxLoginFilter = new UxnuxLoginFilter();
        try {
            uxnuxLoginFilter.setFilterProcessesUrl("/login");
            uxnuxLoginFilter.setAuthenticationSuccessHandler(uxnuxAuthenticationSuccessHandler());
            uxnuxLoginFilter.setAuthenticationFailureHandler(uxnuxAuthenticationFailureHandler());
            uxnuxLoginFilter.setAuthenticationManager(authenticationManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uxnuxLoginFilter;
    }

    @Bean
    UxnuxAuthenticationSuccessHandler uxnuxAuthenticationSuccessHandler() {
        return new UxnuxAuthenticationSuccessHandler();
    }

    @Bean
    UxnuxAuthenticationFailureHandler uxnuxAuthenticationFailureHandler() {
        return new UxnuxAuthenticationFailureHandler();
    }
}
