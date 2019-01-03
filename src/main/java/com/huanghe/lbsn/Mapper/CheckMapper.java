package com.huanghe.lbsn.Mapper;

import com.huanghe.lbsn.Entity.Check;
import com.huanghe.lbsn.Entity.CheckExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CheckMapper {
    int countByExample(CheckExample example);

    int deleteByExample(CheckExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Check record);

    int insertSelective(Check record);

    List<Check> selectByExample(CheckExample example);

    Check selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Check record, @Param("example") CheckExample example);

    int updateByExample(@Param("record") Check record, @Param("example") CheckExample example);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    List<Map<Integer, Object>> getPoiAvgScore();

    List<Check> selectCheckByUserId(Integer userId);

    List<Map<String, Object>> getCheckIn();

    List<Map<String, Object>> getUserCheckCount();

    Map<String, Object> getPoiNumsAndAvgScore(Integer poiId);

    List<Map<String, Object>> getCommentListByPoiId(Integer poiId);

    List<Map<String,Object>> getCheckByUserIdAndPoiId(Map map);

    void insertCheck(Check check);
}