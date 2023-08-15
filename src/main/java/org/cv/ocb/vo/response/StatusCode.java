package org.cv.ocb.vo.response;

public enum StatusCode {

    OK(999, "OK"),
    REQUEST_PARAMETER_FAULT(1000, "请求参数错误"),
    WRONG_REQUEST_COOKIE(1001, "请求COOKIE错误"),
    TOKEN_VERIFICATION_FAILED(1002, "Token认证失败"),
    NO_USER(1003, "找不到用户"),
    WRONG_PASSWORD(1004, "密码错误");

    public Integer code;
    public String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
