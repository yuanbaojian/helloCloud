package com.ybj.auth.dao;


import com.ybj.auth.model.Permission;

import java.util.List;

/**
 * 权限管理mapper
 * @author caicai.gao
 */
public interface PermissionMapper {

    /**
     * 获取用户的权限
     * @param userId 用户ID
     * @return List<Permission> 权限列表
     */
    List<Permission> selectPermissionByUser(String userId);

}