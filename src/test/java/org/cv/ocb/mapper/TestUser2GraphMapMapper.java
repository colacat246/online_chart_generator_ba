package org.cv.ocb.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.utils.GenFakeData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
@Slf4j
@DisplayName("测试用户-图形关联表")
public class TestUser2GraphMapMapper {

    @Autowired
    private User2GraphMapMapper user2GraphMapMapper;

    @Autowired
    private GenFakeData genFakeData;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void before() {
        genFakeData.addAllUser();
        genFakeData.addTomsFakeGraph();
    }

    @AfterEach
    public void after() {
        genFakeData.deleteAllUser();
    }

    @Test
    @DisplayName("测试查询一个用户")
    public void test1() throws JsonProcessingException {
        List<User2GraphMap> res = user2GraphMapMapper.getGraphsByUserId(2);
        log.info( objectMapper.writeValueAsString(res));

    }

    @Test
    @DisplayName("测试查询所有用户")
    public void test2() {
    }
}
