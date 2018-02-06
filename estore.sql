#如果存在数据库"estore"，删除estore
DROP DATABASE IF EXISTS `estore`;

#如果数据库"estore"不存在，则创建"estore"数据库，使用utf8编码格式
CREATE DATABASE `estore` DEFAULT CHARACTER SET utf8;

#切换到estore数据库
USE `estore`;
#如果用户表user存在，删除user表-----------------------------------------------------------
DROP TABLE IF EXISTS `user`;
#创建用户表user
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nickname` VARCHAR(10) DEFAULT NULL COMMENT '昵称',
  `username` VARCHAR(32) DEFAULT NULL COMMENT '用户名',
  `password` CHAR(32) DEFAULT NULL COMMENT '密码',
  `role` VARCHAR(10) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

#如果商品表goods存在，删除goods表---------------------------------------------------------
DROP TABLE IF EXISTS `goods`;
#创建商品表goods
CREATE TABLE `goods` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '商品名称',
  `marketprice` DOUBLE(10,2) DEFAULT NULL COMMENT '市场价',
  `estoreprice` DOUBLE(10,2) DEFAULT NULL COMMENT 'estore商城优惠价',
  `category` VARCHAR(50) DEFAULT NULL COMMENT '商品分类',
  `num` INT(11) DEFAULT NULL COMMENT '库存数量',
  `imgurl` VARCHAR(500) DEFAULT NULL COMMENT '图片路径',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='商品表';
insert  into `goods`(`id`,`name`,`marketprice`,`estoreprice`,`category`,`num`,`imgurl`,`description`) values (3,'Java编程思想（第4版）',108.00,85.00,'图书、电子书刊、音像',999,'/upload/11/9/f913990ee3f04e029dd34f7d55a60fb1.jpg','本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在BruceEckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。'),(4,'JavaScript权威指南（第6版）',139.00,93.00,'图书、电子书刊、音像',999,'/upload/7/13/a9e547531642482b8217669f2698acb4.jpg','本书要讲述的内容涵盖JavaScript语言本身，以及Web浏览器所实现的JavaScriptAPI。本书第6版涵盖了HTML5和ECMAScript5，很多章节完全重写，增加了当今Web开发的最佳实践的内容，新增的章节包括jQuery、服务器端JavaScript、图形编程以及JavaScript式的面向对象。本书不仅适合初学者系统学习，也适合有经验的 JavaScript 开发者随手翻阅。'),(5,'锋利的jQuery(第2版)',49.00,40.00,'图书、电子书刊、音像',99,'/upload/8/15/774595f7c5b44fefa7b08b9cc5969e9f.jpg','《锋利的jQuery(第2版)》循序渐进地对jQuery的各种函数和方法调用进行了介绍，读者可以系统地掌握jQuery的选择器、DOM操作、事件和动画、AJAX应用、插件、jQueryMobile、jQuery各个版本变化、jQuery性能优化和技巧等知识点，并结合每个章节后面的案例演示进行练习，达到掌握核心知识点的目的。　　为使读者更好地进行开发实践，《锋利的jQuery(第2版)》的第8章将前7章讲解的知识点和效果进行了整合，打造出一个非常有个性的网站，并从案例研究、网站材料、网站结构、网站样式和网站脚本等方面指导读者参与到项目建设中来。　　《锋利的jQuery(第2版)》适合所有对jQuery技术感兴趣的Web设计者和前端开发人员阅读和参考。');


#如果购物车表cart存在，删除cart表---------------------------------------------------------
DROP TABLE IF EXISTS `cart`;
#创建购物车表cart
CREATE TABLE `cart` (
  `uid` INT(11) NOT NULL COMMENT '用户ID',
  `gid` INT(11) NOT NULL COMMENT '商品ID',
  `buynum` INT(11) DEFAULT NULL COMMENT '商品购买数量',
  PRIMARY KEY (`uid`,`gid`),
  KEY `FK_Reference_2` (`gid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='购物车表';

#如果订单表orders存在，删除orders表-------------------------------------------------------
DROP TABLE IF EXISTS `orders`;
#创建订单表orders
CREATE TABLE `orders` (
  `id` CHAR(32) NOT NULL COMMENT '订单ID',
  `uid` INT(11) DEFAULT NULL COMMENT '用户ID',
  `totalprice` DOUBLE(6,2) DEFAULT NULL COMMENT '订单总金额',
  `address` VARCHAR(200) DEFAULT NULL COMMENT '收货地址，包括收货人信息',
  `status` INT(11) DEFAULT NULL COMMENT '1：待付款，2：已付款3：已过期',
  `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`uid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='订单表';

#如果订单明细表orderitems存在，删除orderitems表-------------------------------------------
DROP TABLE IF EXISTS `orderitems`;
#创建订单明细表
CREATE TABLE `orderitems` (
  `oid` CHAR(32) NOT NULL COMMENT '订单ID',
  `gid` INT(11) NOT NULL COMMENT '商品ID',
  `buynum` INT(11) DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`oid`,`gid`),
  KEY `FK_Reference_4` (`gid`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`oid`) REFERENCES `orders` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='订单明细表';