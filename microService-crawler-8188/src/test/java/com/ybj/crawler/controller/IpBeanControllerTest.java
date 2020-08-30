package com.ybj.crawler.controller;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
class IpBeanControllerTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IpBeanService ipBeanService;

    @Test
    void saveIpBeanToDB() {}

    @Test
    void getAllIp() {}

    @Test
    void getValidIp() {
        Set validIpSet = redisTemplate.opsForSet().members("validIpSet");
        System.out.println("validIpSet = " + validIpSet);
    }

    @Test
    public void test() throws IOException {
        List<IpBean> ipFromWeb = ipBeanService.getIpFromWeb();
        Long testIpList = redisTemplate.opsForList().leftPushAll("testIpList", ipFromWeb);
    }

    @Test
    public void test2(){
        List testIpList = redisTemplate.opsForList().range("testIpList", 1, 600);
        System.out.println("testIpList = " + testIpList);
    }

  @Test
  void testGetValidIp() {}

    @Test
    void testSaveIpBeanToDB() {
        List<IpBean> list = ipBeanService.list();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).setID(null);
            ipBeanService.save(list.get(i));
            log.info("正在处理第{}条记录，将id设置为null， 完成比例{}", i, i*1.00 / size);
        }
        // log.info("列表处理完毕");
        // for (int i = 0; i < 50; i++) {
        //     ipBeanService.saveBatch(list);
        //     log.info("已完成第{} 批", ipBeanService);
        // }
    }


}