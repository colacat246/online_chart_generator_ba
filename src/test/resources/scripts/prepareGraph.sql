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
  "series": [
    {
      "name": "语文",
      "type": "bar",
      "barWidth": "30px",
      "label": {
        "show": true,
        "rotate": 30,
        "position": "outside"
      },
      "color": {
        "type": "linear",
        "x": 0,
        "y": 0,
        "x2": 0,
        "y2": 1,
        "colorStops": [
          {
            "offset": 0,
            "color": "gold"
          },
          {
            "offset": 1,
            "color": "red"
          }
        ]
      }
    },
    {
      "name": "数学",
      "type": "bar",
      "barWidth": "30px",
      "label": {
        "show": true,
        "rotate": 30,
        "position": "outside"
      },
      "color": {
        "type": "radial",
        "x": 0.5,
        "y": 0.5,
        "r": 1.5,
        "colorStops": [
          {
            "offset": 0,
            "color": "cyan"
          },
          {
            "offset": 1,
            "color": "green"
          }
        ]
      }
    }
  ]
}
');

insert into series_template (id, graph_type_id, data) values (1, 1, '
{
  "$extra": {
    "id": 0
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
    1.5,
    5.8,
    7.4,
    10
  ],
  "name": "图1：第1组数据",
  "$extra": {
    "id": 1
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
        "id": 1
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
        "id": 2
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    }
  ],
  "$extra": {
    "id": 1,
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
        "id": 3
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
        "id": 4
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
        "id": 5
      },
      "label": {
        "show": true,
        "position": "outside"
      },
      "type": "bar"
    }
  ],
  "$extra": {
    "id": 2,
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
    "id": "fbb02f03-6657-4040-972e-9d4443eae9cc",
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
        "id": "11"
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
        "id": "12"
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