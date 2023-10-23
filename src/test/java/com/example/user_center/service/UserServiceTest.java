package com.example.user_center.service;

import com.example.user_center.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * author: Victor;
 * version: 1.0
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired UserService userService;

//    @Test
//    public void testAddUser(){
//        User user = new User();
//        user.setUsername("pppig");
//        user.setUserAccount("");
//        user.setAvatarUrl("");
//        user.setGender(0);
////        user.setUserPassword("111");
//        user.setPhone("314");
//        user.setEmail("3241@gmeil.com");
//        boolean save = userService.save(user);
//        System.out.println(user.getId());
//        Assertions.assertTrue(save);

//    }

    @Test
    void userRegister() {

//        String userAccount = "yupi1";
//        String userPassword = "";
//        String checkPassword = "123456";
//        String studentId = "202118020121";
//        long result = userService.userRegister(userAccount,userPassword,checkPassword,studentId);
//        Assertions.assertEquals(-1, result);
//
//        String userAccount1 = "benaso";
//        String userPassword1 = "12345678";
//        String checkPassword1 = "12345678";
//        String studentId1 = "202118020120";
//        long result1 = userService.userRegister(userAccount1,userPassword1,checkPassword1,studentId1);
//        System.out.println("返回结果是" + result1);
//        Assertions.assertEquals(-1, result1);
    }
}