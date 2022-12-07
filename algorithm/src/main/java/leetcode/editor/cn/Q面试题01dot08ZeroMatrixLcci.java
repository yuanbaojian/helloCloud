package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

/** * @author  daijiyong * @date
2022-09-30 14:33:16 */
public class Q面试题01dot08ZeroMatrixLcci{    //编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
//
// 
//
// 示例 1： 
//
// 输入：
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出：
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
// 
//
// 示例 2： 
//
// 输入：
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出：
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//]
// 
//
// Related Topics 数组 哈希表 矩阵 👍 120 👎 0
    public static void main(String[] args) {
        Solution solution = new Q面试题01dot08ZeroMatrixLcci().new Solution();
        int[][] matrix = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        solution.setZeroes(matrix);
        System.out.println("matrix = " + matrix);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void setZeroes(int[][] matrix) {
        List<Integer> zeroI = new LinkedList<>();
        List<Integer> zeroJ = new LinkedList<>();
        //1. 定位哪行哪列包含0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //记录是否为0
                if(0==matrix[i][j]){
                    zeroI.add(i);
                    zeroJ.add(j);
                }
            }
        }
        //进行消0处理
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(zeroI.contains(i) || zeroJ.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println("matrix = " + matrix);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}