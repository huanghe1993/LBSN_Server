package com.huanghe.lbsn.Service.impl;

import com.huanghe.lbsn.Entity.Check;
import com.huanghe.lbsn.Mapper.CheckMapper;
import com.huanghe.lbsn.Service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huanghe
 * @Date 2019/1/3 11:18
 * @Description
 */
@Service
public class CheckServiceImpl implements CheckService {


    @Autowired
    private CheckMapper checkMapper;


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


}
