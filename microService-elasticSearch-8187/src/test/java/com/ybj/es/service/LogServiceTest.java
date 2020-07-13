package com.ybj.es.service;

import com.ybj.es.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class LogServiceTest {

    @Autowired
    LogService logService;

    @Test
    public void test(){
        List<Log> logList = logService.findAllByMessageLike("entered");
        for (Log log1 : logList) {
            System.out.println("log1.toString() = " + log1.toString());
        }
    }

}