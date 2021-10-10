package com.ybj.es.service;

import com.ybj.es.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {


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

    public <T> T getResult(T t){
        System.out.println("t = " + t);
        return t;
    }

    @Test
    public void test1(){
        UserServiceTest userServiceTest = new UserServiceTest();
        userServiceTest.getResult("hello");
    }

//    分页
    @Test
    public void test2(){
        // MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // SearchQuery searchQuery = new NativeSearchQuery(matchAllQueryBuilder)
        //         .setPageable(PageRequest.of(1,10));
        // Page<User> userPage = userService.search(searchQuery);
        // for (User user : userPage) {
        //     System.out.println("user.toString() = " + user.toString());
        // }
    }


    @Test
    public void test3(){
        // SortBuilder sortBuilder = new FieldSortBuilder("userName").order(SortOrder.DESC);
        // SearchQuery searchQuery = new NativeSearchQueryBuilder()
        //         .withIndices("database2")
        //         .withTypes("TBL_USER")
        //         .withQuery(QueryBuilders.matchAllQuery())
        //         .withSort(sortBuilder)
        //         .withPageable(PageRequest.of(1, 10))
        //         .build();
        // AggregatedPage<User> userList = elasticsearchTemplate.queryForPage(searchQuery, User.class);
        // for (User user : userList) {
        //     System.out.println("user.toString() = " + user.toString());
        // }
    }
}