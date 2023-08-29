package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;

public interface UserGraphService {
    Result insertNewGraph(Integer graphTypeId, String graphName);

    Result getGraphById(Integer id);
}
