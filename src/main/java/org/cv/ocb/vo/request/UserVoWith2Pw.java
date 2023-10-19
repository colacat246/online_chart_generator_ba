package org.cv.ocb.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserVoWith2Pw {
    private Integer userId;
//    @NotBlank(message = "用户名为空")
    private String userName;
//    @NotBlank(message = "旧密码为空")
    private String oriPassword;
//    @NotBlank(message = "新密码为空")
    private String newPassword;
}

