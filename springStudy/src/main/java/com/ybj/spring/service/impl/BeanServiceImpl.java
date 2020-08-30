package com.ybj.spring.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.api.model.JsonResult;
import com.ybj.spring.dao.ArticleMapper;
import com.ybj.spring.model.Article;
import com.ybj.spring.service.BeanServiceI;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
@Slf4j
@Component
@Scope(value = "prototype")
public class BeanServiceImpl implements BeanServiceI {



    private AtomicInteger atomicInteger = new AtomicInteger();

    @Bean
    @Override
    public int getInteger() {
        return atomicInteger.incrementAndGet();
    }

    @Bean
    public void testBean(){
        System.out.println(" this is a test bean ");
    }
}
