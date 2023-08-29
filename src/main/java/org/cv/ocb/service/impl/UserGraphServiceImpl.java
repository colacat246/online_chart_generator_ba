package org.cv.ocb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.cv.ocb.mapper.BarGraphCategoryConfSetMapper;
import org.cv.ocb.mapper.BarGraphCategoryConfigMapper;
import org.cv.ocb.mapper.BarGraphCategoryDataMapper;
import org.cv.ocb.mapper.User2GraphMapMapper;
import org.cv.ocb.meta.GraphMeta;
import org.cv.ocb.meta.StatusCode;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryConfSet;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryConfig;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryData;
import org.cv.ocb.service.UserGraphService;
import org.cv.ocb.utils.UserThreadLocal;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.GraphExtraVo;
import org.cv.ocb.vo.response.Result;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGraphServiceImpl implements UserGraphService {
    private User2GraphMapMapper user2GraphMapMapper;
    private BarGraphCategoryConfSetMapper barGraphCategoryConfSetMapper;
    private BarGraphCategoryConfigMapper barGraphCategoryConfigMapper;
    private BarGraphCategoryDataMapper barGraphCategoryDataMapper;


    public UserGraphServiceImpl(User2GraphMapMapper user2GraphMapMapper, BarGraphCategoryConfSetMapper barGraphCategoryConfSetMapper, BarGraphCategoryConfigMapper barGraphCategoryConfigMapper, BarGraphCategoryDataMapper barGraphCategoryDataMapper) {
        this.user2GraphMapMapper = user2GraphMapMapper;
        this.barGraphCategoryConfSetMapper = barGraphCategoryConfSetMapper;
        this.barGraphCategoryConfigMapper = barGraphCategoryConfigMapper;
        this.barGraphCategoryDataMapper = barGraphCategoryDataMapper;
    }

    @Override
    public Result getGraphById(Integer graphTypeId, Integer id) {
        if (graphTypeId == null || id == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        UserVo userVo = UserThreadLocal.get();
        GraphMeta graphType = GraphMeta.values()[graphTypeId - 1];
        // 验证图是否属于用户
        Integer userId = user2GraphMapMapper.getUserIdByGraphId(id);
        if (userId != userVo.getUserId()) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        Object data;
        switch (graphType) {
            case LINE_GRAPH:
                data = getLineGraphById(id);
                if (data == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
                return Result.ok(data);
            case BAR_GRAPH_CATEGORY:
                try {
                    data = getBarGraphCategoryById(id);
                    if (data == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
                    return Result.ok(data);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            default:
                return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        }
    }

    private Map<String, Object> getLineGraphById(Integer id) {
        User2GraphMap graphData = user2GraphMapMapper.getGraphByGraphId(id);
        if (graphData == null) return null;
        return graphData.getData();


    }

    // TODO
    private Map<String, Object> getBarGraphCategoryById(Integer id) throws JsonProcessingException {
        Map<String, Object> vo = new HashMap<>();
        GraphExtraVo graphExtraVo = new GraphExtraVo();
        // 固定配置
        graphExtraVo.setGraphTypeId(2);
        vo.put("yAxis", new ObjectMapper().readValue("{\"type\": \"value\"}", HashMap.class));
        // 动态配置
        BarGraphCategoryConfig config = barGraphCategoryConfigMapper.getConfigById(id);
        if (config == null) return null;
        graphExtraVo.setId(id);
        graphExtraVo.setDivHeight(config.getDivHeight());
        graphExtraVo.setW2hRatio(config.getW2hRatio());

        vo.put("$extra", graphExtraVo);
        vo.put("animation", config.getAnimation() == 0 ? false : true);
        vo.put("title", config.getTitle());
        // 获取series
        List<BarGraphCategoryConfSet> confSets = barGraphCategoryConfSetMapper.getBarGraphCategoryConfSetById(id);

        List<Map<String, Object>> series = new ArrayList<>();
        for (BarGraphCategoryConfSet confSet : confSets) {
            Map<String, Object> m = new HashMap();
            m.put("type", "bar");

            m.put("name", confSet.getSeriesName());
            m.put("barWidth", confSet.getBarWidth());
            m.put("label", confSet.getLabel());
            m.put("color", confSet.getColor());
            // data
            List<BarGraphCategoryData> dataList = barGraphCategoryDataMapper.getDataById(confSet.getBarSeriesId());
            dataList.sort(Comparator.comparingInt(BarGraphCategoryData::getOrder));

            List<String> cate = dataList.stream().map(i -> i.getDataCategory()).toList();
            xAxis xAxis = new xAxis();
            // FIXME
            xAxis.setData(cate);
            vo.put("xAxis", xAxis);

            List<Float> data = dataList.stream().map(i -> i.getDataValue()).toList();
            m.put("data", data);

            Map<String, Object> $extra = new HashMap<>();
            $extra.put("id", confSet.getBarSeriesId());
            m.put("$extra", $extra);

            series.add(m);
        }
        vo.put("series", series);
        return vo;
    }

    @Data
    private class xAxis {
        private final String type = "category";
        private List<String> data;
    }
}
