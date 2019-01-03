package com.huanghe.lbsn;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author huanghe
 * @Date 2019/1/1 12:39
 * @Description
 */
public class SetCal {

    public static void main(String[] args) {
        Set<String> poi_set = new LinkedHashSet<>();
        poi_set.add("1");
        poi_set.add("2");
        poi_set.add("6");
        poi_set.add("11");
        poi_set.add("58");
        poi_set.add("78");

        Set<String> current_user_poi = new LinkedHashSet<>();
        current_user_poi.add("1");
        current_user_poi.add("2");
        current_user_poi.add("6");

        Set<String> result = new HashSet<String>();
        result.addAll(poi_set);
        result.removeAll(current_user_poi);
        System.out.println(result);
    }
}
