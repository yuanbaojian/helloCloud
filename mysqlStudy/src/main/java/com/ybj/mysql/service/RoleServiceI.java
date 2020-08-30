package com.ybj.mysql.service;

import com.ybj.mysql.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-07
 */
public interface RoleServiceI extends IService<Role> {

    void expand(Role role, int i);
}
