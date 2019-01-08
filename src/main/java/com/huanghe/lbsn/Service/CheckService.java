package com.huanghe.lbsn.Service;


import com.huanghe.lbsn.Entity.Check;

import java.util.List;
import java.util.Map;

public interface CheckService {


    /**
     *根据用户的id获取用户的签到列表
     * @param userId
     * @return
     */
    List<Check> getCheckListByUserId(Integer userId);

    /**
     * 根据POI的id获取该poi访问的次数，和平均得分
     * @param poiId
     * @return
     */
    Map<String, Object> getPoiNumsAndAvgScore(Integer poiId);

    /**
     * 根据POI的id获取该poi的评论列表
     * @param poiId
     * @return
     */
    List<Map<String, Object>> getCommentListByPoiId(Integer poiId);

    /**
     * 检查当前APP登录用户是否对该poi已经签过到
     * @param userId
     * @param i
     * @return
     */
    List<Map<String, Object>> getCheckByUserIdAndPoiId(Integer userId, Integer i);

    /**
     * 在check表里插入一条数据
     * @param userId
     * @param poiId
     * @param commentContent
     * @param score
     */
    void submitCheckAndComment(Integer userId, Long poiId, String commentContent, Integer score);

    /**
     * 在check表中插入签到的用户，签到的POiId
     * @param userId
     * @param poiId
     */
    void submitCheck(Integer userId, Long poiId);

    /**
     * 根据用户id和POi的id更新评论数据
     * @param userId
     * @param poiId
     * @param commentContent
     * @param score
     */
    void UpdateRateAndComment(Integer userId, Long poiId, String commentContent, Integer score);
}
