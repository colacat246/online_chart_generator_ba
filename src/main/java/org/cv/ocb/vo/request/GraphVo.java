package org.cv.ocb.vo.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Map;

@Data
public class GraphVo {
    @Min(value = 1)
    // TODO 改到配置文件中
    @Max(value = 2)
    private Integer graphTypeId;
    @Positive
    private Integer createdGraphId;
    private String graphName;
    private Map<String, Object> data;
}
