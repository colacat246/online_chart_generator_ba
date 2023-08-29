package org.cv.ocb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.cv.ocb.mapper.User2GraphMapMapper;
import org.cv.ocb.meta.StatusCode;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.service.UserGraphService;
import org.cv.ocb.utils.UserThreadLocal;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserGraphServiceImpl implements UserGraphService {
    private User2GraphMapMapper user2GraphMapMapper;


    public UserGraphServiceImpl(User2GraphMapMapper user2GraphMapMapper) {
        this.user2GraphMapMapper = user2GraphMapMapper;
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
