package com.ybj.crawler.Learn.ThinkingInJava.Ch14;

import lombok.Data;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author ClassForName
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ClassForName {
    @Test
    public void test() throws ClassNotFoundException {
        Class aClass = Class.forName("com.ybj.crawler.Learn.ThinkingInJava.Ch14.User");

        Class aClass2 = Class.forName("com.ybj.crawler.Learn.ThinkingInJava.Ch14.User");

        System.out.println("aClass = " + aClass);
    }

    /**
     * 通过反射获得类的具体信息
     * @author yuanbaojian
     * @date 2020/3/11
     * @time 21:40
     */
    @Test
    public void testGetDetail() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.ybj.crawler.Learn.ThinkingInJava.Ch14.User");

        //类名
        String name = c1.getName();
        //带包路径
        System.out.println("getName = " + name);
        String simpleName = c1.getSimpleName();
        System.out.println("getSimpleName = " + simpleName);

        //获得类属性(全部的， 不管是不是public)
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        //获得指定属性
        Field userName = c1.getDeclaredField("userName");
        System.out.println("指定属性userName = " + userName);

        //获得类的方法 (包括Object Class的方法)
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        // 只获得自己所有的方法
        Method[] declaredMethods = c1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod = " + declaredMethod);
        }

        //获得指定的方法  第二个参数是 getUserName的参数，如果没有就为null
        Method getUsername = c1.getMethod("getUserName", null);
        System.out.println("getUsername = " + getUsername);


        //获得构造器
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }

        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println("declaredConstructor = " + declaredConstructor);
        }

        //获得指定构造器
        // 参数是 Class<T> 类型，  需要填入参数的 类型.class
        Constructor constructor = c1.getConstructor(String.class, int.class);
        System.out.println("constructor = " + constructor);
    }

}
@Data
class User{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
