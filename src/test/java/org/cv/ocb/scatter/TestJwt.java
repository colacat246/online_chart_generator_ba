package org.cv.ocb.scatter;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.InjectSql;
import org.cv.ocb.utils.JWTUtils;
import org.cv.ocb.vo.request.UserVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@Slf4j
public class TestJwt {

    private String tokenExpired = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InRvbSIsInVzZXJJZCI6MiwiZXhwIjoxNjkyMDM2ODY1fQ.YuWk0FmGdJ4w4f8FX5ANbd4ImmKMlPcvlpPJoeoV0-E";

    private String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDJ6InRvJJyjDX6";

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private Environment env;

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

    @DisplayName("检验正常Token")
    @Test
    public void testVerifyToken() {
        Integer userId = 1;
        String userName = "testUser";
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("userName", userName);
        String token = Jwts.builder().addClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 7200 * 1000)).signWith(SignatureAlgorithm.HS256, privateKey).compact();

        UserVo userVo = jwtUtils.verifyToken(token);
        Assertions.assertEquals(userId, userVo.getUserId());
        Assertions.assertEquals(userName, userVo.getUserName());
    }

    @DisplayName("检验过期Token")
    @Test
    public void testVerifyExpToken() {
        UserVo userVo = jwtUtils.verifyToken(tokenExpired);
        Assertions.assertNull(userVo);
    }

}
