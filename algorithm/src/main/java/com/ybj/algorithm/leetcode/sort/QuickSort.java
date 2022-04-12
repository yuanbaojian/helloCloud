package com.ybj.algorithm.leetcode.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int [] array = {5,2,3,8,6,7};
        quickSort(array,0,array.length-1);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }

    public static void quickSort(int [] array , int low, int high){
        //指针未到一起
        if(low < high){
            int position = partition(array,low,high);
            //左边遍历
            quickSort(array,low,position-1);
            //右边遍历
            quickSort(array,position+1,high);
        }
    }

    public static int partition(int [] array,int low, int high){
        //最后一个元素作为基准
        int pivot = array[high];
        int pointer = low;
        for (int i = low; i < high; i++) {
            //需要调换元素
            if(array[i] <= pivot){
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer ++ ;
            }
        }
        int temp = array[pointer];
        array[pointer] = array[high];
        array[high] = temp;
        return pointer;
    }
}
