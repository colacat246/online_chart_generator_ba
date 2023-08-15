package org.cv.ocb.controller;

import org.cv.ocb.utils.GenFakeData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class TestGraphDataController {
    @Autowired
    private GenFakeData genFakeData;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void before() {
        genFakeData.deleteAllUser();
        genFakeData.addAllUser();
        genFakeData.addTomsFakeGraph();
    }
    @AfterEach
    public void afterEach() {
        genFakeData.deleteAllUser();
    }

//    @Test
//    @DisplayName("获取用户图形列表")
//    public void test1() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/userGraphList").header())
//    }
}
