package com.ybj.user.model;

import java.math.BigDecimal;
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
 * @since 2020-07-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("tbl_message")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId("id")
      private String id;

    @TableField("message")
    private String message;

    @TableField("status")
    private Integer status;

    @TableField("createdTime")
    private LocalDateTime createdTime;

    @TableField("udpatedTime")
    private LocalDateTime udpatedTime;


}
