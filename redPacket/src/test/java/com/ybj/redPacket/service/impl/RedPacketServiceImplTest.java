package com.ybj.redPacket.service.impl;

import com.ybj.redPacket.dao.RedPacketMapper;
import com.ybj.redPacket.model.RedPacket;
import com.ybj.redPacket.service.RedPacketServiceI;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}