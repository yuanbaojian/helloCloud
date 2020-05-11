package com.ybj.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色模型
 * @author caicai.gao
 */
@Data
@TableName("TBL_ROLE")
public class Role implements Serializable {
    /** 角色ID */
    @TableId(value = "OID", type = IdType.ASSIGN_UUID)
    @TableField("OID")
    private String oid;
    /** 角色名称 */
    @TableField("ROLE_NAME")
    private String roleName;
    /** 角色编号 */
    @TableField("ROLE_NO")
    private String roleNo;
    /** 备注 */
    @TableField("REMARK")
    private String remark;
    /** 创建者 */
    @TableField("CREATED_BY")
    private String createdBy;
    /** 创建时间 */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    /** 更新者 */
    @TableField("UPDATED_BY")
    private String updatedBy;
    /** 更新时间 */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;
    /** 创建者名称 */
    @TableField(exist = false)
    private String createdUser;
    /** 更新者名称 */
    @TableField(exist = false)
    private String updatedUser;
    /** 角色下的权限列表*/
    @TableField(exist = false)
    private List<RolePermission> rolePermission;

}