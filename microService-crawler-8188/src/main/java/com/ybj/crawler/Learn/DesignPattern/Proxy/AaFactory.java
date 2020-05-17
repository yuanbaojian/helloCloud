package com.ybj.crawler.Learn.DesignPattern.Proxy;

/**
 * @Author AaFactory
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class AaFactory implements ManToolsFactory {

    @Override
    public void saleManTools(String size) {
        System.out.printf("根据您的需求,定制了为 %s 的模特 %n" , size);
    }
}
