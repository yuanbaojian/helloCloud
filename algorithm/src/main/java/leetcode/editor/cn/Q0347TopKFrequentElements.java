package leetcode.editor.cn;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/** * @author  yuanbaojian * @date    2022-07-27 10:45:54 */
public class Q0347TopKFrequentElements{    //给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 1272 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0347TopKFrequentElements().new Solution();
        int[] nums = {1,1,1,2,2,3};
        int[] ints = solution.topKFrequent(nums, 2);
        System.out.println("ints = " + ints);

    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new LinkedHashMap();
        for (int i = 0; i < nums.length; i++) {
            Integer tempCount = map.getOrDefault(nums[i], 0);
            map.put(nums[i],++tempCount);
        }
        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> o2.count - o1.count);
        map.forEach( (key,value)->{
            priorityQueue.add(new Entry(key,value));
        });

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll().value;
        }
        return result;
    }

    class Entry{

        public Entry(int value ,int count){
            this.value = value;
            this.count = count;
        }

        int value;

        int count;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
}