package com.ybj.auth.service;

import com.ybj.auth.feign.UserServiceApi;
import com.ybj.auth.model.User;

public class UserFeignService implements UserServiceApi {
    @Override
    public User validate(User user) {
        return new User();
    }

    @Override
    public Boolean checkUser(String username) {
        return true;
    }
}
