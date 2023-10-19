# readme

## 项目描述

这是[快速在线编辑图表的工具网站](https://github.com/colacat246/online_chart_generator_fr)的后端模块，用于给前端功能提供相应后台服务

## 项目安装及启动

### 前端项目

见项目描述

### 环境

* java17.0.8+
* mysql5.7.36+

### 安装

* 执行`scripts/install/install.sh`安装数据库数据
* 配置环境jwt私钥
* `mvn package`打包jar
* 拷贝`scripts/run/`下脚本至jar包同级目录
* `starter.sh`启动程序 / `stopper.sh`停止程序
* 初始用户`admin`中可调整模板，默认密码为`123456`

## 技术栈

* Java
* SpringBoot
* Mysql

## 文件说明

* `scripts/DB`保存数据库相关model和创建脚本
* `scripts/run`为程序启动和停止脚本
* `src/test/resources/scripts`保存开发及测试的数据库数据

## 构建开发环境

* 执行`scripts/DB/installDB.sh`创建数据库
* 执行`src/test/resources/scripts`注入数据
* 接口文档见`API.md`

## 数据库表说明

* user：用户表
* graphs_template：不包含series数据部分的图形模板
* series_template：series数据部分的图形模板
* user2graph_map：用户所创建的图形数据

## 试用

[点击链接玩一玩吧](http://8.134.162.35:7800/)
