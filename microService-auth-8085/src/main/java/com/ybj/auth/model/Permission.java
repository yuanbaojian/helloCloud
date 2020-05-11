package com.ybj.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限模型
 * @author caicai.gao
 */
@Data
@TableName("TBL_PERMISSION")
public class Permission {
    /** 权限ID */
    @TableId(value = "OID", type = IdType.ASSIGN_UUID)
    private String oid;
    /** 权限编码 */
    private String permissionCode;
    /** 权限名称 */
    private String permissionName;
    /** 父节点ID */
    private String parentId;
    /** 顺序号 */
    private Integer sortId;
    /** 角色权限关联关系 */
    @TableField(exist = false)
    private RolePermission rolePermission;

}