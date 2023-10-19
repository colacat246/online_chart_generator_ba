package org.cv.ocb.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.service.LogInAndRegisterService;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.request.UserVoWith2Pw;
import org.cv.ocb.vo.response.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginAndRegistryController {

    private final LogInAndRegisterService logInAndRegisterService;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated(UserVo.Login.class) UserVo userVo) {
        return logInAndRegisterService.logInVerify(userVo.getUserName(), userVo.getPassword());
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated(UserVo.Register.class) UserVo userVo) {
        return logInAndRegisterService.register(userVo.getUserName(), userVo.getPassword());
    }

    @PostMapping("/changePw")
    public Result changePw(@RequestBody /*@Validated*/ UserVoWith2Pw userVo) {
        return logInAndRegisterService.changePw(userVo.getUserName(), userVo.getOriPassword(), userVo.getNewPassword());
    }
}
