package com.huanghe.lbsn.Service.impl;

import com.huanghe.lbsn.Entity.Relations;
import com.huanghe.lbsn.Entity.RelationsExample;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Mapper.RelationsMapper;
import com.huanghe.lbsn.Mapper.UserMapper;
import com.huanghe.lbsn.Service.RelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author huanghe
 * @Date 2019/1/1 14:19
 * @Description 操作好友关系的数据
 */
@Service
public class RelationsServiceImpl implements RelationsService {

    @Autowired
    private RelationsMapper relationsMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据好友id和用户id查找用户之间的关系是不是存在的
     *
     * @param userid
     * @param friendId
     * @return
     */
    @Override
    public List<Relations> findRelationByIds(Integer userid, Integer friendId) {
        RelationsExample example = new RelationsExample();
        example.createCriteria().andFriendIdEqualTo(friendId).andUserIdEqualTo(userid);
        List<Relations> relations = relationsMapper.selectByExample(example);
        return relations;
    }

    /**
     * 查找每一个用户的好友关系列表
     * @return
     */
    @Override
    public List<Map<String, Object>> findAllRelations() {
        List<Map<String, Object>> allRelations = relationsMapper.findAllRelations();
        return allRelations;
    }

    @Override
    public List<User> getFriendsList(Integer userId) {
        List<User> friends = new ArrayList<>();
        List<String> friendsIds = new ArrayList<>();
        List<Map<String, Object>> allRelations = relationsMapper.findAllRelations();
        for (Map<String, Object> map : allRelations) {
            Integer id = (Integer) map.get("user_id");
            if (userId.equals(id)) {
                friendsIds = Arrays.asList(map.get("friend_set").toString().split(","));
            }
        }
        //通过friendsIds获取user
        for (String id : friendsIds) {
            User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
            friends.add(user);
        }
        return friends;
    }

    /**
     * 添加好友关系:需要开启事务
     *
     * @param userId
     * @param friendId
     */
    @Override
    @Transactional
    public void addFriendsById(Integer userId, Integer friendId) {
        Relations relations1 = new Relations();
        relations1.setUserId(userId);
        relations1.setFriendId(friendId);
        Relations relations2 = new Relations();
        relations2.setUserId(friendId);
        relations2.setFriendId(userId);
        relationsMapper.insert(relations1);
        relationsMapper.insert(relations2);
    }

    /**
     * 根据 用户的id删除记录
     *
     * @param friendId
     */
    @Override
    @Transactional
    public void deleteFriends(Integer userId, Integer friendId) {
        RelationsExample example1 = new RelationsExample();
        RelationsExample example2 = new RelationsExample();
        example1.createCriteria().andFriendIdEqualTo(friendId).andUserIdEqualTo(userId);
        example2.createCriteria().andFriendIdEqualTo(userId).andUserIdEqualTo(friendId);
        relationsMapper.deleteByExample(example1);
        relationsMapper.deleteByExample(example2);
    }


}
