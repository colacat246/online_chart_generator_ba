package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.handler.JsonHandler;
import org.cv.ocb.pojo.User2GraphMap;

import java.util.List;

public interface User2GraphMapMapper {
    @Select("select * from user2graph_map where user_id = #{user_id}")
    @Results({
            @Result(property = "data", column = "data", typeHandler = JsonHandler.class)
    })
    List<User2GraphMap> getGraphsByUserId(@Param("user_id") Integer userId);

    @Select("select `user_id` from user2graph_map where created_graph_id = #{graph_id}")
    Integer getUserIdByGraphId(@Param("graph_id") Integer graphId);

    @Select("select * from user2graph_map where created_graph_id = #{graph_id}")
    @Results({
            @Result(property = "data", column = "data", typeHandler = JsonHandler.class)
    })
    User2GraphMap getGraphByGraphId(@Param("graph_id") Integer graphId);
}
