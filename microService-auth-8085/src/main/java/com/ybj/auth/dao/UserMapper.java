package com.ybj.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ybj.auth.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户管理 MAPPER
 * @author caicai.gao
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 获取用户信息
	 * @param page 分页信息
	 * @param searchParams 过滤信息
	 * @return IPage<User>
	 */
	IPage<User> getAll(IPage<User> page, Map<String, Object> searchParams);

	/**
	 * 查找所有用户-用于审核
	 * @param map 查询条件
	 * @return List<User> 用户列表
	 */
	List<User> searchAuditor(Map<String, Object> map);

	/**
	 * 查找所有用户-用于转交
	 * @param map 查询条件
	 * @return List<User> 用户列表
	 */
	List<User> searchUser(Map<String, Object> map);

	/**
	 * 查看用户是否拥有某种权限
	 * @param userId 用户Id
	 * @param permissionName 权限名称
	 * @return List<User> 用户列表
	 */
	List<User> hasPermission(@Param("userId") String userId, @Param("permissionName") String permissionName);

}