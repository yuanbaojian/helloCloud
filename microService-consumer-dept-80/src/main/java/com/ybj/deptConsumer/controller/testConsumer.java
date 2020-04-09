package com.ybj.deptConsumer.controller;

import com.ybj.api.model.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author testConsumer
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
public class testConsumer {

    @Autowired
    RestTemplate template;

    @GetMapping("testConsumerGet")
    public JsonResult testConsumerGet(){
        return template.getForObject("http://localhost:8080/testGet",JsonResult.class);
    }
}
