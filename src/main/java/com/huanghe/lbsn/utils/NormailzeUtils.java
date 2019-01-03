package com.huanghe.lbsn.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author huanghe
 * @Date 2019/1/1 17:20
 * @Description
 */
public class NormailzeUtils {

    /**
     * 数据的归一化处理,效益型指标（越大越好型）的隶属函数
     * @param map
     * @return
     */
    public HashMap normailze(HashMap<Integer,Double> map) {
        HashMap<Integer,Double> normal = new HashMap<>();
        Double MaxValue = Double.parseDouble(HashMapUtils.getMaxValue(map).toString());
        for (Map.Entry<Integer, Double> normalMap : map.entrySet()) {
            normal.put(normalMap.getKey(), normalMap.getValue() / MaxValue);
        }
        return normal;
    }

    /**
     * 距离归一化处理：成本型指标（越小越好型）(max-x)/(max-min)
     * @param map
     * @return
     */
    public HashMap normailzeDistance(HashMap<Integer,Double> map) {
        HashMap<Integer,Double> normal = new HashMap<>();
        Double MaxValue = Double.parseDouble(HashMapUtils.getMaxValue(map).toString());
        Double MinValue = Double.parseDouble(HashMapUtils.getMinValue(map).toString());
        for (Map.Entry<Integer, Double> normalMap : map.entrySet()) {
            normal.put(normalMap.getKey(), (MaxValue-normalMap.getValue()) / (MaxValue-MinValue));
        }
        return normal;

    }
}
