package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.PoiService;
import com.huanghe.lbsn.Service.RecommandService;
import com.huanghe.lbsn.Service.UserService;
import com.huanghe.lbsn.utils.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public static final String ADDRESS = "http://47.107.95.148:8081";

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
        System.out.println(token);
        //如果Token不为空
        if (StringUtils.isNotEmpty(token) && !"null".equals(token)) {
            List<User> users = userService.getUserByToken(token);
            if (users.size() == 0) {
                responseMessage.setStatus(0);
                responseMessage.setMsg("token非法");
                return responseMessage;
            }
                responseMessage.setStatus(1);
                responseMessage.setMsg("获取成功！");
                List<Poi> pois = recommandService.recommand(request, token, latitude, longitude);
                List<Map> lists = new ArrayList<>();
                Integer ranking = 1;
                for (Poi poi : pois) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("longitude", poi.getLongitude());
                    map.put("poiId", poi.getPoiId());
                    map.put("address", poi.getAddress());
                    map.put("cityName", poi.getCityName());
                    map.put("latitude", poi.getLatitude());
                    map.put("category", poi.getCategory());
                    map.put("tinyPhoto", ADDRESS + poi.getTinyPhoto());
                    map.put("ranking", ranking++);
                    lists.add(map);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("poiList", lists);
                responseMessage.setObjectbean(map);
                return responseMessage;
            }
            Integer ranking = 1;
            responseMessage.setStatus(1);
            responseMessage.setMsg("获取成功！");
            List<Poi> pois = recommandService.recommand(request, token, latitude, longitude);
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
                map.put("ranking", ranking++);
                lists.add(map);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("poiList", lists);
            responseMessage.setObjectbean(map);
            return responseMessage;
    }


}
