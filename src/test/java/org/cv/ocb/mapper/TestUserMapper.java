package org.cv.ocb.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.InjectSql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@Slf4j
@DisplayName("测试用户表")
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("测试查询一个用户")
    public void testGetUserById() throws JsonProcessingException {
        User user = userMapper.getUserById(2);

        Assertions.assertNotNull(user);
        // 检验密码
        boolean isPwCorrect = BCrypt.checkpw("556677", user.getPassword());
        Assertions.assertTrue(isPwCorrect);
        Assertions.assertEquals(1, user.getStatus());
        Assertions.assertEquals("tom", user.getName());

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
    @Test
    @DisplayName("插入用户，返回自增主键")
    public void test1() {
        User newUser = new User();
        newUser.setName("abc");
        newUser.setPassword("123");
        userMapper.insertUser(newUser);
        Assertions.assertTrue(newUser.getUserId() > 5);
    }
}
