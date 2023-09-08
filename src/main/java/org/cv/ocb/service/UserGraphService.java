package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;

import java.util.Map;

public interface UserGraphService {
    Result getGraphById(Integer id);
    Result insertNewGraph(Integer graphTypeId, String graphName);
    Result deleteGraph(Integer createdGraphId);
    Result insertNewSeries(Integer createdGraphId, String seriesName);

    Result deleteSeriesById(Integer createdGraphId, String seriesId);

    Result updateGraph(Integer createdGraphId, Map<String, Object> data);

    Result updateGraphName(Integer createdGraphId, String graphName);
}
