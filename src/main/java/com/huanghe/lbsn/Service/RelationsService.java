package com.huanghe.lbsn.Service;

import com.huanghe.lbsn.Entity.Relations;
import com.huanghe.lbsn.Entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author huanghe
 * @Date 2019/1/1 14:19
 * @Description 好友关系的操作
 */
public interface RelationsService {

    /**
     * 添加好友关系
     * @param userId
     * @param friendId
     */
    void addFriendsById(Integer userId, Integer friendId);

    /**
     * 删除好友
     * @param friendId
     */
    void deleteFriends(Integer userId,Integer friendId);

    /**
     * 根据userId 和 friendId查询好友关系
     * @param userid
     * @param friendId
     * @return
     */
    List<Relations> findRelationByIds(Integer userid, Integer friendId);

    /**
     * 查找每一个用户的好友关系集合
     * @return
     */
    List<Map<String,Object>> findAllRelations();

    /**
     * 获取好友列表
     * @param userId
     * @return
     */
    List<User> getFriendsList(Integer userId);
}
