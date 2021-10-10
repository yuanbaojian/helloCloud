package com.ybj.mysql.service;

import com.ybj.mysql.dao.ArticleMapper;
import com.ybj.mysql.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
class ArticleServiceITest {

    @Autowired
    ArticleMapper articleMapper;

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
        Article article = new Article();
        article.setAuthorId(1).setCategoryId(1).setComments(1)
                .setContent(String.valueOf(1)).setTitile(String.valueOf(1)).setViews(1);
        articleMapper.insertOne(article);
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