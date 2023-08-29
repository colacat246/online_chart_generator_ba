package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.handler.JsonHandler;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryConfig;

public interface BarGraphCategoryConfigMapper {
    @Select("select * from bar_graph_category_config where created_graph_id = #{id}")
    @Results({
            @Result(property = "title", column = "title", typeHandler = JsonHandler.class)
    })
    BarGraphCategoryConfig getConfigById(@Param("id") Integer id);
}
