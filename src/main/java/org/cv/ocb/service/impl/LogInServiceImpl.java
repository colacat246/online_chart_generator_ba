package org.cv.ocb.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.pojo.User;
import org.cv.ocb.service.LogInService;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.utils.StringUtils;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.cv.ocb.vo.response.StatusCode;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class LogInServiceImpl implements LogInService {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result logInVerify(String userName, String password) {
        if (StringUtils.hasEmpty(userName, password)) return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);

        User user = userMapper.getUserByName(userName);

        if (user == null) return Result.ex(StatusCode.NO_USER);
        if (!BCrypt.checkpw(password, user.getPassword())) return Result.ex(StatusCode.WRONG_PASSWORD);

        // 验证通过
        // 向cookie中添加token
        String token = jwtUtils.genToken(user);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.addCookie(new Cookie("user-auth-token", token));

        // 返回user信息
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getName());
        userVo.setUserId(user.getUserId());
        return Result.ok(userVo);
    }
}
