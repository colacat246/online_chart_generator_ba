package org.cv.ocb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cv.ocb.mapper.GraphTemplateMapper;
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
import org.springframework.stereotype.Service;

@Service
public class UserGraphServiceImpl implements UserGraphService {
    private User2GraphMapMapper user2GraphMapMapper;
    private GraphTemplateMapper graphTemplateMapper;
    private UserGraphListService userGraphListService;

    public UserGraphServiceImpl(User2GraphMapMapper user2GraphMapMapper, GraphTemplateMapper graphTemplateMapper, UserGraphListService userGraphListService) {
        this.user2GraphMapMapper = user2GraphMapMapper;
        this.graphTemplateMapper = graphTemplateMapper;
        this.userGraphListService = userGraphListService;
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
        return userGraphListService.getUserGraphListById(userVo.getUserId());
    }

    @Override
    public Result getGraphById(Integer id) {
        if (id == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        UserVo userVo = UserThreadLocal.get();
        // 验证图是否属于用户
        Integer userId = user2GraphMapMapper.getUserIdByGraphId(id);
        if (userId != userVo.getUserId()) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        User2GraphMap graphData = user2GraphMapMapper.getGraphByGraphId(id);
        if (graphData == null) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        return Result.ok(graphData.getData());
    }


}
