package org.cv.ocb.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class User2GraphMap {
    private Integer createdGraphId;
    private Integer userId;
    private Integer graphTypeId;
    private String graphName;
    private String createdTime;
    private Map<String, Object> data;
}
