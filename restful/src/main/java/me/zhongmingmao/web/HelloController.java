package me.zhongmingmao.web;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    
    @ApiOperation(value = "抛出异常接口", notes = "抛出异常接口")
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        throw new RuntimeException("Something Wrong");
    }
}
