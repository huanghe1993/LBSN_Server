package com.huanghe.lbsn.Service;

import com.huanghe.lbsn.Entity.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名查找唯一的用户
     * @param username：用户名
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * 根据用户的手机号码查找唯一用户
     * @param phone
     * @return
     */
    List<User> findByPhone(String phone);

    /**
     * 添加用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据手机号和
     * @param phone
     * @param password
     */
    List<User> findByPhoneAndPassword(String phone, String password);

    /**
     * 根据token查询用户
     * @param token
     * @return
     */
    User findByToken(String token);

    /**
     * 根据用户id把token保存在数据库
     * @param userid
     * @param tokenId
     */
    void insertToken(Integer userid, String tokenId);

    /**
     * 根据用户的token获取用户
     * @param token
     * @return
     */
    List<User> getUserByToken(String token);
}
