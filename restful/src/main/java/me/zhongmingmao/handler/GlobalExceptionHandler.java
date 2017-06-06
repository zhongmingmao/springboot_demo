package me.zhongmingmao.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ErrorInfo runtimeExceptionHandler(HttpServletRequest request, Exception e) {
        return new ErrorInfo(e.getMessage(), request.getRequestURL().toString());
    }
}
