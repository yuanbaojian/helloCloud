package com.ybj.gateWay.controller;

import com.ybj.gateWay.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-8186")
public interface UserService {
  @RequestMapping(value = "/user/validate", method = RequestMethod.POST)
  public User validate(@RequestBody User User);
}