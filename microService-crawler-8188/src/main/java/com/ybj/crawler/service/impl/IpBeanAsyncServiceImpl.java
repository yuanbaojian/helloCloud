package com.ybj.crawler.service.impl;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanAsyncService;
import com.ybj.crawler.utils.Crawler.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @Author IpBeanAsyncServiceImpl
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Service
public class IpBeanAsyncServiceImpl implements IpBeanAsyncService {

    private static final IPUtils ipUtils = IPUtils.getInstance();

    @Autowired
    RedisTemplate redisTemplate;

    @Async
    @Override
    public void filterIp(IpBean ipBean, Set<IpBean> ipBeansInRedis, CountDownLatch countDownLatch) {
        boolean valid = IPUtils.isValid(ipBean);
        log.info("{} 检测 {} 的 有效性是 {}", Thread.currentThread().getName(), ipBean.toString(), valid);
        if(!valid){
            ipBeansInRedis.remove(ipBean);
            redisTemplate.opsForSet().remove("validIpSet", ipBean);
        }
        countDownLatch.countDown();
    }

    @Async
    @Override
    public void checkIpValidAndStore(IpBean ipBean) {
        boolean valid = false;
        try{
            valid = ipUtils.checkIpAvailable(ipBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(valid){
            redisTemplate.opsForSet().add("validIpSet",ipBean);
        }
        log.info("工具类 {} 检测{} 的有效性是 {}",ipUtils.getClass(), ipBean.toString(),valid );
    }

    @Async
    @Override
    public Future<Boolean> checkIpValidAndStoreByFuture(IpBean ipBean) {
        boolean valid = false;
        try{
            valid = ipUtils.checkIpAvailable(ipBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(valid){
            redisTemplate.opsForSet().add("validIpSet",ipBean);
        }
        log.info("工具类 {} 检测{} 的有效性是 {}",ipUtils.getClass(), ipBean.toString(),valid );
        return new AsyncResult<Boolean>(valid);
    }

    @Async
    @Override
    public CompletableFuture<Boolean> checkIpValidAndStoreByCF(IpBean ipBean) {
        boolean valid = false;
        try{
            valid = ipUtils.checkIpAvailable(ipBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(valid){
            redisTemplate.opsForSet().add("validIpSet",ipBean);
        }
        log.info("工具类 {} 检测{} 的有效性是 {}",ipUtils.getClass(), ipBean.toString(),valid );
        return CompletableFuture.completedFuture(valid);
    }
}
