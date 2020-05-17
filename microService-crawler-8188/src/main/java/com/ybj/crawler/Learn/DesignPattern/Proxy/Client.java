package com.ybj.crawler.Learn.DesignPattern.Proxy;

import com.ybj.crawler.Learn.DesignPattern.Proxy.DynamicProxy.BbFactory;
import com.ybj.crawler.Learn.DesignPattern.Proxy.DynamicProxy.LisonCompany;

/**
 * @Author Client
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Client {

    public static void main(String[] args) {
        ManToolsFactory manToolsFactory = new AaFactory();
        Lison lison = new Lison(manToolsFactory);
        lison.saleManTools("19");
        System.out.println(" ------------------------------------------ ");

        // 父类声明  子类实例化
        WomanToolsFactory womanToolsFactory = new BbFactory();
        LisonCompany lisonCompany = new LisonCompany(womanToolsFactory);
        //获得实例化对象
        WomanToolsFactory womanToolsFactory1 = (WomanToolsFactory) lisonCompany.getProxyInstance();
        //通过反射即可获得对应的函数
        womanToolsFactory1.saleWomanTools(11);
        System.out.println(" ------------------------------------------ ");


        lisonCompany.setFactory(manToolsFactory);
        ManToolsFactory manToolsFactory1 = (ManToolsFactory) lisonCompany.getProxyInstance();
        manToolsFactory1.saleManTools("170");

    }

}
