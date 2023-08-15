package org.cv.ocb.controller.api;

import org.cv.ocb.service.UserGraphListService;
import org.cv.ocb.utils.UserThreadLocal;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GraphDataController {

    @Autowired
    private UserGraphListService userGraphListService;
    @GetMapping(value = "/userGraphList")
    public Result getUserGraphList() {
        UserVo userVo = UserThreadLocal.get();
        return userGraphListService.getUserGraphListById(userVo.getUserId());
    }
}
