package com.ybj.es.service;

import com.ybj.es.model.IdeaLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class CrawlerLogServiceTest {

    @Autowired
    CrawlerLogService crawlerLogService;

    @Test
    public void test(){
        log.info(" the system inserted a log");
        List<IdeaLog> valid = crawlerLogService.getAllByMessageLike("valid");
        System.out.println("valid = " + valid);



    }

}