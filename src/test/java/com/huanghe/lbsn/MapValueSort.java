package com.huanghe.lbsn;

import java.util.*;

/**
 * @Author huanghe
 * @Date 2018/12/30 10:49
 * @Description
 */
public class MapValueSort {

    public static void main(String a[]) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("zhangsan", 10);
        map.put("lisi", 8);
        map.put("wangwu", 18);
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        for(Map.Entry<String, Integer> t:list){
            System.out.println(t.getKey()+":"+t.getValue());
        }
    }

}
