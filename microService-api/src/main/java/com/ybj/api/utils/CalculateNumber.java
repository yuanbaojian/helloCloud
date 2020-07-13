package com.ybj.api.utils;

import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author CalculateNumber
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CalculateNumber {

    public static void main(String[] args) {
        Integer []  numbers = { 1, 4, 4, 6,9};
        Integer target = 8;
        Integer [] result = getIndex(numbers, target);
        Integer [] result2 = getIndex2(numbers, target);
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    private static Integer[] getIndex2(Integer[] numbers, Integer target) {
        return new Integer[0];
    }

    /**
     * <p>利用HashMap进行存储 key value  (值， 下标位置)
     * 同时避免了重复元素的影响
     * 得到结果</p>
     * @param numbers
     * @param target
     * @return java.lang.Integer[]
     * @author yuanbaojian
     * @date 2020/7/8
     * @time 17:34
     */
    private static Integer[] getIndex(Integer[] numbers, int target) {
        Map<Integer, Integer> numberMap = new HashMap<>();
        for(int i = 0; i < numbers.length; i++) {
            int temp = target - numbers[i];
            if(numberMap.containsKey(temp) && numberMap.get(temp)!=i){
                return new Integer[]{i,numberMap.get(temp)};
            }
            numberMap.put(numbers[i],i );
        }
        return new Integer[]{};
    }
}
