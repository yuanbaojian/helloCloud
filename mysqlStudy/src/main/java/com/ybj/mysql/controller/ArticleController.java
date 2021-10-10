package com.ybj.mysql.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybj.api.annotation.GetParamLog;
import com.ybj.api.model.JsonResult;
import com.ybj.mysql.dao.ArticleMapper;
import com.ybj.mysql.model.Article;
import com.ybj.mysql.service.ArticleServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
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
@Api(tags = "使用文章表，进行mysql性能测试")
public class ArticleController {



    @Autowired
    ArticleServiceI articleService;

    @Autowired
    ArticleMapper articleMapper;


    /**
     * 为什么我制定了rollbackFor的类型， 还是回滚了？？？
     * 因为 rollbackfor会自动 加入ArithmeticException.class
     */
    @Transactional(rollbackFor = {ArithmeticException.class})
    @GetMapping("/addArticles")
    @ApiOperation(value = "对文章进行扩容",notes = "增大数据库的文章数量")
    public JsonResult addArticles() throws FileNotFoundException, InterruptedException {
        Article article = new Article();
        article.setAuthorId(1).setCategoryId(1).setComments(1)
                .setContent(String.valueOf(1)).setTitile(String.valueOf(1)).setViews(1);
        articleMapper.insertOne(article);
        Thread.sleep(1000000);
        return JsonResult.ok();
        // File file = new File("/ybj/1.txt");
        // InputStream inputStream = new FileInputStream(file);
    }


    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查找",notes = "分页查找，note")
    public Page<Article> getByPage(){
        Page<Article> page = new Page<>(1,10);
        return articleService.page(page);
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
        for(int i = maxArticle.getId() +1 ; i < maxArticle.getId() + 30; i++) {
            Article article = new Article();
            article.setAuthorId(i).setCategoryId(i).setComments(i)
                    .setContent(String.valueOf(i)).setTitile(String.valueOf(i)).setViews(i);
            articleService.expand(article, i);
        }
        log.info("执行完毕");
    }

    /**
     * @param size
     * 10000条数据  12496  12584  14508 ms
     */
    @GetMapping("/expandByForWithOutId")
    @ApiOperation(value = "无id for循环 插入   对文章进行扩容")
    public void expandWithOutId(Long size){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("无id 插入批量数据");
        for(int i = 0 ; i <  size; i++) {
            Article article = new Article();
            article.setAuthorId(i).setCategoryId(i).setComments(i)
                    .setContent(String.valueOf(i)).setTitile(String.valueOf(i)).setViews(i);
            articleMapper.insertOne(article);
            log.info("正在保存第 {} 条",i);
        }
        stopWatch.stop();
        log.info("执行完毕, 花费时间 {}", stopWatch.getTotalTimeMillis());
    }


    /**
     * @param size
     * 10000条数据  1335  987`  1121 ms
     */
    @GetMapping("/expandByEachWithOutId")
    @ApiOperation(value = "无id foreach插入   对文章进行扩容")
    public void expandByEachWithOutId(Long size){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("无id 插入批量数据");
        List<Article> list = new ArrayList<>();
        for(int i = 0 ; i <  size; i++) {
            Article article = new Article();
            article.setAuthorId(i).setCategoryId(i).setComments(i)
                    .setContent(String.valueOf(i)).setTitile(String.valueOf(i)).setViews(i);
            list.add(article);
            // articleMapper.insertOne(article);
        }
        articleMapper.saveBatch(list);
        stopWatch.stop();
        log.info("执行完毕, 花费时间 {}", stopWatch.getTotalTimeMillis());
    }


    /**
     * @param size
     * 10000条数据  1341  990  1121 ms
     */
    @GetMapping("/expandByEachWithId")
    @ApiOperation(value = "有id foreach插入   对文章进行扩容")
    public void expandByEachWithId(Long size){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("无id 插入批量数据");
        List<Article> list = new ArrayList<>();
        Random random = new Random();

        for(int i = 0 ; i <  size; i++) {
            Article article = new Article();
            article.setId(1000000 + random.nextInt(10000000));
            article.setAuthorId(i).setCategoryId(i).setComments(i)
                    .setContent(String.valueOf(i)).setTitile(String.valueOf(i)).setViews(i);
            list.add(article);
            // articleMapper.insertOne(article);
        }
        articleMapper.saveBatch(list);
        stopWatch.stop();
        log.info("执行完毕, 花费时间 {}", stopWatch.getTotalTimeMillis());
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

    @GetParamLog
    @GetMapping("/export")
    @ApiOperation(value = "export",notes = "导出excel")
    public JsonResult export() throws Exception {
        LocalDateTime start = LocalDateTime.now();
        String fileName = "C:\\Users\\yuanbaojian\\Desktop\\EasyExcel.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, Article.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("测试sheet").build();
        int number = 25;
        CountDownLatch countDownLatch = new CountDownLatch(number);
        for(int i = 1; i <= number; i++) {
            articleService.writeToExcel(i,fileName, excelWriter,writeSheet,countDownLatch);
        }
        countDownLatch.await();
        excelWriter.finish();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return JsonResult.ok("执行时长" + duration.toMillis());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
    }

    @GetParamLog
    @GetMapping("/ReadAndExportWithSingleThread")
    @ApiOperation(value = "ReadAndExportWithSingleThread",notes = "先读取所有数据，再存放到excel中,单线程操作")
    public JsonResult ReadAndExport() throws Exception {
        LocalDateTime start = LocalDateTime.now();
        String fileName = "C:\\Users\\yuanbaojian\\Desktop\\EasyExcel.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, Article.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("测试sheet").build();
        int number = 50;
        List allList = new ArrayList();
        for(int i = 1; i <= number; i++) {
            log.info("查询{}页",i);
            IPage<Article> iPage = new Page<>();
            iPage.setCurrent(i);
            iPage.setSize(10000);
            IPage<Article> result = articleMapper.selectPage(iPage, null);
            List<Article> articleList = result.getRecords();
            allList.addAll(articleList);
        }
        excelWriter.write(allList, writeSheet);
        excelWriter.finish();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return JsonResult.ok("执行时长" + duration.toMillis());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
    }
    @GetParamLog
    @GetMapping("/ReadAndExportWithMultiThread")
    @ApiOperation(value = "ReadAndExportWithMultiThread",notes = "多线程先读取所有数据，再存放到excel中")
    public JsonResult ReadAndExportWithMultiThread() throws Exception {
        LocalDateTime start = LocalDateTime.now();
        String fileName = "C:\\Users\\yuanbaojian\\Desktop\\EasyExcel.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, Article.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("测试sheet").build();
        int number = 140;
        List allList = new ArrayList();
        List<CompletableFuture<List<Article>>> futureList = new LinkedList<>();
        for(int i = 1; i <= number; i++) {
            CompletableFuture<List<Article>> listCompletableFuture = articleService.getFromDB(i);
            futureList.add(listCompletableFuture);
        }
        for (CompletableFuture<List<Article>> listCompletableFuture : futureList) {
            List<Article> articles = listCompletableFuture.get();
            allList.addAll(articles);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        log.info("总长度为{}",allList.size());
        excelWriter.write(allList, writeSheet);
        excelWriter.finish();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return JsonResult.ok("执行时长" + duration.toMillis());
    }

    @GetParamLog
    @GetMapping("/getSerachTime")
    @ApiOperation(value = "getSerachTime",notes = "测试获得特定条数时间")
    public JsonResult getSerachTime(Integer count) throws Exception {
        LocalDateTime start = LocalDateTime.now();
        List<Article> articleList = articleMapper.getCount(count);
        log.info("总长度为{}",articleList.size());
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return JsonResult.ok("执行时长" + duration.toMillis());
    }




}

