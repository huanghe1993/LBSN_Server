package com.huanghe.lbsn.Mapper;

import com.huanghe.lbsn.Entity.Relations;
import com.huanghe.lbsn.Entity.RelationsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RelationsMapper {
    int countByExample(RelationsExample example);

    int deleteByExample(RelationsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Relations record);

    int insertSelective(Relations record);

    List<Relations> selectByExample(RelationsExample example);

    Relations selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Relations record, @Param("example") RelationsExample example);

    int updateByExample(@Param("record") Relations record, @Param("example") RelationsExample example);

    int updateByPrimaryKeySelective(Relations record);

    int updateByPrimaryKey(Relations record);

    List<Map<String,Object>> findAllRelations();
}