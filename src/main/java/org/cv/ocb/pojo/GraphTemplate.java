package org.cv.ocb.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class GraphTemplate {
    private Integer id;
    private Integer graphTypeId;
    private Map<String, Object> data;
}
