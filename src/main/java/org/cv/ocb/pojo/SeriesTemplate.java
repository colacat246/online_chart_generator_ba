package org.cv.ocb.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class SeriesTemplate {
    private Integer id;
    private Integer graphTypeId;
    private Map<String, Object> data;
}
