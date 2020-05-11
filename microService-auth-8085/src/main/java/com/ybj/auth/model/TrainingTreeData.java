package com.ybj.auth.model;

import lombok.Data;

/**
 * 分类结构树节点模型
 * @author caicai.gao
 */
@Data
public class TrainingTreeData {

	private String id;
	private String nodeId;
	private String text;
	private Boolean children;
	private String parentId;
	private String type;
	private String nodeType;
	private Short status;

}