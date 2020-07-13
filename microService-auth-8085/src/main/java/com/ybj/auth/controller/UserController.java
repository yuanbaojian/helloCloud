package com.ybj.auth.controller;

import com.ybj.auth.model.User;
import com.ybj.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author UserController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        List<User> userList = userService.list();
        return userList;
    }
}
