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

 Date: 16/01/2020 13:52:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `is_delete` int(1) DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `heat` int(11) DEFAULT 0 COMMENT '热度',
  `pick_num` int(11) DEFAULT 0 COMMENT '点赞数',
  `rewoard` int(11) DEFAULT 0 COMMENT '打赏值',
  `commit_num` int(11) DEFAULT 0 COMMENT '评论数',
  `collect_num` int(11) DEFAULT 0 COMMENT '收藏数',
  `views` int(11) DEFAULT 0 COMMENT '浏览量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of essay_song
-- ----------------------------
INSERT INTO `essay_song` VALUES (1, 2, 2, 16, '2019-12-05 14:56:44', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (2, 3, 3, 16, '2019-12-05 15:01:24', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (3, 3, 4, 16, '2019-12-05 15:02:11', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (4, 4, 5, 16, '2019-12-05 15:02:54', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (5, 5, 6, 16, '2019-12-05 15:03:49', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (6, 6, 7, 16, '2019-12-05 15:04:34', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (7, 8, 8, 16, '2019-12-09 11:28:06', 0, 0, 0, 0, 0, 0);
INSERT INTO `essay_song` VALUES (8, 10, 9, 16, '2019-12-09 11:37:39', 0, 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (16, 'test', '872acb223a4ce507a554af4017eeb3d3', 'ceTfs1y38FrRLBnG+9VncA==');
INSERT INTO `user` VALUES (17, 'lw', '1555b4eb5206e6671d7fbc1fcd3d1ae5', 'AGcMRcFu8un5BYlkIpj8Cw==');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
