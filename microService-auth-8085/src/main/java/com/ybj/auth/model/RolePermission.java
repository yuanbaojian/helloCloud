package com.ybj.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联关系
 * @author caicai.gao
 */
@Data
@TableName("ROLE_PERMISSION")
public class RolePermission implements Serializable {
    /** 角色ID */
    @TableId(value = "ROLE_ID", type = IdType.ASSIGN_UUID)
    private String roleId;
    /** 权限ID */
    private String permissionId;
    /** 权限名称*/
    @TableField(exist = false)
    private String permissionName;

}