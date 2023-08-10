package org.cv.ocb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User2GraphMap { // user2graph_map
    private Integer createdGraphId;
    private Integer userId;
    private Integer graphTypeId;
    private String graphName;
    private String created_time;
}
