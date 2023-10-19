# reminder

## TODO

定义折线图数据库，通过用户jwt，图形id获取图形，使用json储存图形配置，建立数据库模型

* 注册用户名提前验证
* 使用validator[校验配置文件](https://blog.csdn.net/jianzhang11/article/details/108332727)，检查是否有jwt私钥
* 使用validator检查请求参数、请求体等数据，配合注解`@Validated` `@Notblank`等使用
    * 更新图形时的数据校验
* 通过注解确定是否使用登录拦截器，拦截器中通过handler可以拿到方法，进而拿到注解
* mybatis通过typeHandler处理数据库中的json数据
* springboot使用全参数构造函数之后省略`@Autowired`
* 集中处理异常
    * 数据库连接错误 等等
* 处理jwt过期问题
* 配置测试和开发使用不同数据库
* 使用lombok @AllArgsConstructor 配合final属性进行注入
