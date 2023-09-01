#!/bin/bash

mysql -u cv -p123456 -D online_charts_dev < deleteAll.sql
mysql -u cv -p123456 -D online_charts_dev < prepareUser.sql
mysql -u cv -p123456 -D online_charts_dev < prepareGraph.sql
