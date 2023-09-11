package org.cv.ocb.handler;

import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.meta.StatusCode;
import org.cv.ocb.utils.StringUtils;
import org.cv.ocb.vo.response.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// 统一处理Controller异常
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleUnknown(Exception ex) {
        log.warn(ex.getMessage());
        ex.printStackTrace();
        return Result.ex(StatusCode.UNHANDLED_NOT_OK);
    }

    // 请求解析异常
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public Result handleHttpMessageNotReadableException(Exception ex) {
        log.warn(ex.getMessage());
        ex.printStackTrace();
        return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
    }

    // 参数校验异常
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result handleValidationException(BindException ex) {
        String msg = ex.getFieldError().getDefaultMessage();
        if (StringUtils.isEmpty(msg)) {
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT);
        } else {
            return Result.ex(StatusCode.REQUEST_PARAMETER_FAULT, msg);
        }
    }
}
