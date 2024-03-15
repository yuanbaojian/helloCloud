package com.ybj.crawler.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratePaymentService {

    static String URL = "jdbc:mysql://10.98.32.103:6303/trade?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=UTC";
    static String userName = "payment";
    static String password = "Dtv8MTwnoMFFKlauepVm0KLd";

    public static void main(String[] args) {
        //1、创建generator对象
        AutoGenerator autoGenerator = new AutoGenerator();
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        autoGenerator.setDataSource(dataSourceConfig);      //设置数据源
        //2、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("/Users/lp211200227/IdeaProjects/paymentcore/paymentcore-common/src/main/java/");
        globalConfig.setOpen(false);        //是否打开输出目录
        globalConfig.setAuthor("yuanbaojian");      //作者
        globalConfig.setFileOverride(true);
        globalConfig.setServiceName("%sService");           //设置生成的service接口的名字不带I
        autoGenerator.setGlobalConfig(globalConfig);        //设置全局配置
        //3、包信息
        PackageConfig packageConfig = new PackageConfig();  //包配置
        packageConfig.setParent("com.yy.paymentacceptance.core");     //设置父包名
        packageConfig.setController("controller");      //设置控制器包名
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");
        autoGenerator.setPackageInfo(packageConfig);        //设置包配置
        //4、配置策略
        StrategyConfig strategyConfig = new StrategyConfig();       //策略配置
        strategyConfig.setInclude("trade_cancel_order");
        strategyConfig.setEntityLombokModel(true);      //是否使用lombok
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);        //数据库表映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);  //数据库表字段映射到实体的命名策略
        autoGenerator.setStrategy(strategyConfig);      //设置策略配置
        autoGenerator.execute();        //执行生成

    }

}