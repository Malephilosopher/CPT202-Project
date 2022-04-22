/*
 Navicat Premium Data Transfer

 Source Server         : tencentyun
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 124.221.214.9:3306
 Source Schema         : CPT202

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 22/04/2022 23:26:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `author_id` int unsigned NOT NULL,
  `post_time` timestamp NULL DEFAULT NULL,
  `edit_time` timestamp NULL DEFAULT NULL,
  `num_like` int NOT NULL DEFAULT '0',
  `content` varchar(10000) DEFAULT NULL,
  `num_view` int NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `num_fav` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id1` (`author_id`),
  CONSTRAINT `user_id1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of blog
-- ----------------------------
BEGIN;
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (1, 2, '2022-04-17 07:43:06', NULL, 0, 'this is content1', 0, 'description1', 'this is title1', 1);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (2, 2, '2022-04-17 08:12:38', NULL, 0, 'this is content1', 0, 'description1', 'this is title2', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (3, 3, '2022-04-17 09:22:54', NULL, 0, 'content2', 0, 'description2', 'this is title3', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (4, 1, '2022-04-17 09:34:07', NULL, 0, 'here is zeqiang', 0, 'he is zeqiang', 'zeqiang', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (5, 1, '2022-04-17 09:35:29', NULL, 0, 'here is zeqiang', 0, 'description5', 'this is title5', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (6, 1, '2022-04-17 09:44:28', NULL, 0, 'new content', 0, 'description6', 'this is title6', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (7, 10, '2022-04-18 15:33:49', NULL, 0, 'this is content7', 0, 'description7', 'this is title7', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (8, 3, '2022-04-19 13:18:49', NULL, 0, 'content8', 0, 'description8', 'title8', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (9, 10, '2022-04-19 13:38:39', NULL, 0, 'this is content7', 0, 'description9', 'title9', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (10, 10, '2022-04-19 13:57:35', NULL, 0, 'this is content7', 0, 'description10', 'title10', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (11, 10, '2022-04-19 14:09:00', NULL, 0, 'this is content7', 0, 'description11', 'title11', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (12, 10, '2022-04-19 14:26:39', NULL, 0, 'this is content7', 0, 'description12', 'title12', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (13, 233335, '2022-04-19 14:49:49', NULL, 0, 'asdjkhaksjfhsdkjafhkjsdahfkjhakjfhjkdfhjasdhkfhkasdlfjklasjflkdsjakfhksdjafhkjsdafhdjsfhajkadsahfkjsdhfkajdhakjsfhkdjsahflk', 0, 'test decription', 'test title', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (14, 233336, '2022-04-19 14:53:34', NULL, 0, NULL, 0, NULL, 'test title title', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (15, 1, '2022-04-20 13:56:11', NULL, 0, '/* 左侧内容区 */\n.oneList {\n	width: 70%;\n	height: auto;\n	/*border: 1px solid #333;*/\n	float: left;\n	margin-bottom: 10px;\n   margin-right: 20px;\n\n}\n\n/* 右侧展示区 */\n/* 热门阅读 */\n.twoList {\n	width: 20%;\n	height: 400px;\n    float: left;\n}\n\n\n/* 两个列表及其标题 */\n.AllList {\n	margin-top: 0px;\n	\n}\n.titlefont{\n	margin-top: 8px;\n	background-color:rgb(225, 231, 236);\n	font-size: large;\n	font-weight:bolder;\n}\n.list_title_font{\n	font-size: 25px;\n	font-weight:bolder;\n}\n\n\n\n\n\n\n\n\n\n\n\n\n', 0, '123', 'test 文章', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (16, 1, '2022-04-20 15:15:16', NULL, 0, '<h2 id=\"泽强\">泽强</h2>\n<blockquote>\n<p>这是一篇测试文章</p>\n</blockquote>\n', 0, 'test decription', '测试文章', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (17, 1, '2022-04-20 15:57:30', NULL, 0, '<h2 id=\"题目叫做无题\">题目叫做无题</h2>\n<blockquote>\n<p>首先表演个敲代码</p>\n</blockquote>\n<h2 id=\"然后表演一个分区\">然后表演一个分区</h2>\n<p>然后表演一个code</p>\n<pre code>\n public void main\n  int[] = new []\n const = new const\nreturn highleigh.js</pre code>\n\n然后表演一个正常打字<br>\n<code> funtion sth()={sdaasdasdasda\n<br>\nsdasdaad\n\n}\n\n\n ', 0, '这是一篇测试文章', '测试文章 1', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (18, 13, '2022-04-20 16:06:27', NULL, 0, '<ol>\n<li>list all you need </li>\n<li>write what you want</li>\n<li>how to find the itlics</li>\n<li>how to red sth others</li>\n</ol>\n<hr>\n<p><code> whre is the code style shortcut</code></p>\n', 0, '这是第二篇测试文章 作者是bob', '测试文章 第二篇', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (20, 1, '2022-04-21 11:39:00', NULL, 0, '<h2 id=\"sth\">sth</h2>\n', 0, 'test decription', '4/21 root 测试文章', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (22, 1, '2022-04-22 03:54:24', NULL, 0, '<p>11</p>\n', 0, '123', '测试文章422', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (23, 1, '2022-04-22 05:53:29', NULL, 1, '<h2 id=\"zeqiang\">Zeqiang</h2>\n<p><code> funciton </code></p>\n', 0, 'he is zeqiang', '422 测试文章', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (24, 233339, '2022-04-22 08:54:18', NULL, 0, '<p>svblbvsdlzbclvlfhfpwvjalvbadvbsaivS:GVwiuvguiudbvubdsuigachsjvcbdaaCc</p>\n', 0, 'dbvljdabv', 'test', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (25, 233339, '2022-04-22 09:13:36', NULL, 3, '<p>hduchuadkbvuakbk</p>\n', 0, 'cbcbc', 'lalala', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (26, 1, '2022-04-22 10:55:44', NULL, 0, '<p>htdtsnwrn</p>\n', 0, 'srhbfxb', 'tjsjmdgnm', 0);
INSERT INTO `blog` (`id`, `author_id`, `post_time`, `edit_time`, `num_like`, `content`, `num_view`, `description`, `title`, `num_fav`) VALUES (27, 1, '2022-04-22 11:16:42', NULL, 1, '<h2 id=\"title\">title</h2>\n<blockquote>\n<p>so we can have something</p>\n</blockquote>\n', 0, 'there are some description', 'test-post-final 422 ', 0);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `blog_id` int unsigned NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  `author_id` int unsigned NOT NULL,
  `parent_comment_id` int DEFAULT '-1',
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id5` (`author_id`),
  CONSTRAINT `blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `user_id5` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (1, 1, 'good', '2022-04-17 21:39:29', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (2, 1, NULL, '2022-04-10 13:15:53', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (3, 1, 'this is the the test ofaddComment', '2022-04-17 22:08:01', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (4, 1, 'this is the the test ofaddComment', '2022-04-18 11:29:06', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (5, 1, NULL, '2022-04-18 11:31:00', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (6, 1, 'this is the the test ofaddComment', '2022-04-18 19:55:29', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (7, 1, 'this is the the test ofaddComment', '2022-04-18 22:43:30', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (10, 1, 'this is the the test ofaddComment', '2022-04-19 15:19:33', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (11, 1, 'this is the the test ofaddComment', '2022-04-19 15:22:29', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (12, 1, 'this is the the test ofaddComment', '2022-04-19 20:05:17', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (13, 5, 'this is the the test ofaddComment', '2022-04-19 20:05:35', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (14, 5, 'this is the the test ofaddComment', '2022-04-19 20:17:50', 1, 0, '12');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (15, 5, 'this is the the test ofaddComment', '2022-04-19 20:47:24', 1, 0, 'root');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (16, 5, '123', '2022-04-19 21:04:23', 1, 0, 'root');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (17, 6, '123', '2022-04-19 21:32:10', 1, 0, 'root');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (18, 6, 'this is my comment', '2022-04-19 21:38:04', 1, 0, 'root');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (19, 13, 'this is comment from zeqiang', '2022-04-19 23:20:56', 1, 0, 'root');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (20, 13, 'this is coment', '2022-04-19 23:21:21', 1, 0, 'root');
INSERT INTO `comment` (`id`, `blog_id`, `content`, `post_time`, `author_id`, `parent_comment_id`, `username`) VALUES (21, 24, 'hello', '2022-04-22 16:56:38', 233339, 0, '111111111111111111111111111111111111111111111111111111');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `like_blog` int NOT NULL DEFAULT '0',
  `fav_blog` int NOT NULL DEFAULT '0',
  `num_fan` int NOT NULL DEFAULT '0',
  `comment_num` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233340 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (1, 'root', '123456', '123', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (2, 'adwadwd', '123456', '123', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (3, 'Yijie', 'aaaaaa', '111', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (4, '123', 'aaaaaa', '111', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (5, '12', 'aaaaaa', '111', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (6, 'dgwgw', 'aaaaaa', '111', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (7, 'lnfwing', 'aaaaaa', '111', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (8, 'xiaohong', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (9, 'xiaoming', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (10, 'xiaoliang', '123', '3316833027@qq.com', 'male', '夳', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (11, 'aisibi', '123456', '123', 'male', NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (12, 'aisib', '123456', '123', 'male', NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (13, 'bob', '123', '3316833027@qq.com', 'male', '夳', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (14, 'test', 'aaaaaa', '111', NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (100, 'adwdadw', '1234561', '123', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (101, 'adwdadadww', '1234561', '123', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (103, 'bostman', '1234561', 'America', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (104, 'bostmen', '1234561', 'America', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (105, 'bostm1en', '1234561', 'America', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (107, 'spongebob1', '1234561', 'America', 'male', '3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233332, 'xiaopeng', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233333, 'xiaohua', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233334, 'xiaolv', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233335, 'xiaoxiao', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233336, 'xiaolan', '123', '3316833027@qq.com', 'male', 'Y3', 'ics', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233337, 'zhy', '123456', 'zhy20001215@sina.com', 'female', '3', 'ICS', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233338, 'zhylalskndjkvbsvskbvjdbkbjbvjbdjbvjdbkvbkjadbjavbdavbkcbajvchdacb djkackv', '123456', 'zhy20001215@sina.com', 'female', '3', 'ICS', 0, 0, 0, 0);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `gender`, `grade`, `major`, `like_blog`, `fav_blog`, `num_fan`, `comment_num`) VALUES (233339, '111111111111111111111111111111111111111111111111111111', '123456', 'zhy20001215@sina.com', 'female', '3', 'ICS', 0, 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_fav
-- ----------------------------
DROP TABLE IF EXISTS `user_fav`;
CREATE TABLE `user_fav` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `blog_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id3` (`user_id`),
  CONSTRAINT `user_id3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of user_fav
-- ----------------------------
BEGIN;
INSERT INTO `user_fav` (`id`, `user_id`, `blog_id`) VALUES (69, 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `blog_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id4` (`user_id`),
  CONSTRAINT `user_id4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of user_follow
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `blog_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id2` (`user_id`),
  CONSTRAINT `user_id2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of user_like
-- ----------------------------
BEGIN;
INSERT INTO `user_like` (`id`, `user_id`, `blog_id`) VALUES (88, 1, 25);
INSERT INTO `user_like` (`id`, `user_id`, `blog_id`) VALUES (89, 13, 25);
INSERT INTO `user_like` (`id`, `user_id`, `blog_id`) VALUES (101, 233336, 25);
INSERT INTO `user_like` (`id`, `user_id`, `blog_id`) VALUES (104, 1, 27);
INSERT INTO `user_like` (`id`, `user_id`, `blog_id`) VALUES (112, 1, 23);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
