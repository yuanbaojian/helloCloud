package com.ybj.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybj.auth.model.Permission;
import com.ybj.auth.model.User;
import com.ybj.auth.model.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @Author yabo.wang
 * @Date 2020/1/13 13:35
 * @Description 用户管理接口类
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有用户
     * @return 除管理员外所有用户信息
     */
    IPage<User> getAll(Map<String, Object> searchParams);

    /**
     * 保存用户信息
     * @param flag 新增OR修改标识 0-新增 1-修改
     * @param record 用户信息
     * @return User 用户对象
     */
    User saveUser(int flag, User record);

    /**
     * 获取用户权限
     * @param userId 用户ID
     * @return List<Permission> 权限列表
     */
    List<Permission> selectPermissionByUser(String userId);

    /**
     * 为用户分配角色
     * @param list 用户角色关联关系
     * @param userId 用户ID
     */
    void assignRole(List<UserRole> list, String userId);

    /**
     * 以用户名和密码查询用户
     *
     * @param  user 用户名或者密码或者两者都有
     * @return 用户信息
     */
    User checkUser(User user);

    /**
     * 获取用户已选角色的ID列表
     * @param userId 用户ID
     * @return 关联角色ID列表
     */
    List<Integer> getUserSelectedRole(String userId);

    /**
     * 获取所有人员-用于审核
     * @param type 权限（拥有审核XX权限的用户才会被选中）
     * @return List<User>    返回类型
     */
    List<User> searchAuditor(String type);

    /**
     * 获取所有人员-用于转交
     * @return List<User>    返回类型
     */
    List<User> searchUser();


    /**
     * 查看用户是否拥有某种权限
     * @param userId  用户ID
     * @param permissionName  权限名称
     * @return void    返回类型
     */
    Boolean hasPermission(String userId, String permissionName);

}

