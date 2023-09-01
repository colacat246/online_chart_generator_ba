package org.cv.ocb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.pojo.User;
import org.cv.ocb.utils.InjectSql;
import org.cv.ocb.utils.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@AutoConfigureMockMvc
public class TestGraphDataController {
    @Autowired
    public TestGraphDataController(MockMvc mockMvc, UserMapper userMapper, JWTUtils jwtUtils) {
        this.mockMvc = mockMvc;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    private MockMvc mockMvc;
    private UserMapper userMapper;
    private JWTUtils jwtUtils;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private HttpHeaders httpHeadersNotTom = new HttpHeaders();

    @BeforeEach
    public void beforeEach() {
        User user = userMapper.getUserByName("tom");
        String token = jwtUtils.genToken(user);
        httpHeaders.add("x-user-auth-token", token);

        User user2 = userMapper.getUserByName("testUser");
        String token2 = jwtUtils.genToken(user2);
        httpHeadersNotTom.add("x-user-auth-token", token2);
    }

    @Test
    @DisplayName("获取用户图形列表")
    public void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/userGraphList")
                        .headers(httpHeaders))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isArray());
    }

    @Test
    @DisplayName("获取用户图形列表-无token")
    public void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/userGraphList")
                ).andExpect(MockMvcResultMatchers.jsonPath("data").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1001));
    }

    @Test
    @DisplayName("获取具体图-柱状图")
    public void test3() {
        Arrays.stream(new String[]{"1", "2"}).forEach(i -> {
            try {
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/userGraph/" + i)
                                .headers(httpHeaders)
                        ).andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                        .andExpect(MockMvcResultMatchers.jsonPath("data").isNotEmpty())
                        .andExpect(MockMvcResultMatchers.jsonPath("data.series[0].color").value("#666666"));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    @DisplayName("获取具体图-找不到的情况")
    public void test4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/userGraph/100")
                        .headers(httpHeaders)
                ).andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isEmpty());
    }

    @Test
    @DisplayName("获取具体图-图和用户不匹配")
    public void test4_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/userGraph/1")
                        .headers(httpHeadersNotTom)
                ).andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isEmpty());
    }

    @Test
    @DisplayName("获取具体图-折线图")
    public void test5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/userGraph/3")
                        .headers(httpHeaders)
                ).andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isNotEmpty());
//                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("插入新图形")
    public void test6() throws Exception {

        HashMap<String, Object> data = new HashMap<>();
        data.put("graphTypeId", 1);
        data.put("graphName", "新图形~~");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/userGraph")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isNotEmpty());
//                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("插入新曲线")
    public void test7() throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("createdGraphId", 1);
        data.put("seriesName", "新柱状系列~~");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/userGraphSeries")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph.series").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph.series.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph.series[2].$extra.id").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("data.newSeriesId").isString())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("插入新曲线-图形和用户不匹配")
    public void test8() throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("createdGraphId", 1);
        data.put("seriesName", "新柱状系列~~");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/userGraphSeries")
                        .headers(httpHeadersNotTom)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isEmpty());
    }

    @Test
    @DisplayName("删除图形-正常")
    public void test9() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 1);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraph")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graphList.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.curGraphId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graphList[0].graphName").value("柱状图2"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graphList[1].graphName").value("折线图1"));
    }

    @Test
    @DisplayName("删除图形-用户名和图形不匹配")
    public void test10() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 1);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraph")
                        .headers(httpHeadersNotTom)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isEmpty());
    }
    @Test
    @DisplayName("删除series-正常-删除第一个")
    public void test11() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 1, "seriesId", "3c5c60a2-9ace-41f8-8bd1-c9e74c7d785a");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraphSeries")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph.series.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("data.seriesId").isEmpty());
    }
    @Test
    @DisplayName("删除series-正常-删除第二个")
    public void test12() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 1, "seriesId", "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraphSeries")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("data.graph.series.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("data.seriesId").value("3c5c60a2-9ace-41f8-8bd1-c9e74c7d785a"));
    }
    @Test
    @DisplayName("删除series-用户图形不匹配")
    public void test13() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 1, "seriesId", "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraphSeries")
                        .headers(httpHeadersNotTom)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));

    }
    @Test
    @DisplayName("删除series-图形和seriesId不匹配")
    public void test14() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 2, "seriesId", "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraphSeries")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));

    }
    @Test
    @DisplayName("删除series-seriesId找不到")
    public void test15() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 1, "seriesId", "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe___12345");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraphSeries")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));

    }
    @Test
    @DisplayName("删除series-图形找不到")
    public void test16() throws Exception {
        Map<String, Object> data = Map.of("createdGraphId", 100, "seriesId", "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/userGraphSeries")
                        .headers(httpHeaders)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));

    }
}
