package com.huanghe.lbsn.Service.impl;

import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Entity.UserExample;
import com.huanghe.lbsn.Mapper.UserMapper;
import com.huanghe.lbsn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huanghe
 * @Date 2018/12/27 15:44
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public List<User> findByPhone(String phone) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(phone);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public List<User> findByPhoneAndPassword(String phone, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(phone).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public User findByToken(String token) {
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        return users.get(0);
    }


}
