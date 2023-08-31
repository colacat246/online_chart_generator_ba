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

    // 拿到模板并设置名称和uuid
    @Select("select json_replace((select `data` from `series_template` where graph_type_id = #{id}), '$.name', #{new_name}, '$.$extra.id', #{uuid})")
    String getTemplateDataAndSetName(@Param("id") Integer id, @Param("new_name") String newName, @Param("uuid") String uuid);
}
