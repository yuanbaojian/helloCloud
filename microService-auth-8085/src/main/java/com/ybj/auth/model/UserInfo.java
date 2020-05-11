package com.ybj.auth.model;

import lombok.Data;

/**
 * 用户简单信息
 * @author caicai.gao
 */
@Data
public class UserInfo {
	/** 用户Id */
	private String userId;
	/** 登陆名称 */
	private String loginName;
 	/** 用户全名称 */
	private String userFullName;
	
}
