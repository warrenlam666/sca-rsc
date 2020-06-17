package org.warren.sca.rsc.gateway.filter;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.warren.sca.rsc.common.auth.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("PostmanAuthenticateInterceptor")
public class PostmanAuthenticateInterceptor implements HandlerInterceptor {

    @Reference(group = "postman-auth")
    private TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(CustomerAuthenticateInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("快递员拦截器");
        String token = request.getHeader("Authorization");
        //开发测试使用
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJXQVJSRU4iLCJpZCI6MiwidXNlcm5hbWUiOiLlj6Tlt6jln7oifQ.XOMtowfSUiT-3FPchVElD2-98HreOJ726HoBplpVQfMcGk7WzqHmWBd1Iq_on37t";
        if (StringUtils.isEmpty(token) || !tokenService.verityToken(token))
            return false;
        int id = tokenService.getIdByToken(token);
        request.setAttribute("postmanId", id);
        return true;
    }
}
