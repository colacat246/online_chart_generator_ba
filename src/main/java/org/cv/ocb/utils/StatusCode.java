package org.cv.ocb.utils;

public enum StatusCode {

    OK(999),
    EMPTY_PARAMETERS(1000),
    VERIFICATION_FAILED(1001);

    private Integer code;

    StatusCode(Integer code) {
        this.code = code;
    }
}
