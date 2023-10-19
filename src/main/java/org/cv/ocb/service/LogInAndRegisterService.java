package org.cv.ocb.service;

import org.cv.ocb.vo.response.Result;

public interface LogInAndRegisterService {
    Result logInVerify(String userName, String password);
    Result register(String userName, String password);

    Result changePw(String userName, String oriPassword, String newPassword);
}
