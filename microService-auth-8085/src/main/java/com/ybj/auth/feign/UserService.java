package com.ybj.auth.feign;

import com.ybj.auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "user-8186")
public interface UserService {
    @PostMapping(value = "/user/validate")
    public User validate(User user);
}