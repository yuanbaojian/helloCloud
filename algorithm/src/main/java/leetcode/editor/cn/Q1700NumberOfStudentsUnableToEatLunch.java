package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/** * @author  daijiyong * @date
2022-10-19 17:40:13 */
public class Q1700NumberOfStudentsUnableToEatLunch{    //学校的自助午餐提供圆形和方形的三明治，分别用数字 0 和 1 表示。所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。 餐厅里三明治的数量与学生
//的数量相同。所有三明治都放在一个 栈 里，每一轮： 
//
// 
// 如果队列最前面的学生 喜欢 栈顶的三明治，那么会 拿走它 并离开队列。 
// 否则，这名学生会 放弃这个三明治 并回到队列的尾部。 
// 
//
// 这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。 
//
// 给你两个整数数组 students 和 sandwiches ，其中 sandwiches[i] 是栈里面第 i 个三明治的类型（i = 0 是栈的顶部）
//， students[j] 是初始队列里第 j 名学生对三明治的喜好（j = 0 是队列的最开始位置）。请你返回无法吃午餐的学生数量。 
//
// 
//
// 示例 1： 
//
// 输入：students = [1,1,0,0], sandwiches = [0,1,0,1]
//输出：0 
//解释：
//- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
//- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
//- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
//- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
//- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
//- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
//- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
//- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
//所以所有学生都有三明治吃。
// 
//
// 示例 2： 
//
// 输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= students.length, sandwiches.length <= 100 
// students.length == sandwiches.length 
// sandwiches[i] 要么是 0 ，要么是 1 。 
// students[i] 要么是 0 ，要么是 1 。 
// 
//
// Related Topics 栈 队列 数组 模拟 👍 109 👎 0
    public static void main(String[] args) {
        Solution solution = new Q1700NumberOfStudentsUnableToEatLunch().new Solution();
        int [] students = {1,1,1,0,0,1}, sandwiches = {1,0,0,0,1,1};
        int i = solution.countStudentsV2(students, sandwiches);
        System.out.println("i = " + i);
    }    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        //这个肯定是栈的知识了
        Deque<Integer> studentDeque = new LinkedList<>();
        Deque<Integer> sandwichesDeque = new LinkedList<>();

        for (int i = 0; i < students.length; i++) {
            studentDeque.push(students[i]);
        }

        for (int i = 0; i < sandwiches.length; i++) {
            sandwichesDeque.push(sandwiches[i]);
        }
        // 0 1 0 1
        // 1 1 0 0

        //换
        // 0 1 0 1
        // 1 0 0 1

        //换
        // 0 1 0 1
        // 0 0 1 1

        // 1 0 1
        // 0 1 1

        //换
        // 1 0 1
        // 1 1 0

        //换
        // 0 1
        // 1 0

        // 0 1
        // 0 1
        int originalStudentLength = students.length;
        int changeTimes = 0;
        while(!sandwichesDeque.isEmpty()){
            //合口味，即相等
            if(studentDeque.peek().equals(sandwichesDeque.peek())){
                // 移除队首
                sandwichesDeque.removeFirst();
                studentDeque.removeFirst();
            } else {
                //不等， 则换位子到后面
                changeTimes++;
                Integer first = studentDeque.pollFirst();
                studentDeque.addLast(first);
                if(changeTimes>originalStudentLength){
                    break;
                }
            }
        }
        return studentDeque.size();
        // 1,0,0,0,1,1
        // 1,1,1,0,0,1

        // 0,0,0,1,1
        // 1,1,0,0,1

        //换
        // 0,0,0,1,1
        // 1,0,0,1,1

        //换
        // 0,0,0,1,1
        // 0,0,1,1,1

        // 0,1,1
        // 1,1,1

        //换
        // 0,1,1
        // 1,1,1

    }

    public int countStudentsV2(int[] students, int[] sandwiches) {
        int s0 = 0, s1 =0;
        //记录喜欢吃圆形 & 方形 三明治的学生数量
        for (int i = 0; i < students.length; i++) {
            if(students[i] ==0){
                s0++;
            } else {
                s1++;
            }
        }
        // 输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
        for (int i = 0; i < sandwiches.length; i++) {
            if(sandwiches[i] == 0 && s0 > 0){
                s0--;
            } else if (sandwiches[i] == 1 && s1 > 0) {
                s1--;
            } else {
                //注意：如果匹配不到学生，就没必要继续往下走了，因为栈顶堵住了
                break;
            }

        }
        return s0+s1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}