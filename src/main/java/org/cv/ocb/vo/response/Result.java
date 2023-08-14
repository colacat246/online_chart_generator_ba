package org.cv.ocb.vo.response;

import org.cv.ocb.utils.StatusCode;

public class Result {
    private StatusCode statusCode;
    private String message;
    private Object data;

    private Result(StatusCode statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static Result okResult(Object data) {
        return new Result(StatusCode.OK, "OK", data);
    }

    public static Result exceptionResult(Object data, StatusCode statusCode, String message) {
        return new Result(statusCode, message, data);
    }

}
