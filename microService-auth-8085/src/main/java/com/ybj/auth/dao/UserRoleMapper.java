package com.ybj.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybj.auth.model.UserRole;

import java.util.List;

/**
 * 用户角色关联管理 MAPPER
 * @author caicai.gao
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     *  获取用户已选角色的ID列表
     * @param userId 用户ID
     * @return List<Integer> 已选角色ID列表
     */
    List<Integer> selectRoleIdByUser(String userId);

    /**
     * 获取用户关联的角色
     * @param userId 用户ID
     * @return List<UserRole> 角色列表
     */
    List<UserRole> getRoleByUserId(String userId);


}