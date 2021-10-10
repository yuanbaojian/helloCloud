// package com.ybj.es.config;
//
// import org.apache.http.HttpHost;
// import org.elasticsearch.client.RestClient;
// import org.elasticsearch.client.RestClientBuilder;
// import org.elasticsearch.client.RestHighLevelClient;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * @Owner yuanbaojian
//  * @Team
//  */
// @Configuration
// public class ElasticSearchClientConfig {
//
//     @Bean
//     public RestHighLevelClient restHighLevelClient(){
//         HttpHost httpHost = new HttpHost("localhost",9200,"http" );
//         RestClientBuilder builder = RestClient.builder(httpHost);
//         RestHighLevelClient restHighLevelClient = new RestHighLevelClient( builder);
//         return restHighLevelClient;
//     }
//
// }
