package com.uxnux.boot.security;

import com.uxnux.boot.filter.JwtAuthenticationTokenFilter;
import jdk.nashorn.internal.runtime.logging.Logger;
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
        // 关闭cors
        httpSecurity.cors().disable()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 对所有的路径进行保护
                .authorizeRequests()
                // 不需要保护的请求
                .antMatchers("/test1")
                .permitAll()
                .antMatchers() // 可以加多个
                .permitAll()
                .anyRequest() // 除了上面过滤的请求，全部保护
                .authenticated();
        // 禁止用缓存
        httpSecurity.headers().cacheControl();
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uxnuxUserDetailsService())
                .passwordEncoder(uxnuxPasswordEncoder());
    }

    @Bean
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
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

}
