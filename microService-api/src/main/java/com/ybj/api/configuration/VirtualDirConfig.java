//package com.ybj.api.configuration;
//
//import com.ybj.api.constant.ConfigConstants;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 虚拟路径配置
// * @author caicai.gao
// * @date 2020-03-27
// */
//@Component
//public class VirtualDirConfig implements WebMvcConfigurer {
//
//    @Value("${spring.servlet.multipart.location}")
//    public String storehousePath;
//
//    /**
//     * 虚拟路径配置
//     * @param registry ResourceHandlerRegistry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(ConfigConstants.REPOSITORY_PATH + "/**")
//                .addResourceLocations("file:/" + storehousePath);
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
//}