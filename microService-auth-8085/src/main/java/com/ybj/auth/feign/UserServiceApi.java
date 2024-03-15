package com.ybj.auth.feign;

import com.ybj.auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "auth-8085")
public interface UserServiceApi {
    @PostMapping(value = "/user/validate")
    public User validate(User user);

    @PostMapping(value = "/getUserInfo")
    public Boolean checkUser(@RequestParam("username") String username);
}