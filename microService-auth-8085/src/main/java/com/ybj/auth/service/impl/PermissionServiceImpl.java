package com.ybj.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybj.auth.dao.RolePermissionMapper;
import com.ybj.auth.dao.TrainingTreeDataMapper;
import com.ybj.auth.model.RolePermission;
import com.ybj.auth.model.TreeJsonData;
import com.ybj.auth.service.PermissionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限实现类
 * @author caicai.gao
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionServiceI {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private TrainingTreeDataMapper trainingTreeDataMapper;

	
	@Override
	public List<TreeJsonData> getPermissionTree(String roleId) {
		List<TreeJsonData> lstResult = new ArrayList<>();
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ROLE_ID",roleId);
		List<RolePermission>  rolePermission = rolePermissionMapper.selectList(queryWrapper);
		List<String>  rolePermissionList = new ArrayList<>();
		for (RolePermission rolePer :rolePermission) {
			rolePermissionList.add(rolePer.getPermissionId());
		}
		List<TreeJsonData> treeDataList = trainingTreeDataMapper.getChildOfRoot();
		for (TreeJsonData data :treeDataList) {
			List<TreeJsonData> childTreeDataList = getChildOfPermission(roleId, data.getId());
			data.setChildren(childTreeDataList);
			Map<String, Boolean> map2 = new HashMap<>();
			if (rolePermissionList.contains(data.getId())){
				map2.put("selected", childTreeDataList.isEmpty());
				map2.put("opened", true);
				data.setState(map2);
			} else {
				map2.put("selected", false);
				map2.put("opened", true);
				data.setState(map2);
				for (TreeJsonData data2 : treeDataList) {
					if (data2.getNodeId().equals(data.getId())){
						data2.setState(map2);
					}
				}
			}
			lstResult.add(data);
		}
		return lstResult;
	}
	
	private List<TreeJsonData> getChildOfPermission(String roleId, String nodelId) {
		List<TreeJsonData> lstResult = new ArrayList<>();
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ROLE_ID",roleId);
		List<RolePermission>  rolePermission = rolePermissionMapper.selectList(queryWrapper);
		List<String>  rolePermissionList = new ArrayList<>();
		for (RolePermission rolePer :rolePermission) {
			rolePermissionList.add(rolePer.getPermissionId());
		}
		List<TreeJsonData> treeDataList = trainingTreeDataMapper.getChildOfPermission(nodelId);
		for (TreeJsonData data :treeDataList) {
			List<TreeJsonData> childTreeDataList = getChildOfPermission(roleId, data.getId());
			data.setChildren(childTreeDataList);
			Map<String, Boolean> map2 = new HashMap<>();
			if (rolePermissionList.contains(data.getId())) {
				map2.put("selected", childTreeDataList.isEmpty());
				map2.put("opened", true);
				data.setState(map2);
			} else {
				map2.put("selected", false);
				map2.put("opened", true);
				data.setState(map2);
				for (TreeJsonData data2 : treeDataList) {
					if (data2.getNodeId().equals(nodelId)) {
						data2.setState(map2);
					}
				}
			}
			lstResult.add(data);
		}
		return lstResult;
	}

}
