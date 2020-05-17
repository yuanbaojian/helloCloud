package com.ybj.crawler.model;

import java.util.LinkedList;
import java.util.List;

public class IpBeanList {


    public static List<IpBean> ipBeanList = new LinkedList<>();

    private static int count = 0;

    public static synchronized void add(IpBean bean) {
        ipBeanList.add(bean);
    }

    public static int getSize() {
        return ipBeanList.size();
    }

    public static List<IpBean> getIpBeanList(){
        return ipBeanList;
    }


    public static synchronized void increase() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }

}
