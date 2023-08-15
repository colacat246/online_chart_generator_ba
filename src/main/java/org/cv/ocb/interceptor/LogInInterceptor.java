package org.cv.ocb.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.utils.RespWriter;
import org.cv.ocb.utils.UserThreadLocal;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.cv.ocb.vo.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LogInInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return true;
        Cookie[] cookies = request.getCookies();
        Cookie userAuthCookie = null;
        if (cookies == null) {
            RespWriter.writeJson(response, Result.ex(StatusCode.WRONG_REQUEST_COOKIE));
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-auth-token")) userAuthCookie = cookie;
            break;
        }
        if (userAuthCookie == null) {
            RespWriter.writeJson(response, Result.ex(StatusCode.WRONG_REQUEST_COOKIE));
            return false;
        }
        String token = userAuthCookie.getValue();

        if (token == null || token.equals("")) {
            RespWriter.writeJson(response, Result.ex(StatusCode.WRONG_REQUEST_COOKIE));
            return false;
        }
        // TODO 优化逻辑
        UserVo userVo = jwtUtils.verifyToken(token);
        if (userVo == null) {
            RespWriter.writeJson(response, Result.ex(StatusCode.TOKEN_VERIFICATION_FAILED));
            return false;
        }
        // ThreadLocal放用户
        UserThreadLocal.put(userVo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}