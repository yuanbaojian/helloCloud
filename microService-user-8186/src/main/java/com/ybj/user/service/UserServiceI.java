package com.ybj.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybj.user.model.User;

import java.util.List;
import java.util.Map;

/**
 * @Author yabo.wang
 * @Date 2020/1/13 13:35
 * @Description 用户管理接口类
 */
public interface UserServiceI extends IService<User> {

    /**
     * 查询所有用户
     *  @param searchParams 查询条件
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
    //
    // List<Permission> selectPermissionByUser(String userId);

    /**
     * 为用户分配角色
     * @param list 用户角色关联关系
     * @param userId 用户ID
     */
    void assignRole(String[] list, String userId);

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
    List<String> getUserSelectedRole(String userId);

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
     * @return void 返回类型
     */
    Boolean hasPermission(String userId, String permissionName);

    /**
     * 根据组织机构获取用户
     * @param orgId 组织机构ID
     * @return List<User> 用户列表
     */
    List<User> getUserByOrg(String orgId);

    /**
     * 获取所有用户，除管理员外
     * @return List<User> 用户列表
     */
    List<User> getAllUser();

    /**
     * 获得组织机构下的人员（包含子机构的人员）
     * @param orgId
     * @return java.util.List<com.atoz.mpm.system.authentication.model.User>
     * @author yuanbaojian
     * @date 2020/4/7
     * @time 16:45
     */
    List<User> getAllPeopleByOrgId(String orgId);

    /** 将人员分配到组织结构
     * @param userIds
     * @param orgId
     * @return void
     * @author yuanbaojian
     * @date 2020/4/8
     * @time 13:50
     */
    void alloteUserToOrg(String userIds, String orgId);
}

