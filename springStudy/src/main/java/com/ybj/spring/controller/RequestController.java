package com.ybj.spring.controller;

import com.ybj.api.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestScope
@Api(tags = {"request bean "})
public class RequestController {
    private AtomicInteger atomicInteger = new AtomicInteger();


    /**
     * @return com.ybj.api.model.JsonResult
     * @author yuanbaojian
     * @date 2020/8/5
     * @time 16:01
     */
    @GetMapping("/requestScope")
    @ApiOperation(value = "直接调用controller",notes = "controller设置成request")
    public JsonResult getIntegerInController(HttpServletRequest request){
        return JsonResult.ok().add("threadName", Thread.currentThread().getName()).add("result",atomicInteger.incrementAndGet());
    }
}
