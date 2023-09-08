package org.cv.ocb.vo.request;


import lombok.Data;

import java.util.Map;

@Data
public class GraphVo {
    private Integer createdGraphId;
    private String graphName;
    private Map<String, Object> data;
}
