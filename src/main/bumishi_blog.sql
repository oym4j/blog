/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : bumishi_blog

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-12-30 20:42:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog_views`
-- ----------------------------
DROP TABLE IF EXISTS `blog_views`;
CREATE TABLE `blog_views` (
  `id` varchar(255) DEFAULT NULL,
  `views` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_views
-- ----------------------------
INSERT INTO `blog_views` VALUES ('1', '1');
