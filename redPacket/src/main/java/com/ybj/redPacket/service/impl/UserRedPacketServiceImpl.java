package com.ybj.redPacket.service.impl;

import com.ybj.redPacket.dao.RedPacketMapper;
import com.ybj.redPacket.model.RedPacket;
import com.ybj.redPacket.model.UserRedPacket;
import com.ybj.redPacket.dao.UserRedPacketMapper;
import com.ybj.redPacket.redPacket;
import com.ybj.redPacket.service.UserRedPacketServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
@Service
public class UserRedPacketServiceImpl extends ServiceImpl<UserRedPacketMapper, UserRedPacket> implements UserRedPacketServiceI {

    @Autowired
    RedPacketMapper redPacketMapper;

    @Autowired
    UserRedPacketMapper userRedPacketMapper;

    /**
    * <p> 乐观锁 </p>
     * @param redPacketId
     * @param userId
     * @return int
     * @author yuanbaojian
     * @date 2020/6/9
     * @time 13:35
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int grapRedPacketOptimisticLock(String redPacketId, String userId) {
        RedPacket redPacket = redPacketMapper.selectById(redPacketId);
        if(redPacket.getStock() > 0){
            System.out.println("乐观锁 线程" + Thread.currentThread().getName() +"库存为 " + redPacket.getStock());
            int updateResult = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
            if(updateResult == 0){
                return 0;
            }
            //生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setGrabTime(LocalDateTime.now()).setAmount(BigDecimal.valueOf(10));
            userRedPacket.setRedPacketId(redPacketId).setUserId(userId).setNote("抢红包 " + redPacketId).setAmount(redPacket.getUnitAmount());
            int result = userRedPacketMapper.insert(userRedPacket);
            return result;
        }
        return 0;
    }

    // 乐观锁 三次重入
    @Override
    public int grapRedPacketOptimisticLockByTime(String redPacketId, String userId) {
        for(int i = 0; i < 3; i++) {
            RedPacket redPacket = redPacketMapper.selectById(redPacketId);
            if(redPacket.getStock() > 0){
                System.out.println("乐观锁 三次重入 线程" + Thread.currentThread().getName() +"库存为 " + redPacket.getStock());
                int updateResult = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if(updateResult == 0){
                    return 0;
                }
                //生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setGrabTime(LocalDateTime.now()).setAmount(BigDecimal.valueOf(10));
                userRedPacket.setRedPacketId(redPacketId).setUserId(userId).setNote("抢红包 " + redPacketId).setAmount(redPacket.getUnitAmount());
                int result = userRedPacketMapper.insert(userRedPacket);
                return result;
            }
        }
        return 0;
    }

    @Async
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int grapRedPacketByPessimisticLock(String redPacketId, String userId) {
        RedPacket redPacket = redPacketMapper.getRedPacketForUpdate(redPacketId);
        if(redPacket.getStock() > 0){
            System.out.println("悲观锁线程 " + Thread.currentThread().getName() +" 库存为 " + redPacket.getStock());
            redPacketMapper.decreaseRedPacket(redPacketId);
            //生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setGrabTime(LocalDateTime.now()).setAmount(BigDecimal.valueOf(10));
            userRedPacket.setRedPacketId(redPacketId).setUserId(userId).setNote("抢红包 " + redPacketId).setAmount(redPacket.getUnitAmount());
            int result = userRedPacketMapper.insert(userRedPacket);
            return result;
        }
        return 0;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int grapRedPacketNormal(String redPacketId, String userId) {
        RedPacket redPacket = redPacketMapper.selectById(redPacketId);
        if(redPacket.getStock() > 0){
            System.out.println("不做任何处理的 线程" + Thread.currentThread().getName() + " 库存为 " + redPacket.getStock());
            redPacketMapper.decreaseRedPacket(redPacketId);
            //生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setGrabTime(LocalDateTime.now()).setAmount(BigDecimal.valueOf(10));
            userRedPacket.setRedPacketId(redPacketId).setUserId(userId).setNote("抢红包 " + redPacketId).setAmount(redPacket.getUnitAmount());
            int result = userRedPacketMapper.insert(userRedPacket);
            return result;
        }
        return 0;
    }

}
