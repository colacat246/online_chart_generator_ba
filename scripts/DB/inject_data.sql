delete
from graph_template;
delete
from series_template;
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
      "fontWeight": "normal",
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
    "right": 22,
    "itemWidth": 20,
    "itemHeight": 20,
    "textStyle": {
      "fontSize": 20
    }
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

insert into graph_template (id, graph_type_id, data)
values (2, 2, '
{
  "$extra": {
    "graphTypeId": 2,
    "divHeight": 1000,
    "w2hRatio": 1.33
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
    "type": "category",
    "data": []
  },
  "yAxis": {
    "type": "value"
  },
  "series": [],
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
    "right": 22,
    "itemWidth": 20,
    "itemHeight": 20,
    "textStyle": {
      "fontSize": 20
    }
  }
}
');

# PieChart
insert into graph_template (id, graph_type_id, data)
values (3, 3, '
{
  "$extra": {
    "graphTypeId": 3,
    "divHeight": 1000,
    "w2hRatio": 1.33
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
  "series": {
    "type": "pie",
    "radius": "50%",
    "center": [
      "50%",
      "50%"
    ],
    "data": [],
    "label": {
      "show": true,
      "fontSize": 18
    }
  },
  "legend": {
    "show": true,
    "orient": "horizontal",
    "top": 22,
    "right": 22,
    "itemWidth": 20,
    "itemHeight": 20,
    "textStyle": {
      "fontSize": 20
    }
  }
}
');

insert into series_template (id, graph_type_id, data)
values (1, 1, '
{
  "$extra": {
    "id": ""
  },
  "name": "新曲线",
  "data": [
    [],
    []
  ],
  "color": null,
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

insert into series_template (id, graph_type_id, data)
values (2, 2, '
{
  "barWidth": 30,
  "color": "#666666",
  "data": [
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
values (1, 1, 1, '折线图-示范', '{
  "$extra": {
    "graphTypeId": 1,
    "divHeight": 1000,
    "w2hRatio": 1.33
  },
  "title": {
    "show": true,
    "text": "折线图",
    "textStyle": {
      "color": "#000",
      "fontWeight": "bold",
      "fontSize": 50
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
    "right": 22,
    "itemWidth": 20,
    "itemHeight": 20,
    "textStyle": {
      "fontSize": 20
    }
  },
  "series": [
    {
      "$extra": {
        "id": "ff0eebbf-2748-42df-b17d-98a51bca6db4"
      },
      "name": "series_1",
      "type": "line",
      "data": [
        [
          1,
          1
        ],
        [
          2,
          2
        ],
        [
          3,
          3.5
        ],
        [
          4,
          6
        ],
        [
          5,
          9
        ]
      ],
      "symbol": "circle",
      "symbolSize": 13,
      "lineStyle": {
        "width": 2,
        "type": "solid"
      },
      "smooth": 0
    },
    {
      "$extra": {
        "id": "312f4427-27ea-4b4a-b921-753d3ba0d588"
      },
      "name": "series_2",
      "type": "line",
      "data": [
        [
          1,
          1
        ],
        [
          2,
          1.8
        ],
        [
          3,
          2.5
        ],
        [
          4,
          3.6
        ],
        [
          5,
          5.5
        ]
      ],
      "symbol": "arrow",
      "symbolSize": 13,
      "lineStyle": {
        "width": 3,
        "type": "dashed"
      },
      "smooth": 0
    }
  ],
  "xAxis": {
    "name": "x轴",
    "nameLocation": "middle",
    "nameTextStyle": {
      "color": "#000",
      "fontStyle": "normal",
      "fontFamily": "times new roman",
      "fontSize": 25,
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
    "name": "y轴",
    "nameLocation": "middle",
    "nameTextStyle": {
      "color": "#000",
      "fontStyle": "normal",
      "fontFamily": "times new roman",
      "fontSize": 25,
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


insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name, data)
values (2, 1, 2, '柱状图-示范', '
{
  "yAxis": {
    "type": "value"
  },
  "xAxis": {
    "type": "category",
    "data": [
      "DataCol_1",
      "DataCol_2",
      "DataCol_3",
      "DataCol_4"
    ]
  },
  "series": [
    {
      "barWidth": 50,
      "color": "#5470C6",
      "data": [
        1,
        2,
        3,
        4
      ],
      "name": "series_1",
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
      "barWidth": 50,
      "color": "#91CC75",
      "data": [
        2,
        2.5,
        3,
        3.5
      ],
      "name": "series_2",
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
    "show": true,
    "text": "柱状图",
    "textStyle": {
      "color": "#000",
      "fontWeight": "bold",
      "fontSize": 50
    },
    "left": "center",
    "top": 23
  },
  "animation": false,
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
    "right": 22,
    "itemWidth": 20,
    "itemHeight": 20,
    "textStyle": {
      "fontSize": 20
    }
  }
}
');


insert into user2graph_map (created_graph_id, user_id, graph_type_id, graph_name, data)
values (3, 1, 3, '饼图-示范', '
{
  "$extra": {
    "graphTypeId": 3,
    "divHeight": 1000,
    "w2hRatio": 1.33
  },
  "animation": false,
  "title": {
    "text": "饼图",
    "show": true,
    "textStyle": {
      "color": "#000",
      "fontSize": 50,
      "fontWeight": "bold"
    },
    "left": "center",
    "top": 23
  },
  "series": {
    "type": "pie",
    "radius": "50%",
    "center": [
      "50%",
      "50%"
    ],
    "data": [
      {
        "value": 525,
        "name": "data_1",
        "id": "3a1db14a-2c6f-4117-8d95-ce12e051450b"
      },
      {
        "value": 735,
        "name": "data_2",
        "id": "4545287d-847b-4c5b-8840-c7f2bbbe6726"
      },
      {
        "value": 1024,
        "name": "data_3",
        "id": "5984180b-609f-40fc-8bff-33c58f4b6abc"
      },
      {
        "value": 252,
        "name": "data_4",
        "id": "7553deb0-ff50-42b0-b174-3927361b25f3"
      }
    ],
    "label": {
        "show": true,
          "fontSize": 35
    }
  },
  "legend": {
    "show": true,
    "orient": "horizontal",
    "top": 22,
    "right": 22,
    "itemWidth": 20,
    "itemHeight": 20,
    "textStyle": {
      "fontSize": 20
    }
  }
}
');
