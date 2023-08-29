package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.*;
import org.cv.ocb.handler.JsonHandler;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.pojo.User2GraphMapForInsert;

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

    @Insert("insert into user2graph_map (user_id, graph_type_id, graph_name, data) values (#{userId}, #{graphTypeId}, #{graphName}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "createdGraphId", keyColumn = "created_graph_id")
    void insertNewGraph(User2GraphMapForInsert user2GraphMap);
}
