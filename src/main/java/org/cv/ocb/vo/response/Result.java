package org.cv.ocb.vo.response;

import lombok.Data;
import org.cv.ocb.meta.StatusCode;

@Data
public class Result {
    private Integer statusCodeValue;
    private String message;
    private Object data;

    // 使用StatusCode enum中的message
    private Result(StatusCode statusCode, Object data) {
        this.statusCodeValue = statusCode.code;
        this.message = statusCode.message;
        this.data = data;
    }

    // 使用自定义message
    private Result(StatusCode statusCode, String message, Object data) {
        this.statusCodeValue = statusCode.code;
        this.message = message;
        this.data = data;
    }

    public static Result ok(Object data) {
        return new Result(StatusCode.OK, data);
    }

    public static Result ex(StatusCode statusCode) {
        return new Result(statusCode, null);
    }

    public static Result ex(StatusCode statusCode, String message) {
        return new Result(statusCode, message, null);
    }

}
