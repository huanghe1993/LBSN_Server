/*
Navicat MySQL Data Transfer

Source Server         : LBSN
Source Server Version : 50642
Source Host           : 47.107.95.148 :3306
Source Database       : lbsn

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-01-04 17:25:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `check`
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `poi_id` int(20) NOT NULL,
  `poi_name` varchar(100) DEFAULT NULL,
  `latitude` float(20,6) DEFAULT NULL,
  `longitude` float(20,6) DEFAULT NULL,
  `rate` int(20) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check
-- ----------------------------
INSERT INTO `check` VALUES ('1', '1', '1', '西安电子科技大学北校区操场', '34.237534', '108.924500', '3', '老人很多', '2018-12-29 17:31:35');
INSERT INTO `check` VALUES ('2', '1', '1', '西安电子科技大学北校区操场', '34.237534', '108.924500', '4', '适合锻炼', '2018-12-29 17:33:10');
INSERT INTO `check` VALUES ('3', '1', '2', '太白南路人人乐超市', '34.241707', '108.922684', '4', '物美价廉', '2018-12-29 17:34:22');
INSERT INTO `check` VALUES ('4', '1', '6', '大雁塔音乐喷泉', '34.227547', '108.970573', '5', '适合晚上欣赏', '2018-12-29 17:36:20');
INSERT INTO `check` VALUES ('5', '1', '11', '陕西历史博物馆', '34.230522', '108.961494', '5', '很有意义', '2018-12-29 17:38:04');
INSERT INTO `check` VALUES ('6', '2', '11', '陕西历史博物馆', '34.230522', '108.961494', '4', '物品丰富', '2018-12-29 17:39:49');
INSERT INTO `check` VALUES ('7', '2', '9', '大唐慈恩寺遗址公园', '34.223804', '108.974152', '4', '公园景色优美', '2018-12-29 17:40:14');
INSERT INTO `check` VALUES ('8', '2', '22', '小雁塔雁园皮影戏', '34.246414', '108.948570', '3', '戏曲精彩', '2018-12-29 17:41:23');
INSERT INTO `check` VALUES ('9', '2', '46', '西安市明清皮影艺术博物馆', '34.217075', '108.971550', '4', '历史文物齐全', '2018-12-29 18:42:24');
INSERT INTO `check` VALUES ('10', '2', '88', '窄巷子陕菜馆', '34.205521', '108.976151', '2', '吃的一般', '2018-12-29 18:43:50');
INSERT INTO `check` VALUES ('11', '2', '87', '老米家泡馍', '34.266445', '108.956696', '5', '好吃', '2018-12-29 18:44:29');
INSERT INTO `check` VALUES ('12', '3', '11', '陕西历史博物馆', '34.230522', '108.961494', '3', '文物齐全', '2018-12-29 18:45:33');
INSERT INTO `check` VALUES ('13', '3', '9', '大唐慈恩寺遗址公园', '34.223804', '108.974152', '5', '环境优美', '2018-12-29 18:46:09');
INSERT INTO `check` VALUES ('14', '3', '78', '养生粥坊', '34.274117', '108.971222', '4', '粥的味道不错，值得推荐', '2018-12-29 18:47:05');
INSERT INTO `check` VALUES ('15', '3', '1', '西安电子科技大学北校区操场', '34.237534', '108.924500', '3', '适合锻炼', '2018-12-29 18:48:24');
INSERT INTO `check` VALUES ('16', '3', '58', '曲江池', '34.207962', '108.990524', '2', '风景一般', '2018-12-29 18:49:27');
INSERT INTO `check` VALUES ('17', '3', '68', '唐城墙遗址', '34.210831', '108.978943', '4', '宏伟大气', '2018-12-29 18:50:10');
INSERT INTO `check` VALUES ('18', '4', '68', '唐城墙遗址', '34.210831', '108.978943', '5', '很有意义', '2018-12-29 18:51:02');
INSERT INTO `check` VALUES ('19', '4', '1', '西安电子科技大学北校区操场', '34.237534', '108.924500', '4', '老人比较多', '2018-12-29 18:51:49');
INSERT INTO `check` VALUES ('20', '4', '2', '太白南路人人乐超市', '34.241707', '108.922684', '3', '物美价廉', '2018-12-29 18:52:47');
INSERT INTO `check` VALUES ('21', '5', '11', '陕西历史博物馆', '34.230522', '108.961494', '3', '比较一般', '2018-12-30 21:18:14');
INSERT INTO `check` VALUES ('22', '2', '22', '小雁塔雁园皮影戏', '34.246414', '108.971550', '4', '历史文物齐全', '2018-12-30 21:23:09');
INSERT INTO `check` VALUES ('23', '2', '22', '小雁塔雁园皮影戏', '34.246414', '108.971550', '5', '很有意义', '2018-12-30 21:24:11');
INSERT INTO `check` VALUES ('24', '1', '22', '小雁塔雁园皮影戏', '34.246414', '108.948570', '4', '非常不错', '2019-01-03 18:16:59');
INSERT INTO `check` VALUES ('25', '1', '22', '小雁塔雁园皮影戏', '34.246414', '108.948570', '4', '非常不错', '2019-01-03 18:47:35');

-- ----------------------------
-- Table structure for `poi`
-- ----------------------------
DROP TABLE IF EXISTS `poi`;
CREATE TABLE `poi` (
  `poi_id` int(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `city_name` varchar(100) DEFAULT NULL,
  `longitude` float(20,6) DEFAULT NULL,
  `latitude` float(20,6) DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `poi_describe` varchar(200) DEFAULT NULL,
  `photo` varchar(1024) DEFAULT NULL,
  `tiny_photo` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`poi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of poi
-- ----------------------------
INSERT INTO `poi` VALUES ('1', '西安电子科技大学北校区操场', '西安市', '108.924500', '34.237534', '校园社区', '散步，慢跑好地方', '/images/poi/beixiaoqucaochang1.jpg,/images/poi/beixiaoqucaochang2.jpg', '/images/tinypoi/beixiaoqucaochang1.jpg');
INSERT INTO `poi` VALUES ('2', '太白南路人人乐超市', '西安市', '108.925362', '34.241707', '商场超市', '物美价廉', '/images/poi/renrenle1.jpg,/images/poi/renrenle2.jpg', '/images/tinypoi/renrenle1.jpg');
INSERT INTO `poi` VALUES ('3', '西安电子科技学体育馆', '西安市', '108.922684', '34.238029', '校园社区', '运动好去处', '/images/poi/tiyuguang1.jpg', '/images/tinypoi/tiyuguang1.jpg');
INSERT INTO `poi` VALUES ('4', '赛格国际购物中心', '西安市', '108.954803', '34.229908', '商场超市', '西安美食与购物天堂', '/images/poi/saige1.jpg,/images/poi/saige2.jpg', '/images/tinypoi/saige1.jpg');
INSERT INTO `poi` VALUES ('5', '大雁塔', '西安市', '108.971153', '34.225620', '名胜古迹', '大雁塔作为现存最早、规模最大的唐代四方楼阁式砖塔', '/images/poi/yanta1.jpg,/images/poi/yanta2.jpg', '/images/tinypoi/yanta1.jpg');
INSERT INTO `poi` VALUES ('6', '大雁塔音乐喷泉', '西安市', '108.970573', '34.227547', '公园广场', '亚洲规模最大的音乐喷泉广场', '/images/poi/yinyuepengquan1.jpg,/images/poi/yinyuepengquan2.jpg', '/images/tinypoi/yinyuepengquan1.jpg');
INSERT INTO `poi` VALUES ('7', '和居家养老生活馆', '西安市', '108.967796', '34.228542', '健康养生', '休闲娱乐好去处', '/images/poi/hejujia1.jpg,/images/poi/hejujia2.jpg,/images/poi/hejujia3.jpg', '/images/tinypoi/hejujia1.jpg');
INSERT INTO `poi` VALUES ('8', '陕西戏曲大观园', '西安市', '108.974236', '34.226288', '公园广场', '戏曲文化的圣地', '/images/poi/xiqudaguanyuan1.jpg,/images/poi/xiqudaguanyuan2.jpg', '/images/tinypoi/xiqudaguanyuan1.jpg');
INSERT INTO `poi` VALUES ('9', '大唐慈恩寺遗址公园', '西安市', '108.974152', '34.223804', '公园广场', '大雁塔文化休闲景区中相对独立且主题特色鲜明的开放式园林，坐落在唐代大慈恩寺遗址上，且现在与大慈恩寺紧邻，具备得天独厚的历史价值、景观特色', '/images/poi/ciensi1.jpg,/images/poi/ciensi2.jpg', '/images/tinypoi/ciensi1.jpg');
INSERT INTO `poi` VALUES ('10', '大唐芙蓉园', '西安市', '108.980721', '34.218948', '公园广场', '仿照唐代皇家园林式样重新建造的，是中国第一个全方位展示盛唐风貌的大型皇家园林式文化主题公园', '/images/poi/furongyuan1.jpg,/images/poi/furongyuan2.jpg', '/images/tinypoi/furongyuan1.jpg');
INSERT INTO `poi` VALUES ('11', '陕西历史博物馆', '西安市', '108.961494', '34.230522', '名胜古迹', '古都明珠，华夏宝库', '/images/poi/lishibowuguan1.jpg,/images/poi/lishibowuguan2.jpg', '/images/tinypoi/lishibowuguan1.jpg');
INSERT INTO `poi` VALUES ('12', '陕西秦腔博物馆', '西安市', '108.986855', '34.248123', '名胜古迹', '中国首个秦腔博物馆', '/images/poi/qinqiangbowuguan1.jpg,/images/poi/qinqiangbowuguan2.jpg', '/images/tinypoi/qinqiangbowuguan1.jpg');
INSERT INTO `poi` VALUES ('13', '钟楼', '西安市', '108.953461', '34.265724', '名胜古迹', '是中国现存钟楼中形制最大、保存最完整的一座', '/images/poi/zhonglou1.jpg,/images/poi/zhonglou2.jpg', '/images/tinypoi/zhonglou1.jpg');
INSERT INTO `poi` VALUES ('14', '开元商城', '西安市', '108.954788', '34.264912', '商城超市', '开元商城所在位置，清时是一座寺院，叫开元寺', '/images/poi/kaiyuanshangcheng1.jpg,/images/poi/kaiyuanshangcheng2.jpg', '/images/tinypoi/kaiyuanshangcheng1.jpg');
INSERT INTO `poi` VALUES ('15', '钟楼饭店', '西安市', '108.952324', '34.264664', '美食养生', '历史悠久的饭店', '/images/poi/zhongloufandian1.jpg,/images/poi/zhongloufandian2.jpg', '/images/tinypoi/zhongloufandian1.jpg');
INSERT INTO `poi` VALUES ('16', '回民街', '西安市', '108.950256', '34.266708', '美食养生', '著名的美食文化街区', '/images/poi/huimingjie1.jpg,/images/poi/huimingjie2.jpg,/images/poi/huimingjie3.jpg', '/images/tinypoi/huimingjie1.jpg');
INSERT INTO `poi` VALUES ('17', '民生百货', '西安市', '108.957565', '34.264912', '商城超市', '购物的好去处', '/images/poi/mingshengbaihuo1.jpg,/images/poi/mingshengbaihuo2.jpg', '/images/tinypoi/mingshengbaihuo1.jpg');
INSERT INTO `poi` VALUES ('19', '钟鼓楼广场', '西安市', '108.948601', '34.265968', '公园广场', '古城西安“城市客厅”。', '/images/poi/zhonggulouguangchang1.jpg,/images/poi/zhonggulouguangchang2.jpg', '/images/tinypoi/zhonggulouguangchang1.jpg');
INSERT INTO `poi` VALUES ('20', '碑林博物馆', '西安市', '108.959190', '34.260189', '名胜古迹', '收藏、陈列和研究历代碑刻、墓志及石刻为主，成为在中国独树一帜的艺术博物馆', '/images/poi/beilinbowuguan1.jpg,/images/poi/beilinbowuguan2.jpg,/images/poi/beilinbowuguan3.jpg', '/images/tinypoi/beilinbowuguan1.jpg');
INSERT INTO `poi` VALUES ('21', '大唐西市生活广场', '西安市', '108.915726', '34.253174', '公园广场', '“皇城复兴计划”的推动下以丝路风情和文化为特色的综合性广场。', '/images/poi/datangxishi1.jpg,/images/poi/datangxishi2.jpg', '/images/tinypoi/datangxishi1.jpg');
INSERT INTO `poi` VALUES ('22', '小雁塔雁园皮影戏', '西安市', '108.948570', '34.246414', '影视戏曲', '皮影戏的胜地', '/images/poi/yantapiying1.jpg,/images/poi/yantapiying2.jpg', '/images/tinypoi/yantapiying1.jpg');
INSERT INTO `poi` VALUES ('23', '西安古乐博物馆', '西安市', '108.948059', '34.244320', '名胜古迹', '古乐研究好去处', '/images/poi/guyuebowug1.jpg', '/images/tinypoi/guyuebowug1.jpg');
INSERT INTO `poi` VALUES ('24', '兴庆宫公园', '西安市', '108.990448', '34.260284', '公园广场', '兴庆宫公园是中国最古老的历史文化遗址公园，与百年名校西安交通大学相邻', '/images/poi/xinqingongyuan1.jpg,/images/poi/xinqingongyuan2.jpg', '/images/tinypoi/xinqingongyuan1.jpg');
INSERT INTO `poi` VALUES ('25', '西安交通大学操场', '西安市', '108.992661', '34.252522', '校园社区', '散步休闲好去处', '/images/poi/jiaodacaochang1.jpg,/images/poi/jiaodacaochang2.jpg', '/images/tinypoi/jiaodacaochang1.jpg');
INSERT INTO `poi` VALUES ('26', '青龙寺遗址', '西安市', '108.995667', '34.239227', '名胜古迹', '仿唐式古建主楼、东西配楼和古原楼广场组成', '/images/poi/qinglongsi1.jpg,/images/poi/qinglongsi2.jpg', '/images/tinypoi/qinglongsi1.jpg');
INSERT INTO `poi` VALUES ('27', '西安城墙-西门', '西安市', '108.932594', '34.265335', '名胜古迹', '安定门为西安城墙正西门', '/images/poi/chengqiangximen1.jpg,/images/poi/chengqiangximen1.jpg', '/imagestinypoi/chengqiangximen1.jpg');
INSERT INTO `poi` VALUES ('28', '西安城墙-南门', '西安市', '108.953384', '34.256924', '名胜古迹', '朱雀门', '/images/poi/chengqiangnanmen1.jpg,/images/poi/chengqiangnanmen1.jpg', '/images/tinypoi/chengqiangnanmen1.jpg');
INSERT INTO `poi` VALUES ('29', '大明宫国家遗址公园', '西安市', '108.969894', '34.298634', '名胜古迹', '大明宫是唐帝国最宏伟壮丽的宫殿建筑群，也是当时世界上面积最大的宫殿建筑群，是唐朝的国家象征', '/images/poi/daminggong1.jpg,/images/poi/daminggong2.jpg', '/images/tinypoi/daminggong1.jpg');
INSERT INTO `poi` VALUES ('30', '西安世博园', '西安市', '109.068237', '34.329601', '公园广场', '是2011西安世界园艺博览会会址', '/images/poi/shiboyuan1.jpg,/images/poi/shiboyuan2.jpg', '/images/tinypoi/shiboyuan1.jpg');
INSERT INTO `poi` VALUES ('31', '灞桥生态湿地地公园', '西安市', '109.083473', '34.313442', '公园广场', '灞桥、灞柳、灞水为主题文化的湿地公园', '/images/poi/baqiaoshidi1.jpg,/images/poi/baqiaoshidi2.jpg', '/images/tinypoi/baqiaoshidi1.jpg');
INSERT INTO `poi` VALUES ('32', '西安博物馆', '西安市', '108.948196', '34.244663', '名胜古迹', '文物研究好去处', '/images/poi/xianbowug1.jpg', '/images/tinypoi/xianbowug1.jpg');
INSERT INTO `poi` VALUES ('33', '陕西省图书馆', '西安市', '108.950027', '34.237827', '校园社区', '中国西部地区成立最早的公共图书馆', '/images/poi/shanxitushuguang1.jpg,/images/poi/shanxitushuguang2.jpg', '/images/tinypoi/shanxitushuguang1.jpg');
INSERT INTO `poi` VALUES ('34', '西北工业大学操场', '西安市', '108.924850', '34.252167', '校园社区', '适合休闲散步', '/images/poi/xigongdacaochang1.jpg,/images/poi/xigongdacaochang2.jpg', '/images/tinypoi/xigongdacaochang1.jpg');
INSERT INTO `poi` VALUES ('35', '太白印象城广场', '西安市', '108.928223', '34.241104', '公园广场', '休闲，娱乐的好去处', '/images/poi/taibaiyingxcheng1.jpg,/images/poi/taibaiyingxcheng2.jpg', '/images/tinypoi/taibaiyingxcheng1.jpg');
INSERT INTO `poi` VALUES ('36', '徐家庄好又多购物广场', '西安市', '108.919739', '34.235825', '商城超市', '物美价廉', '/images/poi/haoyouduo1.jpg,/images/poi/haoyouduo2.jpg', '/images/tinypoi/haoyouduo1.jpg');
INSERT INTO `poi` VALUES ('37', '陕西巷子老菜馆', '西安市', '108.911835', '34.241852', '美食养生', '极具陕西特色的菜馆', '/images/poi/shanxixiangzilaocai1.jpg,/images/poi/shanxixiangzilaocai2.jpg', '/images/tinypoi/shanxixiangzilaocai1.jpg');
INSERT INTO `poi` VALUES ('40', '戏曲小剧场', '碑林区', '108.962135', '34.258533', '影视戏曲', '听戏曲的好去处', '/images/poi/xiquxiaojuchang1.jpg,/images/poi/xiquxiaojuchang2.jpg', '/images/tinypoi/xiquxiaojuchang1.jpg');
INSERT INTO `poi` VALUES ('41', '秦韵戏曲茶吧', '碑林区', '108.960732', '34.259727', '影视戏曲', '听戏曲的好去处', '/images/poi/qinyunxiquchaba1.jpg,/images/poi/qinyunxiquchaba2.jpg', '/images/tinypoi/qinyunxiquchaba1.jpg');
INSERT INTO `poi` VALUES ('42', '戏曲茶座', '碑林区', '108.980598', '34.270828', '影视戏曲', '听戏曲的好去处', '/images/poi/qxiquchazuo1.jpg,/images/poi/qxiquchazuo2.jpg', '/images/tinypoi/qxiquchazuo1.jpg');
INSERT INTO `poi` VALUES ('43', '中山戏曲茶座', '新城区', '108.976479', '34.270260', '影视戏曲', '听戏曲的好去处', '/images/poi/zhongsxiquchazuo1.jpg,/images/poi/zhongsxiquchazuo2.jpg', '/images/tinypoi/zhongsxiquchazuo1.jpg');
INSERT INTO `poi` VALUES ('44', '陕西大戏剧会馆', '碑林区', '108.956543', '34.250732', '影视戏曲', '听戏曲的好去处', '/images/poi/shanxidaxiju1.jpg', '/images/tinypoi/shanxidaxiju1.jpg');
INSERT INTO `poi` VALUES ('45', '陕西大剧院戏剧厅', '雁塔区', '108.969124', '34.216652', '影视戏曲', '听戏曲的好去处', '/images/poi/xiandajuyuan1.jpg,/images/poi/xiandajuyuan2.jpg', '/images/tinypoi/xiandajuyuan1.jpg');
INSERT INTO `poi` VALUES ('46', '西安市明清皮影艺术博物馆', '雁塔区', '108.971550', '34.217075', '影视戏曲', '研究皮影好去处', '/images/poi/mingqingpiying1.jpg,/images/poi/mingqingpiying2.jpg', '/images/tinypoi/mingqingpiying1.jpg');
INSERT INTO `poi` VALUES ('47', '西安城市运动公园', '未央区', '108.949638', '34.350826', '公园广场', '散步，休闲的好地方', '/images/poi/chengshiyundongyuan1.jpg,/images/poi/chengshiyundongyuan2.jpg', '/images/tinypoi/chengshiyundongyuan1.jpg');
INSERT INTO `poi` VALUES ('49', '丰庆公园', '碑林区', '108.905190', '34.253609', '公园广场', '西安西郊的“老机场”', '/images/poi/fengqinggongyuan1.jpg,/images/poi/fengqinggongyuan2.jpg', '/images/tinypoi/fengqinggongyuan1.jpg');
INSERT INTO `poi` VALUES ('50', '樱花广场', '长安区', '108.885048', '34.166473', '公园广场', '观赏樱花的好地方', '/images/poi/yinghuaguangc1.jpg,/images/poi/yinghuaguangc2.jpg', '/images/tinypoi/yinghuaguangc1.jpg');
INSERT INTO `poi` VALUES ('51', '长乐公园', '碑林区', '109.005951', '34.267876', '公园广场', '环境优美，景色宜人', '/images/poi/changlegongyuan1.jpg,/images/poi/changlegongyuan2.jpg', '/images/tinypoi/changlegongyuan1.jpg');
INSERT INTO `poi` VALUES ('52', '未央湖公园', '未央区', '108.995743', '34.406490', '公园广场', '环境优美，景色宜人', '/images/poi/weiyanghugongyuan1.jpg,/images/poi/weiyanghugongyuan2.jpg', '/images/tinypoi/weiyanghugongyuan1.jpg');
INSERT INTO `poi` VALUES ('53', '莲湖公园', '莲湖区', '108.946777', '34.274982', '公园广场', '环境优美，景色宜人', '/images/poi/lianhugongyuan1.jpg,/images/poi/lianhugongyuan2.jpg', '/images/tinypoi/lianhugongyuan1.jpg');
INSERT INTO `poi` VALUES ('55', '革命公园', '新城区', '108.964973', '34.277893', '公园广场', '环境优美，景色宜人', '/images/poi/geminggongyuan1.jpg,/images/poi/geminggongyuan2.jpg', '/images/tinypoi/geminggongyuan1.jpg');
INSERT INTO `poi` VALUES ('56', '潏河湿地公园', '长安区', '108.921936', '34.148251', '公园广场', '环境优美，景色宜人', '/images/poi/juheshidigongyuan1.jpg,/images/poi/juheshidigongyuan2.jpg', '/images/tinypoi/juheshidigongyuan1.jpg');
INSERT INTO `poi` VALUES ('57', '桃花潭', '灞桥区', '109.039551', '34.309826', '公园广场', '环境优美，景色宜人', '/images/poi/taohuatang1.jpg,/images/poi/taohuatang2.jpg', '/images/tinypoi/taohuatang1.jpg');
INSERT INTO `poi` VALUES ('58', '曲江池', '雁塔区', '108.990524', '34.207962', '公园广场', '环境优美，景色宜人', '/images/poi/qujiangchi1.jpg,/images/poi/qujiangchi2.jpg', '/images/tinypoi/qujiangchi1.jpg');
INSERT INTO `poi` VALUES ('59', '红光公园', '未央区', '108.847946', '34.269451', '公园广场', '环境优美，景色宜人', '/images/poi/hongguanggongyuan1.jpg,/images/poi/hongguanggongyuan2.jpg', '/images/tinypoi/hongguanggongyuan1.jpg');
INSERT INTO `poi` VALUES ('60', '西安秦岭野生动物园', '长安区', '108.873848', '34.053406', '公园广场', '西北首家野生动物园', '/images/poi/qinlingyeshengdong1.jpg,/images/poi/qinlingyeshengdong2.jpg', '/images/tinypoi/qinlingyeshengdong1.jpg');
INSERT INTO `poi` VALUES ('61', '陕西翠华山国家地质公园', '长安区', '109.018089', '33.979149', '公园广场', '西安旅游十大景', '/images/poi/cuihuashang1.jpg,/images/poi/cuihuashang2.jpg', '/images/tinypoi/cuihuashang1.jpg');
INSERT INTO `poi` VALUES ('63', '西安植物园', '雁塔区', '109.036301', '34.215164', '公园广场', '我国重点建设的八大植物园之一，也是西北地区最早成立的植物园', '/images/poi/zhiwuyuan1.jpg,/images/poi/zhiwuyuan2.jpg', '/images/tinypoi/zhiwuyuan1.jpg');
INSERT INTO `poi` VALUES ('64', '华清宫', '临潼区', '109.221619', '34.364033', '名胜古迹', '背山面渭，倚骊峰山势而筑，规模宏大，建筑壮丽，楼台馆殿，遍布骊山上下', '/images/poi/huaqinggong1.jpg,/images/poi/huaqinggong2.jpg,/images/poi/huaqinggong3.jpg', '/images/tinypoi/huaqinggong1.jpg');
INSERT INTO `poi` VALUES ('65', '西安烈士陵园', '雁塔区', '108.940063', '34.215328', '名胜古迹', '西安烈士陵园是陕西省兴建较早、规模最大的一座烈士陵园，也是西北地区较大的烈士陵园之一', '/images/poi/xianlieshi1.jpg', '/images/tinypoi/xianlieshi1.jpg');
INSERT INTO `poi` VALUES ('66', '汉唐帝陵旅游区', '雁塔区', '109.031364', '34.186481', '名胜古迹', '研究历史旅游的圣地', '/images/poi/hantangdiluyou1.jpg,/images/poi/fhantangdiluyou2.jpg', '/images/tinypoi/hantangdiluyou1.jpg');
INSERT INTO `poi` VALUES ('67', '日本飞机轰炸西安遗址', '莲湖区', '108.944786', '34.271404', '名胜古迹', '研究历史的圣地', '/images/poi/feijiyizhi1.jpg,/images/poi/feijiyizhi2.jpg', '/images/tinypoi/feijiyizhi1.jpg');
INSERT INTO `poi` VALUES ('68', '唐城墙遗址', '雁塔区', '108.978943', '34.210831', '名胜古迹', '是唐代长安城的南城墙所在地', '/images/poi/tangchengqiangyizhi1.jpg,/images/poi/tangchengqiangyizhi2.jpg', '/images/tinypoi/tangchengqiangyizhi1.jpg');
INSERT INTO `poi` VALUES ('69', '张学良公馆', '长安区', '108.983696', '34.070930', '名胜古迹', '这里是爱国将军张学良曾经的居住地，是震惊中外的西安事变发生地', '/images/poi/zhangxuelianggongguan1.jpg,/images/poi/zhangxuelianggongguan2.jpg', '/images/tinypoi/zhangxuelianggongguan1.jpg');
INSERT INTO `poi` VALUES ('70', '灵应寺', '长安区', '108.777237', '34.025146', '名胜古迹', '著名的景区', '/images/poi/yinglingsi1.jpg,/images/poi/yinglingsi2.jpg', '/images/tinypoi/yinglingsi1.jpg');
INSERT INTO `poi` VALUES ('71', '西安事变纪念馆', '莲湖区', '108.946243', '34.277737', '名胜古迹', '西安事变纪念馆是以“西安事变”重要旧址张学良公馆、杨虎城止园别墅为基础而建立的遗址性博物馆', '/images/poi/xianshibianjiliangunag1.jpg,/images/poi/xianshibianjiliangunag2.jpg', '/images/tinypoi/xianshibianjiliangunag1.jpg');
INSERT INTO `poi` VALUES ('72', '尚武门', '莲湖区', '108.939568', '34.281815', '名胜古迹', '尚武门是西安城墙北城墙最西侧的一个城门，开于建国后,是西安城墙十八座城门中最为年轻的一个门', '/images/poi/shanwumen1.jpg,/images/poi/shanwumen2.jpg', '/images/tinypoi/shanwumen1.jpg');
INSERT INTO `poi` VALUES ('73', '古观音禅寺', '长安区', '108.798706', '34.031662', '名胜古迹', '终南山千年古刹之一', '/images/poi/changsi1.jpg,/images/poi/changsi2.jpg', '/images/tinypoi/changsi1.jpg');
INSERT INTO `poi` VALUES ('74', '大兴善寺', '雁塔区', '108.949898', '34.233208', '名胜古迹', '大兴善寺是隋唐皇家寺院，中国“佛教八宗”之一“密宗”祖庭', '/images/poi/daxingshansi1.jpg,/images/poi/daxingshansi2.jpg', '/images/tinypoi/daxingshansi1.jpg');
INSERT INTO `poi` VALUES ('75', '秦兵马俑壹号坑大厅', '临潼区', '109.284897', '34.389412', '名胜古迹', '世界第八大奇迹之一', '/images/poi/bingmayong1.jpg,/images/poi/bingmayong2.jpg,/images/poi/bingmayong3.jpg', '/images/tinypoi/bingmayong1.jpg');
INSERT INTO `poi` VALUES ('77', '汉长安城未央宫遗址', '未央区', '108.869293', '34.308029', '名胜古迹', '是中国历史上使用朝代最多、存在时间最长的皇宫', '/images/poi/hanchangcheng1.jpg,/images/poi/hanchangcheng2.jpg', '/images/tinypoi/hanchangcheng1.jpg');
INSERT INTO `poi` VALUES ('78', '养生粥坊', '新城区', '108.971222', '34.274117', '美食养生', '养生，喝粥的好去处', '/images/poi/yangshengzhoufang1.jpg,/images/poi/yangshengzhoufang2.jpg', '/images/tinypoi/yangshengweitang1.jpg');
INSERT INTO `poi` VALUES ('79', '养身煨汤', '新城区', '108.970901', '34.271076', '美食养生', '养生，喝汤的好去处', '/images/poi/yangshengweitang1.jpg,/images/poi/yangshengweitang2.jpg', '/images/tinypoi/yangshengweitang1.jpg');
INSERT INTO `poi` VALUES ('81', '五谷养生坊', '长安区', '108.873848', '34.177288', '美食养生', '养生，健康', '/images/poi/wuguyangsheng1.jpg,/images/poi/wuguyangsheng2.jpg', '/images/tinypoi/wuguyangsheng1.jpg');
INSERT INTO `poi` VALUES ('82', '粥八戒养生粥', '未央区', '108.959915', '34.340931', '美食养生', '养生，喝粥的好去处', '/images/poi/zhoubajie1.jpg,/images/poi/zhoubajie2.jpg', '/images/tinypoi/zhoubajie1.jpg');
INSERT INTO `poi` VALUES ('83', '春养生', '碑林区', '108.947708', '34.261818', '美食养生', '养生好去处', '/images/poi/chunyangsheng1.jpg', '/images/tinypoi/chunyangsheng1.jpg');
INSERT INTO `poi` VALUES ('84', '柳粥记养生粥铺', '未央区', '108.950798', '34.306049', '美食养生', '养生好去处', '/images/poi/yangshengzhou1.jpg,/images/poi/yangshengzhou2.jpg', '/images/tinypoi/yangshengzhou1.jpg');
INSERT INTO `poi` VALUES ('85', '禅茶一味养生茶道坊', '未央区', '108.966049', '34.332333', '美食养生', '养生好去处', '/images/poi/changcha1.jpg,/images/poi/changcha2.jpg', '/images/tinypoi/changcha1.jpg');
INSERT INTO `poi` VALUES ('86', '御品养生苑', '灞桥区', '109.013206', '34.293293', '美食养生', '养生好去处', '/images/poi/yangshengyuan1.jpg', '/images/tinypoi/yangshengyuan1.jpg');
INSERT INTO `poi` VALUES ('87', '老米家泡馍', '新城区', '108.956696', '34.266445', '美食养生', '西安吃泡馍的好去处', '/images/poi/laomijiapaomo1.jpg,/images/poi/laomijiapaomo2.jpg,/images/poi/laomijiapaomo3.jpg', '/images/tinypoi/laomijiapaomo1.jpg');
INSERT INTO `poi` VALUES ('88', '窄巷子陕菜馆', '雁塔区', '108.976151', '34.205521', '美食养生', '独具特色的陕西菜', '/images/poi/kuangzhai1.jpg,/images/poi/kuangzhai2.jpg', '/images/tinypoi/kuangzhai1.jpg');
INSERT INTO `poi` VALUES ('89', '头道汤养生馆', '雁塔区', '108.992462', '34.218037', '美食养生', '喝汤，养生的好去处', '/images/poi/toudaotang1.jpg', '/images/tinypoi/toudaotang1.jpg');
INSERT INTO `poi` VALUES ('90', '中医养生馆', '未央区', '108.987015', '34.398102', '健康养生', '中医，养生好去处', '/images/poi/zhongyiyangsheng1.jpg', '/images/tinypoi/zhongyiyangsheng1.jpg');
INSERT INTO `poi` VALUES ('91', '道和汗蒸中医养生', '碑林区', '108.928307', '34.245495', '健康养生', '汗蒸，养生好去处', '/images/poi/hanzheng1.jpg', '/images/tinypoi/hanzheng1.jpg');
INSERT INTO `poi` VALUES ('92', '品宜中医养生', '临潼区', '109.379707', '34.622444', '健康养生', '中医，养生好去处', '/images/poi/pingyi1.jpg', '/images/tinypoi/pingyi1.jpg');
INSERT INTO `poi` VALUES ('93', '生命树中医养生馆', '莲湖区', '108.957550', '34.288994', '健康养生', '中医，养生好去处', '/images/poi/shengmingshuzhongyi.jpg', '/images/tinypoi/shengmingshuzhongyi.jpg');
INSERT INTO `poi` VALUES ('94', '中医经络养生馆', '灞桥区', '109.066109', '34.293579', '健康养生', '中医，养生好去处', '/images/poi/jingluoyangs1.jpg', '/images/tinypoi/jingluoyangs1.jpg');
INSERT INTO `poi` VALUES ('95', '权健火疗中医养生馆', '长安区', '108.937500', '34.169048', '健康养生', '中医，养生好去处', '/images/poi/quanjinaghuoliao1.jpg', '/images/tinypoi/quanjinaghuoliao1.jpg');
INSERT INTO `poi` VALUES ('96', '华润万家(大雁塔店)', '雁塔区', '108.971085', '34.219894', '商场超市', '物美价廉', '/images/poi/huarunwangjia1.jpg,/images/poi/huarunwangjia2.jpg', '/images/tinypoi/huarunwangjia1.jpg');
INSERT INTO `poi` VALUES ('97', '沃尔玛(西安雁塔路店)', '雁塔区', '108.968719', '34.249451', '商场超市', '物美价廉', '/images/poi/woerma1.jpg,/images/poi/woerma2.jpg', '/images/tinypoi/woerma1.jpg');
INSERT INTO `poi` VALUES ('98', '大唐购物中心', '咸阳市', '108.720772', '34.341900', '商城超市', '物美价廉', '/images/poi/datangguoji1.jpg,/images/poi/datangguoji2.jpg', '/images/tinypoi/datangguoji1.jpg');
INSERT INTO `poi` VALUES ('99', '阳阳国际购物广场', '雁塔区', '108.944206', '34.223518', '商城超市', '物美价廉', '/images/poi/yanyanguoji1.jpg', '/images/tinypoi/yanyanguoji1.jpg');
INSERT INTO `poi` VALUES ('100', '世纪金花', '雁塔区', '108.909966', '34.238136', '商城超市', '物美价廉', '/images/poi/shijijinghua1.jpg', '/images/tinypoi/shijijinghua1.jpg');
INSERT INTO `poi` VALUES ('101', '好又多', '雁塔区', '108.856430', '34.205956', '商城超市', '物美价廉', '/images/poi/haoyouduoxian1.jpg', '/images/tinypoi/haoyouduoxian1.jpg');
INSERT INTO `poi` VALUES ('102', '金莎国际购物广场', '雁塔区', '108.953911', '34.232464', '商城超市', '物美价廉', '/images/poi/jingshaguoji1.jpg', '/images/tinypoi/jingshaguoji1.jpg');
INSERT INTO `poi` VALUES ('103', '民生购物广场', '长安区', '108.925621', '34.205235', '商城超市', '物美价廉', '/images/poi/mingshenggouwu1.jpg,/images/poi/mingshenggouwu2.jpg', '/images/tinypoi/mingshenggouwu1.jpg');
INSERT INTO `poi` VALUES ('105', '陕西师范大学', '雁塔区', '108.958923', '34.210979', '校园社区', '人文气息浓厚', '/images/poi/shanxishifan1.jpg,/images/poi/shanxishifan2.jpg', '/images/tinypoi/shanxishifan1.jpg');
INSERT INTO `poi` VALUES ('106', '西安文理学院操场', '雁塔区', '108.913040', '34.222496', '校园社区', '散步，慢跑好地方', '/images/poi/xianwenli1.jpg,/images/poi/xianwenli2.jpg', '/images/tinypoi/xianwenli1.jpg');
INSERT INTO `poi` VALUES ('107', '西北大学', '碑林区', '108.933655', '34.254326', '校园社区', '人文气息浓厚', '/images/poi/xibeidaxue1.jpg,/images/poi/xibeidaxue2.jpg', '/images/tinypoi/xibeidaxue1.jpg');
INSERT INTO `poi` VALUES ('108', '西安电子科技大学离退休人员活动中心', '雁塔区', '108.924469', '34.235390', '校园社区', '休闲娱乐好去处', null, null);
INSERT INTO `poi` VALUES ('110', '西安石油大学-社区老人活动室', '雁塔区', '108.937363', '34.220177', '校园社区', '休闲娱乐好去处', '/images/poi/shiyoudaxueshequlaoren1.jpg,/images/poi/shiyoudaxueshequlaoren2.jpg', '/images/tinypoi/shiyoudaxueshequlaoren1.jpg');
INSERT INTO `poi` VALUES ('112', '咸东社区-老年协会', '新城区', '109.026352', '34.257183', '校园社区', '休闲娱乐好去处', '/images/poi/xiandonglaonianxuehui1.jpg,/images/poi/xiandonglaonianxuehui2.jpg', '/images/tinypoi/xiandonglaonianxuehui1.jpg');
INSERT INTO `poi` VALUES ('113', '经九社区老年之家', '碑林区', '108.987411', '34.243969', '校园社区', '休闲娱乐好去处', '/images/poi/jingjiulaonianzhijia1.jpg,/images/poi/jingjiulaonianzhijia2.jpg', '/images/tinypoi/jingjiulaonianzhijia1.jpg');
INSERT INTO `poi` VALUES ('114', '夕阳红老年之家西何社区', '碑林区', '108.931313', '34.244064', '校园社区', '休闲娱乐好去处', '/images/poi/xiyanghonglaonian1.jpg', '/images/tinypoi/xiyanghonglaonian1.jpg');
INSERT INTO `poi` VALUES ('115', '天朗社区中老年大学(观园分校)', '莲湖区', '108.909317', '34.272293', '校园社区', '休闲娱乐好去处', '/images/poi/tianlanglaoniandaxue1.jpg,/images/poi/tianlanglaoniandaxue2.jpg', '/images/tinypoi/tianlanglaoniandaxue1.jpg');
INSERT INTO `poi` VALUES ('116', '陕西老年大学', '碑林区', '108.947998', '34.264923', '校园社区', '休闲娱乐好去处', '/images/poi/shanxilaoniandaxue1.jpg,/images/poi/shanxilaoniandaxue2.jpg', '/images/tinypoi/shanxilaoniandaxue1.jpg');
INSERT INTO `poi` VALUES ('117', '千户社区老年协会', '雁塔区', '109.011925', '34.240459', '校园社区', '休闲娱乐好去处', '/images/poi/qianhulaonianxuehui1.jpg,/images/poi/qianhulaonianxuehui2.jpg', '/images/tinypoi/qianhulaonianxuehui1.jpg');
INSERT INTO `poi` VALUES ('118', '曲江社区老年大学', '雁塔区', '108.986946', '34.200382', '校园社区', '休闲娱乐好去处', '/images/poi/qujianglaoniandaxue1.jpg', '/images/tinypoi/qujianglaoniandaxue1.jpg');
INSERT INTO `poi` VALUES ('119', '精密社区老年协会', '莲湖区', '108.885704', '34.279778', '校园社区', '休闲娱乐好去处', '/images/poi/jingmishequ1.jpg,/images/poi/jingmishequ2.jpg', '/images/tinypoi/jingmishequ1.jpg');
INSERT INTO `poi` VALUES ('120', '八府庄园社区老年协会', '新城区', '108.987297', '34.296524', '校园社区', '休闲娱乐好去处', '/images/poi/shequlaonianxuehui1.jpg,/images/poi/shequlaonianxuehui2.jpg,//images/poi/shequlaonianxuehui3.jpg', '/images/tinypoi/shequlaonianxuehui1.jpg');
INSERT INTO `poi` VALUES ('121', '翁家社区老年活动中心', '未央区', '108.946724', '34.335072', '校园社区', '休闲娱乐好去处', '/images/poi/shequlaoren1.jpg,/images/poi/shequlaoren2.jpg', '/images/tinypoi/shequlaoren1.jpg');
INSERT INTO `poi` VALUES ('122', '茶道中老年活动中心', '莲湖区', '108.873970', '34.263103', '休闲娱乐', '休闲娱乐好去处', '/images/poi/zhonglaonianhuodong1.jpg,/images/poi/zhonglaonianhuodong2.jpg', '/images/tinypoi/zhonglaonianhuodong1.jpg');
INSERT INTO `poi` VALUES ('124', '老秦人茶艺剧场', '临潼区', '109.291695', '34.392948', '休闲娱乐', '休闲娱乐好去处', '/images/poi/laoqinrenchayi1.jpg,/images/poi/laoqinrenchayi2.jpg,//images/poi/laoqinrenchayi3.jpg', '/images/tinypoi/laoqinrenchayi1.jpg');
INSERT INTO `poi` VALUES ('125', '秦韵茶艺', '长安区', '108.869118', '34.166340', '休闲娱乐', '休闲娱乐好去处', '/images/poi/qinyunchayi1.jpg,/images/poi/qinyunchayi2.jpg', '/images/tinypoi/qinyunchayi1.jpg');
INSERT INTO `poi` VALUES ('126', '雅圣棋牌茶艺', '灞桥区', '109.058487', '34.285892', '休闲娱乐', '休闲娱乐好去处', '/images/poi/qipaichayi1.jpg,/images/poi/qipaichayi2.jpg', '/images/tinypoi/qipaichayi1.jpg');
INSERT INTO `poi` VALUES ('127', '西安市老年书画研究会', '雁塔', '109.000359', '34.219734', '休闲娱乐', '休闲娱乐好去处', '/images/poi/xianshishuhua1.jpg,/images/poi/xianshishuhua2.jpg', '/images/tinypoi/xianshishuhua1.jpg');
INSERT INTO `poi` VALUES ('128', '省老年书画学会胡家庙分会', '新城区', '109.005295', '34.284176', '休闲娱乐', '休闲娱乐好去处', '/images/poi/shenglainianshuhua1.jpg,/images/poi/shenglainianshuhua2.jpg', '/images/tinypoi/shenglainianshuhua1.jpg');
INSERT INTO `poi` VALUES ('129', '西安高新中老年书画艺术交流中心', '雁塔区', '108.910019', '34.226677', '休闲娱乐', '休闲娱乐好去处', '/images/poi/laonianshuhua1.jpg,/images/poi/laonianshuhua2.jpg,//images/poi/laonianshuhua3.jpg', '/images/tinypoi/laonianshuhua1.jpg');
INSERT INTO `poi` VALUES ('130', '秦风韵秦腔茶楼(秦腔青曲社)', '碑林区', '108.964203', '34.249470', '休闲娱乐', '休闲娱乐好去处', '/images/poi/qingqushe1.jpg,/images/poi/qingqushe2.jpg', '/images/tinypoi/qingqushe1.jpg');
INSERT INTO `poi` VALUES ('131', '秦腔博物馆-北门', '碑林区', '108.986740', '34.248039', '休闲娱乐', '休闲娱乐好去处', '/images/poi/qinqiangbowuguan1.jpg,/images/poi/qinqiangbowuguan2.jpg,//images/poi/qinqiangbowuguan3.jpg', '/images/tinypoi/qinqiangbowuguan1.jpg');
INSERT INTO `poi` VALUES ('132', '雁塔区象棋协会', '雁塔区', '108.921097', '34.203449', '休闲娱乐', '休闲娱乐好去处', '/images/poi/yantaxiangqi1.jpg,/images/poi/yantaxiangqi2.jpg', '/images/tinypoi/yantaxiangqi1.jpg');
INSERT INTO `poi` VALUES ('133', '未央国际象棋俱乐部', '末央区', '108.940193', '34.355576', '休闲娱乐', '休闲娱乐好去处', '/images/poi/weiyangxiangqi1.jpg', '/images/tinypoi/weiyangxiangqi1.jpg');
INSERT INTO `poi` VALUES ('134', '曲江书画艺术馆', '雁塔区', '108.971596', '34.211006', '休闲娱乐', '陕西省较有影响的书画艺术馆', '/images/poi/qujiangyishuguan1.jpg,/images/poi/qujiangyishuguan2.jpg', '/images/tinypoi/qujiangyishuguan1.jpg');

-- ----------------------------
-- Table structure for `relations`
-- ----------------------------
DROP TABLE IF EXISTS `relations`;
CREATE TABLE `relations` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `friend_id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relations
-- ----------------------------
INSERT INTO `relations` VALUES ('1', '1', '2');
INSERT INTO `relations` VALUES ('2', '2', '1');
INSERT INTO `relations` VALUES ('3', '1', '3');
INSERT INTO `relations` VALUES ('4', '3', '1');
INSERT INTO `relations` VALUES ('5', '1', '5');
INSERT INTO `relations` VALUES ('6', '5', '1');
INSERT INTO `relations` VALUES ('7', '1', '6');
INSERT INTO `relations` VALUES ('8', '6', '1');
INSERT INTO `relations` VALUES ('9', '2', '6');
INSERT INTO `relations` VALUES ('10', '6', '2');
INSERT INTO `relations` VALUES ('11', '2', '3');
INSERT INTO `relations` VALUES ('12', '3', '2');
INSERT INTO `relations` VALUES ('13', '2', '4');
INSERT INTO `relations` VALUES ('14', '4', '2');
INSERT INTO `relations` VALUES ('15', '3', '5');
INSERT INTO `relations` VALUES ('16', '5', '3');
INSERT INTO `relations` VALUES ('17', '3', '8');
INSERT INTO `relations` VALUES ('18', '8', '3');
INSERT INTO `relations` VALUES ('21', '11', '8');
INSERT INTO `relations` VALUES ('22', '8', '11');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(14) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `userCreateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13669277371', '黄河1', '123456', null, '2018-12-29 17:32:12');
INSERT INTO `user` VALUES ('2', '13669277372', '黄河2', '123456', null, '2018-12-29 17:29:23');
INSERT INTO `user` VALUES ('3', '13669277373', '黄河3', '123456', null, '2018-12-29 17:29:29');
INSERT INTO `user` VALUES ('4', '13669277374', '黄河4', '123456', null, '2018-12-29 17:29:31');
INSERT INTO `user` VALUES ('5', '13669277375', '黄河5', '123456', null, '2018-12-29 17:29:33');
INSERT INTO `user` VALUES ('6', '13669277376', '黄河6', '123456', null, '2018-12-29 17:29:40');
INSERT INTO `user` VALUES ('7', '13669277377', '黄河7', '123456', null, '2019-01-01 16:09:32');
INSERT INTO `user` VALUES ('8', '13669277378', '黄河8', '123456', null, '2019-01-01 16:09:35');
INSERT INTO `user` VALUES ('9', '13669277379', '黄河9', '123456', null, '2019-01-01 16:09:55');
INSERT INTO `user` VALUES ('10', '13072925428', 'Andy1', '123456', null, '2019-01-01 20:01:26');
INSERT INTO `user` VALUES ('11', '13072925421', 'Andy01', '123456', null, '2019-01-01 20:06:26');
INSERT INTO `user` VALUES ('12', '13072925422', 'Andy02', '123456', null, '2019-01-01 21:54:47');

-- ----------------------------
-- View structure for `temp`
-- ----------------------------
DROP VIEW IF EXISTS `temp`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `temp` AS select `check`.`user_id` AS `user_id`,`check`.`poi_id` AS `poi_id`,count(`check`.`poi_id`) AS `count`,avg(`check`.`rate`) AS `rate` from `check` group by `check`.`user_id`,`check`.`poi_id` ;
