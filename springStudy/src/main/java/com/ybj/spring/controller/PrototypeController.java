package com.ybj.spring.controller;


import com.ybj.api.model.JsonResult;
import com.ybj.spring.dao.ArticleMapper;
import com.ybj.spring.model.Article;
import com.ybj.spring.service.BeanServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
@Slf4j
@RestController
@Scope(value = "prototype")
@Api(tags = {"prototype bean "})
public class PrototypeController {

    private AtomicInteger atomicInteger = new AtomicInteger();

    /**
    * <p>发送请求，直接调用新的bean
     * </p>
     * @param
     * @return com.ybj.api.model.JsonResult
     * @author yuanbaojian
     * @date 2020/8/5
     * @time 16:01
     */
    @GetMapping("/prototypeScope")
    @ApiOperation(value = "直接调用controller",notes = "controller设置成prototype，所以是prototype")
    public JsonResult getIntegerInController(){
        return JsonResult.ok().add("threadName", Thread.currentThread().getName()).add("result",atomicInteger.incrementAndGet());
    }

}

