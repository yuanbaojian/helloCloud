package com.ybj.crawler.Learn.spring.utils;

import com.ybj.crawler.model.IpBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @Author BeanUtilsDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class BeanUtilsDemo {

    @Test
    public void test(){
        IpBean ipBean1 = new IpBean();
        ipBean1.setIpAddress("222");
        IpBean ipBean2 = new IpBean();
        BeanUtils.copyProperties(ipBean1, ipBean2);
        System.out.println("ipBean2.toString() = " + ipBean2.toString());
    }
}
