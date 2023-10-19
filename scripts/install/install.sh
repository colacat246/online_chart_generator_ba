#!/bin/bash

echo "enter sql user name"
read -r sql_name
echo "enter sql password"
read -r -s sql_pw

# 建立dev数据库
mysql -u "$sql_name" -p"$sql_pw" < create_DB.sql
mysql -u "$sql_name" -p"$sql_pw" < inject_data.sql
