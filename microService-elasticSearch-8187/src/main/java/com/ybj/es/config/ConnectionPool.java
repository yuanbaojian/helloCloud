package com.ybj.es.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ConnectionPool {

    private static final int MAX_SIZE = 8;

    private GenericObjectPool<RestHighLevelClient> elasticPool = null;

    private static final Object lock = new Object();

    public RestHighLevelClient getElasticClient(){
        RestHighLevelClient client = null;
        if(Objects.isNull(client)){
            GenericObjectPoolConfig<RestHighLevelClient> poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxTotal(MAX_SIZE);
            EsMultiClientConfig esMultiClientConfig = new EsMultiClientConfig();
            elasticPool = new GenericObjectPool<>(esMultiClientConfig,poolConfig);
        }
        try {
            client = elasticPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
