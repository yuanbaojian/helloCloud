package com.ybj.rabbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * rabbitMq 研究
 **/
@EnableRabbit
@MapperScan({"com.ybj.rabbit.dao"})
@ComponentScan(basePackages = {"com.ybj.*"})
@SpringBootApplication
public class rabbit {
    public static void main(String[] args) {
        SpringApplication.run(rabbit.class, args);
    }
}
