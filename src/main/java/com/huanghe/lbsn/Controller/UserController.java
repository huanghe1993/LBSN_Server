package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.UserService;
import com.huanghe.lbsn.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Author huanghe
 * @Date 2019/1/1 21:13
 * @Description 用户资料相关的接口
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public Object getFriendList(HttpServletRequest request,
                                @RequestParam(value = "token") String token,
                                @RequestParam(value = "mobile") String phone){
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        User user = (User)request.getSession().getAttribute(token);
        if (user == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        List<User> users = userService.findByPhone(phone);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", users.get(0).getName());
        responseMessage.setObjectbean(map);
        responseMessage.setMsg("成功获取用户资料!");
        responseMessage.setStatus(1);
        return responseMessage;

    }


}
