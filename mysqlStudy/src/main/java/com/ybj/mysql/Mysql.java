package com.ybj.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@MapperScan({"com.ybj.mysql.dao"})
@EnableAsync
// @EnableEurekaClient
@SpringBootApplication()
public class Mysql {
    public static void main(String[] args) {
        SpringApplication.run(Mysql.class, args);
    }

}
