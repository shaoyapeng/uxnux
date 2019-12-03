package com.uxnux.boot.filter;

import com.uxnux.boot.security.UxnuxUserDetails;
import com.uxnux.boot.security.UxnuxUserDetailsService;
import com.uxnux.boot.utils.JwtUtils;
import com.uxnux.boot.utils.ObjectUtils;
import com.uxnux.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 10785
 * @Date: 2019/11/14 19:56
 * @Version: 1.0
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UxnuxUserDetailsService uxnuxUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("---------- 开始jwt token认证 ----------");
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = null;
        UxnuxUserDetails uxnuxUserDetails = null;
        if (!StringUtils.isBlack(token)) {
            username = jwtUtils.getSubjectFromToken(token);
            if (!StringUtils.isBlack(username)) {
                uxnuxUserDetails = uxnuxUserDetailsService.loadUserByUsername(username);
            }
            if (!ObjectUtils.isEmpty(uxnuxUserDetails) && jwtUtils.validateToken(token, uxnuxUserDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        uxnuxUserDetails.getUsername(), uxnuxUserDetails.getPassword(), uxnuxUserDetails.getAuthorities()
                );
                authenticationToken.setDetails(uxnuxUserDetails);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("---------- jwt token认证成功 ----------");
            }
        }
        log.info("---------- jwt token认证结束 ----------");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
