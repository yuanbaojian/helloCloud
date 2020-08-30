package com.ybj.crawler.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author ybj
 * @since 2020-02-10
 */
@Data
@Accessors(chain = true)
@TableName("TBL_IP_BEAN")
public class IpBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String ID;

    private String ipAddress;

    private Integer ipPort;

    private String serverAddress;

    private String anonyType;

    private String protocolType;



}
