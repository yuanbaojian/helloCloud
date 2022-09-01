package leetcode.editor.cn.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUcache {

    int cap;

    int defaultValue = -1;

    HashMap<Integer, Integer> cache = new LinkedHashMap();

    public LRUcache(int capacity){
        this.cap = capacity;
    }

    public int get(int key){
        if(!cache.containsKey(key)){
            return defaultValue;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void set(int key, int value){
        if(cache.containsKey(key)){
            cache.put(key,value);
            makeRecently(key);
            return;
        }
        //超长了，需要删除
        if(cache.size() > this.cap){
            //链表的表头就是最久未使用的key
            Integer oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key,value);
    }

    /**
     * 把key升级为最近使用
     * @param key
     */
    private void makeRecently(int key) {
        Integer value = cache.get(key);
        cache.remove(key);
        cache.put(key,value);
    }

}
