package com.uxnux.boot.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 10785
 * @Date: 2019/11/16 18:07
 * @Version: 1.0
 */
@Slf4j
public class UxnuxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        log.info("---------- 登录失败 ----------");
        log.info("---------- 异常：" + e.getMessage() + " ----------");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write("登录失败".getBytes());
        outputStream.close();
    }
}
