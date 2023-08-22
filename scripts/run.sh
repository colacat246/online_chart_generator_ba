#/bin/bash

test -e createDB_modified.sql && rm createDB_modified.sql

# mysql5.7不支持VISIBLE关键字
cat createDB.sql | sed 's/ VISIBLE//' > createDB_modified.sql

# 添加meta信息
echo "# meta信息" >> createDB_modified.sql
echo "insert into graphs_meta (graph_type_id, name)" >> createDB_modified.sql
echo "values (1, 'line graph');                    " >> createDB_modified.sql
echo "insert into graphs_meta (graph_type_id, name)" >> createDB_modified.sql
echo "values (2, 'bar graph:category');            " >> createDB_modified.sql
echo "# TODO 创建默认用户，管理默认样式" >> createDB_modified.sql
echo "# TODO 第一行为默认样式配置" >> createDB_modified.sql

# 建立dev数据库
mysql -u cv -p123456 < createDB_modified.sql
# 复制dev数据库，建立test数据库
mysqldump online_charts_dev -u cv -p123456 | mysql online_charts_test -u cv -p123456
