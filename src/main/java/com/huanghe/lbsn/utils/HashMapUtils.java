package com.huanghe.lbsn.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @Author huanghe
 * @Date 2018/12/30 11:18
 * @Description
 */
public class HashMapUtils {

    /**
     * 求Map<K,V>中Value(值)的最大值
     *
     * @param map
     * @return
     */
    public static Object getMaxValue(Map<Integer, Double> map) {
        if (map == null)
            return null;
        int length =map.size();
        Collection<Double> c = map.values();
        Object[] obj = c.toArray();
        Arrays.sort(obj);
        return obj[length-1];
    }

    /**
     * 获取Map中value的最小值
     * @param map
     * @return
     */
    public static Object getMinValue(Map<Integer, Double> map) {
        if (map == null)
            return null;
        Collection<Double> c = map.values();
        Object[] obj = c.toArray();
        Arrays.sort(obj);
        return obj[0];
    }

}
