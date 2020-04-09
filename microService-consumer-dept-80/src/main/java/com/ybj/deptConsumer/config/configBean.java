package com.ybj.deptConsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author configBean
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Configuration
public class configBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
