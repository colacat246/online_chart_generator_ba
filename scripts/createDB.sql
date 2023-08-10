drop database if exists online_charts;
create database online_charts;
use online_charts;


# 创建用户表
create table user
(
    `user_id`      int                not null primary key auto_increment,
    `name`         varchar(50) unique not null,
    `email`        varchar(255) unique,
    `password`     char(64)           not null comment '使用bcrypt加密后的密码',
    `status`       int                not null default 1 comment '1-正常 0-删除 2-禁用',
    `created_time` timestamp(6)                   default current_timestamp(6)
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# 检查下user表的status
create trigger `t_precheck_on_user`
    before insert
    on `user`
    for each row
begin
    if new.`status` not in (0, 1, 2)
    then
        signal sqlstate '23514' set message_text = '用户status值不正确';
    end if;
end;

# 创建各种图的配置信息，如图名等
create table graphs_meta
(
    `graph_type_id` int         not null primary key,
    `name`          varchar(50) not null comment '图名，如折线图'
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# 每个用户都有哪些图
create table user2graph_map
(
    `created_graph_id` int          not null primary key auto_increment comment '用户创建的某张图的id',
    `user_id`          int          not null comment '用户ID',
    `graph_type_id`    int          not null comment '图形的种类',
    `graph_name`       varchar(255) not null default '新图形' comment '图形名称', # 图形名称放在这里，获取侧边栏数据只查这个表就行
    `created_time`     timestamp(6)             default current_timestamp(6),
    constraint foreign key (`user_id`) references user (`user_id`) on delete cascade on update cascade,
    constraint foreign key (`graph_type_id`) references graphs_meta (`graph_type_id`) on delete restrict on update cascade
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# 一个图有哪些数据集合
create table bar_graph_category_conf_set
(
    `bar_series_id`    int not null primary key auto_increment comment '某个数据集合',
    `created_graph_id` int not null comment '属于那个图，外键，链接到user2graph_map表',
    `series_name`      varchar(255) default '新系列',
    `created_time`     timestamp(6)    default current_timestamp(6),
    # TODO 添加一些柱子的配置
    constraint foreign key (`created_graph_id`) references user2graph_map (`created_graph_id`)
        on delete cascade on update cascade
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# 柱状图的数据
create table bar_graph_category_data
(
    `bar_series_id` int          not null comment '链接到bar_graph_category_conf_set',
    `data_category` varchar(255) not null comment 'category',
    `data_value`    float        not null comment 'value',
    `order`         int          not null comment '第几个柱子',
    constraint foreign key (`bar_series_id`) references user2graph_map (`created_graph_id`)
        on delete cascade on update cascade
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# 柱状图配置
create table bar_graph_category_config
(
    `created_graph_id` int                              not null primary key,
    `title_show`       boolean                          not null default true,
    `title_left`       enum ('left', 'center', 'right') not null default 'center',
    constraint foreign key (`created_graph_id`) references user2graph_map (`created_graph_id`)
        on delete cascade on update cascade
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;


# TODO 折线图的数据
# TODO 建一个每条线的样式配置表，搞一下数据重复问题
create table line_graph_data
(
    `created_graph_id` int   not null,
    `line_id`          int   not null comment '第几条线',
    `data_x`           float not null,
    `data_y`           float not null,
    `order`            int   not null comment '一条线上的第几个数据',
    constraint foreign key (`created_graph_id`) references user2graph_map (`created_graph_id`)
        on delete cascade on update cascade
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# 折线图的样式配置
create table line_graph_config
(
    `created_graph_id` int     not null primary key,
    `title_show`       boolean not null default true,
    constraint foreign key (`created_graph_id`) references user2graph_map (`created_graph_id`)
        on delete cascade on update cascade


) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin;

# TODO 创建默认用户，管理默认样式
# TODO 第一行为默认样式配置

# 测试数据注入
insert into user (`name`, `password`)
values ('test',
        '123456');


select *
from user;
