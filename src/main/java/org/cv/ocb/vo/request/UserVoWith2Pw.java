package org.cv.ocb.vo.request;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserVo {
    private Integer userId;
    @NotBlank(groups = {Login1.class, Register1.class}, message = "用户名为空")
    private String userName;
    @NotBlank(groups = {Login2.class, Register2.class}, message = "密码为空")
    private String password;

    // 定义顺序
    @GroupSequence({Login1.class, Login2.class})
    public interface Login {
    }
    public interface Login1 {
    }
    public interface Login2 {
    }

    @GroupSequence({Register1.class, Register2.class})
    public interface Register {
    }
    public interface Register1 {
    }
    public interface Register2 {
    }
}
