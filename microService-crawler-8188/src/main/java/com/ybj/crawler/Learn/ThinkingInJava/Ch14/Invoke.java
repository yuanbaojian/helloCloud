package com.ybj.crawler.Learn.ThinkingInJava.Ch14;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Class对象的用法
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
public class Invoke {

  /**
   * 通过newInstance() 实例化一个对象
   * 前提:
   *    1.该类必须有一个无参构造器
   *    2.构造器的访问权限要够（）
   * */
  @Test
  public void invokeDemo() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
      Class c1 = Class.forName("com.ybj.crawler.Learn.ThinkingInJava.Ch14.user");
      user user1 = (user) c1.newInstance();
      // 可以直接运行
      user1.say();
      Method setName = c1.getDeclaredMethod("setName", String.class);

      // 通过反射操作  方法
      // "王尼玛"  setName 的参数
      setName.invoke(user1, "王尼玛");
      user1.say();

      // 通过反射操作  属性
      Field name = c1.getDeclaredField("name");
      //private修饰的属性， invoke时需要设置权限为true （默认为flase）
      name.setAccessible(true);
      name.set(user1 , "杨过");
      user1.say();
      Parameter[] parameters = c1.getMethods()[7].getParameters();
      for(int i = 0; i < parameters.length; i++) {
          Class<?> type = parameters[i].getType();
          String name1 = parameters[i].getName();
          log.info("类型为 {}, 形参名为 {}", type, name1);
      }

      /**
       * Unsatisfied dependency expressed through constructor parameter 0: Could not convert argument value of type [java.lang.String] to required type [java.lang.Class]: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Class';
       */
  }
}
@Data
class user{
    private  String name;
    private  int age;
    public void say(){
        System.out.println("hello " + this.name );
    }

    public void sayHello(String userName){
        System.out.println("hello " + name);
    }
}
