package org.cv.ocb.mapper;


import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryConfSet;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryConfig;
import org.cv.ocb.pojo.barGraph.BarGraphCategoryData;
import org.cv.ocb.utils.InjectSql;
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
@DisplayName("测试柱状图相关mapper")
public class TestBarGraphMappers {
    private BarGraphCategoryConfigMapper configMapper;
    private BarGraphCategoryConfSetMapper confSetMapper;
    private BarGraphCategoryDataMapper dataMapper;

    @Autowired
    public TestBarGraphMappers(BarGraphCategoryConfigMapper configMapper, BarGraphCategoryConfSetMapper confSetMapper, BarGraphCategoryDataMapper dataMapper) {
        this.configMapper = configMapper;
        this.confSetMapper = confSetMapper;
        this.dataMapper = dataMapper;
    }
    @Test
    public void test1() {
        BarGraphCategoryConfig config = configMapper.getConfigById(1);
        log.info(config.toString());
    }
    @Test
    public void test2() {
        List<BarGraphCategoryConfSet> confSets = confSetMapper.getBarGraphCategoryConfSetById(1);
        log.info(confSets.get(0).getColor());
    }
    @Test
    public void test3() {
        List<BarGraphCategoryData> dataSet = dataMapper.getDataById(1);
        dataSet.stream().forEach(i -> {
            System.out.println(i.getDataCategory() + "  " +  i.getDataValue() + "  " +  i.getBarSeriesId() + "  " +  i.getOrder());
        });
    }
}
