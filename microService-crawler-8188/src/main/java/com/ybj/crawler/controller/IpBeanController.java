package com.ybj.crawler.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybj.api.model.JsonResult;
import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ybj
 * @since 2020-02-10
 */
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
    @GetMapping("/getValidIp")
    public JsonResult getValidIp() throws IOException, InterruptedException {
//        Set<IpBean> validIp = ipBeanService.getValidIpByAsyncMethod();
        Set<IpBean> validIp = ipBeanService.getValidIpByThread();
        return JsonResult.ok().add("validIPSet", validIp);

    }


}

