package org.cv.ocb.mapper;

import org.apache.ibatis.annotations.Param;
import org.cv.ocb.pojo.User2GraphMap;

import java.util.List;

public interface User2GraphMapMapper {
    List<User2GraphMap> getGraphsByUserId(@Param("user_id") Integer userId);
}
