package com.tezign.core.mq.config;

import com.ybj.mq.config.MQEnvIsolationBeanPostProcessor;
import com.ybj.mq.config.MQEnvIsolationProperties;
import com.ybj.mq.util.MQEnvIsolationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Zhang Jian
 * @since 0.0.1
 */
@Configuration
@EnableConfigurationProperties(MQEnvIsolationProperties.class)
@Slf4j
@RequiredArgsConstructor
public class MQEnvIsolationAutoConfiguration {

    private final MQEnvIsolationProperties mqEnvIsolationProperties;

    @Bean
    @ConditionalOnMissingBean(MQEnvIsolationBeanPostProcessor.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public MQEnvIsolationBeanPostProcessor getMQEnvIsolationBeanPostProcessor() {
        // 初始化工具类中的环境名
        MQEnvIsolationUtils.setEnv(mqEnvIsolationProperties.getEnv());

        return new MQEnvIsolationBeanPostProcessor();
    }

}
