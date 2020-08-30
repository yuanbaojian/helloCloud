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
@TableName("tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId("id")
      private String id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField("age")
    private Integer age;

    @TableField("loginName")
    private String loginName;

    @TableField("password")
    private String password;

    @TableField("gender")
    private String gender;

    @TableField("idNo")
    private Integer idNo;


}
