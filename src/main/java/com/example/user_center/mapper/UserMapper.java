package com.example.user_center.mapper;

import com.example.user_center.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-09-29 12:10:25
* @Entity com.example.user_center.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




