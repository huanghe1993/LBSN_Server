package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.UserService;
import com.huanghe.lbsn.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author huanghe
 * @Date 2018/12/27 15:11
 * @Description 登录注册功能
 */
@RestController
@RequestMapping("/api/user")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param phone
     * @param password
     * @param username
     * @param repwd
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestParam("mobile") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("name") String username,
                           @RequestParam("repwd") String repwd){

        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());

        if (password == null || "".equals(password.trim())) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("密码不能为空！");
            return  responseMessage;
        }

        if (username == null || "".equals(username.trim())) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("用户名不能为空！");
            return  responseMessage;
        }

        if (repwd == null || "".equals(repwd.trim())) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请输入确认密码！");
            return  responseMessage;
        }

        if (!password.equals(repwd.trim())) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("两次密码输入不一致！");
            return  responseMessage;
        }

        if (userService.findByUsername(username).size() != 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("用户名已经被占用！");
            return  responseMessage;
        }

        if (userService.findByPhone(phone).size()!= 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("手机号已经存在！");
            return  responseMessage;
        }

        User user = new User();
        user.setUsercreatetime(new Date(System.currentTimeMillis()));
        user.setMobile(phone);
        user.setPassword(password);
        user.setName(username);
        userService.insertUser(user);

        responseMessage.setStatus(1);
        responseMessage.setMsg("注册成功!");
        responseMessage.setObjectbean(user);

        return responseMessage;
    }

    /**
     * 用户登录
     * @param request
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request,
                        @RequestParam("mobile") String phone,
                        @RequestParam("password") String password){
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if(phone == null||password==null ){
            responseMessage.setStatus(0);
            responseMessage.setMsg("用户名或者密码不能为空");
            return  responseMessage;
        }
        List<User> user = userService.findByPhoneAndPassword(phone, password);
        if (user.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("用户名或者是密码错误");
            return responseMessage;
        }
        String tokenId = TokenProccessor.getInstance().makeToken();
        //直接将Token保存在数据库
        userService.insertToken(user.get(0).getUserid(), tokenId);
        //测试使用
        logger.info("推荐时候获取到的token:"+tokenId);

        Map<String, Object> map = new HashMap<>();
        map.put("name", user.get(0).getName());
        map.put("userId", user.get(0).getUserid());
        map.put("mobile", user.get(0).getMobile());
        map.put("password", user.get(0).getPassword());
        map.put("token", tokenId);

        responseMessage.setStatus(1);
        responseMessage.setObjectbean(map);
        responseMessage.setMsg("登录成功");
        responseMessage.setTime(System.currentTimeMillis());
        return responseMessage;
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping(value="/logout")
    public BaseResponse<?>  logout(HttpServletRequest request,@RequestParam("token") String tokenServerkey) {
        BaseResponse<String> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (tokenServerkey == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        List<User> users = userService.getUserByToken(tokenServerkey);
        if (users.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }

        userService.insertToken(users.get(0).getUserid(), "");
        responseMessage.setStatus(1);
        responseMessage.setMsg("退出成功");

        return responseMessage;
    }
}


