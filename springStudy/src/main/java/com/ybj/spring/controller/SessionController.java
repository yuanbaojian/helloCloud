package com.ybj.spring.controller;

import com.ybj.api.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author SessionController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@RestController
@SessionScope
@Api(tags = {"session bean "})
public class SessionController {



    private AtomicInteger atomicInteger = new AtomicInteger();


    /**
     * @return com.ybj.api.model.JsonResult
     * @author yuanbaojian
     * @date 2020/8/5
     * @time 16:01
     */
    @GetMapping("/sessionScope")
    @ApiOperation(value = "直接调用controller",notes = "controller设置成session")
    public JsonResult getIntegerInController(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        log.info("sessionId 是 {}",sessionId);
        return JsonResult.ok().add("threadName", Thread.currentThread().getName())
                .add("session", sessionId);
    }

}
