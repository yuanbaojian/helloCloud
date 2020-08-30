package com.ybj.mysql.service.impl;

import com.ybj.mysql.model.Role;
import com.ybj.mysql.dao.RoleMapper;
import com.ybj.mysql.service.RoleServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-07
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleServiceI {

    @Autowired
    RoleMapper roleMapper;

    @Async
    @Override
    public void expand(Role role, int i) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);
        log.info("正在处理 {} 条记录", i);
    }
}
