package com.ybj.deptConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @Author deptProvider
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class deptConsumer {
    public static void main(String[] args) {
        SpringApplication.run(deptConsumer.class, args);
    }
}
