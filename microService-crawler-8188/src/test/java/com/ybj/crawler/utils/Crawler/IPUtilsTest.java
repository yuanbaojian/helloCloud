package com.ybj.crawler.utils.Crawler;

import com.ybj.crawler.model.IpBean;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class IPUtilsTest {

    @Test
    void getInstance() throws InterruptedException {
        Set<Integer> set = new CopyOnWriteArraySet<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                IPUtils instance = IPUtils.getInstance();
                set.add(instance.hashCode());
            });

        }
        Thread.sleep(2000);
        System.out.println("set = " + set.toString());
    }

    @Test
    public void test(){
        IPUtils instance = IPUtils.getInstance();
        IpBean ipBean = new IpBean();
        ipBean.setIpAddress("188.165.141.114");
        boolean result = instance.checkIpAvailable(ipBean);
        System.out.println("result = " + result);
    }

    @Test
    public void testFor(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void test2(){
        List<Long> list = null;
        list.forEach( a ->
                System.out.println("a = " + a));
    }
    @Test
    public void test23(){
        // List<Object> objects = Lists.new
        // objects.add(333);
    }

}