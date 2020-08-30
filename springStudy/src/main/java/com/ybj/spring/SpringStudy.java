package com.ybj.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author Mysql
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@ComponentScan(basePackages = {"com.ybj.*"})
@MapperScan({"com.ybj.spring.dao"})
@EnableAsync
@EnableEurekaClient
@SpringBootApplication()
public class SpringStudy {
    public static void main(String[] args) {
        SpringApplication.run(SpringStudy.class, args);
    }

}
