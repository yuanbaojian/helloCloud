package com.ybj.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.auth.dao.RolePermissionMapper;
import com.ybj.auth.model.RolePermission;
import com.ybj.auth.service.RolePermissionServiceI;
import org.springframework.stereotype.Service;


/**
 * 角色权限实现类
 * @author caicai.gao
 */
@Service("RolePermissionServiceI")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionServiceI {

}
