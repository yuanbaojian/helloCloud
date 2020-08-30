package com.ybj.spring.controller;

import com.ybj.api.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
* <p>使用单例bean（默认就是）
 * bean只有一个实例，每次都用这个
 * </p>
 * @return
 * @author yuanbaojian
 * @date 2020/8/5
 * @time 17:25
 */
@Slf4j
@RestController
@Scope(value = "singleton")
@Api(tags = {"singleton bean"})
public class SingletonBeanController {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @GetMapping("/singletonScope")
    @ApiOperation(value = "controller调用service",notes = "一个singleton 一个prototype，还是singleton")
    public JsonResult getAll(){
        int integer = atomicInteger.incrementAndGet();
        return JsonResult.ok().add("threadName", Thread.currentThread().getName()).add("result",integer);
    }
}
