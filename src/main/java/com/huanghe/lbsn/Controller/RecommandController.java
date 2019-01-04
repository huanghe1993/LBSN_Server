package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.PoiService;
import com.huanghe.lbsn.Service.RecommandService;
import com.huanghe.lbsn.Service.UserService;
import com.huanghe.lbsn.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author huanghe
 * @Date 2018/12/28 21:34
 * @Description  推荐POI
 */
@RestController
@RequestMapping("/api")
public class RecommandController {

    public static final String ADDRESS = "Http://47.107.95.148:8081";

    @Autowired
    private RecommandService recommandService;

    @Autowired
    private PoiService poiService;

    @Autowired
    private UserService userService;


    /**
     * 根据用户的经纬度获取用户当前位置的热点位置
     * @param latitude
     * @param longitude
     * @param token
     * @return
     */
    @GetMapping(value ="/recommand")
    public Object getPOI(HttpServletRequest request,
                         @RequestParam("latitude") String latitude,
                         @RequestParam("longitude") String longitude,
                         @RequestParam(value = "token",required = false) String token) {

        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        //如果Token不为空
        if (token != null) {
            User user = (User)request.getSession().getAttribute(token);
            if (user == null) {
                responseMessage.setStatus(0);
                responseMessage.setMsg("token非法");
                return responseMessage;
            }
            responseMessage.setStatus(1);
            responseMessage.setMsg("获取成功！");
            List<Poi> pois =  recommandService.recommand(request,token,latitude,longitude);
            Map<String, Object> map = new HashMap<>();
            map.put("poiList", pois);
            responseMessage.setObjectbean(map);
            return responseMessage;
        }
        responseMessage.setStatus(1);
        responseMessage.setMsg("获取成功！");
        List<Poi> pois =  recommandService.recommand(request,token,latitude,longitude);
        List<Map> lists = new ArrayList<>();
        for (Poi poi : pois) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("poiId", poi.getPoiId());
            map.put("address", poi.getAddress());
            map.put("cityName", poi.getCityName());
            map.put("latitude", poi.getLatitude());
            map.put("longitude", poi.getLongitude());
            map.put("category", poi.getCategory());
            map.put("tinyPhoto", ADDRESS + poi.getTinyPhoto());
            lists.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("poiList", lists);
        responseMessage.setObjectbean(map);
        return responseMessage;
    }


}
