package leetcode.editor.cn.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums ={5,4,3,8,9,10,1};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums,0,nums.length-1);
        System.out.println("quickSort = " + Arrays.toString(nums));
    }

    void quickSort(int[] nums, int l,int h){
        //root节点特殊处理
        if(l>=h){
            return;
        }
        //将index = p 位置的元素排好序
        int p = partitionV2(nums, l,h);
        //left
        quickSort(nums,l,p-1);
        //right
        quickSort(nums,p+1,h);

    }

    /**
     * 精华所在
     * 对l-h 之间的元素进行切分，定位一个元素,这个太复杂了
     * @param nums
     * @param l
     * @param h
     * @return 拍好序元素的下标
     */
    private int participation(int[] nums, int l, int h) {
        //定义第一个元素为起始元素
        int pivot = nums[l];
        //规定排序范围
        int i = l+1, j=h;
        while(i<=j){
            //收缩i
            while(i<j && nums[i]<=pivot){
                i++;
            }
            //收缩j
            while ((j>=l && nums[j]>pivot)){
                j--;
            }
            if(i>=j){
                break;
            }
            swap(nums,i,j);
        }
        swap(nums,l,j);
        /*
        index = 3 value = 5  二叉树的root节点
        index = 0 value = 1 left
         */
        return j;
    }


    private int partitionV2(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

