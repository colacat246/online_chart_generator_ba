package org.cv.ocb.pojo.barGraph;

import lombok.Data;

import java.util.Map;

@Data
public class BarGraphCategoryConfig {
    private Integer createdGraphId;
    private Byte animation;
    private Integer divHeight;
    private Float w2hRatio;
    private Map<String, Object> title;
}