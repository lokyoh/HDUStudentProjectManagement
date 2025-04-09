package com.lokyoh.hduspm.interceptors;

import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class TeacherInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (claims.get("role").equals("teacher") || claims.get("role").equals("admin")) return true;
        response.setStatus(403);
        return false;
    }
}
