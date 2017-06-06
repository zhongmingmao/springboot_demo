package me.zhongmingmao.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LocalExceptionHandler {
    
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("runtime_error"); // src/main/resources/templates/runtime_error.html
        return modelAndView;
    }
}
