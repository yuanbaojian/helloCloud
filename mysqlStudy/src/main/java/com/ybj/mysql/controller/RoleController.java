package com.ybj.mysql.controller;


import com.ybj.mysql.model.Role;
import com.ybj.mysql.service.RoleServiceI;
import io.swagger.annotations.Api;
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
 * @since 2020-08-07
 */
@RestController
@RequestMapping("/role")
@Api(tags = {"role表测试接口"})
public class RoleController {

    Random random = new Random(100);

    @Autowired
    RoleServiceI roleService;

    @GetMapping("/expand")
    @ApiOperation(value = "对文章进行扩容",notes = "增大数据库的文章数量")
    public void expand(){
        for(int i = 0 ; i < 20000000; i++) {
            String uuid = UUID.randomUUID().toString();
            Role role = roleService.getById("532fe646ea6ce88d59f34ff6d90efe72");
            role.setCreateTime(LocalDateTime.now());
            role.setUpdatedBy(String.valueOf(i));
            role.setId(uuid);
            role.setRoleName( role.getRoleName() + random.nextInt(100));
            roleService.expand(role, i);
        }
    }


}

