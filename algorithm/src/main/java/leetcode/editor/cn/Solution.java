package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static List<String> odd = new ArrayList<>();

    static {
        odd.add("1");
        odd.add("3");
        odd.add("5");
        odd.add("7");
        odd.add("a");
        odd.add("c");
        odd.add("e");
        odd.add("g");
    }
    public boolean squareIsWhite(String coordinates) {
        // 单&单 ： 黑
        // 双&双：黑

        // 单&双 ：白
        // 双&单：白
        char[] chars = coordinates.toCharArray();
        boolean same = odd.contains(String.valueOf(chars[0])) == odd.contains(String.valueOf(chars[1]));
        return !same;
    }

    public static void main(String[] args) {
        String coordinates = "c7";
        System.out.println("coordinates = " + coordinates);
        Solution solution = new Solution();
        boolean b = solution.squareIsWhite(coordinates);
        System.out.println("b = " + b);
    }
}