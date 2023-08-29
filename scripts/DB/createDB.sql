-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema online_charts_dev
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `online_charts_dev` ;

-- -----------------------------------------------------
-- Schema online_charts_dev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `online_charts_dev` DEFAULT CHARACTER SET utf8 ;
USE `online_charts_dev` ;

-- -----------------------------------------------------
-- Table `online_charts_dev`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`user` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` CHAR(64) NOT NULL COMMENT '使用bcrypt加密后的密码',
  `status` INT(11) NOT NULL DEFAULT '1' COMMENT '1-正常 0-删除 2-禁用',
  `created_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE,
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `online_charts_dev`.`graphs_meta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`graphs_meta` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`graphs_meta` (
  `graph_type_id` INT(11) NOT NULL,
  `name` VARCHAR(50) NOT NULL COMMENT '图名，如折线图',
  PRIMARY KEY (`graph_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `online_charts_dev`.`user2graph_map`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`user2graph_map` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`user2graph_map` (
  `created_graph_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户创建的某张图的id',
  `user_id` INT(11) NOT NULL COMMENT '用户ID',
  `graph_type_id` INT(11) NOT NULL COMMENT '图形的种类',
  `graph_name` VARCHAR(255) NOT NULL DEFAULT '新图形' COMMENT '图形名称',
  `created_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `data` JSON NULL,
  PRIMARY KEY (`created_graph_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `graph_type_id` (`graph_type_id` ASC) VISIBLE,
  CONSTRAINT `user2graph_map_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `online_charts_dev`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user2graph_map_ibfk_2`
    FOREIGN KEY (`graph_type_id`)
    REFERENCES `online_charts_dev`.`graphs_meta` (`graph_type_id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '存储所有已创建图形的记录，';


-- -----------------------------------------------------
-- Table `online_charts_dev`.`bar_graph_category_conf_set`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`bar_graph_category_conf_set` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`bar_graph_category_conf_set` (
  `bar_series_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '某个数据集合',
  `created_graph_id` INT(11) NOT NULL COMMENT '属于那个图，外键，链接到user2graph_map表',
  `created_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `series_name` VARCHAR(255) NOT NULL DEFAULT '新系列',
  `bar_width` VARCHAR(10) NOT NULL,
  `label` JSON NULL DEFAULT NULL,
  `color` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`bar_series_id`),
  INDEX `created_graph_id` (`created_graph_id` ASC) VISIBLE,
  CONSTRAINT `bar_graph_category_conf_set_ibfk_1`
    FOREIGN KEY (`created_graph_id`)
    REFERENCES `online_charts_dev`.`user2graph_map` (`created_graph_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '类型柱状图中每个series的配置';


-- -----------------------------------------------------
-- Table `online_charts_dev`.`bar_graph_category_config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`bar_graph_category_config` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`bar_graph_category_config` (
  `created_graph_id` INT(11) NOT NULL,
  `title` JSON NOT NULL,
  `animation` TINYINT(4) NOT NULL DEFAULT '0',
  `div_height` INT(11) NOT NULL DEFAULT '500',
  `w2h_ratio` FLOAT NOT NULL DEFAULT '1.33',
  PRIMARY KEY (`created_graph_id`),
  CONSTRAINT `bar_graph_category_config_ibfk_1`
    FOREIGN KEY (`created_graph_id`)
    REFERENCES `online_charts_dev`.`user2graph_map` (`created_graph_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '类型柱状图中每个图的配置文件';


-- -----------------------------------------------------
-- Table `online_charts_dev`.`bar_graph_category_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`bar_graph_category_data` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`bar_graph_category_data` (
  `bar_series_id` INT(11) NOT NULL COMMENT '链接到bar_graph_category_conf_set',
  `data_category` VARCHAR(255) NOT NULL COMMENT 'category',
  `data_value` FLOAT NOT NULL COMMENT 'value',
  `order` INT(11) NOT NULL COMMENT '第几个柱子',
  INDEX `bar_series_id` (`bar_series_id` ASC) VISIBLE,
  PRIMARY KEY (`bar_series_id`, `order`),
  CONSTRAINT `bar_graph_category_data_ibfk_1`
    FOREIGN KEY (`bar_series_id`)
    REFERENCES `online_charts_dev`.`bar_graph_category_conf_set` (`bar_series_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '每个series所包含的数据';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
