# readme

## 数据库结构

### 查图形数据流程

* user表放用户
* graphs_meta表放每种图形的一些整体配置
* user2graph_map建立用户和所创建图形之间的映射
* 通过所创建图形的id和种类查询对应的表，找到数据集、数据集配置、图配置

## 接口文档

### 请求侧边栏

请求地址：`/api/aside`

请求参数：

| 字段      | 说明        | 类型     | 备注 | 是否必填 |
|---------|-----------|--------|----|------|
| userJwt | 用户验证Jwt数据 | String |    | 是    |
|         |           |        |    |      |

返回参数：

| 字段        | 说明    | 类型              | 备注                                                 | 是否必填 |
|-----------|-------|-----------------|----------------------------------------------------|------|
| code      | 接口状态码 | Number          | 成功：200；失败：{400: 请求语法错误, 403: 用户验证失败, 404: 用户不存在, } | 是    |
| message   | 接口信息  | String          | 成功：'success' 失败：提示信息                               | 是    |
| asideList | 数据    | list<asideItem> |                                                    | 是    |

asideItem:

| 字段          | 说明    | 类型     | 备注                      | 是否必填 |
|-------------|-------|--------|-------------------------|------|
| id          | 序号    | Number |                         | 是    |
| name        | 图形名称  | String |                         | 是    |
| createdTime | 创建时间  | String | yyyy-MM-dd HH:mm:ss:SSS | 是    |
| typeId      | 图形的种类 | Number | 这个种类设置在前端配置中            | 是    |


### template

请求地址：`/api/path`

请求参数：

| 字段     | 说明 | 类型 | 备注 | 是否必填 |
|--------|----|----|----|------|
| field1 |    |    |    |      |
| field2 |    |    |    |      |

返回参数：

| 字段      | 说明    | 类型     | 备注                   | 是否必填 |
|---------|-------|--------|----------------------|------|
| code    | 接口状态码 | Number | 成功：200；失败：404        | 是    |
| message | 接口信息  | String | 成功：'success' 失败：提示信息 | 是    |
| data    | 数据    | json   |                      |      |
| field1  |       |        |                      |      |
| field2  |       |        |                      |      |

data:

| 字段   | 说明 | 类型     | 备注 | 是否必填 |
|------|----|--------|----|------|
| page | 页号 | Number |    | 是    |
| size | 大小 | Number |    | 是    |

