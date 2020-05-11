package com.ybj.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.auth.dao.MenuMapper;
import com.ybj.auth.model.Menu;
import com.ybj.auth.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ybj
 * @since 2020-02-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        List<Menu> allMenu = menuMapper.getAllMenu();
        return allMenu;
    }
}
