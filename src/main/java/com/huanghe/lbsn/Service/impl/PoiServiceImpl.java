package com.huanghe.lbsn.Service.impl;

import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Mapper.PoiMapper;
import com.huanghe.lbsn.Service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author huanghe
 * @Date 2018/12/29 20:06
 * @Description
 */
@Service
public class PoiServiceImpl implements PoiService {

    @Autowired
    private PoiMapper poiMapper;

    @Override
    public Poi getPoiById(Integer id) {
        Poi poi = poiMapper.selectByPrimaryKey(id);
        return poi;
    }
}
