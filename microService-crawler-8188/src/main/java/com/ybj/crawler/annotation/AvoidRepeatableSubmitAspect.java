// package com.ybj.crawler.annotation;
//
//
// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.JSONObject;
// import com.google.common.collect.Maps;
// import com.tezign.core.exception.exceptions.TezignException;
// import com.tezign.intelligence.common.constant.CommonExceptionEnum;
// import com.tezign.intelligence.common.constant.Constant;
// import com.tezign.intelligence.common.util.MD5Util;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.commons.collections4.CollectionUtils;
// import org.apache.commons.collections4.MapUtils;
// import org.apache.commons.lang3.StringUtils;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.reflect.CodeSignature;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
//
// import javax.servlet.http.HttpServletRequest;
// import java.lang.reflect.Method;
// import java.lang.reflect.Parameter;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Objects;
// import java.util.TreeMap;
//
// @Aspect
// @Component
// @Slf4j
// public class AvoidRepeatableSubmitAspect {
//
//
//     @Autowired
//     private StringRedisTemplate stringRedisTemplate;
//
//     @Around("@annotation(com.tezign.intelligence.common.annotation.AvoidRepeatableSubmit)")
//     public Object around(ProceedingJoinPoint point) throws Throwable {
//         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//         //获取注解
//         MethodSignature signature = (MethodSignature) point.getSignature();
//         Method method = signature.getMethod();
//         //目标类、方法
//         String className = method.getDeclaringClass().getName();
//         String methodName = method.getName();
//         log.info("className={},methodName={}", className, methodName);
//
//         AvoidRepeatableSubmit avoidRepeatableSubmit = method.getAnnotation(AvoidRepeatableSubmit.class);
//         if (Objects.nonNull(avoidRepeatableSubmit)) {
//             TreeMap<String, Object> paramTreeMap = this.getNameAndValue(point);
//             if (MapUtils.isNotEmpty(paramTreeMap)) {
//                 String[] excludeKeys = avoidRepeatableSubmit.excludeParams();
//                 if (excludeKeys != null) {
//                     List<String> dedupExcludeKeys = Arrays.asList(excludeKeys);
//                     if (CollectionUtils.isNotEmpty(dedupExcludeKeys)) {
//                         for (String dedupExcludeKey : dedupExcludeKeys) {
//                             paramTreeMap.remove(dedupExcludeKey);
//                         }
//                     }
//                 }
//
//                 String paramTreeMapJSON = JSON.toJSONString(paramTreeMap);
//                 String md5deDupParam = MD5Util.md5(paramTreeMapJSON);
//                 long timeout = avoidRepeatableSubmit.timeout();
//                 if (timeout < 0) {
//                     timeout = 2L;
//                 }
//                 log.info("md5deDupParam = {}, excludeKeys = {} ,paramTreeMapJSON======{}", md5deDupParam, Arrays.deepToString(excludeKeys), paramTreeMapJSON);
//                 String key = this.getUserId(request) + "-" + method.getName() + "-" + md5deDupParam;
//                 log.info("key:{}", key);
//
//                 Boolean firstSet = stringRedisTemplate.opsForValue().setIfAbsent(key, md5deDupParam, timeout, avoidRepeatableSubmit.timeUnit());
//
//                 if (firstSet) {
//                     Object object;
//                     object = point.proceed();
//                     return object;
//                 } else {
//                     throw new TezignException(CommonExceptionEnum.NO_DUPLICATE_SUBMISSION);
//                 }
//
//             }
//
//         }
//         return point.proceed();
//     }
//
//
//     private Long getUserId(HttpServletRequest request) {
//         String userId = request.getHeader(Constant.CUSTOMER_HEADER_USER_ID);
//         if (StringUtils.isBlank(userId)) {
//             userId = request.getParameter(Constant.CUSTOMER_HEADER_USER_ID);
//         }
//         if (org.springframework.util.StringUtils.isEmpty(userId)) {
//             return null;
//         }
//         return Long.parseLong(userId);
//     }
//
//
//     private TreeMap<String, Object> getNameAndValue(ProceedingJoinPoint point) {
//         TreeMap<String, Object> param = Maps.newTreeMap();
//         MethodSignature signature = (MethodSignature) point.getSignature();
//         Method method = signature.getMethod();
//         Parameter[] parameters = method.getParameters();
//         String pName = null;
//         for (Parameter parameter : parameters) {
//             if (parameter.isAnnotationPresent(RequestBody.class)) {
//                 pName = parameter.getName();
//             }
//         }
//
//         Object[] paramValues = point.getArgs();
//         String[] paramNames = ((CodeSignature) point.getSignature()).getParameterNames();
//         if (StringUtils.isBlank(pName) || paramValues.length <= 0) {
//             return param;
//         }
//         for (int i = 0; i < paramNames.length; i++) {
//             if (Objects.equals(pName, paramNames[i])) {
//                 param = JSONObject.parseObject(JSON.toJSONString(paramValues[i]), TreeMap.class);
//             }
//         }
//         return param;
//     }
//
//
// }
