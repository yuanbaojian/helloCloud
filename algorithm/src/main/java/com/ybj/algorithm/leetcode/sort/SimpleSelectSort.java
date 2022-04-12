package com.ybj.algorithm.leetcode.sort;

import java.util.Arrays;

public class SimpleSelectSort {

    public static void main(String[] args) {
        int [] array = {5,2,1,3,8,6,7};
        simpleSelectSort(array);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }

    public static void simpleSelectSort(int [] array){
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for ( int j = i + 1; j < array.length; j++) {
                //如果 前面>后面，记录下标
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            if(i != minIndex){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }


}
