package com.ybj.mysql.service;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.imageio.spi.ImageReaderSpi;
import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author Test
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
class Key {
    private String k;
    private List<Key> list;
}
public class Test {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList(
                "信息部,IT组,张三",
                "信息部,IT组,李四",
                "信息部,运维组,王五",
                "科技部,研发组,赵六");
        List<Key> list = new ArrayList<>();
        HashSet<String> set0 = new HashSet<String>();
        HashSet<String> set1 = new HashSet<String>();
        HashSet<String> set2 = new HashSet<String>();
        HashMap<Integer, String> hashMap0 = new HashMap();
        HashMap<Integer, String> hashMap1 = new HashMap();
        HashMap<Integer, String> hashMap2 = new HashMap();

        //防止Arrays.asList得到的List发生异常
        List<String> arrayList = new ArrayList<>(strList);
        for(int i = 0; i < arrayList.size(); i++) {
            String item = arrayList.get(i);
            String[] split = item.split(",", 3);
        }
        for (String item2 : set2) {
            Key key2 = new Key();
            key2.setK(item2);
            for (String item1 : set1) {
                Key key1 = new Key();
                key1.setK(item1);
            }
        }

        // TODO 请填写此处代码


        System.out.println(JSON.toJSONString(arrayList));
    }
}