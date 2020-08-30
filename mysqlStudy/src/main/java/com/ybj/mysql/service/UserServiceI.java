package com.ybj.mysql.service;

import com.ybj.mysql.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-10
 */
public interface UserServiceI extends IService<User> {

    void expand(User user, int i);
}
