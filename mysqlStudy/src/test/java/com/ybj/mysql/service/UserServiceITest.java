package com.ybj.mysql.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ybj.api.configuration.MybatisPlusConfig;
import com.ybj.mysql.dao.UserMapper;
import com.ybj.mysql.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceITest {

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    UserServiceI userService;


    @Test
    public void test(){
        MybatisPlusConfig.DYNAMIC_TABLE_NAME.set("user_1");
        List<User> userList = userMapper.selectList(null);
        log.info("user_1中的数据  {}", userList.toString());

        MybatisPlusConfig.DYNAMIC_TABLE_NAME.set("user_2");
        List<User> userList2 = userMapper.selectList(null);
        log.info("user_2中的数据  {}", userList2.toString());
    }

    @Test
    public void test23(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq((SFunction<User, String>) User::getName, "王尼玛");
        List<User> adminList = userService.list(lambdaQueryWrapper);
        log.info("数量为 {}", adminList.size());
    }


    /**
     * <p>
     *  name索引搜索
     *
     * </p>
     * @param
     * @return void
     * @author yuanbaojian
     * @date 2020/8/7
     * @time 16:14
     */
    @Test
    public void test2(){
        List<String> idList = userMapper.selectIdListByName("赵日天2");
        log.info("数量为 {}", idList.size());
    }


    @Test
    public void test4(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq((SFunction<User, String>) User::getAddress, "火星1");
        List<User> idList = userMapper.selectList(lambdaQueryWrapper);
        log.info("数量为 {}", idList.size());
    }

    @Test
    public void getCount(){
        int count = userService.count();
        log.info("数量为 {}",count);
    }



}