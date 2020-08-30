package com.ybj.mysql.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ybj.api.model.JsonResult;
import com.ybj.mysql.dao.ArticleMapper;
import com.ybj.mysql.model.Article;
import com.ybj.mysql.service.ArticleServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
@Slf4j
@RestController
@RequestMapping("/article")
@Api(value = "文章接口",tags = {"文章表进行mysql性能测试"})
public class ArticleController {



    @Autowired
    ArticleServiceI articleService;

    @Autowired
    ArticleMapper articleMapper;

    @GetMapping("/getAll")
    @ApiOperation(value = "获得所有文章",notes = "全部获取")
    public List<Article> getAll(){
        List<Article> articleList = articleService.list();
        return articleList;
    }
    @GetMapping("/getById")
    @ApiOperation(value = "根据id获得记录",notes = "单条记录")
    public Article getById(String id){
        Article article = articleService.getById(id);
        return article;
    }

    @GetMapping("/getByAuthorId")
    @ApiOperation(value = "根据authorId获得记录",notes = "单条记录")
    public Article getByIp(String authorId){
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq((SFunction<Article, Integer>) article -> article.getAuthorId(),authorId);
        Article article = articleService.getOne(lambdaQueryWrapper);
        return article;
    }

    @GetMapping("/expand")
    @ApiOperation(value = "对文章进行扩容",notes = "增大数据库的文章数量")
    public void expand(){
        Article maxArticle = articleService.getMaxIdArticle();
        for(int i = maxArticle.getId() +1 ; i < 20000000; i++) {
            Article article = new Article();
            article.setId(i).setAuthorId(i).setCategoryId(i).setComments(i)
                    .setContent(String.valueOf(i)).setTitile(String.valueOf(i)).setViews(i);
            articleService.expand(article, i);
        }
        log.info("执行完毕");
    }
    @GetMapping("/indexNotWork")
    @ApiOperation(value = "主键索引失效",notes = "组件索引失效，查询速度变慢")
    public JsonResult lineLock(){
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
        return JsonResult.ok("执行时间 " + millis );
    }

    @GetMapping("/indexWork")
    @ApiOperation(value = "组件索引有效",notes = "使用组件索引，测试执行时间")
    public JsonResult withOutLock(){
        LocalDateTime start = LocalDateTime.now();
        Article article = articleMapper.selectById(1);
        article.setCategoryId(article.getCategoryId()+1);
        articleMapper.selectById(1);
        articleMapper.updateById(article);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        long millis = duration.toMillis();
        System.out.println("millis = " + millis);
        System.out.println("article.toString() = " + article.toString());
        return JsonResult.ok("执行时间 " + millis );
    }


    @GetMapping("/waitThreadByCountDownLatch")
    @ApiOperation(value = "等待线程测试ByCountDownLatch",notes = "等待线程结束再执行下一步ByCountDownLatch")
    public void waitThreadByCountDownLatch() throws InterruptedException {
        Article maxArticle = articleService.getMaxIdArticle();
        // 1.声明CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for(int i = 0  ; i < 200; i++) {
            // 2.在新线程内部执行  countDownLatch.countDown();
            articleService.expand(null, i,countDownLatch);
        }
        // 3.等待执行完毕
        countDownLatch.await();
        log.info("执行完毕");
    }

    /**
    * <p>Semaphore
     * 规定特定数量的线程对共享资源进行访问
     * </p>
     * @param
     * @return void
     * @author yuanbaojian
     * @date 2020/8/13
     * @time 14:02
     */
    @GetMapping("/Semaphore")
    @ApiOperation(value = "Semaphore",notes = "Semaphore")
    public void Semaphore() throws Exception {
        // 声明信号量
        Semaphore semaphore = new Semaphore(10);
        for(int i = 0  ; i < 20; i++) {
            // 新线程内部抢占信号量
            articleService.testBySemaphore(null, i,semaphore);
        }
        log.info("执行完毕");
    }


}

