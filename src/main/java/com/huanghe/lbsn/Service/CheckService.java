package com.huanghe.lbsn.Service;


import com.huanghe.lbsn.Entity.Check;

import java.util.List;

public interface CheckService {


    /**
     *根据用户的id获取用户的签到列表
     * @param userId
     * @return
     */
    List<Check> getCheckListByUserId(Integer userId);
}
