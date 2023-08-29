package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.handler.JsonHandler;
import org.cv.ocb.pojo.GraphTemplate;

public interface GraphTemplateMapper {
    @Select("select * from graph_template where graph_type_id = #{id}")
    @Results({
            @Result(property = "data", column = "data", typeHandler = JsonHandler.class)
    })
    GraphTemplate getTemplateByGraphTypeId(@Param("id") Integer id);
}
