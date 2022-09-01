package leetcode.editor.cn.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] array){
        int[] temp = new int[array.length];
        sort(array,0,array.length-1,temp);
    }

    public static void sort(int[] array, int left, int right, int[] temp){
        if(left<right){
            int mid = (left + right)/2;
            sort(array,left,mid,temp);
            sort(array,mid+1,right,temp);
            merge(array,left,mid,right,temp);
        }
    }

    public static void merge(int[] array, int left, int mid, int right,int[] temp){
        int i = left;
        int j = mid+1;
        int t=0;
        while (i<=mid && j<=right){
            if(array[i]<=array[j]){
                temp[t++] = array[i++];
            } else{
                temp[t++] = array[j++];
            }
        }
        while (i<=mid){
            temp[t++] = array[i++];
        }
        while (j<=right){
            temp[t++] = array[j++];
        }

        t=0;
        while (left<=right){
            array[left++] = temp[t++];
        }
    }
}
