package com.ybj.auth.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 权限结构树节点模型
 * @author caicai.gao
 */
@Data
public class TreeJsonData {

	private String id;
	private String nodeId;
	private String text;
	private String icon;
	private String type;
	private String parentId;
	private Map<String,Boolean> state;
	private List<TreeJsonData> children;

}