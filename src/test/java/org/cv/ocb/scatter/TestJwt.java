package org.cv.ocb.scatter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.GenFakeData;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.vo.request.UserVo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;


@SpringBootTest
@Slf4j
public class TestJwt {

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InRvbSIsInVzZXJJZCI6MiwiZXhwIjoxNjkyMDM2ODY1fQ.YuWk0FmGdJ4w4f8FX5ANbd4ImmKMlPcvlpPJoeoV0-E";

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private Environment env;

    @Autowired
    private GenFakeData genFakeData;
    @BeforeEach
    public void beforeEach() {
        genFakeData.deleteAllUser();
        genFakeData.addAllUser();
    }
    @AfterEach
    public void afterEach() {
//        genFakeData.deleteAllUser();
    }

    @DisplayName("生成Token")
    @Test
    public void testGenJwt() {
        Integer id = 12345;
        String name = "cvcvcv";
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        String token = jwtUtils.genToken(user);
        String privateKey = env.getProperty("jwt.private-key");

        JwtParser parser = Jwts.parserBuilder().setSigningKey(privateKey).build();
        Jws<Claims> res = parser.parseClaimsJws(token);
        Claims body = res.getBody();
        Assertions.assertEquals(id, ((Double) body.get("userId")).intValue());
        Assertions.assertEquals(name, body.get("userName"));
    }

    @DisplayName("检验Token")
    @Test
    public void testVerifyToken() {
        UserVo userVo = jwtUtils.verifyToken(token);
        Assertions.assertEquals(2, userVo.getUserId());
        Assertions.assertEquals("tom", userVo.getUserName());
    }

    @Test
    public void testJwtExp() throws InterruptedException {
        Integer id = 12345;
        String name = "cvcvcv";
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        jwtUtils.setExpTime(1);
        String token = jwtUtils.genToken(user);

        Thread.sleep(2000);

        UserVo userVo = jwtUtils.verifyToken(token);
        Assertions.assertNull(userVo);
    }

}
