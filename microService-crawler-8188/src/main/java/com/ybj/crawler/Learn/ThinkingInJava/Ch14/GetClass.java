package com.ybj.crawler.Learn.ThinkingInJava.Ch14;

import com.ybj.crawler.model.IpBean;
import org.testng.annotations.Test;

/**
 * 获得class对象的方法
 * @Date $ $
 * @Param $
 * @return $
 **/
public class GetClass {

    @Test
    public void test() throws ClassNotFoundException {
        IpBean ipBean = new IpBean();
        //1.通过现有对象获得
        Class c1 = ipBean.getClass();

        //2.forName方法获得
        Class c2 = Class.forName("com.ybj.crawler.model.IpBean");

        //3.通过 类名.class 获得
        Class c3 = IpBean.class;

        //4 基本内置对象的TYPE
        Class c4 = Integer.class;

        //获得父类类型
        Class c5 = c1.getSuperclass();

        System.out.println("c5 = " + c5);
    }

}
