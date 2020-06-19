package com.ybj.redPacket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan({"com.ybj.redPacket.dao"})
@SpringBootApplication()
@EnableAsync
public class redPacket {

    public static void main(String[] args) {
        SpringApplication.run(redPacket.class, args);
    }

}
