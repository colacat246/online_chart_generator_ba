package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.handler.JsonHandler;
import org.cv.ocb.pojo.SeriesTemplate;

public interface SeriesTemplateMapper {
    @Select("select * from series_template where graph_type_id = #{id}")
    @Results({
            @Result(property = "data", column = "data", typeHandler = JsonHandler.class)
    })
    SeriesTemplate getTemplateByGraphTypeId(@Param("id") Integer id);
}
