package com.uxnux.boot.security;

import com.uxnux.boot.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 10785
 * @Date: 2019/11/16 18:05
 * @Version: 1.0
 */
@Slf4j
public class UxnuxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("---------- 登录成功 ----------");
        log.info("---------- 用户：" + authentication.getPrincipal() + " ----------");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        String token = jwtUtils.generateJwt((UxnuxUserDetails) authentication.getPrincipal());
        outputStream.write(token.getBytes());
        outputStream.write("登录成功".getBytes());
        log.info("---------- token：" + token + " ----------");
        outputStream.close();
    }
}
