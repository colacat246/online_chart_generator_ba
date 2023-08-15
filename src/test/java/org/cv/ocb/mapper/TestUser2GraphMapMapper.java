package org.cv.ocb.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.utils.InjectSql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@Slf4j
@DisplayName("测试用户-图形关联表")
public class TestUser2GraphMapMapper {
    @Autowired
    private User2GraphMapMapper user2GraphMapMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("测试查询一个用户的图形")
    public void test1() throws JsonProcessingException {
        List<User2GraphMap> res = user2GraphMapMapper.getGraphsByUserId(2);
        Assertions.assertEquals(2, res.size());
        log.info( objectMapper.writeValueAsString(res));

    }
}
