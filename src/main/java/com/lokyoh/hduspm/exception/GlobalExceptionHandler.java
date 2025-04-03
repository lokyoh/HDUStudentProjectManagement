package com.lokyoh.hduspm.exception;

import com.lokyoh.hduspm.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        if(e instanceof NoResourceFoundException){
            System.out.println("error: 404 " + ((NoResourceFoundException) e).getHttpMethod() + " " + ((NoResourceFoundException) e).getResourcePath());
            return Result.error("404 not found");
        }
        e.printStackTrace();
        return Result.error("操作失败");
    }
}
