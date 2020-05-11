package com.ybj.auth.dao;


import com.ybj.auth.model.TreeJsonData;

import java.util.List;

/**
 * 分类树维护mapper
 * @author caicai.gao
 */
public interface TrainingTreeDataMapper {

	/**
	 * 获取权限分类根节点的子节点
	 * @return List<TreeJsonData>    返回类型  
	 */
	List<TreeJsonData> getChildOfRoot();

	/**
	 * 获取权限分类某节点的子节点
	 * @param parent 节点ID
	 * @return List<TreeJsonData>    返回类型
	 */
	List<TreeJsonData> getChildOfPermission(String parent);
}