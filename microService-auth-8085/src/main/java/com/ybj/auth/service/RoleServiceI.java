package com.ybj.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybj.auth.model.Role;

import java.util.Map;

/**
 * 角色管理接口类
 * @author caicai.gao
 */
public interface RoleServiceI extends IService<Role> {

	IPage<Role> getAll(Map<String, Object> searchParams);
	
	Role saveRole(int flg, String oid, String roleName, String remark, String createdBy, String updatedBy);


}
