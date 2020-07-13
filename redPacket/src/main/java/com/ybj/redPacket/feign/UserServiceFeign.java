package com.ybj.redPacket.feign;

import com.ybj.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(value = "user-8186")
public interface UserServiceFeign {
    @GetMapping(value = "/user/getAllUsers")
    public List<User> getAllUsers();
}