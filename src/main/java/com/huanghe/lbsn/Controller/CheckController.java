package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.Check;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.CheckService;
import com.huanghe.lbsn.Service.PoiService;
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

/**
 * @Author huanghe
 * @Date 2019/1/3 11:16
 * @Description 获取签到列表
 */
@RestController
@RequestMapping("/api/check")
public class CheckController {

    public static final String ADDRESS = "Http://127.0.0.1:8081";

    @Autowired
    private CheckService checkService;

    @Autowired
    private PoiService poiService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object addFriend(HttpServletRequest request,
                            @RequestParam(value = "token") String token) {
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        User user = (User) request.getSession().getAttribute(token);
        if (user == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        Integer userId = user.getUserid();
        List<Check> checks = checkService.getCheckListByUserId(userId);
        List<HashMap<String, Object>> lists = new ArrayList<>();
        for (Check check : checks) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("signedId", check.getId());
            map.put("poiId", check.getPoiId());
            map.put("rate", check.getRate());
            map.put("address", poiService.getPoiById(check.getPoiId()).getAddress());
            map.put("cityName", poiService.getPoiById(check.getPoiId()).getCityName());
            map.put("category", poiService.getPoiById(check.getPoiId()).getCategory());
            map.put("tinyPhoto", ADDRESS+poiService.getPoiById(check.getPoiId()).getTinyPhoto());
            lists.add(map);
        }
        responseMessage.setStatus(1);
        responseMessage.setMsg("获取成功");
        responseMessage.setObjectbean(lists);

        return responseMessage;
    }


}
