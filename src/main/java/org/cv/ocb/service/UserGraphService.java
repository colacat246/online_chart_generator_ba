package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;

public interface UserGraphService {
    Result getGraphById(Integer id);
    Result insertNewGraph(Integer graphTypeId, String graphName);
    Result deleteGraph(Integer createdGraphId);
    Result insertNewSeries(Integer createdGraphId, String seriesName);
}
