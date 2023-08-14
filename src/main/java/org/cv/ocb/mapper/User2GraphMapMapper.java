package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.pojo.User2GraphMap;

import java.util.List;

public interface User2GraphMapMapper {
    @Select("select * from user2graph_map where user_id = #{user_id}")
    List<User2GraphMap> getGraphsByUserId(@Param("user_id") Integer userId);
}
