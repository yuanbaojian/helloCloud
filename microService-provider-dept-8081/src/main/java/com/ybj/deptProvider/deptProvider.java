package com.ybj.deptProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author deptProvider
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@SpringBootApplication
@EnableEurekaClient
public class deptProvider {
    public static void main(String[] args) {
        SpringApplication.run(deptProvider.class, args);
    }
}
