/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 28/08/2018 17:13:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'password', 'admin');
INSERT INTO `sys_user` VALUES (2, '123456', 'zhangfeng');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `hobby` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 1, 'football');
INSERT INTO `user` VALUES (2, 'tom', 1, 'football');
INSERT INTO `user` VALUES (3, 'tom', 1, 'football');
INSERT INTO `user` VALUES (4, 'tom', 1, 'football');
INSERT INTO `user` VALUES (5, 'tom', 1, 'football');
INSERT INTO `user` VALUES (6, 'tom', 1, 'football');
INSERT INTO `user` VALUES (7, 'tom', 1, 'football');
INSERT INTO `user` VALUES (8, 'tom', 1, 'football');
INSERT INTO `user` VALUES (9, 'tom1111', 34, 'zhangfeng');

SET FOREIGN_KEY_CHECKS = 1;
