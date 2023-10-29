package com.example.user_center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user_center.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-09-29 12:10:25
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String studentId);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取脱敏用户
     * @param originUser
     * @return
     */
    User getsafetyUser(User originUser);

    /**
     * 用户注销
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 增加用户
     * @param user
     * @return
     */
    Integer AddUser(User user);
}