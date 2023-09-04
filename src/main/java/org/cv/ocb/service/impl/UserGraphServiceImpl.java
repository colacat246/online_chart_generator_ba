package org.cv.ocb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cv.ocb.mapper.GraphTemplateMapper;
import org.cv.ocb.mapper.SeriesTemplateMapper;
import org.cv.ocb.mapper.User2GraphMapMapper;
import org.cv.ocb.meta.StatusCode;
import org.cv.ocb.pojo.GraphTemplate;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.pojo.User2GraphMapForInsert;
import org.cv.ocb.service.UserGraphListService;
import org.cv.ocb.service.UserGraphService;
import org.cv.ocb.utils.StringUtils;
import org.cv.ocb.utils.UserThreadLocal;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.cv.ocb.vo.response.UserGraphListVo;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserGraphServiceImpl implements UserGraphService {
    private User2GraphMapMapper user2GraphMapMapper;
    private GraphTemplateMapper graphTemplateMapper;
    private UserGraphListService userGraphListService;
    private SeriesTemplateMapper seriesTemplateMapper;
    private ObjectMapper objectMapper;

    public UserGraphServiceImpl(User2GraphMapMapper user2GraphMapMapper, GraphTemplateMapper graphTemplateMapper, UserGraphListService userGraphListService, SeriesTemplateMapper seriesTemplateMapper, ObjectMapper objectMapper) {
        this.user2GraphMapMapper = user2GraphMapMapper;
        this.graphTemplateMapper = graphTemplateMapper;
        this.userGraphListService = userGraphListService;
        this.seriesTemplateMapper = seriesTemplateMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public Result insertNewGraph(Integer graphTypeId, String graphName) {
        if (graphTypeId == null || StringUtils.isEmpty(graphName)) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        UserVo userVo = UserThreadLocal.get();
        // 拿到模板
        GraphTemplate template = graphTemplateMapper.getTemplateByGraphTypeId(graphTypeId);
        if (template == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        User2GraphMapForInsert user2GraphMap = new User2GraphMapForInsert();
        user2GraphMap.setUserId(userVo.getUserId());
        user2GraphMap.setGraphName(graphName);
        user2GraphMap.setGraphTypeId(graphTypeId);
        try {
            user2GraphMap.setData(new ObjectMapper().writeValueAsString(template.getData()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        user2GraphMapMapper.insertNewGraph(user2GraphMap);
        Integer newGraphId = user2GraphMap.getCreatedGraphId();

        List<UserGraphListVo> userGraphListVos = userGraphListService.getUserGraphListVos(userVo.getUserId());

        Map<String, Object> map = new HashMap<>();
        map.put("newGraphId", newGraphId);
        map.put("graphList", userGraphListVos);

        return Result.ok(map);
    }

    @Override
    public Result deleteGraph(Integer id) {
        UserVo userVo = UserThreadLocal.get();
        // TODO 使用AOP增强此功能验证图是否属于用户
        Integer userId = user2GraphMapMapper.getUserIdByGraphId(id);
        if (userId == null || userId != userVo.getUserId()) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        Integer row = user2GraphMapMapper.deleteGraph(id);
        List<UserGraphListVo> userGraphListVos = userGraphListService.getUserGraphListVos(userId);
        Integer curGraphId = null;
        // 找到被删除图前一张图的ID
        switch (userGraphListVos.size()) {
            case 0:
                break;
            case 1:
                curGraphId = userGraphListVos.get(0).getCreatedGraphId();
                break;
            default:
                List<Integer> ids = userGraphListVos.stream().map(UserGraphListVo::getCreatedGraphId).toList();
                for (int i = 0; i < ids.size() - 1; i++) {
                    if (ids.get(i) > id) {
                        curGraphId = ids.get(i);
                        break;
                    } else if (ids.get(i) < id && ids.get(i + 1) > id) {
                        curGraphId = ids.get(i);
                        break;
                    } else {
                        curGraphId = ids.get(i + 1);
                    }
                }
                break;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("graphList", userGraphListVos);
        data.put("curGraphId", curGraphId);
        return Result.ok(data);
    }

    @Override
    public Result getGraphById(Integer id) {
        if (id == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        UserVo userVo = UserThreadLocal.get();
        // TODO 使用AOP增强此功能验证图是否属于用户
        Integer userId = user2GraphMapMapper.getUserIdByGraphId(id);
        if (userId == null || userId != userVo.getUserId()) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        User2GraphMap graphData = user2GraphMapMapper.getGraphByGraphId(id);
        if (graphData == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        return Result.ok(graphData.getData());
    }

    @Override
    public Result insertNewSeries(Integer createdGraphId, String seriesName) {
        if (createdGraphId == null || StringUtils.isEmpty(seriesName))
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        UserVo userVo = UserThreadLocal.get();
        // TODO 使用AOP增强此功能验证图是否属于用户
        User2GraphMap graph = user2GraphMapMapper.getGraphByGraphId(createdGraphId);
        if (graph == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        Integer userId = graph.getUserId();
        if (userId == null || userId != userVo.getUserId()) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        // 获取series模板
        Integer graphTypeId = graph.getGraphTypeId();
        String uuid = UUID.randomUUID().toString();
        String templateData = seriesTemplateMapper.getTemplateDataAndSetName(graphTypeId, seriesName, uuid);
        // 添加到graph
        Integer row = user2GraphMapMapper.addNewSeries(createdGraphId, templateData);
        // 拿到更新后的图形
        User2GraphMap updatedGraph = user2GraphMapMapper.getGraphByGraphId(createdGraphId);
        Map<String, Object> data = new HashMap<>();
        data.put("graph", updatedGraph.getData());
        data.put("newSeriesId", uuid);
        return Result.ok(data);
    }

    @Override
    public Result deleteSeriesById(Integer createdGraphId, String seriesId) {
        if (createdGraphId == null || StringUtils.isEmpty(seriesId))
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        UserVo userVo = UserThreadLocal.get();
        // TODO 使用AOP增强此功能验证图是否属于用户
        User2GraphMap graph = user2GraphMapMapper.getGraphByGraphId(createdGraphId);
        if (graph == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        Integer userId = graph.getUserId();
        if (userId == null || userId != userVo.getUserId()) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        String path = user2GraphMapMapper.getSeriesJsonPathById(createdGraphId, seriesId);
        // 找不到路径
        if (path == null)
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        // 匹配
        Pattern p = Pattern.compile("(?<=series\\[)\\d+(?=\\])");
        Matcher matcher = p.matcher(path);
        boolean isFound = matcher.find();
        if (!isFound)
            // uuid出现问题
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        String idx = matcher.group(0);
        Integer row = user2GraphMapMapper.deleteSeriesByIndex(createdGraphId, idx);

        User2GraphMap updatedGraph = user2GraphMapMapper.getGraphByGraphId(createdGraphId);

        // 获得前一个series的idx
        int idxInt = Integer.parseInt(idx);

        String seriesIdPrevious = null;
        // TODO
        if (idxInt != 0) {
            String idxPre = String.valueOf(idxInt - 1);
            seriesIdPrevious = user2GraphMapMapper.getSeriesIdByIndex(createdGraphId, idxPre);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("graph", updatedGraph.getData());
        data.put("seriesId", seriesIdPrevious);
        return Result.ok(data);
    }

    @Override
    public Result updateGraph(Integer createdGraphId, Map<String, Object> data) {
        if (createdGraphId == null || data == null)
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        UserVo userVo = UserThreadLocal.get();
        // TODO 使用AOP增强此功能验证图是否属于用户
        User2GraphMap graph = user2GraphMapMapper.getGraphByGraphId(createdGraphId);
        if (graph == null || userVo.getUserId() != graph.getUserId())
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        try {
            String dataString = objectMapper.writeValueAsString(data);
            Integer row = user2GraphMapMapper.updateGraph(createdGraphId, dataString);
            HashMap<String, Object> retData = new HashMap<>() {{
                put("isUpdated", true);
            }};
            return Result.ok(retData);
        } catch (JsonProcessingException e) {
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        }

    }


}
