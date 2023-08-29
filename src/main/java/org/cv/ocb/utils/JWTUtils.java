package org.cv.ocb.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.pojo.User;
import org.cv.ocb.vo.request.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "jwt")
@Component
@Slf4j
public class JWTUtils {

    @Autowired
    private UserMapper userMapper;
    // TODO 校验配置文件
    private String privateKey;
    private Integer expTime;


    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private Map<String, Object> header = new HashMap<>();

    {
        header.put("alg", signatureAlgorithm.getValue());
        header.put("typ", "JWT");
    }

    public Integer getExpTime() {
        return expTime;
    }

    public void setExpTime(Integer expTime) {
        this.expTime = expTime;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String genToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("userName", user.getName());

        String token = Jwts.builder().setHeader(header)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expTime * 1000))
                .signWith(signatureAlgorithm, privateKey)
                .compact();
        return token;
    }

    /**
     * 1. 检验token是否有效
     * 2. 检验用户是否存在
     * <p>
     * 无效时返回null
     *
     * @param token
     * @return
     */
    public UserVo verifyToken(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(privateKey).build();
            Jws<Claims> res = parser.parseClaimsJws(token);
            Claims body = res.getBody();
            int userId = ((Double) body.get("userId")).intValue();
            String userName = (String) body.get("userName");
            // TODO 验证用户是否存在
            User user = userMapper.getUserById(userId);
            if (user == null || !userName.equals(user.getName())) {
                log.error("private key leaking, token: " + token + " userId: " + userId);
                return null;
            }

            UserVo userVo = new UserVo();
            userVo.setUserId(userId);
            userVo.setUserName(userName);
            return userVo;
        } catch (SignatureException ex) {
            return null;
        } catch (ExpiredJwtException ex) {
            log.info("jwt expired");
            // TODO 过期处理
            return null;
        } catch (MalformedJwtException ex) {
            return null;
        }
    }
}
