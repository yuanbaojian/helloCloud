package com.ybj.api.configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MybatisPlus分页插件配置
 * @author caicai.gao
 */
@Slf4j
@EnableTransactionManagement
@Configuration
@MapperScan("com.ybj.*.*.dao.*")
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
public class MybatisPlusConfig {

    // threadLocal 使得变量具有隔离性
    public static final ThreadLocal<String> DYNAMIC_TABLE_NAME = new ThreadLocal<>();

    // 静态变量线程不安全
    public static String dynamicTableName;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // SQL解析接口
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 动态表名解析器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();

        Map<String, ITableNameHandler> tableNameHandlerMap = new ConcurrentHashMap<>();
        tableNameHandlerMap.put("user", (metaObject, sql, tableName) -> DYNAMIC_TABLE_NAME.get());
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);
        log.info("当前线程: {}， 动态表名: {}",Thread.currentThread().getName(), DYNAMIC_TABLE_NAME.get());

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
         paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
         paginationInterceptor.setLimit(-1);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }


    /**
     * <p>用于mybatis plus 的乐观锁</p>
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
     * @author yuanbaojian
     * @date 2020/7/31
     * @time 14:40
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

}

