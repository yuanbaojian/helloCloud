package com.ybj.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.auth.dao.UserMapper;
import com.ybj.auth.model.Permission;
import com.ybj.auth.model.User;
import com.ybj.auth.model.UserRole;
import com.ybj.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author yabo.wang
 * @Date 2020/1/13 13:48
 * @Description
 * IService:mp提供的接口。ServiceImpl:mp提供的接口实现类。
 * ServiceImpl<BaseMapper<T>, T>是IService 的实现类。
 */
@Slf4j
@Service()
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public IPage<User> getAll(Map<String, Object> searchParams) {
        return null;
    }

    @Override
    public User saveUser(int flag, User record) {
        return null;
    }

    @Override
    public List<Permission> selectPermissionByUser(String userId) {
        return null;
    }

    @Override
    public void assignRole(List<UserRole> list, String userId) {

    }

    @Override
    public User checkUser(User user) {
        return null;
    }

    @Override
    public List<Integer> getUserSelectedRole(String userId) {
        return null;
    }

    @Override
    public List<User> searchAuditor(String type) {
        return null;
    }

    @Override
    public List<User> searchUser() {
        return null;
    }

    @Override
    public Boolean hasPermission(String userId, String permissionName) {
        return null;
    }



}
