package com.ybj.redPacket.feign;

import com.ybj.api.model.JsonResult;
import com.ybj.redPacket.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "user-8186")
public interface UserServiceFeign {
    @GetMapping(value = "/user/getAllUsers")
    public List<User> getAllUsers();

    @PostMapping(value = "/user/addBalance")
    JsonResult addUserBalance(@RequestParam("userId") String userId);
}