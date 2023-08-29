package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryData;

import java.util.List;

public interface BarGraphCategoryDataMapper {
    @Select("select * from bar_graph_category_data where bar_series_id = #{id}")
    List<BarGraphCategoryData> getDataById(@Param("id") Integer id);
}
