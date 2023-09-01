package org.cv.ocb.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import org.cv.ocb.service.LogInAndRegisterService;
import org.cv.ocb.vo.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginAndRegistryController {
    public LoginAndRegistryController(LogInAndRegisterService logInAndRegisterService) {
        this.logInAndRegisterService = logInAndRegisterService;
    }

    private LogInAndRegisterService logInAndRegisterService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> data) {
        return logInAndRegisterService.logInVerify(data.get("userName"), data.get("password"));

    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> data) {
        return logInAndRegisterService.register(data.get("userName"), data.get("password"));
    }
}
