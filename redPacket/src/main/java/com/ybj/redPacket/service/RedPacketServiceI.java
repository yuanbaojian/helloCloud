package com.ybj.redPacket.service;

import com.ybj.redPacket.model.RedPacket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
public interface RedPacketServiceI extends IService<RedPacket> {

    /**
    * <p>获取大红包信息</p>
     * @param id
     * @return com.ybj.redPacket.model.RedPacket
     * @author yuanbaojian
     * @date 2020/6/8
     * @time 16:32
     */
    RedPacket getRedPacket(String id);

    int decreaseRedPacket(String id);

}
