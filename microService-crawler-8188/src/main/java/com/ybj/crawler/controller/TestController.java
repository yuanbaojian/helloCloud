package com.ybj.crawler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TestController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/hello")
    public String test(){
        return "hello";
    }
}
