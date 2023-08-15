package org.cv.ocb.vo.response;

import lombok.Data;

@Data
public class Result {
    private Integer statusCodeValue;
    private String message;
    private Object data;

    private Result(StatusCode statusCode, Object data) {
        this.statusCodeValue = statusCode.code;
        this.message = statusCode.message;
        this.data = data;
    }

    public static Result ok(Object data) {
        return new Result(StatusCode.OK, data);
    }

    public static Result ex(StatusCode statusCode) {
        return new Result(statusCode, null);
    }

}
