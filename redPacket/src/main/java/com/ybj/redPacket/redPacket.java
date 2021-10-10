package com.ybj.redPacket;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ServletComponentScan
@MapperScan({"com.ybj.redPacket.dao"})
@ComponentScan(basePackages = {"com.ybj.*"})
@EnableFeignClients(basePackages = "com.ybj.redPacket.feign")
@SpringBootApplication
@EnableAsync
// @EnableEurekaClient
public class redPacket {

    public static void main(String[] args) {
        SpringApplication.run(redPacket.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.172.161.123:6389").setPassword("SSVFDKBJNDFNJ@rfgvf3244").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }


}
