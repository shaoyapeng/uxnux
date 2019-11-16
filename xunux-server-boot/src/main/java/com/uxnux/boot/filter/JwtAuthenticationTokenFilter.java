package com.uxnux.boot.filter;

import com.uxnux.boot.security.UxnuxUserDetails;
import com.uxnux.boot.security.UxnuxUserDetailsService;
import com.uxnux.boot.utils.JwtUtils;
import com.uxnux.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("tokenHeader")
    private String tokenHeader;
    @Value("tokenHead")
    private String tokenHead;

    @Autowired
    private UxnuxUserDetailsService uxnuxUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("---------- 开始jwt token认证 ----------");
        String token = ((HttpServletRequest)request).getHeader(tokenHeader);
        if (StringUtils.isBlack(token)) {
            log.info("---------- token不存在 ----------");
            filterChain.doFilter(request, response);
            return;
        }
        if (jwtUtils.isTokenExpired(token)) {
            log.info("---------- token过期 ----------");
            filterChain.doFilter(request, response);
            return;
        }
        String username = jwtUtils.getSubjectFromToken(token);
        if (StringUtils.isBlack(username)) {
            log.info("---------- 获取用户名失败 ----------");
            filterChain.doFilter(request, response);
            return;
        }
        UxnuxUserDetails uxnuxUserDetails = uxnuxUserDetailsService.loadUserByUsername(username);
        if (uxnuxUserDetails == null) {
            log.info("---------- 用户不存在 ----------");
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(uxnuxUserDetails, null, uxnuxUserDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    }
}
