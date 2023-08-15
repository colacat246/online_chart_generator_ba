package org.cv.ocb.controller;

import org.cv.ocb.utils.InjectSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@AutoConfigureMockMvc
public class TestGraphDataController {
    @Autowired
    private MockMvc mockMvc;
//    @Test
//    @DisplayName("获取用户图形列表")
//    public void test1() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/userGraphList").header())
//    }
}
