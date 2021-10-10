package com.ybj.crawler.config;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MQBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(@NotNull  Object bean ,@NotNull String beanName){

        log.info("开始初始化bean啦, bean为 {},bean的name为{}",bean.toString(),beanName);
        return null;
    }
}
