package com.ybj.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ybj.api.constant.BusniessConstants;
import com.ybj.api.constant.ConfigConstants;
import com.ybj.api.constant.MessageConstants;
import com.ybj.api.model.JsonResult;
import com.ybj.user.dao.UserMapper;
import com.ybj.user.model.User;
import com.ybj.user.service.UserServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理控制类
 * @author caicai.gao
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户管理-包括新建、修改、查询用户等方法", tags={"用户管理操作接口"})
public class UserController {

    @PostMapping(value = "/validate")
    public User validate(User User){
        User user = new User();
        user.setUserId("1");
        user.setLoginName("admin111");
        user.setPassword("123456");
        return user;
    }

}