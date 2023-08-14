package org.cv.ocb.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.GenFakeData;
import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
@DisplayName("测试用户表")
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenFakeData genFakeData;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void before() {
        genFakeData.addAllUser();
    }

    @AfterEach
    public void after() {
        genFakeData.deleteAllUser();
    }

    @Test
    @DisplayName("测试查询一个用户")
    public void testGetUserById() throws JsonProcessingException {
        User user = userMapper.getUserById(2);

        Assertions.assertNotNull(user);
        // 检验密码
        boolean isPwCorrect = BCrypt.checkpw("556677", user.getPassword());
        Assertions.assertEquals(true, isPwCorrect);
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
}
