#/bin/bash

test -e createDB_modified.sql && rm createDB_modified.sql

# mysql5.7不支持VISIBLE关键字
cat createDB.sql | sed 's/ VISIBLE//' > createDB_modified.sql

# 建立dev数据库
mysql -u cv -p123456 < createDB_modified.sql
# 复制dev数据库，建立test数据库
mysqldump online_charts_dev -u cv -p123456 | mysql online_charts_test -u cv -p123456
