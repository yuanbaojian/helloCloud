package com.ybj.redPacket.model;

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
 * @since 2020-06-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("t_red_packet")
public class RedPacket implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId("id")
      private String id;

    @TableField("user_id")
    private String userId;

    @TableField("amount")
    private String amount;

    @TableField("send_date")
    private LocalDateTime sendDate;

    @TableField("total")
    private Integer total;

    @TableField("unit_amount")
    private BigDecimal unitAmount;

    @TableField("stock")
    private Integer stock;

    @TableField("version")
    private Integer version;

    @TableField("note")
    private String note;


}
