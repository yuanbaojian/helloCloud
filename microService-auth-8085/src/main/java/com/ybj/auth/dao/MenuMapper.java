package com.ybj.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybj.auth.model.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ybj
 * @since 2020-02-01
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenu();

    List<Menu> getNextNodeTree();
}
