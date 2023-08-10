package org.cv.ocb.scatter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TestJwt {

    private String privateKey = "helloSpringbootlalalaramdomaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    private String token;

    @Test
    public void testGenJwt() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        int expirationTime = 7200; // minute

        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        Map<String, String> claims = new HashMap<>();
        claims.put("user_id", "2");
        claims.put("name", "tom");

        JwtBuilder builder = Jwts.builder();
        String jwt = builder.setHeader(header)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000)) // 设置过期时间
                .signWith(signatureAlgorithm, privateKey)
                .compact();
        token = jwt;
        System.out.println(jwt);
    }

    @Test
    public void testVerifyJwt() {
        testGenJwt();

        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(privateKey).build();
            Jws<Claims> res = parser.parseClaimsJws(token);
            Claims body = res.getBody();
            JwsHeader header = res.getHeader();
            String signature = res.getSignature();

//            System.out.println("signature = " + signature);
//            System.out.println("header = " + header);
            Double exp = (Double)body.get("exp"); // 注意生成的单位是秒
            long expLong = Math.round(exp * 1000);
            System.out.println(System.currentTimeMillis());
            System.out.println(expLong);
            System.out.println(new Date(expLong));


        } catch (SignatureException e) {
            System.out.println("not trusted jwt");
        }
    }

}
