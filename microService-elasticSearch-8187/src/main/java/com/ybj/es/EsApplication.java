package com.ybj.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * elasticSearch 研究
 **/
@SpringBootApplication(scanBasePackages = {"com.ybj.es"})
public class EsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }
}
