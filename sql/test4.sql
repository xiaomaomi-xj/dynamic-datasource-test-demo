/*
 Navicat Premium Data Transfer

 Source Server         : xiaomaomi-xj
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : test4

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 10/01/2023 10:25:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'slave3');
INSERT INTO `user` VALUES (2, 'slave3-2');
INSERT INTO `user` VALUES (3, 'slave3-3');
INSERT INTO `user` VALUES (4, 'slave3-4');
INSERT INTO `user` VALUES (5, 'slave3-5');
INSERT INTO `user` VALUES (6, 'slave3-6');

SET FOREIGN_KEY_CHECKS = 1;
