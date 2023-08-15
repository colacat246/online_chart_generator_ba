insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name) values (1, 2, 2, '柱状图1');
insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name) values (2, 2, 2, '柱状图2');

insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (1, 1, '图1：第1组数据');
insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (2, 1, '图1：第2组数据');

insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (3, 2, '图2：第1组数据');
insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (4, 2, '图2：第2组数据');
insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name) values (5, 2, '图2：第3组数据');


insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴1', 1.5, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴2', 5.8, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴3', 7.4, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (1, '横轴4', 10.0, 4);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴1', 3.5, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴2', 1.2, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴3', 6.4, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (2, '横轴4', 3.0, 4);

insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (3, '横轴1', 1.5, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (3, '横轴2', 5.8, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (3, '横轴3', 7.4, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (4, '横轴1', 10.0, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (4, '横轴2', 3.5, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (4, '横轴3', 1.2, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (5, '横轴1', 6.4, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (5, '横轴2', 3.0, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`) values (5, '横轴3', 3.0, 3);

insert into bar_graph_category_config (created_graph_id, title_show, title_left) values (1, true, 'left');
insert into bar_graph_category_config (created_graph_id, title_show, title_left) values (2, false, 'center');