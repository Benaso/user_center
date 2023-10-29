package com.example.user_center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user_center.common.ErrorCode;
import com.example.user_center.exception.BusinessException;
import com.example.user_center.model.User;
import com.example.user_center.service.UserService;
import com.example.user_center.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.user_center.constant.UserConstant.USER_LOGIN_SATE;

/**
* @author ASUS
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-09-29 12:10:25
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private UserMapper userMapper;
    //盐
    private static final String SALT = "benaso";

    /**
     * 用户注册
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param studentId     学生号
     * @return
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String studentId) {

        //1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, studentId)){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, "参数为空");
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号小于4位");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户密码小于8位");
        }
        if (studentId.length() != 12){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"学生号不为12位");
        };
        //学生号只为数字
        String validPattern1 = "^[0-9]+$";
        Matcher matcher1  = Pattern.compile(validPattern1).matcher(studentId);
        if (matcher1.find()){
        }else {
            throw new  BusinessException(ErrorCode.PARAMS_ERROR,"学生号只能为数字");
        }
        //账户不能包含特殊字符
        String validPattern = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            throw new  BusinessException(ErrorCode.PARAMS_ERROR,"账户不能包含特殊字符");
        }
        //密码和校验码相同
        if (!(userPassword.equals(checkPassword))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码和校验码不同");
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.USER_REPEAT_ERROR,"账户重复");
        }
        //学生号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("studentId", studentId);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.USER_REPEAT_ERROR,"学生号重复");
        }
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUsername(userAccount);
        user.setUserPassword(encryptPassword );
        user.setStudentId(studentId);
        boolean save = this.save(user);
        if (!save){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"保存错误");
        }
        return user.getId();
    }

    /**
     * 用户登录
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号密码不为空");
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度小于4");
        }
        if (userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度小于8");
        }
        //账户不能包含特殊字符
        String validPattern = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号存在特殊字符");
        }

        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        //用户不存在
        if (user == null){
            log.info("user login failed, user can not match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"用户不存在");
        }
        //用户脱敏
        User safeUser = getsafetyUser(user);

        //记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_SATE, user);
        return safeUser;
    }

    /**
     * 用户信息脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getsafetyUser(User originUser){
        if (originUser == null){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"原始用户不存在");
        }
        User safeUser = new User();
        safeUser.setId(originUser.getId());
        safeUser.setUsername(originUser.getUsername());
        safeUser.setUserAccount(originUser.getUserAccount());
        safeUser.setAvatarUrl(originUser.getAvatarUrl());
        safeUser.setGender(originUser.getGender());
        safeUser.setPhone(originUser.getPhone());
        safeUser.setEmail(originUser.getEmail());
        safeUser.setStudentId(originUser.getStudentId());
        safeUser.setUserRole(originUser.getUserRole());
        safeUser.setUserStatus(originUser.getUserStatus());
        safeUser.setCreateTime(originUser.getCreateTime());
    return safeUser;
    }

    /**
     * 移除登录态
     * @param request
     * @return
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_SATE);
        return 1;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public Integer AddUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", user.getUserAccount());
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.USER_REPEAT_ERROR,"账户重复");
        }
        int insert = userMapper.insert(user);
        return insert;
    }
}




