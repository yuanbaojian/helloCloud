package com.ybj.mysql.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ybj.mysql.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
public interface ArticleServiceI extends IService<Article> {

    void expand(Article article, int i);

    @Async
    void expand(Article article, int i, CountDownLatch countDownLatch);

    Article getMaxIdArticle();

    void testByCycliBarrier(Object o, int i, CyclicBarrier cyclicBarrier);

    void testBySemaphore(Object o, int i, Semaphore semaphore) throws InterruptedException;

    void writeToExcel(int count, String fileName, ExcelWriter excelWriter, WriteSheet writeSheet, CountDownLatch countDownLatch);

    CompletableFuture<List<Article>> getFromDB(int i);
}
