package com.ybj.api.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    @Pointcut("@annotation(com.ybj.api.annotation.ReadLogContent)")
    public void getExecutionTime() {

    }

    @Around(value = "getExecutionTime()")
    public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
        String logType = (String) joinPoint.getArgs()[4];
        Date start = new Date();
        log.info("读取<{}>日志内容开始时间 {}", logType, start);
        Object proceed = joinPoint.proceed();
        Date end = new Date();
        long millSecond = end.getTime() - start.getTime();
        long second = millSecond/1000;
        log.info("读取<{}>日志内容结束时间 {}, 执行时间为 {}S", logType, end, second);
        return proceed;
    }



    @Pointcut("@annotation(com.ybj.api.annotation.GetLogList)")
    public void getShowListTime() {

    }

    @Around(value = "getShowListTime()")
    public void getShowListTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        String logType = (String) joinPoint.getArgs()[2];
        Date start = new Date();
        log.info("获得 《{}》 日志列表开始时间 {}", logType, start);
        Object proceed = joinPoint.proceed();
        Date end = new Date();
        long millSecond = end.getTime() - start.getTime();
        long second = millSecond/1000;
        log.info("获得 《{}》 日志列表结束时间 {}, 执行时间为 {}S", logType, end, second);
    }

    @Pointcut("@annotation(com.ybj.api.annotation.GetParamLog)")
    public void getParam() {

    }


    //获得函数名， 参数名， 参数值，执行时间
    @Around(value = "getParam()")
    public void getParams(ProceedingJoinPoint joinPoint) throws Throwable {
        Date start = new Date();
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        String entranceName = className + "." + methodName;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //参数值
        Object[] args = joinPoint.getArgs();
        //参数名
        String[] parameterNames = signature.getParameterNames();
        //参数类型
        Class[] parameterTypes = signature.getParameterTypes();

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < parameterNames.length; i++) {
            stringBuilder.append(parameterTypes[i] + " : " + parameterNames[i] + " : " + args[i] +"\n");
        }
        String parameters = stringBuilder.toString();
        log.info("入口为 {} \n 参数信息: \n{}", entranceName , stringBuilder.toString());
        Object proceed = joinPoint.proceed();
        Date end = new Date();
        long millSecond = end.getTime() - start.getTime();
        long second = millSecond/1000;
        log.info("执行时间为 {} s", second);
    }

}
