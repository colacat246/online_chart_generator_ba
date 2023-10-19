package org.cv.ocb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.cv.ocb.mapper.User2GraphMapMapper;
import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.meta.StatusCode;
import org.cv.ocb.pojo.User;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.pojo.User2GraphMapForInsert;
import org.cv.ocb.service.LogInAndRegisterService;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.utils.StringUtils;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class LogInAndRegisterServiceImpl implements LogInAndRegisterService {

    public LogInAndRegisterServiceImpl(JWTUtils jwtUtils, UserMapper userMapper, User2GraphMapMapper user2GraphMapMapper, ObjectMapper objectMapper) {
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
        this.user2GraphMapMapper = user2GraphMapMapper;
        this.objectMapper = objectMapper;
    }

    private JWTUtils jwtUtils;
    private UserMapper userMapper;
    private User2GraphMapMapper user2GraphMapMapper;
    private ObjectMapper objectMapper;

    @Override
    public Result logInVerify(String userName, String password) {
        User user = userMapper.getUserByName(userName);

        if (user == null) return Result.ex(StatusCode.NO_USER);
        if (!BCrypt.checkpw(password, user.getPassword())) return Result.ex(StatusCode.WRONG_PASSWORD);

        return genUserResp(user);
    }

    @Override
    public Result register(String userName, String password) {
        if (StringUtils.hasEmpty(userName, password)) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        User user = userMapper.getUserByName(userName);
        if (user != null) return Result.ex(StatusCode.USERNAME_DUPLICATED);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User();
        newUser.setName(userName);
        newUser.setPassword(hashedPassword);
        userMapper.insertUser(newUser);

        // 添加模板图形
        List<User2GraphMap> graphs = user2GraphMapMapper.getGraphsByUserId(1);
        for (User2GraphMap graph : graphs) {
            User2GraphMapForInsert user2GraphMapForInsert = new User2GraphMapForInsert();
            user2GraphMapForInsert.setCreatedGraphId(graph.getCreatedGraphId());
            user2GraphMapForInsert.setUserId(newUser.getUserId());
            user2GraphMapForInsert.setGraphTypeId(graph.getGraphTypeId());
            user2GraphMapForInsert.setGraphName(graph.getGraphName());
            try {
                user2GraphMapForInsert.setData(objectMapper.writeValueAsString(graph.getData()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            user2GraphMapMapper.insertNewGraph(user2GraphMapForInsert);
        }
        return genUserResp(newUser);
    }

    @Override
    public Result changePw(String userName, String oriPassword, String newPassword) {
        User user = userMapper.getUserByName(userName);

        if (user == null) return Result.ex(StatusCode.NO_USER);
        if (!BCrypt.checkpw(oriPassword, user.getPassword())) return Result.ex(StatusCode.WRONG_PASSWORD);
        // 更换密码
        String pw = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(pw);
        userMapper.updateUsePw(user);
        return genUserResp(user);
    }

    private Result genUserResp(User user) {
        String token = jwtUtils.genToken(user);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.setHeader("x-user-auth-token", token);
        // 若跨域，需设置这个，否则浏览器拿不到header
//        response.setHeader("Access-Control-Expose-Headers", "x-user-auth-token");
        // 返回user信息
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getName());
        userVo.setUserId(user.getUserId());
        return Result.ok(userVo);

    }
}
