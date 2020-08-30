package com.ybj.mysql.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-08-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("tbl_role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 角色ID
     */
        @TableId("ID")
      private String id;

      /**
     * 角色名称
     */
      @TableField("ROLE_NAME")
    private String roleName;

      /**
     * 角色编码
     */
      @TableField("ROLE_NO")
    private String roleNo;

      /**
     * 备注
     */
      @TableField("REMARK")
    private String remark;

      /**
     * 创建时间
     */
      @TableField("CREATE_TIME")
    private LocalDateTime createTime;

      /**
     * 更新时间
     */
      @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

      /**
     * 创建者
     */
      @TableField("CREATED_BY")
    private String createdBy;

      /**
     * 更新者
     */
      @TableField("UPDATED_BY")
    private String updatedBy;


}
