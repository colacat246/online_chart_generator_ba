package org.cv.ocb.service.impl;

import org.cv.ocb.mapper.User2GraphMapMapper;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.service.UserGraphListService;
import org.cv.ocb.vo.response.Result;
import org.cv.ocb.vo.response.UserGraphListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGraphListServiceImpl implements UserGraphListService {

    @Autowired
    private User2GraphMapMapper user2GraphMapMapper;

    @Override
    public Result getUserGraphListById(Integer id) {
        List<User2GraphMap> graphs = user2GraphMapMapper.getGraphsByUserId(id);
        List<UserGraphListVo> graphListVo = graphs.stream().map(graph -> {
            UserGraphListVo userGraphListVo = new UserGraphListVo();
            BeanUtils.copyProperties(graph, userGraphListVo);
            return userGraphListVo;
        }).toList();
        return Result.ok(graphListVo);
    }
}
