package com.ybj.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author deptProvider
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class zuul {
    public static void main(String[] args) {
        SpringApplication.run(zuul.class, args);
    }
}
