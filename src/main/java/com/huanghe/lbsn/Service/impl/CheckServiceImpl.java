package com.huanghe.lbsn.Service.impl;

import com.huanghe.lbsn.Entity.Check;
import com.huanghe.lbsn.Entity.CheckExample;
import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Mapper.CheckMapper;
import com.huanghe.lbsn.Mapper.PoiMapper;
import com.huanghe.lbsn.Service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author huanghe
 * @Date 2019/1/3 11:18
 * @Description
 */
@Service
public class CheckServiceImpl implements CheckService {


    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private PoiMapper poiMapper;


    /**
     * 根据用户的id获取用户的签到列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Check> getCheckListByUserId(Integer userId) {
        List<Check> checks = checkMapper.selectCheckByUserId(userId);
        return checks;
    }

    /**
     * 获取此POI的被访问的次数和平均得分
     * @param poiId
     * @return
     */
    @Override
    public Map<String, Object> getPoiNumsAndAvgScore(Integer poiId) {
        Map<String, Object> poiNumsAndAvgScore = checkMapper.getPoiNumsAndAvgScore(poiId);
        return poiNumsAndAvgScore;
    }

    /**
     * 根据POI的id获取该poi的评论列表
     * @param poiId
     * @return
     */
    @Override
    public List<Map<String, Object>> getCommentListByPoiId(Integer poiId) {
        List<Map<String, Object>> commentList = new ArrayList<>();
        commentList = checkMapper.getCommentListByPoiId(poiId);
        return commentList;
    }

    /**
     * 检查当前APP登录用户是否对该poi已经签过到
     *
     * @param userId
     * @param poiId
     * @return
     */
    @Override
    public List<Map<String, Object>> getCheckByUserIdAndPoiId(Integer userId, Integer poiId) {
        Map<String,Integer> paramMap = new HashMap();
        paramMap.put("userId", userId);
        paramMap.put("poiId", poiId);
        List<Map<String, Object>> checks = checkMapper.getCheckByUserIdAndPoiId(paramMap);
        return checks;
    }

    /**
     * 往check表里面插入一条数据
     * @param userId
     * @param poiId
     * @param commentContent
     * @param score
     */
    @Override
    public void submitCheckAndComment(Integer userId, Long poiId, String commentContent, Integer score) {
        Check check = new Check();
        check.setContent(commentContent);
        check.setCreatetime(new Date());
        check.setPoiId(poiId.intValue());
        check.setUserId(userId);
        check.setRate(score);
        Poi poi = poiMapper.selectByPrimaryKey(poiId.intValue());
        check.setLatitude(poi.getLatitude());
        check.setLongitude(poi.getLongitude());
        check.setPoiName(poi.getAddress());
        checkMapper.insertCheck(check);

    }


}
