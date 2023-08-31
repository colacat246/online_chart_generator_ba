package org.cv.ocb.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.GraphTemplate;
import org.cv.ocb.pojo.User2GraphMap;
import org.cv.ocb.pojo.User2GraphMapForInsert;
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
    @Autowired
    private GraphTemplateMapper graphTemplateMapper;

    @Test
    @DisplayName("测试查询一个用户的图形")
    public void test1() throws JsonProcessingException {
        List<User2GraphMap> res = user2GraphMapMapper.getGraphsByUserId(2);
        Assertions.assertEquals(3, res.size());
        log.info(objectMapper.writeValueAsString(res));
    }
    @Test
    @DisplayName("测试查询一个用户的图形-空")
    public void test1_1() throws JsonProcessingException {
        List<User2GraphMap> res = user2GraphMapMapper.getGraphsByUserId(1);
        Assertions.assertEquals(0, res.size());
    }

    @Test
    @DisplayName("插入图形")
    public void test2() throws JsonProcessingException {
        GraphTemplate template = graphTemplateMapper.getTemplateByGraphTypeId(1);
        User2GraphMapForInsert user2GraphMap = new User2GraphMapForInsert();
        user2GraphMap.setUserId(1);
        user2GraphMap.setGraphName("abcd");
        user2GraphMap.setGraphTypeId(1);
        user2GraphMap.setData(new ObjectMapper().writeValueAsString(template.getData()));

        user2GraphMapMapper.insertNewGraph(user2GraphMap);

        Integer id = user2GraphMap.getCreatedGraphId();

        User2GraphMap newGraph = user2GraphMapMapper.getGraphByGraphId(id);
        System.out.println(newGraph);

    }

    @Test
    @DisplayName("插入新series")
    public void test3() {
        Integer res = user2GraphMapMapper.addNewSeries(1, "{\"new_data\": 1234567}");
        User2GraphMap graph = user2GraphMapMapper.getGraphByGraphId(1);
        System.out.println(res);
        System.out.println(graph);

    }

    @Test
    @DisplayName("删除graph")
    public void test4() {
        Integer res = user2GraphMapMapper.deleteGraph(1);
        User2GraphMap graph = user2GraphMapMapper.getGraphByGraphId(1);
        Assertions.assertEquals(1, res);
        Assertions.assertNull(graph);
        List<User2GraphMap> graphs = user2GraphMapMapper.getGraphsByUserId(2);
        Assertions.assertEquals(2, graphs.size());
        graphs.forEach(i -> {
            System.out.println(i.getGraphName());
        });
    }
}
