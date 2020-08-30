package com.ybj.spring.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * 用于测试bean的作用范围
 **/
@Data
@SessionScope
@Service
public class HelloMessageGenerator {

    private String message;

}
