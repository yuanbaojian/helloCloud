package com.ybj.mysql.service;

import com.ybj.mysql.dao.ArticleMapper;
import com.ybj.mysql.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
class ArticleServiceITest {

    @Autowired
    ArticleMapper articleMapper;


    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private MailProperties mailProperties;

    @Test
    void expand() {
        Article article = articleMapper.selectById(0);
        article.setTitile("changed");
        articleMapper.updateById(article);
        System.out.println("article.toString() = " + article.toString());
    }

    interface  testInterface{
        void setName();
    }

    class person implements testInterface {
        int a = 1;

        @Override
        public void setName() {

        }
    }



    @Test
    void add(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo("1793870688@qq.com");
        message.setSubject("from qq");
        message.setText("hello");
        mailSender.send(message);
    }

    @Test
    void lineLock() {
        LocalDateTime start = LocalDateTime.now();
        Article article = articleMapper.selectById(1);
        article.setCategoryId(article.getCategoryId()+1);
        articleMapper.selectByLineLock(1);
        articleMapper.updateById(article);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        long millis = duration.toMillis();
        System.out.println("millis = " + millis);
        System.out.println("article.toString() = " + article.toString());
    }
}