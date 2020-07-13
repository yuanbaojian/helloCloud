package com.ybj.crawler.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Author LogAspect
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.ybj.crawler.annotation.GetExecutionTime)")
    public void getExecutionTime() {

    }



    @Around(value = "getExecutionTime()")
    public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime start = LocalDateTime.now();
        log.info("开始时间 {}",  start);
        Object proceed = joinPoint.proceed();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        log.info("结束时间 {}, 执行时间为 {}S", end, duration.getSeconds());
        return proceed;
    }

    @Pointcut("execution( * com.ybj.crawler.controller.BusController.*(..) )" )
    public void getExecutionTimeByExecution() {

    }

    @Around( value = "getExecutionTimeByExecution()"  )
    public Object getExecutionTimeByExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime start = LocalDateTime.now();
        log.info("开始时间 {}",  start);
        Object proceed = joinPoint.proceed();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        log.info("结束时间 {}, 执行时间为 {}S", end, duration.getSeconds());
        return proceed;
    }


    /**
    * <p>获得参数</p>
     * @param param1
     * @return void
     * @author yuanbaojian
     * @date 2020/6/24
     * @time 17:43
     */
    @After("execution( * com.ybj.crawler.controller.BusController.getBusInfo(..))" + "&& args(param1)" )
    public void getExecutionTimeAndParamsByExecution(String param1) throws Throwable {
        System.out.println("参数为 = " + param1);
    }





}
