package com.ybj.es.service;

import com.ybj.es.model.Book;
import com.ybj.es.model.User;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired UserService userService;

    @Test
    void findByUserNameAndEmail() {
        for(int i = 0; i < 200; i++) {
            String number = String.valueOf(i);
            userService.save(new User().setUserId(number).setUserName("ybj"+number).setEmail("qq"+number));
        }
    }

    @Test
    public void test(){
        Iterable<User> all = userService.findAll();
        System.out.println("all = " + all);
    }

//    分页
    @Test
    public void test2(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        SearchQuery searchQuery = new NativeSearchQuery(matchAllQueryBuilder)
                .setPageable(PageRequest.of(1,10));
        Page<User> userPage = userService.search(searchQuery);
        for (User user : userPage) {
            System.out.println("user.toString() = " + user.toString());
        }
    }


    @Test
    public void test3(){
        SortBuilder sortBuilder = new FieldSortBuilder("userName").order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("database2")
                .withTypes("TBL_USER")
                .withQuery(QueryBuilders.matchAllQuery())
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(1, 10))
                .build();
        AggregatedPage<User> userList = elasticsearchTemplate.queryForPage(searchQuery, User.class);
        for (User user : userList) {
            System.out.println("user.toString() = " + user.toString());
        }
    }
}