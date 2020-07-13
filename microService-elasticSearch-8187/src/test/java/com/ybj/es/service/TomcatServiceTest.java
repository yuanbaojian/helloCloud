package com.ybj.es.service;

import com.ybj.es.model.TomcatLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class TomcatServiceTest {

    @Autowired
    TomcatService tomcatService;

    @Test
    void findAllByMessageLike() {
        List<TomcatLog> log = tomcatService.findAllByMessageLike("日志");
        for (TomcatLog tomcatLog : log) {
            System.out.println("message is " + tomcatLog.getMessage());
        }
    }
}