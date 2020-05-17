package com.ybj.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author deptProvider
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@MapperScan({"com.ybj.auth.dao"})
@SpringBootApplication(scanBasePackages = "com.ybj.*")
@EnableEurekaClient
@EnableFeignClients
public class authApplication {
    public static void main(String[] args) {
        SpringApplication.run(authApplication.class, args);
    }
}
