/*
 Navicat Premium Data Transfer

 Source Server         : user-center
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 28/04/2024 14:20:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_account` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录账号',
  `avatar_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `user_password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `phone` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_status` int(11) NOT NULL DEFAULT 0 COMMENT '用户状态 0 - 正常',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `user_role` int(11) NOT NULL DEFAULT 1 COMMENT '0 - 管理员 1 - 普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10, 'dog', 'admin', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-01-20 20:53:35', '2024-04-16 16:32:27', 0, 0);
INSERT INTO `user` VALUES (11, 'yvpi', 'admin01', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-01-21 15:20:05', '2024-04-16 16:32:28', 0, 0);
INSERT INTO `user` VALUES (12, 'dogs', 'admin02', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-01-21 20:12:12', '2024-04-16 16:32:29', 0, 0);
INSERT INTO `user` VALUES (13, NULL, 'dogyvpi', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-25 21:13:23', '2024-04-16 16:32:30', 0, 0);
INSERT INTO `user` VALUES (14, NULL, 'dogyvpi2', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-25 21:14:30', '2024-04-16 16:32:31', 0, 0);
INSERT INTO `user` VALUES (16, NULL, 'dogyvpi4', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:12:06', '2024-04-16 16:32:32', 0, 0);
INSERT INTO `user` VALUES (17, NULL, 'dogyvpi5', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:19:26', '2024-04-16 16:32:36', 0, 0);
INSERT INTO `user` VALUES (18, NULL, 'dogyvpi6', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:20:52', '2024-04-16 16:32:49', 0, 0);
INSERT INTO `user` VALUES (19, NULL, 'dogyvpi7', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:23:01', '2024-03-26 20:23:01', 0, 1);
INSERT INTO `user` VALUES (20, NULL, 'dogyvpi8', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:23:36', '2024-03-26 20:23:36', 0, 1);
INSERT INTO `user` VALUES (21, NULL, 'dogyvpi9', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:34:16', '2024-03-26 20:34:16', 0, 1);
INSERT INTO `user` VALUES (22, NULL, 'dogyvpi10', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:35:11', '2024-03-26 20:35:11', 0, 1);
INSERT INTO `user` VALUES (23, NULL, 'dogyvpi13', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:35:54', '2024-03-26 20:35:54', 0, 1);
INSERT INTO `user` VALUES (24, NULL, 'douyvpi012', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:48:19', '2024-03-26 20:48:19', 0, 1);
INSERT INTO `user` VALUES (25, NULL, 'douyvpi013', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:48:32', '2024-03-26 20:48:32', 0, 1);
INSERT INTO `user` VALUES (26, NULL, 'douyvpi014', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:49:20', '2024-03-26 20:49:20', 0, 1);
INSERT INTO `user` VALUES (27, NULL, 'douyvpi015', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:50:25', '2024-04-15 19:24:54', 1, 1);
INSERT INTO `user` VALUES (29, NULL, 'douyvpi017', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-03-26 20:51:44', '2024-04-15 19:19:14', 1, 1);
INSERT INTO `user` VALUES (30, NULL, 'admin99', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-04-16 13:20:12', '2024-04-16 13:20:12', 0, 1);
INSERT INTO `user` VALUES (31, NULL, 'yupi123', NULL, NULL, 'MTIzNDU2', NULL, NULL, 0, '2024-04-22 15:14:16', '2024-04-22 15:14:16', 0, 1);
INSERT INTO `user` VALUES (32, NULL, 'yvpi123', NULL, NULL, 'MTIzMTIz', NULL, NULL, 0, '2024-04-22 15:16:34', '2024-04-22 15:16:34', 0, 1);
INSERT INTO `user` VALUES (33, NULL, 'yvpi123123', NULL, NULL, 'MTIzMTIz', NULL, NULL, 0, '2024-04-22 15:27:34', '2024-04-22 15:27:34', 0, 1);
INSERT INTO `user` VALUES (34, NULL, 'yvpi1233', NULL, NULL, 'MTIzMTIz', NULL, NULL, 0, '2024-04-22 15:29:55', '2024-04-22 15:29:55', 0, 1);
INSERT INTO `user` VALUES (35, NULL, 'adminsadf', NULL, NULL, 'YXNkYXNk', NULL, NULL, 0, '2024-04-23 15:03:54', '2024-04-23 15:03:54', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
