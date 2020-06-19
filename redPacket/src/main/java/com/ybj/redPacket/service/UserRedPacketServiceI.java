package com.ybj.redPacket.service;

import com.ybj.redPacket.model.UserRedPacket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
public interface UserRedPacketServiceI extends IService<UserRedPacket> {

    /**
    * <p>抢红包</p>
     * @param redPacketId
     * @param userId
     * @return int
     * @author yuanbaojian
     * @date 2020/6/8
     * @time 16:38
     */
    int grapRedPacketOptimisticLock(String redPacketId, String userId);


    int grapRedPacketOptimisticLockByTime(String redPacketId, String userId);

    int grapRedPacketNormal(String redPacketId, String userId);

    int grapRedPacketByPessimisticLock(String redPacketId, String userId);





}
