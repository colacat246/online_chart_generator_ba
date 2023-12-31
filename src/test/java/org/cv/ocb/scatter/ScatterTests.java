package org.cv.ocb.scatter;

import lombok.RequiredArgsConstructor;
import org.cv.ocb.service.LogInAndRegisterService;
import org.cv.ocb.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class ScatterTests {

//    @Autowired
//    private LogInAndRegisterService logInAndRegisterService;

//    @Test
//    @DisplayName("测试service参数校验")
//    public void test1_0() {
//        logInAndRegisterService.logInVerify("", "12345");
//    }

    @Test
    @DisplayName("测试StringUtils类")
    public void test1() {
        String str = null;
        Assertions.assertTrue(StringUtils.isEmpty(""));
        Assertions.assertTrue(StringUtils.isEmpty(str));
        Assertions.assertFalse(StringUtils.isEmpty("aa"));
        Assertions.assertTrue(StringUtils.hasEmpty("", "abc"));
        Assertions.assertTrue(StringUtils.hasEmpty("123", "abc", null));
        Assertions.assertFalse(StringUtils.hasEmpty("123", "abc", "123"));
        Assertions.assertTrue(StringUtils.hasEmpty("123", null, ""));
    }

    @Test
    @DisplayName("生成uuid")
    public void test2() {
        for (int i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString());

        }
    }

}
