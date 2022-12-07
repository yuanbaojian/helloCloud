package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** * @author  daijiyong * @date
2022-10-08 22:36:57 */
public class Q0870AdvantageShuffle{    //给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的
//数目来描述。 
//
// 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= 10⁵ 
// nums2.length == nums1.length 
// 0 <= nums1[i], nums2[i] <= 10⁹ 
// 
//
// Related Topics 贪心 数组 双指针 排序 👍 323 👎 0
    public static void main(String[] args) {
        Solution solution = new Q0870AdvantageShuffle().new Solution();
        int[] nums1 = {12,24,8,32};
        int[] nums2 = {13,25,32,11};
        int[] ints = solution.advantageCount(nums1, nums2);
        System.out.println("ints = " + ints);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        //这道题有点意思哈，可以想想做法
        //把nums1  nums2 进行排序处理，从小到大， 按照田忌赛马的逻辑来

        int[] nums2Copy = Arrays.copyOf(nums2,nums2.length);

        Map<Integer,Integer> indexMap = new HashMap<>();

        //2,7,11,15
        //1,10,4,11

        //12,24,8,32
        //13,25,32,11
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        //2,7,11,15
        //1,4,10,11

        //8 ,12,24,32
        //11,13,25,32

        //12,24,32
        //11,13,25,32

        //此时num1 和 nums2都已经排好序了，现在就要对num1进行遍历，找到对应优势了
        for (int i = 0; i < nums1.length; i++) {
            //存在优势
            if(nums1[i]> nums2[i]){
                //问题在于如何定位呢，假设元素不重复，利用hashMap定位
                indexMap.put(nums2[i],nums1[i]);
            } else{
                //没有优势，需要下一个, 这个需要解决
                int temp = i;
                while (temp<nums1.length && nums1[temp] < nums2[i] ){
                    temp++;
                }

                indexMap.put(nums2[i],nums1[temp]);
                //移动指针
                i = temp;
            }
        }
        //todo 使用双指针代替单次for循环


        //组装最终结果
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums2Copy.length; i++) {
            if(Objects.nonNull(indexMap.get(nums2Copy[i]))){
                result[i] = indexMap.get(nums2Copy[i]);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}