package com.ybj.mysql.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ybj.mysql.dao.RoleMapper;
import com.ybj.mysql.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class RoleServiceITest {

    @Autowired
    RoleMapper roleMapper;

    @Test
    public void test(){
        List<String> stringList = new LinkedList<>();
        Role role = roleMapper.selectById("72df41e8-d8de-4ca9-bfaf-7f01c77a79ea");
        System.out.println("role.toString() = " + role.toString());
    }

    @Test
    public void test2(){
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq((SFunction<Role, LocalDateTime>) Role::getCreateTime, LocalDateTime.now());
        List<Role> roles = roleMapper.selectList(lambdaQueryWrapper);
        System.out.println("roles.toString() = " + roles.toString());
    }

    @Test
    public void testFor(){
        List<String> stringList = new LinkedList<>();
        for2:
        for (String s : stringList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void testInsert(){

    }

}