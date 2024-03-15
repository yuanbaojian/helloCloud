package com.ybj.redPacket.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-16
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @PostMapping("zCard")
    public void test(){
        ZSetOperations<String, String> setOperations = stringRedisTemplate.opsForZSet();
        setOperations.add("key1","value1",1);
        setOperations.add("key1","value2",3);
        setOperations.add("key1","value3",3.1);
        setOperations.add("key1","value4",2.9);
        setOperations.add("key1","value5",2);
        // 范围取值（由小到大）
        Set<String> values = setOperations.range("key1", 0, 9);
        setOperations.reverseRank("key1",2);
        // 范围取值（由大到小）
        Set<String> reverseRange = setOperations.reverseRange("key1", 0, 9);

        log.info(">>> values 为 {}",values);
    }


    @PostMapping("/redisTest")
    public void testRedis(){


    }



}

