package com.uxnux.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.uxnux.zuul.common.enums.ErrorCodeEnum;
import com.uxnux.zuul.common.expection.BaseExpection;
import com.uxnux.zuul.common.utils.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 10785
 * @Date: 2019/11/9 11:11
 * @Version: 1.0
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends ZuulFilter {

    /**
     * 不需要过滤的请求
     */
    private static final String AUTH_PATH = "/auth";
    /**
     *  不需要过滤的请求
     */
    private static final String LOGOUT_URI = "/oauth/token";
    /**
     *
     */
    public static final String HEADER_LABEL = "x-label";

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("JwtAuthenticationTokenFilter --- 认证开始");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            authTokenCtx(requestContext);
        } catch (Exception e) {
            log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
            throw new BaseExpection(ErrorCodeEnum.UX_ECM401);
        }
        return null;
    }

    public void authTokenCtx(RequestContext requestContext) throws ZuulException  {
        HttpServletRequest request = requestContext.getRequest();

        String requestURI = request.getRequestURI();

        if (!requestURI.contains(AUTH_PATH) || !requestURI.contains(LOGOUT_URI)) {
            return;
        }
        String authHeader = request.getHeader(tokenHeader);
        if (PublicUtil.isEmpty(authHeader)) {
            throw new ZuulException("访问失败", 403, "check token fail");
        }
        if (authHeader.startsWith(tokenHead)) {
            requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
            log.info("authHeader={} ", authHeader);
            requestContext.addZuulRequestHeader(HEADER_LABEL, authHeader);
        }
    }
}
