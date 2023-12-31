package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.*;
import org.cv.ocb.pojo.User;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where name = #{name}")
    User getUserByName(@Param("name") String name);

    @Select("select * from user where user_id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Select("select * from user")
    List<User> getAllUsers();

    @Insert("insert into user (`name`, `password`) values (#{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    void insertUser(User user);

    @Update("update user set `password` = #{password} where `name` = #{name}")
    void updateUsePw(User user);
}
