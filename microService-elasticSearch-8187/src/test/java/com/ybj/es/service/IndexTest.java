package com.ybj.es.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class IndexTest {

    @Autowired
    BookService bookService;

    // @Autowired
    // RestHighLevelClient restHighLevelClient;

    @Autowired
    TransportClient transportClient;

    // @Autowired
    // ConnectionPool connectionPool;

    @Test
    public void searchAndDeleteIndex() throws IOException {
        GetAliasesRequest request = new GetAliasesRequest();
        ActionFuture<GetAliasesResponse> aliases = transportClient.admin().indices().getAliases(request);
        //key 为索引名  value为别名
        // aliases.keySet().forEach( a->{
        //     if(a.contains("material")){
        //         log.info("素材索引名为 {}\n",a);
        //     }
        // });
    }

}