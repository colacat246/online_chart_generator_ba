package org.cv.ocb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.hamcrest.Matchers.greaterThan;

import java.util.HashMap;

@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@AutoConfigureMockMvc
public class TestRegisterController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("正常注册")
    public void test1() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "new_user");
        userInfo.put("password", "556677");
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue" ).value(999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userId", greaterThan(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("data.userName").value("new_user"))
                .andExpect(MockMvcResultMatchers.header().exists("x-user-auth-token"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("用户名已占用")
    public void test2() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "peter");
        userInfo.put("password", "556677");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1005));
    }

    @Test
    @DisplayName("用户名为空")
    public void test3() throws Exception {

        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "");
        userInfo.put("password", "5566778");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userInfo)))
                .andExpect(MockMvcResultMatchers.jsonPath("statusCodeValue").value(1000));
    }

    @Test
    @DisplayName("密码为空")
    public void test4() throws Exception {
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "test_user");
        userInfo.put("password", "");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
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
}
