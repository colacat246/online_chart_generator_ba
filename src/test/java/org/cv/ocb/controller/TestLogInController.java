package org.cv.ocb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cv.ocb.OcbApplication;
import org.cv.ocb.utils.InjectSql;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

@SpringBootTest(classes = {OcbApplication.class})
@ActiveProfiles("test")
@InjectSql
@AutoConfigureMockMvc
public class TestLogInController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("正常登录")
    public void test1() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom");
        userInfo.put("password", "556677");
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("data.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.userName").value("tom"))
                .andExpect(MockMvcResultMatchers.header().exists("x-user-auth-token"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("用户名错误")
    public void test2() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom2");
        userInfo.put("password", "556677");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
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
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
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
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
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
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));
    }

    @Test
    @DisplayName("更改密码")
    public void test6() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "tom");
        userInfo.put("password", "556677");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(userInfo))).andReturn();

        String token = mvcResult.getResponse().getHeader("x-user-auth-token");

        HashMap<String, String> userInfo1 = new HashMap<>();
        userInfo1.put("userName", "tom");
        userInfo1.put("oriPassword", "556677");
        userInfo1.put("newPassword", "778899");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/changePw")
                .contentType("application/json")
                .header("x-user-auth-token", token)
                .content(new ObjectMapper().writeValueAsString(userInfo1)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(999));
        HashMap<String, String> userInfo2 = new HashMap<>();
        userInfo2.put("userName", "tom");
        userInfo2.put("password", "556677");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo2)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue")
                        .value(1004));

        HashMap<String, String> userInfo3 = new HashMap<>();
        userInfo3.put("userName", "tom");
        userInfo3.put("password", "778899");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo3)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue")
                        .value(999));
    }

}
