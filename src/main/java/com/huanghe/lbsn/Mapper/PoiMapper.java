package com.huanghe.lbsn.Mapper;

import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Entity.PoiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoiMapper {
    int countByExample(PoiExample example);

    int deleteByExample(PoiExample example);

    int deleteByPrimaryKey(Integer poiId);

    int insert(Poi record);

    int insertSelective(Poi record);

    List<Poi> selectByExample(PoiExample example);

    Poi selectByPrimaryKey(Integer poiId);

    int updateByExampleSelective(@Param("record") Poi record, @Param("example") PoiExample example);

    int updateByExample(@Param("record") Poi record, @Param("example") PoiExample example);

    int updateByPrimaryKeySelective(Poi record);

    int updateByPrimaryKey(Poi record);
}