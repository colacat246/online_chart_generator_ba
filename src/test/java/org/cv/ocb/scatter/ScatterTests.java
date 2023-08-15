package org.cv.ocb.scatter;

import org.cv.ocb.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ScatterTests {
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

}
