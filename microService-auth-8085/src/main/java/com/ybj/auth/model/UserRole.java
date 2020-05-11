package com.ybj.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联关系
 * @author caicai.gao
 */
@Data
@TableName("TBL_USER_ROLE")
public class UserRole implements Serializable {
    /** 用户ID */
    @TableId(value = "USER_ID", type = IdType.ASSIGN_UUID)
    private String userId;
    /** 角色ID */
    private String roleId;
    /** 角色名称 */
    @TableField(exist = false)
    private String roleName;

}