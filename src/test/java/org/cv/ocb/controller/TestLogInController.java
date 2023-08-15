package org.cv.ocb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.cv.ocb.utils.GenFakeData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
public class TestLogInController {
    @Autowired
    private GenFakeData genFakeData;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        genFakeData.deleteAllUser();
        genFakeData.addAllUser();
    }

    @AfterEach
    public void after() {
        genFakeData.deleteAllUser();
    }

    @Test
    @DisplayName("正常登录")
    public void test1() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom");
        userInfo.put("password", "556677");
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.userName").value("tom"))
                .andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("用户名错误")
    public void test2() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom2");
        userInfo.put("password", "556677");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1003));
    }

    @Test
    @DisplayName("密码错误")
    public void test3() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom");
        userInfo.put("password", "5566778");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1004));
    }
    @Test
    @DisplayName("空字符")
    public void test4() throws Exception {
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom");
        userInfo.put("password", "");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));
    }
    @Test
    @DisplayName("请求参数错误")
    public void test5() throws Exception {
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userNama", "tom"); // key错误
        userInfo.put("password", "556677");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));
    }
}
