package com.ybj.auth.controller;


import com.ybj.auth.model.Menu;
import com.ybj.auth.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author ybj
 * @since 2020-02-01
 */
@Slf4j
@Api(value = "菜单控制器")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    //    @Cacheable(value = "menu")
    @ApiOperation(value = "获得所有菜单")
    @PostMapping("/getAll")
    public List<Menu> getAllMenu() {
        log.info("查询了数据库");
        List<Menu> menuList = menuService.getAll();
        return menuList;
    }


}

