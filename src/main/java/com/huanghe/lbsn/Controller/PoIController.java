package com.huanghe.lbsn.Controller;

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

/**
 * @Author huanghe
 * @Date 2019/1/3 11:50
 * @Description
 */
@RestController
@RequestMapping("/api/Poi")
public class PoIController {
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



        return responseMessage;

    }
}
