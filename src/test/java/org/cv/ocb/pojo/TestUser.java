package org.cv.ocb.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
public class TestUser {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void before() {
        jdbcTemplate.update("delete from user");
        jdbcTemplate.update("insert into user (`name`, `password`) values ('testUser', '123456')");
        jdbcTemplate.update("insert into user (`name`, `password`) values ('tom', '556677')");
        jdbcTemplate.update("insert into user (`name`, `password`) values ('peter', 'abc123')");
        jdbcTemplate.update("insert into user (`name`, `password`) values ('testUser2', 'aabbcc')");
        jdbcTemplate.update("insert into user (`name`, `password`) values ('testUser3', 'abcabc')");
    }

    @AfterEach
    public void after() {
        jdbcTemplate.update("delete from user");
    }

    @Test
    @DisplayName("测试查询一个用户")
    public void testGetUserById() throws JsonProcessingException {
        User user = userMapper.getUserByName("tom");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("556677", user.getPassword());
        Assertions.assertEquals(1, user.getStatus());

        String res = objectMapper.writeValueAsString(user);
        log.info(res);
    }

    @Test
    @DisplayName("测试查询所有用户")
    public void testGetAllUsers() {
        List<User> allUsers = userMapper.getAllUsers();
        Assertions.assertEquals(5, allUsers.size());
        Assertions.assertEquals("tom", allUsers.get(1).getName());

        allUsers.stream().map(value -> {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).forEach(log::info);
    }
}
