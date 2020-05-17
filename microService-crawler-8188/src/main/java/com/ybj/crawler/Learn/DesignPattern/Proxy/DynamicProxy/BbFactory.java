package com.ybj.crawler.Learn.DesignPattern.Proxy.DynamicProxy;

import com.ybj.crawler.Learn.DesignPattern.Proxy.WomanToolsFactory;

/**
 * @Author BbFactory
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class BbFactory implements WomanToolsFactory {

    @Override
    public void saleWomanTools(Integer length) {
        System.out.format("您订购了 %s 的男模 %n", length);
    }
}
