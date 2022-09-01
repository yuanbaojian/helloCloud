package leetcode.editor.cn;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/** * @author  yuanbaojian * @date    2022-06-08 16:16:59 */
public class Q0239SlidingWindowMaximum{
    ////////给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只
//向右
////移动
//////一位
////////。 
////////
//////// 返回 滑动窗口中的最大值 。 
////////
//////// 
////////
//////// 示例 1： 
////////
//////// 
////////输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
////////输出：[3,3,5,5,6,7]
////////解释：
////////滑动窗口的位置 最大值
////////--------------- -----
////////[1 3 -1] -3 5 3 6 7 3
//////// 1 [3 -1 -3] 5 3 6 7 3
//////// 1 3 [-1 -3 5] 3 6 7 5
//////// 1 3 -1 [-3 5 3] 6 7 5
//////// 1 3 -1 -3 [5 3 6] 7 6
//////// 1 3 -1 -3 5 [3 6 7] 7
//////// 
////////
//////// 示例 2： 
////////
//////// 
////////输入：nums = [1], k = 1
////////输出：[1]
//////// 
////////
//////// 
////////
//////// 提示： 
////////
//////// 
//////// 1 <= nums.length <= 10⁵ 
//////// -10⁴ <= nums[i] <= 10⁴ 
//////// 1 <= k <= nums.length 
//////// 
//////// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1666 👎 0
//////
////
//
    public static void main(String[] args) {
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            priorityQueue.add(i);
            priorityQueue.add(25);
        }
        Solution solution = new Q0239SlidingWindowMaximum().new Solution();
        int [] nums = {1,3,-1,-3,5,3,6,7};
        int[] ints = solution.maxSlidingWindow2(nums, 3);
        System.out.println("ints = " + Arrays.toString(Arrays.stream(ints).toArray()));
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotoneDeque monotoneDeque = new MonotoneDeque();
        int[] resultArray = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
           if(i<k-1){
               // 先把k个窗口填满
               monotoneDeque.pushValue(nums[i]);
               if(i==k-1){
                   resultArray[0] = monotoneDeque.max();
               }
           } else {
               //窗口滑动了
               monotoneDeque.pushValue(nums[i]);
               // 记录最大元素, 这个位置需要确定好
               resultArray[i-k+1] = monotoneDeque.max();
               // 移出指定元素（队首元素）
               monotoneDeque.remove(nums[i - k + 1]);
           }
        }
        return resultArray;
    }

    @Data
    @AllArgsConstructor
    class ArrayObject{

        private Integer value;

        private Integer index;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int [] result = new int[nums.length - k + 1];
//       {值，下标}，  值按照从大到小排列，如果值相等，下表也是从大到小
        PriorityQueue<ArrayObject> priorityQueue = new PriorityQueue<>((a,b)->{
            if(!a.getValue().equals(b.getValue())){
                return b.getValue()-a.getValue();
            } else{
                return b.getIndex() - a.getIndex();
            }
        });

        for (int i = 0; i < nums.length; i++) {
            ArrayObject arrayObject = new ArrayObject(nums[i], i);
            if(i<k){
                //            容量为2的数组
                priorityQueue.offer(arrayObject);
                if(i==k-1){
                    result[0] = priorityQueue.peek().getValue();
                }
            } else {
                //增加新元素
                priorityQueue.offer(arrayObject);
                //如果顶部元素是i-k之前的素材，即不是这个范围的，那么需要移出， 否则不会造成影响
                while (!priorityQueue.isEmpty() && priorityQueue.peek().getIndex() <= i-k) {
                    priorityQueue.poll();
                }
                //拿出最大值
                result[i - k + 1] = priorityQueue.peek().getValue();
            }
        }
        return result;
    }

    class MonotoneDeque {
        LinkedList<Integer> deque = new LinkedList<>();

        public void pushValue(Integer value){
            // 使得value前面的值都大于value
            while (!deque.isEmpty() && deque.getLast()<value){
                deque.pollLast();
            }
            deque.addLast(value);
        }

        public Integer max(){
            return deque.getFirst();
        }

        public void remove(Integer target){
            if(target.equals(deque.getFirst())){
                deque.pollFirst();
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}