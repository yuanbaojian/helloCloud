// package com.ybj.elasticsearch5.config;
//
// import org.elasticsearch.client.transport.TransportClient;
// import org.elasticsearch.common.settings.Settings;
// import org.elasticsearch.common.transport.InetSocketTransportAddress;
// import org.elasticsearch.transport.client.PreBuiltTransportClient;
// import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
// import org.springframework.beans.factory.DisposableBean;
// import org.springframework.beans.factory.FactoryBean;
// import org.springframework.beans.factory.InitializingBean;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.util.StringUtils;
//
// import java.net.InetAddress;
// import java.net.UnknownHostException;
//
// /**
//  *TransportClientFactoryBean
//  *
//  *@author liuf
//  */
// @Configuration
// public class ElasticsearchConfiguration implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {
//
//     @Value("${spring.data.elasticsearch.cluster-nodes}")
//     private String clusterNodes ;
//
//     @Value("${spring.data.elasticsearch.cluster-name}")
//     private String clusterName;
//     @Value("${spring.data.elasticsearch.username-password}")
//     private String usernamePassword;
//
//     /**
//      * 超时时间
//      */
//     @Value("${spring.data.elasticsearch.properties.transport.tcp.connect_timeout}")
//     private String connectTimeout;
//     /**
//      * 等待来自节点的ping响应的时间。默认值为5s。
//      */
//     @Value("${spring.data.elasticsearch.properties.client.transport.ping_timeout}")
//     private String pingTimeout;
//     /**
//      * 采样/ ping列出和连接的节点的频率。默认值为5s
//      */
//     @Value("${spring.data.elasticsearch.properties.client.transport.nodes_sampler_interval}")
//     private String nodesSamplerInterval;
//
//     private TransportClient client;
//
//     @Override
//     public void destroy() throws Exception {
//         try {
//             logger.info("Closing elasticSearch client");
//             if (client != null) {
//                 client.close();
//             }
//         } catch (final Exception e) {
//             logger.error("Error closing ElasticSearch client: ", e);
//         }
//     }
//
//     @Override
//     public TransportClient getObject() throws Exception {
//         return client;
//     }
//
//     @Override
//     public Class<TransportClient> getObjectType() {
//         return TransportClient.class;
//     }
//
//     @Override
//     public boolean isSingleton() {
//         return false;
//     }
//
//     @Override
//     public void afterPropertiesSet() throws Exception {
//         buildClient();
//     }
//
//     protected void buildClient() {
//         try {
//             //x-pack权限方法
//             if (!StringUtils.isEmpty(usernamePassword)) {
//                 PreBuiltXPackTransportClient preBuiltTransportClient = new PreBuiltXPackTransportClient(settings());
//                 if (!"".equals(clusterNodes)) {
//                     for (String nodes : clusterNodes.split(",")) {
//                         String InetSocket[] = nodes.split(":");
//                         String Address = InetSocket[0];
//                         Integer port = Integer.valueOf(InetSocket[1]);
//                         preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(Address), port));
//                     }
//                     client = preBuiltTransportClient;
// //                client.threadPool().getThreadContext().putHeader("Authorization", "Basic "+ Encodes.decodeBase64(usernamePassword));
//                 }
//             } else {
//                 //没有x-pack权限访问
//                 PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings());
//                 if (!"".equals(clusterNodes)) {
//                     for (String nodes:clusterNodes.split(",")) {
//                         String InetSocket [] = nodes.split(":");
//                         String  Address = InetSocket[0];
//                         Integer  port = Integer.valueOf(InetSocket[1]);
//                         preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(Address),port ));
//                     }
//                     client = preBuiltTransportClient;
//                 }
//             }
//         } catch (UnknownHostException e) {
//             logger.error(e.getMessage());
//         }
//
//     }
//
//     private Settings settings(){
//         Settings.Builder settings = Settings.builder();
//         settings.put("cluster.name",clusterName)
// //            .put("client.transport.ignore_cluster_name", false)
// //            .put("client.transport.sniff", true)
// //            .put("xpack.security.transport.ssl.enabled", true)
//             .put("client.transport.nodes_sampler_interval", nodesSamplerInterval) // elasticSearch 健康检查时间
//             .put("transport.tcp.connect_timeout", connectTimeout) // elasticSearch 超时时间
//             .put("client.transport.ping_timeout",pingTimeout);    //elasticSearch ping
//         //x-pack权限方法
//         if (!StringUtils.isEmpty(usernamePassword)) {
//             settings.put("xpack.security.user", usernamePassword);
//         } else {
//
//         }
//         return settings.build();
//     }
//
// }
//
