/*
 Navicat Premium Data Transfer

 Source Server         : modelios
 Source Server Type    : SQLite
 Source Server Version : 3030001
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3030001
 File Encoding         : 65001

 Date: 24/07/2024 17:11:24
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "sqlite_sequence";
CREATE TABLE "sqlite_sequence" (
  "name" ,
  "seq" 
);

-- ----------------------------
-- Table structure for t_datesource
-- ----------------------------
DROP TABLE IF EXISTS "t_datesource";
CREATE TABLE "t_datesource" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "driver_class_name" TEXT,
  "url" TEXT,
  "username" TEXT,
  "password" TEXT
);

-- ----------------------------
-- Auto increment value for t_datesource
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 9 WHERE name = 't_datesource';

PRAGMA foreign_keys = true;
