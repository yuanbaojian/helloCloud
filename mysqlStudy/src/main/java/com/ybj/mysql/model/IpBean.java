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
 * @since 2020-07-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("tbl_ip_bean")
public class IpBean implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId("ID")
      private String id;

    @TableField("IP_ADDRESS")
    private String ipAddress;

    @TableField("IP_PORT")
    private String ipPort;

    @TableField("SERVER_ADDRESS")
    private String serverAddress;

    @TableField("ANONY_TYPE")
    private String anonyType;

    @TableField("PROTOCOL_TYPE")
    private String protocolType;


}
