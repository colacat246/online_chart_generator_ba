package org.cv.ocb.controller.api;

import jakarta.servlet.http.HttpServletResponse;
import org.cv.ocb.service.LogInService;
import org.cv.ocb.vo.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginAndRegistryController {
    @Autowired
    private LogInService logInService;

    @GetMapping("/login")
    public Result login(@RequestBody Map<String, String> logInData) {
        return logInService.logInVerify(logInData.get("userName"), logInData.get("password"));

    }
}
