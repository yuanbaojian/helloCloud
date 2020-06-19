package com.ybj.crawler.service;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.utils.Crawler.IPCrawler.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author IpBeanThreadService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Service
@Slf4j
public class IpBeanThreadService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
    * <p> 目前还有点问题，会有异常，问题还没解决</p>
     * @param elements
     * @param i
     * @return java.util.concurrent.CompletableFuture<com.ybj.crawler.model.IpBean>
     * @author yuanbaojian
     * @date 2020/6/11
     * @time 21:31
     */
    @Async
    public CompletableFuture<IpBean> checkIpValidByThreadPool(Elements elements, int i) {
        log.info("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
        IpBean ipBean = new IpBean();
        setIpBeanAttribute(elements, i, ipBean);
        boolean valid = IPUtils.isValid(ipBean);
        if (valid) {
            redisTemplate.opsForSet().add("validIpSet", ipBean);
            log.info(ipBean.getIpAddress() + " 有效，已存入redis");
        }
        return CompletableFuture.completedFuture(ipBean);
    }

    private static void setIpBeanAttribute(Elements elements, int i, IpBean ipBean) {
        String ipAddress = elements.get(i).children().get(1).text();
        Integer ipPort = Integer.valueOf(elements.get(i).children().get(2).text().trim());
        String serverAddress = elements.get(i).children().get(3).text();
        String anonymityType = elements.get(i).children().get(4).text();
        String protocolType = elements.get(i).children().get(5).text();
        ipBean.setIpAddress(ipAddress);
        ipBean.setIpPort(ipPort);
        ipBean.setAnonyType(anonymityType);
        ipBean.setProtocolType(protocolType);
        ipBean.setServerAddress(serverAddress);
    }


    @Async
    public CompletableFuture<IpBean> checkIpValidByThreadPool(IpBean ipBean) {
        IPUtils ipUtils = new IPUtils();
        boolean valid = ipUtils.checkIpValid(ipBean);
        String threadName = Thread.currentThread().getName();
        if (valid) {
            redisTemplate.opsForSet().add("validIpSet", ipBean);
            log.info(ipBean.getIpAddress() + " is valid and has already stored into redis");
        } else{
            ipBean = null;
        }
        return CompletableFuture.completedFuture(ipBean);
    }
}
