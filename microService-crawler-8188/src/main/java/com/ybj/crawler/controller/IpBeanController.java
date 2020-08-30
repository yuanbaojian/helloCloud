package com.ybj.crawler.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybj.api.model.JsonResult;
import com.ybj.crawler.annotation.GetExecutionTime;
import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanService;
import com.ybj.crawler.service.IpBeanThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.concurrent.ExecutionException;

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
@Api(value = "IP接口",tags = {"对IP进行爬取，过滤，回显等操作"})
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
    @ApiOperation(value = "爬取IP",notes = "从公共代理网站中爬取有效IP代理")
    public String saveIpBeanToDB() throws IOException, ExecutionException, InterruptedException {
        String result = ipBeanService.getIpFromFreeProxy();
        return  result;
    }


    @GetMapping("/getAllIp")
    public IPage<IpBean> getAllIp(int pageNum, int pageSize) throws IOException {
        Page<IpBean> ipBeanPage = new Page<>(pageNum, pageSize, true);
        IPage<IpBean> page = ipBeanService.page(ipBeanPage);
        return page;
    }

    /**
     * @return 获得有效的ip代理，
     * 从redis中过滤
     * @throws IOException
     */
    @GetExecutionTime
    @GetMapping("/getValidIp")
    @ApiOperation(value = "获得有效的IP代理",notes = "从Redis中获取")
    public JsonResult getValidIp() throws InterruptedException {
        Set<IpBean> validIp = ipBeanService.getValidIpByAsyncMethod();
        return JsonResult.ok().add("validIPSet", validIp);
    }


    // @GetExecutionTime
    @GetMapping("/expand")
    public JsonResult expand() throws IOException, InterruptedException {
        List<IpBean> list = ipBeanService.list();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ipBeanService.expandNumber(list.get(i));
            log.info("正在处理第{}条记录，将id设置为null， 完成比例{}", i, i*1.00 / size);
        }
        return JsonResult.ok();
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

