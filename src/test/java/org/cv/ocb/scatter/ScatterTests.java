package org.cv.ocb.scatter;

import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.GenFakeData;
import org.cv.ocb.utils.JWTUtils;
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

}
