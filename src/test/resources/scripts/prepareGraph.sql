insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name)
values (1, 2, 2, '柱状图1');
insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name)
values (2, 2, 2, '柱状图2');

insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name, bar_width, label, color)
values (1, 1, '图1：第1组数据', '30px', '{
  "show": true,
  "position": "outside"
}', '#666666');
insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name, bar_width, label, color)
values (2, 1, '图1：第2组数据', '30px', '{
  "show": true,
  "position": "outside"
}', '#666666');

insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name, bar_width, label, color)
values (3, 2, '图2：第1组数据', '30px', '{
  "show": true,
  "position": "outside"
}', '#666666');
insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name, bar_width, label, color)
values (4, 2, '图2：第2组数据', '30px', '{
  "show": true,
  "position": "outside"
}', '#666666');
insert into bar_graph_category_conf_set (bar_series_id, created_graph_id, series_name, bar_width, label, color)
values (5, 2, '图2：第3组数据', '30px', '{
  "show": true,
  "position": "outside"
}', '#666666');


insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (1, '横轴1', 1.5, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (1, '横轴2', 5.8, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (1, '横轴3', 7.4, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (1, '横轴4', 10.0, 4);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (2, '横轴1', 3.5, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (2, '横轴2', 1.2, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (2, '横轴3', 6.4, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (2, '横轴4', 3.0, 4);

insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (3, '横轴1', 1.5, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (3, '横轴2', 5.8, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (3, '横轴3', 7.4, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (4, '横轴1', 10.0, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (4, '横轴2', 3.5, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (4, '横轴3', 1.2, 3);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (5, '横轴1', 6.4, 1);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (5, '横轴2', 3.0, 2);
insert into bar_graph_category_data (bar_series_id, data_category, data_value, `order`)
values (5, '横轴3', 3.0, 3);

insert into bar_graph_category_config (created_graph_id, title, animation, div_height, w2h_ratio)
values (1, '{
  "show": true,
  "textStyle": {
    "color": "#000",
    "fontSize": 14,
    "fontWeight": "normal",
    "left": "center",
    "top": 23
  }
}', false, 1000, 1.33);
insert into bar_graph_category_config (created_graph_id, title, animation, div_height, w2h_ratio)
values (2, '{
  "show": true,
  "textStyle": {
    "color": "#4475ab",
    "fontSize": 12,
    "fontWeight": "normal",
    "left": "center",
    "top": 23
  }
}', false, 500, 1.33);
