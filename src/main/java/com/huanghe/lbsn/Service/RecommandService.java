package com.huanghe.lbsn.Service;

import com.huanghe.lbsn.Entity.Check;
import com.huanghe.lbsn.Entity.Poi;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author huanghe
 * @Date 2018/12/29 11:50
 * @Description
 */
public interface RecommandService {

    /**
     * 根据用户 纬度 经度 或者该用户可能感兴趣的热点地区
     *
     * @param token
     * @param latitude
     * @param longitude
     * @return
     */
    List<Poi> recommand(HttpServletRequest request , String token, String latitude, String longitude);

    /**
     * 根据用户的ID查询签到信息
     * @param userId
     * @return
     */
    List<Check> selectCheckByUserId(Integer userId);



}
