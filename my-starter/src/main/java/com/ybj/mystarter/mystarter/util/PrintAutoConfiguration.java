package com.ybj.mystarter.mystarter.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(MyPrintService.class)
public class PrintAutoConfiguration {

    public PrintAutoConfiguration() {
        System.out.println("Your custom starter is enabled!");
    }
}
