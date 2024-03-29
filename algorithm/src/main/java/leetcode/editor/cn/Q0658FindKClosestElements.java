package leetcode.editor.cn;

import java.util.*;

/** * @author  yuanbaojian * @date    2022-08-25 08:40:14 */
public class Q0658FindKClosestElements{    //给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
//
// 整数 a 比整数 b 更接近 x 需要满足： 
//
// 
// |a - x| < |b - x| 或者 
// |a - x| == |b - x| 且 a < b 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,2,3,4,5], k = 4, x = 3
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：arr = [1,2,3,4,5], k = 4, x = -1
//输出：[1,2,3,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= arr.length 
// 1 <= arr.length <= 10⁴ 
// arr 按 升序 排列 
// -10⁴ <= arr[i], x <= 10⁴ 
// 
// Related Topics 数组 双指针 二分查找 排序 堆（优先队列） 👍 367 👎 0
    public static void main(String[] args) {

        PriorityQueue<String> queue = new PriorityQueue<>( (a,b) -> {
            return a.length() - b.length();
        });
        Solution solution = new Q0658FindKClosestElements().new Solution();
        int[] array = {1,2,3,4,5};
        List<Integer> closestElements = solution.findClosestElements(array, 4, 3);
        System.out.println("closestElements = " + closestElements);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Data> priorityQueue = new PriorityQueue<>( (a,b) -> {
            //按照差值进行排序, 默认从小到大
            if(a.difference != b.difference){
                return a.difference-b.difference;
            }  else {
                //当差值一样时， 越小的排在前面
                return b.value - a.value;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.offer(new Data(arr[i], Math.abs(x-arr[i])));
        }
        System.out.println("priorityQueue = " + priorityQueue);
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            result.add(priorityQueue.poll().value);
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    class Data{
        int value;
        int difference;

        Data(int value, int difference){
            this.value = value;
            this.difference = difference;
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
}