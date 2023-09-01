delete from graph_template;
delete from series_template;
# 模板
insert into graph_template (id, graph_type_id, data)
values (1, 1, '{
  "$extra": {
    "graphTypeId": 1,
    "divHeight": 800,
    "w2hRatio": 1.33
  },
  "series": [],
  "title": {
    "show": true,
    "textStyle": {
      "color": "#000",
      "fontWeight": "bold",
      "fontSize": 18
    },
    "left": "center",
    "top": 23
  },
  "grid": {
    "show": false,
    "left": "18%",
    "top": "15%",
    "borderWidth": 1.5,
    "borderColor": "#666"
  },
  "height": "70%",
  "width": "70%",
  "legend": {
    "show": true,
    "orient": "horizontal",
    "top": 22,
    "right": 22
  },
  "xAxis": {
    "name": "x轴标题",
    "nameLocation": "middle",
    "nameTextStyle": {
      "color": "#000",
      "fontStyle": "normal",
      "fontFamily": "times new roman",
      "fontSize": 18,
      "fontWeight": "normal",
      "align": "center",
      "lineHeight": 20,
      "padding": 10,
      "rich": {}
    },
    "type": "value",
    "position": "bottom",
    "axisLine": {
      "show": true,
      "lineStyle": {
        "color": "#666",
        "width": 1.5,
        "type": "solid"
      },
      "symbol": [
        "none",
        "none"
      ],
      "symbolSize": [
        7,
        15
      ],
      "symbolOffset": [
        0,
        16
      ]
    },
    "axisTick": {
      "show": true,
      "inside": true,
      "length": 6,
      "lineStyle": {
        "width": 1.5,
        "cap": "butt",
        "color": "#666"
      }
    },
    "splitNumber": 7,
    "minorTick": {
      "show": true,
      "splitNumber": 5,
      "length": 3,
      "lineStyle": {
        "color": "#666",
        "width": 1.5
      }
    },
    "minorSplitLine": {
      "show": false,
      "lineStyle": {
        "color": "#e9e9e9",
        "width": 1
      }
    },
    "splitLine": {
      "show": true,
      "lineStyle": {
        "color": "#ccc",
        "width": 1
      }
    },
    "splitArea": {
      "show": false
    }
  },
  "yAxis": {
    "name": "y轴标题",
    "nameLocation": "middle",
    "nameTextStyle": {
      "color": "#000",
      "fontStyle": "normal",
      "fontFamily": "times new roman",
      "fontSize": 18,
      "fontWeight": "normal",
      "align": "center",
      "lineHeight": 20,
      "padding": 10,
      "rich": {}
    },
    "type": "value",
    "position": "bottom",
    "axisLine": {
      "show": true,
      "lineStyle": {
        "color": "#666",
        "width": 1.5,
        "type": "solid"
      },
      "symbol": [
        "none",
        "none"
      ],
      "symbolSize": [
        7,
        15
      ],
      "symbolOffset": [
        0,
        16
      ]
    },
    "axisTick": {
      "show": true,
      "inside": true,
      "length": 6,
      "lineStyle": {
        "width": 1.5,
        "cap": "butt",
        "color": "#666"
      }
    },
    "splitNumber": 7,
    "minorTick": {
      "show": true,
      "splitNumber": 5,
      "length": 3,
      "lineStyle": {
        "color": "#666",
        "width": 1.5
      }
    },
    "minorSplitLine": {
      "show": false,
      "lineStyle": {
        "color": "#e9e9e9",
        "width": 1
      }
    },
    "splitLine": {
      "show": true,
      "lineStyle": {
        "color": "#ccc",
        "width": 1
      }
    },
    "splitArea": {
      "show": false
    }
  },
  "animation": false
}
');

insert into graph_template (id, graph_type_id, data) values (2, 2, '
{
  "$extra": {
    "graphTypeId": 2,
    "divHeight": 1000,
    "w2hRatio": 1.3333333333333333
  },
  "animation": false,
  "title": {
    "show": true,
    "textStyle": {
      "color": "#000",
      "fontSize": 14,
      "fontWeight": "normal"
    },
    "left": "center",
    "top": 23
  },
  "xAxis": {
    "type": "category"
  },
  "yAxis": {
    "type": "value"
  },
  "series": []
}
');

insert into series_template (id, graph_type_id, data) values (1, 1, '
{
  "$extra": {
    "id": ""
  },
  "name": "新曲线",
  "data": [
    [],
    []
  ],
  "type": "line",
  "lineStyle": {
    "width": 1.5,
    "type": "solid"
  },
  "symbol": "none",
  "symbolSize": 7,
  "smooth": 0
}
');

insert into series_template (id, graph_type_id, data) values (2, 2, '
{
  "barWidth": "30px",
  "color": "#666666",
  "data": [
    1,
    2,
    3,
    4
  ],
  "name": "series",
  "$extra": {
    "id": ""
  },
  "label": {
    "show": true,
    "position": "outside"
  },
  "type": "bar"
}
');

insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name, data)
values (1, 2, 2, '柱状图1', '
{
  "yAxis": {
    "type": "value"
  },
  "xAxis": {
    "type": "category",
    "data": [
      "横轴1",
      "横轴2",
      "横轴3",
      "横轴4"
    ]
  },
  "series": [
    {
      "barWidth": "30px",
      "color": "#666666",
      "data": [
        1.5,
        5.8,
        7.4,
        10
      ],
      "name": "图1：第1组数据",
      "$extra": {
        "id": "3c5c60a2-9ace-41f8-8bd1-c9e74c7d785a"
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    },
    {
      "barWidth": "30px",
      "color": "#666666",
      "data": [
        3.5,
        1.2,
        6.4,
        3
      ],
      "name": "图1：第2组数据",
      "$extra": {
        "id": "5a0bd736-80bd-4f08-b436-c7fcdee7c0fe"
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    }
  ],
  "$extra": {
    "graphTypeId": 2,
    "divHeight": 1000,
    "w2hRatio": 1.33
  },
  "title": {
    "top": 23,
    "left": "center",
    "show": true,
    "textStyle": {
      "color": "#000",
      "fontSize": 14,
      "fontWeight": "normal"
    }
  },
  "animation": false
}
');
insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name, data)
values (2, 2, 2, '柱状图2', '
{
  "yAxis": {
    "type": "value"
  },
  "xAxis": {
    "type": "category",
    "data": [
      "横轴1",
      "横轴2",
      "横轴3"
    ]
  },
  "series": [
    {
      "barWidth": "30px",
      "color": "#666666",
      "data": [
        1.5,
        5.8,
        7.4
      ],
      "name": "图2：第1组数据",
      "$extra": {
        "id": "a17bcd37-9179-4a7e-9e43-c15a2972771f"
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    },
    {
      "barWidth": "30px",
      "color": "#666666",
      "data": [
        10,
        3.5,
        1.2
      ],
      "name": "图2：第2组数据",
      "$extra": {
        "id": "3bfcae00-6a32-4b6b-b3e0-1bfcdfbc105e"
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    },
    {
      "barWidth": "30px",
      "color": "#666666",
      "data": [
        6.4,
        3,
        3
      ],
      "name": "图2：第3组数据",
      "$extra": {
        "id": "94486b75-5e3e-486b-b3ce-4e42f712e3d0"
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    }
  ],
  "$extra": {
    "graphTypeId": 2,
    "divHeight": 500,
    "w2hRatio": 1.33
  },
  "title": {
    "top": 23,
    "left": "center",
    "show": true,
    "textStyle": {
      "color": "#4475ab",
      "fontSize": 12,
      "fontWeight": "normal"
    }
  },
  "animation": false
}
');

insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name, data)
values (3, 2, 1, '折线图1', '{
  "$extra": {
    "graphTypeId": 1,
    "divHeight": 800,
    "w2hRatio": 1.33
  },
  "title": {
    "show": true,
    "text": "data1",
    "textStyle": {
      "color": "#000",
      "fontWeight": "bold",
      "fontSize": 18
    },
    "left": "center",
    "top": 23
  },
  "grid": {
    "show": false,
    "left": "18%",
    "top": "15%",
    "borderWidth": 1.5,
    "borderColor": "#666"
  },
  "height": "70%",
  "width": "70%",
  "legend": {
    "show": true,
    "orient": "horizontal",
    "top": 22,
    "right": 22
  },
  "series": [
    {
      "$extra": {
        "id": "ff0eebbf-2748-42df-b17d-98a51bca6db4"
      },
      "name": "line 1~~~",
      "type": "line",
      "data": [
        [
          1,
          3
        ],
        [
          2,
          6
        ],
        [
          4,
          19
        ],
        [
          6,
          1
        ]
      ],
      "symbol": "none",
      "symbolSize": 17,
      "lineStyle": {
        "width": 1.5,
        "type": "solid"
      },
      "smooth": 0
    },
    {
      "$extra": {
        "id": "312f4427-27ea-4b4a-b921-753d3ba0d588"
      },
      "name": "myLine 2",
      "type": "line",
      "data": [
        [
          2,
          4
        ],
        [
          5,
          2
        ],
        [
          6,
          11
        ],
        [
          7,
          4
        ]
      ],
      "symbol": "none",
      "symbolSize": 7,
      "lineStyle": {
        "width": 1.5,
        "type": "solid"
      },
      "smooth": 0
    }
  ],
  "xAxis": {
    "name": "x轴内容",
    "nameLocation": "middle",
    "nameTextStyle": {
      "color": "#000",
      "fontStyle": "normal",
      "fontFamily": "times new roman",
      "fontSize": 18,
      "fontWeight": "normal",
      "align": "center",
      "lineHeight": 20,
      "padding": 10,
      "rich": {}
    },
    "type": "value",
    "position": "bottom",
    "axisLine": {
      "show": true,
      "lineStyle": {
        "color": "#666",
        "width": 1.5,
        "type": "solid"
      },
      "symbol": [
        "none",
        "none"
      ],
      "symbolSize": [
        7,
        15
      ],
      "symbolOffset": [
        0,
        16
      ]
    },
    "axisTick": {
      "show": true,
      "inside": true,
      "length": 6,
      "lineStyle": {
        "width": 1.5,
        "cap": "butt",
        "color": "#666"
      }
    },
    "splitNumber": 7,
    "minorTick": {
      "show": true,
      "splitNumber": 5,
      "length": 3,
      "lineStyle": {
        "color": "#666",
        "width": 1.5
      }
    },
    "minorSplitLine": {
      "show": false,
      "lineStyle": {
        "color": "#e9e9e9",
        "width": 1
      }
    },
    "splitLine": {
      "show": true,
      "lineStyle": {
        "color": "#ccc",
        "width": 1
      }
    },
    "splitArea": {
      "show": false
    }
  },
  "yAxis": {
    "name": "y轴内容",
    "nameLocation": "middle",
    "nameTextStyle": {
      "color": "#000",
      "fontStyle": "normal",
      "fontFamily": "times new roman",
      "fontSize": 18,
      "fontWeight": "normal",
      "align": "center",
      "lineHeight": 20,
      "padding": 10,
      "rich": {}
    },
    "type": "value",
    "position": "bottom",
    "axisLine": {
      "show": true,
      "lineStyle": {
        "color": "#666",
        "width": 1.5,
        "type": "solid"
      },
      "symbol": [
        "none",
        "none"
      ],
      "symbolSize": [
        7,
        15
      ],
      "symbolOffset": [
        0,
        16
      ]
    },
    "axisTick": {
      "show": true,
      "inside": true,
      "length": 6,
      "lineStyle": {
        "width": 1.5,
        "cap": "butt",
        "color": "#666"
      }
    },
    "splitNumber": 7,
    "minorTick": {
      "show": true,
      "splitNumber": 5,
      "length": 3,
      "lineStyle": {
        "color": "#666",
        "width": 1.5
      }
    },
    "minorSplitLine": {
      "show": false,
      "lineStyle": {
        "color": "#e9e9e9",
        "width": 1
      }
    },
    "splitLine": {
      "show": true,
      "lineStyle": {
        "color": "#ccc",
        "width": 1
      }
    },
    "splitArea": {
      "show": false
    }
  },
  "animation": false
}');