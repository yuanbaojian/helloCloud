package com.ybj.mysql.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-08-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("tbl_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId("id")
      private String id;

    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;


}
