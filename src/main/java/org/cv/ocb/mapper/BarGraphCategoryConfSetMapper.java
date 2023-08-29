package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.handler.JsonHandler;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryConfSet;

import java.util.List;

public interface BarGraphCategoryConfSetMapper {
    @Select("select * from bar_graph_category_conf_set where created_graph_id = #{id}")
    @Results({
            @Result(property = "label", column = "label", typeHandler = JsonHandler.class)
    })
    List<BarGraphCategoryConfSet> getBarGraphCategoryConfSetById(@Param("id") Integer id);
}
