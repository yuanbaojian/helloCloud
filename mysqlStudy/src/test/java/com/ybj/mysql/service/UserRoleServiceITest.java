package com.ybj.mysql.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybj.mysql.dao.RoleMapper;
import com.ybj.mysql.dao.UserMapper;
import com.ybj.mysql.dao.UserRoleMapper;
import com.ybj.mysql.model.Role;
import com.ybj.mysql.model.User;
import com.ybj.mysql.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class UserRoleServiceITest {

    @Autowired
    UserRoleServiceI userRoleService;

    @Autowired
    UserServiceI userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Test
    public void test(){
        Page<User> page = new Page<>();
        page.setPages(4);
        page.setSize(100);
        // page.setTotal(100);
        Page<User> userPage = userService.page(page);
        List<User> userList = userPage.getRecords();
        log.info("userList长度为 {}", userList.size());

        Page<Role> rolePage = new Page<>();
        rolePage.setPages(2);
        rolePage.setSize(50);
        Page<Role> rolePage1 = roleMapper.selectPage(rolePage, null);
        List<Role> roleList = rolePage1.getRecords();
        log.info("roleList长度为 {}", roleList.size());


        for (User user : userList) {
            for (Role role : roleList) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(role.getId()).setUserId(user.getId());
                userRoleService.save(userRole);
            }
        }
    }

    @Test
    public void getCount(){
        int count = userRoleService.count();
        System.out.println("count = " + count);
    }

    //64 ms 76ms
    @Test
    public void getUserWithRoleCount(){
        List<User> userList = userMapper.getUserWithRole();
        System.out.println("userList.size() = " + userList.size());
    }

    // 59ms 72ms
    @Test
    public void getUserWithRoleCountByExist(){
        List<User> userList = userMapper.getUserWithRoleByExists();
        System.out.println("userList.size() = " + userList.size());
    }

    // 723ms  769ms 786ms
    @Test
    public void testByIn(){
        List<UserRole> userRoleList = userRoleMapper.getUserRoleByIn();
        System.out.println("userRoleList.size() = " + userRoleList.size());
    }

    //739ms 758ms 817ms
    @Test
    public void testByExist(){
        List<UserRole> userRoleList = userRoleMapper.getUserRoleByExist();
        System.out.println("userRoleList.size() = " + userRoleList.size());
    }

    @Test
    public void testFor(){
        // 连接了10次
        for(int i = 0; i < 10; i++) {
            log.info("连接了第 {} 次" ,i);
            for(int j = 0; j < 1000; j++) {
            }
        }

        // 连接了1000次
        for(int i = 0; i < 1000; i++) {
            log.info("连接了第 {} 次" ,i);
            for(int j = 0; j < 10; j++) {
            }
        }
    }

}