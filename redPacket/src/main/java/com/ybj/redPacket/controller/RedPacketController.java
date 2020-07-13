package com.ybj.redPacket.controller;


import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
@Slf4j
@RestController
@RequestMapping("/redPacket")
public class RedPacketController {

    public static final String RED_PACKET_NUMBER = "redPacketNumber";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedissonClient redissonClient;
    /*

    *
    * */
    /**
    * <p>单机版的锁
     * 分布式会有问题
     * 也可以使用 @Synchronized 代替
     * </p>
     * @param
     * @return java.lang.Integer
     * @author yuanbaojian
     * @date 2020/7/3
     * @time 11:07
     */
    @GetMapping("/getNumber")
    public Integer getNumber(){
        Integer redPacketNumber;
        synchronized (this){
            log.info("《idea redPacket》   请求红包数量");
            redPacketNumber = Integer.valueOf(stringRedisTemplate.opsForValue().get(RED_PACKET_NUMBER));
            if(redPacketNumber > 0){
                stringRedisTemplate.opsForValue().set(RED_PACKET_NUMBER, String.valueOf(--redPacketNumber));
                log.info("扣除成功，剩余库存 {}", redPacketNumber);
            } else {
                log.warn("扣除失败, 数量为{}", redPacketNumber);
            }
        }
        return redPacketNumber;
    }


    /**
    * <p>使用分布式锁
     * 借助Redis的setIfAbsent </p>
     * @paramz
     * @return java.lang.Integer
     * @author yuanbaojian
     * @date 2020/7/3
     * @time 11:04
     */
    @GetMapping("/getNumberByDistributedRedis")
    public Integer getNumberByDistributed(){
        String redPacket = "redPacket";
        Integer redPacketNumber;
        String clientId = UUID.randomUUID().toString();
        try{
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(redPacket, clientId, 60, TimeUnit.SECONDS);
            //已存在，相当于已加锁
            if(!aBoolean){
                log.info("此次{}请求已加锁，不能进行", Thread.currentThread().getName());
                return -1;
            }
            log.info("《idea redPacket》   请求红包数量");
            redPacketNumber = Integer.valueOf(stringRedisTemplate.opsForValue().get(RED_PACKET_NUMBER));
            if(redPacketNumber > 0){
                stringRedisTemplate.opsForValue().set(RED_PACKET_NUMBER, String.valueOf(--redPacketNumber));
                log.info("扣除成功，剩余库存 {}", redPacketNumber);
            } else {
                log.warn("扣除失败, 数量为{}", redPacketNumber);
            }
        } finally{
            //释放锁(为当前锁)
            String valueInRedis = stringRedisTemplate.opsForValue().get(redPacket);
            if(clientId.equals(valueInRedis)){
                log.info("{}线程已解锁",Thread.currentThread().getName());
                stringRedisTemplate.delete(redPacket);
            } else{
                log.info("redis中的值{} 不等于 当前值{}", valueInRedis,clientId);
            }
        }
        return redPacketNumber;
    }


    /**
    * <p>使用Redisson充当分布式锁</p>
     * @param
     * @return java.lang.Integer
     * @author yuanbaojian
     * @date 2020/7/6
     * @time 14:31
     */
    @GetMapping("/getNumberByRedisson")
    public Integer getNumberByRedisson(){
        String redPacket = "redPacket";
        Integer redPacketNumber;
        RLock redissonLock = redissonClient.getLock(redPacket);
        try{
            redissonLock.lock(30, TimeUnit.SECONDS);
            log.info("{}进入redisson分布式锁的接口中",Thread.currentThread().getName());
            log.info("《idea redPacket》   请求红包数量");
            redPacketNumber = Integer.valueOf(stringRedisTemplate.opsForValue().get(RED_PACKET_NUMBER));
            if(redPacketNumber > 0){
                stringRedisTemplate.opsForValue().set(RED_PACKET_NUMBER, String.valueOf(--redPacketNumber));
                log.info("扣除成功，剩余库存 {}", redPacketNumber);
            } else {
                log.warn("扣除失败, 数量为{}", redPacketNumber);
            }
        } finally{
           redissonLock.unlock();
        }
        return redPacketNumber;
    }


    /**
    * <p> 使用redisson实现bloomFilter</p>
     * @param param
     * @return java.lang.Boolean
     * @author yuanbaojian
     * @date 2020/7/8
     * @time 9:56
     */
    @GetMapping("bloomFilter")
    public Boolean bloomFilter(String param){
        log.info("待判定的参数为{}",param);
        RBloomFilter<String>  rBloomFilter = redissonClient.getBloomFilter("filter");
        rBloomFilter.tryInit(500000L, 0.03);
        rBloomFilter.add("hello");
        rBloomFilter.add("world");
        boolean result = rBloomFilter.contains(param);
        log.info("是否包含的结果为{}",result);
        return result;
    }



}

