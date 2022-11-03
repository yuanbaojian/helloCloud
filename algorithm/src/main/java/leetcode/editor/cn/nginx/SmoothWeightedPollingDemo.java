package leetcode.editor.cn.nginx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SmoothWeightedPollingDemo {

    /**
     * 权重对象
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class weightDTO{

        /**
         * 名称
         */
        String name;

        /**
         * 权重
         */
        Integer weight;

        /**
         * 当前权重
         */
        Integer currentWeight = 0;
    }

    @Data
    @AllArgsConstructor
    static
    class Percent {

        /**
         * 名称
         */
        String name;

        /**
         * 权重
         */
        Integer weight;
    }


    public static Map<String,weightDTO> weightDTOMap = new ConcurrentHashMap<>();

    public static final List<String> list = new LinkedList<>();

    /**
     * 权重总和
     */
    private static Integer weightSum = 0;

    /**
     * 调整权重
     * @param pages
     */
    public static void adjustWeight(List<Percent> pages){
        if(CollectionUtils.isEmpty(weightDTOMap)){
            pages.forEach( a->{
                weightDTO weightDTO = new weightDTO();
                BeanUtils.copyProperties(a,weightDTO);
                //组装权重对象
                weightDTOMap.put(a.getName(),weightDTO);
                //计算权重和
                weightSum+=a.getWeight();
            });
        }
        //重置currentWeight
        weightDTOMap.values().forEach(a->{
            a.setCurrentWeight(a.getCurrentWeight() + a.getWeight());
        });

    }

    public static void main(String[] args) {
        //计数Map
        Map<String,Integer> countMap = new HashMap<>();
        //百分比列表
        List<Percent> percentList = new LinkedList<>();
        percentList.add( new Percent("a",5));
        percentList.add(new Percent("b",1));
        percentList.add(new Percent("c",1));
        weightDTO maxWeightDTO = null;
        for (int i = 0; i < 70; i++) {
            //调整权重
            adjustWeight(percentList);
            for(weightDTO weightDTO: weightDTOMap.values()){
                //选出最大的CW
                if(maxWeightDTO == null || maxWeightDTO.getCurrentWeight() < weightDTO.getCurrentWeight()){
                    maxWeightDTO = weightDTO;
                }
            }
            if(Objects.nonNull(maxWeightDTO)){
                //重置最大实例的 CW = CW - weightSum
                maxWeightDTO.setCurrentWeight(maxWeightDTO.getCurrentWeight()-weightSum);
                final Integer count = countMap.getOrDefault(maxWeightDTO.getName(), 0);
                countMap.put(maxWeightDTO.getName(),count+1);
            }
        }
        System.out.println("countMap = " + countMap.toString());
    }

}