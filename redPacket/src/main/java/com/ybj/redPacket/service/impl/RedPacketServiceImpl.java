package com.ybj.redPacket.service.impl;

import com.ybj.redPacket.model.RedPacket;
import com.ybj.redPacket.dao.RedPacketMapper;
import com.ybj.redPacket.service.RedPacketServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
@Service
public class RedPacketServiceImpl extends ServiceImpl<RedPacketMapper, RedPacket> implements RedPacketServiceI {

    @Autowired
    RedPacketMapper redPacketMapper;

    /**
    * <p>根据id获得红包
     *     事务传播：加入或者创建事务
    * </p>
     * @param id
     * @return com.ybj.redPacket.model.RedPacket
     * @author yuanbaojian
     * @date 2020/6/8
     * @time 16:34
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public RedPacket getRedPacket(String id)
    {
        RedPacket redPacket = redPacketMapper.selectById(id);
        return redPacket;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int decreaseRedPacket(String id) {
        return redPacketMapper.decreaseRedPacket(id);
    }
}
