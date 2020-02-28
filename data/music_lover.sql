/*
 Navicat Premium Data Transfer

 Source Server         : aa
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : music_lover

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 20/02/2020 16:39:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dec` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口路径',
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dec` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `user_id` int(11) NOT NULL,
  `matter_id` int(11) NOT NULL COMMENT '操作的对象的id',
  `type` tinyint(4) NOT NULL COMMENT '操作的对象的类型',
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`user_id`, `matter_id`, `type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (16, 1, 4, '2020-02-13 11:27:36', 0);
INSERT INTO `collect` VALUES (16, 9, 2, '2020-02-08 17:33:04', 0);
INSERT INTO `collect` VALUES (16, 17, 0, '2020-02-13 11:28:58', 1);
INSERT INTO `collect` VALUES (16, 18, 0, '2020-02-13 10:57:34', 0);
INSERT INTO `collect` VALUES (17, 2, 4, '2020-02-17 15:14:54', 0);
INSERT INTO `collect` VALUES (17, 9, 2, '2020-02-05 12:51:16', 0);
INSERT INTO `collect` VALUES (17, 10, 2, '2020-02-08 17:39:08', 0);
INSERT INTO `collect` VALUES (17, 16, 0, '2020-02-11 16:12:08', 0);
INSERT INTO `collect` VALUES (17, 21, 0, '2020-02-16 10:57:36', 0);
INSERT INTO `collect` VALUES (18, 2, 4, '2020-02-13 11:49:21', 0);
INSERT INTO `collect` VALUES (18, 9, 2, '2020-02-08 17:31:24', 0);
INSERT INTO `collect` VALUES (18, 16, 0, '2020-02-12 11:55:03', 0);
INSERT INTO `collect` VALUES (18, 17, 0, '2020-02-12 11:55:02', 0);
INSERT INTO `collect` VALUES (20, 10, 2, '2020-02-12 12:08:28', 0);
INSERT INTO `collect` VALUES (20, 11, 2, '2020-02-12 12:13:19', 1);
INSERT INTO `collect` VALUES (20, 17, 0, '2020-02-12 12:14:23', 0);
INSERT INTO `collect` VALUES (21, 2, 4, '2020-02-14 15:18:24', 0);
INSERT INTO `collect` VALUES (21, 10, 2, '2020-02-14 15:17:04', 0);
INSERT INTO `collect` VALUES (21, 11, 2, '2020-02-13 15:52:09', 0);
INSERT INTO `collect` VALUES (21, 16, 0, '2020-02-14 15:18:10', 0);
INSERT INTO `collect` VALUES (21, 18, 0, '2020-02-14 15:18:17', 1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `matter_id` int(11) NOT NULL COMMENT '操作的对象的id',
  `type` tinyint(4) NOT NULL COMMENT '操作的对象的类型',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '评论的父id',
  `reply_id` int(11) NOT NULL DEFAULT 0 COMMENT '回复的人id',
  `create_time` datetime(0) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `pick_num` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 1, 2, 0, 0, '2019-12-23 17:56:59', '你的心', 0, 0);
INSERT INTO `comment` VALUES (2, 16, 2, 2, 0, 0, '2019-12-25 10:39:26', '水电费', 0, 0);
INSERT INTO `comment` VALUES (3, 16, 2, 2, 0, 0, '2019-12-25 10:40:51', '你的心', 0, 0);
INSERT INTO `comment` VALUES (4, 16, 2, 2, 0, 0, '2019-12-25 14:33:16', '我的心', 0, 0);
INSERT INTO `comment` VALUES (5, 16, 2, 2, 0, 0, '2019-12-26 17:40:51', '水电费吗', 0, 0);
INSERT INTO `comment` VALUES (6, 16, 2, 2, 0, 0, '2019-12-26 17:47:28', '你的心啊', 0, 0);
INSERT INTO `comment` VALUES (7, 16, 2, 2, 0, 0, '2019-12-26 17:51:17', '是我的心', 0, 0);
INSERT INTO `comment` VALUES (8, 16, 2, 2, 7, 0, '2019-12-26 17:52:57', '真的吗', 0, 0);
INSERT INTO `comment` VALUES (9, 16, 2, 2, 7, 0, '2019-12-26 19:21:46', '是真的哦', 0, 0);
INSERT INTO `comment` VALUES (10, 16, 2, 2, 6, 0, '2019-12-26 19:28:22', '你妈的', 0, 0);
INSERT INTO `comment` VALUES (11, 16, 2, 2, 0, 0, '2019-12-26 19:28:43', '这是啥啊', 0, 0);
INSERT INTO `comment` VALUES (12, 16, 2, 2, 11, 0, '2019-12-26 19:28:54', '我也不知道啊', 0, 0);
INSERT INTO `comment` VALUES (15, 17, 2, 2, 6, 16, '2019-12-26 19:51:52', '怎么能说脏话呢\n', 0, 0);
INSERT INTO `comment` VALUES (16, 17, 2, 2, 0, 0, '2019-12-26 19:52:38', '别说了', 0, 0);
INSERT INTO `comment` VALUES (17, 17, 2, 2, 16, 16, '2019-12-26 19:52:48', '说你咋地\n', 0, 0);
INSERT INTO `comment` VALUES (18, 17, 2, 2, 11, 16, '2019-12-26 20:02:41', '那你说什么说', 0, 0);
INSERT INTO `comment` VALUES (19, 17, 2, 2, 16, 16, '2019-12-26 20:02:50', '我就说', 0, 0);
INSERT INTO `comment` VALUES (20, 17, 2, 2, 16, 0, '2019-12-26 20:03:41', '尼玛的', 0, 0);
INSERT INTO `comment` VALUES (21, 17, 2, 2, 11, 17, '2019-12-26 20:03:47', '我就输', 0, 0);
INSERT INTO `comment` VALUES (22, 17, 2, 2, 16, 17, '2019-12-26 20:03:58', '我说额', 0, 0);
INSERT INTO `comment` VALUES (23, 17, 2, 2, 16, 0, '2019-12-26 20:04:04', '什么情况', 0, 0);
INSERT INTO `comment` VALUES (24, 17, 5, 2, 0, 0, '2019-12-30 13:38:13', '这是什么鬼东西\n', 0, 0);
INSERT INTO `comment` VALUES (25, 17, 5, 2, 0, 0, '2019-12-30 13:40:49', '不知奥', 0, 0);
INSERT INTO `comment` VALUES (26, 17, 5, 2, 25, 0, '2019-12-30 13:41:00', '真的吗', 0, 0);
INSERT INTO `comment` VALUES (27, 17, 3, 2, 0, 0, '2019-12-30 16:00:03', '什么东西', 0, 0);
INSERT INTO `comment` VALUES (28, 17, 3, 2, 0, 0, '2019-12-30 16:40:59', '2', 0, 0);
INSERT INTO `comment` VALUES (29, 17, 6, 2, 0, 0, '2020-01-02 17:49:52', 'wobuxing', 0, 0);
INSERT INTO `comment` VALUES (30, 16, 6, 2, 29, 0, '2020-01-02 17:51:21', '你在泥水', 0, 0);
INSERT INTO `comment` VALUES (31, 17, 6, 2, 29, 16, '2020-01-02 17:51:48', 'o ,shima ', 0, 0);
INSERT INTO `comment` VALUES (32, 17, 2, 2, 0, 0, '2020-01-14 18:00:29', 'fd ', 0, 0);
INSERT INTO `comment` VALUES (33, 17, 6, 2, 0, 0, '2020-01-15 15:56:41', 'sdf ', 0, 0);
INSERT INTO `comment` VALUES (34, 17, 7, 2, 0, 0, '2020-01-15 16:34:59', 'sdf ', 0, 0);
INSERT INTO `comment` VALUES (35, 17, 7, 2, 0, 0, '2020-01-15 16:35:03', 'f g', 0, 0);
INSERT INTO `comment` VALUES (36, 17, 9, 2, 0, 0, '2020-01-31 12:13:09', '什么', 0, 0);
INSERT INTO `comment` VALUES (37, 19, 9, 2, 0, 0, '2020-02-07 15:29:05', '不懂', 0, 0);
INSERT INTO `comment` VALUES (38, 19, 9, 2, 36, 0, '2020-02-07 15:29:23', '真是个白痴', 0, 0);
INSERT INTO `comment` VALUES (39, 17, 10, 2, 0, 0, '2020-02-10 11:11:01', '你觉得呢', 0, 0);
INSERT INTO `comment` VALUES (40, 20, 10, 2, 0, 0, '2020-02-12 12:08:09', '真好听', 0, 0);
INSERT INTO `comment` VALUES (41, 20, 10, 2, 39, 0, '2020-02-12 12:08:46', '还好', 0, 0);
INSERT INTO `comment` VALUES (42, 20, 11, 2, 0, 0, '2020-02-12 12:13:15', '通过后', 0, 0);
INSERT INTO `comment` VALUES (43, 21, 10, 2, 0, 0, '2020-02-14 15:16:52', '好好听', 0, 0);
INSERT INTO `comment` VALUES (44, 21, 9, 2, 36, 19, '2020-02-14 15:17:33', '怎么这么没素质', 0, 0);

-- ----------------------------
-- Table structure for condition
-- ----------------------------
DROP TABLE IF EXISTS `condition`;
CREATE TABLE `condition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `operate` tinyint(4) NOT NULL COMMENT '操作的类型',
  `type` tinyint(4) NOT NULL COMMENT '操作的对象的类型',
  `matter_id` int(11) NOT NULL COMMENT '操作的对象的id',
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of condition
-- ----------------------------
INSERT INTO `condition` VALUES (1, 12, 1, 1, 1, '2020-02-13 15:50:26', 0);
INSERT INTO `condition` VALUES (2, 21, 0, 2, 11, '2020-02-13 15:52:09', 0);
INSERT INTO `condition` VALUES (3, 21, 3, 3, 10, '2020-02-14 15:16:52', 0);
INSERT INTO `condition` VALUES (4, 21, 2, 3, 40, '2020-02-14 15:16:58', 0);
INSERT INTO `condition` VALUES (5, 21, 0, 2, 10, '2020-02-14 15:17:04', 0);
INSERT INTO `condition` VALUES (6, 21, 2, 2, 10, '2020-02-14 15:17:14', 0);
INSERT INTO `condition` VALUES (7, 21, 3, 3, 9, '2020-02-14 15:17:33', 0);
INSERT INTO `condition` VALUES (8, 21, 1, 0, 16, '2020-02-14 15:18:10', 0);
INSERT INTO `condition` VALUES (9, 21, 1, 0, 18, '2020-02-14 15:18:17', 0);
INSERT INTO `condition` VALUES (10, 21, 1, 4, 2, '2020-02-14 15:18:24', 0);
INSERT INTO `condition` VALUES (11, 17, 1, 0, 21, '2020-02-16 10:57:36', 0);
INSERT INTO `condition` VALUES (12, 17, 1, 4, 2, '2020-02-17 15:14:54', 0);
INSERT INTO `condition` VALUES (13, 17, 1, 4, 2, '2020-02-17 15:41:07', 0);

-- ----------------------------
-- Table structure for essay
-- ----------------------------
DROP TABLE IF EXISTS `essay`;
CREATE TABLE `essay`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `intor` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of essay
-- ----------------------------
INSERT INTO `essay` VALUES (2, '家有宠物的一点极简想法', '我其实不是极简的人，只是一个懒但是又受不了乱的人，因为懒所以想了很多办法让自己省事又舒服。', '<p>下面是狗子的衣食住行类物品：</p>\n<p>1.衣——没有。从最开始养狗之初，我就坚信宠物并不需要穿衣服的，（而且穿衣服还要洗，还要找地方放）而且我家本就是长毛犬，衣服就此拜拜了~</p>\n<p>2.食——原木色狗盆狗碗，这是唯一一个从最开始一直没有换过的东西。狗碗放在厨房，方便接水，狗粮就放在旁边，一切就近摆放，物品更容易使用完后即时归位。</p>\n<p>狗狗大型用品基本就是这个蓝色盒子，狗碗，狗粮加上狗窝</p>\n<p>蓝色盒子里的各种物品，这个玩具绳球考虑要不要极简掉<br />\n3.住——狗窝。这个就换了好多种啊！连我家孩子都说妈妈你别再买狗窝了（对话出现在今年双11）然后我就忍住了…目前是一个灰色的极简风狗垫，自己拿旧床单做了个小被子。</p>\n<p>家里还有个专门的地方给狗狗上厕所，以备不时之需。之前还用狗尿垫（从来没用过狗厕所，因为不好清洗，也因为我的狗狗不会抓刨尿垫）最近试着啥也不用，狗狗上完厕所直接拿水冲。</p>\n');
INSERT INTO `essay` VALUES (3, '手工 | 老黄瓜刷绿漆', '哈哈哈，做个酸瓜哈哈！喜欢我再出教程！\n\n各位教学视频，粗暴剪辑！', '<p>哈哈哈，做个酸瓜哈哈！喜欢我再出教程！</p>\n<p>各位教学视频，粗暴剪辑！</p>\n<p>youku 有广告，b站清晰度吓哭，推荐到b站app见吧。。。</p>\n');
INSERT INTO `essay` VALUES (4, '干货 | 关于羽绒服暖不暖和的几个指标', '绒是长在鹅鸭等水鸟的脖子到胸腹之间不含羽梗的毛，由于这个部位的毛经常浸在水中，所以不仅具有一定的防水', '<p>判断羽绒服温不温暖的指标主要有三个：蓬松度、含绒量、充绒量。其他的当然还有面料材质之类的。</p>\n<p>第一个：蓬松度。<br />\n蓬松度是国际上度量羽绒保暖程度的重要指标，指的是在一定条件下每一盎司（30克）羽绒所占体积立方英寸的数值。</p>\n<p>如一盎司的羽绒所占的空间为600立方英寸则称该羽绒的蓬松度为600。羽绒的蓬松度越高，说明在同样充绒量下的羽绒可以固定更大体积的空气层来保温和隔热，所以羽绒的保暖性越好。</p>\n<p>平时觉得保暖性能较好的the north face的羽绒服，是700蓬鹅绒填充。就是蓬松度为700。</p>\n<p>平时买的羽绒服蓬松度在450——500之间的就可以。</p>\n<p>蓬松度550的羽绒，是最常用的轻质保暖隔热材质，穿着轻巧温暖。</p>\n<p>蓬松度600的羽绒，广泛适用于不同的温度情况，温暖舒适，携带方便。</p>\n');
INSERT INTO `essay` VALUES (5, '来欣赏一下当代rapper', '我的style你不懂在那傻笑\n- 却不懂我是让你一直在闹', '<p>我的style你不懂在那傻笑</p>\n<ul>\n<li>却不懂我是让你一直在闹</li>\n<li>上高速 你却没带驾照</li>\n<li>还得我开共享将你带到</li>\n<li>你说我battle像在放炮</li>\n<li>我笑你满嘴在吐气泡</li>\n<li>韵脚太美你用的让人上吊</li>\n<li>吊完还得我来当复苏解药</li>\n<li>你穿鞋不系带</li>\n<li>你打牌只耍赖</li>\n<li>你是哪来的菜</li>\n<li>烧饭都嫌废材</li>\n<li>说话这么埋汰</li>\n<li>嘴臭自成一派</li>\n<li>这局你就淘汰</li>\n<li>别想我把你带<br />\n老哥这种实在太上头，我之前玩押韵和姐妹互夸夸到凌晨两点停不下来…从那以后我就不咋玩这个了哈哈哈哈</li>\n</ul>\n');
INSERT INTO `essay` VALUES (6, '整理了老友记里女孩儿们的穿搭衣橱', '老友记25周年，想整理集合下剧中姑娘们的的穿搭。下载了蓝光版一个个截图，陆陆续续更新～\nRache', '<p>现在看还是好好看啊</p>\n<p>回应赞 (331)<br />\n沙隆巴斯大地主<br />\n沙隆巴斯大地主 2019-09-26 15:38:07<br />\nRachel 的连衣裙都超好看</p>\n<p>回应赞 (279)<br />\n囧口🇨🇳<br />\n囧口🇨🇳 (求台风) 2019-09-26 15:35:00<br />\n最喜欢Monica的衣着品味了！</p>\n<p>回应赞 (192)<br />\n道明寺<br />\n道明寺 (远离垃圾。) 2019-09-26 15:39:46<br />\n马云卖家已经仿了好多款式了 说不定你这个帖子就会被搬</p>\n');
INSERT INTO `essay` VALUES (7, '南京灵谷寺无梁殿', '无梁殿，是中国历史最悠久、规模最大的砖砌拱券结构殿宇，建于1381年，整座建筑采用砖砌拱券结构、不设', '<p>无梁殿，是中国历史最悠久、规模最大的砖砌拱券结构殿宇，建于1381年，整座建筑采用砖砌拱券结构、不设木梁。<br />\n无梁殿全用砖砌，不用寸钉片木，是中国国内现存同类建筑中时代最早、规模最大的。</p>\n<p>小时候，爸妈曾带着我，在无梁殿大窗户的平台上野餐……</p>\n<p>来自 豆瓣App<br />\n赞<br />\n回应 转发 赞 收藏只看楼主<br />\n最赞回应<br />\nningningning<br />\nningningning 2019-12-04 10:57:42<br />\n好美啊</p>\n<p>回应赞 (4)来自 豆瓣App<br />\n三外<br />\n三外 2019-12-04 11:03:27<br />\n古典建筑融入自然美，树的伟大，为自然增色添彩</p>\n');
INSERT INTO `essay` VALUES (8, 'tyalot', '地方', '<p>是否</p>\n<h3><a id=\"_1\"></a>三级标题</h3>\n<p><ins>下划线</ins></p>\n<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n');
INSERT INTO `essay` VALUES (9, '一辈子', '不烦你该', '<p>地方撒 方式</p>\n<p><ins>下划线</ins></p>\n<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n');
INSERT INTO `essay` VALUES (10, '你说的', '对第三方第三方符是大法官', '<p>yum install –y git</p>\n<p>git clone https://github.com/mobz/elasticsearch-head.git</p>\n<p>安装grunt</p>\n<p>cd elasticsearch-head</p>\n<p>npm install -g grunt --registry=https://registry.npm.taobao.org</p>\n<p>在elasticsearch-head目录下node_modules/grunt下如果没有grunt二进制程序，需要执行：</p>\n<p>npm install grunt --save</p>\n<p>修改配置 elasticsearch-head下Gruntfile.js文件</p>\n<p>修改connect配置节点</p>\n');
INSERT INTO `essay` VALUES (11, '我最爱的孟慧圆', '这是关于我和圆圆的故事', '<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n<p>第三方</p>\n');
INSERT INTO `essay` VALUES (12, '两仪的', '放得开健康减肥的恐惧空间的科技非公开聚光科技', '<p>电视剧开发了<br />\n符是个</p>\n<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n');

-- ----------------------------
-- Table structure for essay_song
-- ----------------------------
DROP TABLE IF EXISTS `essay_song`;
CREATE TABLE `essay_song`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `song_id` int(11) NOT NULL,
  `essay_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `heat` int(11) NULL DEFAULT 0 COMMENT '热度',
  `pick_num` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `rewoard` int(11) NULL DEFAULT 0 COMMENT '打赏值',
  `commit_num` int(11) NULL DEFAULT 0 COMMENT '评论数',
  `collect_num` int(11) NULL DEFAULT 0 COMMENT '收藏数',
  `views` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态， 0为审核中，1为通过， 2为异常违规',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of essay_song
-- ----------------------------
INSERT INTO `essay_song` VALUES (1, 2, 2, 16, '2019-12-05 14:56:44', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (2, 3, 3, 16, '2019-12-05 15:01:24', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (3, 3, 4, 16, '2019-12-05 15:02:11', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (4, 4, 5, 16, '2019-12-05 15:02:54', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (5, 5, 6, 16, '2019-12-05 15:03:49', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (6, 6, 7, 16, '2019-12-05 15:04:34', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (7, 8, 8, 16, '2019-12-09 11:28:06', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (8, 10, 9, 16, '2019-12-09 11:37:39', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (9, 11, 10, 17, '2020-01-31 12:08:54', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (10, 12, 11, 16, '2020-02-08 17:36:28', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (11, 13, 12, 20, '2020-02-12 12:12:30', 0, 0, 0, 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级标签的id，没有则为0',
  `user_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for relation_topic_publish
-- ----------------------------
DROP TABLE IF EXISTS `relation_topic_publish`;
CREATE TABLE `relation_topic_publish`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `publish_id` int(11) NOT NULL COMMENT '创作id',
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态， 0为审核中，1为通过， 2为异常违规',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专栏和发表关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_topic_publish
-- ----------------------------
INSERT INTO `relation_topic_publish` VALUES (1, 1, 1, '2020-02-11 16:01:01', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (2, 1, 2, '2020-02-05 16:01:14', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (3, 1, 3, '2020-02-21 16:01:29', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (4, 2, 4, '2020-02-12 16:01:44', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (5, 2, 6, '2020-02-10 16:01:55', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (10, 1, 7, '2020-02-17 14:19:56', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (11, 1, 6, '2020-02-17 14:19:56', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (12, 1, 8, '2020-02-17 14:19:56', 0, 0);
INSERT INTO `relation_topic_publish` VALUES (13, 1, 9, '2020-02-17 15:01:27', 0, 0);

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (1, 'Taylor Swift - All You Had to Do Was Stay.', 'D:\\myProject\\Music Lover\\files\\iklcye.mp3');
INSERT INTO `song` VALUES (2, 'Taylor Swift - Bad Blood.', 'D:\\myProject\\Music Lover\\files\\ehtp82.mp3');
INSERT INTO `song` VALUES (3, 'Taylor Swift - Clean.', 'D:\\myProject\\Music Lover\\files\\9yuabn.mp3');
INSERT INTO `song` VALUES (4, 'Taylor Swift - New Romantics.', 'D:\\myProject\\Music Lover\\files\\4s8vcj.mp3');
INSERT INTO `song` VALUES (5, 'Taylor Swift - Style.', 'D:\\myProject\\Music Lover\\files\\ds3sdl.mp3');
INSERT INTO `song` VALUES (6, 'Taylor Swift - Wildest Dreams.', 'D:\\myProject\\Music Lover\\files\\3rmvt1.mp3');
INSERT INTO `song` VALUES (7, 'Taylor Swift - Welcome To New York.', 'D:\\myProject\\Music Lover\\files\\q4nsb6.mp3');
INSERT INTO `song` VALUES (8, 'Taylor Swift - This Love.', 'D:\\myProject\\Music Lover\\files\\h29732.mp3');
INSERT INTO `song` VALUES (9, 'Taylor Swift - Out Of The Woods.', 'D:\\myProject\\Music Lover\\client\\static\\audio\\vmg0bn.mp3');
INSERT INTO `song` VALUES (10, 'Taylor Swift - You Are In Love.', 'D:\\myProject\\Music Lover\\client\\static\\audio\\t1xz5w.mp3');
INSERT INTO `song` VALUES (11, '孟慧圆圆圆 - 京城八十一号.', 'D:\\myProject\\Music Lover\\client\\static\\audio\\rvm1gd.mp3');
INSERT INTO `song` VALUES (12, '孟慧圆圆圆 - 只要有你的地方.', 'D:\\myProject\\Music Lover\\client\\static\\audio\\674grr.mp3');
INSERT INTO `song` VALUES (13, '01 I Don_t Wanna Live Forever (Fifty.', 'D:\\myProject\\Music-Lover\\client\\static\\audio\\5xodjy.m4a');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专栏介绍',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别标签，不同的标签用:隔开，最多5个',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专栏的封面',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态， 0为审核中，1为通过， 2为异常违规',
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, 17, '属于我的抓兰', '', '', 0, '2020-02-12 18:03:31', 0, ' liangwei');
INSERT INTO `topic` VALUES (2, 16, '知道我的心', '', '', 0, '2020-02-13 11:29:56', 0, '没有一个人');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (16, 'test', '872acb223a4ce507a554af4017eeb3d3', 'ceTfs1y38FrRLBnG+9VncA==');
INSERT INTO `user` VALUES (17, 'lw', '1555b4eb5206e6671d7fbc1fcd3d1ae5', 'AGcMRcFu8un5BYlkIpj8Cw==');
INSERT INTO `user` VALUES (18, '赵今麦', '0b5ceee92901cf3b342c728717c9b68e', 'FsKVfKPVhPx3BLWw9qqxUQ==');
INSERT INTO `user` VALUES (19, '梁威', 'ba8f7d7651bd1454ea79e4599ec8d670', 'lOfZUH9eve6kBiELYFUnYg==');
INSERT INTO `user` VALUES (20, '梁毅', '0dd0594fae6411e60fda76e34cebb9c5', '249MS2CX5/rIvMXmKy8aKQ==');
INSERT INTO `user` VALUES (21, '张子枫', 'bbc16b02a814ca50cf5a74c088e2b23f', 'hqUT4GAYXvPjxsVCxoigdg==');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` tinyint(4) NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人介绍',
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `user_id` int(11) NOT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像的图片url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 1, 12, '得分', '2020-02-07 11:59:13', 0, 19, NULL);
INSERT INTO `user_info` VALUES (2, NULL, NULL, NULL, '2020-02-14 14:00:41', 0, 16, NULL);
INSERT INTO `user_info` VALUES (3, 0, 12, NULL, '2020-02-05 14:00:55', 0, 17, NULL);
INSERT INTO `user_info` VALUES (4, 1, 17, '美', '2020-02-10 14:01:07', 0, 18, NULL);
INSERT INTO `user_info` VALUES (5, 0, 12, '好帅', '2020-02-12 12:07:10', 0, 20, NULL);
INSERT INTO `user_info` VALUES (6, 1, 18, '我最爱的女神', '2020-02-13 15:43:01', 0, 21, NULL);

-- ----------------------------
-- Table structure for user_pick
-- ----------------------------
DROP TABLE IF EXISTS `user_pick`;
CREATE TABLE `user_pick`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `matter_id` int(11) NOT NULL COMMENT '操作的对象的id',
  `type` tinyint(4) NOT NULL COMMENT '操作的对象的类型',
  `create_time` datetime(0) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `status` tinyint(4) NOT NULL COMMENT '状态，1为点赞，0为取消点赞',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_pick
-- ----------------------------
INSERT INTO `user_pick` VALUES (1, 17, 10, 2, '2020-02-04 15:40:22', 0, 0);
INSERT INTO `user_pick` VALUES (2, 17, 36, 3, '2020-02-04 15:40:22', 0, 0);
INSERT INTO `user_pick` VALUES (16, 19, 10, 2, '2020-02-07 18:00:00', 0, 1);
INSERT INTO `user_pick` VALUES (17, 19, 36, 3, '2020-02-07 18:00:00', 0, 1);
INSERT INTO `user_pick` VALUES (18, 16, 10, 2, '2020-02-10 12:00:00', 0, 1);
INSERT INTO `user_pick` VALUES (19, 17, 11, 2, '2020-02-10 12:00:00', 0, 1);
INSERT INTO `user_pick` VALUES (20, 20, 12, 2, '2020-02-12 15:00:00', 0, 1);
INSERT INTO `user_pick` VALUES (21, 20, 39, 3, '2020-02-12 15:00:00', 0, 0);
INSERT INTO `user_pick` VALUES (22, 21, 11, 2, '2020-02-14 18:00:01', 0, 1);
INSERT INTO `user_pick` VALUES (23, 21, 10, 2, '2020-02-14 18:00:01', 0, 1);
INSERT INTO `user_pick` VALUES (24, 21, 40, 3, '2020-02-14 18:00:01', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
