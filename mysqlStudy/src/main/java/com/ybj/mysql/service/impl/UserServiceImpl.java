package com.ybj.mysql.service.impl;

import com.ybj.mysql.model.User;
import com.ybj.mysql.dao.UserMapper;
import com.ybj.mysql.service.UserServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-10
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserServiceI {

    @Autowired
    UserMapper userMapper;

    @Async
    @Override
    public void expand(User user, int i) {
        log.info("正在插入第 {} 条数据",i);
        userMapper.insert(user);
    }
}
