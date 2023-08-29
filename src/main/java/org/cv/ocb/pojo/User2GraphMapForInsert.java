package org.cv.ocb.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class User2GraphMapForInsert {
    private Integer createdGraphId;
    private Integer userId;
    private Integer graphTypeId;
    private String graphName;
    private String createdTime;
    private String data;
}
