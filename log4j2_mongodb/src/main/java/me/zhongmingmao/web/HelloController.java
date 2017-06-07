package me.zhongmingmao.web;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    Logger logger = LogManager.getLogger(HelloController.class);
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        logger.log(Level.DEBUG, "debug");
        return "Hello World";
    }
}
