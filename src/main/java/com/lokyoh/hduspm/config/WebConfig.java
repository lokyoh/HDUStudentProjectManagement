package com.lokyoh.hduspm.config;

import com.lokyoh.hduspm.interceptors.LoginInterceptor;
import com.lokyoh.hduspm.interceptors.AdminInterceptor;
import com.lokyoh.hduspm.interceptors.StudentInterceptor;
import com.lokyoh.hduspm.interceptors.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns(
                "/student/**",
                "/project/student/**",
                "/teacher/**",
                "/admin/**",
                "/task/**",
                "/class/student/**",
                "/review/**"
        );
        registry.addInterceptor(studentInterceptor).addPathPatterns(
                "/student/**",
                "/project/student/**",
                "/task/**",
                "/class/student/**"
        ).excludePathPatterns("/task/get/**");
        registry.addInterceptor(teacherInterceptor).addPathPatterns(
                "/teacher/**"
        );
        registry.addInterceptor(adminInterceptor).addPathPatterns(
                "/admin/**",
                "/project/admin/**"
        );
    }
}
