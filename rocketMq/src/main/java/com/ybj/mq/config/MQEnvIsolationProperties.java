package com.ybj.mq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * RocketMQ环境隔离自定义参数配置类
 * 与 resources/META_INF/spring-configuration-metadata.json 配置信息绑定
 *
 * @author Zhang Jian
 * @since 0.0.1
 */
@Data
@ConfigurationProperties(prefix = "rocketmq.customized")
public class MQEnvIsolationProperties {

    /**
     * 环境名
     */
    private String env;

}
