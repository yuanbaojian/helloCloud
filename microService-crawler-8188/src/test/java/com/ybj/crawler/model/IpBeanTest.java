package com.ybj.crawler.model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IpBeanTest {

    @Test
    public void test(){
        Folder folder1 = new Folder();
        folder1.setId("1");
        Folder folder2 = new Folder();
        folder2.setId("1");

        List<Folder> folderList1= new ArrayList<>();
        folderList1.add(folder1);
        List<Folder> folderList2= new ArrayList<>();
        folderList2.add(folder2);

        Set<IpBean> ipBeanMap = new HashSet<>();
        IpBean ipBean = new IpBean();
        ipBean.setID("1");
        IpBean ipBean2 = new IpBean();
        ipBean2.setID("1");
        boolean equals = ipBean.equals(ipBean2);
        int hashCode = ipBean.hashCode();
        System.out.println("hashCode = " + hashCode);
        ipBeanMap.add(ipBean);
        ipBeanMap.add(ipBean2);
        System.out.println("ipBeanMap.toString() = " + ipBeanMap.toString());
        System.out.println("equals = " + equals);
    }

}