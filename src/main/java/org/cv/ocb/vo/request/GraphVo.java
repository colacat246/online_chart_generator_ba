package org.cv.ocb.vo.request;


import lombok.Data;

import java.util.Map;

@Data
public class GraphVo {
    private Integer createdGraphId;
    private Map<String, Object> data;
}
