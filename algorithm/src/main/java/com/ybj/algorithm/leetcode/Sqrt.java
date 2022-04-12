package com.ybj.algorithm.leetcode;

public class Sqrt {

    /**
     * 需要考虑超大数的影响
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        for (int i = 0; i <= x / 2; i++) {
            if (i*i == x){
                return i;
            }
            if(i*i < x &  (i+1) * (i+1) > x){
                return i;
            }
            System.out.println("i = " + i);
        }
        return 1;
    }

    public static int mySqrt2(int x) {
        if(x ==0){
            return 0;
        }
        for (int i = 1; i <= x / 2; i++) {
            if (x / i == i){
                return i;
            }
            if( x/i > i &  x / (i+1) < i + 1){
                return i;
            }
            // System.out.println("i = " + i);
        }
        return 1;
    }


    public static void main(String[] args) {
        int i = mySqrt2(2147483647
        );
        System.out.println("i = " + i);
    }

}
