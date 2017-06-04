package me.zhongmingmao.web;

import me.zhongmingmao.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.function.BiFunction;

@Controller
public class HelloController {
    
    @RequestMapping("/")
    public String index(ModelMap map) {
        // Java 8 函数式接口 + 方法引用（Lambda）
        BiFunction<String, String, User> userBiFunction = User::new;
        // 加入属性，能在Thymeleaf模板中能读取到
        map.addAttribute("user", userBiFunction.apply("zhongmingmao", "GD"));
        return "index"; // 映射为src/main/resources/templates/index.html
    }
}
