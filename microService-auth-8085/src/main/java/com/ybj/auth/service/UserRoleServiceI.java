package com.ybj.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybj.auth.model.UserRole;

/**
 * 用户-角色管理接口类
 * @author caicai.gao
 */
public interface UserRoleServiceI  extends IService<UserRole> {

    void addByTransAction();
}
