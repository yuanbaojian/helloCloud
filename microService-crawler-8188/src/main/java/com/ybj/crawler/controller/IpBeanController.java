package com.ybj.crawler.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybj.api.model.JsonResult;
import com.ybj.crawler.annotation.GetExecutionTime;
import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanService;
import com.ybj.crawler.service.IpBeanThreadService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ybj
 * @since 2020-02-10
 */
@Slf4j
@RestController
@RequestMapping("/IpBean")
public class IpBeanController {

    private static String HTTP_API = "https://www.xicidaili.com/wt/";

    @Autowired
    IpBeanService ipBeanService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ThreadPoolTaskExecutor executor;

    @Autowired
    IpBeanThreadService ipBeanThreadService;

    @GetMapping("/save")
//    @Scheduled(fixedDelay = 10000)
    public void saveIpBeanToDB() throws IOException {
        List<IpBean> ipFromWeb = ipBeanService.getIpFromWeb();
        ipBeanService.saveBatch(ipFromWeb);
    }


    @GetMapping("/getAllIp")
    public IPage<IpBean> getAllIp(int pageNum, int pageSize) throws IOException {
        Page<IpBean> ipBeanPage = new Page<>(pageNum, pageSize, true);
        IPage<IpBean> page = ipBeanService.page(ipBeanPage);
        return page;
    }

    /**
     * @return 获得有效的ip代理，
     * 无需分页， 不用查mysql， 只从redis 拿
     * @throws IOException
     */
    @GetExecutionTime
    @GetMapping("/getValidIp")
    public JsonResult getValidIp() throws IOException, InterruptedException {
       Set<IpBean> validIp = ipBeanService.getValidIpByAsyncMethod();
        // Set<IpBean> validIp = ipBeanService.getValidIpByThread();
        return JsonResult.ok().add("validIPSet", validIp);

    }

    @SneakyThrows
    @GetMapping("/getIpFromDB")
    public JsonResult getIpFromDB()  {
        Page<IpBean> ipBeanPage = new Page<>(1, 200, true);
        Page<IpBean> page = ipBeanService.page(ipBeanPage);
        List<IpBean> ipBeanList = page.getRecords();
        List<IpBean> validIpBeanList = new LinkedList<>();
        log.info("get Ip from DB, count is " + ipBeanList.size());
        List<CompletableFuture<IpBean>> futureList = new LinkedList<>();
        for (IpBean ipBean : ipBeanList) {
            CompletableFuture<IpBean> ipBeanCompletableFuture = ipBeanThreadService.checkIpValidByThreadPool(ipBean);
            futureList.add(ipBeanCompletableFuture);
        }
        for (CompletableFuture<IpBean> ipBeanCompletableFuture : futureList) {
            IpBean ipBean = ipBeanCompletableFuture.get();
            if(ipBean != null){
                validIpBeanList.add(ipBean);
            }
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();

        return JsonResult.ok().add("validIpBeanList", validIpBeanList);

    }
}

