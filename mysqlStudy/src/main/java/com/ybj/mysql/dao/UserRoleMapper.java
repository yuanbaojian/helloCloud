package com.ybj.mysql.dao;

import com.ybj.mysql.model.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-10
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> getUserRoleByIn();

    List<UserRole> getUserRoleByExist();
}
