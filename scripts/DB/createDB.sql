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
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `online_charts_dev`.`graph_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`graph_template` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`graph_template` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `graph_type_id` INT(11) NOT NULL,
  `data` JSON NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `graph_type_id_UNIQUE` (`graph_type_id` ASC) VISIBLE)
ENGINE = InnoDB;


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
  `data` JSON NOT NULL,
  PRIMARY KEY (`created_graph_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `fk2_idx` (`graph_type_id` ASC) VISIBLE,
  UNIQUE INDEX `created_graph_id_UNIQUE` (`created_graph_id` ASC) VISIBLE,
  CONSTRAINT `user2graph_map_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `online_charts_dev`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk2`
    FOREIGN KEY (`graph_type_id`)
    REFERENCES `online_charts_dev`.`graph_template` (`graph_type_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '存储所有已创建图形的记录，';


-- -----------------------------------------------------
-- Table `online_charts_dev`.`series_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online_charts_dev`.`series_template` ;

CREATE TABLE IF NOT EXISTS `online_charts_dev`.`series_template` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `graph_type_id` INT NOT NULL,
  `data` JSON NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `graph_type_id_UNIQUE` (`graph_type_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk1`
    FOREIGN KEY (`graph_type_id`)
    REFERENCES `online_charts_dev`.`graph_template` (`graph_type_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
