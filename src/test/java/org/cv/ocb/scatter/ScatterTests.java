package org.cv.ocb.scatter;

import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.GenFakeData;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootTest
public class ScatterTests {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private GenFakeData genFakeData;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void genToken() {
        genFakeData.deleteAllUser();
        genFakeData.addAllUser();
        genFakeData.addTomsFakeGraph();
    }

    @Test
    public void test1() {
        String str = null;
        Assertions.assertEquals(true, StringUtils.isEmpty(""));
        Assertions.assertEquals(true, StringUtils.isEmpty(str));
        Assertions.assertEquals(false, StringUtils.isEmpty("aa"));
        Assertions.assertEquals(true, StringUtils.hasEmpty("", "abc"));
        Assertions.assertEquals(true, StringUtils.hasEmpty("123", "abc", null));
        Assertions.assertEquals(false, StringUtils.hasEmpty("123", "abc", "123"));
        Assertions.assertEquals(true, StringUtils.hasEmpty("123", null, ""));
    }
}
