package org.cv.ocb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private Integer status;
    private String createdTime;
}