package org.cv.ocb.controller.api;

import org.cv.ocb.service.UserGraphListService;
import org.cv.ocb.service.UserGraphService;
import org.cv.ocb.utils.UserThreadLocal;
import org.cv.ocb.vo.request.UserVo;
import org.cv.ocb.vo.response.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GraphDataController {
    public GraphDataController(UserGraphListService userGraphListService, UserGraphService userGraphService) {
        this.userGraphListService = userGraphListService;
        this.userGraphService = userGraphService;
    }

    private UserGraphListService userGraphListService;
    private UserGraphService userGraphService;

    /**
     * 根据用户token获取用户图形列表
     *
     * @return
     */
    @GetMapping(value = "/userGraphList")
    public Result getUserGraphList() {
        UserVo userVo = UserThreadLocal.get();
        return userGraphListService.getUserGraphListById(userVo.getUserId());
    }

    /**
     * 根据用户token和具体图id获取图形
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/userGraph/{id}")
    public Result getUserGraph(@PathVariable("id") Integer id) {
        return userGraphService.getGraphById(id);
    }

    @PostMapping(value = "/userGraph")
    public Result postNewGraph(@RequestBody Map<String, Object> data) {
        return userGraphService.insertNewGraph((Integer) data.get("graphTypeId"), (String)data.get("graphName"));
    }
    @DeleteMapping(value = "/userGraph")
    public Result deleteGraph(@RequestBody Map<String, Object> data) {
        return userGraphService.deleteGraph((Integer) data.get("createdGraphId"));
    }

    @PostMapping(value = "/userGraphSeries")
    public Result postNewSeries(@RequestBody Map<String, Object> data) {
        return userGraphService.insertNewSeries((Integer) data.get("createdGraphId"), (String)data.get("seriesName"));
    }

    @DeleteMapping(value = "/userGraphSeries")
    public Result deleteSeriesById(@RequestBody Map<String, Object> data) {
        return userGraphService.deleteSeriesById((Integer) data.get("createdGraphId"), (String)data.get("seriesId"));

    }
}
