package me.zhongmingmao.web;

import me.zhongmingmao.domain.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {
    
    @RequestMapping("/200")
    public Message ok() {
        return new Message(1L, "200");
    }
    
    @RequestMapping("/403")
    public Message forbidden() {
        return new Message(2L, "403");
    }
}
