package com.ybj.mysql.service.impl;

import com.ybj.mysql.model.Article;
import com.ybj.mysql.dao.ArticleMapper;
import com.ybj.mysql.service.ArticleServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleServiceI {


    @Autowired
    ArticleMapper articleMapper;

    @Async
    @Override
    public void expand(Article article, int i) {
        // articleMapper.insert(article);
        log.info("正在插入第{}条记录",i);
    }

    @Async
    @Override
    public void expand(Article article, int i, CountDownLatch countDownLatch) {
        // articleMapper.insert(article);
        log.info("正在插入第{}条记录",i);
        countDownLatch.countDown();
    }

    @Override
    public Article getMaxIdArticle() {
        return articleMapper.getMaxIdArticle();
    }

    @Async
    @Override
    public void testByCycliBarrier(Object o, int i, CyclicBarrier cyclicBarrier) {
        log.info("正在插入第{}条记录",i);
        try {
            cyclicBarrier.await();
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Async
    @Override
    public void testBySemaphore(Object o, int i, Semaphore semaphore) throws InterruptedException {
        boolean result = semaphore.tryAcquire();
        int availablePermits = semaphore.availablePermits();
        if(result){
            log.info("第{}条记录 获得信号量 , availablePermits为{}",i,availablePermits);
            Thread.sleep(1000);
            semaphore.release();
            log.info("第{}条记录 已释放信号量",i);
        } else{
            log.info("正在插入第{} 未获得信号量",i);
        }

    }
}
