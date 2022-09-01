package com.ybj.elasticsearch5;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RunWith(SpringRunner.class)
@SpringBootTest
class ElasticSearch5ApplicationTests {



    @Test
    void test() throws UnknownHostException, ExecutionException, InterruptedException {
        // TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
        //         .put("cluster.name", "es-cn-0pp1m9rue000dunms") //替换为对应阿里云ES实例的ID。
        //         .put("xpack.security.user", "elastic:CwWkTuF1AY14HA3OTo66") //阿里云ES实例的用户名、密码。
        //         .put("client.transport.sniff", false) //设置sniff为false。
        //         .build())
        //         .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("es-cn-zvp29oayp001mbgk5.elasticsearch.aliyuncs.com"), 9300));
        // GetAliasesRequest request = new GetAliasesRequest();
        // ActionFuture<GetAliasesResponse> aliases = client.admin().indices().getAliases(request);
        // ImmutableOpenMap<String, List<AliasMetaData>> objectObjectCursors = aliases.get().getAliases();
        //key 为索引名  value为别名
        // objectObjectCursors.values();
        // objectObjectCursors.keys();
        // log.info("keys结果为{}");
    }

    @Test
    void deleteTestIndex() throws UnknownHostException, ExecutionException, InterruptedException {
        TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
                .put("cluster.name", "es-cn-mp91mb9ff000j5bde") //替换为对应阿里云ES实例的ID。
                .put("xpack.security.user", "elastic:CwWkTuF1AY14HA3OTo66") //阿里云ES实例的用户名、密码。
                .put("client.transport.sniff", false) //设置sniff为false。
                .build())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("es-cn-mp91mb9ff000j5bde.elasticsearch.aliyuncs.com"), 9300));
        GetAliasesRequest request = new GetAliasesRequest();
        ActionFuture<GetAliasesResponse> aliases = client.admin().indices().getAliases(request);
        ImmutableOpenMap<String, List<AliasMetaData>> objectObjectCursors = aliases.get().getAliases();
        // key 为索引名  value为别名
        objectObjectCursors.values();
        objectObjectCursors.keys();
    }





}
