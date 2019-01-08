package com.huanghe.lbsn.Controller;

import com.huanghe.lbsn.Entity.Relations;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Service.RelationsService;
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

/**
 * @Author huanghe
 * @Date 2019/1/1 14:20
 * @Description 好友的添加和删除：加2条，删1条：每次添加好友加2条记录，任意1方删除好友，删除两条
 */
@RestController
@RequestMapping("/api/relation")
public class RelationController {

    @Autowired
    private RelationsService relationService;

    @Autowired
    private UserService userService;

    /**
     * 添加好友关系
     * @param request
     * @param phone
     * @param token
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addFriend(HttpServletRequest request,
                        @RequestParam("mobile") String phone,
                        @RequestParam(value = "token") String token){
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
        List<User> friend = userService.findByPhone(phone);
        if (friend.size() ==0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("您添加的用户不存在！");
            return responseMessage;
        }
        List<Relations> relations = relationService.findRelationByIds(users.get(0).getUserid(), friend.get(0).getUserid());
        if (relations.size()!=0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("您已经添加过了该用户！");
            return responseMessage;
        }
        relationService.addFriendsById(users.get(0).getUserid(), friend.get(0).getUserid());
        responseMessage.setStatus(1);
        responseMessage.setMsg("添加好友成功");
        return responseMessage;
    }

    /**
     *删除好友关系
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteFriend(HttpServletRequest request,
                            @RequestParam("mobile") String phone,
                            @RequestParam(value = "token") String token){
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        List<User> users1 = userService.getUserByToken(token);
        if (users1.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        List<User> users = userService.findByPhone(phone);
        if (users.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("您的好友列表中无此好友");
            return responseMessage;
        }
        Integer friendId = users.get(0).getUserid();
        Integer userId = users1.get(0).getUserid();
        relationService.deleteFriends(userId,friendId);
        responseMessage.setStatus(1);
        responseMessage.setMsg("删除好友成功");
        return responseMessage;
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Object getFriendList(HttpServletRequest request,
                                @RequestParam(value = "token") String token){
        BaseResponse<User> responseMessage = new BaseResponse<>();
        responseMessage.setTime(System.currentTimeMillis());
        responseMessage.setTime(System.currentTimeMillis());
        if (token == null) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("请先登录！");
            return responseMessage;
        }
        List<User> users1 = userService.getUserByToken(token);
        if (users1.size() == 0) {
            responseMessage.setStatus(0);
            responseMessage.setMsg("token非法");
            return responseMessage;
        }
        Integer userId = users1.get(0).getUserid();
        List<User> users = relationService.getFriendsList(userId);
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (User user_temp : users) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("mobile", user_temp.getMobile());
            map1.put("name", user_temp.getName());
            list.add(map1);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("friendsList", list);
        responseMessage.setStatus(1);
        responseMessage.setMsg("查询好友列表成功");
        responseMessage.setObjectbean(map);
        return responseMessage;
    }



}


