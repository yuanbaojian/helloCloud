package com.ybj.mysql.controller;


import com.ybj.mysql.model.User;
import com.ybj.mysql.service.UserServiceI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceI userService;

    Random random = new Random(100);

    @GetMapping("/expand")
    @ApiOperation(value = "对文章进行扩容",notes = "增大数据库的文章数量")
    public void expand(){
        for(int i = 0 ; i < 20000000; i++) {
            String uuid = UUID.randomUUID().toString();
            User user = new User();

            user.setName("testUser");
            user.setId(uuid);
            user.setAge(random.nextInt(100));
            if( i % 2 == 0){
                user.setName("王尼玛");
                user.setGender("M");
                user.setAddress("地球");
            } else{
                user.setGender("F");
                user.setAddress("火星");
                user.setName("赵日天");
            }
            user.setLoginName(LocalDateTime.now().toString());
            userService.expand(user, i);
        }
    }

}

