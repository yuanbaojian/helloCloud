package com.ybj.mysql.dao;

import com.ybj.mysql.model.User;
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
public interface UserMapper extends BaseMapper<User> {

    List<String> selectIdListByName(String name);

    List<User> getUserWithRole();

    List<User> getUserWithRoleByExists();

}
