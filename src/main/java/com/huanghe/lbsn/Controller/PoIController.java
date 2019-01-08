package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.CheckService;
import com.huanghe.lbsn.Service.PoiService;
import com.huanghe.lbsn.Service.UserService;
import com.huanghe.lbsn.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author huanghe
 * @Date 2019/1/3 11:50
 * @Description
 */
@RestController
@RequestMapping("/api/poi")
public class PoIController {

    public static final String ADDRESS = "http://47.107.95.148:8081";
    @Autowired
    private CheckService checkService;

    @Autowired
    private PoiService poiService;

    @Autowired
    private UserService userService;

    /**
     * 热点详情页的数据
     * @param request
     * @param poiId
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getPoiDetail(HttpServletRequest request,
                            @RequestParam(value = "poiId") Long poiId) {
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        Poi poi = poiService.getPoiById(poiId.intValue());
        if (poi == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("没有此热点！");
            return responseMessage;
        }

        //图片展示栏
        List<HashMap<String, Object>> bannerMap = new ArrayList<>();
        String[] urls = poi.getPhoto().split(",");
        for (int i = 0; i<urls.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bannerId", i);
            map.put("imageUrl",ADDRESS+urls[i]);
            bannerMap.add(map);
        }
        HashMap<String, Object> introductionMap = new HashMap<>();
        introductionMap.put("describe", poi.getPoiDescribe());
        introductionMap.put("address", poi.getAddress());
        introductionMap.put("commentNums", checkService.getPoiNumsAndAvgScore(poiId.intValue()).get("commentNums"));
        introductionMap.put("averageScore", checkService.getPoiNumsAndAvgScore(poiId.intValue()).get("averageScore"));
        //评论列表
        List<Map<String, Object>> commentList = checkService.getCommentListByPoiId(poiId.intValue());

        HashMap map = new HashMap<>();
        map.put("banner", bannerMap);
        map.put("introduction", introductionMap);
        map.put("commenList", commentList);
        responseMessage.setMsg("获取成功!");
        responseMessage.setStatus(1);
        responseMessage.setObjectbean(map);

        return responseMessage;
    }

    /**
     * 检查用户对Poi热点是否已签到
     * @param request
     * @param poiId
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Object checkIsSigned(HttpServletRequest request,
                            @RequestParam(value = "token") String token,
                            @RequestParam(value = "poiId") Long poiId) {
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        List<User> users = userService.getUserByToken(token);
        if (users.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        Integer userId = users.get(0).getUserid();
        List<Map<String, Object>> checks = checkService.getCheckByUserIdAndPoiId(userId, poiId.intValue());
        if (checks.size() == 0) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("isSigned", 0);
            responseMessage.setStatus(1);
            return responseMessage;
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("isSigned", 1);
        responseMessage.setObjectbean(map);
        responseMessage.setStatus(1);
        return responseMessage;
    }

    /**
     * 签到POI
     * @param token
     * @param poiId
     * @return
     */
    @RequestMapping(value = "/submitCheck", method = RequestMethod.POST)
    public Object checkSubmit(@RequestParam(value = "token") String token,
                              @RequestParam(value = "poiId") Long poiId){

        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        List<User> users = userService.getUserByToken(token);
        if (users.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        Integer userId = users.get(0).getUserid();
        checkService.submitCheck(userId,poiId);
        responseMessage.setStatus(1);
        responseMessage.setMsg("提交成功");
        return responseMessage;
    }




    /**
     * 和POI评论
     * @param request
     * @param token
     * @param poiId
     * @param commentContent
     * @param score
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Object checkSubmit(HttpServletRequest request,
                                @RequestParam(value = "token") String token,
                                @RequestParam(value = "poiId") Long poiId,
                              @RequestParam(value = "commentContent") String commentContent,
                              @RequestParam(value = "score") Integer score) {
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        List<User> users = userService.getUserByToken(token);
        if (users.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        Integer userId = users.get(0).getUserid();
        //判断是否签到
        List<Map<String, Object>> checks = checkService.getCheckByUserIdAndPoiId(userId, poiId.intValue());
        if (checks.size() == 0) {
            responseMessage.setMsg("您还没有签到");
            responseMessage.setStatus(0);
            return responseMessage;
        }
        checkService.UpdateRateAndComment(userId,poiId,commentContent,score);
        responseMessage.setStatus(1);
        responseMessage.setMsg("提交成功");
        return responseMessage;
    }





}
