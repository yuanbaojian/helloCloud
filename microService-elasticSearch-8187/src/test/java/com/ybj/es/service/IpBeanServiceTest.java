package com.ybj.es.service;

import com.ybj.es.model.IpBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IpBeanServiceTest {

    @Autowired
    IpBeanService ipBeanService;

    // @Test
    // void findAllByServerAddressLike() {
    //     System.out.println("start to search" );
    //     List<IpBean> ipBeanList = ipBeanService.findAllByIp_addressLike("安徽");
    //     for (IpBean ipBean : ipBeanList) {
    //         System.out.println("ipBean.toString() = " + ipBean.toString());
    //     }
    // }
    //
    // @Test
    // void findAllByIpPortLike() {
    //     List<IpBean> allByIpPortLike = ipBeanService.findAllByIpPortLike("14");
    //     System.out.println("allByIpPortLike = " + allByIpPortLike);
    // }

    @Test
    void findAllByIdLike() {
        List<IpBean> ipBeanList = ipBeanService.findAllByIdLike("fff");
        for (IpBean ipBean : ipBeanList) {
            System.out.println("ipBean.toString() = " + ipBean.toString());
        }
    }
}