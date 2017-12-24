package me.zhongmingmao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HotDeployController {
    
    @GetMapping("/say")
    public String say(HttpServletRequest request) {
        request.setAttribute("say", "zhongmingmao");
        return "say";
    }
}