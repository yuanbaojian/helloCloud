package com.ybj.auth.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ybj.api.model.JsonResult;
import com.ybj.auth.constants.AuthConstants;
import com.ybj.auth.jwt.JwtUtil;
import com.ybj.auth.model.User;
import com.ybj.auth.service.UserService;
import com.ybj.auth.util.Md5EncryptionUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @Author yabo.wang
 * @Date 2020/1/13 14:03
 * @Description 登录控制类
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth")
@Api(value = "登录管理-包括用户登录、注销等", tags={"登录管理操作接口"})
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserService userService;

    /***
     * 登录验证
     * @param user User
     * @author wangyabo
     * @date 2020/1/10 10:12
     */
    @PostMapping(value = "/login")
    public JsonResult login(@RequestBody User user) {
        // 获取加密后的密码
        String passwordEncrypt = Md5EncryptionUtil.convertMd5(user.getPassword());
        // 根据用户名查询用户
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        // 获取状态为启用，userId不为loginUserId的用户
        lambdaQuery.eq(User::getLoginName, user.getLoginName());
        User currentUser =   userService.getOne(lambdaQuery);
        if (currentUser == null) {
            return JsonResult.ok(AuthConstants.ACCOUNT_EMPTY,"该账号在系统中不存在");
        }
        if (!passwordEncrypt.equals(currentUser.getPassword())) {
            return JsonResult.ok(AuthConstants.PASSWORD_ERROR,"您输入的用户名或密码不正确，请检查后再输入");
        }
        if (AuthConstants.ACCOUNT_FORBIDDEN == currentUser.getStatus()) {
            return JsonResult.ok(AuthConstants.PASSWORD_ERROR,"该账号已被禁用，请联系管理员");
        }
        String token = JwtUtil.generateToken(currentUser.getLoginName(),currentUser.getUserId(), passwordEncrypt);
        try {
            stringRedisTemplate.opsForValue().set(AuthConstants.TOKEN_PREFIX + currentUser.getLoginName() , token, Duration.ofMinutes(30));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("生成的token令牌为:{}", token);
        log.info("当前登录的用户为:{}", JSON.toJSONString(currentUser));
        JsonResult.ok().add("currentUser",currentUser).addData(token);
        // 向前台发送成功状态码,以及当前登录者的信息,保存在前端
        return JsonResult.ok().add("currentUser",currentUser).addData(token);

    }



//    @PostMapping("/logout")
//    public JsonResult logout(){
//        String loginName = JwtUtil.getLoginName();
//        String key = AuthConstants.TOKEN_PREFIX + loginName;
//        stringRedisTemplate.delete(key);
//        return JsonResult.ok();
//    }

}
