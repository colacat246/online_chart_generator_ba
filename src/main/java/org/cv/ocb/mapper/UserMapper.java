package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.cv.ocb.pojo.User;

import java.util.List;

public interface UserMapper {
    User getUserByName(@Param("name") String name);

    List<User> getAllUsers();
}
