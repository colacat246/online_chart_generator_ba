package org.cv.ocb.utils;


import org.cv.ocb.vo.request.UserVo;

public class UserThreadLocal {

    private UserThreadLocal() {}
    private static final ThreadLocal<UserVo> LOCAL = new ThreadLocal<>();
    public static void put(UserVo userVo) {
        LOCAL.set(userVo);
    }
    public static UserVo get() {
        return LOCAL.get();
    }
    public static void remove() {
        LOCAL.remove();
    }
}
