package com.ybj.crawler.Learn.DesignPattern.Proxy;

/**
 * @Author Lison
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Lison implements ManToolsFactory {

    //工厂类
    public ManToolsFactory manToolsFactory;

    public Lison(ManToolsFactory manToolsFactory) {
        super();
        this.manToolsFactory = manToolsFactory;
    }


    @Override
    public void saleManTools(String size) {
        this.doSomethingBefore();
        manToolsFactory.saleManTools(size);
        this.doSomethingAfter();
    }

    private void doSomethingAfter() {
        System.out.println("完善的售后");
    }

    private void doSomethingBefore() {
        System.out.println("良好的售前");
    }
}
