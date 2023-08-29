package org.cv.ocb.mapper;

import lombok.extern.slf4j.Slf4j;
import org.cv.ocb.pojo.GraphTemplate;
import org.cv.ocb.pojo.SeriesTemplate;
import org.cv.ocb.utils.InjectSql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@InjectSql
@Slf4j
@DisplayName("测试图形模板表")
public class TestTemplateMappers {

    private GraphTemplateMapper graphTemplateMapper;
    private SeriesTemplateMapper seriesTemplateMapper;

    @Autowired
    public TestTemplateMappers(GraphTemplateMapper graphTemplateMapper, SeriesTemplateMapper seriesTemplateMapper) {
        this.graphTemplateMapper = graphTemplateMapper;
        this.seriesTemplateMapper = seriesTemplateMapper;
    }

    @Test
    @DisplayName("test_templates")
    public void test1() {
        GraphTemplate graphTemplate = graphTemplateMapper.getTemplateByGraphTypeId(1);
        Assertions.assertNotNull(graphTemplate);
        SeriesTemplate seriesTemplate = seriesTemplateMapper.getTemplateByGraphTypeId(1);
        Assertions.assertNotNull(seriesTemplate);
    }
}
