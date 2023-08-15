package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;

public interface LogInService {
    Result logInVerify(String userName, String password);
}
