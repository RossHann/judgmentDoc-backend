-- MySQL dump 10.13  Distrib 5.7.33, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: JudgmentDoc
-- ------------------------------------------------------
-- Server version	5.7.33-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

DROP DATABASE IF EXISTS `JudgmentDoc`;
CREATE DATABASE JudgmentDoc;
USE JudgmentDoc;

--
-- Table structure for table `user`
--

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user`
(
    `id`       bigint(20)         NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` varchar(64) UNIQUE NOT NULL COMMENT '用户名',
    `password` varchar(64)        NOT NULL COMMENT '密码',
    `role`     int                NOT NULL COMMENT '角色：0法官，1群众',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

BEGIN;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
INSERT INTO user VALUES (1, 'panyue', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 0),
                        (2, 'hanzhuo', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
COMMIT;