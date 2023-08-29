package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;

public interface UserGraphService {

    Result getGraphById(Integer graphTypeId, Integer id);
}
