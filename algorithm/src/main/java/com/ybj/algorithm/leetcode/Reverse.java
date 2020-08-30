package com.ybj.algorithm.leetcode;

/**
 * @Author Reverse
 * @Description //7. 整数反转
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Reverse {

    public static void main(String[] args) {
        //
        int reverse = reverse(1534236469);
        System.out.println("reverse = " + reverse);
    }

    public static int reverse(int x) {
        Boolean minus = false;
        if(x < 0) {
            minus = true;
            x = -x;
        }
        int temp = x;
        int length = 0;
        while (temp >0 ){
            temp = temp / 10;
            length ++;
        }
        int number=0;
        int sum = 0;
        int i=0;
        while (x > 0){
            number = (int) (x / Math.pow(10,length - 1 -i));
            x = (int) (x % Math.pow(10,length - 1 -i));
            sum += number * Math.pow(10, i);
            i ++;
        }
        if(sum == 2147483647){
            return 0;
        }
        if(minus){
            sum = -sum;
        }
        return sum;
    }
}
