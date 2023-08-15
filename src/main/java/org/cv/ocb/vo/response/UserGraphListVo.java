package org.cv.ocb.vo.response;

import lombok.Data;

@Data
public class UserGraphListVo {
    private Integer createdGraphId;
    private String graphName;
    private String createdTime;
    private Integer graphTypeId;
}
