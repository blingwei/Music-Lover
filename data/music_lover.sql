/*
 Navicat Premium Data Transfer

 Source Server         : aa
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : music_lover

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 03/12/2019 11:21:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (16, 'test', '872acb223a4ce507a554af4017eeb3d3', 'ceTfs1y38FrRLBnG+9VncA==');

SET FOREIGN_KEY_CHECKS = 1;

create table `essay_song` (
	`id` int(11) not null AUTO_INCREMENT,
	`song_id` int(11) not null,
	`eass_id` int(11) not null,
	`user_id` int(11) not null,
	`create_time`  datetime not null,
	`heat` int(11) DEFAULT 0 comment '热度',
	`pick_num` int(11) DEFAULT 0 comment '点赞数',
	`rewoard` int(11) DEFAULT 0 comment '打赏值',
	`commit_num` int(11) DEFAULT 0 comment '评论数',
	`collect_num` int(11) DEFAULT 0 comment '收藏数',
	`views` int(11) DEFAULT 0 comment "浏览量",
	PRIMARY KEY (`id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;   


create table `essay` (
	`id` int(11) not null AUTO_INCREMENT,
	`title` varchar(100) character set utf8mb4 not null comment '标题',
	`intor` varchar(256) character set utf8mb4 not null comment '简介',
	`content` longtext character set utf8mb4 not null comment '内容',
	primary key (`id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8; 

create table `commit` (
	`id` int(11) not null AUTO_INCREMENT,
	`user_id` int(11) not null,
	`matter_id` int(11) not null comment '操作的对象的id',
	`type` tinyint(4) not null comment '操作的对象的类型',
	`pid` int(11) not null default '0' comment '评论的父id',
	`reply_id` int(11) not null default '0' comment '回复的人id',
	`create_time` datetime not null ,
	`content` varchar(255) not null default '',
	`pick_num` int(11) not null default '0' comment '点赞数',
	primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table `user_pick` (
	`id` int(11) not null AUTO_INCREMENT,
	`user_id` int(11) not null,
	`matter_id` int(11) not null comment '操作的对象的id',
	`type` tinyint(4) not null comment '操作的对象的类型',
	`create_time` datetime not null ,
	`is_delete` int(1) default 0 not null comment '是否删除',
	`status` tinyint(4) not null comment '状态，1为点赞，0为取消点赞',
	primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
