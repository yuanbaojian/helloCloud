package com.ybj.redPacket;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan({"com.ybj.redPacket.dao"})
// @ComponentScan(basePackages = {"com.ybj.api.*"})
@EnableFeignClients(basePackages = "com.ybj.redPacket.feign")
@SpringBootApplication
@EnableAsync
@EnableEurekaClient
public class redPacket {

    public static void main(String[] args) {
        SpringApplication.run(redPacket.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://yuanbaojian.xyz:6379").setPassword("312ybj").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }


}
