package org.cv.ocb.pojo.barGraph;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class BarGraphCategoryConfSet {
    private Integer barSeriesId;
    private Integer createdGraphId;
    private Date createdTime;
    private String seriesName;
    private String barWidth;
    private String color;
    private Map<String, Object> label;
}