package com.ybj.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author deptProvider
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@SpringBootApplication
@EnableEurekaServer
public class eureka {
    public static void main(String[] args) {
        SpringApplication.run(eureka.class, args);
    }
}
