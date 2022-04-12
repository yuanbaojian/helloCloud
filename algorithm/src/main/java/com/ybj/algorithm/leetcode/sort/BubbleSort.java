package com.ybj.algorithm.leetcode.sort;

import java.util.Arrays;

public class BubbleSort {


    public static void main(String[] args) {
        int [] array = {5,2,3,8,6,7};
        int[] result = bubbleSort(array);
        System.out.println("array = " + Arrays.toString(result));
    }



    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int [] array){
        for (int i = 0; i < array.length; i++) {
            for ( int j = i + 1; j < array.length; j++) {
                //如果 前面>后面， 则调换位置
                if(array[j-1] > array[j]){
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return  array;
    }
}
