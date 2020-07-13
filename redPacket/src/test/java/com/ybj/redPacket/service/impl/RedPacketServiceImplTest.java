package com.ybj.redPacket.service.impl;

import com.ybj.redPacket.dao.RedPacketMapper;
import com.ybj.redPacket.model.RedPacket;
import com.ybj.redPacket.service.RedPacketServiceI;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
class RedPacketServiceImplTest {

    @Autowired
    RedPacketMapper redPacketMapper;

    @Autowired
    RedPacketServiceI redPacketService;

    @Test
    public void test(){
        List<RedPacket> list = redPacketService.list();
        for (RedPacket redPacket : list) {
            System.out.println("redPacket = " + redPacket);
        }
    }

    /**
    * <p>test方法自动回滚，</p>
     * @param
     * @return void
     * @author yuanbaojian
     * @date 2020/7/13
     * @time 15:31
     */
    @Transactional
    @Test
    void getRedPacket() {
        RedPacket redPacket = new RedPacket();
        redPacket.setStock(1000);
        RedPacket redPacket1 = new RedPacket();
        redPacket1.setStock(10001);
        redPacketMapper.insert(redPacket);
        redPacketMapper.insert(redPacket1);
        System.out.println("redPacket1 = " + 1/0);
    }

    @Transactional
    public void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        redPacket.setStock(1000);
        RedPacket redPacket1 = new RedPacket();
        redPacket1.setStock(10001);
        redPacketMapper.insert(redPacket);
        redPacketMapper.insert(redPacket1);
        System.out.println("redPacket1 = " + 1/0);
    }
}