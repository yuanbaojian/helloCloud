package com.ybj.crawler.utils.Email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmailServiceImplTest {

    @Test
    void sendSimpleMessage() {
        EmailServiceImpl emailService=new EmailServiceImpl();
        emailService.sendSimpleMessage("312ybj@Gmail.com",  "qq测试右键", "收到了吗");
    }
}