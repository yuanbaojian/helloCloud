package com.ybj.crawler.service;

import com.ybj.crawler.model.IpBean;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @Author IpBeanAsyncService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface IpBeanAsyncService {
    //过滤无效IP
    void filterIp(IpBean ipBean, Set<IpBean> ipBeansInRedis, CountDownLatch countDownLatch);

    //检测ip有效性，并存入redis
    void checkIpValidAndStore(IpBean ipBean);

    Future<Boolean> checkIpValidAndStoreByFuture(IpBean ipBean);

    CompletableFuture<Boolean> checkIpValidAndStoreByCF(IpBean ipBean);
}
