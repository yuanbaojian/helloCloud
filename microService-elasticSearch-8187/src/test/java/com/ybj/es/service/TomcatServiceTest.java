package com.ybj.es.service;

import com.ybj.es.model.TomcatLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;


@SpringBootTest
class TomcatServiceTest {

    @Autowired
    TomcatService tomcatService;

    @Test
    void findAllByMessageLike() {
        List<TomcatLog> log = tomcatService.findAllByMessageLike("导入");
        for (TomcatLog tomcatLog : log) {
            System.out.println("message is " + tomcatLog.getMessage());
        }
    }
}