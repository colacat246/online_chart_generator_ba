package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;
import org.cv.ocb.vo.response.UserGraphListVo;

import java.util.List;

public interface UserGraphListService {
    Result getUserGraphListById(Integer id);

    List<UserGraphListVo> getUserGraphListVos(Integer id);
}
