package com.ybj.crawler.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpBean ipBean = (IpBean) o;
        return Objects.equals(ID, ipBean.ID) &&
                Objects.equals(ipAddress, ipBean.ipAddress) &&
                Objects.equals(ipPort, ipBean.ipPort) &&
                Objects.equals(serverAddress, ipBean.serverAddress) &&
                Objects.equals(anonyType, ipBean.anonyType) &&
                Objects.equals(protocolType, ipBean.protocolType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, ipAddress, ipPort, serverAddress, anonyType, protocolType);
    }
}
