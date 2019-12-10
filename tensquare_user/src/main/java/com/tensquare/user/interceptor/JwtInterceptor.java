package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/11 12:30 上午
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (!ObjectUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            String token = authorization.substring(6);
            Claims claims = jwtUtil.pareseJwt(token);
            if (ObjectUtils.isEmpty(claims)) {
                String role;
                String roles = (String) claims.get("roles");
                switch (roles) {
                    case "admin":
                        role = "admin_claims";
                        break;
                    case "user":
                        role = "user_claims";
                        break;
                    default:
                        role = null;
                }
                if (!ObjectUtils.isEmpty(role)) {
                    request.setAttribute(role, claims);
                }
            }
        }
        return true;
    }
}
