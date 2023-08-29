package org.cv.ocb.controller;

import jakarta.servlet.http.Cookie;
import org.cv.ocb.mapper.UserMapper;
import org.cv.ocb.meta.GraphMeta;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

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

    @BeforeEach
    public void beforeEach() {
        User user = userMapper.getUserByName("tom");
        String token = jwtUtils.genToken(user);
        httpHeaders.add("x-user-auth-token", token);
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
                                .get("/api/userGraph/2/" + i)
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
                        .get("/api/userGraph/2/3")
                        .headers(httpHeaders)
                ).andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isEmpty());
    }

    @Test
    @DisplayName("获取具体图-折线图")
    public void test5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/userGraph/1/3")
                        .headers(httpHeaders)
                ).andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isNotEmpty());
//                .andDo(MockMvcResultHandlers.print());
    }
}
