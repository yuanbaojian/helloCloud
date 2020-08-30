package com.ybj.rabbit.controller;


import com.ybj.api.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-15
 */
@Slf4j
@RestController
@RequestMapping("/message")
@Api(value = "消息模型测试接口",tags = {"用于不同消息类型传递的测试"})
public class MessageDemoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/helloWorld")
    @ApiOperation(value = "helloWorld",notes = "最简单的消息")
    public JsonResult getNumber(){
        String context = "消息内容: hello, time is " + LocalDateTime.now().toString();
        this.rabbitTemplate.convertAndSend("hello", context);
        return JsonResult.ok("操作成功完成");
    }

    @GetMapping("/workQueue")
    @ApiOperation(value = "workQueue 工作队列",notes = "未使用交换机，直接发送给队列,多个消费者，轮询的方式")
    public JsonResult workQueue(){

        String context = "hello, time is " + LocalDateTime.now().toString();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("workQueue", context);
        return JsonResult.ok("操作成功完成");
    }


    @GetMapping("/publishSubscribe")
    @ApiOperation(value = "发布订阅模式",notes = "通过交换机发送信息")
    public JsonResult publish(){
        String context = "hello 发布/订阅, time is " + LocalDateTime.now().toString();
        System.out.println("PublishSender : " + context);
        this.rabbitTemplate.convertAndSend("publishSubscribe","", context);
        return JsonResult.ok("操作成功完成");
    }

    @GetMapping("/routing")
    @ApiOperation(value = "路由队列",notes = "需要指定的key")
    public JsonResult routing(){
        String context = "hello, time is " + LocalDateTime.now().toString();
        System.out.println("路由信息为 : " + context);
        this.rabbitTemplate.convertAndSend("directs","info","发送key为info的路由信息");
        return JsonResult.ok("操作成功完成");
    }

    @GetMapping("/topics")
    @ApiOperation(value = "动态路由",notes = "可以用# 或者 * 匹配key")
    public JsonResult topics(){
        String context = "hello, time is " + LocalDateTime.now().toString();
        System.out.println("动态路由信息为 : " + context);
        this.rabbitTemplate.convertAndSend("topics","user.save","user.save 动态路由消息");
        return JsonResult.ok("操作成功完成");
    }





}

