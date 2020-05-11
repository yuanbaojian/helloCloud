package com.ybj.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.auth.dao.UserMapper;
import com.ybj.auth.dao.UserRoleMapper;
import com.ybj.auth.model.User;
import com.ybj.auth.model.UserRole;
import com.ybj.auth.service.UserRoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户-角色管理实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleServiceI {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public void addByTransAction() {
        User user = new User();
        user.setUserFullName("testTransaction");
        user.setDeleted(0);
        userMapper.insert(user);
    }
}
