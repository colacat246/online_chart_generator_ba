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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    @DisplayName("按照seriesId找到json_path")
    public void test5() {
        String jsonPath = user2GraphMapMapper.getSeriesJsonPathById(1, "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe");
        Assertions.assertEquals("$.series[1].$extra.id", jsonPath);
        System.out.println(jsonPath);
        Pattern p = Pattern.compile("(?<=series\\[)\\d+(?=\\])");

        Matcher matcher = p.matcher(jsonPath);
        matcher.find();
        Assertions.assertEquals("1", matcher.group(0));
    }

    @Test
    @DisplayName("删除series")
    public void test6() throws JsonProcessingException {
        Integer row = user2GraphMapMapper.deleteSeriesByIndex(1, "1");
        List series = (List) user2GraphMapMapper.getGraphByGraphId(1).getData().get("series");
        Assertions.assertEquals(1, series.size());
    }

    @Test
    @DisplayName("根据idx得到seriesId")
    public void test7() {
        String id = user2GraphMapMapper.getSeriesIdByIndex(1, "0");
        Assertions.assertEquals("3c5c60a2-9ace-41f8-8bd1-c9e74c7d785a", id);
        System.out.println(id);
    }

    @Test
    @DisplayName("更新graph")
    public void test8() throws JsonProcessingException {
        user2GraphMapMapper.updateGraph(1, new ObjectMapper().writeValueAsString(new HashMap() {
            {
                put("k1", "v1");
            }
        }));
        Map<String, Object> data = user2GraphMapMapper.getGraphByGraphId(1).getData();
        String s = new ObjectMapper().writeValueAsString(data);
        Assertions.assertEquals("{\"k1\":\"v1\"}", s);
    }
    @Test
    @DisplayName("更新graph_name")
    public void test9() throws JsonProcessingException {
        String newName = "新图形~~";
        user2GraphMapMapper.updateGraphName(1, newName);
        String graphName = user2GraphMapMapper.getGraphByGraphId(1).getGraphName();

        Assertions.assertEquals(newName, graphName);
    }
}
