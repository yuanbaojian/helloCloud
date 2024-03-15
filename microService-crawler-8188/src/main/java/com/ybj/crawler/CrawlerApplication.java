package com.ybj.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.print.PrintService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@ComponentScan(basePackages = {"com.ybj.*"})
@MapperScan({"com.ybj.crawler.dao"})
@EnableAsync
//@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.ybj.*"})
@EnableRetry
public class CrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }

}
