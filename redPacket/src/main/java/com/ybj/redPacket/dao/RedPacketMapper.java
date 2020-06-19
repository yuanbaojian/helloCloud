package com.ybj.redPacket.dao;

import com.ybj.redPacket.model.RedPacket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
public interface RedPacketMapper extends BaseMapper<RedPacket> {

    public int decreaseRedPacket(String id);

    public int decreaseRedPacketForVersion(@Param("id") String id, @Param("version") Integer version);

    RedPacket getRedPacketForUpdate(String redPacketId);
}
