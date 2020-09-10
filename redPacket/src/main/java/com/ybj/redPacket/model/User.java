package com.ybj.redPacket.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 用户模型
 * @author caicai.gao
 */
@Data
@TableName("TBL_USER")
public class User {

	/** 用户ID
	 * TableId 用于解决主键不是ID的情况
	 */
	@TableId(value = "USER_ID", type = IdType.ASSIGN_UUID)
	@TableField("USER_ID")
	private String userId;

	/** 登录名
	 * TableField 用于解决model变量名与数据库字段不匹配的问题
	 */
	@TableField("LOGIN_NAME")
	private String loginName;

	/** 用户姓名 */
	@TableField("USER_FULL_NAME")
	private String userFullName;

	/** 密码-加密 */
	@TableField("PASSWORD")
	private String password;

	/** 邮箱 */
	@TableField("EMAIL")
	private String email;

	/** 联系方式 */
	@TableField("MOBILE_PHONE")
	private String mobilePhone;

	/** 状态 1-启用 0-禁用 */
	@TableField("STATUS")
	private Integer status;

	/** 登录失败次数 */
	@TableField("FAIL_COUNT")
	private Integer failCount;

	/** 上次成功登录时间 */
	@TableField("LAST_LOGIN_TIME")
	private LocalDateTime lastLoginTime;

	/** 密级 密级0-公开，1-内部，2-秘密，3-机密，4-绝密*/
	@TableField("SECURITY_LEVEL")
	private Integer securityLevel;

	/** 组织机构 */
	@TableField("ORG_ID")
	private String orgId;

	/** 逻辑删除标志 0-未删除 1-已删除
	 *  select=false 查询结果不包含该字段*/
	@TableField(value = "DELETED", select = false)
	@TableLogic
	private Integer deleted;

	/** 对于数据库中不存在的字段的三种处理方法
	 * 1. transient  不参与序列化
	 * 2. static 标识为静态变量 lombok不为静态变量生成get/set方法
	 * 3. @TableField(exist=false)  推荐使用
	 *
	 * 是否记住登录账号-数据库中没有对应字段 */
	@TableField(exist = false)
	private boolean rememberMe;

	/** 状态名称 1-启用 0-禁用 */
	@TableField(exist = false)
	private String statusName;

	/** 密级名称 */
	@TableField(exist = false)
	private String securityLevelName;

	/** 用户关联的角色 */
//	@TableField(exist = false)
//	private List<UserRole> userRoles;

	/** 密码 */
	@TableField(exist = false)
	private String pwd;

	@TableField(exist = false)
	private String orgName;

	private Integer balance;
}