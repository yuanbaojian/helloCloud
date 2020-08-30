package com.ybj.spring.controller;

import com.ybj.api.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@ApplicationScope
@Api(tags = {"application bean "})
public class ApplicationController {

    private AtomicInteger atomicInteger = new AtomicInteger();


    /**
     * <p>发送请求，直接调用新的bean
     * 不管是不是一个session
     * </p>
     * @param
     * @return com.ybj.api.model.JsonResult
     * @author yuanbaojian
     * @date 2020/8/5
     * @time 16:01
     */
    @GetMapping("/applicationScope")
    @ApiOperation(value = "直接调用controller",notes = "controller设置成application，所以是application")
    public JsonResult getIntegerInController(){
        return JsonResult.ok().add("threadName", Thread.currentThread().getName()).add("result",atomicInteger.incrementAndGet());
    }

}
