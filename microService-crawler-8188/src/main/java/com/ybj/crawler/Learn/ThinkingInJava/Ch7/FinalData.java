package com.ybj.crawler.Learn.ThinkingInJava.Ch7;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

/**
 * @Author FinalData
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class FinalData {

    private static Random random = new Random();
    private String  id;

    public FinalData(String id) {
        this.id = id;
    }
    //既是static 又是 final使用（编译器常量） 大写字母下划线分割
    private final int valueOne = 9;
    // static final 全局的，不同对象都是这个属性值
    private static final int VALUE_TWO = 99;
    private static final int VALUE_THREE = 99;

    //final 在同一个对象中，是
    private final int i4 = random.nextInt(20);
    static final int INT_5 = random.nextInt(20);

    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VAL_3 = new Value(22);

    private final int[] a = {1,2,3,4,5,6};

    @Override
    public String toString() {
        return "FinalData{" +
                "id='" + id + '\'' +
                ", i4=" + i4 +
                ", INT_5=" + INT_5 +
                '}';
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
        fd1.v2.i++;
        fd1.v1 = new Value(9);
        for(int i = 0; i < fd1.a.length; i++) {
            fd1.a[i]++;
        }
        System.out.println("fd1 = " + fd1);
        System.out.println("creating new finalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println("fd1 = " + fd1);
        System.out.println("fd2 = " + fd2);
    }
}

class Value{
    int i;

    public Value(int i) {
        this.i = i;
    }

}