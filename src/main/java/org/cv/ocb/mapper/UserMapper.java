package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.pojo.User;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where name = #{name}")
    User getUserByName(@Param("name") String name);

    @Select("select * from user where user_id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Select("select * from user")
    List<User> getAllUsers();
}
