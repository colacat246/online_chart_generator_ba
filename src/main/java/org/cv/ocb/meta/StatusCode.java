package org.cv.ocb.meta;

public enum StatusCode {


    OK(999, "OK"),
    UNHANDLED_NOT_OK(-999, "服务器未知错误"),
    REQUEST_PARAMETER_FAULT(1000, "请求参数错误"),
    WRONG_REQUEST_HEADER(1001, "请求Header错误"),
    TOKEN_VERIFICATION_FAILED(1002, "Token认证失败"),
    NO_USER(1003, "找不到用户"),
    WRONG_PASSWORD(1004, "密码错误"),
    USERNAME_DUPLICATED(1005, "用户名已占用"),
    USER_NOT_MATCH(1006, "图形不属于用户");


    public Integer code;
    public String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
