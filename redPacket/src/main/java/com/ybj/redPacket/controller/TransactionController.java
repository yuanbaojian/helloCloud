package com.ybj.redPacket.controller;

import com.ybj.api.annotation.GetParamLog;
import com.ybj.redPacket.feign.UserServiceFeign;
import com.ybj.redPacket.model.RedPacket;
import com.ybj.redPacket.service.RedPacketServiceI;
import com.ybj.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author TransactionController
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    RedPacketServiceI redPacketService;

    @Autowired
    UserServiceFeign userServiceFeign;



    @Transactional
    @GetParamLog
    @GetMapping("/insertByTransaction")
    public void getNumber(){
        RedPacket redPacket = new RedPacket();
        redPacket.setStock(1000);
        RedPacket redPacket1 = new RedPacket();
        redPacket1.setStock(10001);
        boolean result1 = redPacketService.save(redPacket);
        log.info("保存result1 {}", result1);
        System.out.println("redPacket1 = " + 1/0);
        boolean result2 = redPacketService.save(redPacket1);
        log.info("保存result2 {}", result2);
    }

    /**
    * <p></p>
     * @param
     * @return java.util.List<com.ybj.user.model.User>
     * @author yuanbaojian
     * @date 2020/7/13
     * @time 18:29
     */
    @GetMapping("getUsers")
    public List<User> getUsers(){
        List<User> allUsers = userServiceFeign.getAllUsers();
        return allUsers;
    }

}
