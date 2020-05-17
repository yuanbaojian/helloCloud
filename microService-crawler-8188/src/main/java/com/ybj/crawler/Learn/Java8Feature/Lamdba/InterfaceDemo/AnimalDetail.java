package com.ybj.crawler.Learn.Java8Feature.Lamdba.InterfaceDemo;

import org.testng.annotations.Test;

public class AnimalDetail {


    // 1.声明并实例化变量，传递对象
    @Test
    public void test(){
        Animal dog=new Dog();
        animalMove(dog);
    }

    public static void animalMove(Animal animal){
        animal.move("旺财");
    }

}
