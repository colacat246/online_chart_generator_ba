package org.cv.ocb.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.meta.StatusCode;
import org.cv.ocb.pojo.User;
import org.cv.ocb.service.LogInAndRegisterService;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.utils.StringUtils;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class LogInAndRegisterServiceImpl implements LogInAndRegisterService {

    public LogInAndRegisterServiceImpl(JWTUtils jwtUtils, UserMapper userMapper) {
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
    }

    private JWTUtils jwtUtils;
    private UserMapper userMapper;

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

        // TODO 加事务
        User user = userMapper.getUserByName(userName);
        if (user != null) return Result.ex(StatusCode.USERNAME_DUPLICATED);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User();
        newUser.setName(userName);
        newUser.setPassword(hashedPassword);
        userMapper.insertUser(newUser);

        return genUserResp(newUser);
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
