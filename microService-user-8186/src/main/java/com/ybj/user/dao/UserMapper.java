package com.ybj.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ybj.user.model.User;
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

	/**
	 * 根据组织机构获取用户
	 * @param orgId 组织机构ID
	 * @return List<User> 用户列表
	 */
    List<User> getUserByOrg(String orgId);

    /** 获得组织结构下的直接用户
     * @param orgId
     * @return java.util.List<com.atoz.mpm.system.authentication.model.User>
     * @author yuanbaojian
     * @date 2020/4/8
     * @time 9:34
     */
    List<User> selectDirectlyUserByOrgId(@Param("orgId") String orgId);

    /**
	 * 获得未分配机构的人员
     * @param
     * @return java.util.List<com.atoz.mpm.system.authentication.model.User>
     * @author yuanbaojian
     * @date 2020/4/8
     * @time 11:18
     */
	List<User> getUndistributedCrew();

	/** 删除机构时，更新user的orgId为null
	 * @param orgId
	 * @return void
	 * @author yuanbaojian
	 * @date 2020/4/12
	 * @time 22:25
	 */
	void updateSpecificOrgIdToNull( String orgId);


}