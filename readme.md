# readme

## 数据库结构

### 查图形数据流程

* user表放用户
* graphs_meta表放每种图形的一些整体配置
* user2graph_map建立用户和所创建图形之间的映射
* 通过所创建图形的id和种类查询对应的表，找到数据集、数据集配置、图配置

## 接口文档

### 登录

请求地址：`/api/login`
请求方法：get

请求参数：

| 字段       | 说明  | 类型     | 备注 | 是否必填 |
|----------|-----|--------|----|------|
| userName | 用户名 | String |    | 是    |
| password | 密码  | String |    | 是    |

返回参数：

| 字段              | 说明              | 类型     | 备注                         | 是否必填                                                                  |
|-----------------|-----------------|--------|----------------------------|-----------------------------------------------------------------------|
| statusCode      | 接口状态码           | Number | 成功：999；空参数：1000； 验证失败：1001 | 是                                                                     |
| message         | 接口信息            | String | 成功：'success' 失败：提示信息       | 是                                                                     |
| userVo          | 用户数据，包括用户id和用户名 | Object | 是                          |                                                                       |
| user-auth-token | JWT Token       | String | 是                          | 有用户ID userId: Number、用户名 userName: String、过期时间 exp: Number，cookie形式获取 |

### 请求侧边栏

请求地址：`/api/userGraphList`
请求方法：get

请求Cookie：

| 字段              | 说明        | 类型     | 备注                        | 是否必填 |
|-----------------|-----------|--------|---------------------------|------|
| user-auth-token | 用户验证Jwt数据 | String | header中x-www-auth-token携带 | 是    |

请求参数：无

返回参数：

| 字段         | 说明    | 类型              | 备注                          | 是否必填 |
|------------|-------|-----------------|-----------------------------|------|
| statusCode | 接口状态码 | Number          | 成功：999；失败：{1000, 1001, 1002 | 是    |
| message    | 接口信息  | String          | 成功：'OK' 失败：提示信息             | 是    |
| asideList  | 数据    | list<asideItem> |                             | 是    |

asideItem:

| 字段             | 说明    | 类型     | 备注                      | 是否必填 |
|----------------|-------|--------|-------------------------|------|
| createdGraphId | 序号    | Number |                         | 是    |
| graphName      | 图形名称  | String |                         | 是    |
| createdTime    | 创建时间  | String | yyyy-MM-dd HH:mm:ss:SSS | 是    |
| graphTypeId    | 图形的种类 | Number | 这个种类设置在前端配置中            | 是    |

### 折线图数据

请求地址：`/api/userGraph/{createdGraphId}`
请求方法：get

请求参数：

| 字段             | 说明  | 类型     | 备注 | 是否必填 |
|----------------|-----|--------|----|------|
| createdGraphId | 图id | Number |    | 是    |

返回参数：

| 字段         | 说明    | 类型     | 备注                   | 是否必填 |
|------------|-------|--------|----------------------|------|
| statusCode | 接口状态码 | Number | 成功：200；失败：404        | 是    |
| message    | 接口信息  | String | 成功：'success' 失败：提示信息 | 是    |
| data       | 数据    | json   |                      |      |
| field1     |       |        |                      |      |
| field2     |       |        |                      |      |

data:

| 字段   | 说明 | 类型     | 备注 | 是否必填 |
|------|----|--------|----|------|
| page | 页号 | Number |    | 是    |
| size | 大小 | Number |    | 是    |

## TODO

* 使用validator[校验配置文件](https://blog.csdn.net/jianzhang11/article/details/108332727)，检查是否有jwt私钥

### template

请求地址：`/api/path`
请求方法：get

请求参数：

| 字段     | 说明 | 类型 | 备注 | 是否必填 |
|--------|----|----|----|------|
| field1 |    |    |    |      |
| field2 |    |    |    |      |

返回参数：

| 字段         | 说明    | 类型     | 备注                   | 是否必填 |
|------------|-------|--------|----------------------|------|
| statusCode | 接口状态码 | Number | 成功：200；失败：404        | 是    |
| message    | 接口信息  | String | 成功：'success' 失败：提示信息 | 是    |
| data       | 数据    | json   |                      |      |
| field1     |       |        |                      |      |
| field2     |       |        |                      |      |

data:

| 字段   | 说明 | 类型     | 备注 | 是否必填 |
|------|----|--------|----|------|
| page | 页号 | Number |    | 是    |
| size | 大小 | Number |    | 是    |

## TODO

定义折线图数据库，通过用户jwt，图形id获取图形

* 使用validator[校验配置文件](https://blog.csdn.net/jianzhang11/article/details/108332727)，检查是否有jwt私钥
* 使用validator检查请求参数、请求体等数据
* 通过注解确定是否使用登录拦截器，拦截器中通过handler可以拿到方法，进而拿到注解
* Bean提到初始化时赋值
* 调整cookie过期时间
* 处理jwt过期问题
* 配置测试和开发使用不同数据库