package org.cv.ocb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenFakeData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String[][] fakeUsers = new String[][]{
            {"1", "testUser", "123456"},
            {"2", "tom", "556677"},
            {"3", "peter", "abc123"},
            {"4", "testUser2", "aabbcc"},
            {"5", "testUser3", "abcabc"}
    };

    // 注意：通过数据库级联操作可一并删除所有图
    public void deleteAllUser() {
        jdbcTemplate.update("delete from user");
    }

    public void addAllUser() {
        for (String[] val : fakeUsers) {
            jdbcTemplate.update("insert into user (`user_id`, `name`, `password`) values (?, ?, ?)", Integer.valueOf(val[0]), val[1], val[2]);
        }
    }

    public void addTomsFakeGraph() {
        // 两张柱状图
        jdbcTemplate.update("insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name) values (1, 2, 2, '柱状图1')");
        jdbcTemplate.update("insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name) values (2, 2, 2, '柱状图2')");

        // 第一张柱状图里的两组数据定义
        jdbcTemplate.update("insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (1, 1, '图1：第1组数据')");
        jdbcTemplate.update("insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (2, 1, '图1：第2组数据')");
        // 第二张柱状图里的3组数据
        jdbcTemplate.update("insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (3, 2, '图2：第1组数据')");
        jdbcTemplate.update("insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (4, 2, '图2：第2组数据')");
        jdbcTemplate.update("insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (5, 2, '图2：第3组数据')");

        // 第一张图里的实际数据
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴1', 1.5, 1)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴2', 5.8, 2)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴3', 7.4, 3)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴4', 10.0, 4)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴1', 3.5, 1)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴2', 1.2, 2)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴3', 6.4, 3)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴4', 3.0, 4)");
        // 第二张图里的实际数据
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (3, '横轴1', 1.5, 1)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (3, '横轴2', 5.8, 2)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (3, '横轴3', 7.4, 3)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (4, '横轴1', 10.0, 1)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (4, '横轴2', 3.5, 2)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (4, '横轴3', 1.2, 3)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (5, '横轴1', 6.4, 1)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (5, '横轴2', 3.0, 2)");
        jdbcTemplate.update("insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (5, '横轴3', 3.0, 3)");

        // 整体图配置
        jdbcTemplate.update("insert into bar_graph_category_config (created_graph_id, title_show, title_left) values (1, true, 'left')");
        jdbcTemplate.update("insert into bar_graph_category_config (created_graph_id, title_show, title_left) values (2, false, 'center')");
    }

}
