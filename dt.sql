-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: dt
-- ------------------------------------------------------
-- Server version	5.7.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `dt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dt`;

--
-- Table structure for table `ct_category`
--

DROP TABLE IF EXISTS `ct_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_category` (
  `id` decimal(10,0) NOT NULL COMMENT '主键',
  `root` decimal(10,0) NOT NULL COMMENT '类目归属节点',
  `name` varchar(200) DEFAULT NULL COMMENT '类目名称',
  `mpic` varchar(50) DEFAULT NULL COMMENT '类目图片',
  `parent_id` decimal(10,0) DEFAULT NULL COMMENT '类目父级',
  `route` text COMMENT '路径',
  `mark` text COMMENT '备注',
  `node_level` decimal(2,0) DEFAULT NULL COMMENT '层级',
  `od` decimal(2,0) DEFAULT NULL COMMENT '顺序',
  `isaction` char(1) DEFAULT NULL COMMENT '是否有效(Y|N)',
  `code` varchar(50) DEFAULT NULL COMMENT '类目编码',
  `type` varchar(50) DEFAULT NULL COMMENT '类目类型',
  `action` varchar(50) DEFAULT NULL COMMENT '动作',
  `route_name` varchar(500) DEFAULT NULL COMMENT '全路径名称',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `unit` varchar(100) DEFAULT NULL COMMENT '单位',
  `upcnt` decimal(5,0) DEFAULT NULL COMMENT '安全库存上限',
  `downcnt` decimal(5,0) DEFAULT NULL COMMENT '安全库存下限',
  `unitprice` varchar(50) DEFAULT NULL COMMENT '默认单价',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `acl` varchar(50) DEFAULT NULL COMMENT '权限审批等控制字段',
  `brandmark` varchar(100) DEFAULT NULL COMMENT '品牌商标',
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ct_categoryinx` (`root`),
  KEY `ct_categoryinx2` (`root`,`isaction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_category`
--

LOCK TABLES `ct_category` WRITE;
/*!40000 ALTER TABLE `ct_category` DISABLE KEYS */;
INSERT INTO `ct_category` VALUES (46,3,'电子设备',NULL,3,'46','12',1,99,'Y',NULL,'dir','ndel','电子设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(47,3,'办公物品',NULL,3,'47',NULL,1,99,'Y','office','dir','ndel','办公物品',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(49,3,'无形资产',NULL,3,'49',NULL,1,99,'Y',NULL,'dir','ndel','无形资产',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(50,3,'服务器',NULL,46,'46-50','***',2,99,'Y','server','dir','ndel','电子设备/服务器',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(52,3,'光纤交换机',NULL,46,'46-52',NULL,2,99,'Y','lightsw','goods','ndel','电子设备/光纤交换机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(54,3,'安全设备',NULL,46,'46-54',NULL,2,99,'Y','safety','dir','ndel','电子设备/安全设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(55,3,'网点设备',NULL,46,'46-55',NULL,2,99,'Y','pointdev','dir','ndel','电子设备/网点设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(57,3,'存储设备',NULL,46,'46-57',NULL,2,99,'Y','storage','dir','ndel','电子设备/存储设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(58,3,'办公电脑',NULL,47,'47-58',NULL,2,99,'Y',NULL,'dir','ndel','办公物品/办公电脑',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(59,3,'加密机',NULL,54,'46-54-59',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/加密机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(61,3,'核验机',NULL,54,'46-54-61',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/核验机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(62,3,'办公设备',NULL,47,'47-62',NULL,2,99,'Y',NULL,'dir','ndel','办公物品/办公设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(63,3,'商标',NULL,49,'49-63',NULL,2,99,'Y',NULL,'dir','ndel','无形资产/商标',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(64,3,'笔记本电脑',NULL,58,'47-58-64',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/笔记本电脑',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(65,3,'台式电脑',NULL,58,'47-58-65',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/台式电脑',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(66,3,'显示器',NULL,58,'47-58-66',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/显示器',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(67,3,'打印机',NULL,62,'47-62-67',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/打印机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(68,3,'电视',NULL,62,'47-62-68',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/电视',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(69,3,'投影仪',NULL,62,'47-62-69',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/投影仪',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(70,3,'清分机',NULL,55,'46-55-70',NULL,3,99,'Y','1','goods','ndel','电子设备/网点设备/清分机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(71,3,'机房设备',NULL,46,'46-71',NULL,2,99,'Y','other','dir','ndel','电子设备/机房设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(72,3,'监控设备',NULL,71,'46-71-72',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/机房设备/监控设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(73,3,'空调设备',NULL,71,'46-71-73',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/机房设备/空调设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(74,3,'一体机终端',NULL,58,'47-58-74',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/一体机终端',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(75,3,'专利',NULL,49,'49-75',NULL,2,99,'Y',NULL,'goods','ndel','无形资产/专利',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(77,3,'保险箱',NULL,62,'47-62-77',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/保险箱',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(78,3,'办公家具',NULL,62,'47-62-78',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/办公家具',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(79,3,'柜员现金循环机',NULL,55,'46-55-79',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/柜员现金循环机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(80,3,'五合一读卡器',NULL,55,'46-55-80',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/五合一读卡器',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(81,3,'票据鉴别仪',NULL,55,'46-55-81',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/票据鉴别仪',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(82,3,'柜面终端',NULL,55,'46-55-82',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/柜面终端',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(83,3,'存折打印机',NULL,55,'46-55-83',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/存折打印机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(84,3,'入侵检测',NULL,54,'46-54-84',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/入侵检测',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(85,3,'防火墙',NULL,54,'46-54-85',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/防火墙',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(86,3,'捆钞机',NULL,55,'46-55-86',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/捆钞机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(87,3,'网银机',NULL,55,'46-55-87',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/网银机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(88,3,'保险箱',NULL,55,'46-55-88',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/保险箱',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(89,3,'点钞机',NULL,55,'46-55-89',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/点钞机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(90,3,'取款机',NULL,55,'46-55-90',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网点设备/取款机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(91,3,'空调',NULL,62,'47-62-91',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/空调',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(92,3,'动力设备',NULL,71,'46-71-92',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/机房设备/动力设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(93,3,'UPS',NULL,62,'47-62-93',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/UPS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(94,3,'沙发',NULL,62,'47-62-94',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/沙发',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(95,3,'碎纸机',NULL,62,'47-62-95',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/碎纸机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(96,3,'传真机',NULL,62,'47-62-96',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/传真机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(97,3,'空气净化器',NULL,62,'47-62-97',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/空气净化器',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(98,3,'会议桌',NULL,62,'47-62-98',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/会议桌',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(99,3,'平板',NULL,62,'47-62-99',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/平板',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(100,3,'鼠标',NULL,58,'47-58-100',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/鼠标',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(101,3,'网络加密机',NULL,54,'46-54-101',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/网络加密机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(102,3,'视频会议',NULL,62,'47-62-102',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/视频会议',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(103,3,'摄像机',NULL,62,'47-62-103',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/摄像机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(104,3,'椅子',NULL,62,'47-62-104',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/椅子',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(105,3,'桌子',NULL,62,'47-62-105',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/桌子',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(106,3,'文件柜',NULL,62,'47-62-106',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公设备/文件柜',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(110,3,'键盘',NULL,58,'47-58-110',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/键盘',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(111,3,'内存条',NULL,58,'47-58-111',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/内存条',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(112,3,'硬盘',NULL,58,'47-58-112',NULL,3,99,'Y',NULL,'goods','ndel','办公物品/办公电脑/硬盘',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(113,3,'堡垒机',NULL,54,'46-54-113',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/堡垒机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(114,3,'入侵防护',NULL,54,'46-54-114',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/入侵防护',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(115,3,'安全审计',NULL,54,'46-54-115',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/安全设备/安全审计',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(116,3,'网络设备',NULL,46,'46-116',NULL,2,99,'Y',NULL,'dir','ndel','电子设备/网络设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(117,3,'交换机',NULL,116,'46-116-117',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网络设备/交换机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(118,3,'路由器',NULL,116,'46-116-118',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网络设备/路由器',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(119,3,'波分设备',NULL,116,'46-116-119',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网络设备/波分设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(120,3,'其他设备',NULL,116,'46-116-120',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网络设备/其他设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(121,3,'集线器',NULL,116,'46-116-121',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网络设备/集线器',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(122,3,'防火墙',NULL,116,'46-116-122',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/网络设备/防火墙',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(123,3,'X86',NULL,50,'46-50-123',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/服务器/X86',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(124,3,'小机',NULL,50,'46-50-124',NULL,3,99,'Y',NULL,'goods','ndel','电子设备/服务器/小机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(125,3,'监控设备',NULL,54,'46-54-125',NULL,3,99,'Y',NULL,'goods',NULL,'电子设备/安全设备/监控设备',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(132,3,'终端管控',NULL,54,'46-54-132',NULL,3,99,'Y',NULL,'goods',NULL,'电子设备/安全设备/终端管控',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(133,6,'资产管理',NULL,6,'133',NULL,1,99,'Y',NULL,NULL,NULL,'资产管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(134,5,'资产管理流程',NULL,5,'134',NULL,1,99,'Y',NULL,NULL,NULL,'资产管理流程',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(143,7,'办公耗材',NULL,7,'143',NULL,1,99,'Y',NULL,'dir',NULL,'办公耗材',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(144,7,'低值易耗品',NULL,7,'144',NULL,1,99,'Y',NULL,'dir',NULL,'低值易耗品',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(145,7,'纸',NULL,143,'143-145','111',2,99,'Y',NULL,'goods',NULL,'办公耗材/纸','A4规格','箱',100,10,'20',NULL,NULL,'新新文具','0',NULL,NULL,NULL,NULL),(146,7,'网线',NULL,144,'144-146',NULL,2,99,'Y',NULL,'goods',NULL,'低值易耗品/网线','5类','箱',200,20,'120',NULL,NULL,'网科科技','0',NULL,NULL,NULL,NULL),(147,8,'备件类型A',NULL,8,'147',NULL,1,99,'Y',NULL,'dir',NULL,'备件类型A',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(148,8,'备件类型B',NULL,8,'148',NULL,1,99,'Y',NULL,'dir',NULL,'备件类型B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(149,8,'硬盘',NULL,147,'147-149','1',2,99,'Y','1212','goods',NULL,'备件类型A/硬盘','A500','支',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(164,3,'对象存储',NULL,57,'46-57-164',NULL,3,99,'Y',NULL,'goods',NULL,'电子设备/存储设备/对象存储',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(165,3,'传统存储',NULL,57,'46-57-165',NULL,3,99,'Y',NULL,'goods',NULL,'电子设备/存储设备/传统存储',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(166,8,'电源',NULL,148,'148-166',NULL,2,99,'Y',NULL,'goods',NULL,'备件类型B/电源','硬盘','块',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(167,7,'螺丝刀',NULL,144,'144-167',NULL,2,99,'Y',NULL,'goods',NULL,'低值易耗品/螺丝刀','LSD-DS-31','把',200,50,'7',NULL,NULL,'精品医用','0',NULL,NULL,NULL,NULL),(168,7,'劳保用品',NULL,7,'168',NULL,1,99,'Y',NULL,'dir',NULL,'劳保用品',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(169,7,'笔',NULL,143,'143-169',NULL,2,99,'Y',NULL,'goods',NULL,'办公耗材/笔','黑色SR-1型号','箱',50,10,'120',NULL,NULL,'欣欣文具','0',NULL,NULL,NULL,NULL),(170,7,'毛巾',NULL,168,'168-170',NULL,2,99,'Y',NULL,'goods',NULL,'劳保用品/毛巾','TT-05','条',10000,500,'3',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(171,3,'房屋建筑',NULL,3,'171',NULL,1,99,'Y',NULL,'dir',NULL,'房屋建筑',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(172,3,'房屋',NULL,171,'171-172',NULL,2,99,'Y',NULL,'goods',NULL,'房屋建筑/房屋',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(173,3,'软件',NULL,49,'49-173',NULL,2,99,'Y',NULL,'dir',NULL,'无形资产/软件',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(174,3,'软件许可',NULL,173,'49-173-174',NULL,3,99,'Y',NULL,'goods',NULL,'无形资产/软件/软件许可',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(175,3,'软件介质',NULL,173,'49-173-175',NULL,3,99,'Y',NULL,'goods',NULL,'无形资产/软件/软件介质',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(185,3,'大机',NULL,50,'46-50-185',NULL,3,99,'N',NULL,NULL,NULL,'电子设备/服务器/大机',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(186,7,'新节点',NULL,7,'186',NULL,1,99,NULL,NULL,NULL,NULL,'新节点',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(187,3,'新节点',NULL,50,'46-50-187',NULL,3,99,NULL,NULL,NULL,NULL,'电子设备/服务器/新节点',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(188,3,'新节点',NULL,63,'49-63-188',NULL,3,99,NULL,NULL,NULL,NULL,'无形资产/商标/新节点',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ct_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_category_root`
--

DROP TABLE IF EXISTS `ct_category_root`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_category_root` (
  `id` decimal(10,0) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '类目主名称',
  `type` varchar(50) DEFAULT NULL COMMENT '类目主类型',
  `mark` varchar(100) DEFAULT NULL COMMENT '备注',
  `od` decimal(1,0) DEFAULT NULL COMMENT '顺序',
  `inter_type` varchar(50) DEFAULT NULL COMMENT '内部分类(bus|system)',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_category_root`
--

LOCK TABLES `ct_category_root` WRITE;
/*!40000 ALTER TABLE `ct_category_root` DISABLE KEYS */;
INSERT INTO `ct_category_root` VALUES (3,'资产类目','hard','',3,NULL,NULL,NULL,NULL,NULL,'0'),(4,'流程分类','hard',NULL,4,NULL,NULL,NULL,NULL,NULL,'0'),(5,'流程模版',NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,'0'),(6,'表单模版',NULL,NULL,6,NULL,NULL,NULL,NULL,NULL,'0'),(7,'物品档案',NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,'0'),(8,'备件类目',NULL,NULL,8,NULL,NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `ct_category_root` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_content`
--

DROP TABLE IF EXISTS `ct_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_content` (
  `id` varchar(50) NOT NULL,
  `cat_id` varchar(50) DEFAULT NULL COMMENT '类型ID',
  `digest` char(1) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `profile` text COMMENT '介绍',
  `urltype` varchar(50) DEFAULT NULL COMMENT 'URL地址类型',
  `url` text COMMENT 'URL',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `mpic` varchar(50) DEFAULT NULL COMMENT '主图',
  `mpic_loc` varchar(20) DEFAULT NULL COMMENT '主图位置',
  `content` text COMMENT '内容',
  `hits` decimal(5,0) DEFAULT NULL COMMENT '点击数',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `sort` decimal(2,0) DEFAULT NULL COMMENT '排序',
  `display` char(1) DEFAULT NULL COMMENT '是否显示',
  `tag` varchar(50) DEFAULT NULL COMMENT '标记',
  `mark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id` varchar(50) DEFAULT NULL COMMENT '内容的所属用户',
  `col_a` text,
  `col_b` text,
  `col_c` text,
  `col_d` text,
  `col_e` text,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_content`
--

LOCK TABLES `ct_content` WRITE;
/*!40000 ALTER TABLE `ct_content` DISABLE KEYS */;
INSERT INTO `ct_content` VALUES ('1025911201639948289',NULL,NULL,'12','12','local','12','news','916DB4998D16DB8C04F7E710B40B2ABB','left','<p>12</p>',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','sys','sys','2018-08-05 09:07:45','2018-08-05 09:07:45'),('1168520781556572162',NULL,NULL,'12','12','local','12','news','16829958CE1DA8B156F939938EACEAFE','left','<p>12121212</p>',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','sys','sys','2019-09-02 21:47:19','2019-09-02 21:47:19');
/*!40000 ALTER TABLE `ct_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_store_sql`
--

DROP TABLE IF EXISTS `ct_store_sql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_store_sql` (
  `store_id` varchar(50) NOT NULL,
  `name` text,
  `cat_id` varchar(50) DEFAULT NULL,
  `uri` text,
  `uri_parameter` text,
  `user_id` varchar(50) DEFAULT NULL,
  `sql` text,
  `db_id` text,
  `acl` varchar(50) DEFAULT NULL COMMENT 'public,user',
  `mark` text,
  `return_type` varchar(100) DEFAULT NULL,
  `is_used` char(1) DEFAULT NULL,
  `alias_id` varchar(100) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_store_sql`
--

LOCK TABLES `ct_store_sql` WRITE;
/*!40000 ALTER TABLE `ct_store_sql` DISABLE KEYS */;
/*!40000 ALTER TABLE `ct_store_sql` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_uri`
--

DROP TABLE IF EXISTS `ct_uri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_uri` (
  `store_id` varchar(50) NOT NULL,
  `name` text,
  `cat_id` varchar(50) DEFAULT NULL,
  `uri` text,
  `uri_parameter` text,
  `user_id` varchar(50) DEFAULT NULL,
  `sql` text,
  `db_id` text,
  `ctime` datetime DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `acl` varchar(50) DEFAULT NULL COMMENT 'public,user',
  `mark` text,
  `return_type` varchar(100) DEFAULT NULL,
  `is_used` char(1) DEFAULT NULL,
  `alias_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_uri`
--

LOCK TABLES `ct_uri` WRITE;
/*!40000 ALTER TABLE `ct_uri` DISABLE KEYS */;
INSERT INTO `ct_uri` VALUES ('1','测试访问22',NULL,NULL,NULL,NULL,'select  *   from ct_uri  tab',NULL,NULL,'N','user','测试用途22','array','Y',NULL),('3b0ab2d7-f5e8-4166-8dbe-deb6d70d258d','12',NULL,NULL,NULL,NULL,'select 1 from dual',NULL,NULL,'Y','public','12','array','Y',NULL),('4bf50b8f-dc3f-4817-b497-73d55b1d8403','12',NULL,NULL,NULL,NULL,'12',NULL,NULL,NULL,'public','12','array','Y',NULL),('e113c6f8-153c-4cfb-a265-4494d2bbe16a','测试2',NULL,NULL,NULL,NULL,'select  * from sys_job',NULL,NULL,'Y','user','121212','array','Y',NULL);
/*!40000 ALTER TABLE `ct_uri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrm_org_employee`
--

DROP TABLE IF EXISTS `hrm_org_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hrm_org_employee` (
  `id` varchar(50) NOT NULL,
  `node_id` varchar(50) DEFAULT NULL COMMENT '组织节点ID',
  `empl_id` varchar(50) DEFAULT NULL COMMENT '员工工号',
  `dr` char(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrm_org_employee`
--

LOCK TABLES `hrm_org_employee` WRITE;
/*!40000 ALTER TABLE `hrm_org_employee` DISABLE KEYS */;
INSERT INTO `hrm_org_employee` VALUES ('0e7c989d6f5c4b8eb4588a13a260fa63','3','000284','0',NULL,NULL,NULL,NULL),('19906347964b48b49cca96a642f2c642','20','000301','0',NULL,NULL,NULL,NULL),('1c2fb05a191845f9a0908b5964404d3f','3','000287','0',NULL,NULL,NULL,NULL),('21e30d5c7a034fd68ee0146d1a234e6d','20','000290','0',NULL,NULL,NULL,NULL),('29194c940cae426f9d5da4bff19bd89c','39','000295','0',NULL,NULL,NULL,NULL),('32801dbfa15a4a808239d8a7f6935226','3','000283','0',NULL,NULL,NULL,NULL),('391ebf0c37e044138605bba075f3fd8e','3','000285','0',NULL,NULL,NULL,NULL),('3aff998e47a949c998a92469c8c2c7c8','3','000281','0',NULL,NULL,NULL,NULL),('47529768482d441d92ff935fcd8f1d02','3','000279','0',NULL,NULL,NULL,NULL),('4c09d9098670429a922e34e0c321853c','39','000296','0',NULL,NULL,NULL,NULL),('4c2ea78d843f4a038e4c3a66cc652c3f','3','000282','0',NULL,NULL,NULL,NULL),('4fbd6f76ec8d40f0ade2cc2816bd89b1','36','000293','0',NULL,NULL,NULL,NULL),('65e2d52c577a41e09b34bcc37073cebd','3','000286','0',NULL,NULL,NULL,NULL),('6c32fa4cd9f749219203c60eaa1a73a3','20','000299','0',NULL,NULL,NULL,NULL),('a0bebdb655594dd6b99af511284853d4','39','000297','0',NULL,NULL,NULL,NULL),('b42b883c77874ce6b47505c9f7aab1f5','39','000294','0',NULL,NULL,NULL,NULL),('bdbd4479f78b4dca8e3d0aec8f6b2da3','20','000291','0',NULL,NULL,NULL,NULL),('c77eaa0506e14f97af49c5d9ace382e3','3','000278','0',NULL,NULL,NULL,NULL),('cb9de11df1554023b90c531e165c6198','3','000277','0',NULL,NULL,NULL,NULL),('d0be733d2cac42878d0070b89e960302','3','000280','0',NULL,NULL,NULL,NULL),('d0fc39a5862949d997d6530221926706','21','000300','0',NULL,NULL,NULL,NULL),('eb64925299c74ff49e3229bbc3c66d86','33','000298','0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hrm_org_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrm_org_info`
--

DROP TABLE IF EXISTS `hrm_org_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hrm_org_info` (
  `org_id` decimal(5,0) NOT NULL,
  `org_name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `is_action` char(1) DEFAULT NULL COMMENT '是否有效',
  `dr` char(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrm_org_info`
--

LOCK TABLES `hrm_org_info` WRITE;
/*!40000 ALTER TABLE `hrm_org_info` DISABLE KEYS */;
INSERT INTO `hrm_org_info` VALUES (1,'公司组织架构','Y','0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hrm_org_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrm_org_part`
--

DROP TABLE IF EXISTS `hrm_org_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hrm_org_part` (
  `node_id` decimal(5,0) NOT NULL COMMENT '组织节点ID',
  `node_name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `org_id` decimal(5,0) DEFAULT NULL COMMENT '组织所属ID',
  `parent_id` decimal(5,0) DEFAULT NULL COMMENT '父节点',
  `route` varchar(100) DEFAULT NULL COMMENT '路径编码',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `route_name` text COMMENT '全路径名称',
  `dr` char(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrm_org_part`
--

LOCK TABLES `hrm_org_part` WRITE;
/*!40000 ALTER TABLE `hrm_org_part` DISABLE KEYS */;
INSERT INTO `hrm_org_part` VALUES (3,'滨湖支行',1,1,'3','comp','滨湖支行','0',NULL,NULL,NULL,NULL),(8,'新节点2',1,3,'3-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'豪迈有限公司',1,1,'9',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'财务部',1,9,'10',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'新天地有限公司',1,1,'11',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'蓝可科技有限公司',1,1,'12',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'蓝可科技宁波分公司',1,12,'13',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'蓝可科技杭州分公司',1,12,'14',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'财务部',1,13,'15',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'科技部',1,13,'16',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'物流部',1,14,'17',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'新节点',1,3,'3-18',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'杭州分公司',1,1,'20','comp','杭州分公司','0',NULL,NULL,NULL,NULL),(21,'物流组',1,20,'20-21','part','杭州分公司/物流组','0',NULL,NULL,NULL,NULL),(22,'北京分公司',1,1,'22','comp','北京分公司','0',NULL,NULL,NULL,NULL),(23,'物流组',1,22,'22-23','part','北京分公司/物流组','0',NULL,NULL,NULL,NULL),(24,'包装组',1,22,'22-24','part','北京分公司/包装组','0',NULL,NULL,NULL,NULL),(28,'物流组',1,3,'3-28','part','滨湖支行/物流组','0',NULL,NULL,NULL,NULL),(29,'包装组',1,3,'3-29','part','滨湖支行/包装组','0',NULL,NULL,NULL,NULL),(30,'设备组',1,3,'3-30','part','滨湖支行/设备组','0',NULL,NULL,NULL,NULL),(31,'包装组',1,20,'20-31','part','杭州分公司/包装组','0',NULL,NULL,NULL,NULL),(32,'设备组',1,20,'20-32','part','杭州分公司/设备组','0',NULL,NULL,NULL,NULL),(33,'设备组',1,22,'22-33','part','北京分公司/设备组','0',NULL,NULL,NULL,NULL),(35,'财务公司',1,1,'35','comp','财务公司','0',NULL,NULL,NULL,NULL),(36,'科技部',1,35,'35-36','part','财务公司/科技部','0',NULL,NULL,NULL,NULL),(37,'设备科',1,35,'35-37','part','财务公司/设备科','0',NULL,NULL,NULL,NULL),(38,'集团总部',1,1,'38','comp','集团总部','0',NULL,NULL,NULL,NULL),(39,'财务部',1,38,'38-39','part','集团总部/财务部','0',NULL,NULL,NULL,NULL),(40,'科技部',1,38,'38-40','part','集团总部/科技部','0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hrm_org_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ops_node`
--

DROP TABLE IF EXISTS `ops_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ops_node` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `runenv` varchar(50) DEFAULT NULL COMMENT '运行环境',
  `syslevel` varchar(50) DEFAULT NULL COMMENT '风险等级',
  `leader` varchar(500) DEFAULT NULL,
  `busitype` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `loc` varchar(50) DEFAULT NULL COMMENT '位置',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `osdtl` varchar(50) DEFAULT NULL COMMENT '操作系统详情',
  `nodebackup` varchar(500) DEFAULT NULL COMMENT '节点备份',
  `middleware` varchar(900) DEFAULT NULL COMMENT '中间件',
  `middlewarestr` varchar(900) DEFAULT NULL COMMENT '中间件',
  `db` varchar(900) DEFAULT NULL COMMENT '数据库',
  `dbdtl` varchar(900) DEFAULT NULL COMMENT '数据库详情',
  `execenv` varchar(100) DEFAULT NULL COMMENT '执行环境',
  `monitor` varchar(50) DEFAULT NULL COMMENT '是否在监控中',
  `pwdstrategy` varchar(50) DEFAULT NULL COMMENT '改密策略',
  `pwdmark` varchar(500) DEFAULT NULL COMMENT '密码备注',
  `importlabel` varchar(500) DEFAULT NULL COMMENT '全量导入标记',
  `label1` varchar(100) DEFAULT NULL COMMENT '标签1',
  `label2` varchar(100) DEFAULT NULL COMMENT '标签2',
  `label3` varchar(100) DEFAULT NULL COMMENT '标签3',
  `label4` varchar(100) DEFAULT NULL COMMENT '标签4',
  `label5` varchar(100) DEFAULT NULL COMMENT '标签5',
  `label6` varchar(100) DEFAULT NULL COMMENT '标签6',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `userapp` varchar(1000) DEFAULT NULL COMMENT '应用用户',
  `userdb` varchar(1000) DEFAULT NULL COMMENT '数据库用户',
  `useradmin` varchar(1000) DEFAULT NULL COMMENT '管理员用户',
  `usermid` varchar(1000) DEFAULT NULL COMMENT '中间件用户',
  `userother` varchar(1000) DEFAULT NULL COMMENT '其他用户',
  `userops` varchar(1000) DEFAULT NULL COMMENT '运维用户',
  `usernologin` varchar(1000) DEFAULT NULL COMMENT '不可使用用户',
  `userdbused` varchar(1000) DEFAULT NULL COMMENT '数据库使用用户',
  `nodebackupdtl` varchar(1000) DEFAULT NULL COMMENT '节点备份详情',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ops_node`
--

LOCK TABLES `ops_node` WRITE;
/*!40000 ALTER TABLE `ops_node` DISABLE KEYS */;
/*!40000 ALTER TABLE `ops_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ops_node_infosys`
--

DROP TABLE IF EXISTS `ops_node_infosys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ops_node_infosys` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `about` varchar(1000) DEFAULT NULL COMMENT '介绍',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `ifmain` varchar(50) DEFAULT NULL COMMENT '是否重要系统',
  `opsmethod` varchar(500) DEFAULT NULL COMMENT '运维模式',
  `devmethod` varchar(500) DEFAULT NULL COMMENT '开发模式',
  `tcontact` varchar(1000) DEFAULT NULL,
  `bcontact` varchar(1000) DEFAULT NULL,
  `bpart` varchar(500) DEFAULT NULL,
  `lastdrilldate` varchar(1000) DEFAULT NULL,
  `ondatestr` varchar(100) DEFAULT NULL COMMENT '上线时间',
  `downdatestr` varchar(100) DEFAULT NULL COMMENT '下线时间',
  `os` varchar(500) DEFAULT NULL COMMENT '操作系统',
  `db` varchar(500) DEFAULT NULL COMMENT '数据库',
  `app` varchar(500) DEFAULT NULL COMMENT '应用',
  `grade` varchar(50) DEFAULT NULL,
  `rto` varchar(100) DEFAULT NULL COMMENT 'RTO',
  `rpo` varchar(100) DEFAULT NULL COMMENT 'RPO',
  `Hardware` varchar(500) DEFAULT NULL COMMENT '硬件',
  `bkmethod` varchar(500) DEFAULT NULL COMMENT '备份方式',
  `sameplacebkmethod` varchar(500) DEFAULT NULL COMMENT '本地备份方式',
  `diffplacebkmethod` varchar(500) DEFAULT NULL COMMENT '异地备份方式',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `label1` varchar(500) DEFAULT NULL,
  `label2` varchar(500) DEFAULT NULL,
  `label3` varchar(500) DEFAULT NULL,
  `label4` varchar(500) DEFAULT NULL,
  `mark` varchar(200) DEFAULT NULL COMMENT '备注',
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ops_node_infosys`
--

LOCK TABLES `ops_node_infosys` WRITE;
/*!40000 ALTER TABLE `ops_node_infosys` DISABLE KEYS */;
/*!40000 ALTER TABLE `ops_node_infosys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ops_node_item`
--

DROP TABLE IF EXISTS `ops_node_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ops_node_item` (
  `id` varchar(50) NOT NULL,
  `nid` varchar(50) DEFAULT NULL,
  `item` varchar(50) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `dbinstance` varchar(500) DEFAULT NULL,
  `archtype` varchar(50) DEFAULT NULL,
  `bkstrategy` varchar(500) DEFAULT NULL,
  `bktype` varchar(50) DEFAULT NULL,
  `bkkeep` varchar(500) DEFAULT NULL,
  `bkstatus` varchar(50) DEFAULT NULL,
  `mark` varchar(500) DEFAULT NULL,
  `bkmethod` varchar(50) DEFAULT NULL,
  `dsize` varchar(500) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ops_node_item`
--

LOCK TABLES `ops_node_item` WRITE;
/*!40000 ALTER TABLE `ops_node_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `ops_node_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_id`
--

DROP TABLE IF EXISTS `part_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_id` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `pdbatchid` varchar(50) DEFAULT NULL,
  `pdid` varchar(50) DEFAULT NULL,
  `pdstatus` varchar(50) DEFAULT NULL COMMENT '盘点状态,wait|finish',
  `pdsyncneed` varchar(50) DEFAULT NULL COMMENT '盘点处理,1|0',
  `pdtime` varchar(50) DEFAULT NULL COMMENT '盘点时间',
  `pduserid` varchar(50) DEFAULT NULL COMMENT '盘点人ID',
  `pdusername` varchar(50) DEFAULT NULL COMMENT '盘点人',
  `pdmark` varchar(50) DEFAULT NULL COMMENT '盘点备注',
  `resid` varchar(50) DEFAULT NULL,
  `zc_category` varchar(50) DEFAULT NULL COMMENT '资产类目',
  `class_id` varchar(50) DEFAULT NULL COMMENT '大类',
  `type` varchar(50) DEFAULT NULL COMMENT '小类',
  `gj_dl` varchar(50) DEFAULT NULL COMMENT '国际大类',
  `gj_xl` varchar(50) DEFAULT NULL COMMENT '国际小类',
  `uuid` varchar(50) DEFAULT NULL COMMENT '资产编号',
  `name` text COMMENT '资产名称',
  `zcsource` varchar(50) DEFAULT NULL COMMENT '资产来源',
  `model` varchar(500) DEFAULT NULL COMMENT '型号',
  `sn` text COMMENT '序列号',
  `version` varchar(100) DEFAULT NULL COMMENT '版本',
  `res_desc` text COMMENT ' 资产描述',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `recycle` varchar(50) DEFAULT NULL COMMENT '生命周期状态',
  `prerecycle` varchar(50) DEFAULT NULL COMMENT '生命周期',
  `env` varchar(100) DEFAULT NULL COMMENT '运行环境',
  `risk` varchar(50) DEFAULT NULL COMMENT '风险等级',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下线时间',
  `online_time` datetime DEFAULT NULL COMMENT '上线时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `rwm` varchar(50) DEFAULT NULL COMMENT '二维码',
  `confdesc` varchar(509) DEFAULT NULL COMMENT '配置描述',
  `loc` varchar(50) DEFAULT NULL COMMENT '资产区域',
  `locshow` varchar(1) DEFAULT NULL COMMENT '资产区域是否显示',
  `locdtl` varchar(1000) DEFAULT NULL COMMENT '位置详情',
  `rack` varchar(50) DEFAULT NULL COMMENT '机柜号',
  `frame` varchar(50) DEFAULT NULL COMMENT '机架号',
  `belong_company_id` varchar(50) DEFAULT NULL COMMENT '归属公司',
  `belong_part_id` varchar(50) DEFAULT NULL COMMENT '归属部门',
  `used_company_id` varchar(50) DEFAULT NULL COMMENT '使用公司Id',
  `part_id` decimal(5,0) DEFAULT NULL COMMENT '使用部门Id',
  `used_userid` varchar(50) DEFAULT NULL COMMENT '使用人Id',
  `mgr_part_id` varchar(50) DEFAULT NULL COMMENT '管理部门Id',
  `maintain_userid` varchar(50) DEFAULT NULL COMMENT '维护人用户ID',
  `headuserid` varchar(50) DEFAULT NULL COMMENT '负责人ID',
  `buy_price` decimal(10,2) DEFAULT NULL COMMENT '采购单价',
  `net_worth` decimal(10,2) DEFAULT NULL COMMENT '当前价值',
  `zc_cnt` decimal(10,2) DEFAULT '1.00' COMMENT '资产数量',
  `actionstatus` varchar(50) DEFAULT NULL COMMENT '资产变更状态',
  `wb` varchar(50) DEFAULT NULL COMMENT '维保状态',
  `wb_auto` varchar(50) DEFAULT NULL COMMENT '是否自动计算维保:1,0',
  `wbout_date` datetime DEFAULT NULL COMMENT '脱保时间',
  `wbsupplier` varchar(50) DEFAULT NULL COMMENT '维保供应商',
  `wbct` varchar(100) DEFAULT NULL COMMENT '维保说明',
  `status` varchar(100) DEFAULT NULL COMMENT '状态,未使用',
  `changestatus` varchar(50) DEFAULT NULL COMMENT '资产变更期间临时状态',
  `importlabel` varchar(50) DEFAULT NULL COMMENT '批量导入标记',
  `img` varchar(50) DEFAULT NULL COMMENT '图片Id',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `changestate` varchar(50) DEFAULT NULL COMMENT '复核状态:updated,reviewed',
  `review_userid` varchar(50) DEFAULT NULL COMMENT '复核人Id',
  `review_date` datetime DEFAULT NULL COMMENT '最后复核时间',
  `fs1` text COMMENT '资产标签1',
  `fs2` text COMMENT '资产标签2',
  `fs3` text,
  `fs4` text,
  `fs5` text,
  `fs6` text,
  `fs7` text,
  `fs8` text,
  `fs9` text,
  `fs10` text,
  `fs11` text,
  `fs12` text,
  `fs13` text,
  `fs14` text,
  `fs15` text,
  `fs16` text,
  `fs17` text,
  `fs18` text,
  `fs19` text,
  `fs20` text,
  `fi1` decimal(32,0) DEFAULT NULL,
  `fi2` decimal(32,0) DEFAULT NULL,
  `fi3` decimal(32,0) DEFAULT NULL,
  `fi4` decimal(32,0) DEFAULT NULL,
  `fi5` decimal(32,0) DEFAULT NULL,
  `fi6` decimal(32,0) DEFAULT NULL,
  `fi7` decimal(32,0) DEFAULT NULL,
  `fi8` decimal(32,0) DEFAULT NULL,
  `fi9` decimal(32,0) DEFAULT NULL,
  `fi10` decimal(32,0) DEFAULT NULL,
  `fi11` decimal(32,0) DEFAULT NULL,
  `fi12` decimal(32,0) DEFAULT NULL,
  `fi13` decimal(32,0) DEFAULT NULL,
  `fi14` decimal(32,0) DEFAULT NULL,
  `fi15` decimal(32,0) DEFAULT NULL,
  `fi16` decimal(32,0) DEFAULT NULL,
  `fi17` decimal(32,0) DEFAULT NULL,
  `fi18` decimal(32,0) DEFAULT NULL,
  `fi19` decimal(32,0) DEFAULT NULL,
  `fi20` decimal(32,0) DEFAULT NULL,
  `fd1` datetime DEFAULT NULL,
  `fd2` datetime DEFAULT NULL,
  `fd3` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_id`
--

LOCK TABLES `part_id` WRITE;
/*!40000 ALTER TABLE `part_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `part_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res`
--

DROP TABLE IF EXISTS `res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `category` varchar(50) DEFAULT NULL COMMENT '资产类目',
  `class_id` varchar(50) DEFAULT NULL COMMENT '大类',
  `lastinventorytime` datetime DEFAULT NULL COMMENT '最近一次盘点时间',
  `gj_dl` varchar(50) DEFAULT NULL COMMENT '国际大类',
  `gj_xl` varchar(50) DEFAULT NULL COMMENT '国际小类',
  `type` varchar(50) DEFAULT NULL COMMENT '小类',
  `uuid` varchar(50) DEFAULT NULL COMMENT '资产编号',
  `actionstatus` varchar(50) DEFAULT NULL COMMENT '资产变更状态',
  `crkstatus` varchar(50) DEFAULT NULL COMMENT '出入库单据状态，待审批wait,已同意agreen,拒绝deny,打回back,无需审批none',
  `status` varchar(100) DEFAULT NULL COMMENT '状态,该字段未使用',
  `changestatus` varchar(50) DEFAULT NULL COMMENT '资产变更期间临时状态',
  `name` text COMMENT '资产名称',
  `zc_cnt` decimal(10,0) DEFAULT '1' COMMENT '资产数量',
  `model` varchar(500) DEFAULT NULL COMMENT '型号',
  `zcsource` varchar(50) DEFAULT NULL COMMENT '资产来源',
  `sn` text COMMENT '序列号',
  `version` varchar(100) DEFAULT NULL COMMENT '版本',
  `res_desc` text COMMENT '资产描述',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `recycle` varchar(50) DEFAULT NULL COMMENT '生命周期状态',
  `prerecycle` varchar(50) DEFAULT NULL COMMENT '生命周期',
  `env` varchar(100) DEFAULT NULL COMMENT '运行环境',
  `risk` varchar(50) DEFAULT NULL COMMENT '风险等级',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下线时间',
  `online_time` datetime DEFAULT NULL COMMENT '上线时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `rwm` varchar(50) DEFAULT NULL COMMENT '二维码',
  `confdesc` varchar(509) DEFAULT NULL COMMENT '配置描述',
  `loc` varchar(50) DEFAULT NULL COMMENT '存放区域',
  `warehouse` varchar(50) DEFAULT NULL COMMENT '存放仓库',
  `locshow` varchar(1) DEFAULT NULL COMMENT '资产区域是否显示',
  `locdtl` varchar(1000) DEFAULT NULL COMMENT '位置详情',
  `rack` varchar(50) DEFAULT NULL COMMENT '机柜号',
  `frame` varchar(50) DEFAULT NULL COMMENT '机架号',
  `belong_company_id` varchar(50) DEFAULT NULL COMMENT '归属公司',
  `belong_part_id` varchar(50) DEFAULT NULL COMMENT '归属部门',
  `used_company_id` varchar(50) DEFAULT NULL COMMENT '使用公司Id',
  `part_id` varchar(50) DEFAULT NULL COMMENT '使用部门Id',
  `used_userid` varchar(50) DEFAULT NULL COMMENT '使用人Id',
  `mgr_part_id` varchar(50) DEFAULT NULL COMMENT '管理部门Id',
  `maintain_userid` varchar(50) DEFAULT NULL COMMENT '维护人用户ID',
  `headuserid` varchar(50) DEFAULT NULL COMMENT '负责人ID',
  `buy_price` decimal(10,2) DEFAULT '0.00' COMMENT '采购单价',
  `unit_price` decimal(10,2) DEFAULT '0.00' COMMENT '资产单价',
  `net_worth` decimal(10,2) DEFAULT '0.00' COMMENT '当前价值',
  `wb` varchar(50) DEFAULT NULL COMMENT '维保状态',
  `wb_auto` varchar(50) DEFAULT NULL COMMENT '是否自动计算维保:1,0',
  `wbout_date` datetime DEFAULT NULL COMMENT '脱保时间',
  `wbsupplier` varchar(50) DEFAULT NULL COMMENT '维保供应商',
  `wbct` varchar(100) DEFAULT NULL COMMENT '维保说明',
  `img` varchar(50) DEFAULT NULL COMMENT '图片Id',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `changestate` varchar(50) DEFAULT NULL COMMENT '复核状态:updated,reviewed',
  `review_userid` varchar(50) DEFAULT NULL COMMENT '复核人Id',
  `review_date` datetime DEFAULT NULL COMMENT '最后复核时间',
  `batchno` varchar(50) DEFAULT NULL COMMENT '出入库批次号',
  `importlabel` varchar(50) DEFAULT NULL COMMENT '批量导入标记',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `fs1` text COMMENT '资产标签1',
  `fs2` text COMMENT '资产标签2',
  `fs3` text,
  `fs4` text,
  `fs5` text,
  `fs6` text,
  `fs7` text,
  `fs8` text,
  `fs9` text,
  `fs10` text,
  `fs11` text,
  `fs12` text,
  `fs13` text,
  `fs14` text,
  `fs15` text,
  `fs16` text,
  `fs17` text,
  `fs18` text,
  `fs19` text,
  `fs20` text,
  `fi1` decimal(32,0) DEFAULT NULL,
  `fi2` decimal(32,0) DEFAULT NULL,
  `fi3` decimal(32,0) DEFAULT NULL,
  `fi4` decimal(32,0) DEFAULT NULL,
  `fi5` decimal(32,0) DEFAULT NULL,
  `fi6` decimal(32,0) DEFAULT NULL,
  `fi7` decimal(32,0) DEFAULT NULL,
  `fi8` decimal(32,0) DEFAULT NULL,
  `fi9` decimal(32,0) DEFAULT NULL,
  `fi10` decimal(32,0) DEFAULT NULL,
  `fi11` decimal(32,0) DEFAULT NULL,
  `fi12` decimal(32,0) DEFAULT NULL,
  `fi13` decimal(32,0) DEFAULT NULL,
  `fi14` decimal(32,0) DEFAULT NULL,
  `fi15` decimal(32,0) DEFAULT NULL,
  `fi16` decimal(32,0) DEFAULT NULL,
  `fi17` decimal(32,0) DEFAULT NULL,
  `fi18` decimal(32,0) DEFAULT NULL,
  `fi19` decimal(32,0) DEFAULT NULL,
  `fi20` decimal(32,0) DEFAULT NULL,
  `fd1` datetime DEFAULT NULL,
  `fd2` datetime DEFAULT NULL,
  `fd3` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `residualvalue` decimal(10,2) DEFAULT '0.00' COMMENT '资产残值',
  `usefullife` varchar(50) DEFAULT NULL COMMENT '资产使用年限',
  `lastdepreciationdate` date DEFAULT NULL COMMENT '最近一次折旧时间',
  `accumulateddepreciation` decimal(10,2) DEFAULT '0.00' COMMENT '累计折旧',
  PRIMARY KEY (`id`),
  KEY `residx1` (`category`),
  KEY `residx2` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res`
--

LOCK TABLES `res` WRITE;
/*!40000 ALTER TABLE `res` DISABLE KEYS */;
/*!40000 ALTER TABLE `res` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_action_item`
--

DROP TABLE IF EXISTS `res_action_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_action_item` (
  `id` varchar(50) NOT NULL,
  `busuuid` varchar(50) DEFAULT NULL COMMENT '资产审批业务ID',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `backtime` datetime DEFAULT NULL COMMENT '归还时间',
  `resid` varchar(50) DEFAULT NULL COMMENT '资产ID',
  `backtimestr` varchar(500) DEFAULT NULL COMMENT '归还时间',
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_action_item`
--

LOCK TABLES `res_action_item` WRITE;
/*!40000 ALTER TABLE `res_action_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_action_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_allocate`
--

DROP TABLE IF EXISTS `res_allocate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_allocate` (
  `id` varchar(50) NOT NULL,
  `uuid` varchar(50) DEFAULT NULL,
  `allocateuserid` varchar(50) DEFAULT NULL,
  `allocateusername` varchar(50) DEFAULT NULL,
  `frombelongcompid` varchar(50) DEFAULT NULL,
  `frombelongcompname` varchar(50) DEFAULT NULL,
  `touseduserid` varchar(50) DEFAULT NULL,
  `tousedusername` varchar(100) DEFAULT NULL,
  `tousedpartid` varchar(50) DEFAULT NULL,
  `tousedpartname` varchar(100) DEFAULT NULL,
  `tousedcompid` varchar(50) DEFAULT NULL,
  `tousedcompname` varchar(100) DEFAULT NULL,
  `tobelongcompid` varchar(50) DEFAULT NULL,
  `tobelongcompname` varchar(100) DEFAULT NULL,
  `tobelongpartid` varchar(50) DEFAULT NULL,
  `tobelongpartname` varchar(100) DEFAULT NULL,
  `toloc` varchar(50) DEFAULT NULL,
  `tolocname` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT '未完成 doing，已完成 finish，已取消 cancel',
  `acttime` datetime DEFAULT NULL,
  `tolocdtl` varchar(100) DEFAULT NULL,
  `mark` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_allocate`
--

LOCK TABLES `res_allocate` WRITE;
/*!40000 ALTER TABLE `res_allocate` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_allocate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_allocate_item`
--

DROP TABLE IF EXISTS `res_allocate_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_allocate_item` (
  `id` varchar(50) NOT NULL,
  `allocateid` varchar(50) DEFAULT NULL,
  `residprerecycle` varchar(50) DEFAULT NULL,
  `resid` varchar(50) DEFAULT NULL,
  `touseduserid` varchar(50) DEFAULT NULL,
  `tousedusername` varchar(100) DEFAULT NULL,
  `tousedpartid` varchar(50) DEFAULT NULL,
  `tousedpartname` varchar(100) DEFAULT NULL,
  `tousedcompid` varchar(50) DEFAULT NULL,
  `tousedcompname` varchar(100) DEFAULT NULL,
  `tobelongcompid` varchar(50) DEFAULT NULL,
  `tobelongcompname` varchar(100) DEFAULT NULL,
  `tobelongpartid` varchar(50) DEFAULT NULL,
  `tobelongpartname` varchar(100) DEFAULT NULL,
  `toloc` varchar(50) DEFAULT NULL,
  `tolocname` varchar(100) DEFAULT NULL,
  `tolocdtl` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `busuuid` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_allocate_item`
--

LOCK TABLES `res_allocate_item` WRITE;
/*!40000 ALTER TABLE `res_allocate_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_allocate_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_attr_value`
--

DROP TABLE IF EXISTS `res_attr_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_attr_value` (
  `id` varchar(50) NOT NULL,
  `resid` varchar(50) DEFAULT NULL COMMENT '属性值所属',
  `attrid` varchar(50) DEFAULT NULL COMMENT '属性Id',
  `attrvalue` varchar(1000) DEFAULT NULL COMMENT '属性值',
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_attr_value`
--

LOCK TABLES `res_attr_value` WRITE;
/*!40000 ALTER TABLE `res_attr_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_attr_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_attrs`
--

DROP TABLE IF EXISTS `res_attrs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_attrs` (
  `id` varchar(50) NOT NULL,
  `attrname` varchar(50) DEFAULT NULL COMMENT '扩展属性名称',
  `attrcode` varchar(100) DEFAULT NULL COMMENT '扩展属性编码',
  `inputtype` varchar(50) DEFAULT NULL COMMENT '属性输入类型',
  `catid` varchar(50) DEFAULT NULL COMMENT '属性归属',
  `ifneed` varchar(1) DEFAULT NULL COMMENT '是否必须',
  `ifinheritable` varchar(1) DEFAULT NULL COMMENT '属性是否可继承',
  `dict` varchar(50) DEFAULT NULL COMMENT '属性数据字典',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `res_attrsidx1` (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_attrs`
--

LOCK TABLES `res_attrs` WRITE;
/*!40000 ALTER TABLE `res_attrs` DISABLE KEYS */;
INSERT INTO `res_attrs` VALUES ('1273502014613016577','内存','memory','inputstr','50','1','1',NULL,1,'1151420235196588033','2020-06-18 06:25:15','2020-06-20 05:40:51','1151420235196588033','0'),('1273524219078033410','JQ','jj','inputstr','49','0','1',NULL,1,'1151420235196588033','2020-06-18 07:53:29','2020-06-18 07:53:59','1151420235196588033','0'),('1273524289118715905','测试','test','inputstr','63','0','0',NULL,1,'1151420235196588033','2020-06-18 07:53:46','2020-06-18 07:53:46','1151420235196588033','0'),('1273818572308631553','CPU型号','cpu','inputstr','50','0','1',NULL,1,'1151420235196588033','2020-06-19 03:23:09','2020-06-19 03:27:45','1151420235196588033','0'),('1273821861964173314','1','1','inputstr',NULL,'0','0',NULL,1,'1151420235196588033','2020-06-19 03:36:13','2020-06-19 03:36:13','1151420235196588033','0'),('1273822256170029057','硬盘','harddisk','inputstr','50','0','1',NULL,1,'1151420235196588033','2020-06-19 03:37:47','2020-06-19 03:38:29','1151420235196588033','0'),('1287634141869883394','test','test','inputstr','50','0','0',NULL,1,'1151420235196588033','2020-07-27 06:21:17','2020-07-27 06:21:17','1151420235196588033','0'),('1287637311505416193','test','test','inputstr','124','0','0',NULL,1,'1151420235196588033','2020-07-27 06:33:53','2020-07-27 06:33:53','1151420235196588033','0'),('1287637433668714497','test','test','inputstr','123','0','0',NULL,1,'1151420235196588033','2020-07-27 06:34:22','2020-07-27 06:34:22','1151420235196588033','0'),('1288392464059711489','1','1','inputint','101','0','1',NULL,1,'1151420235196588033','2020-07-29 08:34:35','2020-08-06 15:04:29','1151420235196588033','0'),('1288392538483441666','2','2','inputstr','101','0','1','1',1,'1151420235196588033','2020-07-29 08:34:53','2020-07-29 08:34:53','1151420235196588033','0'),('1288772799800922114','测试','000001','inputstr','54','0','0',NULL,1,'1276368794897408001','2020-07-30 09:45:54','2020-07-30 09:45:54','1276368794897408001','0'),('1290183475731718145','重量','HW00001','inputint','50','0','1',NULL,1,'1276368794897408001','2020-08-03 07:11:26','2020-08-03 07:20:11','1276368794897408001','0'),('1290184168467161090','重量','HW','inputstr','64','0','1',NULL,1,'1276368794897408001','2020-08-03 07:14:11','2020-08-03 07:14:11','1276368794897408001','0'),('1290185311687630849','体积','TJ1111','inputstr','50','1','0',NULL,1,'1276368794897408001','2020-08-03 07:18:43','2020-08-03 07:18:43','1276368794897408001','0');
/*!40000 ALTER TABLE `res_attrs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_change_item`
--

DROP TABLE IF EXISTS `res_change_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_change_item` (
  `id` varchar(50) DEFAULT NULL,
  `resid` varchar(50) DEFAULT NULL,
  `busuuid` varchar(50) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `fct` varchar(100) DEFAULT NULL,
  `tct` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_change_item`
--

LOCK TABLES `res_change_item` WRITE;
/*!40000 ALTER TABLE `res_change_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_change_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_class`
--

DROP TABLE IF EXISTS `res_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_class` (
  `class_id` varchar(50) NOT NULL,
  `class_code` varchar(50) DEFAULT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `subtype` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `sort` decimal(5,0) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_class`
--

LOCK TABLES `res_class` WRITE;
/*!40000 ALTER TABLE `res_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_class_attrs`
--

DROP TABLE IF EXISTS `res_class_attrs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_class_attrs` (
  `attr_id` varchar(50) NOT NULL,
  `attr_name` varchar(50) DEFAULT NULL,
  `attr_type` varchar(50) DEFAULT NULL,
  `sort` decimal(10,0) DEFAULT NULL,
  `attr_code` varchar(100) DEFAULT NULL,
  `class_id` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_class_attrs`
--

LOCK TABLES `res_class_attrs` WRITE;
/*!40000 ALTER TABLE `res_class_attrs` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_class_attrs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_history`
--

DROP TABLE IF EXISTS `res_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_history` (
  `id` varchar(50) NOT NULL,
  `res_id` varchar(50) DEFAULT NULL,
  `oper_time` datetime DEFAULT NULL,
  `oper_user` varchar(50) DEFAULT NULL,
  `fullct` text,
  `oper_type` varchar(50) DEFAULT NULL,
  `mark` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_history`
--

LOCK TABLES `res_history` WRITE;
/*!40000 ALTER TABLE `res_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_inout`
--

DROP TABLE IF EXISTS `res_inout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_inout` (
  `id` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT '类型:hc',
  `uuid` varchar(50) DEFAULT NULL COMMENT '单据号',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `action` varchar(50) DEFAULT NULL COMMENT 'HCRK,HCCK',
  `status` varchar(50) DEFAULT NULL COMMENT '待审批wait,已同意agreen,拒绝deny,打回back,无需审批none',
  `cnt` decimal(5,0) DEFAULT NULL COMMENT '类型数量',
  `zcsource` varchar(50) DEFAULT NULL COMMENT '资产来源',
  `suppliername` varchar(100) DEFAULT NULL COMMENT '供应商',
  `buytime` varchar(100) DEFAULT NULL COMMENT '购买时间',
  `price` decimal(10,2) DEFAULT NULL COMMENT '购买总价',
  `operuserid` varchar(50) DEFAULT NULL,
  `operusername` varchar(50) DEFAULT NULL,
  `busidate` date DEFAULT NULL COMMENT '业务日期',
  `rdate` datetime DEFAULT NULL COMMENT '记录时间,扩展使用',
  `loc` varchar(50) DEFAULT NULL COMMENT '区域、出库',
  `compid` varchar(50) DEFAULT NULL COMMENT '所属公司、出库',
  `warehouse` varchar(50) DEFAULT NULL COMMENT '仓库、出库',
  `belongcompid` varchar(50) DEFAULT NULL COMMENT '所属公司、进库',
  `usedcompid` varchar(50) DEFAULT NULL COMMENT '使用公司、进库',
  `usedpartid` varchar(50) DEFAULT NULL COMMENT '使用部门、进库',
  `useduserid` varchar(50) DEFAULT NULL COMMENT '使用人、进库',
  `inloc` varchar(50) DEFAULT NULL COMMENT '区域、进库',
  `inwarehouse` varchar(50) DEFAULT NULL COMMENT '仓库、进库',
  `label1` varchar(100) DEFAULT NULL,
  `label2` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_inout`
--

LOCK TABLES `res_inout` WRITE;
/*!40000 ALTER TABLE `res_inout` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_inout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_inout_item`
--

DROP TABLE IF EXISTS `res_inout_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_inout_item` (
  `id` varchar(50) DEFAULT NULL,
  `batchno` varchar(50) DEFAULT NULL COMMENT '批次号',
  `crkstatus` varchar(50) DEFAULT NULL COMMENT '出入库状态',
  `zc_cnt` decimal(10,0) DEFAULT NULL COMMENT '数量',
  `resid` varchar(50) DEFAULT NULL COMMENT '出库记录物品ID',
  `uuid` varchar(50) DEFAULT NULL COMMENT '单据号',
  `class_id` varchar(50) DEFAULT NULL COMMENT '物品ID',
  `category` varchar(50) DEFAULT NULL COMMENT '物品档案',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `buy_price` decimal(10,2) DEFAULT NULL COMMENT '购买总价',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '购买单价',
  `loc` varchar(50) DEFAULT NULL COMMENT '资产区域',
  `warehouse` varchar(50) DEFAULT NULL COMMENT '存放仓库',
  `belong_company_id` varchar(50) DEFAULT NULL COMMENT '所属公司',
  `belong_part_id` varchar(50) DEFAULT NULL COMMENT '所属部门',
  `used_company_id` varchar(50) DEFAULT NULL COMMENT '使用公司Id',
  `part_id` varchar(50) DEFAULT NULL COMMENT '使用部门Id',
  `used_userid` varchar(50) DEFAULT NULL COMMENT '使用人Id',
  `mark` varchar(10) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_inout_item`
--

LOCK TABLES `res_inout_item` WRITE;
/*!40000 ALTER TABLE `res_inout_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_inout_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_inventory`
--

DROP TABLE IF EXISTS `res_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_inventory` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '盘点单名称',
  `batchid` varchar(50) DEFAULT NULL COMMENT '盘点单批次号',
  `syncstatus` varchar(2) DEFAULT NULL COMMENT '盘点结束，是否同步状态,1|0',
  `status` varchar(50) DEFAULT NULL COMMENT 'wait|start|finish|cancel',
  `resstartdate` date DEFAULT NULL COMMENT '资产采购时间',
  `resenddate` date DEFAULT NULL COMMENT '资产采购时间',
  `usedcomp` varchar(50) DEFAULT NULL COMMENT '使用公司',
  `usedcompname` varchar(50) DEFAULT NULL COMMENT '使用公司名称',
  `usedpart` varchar(500) DEFAULT NULL COMMENT '使用部门',
  `usedpartname` varchar(2000) DEFAULT NULL COMMENT '使用部门名称',
  `usedpartdata` varchar(1000) DEFAULT NULL COMMENT '使用部门数据',
  `belongcomp` varchar(50) DEFAULT NULL COMMENT '所属公司',
  `belongcompname` varchar(50) DEFAULT NULL COMMENT '所属公司名称',
  `rescat` varchar(500) DEFAULT NULL COMMENT '资产分类',
  `rescatname` varchar(500) DEFAULT NULL COMMENT '资产分类名称',
  `rescatdata` varchar(1000) DEFAULT NULL COMMENT '资产分类数据',
  `area` varchar(500) DEFAULT NULL COMMENT '资产区域',
  `areaname` varchar(500) DEFAULT NULL COMMENT '资产区域名称',
  `areadata` varchar(1000) DEFAULT NULL COMMENT '资产区域数据',
  `adminuserid` varchar(50) DEFAULT NULL COMMENT '盘点单负责人',
  `adminusername` varchar(50) DEFAULT NULL COMMENT '盘点单负责人',
  `starttime` date DEFAULT NULL COMMENT '盘点单开始时间',
  `finishtime` date DEFAULT NULL COMMENT '盘点单结束时间',
  `pduserlist` varchar(500) DEFAULT NULL COMMENT '盘点人',
  `pduserdata` varchar(1000) DEFAULT NULL COMMENT '盘点人数据',
  `manualinventory` varchar(2) DEFAULT NULL COMMENT '是否支持手工盘点,1|0',
  `allusersinventory` varchar(2) DEFAULT NULL COMMENT '是否全员盘点,1|0',
  `mark` varchar(500) DEFAULT NULL,
  `cnt` decimal(10,0) DEFAULT NULL COMMENT '资产盘点数量',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_inventory`
--

LOCK TABLES `res_inventory` WRITE;
/*!40000 ALTER TABLE `res_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_inventory_item`
--

DROP TABLE IF EXISTS `res_inventory_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_inventory_item` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `pdbatchid` varchar(50) DEFAULT NULL,
  `pdid` varchar(50) DEFAULT NULL,
  `pdstatus` varchar(50) DEFAULT NULL COMMENT '盘点状态,wait|finish',
  `pdsyncneed` varchar(50) DEFAULT NULL COMMENT '是否需要同步数据,1|0',
  `pdtime` date DEFAULT NULL,
  `pduserid` varchar(50) DEFAULT NULL,
  `pdusername` varchar(500) DEFAULT NULL,
  `pdmark` varchar(500) DEFAULT NULL,
  `pdflag` varchar(500) DEFAULT NULL COMMENT '盘点标记,source|new|delete',
  `resid` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL COMMENT '资产类目',
  `class_id` varchar(50) DEFAULT NULL COMMENT '大类',
  `type` varchar(50) DEFAULT NULL COMMENT '小类',
  `gj_dl` varchar(50) DEFAULT NULL COMMENT '国际大类',
  `gj_xl` varchar(50) DEFAULT NULL COMMENT '国际小类',
  `uuid` varchar(50) DEFAULT NULL COMMENT '资产编号',
  `name` text COMMENT '资产名称',
  `zcsource` varchar(50) DEFAULT NULL COMMENT '资产来源',
  `model` varchar(500) DEFAULT NULL COMMENT '型号',
  `sn` text COMMENT '序列号',
  `version` varchar(100) DEFAULT NULL COMMENT '版本',
  `res_desc` text COMMENT ' 资产描述',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `recycle` varchar(50) DEFAULT NULL COMMENT '生命周期状态',
  `prerecycle` varchar(50) DEFAULT NULL COMMENT '生命周期',
  `env` varchar(100) DEFAULT NULL COMMENT '运行环境',
  `risk` varchar(50) DEFAULT NULL COMMENT '风险等级',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下线时间',
  `online_time` datetime DEFAULT NULL COMMENT '上线时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `rwm` varchar(50) DEFAULT NULL COMMENT '二维码',
  `confdesc` varchar(509) DEFAULT NULL COMMENT '配置描述',
  `loc` varchar(50) DEFAULT NULL COMMENT '资产区域',
  `locshow` varchar(1) DEFAULT NULL COMMENT '资产区域是否显示',
  `locdtl` varchar(1000) DEFAULT NULL COMMENT '位置详情',
  `rack` varchar(50) DEFAULT NULL COMMENT '机柜号',
  `frame` varchar(50) DEFAULT NULL COMMENT '机架号',
  `belong_company_id` varchar(50) DEFAULT NULL COMMENT '归属公司',
  `belong_part_id` varchar(50) DEFAULT NULL COMMENT '归属部门',
  `used_company_id` varchar(50) DEFAULT NULL COMMENT '使用公司Id',
  `part_id` varchar(50) DEFAULT NULL COMMENT '使用部门Id',
  `used_userid` varchar(50) DEFAULT NULL COMMENT '使用人Id',
  `mgr_part_id` varchar(50) DEFAULT NULL COMMENT '管理部门Id',
  `maintain_userid` varchar(50) DEFAULT NULL COMMENT '维护人用户ID',
  `headuserid` varchar(50) DEFAULT NULL COMMENT '负责人ID',
  `buy_price` decimal(10,2) DEFAULT NULL COMMENT '采购单价',
  `net_worth` decimal(10,2) DEFAULT NULL COMMENT '当前价值',
  `zc_cnt` decimal(10,2) DEFAULT '1.00' COMMENT '资产数量',
  `actionstatus` varchar(50) DEFAULT NULL COMMENT '资产变更状态',
  `wb` varchar(50) DEFAULT NULL COMMENT '维保状态',
  `wb_auto` varchar(50) DEFAULT NULL COMMENT '是否自动计算维保:1,0',
  `wbout_date` datetime DEFAULT NULL COMMENT '脱保时间',
  `wbsupplier` varchar(50) DEFAULT NULL COMMENT '维保供应商',
  `wbct` varchar(100) DEFAULT NULL COMMENT '维保说明',
  `status` varchar(100) DEFAULT NULL COMMENT '状态,未使用',
  `changestatus` varchar(50) DEFAULT NULL COMMENT '资产变更期间临时状态',
  `importlabel` varchar(50) DEFAULT NULL COMMENT '批量导入标记',
  `img` varchar(50) DEFAULT NULL COMMENT '图片Id',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `changestate` varchar(50) DEFAULT NULL COMMENT '复核状态:updated,reviewed',
  `review_userid` varchar(50) DEFAULT NULL COMMENT '复核人Id',
  `review_date` datetime DEFAULT NULL COMMENT '最后复核时间',
  `fs1` text COMMENT '资产标签1',
  `fs2` text COMMENT '资产标签2',
  `fs3` text,
  `fs4` text,
  `fs5` text,
  `fs6` text,
  `fs7` text,
  `fs8` text,
  `fs9` text,
  `fs10` text,
  `fs11` text,
  `fs12` text,
  `fs13` text,
  `fs14` text,
  `fs15` text,
  `fs16` text,
  `fs17` text,
  `fs18` text,
  `fs19` text,
  `fs20` text,
  `fi1` decimal(32,0) DEFAULT NULL,
  `fi2` decimal(32,0) DEFAULT NULL,
  `fi3` decimal(32,0) DEFAULT NULL,
  `fi4` decimal(32,0) DEFAULT NULL,
  `fi5` decimal(32,0) DEFAULT NULL,
  `fi6` decimal(32,0) DEFAULT NULL,
  `fi7` decimal(32,0) DEFAULT NULL,
  `fi8` decimal(32,0) DEFAULT NULL,
  `fi9` decimal(32,0) DEFAULT NULL,
  `fi10` decimal(32,0) DEFAULT NULL,
  `fi11` decimal(32,0) DEFAULT NULL,
  `fi12` decimal(32,0) DEFAULT NULL,
  `fi13` decimal(32,0) DEFAULT NULL,
  `fi14` decimal(32,0) DEFAULT NULL,
  `fi15` decimal(32,0) DEFAULT NULL,
  `fi16` decimal(32,0) DEFAULT NULL,
  `fi17` decimal(32,0) DEFAULT NULL,
  `fi18` decimal(32,0) DEFAULT NULL,
  `fi19` decimal(32,0) DEFAULT NULL,
  `fi20` decimal(32,0) DEFAULT NULL,
  `fd1` datetime DEFAULT NULL,
  `fd2` datetime DEFAULT NULL,
  `fd3` datetime DEFAULT NULL,
  `lastinventorytime` decimal(10,0) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `warehouse` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_inventory_item`
--

LOCK TABLES `res_inventory_item` WRITE;
/*!40000 ALTER TABLE `res_inventory_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_inventory_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_inventory_item_s`
--

DROP TABLE IF EXISTS `res_inventory_item_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_inventory_item_s` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `pdbatchid` varchar(50) DEFAULT NULL,
  `pdid` varchar(50) DEFAULT NULL,
  `resid` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL COMMENT '资产类目',
  `class_id` varchar(50) DEFAULT NULL COMMENT '大类',
  `type` varchar(50) DEFAULT NULL COMMENT '小类',
  `gj_dl` varchar(50) DEFAULT NULL COMMENT '国际大类',
  `gj_xl` varchar(50) DEFAULT NULL COMMENT '国际小类',
  `uuid` varchar(50) DEFAULT NULL COMMENT '资产编号',
  `name` text COMMENT '资产名称',
  `zcsource` varchar(50) DEFAULT NULL COMMENT '资产来源',
  `model` varchar(500) DEFAULT NULL COMMENT '型号',
  `sn` text COMMENT '序列号',
  `version` varchar(100) DEFAULT NULL COMMENT '版本',
  `res_desc` text COMMENT ' 资产描述',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `recycle` varchar(50) DEFAULT NULL COMMENT '生命周期状态',
  `prerecycle` varchar(50) DEFAULT NULL COMMENT '生命周期',
  `env` varchar(100) DEFAULT NULL COMMENT '运行环境',
  `risk` varchar(50) DEFAULT NULL COMMENT '风险等级',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下线时间',
  `online_time` datetime DEFAULT NULL COMMENT '上线时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `rwm` varchar(50) DEFAULT NULL COMMENT '二维码',
  `confdesc` varchar(509) DEFAULT NULL COMMENT '配置描述',
  `loc` varchar(50) DEFAULT NULL COMMENT '资产区域',
  `locshow` varchar(1) DEFAULT NULL COMMENT '资产区域是否显示',
  `locdtl` varchar(1000) DEFAULT NULL COMMENT '位置详情',
  `rack` varchar(50) DEFAULT NULL COMMENT '机柜号',
  `frame` varchar(50) DEFAULT NULL COMMENT '机架号',
  `belong_company_id` varchar(50) DEFAULT NULL COMMENT '归属公司',
  `belong_part_id` varchar(50) DEFAULT NULL COMMENT '归属部门',
  `used_company_id` varchar(50) DEFAULT NULL COMMENT '使用公司Id',
  `part_id` varchar(50) DEFAULT NULL COMMENT '使用部门Id',
  `used_userid` varchar(50) DEFAULT NULL COMMENT '使用人Id',
  `mgr_part_id` varchar(50) DEFAULT NULL COMMENT '管理部门Id',
  `maintain_userid` varchar(50) DEFAULT NULL COMMENT '维护人用户ID',
  `headuserid` varchar(50) DEFAULT NULL COMMENT '负责人ID',
  `buy_price` decimal(10,2) DEFAULT NULL COMMENT '采购单价',
  `net_worth` decimal(10,2) DEFAULT NULL COMMENT '当前价值',
  `zc_cnt` decimal(10,2) DEFAULT '1.00' COMMENT '资产数量',
  `actionstatus` varchar(50) DEFAULT NULL COMMENT '资产变更状态',
  `wb` varchar(50) DEFAULT NULL COMMENT '维保状态',
  `wb_auto` varchar(50) DEFAULT NULL COMMENT '是否自动计算维保:1,0',
  `wbout_date` datetime DEFAULT NULL COMMENT '脱保时间',
  `wbsupplier` varchar(50) DEFAULT NULL COMMENT '维保供应商',
  `wbct` varchar(100) DEFAULT NULL COMMENT '维保说明',
  `status` varchar(100) DEFAULT NULL COMMENT '状态,未使用',
  `changestatus` varchar(50) DEFAULT NULL COMMENT '资产变更期间临时状态',
  `importlabel` varchar(50) DEFAULT NULL COMMENT '批量导入标记',
  `img` varchar(50) DEFAULT NULL COMMENT '图片Id',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `changestate` varchar(50) DEFAULT NULL COMMENT '复核状态:updated,reviewed',
  `review_userid` varchar(50) DEFAULT NULL COMMENT '复核人Id',
  `review_date` datetime DEFAULT NULL COMMENT '最后复核时间',
  `fs1` text COMMENT '资产标签1',
  `fs2` text COMMENT '资产标签2',
  `fs3` text,
  `fs4` text,
  `fs5` text,
  `fs6` text,
  `fs7` text,
  `fs8` text,
  `fs9` text,
  `fs10` text,
  `fs11` text,
  `fs12` text,
  `fs13` text,
  `fs14` text,
  `fs15` text,
  `fs16` text,
  `fs17` text,
  `fs18` text,
  `fs19` text,
  `fs20` text,
  `fi1` decimal(32,0) DEFAULT NULL,
  `fi2` decimal(32,0) DEFAULT NULL,
  `fi3` decimal(32,0) DEFAULT NULL,
  `fi4` decimal(32,0) DEFAULT NULL,
  `fi5` decimal(32,0) DEFAULT NULL,
  `fi6` decimal(32,0) DEFAULT NULL,
  `fi7` decimal(32,0) DEFAULT NULL,
  `fi8` decimal(32,0) DEFAULT NULL,
  `fi9` decimal(32,0) DEFAULT NULL,
  `fi10` decimal(32,0) DEFAULT NULL,
  `fi11` decimal(32,0) DEFAULT NULL,
  `fi12` decimal(32,0) DEFAULT NULL,
  `fi13` decimal(32,0) DEFAULT NULL,
  `fi14` decimal(32,0) DEFAULT NULL,
  `fi15` decimal(32,0) DEFAULT NULL,
  `fi16` decimal(32,0) DEFAULT NULL,
  `fi17` decimal(32,0) DEFAULT NULL,
  `fi18` decimal(32,0) DEFAULT NULL,
  `fi19` decimal(32,0) DEFAULT NULL,
  `fi20` decimal(32,0) DEFAULT NULL,
  `fd1` datetime DEFAULT NULL,
  `fd2` datetime DEFAULT NULL,
  `fd3` datetime DEFAULT NULL,
  `lastinventorytime` decimal(10,0) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `warehouse` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_inventory_item_s`
--

LOCK TABLES `res_inventory_item_s` WRITE;
/*!40000 ALTER TABLE `res_inventory_item_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_inventory_item_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_inventory_user`
--

DROP TABLE IF EXISTS `res_inventory_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_inventory_user` (
  `id` varchar(50) NOT NULL,
  `pdid` varchar(50) DEFAULT NULL,
  `userid` varchar(50) DEFAULT NULL COMMENT '盘点单分配人ID',
  `username` varchar(100) DEFAULT NULL COMMENT '盘点单分配人',
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_inventory_user`
--

LOCK TABLES `res_inventory_user` WRITE;
/*!40000 ALTER TABLE `res_inventory_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_inventory_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_label_tpl`
--

DROP TABLE IF EXISTS `res_label_tpl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_label_tpl` (
  `id` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT 'rwm|txm',
  `picloc` varchar(50) DEFAULT NULL COMMENT '图片位置，up｜down',
  `ctlcols` varchar(100) DEFAULT NULL COMMENT '字段顺序',
  `ifdef` varchar(50) DEFAULT NULL COMMENT '是否默认选中',
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ctlcolsstr` varchar(1000) DEFAULT NULL,
  `ctlvalue` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_label_tpl`
--

LOCK TABLES `res_label_tpl` WRITE;
/*!40000 ALTER TABLE `res_label_tpl` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_label_tpl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_repair`
--

DROP TABLE IF EXISTS `res_repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_repair` (
  `id` varchar(50) NOT NULL,
  `freason` varchar(100) DEFAULT NULL COMMENT '原因',
  `fmark` varchar(500) DEFAULT NULL COMMENT '备注',
  `foper_user` varchar(50) DEFAULT NULL COMMENT '上报人',
  `fopertime` datetime DEFAULT NULL COMMENT '上报时间',
  `fuuid` varchar(50) DEFAULT NULL COMMENT '单据号',
  `fprocessuser` varchar(500) DEFAULT NULL COMMENT '维护人',
  `fprocesstime` varchar(500) DEFAULT NULL COMMENT '维护时间',
  `fstatus` varchar(50) DEFAULT NULL COMMENT 'wait|finish',
  `flevel` varchar(50) DEFAULT NULL COMMENT '报修等级',
  `frepairtype` varchar(50) DEFAULT NULL COMMENT '报修类型',
  `fmoney` varchar(50) DEFAULT NULL COMMENT '费用',
  `dr` varchar(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_repair`
--

LOCK TABLES `res_repair` WRITE;
/*!40000 ALTER TABLE `res_repair` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_repair_file`
--

DROP TABLE IF EXISTS `res_repair_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_repair_file` (
  `id` varchar(50) NOT NULL,
  `fileid` varchar(50) DEFAULT NULL,
  `repiarid` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_repair_file`
--

LOCK TABLES `res_repair_file` WRITE;
/*!40000 ALTER TABLE `res_repair_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_repair_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_repair_item`
--

DROP TABLE IF EXISTS `res_repair_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_repair_item` (
  `id` varchar(50) NOT NULL,
  `residprerecycle` varchar(50) DEFAULT NULL COMMENT '变更前资产状态',
  `resid` varchar(50) DEFAULT NULL,
  `repairid` varchar(50) DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `busuuid` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_repair_item`
--

LOCK TABLES `res_repair_item` WRITE;
/*!40000 ALTER TABLE `res_repair_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_repair_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_residual`
--

DROP TABLE IF EXISTS `res_residual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_residual` (
  `id` varchar(50) NOT NULL,
  `uuid` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `residualvaluerate` decimal(10,2) DEFAULT NULL,
  `depreciationrate` decimal(10,2) DEFAULT NULL,
  `valuestr` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `checkstatus` varchar(50) DEFAULT NULL,
  `strategyid` varchar(50) DEFAULT NULL,
  `processuserid` varchar(50) DEFAULT NULL,
  `processusername` varchar(100) DEFAULT NULL,
  `processdate` datetime DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `cnt` decimal(10,0) DEFAULT NULL,
  `busidate` date DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_residual`
--

LOCK TABLES `res_residual` WRITE;
/*!40000 ALTER TABLE `res_residual` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_residual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_residual_item`
--

DROP TABLE IF EXISTS `res_residual_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_residual_item` (
  `id` varchar(50) NOT NULL,
  `uuid` varchar(50) DEFAULT NULL,
  `resid` varchar(50) DEFAULT NULL,
  `checkstatus` varchar(50) DEFAULT NULL,
  `curresidualvalue` decimal(10,2) DEFAULT NULL,
  `buyprice` decimal(10,2) DEFAULT NULL,
  `bnetworth` decimal(10,2) DEFAULT NULL,
  `anetworth` decimal(10,2) DEFAULT NULL,
  `lossprice` decimal(10,2) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_residual_item`
--

LOCK TABLES `res_residual_item` WRITE;
/*!40000 ALTER TABLE `res_residual_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_residual_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_residual_strategy`
--

DROP TABLE IF EXISTS `res_residual_strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_residual_strategy` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `strategydesc` varchar(200) DEFAULT NULL COMMENT '策略描述',
  `code` varchar(50) DEFAULT NULL COMMENT '未使用',
  `residualvaluerate` decimal(10,2) DEFAULT NULL COMMENT '残值率，-1则使用资产设置的残值',
  `depreciationrate` decimal(10,2) DEFAULT NULL COMMENT '折旧率',
  `valuestr` varchar(100) DEFAULT NULL,
  `mark` varchar(500) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_residual_strategy`
--

LOCK TABLES `res_residual_strategy` WRITE;
/*!40000 ALTER TABLE `res_residual_strategy` DISABLE KEYS */;
INSERT INTO `res_residual_strategy` VALUES ('1','平均折旧法','1','残值率:3%,折旧率:25%','1',0.03,0.25,'','','0',NULL,NULL,NULL,NULL),('2','平均折旧法','1','残值率:0%,折旧率:10%','1',0.00,0.10,NULL,NULL,'0',NULL,NULL,NULL,NULL),('3','平均折旧法','1','残值率:-1,折旧率12.5%','1',-1.00,0.13,NULL,NULL,'0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `res_residual_strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_scrape`
--

DROP TABLE IF EXISTS `res_scrape`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_scrape` (
  `id` varchar(50) NOT NULL,
  `uuid` varchar(50) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `processuserid` varchar(50) DEFAULT NULL,
  `processusername` varchar(500) DEFAULT NULL,
  `processdate` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `ct` varchar(500) DEFAULT NULL,
  `busidate` date DEFAULT NULL,
  `mark` varchar(500) DEFAULT NULL,
  `cnt` decimal(10,0) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_scrape`
--

LOCK TABLES `res_scrape` WRITE;
/*!40000 ALTER TABLE `res_scrape` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_scrape` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_scrape_item`
--

DROP TABLE IF EXISTS `res_scrape_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_scrape_item` (
  `id` varchar(50) NOT NULL,
  `uuid` varchar(50) DEFAULT NULL,
  `resid` varchar(50) DEFAULT NULL,
  `prestatus` varchar(50) DEFAULT NULL,
  `mark` varchar(500) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_scrape_item`
--

LOCK TABLES `res_scrape_item` WRITE;
/*!40000 ALTER TABLE `res_scrape_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `res_scrape_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_api`
--

DROP TABLE IF EXISTS `sys_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_api` (
  `id` varchar(50) NOT NULL,
  `ct` text,
  `ctacl` varchar(50) DEFAULT NULL,
  `apitype` varchar(100) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `mark` text,
  `info` text,
  `type` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_api`
--

LOCK TABLES `sys_api` WRITE;
/*!40000 ALTER TABLE `sys_api` DISABLE KEYS */;
INSERT INTO `sys_api` VALUES ('007de4f9-4c75-4f1b-b34b-145d638943fa','/api/sysQudQux/selectPage.do','allow','url','2020-06-26 00:49:19',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('01a314c5-045f-4fdc-8cac-666d5c115a7d','/api/smallprogram/login.do','allow','url','2020-06-26 00:49:18',NULL,'小程序用户登录','api',NULL,NULL,NULL,NULL,'0'),('02cfcb83-8ae4-4e58-b85a-92edff5a2dd0','/api/sysModulesItem/deleteById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('0336c1db-6cb3-4311-b9ba-830abb4b881a','/api/sysUserGroup/selectPage.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('039cfc6d-0657-41f3-b35a-44618b57f68e','/api/user/checkLogin.do','allow','url','2020-06-26 00:49:18',NULL,'检测登录','api',NULL,NULL,NULL,NULL,'0'),('03e4a2e3-6ead-483e-861a-df411e5c6f45','/api/sysApi/system/refreshCache.do','deny','url','2020-06-26 00:49:17',NULL,'删除CacheKey','api',NULL,NULL,NULL,NULL,'0'),('047a5eb1-579a-4f8b-ac16-e6f6b12ea72e','/api/sysUserInfo/changeUserPwd.do','user','url','2020-06-26 00:49:21',NULL,'强制修改密码','api',NULL,NULL,NULL,NULL,'0'),('05503f1b-9d07-45a8-b038-567ec0811bc1','/api/sysFileConf/updateById.do','user','url','2020-06-26 00:49:20',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('067c1c38-aec7-46a8-a671-33c1d2e1bb8e','/api/sysUserReceivingaddr/insert.do','deny','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('06d31638-87e3-41af-88ce-b3834c9c6eda','/api/sysRoleInfo/roleQueryFormatKV.do','deny','url','2020-06-26 00:49:20',NULL,'查询权限','api',NULL,NULL,NULL,NULL,'0'),('0915956b-72ca-49ea-96f7-dc08f5609f10','/api/sysQudChengs/insertOrUpdate.do','deny','url','2020-06-26 00:49:23',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('0a734c69-57c7-41d6-bda9-c2ef1d5d1e17','/api/sysUserGroupItem/deleteById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('0b632dce-deca-4792-98a7-678582853b9d','/api/sysUserHomeaddr/deleteById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('0bb193c6-6b1a-4e0e-96fb-db84d3e2734a','/api/sysUserHomeaddr/selectList.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('0c2b7a37-027b-4e2c-bb2f-a407521ca6d6','/api/sysFileConf/selectPage.do','user','url','2020-06-26 00:49:20',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('0eb91a19-c40d-4529-9609-3fc90e68092f','/api/sysModulesItem/insertOrUpdate.do','deny','url','2020-06-26 00:49:18',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('0f72c3c4-1d7e-416e-b2d0-d75a50ec106c','/api/cmdb/resAttrs/deleteById.do','user','url','2020-06-26 00:49:23',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('112b48bf-2f5d-448f-9012-837d2edbdca3','/api/cmdb/resActionItem/insertOrUpdate.do','user','url','2020-06-26 00:49:23',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('11b94475-18e1-49c3-b5c7-4d95a6f81437','/api/sysRegion/insertOrUpdate.do','deny','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('13d0504f-2c31-43dc-8af4-4d56d9de2eca','/api/sysUserHomeaddr/selectById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('13fb67da-c86c-4ce5-9df2-c034a355d5e4','/api/sysSession/selectPage.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('1479a13b-ab7d-4b77-b14b-035e28f24944','/api/sysQudChengs/selectPage.do','allow','url','2020-06-26 00:49:18',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('14bc0221-28c2-40be-9dd6-1778889dffc4','/api/sysDict/insert.do','deny','url','2020-06-26 00:49:19',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('15adf9e4-a919-492b-bbae-8eaebe8a6c4a','/api/sysLogAccess/insert.do','deny','url','2020-06-26 00:49:21',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('15cafd9b-19fe-47b4-8212-82f95c2a8d27','/api/sysSession/selectList.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('178a1c91-76b7-4941-9bbb-5605b7a8beb8','/api/sysQudQux/deleteById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('17993782-08b2-49c3-a20c-74b922f431d5','/api/cmdb/resActionExt/selectList.do','user','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('18565da2-44af-4e46-9738-92819ec946c7','/api/schedule/disablejob.do','deny','url','2020-06-26 00:49:17',NULL,'不激活任务','api',NULL,NULL,NULL,NULL,'0'),('19f42983-aa3d-4eee-aecd-c36e48ebcf45','/api/sysModulesItem/updateById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('1ad0b263-e5c1-4bb0-9adc-3d24774fb0b8','/api/sysMenusNode/insertOrUpdate.do','deny','url','2020-06-26 00:49:17',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('1af9bcb6-7850-4365-9d4e-842989b2cf35','/api/menu/treeNodeRoleMap.do','deny','url','2020-06-26 00:49:18',NULL,'查询菜单权限','api',NULL,NULL,NULL,NULL,'0'),('1d7178a1-087a-42da-a029-f8e4ebedc1c9','/api/sysUserInfo/my/update.do','user','url','2020-06-26 00:49:22',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('1dce49bb-ff3f-4425-a2e5-3046db4ed89b','/api/cmdb/resActionItem/selectList.do','user','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('1f3d1dce-6688-44ee-956c-9be1d1d08633','/api/sysParams/insert.do','deny','url','2020-06-26 00:49:18',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('20dfbce8-7f38-4d64-8550-ec02cc53ba6a','/api/sysMenus/selectById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('20e3eb8e-3e23-44c5-8403-20b8641e3558','/api/base/sysFeedback/selectPage.do','user','url','2020-06-26 00:49:20',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('21b07e5f-b377-414c-9bab-41f0c1fe5ea4','/api/sysLogAccess/selectById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('21c9b6fe-bd19-48fe-ad98-a0f4a4c2d3a7','/api/sysQudShengf/insertOrUpdate.do','deny','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('233e387c-9898-43e1-b67c-401803eb94eb','/api/sysApi/system/refreshCacheForExpire.do','user','url','2020-06-26 00:49:17',NULL,'','api',NULL,NULL,NULL,NULL,'0'),('234d8876-edff-4556-967a-1656675b252f','/api/sysDbbackupRec/updateById.do','user','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('257888c4-3690-4566-a785-88639dc6f503','/api/sysDictItem/insertOrUpdate.do','deny','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('26ff5cee-73ee-4fcd-9684-6803564c524a','/api/sysJob/insert.do','deny','url','2020-06-26 00:49:21',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('2b04eff3-e414-4ed6-ac33-1f69b1e7ffc1','/api/sysDict/selectList.do','deny','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('2bae38b7-6996-4957-a399-85a56337950d','/api/sysQudChengs/insert.do','deny','url','2020-06-26 00:49:23',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('2c15e21a-bc25-4362-8497-ec24abd00752','/api/sysUserHomeaddr/insert.do','deny','url','2020-06-26 00:49:21',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('2ca543c7-3dac-46fe-81f5-75338d19c665','/api/sysDbbackupRec/selectList.do','user','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('2d027487-1fe5-40c2-bb60-c0e9ee1f6340','/api/sysLogAccess/updateById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('2f16054c-5a5c-4b99-b1d4-9f9d9999d929','/api/sysModulesItem/selectById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('2f2c17f2-00e9-429e-b10f-5e428d8343e3','/api/file/filedown.do','allow','url','2020-06-26 00:49:18',NULL,'下载','api',NULL,NULL,NULL,NULL,'0'),('2fd3184d-9c5d-41a6-9113-0642700f5c76','/api/sysRegion/selectList.do','allow','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('2fe3cadb-6721-4797-b83a-35654daf4172','/api/schedule/runonce.do','deny','url','2020-06-26 00:49:17',NULL,'运行一次任务','api',NULL,NULL,NULL,NULL,'0'),('3056caf0-207c-4ac4-8e59-4b7f980ea44b','/api/sysUserGroupItem/updateById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('32cb0b99-540d-4b90-b68c-515e1c49d37b','/api/sysParams/updateById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('34c6d289-f3f8-42fa-a7dd-6eb4c0e7b1b9','/api/sysDbbackupRec/deleteById.do','user','url','2020-06-26 00:49:19',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('3541cf29-7d85-40cc-bf98-6a9118511e8d','/api/sysCardAddr/deleteById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('35dacf83-f57a-4891-a073-f279c98dc3a8','/api/sysFileConf/selectList.do','user','url','2020-06-26 00:49:20',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('35deed9e-788f-42a0-b09d-db62bfbe15c3','/api/sysModulesItem/selectList.do','deny','url','2020-06-26 00:49:18',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('3668b31d-7077-40f1-abe0-feba7dc6a36d','/api/sysUserInfo/selectList.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('3768c0cd-d77c-4ec9-ae22-7a3dda18c61f','/api/base/sysFeedback/updateById.do','allow','url','2020-06-26 00:49:20',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('37879080-f67d-4638-8edc-e855b655eba7','/api/schedule/enablejob.do','deny','url','2020-06-26 00:49:17',NULL,'激活任务','api',NULL,NULL,NULL,NULL,'0'),('37d66837-09c5-4ba7-bc23-abda3f5b6a3a','/api/sysMenusNode/insert.do','deny','url','2020-06-26 00:49:17',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('39a1ac0c-3367-4b47-8aa5-47dd07fa66b5','/api/sysUserHomeaddr/updateById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('3a1b4c87-0b81-4153-b0c8-f5acd22d277f','/api/sysRoleInfo/insert.do','deny','url','2020-06-26 00:49:19',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('3b649a4b-41f6-4331-a704-ea9447087b8e','/api/sysDict/insertOrUpdate.do','deny','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('3c1086b6-b236-43c3-ba34-ae8d0d354a6f','/api/module/updateModuleItemMap.do','deny','url','2020-06-26 00:49:18',NULL,'更新模块','api',NULL,NULL,NULL,NULL,'0'),('3d2fc9e5-9687-4fb8-9b35-facfd1999289','/api/sysApi/system/clearCache.do','user','url','2020-06-26 00:49:17',NULL,'删除Cache','api',NULL,NULL,NULL,NULL,'0'),('3d538a4e-c643-42e0-aa14-ac45551e585b','/api/sysUserInfo/my/modifypwd.do','user','url','2020-06-26 00:49:22',NULL,'修改密码','api',NULL,NULL,NULL,NULL,'0'),('3ddcd9d3-4ea4-4717-bbf7-c6356690ed71','/api/sysDbbackupRec/selectPage.do','user','url','2020-06-26 00:49:19',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('3e399415-e810-40d2-ad0f-1bddd78d729c','/api/cmdb/resAttrs/selectList.do','user','url','2020-06-26 00:49:23',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('3f4bda83-f127-4198-9722-a2d531431154','/api/sysQudShengf/deleteById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('40d015e2-eee5-4f39-b1bf-30d1e96e1475','/api/sysJob/selectList.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('410aee75-acb4-4359-84f5-42c4925a111e','/api/sysUserInfo/updateById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('416586ff-6c22-4fe5-9157-930ec9296b9f','/api/sysQudQux/updateById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('43643dbd-8ad4-4a9e-a359-c83aef6f13e5','/api/sysQudChengs/selectList.do','allow','url','2020-06-26 00:49:18',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('439e2c63-35c7-4ea8-82d2-dfde366efe0c','/api/sysDict/ext/selectItemListByDictId.do','user','url','2020-06-26 00:49:20',NULL,'','api',NULL,NULL,NULL,NULL,'0'),('43ce138a-73ce-4cdc-b50a-471eefc6aa6a','/api/sysFiles/selectList.do','user','url','2020-06-26 00:49:21',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('443fafb3-f186-4473-a76f-12d5c83a115d','/api/sysUserGroupItem/insertOrUpdate.do','deny','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('45aa0022-b7a1-4f6a-8e18-fc116e4ebf78','/api/cmdb/resActionExt/selectById.do','user','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('45b78798-c612-4404-b6eb-ac441a9f431a','/api/base/sysFeedback/insertOrUpdate.do','allow','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('45fec4ac-0f57-463d-a97e-d0263375dfb7','/api/sysFileConf/insertOrUpdate.do','user','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('461fcf05-4dca-486d-afd6-fd64a113702c','/api/schedule/resumejob.do','deny','url','2020-06-26 00:49:17',NULL,'挂起任务','api',NULL,NULL,NULL,NULL,'0'),('49180abd-557d-4c8d-8cca-9b365929ebbf','/api/sysCardAddr/insertOrUpdate.do','deny','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('49e5423e-fb85-4d6f-b99a-ee326dfd6682','/api/sysUserReceivingaddr/insertOrUpdate.do','deny','url','2020-06-26 00:49:22',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('4d0efcf4-21e9-4d39-a807-423b4132fd61','/api/cmdb/resActionItem/insert.do','user','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('4ea62f6b-93fb-47b3-9f74-598c504e0450','/api/sysApi/system/queryCacheKeys.do','deny','url','2020-06-26 00:49:17',NULL,'查询CacheName','api',NULL,NULL,NULL,NULL,'0'),('4ebe74c2-874e-456f-b65f-892e57ac9acd','/api/sysLogAccess/insertOrUpdate.do','deny','url','2020-06-26 00:49:17',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('4ee1f41d-fd89-4958-aad1-fa60b82a7894','/api/sysQudChengs/updateById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('4f613a88-d33b-4eef-a17a-f70e33cc6928','/api/sysUserGroupItem/insert.do','deny','url','2020-06-26 00:49:20',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('50edbd2f-b829-4b13-8c22-0cf4a0ddcbc2','/api/sysQudQux/selectById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('51fa5fbd-f7fa-4ccb-9f82-5da500ea8f37','/api/sysDictItem/selectList.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('52c21055-fa1c-4e8e-bfd4-712779a09316','/api/cmdb/resActionItem/deleteById.do','user','url','2020-06-26 00:49:22',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('52f7ca4b-54b3-4bc8-9717-52404c611dc0','/api/sysModulesItem/deleteById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('5319d554-a525-40d0-8d51-257156567c88','/api/sysUserGroup/insert.do','deny','url','2020-06-26 00:49:20',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('5460d22d-0393-4480-8252-a8cc80403ff0','/api/sysUserReceivingaddr/selectPage.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('56b1f1a5-cab3-4f5c-991b-7b12fd591831','/api/sysUserGroup/selectList.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('57222037-6a07-421f-838d-22cdf443c776','/api/sysRoleInfo/selectById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('5869c11f-ab53-44b0-a623-ca0c0f50087c','/api/smallprogram/register.do','allow','url','2020-06-26 00:49:18',NULL,'小程序用户注册','api',NULL,NULL,NULL,NULL,'0'),('5a9ee83d-ed75-4dab-a8f2-1db1f3f236f3','/api/sysLogAccess/insertOrUpdate.do','deny','url','2020-06-26 00:49:21',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('5ec815ff-bd37-4f98-b85a-c353ce6fbda3','/api/file/fileupload.do','allow','url','2020-06-26 00:49:18',NULL,'上传','api',NULL,NULL,NULL,NULL,'0'),('5f7ef3de-1151-45d6-b47c-919b20a5483a','/api/sysRoleInfo/insertOrUpdate.do','deny','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('6022d4ed-ef4d-4e85-a2f9-4ebbffde808c','/api/sysModulesItem/selectList.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('60286f9f-2262-4953-be13-a09c6d197de6','/api/sysApi/selectList.do','deny','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('604538f1-a79a-4985-870f-e0b89b5edff0','/api/sysMenusNode/deleteById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('6116c8f6-f120-4728-90c4-c76f0e6bb4ce','/api/sysUserInfo/selectByOpenId.do','allow','url','2020-06-26 00:49:21',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('6264f5f0-207b-4849-b9d6-eac0a74f943b','/api/sysRoleInfo/updateById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('627857d7-e4fa-44d6-a843-3600c48f8736','/api/sysSession/selectById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('6361303b-e3a9-453e-88dd-75c7ef34fb80','/api/sysParams/insertOrUpdate.do','deny','url','2020-06-26 00:49:18',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('637b121b-1213-4f79-b869-243397ff88bc','/api/sysQudQux/insertOrUpdate.do','deny','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('63d4fd37-d873-472a-a660-6fdeb625fa90','/api/sysParams/insert.do','deny','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('64fda1ac-9bc3-4078-a9f8-b75cfed3e09c','/api/sysQudShengf/insert.do','deny','url','2020-06-26 00:49:19',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('6655ded4-1c31-4111-807f-ebc24db2ebce','/api/sysMenusNode/updateById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('690016cd-d8c3-476d-8247-ceed70e56222','/api/user/login.do','allow','url','2020-06-26 00:49:18',NULL,'登录','api',NULL,NULL,NULL,NULL,'0'),('69f645b3-97e2-468f-9f6a-20784406d7ae','/api/sysUserInfo/my/saveDefMenus.do','user','url','2020-06-26 00:49:22',NULL,'修改密码','api',NULL,NULL,NULL,NULL,'0'),('6b49a3c2-4485-485f-90a7-b06238c2ef72','/api/sysMenus/insert.do','deny','url','2020-06-26 00:49:21',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('6bc724d4-a909-4a8a-8490-db36b2086ff6','/api/sysModulesItem/selectPage.do','deny','url','2020-06-26 00:49:18',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('6d8e5c48-f595-4744-af43-998e02749518','/api/sysFiles/insert.do','user','url','2020-06-26 00:49:21',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('6f9d81ea-f077-4661-844e-f4c35f32c11b','/api/sysParams/selectById.do','user','url','2020-06-26 00:49:18',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('7021b04a-a88f-4f70-bb25-2c66b804704d','/api/sysFiles/updateById.do','user','url','2020-06-26 00:49:21',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('70c55b51-e731-4c63-99bf-df53f4244edd','/api/sysUserReceivingaddr/selectById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('716e00d8-9572-4637-946a-dc46fc7cc8d1','/api/cmdb/resActionItem/updateById.do','user','url','2020-06-26 00:49:23',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('71bcba76-71e8-4dcf-8fa5-fa93004070d9','/api/sysUserInfo/changeRoles.do','deny','url','2020-06-26 00:49:21',NULL,'修改用户权限','api',NULL,NULL,NULL,NULL,'0'),('72a4e827-6b02-4618-a50d-40332ab4f560','/api/sysDbbackupRec/selectById.do','user','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('72e337a7-61d7-4a1b-abd2-18adffc3f255','/api/sysParams/deleteById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('735819b1-9c9a-4f05-993c-37256ea26ab6','/api/sysMenus/updateById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('76b479a7-33cb-48fc-b6b9-cf3ddf7a4718','/api/sysUserInfo/my/select.do','user','url','2020-06-26 00:49:21',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('76bb973d-23a1-47ed-ab40-2403e3977288','/api/menu/updateNode.do','deny','url','2020-06-26 00:49:18',NULL,'更新节点','api',NULL,NULL,NULL,NULL,'0'),('77d7816e-a59e-43ac-8a68-150e1e279906','/api/menu/treeRoleChecked.do','deny','url','2020-06-26 00:49:18',NULL,'查询菜单权限检测','api',NULL,NULL,NULL,NULL,'0'),('7810ea85-14b4-4be3-ab10-a89d2eb6c594','/api/sysUserInfo/deleteByIds.do','deny','url','2020-06-26 00:49:21',NULL,'根据Ids删除','api',NULL,NULL,NULL,NULL,'0'),('7acd4cc3-2564-4240-9679-35193f0516ed','/api/cmdb/resActionExt/removeById.do','user','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('7aeddd2b-ac3f-4315-a99b-bda6741890d3','/api/sysMenusNode/queryMenuNodesForStageSetting.do','deny','url','2020-06-26 00:49:22',NULL,'查','api',NULL,NULL,NULL,NULL,'0'),('7b8ed88a-25e8-4f91-87f8-71b3a4094c36','/api/sysDbbackupRec/insert.do','user','url','2020-06-26 00:49:19',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('7bed4a2b-e160-4a35-a339-8eb0563a34ed','/api/cmdb/resActionItem/selectById.do','user','url','2020-06-26 00:49:22',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('7cab643c-6c7a-41cf-ac36-fc475ac10d7c','/api/sysLogAccess/selectList.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('7d7b9867-277f-41fc-9cd3-e2d53476e770','/api/schedule/pausejob.do','deny','url','2020-06-26 00:49:17',NULL,'暂停任务','api',NULL,NULL,NULL,NULL,'0'),('7da07fd8-5640-4838-ad9f-c156c0741bc4','/api/sysJob/selectPage.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('7e30d632-8ed3-41e5-81c7-c4e2b14a57b1','/api/schedule/removejob.do','deny','url','2020-06-26 00:49:17',NULL,'删除任务','api',NULL,NULL,NULL,NULL,'0'),('7f1792d2-3c37-4a53-b4ec-8b442dac62f3','/api/sysFiles/insertOrUpdate.do','user','url','2020-06-26 00:49:21',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('7fa2ffdc-8cc4-4875-b3f5-e442d5b6a510','/api/sysFiles/deleteById.do','user','url','2020-06-26 00:49:21',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('807a92f4-8359-4548-bfc8-1f8373596c7e','/api/cmdb/resAttrs/insert.do','user','url','2020-06-26 00:49:23',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('80bc590a-b7da-410c-a807-d8b61fe43ed5','/api/sysQudShengf/updateById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('826780ab-6f70-44dc-9668-47eadbf8d975','/api/sysUserGroup/selectById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('84aa4e88-3db4-498b-b618-982ed9ee193b','/api/sysDictItem/updateById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('8581bcab-aae7-4357-a08c-ca10000477c7','/api/sysQudChengs/selectById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('858b12b0-80ab-4b9e-a79f-3a6aa20b03e7','/api/sysMenusNode/selectList.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('8a220560-1f2f-4994-980f-d96d97369c08','/api/sysJob/deleteById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('8a8a77e7-f38a-4281-a78a-ffe8a8997aaf','/api/sysMenus/updateById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('8a973600-6ad5-4d6d-ae76-cd5a5639fe61','/api/sysUserGroupItem/selectById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('8aeda5a2-ace9-42df-b1e7-7cc38ca0f0de','/api/sysRegion/insert.do','deny','url','2020-06-26 00:49:19',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('8b27cff0-f958-4851-8d64-f5f5cb633e64','/api/sysUserInfo/selectById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('8bc06564-d742-4ab2-89fe-055590c3ff8b','/api/sysMenusNode/deleteById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('8bd5055a-2fdd-45c1-98bd-e36893ee98df','/api/sysDictItem/insert.do','deny','url','2020-06-26 00:49:20',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('8d25912a-9bf2-4f2f-a48e-b3b69cb215bc','/api/sysUserInfo/insert.do','deny','url','2020-06-26 00:49:21',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('8dccbcf5-c669-4824-99da-0042ce4a7d44','/api/sysDict/deleteById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('8f133aff-40cd-4552-9f8b-cf02f89bcc1f','/api/sysLogAccess/deleteById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('8ff56bbd-c636-43a2-9788-86f7936f3e2e','/api/sysRegion/deleteById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('909f9304-f503-4dd3-bc48-3d639bc65ddb','/api/sysUserReceivingaddr/selectList.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('917a622b-b038-4663-9444-af8152989c31','/api/sysParams/selectById.do','user','url','2020-06-26 00:49:22',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('95c3a7d1-2226-465a-abbd-63b1f10f9ea3','/api/sysDictItem/selectById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('960baf45-058f-4b5b-8195-5859b16fc40f','/api/sysUserGroupItem/selectPage.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('97278e5d-4bee-4d95-8604-a7f771a020b2','/api/sysApi/insertOrUpdate.do','deny','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('98080463-eb49-48ff-91ae-648ee5780705','/api/sysQudChengs/insertOrUpdate.do','deny','url','2020-06-26 00:49:18',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('9baf597f-6eb5-4d0a-ab1d-c35a2e97025b','/api/sysUserGroup/deleteById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('9c2a55db-a344-41cc-8d6c-98307ef0a000','/api/sysUserInfo/my/insertOrUpdate.do','user','url','2020-06-26 00:49:21',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('a056820f-140e-4c62-8900-2427b717c8f3','/api/file/imagedown.do','allow','url','2020-06-26 00:49:18',NULL,'下载','api',NULL,NULL,NULL,NULL,'0'),('a269887d-dc8a-461f-96d7-3eb727a92630','/api/sysDictItem/selectDictItemByDict.do','deny','url','2020-06-26 00:49:20',NULL,'根据Dict查询','api',NULL,NULL,NULL,NULL,'0'),('a2ac9d20-c7a4-464d-b206-e9ee9e1663de','/api/sysQudChengs/insert.do','deny','url','2020-06-26 00:49:18',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('a30a4cd5-fe0c-4942-9486-f77dcbaf1728','/api/sysQudChengs/updateById.do','deny','url','2020-06-26 00:49:23',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('a56499dc-405c-475e-966b-0c445e15d4b1','/api/module/queryModuleItemMap.do','deny','url','2020-06-26 00:49:18',NULL,'查询模块','api',NULL,NULL,NULL,NULL,'0'),('a5dd37ee-d2db-4d83-a897-a45f425d8b3b','/api/sysMenus/deleteById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('a6486e01-6121-498f-bcc6-f28a17d6bbc4','/api/menu/BatchAddNodeBtn.do','deny','url','2020-06-26 00:49:18',NULL,'批量增加节点','api',NULL,NULL,NULL,NULL,'0'),('a6843541-e72b-41a7-8d72-cc3dba2cdcc6','/api/sysDict/selectById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('a74aff6f-d907-41f4-bf0a-cacc00cb3676','/api/sysCardAddr/updateById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('aa7a81e3-db18-4744-ba74-3322e93b3e6c','/api/base/sysFeedback/selectList.do','user','url','2020-06-26 00:49:20',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('aab87f3e-b3af-4ac7-9eaa-fbdb01862614','/api/sysApi/system/removeCacheKey.do','deny','url','2020-06-26 00:49:17',NULL,'删除CacheKey','api',NULL,NULL,NULL,NULL,'0'),('ae0bed5c-0911-419f-b7d4-1a0bd440cfcd','/api/sysMenusNode/insertOrUpdate.do','deny','url','2020-06-26 00:49:22',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('af2f6c41-697a-4537-81bb-cb901157c4e5','/api/sysMenus/insertOrUpdate.do','deny','url','2020-06-26 00:49:22',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('af6e9d06-4ed3-48ec-bb8f-0e531429fc9e','/api/sysUserHomeaddr/selectPage.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('af79db3b-04dc-453a-8a51-5c6cdad86e68','/api/sysQudChengs/selectPage.do','allow','url','2020-06-26 00:49:23',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('b09a1e79-2b87-482d-a985-f2ba7090fe03','/api/smallprogram/checkLogin.do','allow','url','2020-06-26 00:49:18',NULL,'检测登录','api',NULL,NULL,NULL,NULL,'0'),('b0dfd09a-44cd-4d95-80cf-359801988b7d','/api/sysUserInfo/addUser.do','user','url','2020-06-26 00:49:21',NULL,'增加用户','api',NULL,NULL,NULL,NULL,'0'),('b23c68fb-e976-4393-a682-c0ad6ae1ce1b','/api/base/sysFeedback/deleteById.do','user','url','2020-06-26 00:49:20',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('b2eab570-dde0-4fd5-805b-c639b86a5200','/api/sysUserInfo/deleteById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('b36df432-5515-402b-8d47-42d07340252b','/api/sysMenus/selectById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('b3fc9ba6-f8f8-48a1-87bf-4e6b53bcaba9','/api/sysRoleInfo/selectList.do','user','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('b473d175-41a6-429b-b3dd-4cec71bf8fc3','/api/sysSession/updateById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('b6006950-459a-45ad-b51a-6ae2cc42ecef','/api/menu/addNode.do','deny','url','2020-06-26 00:49:18',NULL,'增加节点','api',NULL,NULL,NULL,NULL,'0'),('b617573f-3281-4dbe-bb6d-5e9070320f03','/api/sysRegion/updateById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('b6d49565-2110-45d0-8b73-ff43c1b4f30d','/api/sysQudChengs/deleteById.do','deny','url','2020-06-26 00:49:23',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('b78a0845-a86b-42ca-9810-3b67d1c3a99d','/api/sysApi/deleteById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('b8214ce5-f8c8-4f82-98ce-ddca6170e317','/api/sysUserInfo/my/listMyMenus.do','user','url','2020-06-26 00:49:22',NULL,'显示我的菜单','api',NULL,NULL,NULL,NULL,'0'),('b8b10d11-b966-4c94-a43e-a6f6b15b99ad','/api/monitor/queryServerInfo.do','allow','url','2020-06-26 00:49:18',NULL,'查询','api',NULL,NULL,NULL,NULL,'0'),('b9598d53-c9a0-494d-8864-0c621f95053d','/api/sysQudShengf/selectById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('b96806e3-de61-4d97-9bcf-d4de9ae12a6e','/api/sysMenus/selectList.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('bbb4572a-1f07-4689-90e8-63685899c969','/api/sysModulesItem/selectById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('bc193efc-2fbd-48d3-b61d-eeb9ba6e1bff','/api/sysMenus/insert.do','deny','url','2020-06-26 00:49:17',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('bcad64a3-3658-4384-b8e1-28ed9a382e9a','/api/sysModulesItem/selectPage.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('bcdedda9-bb35-4d56-bc43-7ffc98236b3c','/api/sysQudQux/selectList.do','allow','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('bd602019-6d8a-4320-89ef-b7e3d4f368b9','/api/sysFiles/selectPage.do','user','url','2020-06-26 00:49:21',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('bd8d258a-bb50-47d9-824e-3123251e760c','/api/sysUserInfo/my/deleteReceivingaddr.do','user','url','2020-06-26 00:49:22',NULL,'删除收货地址','api',NULL,NULL,NULL,NULL,'0'),('bda39c32-bf59-4236-a9dd-c862663d5880','/api/sysMenus/deleteById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('bebf8bf7-5604-44f8-b87e-c1a6dc75c992','/api/sysMenusNode/selectById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('bf2dc6c1-a4ee-401e-825b-53e7815fe3e9','/api/sysMenus/selectList.do','deny','url','2020-06-26 00:49:17',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('bf9c3d5b-fa08-4f10-81b2-316104918c37','/api/sysCardAddr/selectList.do','user','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('bfc073a5-4ab4-405f-a248-adefdbb0fe13','/api/sysApi/system/queryCacheKeyValue.do','user','url','2020-06-26 00:49:17',NULL,'查询CacheName','api',NULL,NULL,NULL,NULL,'0'),('c0b2a2de-5225-4871-a7bc-bde26cf9f28d','/api/sysQudChengs/selectList.do','allow','url','2020-06-26 00:49:23',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('c1efdfab-e7bb-4775-9e37-f8a8a9c5700f','/api/sysUserReceivingaddr/deleteById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('c2a67144-e3b1-4bd3-b318-bf8944556d27','/api/sysParams/deleteById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('c4e000f2-94c8-4eef-af42-18add72210da','/api/sysMenus/insertOrUpdate.do','deny','url','2020-06-26 00:49:17',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('c5267c68-8dcb-4cd8-b4db-32c45830a6b7','/api/sysFileConf/insert.do','user','url','2020-06-26 00:49:20',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('c5c95b93-f6dc-4ffe-a518-3e018646ed52','/api/sysQudShengf/selectPage.do','allow','url','2020-06-26 00:49:19',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('c64779f7-8bdc-4360-a687-f3798ada8dbb','/api/sysJob/insertOrUpdate.do','deny','url','2020-06-26 00:49:21',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('c788d89e-ae52-4b4a-bab0-feccb036cb7f','/api/sysRoleInfo/deleteById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('c7b9566c-98ea-4daf-8e1b-9cba79efaf2e','/api/sysSession/insert.do','deny','url','2020-06-26 00:49:20',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('c83cdfc8-ac42-453d-b4a3-c5a75c7f83c2','/api/sysDict/updateById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('c8d23ca6-d6c5-42ea-a237-71cbc73572c0','/api/sysApi/updateById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('c963bbb1-ce9b-4c43-87c1-f0769e586848','/api/user/logout.do','allow','url','2020-06-26 00:49:18',NULL,'退出','api',NULL,NULL,NULL,NULL,'0'),('c9800bb8-4692-4bd4-b519-6c507caa60a5','/api/sysModulesItem/insert.do','deny','url','2020-06-26 00:49:17',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('cb588353-8d13-47ba-87c0-02be030aeea4','/api/sysMenusNode/selectById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('cba30759-4ee2-4a97-971c-3dd709a97edc','/api/sysApi/system/queryCacheName.do','deny','url','2020-06-26 00:49:17',NULL,'查询CacheName','api',NULL,NULL,NULL,NULL,'0'),('cbeed85d-38d6-4b4b-bd2d-6c683dba453a','/api/sysUserReceivingaddr/updateById.do','deny','url','2020-06-26 00:49:22',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('cc3c39d1-0286-4a6a-94a9-aac7bed0a95f','/api/sysDict/selectPage.do','deny','url','2020-06-26 00:49:19',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('cc7d93ac-762b-4bf4-b16e-dd2bc8e105be','/api/sysParams/selectList.do','deny','url','2020-06-26 00:49:22',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('cdbf6e47-a058-4bee-a157-b8cdd776a0fd','/api/sysLogAccess/selectPage.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('cdd3fd42-993a-4304-86c9-fb9054e62a59','/api/sysQudShengf/selectList.do','allow','url','2020-06-26 00:49:19',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('ce454a64-231f-4aeb-84a6-b7927f3fafd7','/api/sysUserGroup/insertOrUpdate.do','deny','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('cfc3592e-16e5-40ee-bca1-a905dbb2ef31','/api/sysModulesItem/insertOrUpdate.do','deny','url','2020-06-26 00:49:22',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('d0ad1048-9be6-46ed-b40c-53caa3d40ca9','/api/base/sysFeedback/insert.do','user','url','2020-06-26 00:49:20',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('d287c2ae-df45-4123-a454-00f6eaa45c54','/api/sysUserHomeaddr/insertOrUpdate.do','deny','url','2020-06-26 00:49:21',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('d32766a2-0c6e-4606-a297-cfc9ef7abc28','/api/sysParams/insertOrUpdate.do','deny','url','2020-06-26 00:49:22',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('d3de7ed9-b22a-46c1-b1aa-9c3d842d4ec9','/api/sysUserInfo/my/queryReceivingaddr.do','user','url','2020-06-26 00:49:22',NULL,'查询用户收货地址','api',NULL,NULL,NULL,NULL,'0'),('d4c83d71-648f-41ed-bf3e-50813c452f6d','/api/sysModulesItem/insert.do','deny','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('d88a3699-1647-4b31-b81f-71b0ce72b8fd','/api/sysMenusNode/insert.do','deny','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('d9984d40-233d-4944-b2a7-0399f62003af','/api/sysFileConf/deleteById.do','user','url','2020-06-26 00:49:20',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('daadafb7-b64a-4791-ba53-7715924ab86e','/api/sysUserGroupItem/selectList.do','deny','url','2020-06-26 00:49:20',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('db1244d6-323d-46e5-af95-c195c3e5d6ca','/api/base/sysFeedback/selectById.do','user','url','2020-06-26 00:49:20',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('dd7db870-bbaa-4968-ad34-0d91f5d016b5','/api/sysApi/selectById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('dd9506e1-5d04-41ca-b0a5-f3899df396d2','/api/sysSession/deleteById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('dfe7230f-6232-4eb8-ae8d-fd5bad4f2dc1','/api/sysQudQux/insert.do','deny','url','2020-06-26 00:49:18',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('e1a8a195-729b-4df9-8f86-9c19d74708d6','/api/sysUserInfo/queryRoles.do','deny','url','2020-06-26 00:49:21',NULL,'查询用户权限','api',NULL,NULL,NULL,NULL,'0'),('e2f8a7b1-ad85-4d01-8ba0-a7c944bbc1d0','/api/sysMenusNode/selectList.do','deny','url','2020-06-26 00:49:17',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0'),('e30ed86d-fd12-4e8a-86a8-a6c45b889c48','/api/schedule/queryJobs.do','deny','url','2020-06-26 00:49:17',NULL,'查询任务','api',NULL,NULL,NULL,NULL,'0'),('e3254d16-d5e6-4532-98f6-ffdf7024778f','/api/cmdb/resActionExt/insert.do','user','url','2020-06-26 00:49:22',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('e4dfed88-03d9-4a77-82be-c127335f0ae1','/api/sysCardAddr/selectById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('e5581e38-1d7c-4891-ae5e-2b47421b41d3','/api/sysDict/ext/selectList.do','deny','url','2020-06-26 00:49:20',NULL,'','api',NULL,NULL,NULL,NULL,'0'),('e55ccdf8-d539-4d03-afae-dc0cb4769904','/api/cmdb/resAttrs/selectById.do','user','url','2020-06-26 00:49:23',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('e5686496-9adc-4774-8614-295b6ccf75af','/api/sysRegion/selectById.do','deny','url','2020-06-26 00:49:19',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('e5ab078a-cfc5-4e3e-8123-74f4d199fef5','/api/sysCardAddr/insert.do','deny','url','2020-06-26 00:49:19',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('e79c5761-fc72-40bf-9fcd-cce13c6b702f','/api/sysParams/updateById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('e7be27c9-166f-4af1-be33-667761a26e1c','/api/sysUserInfo/selectPage.do','deny','url','2020-06-26 00:49:21',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('e7d5fda5-7a63-4f28-925e-e810c4f491fa','/api/cmdb/resActionItem/selectPage.do','user','url','2020-06-26 00:49:23',NULL,'查询所有,有分页','api',NULL,NULL,NULL,NULL,'0'),('e9a66dea-56c8-4cee-834b-7c9b3159ef71','/api/sysJob/selectById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('eaea667c-e166-461e-b1da-b872d023f337','/api/sysFiles/selectById.do','user','url','2020-06-26 00:49:21',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('eb29c8e9-ce70-4bc5-8987-6dcc90c68eca','/api/sysModulesItem/updateById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('ebaa4807-84b8-4f81-8eb8-e46a2a0aa6c6','/api/sysUserInfo/insertOrUpdate.do','deny','url','2020-06-26 00:49:21',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('ec2127a2-2818-4847-8d8b-e3366a0819f8','/api/user/loginFast.do','allow','url','2020-06-26 00:49:18',NULL,'登录','api',NULL,NULL,NULL,NULL,'0'),('ecdc783f-8e55-4a93-8f57-a22b929ad837','/api/sysDictItem/deleteById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('ece545de-494e-4b01-804c-6ee658d05166','/api/sysLogAccess/updateById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('edb26252-58d9-4497-8892-5ff611ac3cd8','/api/sysUserGroup/updateById.do','deny','url','2020-06-26 00:49:20',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('ee3663a4-22a4-4723-b77c-de3f98e3f16d','/api/sysDbbackupRec/insertOrUpdate.do','user','url','2020-06-26 00:49:19',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('ee367df7-65b3-4eb8-9761-b522651af522','/api/sysApi/insert.do','deny','url','2020-06-26 00:49:18',NULL,'插入','api',NULL,NULL,NULL,NULL,'0'),('ee857a46-f81c-4350-b6ba-aa30ba844e3b','/api/sysMenusNode/updateById.do','deny','url','2020-06-26 00:49:17',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('f137b92d-7a9e-4d7e-ba9e-93edd83c589f','/api/sysUserInfo/my/listMyMenusById.do','user','url','2020-06-26 00:49:22',NULL,'查询用户菜单','api',NULL,NULL,NULL,NULL,'0'),('f28b6f80-c73b-463f-91fc-6341d7f4fc57','/api/sysQudChengs/deleteById.do','deny','url','2020-06-26 00:49:18',NULL,'根据Id删除','api',NULL,NULL,NULL,NULL,'0'),('f294a7cc-a376-4a35-8b66-bfd6cce7b4fa','/api/sysQudChengs/selectById.do','deny','url','2020-06-26 00:49:23',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('f5727bca-8dac-4531-8e0b-cdef790284ec','/api/sysSession/insertOrUpdate.do','deny','url','2020-06-26 00:49:20',NULL,'存在则更新,否则插入','api',NULL,NULL,NULL,NULL,'0'),('f7d96701-e7be-447e-b551-5b7310fe61b2','/api/sysJob/updateById.do','deny','url','2020-06-26 00:49:21',NULL,'根据Id更新','api',NULL,NULL,NULL,NULL,'0'),('f868e545-613d-42a6-b86b-79b86dae92a8','/api/menu/deleteNode.do','deny','url','2020-06-26 00:49:18',NULL,'删除菜单','api',NULL,NULL,NULL,NULL,'0'),('fc65ed88-fe8f-4509-a45a-4e7ed9c5e90d','/api/sysMenusNode/queryMenuNodesForStageSetting.do','deny','url','2020-06-26 00:49:17',NULL,'查','api',NULL,NULL,NULL,NULL,'0'),('fd86e7e3-02db-4830-b0a8-8de75ab84891','/api/sysFileConf/selectById.do','user','url','2020-06-26 00:49:20',NULL,'根据Id查询','api',NULL,NULL,NULL,NULL,'0'),('fddd6ad2-1c2a-4049-a3b6-4a19fb29fe6a','/api/sysParams/selectList.do','deny','url','2020-06-26 00:49:18',NULL,'查询所有,无分页','api',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_card_addr`
--

DROP TABLE IF EXISTS `sys_card_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_card_addr` (
  `id` varchar(50) NOT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `shengf` varchar(50) DEFAULT NULL,
  `chengs` varchar(50) DEFAULT NULL,
  `qux` varchar(50) DEFAULT NULL,
  `shengfmc` text,
  `chengsmc` text,
  `quxmc` text,
  `user_id` varchar(50) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL,
  `card_bank` varchar(100) DEFAULT NULL,
  `card_part` varchar(100) DEFAULT NULL,
  `card_name` varchar(200) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_card_addr`
--

LOCK TABLES `sys_card_addr` WRITE;
/*!40000 ALTER TABLE `sys_card_addr` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_card_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dbbackup_rec`
--

DROP TABLE IF EXISTS `sys_dbbackup_rec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dbbackup_rec` (
  `id` varchar(50) NOT NULL,
  `dbname` varchar(50) DEFAULT NULL,
  `result` varchar(1000) DEFAULT NULL,
  `duration` varchar(100) DEFAULT NULL,
  `fileid` varchar(50) DEFAULT NULL,
  `filepath` varchar(1000) DEFAULT NULL,
  `filesize` varchar(100) DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dbbackup_rec`
--

LOCK TABLES `sys_dbbackup_rec` WRITE;
/*!40000 ALTER TABLE `sys_dbbackup_rec` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dbbackup_rec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `dict_id` varchar(50) NOT NULL,
  `name` text,
  `dict_level` varchar(100) NOT NULL,
  `status` char(1) NOT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `mark` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES ('assettype','资产_分类','system','Y','0','1','2019-10-30 10:02:56','sys','2019-11-09 15:52:44','1151420235196588033'),('dbbkarchtype','数据库备份_日志类型','system','Y','0',NULL,'2020-01-30 12:24:37','sys','2020-01-30 12:24:37','sys'),('dbbkmethod','数据库备份_备份方式','system','Y','0',NULL,'2020-01-30 12:25:44','sys','2020-01-30 12:25:44','sys'),('dbbkstatus','数据库备份_目前状况','system','Y','0',NULL,'2020-01-30 12:26:22','sys','2020-01-30 12:26:22','sys'),('dbbktype','数据库备份_备份类型','system','Y','0',NULL,'2020-01-30 12:25:07','sys','2020-01-30 12:25:07','sys'),('devbrand','资产_品牌','system','Y','0',NULL,'2018-12-31 23:44:56','sys','2019-11-09 15:53:03','1151420235196588033'),('devdc','资产_区域','system','Y','0',NULL,'2018-12-31 19:10:24','sys','2020-04-21 02:22:22','1151420235196588033'),('devenv','资产_运行环境','system','Y','0',NULL,'2018-12-29 21:40:29','sys','2019-11-09 15:54:00','1151420235196588033'),('devrack','资产_位置_机柜','system','Y','0',NULL,'2019-10-19 11:02:56','sys','2019-11-09 15:54:23','1151420235196588033'),('devrecycle','资产_生命周期','system','Y','0',NULL,'2018-12-31 19:07:06','sys','2019-11-09 15:54:33','1151420235196588033'),('devrisk','资产_风险等级','system','Y','0',NULL,'2018-12-31 19:09:13','sys','2019-11-09 15:54:41','1151420235196588033'),('devwb','资产_维保状态','system','Y','0',NULL,'2019-10-19 12:16:04','sys','2019-11-09 15:55:13','1151420235196588033'),('nodebak','系统_备份类型','system','Y','0','1','2020-02-28 01:06:51','1151420235196588033','2020-02-28 01:09:28','1151420235196588033'),('outercontact','外部联系人','system','Y','0',NULL,'2019-11-09 16:17:40','1151420235196588033','2019-11-09 16:17:40','1151420235196588033'),('sysdb','系统_数据库类型','system','Y','0',NULL,'2020-01-24 03:41:08','1151420235196588033','2020-01-30 08:42:28','1151420235196588033'),('sysdbdtl','系统_数据库详情','system','Y','0',NULL,'2020-01-24 04:04:53','1151420235196588033','2020-01-24 04:04:53','1151420235196588033'),('sysenv','系统_运行环境_TEMP','system','Y','0',NULL,'2020-01-24 03:40:27','1151420235196588033','2020-07-20 07:51:37','1151420235196588033'),('sysexecenv','系统_执行环境','system','Y','0',NULL,'2020-01-24 03:47:15','1151420235196588033','2020-01-24 03:47:15','1151420235196588033'),('sysinfodev','信息系统_开发模式','system','Y','0','1','2020-03-06 04:13:07','sys','2020-03-06 04:13:07','sys'),('sysinfograde','信息系统_风险评级','system','Y','0',NULL,'2020-03-06 04:13:34','sys','2020-03-06 04:13:34','sys'),('sysinfoops','信息系统_运维模式','system','Y','0',NULL,'2020-03-06 04:13:18','sys','2020-03-06 04:13:18','sys'),('sysinfotype','信息系统_类型','system','Y','0','1','2020-03-06 04:12:49','sys','2020-03-06 04:12:49','sys'),('syslevel','系统_风险等级','system','Y','0',NULL,'2020-01-24 03:34:51','1151420235196588033','2020-01-30 08:42:39','1151420235196588033'),('sysloc','系统_位置','system','Y','0',NULL,'2020-01-24 03:40:48','1151420235196588033','2020-01-30 08:42:54','1151420235196588033'),('sysmid','系统_中间件','system','Y','0',NULL,'2020-01-24 03:39:05','1151420235196588033','2020-01-24 03:39:05','1151420235196588033'),('sysmonitor','系统_系统监控','system','Y','0',NULL,'2020-01-24 03:48:31','1151420235196588033','2020-01-30 08:43:05','1151420235196588033'),('sysos','系统_操作系统类型','system','Y','0',NULL,'2020-01-24 03:38:28','1151420235196588033','2020-01-24 03:38:28','1151420235196588033'),('sysosdtl','系统_操作系统详情','system','Y','0',NULL,'2020-01-24 03:38:44','1151420235196588033','2020-01-24 03:38:44','1151420235196588033'),('syspwdstrategy','系统_改密策略','system','Y','0',NULL,'2020-01-24 03:48:52','1151420235196588033','2020-01-24 03:48:52','1151420235196588033'),('sysstatus','系统_使用状态','system','Y','0','1','2020-02-06 07:04:14','1198252528207134721','2020-02-06 07:04:14','1198252528207134721'),('systype','系统_业务类型','system','Y','0',NULL,'2020-01-24 04:45:44','1151420235196588033','2020-01-24 04:45:44','1151420235196588033'),('warehouse','资产_仓库','system','Y','0','1','2020-05-22 09:48:11','1151420235196588033','2020-05-25 05:29:31','1151420235196588033'),('zcsource','资产_来源','system','Y','0',NULL,'2020-04-21 02:21:30','1151420235196588033','2020-04-21 02:21:30','1151420235196588033'),('zcsupper','资产_供应商','system','Y','0',NULL,'2018-12-29 21:44:49','sys','2019-11-09 15:55:04','1151420235196588033'),('zcusefullife','资产_使用年限','system','Y','0',NULL,'2020-08-03 22:36:16','1151420235196588033','2020-08-03 22:36:16','1151420235196588033'),('zcwbcomoute','资产_维保计算方式','system','Y','0',NULL,'2019-11-13 21:39:15','1151420235196588033','2020-08-03 22:36:54','1151420235196588033'),('zcwbsupper','资产_维保供应商','system','Y','0','1','2020-04-21 02:21:48','1151420235196588033','2020-04-21 02:21:48','1151420235196588033');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_item`
--

DROP TABLE IF EXISTS `sys_dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_item` (
  `dict_id` varchar(100) NOT NULL,
  `dict_item_id` varchar(50) NOT NULL,
  `name` text,
  `sort` decimal(10,0) DEFAULT NULL,
  `mark` text,
  `code` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`dict_item_id`),
  KEY `sys_dict_itemidx1` (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_item`
--

LOCK TABLES `sys_dict_item` WRITE;
/*!40000 ALTER TABLE `sys_dict_item` DISABLE KEYS */;
INSERT INTO `sys_dict_item` VALUES ('devsupper','1185419950539882498','拓云',1,NULL,'1','2019-10-19 12:58:35','sys','2019-10-19 12:58:35','sys','0'),('devdotequipment','1189173342798032898','清分机',1,'1','pointdev','2019-10-29 21:33:13','sys','2019-11-18 01:01:44','1151420235196588033','0'),('devdotequipment','1189173395008729089','点钞机',1,'1','pointdev','2019-10-29 21:33:26','sys','2019-11-18 01:01:47','1151420235196588033','0'),('devdotequipment','1189522974422024194','终端',2,NULL,'pointdev','2019-10-30 20:42:32','sys','2019-11-18 01:01:50','1151420235196588033','0'),('zcother','1189523190692921346','打印机',1,NULL,'1','2019-10-30 20:43:24','sys','2019-10-30 20:43:24','sys','0'),('zcother','1189523384499126273','空调',2,NULL,'2','2019-10-30 20:44:10','sys','2019-10-30 20:44:10','sys','0'),('zcother','1190530758369968129','电话机',1,NULL,'1','2019-11-02 15:27:06','1151420235196588033','2019-11-02 15:27:06','1151420235196588033','0'),('devdc','1190531808845987842','华商-库房',11,NULL,'11','2019-11-02 15:31:17','1151420235196588033','2019-11-02 15:31:17','1151420235196588033','0'),('1193065151265746946','1193065268567846914','空调',1,NULL,'1','2019-11-09 15:18:21','1151420235196588033','2019-11-09 15:18:21','1151420235196588033','0'),('1193065151265746946','1193065316257083393','冰箱',2,NULL,'2','2019-11-09 15:18:32','1151420235196588033','2019-11-09 15:18:32','1151420235196588033','0'),('1193065151265746946','1193065350608433153','打印机',2,NULL,'2','2019-11-09 15:18:40','1151420235196588033','2019-11-09 15:18:40','1151420235196588033','0'),('devrack','1193697700433993729','未知',1000,NULL,'1','2019-11-11 09:11:24','1151420235196588033','2020-06-22 07:39:06','1151420235196588033','0'),('zcother','1193887257901256705','电视机',1,NULL,'1','2019-11-11 21:44:38','1151420235196588033','2019-11-11 21:44:38','1151420235196588033','0'),('zcsofttype','1194165528660398082','授权许可证',1,NULL,'1','2019-11-12 16:10:23','1151420235196588033','2019-11-12 16:10:23','1151420235196588033','0'),('zcsofttype','1194165580493606914','介质',1,NULL,'1','2019-11-12 16:10:36','1151420235196588033','2019-11-12 16:10:36','1151420235196588033','0'),('devbrand','1194605078528487425','大华',1111,NULL,'111','2019-11-13 21:17:00','1151420235196588033','2019-11-13 21:17:00','1151420235196588033','0'),('devbrand','1194605149336727553','NetAPP',1111,NULL,'111','2019-11-13 21:17:17','1151420235196588033','2020-05-07 00:43:24','1151420235196588033','0'),('devbrand','1194605214390382593','Raritan',111,NULL,'111','2019-11-13 21:17:32','1151420235196588033','2020-05-07 00:43:15','1151420235196588033','0'),('devbrand','1194605261291089921','明御',111,NULL,'111','2019-11-13 21:17:44','1151420235196588033','2019-11-13 21:17:44','1151420235196588033','0'),('devbrand','1194605304811188226','航信',111,NULL,'11','2019-11-13 21:17:54','1151420235196588033','2019-11-13 21:17:54','1151420235196588033','0'),('devbrand','1194605347114938369','HDS',1111,NULL,'111','2019-11-13 21:18:04','1151420235196588033','2020-05-07 00:43:27','1151420235196588033','0'),('devbrand','1194605422830514178','博科',111,NULL,'111','2019-11-13 21:18:22','1151420235196588033','2020-05-07 00:43:20','1151420235196588033','0'),('devdc','1195146407518990338','华商-大厦',1,'1','1','2019-11-15 01:08:03','1151420235196588033','2019-11-15 01:08:03','1151420235196588033','0'),('devbrand','1195150972574298114','绿盟',1,NULL,'1','2019-11-15 01:26:11','1151420235196588033','2019-11-15 01:26:11','1151420235196588033','0'),('devbrand','1195151019093323777','安恒',1,NULL,'1','2019-11-15 01:26:22','1151420235196588033','2019-11-15 01:26:22','1151420235196588033','0'),('devbrand','1195151091768029186','江南科友',1,NULL,'1','2019-11-15 01:26:40','1151420235196588033','2019-11-15 01:26:40','1151420235196588033','0'),('devbrand','1195151127365087234','江南天安',1,NULL,'1','2019-11-15 01:26:48','1151420235196588033','2019-11-15 01:26:48','1151420235196588033','0'),('devbrand','1195151201419718657','信安世纪',1,NULL,'1','2019-11-15 01:27:06','1151420235196588033','2019-11-15 01:27:06','1151420235196588033','0'),('devbrand','1195151253538140161','吉大正元',1,NULL,'1','2019-11-15 01:27:18','1151420235196588033','2019-11-15 01:27:18','1151420235196588033','0'),('devbrand','1195151449596686337','安达通',1,NULL,'1','2019-11-15 01:28:05','1151420235196588033','2019-11-15 01:28:05','1151420235196588033','0'),('devbrand','1195151514943942657','江南所',1,NULL,'1','2019-11-15 01:28:21','1151420235196588033','2019-11-15 01:28:21','1151420235196588033','0'),('devbrand','1195152043514327041','NPC',1,NULL,'1','2019-11-15 01:30:27','1151420235196588033','2019-11-15 01:30:27','1151420235196588033','0'),('devbrand','1195152324654329857','D-Link',1,NULL,'1','2019-11-15 01:31:34','1151420235196588033','2019-11-15 01:31:34','1151420235196588033','0'),('devbrand','1195199377031884802','融天科技',1,NULL,'1','2019-11-15 04:38:32','1151420235196588033','2019-11-15 04:38:32','1151420235196588033','0'),('devrack','1195216615969517569','G11',1,NULL,'1','2019-11-15 05:47:02','1151420235196588033','2019-11-15 05:47:02','1151420235196588033','0'),('devrack','1195216701113888770','K04',1,NULL,'1','2019-11-15 05:47:22','1151420235196588033','2019-11-15 05:47:22','1151420235196588033','0'),('devrack','1195216876859420673','L02',1,NULL,'1','2019-11-15 05:48:04','1151420235196588033','2019-11-15 05:48:04','1151420235196588033','0'),('devrack','1195216996086706177','J05',1,NULL,'1','2019-11-15 05:48:33','1151420235196588033','2019-11-15 05:48:33','1151420235196588033','0'),('devrack','1195217062172160001','J03',1,NULL,'1','2019-11-15 05:48:48','1151420235196588033','2019-11-15 05:48:48','1151420235196588033','0'),('devrack','1195217129570430978','I04',1,NULL,'1','2019-11-15 05:49:04','1151420235196588033','2019-11-15 05:49:04','1151420235196588033','0'),('devrack','1195217169529565186','I03',1,NULL,'1','2019-11-15 05:49:14','1151420235196588033','2019-11-15 05:49:14','1151420235196588033','0'),('devrack','1195217197417492481','I01',1,NULL,'1','2019-11-15 05:49:21','1151420235196588033','2019-11-15 05:49:21','1151420235196588033','0'),('devrack','1196609241348141058','H11',1,NULL,'1','2019-11-19 02:00:50','1151420235196588033','2019-11-19 02:00:50','1151420235196588033','0'),('devrack','1196609441563242498','I02',1,NULL,'1','2019-11-19 02:01:37','1151420235196588033','2019-11-19 02:01:37','1151420235196588033','0'),('devrack','1196609524971171841','L01',1,NULL,'1','2019-11-19 02:01:57','1151420235196588033','2019-11-19 02:01:57','1151420235196588033','0'),('devrack','1196609626238447617','J04',1,NULL,'1','2019-11-19 02:02:21','1151420235196588033','2019-11-19 02:02:21','1151420235196588033','0'),('devrack','1196609665971089409','J02',1,NULL,'1','2019-11-19 02:02:31','1151420235196588033','2019-11-19 02:02:31','1151420235196588033','0'),('zcother','1196609763748704257','波分设备',1,NULL,'1','2019-11-19 02:02:54','1151420235196588033','2019-11-19 02:02:54','1151420235196588033','0'),('devsafety','1196994061899161602','防火墙',1,NULL,'safety','2019-11-20 03:29:58','1151420235196588033','2019-11-20 03:31:56','1151420235196588033','0'),('devsafety','1196994196750229506','加密机',1,NULL,'safety','2019-11-20 03:30:30','1151420235196588033','2019-11-20 03:32:00','1151420235196588033','0'),('devsafety','1196994237661470722','堡垒机',1,NULL,'safety','2019-11-20 03:30:40','1151420235196588033','2019-11-20 03:32:03','1151420235196588033','0'),('devsafety','1196994286948737025','IDS',1,NULL,'safety','2019-11-20 03:30:52','1151420235196588033','2019-11-20 03:32:06','1151420235196588033','0'),('devsafety','1196994329239904257','核验机',1,NULL,'safety','2019-11-20 03:31:02','1151420235196588033','2019-11-20 03:32:13','1151420235196588033','0'),('devsafety','1196994378854326273','监控设备',1,NULL,'safety','2019-11-20 03:31:14','1151420235196588033','2019-11-20 03:32:16','1151420235196588033','0'),('sysdb','1220552556205428738','Oracle',1,NULL,'1','2020-01-24 03:43:01','1151420235196588033','2020-01-24 03:43:56','1151420235196588033','0'),('sysdb','1220552610530054146','Mysql',2,NULL,'2','2020-01-24 03:43:14','1151420235196588033','2020-01-24 03:43:52','1151420235196588033','0'),('sysdb','1220552663407644673','DB2',3,NULL,'3','2020-01-24 03:43:26','1151420235196588033','2020-01-24 03:43:48','1151420235196588033','0'),('sysdb','1220552738200473602','SqlServer',4,NULL,'4','2020-01-24 03:43:44','1151420235196588033','2020-01-24 03:43:44','1151420235196588033','0'),('sysdb','1220552886716583938','MongoDB',5,NULL,'5','2020-01-24 03:44:20','1151420235196588033','2020-01-24 03:44:20','1151420235196588033','0'),('syslevel','1220554568976420866','一级',1,NULL,'1','2020-01-24 03:51:01','1151420235196588033','2020-01-24 03:55:02','1151420235196588033','0'),('sysexecenv','1220555122582605825','Java 1.6.0_24',1,NULL,'1','2020-01-24 03:53:13','1151420235196588033','2020-01-30 08:36:10','1151420235196588033','0'),('sysexecenv','1220555191738290177','Java 1.7.0_79',2,NULL,'2','2020-01-24 03:53:29','1151420235196588033','2020-01-30 08:26:15','1151420235196588033','0'),('sysexecenv','1220555234696351745','Java 1.8.0_161',4,NULL,'4','2020-01-24 03:53:39','1151420235196588033','2020-01-30 08:38:05','1151420235196588033','0'),('sysexecenv','1220555298755956738','Java 1.7.0_75',4,NULL,'3','2020-01-24 03:53:55','1151420235196588033','2020-01-24 03:53:55','1151420235196588033','0'),('sysexecenv','1220555345639886850','Java 1.8.0_131',5,NULL,'5','2020-01-24 03:54:06','1151420235196588033','2020-01-24 03:54:06','1151420235196588033','0'),('syslevel','1220555610636013569','二级',2,NULL,'2','2020-01-24 03:55:09','1151420235196588033','2020-01-24 03:55:09','1151420235196588033','0'),('syslevel','1220555642193956865','未定',0,NULL,'0','2020-01-24 03:55:17','1151420235196588033','2020-01-25 10:09:07','1151420235196588033','0'),('sysloc','1220555760129396737','VM5_XXX',1,NULL,'1','2020-01-24 03:55:45','1151420235196588033','2020-05-23 01:07:24','1151420235196588033','0'),('sysloc','1220555804052148226','物理机',2,NULL,'1','2020-01-24 03:55:55','1151420235196588033','2020-01-24 03:55:55','1151420235196588033','0'),('sysloc','1220555838332194817','小机',3,NULL,'1','2020-01-24 03:56:03','1151420235196588033','2020-01-24 03:56:03','1151420235196588033','0'),('sysmonitor','1220555913099857922','不需要',1,NULL,'1','2020-01-24 03:56:21','1151420235196588033','2020-01-24 03:56:21','1151420235196588033','0'),('sysmonitor','1220555936999002113','未部署',2,NULL,'2','2020-01-24 03:56:27','1151420235196588033','2020-01-24 03:56:27','1151420235196588033','0'),('sysmonitor','1220556091278086145','监控中',3,NULL,'5','2020-01-24 03:57:04','1151420235196588033','2020-01-24 03:57:04','1151420235196588033','0'),('sysos','1220556159737516034','AIX',1,NULL,'1','2020-01-24 03:57:20','1151420235196588033','2020-01-26 02:00:20','1151420235196588033','0'),('sysos','1220556195930165249','Windows',2,NULL,'1','2020-01-24 03:57:29','1151420235196588033','2020-01-26 02:00:16','1151420235196588033','0'),('sysos','1220556230780637185','RedHat',4,NULL,'3','2020-01-24 03:57:37','1151420235196588033','2020-01-24 03:57:37','1151420235196588033','0'),('sysos','1220556268458070018','CentOS',5,NULL,'5','2020-01-24 03:57:46','1151420235196588033','2020-01-24 03:57:46','1151420235196588033','0'),('sysos','1220556303589560322','中标麒麟',3,NULL,'1','2020-01-24 03:57:54','1151420235196588033','2020-01-24 03:57:54','1151420235196588033','0'),('sysos','1220556389950279681','SUSE',6,NULL,'6','2020-01-24 03:58:15','1151420235196588033','2020-01-24 03:58:15','1151420235196588033','0'),('sysosdtl','1220556637791703041','AIX 7100-01-04-1216',1,NULL,'AIX','2020-01-24 03:59:14','1151420235196588033','2020-01-24 03:59:14','1151420235196588033','0'),('sysosdtl','1220556702077800450','AIX 7100-02-02-1316',5,'5','AIX','2020-01-24 03:59:29','1151420235196588033','2020-01-24 03:59:29','1151420235196588033','0'),('sysosdtl','1220556733346336770','AIX',2,NULL,'AIX','2020-01-24 03:59:37','1151420235196588033','2020-01-24 03:59:37','1151420235196588033','0'),('sysosdtl','1220556778749677570','Unknow',100,NULL,'Unknow','2020-01-24 03:59:48','1151420235196588033','2020-01-24 03:59:48','1151420235196588033','0'),('sysosdtl','1220556813830836226','CentOS 6.4(64位)',10,'1','CentOS','2020-01-24 03:59:56','1151420235196588033','2020-01-25 11:16:22','1151420235196588033','0'),('sysosdtl','1220556872966328322','RedHat 7.4(64位)',20,'1','RedHat','2020-01-24 04:00:10','1151420235196588033','2020-01-24 04:00:10','1151420235196588033','0'),('sysosdtl','1220556921741889538','Windows 2003(32位)',30,NULL,'Windows','2020-01-24 04:00:22','1151420235196588033','2020-01-24 04:00:22','1151420235196588033','0'),('sysosdtl','1220557047747170306','Windows 2012(64位)',31,NULL,'Windows','2020-01-24 04:00:52','1151420235196588033','2020-01-24 04:00:52','1151420235196588033','0'),('sysosdtl','1220557117943042050','Windows 2008(64位)',32,NULL,'Windows','2020-01-24 04:01:08','1151420235196588033','2020-01-25 11:17:49','1151420235196588033','0'),('sysosdtl','1220557176759767041','CentOS 7(64位)',11,NULL,'CentOS','2020-01-24 04:01:22','1151420235196588033','2020-01-24 04:01:22','1151420235196588033','0'),('sysosdtl','1220557278517776386','Redhat 6.6(64位)',21,NULL,'RedHat','2020-01-24 04:01:47','1151420235196588033','2020-01-24 04:01:47','1151420235196588033','0'),('sysosdtl','1220557353570652162','Windows 7',33,NULL,'Windows','2020-01-24 04:02:05','1151420235196588033','2020-01-24 04:02:05','1151420235196588033','0'),('sysosdtl','1220557463897624578','中标麒麟Linux',81,NULL,'中标麒麟','2020-01-24 04:02:31','1151420235196588033','2020-01-24 04:53:30','1151420235196588033','0'),('sysosdtl','1220557505077301249','SUSE 11(64位)',51,NULL,'SUSE','2020-01-24 04:02:41','1151420235196588033','2020-01-24 04:02:41','1151420235196588033','0'),('sysosdtl','1220557577416462338','CentOS 7.1(64位)',12,NULL,'CentOS','2020-01-24 04:02:58','1151420235196588033','2020-01-24 04:02:58','1151420235196588033','0'),('sysosdtl','1220557741292113921','SUSE 10(32位)',52,NULL,'SUSE','2020-01-24 04:03:37','1151420235196588033','2020-01-24 04:03:37','1151420235196588033','0'),('sysdb','1220557873593044994','Informix',6,NULL,'6','2020-01-24 04:04:09','1151420235196588033','2020-01-24 04:04:09','1151420235196588033','0'),('sysdbdtl','1220558318117965826','Informix',51,NULL,'Informix','2020-01-24 04:05:55','1151420235196588033','2020-01-24 04:05:55','1151420235196588033','0'),('sysdbdtl','1220558472548044801','Oracle 11.2.0.1.0',81,NULL,'Oracle','2020-01-24 04:06:31','1151420235196588033','2020-01-24 04:06:31','1151420235196588033','0'),('sysdbdtl','1220558520342138882','SqlServer 2008',61,NULL,'SqlServer','2020-01-24 04:06:43','1151420235196588033','2020-01-25 10:15:54','1151420235196588033','0'),('sysdbdtl','1220558557340094466','SqlServer 2012',62,NULL,'SqlServer','2020-01-24 04:06:52','1151420235196588033','2020-01-25 10:16:02','1151420235196588033','0'),('sysdbdtl','1220558646502608897','MongoDB 3.6.3',71,NULL,'MongoDB','2020-01-24 04:07:13','1151420235196588033','2020-01-24 04:07:13','1151420235196588033','0'),('sysdbdtl','1220558751066607618','DB2 v11.1.4.4(4)',1,NULL,'DB2','2020-01-24 04:07:38','1151420235196588033','2020-01-24 04:07:38','1151420235196588033','0'),('sysdbdtl','1220558828594122753','DB2 v10.5.0.3(3)',2,NULL,'DB2','2020-01-24 04:07:56','1151420235196588033','2020-01-24 04:07:56','1151420235196588033','0'),('sysdbdtl','1220558879999512577','DB2 v10.5.0.1(1)',3,NULL,'DB2','2020-01-24 04:08:09','1151420235196588033','2020-01-24 04:08:09','1151420235196588033','0'),('sysdbdtl','1220558942956015618','DB2 v10.5.0.7(7)',4,NULL,'DB2','2020-01-24 04:08:24','1151420235196588033','2020-01-24 04:08:24','1151420235196588033','0'),('sysdbdtl','1220558985238794241','DB2 v10.5.0.9(9)',5,NULL,'DB2','2020-01-24 04:08:34','1151420235196588033','2020-01-24 04:08:34','1151420235196588033','0'),('sysdbdtl','1220559019325902850','DB2 v11.1.0.0(0)',6,NULL,'DB2','2020-01-24 04:08:42','1151420235196588033','2020-01-24 04:08:42','1151420235196588033','0'),('sysdbdtl','1220559203522957313','Mysql 5.1.66',11,NULL,'Mysql','2020-01-24 04:09:26','1151420235196588033','2020-01-24 04:09:26','1151420235196588033','0'),('sysdbdtl','1220559240915177473','Mysql 5.7.21',12,NULL,'Mysql','2020-01-24 04:09:35','1151420235196588033','2020-01-24 04:09:35','1151420235196588033','0'),('sysdbdtl','1220559282170351617','Mysql 5.1.30',13,NULL,'Mysql','2020-01-24 04:09:44','1151420235196588033','2020-01-24 04:09:44','1151420235196588033','0'),('sysdbdtl','1220559340697669634','Mysql 5.7.23',14,NULL,'Mysql','2020-01-24 04:09:58','1151420235196588033','2020-01-24 04:09:58','1151420235196588033','0'),('sysdbdtl','1220559373631344642','Mysql 5.6.44',15,NULL,'Mysql','2020-01-24 04:10:06','1151420235196588033','2020-01-24 04:10:06','1151420235196588033','0'),('sysdbdtl','1220559454224896001','Mysql 5.7.26',16,NULL,'Mysql','2020-01-24 04:10:25','1151420235196588033','2020-01-24 04:10:25','1151420235196588033','0'),('syspwdstrategy','1220560629183664129','需要',1,NULL,'1','2020-01-24 04:15:06','1151420235196588033','2020-01-24 04:15:06','1151420235196588033','0'),('syspwdstrategy','1220560667855147009','改前通知',2,NULL,'2','2020-01-24 04:15:15','1151420235196588033','2020-01-24 04:15:15','1151420235196588033','0'),('syspwdstrategy','1220560689007022082','不需要',3,NULL,'2','2020-01-24 04:15:20','1151420235196588033','2020-01-24 04:15:20','1151420235196588033','0'),('sysdb','1220560969484324866','PostgreSQL',7,NULL,'7','2020-01-24 04:16:27','1151420235196588033','2020-01-24 04:16:27','1151420235196588033','0'),('systype','1220568510004056066','运维系统',1,NULL,'1','2020-01-24 04:46:25','1151420235196588033','2020-01-24 04:46:25','1151420235196588033','0'),('systype','1220568534842724353','业务系统',1,NULL,'1','2020-01-24 04:46:30','1151420235196588033','2020-01-24 04:46:30','1151420235196588033','0'),('systype','1220568571316391937','办公系统',1,NULL,'1','2020-01-24 04:46:39','1151420235196588033','2020-01-24 04:46:39','1151420235196588033','0'),('sysmid','1220569841418752002','Apache',1,'1','Apache','2020-01-24 04:51:42','1151420235196588033','2020-01-24 04:51:42','1151420235196588033','0'),('sysmid','1220569891662319618','IIS',11,'1','IIS','2020-01-24 04:51:54','1151420235196588033','2020-01-24 04:51:54','1151420235196588033','0'),('sysmid','1220569922976993282','IIS6',12,NULL,'IIS','2020-01-24 04:52:01','1151420235196588033','2020-01-24 04:52:01','1151420235196588033','0'),('sysmid','1220569951062052866','IIS7',13,NULL,'IIS','2020-01-24 04:52:08','1151420235196588033','2020-01-24 04:52:08','1151420235196588033','0'),('sysmid','1220570022923063297','Tomcat 6',21,NULL,'Tomcat','2020-01-24 04:52:25','1151420235196588033','2020-01-24 04:53:03','1151420235196588033','0'),('sysmid','1220570056515244034','Tomcat 7',22,NULL,'Tomcat','2020-01-24 04:52:33','1151420235196588033','2020-01-24 04:53:08','1151420235196588033','0'),('sysmid','1220570088794607618','Tomcat 8',23,NULL,'Tomcat','2020-01-24 04:52:41','1151420235196588033','2020-01-25 10:12:37','1151420235196588033','0'),('sysmid','1220570159468630018','Weblogic 10.3.6',0,NULL,'Weblogic','2020-01-24 04:52:58','1151420235196588033','2020-02-01 02:24:11','1151420235196588033','0'),('sysmid','1220570429720219650','Tomcat 6.0.45',24,NULL,'Tomcat','2020-01-24 04:54:02','1151420235196588033','2020-01-24 04:54:02','1151420235196588033','0'),('sysmid','1220570477199740930','Tomcat 6.0.26',25,NULL,'Tomcat','2020-01-24 04:54:14','1151420235196588033','2020-01-24 04:54:14','1151420235196588033','0'),('sysmid','1220570555805192194','Tomcat 6.0.29',26,NULL,'Tomcat','2020-01-24 04:54:32','1151420235196588033','2020-01-24 04:54:32','1151420235196588033','0'),('sysmid','1220570624193318914','Tomcat 7.0.67',27,NULL,'Tomcat','2020-01-24 04:54:49','1151420235196588033','2020-01-24 04:54:49','1151420235196588033','0'),('sysmid','1220570705680257026','Tomcat 7.0.42',28,NULL,'Tomcat','2020-01-24 04:55:08','1151420235196588033','2020-01-24 04:55:08','1151420235196588033','0'),('sysmid','1220570796663099394','Tomcat 9.0.2',29,NULL,'Tomcat','2020-01-24 04:55:30','1151420235196588033','2020-01-24 04:55:30','1151420235196588033','0'),('sysmid','1220570852430565377','Tomcat 9.0.12',30,NULL,'Tomcat','2020-01-24 04:55:43','1151420235196588033','2020-01-24 04:55:43','1151420235196588033','0'),('sysmid','1220570924392239106','Tomcat 7.0.68',31,NULL,'Tomcat','2020-01-24 04:56:00','1151420235196588033','2020-01-24 04:56:00','1151420235196588033','0'),('sysmid','1220570988804165633','Tomcat 8.0.53',32,NULL,'Tomcat','2020-01-24 04:56:16','1151420235196588033','2020-01-24 04:56:16','1151420235196588033','0'),('sysmid','1220571128050864130','Tomcat 9.0.21',33,NULL,'Tomcat','2020-01-24 04:56:49','1151420235196588033','2020-01-24 04:56:49','1151420235196588033','0'),('sysmid','1220571180261560321','Tomcat 9.0.29',34,NULL,'Tomcat','2020-01-24 04:57:01','1151420235196588033','2020-01-24 04:57:01','1151420235196588033','0'),('sysmid','1220571613143093249','Nginx 1.10.1',51,NULL,'Nginx','2020-01-24 04:58:44','1151420235196588033','2020-01-24 04:58:44','1151420235196588033','0'),('sysmid','1220571672773513218','Nginx 1.12.2',52,NULL,'Nginx','2020-01-24 04:58:59','1151420235196588033','2020-01-24 04:58:59','1151420235196588033','0'),('sysmid','1220571712439046146','Nginx 1.14.2',53,NULL,'Nginx','2020-01-24 04:59:08','1151420235196588033','2020-01-24 04:59:08','1151420235196588033','0'),('sysmid','1220571780915253250','Apache 2.2.15',2,NULL,'Apache','2020-01-24 04:59:24','1151420235196588033','2020-01-24 04:59:24','1151420235196588033','0'),('sysmid','1220571875509391362','Redis 5.0.5',81,NULL,'Redis','2020-01-24 04:59:47','1151420235196588033','2020-01-24 04:59:47','1151420235196588033','0'),('sysmid','1220572007093096450','MQ 7.5.0.2',71,NULL,'MQ','2020-01-24 05:00:18','1151420235196588033','2020-01-25 11:13:50','1151420235196588033','0'),('sysmid','1220572056204201985','MQ 8.0.0.9',72,'1','MQ','2020-01-24 05:00:30','1151420235196588033','2020-01-25 11:13:54','1151420235196588033','0'),('sysenv','1220584771509407745','生产',1,NULL,'1','2020-01-24 05:51:02','1151420235196588033','2020-01-24 05:51:02','1151420235196588033','0'),('sysmid','1221013196568113154','Keepalived v1.4.1',91,NULL,'Keepalived','2020-01-25 10:13:26','1151420235196588033','2020-01-25 11:14:17','1151420235196588033','0'),('sysos','1221021679795417089','Unknow',200,NULL,'100','2020-01-25 10:47:09','1151420235196588033','2020-01-25 10:47:09','1151420235196588033','0'),('sysosdtl','1221022210936909825','Windows 2016(64位)',34,NULL,'Window','2020-01-25 10:49:15','1151420235196588033','2020-01-25 10:49:15','1151420235196588033','0'),('sysosdtl','1221030179191762946','Windows 2003(64位)',39,NULL,'Windows','2020-01-25 11:20:55','1151420235196588033','2020-01-25 11:20:55','1151420235196588033','0'),('sysosdtl','1221251278768721922','Redhat 7.6(64位)',22,NULL,'RedHat','2020-01-26 01:59:29','1151420235196588033','2020-01-26 01:59:29','1151420235196588033','0'),('sysmid','1221252488544407553','Zookeeper 3.5.6',60,NULL,'Zookeeper','2020-01-26 02:04:18','1151420235196588033','2020-02-20 06:53:23','1151420235196588033','0'),('sysmid','1221252625828171777','Kafka 2.11-2.0.0',40,NULL,'Kafka','2020-01-26 02:04:50','1151420235196588033','2020-02-20 06:52:58','1151420235196588033','0'),('sysmid','1221252792392372225','Consul 1.2.2',100,NULL,'Consul','2020-01-26 02:05:30','1151420235196588033','2020-02-20 03:17:56','1151420235196588033','0'),('sysmid','1221253550219218946','Was 90501.nd',110,NULL,'IBM WebSphere','2020-01-26 02:08:31','1151420235196588033','2020-01-26 02:12:07','1151420235196588033','0'),('sysmid','1221253644637196290','Was 90501.ihs',111,NULL,'IBM WebSphere','2020-01-26 02:08:53','1151420235196588033','2020-01-26 02:11:58','1151420235196588033','0'),('sysmid','1221253733212508161','Was 90501.plugins',112,NULL,'IBM WebSphere','2020-01-26 02:09:14','1151420235196588033','2020-01-26 02:12:12','1151420235196588033','0'),('sysmid','1221254071621537794','TongLINK/Q',120,NULL,'TongLINK/Q','2020-01-26 02:10:35','1151420235196588033','2020-01-26 02:10:35','1151420235196588033','0'),('sysmid','1221254151875350530','Tuxedo',130,NULL,'Tuxedo','2020-01-26 02:10:54','1151420235196588033','2020-01-26 02:10:54','1151420235196588033','0'),('sysexecenv','1222798222842449922','Java 1.5.0_19',6,NULL,'6','2020-01-30 08:26:29','1151420235196588033','2020-01-30 08:26:29','1151420235196588033','0'),('sysexecenv','1222798277221601282','Java 1.5.0_21',7,NULL,'7','2020-01-30 08:26:42','1151420235196588033','2020-01-30 08:26:42','1151420235196588033','0'),('sysexecenv','1222800733305360386','Java 1.7.0_25',10,NULL,'10','2020-01-30 08:36:28','1151420235196588033','2020-01-30 08:36:28','1151420235196588033','0'),('sysexecenv','1222800813676613634','Java 1.6.0_32',11,NULL,'11','2020-01-30 08:36:47','1151420235196588033','2020-01-30 08:36:47','1151420235196588033','0'),('sysexecenv','1222801006220333058','Java 1.6.0_27',12,NULL,'12','2020-01-30 08:37:33','1151420235196588033','2020-01-30 08:37:33','1151420235196588033','0'),('sysexecenv','1222801100403429378','Java 1.7.0_09',8,NULL,'8','2020-01-30 08:37:56','1151420235196588033','2020-01-30 08:37:56','1151420235196588033','0'),('sysdbdtl','1222801438770515970','Mysql 5.6.41',17,NULL,'Mysql','2020-01-30 08:39:16','1151420235196588033','2020-01-30 08:39:16','1151420235196588033','0'),('sysmid','1222802161843359745','Tomcat 8.0.24',35,NULL,'Tomcat','2020-01-30 08:42:09','1151420235196588033','2020-01-30 08:42:09','1151420235196588033','0'),('dbbkarchtype','1222859895994798082','循环日志',1,NULL,'1','2020-01-30 12:31:34','sys','2020-01-30 12:31:34','sys','0'),('dbbkarchtype','1222859936042012674','归档日志',2,NULL,'2','2020-01-30 12:31:43','sys','2020-01-30 12:31:43','sys','0'),('dbbkarchtype','1222859995320111105','简单日志',3,NULL,'3','2020-01-30 12:31:57','sys','2020-01-30 12:31:57','sys','0'),('dbbkarchtype','1222860027180044289','无',22,NULL,'22','2020-01-30 12:32:05','sys','2020-05-07 01:06:27','1151420235196588033','0'),('dbbkmethod','1222860080649031682','全量',1,NULL,'1','2020-01-30 12:32:18','sys','2020-01-30 12:32:46','sys','0'),('dbbkmethod','1222860124282376194','增量',1,NULL,'1','2020-01-30 12:32:28','sys','2020-01-30 12:32:54','sys','0'),('dbbkstatus','1222860278133641217','有备份',1,NULL,'1','2020-01-30 12:33:05','sys','2020-01-30 12:33:05','sys','0'),('dbbkstatus','1222860306516496386','无备份',1,NULL,'1','2020-01-30 12:33:11','sys','2020-01-30 12:33:11','sys','0'),('dbbkstatus','1222860330692464642','未知',5,NULL,'5','2020-01-30 12:33:17','sys','2020-05-07 01:06:17','1151420235196588033','0'),('dbbktype','1222860435361320961','物理备份',1,NULL,'1','2020-01-30 12:33:42','sys','2020-01-30 12:33:42','sys','0'),('dbbktype','1222860461600886785','逻辑备份',1,NULL,'1','2020-01-30 12:33:48','sys','2020-01-30 12:33:48','sys','0'),('dbbkarchtype','1223416994477395969','缓冲日志',6,NULL,'6','2020-02-01 01:25:16','1151420235196588033','2020-02-01 01:25:16','1151420235196588033','0'),('dbbkarchtype','1223417073485500418','非缓冲日志',7,NULL,'7','2020-02-01 01:25:35','1151420235196588033','2020-02-01 01:25:35','1151420235196588033','0'),('dbbkmethod','1223417209301258242','无',3,NULL,'3','2020-02-01 01:26:07','1151420235196588033','2020-02-01 01:26:07','1151420235196588033','0'),('dbbktype','1223417274380079105','FlashCopy',4,NULL,'4','2020-02-01 01:26:23','1151420235196588033','2020-02-01 01:26:23','1151420235196588033','0'),('dbbktype','1223417300175048705','无',5,NULL,'5','2020-02-01 01:26:29','1151420235196588033','2020-02-01 01:26:29','1151420235196588033','0'),('dbbkstatus','1223417435902726146','不需要',4,NULL,'4','2020-02-01 01:27:01','1151420235196588033','2020-02-01 01:27:01','1151420235196588033','0'),('sysdbdtl','1223417717936115714','Informix11.50.UC9',52,NULL,'Informix','2020-02-01 01:28:09','1151420235196588033','2020-02-01 01:28:09','1151420235196588033','0'),('dbbkarchtype','1223422534785421313','日志',8,NULL,'8','2020-02-01 01:47:17','1151420235196588033','2020-02-01 01:47:17','1151420235196588033','0'),('sysdbdtl','1223431409411035138','Mysql 5.7.27',18,NULL,'Mysql','2020-02-01 02:22:33','1151420235196588033','2020-02-01 02:22:33','1151420235196588033','0'),('sysmid','1223431704547430401','Nginx 1.16.1',54,NULL,'Nginx','2020-02-01 02:23:43','1151420235196588033','2020-03-16 02:33:37','1151420235196588033','0'),('sysusedstatus','1225315294168326146','使用中',1,NULL,'1','2020-02-06 07:08:26','1198252528207134721','2020-02-06 07:08:26','1198252528207134721','0'),('sysusedstatus','1225315329341759490','停用',2,NULL,'2','2020-02-06 07:08:34','1198252528207134721','2020-02-06 07:08:34','1198252528207134721','0'),('sysstatus','1227092656778129410','在线',1,NULL,'online','2020-02-11 04:51:02','sys','2020-07-20 07:50:59','1151420235196588033','0'),('sysstatus','1227092685643329537','下线',2,NULL,'offline','2020-02-11 04:51:09','sys','2020-07-20 07:51:08','1151420235196588033','0'),('sysmid','1227096852734685186','Tengine/2.1.2(Nix1.6.2)',59,NULL,'Tengine','2020-02-11 05:07:43','1151420235196588033','2020-02-11 05:08:18','1151420235196588033','0'),('sysmid','1230331927735615489','Redis 3.0.3',82,NULL,'Redis','2020-02-20 03:22:45','1151420235196588033','2020-02-20 03:22:45','1151420235196588033','0'),('sysdbdtl','1230334597657575425','PostgreSQL 11.2',90,NULL,'PostgreSQL','2020-02-20 03:33:21','1151420235196588033','2020-02-20 03:33:32','1151420235196588033','0'),('sysmid','1230364418676670466','Tomcat 8.5.50.0',36,NULL,'Tomcat','2020-02-20 05:31:51','1151420235196588033','2020-02-20 05:31:51','1151420235196588033','0'),('sysmid','1230385307011039234','Nginx 1.8.1',55,NULL,'Nginx','2020-02-20 06:54:51','1151420235196588033','2020-02-20 06:54:51','1151420235196588033','0'),('sysmid','1231741639995686913','Tomcat 8.5.51.0',37,NULL,'Tomcat','2020-02-24 00:44:26','1151420235196588033','2020-02-24 00:44:26','1151420235196588033','0'),('sysmid','1232467155174293506','Keepalived v1.3.5',92,NULL,'Keepalived','2020-02-26 00:47:23','1151420235196588033','2020-02-26 00:47:23','1151420235196588033','0'),('nodebak','1233197579810897922','无',0,NULL,'none','2020-02-28 01:09:49','1151420235196588033','2020-02-28 01:49:57','1151420235196588033','0'),('nodebak','1233197665454391298','整机备份(NBU)',1,'NBU方式备份Vmware虚机','NBU','2020-02-28 01:10:10','1151420235196588033','2020-02-28 01:50:29','1151420235196588033','0'),('nodebak','1233197742264680450','系统内部_DB(NBU)',1,'NBU代理运行在虚机内部，备份数据等相关','NBU','2020-02-28 01:10:28','1151420235196588033','2020-02-28 01:50:37','1151420235196588033','0'),('nodebak','1233198043730280449','系统内部_APP(NBU)',2,NULL,'NBU','2020-02-28 01:11:40','1151420235196588033','2020-02-28 01:50:45','1151420235196588033','0'),('nodebak','1233198133547106306','系统内部_FILE(NBU)',3,NULL,'NBU','2020-02-28 01:12:01','1151420235196588033','2020-02-28 01:50:49','1151420235196588033','0'),('sysdb','1235422397109231618','Redis',8,NULL,'8','2020-03-05 04:30:27','1151420235196588033','2020-03-05 04:31:40','1151420235196588033','0'),('sysdbdtl','1235422780024020994','Redis 3.0.3',100,NULL,'Redis','2020-03-05 04:31:58','1151420235196588033','2020-03-05 04:31:58','1151420235196588033','0'),('sysdbdtl','1235422915751698433','Redis 5.0.5',102,NULL,'Redis','2020-03-05 04:32:31','1151420235196588033','2020-03-05 04:32:31','1151420235196588033','0'),('sysinfodev','1235781790392651777','外包',1,'1','1','2020-03-06 04:18:33','sys','2020-03-06 04:18:33','sys','0'),('sysinfodev','1235781865462304770','自主',2,NULL,'2','2020-03-06 04:18:51','sys','2020-03-06 04:19:58','sys','0'),('sysinfodev','1235781928326533121','未知',3,NULL,'3','2020-03-06 04:19:06','sys','2020-03-06 04:19:06','sys','0'),('sysinfograde','1235781977349558274','未定评级',1,NULL,'1','2020-03-06 04:19:18','sys','2020-03-06 04:19:18','sys','0'),('sysinfograde','1235782000091074562','一类',1,NULL,'1','2020-03-06 04:19:23','sys','2020-03-06 04:19:23','sys','0'),('sysinfograde','1235782022199250946','二类',1,NULL,'1','2020-03-06 04:19:28','sys','2020-03-06 04:19:28','sys','0'),('sysinfograde','1235782044835909634','三类',3,NULL,'3','2020-03-06 04:19:34','sys','2020-03-06 04:19:34','sys','0'),('sysinfoops','1235782214281596929','自主',1,NULL,'1','2020-03-06 04:20:14','sys','2020-03-06 04:20:14','sys','0'),('sysinfoops','1235782283961569282','托管(山东城商行联盟)',2,NULL,'2','2020-03-06 04:20:31','sys','2020-03-06 04:20:31','sys','0'),('sysinfoops','1235782323031511042','托管（上海城银清）',3,NULL,'3','2020-03-06 04:20:40','sys','2020-03-06 04:20:40','sys','0'),('sysinfoops','1235782360121741314','未知',4,NULL,'4','2020-03-06 04:20:49','sys','2020-03-06 04:21:06','sys','0'),('sysinfotype','1235782465176473602','辅项目',3,NULL,'1','2020-03-06 04:21:14','sys','2020-03-06 04:21:29','sys','0'),('sysinfotype','1235782494196862978','主项目',1,NULL,'1','2020-03-06 04:21:21','sys','2020-03-06 04:21:21','sys','0'),('sysdbdtl','1239346139338211330','PostgreSQL 9.2.7',91,NULL,'PostgreSQL','2020-03-16 00:22:00','1151420235196588033','2020-03-16 00:22:00','1151420235196588033','0'),('sysmid','1239379156794441729','Nginx 1.16.0',54,NULL,'Nginx','2020-03-16 02:33:12','1151420235196588033','2020-03-16 02:33:12','1151420235196588033','0'),('sysdbdtl','1239454479884058626','Mysql5.1.71',19,NULL,'Mysql','2020-03-16 07:32:31','1151420235196588033','2020-03-16 07:32:31','1151420235196588033','0'),('sysmid','1240456465878188034','Tomcat 9.0.31',38,NULL,'Tomcat','2020-03-19 01:54:03','1151420235196588033','2020-03-19 01:54:03','1151420235196588033','0'),('sysmid','1242992215614128129','Spring-boot',200,NULL,'Spring-boot','2020-03-26 01:50:13','1151420235196588033','2020-03-26 01:51:19','1151420235196588033','0'),('zcsource','1252422763608596481','自购',1,NULL,'1','2020-04-21 02:23:50','1151420235196588033','2020-04-21 02:23:50','1151420235196588033','0'),('zcsource','1252422816242917377','赠送',2,NULL,'2','2020-04-21 02:24:03','1151420235196588033','2020-04-21 02:24:03','1151420235196588033','0'),('zcwbsupper','1252422861805641729','第三方',2,NULL,'2','2020-04-21 02:24:14','1151420235196588033','2020-04-21 02:24:14','1151420235196588033','0'),('zcwbsupper','1252422883783794690','原厂',2,NULL,'2','2020-04-21 02:24:19','1151420235196588033','2020-04-21 02:24:19','1151420235196588033','0'),('zcsupper','1252454262470893569','华为',1,NULL,'1','2020-04-21 04:29:00','1151420235196588033','2020-04-21 04:29:00','1151420235196588033','0'),('zcsupper','1252454289620623361','IBM',2,NULL,'2','2020-04-21 04:29:07','1151420235196588033','2020-05-07 00:44:13','1151420235196588033','0'),('devbrand','1252460253224656897','Veritas',111,NULL,'11','2020-04-21 04:52:49','1151420235196588033','2020-04-21 04:52:49','1151420235196588033','0'),('zcsupper','1252460288611999746','Veritas',111,NULL,'111','2020-04-21 04:52:57','1151420235196588033','2020-05-07 00:44:20','1151420235196588033','0'),('zcsupper','1254632864641753090','惠普',1,NULL,'1','2020-04-27 04:46:00','1198252456912355330','2020-04-27 04:46:00','1198252456912355330','0'),('zcsupper','1254632908136685569','浪潮',1,NULL,'1','2020-04-27 04:46:10','1198252456912355330','2020-05-07 00:43:40','1151420235196588033','0'),('zcsupper','1254632937891078146','联想',1,NULL,'1','2020-04-27 04:46:17','1198252456912355330','2020-05-07 00:43:43','1151420235196588033','0'),('zcsupper','1254632975174246402','绿盟',1,NULL,'1','2020-04-27 04:46:26','1198252456912355330','2020-05-07 00:43:49','1151420235196588033','0'),('zcsupper','1254633000004526081','安恒',1,NULL,'1','2020-04-27 04:46:32','1198252456912355330','2020-05-07 00:43:52','1151420235196588033','0'),('zcsupper','1254633023635234817','江南科友',1,NULL,'1','2020-04-27 04:46:37','1198252456912355330','2020-05-07 00:43:56','1151420235196588033','0'),('zcsupper','1254633058653478914','江南天安',1,NULL,'1','2020-04-27 04:46:46','1198252456912355330','2020-04-27 04:46:46','1198252456912355330','0'),('zcsupper','1254633105759707137','吉大正元',1,NULL,'1','2020-04-27 04:46:57','1198252456912355330','2020-04-27 04:46:57','1198252456912355330','0'),('zcsupper','1254633128559943681','安达通',1,NULL,'1','2020-04-27 04:47:02','1198252456912355330','2020-04-27 04:47:02','1198252456912355330','0'),('zcsupper','1254633155764199425','江南所',1,NULL,'1','2020-04-27 04:47:09','1198252456912355330','2020-04-27 04:47:09','1198252456912355330','0'),('zcsupper','1254633176999960578','NPC',1,NULL,'1','2020-04-27 04:47:14','1198252456912355330','2020-05-07 00:44:00','1151420235196588033','0'),('zcsupper','1254633204078387201','D-Link',1,NULL,'1','2020-04-27 04:47:20','1198252456912355330','2020-05-07 00:44:04','1151420235196588033','0'),('zcsupper','1254633233971191809','融天科技',1,NULL,'1','2020-04-27 04:47:28','1198252456912355330','2020-04-27 04:47:28','1198252456912355330','0'),('zcsupper','1254633253835415553','戴尔',1,NULL,'1','2020-04-27 04:47:32','1198252456912355330','2020-04-27 04:47:32','1198252456912355330','0'),('zcsupper','1254633303483392001','深信服',1,NULL,'1','2020-04-27 04:47:44','1198252456912355330','2020-04-27 04:47:44','1198252456912355330','0'),('zcsupper','1254633350950330370','天融信',1,NULL,'1','2020-04-27 04:47:55','1198252456912355330','2020-04-27 04:47:55','1198252456912355330','0'),('zcsupper','1254633386568359937','华三',1,NULL,'1','2020-04-27 04:48:04','1198252456912355330','2020-04-27 04:48:04','1198252456912355330','0'),('zcsupper','1254633408018030594','思科',1,NULL,'1','2020-04-27 04:48:09','1198252456912355330','2020-04-27 04:48:09','1198252456912355330','0'),('zcsupper','1254633445661908993','其他',1,NULL,'1','2020-04-27 04:48:18','1198252456912355330','2020-04-27 04:48:18','1198252456912355330','0'),('zcsupper','1254633475680542721','明御',1,NULL,'1','2020-04-27 04:48:25','1198252456912355330','2020-04-27 04:48:25','1198252456912355330','0'),('zcsupper','1254633499852316674','航信',1,NULL,'1','2020-04-27 04:48:31','1198252456912355330','2020-05-07 00:44:09','1151420235196588033','0'),('zcsupper','1254633521822081026','博科',1,NULL,'1','2020-04-27 04:48:36','1198252456912355330','2020-04-27 04:48:36','1198252456912355330','0'),('zcsupper','1254633542164455425','大华',1,NULL,'1','2020-04-27 04:48:41','1198252456912355330','2020-04-27 04:48:41','1198252456912355330','0'),('zcsupper','1254633565547700226','NetAPP',1,NULL,'1','2020-04-27 04:48:47','1198252456912355330','2020-04-27 04:48:47','1198252456912355330','0'),('zcsupper','1254633590000492546','HDS',1,NULL,'1','2020-04-27 04:48:52','1198252456912355330','2020-04-27 04:48:52','1198252456912355330','0'),('zcwbsupper','1257856538576424961','无',1,NULL,'1','2020-05-06 02:15:43','1151420235196588033','2020-05-07 00:44:37','1151420235196588033','0'),('zcwbsupper','1257862637077663746','在保',11,NULL,'11','2020-05-06 02:39:57','1151420235196588033','2020-05-06 02:39:57','1151420235196588033','0'),('zcsupper','1257863977950515202','Raritan',111,NULL,'111','2020-05-06 02:45:17','1151420235196588033','2020-05-07 00:44:24','1151420235196588033','0'),('zcsupper','1257864173774180354','信安世纪',11,NULL,'11','2020-05-06 02:46:04','1151420235196588033','2020-05-07 00:44:16','1151420235196588033','0'),('zcsource','1258197312937676802','借用',3,NULL,'3','2020-05-07 00:49:50','1151420235196588033','2020-05-07 00:49:50','1151420235196588033','0'),('zcsource','1258197448195592194','自建',12,NULL,'12','2020-05-07 00:50:22','1151420235196588033','2020-05-07 00:50:22','1151420235196588033','0'),('zcsource','1258197511143706626','租入',10,NULL,'10','2020-05-07 00:50:37','1151420235196588033','2020-05-07 00:50:37','1151420235196588033','0'),('zcwbsupper','1258197887376969729','未知',16,NULL,'16','2020-05-07 00:52:07','1151420235196588033','2020-05-07 00:52:07','1151420235196588033','0'),('syslevel','1258197945119952898','三级',3,NULL,'3','2020-05-07 00:52:21','1151420235196588033','2020-05-07 00:52:21','1151420235196588033','0'),('sysmonitor','1258198338122043394','未知',5,NULL,'5','2020-05-07 00:53:55','1151420235196588033','2020-05-07 00:53:55','1151420235196588033','0'),('syspwdstrategy','1258198480090845186','未知',10,NULL,'10','2020-05-07 00:54:29','1151420235196588033','2020-05-07 00:54:29','1151420235196588033','0'),('sysosdtl','1259699716958998530','NaN',1000,NULL,'NaN','2020-05-11 04:19:51','1151420235196588033','2020-05-11 04:20:39','1151420235196588033','0'),('sysos','1259699987336417282','NaN',10000,NULL,'NaN','2020-05-11 04:20:56','1151420235196588033','2020-05-11 04:20:56','1151420235196588033','0'),('sysloc','1260037438781280258','VM_[生产]',1,'1','1','2020-05-12 02:41:50','1151420235196588033','2020-05-23 01:07:16','1151420235196588033','0'),('devenv','1260072746570764290','无',111,NULL,'111','2020-05-12 05:02:08','1151420235196588033','2020-05-12 05:02:08','1151420235196588033','0'),('warehouse','1264790986626695169','仓库1',1,NULL,'1','2020-05-25 05:30:45','1151420235196588033','2020-05-25 05:30:45','1151420235196588033','0'),('warehouse','1264791012547493890','仓库2',2,NULL,'2','2020-05-25 05:30:51','1151420235196588033','2020-05-25 05:30:51','1151420235196588033','0'),('warehouse','1264791046244532226','仓库3',3,NULL,'3','2020-05-25 05:30:59','1151420235196588033','2020-05-25 05:30:59','1151420235196588033','0'),('zcusefullife','1290416493251977218','1年',10,NULL,'365','2020-08-03 22:37:21','1151420235196588033','2020-08-03 22:38:05','1151420235196588033','0'),('zcusefullife','1290416535819968513','2年',20,NULL,'730','2020-08-03 22:37:32','1151420235196588033','2020-08-03 22:38:14','1151420235196588033','0'),('zcusefullife','1290416760894709761','3年',30,NULL,'365','2020-08-03 22:38:25','1151420235196588033','2020-08-03 22:38:25','1151420235196588033','0'),('zcusefullife','1290416806058975233','5年',50,NULL,'1825','2020-08-03 22:38:36','1151420235196588033','2020-08-03 22:39:13','1151420235196588033','0'),('zcusefullife','1290416874908475393','10年',100,NULL,'3650','2020-08-03 22:38:52','1151420235196588033','2020-08-03 22:38:59','1151420235196588033','0'),('devrecycle','allocation','调拨中',12,NULL,'allocation','2020-04-21 04:20:29','1151420235196588033','2020-04-21 04:20:29','1151420235196588033','0'),('assettype','at_unknow','其他类型',2,'2','2','2019-10-30 10:03:14','sys','2019-10-31 13:04:39','1151420235196588033','0'),('devrecycle','borrow','借用',11,NULL,'borrow','2020-04-21 04:20:10','1151420235196588033','2020-04-21 04:20:10','1151420235196588033','0'),('devbrand','dell','戴尔',3,NULL,'3','2019-10-19 13:09:40','sys','2019-10-20 17:44:49','sys','0'),('devenv','devtest','开发测试',2,NULL,'2','2019-10-19 12:56:53','sys','2019-10-20 06:59:07','sys','0'),('devdotequipment','dq_unknow','其他',100,NULL,'pointdev','2019-10-30 10:05:24','sys','2019-11-18 01:01:53','1151420235196588033','0'),('assettype','edev','电子设备',1,NULL,'1','2019-10-30 10:03:07','sys','2019-10-30 10:03:07','sys','0'),('devenv','env_unknow','其他',100,NULL,'100','2019-10-30 10:04:40','sys','2019-10-30 10:05:38','sys','0'),('devrack','G01','G01',1,NULL,'1',NULL,NULL,'2020-06-22 07:38:57','1151420235196588033','0'),('devrack','G04','G04',1,NULL,'1','2019-10-19 12:57:01','sys','2019-10-19 12:57:01','sys','0'),('devrack','G05','G05',1,'1','1','2019-10-21 10:52:20','sys','2020-06-22 07:38:52','1151420235196588033','0'),('devrack','G06','G06',2,NULL,'1','2019-10-19 12:57:08','sys','2020-06-22 07:39:17','1151420235196588033','0'),('devrack','G07','G07',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','G08','G08',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','G09','G09',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','G10','G10',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','G12','G12',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H02','H02',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H03','H03',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H04','H04',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H05','H05',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H06','H06',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H07','H07',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H08','H08',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H09','H09',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H10','H10',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','H12','H12',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devbrand','hp','惠普',1,NULL,'1','2019-10-20 17:44:33','sys','2019-10-20 17:44:33','sys','0'),('devbrand','hs','华三',7,NULL,'7','2019-10-19 13:10:12','sys','2019-10-19 13:10:12','sys','0'),('devbrand','huawei','华为',1,NULL,'1','2019-10-19 12:56:03','sys','2019-10-19 13:08:44','sys','0'),('devbrand','ibm','IBM',2,NULL,'2','2019-10-19 12:55:55','sys','2019-10-19 13:01:56','sys','0'),('devrecycle','idle','闲置',1,NULL,'idle','2019-11-19 01:28:27','sys','2020-05-26 03:41:26','1151420235196588033','0'),('devbrand','inspur','浪潮',1,NULL,'1','2019-10-20 17:45:00','sys','2019-10-20 17:45:00','sys','0'),('devrecycle','inuse','在用',70,NULL,'inuse','2019-10-19 12:57:16','sys','2020-04-21 04:18:59','1151420235196588033','0'),('devwb','invalid','脱保',2,NULL,'2','2019-10-19 12:59:00','sys','2019-10-30 21:24:21','sys','0'),('devrack','J01','J01',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','K02','K02',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','K03','K03',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','K05','K05',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','K06','K06',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devrack','K07','K07',1,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('devbrand','lenovo','联想',1,NULL,'1','2019-10-20 17:48:38','sys','2019-10-20 17:48:45','sys','0'),('devdc','loc_unknow','其他',100,NULL,'100','2019-10-30 10:04:52','sys','2019-10-30 10:05:10','sys','0'),('devrisk','none','未评级',0,NULL,'0','2019-10-20 07:00:00','sys','2020-05-07 00:42:53','1151420235196588033','0'),('devdc','officebuilding','总行大楼',1,'1','1','2019-10-20 17:45:55','sys','2019-11-02 15:30:24','1151420235196588033','0'),('devrisk','one','一级',1,NULL,'1','2019-10-19 12:57:51','sys','2019-10-19 12:57:51','sys','0'),('devbrand','other','其他',10,NULL,'10','2019-10-19 13:10:37','sys','2019-10-19 13:10:37','sys','0'),('outercontact','outercontact','外部联系人',1,NULL,'1','2019-11-09 16:17:52','1151420235196588033','2019-11-09 16:17:52','1151420235196588033','0'),('devsupper','outlets','神舟',2,NULL,'2','2019-10-19 12:58:40','sys','2019-10-19 12:58:40','sys','0'),('devenv','prod','生产',2,NULL,'2','2019-10-19 12:56:41','sys','2019-10-20 06:59:12','sys','0'),('devrecycle','repair','维修中',86,NULL,'repair','2019-10-30 10:14:05','sys','2020-04-21 04:19:36','1151420235196588033','0'),('devrecycle','scrap','报废',100,NULL,'scrap','2019-10-20 17:44:21','sys','2020-04-21 04:19:53','1151420235196588033','0'),('devdc','site','营业网点',2,'2','2','2019-10-20 17:46:11','sys','2019-10-20 17:46:29','sys','0'),('devbrand','sk','思科',8,NULL,'8','2019-10-19 13:10:26','sys','2019-10-19 13:10:26','sys','0'),('assettype','software','软件资产',100,'','1','2019-10-31 13:04:26','1151420235196588033','2019-11-09 17:42:56','1151420235196588033','0'),('devrecycle','stopuse','停用',85,NULL,'stopuse','2019-10-19 12:57:37','sys','2020-04-21 04:19:21','1151420235196588033','0'),('devbrand','sxf','深信服',5,NULL,'5','2019-10-19 13:09:50','sys','2019-10-19 13:09:50','sys','0'),('devenv','test','测试',2,NULL,'2','2019-10-30 10:04:03','sys','2019-10-30 10:04:03','sys','0'),('devrisk','three','三级',3,NULL,'3','2019-10-20 06:59:34','sys','2019-10-20 06:59:34','sys','0'),('devbrand','trx','天融信',5,NULL,'5','2019-10-19 13:09:58','sys','2019-10-19 13:09:58','sys','0'),('devrisk','two','二级',2,NULL,'2','2019-10-19 12:58:01','sys','2019-10-20 06:59:26','sys','0'),('devwb','unknow','未知',4,NULL,'4','2019-11-13 21:40:27','1151420235196588033','2020-05-07 00:42:40','1151420235196588033','0'),('devwb','valid','在保',1,NULL,'1','2019-10-19 12:58:50','sys','2019-10-19 12:58:50','sys','0'),('devdc','warehouse','华商-机房',1,NULL,'1','2019-10-21 10:46:57','sys','2019-11-15 01:07:54','1151420235196588033','0'),('zcwbcomoute','wbcompute_0','手动',1,NULL,'1','2019-11-13 21:40:05','1151420235196588033','2019-11-13 21:40:05','1151420235196588033','0'),('zcwbcomoute','wbcompute_1','自动',1,NULL,'1','2019-11-13 21:39:59','1151420235196588033','2019-11-13 21:39:59','1151420235196588033','0'),('devwb','wb_unused','不保',3,NULL,'3','2019-10-30 06:37:41','sys','2020-05-07 00:49:07','1151420235196588033','0'),('devservertype','x86','X86',1,NULL,'server','2019-10-19 12:58:16','sys','2019-11-18 01:02:28','1151420235196588033','0'),('devservertype','xj','小机',2,NULL,'server','2019-10-19 12:58:23','sys','2019-11-18 01:02:31','1151420235196588033','0'),('devdc','xyl','数据中心DC-华商',1,NULL,'1','2019-10-19 12:56:24','sys','2020-05-07 00:47:01','1151420235196588033','0'),('devdc','yz','数据中心DC-鄞州',2,NULL,'2','2019-10-19 12:56:32','sys','2020-05-07 00:47:09','1151420235196588033','0'),('devenv','zprod','准生产',2,NULL,'2','2019-10-30 10:04:18','sys','2019-10-30 10:04:18','sys','0');
/*!40000 ALTER TABLE `sys_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_feedback`
--

DROP TABLE IF EXISTS `sys_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_feedback` (
  `id` varchar(50) NOT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `ct` varchar(500) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_feedback`
--

LOCK TABLES `sys_feedback` WRITE;
/*!40000 ALTER TABLE `sys_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_file_conf`
--

DROP TABLE IF EXISTS `sys_file_conf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_file_conf` (
  `id` varchar(50) NOT NULL,
  `name` text COMMENT '名称',
  `path` text COMMENT '系统路径',
  `type` varchar(50) DEFAULT NULL,
  `keepname` varchar(1) DEFAULT NULL COMMENT '保留文件名称',
  `limit_str` varchar(500) DEFAULT NULL,
  `is_used` char(1) DEFAULT NULL COMMENT '是否使用中',
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_file_conf`
--

LOCK TABLES `sys_file_conf` WRITE;
/*!40000 ALTER TABLE `sys_file_conf` DISABLE KEYS */;
INSERT INTO `sys_file_conf` VALUES ('dbbackup','数据库备份','dbbackup','file','0',NULL,'Y',NULL,NULL,NULL,NULL,'0'),('file','文件','file','file','0','','Y',NULL,NULL,NULL,NULL,'0'),('header','头像','header','image','0',NULL,'Y',NULL,NULL,NULL,NULL,'0'),('news','新闻图片','news','image','0',NULL,'Y',NULL,NULL,NULL,NULL,'0'),('prodimgs','业务','prodimgs','image','0',NULL,'Y',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_file_conf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_files`
--

DROP TABLE IF EXISTS `sys_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_files` (
  `id` varchar(50) NOT NULL,
  `path` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `bus` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `mark` text COMMENT '备注',
  `filename` text COMMENT '文件名',
  `filename_o` varchar(500) DEFAULT NULL COMMENT '原文件名',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_files`
--

LOCK TABLES `sys_files` WRITE;
/*!40000 ALTER TABLE `sys_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_form`
--

DROP TABLE IF EXISTS `sys_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_form` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `owner` varchar(50) DEFAULT NULL,
  `ct` text COMMENT '内容',
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_form`
--

LOCK TABLES `sys_form` WRITE;
/*!40000 ALTER TABLE `sys_form` DISABLE KEYS */;
INSERT INTO `sys_form` VALUES ('1248049296763314177','资产领用','资产领用',NULL,'133','%7B%22list%22:%5B%7B%22type%22:%22text%22,%22label%22:%22%E8%B5%84%E4%BA%A7%E9%A2%86%E7%94%A8%E7%94%B3%E8%AF%B7%22,%22options%22:%7B%22textAlign%22:%22center%22,%22hidden%22:false,%22showRequiredMark%22:false%7D,%22key%22:%22text_1592981424358%22%7D,%7B%22type%22:%22input%22,%22label%22:%22%E6%A0%87%E9%A2%98%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22disabled%22:false%7D,%22model%22:%22dtitle%22,%22key%22:%22input_1586396052196%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22input%22,%22label%22:%22%E9%A2%86%E7%94%A8%E4%BA%BA%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22disabled%22:false%7D,%22model%22:%22duser%22,%22key%22:%22input_1586396050913%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22textarea%22,%22label%22:%22%E5%A4%87%E6%B3%A8%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22dmark%22,%22key%22:%22textarea_1586396020229%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D%5D,%22config%22:%7B%22layout%22:%22horizontal%22,%22labelCol%22:%7B%22span%22:4%7D,%22wrapperCol%22:%7B%22span%22:18%7D,%22hideRequiredMark%22:false,%22customStyle%22:%22%22%7D%7D','0','1151420235196588033','2020-04-09 00:45:15','1151420235196588033','2020-06-26 04:10:38'),('1249520630522449921','资产报修','资产报修',NULL,'133','%7B%22list%22:%5B%7B%22type%22:%22text%22,%22label%22:%22%E8%B5%84%E4%BA%A7%E6%8A%A5%E4%BF%AE%22,%22options%22:%7B%22textAlign%22:%22center%22,%22hidden%22:false,%22showRequiredMark%22:false%7D,%22key%22:%22text_1593144689843%22%7D,%7B%22type%22:%22input%22,%22label%22:%22%E7%94%B3%E8%AF%B7%E4%BA%BA%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22hidden%22:false,%22disabled%22:false%7D,%22model%22:%22input_1593144708403%22,%22key%22:%22input_1593144708403%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22textarea%22,%22label%22:%22%E7%94%B3%E8%AF%B7%E5%8E%9F%E5%9B%A0%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22hidden%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22textarea_1593144721221%22,%22key%22:%22textarea_1593144721221%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22input%22,%22label%22:%22%E5%A4%87%E6%B3%A8%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22hidden%22:false,%22disabled%22:false%7D,%22model%22:%22input_1593144738598%22,%22key%22:%22input_1593144738598%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D%5D,%22config%22:%7B%22layout%22:%22horizontal%22,%22labelCol%22:%7B%22span%22:4%7D,%22wrapperCol%22:%7B%22span%22:18%7D,%22hideRequiredMark%22:false,%22customStyle%22:%22%22%7D%7D','0','1151420235196588033','2020-04-13 02:11:48','1151420235196588033','2020-06-26 04:12:27'),('1276366512902430722','资产借用','资产借用',NULL,'133','%7B%22list%22:%5B%7B%22type%22:%22text%22,%22label%22:%22%E8%B5%84%E4%BA%A7%E5%80%9F%E7%94%A8%E7%94%B3%E8%AF%B7%22,%22options%22:%7B%22textAlign%22:%22center%22,%22hidden%22:false,%22showRequiredMark%22:false%7D,%22key%22:%22text_1593144775199%22%7D,%7B%22type%22:%22input%22,%22label%22:%22%E6%A0%87%E9%A2%98%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22hidden%22:false,%22disabled%22:false%7D,%22model%22:%22input_1593144772631%22,%22key%22:%22input_1593144772631%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22input%22,%22label%22:%22%E9%A2%86%E7%94%A8%E4%BA%BA%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22hidden%22:false,%22disabled%22:false%7D,%22model%22:%22input_1593144802718%22,%22key%22:%22input_1593144802718%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22textarea%22,%22label%22:%22%E5%A4%87%E6%B3%A8%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22hidden%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22textarea_1593144797397%22,%22key%22:%22textarea_1593144797397%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D%5D,%22config%22:%7B%22layout%22:%22horizontal%22,%22labelCol%22:%7B%22span%22:4%7D,%22wrapperCol%22:%7B%22span%22:18%7D,%22hideRequiredMark%22:false,%22customStyle%22:%22%22%7D%7D','0','1151420235196588033','2020-06-26 04:07:45','1151420235196588033','2020-06-26 04:13:48'),('1276366544498122753','资产转移','资产转移',NULL,'133','%7B%22list%22:%5B%7B%22type%22:%22text%22,%22label%22:%22%E8%B5%84%E4%BA%A7%E8%BD%AC%E7%A7%BB%E7%94%B3%E8%AF%B7%22,%22options%22:%7B%22textAlign%22:%22center%22,%22hidden%22:false,%22showRequiredMark%22:false%7D,%22key%22:%22text_1593144841711%22%7D,%7B%22type%22:%22input%22,%22label%22:%22%E6%A0%87%E9%A2%98%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22hidden%22:false,%22disabled%22:false%7D,%22model%22:%22input_1593144840276%22,%22key%22:%22input_1593144840276%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22input%22,%22label%22:%22%E8%BD%AC%E7%A7%BB%E4%BA%BA%22,%22options%22:%7B%22type%22:%22text%22,%22width%22:%22100%25%22,%22defaultValue%22:%22%22,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22,%22clearable%22:false,%22maxLength%22:null,%22hidden%22:false,%22disabled%22:false%7D,%22model%22:%22input_1593144839227%22,%22key%22:%22input_1593144839227%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22textarea%22,%22label%22:%22%E5%A4%87%E6%B3%A8%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22hidden%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22textarea_1593144837422%22,%22key%22:%22textarea_1593144837422%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D%5D,%22config%22:%7B%22layout%22:%22horizontal%22,%22labelCol%22:%7B%22span%22:4%7D,%22wrapperCol%22:%7B%22span%22:18%7D,%22hideRequiredMark%22:false,%22customStyle%22:%22%22%7D%7D','0','1151420235196588033','2020-06-26 04:07:53','1151420235196588033','2020-06-26 04:14:27'),('1286622045610815489','FTP账号管理表格','生产FTP',NULL,'6','%7B%22list%22:%5B%7B%22type%22:%22textarea%22,%22label%22:%22%E6%96%87%E6%9C%AC%E6%A1%86%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22hidden%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22textarea_1595591089743%22,%22key%22:%22textarea_1595591089743%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22textarea%22,%22label%22:%22%E6%96%87%E6%9C%AC%E6%A1%86%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22hidden%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22textarea_1595591091026%22,%22key%22:%22textarea_1595591091026%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22textarea%22,%22label%22:%22%E6%96%87%E6%9C%AC%E6%A1%86%22,%22options%22:%7B%22width%22:%22100%25%22,%22minRows%22:4,%22maxRows%22:6,%22maxLength%22:null,%22defaultValue%22:%22%22,%22clearable%22:false,%22hidden%22:false,%22disabled%22:false,%22placeholder%22:%22%E8%AF%B7%E8%BE%93%E5%85%A5%22%7D,%22model%22:%22textarea_1595591092197%22,%22key%22:%22textarea_1595591092197%22,%22rules%22:%5B%7B%22required%22:false,%22message%22:%22%E5%BF%85%E5%A1%AB%E9%A1%B9%22%7D%5D%7D,%7B%22type%22:%22divider%22,%22label%22:%22%E5%88%86%E5%89%B2%E7%BA%BF%22,%22icon%22:%22icon-fengexian%22,%22options%22:%7B%22orientation%22:%22left%22%7D,%22key%22:%22divider_1595591103058%22%7D,%7B%22type%22:%22table%22,%22label%22:%22%E8%A1%A8%E6%A0%BC%E5%B8%83%E5%B1%80%22,%22icon%22:%22icon-biaoge%22,%22trs%22:%5B%7B%22tds%22:%5B%7B%22colspan%22:1,%22rowspan%22:1,%22list%22:%5B%5D%7D,%7B%22colspan%22:1,%22rowspan%22:1,%22list%22:%5B%5D%7D%5D%7D,%7B%22tds%22:%5B%7B%22colspan%22:1,%22rowspan%22:1,%22list%22:%5B%5D%7D,%7B%22colspan%22:1,%22rowspan%22:1,%22list%22:%5B%5D%7D%5D%7D%5D,%22options%22:%7B%22width%22:%22100%25%22,%22bordered%22:true,%22bright%22:false,%22small%22:true,%22customStyle%22:%22%22%7D,%22key%22:%22table_1595591104581%22%7D,%7B%22type%22:%22grid%22,%22label%22:%22%E6%A0%85%E6%A0%BC%E5%B8%83%E5%B1%80%22,%22icon%22:%22icon-zhage%22,%22columns%22:%5B%7B%22span%22:12,%22list%22:%5B%5D%7D,%7B%22span%22:12,%22list%22:%5B%5D%7D%5D,%22options%22:%7B%22gutter%22:0%7D,%22key%22:%22grid_1595591105465%22%7D%5D,%22config%22:%7B%22layout%22:%22horizontal%22,%22labelCol%22:%7B%22span%22:4%7D,%22wrapperCol%22:%7B%22span%22:18%7D,%22hideRequiredMark%22:false,%22customStyle%22:%22%22%7D%7D','0','1151420235196588033','2020-07-24 11:19:35','1151420235196588033','2020-07-24 11:45:09');
/*!40000 ALTER TABLE `sys_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_help`
--

DROP TABLE IF EXISTS `sys_help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_help` (
  `help_id` varchar(50) NOT NULL,
  `help_keyword` varchar(50) DEFAULT NULL,
  `help_title` varchar(50) DEFAULT NULL,
  `help_context` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`help_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_help`
--

LOCK TABLES `sys_help` WRITE;
/*!40000 ALTER TABLE `sys_help` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_help` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job` (
  `seq` varchar(100) NOT NULL,
  `node` text,
  `jobname` varchar(100) DEFAULT NULL,
  `jobgroup` varchar(100) DEFAULT NULL,
  `jobclassname` varchar(100) DEFAULT NULL,
  `jobcron` text,
  `jobtype` varchar(100) DEFAULT NULL,
  `jobenable` varchar(10) DEFAULT NULL,
  `mark` text,
  `recdate` datetime DEFAULT NULL,
  `last_run` datetime DEFAULT NULL,
  `inited` char(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES ('1','local','API','systemgroup','com.dt.module.base.job.CollectApiJob','0 0 */1 * * ?','user','false',NULL,NULL,'2020-06-25 16:09:52','Y',NULL,NULL,NULL,NULL,'0'),('2','local','主动刷新缓存','systemgroup','com.dt.module.base.job.CacheRefreshJob','0 */3 * * * ?','user','true',NULL,NULL,'2020-08-06 23:18:00','Y',NULL,NULL,NULL,NULL,'0'),('3','local','数据备份','systemgroup','com.dt.module.base.job.DatabackupJob','0 */50 * * * ?','user','true',NULL,NULL,'2020-08-06 23:00:40','Y',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_dtl`
--

DROP TABLE IF EXISTS `sys_job_dtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job_dtl` (
  `seq` varchar(100) DEFAULT NULL,
  `jobseq` text,
  `jobcontent` text,
  `opertype` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `reclog` text,
  `stime` datetime DEFAULT NULL,
  `etime` datetime DEFAULT NULL,
  KEY `mnjobdtlind1` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_dtl`
--

LOCK TABLES `sys_job_dtl` WRITE;
/*!40000 ALTER TABLE `sys_job_dtl` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_dtl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log_access`
--

DROP TABLE IF EXISTS `sys_log_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log_access` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `rtime` datetime DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` text,
  `postorget` text,
  `remark` text,
  `method_type` varchar(100) DEFAULT NULL,
  `info` text,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log_access`
--

LOCK TABLES `sys_log_access` WRITE;
/*!40000 ALTER TABLE `sys_log_access` DISABLE KEYS */;
INSERT INTO `sys_log_access` VALUES ('e837fcf2-ad59-4b51-8a85-db35c83eadf7','1151420235196588033','0:0:0:0:0:0:0:1','2020-08-06 23:18:01',NULL,'/dt//api/user/login.do','user:admin,pwd:oracle,type:username,basePublic:yes,client:web,',NULL,'POST','登录',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_log_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log_login`
--

DROP TABLE IF EXISTS `sys_log_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log_login` (
  `id` varchar(50) NOT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `rdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log_login`
--

LOCK TABLES `sys_log_login` WRITE;
/*!40000 ALTER TABLE `sys_log_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menus`
--

DROP TABLE IF EXISTS `sys_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menus` (
  `menu_id` varchar(50) NOT NULL COMMENT '菜单',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `mark` text COMMENT '备注',
  `sort` decimal(5,0) DEFAULT NULL COMMENT '排序',
  `org_id` varchar(50) DEFAULT NULL COMMENT '多模式',
  `type` varchar(20) DEFAULT NULL COMMENT 'PC |',
  `used` decimal(10,0) DEFAULT NULL COMMENT '是否有效',
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menus`
--

LOCK TABLES `sys_menus` WRITE;
/*!40000 ALTER TABLE `sys_menus` DISABLE KEYS */;
INSERT INTO `sys_menus` VALUES ('1','系统控制台','1',1,NULL,'PC',1,'0','1151420235196588033',NULL,NULL,'2020-05-07 23:32:53'),('1079008526457073665','资产管理系统',NULL,NULL,NULL,NULL,1,'0','1151420235196588033','sys','2018-12-29 21:37:34','2020-05-26 01:16:49'),('1224134297569464321','运维管理系统',NULL,NULL,NULL,NULL,1,'0','sys','sys','2020-02-03 00:55:35','2020-02-03 00:55:35'),('1286633577483616257','运维监控系统',NULL,NULL,NULL,NULL,1,'0','1151420235196588033','1151420235196588033','2020-07-24 12:05:24','2020-07-24 12:05:39');
/*!40000 ALTER TABLE `sys_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menus_node`
--

DROP TABLE IF EXISTS `sys_menus_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menus_node` (
  `node_id` decimal(5,0) NOT NULL COMMENT '用做module_id',
  `node_name` varchar(100) NOT NULL,
  `parent_id` decimal(5,0) DEFAULT NULL,
  `module_id` varchar(50) DEFAULT NULL COMMENT '不在使用',
  `menu_id` varchar(50) NOT NULL COMMENT '不同功能树',
  `mark` text,
  `logo` varchar(50) DEFAULT NULL,
  `menu_level` decimal(1,0) DEFAULT NULL,
  `org_id` varchar(50) DEFAULT NULL,
  `sort` decimal(5,0) DEFAULT NULL,
  `keyvalue` varchar(100) DEFAULT NULL,
  `route` text,
  `is_action` varchar(20) NOT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT 'menus api',
  `route_name` text,
  `is_g_show` char(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`node_id`,`menu_id`),
  KEY `sys_menus_nodeidx1` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menus_node`
--

LOCK TABLES `sys_menus_node` WRITE;
/*!40000 ALTER TABLE `sys_menus_node` DISABLE KEYS */;
INSERT INTO `sys_menus_node` VALUES (2,'系统管理',0,NULL,'1',NULL,'fa fa-laptop',1,NULL,1,'system','2','Y','dir','系统管理','Y',NULL,NULL,NULL,NULL,'0'),(3,'组织架构',0,NULL,'1',NULL,'fa fa-sitemap',1,NULL,1,'org','3','Y','dir','组织架构','Y',NULL,NULL,NULL,NULL,'0'),(16,'人员查询',3,NULL,'1','按组织树查询员工',NULL,NULL,NULL,1,'employee','3-16','Y','menu','组织架构/人员查询','Y',NULL,NULL,NULL,NULL,'0'),(17,'组织设置',3,NULL,'1',NULL,NULL,NULL,NULL,1,'part','3-17','Y','menu','组织架构/组织设置','Y',NULL,NULL,NULL,NULL,'0'),(22,'字典管理',2,NULL,'1',NULL,NULL,NULL,NULL,1,'dict_setting','2-22','Y','menu','系统管理/字典管理','Y',NULL,NULL,NULL,NULL,'0'),(56,'人员调整',3,NULL,'1','删除,修改',NULL,NULL,NULL,1,'employee_adjust','3-56','Y','menu','组织架构/人员调整','Y',NULL,NULL,NULL,NULL,'0'),(57,'系统参数',2,NULL,'1',NULL,NULL,NULL,NULL,1,'params','2-57','Y','menu','系统管理/系统参数','Y',NULL,NULL,NULL,NULL,'0'),(70,'上传配置',2,NULL,'1',NULL,NULL,NULL,NULL,1,'file_setting','2-70','Y','menu','系统管理/上传配置','Y',NULL,NULL,NULL,NULL,'0'),(76,'类目管理',0,NULL,'1',NULL,'fa fa-table',NULL,NULL,1,'ct','76','Y','dir','类目管理','Y',NULL,NULL,NULL,NULL,'0'),(77,'类目设置',76,NULL,'1',NULL,NULL,NULL,NULL,1,'catesetting','76-77','Y','menu','类目管理/类目设置','Y',NULL,NULL,NULL,NULL,'0'),(89,'个人设置',0,NULL,'1',NULL,'fa fa-user',NULL,NULL,1,'me','89','Y','dir','个人设置','Y',NULL,NULL,NULL,NULL,'0'),(90,'资料修改',89,NULL,'1',NULL,NULL,NULL,NULL,1,'profile_mgr','89-90','Y','menu','个人设置/资料修改','Y',NULL,NULL,NULL,NULL,'0'),(91,'重置密码',89,NULL,'1',NULL,NULL,NULL,NULL,1,'pwdreset','89-91','Y','menu','个人设置/重置密码','Y',NULL,NULL,NULL,NULL,'0'),(96,'日志管理',89,NULL,'1',NULL,NULL,NULL,NULL,1,'accesslog','89-96','Y','menu','个人设置/日志管理','Y',NULL,NULL,NULL,NULL,'0'),(114,'商品管理',0,NULL,'5',NULL,'fa fa-square',NULL,NULL,1,'product','114','Y','dir','商品管理','Y',NULL,NULL,NULL,NULL,'0'),(116,'品牌管理',0,NULL,'5',NULL,'fa fa-ge',NULL,NULL,1,'pinp','116','Y','dir','品牌管理','Y',NULL,NULL,NULL,NULL,'0'),(117,'订单管理',0,NULL,'5',NULL,'fa fa-diamond',NULL,NULL,1,'order','117','Y','dir','订单管理','Y',NULL,NULL,NULL,NULL,'0'),(118,'商品发布',114,NULL,'5',NULL,NULL,NULL,NULL,1,'prod_publish','114-118','Y','menu','商品管理/商品发布','Y',NULL,NULL,NULL,NULL,'0'),(119,'商品查询',114,NULL,'5',NULL,NULL,NULL,NULL,1,'prod_query','114-119','Y','menu','商品管理/商品查询','Y',NULL,NULL,NULL,NULL,'0'),(120,'品牌设置',116,NULL,'5',NULL,NULL,NULL,NULL,1,'pinp_mgr','116-120','Y','menu','品牌管理/品牌设置','Y',NULL,NULL,NULL,NULL,'0'),(121,'订单查询',117,NULL,'5',NULL,NULL,NULL,NULL,1,'order_mgr','117-121','Y','menu','订单管理/订单查询','Y',NULL,NULL,NULL,NULL,'0'),(122,'类目管理',0,NULL,'5',NULL,'fa fa-codepen',NULL,NULL,1,'cat','122','Y','dir','类目管理','Y',NULL,NULL,NULL,NULL,'0'),(123,'前台类目设置',122,NULL,'5',NULL,NULL,NULL,NULL,1,'fcat_mgr','122-123','Y','menu','类目管理/前台类目设置','Y',NULL,NULL,NULL,NULL,'0'),(124,'后台类目设置',122,NULL,'5',NULL,NULL,NULL,NULL,1,'bcat_mgr','122-124','Y','menu','类目管理/后台类目设置','Y',NULL,NULL,NULL,NULL,'0'),(125,'前台类目组设置',122,NULL,'5',NULL,NULL,NULL,NULL,1,'fcat_group','122-125','Y','menu','类目管理/前台类目组设置','Y',NULL,NULL,NULL,NULL,'0'),(129,'用户管理',0,NULL,'1',NULL,'fa fa-users',NULL,NULL,1,'user','129','Y','dir','用户管理','Y',NULL,NULL,NULL,NULL,'0'),(130,'角色管理',0,NULL,'1',NULL,'fa fa-low-vision',NULL,NULL,1,'role','130','Y','dir','角色管理','Y',NULL,NULL,NULL,NULL,'0'),(131,'模块管理',0,NULL,'1',NULL,'fa fa-codepen',NULL,NULL,1,'module','131','Y','dir','模块管理','Y',NULL,NULL,NULL,NULL,'0'),(134,'用户组设置',129,NULL,'1',NULL,NULL,NULL,NULL,1,'user_group','129-134','Y','menu','用户管理/用户组设置','Y',NULL,NULL,NULL,NULL,'0'),(135,'用户新增',129,NULL,'1',NULL,NULL,NULL,NULL,1,'user_add','129-135','Y','menu','用户管理/用户新增','Y',NULL,NULL,NULL,NULL,'0'),(136,'用户设置',129,NULL,'1',NULL,NULL,NULL,NULL,1,'user_setting','129-136','Y','menu','用户管理/用户设置','Y',NULL,NULL,NULL,NULL,'0'),(137,'角色设置',130,NULL,'1',NULL,NULL,NULL,NULL,1,'role_setting','130-137','Y','menu','角色管理/角色设置','Y',NULL,NULL,NULL,NULL,'0'),(138,'角色模块映射',130,NULL,'1',NULL,NULL,NULL,NULL,1,'role_module_map','130-138','Y','menu','角色管理/角色模块映射','Y',NULL,NULL,NULL,NULL,'0'),(139,'模块设置',131,NULL,'1',NULL,NULL,NULL,NULL,1,'module_setting','131-139','Y','menu','模块管理/模块设置','Y',NULL,NULL,NULL,NULL,'0'),(142,'调度管理',0,NULL,'1',NULL,'fa fa-asterisk',NULL,NULL,1,'task','142','Y','dir','调度管理','Y',NULL,NULL,NULL,NULL,'0'),(143,'调度设置',142,NULL,'1','12',NULL,NULL,NULL,1,'task_mgr','142-143','Y','menu','调度管理/调度设置','Y',NULL,NULL,NULL,NULL,'0'),(147,'添加订单',117,NULL,'5',NULL,NULL,NULL,NULL,1,'order_add','117-147','Y','menu','订单管理/添加订单','Y',NULL,NULL,NULL,NULL,'0'),(148,'发货单列表',117,NULL,'5',NULL,NULL,NULL,NULL,1,'order_deliver','117-148','Y','menu','订单管理/发货单列表','Y',NULL,NULL,NULL,NULL,'0'),(149,'退货单列表',117,NULL,'5',NULL,NULL,NULL,NULL,1,'order_cancel','117-149','Y','menu','订单管理/退货单列表','Y',NULL,NULL,NULL,NULL,'0'),(150,'店铺管理',0,NULL,'5',NULL,NULL,NULL,NULL,1,'shop','150','Y','dir','店铺管理','Y',NULL,NULL,NULL,NULL,'0'),(151,'我的店铺',150,NULL,'5',NULL,NULL,NULL,NULL,1,'shop_info','150-151','Y','menu','店铺管理/我的店铺','Y',NULL,NULL,NULL,NULL,'0'),(152,'店铺列表',150,NULL,'5',NULL,NULL,NULL,NULL,1,'shop_list','150-152','Y','menu','店铺管理/店铺列表','Y',NULL,NULL,NULL,NULL,'0'),(153,'城市列表',150,NULL,'5',NULL,NULL,NULL,NULL,1,'shop_citys','150-153','Y','menu','店铺管理/城市列表','Y',NULL,NULL,NULL,NULL,'0'),(154,'通用设置',89,NULL,'1',NULL,NULL,NULL,NULL,1,'common_mgr','89-154','Y','menu','个人设置/通用设置','Y',NULL,NULL,NULL,NULL,'0'),(157,'在线用户',2,NULL,'1',NULL,NULL,NULL,NULL,1,'onlinesession','2-157','Y','menu','系统管理/在线用户','Y',NULL,NULL,NULL,NULL,'0'),(163,'商城管理',0,NULL,'5',NULL,NULL,NULL,NULL,1,'mallmgr','163','Y','dir','商城管理','Y',NULL,NULL,NULL,NULL,'0'),(164,'产品设置',163,NULL,'5',NULL,NULL,NULL,NULL,1,'prodsetting','163-164','Y','menu','商城管理/产品设置','Y',NULL,NULL,NULL,NULL,'0'),(165,'商城设置',163,NULL,'5',NULL,NULL,NULL,NULL,1,'commsetting','163-165','Y','menu','商城管理/商城设置','Y',NULL,NULL,NULL,NULL,'0'),(166,'横幅设置',163,NULL,'5',NULL,NULL,NULL,NULL,1,'banner','163-166','Y','menu','商城管理/横幅设置','Y',NULL,NULL,NULL,NULL,'0'),(167,'公告设置',163,NULL,'5',NULL,NULL,NULL,NULL,1,'notice','163-167','Y','menu','商城管理/公告设置','Y',NULL,NULL,NULL,NULL,'0'),(169,'前台类目映射',122,NULL,'5',NULL,NULL,NULL,NULL,1,'fcat_map','122-169','Y','menu','类目管理/前台类目映射','Y',NULL,NULL,NULL,NULL,'0'),(171,'首页分类',122,NULL,'5','',NULL,NULL,NULL,1,'indexclass','122-171','Y','menu','类目管理/首页分类','Y',NULL,NULL,NULL,NULL,'0'),(173,'节点管理',0,NULL,'6',NULL,NULL,NULL,NULL,1,'hostnode','173','Y','dir','节点管理','Y',NULL,NULL,NULL,NULL,'0'),(174,'节点信息',173,NULL,'6',NULL,NULL,NULL,NULL,1,'hostmgr','173-174','Y','menu','节点管理/节点信息','Y',NULL,NULL,NULL,NULL,'0'),(176,'缓存管理',2,NULL,'1',NULL,NULL,NULL,NULL,1,'cachemgr','2-176','Y','menu','系统管理/缓存管理','Y',NULL,NULL,NULL,NULL,'0'),(178,'度量管理',0,NULL,'6',NULL,NULL,NULL,NULL,1,'metricmgr','178','Y','dir','度量管理','Y',NULL,NULL,NULL,NULL,'0'),(179,'服务管理',0,NULL,'6',NULL,NULL,NULL,NULL,1,'servicemgr','179','Y','dir','服务管理','Y',NULL,NULL,NULL,NULL,'0'),(180,'度量设置',178,NULL,'6',NULL,NULL,NULL,NULL,1,'metricsetting','178-180','Y','menu','度量管理/度量设置','Y',NULL,NULL,NULL,NULL,'0'),(181,'度量模版',178,NULL,'6',NULL,NULL,NULL,NULL,1,'metrictempl','178-181','Y','menu','度量管理/度量模版','Y',NULL,NULL,NULL,NULL,'0'),(182,'服务设置',179,NULL,'6',NULL,NULL,NULL,NULL,1,'servicesetting','179-182','Y','menu','服务管理/服务设置','Y',NULL,NULL,NULL,NULL,'0'),(183,'服务节点',179,NULL,'6',NULL,NULL,NULL,NULL,1,'servicenode','179-183','Y','menu','服务管理/服务节点','Y',NULL,NULL,NULL,NULL,'0'),(185,'度量映射',178,NULL,'6',NULL,NULL,NULL,NULL,1,'metricmapping','178-185','Y','menu','度量管理/度量映射','Y',NULL,NULL,NULL,NULL,'0'),(186,'节点度量',179,NULL,'6',NULL,NULL,NULL,NULL,1,'nodemetric','179-186','Y','menu','服务管理/节点度量','Y',NULL,NULL,NULL,NULL,'0'),(187,'检测管理',0,NULL,'6',NULL,NULL,NULL,NULL,1,'touchmgr','187','Y','dir','检测管理','Y',NULL,NULL,NULL,NULL,'0'),(188,'Web检测',187,NULL,'6',NULL,NULL,NULL,NULL,1,'web','187-188','Y','menu','检测管理/Web检测','Y',NULL,NULL,NULL,NULL,'0'),(189,'微信管理',0,NULL,'3',NULL,NULL,NULL,NULL,1,'wxmgr','189','Y','dir','微信管理','Y',NULL,NULL,NULL,NULL,'0'),(190,'应用设置',189,NULL,'3',NULL,NULL,NULL,NULL,1,'app','189-190','Y','menu','微信管理/应用设置','Y',NULL,NULL,NULL,NULL,'0'),(191,'消息管理',0,NULL,'3',NULL,NULL,NULL,NULL,1,'wxmsg','191','Y','dir','消息管理','Y',NULL,NULL,NULL,NULL,'0'),(192,'消息设置',191,NULL,'3',NULL,NULL,NULL,NULL,1,'setting','191-192','Y','menu','消息管理/消息设置','Y',NULL,NULL,NULL,NULL,'0'),(193,'图文消息',191,NULL,'3',NULL,NULL,NULL,NULL,1,'imgtext','191-193','Y','menu','消息管理/图文消息','Y',NULL,NULL,NULL,NULL,'0'),(195,'图片素材',191,NULL,'3',NULL,NULL,NULL,NULL,1,'msgimg','191-195','Y','menu','消息管理/图片素材','Y',NULL,NULL,NULL,NULL,'0'),(197,'连接池',2,NULL,'1',NULL,NULL,NULL,NULL,1,'druid','2-197','Y','menu','系统管理/连接池','Y',NULL,NULL,NULL,NULL,'0'),(198,'应用模块',131,NULL,'1',NULL,NULL,NULL,NULL,1,'rootmenu','131-198','Y','menu','模块管理/应用模块','Y',NULL,NULL,NULL,NULL,'0'),(199,'商品管理',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'prod','199','Y','dir','商品管理','Y',NULL,NULL,NULL,NULL,'0'),(200,'商品大类',199,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'dl','199-200','Y','menu','商品管理/商品大类','Y',NULL,NULL,NULL,NULL,'0'),(201,'商品小类',199,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'xl','199-201','Y','menu','商品管理/商品小类','Y',NULL,NULL,NULL,NULL,'0'),(202,'商品发布',199,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'prodquery','199-202','Y','menu','商品管理/商品发布','Y',NULL,NULL,NULL,NULL,'0'),(203,'用户管理',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,'fa fa-user',NULL,NULL,1,'muser','203','Y','dir','用户管理','Y',NULL,NULL,NULL,NULL,'0'),(204,'用户查询',203,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'query','203-204','Y','menu','用户管理/用户查询','Y',NULL,NULL,NULL,NULL,'0'),(205,'商品详情',199,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'proddtl','199-205','Y','menu','商品管理/商品详情','Y',NULL,NULL,NULL,NULL,'0'),(206,'网页授权',189,NULL,'3',NULL,NULL,NULL,NULL,1,'weboauth','189-206','Y','menu','微信管理/网页授权','Y',NULL,NULL,NULL,NULL,'0'),(208,'订单管理',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,'fa fa-codepen',NULL,NULL,1,'mshoporder','208','Y','dir','订单管理','Y',NULL,NULL,NULL,NULL,'0'),(209,'订单查询',208,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'query','208-209','Y','menu','订单管理/订单查询','Y',NULL,NULL,NULL,NULL,'0'),(213,'用户提现',203,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'user_tix','203-213','Y','menu','用户管理/用户提现','Y',NULL,NULL,NULL,NULL,'0'),(215,'报表管理',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,'fa fa-table',NULL,NULL,1,'mshoprep','215','Y','dir','报表管理','Y',NULL,NULL,NULL,NULL,'0'),(216,'每日订单',215,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'dailyorder','215-216','Y','menu','报表管理/每日订单','Y',NULL,NULL,NULL,NULL,'0'),(219,'控制台',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,'fa fa-home',NULL,NULL,1,'mshopdash','219','Y','menu','控制台','Y',NULL,NULL,NULL,NULL,'0'),(223,'我的情况',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'mshop_me','223','Y','dir','我的情况','Y',NULL,NULL,NULL,NULL,'0'),(224,'我的订单',223,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'mshopmyorder','223-224','Y','menu','我的情况/我的订单','Y',NULL,NULL,NULL,NULL,'0'),(225,'我的券码',223,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'mshopmyitems','223-225','Y','menu','我的情况/我的券码','Y',NULL,NULL,NULL,NULL,'0'),(226,'券码管理',0,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'qmmgr','226','Y','dir','券码管理','Y',NULL,NULL,NULL,NULL,'0'),(227,'待审核券',226,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'prodsh','226-227','Y','menu','券码管理/待审核券','Y',NULL,NULL,NULL,NULL,'0'),(228,'待确认券',226,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'waitforsure','226-228','Y','menu','券码管理/待确认券','Y',NULL,NULL,NULL,NULL,'0'),(229,'已审核券',226,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'audited','226-229','Y','menu','券码管理/已审核券','Y',NULL,NULL,NULL,NULL,'0'),(230,'纠纷中券',226,NULL,'4bfdfbe8-c436-4e66-a483-3c514baed2f5',NULL,NULL,NULL,NULL,1,'jfqm','226-230','Y','menu','券码管理/纠纷中券','Y',NULL,NULL,NULL,NULL,'0'),(233,'常用资产',0,NULL,'1079008526457073665',NULL,'fa fa-database',NULL,NULL,10,'cf','233','Y','dir','常用资产','Y',NULL,NULL,NULL,NULL,'0'),(234,'服务器',233,NULL,'1079008526457073665',NULL,NULL,NULL,NULL,1,'server','233-234','Y','menu','常用资产/服务器','Y',NULL,NULL,NULL,NULL,'0'),(236,'系统设置',0,NULL,'1079008526457073665',NULL,'fa fa-laptop',NULL,NULL,100,'cmsetting','236','Y','dir','系统设置','Y',NULL,NULL,NULL,NULL,'0'),(241,'当前告警',0,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'zbalert','241','Y','menu','当前告警','Y',NULL,NULL,NULL,NULL,'0'),(243,'监控管理',0,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'zbmon','243','Y','dir','监控管理','Y',NULL,NULL,NULL,NULL,'0'),(244,'主机',243,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'zbmonhost','243-244','Y','menu','监控管理/主机','Y',NULL,NULL,NULL,NULL,'0'),(245,'数据库',243,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'zbmon_db','243-245','Y','menu','监控管理/数据库','Y',NULL,NULL,NULL,NULL,'0'),(246,'中间件',243,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'zbmon_mid','243-246','Y','menu','监控管理/中间件','Y',NULL,NULL,NULL,NULL,'0'),(247,'统计报表',0,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'zbrep','247','Y','dir','统计报表','Y',NULL,NULL,NULL,NULL,'0'),(248,'每日报表',247,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'daily','247-248','Y','menu','统计报表/每日报表','Y',NULL,NULL,NULL,NULL,'0'),(250,'统计',247,NULL,'1106521858655117313',NULL,NULL,NULL,NULL,1,'statistics','247-250','Y','menu','统计报表/统计','Y',NULL,NULL,NULL,NULL,'0'),(255,'查询管理',0,NULL,'1106521858655117313','','',NULL,NULL,1,'qadmin','255','Y','dir','查询管理','Y',NULL,NULL,NULL,NULL,'0'),(256,'查询主机',255,NULL,'1106521858655117313','','',NULL,NULL,1,'hosts','255-256','Y','menu','查询管理/查询主机','Y',NULL,NULL,NULL,NULL,'0'),(257,'流程管理',0,NULL,'1','','fa fa-exchange',NULL,NULL,1,'flow','257','Y','dir','流程管理','Y',NULL,NULL,NULL,NULL,'0'),(258,'流程设计',257,NULL,'1','','',NULL,NULL,2,'designer','257-258','Y','menu','流程管理/流程设计','Y',NULL,NULL,NULL,NULL,'0'),(259,'流程监测',257,NULL,'1','','',NULL,NULL,2,'flowquery','257-259','Y','menu','流程管理/流程监测','Y',NULL,NULL,NULL,NULL,'0'),(260,'网络设备',233,NULL,'1079008526457073665','','',NULL,NULL,2,'network','233-260','Y','menu','常用资产/网络设备','N',NULL,NULL,NULL,NULL,'0'),(263,'存储设备',233,NULL,'1079008526457073665','','',NULL,NULL,7,'storage','233-263','Y','menu','常用资产/存储设备','Y',NULL,NULL,NULL,NULL,'0'),(265,'维护管理',0,NULL,'1079008526457073665','','fa fa-wrench',NULL,NULL,12,'maintain','265','Y','dir','维护管理','Y',NULL,NULL,NULL,NULL,'0'),(266,'资产报修',265,NULL,'1079008526457073665','','',NULL,NULL,1,'faultrecord','265-266','Y','menu','维护管理/资产报修','Y',NULL,NULL,NULL,NULL,'0'),(269,'网点设备',233,NULL,'1079008526457073665','111','',NULL,NULL,1111,'outlets','233-269','Y','menu','常用资产/网点设备','Y',NULL,NULL,NULL,NULL,'0'),(270,'安全设备',233,NULL,'1079008526457073665','','',NULL,NULL,12,'safety','233-270','Y','menu','常用资产/安全设备','Y',NULL,NULL,NULL,NULL,'0'),(273,'光纤交换机',233,NULL,'1079008526457073665','','',NULL,NULL,111,'lightsw','233-273','Y','menu','常用资产/光纤交换机','Y',NULL,NULL,NULL,NULL,'0'),(283,'新增',22,NULL,'1','','',NULL,NULL,1,'insert','2-22-283','Y','btn','系统管理/字典管理/新增','Y',NULL,NULL,NULL,NULL,'0'),(284,'查询',57,NULL,'1','','',NULL,NULL,1,'select','2-57-284','Y','btn','系统管理/系统参数/查询','Y',NULL,NULL,NULL,NULL,'0'),(285,'新增',57,NULL,'1','','',NULL,NULL,2,'insert','2-57-285','Y','btn','系统管理/系统参数/新增','Y',NULL,NULL,NULL,NULL,'0'),(286,'更新',57,NULL,'1','','',NULL,NULL,2,'update','2-57-286','Y','btn','系统管理/系统参数/更新','Y',NULL,NULL,NULL,NULL,'0'),(287,'删除',57,NULL,'1','','',NULL,NULL,5,'remove','2-57-287','Y','btn','系统管理/系统参数/删除','Y',NULL,NULL,NULL,NULL,'0'),(288,'更新',22,NULL,'1','','',NULL,NULL,1,'update','2-22-288','Y','btn','系统管理/字典管理/更新','Y',NULL,NULL,NULL,NULL,'0'),(289,'删除',22,NULL,'1','','',NULL,NULL,1,'remove','2-22-289','Y','btn','系统管理/字典管理/删除','Y',NULL,NULL,NULL,NULL,'0'),(290,'新增',70,NULL,'1','','',NULL,NULL,1,'insert','2-70-290','Y','btn','系统管理/上传配置/新增','Y',NULL,NULL,NULL,NULL,'0'),(291,'查询',70,NULL,'1','','',NULL,NULL,1,'select','2-70-291','Y','btn','系统管理/上传配置/查询','Y',NULL,NULL,NULL,NULL,'0'),(292,'删除',70,NULL,'1','','',NULL,NULL,1,'remove','2-70-292','Y','btn','系统管理/上传配置/删除','Y',NULL,NULL,NULL,NULL,'0'),(293,'更新',70,NULL,'1','','',NULL,NULL,1,'update','2-70-293','Y','btn','系统管理/上传配置/更新','Y',NULL,NULL,NULL,NULL,'0'),(294,'查询',157,NULL,'1','','',NULL,NULL,1,'select','2-157-294','Y','btn','系统管理/在线用户/查询','Y',NULL,NULL,NULL,NULL,'0'),(295,'删除',157,NULL,'1','','',NULL,NULL,1,'remove','2-157-295','Y','btn','系统管理/在线用户/删除','Y',NULL,NULL,NULL,NULL,'0'),(296,'查询',176,NULL,'1','','',NULL,NULL,1,'select','2-176-296','Y','btn','系统管理/缓存管理/查询','Y',NULL,NULL,NULL,NULL,'0'),(297,'删除',176,NULL,'1','','',NULL,NULL,1,'remove','2-176-297','Y','btn','系统管理/缓存管理/删除','Y',NULL,NULL,NULL,NULL,'0'),(300,'启用',143,NULL,'1','','',NULL,NULL,1,'job_enable','142-143-300','Y','btn','调度管理/调度设置/启用','Y',NULL,NULL,NULL,NULL,'0'),(301,'执行一次',143,NULL,'1','','',NULL,NULL,1,'job_run','142-143-301','Y','btn','调度管理/调度设置/执行一次','Y',NULL,NULL,NULL,NULL,'0'),(302,'停用',143,NULL,'1','','',NULL,NULL,1,'job_stop','142-143-302','Y','btn','调度管理/调度设置/停用','Y',NULL,NULL,NULL,NULL,'0'),(303,'暂停',143,NULL,'1','','',NULL,NULL,1,'job_pause','142-143-303','Y','btn','调度管理/调度设置/暂停','Y',NULL,NULL,NULL,NULL,'0'),(304,'恢复',143,NULL,'1','','',NULL,NULL,1,'job_recover','142-143-304','Y','btn','调度管理/调度设置/恢复','Y',NULL,NULL,NULL,NULL,'0'),(305,'查询',198,NULL,'1','','',NULL,NULL,1,'select','131-198-305','Y','btn','模块管理/应用模块/查询','Y',NULL,NULL,NULL,NULL,'0'),(306,'删除',198,NULL,'1','','',NULL,NULL,1,'remove','131-198-306','Y','btn','模块管理/应用模块/删除','Y',NULL,NULL,NULL,NULL,'0'),(307,'更新',198,NULL,'1','','',NULL,NULL,1,'update','131-198-307','Y','btn','模块管理/应用模块/更新','Y',NULL,NULL,NULL,NULL,'0'),(308,'新增',198,NULL,'1','','',NULL,NULL,1,'insert','131-198-308','Y','btn','模块管理/应用模块/新增','Y',NULL,NULL,NULL,NULL,'0'),(309,'查询',137,NULL,'1','','',NULL,NULL,1,'select','130-137-309','Y','btn','角色管理/角色设置/查询','Y',NULL,NULL,NULL,NULL,'0'),(310,'删除',137,NULL,'1','','',NULL,NULL,1,'remove','130-137-310','Y','btn','角色管理/角色设置/删除','Y',NULL,NULL,NULL,NULL,'0'),(311,'更新',137,NULL,'1','','',NULL,NULL,1,'update','130-137-311','Y','btn','角色管理/角色设置/更新','Y',NULL,NULL,NULL,NULL,'0'),(312,'新增',137,NULL,'1','','',NULL,NULL,1,'insert','130-137-312','Y','btn','角色管理/角色设置/新增','Y',NULL,NULL,NULL,NULL,'0'),(313,'查询',134,NULL,'1','','',NULL,NULL,1,'select','129-134-313','Y','btn','用户管理/用户组设置/查询','Y',NULL,NULL,NULL,NULL,'0'),(314,'新增',134,NULL,'1','','',NULL,NULL,1,'insert','129-134-314','Y','btn','用户管理/用户组设置/新增','Y',NULL,NULL,NULL,NULL,'0'),(315,'删除',134,NULL,'1','','',NULL,NULL,1,'remove','129-134-315','Y','btn','用户管理/用户组设置/删除','Y',NULL,NULL,NULL,NULL,'0'),(316,'更新',134,NULL,'1','','',NULL,NULL,1,'update','129-134-316','Y','btn','用户管理/用户组设置/更新','Y',NULL,NULL,NULL,NULL,'0'),(317,'查询',136,NULL,'1','','',NULL,NULL,1,'select','129-136-317','Y','btn','用户管理/用户设置/查询','Y',NULL,NULL,NULL,NULL,'0'),(318,'更新',136,NULL,'1','','',NULL,NULL,1,'update','129-136-318','Y','btn','用户管理/用户设置/更新','Y',NULL,NULL,NULL,NULL,'0'),(319,'删除',136,NULL,'1','','',NULL,NULL,1,'remove','129-136-319','Y','btn','用户管理/用户设置/删除','Y',NULL,NULL,NULL,NULL,'0'),(321,'字典项_新增',22,NULL,'1','','',NULL,NULL,1,'item_insert','2-22-321','Y','btn','系统管理/字典管理/字典项_新增','Y',NULL,NULL,NULL,NULL,'0'),(322,'字典项_删除',22,NULL,'1','','',NULL,NULL,1,'item_remove','2-22-322','Y','btn','系统管理/字典管理/字典项_删除','Y',NULL,NULL,NULL,NULL,'0'),(323,'字典项_更新',22,NULL,'1','','',NULL,NULL,1,'item_update','2-22-323','Y','btn','系统管理/字典管理/字典项_更新','Y',NULL,NULL,NULL,NULL,'0'),(326,'授权',136,NULL,'1','','',NULL,NULL,1,'priv','129-136-326','Y','btn','用户管理/用户设置/授权','Y',NULL,NULL,NULL,NULL,'0'),(327,'新增',136,NULL,'1','','',NULL,NULL,1,'insert','129-136-327','Y','btn','用户管理/用户设置/新增','Y',NULL,NULL,NULL,NULL,'0'),(328,'查询',138,NULL,'1','','',NULL,NULL,1,'select','130-138-328','Y','btn','角色管理/角色模块映射/查询','Y',NULL,NULL,NULL,NULL,'0'),(329,'提交',138,NULL,'1','','',NULL,NULL,1,'submit','130-138-329','Y','btn','角色管理/角色模块映射/提交','Y',NULL,NULL,NULL,NULL,'0'),(330,'新增',56,NULL,'1','','',NULL,NULL,1,'insert','3-56-330','Y','btn','组织架构/人员调整/新增','Y',NULL,NULL,NULL,NULL,'0'),(331,'删除',56,NULL,'1','','',NULL,NULL,1,'remove','3-56-331','Y','btn','组织架构/人员调整/删除','Y',NULL,NULL,NULL,NULL,'0'),(332,'更新',56,NULL,'1','','',NULL,NULL,1,'update','3-56-332','Y','btn','组织架构/人员调整/更新','Y',NULL,NULL,NULL,NULL,'0'),(333,'新增',234,NULL,'1079008526457073665','','',NULL,NULL,1,'insert','233-234-333','Y','btn','常用资产/服务器/新增','Y',NULL,NULL,NULL,NULL,'0'),(334,'删除',234,NULL,'1079008526457073665','','',NULL,NULL,1,'remove','233-234-334','Y','btn','常用资产/服务器/删除','Y',NULL,NULL,NULL,NULL,'0'),(335,'批量更新',234,NULL,'1079008526457073665','','',NULL,NULL,1,'item_update','233-234-335','Y','btn','常用资产/服务器/批量更新','Y',NULL,NULL,NULL,NULL,'0'),(336,'导出',234,NULL,'1079008526457073665','','',NULL,NULL,1,'exportfile','233-234-336','Y','btn','常用资产/服务器/导出','Y',NULL,NULL,NULL,NULL,'0'),(337,'新增',260,NULL,'1079008526457073665','','',NULL,NULL,1,'insert','233-260-337','Y','btn','常用资产/网络设备/新增','Y',NULL,NULL,NULL,NULL,'0'),(338,'删除',260,NULL,'1079008526457073665','','',NULL,NULL,1,'remove','233-260-338','Y','btn','常用资产/网络设备/删除','Y',NULL,NULL,NULL,NULL,'0'),(339,'批量新增',260,NULL,'1079008526457073665','','',NULL,NULL,1,'item_update','233-260-339','Y','btn','常用资产/网络设备/批量新增','Y',NULL,NULL,NULL,NULL,'0'),(340,'导出',260,NULL,'1079008526457073665','','',NULL,NULL,1,'exportfile','233-260-340','Y','btn','常用资产/网络设备/导出','Y',NULL,NULL,NULL,NULL,'0'),(349,'新增',263,NULL,'1079008526457073665','','',NULL,NULL,1,'insert','233-263-349','Y','btn','常用资产/存储设备/新增','Y',NULL,NULL,NULL,NULL,'0'),(350,'导出',263,NULL,'1079008526457073665','','',NULL,NULL,1,'exportfile','233-263-350','Y','btn','常用资产/存储设备/导出','Y',NULL,NULL,NULL,NULL,'0'),(351,'删除',263,NULL,'1079008526457073665','','',NULL,NULL,1,'remove','233-263-351','Y','btn','常用资产/存储设备/删除','Y',NULL,NULL,NULL,NULL,'0'),(352,'批量更新',263,NULL,'1079008526457073665','','',NULL,NULL,1,'item_update','233-263-352','Y','btn','常用资产/存储设备/批量更新','Y',NULL,NULL,NULL,NULL,'0'),(355,'新增',269,NULL,'1079008526457073665','','',NULL,NULL,1,'insert','233-269-355','Y','btn','常用资产/网点设备/新增','Y',NULL,NULL,NULL,NULL,'0'),(356,'新增',270,NULL,'1079008526457073665','','',NULL,NULL,1,'insert','233-270-356','Y','btn','常用资产/安全设备/新增','Y',NULL,NULL,NULL,NULL,'0'),(357,'新增',273,NULL,'1079008526457073665','','',NULL,NULL,1,'insert','233-273-357','Y','btn','常用资产/光纤交换机/新增','Y',NULL,NULL,NULL,NULL,'0'),(361,'删除',269,NULL,'1079008526457073665','','',NULL,NULL,1,'remove','233-269-361','Y','btn','常用资产/网点设备/删除','Y',NULL,NULL,NULL,NULL,'0'),(362,'删除',270,NULL,'1079008526457073665','','',NULL,NULL,1,'remove','233-270-362','Y','btn','常用资产/安全设备/删除','Y',NULL,NULL,NULL,NULL,'0'),(363,'删除',273,NULL,'1079008526457073665','','',NULL,NULL,1,'remove','233-273-363','Y','btn','常用资产/光纤交换机/删除','Y',NULL,NULL,NULL,NULL,'0'),(367,'批量更新',269,NULL,'1079008526457073665','','',NULL,NULL,1,'item_update','233-269-367','Y','btn','常用资产/网点设备/批量更新','Y',NULL,NULL,NULL,NULL,'0'),(368,'批量更新',270,NULL,'1079008526457073665','','',NULL,NULL,1,'item_update','233-270-368','Y','btn','常用资产/安全设备/批量更新','Y',NULL,NULL,NULL,NULL,'0'),(369,'批量更新',273,NULL,'1079008526457073665','','',NULL,NULL,1,'item_update','233-273-369','Y','btn','常用资产/光纤交换机/批量更新','Y',NULL,NULL,NULL,NULL,'0'),(373,'导出',269,NULL,'1079008526457073665','','',NULL,NULL,1,'exportfile','233-269-373','Y','btn','常用资产/网点设备/导出','Y',NULL,NULL,NULL,NULL,'0'),(374,'导出',270,NULL,'1079008526457073665','','',NULL,NULL,1,'exportfile','233-270-374','Y','btn','常用资产/安全设备/导出','Y',NULL,NULL,NULL,NULL,'0'),(375,'导出',273,NULL,'1079008526457073665','','',NULL,NULL,1,'exportfile','233-273-375','Y','btn','常用资产/光纤交换机/导出','Y',NULL,NULL,NULL,NULL,'0'),(378,'查询',56,NULL,'1','','',NULL,NULL,0,'select','3-56-378','Y','btn','组织架构/人员调整/查询','Y',NULL,NULL,NULL,NULL,'0'),(379,'查询',143,NULL,'1','','',NULL,NULL,0,'select','142-143-379','Y','btn','调度管理/调度设置/查询','Y',NULL,NULL,NULL,NULL,'0'),(380,'查询',22,NULL,'1','','',NULL,NULL,1,'select','2-22-380','Y','btn','系统管理/字典管理/查询','Y',NULL,NULL,NULL,NULL,'0'),(381,'更新',234,NULL,'1079008526457073665','','',NULL,NULL,0,'update','233-234-381','Y','btn','常用资产/服务器/更新','Y',NULL,NULL,NULL,NULL,'0'),(382,'更新',260,NULL,'1079008526457073665','','',NULL,NULL,0,'update','233-260-382','Y','btn','常用资产/网络设备/更新','Y',NULL,NULL,NULL,NULL,'0'),(385,'更新',263,NULL,'1079008526457073665','','',NULL,NULL,0,'update','233-263-385','Y','btn','常用资产/存储设备/更新','Y',NULL,NULL,NULL,NULL,'0'),(387,'更新',269,NULL,'1079008526457073665','','',NULL,NULL,0,'update','233-269-387','Y','btn','常用资产/网点设备/更新','Y',NULL,NULL,NULL,NULL,'0'),(388,'更新',270,NULL,'1079008526457073665','','',NULL,NULL,0,'update','233-270-388','Y','btn','常用资产/安全设备/更新','Y',NULL,NULL,NULL,NULL,'0'),(389,'更新',273,NULL,'1079008526457073665','','',NULL,NULL,0,'update','233-273-389','Y','btn','常用资产/光纤交换机/更新','Y',NULL,NULL,NULL,NULL,'0'),(392,'查询',234,NULL,'1079008526457073665','','',NULL,NULL,0,'select','233-234-392','Y','btn','常用资产/服务器/查询','Y',NULL,NULL,NULL,NULL,'0'),(393,'查询',260,NULL,'1079008526457073665','','',NULL,NULL,0,'select','233-260-393','Y','btn','常用资产/网络设备/查询','Y',NULL,NULL,NULL,NULL,'0'),(396,'查询',263,NULL,'1079008526457073665','','',NULL,NULL,0,'select','233-263-396','Y','btn','常用资产/存储设备/查询','Y',NULL,NULL,NULL,NULL,'0'),(398,'查询',269,NULL,'1079008526457073665','','',NULL,NULL,0,'select','233-269-398','Y','btn','常用资产/网点设备/查询','Y',NULL,NULL,NULL,NULL,'0'),(399,'查询',270,NULL,'1079008526457073665','','',NULL,NULL,0,'select','233-270-399','Y','btn','常用资产/安全设备/查询','Y',NULL,NULL,NULL,NULL,'0'),(400,'查询',273,NULL,'1079008526457073665','','',NULL,NULL,0,'select','233-273-400','Y','btn','常用资产/光纤交换机/查询','Y',NULL,NULL,NULL,NULL,'0'),(410,'查询',139,NULL,'1','','',NULL,NULL,0,'select','131-139-410','Y','btn','模块管理/模块设置/查询','Y',NULL,NULL,NULL,NULL,'0'),(411,'新增',139,NULL,'1','','',NULL,NULL,0,'insert','131-139-411','Y','btn','模块管理/模块设置/新增','Y',NULL,NULL,NULL,NULL,'0'),(412,'更新',139,NULL,'1','','',NULL,NULL,0,'update','131-139-412','Y','btn','模块管理/模块设置/更新','Y',NULL,NULL,NULL,NULL,'0'),(413,'删除',139,NULL,'1','','',NULL,NULL,0,'remove','131-139-413','Y','btn','模块管理/模块设置/删除','Y',NULL,NULL,NULL,NULL,'0'),(414,'权限',139,NULL,'1','','',NULL,NULL,0,'priv','131-139-414','Y','btn','模块管理/模块设置/权限','Y',NULL,NULL,NULL,NULL,'0'),(415,'新增主节点',139,NULL,'1','','',NULL,NULL,0,'root_insert','131-139-415','Y','btn','模块管理/模块设置/新增主节点','Y',NULL,NULL,NULL,NULL,'0'),(417,'改密',136,NULL,'1','','',NULL,NULL,0,'cpwd','129-136-417','Y','btn','用户管理/用户设置/改密','Y',NULL,NULL,NULL,NULL,'0'),(418,'备件管理',0,NULL,'1079008526457073665','','fa fa-hdd-o',NULL,NULL,11,'bjmgr','418','Y','dir','备件管理','Y',NULL,NULL,NULL,NULL,'0'),(419,'备品备件',418,NULL,'1079008526457073665','','',NULL,NULL,0,'bjpj','418-419','Y','menu','备件管理/备品备件','Y',NULL,NULL,NULL,NULL,'0'),(420,'登记',419,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','418-419-420','Y','btn','备件管理/备品备件/登记','Y',NULL,NULL,NULL,NULL,'0'),(421,'删除',419,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','418-419-421','Y','btn','备件管理/备品备件/删除','Y',NULL,NULL,NULL,NULL,'0'),(424,'更新',419,NULL,'1079008526457073665','','',NULL,NULL,0,'update','418-419-424','Y','btn','备件管理/备品备件/更新','Y',NULL,NULL,NULL,NULL,'0'),(425,'查询',419,NULL,'1079008526457073665','','',NULL,NULL,0,'select','418-419-425','Y','btn','备件管理/备品备件/查询','Y',NULL,NULL,NULL,NULL,'0'),(426,'导出',419,NULL,'1079008526457073665','','',NULL,NULL,0,'exportfile','418-419-426','Y','btn','备件管理/备品备件/导出','Y',NULL,NULL,NULL,NULL,'0'),(428,'资产管理',0,NULL,'1079008526457073665','','fa fa-th',NULL,NULL,13,'zcmgr','428','Y','dir','资产管理','Y',NULL,NULL,NULL,NULL,'0'),(429,'资产台帐',428,NULL,'1079008526457073665','','',NULL,NULL,0,'zctz','428-429','Y','menu','资产管理/资产台帐','Y',NULL,NULL,NULL,NULL,'0'),(431,'查询',266,NULL,'1079008526457073665','','',NULL,NULL,0,'select','265-266-431','Y','btn','维护管理/资产报修/查询','Y',NULL,NULL,NULL,NULL,'0'),(432,'删除',266,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','265-266-432','Y','btn','维护管理/资产报修/删除','Y',NULL,NULL,NULL,NULL,'0'),(433,'申请报修',266,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','265-266-433','Y','btn','维护管理/资产报修/申请报修','Y',NULL,NULL,NULL,NULL,'0'),(434,'修改',266,NULL,'1079008526457073665','','',NULL,NULL,0,'update','265-266-434','Y','btn','维护管理/资产报修/修改','Y',NULL,NULL,NULL,NULL,'0'),(438,'耗材管理',0,NULL,'1079008526457073665','','fa fa-clone',NULL,NULL,12,'hcmgr','438','Y','dir','耗材管理','Y',NULL,NULL,NULL,NULL,'0'),(439,'耗材资产',438,NULL,'1079008526457073665','','',NULL,NULL,0,'hc','438-439','Y','menu','耗材管理/耗材资产','Y',NULL,NULL,NULL,NULL,'0'),(440,'登记',439,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','438-439-440','Y','btn','耗材管理/耗材资产/登记','Y',NULL,NULL,NULL,NULL,'0'),(442,'删除',439,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','438-439-442','Y','btn','耗材管理/耗材资产/删除','Y',NULL,NULL,NULL,NULL,'0'),(443,'更新',439,NULL,'1079008526457073665','','',NULL,NULL,0,'update','438-439-443','Y','btn','耗材管理/耗材资产/更新','Y',NULL,NULL,NULL,NULL,'0'),(444,'查询',439,NULL,'1079008526457073665','','',NULL,NULL,0,'select','438-439-444','Y','btn','耗材管理/耗材资产/查询','Y',NULL,NULL,NULL,NULL,'0'),(452,'资产入库',428,NULL,'1079008526457073665','','',NULL,NULL,0,'zcdj','428-452','Y','menu','资产管理/资产入库','Y',NULL,NULL,NULL,NULL,'0'),(453,'资产分类',236,NULL,'1079008526457073665','','',NULL,NULL,0,'zccat','236-453','Y','menu','系统设置/资产分类','Y',NULL,NULL,NULL,NULL,'0'),(454,'入库',452,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','428-452-454','Y','btn','资产管理/资产入库/入库','Y',NULL,NULL,NULL,NULL,'0'),(455,'删除',452,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','428-452-455','Y','btn','资产管理/资产入库/删除','Y',NULL,NULL,NULL,NULL,'0'),(456,'更新',452,NULL,'1079008526457073665','','',NULL,NULL,0,'update','428-452-456','Y','btn','资产管理/资产入库/更新','Y',NULL,NULL,NULL,NULL,'0'),(457,'查询',452,NULL,'1079008526457073665','','',NULL,NULL,0,'select','428-452-457','Y','btn','资产管理/资产入库/查询','Y',NULL,NULL,NULL,NULL,'0'),(458,'导出',452,NULL,'1079008526457073665','','',NULL,NULL,0,'exportfile','428-452-458','Y','btn','资产管理/资产入库/导出','Y',NULL,NULL,NULL,NULL,'0'),(459,'流程分配',257,NULL,'1','','',NULL,NULL,0,'processconf','257-459','Y','menu','流程管理/流程分配','Y',NULL,NULL,NULL,NULL,'0'),(464,'流程配置',257,NULL,'1','','',NULL,NULL,0,'processmatch','257-464','Y','menu','流程管理/流程配置','Y',NULL,NULL,NULL,NULL,'0'),(465,'新增',464,NULL,'1','','',NULL,NULL,0,'insert','257-464-465','Y','btn','流程管理/流程配置/新增','Y',NULL,NULL,NULL,NULL,'0'),(466,'查询',464,NULL,'1','','',NULL,NULL,0,'select','257-464-466','Y','btn','流程管理/流程配置/查询','Y',NULL,NULL,NULL,NULL,'0'),(467,'删除',464,NULL,'1','','',NULL,NULL,0,'remove','257-464-467','Y','btn','流程管理/流程配置/删除','Y',NULL,NULL,NULL,NULL,'0'),(468,'更新',464,NULL,'1','','',NULL,NULL,0,'update','257-464-468','Y','btn','流程管理/流程配置/更新','Y',NULL,NULL,NULL,NULL,'0'),(469,'首页',0,NULL,'1079008526457073665','','fa fa-university',NULL,NULL,-1,'zcindex','469','Y','menu','首页','Y',NULL,NULL,NULL,NULL,'0'),(470,'我的事件',0,NULL,'1079008526457073665','','',NULL,NULL,0,'myprocess','470','Y','dir','我的事件','Y',NULL,NULL,NULL,NULL,'0'),(471,'待审批任务',470,NULL,'1079008526457073665','','',NULL,NULL,1,'waittask','470-471','Y','menu','我的事件/待审批任务','Y',NULL,NULL,NULL,NULL,'0'),(472,'已审批任务',470,NULL,'1079008526457073665','','',NULL,NULL,2,'taskfinish','470-472','Y','menu','我的事件/已审批任务','Y',NULL,NULL,NULL,NULL,'0'),(473,'我的流程',470,NULL,'1079008526457073665','','',NULL,NULL,0,'myprocess','470-473','Y','menu','我的事件/我的流程','Y',NULL,NULL,NULL,NULL,'0'),(496,'业务系统',0,NULL,'1224134297569464321','','fa fa-desktop',NULL,NULL,5,'infosys','496','Y','dir','业务系统','Y',NULL,NULL,NULL,NULL,'0'),(512,'批量按钮',139,NULL,'1','','',NULL,NULL,0,'act1','131-139-512','Y','btn','模块管理/模块设置/批量按钮','Y',NULL,NULL,NULL,NULL,'0'),(513,'信息系统',496,NULL,'1224134297569464321','','',NULL,NULL,0,'infosys','496-513','Y','menu','业务系统/信息系统','Y',NULL,NULL,NULL,NULL,'0'),(514,'搜索',513,NULL,'1224134297569464321','','',NULL,NULL,0,'search','496-513-514','Y','btn','业务系统/信息系统/搜索','Y',NULL,NULL,NULL,NULL,'0'),(515,'查询',513,NULL,'1224134297569464321','','',NULL,NULL,0,'select','496-513-515','Y','btn','业务系统/信息系统/查询','Y',NULL,NULL,NULL,NULL,'0'),(516,'新增',513,NULL,'1224134297569464321','','',NULL,NULL,0,'insert','496-513-516','Y','btn','业务系统/信息系统/新增','Y',NULL,NULL,NULL,NULL,'0'),(517,'更新',513,NULL,'1224134297569464321','','',NULL,NULL,0,'update','496-513-517','Y','btn','业务系统/信息系统/更新','Y',NULL,NULL,NULL,NULL,'0'),(518,'删除',513,NULL,'1224134297569464321','','',NULL,NULL,0,'remove','496-513-518','Y','btn','业务系统/信息系统/删除','Y',NULL,NULL,NULL,NULL,'0'),(519,'导出',513,NULL,'1224134297569464321','','',NULL,NULL,0,'exportfile','496-513-519','Y','btn','业务系统/信息系统/导出','Y',NULL,NULL,NULL,NULL,'0'),(520,'表单管理',0,NULL,'1','','',NULL,NULL,100,'formmgr','520','Y','dir','表单管理','Y',NULL,NULL,NULL,NULL,'0'),(521,'表单设置',520,NULL,'1','','',NULL,NULL,0,'setting','520-521','Y','menu','表单管理/表单设置','Y',NULL,NULL,NULL,NULL,'0'),(522,'资产变更',0,NULL,'1079008526457073665','','fa fa-exchange',NULL,NULL,20,'zcchange','522','Y','dir','资产变更','Y',NULL,NULL,NULL,NULL,'0'),(523,'领用退库',522,NULL,'1079008526457073665','','',NULL,NULL,0,'lygh','522-523','Y','menu','资产变更/领用退库','Y',NULL,NULL,NULL,NULL,'0'),(524,'借用归还',522,NULL,'1079008526457073665','','',NULL,NULL,0,'jygh','522-524','Y','menu','资产变更/借用归还','Y',NULL,NULL,NULL,NULL,'0'),(525,'资产调拨',522,NULL,'1079008526457073665','','',NULL,NULL,0,'zczy','522-525','Y','menu','资产变更/资产调拨','Y',NULL,NULL,NULL,NULL,'0'),(526,'搜索',523,NULL,'1079008526457073665','','',NULL,NULL,0,'search','522-523-526','Y','btn','资产变更/领用退库/搜索','Y',NULL,NULL,NULL,NULL,'0'),(528,'领用',523,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','522-523-528','Y','btn','资产变更/领用退库/领用','Y',NULL,NULL,NULL,NULL,'0'),(529,'更新',523,NULL,'1079008526457073665','','',NULL,NULL,0,'update','522-523-529','Y','btn','资产变更/领用退库/更新','Y',NULL,NULL,NULL,NULL,'0'),(530,'送审',523,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','522-523-530','Y','btn','资产变更/领用退库/送审','Y',NULL,NULL,NULL,NULL,'0'),(531,'归还',523,NULL,'1079008526457073665','','',NULL,NULL,0,'act2','522-523-531','Y','btn','资产变更/领用退库/归还','Y',NULL,NULL,NULL,NULL,'0'),(532,'搜索',524,NULL,'1079008526457073665','','',NULL,NULL,0,'search','522-524-532','Y','btn','资产变更/借用归还/搜索','Y',NULL,NULL,NULL,NULL,'0'),(534,'借用',524,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','522-524-534','Y','btn','资产变更/借用归还/借用','Y',NULL,NULL,NULL,NULL,'0'),(535,'更新',524,NULL,'1079008526457073665','','',NULL,NULL,0,'update','522-524-535','Y','btn','资产变更/借用归还/更新','Y',NULL,NULL,NULL,NULL,'0'),(536,'删除',524,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','522-524-536','Y','btn','资产变更/借用归还/删除','Y',NULL,NULL,NULL,NULL,'0'),(537,'送审',524,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','522-524-537','Y','btn','资产变更/借用归还/送审','Y',NULL,NULL,NULL,NULL,'0'),(538,'归还',524,NULL,'1079008526457073665','','',NULL,NULL,0,'act2','522-524-538','Y','btn','资产变更/借用归还/归还','Y',NULL,NULL,NULL,NULL,'0'),(539,'搜索',525,NULL,'1079008526457073665','','',NULL,NULL,0,'search','522-525-539','Y','btn','资产变更/资产调拨/搜索','Y',NULL,NULL,NULL,NULL,'0'),(542,'申请',525,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','522-525-542','Y','btn','资产变更/资产调拨/申请','Y',NULL,NULL,NULL,NULL,'0'),(543,'取消',525,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','522-525-543','Y','btn','资产变更/资产调拨/取消','Y',NULL,NULL,NULL,NULL,'0'),(544,'确认调拨',525,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','522-525-544','Y','btn','资产变更/资产调拨/确认调拨','Y',NULL,NULL,NULL,NULL,'0'),(547,'物品档案',236,NULL,'1079008526457073665','','',NULL,NULL,0,'goodscat','236-547','Y','menu','系统设置/物品档案','Y',NULL,NULL,NULL,NULL,'0'),(548,'备件分类',236,NULL,'1079008526457073665','','',NULL,NULL,0,'bjcat','236-548','Y','menu','系统设置/备件分类','Y',NULL,NULL,NULL,NULL,'0'),(549,'导入',234,NULL,'1079008526457073665','','',NULL,NULL,0,'importfile','233-234-549','Y','btn','常用资产/服务器/导入','Y',NULL,NULL,NULL,NULL,'0'),(550,'导入',260,NULL,'1079008526457073665','','',NULL,NULL,0,'importfile','233-260-550','Y','btn','常用资产/网络设备/导入','Y',NULL,NULL,NULL,NULL,'0'),(551,'导入',263,NULL,'1079008526457073665','','',NULL,NULL,0,'importfile','233-263-551','Y','btn','常用资产/存储设备/导入','Y',NULL,NULL,NULL,NULL,'0'),(552,'导入',269,NULL,'1079008526457073665','','',NULL,NULL,0,'importfile','233-269-552','Y','btn','常用资产/网点设备/导入','Y',NULL,NULL,NULL,NULL,'0'),(553,'导入',270,NULL,'1079008526457073665','','',NULL,NULL,0,'importfile','233-270-553','Y','btn','常用资产/安全设备/导入','Y',NULL,NULL,NULL,NULL,'0'),(554,'导入',273,NULL,'1079008526457073665','','',NULL,NULL,0,'importfile','233-273-554','Y','btn','常用资产/光纤交换机/导入','Y',NULL,NULL,NULL,NULL,'0'),(557,'数据导入',236,NULL,'1079008526457073665','','',NULL,NULL,0,'dataimport','236-557','Y','menu','系统设置/数据导入','Y',NULL,NULL,NULL,NULL,'0'),(559,'分析报表',0,NULL,'1079008526457073665','','fa fa-table',NULL,NULL,120,'report','559','Y','dir','分析报表','Y',NULL,NULL,NULL,NULL,'0'),(560,'资产分类汇总表',559,NULL,'1079008526457073665','','subtotal',NULL,NULL,1,'catreport','559-560','Y','menu','分析报表/资产分类汇总表','Y',NULL,NULL,NULL,NULL,'0'),(561,'公司部门汇总表',559,NULL,'1079008526457073665','','',NULL,NULL,0,'departsummary','559-561','Y','menu','分析报表/公司部门汇总表','Y',NULL,NULL,NULL,NULL,'0'),(562,'分类使用汇总表',559,NULL,'1079008526457073665','','',NULL,NULL,0,'Taxonomy','559-562','Y','menu','分析报表/分类使用汇总表','Y',NULL,NULL,NULL,NULL,'0'),(565,'维保到期统计表',559,NULL,'1079008526457073665','','',NULL,NULL,0,'wbexpire','559-565','Y','menu','分析报表/维保到期统计表','Y',NULL,NULL,NULL,NULL,'0'),(566,'员工资产统计表',559,NULL,'1079008526457073665','','',NULL,NULL,0,'employeezc','559-566','Y','menu','分析报表/员工资产统计表','Y',NULL,NULL,NULL,NULL,'0'),(567,'盘点管理',0,NULL,'1079008526457073665','','fa fa-columns',NULL,NULL,120,'pandian','567','Y','dir','盘点管理','Y',NULL,NULL,NULL,NULL,'0'),(571,'模版标签',236,NULL,'1079008526457073665','','',NULL,NULL,0,'labeltpl','236-571','Y','menu','系统设置/模版标签','Y',NULL,NULL,NULL,NULL,'0'),(572,'服务监控',2,NULL,'1','','',NULL,NULL,0,'servermonitor','2-572','Y','menu','系统管理/服务监控','Y',NULL,NULL,NULL,NULL,'0'),(574,'导出',439,NULL,'1079008526457073665','','',NULL,NULL,10,'exportfile','438-439-574','Y','btn','耗材管理/耗材资产/导出','Y',NULL,NULL,NULL,NULL,'0'),(575,'详情',266,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','265-266-575','Y','btn','维护管理/资产报修/详情','Y',NULL,NULL,NULL,NULL,'0'),(576,'完成报修',266,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','265-266-576','Y','btn','维护管理/资产报修/完成报修','Y',NULL,NULL,NULL,NULL,'0'),(577,'删除',523,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','522-523-577','Y','btn','资产变更/领用退库/删除','Y',NULL,NULL,NULL,NULL,'0'),(578,'主机管理',0,NULL,'1224134297569464321','','fa fa-server',NULL,NULL,10,'hmgr','578','Y','dir','主机管理','Y',NULL,NULL,NULL,NULL,'0'),(579,'数据库管理',0,NULL,'1224134297569464321','','fa fa-database',NULL,NULL,20,'dbmgr','579','Y','dir','数据库管理','Y',NULL,NULL,NULL,NULL,'0'),(583,'主机列表',578,NULL,'1224134297569464321','','',NULL,NULL,0,'hlist','578-583','Y','menu','主机管理/主机列表','Y',NULL,NULL,NULL,NULL,'0'),(584,'数据库列表',579,NULL,'1224134297569464321','','',NULL,NULL,0,'dblist','579-584','Y','menu','数据库管理/数据库列表','Y',NULL,NULL,NULL,NULL,'0'),(585,'数据统计',0,NULL,'1224134297569464321','','',NULL,NULL,0,'dtj','585','Y','dir','数据统计','Y',NULL,NULL,NULL,NULL,'0'),(586,'查询',584,NULL,'1224134297569464321','','',NULL,NULL,0,'select','579-584-586','Y','btn','数据库管理/数据库列表/查询','Y',NULL,NULL,NULL,NULL,'0'),(587,'新增',584,NULL,'1224134297569464321','','',NULL,NULL,0,'insert','579-584-587','Y','btn','数据库管理/数据库列表/新增','Y',NULL,NULL,NULL,NULL,'0'),(588,'更新',584,NULL,'1224134297569464321','','',NULL,NULL,0,'update','579-584-588','Y','btn','数据库管理/数据库列表/更新','Y',NULL,NULL,NULL,NULL,'0'),(589,'删除',584,NULL,'1224134297569464321','','',NULL,NULL,0,'remove','579-584-589','Y','btn','数据库管理/数据库列表/删除','Y',NULL,NULL,NULL,NULL,'0'),(590,'导出',584,NULL,'1224134297569464321','','',NULL,NULL,0,'exportfile','579-584-590','Y','btn','数据库管理/数据库列表/导出','Y',NULL,NULL,NULL,NULL,'0'),(591,'导入',584,NULL,'1224134297569464321','','',NULL,NULL,0,'importfile','579-584-591','Y','btn','数据库管理/数据库列表/导入','Y',NULL,NULL,NULL,NULL,'0'),(592,'查询',583,NULL,'1224134297569464321','','',NULL,NULL,0,'select','578-583-592','Y','btn','主机管理/主机列表/查询','Y',NULL,NULL,NULL,NULL,'0'),(593,'新增',583,NULL,'1224134297569464321','','',NULL,NULL,0,'insert','578-583-593','Y','btn','主机管理/主机列表/新增','Y',NULL,NULL,NULL,NULL,'0'),(594,'更新',583,NULL,'1224134297569464321','','',NULL,NULL,0,'update','578-583-594','Y','btn','主机管理/主机列表/更新','Y',NULL,NULL,NULL,NULL,'0'),(595,'删除',583,NULL,'1224134297569464321','','',NULL,NULL,0,'remove','578-583-595','Y','btn','主机管理/主机列表/删除','Y',NULL,NULL,NULL,NULL,'0'),(596,'导出',583,NULL,'1224134297569464321','','',NULL,NULL,0,'exportfile','578-583-596','Y','btn','主机管理/主机列表/导出','Y',NULL,NULL,NULL,NULL,'0'),(597,'导入',583,NULL,'1224134297569464321','','',NULL,NULL,0,'importfile','578-583-597','Y','btn','主机管理/主机列表/导入','Y',NULL,NULL,NULL,NULL,'0'),(598,'主机数据库',585,NULL,'1224134297569464321','','',NULL,NULL,0,'hd','585-598','Y','menu','数据统计/主机数据库','Y',NULL,NULL,NULL,NULL,'0'),(599,'资产盘点',567,NULL,'1079008526457073665','','',NULL,NULL,0,'zcpd','567-599','Y','menu','盘点管理/资产盘点','Y',NULL,NULL,NULL,NULL,'0'),(600,'我的借用归还',470,NULL,'1079008526457073665','','',NULL,NULL,10,'myjy','470-600','Y','menu','我的事件/我的借用归还','Y',NULL,NULL,NULL,NULL,'0'),(601,'我的报修',470,NULL,'1079008526457073665','','',NULL,NULL,4,'mybx','470-601','Y','menu','我的事件/我的报修','Y',NULL,NULL,NULL,NULL,'0'),(602,'详情',234,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','233-234-602','Y','btn','常用资产/服务器/详情','Y',NULL,NULL,NULL,NULL,'0'),(603,'详情',260,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','233-260-603','Y','btn','常用资产/网络设备/详情','Y',NULL,NULL,NULL,NULL,'0'),(604,'详情',263,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','233-263-604','Y','btn','常用资产/存储设备/详情','Y',NULL,NULL,NULL,NULL,'0'),(605,'详情',269,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','233-269-605','Y','btn','常用资产/网点设备/详情','Y',NULL,NULL,NULL,NULL,'0'),(606,'详情',270,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','233-270-606','Y','btn','常用资产/安全设备/详情','Y',NULL,NULL,NULL,NULL,'0'),(607,'详情',273,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','233-273-607','Y','btn','常用资产/光纤交换机/详情','Y',NULL,NULL,NULL,NULL,'0'),(609,'详情',419,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','418-419-609','Y','btn','备件管理/备品备件/详情','Y',NULL,NULL,NULL,NULL,'0'),(610,'详情',452,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','428-452-610','Y','btn','资产管理/资产入库/详情','Y',NULL,NULL,NULL,NULL,'0'),(611,'详细',439,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','438-439-611','Y','btn','耗材管理/耗材资产/详细','Y',NULL,NULL,NULL,NULL,'0'),(612,'搜索',600,NULL,'1079008526457073665','','',NULL,NULL,0,'search','470-600-612','Y','btn','我的事件/我的借用归还/搜索','Y',NULL,NULL,NULL,NULL,'0'),(613,'借用',600,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','470-600-613','Y','btn','我的事件/我的借用归还/借用','Y',NULL,NULL,NULL,NULL,'0'),(614,'更新',600,NULL,'1079008526457073665','','',NULL,NULL,0,'update','470-600-614','Y','btn','我的事件/我的借用归还/更新','Y',NULL,NULL,NULL,NULL,'0'),(615,'删除',600,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','470-600-615','Y','btn','我的事件/我的借用归还/删除','Y',NULL,NULL,NULL,NULL,'0'),(616,'送审',600,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','470-600-616','Y','btn','我的事件/我的借用归还/送审','Y',NULL,NULL,NULL,NULL,'0'),(617,'归还',600,NULL,'1079008526457073665','','',NULL,NULL,0,'act2','470-600-617','Y','btn','我的事件/我的借用归还/归还','Y',NULL,NULL,NULL,NULL,'0'),(618,'搜索',601,NULL,'1079008526457073665','','',NULL,NULL,0,'search','470-601-618','Y','btn','我的事件/我的报修/搜索','Y',NULL,NULL,NULL,NULL,'0'),(619,'申请保修',601,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','470-601-619','Y','btn','我的事件/我的报修/申请保修','Y',NULL,NULL,NULL,NULL,'0'),(620,'更新',601,NULL,'1079008526457073665','','',NULL,NULL,0,'update','470-601-620','Y','btn','我的事件/我的报修/更新','Y',NULL,NULL,NULL,NULL,'0'),(621,'删除',601,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','470-601-621','Y','btn','我的事件/我的报修/删除','Y',NULL,NULL,NULL,NULL,'0'),(622,'完成报修',601,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','470-601-622','Y','btn','我的事件/我的报修/完成报修','Y',NULL,NULL,NULL,NULL,'0'),(623,'详情',601,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','470-601-623','Y','btn','我的事件/我的报修/详情','Y',NULL,NULL,NULL,NULL,'0'),(624,'详情',523,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','522-523-624','Y','btn','资产变更/领用退库/详情','Y',NULL,NULL,NULL,NULL,'0'),(625,'详情',524,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','522-524-625','Y','btn','资产变更/借用归还/详情','Y',NULL,NULL,NULL,NULL,'0'),(626,'详情',525,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','522-525-626','Y','btn','资产变更/资产调拨/详情','Y',NULL,NULL,NULL,NULL,'0'),(628,'资产报废',522,NULL,'1079008526457073665','','',NULL,NULL,0,'zcbf','522-628','Y','menu','资产变更/资产报废','Y',NULL,NULL,NULL,NULL,'0'),(630,'入库单',438,NULL,'1079008526457073665','','',NULL,NULL,0,'hcin','438-630','Y','menu','耗材管理/入库单','Y',NULL,NULL,NULL,NULL,'0'),(631,'出库单',438,NULL,'1079008526457073665','','',NULL,NULL,0,'hcout','438-631','Y','menu','耗材管理/出库单','Y',NULL,NULL,NULL,NULL,'0'),(632,'调拨单',438,NULL,'1079008526457073665','','',NULL,NULL,0,'hcdb','438-632','Y','menu','耗材管理/调拨单','Y',NULL,NULL,NULL,NULL,'0'),(633,'安全库存',438,NULL,'1079008526457073665','','',NULL,NULL,0,'hcsecstore','438-633','Y','menu','耗材管理/安全库存','Y',NULL,NULL,NULL,NULL,'0'),(635,'数据备份',2,NULL,'1','','',NULL,NULL,0,'databackup','2-635','Y','menu','系统管理/数据备份','Y',NULL,NULL,NULL,NULL,'0'),(636,'作废',266,NULL,'1079008526457073665','','',NULL,NULL,0,'act2','265-266-636','Y','btn','维护管理/资产报修/作废','Y',NULL,NULL,NULL,NULL,'0'),(637,'作废',601,NULL,'1079008526457073665','','',NULL,NULL,0,'act2','470-601-637','Y','btn','我的事件/我的报修/作废','Y',NULL,NULL,NULL,NULL,'0'),(638,'实物信息变更',522,NULL,'1079008526457073665','','',NULL,NULL,0,'changephyzc','522-638','Y','menu','资产变更/实物信息变更','Y',NULL,NULL,NULL,NULL,'0'),(639,'维保信息变更',522,NULL,'1079008526457073665','','',NULL,NULL,0,'changemaintenance','522-639','Y','menu','资产变更/维保信息变更','Y',NULL,NULL,NULL,NULL,'0'),(640,'财务信息变更',522,NULL,'1079008526457073665','','',NULL,NULL,0,'changefinancial','522-640','Y','menu','资产变更/财务信息变更','Y',NULL,NULL,NULL,NULL,'0'),(641,'财务管理',0,NULL,'1079008526457073665','','',NULL,NULL,20,'financialmgr','641','Y','dir','财务管理','Y',NULL,NULL,NULL,NULL,'0'),(642,'资产折旧',641,NULL,'1079008526457073665','','',NULL,NULL,0,'depreciation','641-642','Y','menu','财务管理/资产折旧','Y',NULL,NULL,NULL,NULL,'0'),(643,'我的资产申请',470,NULL,'1079008526457073665','','',NULL,NULL,11,'myapplyzc','470-643','Y','menu','我的事件/我的资产申请','Y',NULL,NULL,NULL,NULL,'0'),(644,'我的领用退库',470,NULL,'1079008526457073665','','',NULL,NULL,12,'myly','470-644','Y','menu','我的事件/我的领用退库','Y',NULL,NULL,NULL,NULL,'0'),(645,'详情',600,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','470-600-645','Y','btn','我的事件/我的借用归还/详情','Y',NULL,NULL,NULL,NULL,'0'),(646,'搜索',644,NULL,'1079008526457073665','','',NULL,NULL,0,'search','470-644-646','Y','btn','我的事件/我的领用退库/搜索','Y',NULL,NULL,NULL,NULL,'0'),(647,'领用',644,NULL,'1079008526457073665','','',NULL,NULL,0,'insert','470-644-647','Y','btn','我的事件/我的领用退库/领用','Y',NULL,NULL,NULL,NULL,'0'),(648,'更新',644,NULL,'1079008526457073665','','',NULL,NULL,0,'update','470-644-648','Y','btn','我的事件/我的领用退库/更新','Y',NULL,NULL,NULL,NULL,'0'),(649,'删除',644,NULL,'1079008526457073665','','',NULL,NULL,0,'remove','470-644-649','Y','btn','我的事件/我的领用退库/删除','Y',NULL,NULL,NULL,NULL,'0'),(650,'送审',644,NULL,'1079008526457073665','','',NULL,NULL,0,'act1','470-644-650','Y','btn','我的事件/我的领用退库/送审','Y',NULL,NULL,NULL,NULL,'0'),(651,'退库',644,NULL,'1079008526457073665','','',NULL,NULL,0,'act2','470-644-651','Y','btn','我的事件/我的领用退库/退库','Y',NULL,NULL,NULL,NULL,'0'),(652,'详情',644,NULL,'1079008526457073665','','',NULL,NULL,0,'detail','470-644-652','Y','btn','我的事件/我的领用退库/详情','Y',NULL,NULL,NULL,NULL,'0'),(654,'数据中心',0,NULL,'1079008526457073665','','',NULL,NULL,10,'datacenter','654','Y','dir','数据中心','Y',NULL,NULL,NULL,NULL,'0'),(655,'机柜视图',654,NULL,'1079008526457073665','','',NULL,NULL,0,'rackview','654-655','Y','menu','数据中心/机柜视图','Y',NULL,NULL,NULL,NULL,'0'),(656,'首页',0,NULL,'1286633577483616257','','fa fa-tachometer',NULL,NULL,1,'zbxindex','656','Y','menu','首页','Y',NULL,NULL,NULL,NULL,'0'),(657,'资源管理',0,NULL,'1','','',NULL,NULL,10,'zbxresmgr','657','Y','dir','资源管理','Y',NULL,NULL,NULL,NULL,'0'),(658,'资源管理',0,NULL,'1286633577483616257','','fa fa-bars',NULL,NULL,10,'zbxresmgr','658','Y','dir','资源管理','Y',NULL,NULL,NULL,NULL,'0'),(660,'指标管理',0,NULL,'1286633577483616257','','',NULL,NULL,20,'zbxzbrep','660','Y','dir','指标管理','Y',NULL,NULL,NULL,NULL,'0'),(661,'告警管理',0,NULL,'1286633577483616257','','fa fa-exclamation-triangle',NULL,NULL,30,'zbxalarmmgr','661','Y','dir','告警管理','Y',NULL,NULL,NULL,NULL,'0'),(662,'资源列表',658,NULL,'1286633577483616257','','',NULL,NULL,1,'host','658-662','Y','menu','资源管理/资源列表','Y',NULL,NULL,NULL,NULL,'0'),(665,'图形管理',658,NULL,'1286633577483616257','','',NULL,NULL,20,'graphmgr','658-665','Y','menu','资源管理/图形管理','Y',NULL,NULL,NULL,NULL,'0'),(666,'指标导出',660,NULL,'1286633577483616257','','',NULL,NULL,5,'zbexport','660-666','Y','menu','指标管理/指标导出','Y',NULL,NULL,NULL,NULL,'0'),(667,'巡检报告',660,NULL,'1286633577483616257','','',NULL,NULL,10,'xjrep','660-667','Y','menu','指标管理/巡检报告','Y',NULL,NULL,NULL,NULL,'0'),(668,'实时告警',661,NULL,'1286633577483616257','','',NULL,NULL,5,'alarmreal','661-668','Y','menu','告警管理/实时告警','Y',NULL,NULL,NULL,NULL,'0'),(669,'告警统计',661,NULL,'1286633577483616257','','',NULL,NULL,10,'alarmtj','661-669','Y','menu','告警管理/告警统计','Y',NULL,NULL,NULL,NULL,'0'),(670,'最新数据',658,NULL,'1286633577483616257','','',NULL,NULL,12,'lastdata','658-670','Y','menu','资源管理/最新数据','Y',NULL,NULL,NULL,NULL,'0'),(671,'资源配置',0,NULL,'1286633577483616257','','fa fa-cog',NULL,NULL,10,'zbxresconf','671','Y','dir','资源配置','Y',NULL,NULL,NULL,NULL,'0'),(672,'主机组',671,NULL,'1286633577483616257','','',NULL,NULL,0,'hostgroup','671-672','Y','menu','资源配置/主机组','Y',NULL,NULL,NULL,NULL,'0'),(673,'模版',671,NULL,'1286633577483616257','','',NULL,NULL,0,'tpl','671-673','Y','menu','资源配置/模版','Y',NULL,NULL,NULL,NULL,'0'),(674,'通知记录',661,NULL,'1286633577483616257','','',NULL,NULL,0,'noticerec','661-674','Y','menu','告警管理/通知记录','Y',NULL,NULL,NULL,NULL,'0'),(675,'报表管理',0,NULL,'1286633577483616257','','fa fa-table',NULL,NULL,15,'zbxrep','675','Y','dir','报表管理','Y',NULL,NULL,NULL,NULL,'0'),(676,'监控日报',675,NULL,'1286633577483616257','','',NULL,NULL,0,'dailyrep','675-676','Y','menu','报表管理/监控日报','Y',NULL,NULL,NULL,NULL,'0'),(677,'可视化管理',0,NULL,'1286633577483616257','','fa fa-bar-chart',NULL,NULL,15,'zbxviewmgr','677','Y','dir','可视化管理','Y',NULL,NULL,NULL,NULL,'0'),(678,'投屏视图',677,NULL,'1286633577483616257','','',NULL,NULL,0,'tpview','677-678','Y','menu','可视化管理/投屏视图','Y',NULL,NULL,NULL,NULL,'0'),(679,'对象分组',671,NULL,'1286633577483616257','','',NULL,NULL,0,'objcate','671-679','Y','menu','资源配置/对象分组','Y',NULL,NULL,NULL,NULL,'0'),(680,'全部告警',661,NULL,'1286633577483616257','','',NULL,NULL,0,'allalarm','661-680','Y','menu','告警管理/全部告警','Y',NULL,NULL,NULL,NULL,'0'),(681,'对象管理',671,NULL,'1286633577483616257','','',NULL,NULL,0,'objmgr','671-681','Y','menu','资源配置/对象管理','Y',NULL,NULL,NULL,NULL,'0'),(683,'折旧策略',236,NULL,'1079008526457073665','','',NULL,NULL,0,'zjstrategy','236-683','Y','menu','系统设置/折旧策略','Y',NULL,NULL,NULL,NULL,'0'),(684,'资产总值汇总表',559,NULL,'1079008526457073665','','',NULL,NULL,0,'totalassets','559-684','Y','menu','分析报表/资产总值汇总表','Y',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_menus_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_modules_item`
--

DROP TABLE IF EXISTS `sys_modules_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_modules_item` (
  `module_item_id` varchar(50) NOT NULL,
  `module_id` varchar(50) DEFAULT NULL,
  `ct` text,
  `status` varchar(10) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`module_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_modules_item`
--

LOCK TABLES `sys_modules_item` WRITE;
/*!40000 ALTER TABLE `sys_modules_item` DISABLE KEYS */;
INSERT INTO `sys_modules_item` VALUES ('00468eb0-d1e6-42a1-a859-f1f491517964','186','/api/mn/queryMnServiceNodes.do','Y','url'),('01eb1456-ebec-4290-abf1-524484db2ed4','186','/api/mn/queryServiceNodeMetric.do','Y','url'),('03337397-5d22-4789-8b4f-39b29dcdcf22','206','/api/wx/queryWebOAuth.do','Y','url'),('035acf3c-f6f4-4121-821a-307b5638750a','139','/api/module/queryModuleItemMap.do','Y','url'),('05604135-de22-4dfb-a866-626a0acaa94b','91','/api/sysUserInfo/my/modifypwd.do','Y','url'),('0599b9d1-e394-4c1a-8c1e-c8d8576d07bb','137','/api/sysRoleInfo/insertOrUpdate.do','Y','url'),('0992ad9b-540a-4d53-96db-bad01b6f3d2d','22','/api/sysDictItem/insert.do','Y','url'),('0b202536-4d2c-4fc5-8e15-1f03fee13cc7','180','/api/mn/queryMetricById.do','Y','url'),('0d7120e7-d5aa-49cb-9dc6-28d20d153deb','123','/api/categoryF/queryTreeList.do','Y','url'),('0e7b34da-b765-468d-ae4f-fc80c6ffaf89','156','/api/ctStoreSql/deleteById.do','Y','url'),('0f216ca8-38dc-4fc4-906e-be71b4d44b77','152','/api/shop/queryShopById.do','Y','url'),('0f69c22b-2e9a-448d-a747-cdcb4036d055','123','/api/categoryF/rootCatUpdate.do','Y','url'),('12d5eca6-6d11-4a28-bfca-26c7131ce6e5','143','/api/schedule/queryJobs.do','Y','url'),('1378c591-7996-4573-be12-202e7cfc4c8a','198','/api/sysMenus/updateById.do','Y','url'),('14098ce9-d15c-4da3-8eb6-55bd591bcd61','185','/api/mn/metricGroupAddMetrics.do','Y','url'),('157773d7-ae5b-4f92-a100-f34b65e05548','453','/api/ctCategoryRoot/insert.do','Y','url'),('16429fad-d0c9-434d-a1e0-3c55ebc9ff98','180','/api/mn/delMetric.do','Y','url'),('169178c0-9d72-4396-930d-66f6e12f1771','17','/api/hrm/orgNodeQuery.do','Y','url'),('178cf304-a3a2-4edd-9f38-b247ebf21bed','57','/api/sysParams/insertOrUpdate.do','Y','url'),('18c95fbc-30b4-4f34-ba74-99dd399572f5','174','/api/sftp/exeCommand.do','Y','url'),('1bdb1f89-3c79-4ab2-b444-ce6f1a306161','198','/api/sysMenus/selectById.do','Y','url'),('1d4a2c47-6800-4bcb-b97e-df01f2963c1c','120','/api/brand/brandSave.do','Y','url'),('1e718382-5337-4507-ab26-f1cb1e878b4b','86','/api/news/deleteNews.do','Y','url'),('1f36dfc3-2dbb-4174-891e-81f80a11dc97','124','/api/categoryB/rename.do','Y','url'),('207b20de-63b6-4881-be59-49517290542d','77','/api/ctCategroy/queryCategoryById.do','Y','url'),('20899ad7-5a70-4d94-b5ce-78aaa1a79df8','119','/api/product/prodOffOn.do','Y','url'),('20c151b7-14bb-47f3-a5c3-bdcd0431ea52','453','/api/ctCategoryRoot/insertOrUpdate.do','Y','url'),('2187e971-a7a4-4f6f-a807-a9fe41246e35','156','/api/ctStoreSql/insert.do','Y','url'),('232ca4ab-a8bf-485e-9f76-c4ca33fefa53','190','/api/wx/sysncMenu.do','Y','url'),('2342bcd5-ed34-4b51-92b7-9ded25a86b33','70','/api/sysFileConf/insertOrUpdate.do','Y','url'),('247cfc22-9c4c-41c1-8aed-ccba5162cfbb','119','/api/product/prodDelete.do','Y','url'),('2647812e-03d1-444c-b988-b3f2ed5d9ecb','22','/api/sysDict/selectById.do','Y','url'),('277982e3-3a9d-427b-a541-c5f50c122100','22','/api/sysDictItem/selectList.do','Y','url'),('27892e71-953d-49c4-a548-e6892fc8895d','192','/api/wx/queryMessages.do','Y','url'),('27a523ba-10ed-4d64-835b-9b2e17dc6011','86','/api/user/queryGroup.do','Y','url'),('29a5d8f3-24e9-434d-b386-e0247bec9a94','22','/api/sysDictItem/insertOrUpdate.do','Y','url'),('2b24f9b1-1861-48f8-a5e8-abb1c5a1f060','16','/api/hrm/orgNodeTreeQuery.do','Y','url'),('2e4ed472-5539-4c24-8d59-4ae37e88ba4c','119','/api/product/prodQueryByCat.do','Y','url'),('3012328f-0fd9-4f00-827c-9a42b3ab30fd','193','/api/wx/queryImageTextMessageById.do','Y','url'),('314dec2a-4419-4363-8b7c-eb1b9de762fd','143','/api/schedule/enablejob.do','Y','url'),('31710b41-9458-495f-a5ae-001695639a43','453','/api/ctCategoryRoot/updateById.do','Y','url'),('3247e80d-3102-48f4-b1ec-404535d8b1a0','96','/api/sysLogAccess/selectPage.do','Y','url'),('355a61f5-65a3-46c1-b34d-b8b19c62db7e','143','/api/schedule/removejob.do','Y','url'),('360798b0-5690-47a0-8e79-8786231ab55d','78','/api/api/queryApi.do','Y','url'),('368a5936-3b03-4375-9957-6787bd64ca28','183','/api/mn/queryServicById.do','Y','url'),('380ce2c7-0022-4150-b7a8-6f82cf7caceb','193','/api/wx/queryImageTextMessages.do','Y','url'),('3843d2e0-8288-4fa2-8458-682e0583b364','157','/api/system/getOnlineSession.do','Y','url'),('399eccd6-82aa-4135-8f75-86cae52ea0f0','138','/api/menu/addNode.do','Y','url'),('3a7a99f7-3fc1-45a1-8d8d-2260347c9f63','198','/api/sysMenus/insertOrUpdate.do','Y','url'),('3b50b9a5-7c6a-466f-815f-7965170311c7','22','/api/sysDict/ext/selectList.do','Y','url'),('3d4c6295-f3b8-40c8-bbd9-e0319761885f','124','/api/categoryB/catAttrValueUpdate.do','Y','url'),('3e17a12a-fdc8-415d-a4b0-d546446381c6','120','/api/brand/brandDelete.do','Y','url'),('3e74c75a-11b0-4b87-87cf-f5fc0545dfc5','119','/api/product/prodQueryBySpu.do','Y','url'),('3fda1f5e-b37a-45e8-aed5-eccf15b9dcd7','70','/api/sysFileConf/updateById.do','Y','url'),('41157364-7c03-4a49-8e71-88ca5203d122','156','/api/ctStoreSql/selectById.do','Y','url'),('4246dff6-56fe-41b8-b3da-b7e88932e2f5','136','/api/sysUserInfo/updateById.do','Y','url'),('4260016d-c222-46ad-aa1e-d5fe06cb80c5','138','/api/menu/deleteNode.do','Y','url'),('43cb35c3-dad8-4f05-95f2-a5b58bdd1c50','181','/api/mn/queryMetricGroup.do','Y','url'),('446bc0d6-52d2-4a0e-b3ad-71d3ce86e41c','453','/api/ctCategoryRoot/insertOrUpdate.do','Y','url'),('458b1bd8-4801-4d08-8346-2774f0a0e06d','137','/api/sysRoleInfo/insert.do','Y','url'),('47a4604f-f1e2-4015-9de5-9f4fe5572270','192','/api/wx/saveMessage.do','Y','url'),('4840267d-22cb-4c36-b1b6-18f228eaded0','118','/api/product/prodPublish.do','Y','url'),('48d4a5b8-687c-4a06-b2df-10498b2a0313','136','/api/sysRoleInfo/roleQueryFormatKV.do','Y','url'),('497d254e-5b15-4815-b9cb-f92236c6c1f1','152','/api/shop/queryShop.do','Y','url'),('4adcb8c9-1912-495d-857c-4c235ecacbb8','176','/api/sysApi/system/removeCacheKey.do','Y','url'),('4b43622e-d4ee-4318-bd4e-2b47304748df','22','/api/sysDictItem/updateById.do','Y','url'),('4cbd0f5d-e545-4eba-bf74-dce1225dda9b','185','/api/mn/metricGroupNeedMetrics.do','Y','url'),('4d31040a-886d-459e-a69c-3baef7298750','77','/api/ctCategroy/addCategory.do','Y','url'),('4d56daea-eef3-4e88-96cf-d705ae32aac6','183','/api/mn/mnServiceNeedAddNodes.do','Y','url'),('4de312f5-cc5b-4ff0-b29e-76a16110f274','176','/api/sysApi/system/queryCacheKeys.do','Y','url'),('4e280513-0766-44c3-a942-8a70c8b92ced','119','/api/categoryB/prodPublishCatAttrList.do','Y','url'),('4e43f46a-8d72-44ed-aaa5-6c1e4896f5d7','156','/api/ctStoreSql/updateById.do','Y','url'),('4f030018-708b-4c30-8c40-e84377114c31','88','/api/company/updateCompany.do','Y','url'),('4f7b40e3-0b74-4299-959a-39dab7862294','190','/api/wx/createMenuToWx.do','Y','url'),('5187a5a2-51df-4249-b444-c434ce4cc986','200','/api/prod/saveDl.do','Y','url'),('564ef0a3-b1ba-4552-9f3d-8bd8150318f6','57','/api/sysParams/selectList.do','Y','url'),('582062b1-cd0e-4cb1-a05f-54ca3360c7f5','195','/api/wx/queryScs.do','Y','url'),('5931783b-8df8-457b-b87a-4737b73ba8b9','190','/api/wx/queryWxApps.do','Y','url'),('5991163c-37dc-42a0-b697-52994f306efc','77','/api/ctCategroy/updateCategory.do','Y','url'),('5b2c744b-5fc8-4b87-85d8-3052bda6f89a','154','/api/sysUserInfo/my/saveDefMenus.do','Y','url'),('5b87ff9f-f0d0-4f7b-8c02-beecc50cf015','139','/api/sysMenus/selectList.do','Y','url'),('5e1c0950-ddb9-467a-92fe-b8cdf2588aad','118','/api/categoryB/prodPublishCatList.do','Y','url'),('5ea1c14a-d2d6-4864-80ef-ee53bf389dbe','174','/api/node/deleteNode.do','Y','url'),('5f61a739-949c-474c-ad35-103827114c88','124','/api/categoryB/catAttrValueDel.do','Y','url'),('5f9edccb-e46d-4d85-9a4b-5a6d363d56c5','176','/api/sysApi/system/queryCacheName.do','Y','url'),('5fa7b2e7-3f3d-4ef6-aa4e-11d2b57b0a0c','453','/api/ctCategoryRoot/selectList.do','Y','url'),('604e6e8f-1f35-4403-bb06-ebd9df5628f0','124','/api/categoryB/delete.do','Y','url'),('611502c7-8771-4c91-9a7f-f33818fa27fa','174','/api/term/setCurrentMachine.do','Y','url'),('6308313b-9db3-4b4f-8795-452157094479','183','/api/mn/mnServiceQueryNodeById.do','Y','url'),('63690525-1eb0-4716-a720-ad6836912837','22','/api/sysDictItem/selectDictItemByDict.do','Y','url'),('6582762d-2539-4c60-9487-3f0864428a58','124','/api/ctCategroy/queryCategoryById.do','Y','url'),('68780b0c-799c-4cdc-9890-889bfa866df8','56','/api/hrm/orgQueryLevelList.do','Y','url'),('68d4acc4-6d09-4d22-a4ec-f39538694408','143','/api/schedule/disablejob.do','Y','url'),('69c9501d-b3e0-4c4a-a9e9-718d5703d0a6','138','/api/sysRoleInfo/selectList.do','Y','url'),('6a07bae2-201b-48b4-ac0d-544baa792339','138','/api/menu/treeNodeRoleMap.do','Y','url'),('6cc9c096-c44b-445a-bd84-16e65d2f4209','124','/api/categoryB/prodPublishCatList.do','Y','url'),('6eab7141-e54c-4ff7-a580-6ac77a866b89','453','/api/ctCategoryRoot/deleteById.do','Y','url'),('6fdd50fd-c979-4a24-836c-c66a2611735c','137','/api/sysRoleInfo/roleQueryFormatKV.do','Y','url'),('6ff6a09c-0a08-4f38-9048-e6b30f571ea8','453','/api/ctCategoryRoot/selectById.do','Y','url'),('704d5f9d-d397-4576-aa53-5530283b6e18','56','/api/hrm/employeeUpdate.do','Y','url'),('727276c4-3b05-4a79-9bcf-ba5113562288','183','/api/mn/queryMnServiceNodes.do','Y','url'),('72fd504b-b66f-4f8d-91c3-21c32b9a1f60','181','/api/mn/queryMetricGroupById.do','Y','url'),('738378c4-d9ad-4347-8960-d35212747816','138','/api/sysMenusNode/queryMenuNodesForStageSetting.do','Y','url'),('769ccc9e-5547-4de2-8dc6-e7980795ffc0','57','/api/sysParams/deleteById.do','Y','url'),('76bb0f23-56b4-40ef-919b-42095350b4b4','125','/api/categoryF/rootCatDel.do','Y','url'),('779f5bb7-ba24-49b7-b476-ef090c6866c4','138','/api/sysMenus/selectList.do','Y','url'),('7a3be3d4-f5b4-42b4-82fb-cf4f02125086','17','/api/hrm/orgQuery.do','Y','url'),('7c12cac6-bd1a-4c22-b8be-305fd1d7959f','156','/api/ctStoreSql/insertOrUpdate.do','Y','url'),('7d26a2b8-518a-40bc-a500-f8edb84dfab8','22','/api/sysDictItem/deleteById.do','Y','url'),('7d40ef28-2f6a-4972-9638-8f06b5dbf013','183','/api/mn/mnServiceQueryNodesById.do','Y','url'),('7da7b2be-e9e7-4b7b-bd45-3bb1cabad2a0','198','/api/sysMenus/selectList.do','Y','url'),('7e78e91e-133e-442c-86be-d2e6b153d1c5','204','/api/user/userDelete.do','Y','url'),('7ef32c5b-7db7-4c1f-bd1e-730bae8da27b','152','/api/shop/saveShop.do','Y','url'),('7f794968-f77a-4f51-aece-e5b29b6e1371','133','/api/sysUserInfo/selectPage.do','Y','url'),('8055485d-69dc-4473-a4ab-23f33e039d23','17','/api/hrm/orgNodeSave.do','Y','url'),('8147bdf4-53df-46e2-991b-d5ea74d8f7f6','198','/api/sysMenus/insert.do','Y','url'),('82480241-a3df-4ce7-b21a-def90bf0c241','137','/api/sysRoleInfo/updateById.do','Y','url'),('83431f8d-619d-4a03-b28d-6dc234ca60ad','186','/api/mn/saveServiceNodeMetric.do','Y','url'),('838892d9-2bbb-493e-a7db-86931f8c5a73','17','/api/hrm/orgNodeTreeQuery.do','Y','url'),('83a22a17-9c94-43e2-b834-09a5d21c8066','156','/api/ctStoreSql/selectList.do','Y','url'),('8536b402-dafd-47ec-b4d3-7e38de93a6ee','120','/api/brand/brandQueryById.do','Y','url'),('861bf1b6-2c9e-4c96-b86f-0f6548947718','182','/api/mn/saveService.do','Y','url'),('8692599e-1d72-47ee-8cb4-1202ed476006','206','/api/wx/delWebOAuth.do','Y','url'),('875cd850-20e3-49dd-bd45-f563d624d9ed','138','/api/menu/updateNode.do','Y','url'),('885551d1-e985-44f7-8f5f-bb04a1e42a18','139','/api/menu/deleteNode.do','Y','url'),('8ac55b76-844d-437d-aeef-569a1af1dced','124','/api/categoryB/catAttrAdd.do','Y','url'),('8bb48a87-ef9d-40dc-a7d3-e81715c6dfa9','181','/api/mn/delMetricGroup.do','Y','url'),('8ce63531-5bef-4162-927f-999bf4cba471','152','/api/shop/deleteShop.do','Y','url'),('8d36da8e-3039-4407-9520-8021423a38a0','453','/api/ctCategoryRoot/insert.do','Y','url'),('8d596e1b-4edb-4f43-b083-0a9f7b5b6806','190','/api/wx/queryWxAppById.do','Y','url'),('8e335b04-bc93-4bdd-889b-85faefee25ba','174','/api/node/queryNodeById.do','Y','url'),('8e576268-d0a6-4c13-9cc1-ee607deb5c66','123','/api/categoryF/rename.do','Y','url'),('8eaf4b47-9d27-40bf-9efd-d9e7d7f2d779','143','/api/schedule/resumejob.do','Y','url'),('8f95e460-9e54-485d-b45b-66820a37786d','57','/api/sysParams/selectById.do','Y','url'),('8fd55882-ad13-4ddd-b3d7-f3041d37aa2e','185','/api/mn/queryMetricGroupMetrics.do','Y','url'),('8fea6d11-43a2-4af4-b8a9-c94d7d44200e','201','/api/prod/delXl.do','Y','url'),('919c6697-7767-44b7-a730-f999f1141a83','22','/api/sysDict/insertOrUpdate.do','Y','url'),('932e3ac9-1569-4560-b40c-e8ec45b13454','118','/api/categoryB/prodPublishCatAttrList.do','Y','url'),('93edad67-0b75-4600-9bb8-ccbcc0262cda','183','/api/mn/mnServiceDelNode.do','Y','url'),('95d3133a-ab41-4556-9b44-56f4020c6d6f','124','/api/categoryB/add.do','Y','url'),('95e260cc-39d3-479c-8849-72711b31ab34','124','/api/categoryB/catAttrQueryById.do','Y','url'),('98db8c0d-3ea8-4e0d-a108-0ecebf52fefa','16','/api/hrm/orgQuery.do','Y','url'),('9a15f001-0d61-4743-a198-3aa15107f04d','22','/api/sysDict/insert.do','Y','url'),('9b8c1e44-eef9-4be4-8233-42ed42d597f7','180','/api/mn/queryMetric.do','Y','url'),('9eaef7a8-5952-4ba0-a777-e473d8d23810','17','/api/hrm/orgNodeDelete.do','Y','url'),('9ec3fbf6-b8a9-47ea-b588-5b7ac346fe59','453','/api/ctCategoryRoot/deleteById.do','Y','url'),('9f46cc53-5b29-49e2-b995-b79056f79b32','181','/api/mn/saveMetricGroup.do','Y','url'),('a1739a47-16e2-43d9-8915-0b2847a0882a','193','/api/wx/deleteImageTextMessage.do','Y','url'),('a190aeba-00bb-4cad-b963-d508cfa2779c','198','/api/sysMenus/deleteById.do','Y','url'),('a45891cb-7bdc-4286-ab57-91b14ba10698','201','/api/prod/saveXl.do','Y','url'),('a5269991-5f98-4efb-b1a2-e20665b8c378','185','/api/mn/queryMetricGroup.do','Y','url'),('a589edb9-28a9-45a2-bcdc-aae60d164c28','77','/api/ctCategroy/queryRootCategory.do','Y','url'),('a7c76742-d1e4-4553-a46c-0a0768e63e7b','137','/api/sysRoleInfo/deleteById.do','Y','url'),('a87e73f0-f3ec-4ff7-9dfb-a44f99cd855c','135','/api/sysUserInfo/addUser.do','Y','url'),('aae40c5a-5d97-49a9-a837-42e627f8572e','56','/api/hrm/employeeQueryList.do','Y','url'),('ab50ed8c-3528-4b38-bf19-3e2cd5670557','136','/api/sysUserInfo/selectById.do','Y','url'),('ac4468e4-0911-48bf-ab36-6e98b8840ba5','123','/api/categoryF/add.do','Y','url'),('ac4e705a-ebb2-4274-969c-c3773eba860a','156','/api/ctStoreSql/selectPage.do','Y','url'),('ae1d2e5d-b846-4eeb-ab5f-b2c1f8f5a279','190','/api/wx/saveWxApp.do','Y','url'),('ae50de7b-7aa6-40bc-900b-f783f2e17918','136','/api/sysUserGroup/selectList.do','Y','url'),('af91b201-d68e-4ba6-83bc-1794a441ba56','193','/api/wx/saveImageTextMessage.do','Y','url'),('b1760e3f-6e93-427f-a7dd-696c106746f3','137','/api/sysRoleInfo/selectById.do','Y','url'),('b17ad2ad-c674-4836-8055-c90d36db2502','174','/api/sftp/downloadFile.do','Y','url'),('b1a5b6d3-6781-464a-99fd-fbb73b25c53e','70','/api/sysFileConf/deleteById.do','Y','url'),('b293af48-8305-411d-96ac-3f6afa0b196d','136','/api/sysUserInfo/changeRoles.do','Y','url'),('b383ea1c-1042-40af-8e15-dd5e6f9c69ff','139','/api/menu/addNode.do','Y','url'),('b42db3a5-d88c-4dcc-851c-807b7d5d1858','96','/api/sysUserInfo/my/modifypwd.do','Y','url'),('b60a69be-ddbe-490c-8fc1-1a4a090476fd','174','/api/sftp/connectSftp.do','Y','url'),('b61e0c05-616f-4c14-83fe-4a231d8847ed','453','/api/ctCategoryRoot/selectList.do','Y','url'),('b66b1586-2194-405e-9904-6e06de70cb77','453','/api/ctCategoryRoot/selectById.do','Y','url'),('b78e8337-42dd-47c6-aa4e-58b2f3597417','70','/api/sysFileConf/selectById.do','Y','url'),('b78f374a-0299-401b-95c1-3fa975058f15','119','/api/product/getProdPics.do','Y','url'),('b811b710-32f8-4170-b8da-25815bac9eba','136','/api/sysUserInfo/deleteByIds.do','Y','url'),('b8cfbd59-3b9e-4805-a29e-316dbfe8ee16','96','/api/user/queryAccessLog.do','Y','url'),('ba978ee2-4def-4f00-824f-311000d19355','124','/api/categoryB/prodPublishCatAttrList.do','Y','url'),('bbde9e39-0e90-463e-9fc7-fcee16ef4199','200','/api/prod/delDl.do','Y','url'),('bdddbc6e-9bdc-446c-864a-f213212e42b8','118','/api/shop/queryMyShop.do','Y','url'),('bdecc86d-32e4-40c1-b0fc-fff638fd6b32','136','/api/sysUserInfo/selectPage.do','Y','url'),('c00f80a5-2129-492c-ba3a-52d4d620dcdb','124','/api/categoryB/update.do','Y','url'),('c2d7eef9-4495-42fa-922c-332e42d02548','183','/api/mn/mnServiceAddNode.do','Y','url'),('c5d5e436-0a9f-447f-aed9-2954052773e9','56','/api/hrm/employeeDelete.do','Y','url'),('c69dbaa8-69c9-4df3-98d4-013b70ca3ef7','118','/api/product/prodDescartes.do','Y','url'),('c98514bd-0a28-494a-9cae-5ca024f8c281','139','/api/menu/BatchAddNodeBtn.do','Y','url'),('ca075287-465b-41f4-a1a0-ecef78eab0d3','157','/api/sysSession/selectList.do','Y','url'),('cd170f0a-3ff5-4ce5-b2cf-de850124bdb9','22','/api/sysDict/selectList.do','Y','url'),('cd46d6aa-20aa-40c4-ac8d-a878ac66d835','22','/api/sysDict/deleteById.do','Y','url'),('ceeb7f09-5fba-4b4d-974a-d94caa31a172','174','/api/node/queryNodeHost.do','Y','url'),('d08870aa-5fd4-46d8-b10f-24dfb9b7b162','635','/api/databackup.do','Y','url'),('d1311352-4f37-4b9b-9816-a28de20a4eb0','182','/api/mn/queryServicById.do','Y','url'),('d13dbf3b-c3ed-487e-b94a-1cfa5b4934bb','204','/api/user/userSave.do','Y','url'),('d145be04-2f59-4e67-be86-e234026db9aa','206','/api/wx/saveWebOAuth.do','Y','url'),('d231dac6-ff96-42cd-947a-0fe25e5938cb','124','/api/categoryB/catAttrValueQuery.do','Y','url'),('d36f186b-6d91-4f18-a217-316ce989e19f','202','/api/mshop/saveProd.do','Y','url'),('d4255a79-4e77-477f-b1b3-83b181bc1c12','180','/api/mn/saveMetric.do','Y','url'),('d501bb79-fc8a-4596-b563-4649b36dcda2','70','/api/sysFileConf/selectList.do','Y','url'),('d5a59ab4-b725-4899-9dfc-7eca8f6f37b9','56','/api/hrm/employeeQueryById.do','Y','url'),('d612039d-d7a3-447b-a2b7-bb7a64811bfc','453','/api/ctCategoryRoot/updateById.do','Y','url'),('d9ded681-5286-4831-9831-d350a8f250a8','143','/api/schedule/pausejob.do','Y','url'),('da84d20a-0a8a-49f0-a33c-78211d497fe0','182','/api/mn/delService.do','Y','url'),('daad97c0-a087-4245-85e6-9a22b2dc1c7b','151','/api/shop/queryMyShop.do','Y','url'),('db8d7c0e-5ea6-4a69-885f-ff0736f35149','193','/api/wx/addSc.do','Y','url'),('deabea8c-6fb2-46f3-b0e2-2f8609e81a54','123','/api/categoryF/rootCatAdd.do','Y','url'),('decb929a-1d7d-4c23-81c1-54a3d88f083c','192','/api/wx/queryMessageById.do','Y','url'),('e0cdfe9a-1104-4def-a066-d805beb7917e','137','/api/sysRoleInfo/selectList.do','Y','url'),('e2200e38-1613-42bb-9da8-b4e37c4d7a5f','56','/api/hrm/employeeAdd.do','Y','url'),('e3735aba-5877-417a-a5d1-186095446a53','124','/api/categoryB/catAttrValueAdd.do','Y','url'),('e49723c2-977f-4a70-9128-5494b86a24ce','125','/api/categoryF/rootCatAdd.do','Y','url'),('e4c47482-2362-440c-ad57-236672f83f4b','77','/api/ctCategroy/queryCategoryTreeList.do','Y','url'),('e5abe245-4e07-4035-8fce-c25ab9afd056','139','/api/menu/updateNode.do','Y','url'),('e80bc119-ea8c-4680-9bd4-5d47a2d0df72','124','/api/categoryB/catAttrDel.do','Y','url'),('e9967514-9cc5-42a8-a552-7b9752a579e5','123','/api/categoryF/rootCatDel.do','Y','url'),('ea66704e-b363-418c-9bb5-fd338bc2d3f1','192','/api/wx/deleteMessage.do','Y','url'),('eb0c48e8-cd08-4a26-b8bb-8275719f79be','193','/api/wx/queryImageTextMessagesGroup.do','Y','url'),('ed54f983-349c-483e-8cf1-e869c0dd0179','174','/api/node/saveNode.do','Y','url'),('ef2f65f5-3998-408f-90e2-82c6bec2aa34','176','/api/sysApi/system/refreshCache.do','Y','url'),('ef8c4977-236f-4347-8d00-324d5830078a','78','/api/news/publishNews.do','Y','url'),('efae4c0d-c7be-4bf0-82d9-df57f75b9693','22','/api/sysDictItem/selectById.do','Y','url'),('f1d7d4fb-7fac-415a-8728-566e515a270a','185','/api/mn/delMetricGroupMetric.do','Y','url'),('f2c6b3c5-2a80-486a-9f86-4d9d70a7770d','77','/api/ctCategroy/deleteCategory.do','Y','url'),('f3dbf069-6773-42bf-829e-c27e5543ccdf','125','/api/categoryF/rootCatUpdate.do','Y','url'),('f3e51773-6a2c-413f-ae10-8038995add6b','16','/api/hrm/queryEmplByOrg.do','Y','url'),('f5099129-6042-4fc2-b0e4-078d29a0720a','136','/api/sysUserInfo/queryRoles.do','Y','url'),('f65cd9bf-3d60-4f6f-a3a3-1e0df92009f8','139','/api/module/updateModuleItemMap.do','Y','url'),('f672c311-c47d-4e59-b944-f49fa73470c4','143','/api/schedule/runonce.do','Y','url'),('f77253d8-c134-42e8-9f43-cdfe66c78fe9','133','/api/sysUserGroup/selectList.do','Y','url'),('f77443d2-f878-4296-a8b9-27de6201cd35','124','/api/categoryB/catAttrUpdate.do','Y','url'),('f7aed1d8-a46b-4d41-8f04-4ad5bce5a46f','77','/api/ctCategroy/queryCategory.do','Y','url'),('f7b5cce3-edbc-434c-b136-a4ca12a85fcf','138','/api/menu/treeRoleChecked.do','Y','url'),('f8516692-8b93-4c7c-9fcb-14c2cda00327','174','/api/sftp/uploadState.do','Y','url'),('f864cdcf-e39f-4b01-9af3-ec16ffffe546','202','/api/mshop/delProd.do','Y','url'),('fa311e72-d551-4842-ba28-e94fe9068f9c','70','/api/sysFileConf/insert.do','Y','url'),('fa3b8582-4ddc-4ded-b474-a1978ce07278','188','/api/mn/om/saveUrlMetric.do','Y','url'),('fa4a153f-68c7-43c5-a6e7-79db9a3deb29','188','/api/mn/om/deleteUrlMetric.do','Y','url'),('fe6b8d3b-fd82-4206-823c-6c9bd07af9df','86','/api/news/queryNewsById.do','Y','url'),('fe7a2e07-1e02-4b46-8b60-e4abbbb65b18','183','/api/mn/mnServiceAddNodes.do','Y','url');
/*!40000 ALTER TABLE `sys_modules_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_modules_item_history`
--

DROP TABLE IF EXISTS `sys_modules_item_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_modules_item_history` (
  `module_item_id` varchar(50) DEFAULT NULL,
  `module_id` varchar(50) DEFAULT NULL,
  `ct` text,
  `status` varchar(10) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `recdate` datetime DEFAULT NULL,
  `version` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_modules_item_history`
--

LOCK TABLES `sys_modules_item_history` WRITE;
/*!40000 ALTER TABLE `sys_modules_item_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_modules_item_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_notice` (
  `id` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `title` text,
  `ct` text,
  `rdate` datetime DEFAULT NULL,
  `cdate` datetime DEFAULT NULL,
  `is_show` char(1) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_params`
--

DROP TABLE IF EXISTS `sys_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_params` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_params`
--

LOCK TABLES `sys_params` WRITE;
/*!40000 ALTER TABLE `sys_params` DISABLE KEYS */;
INSERT INTO `sys_params` VALUES ('sys_empl_no','员工编号全局序列','301','system','0','12',NULL,NULL,'2018-12-31 23:51:51','sys'),('sys_menu_id','菜单全局序列','20','sysinter','0','12',NULL,NULL,NULL,NULL),('sys_prod_group_num_ctl','产品添加到组控制','Y','sysinter','0','Y:支持多组,N:不支持',NULL,NULL,NULL,NULL),('zc_lsw','资产_菜单_光交','52','system','0',NULL,'2019-11-24 12:13:52','1151420235196588033','2019-11-24 12:13:52','1151420235196588033'),('zc_network','资产_菜单_网络','116','system','0',NULL,'2019-11-24 11:31:10','1151420235196588033','2020-05-07 23:53:56','1151420235196588033'),('zc_other','资产_菜单_其他','71','system','0',NULL,'2019-11-24 12:14:21','1151420235196588033','2019-11-24 12:14:21','1151420235196588033'),('zc_safety','资产_菜单_安全','54','system','0',NULL,'2019-11-24 12:14:42','1151420235196588033','2019-11-24 12:14:42','1151420235196588033'),('zc_server','资产_菜单_服务器','50','system','0',NULL,'2019-11-24 12:13:25','1151420235196588033','2019-11-24 12:13:25','1151420235196588033'),('zc_storage','资产_菜单_存储','57','system','0',NULL,'2019-11-24 12:13:39','1151420235196588033','2019-11-24 12:13:39','1151420235196588033'),('zc_website','资产_菜单_网点','55','system','0',NULL,'2019-11-24 12:14:09','1151420235196588033','2019-11-24 12:14:09','1151420235196588033');
/*!40000 ALTER TABLE `sys_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_process_data`
--

DROP TABLE IF EXISTS `sys_process_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_process_data` (
  `id` varchar(50) NOT NULL,
  `busid` varchar(50) DEFAULT NULL COMMENT '业务id',
  `bustype` varchar(50) DEFAULT NULL COMMENT '业务类型 flow,form',
  `processkey` varchar(100) DEFAULT NULL COMMENT '流程Key',
  `processname` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `processversion` varchar(50) DEFAULT NULL COMMENT '流程版本',
  `processinstanceid` varchar(50) DEFAULT NULL COMMENT '流程实例化后的ID',
  `ptitle` varchar(100) DEFAULT NULL COMMENT '流程标题',
  `ptype` varchar(50) DEFAULT NULL COMMENT '流程类型(LY,JY...)',
  `psubtype` varchar(50) DEFAULT NULL COMMENT '流程子类型',
  `pstatus` varchar(50) DEFAULT NULL COMMENT 'waiting、inreview、finish',
  `pstatusdtl` varchar(50) DEFAULT NULL COMMENT 'success、failed ',
  `pstartuserid` varchar(50) DEFAULT NULL COMMENT '流程发起人用户ID',
  `pstartusername` varchar(100) DEFAULT NULL COMMENT '流程发起人姓名',
  `pendtime` datetime DEFAULT NULL COMMENT '流程结束时间',
  `formid` varchar(50) DEFAULT NULL COMMENT '流程调用的表单ID',
  `formtype` varchar(50) DEFAULT NULL COMMENT '流程调用的表单类型',
  `ifsp` varchar(1) DEFAULT NULL COMMENT '是否审批',
  `busstatus` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_process_data`
--

LOCK TABLES `sys_process_data` WRITE;
/*!40000 ALTER TABLE `sys_process_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_process_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_process_data_kv`
--

DROP TABLE IF EXISTS `sys_process_data_kv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_process_data_kv` (
  `id` varchar(50) NOT NULL,
  `processdataid` varchar(50) DEFAULT NULL,
  `itemvalues` varchar(500) DEFAULT NULL,
  `itemvalues2` varchar(500) DEFAULT NULL,
  `itemvaluei` decimal(10,2) DEFAULT NULL,
  `itemvalued` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_process_data_kv`
--

LOCK TABLES `sys_process_data_kv` WRITE;
/*!40000 ALTER TABLE `sys_process_data_kv` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_process_data_kv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_process_def`
--

DROP TABLE IF EXISTS `sys_process_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_process_def` (
  `id` varchar(50) NOT NULL,
  `name` varchar(500) DEFAULT NULL COMMENT '流程名称',
  `mark` varchar(500) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT '流程开启类型',
  `ptplkey` varchar(100) DEFAULT NULL,
  `owner` varchar(50) DEFAULT NULL,
  `ptplname` varchar(500) DEFAULT NULL,
  `form` varchar(50) DEFAULT NULL,
  `formname` varchar(500) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT 'stop,normal',
  `ptplid` varchar(50) DEFAULT NULL COMMENT '流程ID',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_process_def`
--

LOCK TABLES `sys_process_def` WRITE;
/*!40000 ALTER TABLE `sys_process_def` DISABLE KEYS */;
INSERT INTO `sys_process_def` VALUES ('1248063189595291649','资产领用','','form','zcly-2','134','zcly','1248049296763314177','资产领用','normal',NULL,'1151420235196588033','2020-04-09 01:40:27','2020-06-27 07:34:07','1151420235196588033','0'),('1248878665496825858','资产借用','','form','zcly-1','134','zcly','1276366512902430722','资产借用','normal',NULL,'1151420235196588033','2020-04-11 07:40:52','2020-06-26 04:33:55','1151420235196588033','0'),('1248878753828868097','资产转移',NULL,'form','zcly-1','134','zcly','1276366512902430722','资产借用','normal',NULL,'1151420235196588033','2020-04-11 07:41:13','2020-06-26 04:34:16','1151420235196588033','0'),('1285488467158065153','mrbird',NULL,'form','zcly-1','134','zcly','1276366544498122753','资产转移','normal',NULL,'1276368794897408001','2020-07-21 08:15:08','2020-07-21 08:15:08','1276368794897408001','0');
/*!40000 ALTER TABLE `sys_process_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_process_form`
--

DROP TABLE IF EXISTS `sys_process_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_process_form` (
  `id` varchar(50) NOT NULL,
  `fdata` mediumtext COMMENT '表单数值',
  `ftpldata` text COMMENT '表单配置数据',
  `processdataid` varchar(50) DEFAULT NULL,
  `duuid` varchar(50) DEFAULT NULL COMMENT '唯一编码',
  `dtitle` varchar(100) DEFAULT NULL COMMENT '流程标题',
  `dprofile` varchar(100) DEFAULT NULL COMMENT '介绍',
  `dct` varchar(500) DEFAULT NULL COMMENT '流程内容',
  `dlevel` varchar(100) DEFAULT NULL COMMENT '级别',
  `dcat` varchar(500) DEFAULT NULL COMMENT '类目',
  `durl` varchar(500) DEFAULT NULL COMMENT '链接',
  `dname` varchar(100) DEFAULT NULL COMMENT '名称',
  `dmark` varchar(100) DEFAULT NULL COMMENT '流程备注',
  `dmessage` varchar(100) DEFAULT NULL COMMENT '消息',
  `dstatus` varchar(50) DEFAULT NULL COMMENT '状态',
  `dtype` varchar(100) DEFAULT NULL COMMENT '大类型',
  `dsubtype` varchar(100) DEFAULT NULL COMMENT '小类型',
  `dpwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `dpic1` varchar(100) DEFAULT NULL COMMENT '图片',
  `dpic2` varchar(100) DEFAULT NULL COMMENT '图片',
  `dpic3` varchar(100) DEFAULT NULL COMMENT '图片',
  `duser` varchar(100) DEFAULT NULL COMMENT '用户',
  `dresult` varchar(100) DEFAULT NULL COMMENT '结果',
  `dtotal` decimal(10,2) DEFAULT NULL COMMENT '总数',
  `dbacktime` datetime DEFAULT NULL COMMENT '返回时间',
  `dmethod` varchar(10) DEFAULT NULL COMMENT '方法',
  `dfile` varchar(100) DEFAULT NULL COMMENT '附件',
  `ddict` varchar(50) DEFAULT NULL COMMENT '动态数据',
  `dattach1` varchar(100) DEFAULT NULL COMMENT '附件',
  `dattach2` varchar(100) DEFAULT NULL COMMENT '附件',
  `dattach3` varchar(100) DEFAULT NULL COMMENT '附件',
  `dcard` varchar(200) DEFAULT NULL COMMENT 'card',
  `ftpldatamd5` varchar(100) DEFAULT NULL COMMENT '配置文件MD5',
  `dcode` varchar(100) DEFAULT NULL COMMENT '编码',
  `dxm` varchar(50) DEFAULT NULL COMMENT '姓名',
  `dsex` varchar(50) DEFAULT NULL COMMENT '性别',
  `daddr` varchar(100) DEFAULT NULL COMMENT '地址',
  `dcontact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `dmail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `dqq` varchar(50) DEFAULT NULL COMMENT 'QQ',
  `dhtml` varchar(500) DEFAULT NULL COMMENT 'html',
  `ddate1` date DEFAULT NULL,
  `ddate2` date DEFAULT NULL,
  `ddate3` date DEFAULT NULL,
  `df1` varchar(500) DEFAULT NULL,
  `df2` varchar(500) DEFAULT NULL,
  `df3` varchar(500) DEFAULT NULL,
  `df4` varchar(500) DEFAULT NULL,
  `df5` varchar(500) DEFAULT NULL,
  `df6` varchar(500) DEFAULT NULL,
  `df7` varchar(500) DEFAULT NULL,
  `df8` varchar(500) DEFAULT NULL,
  `df9` varchar(500) DEFAULT NULL,
  `df10` varchar(500) DEFAULT NULL,
  `dn1` decimal(10,2) DEFAULT NULL,
  `dn2` decimal(10,2) DEFAULT NULL,
  `dn3` decimal(10,2) DEFAULT NULL,
  `dn4` decimal(10,2) DEFAULT NULL,
  `dn5` decimal(10,2) DEFAULT NULL,
  `dn6` decimal(10,2) DEFAULT NULL,
  `dn7` decimal(10,2) DEFAULT NULL,
  `dn8` decimal(10,2) DEFAULT NULL,
  `dn9` decimal(10,2) DEFAULT NULL,
  `dn10` decimal(10,2) DEFAULT NULL,
  `dtimestr` varchar(500) DEFAULT NULL,
  `dtimestr2` varchar(100) DEFAULT NULL,
  `duser2` varchar(100) DEFAULT NULL,
  `dpartid` varchar(50) DEFAULT NULL,
  `dcompid` varchar(50) DEFAULT NULL,
  `dbelongpartid` varchar(50) DEFAULT NULL,
  `dbelongcompid` varchar(50) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_process_form`
--

LOCK TABLES `sys_process_form` WRITE;
/*!40000 ALTER TABLE `sys_process_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_process_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_process_setting`
--

DROP TABLE IF EXISTS `sys_process_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_process_setting` (
  `id` varchar(50) NOT NULL,
  `name` varchar(500) DEFAULT NULL COMMENT '流程名称',
  `code` varchar(500) DEFAULT NULL COMMENT '流程编码',
  `processdefid` varchar(50) DEFAULT NULL COMMENT '流程定义ID',
  `processdefname` varchar(100) DEFAULT NULL COMMENT '流程定义名称',
  `type` varchar(100) DEFAULT NULL COMMENT '流程类型 system|user',
  `form` varchar(50) DEFAULT NULL COMMENT '流程表单',
  `formname` varchar(500) DEFAULT NULL COMMENT '流程表单名称',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index2` (`code`),
  KEY `sys_process_settinginx1` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_process_setting`
--

LOCK TABLES `sys_process_setting` WRITE;
/*!40000 ALTER TABLE `sys_process_setting` DISABLE KEYS */;
INSERT INTO `sys_process_setting` VALUES ('1248859308612321213','资产领用','process_zcly','1248063189595291649','资产领用','system','1248049296763314177','资产领用','资产领用','1151420235196588033','2020-06-26 04:44:09',NULL,NULL,'0'),('1248859308641259521','资产借用','process_zcjy','1248878665496825858','资产借用','system',NULL,NULL,'资产借用','1151420235196588033','2020-06-26 04:44:22','1151420235196588033','2020-04-11 06:23:57','0'),('1248859554108706818','资产转移','process_zczy','1248878753828868097','资产转移','system',NULL,NULL,'资产转移','1151420235196588033','2020-06-26 04:44:33','1151420235196588033','2020-04-11 06:24:55','0');
/*!40000 ALTER TABLE `sys_process_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_qud_chengs`
--

DROP TABLE IF EXISTS `sys_qud_chengs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_qud_chengs` (
  `id` varchar(50) NOT NULL,
  `mingc` varchar(200) DEFAULT NULL,
  `jib` varchar(10) DEFAULT NULL,
  `shengf_id` varchar(50) DEFAULT NULL,
  `youb` varchar(10) DEFAULT NULL,
  `quh` varchar(10) NOT NULL,
  `chengsid_tq` varchar(60) DEFAULT NULL,
  `jianc` text,
  `guobm` varchar(60) DEFAULT NULL,
  `renksl` decimal(10,0) DEFAULT NULL,
  `renjsr` decimal(10,0) DEFAULT NULL,
  `renjxszc` decimal(10,0) DEFAULT NULL,
  `renjfzzc` decimal(10,0) DEFAULT NULL,
  `xiugr` varchar(50) DEFAULT NULL,
  `xiugrq` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_qud_chengs`
--

LOCK TABLES `sys_qud_chengs` WRITE;
/*!40000 ALTER TABLE `sys_qud_chengs` DISABLE KEYS */;
INSERT INTO `sys_qud_chengs` VALUES ('1','北京市',NULL,'1','100000','010','101010100','北京','110000',2,123,345,NULL,'SYS','2014-08-12 14:30:46',NULL,NULL,NULL,NULL,'0'),('10','承德市','3','3','67000','0314','101090402','承德','130800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('100','蚌埠市','2','12','233000','0552','101220201','蚌埠','340300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('101','淮南市','0','12','232000','0554','101220401','淮南','340400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('102','马鞍山市','2','12','243000','0555','101220501','马鞍山','340500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('103','淮北市','2','12','235000','0561','101221201','淮北','340600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('104','铜陵市','2','12','244000','0562','101221301','铜陵','340700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('105','安庆市',NULL,'12','246000','0556','101220601','安庆','340800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('106','黄山市','2','12','242700','0559',NULL,'黄山','341000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('107','滁州市','1','12','239000','0550','101221101','滁州','341100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('108','阜阳市','1','12','236100','0558','101220801','阜阳','341200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('109','宿州市','2','12','234100','0557','101220701','宿州','341300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('11','沧州市','2','3','61000','0317','101090701','沧州','130900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('110','巢湖市','3','12','238000','0565','101221601','巢湖','341400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('111','六安市','3','12','237000','0564','101221501','六安','341500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('112','亳州市','1','12','236800','0558',NULL,'亳州','341600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('113','池州市','4','12','247100','0566','101221701','池州','341700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('114','宣城市','3','12','366000','0563','101221401','宣城','341800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('115','福州市','1','13','350000','0591','101230101','福州','350100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('116','厦门市','1','13','361000','0592','101230201','厦门','350200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('117','莆田市','4','13','351100','0594','101230401','莆田','350300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('118','三明市','2','13','365000','0598','101230801','三明','350400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('119','泉州市','2','13','362000','0595','101230501','泉州','350500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('12','廊坊市','2','3','65000','0316','101090601','廊坊','131000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('120','漳州市','2','13','363000','0596','101230601','漳州','350600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('121','南平市',NULL,'13','353000','0599','101230901','南平','350700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('122','龙岩市',NULL,'13','364000','0597','101230701','龙岩','350800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('123','宁德市','2','13','352100','0593','101230301','宁德','350900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('124','南昌市','1','14','330000','0791','101240101','南昌','360100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('125','景德镇市','2','14','333000','0798','101240801','景德镇','360200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('126','萍乡市','3','14','337000','0799','101240901','萍乡','360300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('127','九江市','1','14','332000','0792','101240201','九江','360400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('128','新余市','1','14','338000','0790','101241001','新余','360500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('129','鹰潭市',NULL,'14','335000','0701','101241101','鹰潭','360600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('13','衡水市',NULL,'3','53000','0318','101090801','衡水','131100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('130','赣州市','2','14','341000','0797','101240701','赣州','360700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('131','吉安市','2','14','343000','0796','101240601','吉安','360800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('132','宜春市','2','14','336000','0795','101240501','宜春','360900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('133','抚州市','3','14','332900','0794','101240401','抚州','361000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('134','上饶市','3','14','334000','0793','101240301','上饶','361100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('135','济南市','1','15','250000','0531','101120101','济南','370100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('136','青岛市','1','15','266000','0532','101120201','青岛','370200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('137','淄博市','1','15','255000','0533','101120301','淄博','370300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('138','枣庄市','2','15','277100','0632','101121401','枣庄','370400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('139','东营市',NULL,'15','257000','0546','101121201','东营','370500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('14','太原市','1','4','30000','0351','101100101','太原','140100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('140','烟台市',NULL,'15','264000','0535','101120501','烟台','370600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('141','潍坊市','2','15','261000','0536','101120601','潍坊','370700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('142','济宁市',NULL,'15','272100','0537','101120701','济宁','370800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('143','泰安市','2','15','271000','0538','101120801','泰安','370900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('144','威海市','2','15','265700','0631','101121301','威海','371000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('145','日照市','3','15','276800','0633','101121501','日照','371100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('146','莱芜市','3','15','271100','0634','101121601','莱芜','371200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('147','临沂市',NULL,'15','276000','0539','101120901','临沂','371300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('148','德州市','1','15','253000','0534','101120401','德州','371400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('149','聊城市','2','15','252000','0635','101121701','聊城','371500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('15','大同市',NULL,'4','37000','0352','101100201','大同','140200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('150','滨州市','2','15','256600','0543','101121101','滨州','371600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('151','荷泽市','2','15','274000','0530',NULL,'荷泽',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('152','郑州市','1','16','450000','0371','101180101','郑州','410100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('153','开封市','1','16','475000','0378','101180801','开封','410200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('154','洛阳市','1','16','471000','0379','101180901','洛阳','410300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('155','平顶山市','2','16','467000','0375','101180501','平顶山','410400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('156','安阳市','2','16','454900','0372','101180201','安阳','410500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('157','鹤壁市','2','16','456600','0392','101181201','鹤壁','410600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('158','新乡市','2','16','453000','0373','101180301','新乡','410700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('159','焦作市','2','16','454100','0391','101181101','焦作','410800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('16','阳泉市','1','4','45000','0353','101100301','阳泉','140300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('160','濮阳市','2','16','457000','0393','101181301','濮阳','410900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('161','许昌市','3','16','461000','0374','101180401','许昌','411000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('162','漯河市','2','16','462000','0395','101181501','漯河','411100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('163','三门峡市','3','16','472000','0398','101181701','三门峡','411200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('164','南阳市','3','16','473000','0377','101180701','南阳','411300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('165','商丘市','2','16','476000','0370','101181001','商丘','411400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('166','信阳市','2','16','464000','0376','101180601','信阳','411500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('167','周口市','3','16','466000','0394','101181401','周口','411600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('168','驻马店市','3','16','463000','0396','101181601','驻马店','411700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('169','武汉市',NULL,'17','430000','027','101200101','武汉','420100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('17','长治市','3','4','46000','0355','101100501','长治','140400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('170','黄石市','1','17','435000','0714','101200601','黄石','420200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('171','十堰市','1','17','442000','0719','101201101','十堰','420300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('172','宜昌市','2','17','443000','0717','101200901','宜昌','420500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('173','襄樊市',NULL,'17','441000','0710','101200201','襄樊','420600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('174','鄂州市','4','17','436000','0711','101200301','鄂州','420700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('175','荆门市','1','17','448000','0724','101201401','荆门','420800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('176','孝感市','2','17','432100','0712','101200401','孝感','420900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('177','荆州市','2','17','434000','0716','101200801','荆州','421000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('178','黄冈市','2','17','438000','0713','101200501','黄冈','421100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('179','咸宁市',NULL,'17','437000','0715','101200701','咸宁','421200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('18','晋城市','3','4','48000','0356','101100601','晋城','140500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('180','随州市','3','17','441300','0722','101201301','随州','421300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('181','恩施土家族苗族自治州','2','17','445000','0718','101201001','恩施','422800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('182','神农架','1','17','442400','0719','101201201','神农架','429021',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('183','长沙市','1','18','410000','0731','101250101','长沙','430100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('184','株洲市','1','18','412000','0731','101250301','株洲','430200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('185','湘潭市','1','18','411100','0731','101250201','湘潭','430300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('186','衡阳市','2','18','421000','0734','101250401','衡阳','430400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('187','邵阳市','2','18','422000','0739','101250901','邵阳','430500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('188','岳阳市','2','18','414000','0730','101251001','岳阳','430600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('189','常德市','2','18','415000','0736','101250601','常德','430700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('19','朔州市','1','4','36000','0349','101100901','朔州','140600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('190','张家界市','1','18','427000','0744','101251101','张家界','430800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('191','益阳市','1','18','413000','0737','101250701','益阳','430900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('192','郴州市','2','18','423000','0735','101250501','郴州','431000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('193','永州市','3','18','425000','0746','101251401','永州','431100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('194','怀化市','1','18','418000','0745','101251201','怀化','431200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('195','娄底市','2','18','417000','0738','101250801','娄底','431300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('196','湘西土家族苗族自治州','1','18','416000','0743',NULL,'湘西','433100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('197','广州市','0','19','510000','020','101280101','广州','440100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('198','韶关市','4','19','521000','0751','101280201','韶关','440200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('199','深圳市',NULL,'19','518000','0755','101280601','深圳','440300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('2','天津市',NULL,'2','100000','022','101030100','天津','120000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('20','晋中市','1','4','30600','0354','101100401','晋中','140700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('200','珠海市','1','19','519000','0756','101280701','珠海','440400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('201','汕头市','2','19','515000','0754','101280501','汕头','440500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('202','佛山市','1','19','528000','0757',NULL,'佛山','440600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('203','江门市','2','19','529000','0750','101281101','江门','440700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('204','湛江市','2','19','524000','0759','101281001','湛江','440800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('205','茂名市',NULL,'19','525000','0668','101282001','茂名','440900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('206','肇庆市','2','19','526000','0758','101280901','肇庆','441200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('207','惠州市','1','19','516000','0752','101280301','惠州','441300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('208','梅州市','3','19','514000','0753','101280401','梅州','441400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('209','汕尾市',NULL,'19','516600','0660','101282101','汕尾','441500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('21','运城市','2','4','44000','0359','101100801','运城','140800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('210','河源市','4','19','517000','0762','101281201','河源','441600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('211','阳江市','2','19','529500','0662','101281801','阳江','441700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('212','清远市',NULL,'19','511500','0763','101281301','清远','441800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('213','东莞市','1','19','511700','0769','101281601','东莞','441900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('214','中山市','1','19','528400','0760','101281701','中山','442000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('215','潮州市','2','19','515600','0768','101281501','潮州','445100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('216','揭阳市','3','19','522000','0663','101281901','揭阳','445200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('217','云浮市',NULL,'19','527300','0766','101281401','云浮','445300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('218','南宁市',NULL,'20','530000','0771','101300101','南宁','450100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('219','柳州市',NULL,'20','545000','0772','101300301','柳州','450200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('22','忻州市','3','4','34000','0350','101101001','忻州','140900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('220','桂林市','2','20','541000','0773','101300501','桂林','450300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('221','梧州市','2','20','543000','0774','101300601','梧州','450400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('222','北海市','2','20','536000','0779','101301301','北海','450500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('223','防城港市','2','20','538000','0770','101301401','防城港','450600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('224','钦州市','4','20','535000','0777','101301101','钦州','450700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('225','贵港市','2','20','537100','0775','101300801','贵港','450800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('226','玉林市','2','20','537000','0775','101300901','玉林','450900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('227','百色市','3','20','533000','0776','101301001','百色','451000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('228','贺州市','4','20','542800','0774','101300701','贺州','451100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('229','河池市','4','20','547000','0778','101301201','河池','451200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('23','临汾市',NULL,'4','41000','0357','101100701','临汾','141000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('230','来宾市','2','20','546100','0772','101300401','来宾','451300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('231','崇左市',NULL,'20','532200','0771','101300201','崇左','451400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('232','海口市','2','21','570000','0898','101310101','海口','460100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('233','三亚市','2','21','572000','0898','101310201','三亚','460200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('234','重庆市',NULL,'22','400000','023','101040100','重庆','500000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('235','成都市','0','23','610000','028','101270101','成都','510100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('236','自贡市',NULL,'23','643000','0813','101270301','自贡','510300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('237','攀枝花市','2','23','617000','0812','101270201','攀枝花','510400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('238','泸州市','1','23','646100','0830','101271001','泸州','510500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('239','德阳市','2','23','618000','0838','101272001','德阳','510600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('24','吕梁市','2','4','30500','0358','101101101','吕梁','141100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('240','绵阳市','1','23','621000','0816','101270401','绵阳','510700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('241','广元市','2','23','628000','0839','101272101','广元','510800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('242','遂宁市','1','23','629000','0825','101270701','遂宁','510900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('243','内江市','2','23','641000','0832','101271201','内江','511000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('244','乐山市',NULL,'23','614000','0833','101271401','乐山','511100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('245','南充市','2','23','637000','0817','101270501','南充','511300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('246','眉山市','1','23','612100','028','101271501','眉山','511400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('247','宜宾市','1','23','644000','0831','101271101','宜宾','511500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('248','广安市','2','23','638000','0826','101270801','广安','511600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('249','达州市','3','23','635000','0818','101270601','达州','511700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('25','呼和浩特市','3','5','10000','0471','101080101','呼和浩特','150100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('250','雅安市','4','23','625000','0835','101271701','雅安','511800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('251','巴中市','4','23','635500','0827','101270901','巴中','511900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('252','资阳市','1','23','641300','028','101271301','资阳','512000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('253','阿坝藏族羌族自治州',NULL,'23','624600','0837','101271901','阿坝','513200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('254','甘孜藏族自治州',NULL,'23','626000','0836',NULL,'甘孜','513300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('255','凉山彝族自治州','4','23','615000','0834','101271601','凉山','513400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('256','贵阳市','0','24','55000','0851','101260101','贵阳','520100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('257','六盘水市','1','24','553000','0858','101260801','六盘水','520200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('258','遵义市',NULL,'24','563000','0852','101260201','遵义','520300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('259','安顺市',NULL,'24','561000','0853','101260301','安顺','520400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('26','包头市','2','5','14000','0472','101080201','包头','150200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('260','铜仁地区','1','24','554300','0856','101260601','铜仁','522200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('261','黔西南布依族苗族自治州','1','24','551500','0859',NULL,'黔西南','522300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('262','毕节地区',NULL,'24','551700','0857','101260701','毕节','522400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('263','黔东南苗族侗族自治州','3','24','551500','0855',NULL,'黔东','522600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('264','黔南布依族苗族自治州','1','24','550100','0854',NULL,'黔南','522700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('265','昆明市','0','25','650000','0871','101290101','昆明','530100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('266','曲靖市',NULL,'25','655000','0874','101290401','曲靖','530300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('267','玉溪市','4','25','653100','0877','101290701','玉溪','530400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('268','保山市','4','25','678000','0875','101290501','保山','530500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('269','昭通市','2','25','657000','0870','101291001','昭通','530600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('27','乌海市','4','5','16000','0473','101080301','乌海','150300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('270','丽江市','2','25','674100','0888','101291401','丽江','530700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('271','思茅市','2','25','665000','0879','101290901','思茅','530800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('272','临沧市','4','25','677000','0883','101291101','临沧','530900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('273','楚雄彝族自治州','2','25','675000','0878','101290801','楚雄','532300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('274','红河哈尼族彝族自治州','4','25','654400','0873','101290301','红河','532500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('275','文山壮族苗族自治州','2','25','663000','0876','101290601','文山','532600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('276','西双版纳傣族自治州','4','25','666200','0691',NULL,'西双版纳','532800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('277','大理白族自治州','3','25','671000','0872','101290201','大理','532900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('278','德宏傣族景颇族自治州','4','25','678400','0692','101291501','德宏','533100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('279','怒江傈僳族自治州',NULL,'25','671400','0886','101291201','怒江','533300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('28','赤峰市','4','5','24000','0476','101080601','赤峰','150400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('280','迪庆藏族自治州',NULL,'25','674400','0887',NULL,'迪庆','533400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('281','拉萨市',NULL,'26','850000','0891','101140101','拉萨','540100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('282','昌都地区',NULL,'26','854000','0895','101140501','昌都','542100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('283','山南地区','1','26','856000','0893','101140301','山南','542200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('284','日喀则地区',NULL,'26','857000','0892','101140201','日喀则','542300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('285','那曲地区',NULL,'26','852000','0896','101140601','那曲','542400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('286','阿里地区',NULL,'26','859100','0897','101140701','阿里','542500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('287','林芝地区',NULL,'26','860100','0894','101140401','林芝','542600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('288','西安市','1','27','710000','029','101110101','西安','610100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('289','铜川市','4','27','727000','0919','101111001','铜川','610200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('29','通辽市','1','5','28000','0475','101080501','通辽','150500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('290','宝鸡市','2','27','721000','0917','101110901','宝鸡','610300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('291','咸阳市','2','27','712000','029','101110200','咸阳','610400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('292','渭南市','2','27','714000','0913','101110501','渭南','610500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('293','延安市','2','27','716000','0911','101110300','延安','610600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('294','汉中市','4','27','723000','0916','101110801','汉中','610700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('295','榆林市','1','27','719000','0912','101110401','榆林','610800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('296','安康市','3','27','725000','0915','101110701','安康','610900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('297','商洛市','1','27','711500','0914','101110601','商洛','611000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('298','兰州市','1','28','730000','0931','101160101','兰州','620100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('299','嘉峪关市','1','28','735100','0937',NULL,'嘉峪关','620200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('3','石家庄市','1','3','50000','0311','101090101','石家庄','130100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('30','鄂尔多斯市','3','5','017000','0477','101080701','鄂尔多斯','150600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('300','金昌市','4','28','737100','0935','101160601','金昌','620300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('301','白银市','1','28','730900','0943','101161301','白银','620400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('302','天水市','1','28','741000','0938','101160901','天水','620500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('303','武威市','1','28','733000','0935','101160501','武威','620600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('304','张掖市','1','28','734000','0936','101160701','张掖','620700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('305','平凉市','2','28','744000','0933','101160301','平凉','620800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('306','酒泉市','2','28','735000','0937','101160801','酒泉','620900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('307','庆阳市',NULL,'28','744500','0934','101160401','庆阳','621000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('308','定西市','2','28','743000','0932','101160201','定西','621100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('309','陇南市','2','28','742100','0939',NULL,'陇南','621200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('31','呼伦贝尔市',NULL,'5','021000','0470','101081001','呼伦贝尔','150700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('310','临夏回族自治州','2','28','731100','0930','101161101','临夏','622900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('311','甘南藏族自治州',NULL,'28','747000','0941','101050204','甘南','230225',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('312','西宁市',NULL,'29','810000','0971','101150101','西宁','630100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('313','海东地区',NULL,'29','810600','0972','101150201','海东','632100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('314','海北藏族自治州',NULL,'29','810300','0970','101150801','海北','632200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('315','黄南藏族自治州',NULL,'29','811300','0973','101150301','黄南','632300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('316','海南藏族自治州',NULL,'29','813000','0974','101150401','海南','150303',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('317','果洛藏族自治州',NULL,'29','814000','0975','101150501','果洛','632600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('318','玉树藏族自治州',NULL,'29','815000','0976','101150601','玉树','632700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('319','海西蒙古族藏族自治州','4','29','817000','0979','101150701','海西','632800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('32','巴彦淖尔市','3','5','015000','0478',NULL,'巴彦淖尔','150800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('320','银川市','1','30','750000','0951','101170101','银川','640100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('321','石嘴山市','1','30','753000','0952','101170201','石嘴山','640200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('322','吴忠市','2','30','751100','0953','101170301','吴忠','640300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('323','固原市',NULL,'30','756000','0954','101170401','固原','640400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('324','中卫市','1','30','751700','0955','101170501','中卫','640500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('325','乌鲁木齐市','2','31','830000','0991','101130101','乌鲁木齐','650100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('326','克拉玛依市',NULL,'31','834000','0990','101130201','克拉玛依','650200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('327','吐鲁番地区',NULL,'31','838000','0995','101130501','吐鲁番','652100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('328','哈密地区',NULL,'31','839000','0902','101131201','哈密','652200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('329','昌吉回族自治州','3','31','831100','0994','101130401','昌吉','652300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('33','乌兰察布市','4','5','11800','0474',NULL,'乌兰察布','150900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('330','博尔塔拉蒙古自治州','3','31','833400','0909',NULL,'博州',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('331','巴音郭楞蒙古自治州','3','31','841000','0996',NULL,'巴州','511902',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('332','阿克苏地区','3','31','843000','0997','101130801','阿克苏','652900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('333','克孜勒苏柯尔克孜自治州','3','31','835600','0908',NULL,'克州',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('334','喀什地区','3','31','844000','0998','101130901','喀什','653100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('335','和田地区','4','31','848000','0903','101131301','和田','653200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('336','伊犁哈萨克自治州','3','31','833200','0999',NULL,'伊犁','654000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('337','塔城地区','4','31','834700','0901','101131101','塔城','654200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('338','阿勒泰地区',NULL,'31','836500','0906','101131401','阿勒泰','654300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('339','石河子市','3','31','832000','0993','101130301','石河子','659001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('34','兴安盟','1','5','137400','0482',NULL,'兴安','152200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('340','阿拉尔市',NULL,'31','843300','0483',NULL,'阿拉尔','659002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('341','图木舒克市',NULL,'31','843900','0998',NULL,'图木舒克','659003',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('342','五家渠市',NULL,'31','831300','0994',NULL,'五家渠','659004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('343','香港特别行政区',NULL,'32','0','00852','101320101','香港','810000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('344','澳门特别行政区',NULL,'33','0','00853','101330101','澳门','820000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('345','台湾省',NULL,'34','0','00886',NULL,'台湾',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('346','济源市','4','16','454000','0391',NULL,'济源','410881',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('347','潜江市','4','17','433100','0728',NULL,'潜江',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('35','锡林郭勒盟','4','5','11100','0479',NULL,'锡林郭勒','152500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('36','阿拉善盟','4','5','16000','0483',NULL,'阿拉善','152900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('37','沈阳市','0','6','110000','024','101070101','沈阳','210100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('38','大连市','1','6','116000','0411','101070201','大连','210200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('39','鞍山市','1','6','114000','0412','101070301','鞍山','210300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('4','唐山市','2','3','63000','0315','101090501','唐山','130200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('40','抚顺市',NULL,'6','113000','024','101070401','抚顺','210400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('41','本溪市','4','6','117000','0414','101070501','本溪','210500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('42','丹东市','1','6','118000','0415','101070601','丹东','210600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('43','锦州市','1','6','121000','0416','101070701','锦州','210700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('44','营口市','3','6','115000','0417','101070801','营口','210800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('45','阜新市','0','6','123000','0418','101070901','阜新','210900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('46','辽阳市','3','6','111000','0419','101071001','辽阳','211000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('47','盘锦市','4','6','124000','0427','101071301','盘锦','211100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('48','铁岭市','2','6','112000','024','101071101','铁岭','211200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('49','朝阳市','4','6','122000','0421','101071201','朝阳','211300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('5','秦皇岛市','2','3','66000','0335','101091101','秦皇岛','130300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('50','葫芦岛市','1','6','125000','0429','101071401','葫芦岛','211400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('51','长春市','0','7','130000','0431','101060101','长春','220100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('52','吉林市','1','7','132000','0432','101060201','吉林','220200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('53','四平市','4','7','136000','0434','101060401','四平','220300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('54','辽源市','2','7','136200','0437','101060701','辽源','220400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('55','通化市','4','7','134000','0435','101060501','通化','220500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('56','白山市','4','7','134300','0439','101060901','白山','220600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('57','松原市','1','7','131100','0438','101060801','松原','220700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('58','白城市','1','7','137000','0436','101060601','白城','220800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('59','延边朝鲜族自治州','2','7','133000','0433','101060301','延边','222400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('6','邯郸市',NULL,'3','56000','0310','101091001','邯郸','130400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('60','哈尔滨市','1','8','150000','0451','101050101','哈尔滨','230100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('61','齐齐哈尔市','1','8','161000','0452','101050201','齐齐哈尔','230200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('62','鸡西市',NULL,'8','158100','0467','101051101','鸡西','230300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('63','鹤岗市','1','8','154100','0468','101051201','鹤岗','230400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('64','双鸭山市',NULL,'8','155100','0469','101051301','双鸭山','230500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('65','大庆市',NULL,'8','163000','0459','101050901','大庆','230600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('66','伊春市','2','8','152300','0458','101050801','伊春','230700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('67','佳木斯市','4','8','154000','0454','101050401','佳木斯','230800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('68','七台河市','4','8','154600','0464','101051002','七台河','230900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('69','牡丹江市','3','8','157000','0453','101050301','牡丹江','231000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('7','邢台市','2','3','54000','0319','101090901','邢台','130500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('70','黑河市',NULL,'8','164300','0456','101050601','黑河','231100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('71','绥化市','2','8','152000','0455','101050501','绥化','231200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('72','大兴安岭地区',NULL,'8','165000','0457','101050701','大兴安岭','232700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('73','上海市','0','9','200000','021','101020100','上海','310000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('74','南京市',NULL,'10','210000','025','101190101','南京','320100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('75','无锡市',NULL,'10','214000','0510','101190201','无锡','320200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('76','徐州市','1','10','221000','0516','101190801','徐州','320300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('77','常州市','1','10','213000','0519','101191101','常州','320400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('78','苏州市','1','10','215000','0512','101190401','苏州','320500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('79','南通市','1','10','226000','0513','101190501','南通','320600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('8','保定市','2','3','71000','0312','101090201','保定','130600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('80','连云港市','2','10','222000','0518','101191001','连云港','320700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('81','淮安市','2','10','223200','0517','101190901','淮安','320800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('82','盐城市','3','10','224000','0515','101190701','盐城','320900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('83','扬州市','3','10','225000','0514','101190601','扬州','321000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('84','镇江市','3','10','212000','0511','101190301','镇江','321100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('85','泰州市','3','10','225300','0523','101191201','泰州','321200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('86','宿迁市',NULL,'10','223800','0527','101191301','宿迁','321300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('87','杭州市',NULL,'11','310000','0571','101210101','杭州','330100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('88','宁波市','2','11','315000','0574','101210401','宁波','330200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('89','温州市','2','11','325000','0577','101210701','温州','330300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('9','张家口市','3','3','75000','0313','101090301','张家口','130700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('90','嘉兴市','2','11','314000','0573','101210301','嘉兴','330400',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('91','湖州市','2','11','313000','0572','101210201','湖州','330500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('92','绍兴市','1','11','312000','0575','101210501','绍兴','330600',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('93','金华市','2','11','321000','0579','101210901','金华','330700',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('94','衢州市','3','11','324000','0570','101211001','衢州','330800',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('95','舟山市','0','11','316000','0580','101211101','舟山','330900',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('96','台州市','2','11','318000','0576','101210601','台州','331000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('97','丽水市',NULL,'11','323000','0578','101210801','丽水','331100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('98','合肥市','0','12','230000','0551','101220101','合肥','340100',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),('99','芜湖市',NULL,'12','241000','0553','101220301','芜湖','340200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_qud_chengs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_qud_qux`
--

DROP TABLE IF EXISTS `sys_qud_qux`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_qud_qux` (
  `id` varchar(20) NOT NULL,
  `mingc` varchar(200) DEFAULT NULL,
  `chengs_id` varchar(20) DEFAULT NULL,
  `guobm` varchar(60) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_qud_qux`
--

LOCK TABLES `sys_qud_qux` WRITE;
/*!40000 ALTER TABLE `sys_qud_qux` DISABLE KEYS */;
INSERT INTO `sys_qud_qux` VALUES ('1','东城区','1','110101',NULL,NULL,NULL,NULL,'0'),('10','房山区','1','110111',NULL,NULL,NULL,NULL,'0'),('100','桥东区','7','130702',NULL,NULL,NULL,NULL,'0'),('1000','颍东区','108','341203',NULL,NULL,NULL,NULL,'0'),('1001','颍泉区','108','341204',NULL,NULL,NULL,NULL,'0'),('1002','临泉县','108','341221',NULL,NULL,NULL,NULL,'0'),('1003','太和县','108','341424',NULL,NULL,NULL,NULL,'0'),('1004','阜南县','108','430921',NULL,NULL,NULL,NULL,'0'),('1005','颍上县','108','341226',NULL,NULL,NULL,NULL,'0'),('1006','界首市','108','341282',NULL,NULL,NULL,NULL,'0'),('1007','埇桥区','109','341302',NULL,NULL,NULL,NULL,'0'),('1008','砀山县','109','341321',NULL,NULL,NULL,NULL,'0'),('1009','萧县','109','341322',NULL,NULL,NULL,NULL,'0'),('101','桥西区','7','130703',NULL,NULL,NULL,NULL,'0'),('1010','灵璧县','109','341323',NULL,NULL,NULL,NULL,'0'),('1011','泗县','109','341324',NULL,NULL,NULL,NULL,'0'),('1012','居巢区','110','341402',NULL,NULL,NULL,NULL,'0'),('1013','庐江县','110','341421',NULL,NULL,NULL,NULL,'0'),('1014','无为县','110','341422',NULL,NULL,NULL,NULL,'0'),('1015','含山县','110','341423',NULL,NULL,NULL,NULL,'0'),('1016','和县','110','341424',NULL,NULL,NULL,NULL,'0'),('1017','金安区','111','341502',NULL,NULL,NULL,NULL,'0'),('1018','裕安区','111','341503',NULL,NULL,NULL,NULL,'0'),('1019','寿县','111','341521',NULL,NULL,NULL,NULL,'0'),('102','邢台县','7','130521',NULL,NULL,NULL,NULL,'0'),('1020','霍邱县','111','130430',NULL,NULL,NULL,NULL,'0'),('1021','舒城县','111','341523',NULL,NULL,NULL,NULL,'0'),('1022','金寨县','111','341524',NULL,NULL,NULL,NULL,'0'),('1023','霍山县','111','341525',NULL,NULL,NULL,NULL,'0'),('1024','谯城区','112','341602',NULL,NULL,NULL,NULL,'0'),('1025','涡阳县','112','341621',NULL,NULL,NULL,NULL,'0'),('1026','蒙城县','112','341622',NULL,NULL,NULL,NULL,'0'),('1027','利辛县','112','341623',NULL,NULL,NULL,NULL,'0'),('1028','贵池区','113','341702',NULL,NULL,NULL,NULL,'0'),('1029','东至县','113','341721',NULL,NULL,NULL,NULL,'0'),('103','临城县','7','130522',NULL,NULL,NULL,NULL,'0'),('1030','石台县','113','341722',NULL,NULL,NULL,NULL,'0'),('1031','青阳县','113','341723',NULL,NULL,NULL,NULL,'0'),('1032','宣州区','114','341802',NULL,NULL,NULL,NULL,'0'),('1033','郎溪县','114','341821',NULL,NULL,NULL,NULL,'0'),('1034','广德县','114','341822',NULL,NULL,NULL,NULL,'0'),('1035','泾县','114','341823',NULL,NULL,NULL,NULL,'0'),('1036','绩溪县','114','341824',NULL,NULL,NULL,NULL,'0'),('1037','旌德县','114','341825',NULL,NULL,NULL,NULL,'0'),('1038','宁国市','114','341881',NULL,NULL,NULL,NULL,'0'),('1039','鼓楼区','115','410204',NULL,NULL,NULL,NULL,'0'),('104','内丘县','7','130523',NULL,NULL,NULL,NULL,'0'),('1040','台江区','115','350103',NULL,NULL,NULL,NULL,'0'),('1041','仓山区','115','350104',NULL,NULL,NULL,NULL,'0'),('1042','马尾区','115','350105',NULL,NULL,NULL,NULL,'0'),('1043','晋安区','115','350111',NULL,NULL,NULL,NULL,'0'),('1044','闽侯县','115','350121',NULL,NULL,NULL,NULL,'0'),('1045','连江县','115','350122',NULL,NULL,NULL,NULL,'0'),('1046','罗源县','115','350123',NULL,NULL,NULL,NULL,'0'),('1047','闽清县','115','350124',NULL,NULL,NULL,NULL,'0'),('1048','永泰县','115','350125',NULL,NULL,NULL,NULL,'0'),('1049','平潭县','115','350128',NULL,NULL,NULL,NULL,'0'),('105','柏乡县','7','130524',NULL,NULL,NULL,NULL,'0'),('1050','福清市','115','350181',NULL,NULL,NULL,NULL,'0'),('1051','长乐市','115','350182',NULL,NULL,NULL,NULL,'0'),('1052','思明区','116','350203',NULL,NULL,NULL,NULL,'0'),('1053','海沧区','116','350205',NULL,NULL,NULL,NULL,'0'),('1054','湖里区','116','350206',NULL,NULL,NULL,NULL,'0'),('1055','集美区','116','350211',NULL,NULL,NULL,NULL,'0'),('1056','同安区','116','350212',NULL,NULL,NULL,NULL,'0'),('1057','翔安区','116','350213',NULL,NULL,NULL,NULL,'0'),('1058','城厢区','117','350302',NULL,NULL,NULL,NULL,'0'),('1059','涵江区','117','350303',NULL,NULL,NULL,NULL,'0'),('106','隆尧县','7','130525',NULL,NULL,NULL,NULL,'0'),('1060','荔城区','117','350304',NULL,NULL,NULL,NULL,'0'),('1061','秀屿区','117','350305',NULL,NULL,NULL,NULL,'0'),('1062','仙游县','117','350322',NULL,NULL,NULL,NULL,'0'),('1063','梅列区','118','350402',NULL,NULL,NULL,NULL,'0'),('1064','三元区','118','350403',NULL,NULL,NULL,NULL,'0'),('1065','明溪县','118','350421',NULL,NULL,NULL,NULL,'0'),('1066','清流县','118','350423',NULL,NULL,NULL,NULL,'0'),('1067','宁化县','118','350424',NULL,NULL,NULL,NULL,'0'),('1068','大田县','118','350425',NULL,NULL,NULL,NULL,'0'),('1069','尤溪县','118','350426',NULL,NULL,NULL,NULL,'0'),('107','任县','7','130526',NULL,NULL,NULL,NULL,'0'),('1070','沙县','118','350427',NULL,NULL,NULL,NULL,'0'),('1071','将乐县','118','350428',NULL,NULL,NULL,NULL,'0'),('1072','泰宁县','118','621026',NULL,NULL,NULL,NULL,'0'),('1073','建宁县','118','621026',NULL,NULL,NULL,NULL,'0'),('1074','永安市','118','350481',NULL,NULL,NULL,NULL,'0'),('1075','鲤城区','119','350502',NULL,NULL,NULL,NULL,'0'),('1076','丰泽区','119','350503',NULL,NULL,NULL,NULL,'0'),('1077','洛江区','119','350504',NULL,NULL,NULL,NULL,'0'),('1078','泉港区','119','350505',NULL,NULL,NULL,NULL,'0'),('1079','惠安县','119','510724',NULL,NULL,NULL,NULL,'0'),('108','南和县','7','130527',NULL,NULL,NULL,NULL,'0'),('1080','安溪县','119','350524',NULL,NULL,NULL,NULL,'0'),('1081','永春县','119','350525',NULL,NULL,NULL,NULL,'0'),('1082','德化县','119','350526',NULL,NULL,NULL,NULL,'0'),('1083','金门县','119','350527',NULL,NULL,NULL,NULL,'0'),('1084','石狮市','119','350581',NULL,NULL,NULL,NULL,'0'),('1085','晋江市','119','350582',NULL,NULL,NULL,NULL,'0'),('1086','南安市','119','350583',NULL,NULL,NULL,NULL,'0'),('1087','芗城区','120','350602',NULL,NULL,NULL,NULL,'0'),('1088','龙文区','120','350603',NULL,NULL,NULL,NULL,'0'),('1089','云霄县','120','350622',NULL,NULL,NULL,NULL,'0'),('109','宁晋县','7','130528',NULL,NULL,NULL,NULL,'0'),('1090','漳浦县','120','350623',NULL,NULL,NULL,NULL,'0'),('1091','诏安县','120','510724',NULL,NULL,NULL,NULL,'0'),('1092','长泰县','120','350625',NULL,NULL,NULL,NULL,'0'),('1093','东山县','120','350626',NULL,NULL,NULL,NULL,'0'),('1094','南靖县','120','350627',NULL,NULL,NULL,NULL,'0'),('1095','平和县','120','350628',NULL,NULL,NULL,NULL,'0'),('1096','华安县','120','510724',NULL,NULL,NULL,NULL,'0'),('1097','龙海市','120','350681',NULL,NULL,NULL,NULL,'0'),('1098','延平区','121','350702',NULL,NULL,NULL,NULL,'0'),('1099','顺昌县','121','350721',NULL,NULL,NULL,NULL,'0'),('11','通州区','1','110112',NULL,NULL,NULL,NULL,'0'),('110','巨鹿县','7','130529',NULL,NULL,NULL,NULL,'0'),('1100','浦城县','121','350722',NULL,NULL,NULL,NULL,'0'),('1101','光泽县','121','350723',NULL,NULL,NULL,NULL,'0'),('1102','松溪县','121','350724',NULL,NULL,NULL,NULL,'0'),('1103','政和县','121','350725',NULL,NULL,NULL,NULL,'0'),('1104','邵武市','121','350781',NULL,NULL,NULL,NULL,'0'),('1105','武夷山市','121','350782',NULL,NULL,NULL,NULL,'0'),('1106','建瓯市','121','350783',NULL,NULL,NULL,NULL,'0'),('1107','建阳市','121','350784',NULL,NULL,NULL,NULL,'0'),('1108','新罗区','122','350802',NULL,NULL,NULL,NULL,'0'),('1109','长汀县','122','350821',NULL,NULL,NULL,NULL,'0'),('111','新河县','7','130530',NULL,NULL,NULL,NULL,'0'),('1110','永定县','122','350822',NULL,NULL,NULL,NULL,'0'),('1111','上杭县','122','350823',NULL,NULL,NULL,NULL,'0'),('1112','武平县','122','350824',NULL,NULL,NULL,NULL,'0'),('1113','连城县','122','350825',NULL,NULL,NULL,NULL,'0'),('1114','漳平市','122','350881',NULL,NULL,NULL,NULL,'0'),('1115','蕉城区','123','350902',NULL,NULL,NULL,NULL,'0'),('1116','霞浦县','123','350921',NULL,NULL,NULL,NULL,'0'),('1117','古田县','123','350922',NULL,NULL,NULL,NULL,'0'),('1118','屏南县','123','430921',NULL,NULL,NULL,NULL,'0'),('1119','寿宁县','123','621026',NULL,NULL,NULL,NULL,'0'),('112','广宗县','7','130531',NULL,NULL,NULL,NULL,'0'),('1120','周宁县','123','621026',NULL,NULL,NULL,NULL,'0'),('1121','柘荣县','123','510321',NULL,NULL,NULL,NULL,'0'),('1122','福安市','123','350981',NULL,NULL,NULL,NULL,'0'),('1123','福鼎市','123','350982',NULL,NULL,NULL,NULL,'0'),('1124','东湖区','124','360102',NULL,NULL,NULL,NULL,'0'),('1125','西湖区','124','360103',NULL,NULL,NULL,NULL,'0'),('1126','青云谱区','124','360104',NULL,NULL,NULL,NULL,'0'),('1127','湾里区','124','360105',NULL,NULL,NULL,NULL,'0'),('1128','青山湖区','124','360111',NULL,NULL,NULL,NULL,'0'),('1129','南昌县','124','360121',NULL,NULL,NULL,NULL,'0'),('113','平乡县','7','130532',NULL,NULL,NULL,NULL,'0'),('1130','新建县','124','360122',NULL,NULL,NULL,NULL,'0'),('1131','安义县','124','360123',NULL,NULL,NULL,NULL,'0'),('1132','进贤县','124','360124',NULL,NULL,NULL,NULL,'0'),('1133','昌江区','125','360202',NULL,NULL,NULL,NULL,'0'),('1134','珠山区','125','360203',NULL,NULL,NULL,NULL,'0'),('1135','浮梁县','125','360222',NULL,NULL,NULL,NULL,'0'),('1136','乐平市','125','360281',NULL,NULL,NULL,NULL,'0'),('1137','安源区','126','360302',NULL,NULL,NULL,NULL,'0'),('1138','湘东区','126','360313',NULL,NULL,NULL,NULL,'0'),('1139','莲花县','126','360321',NULL,NULL,NULL,NULL,'0'),('114','威县','7','130533',NULL,NULL,NULL,NULL,'0'),('1140','上栗县','126','360322',NULL,NULL,NULL,NULL,'0'),('1141','芦溪县','126','360323',NULL,NULL,NULL,NULL,'0'),('1142','庐山区','127','360402',NULL,NULL,NULL,NULL,'0'),('1143','浔阳区','127','360403',NULL,NULL,NULL,NULL,'0'),('1144','九江县','127','360421',NULL,NULL,NULL,NULL,'0'),('1145','武宁县','127','621026',NULL,NULL,NULL,NULL,'0'),('1146','修水县','127','360424',NULL,NULL,NULL,NULL,'0'),('1147','永修县','127','360425',NULL,NULL,NULL,NULL,'0'),('1148','德安县','127','510724',NULL,NULL,NULL,NULL,'0'),('1149','星子县','127','360427',NULL,NULL,NULL,NULL,'0'),('115','清河县','7','130534',NULL,NULL,NULL,NULL,'0'),('1150','都昌县','127','360428',NULL,NULL,NULL,NULL,'0'),('1151','湖口县','127','360429',NULL,NULL,NULL,NULL,'0'),('1152','彭泽县','127','360430',NULL,NULL,NULL,NULL,'0'),('1153','瑞昌市','127','360481',NULL,NULL,NULL,NULL,'0'),('1154','渝水区','128','360502',NULL,NULL,NULL,NULL,'0'),('1155','分宜县','128','360521',NULL,NULL,NULL,NULL,'0'),('1156','月湖区','129','360602',NULL,NULL,NULL,NULL,'0'),('1157','余江县','129','360622',NULL,NULL,NULL,NULL,'0'),('1158','贵溪市','129','360681',NULL,NULL,NULL,NULL,'0'),('1159','章贡区','130','360702',NULL,NULL,NULL,NULL,'0'),('116','临西县','7','130535',NULL,NULL,NULL,NULL,'0'),('1160','赣县','130','360721',NULL,NULL,NULL,NULL,'0'),('1161','信丰县','130','360722',NULL,NULL,NULL,NULL,'0'),('1162','大余县','130','360723',NULL,NULL,NULL,NULL,'0'),('1163','上犹县','130','360724',NULL,NULL,NULL,NULL,'0'),('1164','崇义县','130','360725',NULL,NULL,NULL,NULL,'0'),('1165','安远县','130','360726',NULL,NULL,NULL,NULL,'0'),('1166','龙南县','130','430921',NULL,NULL,NULL,NULL,'0'),('1167','定南县','130','430921',NULL,NULL,NULL,NULL,'0'),('1168','全南县','130','430921',NULL,NULL,NULL,NULL,'0'),('1169','宁都县','130','360730',NULL,NULL,NULL,NULL,'0'),('117','南宫市','7','130581',NULL,NULL,NULL,NULL,'0'),('1170','于都县','130','360731',NULL,NULL,NULL,NULL,'0'),('1171','兴国县','130','360732',NULL,NULL,NULL,NULL,'0'),('1172','会昌县','130','360733',NULL,NULL,NULL,NULL,'0'),('1173','寻乌县','130','360734',NULL,NULL,NULL,NULL,'0'),('1174','石城县','130','360735',NULL,NULL,NULL,NULL,'0'),('1175','瑞金市','130','360781',NULL,NULL,NULL,NULL,'0'),('1176','南康市','130','360782',NULL,NULL,NULL,NULL,'0'),('1177','吉州区','131','360802',NULL,NULL,NULL,NULL,'0'),('1178','青原区','131','360803',NULL,NULL,NULL,NULL,'0'),('1179','吉安县','131','510724',NULL,NULL,NULL,NULL,'0'),('118','沙河市','7','130582',NULL,NULL,NULL,NULL,'0'),('1180','吉水县','131','360822',NULL,NULL,NULL,NULL,'0'),('1181','峡江县','131','360823',NULL,NULL,NULL,NULL,'0'),('1182','新干县','131','360824',NULL,NULL,NULL,NULL,'0'),('1183','永丰县','131','360825',NULL,NULL,NULL,NULL,'0'),('1184','泰和县','131','360826',NULL,NULL,NULL,NULL,'0'),('1185','遂川县','131','360827',NULL,NULL,NULL,NULL,'0'),('1186','万安县','131','510724',NULL,NULL,NULL,NULL,'0'),('1187','安福县','131','360829',NULL,NULL,NULL,NULL,'0'),('1188','永新县','131','411523',NULL,NULL,NULL,NULL,'0'),('1189','井冈山市','131','360881',NULL,NULL,NULL,NULL,'0'),('119','新市区','8','650104',NULL,NULL,NULL,NULL,'0'),('1190','袁州区','132','360902',NULL,NULL,NULL,NULL,'0'),('1191','奉新县','132','411523',NULL,NULL,NULL,NULL,'0'),('1192','万载县','132','360922',NULL,NULL,NULL,NULL,'0'),('1193','上高县','132','511525',NULL,NULL,NULL,NULL,'0'),('1194','宜丰县','132','360924',NULL,NULL,NULL,NULL,'0'),('1195','靖安县','132','510724',NULL,NULL,NULL,NULL,'0'),('1196','铜鼓县','132','360926',NULL,NULL,NULL,NULL,'0'),('1197','丰城市','132','360981',NULL,NULL,NULL,NULL,'0'),('1198','樟树市','132','360982',NULL,NULL,NULL,NULL,'0'),('1199','高安市','132','360983',NULL,NULL,NULL,NULL,'0'),('12','顺义区','1','110113',NULL,NULL,NULL,NULL,'0'),('120','北市区','8','130603',NULL,NULL,NULL,NULL,'0'),('1200','临川区','133','361002',NULL,NULL,NULL,NULL,'0'),('1201','南城县','133','361021',NULL,NULL,NULL,NULL,'0'),('1202','黎川县','133','361022',NULL,NULL,NULL,NULL,'0'),('1203','南丰县','133','361023',NULL,NULL,NULL,NULL,'0'),('1204','崇仁县','133','361024',NULL,NULL,NULL,NULL,'0'),('1205','乐安县','133','510724',NULL,NULL,NULL,NULL,'0'),('1206','宜黄县','133','361026',NULL,NULL,NULL,NULL,'0'),('1207','金溪县','133','361027',NULL,NULL,NULL,NULL,'0'),('1208','资溪县','133','361028',NULL,NULL,NULL,NULL,'0'),('1209','东乡县','133','361029',NULL,NULL,NULL,NULL,'0'),('121','南市区','8','130604',NULL,NULL,NULL,NULL,'0'),('1210','广昌县','133','361030',NULL,NULL,NULL,NULL,'0'),('1211','信州区','134','361102',NULL,NULL,NULL,NULL,'0'),('1212','上饶县','134','361121',NULL,NULL,NULL,NULL,'0'),('1213','广丰县','134','361122',NULL,NULL,NULL,NULL,'0'),('1214','玉山县','134','361123',NULL,NULL,NULL,NULL,'0'),('1215','铅山县','134','361124',NULL,NULL,NULL,NULL,'0'),('1216','横峰县','134','361125',NULL,NULL,NULL,NULL,'0'),('1217','弋阳县','134','361126',NULL,NULL,NULL,NULL,'0'),('1218','余干县','134','361127',NULL,NULL,NULL,NULL,'0'),('1219','鄱阳县','134','361128',NULL,NULL,NULL,NULL,'0'),('122','满城县','8','130621',NULL,NULL,NULL,NULL,'0'),('1220','万年县','134','361129',NULL,NULL,NULL,NULL,'0'),('1221','婺源县','134','361130',NULL,NULL,NULL,NULL,'0'),('1222','德兴市','134','361181',NULL,NULL,NULL,NULL,'0'),('1223','历下区','135','370102',NULL,NULL,NULL,NULL,'0'),('1224','市中区','135','511102',NULL,NULL,NULL,NULL,'0'),('1225','槐荫区','135','370104',NULL,NULL,NULL,NULL,'0'),('1226','天桥区','135','370105',NULL,NULL,NULL,NULL,'0'),('1227','历城区','135','370112',NULL,NULL,NULL,NULL,'0'),('1228','长清区','135','370113',NULL,NULL,NULL,NULL,'0'),('1229','平阴县','135','370124',NULL,NULL,NULL,NULL,'0'),('123','清苑县','8','130622',NULL,NULL,NULL,NULL,'0'),('1230','济阳县','135','370125',NULL,NULL,NULL,NULL,'0'),('1231','商河县','135','370126',NULL,NULL,NULL,NULL,'0'),('1232','章丘市','135','370181',NULL,NULL,NULL,NULL,'0'),('1233','市南区','136','370202',NULL,NULL,NULL,NULL,'0'),('1234','市北区','136','370203',NULL,NULL,NULL,NULL,'0'),('1235','四方区','136','370205',NULL,NULL,NULL,NULL,'0'),('1236','黄岛区','136','370211',NULL,NULL,NULL,NULL,'0'),('1237','崂山区','136','370212',NULL,NULL,NULL,NULL,'0'),('1238','李沧区','136','370213',NULL,NULL,NULL,NULL,'0'),('1239','城阳区','136','370214',NULL,NULL,NULL,NULL,'0'),('124','涞水县','8','130623',NULL,NULL,NULL,NULL,'0'),('1240','胶州市','136','370281',NULL,NULL,NULL,NULL,'0'),('1241','即墨市','136','370282',NULL,NULL,NULL,NULL,'0'),('1242','平度市','136','370283',NULL,NULL,NULL,NULL,'0'),('1243','胶南市','136','370284',NULL,NULL,NULL,NULL,'0'),('1244','莱西市','136','370285',NULL,NULL,NULL,NULL,'0'),('1245','淄川区','137','370302',NULL,NULL,NULL,NULL,'0'),('1246','张店区','137','370303',NULL,NULL,NULL,NULL,'0'),('1247','博山区','137','370304',NULL,NULL,NULL,NULL,'0'),('1248','临淄区','137','370305',NULL,NULL,NULL,NULL,'0'),('1249','周村区','137','370306',NULL,NULL,NULL,NULL,'0'),('125','阜平县','8','130624',NULL,NULL,NULL,NULL,'0'),('1250','桓台县','137','370321',NULL,NULL,NULL,NULL,'0'),('1251','高青县','137','370322',NULL,NULL,NULL,NULL,'0'),('1252','沂源县','137','370323',NULL,NULL,NULL,NULL,'0'),('1253','市中区','138','511102',NULL,NULL,NULL,NULL,'0'),('1254','薛城区','138','370403',NULL,NULL,NULL,NULL,'0'),('1255','峄城区','138','370404',NULL,NULL,NULL,NULL,'0'),('1256','台儿庄区','138','370405',NULL,NULL,NULL,NULL,'0'),('1257','山亭区','138','370406',NULL,NULL,NULL,NULL,'0'),('1258','滕州市','138','370481',NULL,NULL,NULL,NULL,'0'),('1259','东营区','139','370502',NULL,NULL,NULL,NULL,'0'),('126','徐水县','8','130625',NULL,NULL,NULL,NULL,'0'),('1260','河口区','139','370503',NULL,NULL,NULL,NULL,'0'),('1261','垦利县','139','370521',NULL,NULL,NULL,NULL,'0'),('1262','利津县','139','370522',NULL,NULL,NULL,NULL,'0'),('1263','广饶县','139','370523',NULL,NULL,NULL,NULL,'0'),('1264','芝罘区','140','370602',NULL,NULL,NULL,NULL,'0'),('1265','福山区','140','370611',NULL,NULL,NULL,NULL,'0'),('1266','牟平区','140','370612',NULL,NULL,NULL,NULL,'0'),('1267','莱山区','140','370613',NULL,NULL,NULL,NULL,'0'),('1268','长岛县','140','370634',NULL,NULL,NULL,NULL,'0'),('1269','龙口市','140','370681',NULL,NULL,NULL,NULL,'0'),('127','定兴县','8','130626',NULL,NULL,NULL,NULL,'0'),('1270','莱阳市','140','370682',NULL,NULL,NULL,NULL,'0'),('1271','莱州市','140','370683',NULL,NULL,NULL,NULL,'0'),('1272','蓬莱市','140','370684',NULL,NULL,NULL,NULL,'0'),('1273','招远市','140','370685',NULL,NULL,NULL,NULL,'0'),('1274','栖霞市','140','370686',NULL,NULL,NULL,NULL,'0'),('1275','海阳市','140','370687',NULL,NULL,NULL,NULL,'0'),('1276','潍城区','141','370702',NULL,NULL,NULL,NULL,'0'),('1277','寒亭区','141','370703',NULL,NULL,NULL,NULL,'0'),('1278','坊子区','141','370704',NULL,NULL,NULL,NULL,'0'),('1279','奎文区','141','370705',NULL,NULL,NULL,NULL,'0'),('128','唐县','8','130627',NULL,NULL,NULL,NULL,'0'),('1280','临朐县','141','370724',NULL,NULL,NULL,NULL,'0'),('1281','昌乐县','141','370725',NULL,NULL,NULL,NULL,'0'),('1282','青州市','141','370781',NULL,NULL,NULL,NULL,'0'),('1283','诸城市','141','370782',NULL,NULL,NULL,NULL,'0'),('1284','寿光市','141','370783',NULL,NULL,NULL,NULL,'0'),('1285','安丘市','141','370784',NULL,NULL,NULL,NULL,'0'),('1286','高密市','141','370785',NULL,NULL,NULL,NULL,'0'),('1287','昌邑市','141','370786',NULL,NULL,NULL,NULL,'0'),('1288','市中区','142','511102',NULL,NULL,NULL,NULL,'0'),('1289','任城区','142','370811',NULL,NULL,NULL,NULL,'0'),('129','高阳县','8','130628',NULL,NULL,NULL,NULL,'0'),('1290','微山县','142','370826',NULL,NULL,NULL,NULL,'0'),('1291','鱼台县','142','370827',NULL,NULL,NULL,NULL,'0'),('1292','金乡县','142','370828',NULL,NULL,NULL,NULL,'0'),('1293','嘉祥县','142','370829',NULL,NULL,NULL,NULL,'0'),('1294','汶上县','142','370830',NULL,NULL,NULL,NULL,'0'),('1295','泗水县','142','370831',NULL,NULL,NULL,NULL,'0'),('1296','梁山县','142','370832',NULL,NULL,NULL,NULL,'0'),('1297','曲阜市','142','370881',NULL,NULL,NULL,NULL,'0'),('1298','兖州市','142','370882',NULL,NULL,NULL,NULL,'0'),('1299','邹城市','142','370883',NULL,NULL,NULL,NULL,'0'),('13','昌平区','1','110114',NULL,NULL,NULL,NULL,'0'),('130','容城县','8','130629',NULL,NULL,NULL,NULL,'0'),('1300','泰山区','143','370902',NULL,NULL,NULL,NULL,'0'),('1301','岱岳区','143','370903',NULL,NULL,NULL,NULL,'0'),('1302','宁阳县','143','370921',NULL,NULL,NULL,NULL,'0'),('1303','东平县','143','370923',NULL,NULL,NULL,NULL,'0'),('1304','新泰市','143','370982',NULL,NULL,NULL,NULL,'0'),('1305','肥城市','143','370983',NULL,NULL,NULL,NULL,'0'),('1306','环翠区','144','371002',NULL,NULL,NULL,NULL,'0'),('1307','文登市','144','371081',NULL,NULL,NULL,NULL,'0'),('1308','荣成市','144','371082',NULL,NULL,NULL,NULL,'0'),('1309','乳山市','144','371083',NULL,NULL,NULL,NULL,'0'),('131','涞源县','8','130630',NULL,NULL,NULL,NULL,'0'),('1310','东港区','145','371102',NULL,NULL,NULL,NULL,'0'),('1311','岚山区','145','371103',NULL,NULL,NULL,NULL,'0'),('1312','五莲县','145','371121',NULL,NULL,NULL,NULL,'0'),('1313','莒县','145','371122',NULL,NULL,NULL,NULL,'0'),('1314','莱城区','146','371202',NULL,NULL,NULL,NULL,'0'),('1315','钢城区','146','371203',NULL,NULL,NULL,NULL,'0'),('1316','兰山区','147','371302',NULL,NULL,NULL,NULL,'0'),('1317','罗庄区','147','371311',NULL,NULL,NULL,NULL,'0'),('1318','河东区','147','371312',NULL,NULL,NULL,NULL,'0'),('1319','沂南县','147','430921',NULL,NULL,NULL,NULL,'0'),('132','望都县','8','130631',NULL,NULL,NULL,NULL,'0'),('1320','郯城县','147','371322',NULL,NULL,NULL,NULL,'0'),('1321','沂水县','147','371323',NULL,NULL,NULL,NULL,'0'),('1322','苍山县','147','371324',NULL,NULL,NULL,NULL,'0'),('1323','费县','147','371325',NULL,NULL,NULL,NULL,'0'),('1324','平邑县','147','371326',NULL,NULL,NULL,NULL,'0'),('1325','莒南县','147','430921',NULL,NULL,NULL,NULL,'0'),('1326','蒙阴县','147','371328',NULL,NULL,NULL,NULL,'0'),('1327','临沭县','147','371329',NULL,NULL,NULL,NULL,'0'),('1328','德城区','148','371402',NULL,NULL,NULL,NULL,'0'),('1329','陵县','148','371421',NULL,NULL,NULL,NULL,'0'),('133','安新县','8','411523',NULL,NULL,NULL,NULL,'0'),('1330','宁津县','148','371422',NULL,NULL,NULL,NULL,'0'),('1331','庆云县','148','530922',NULL,NULL,NULL,NULL,'0'),('1332','临邑县','148','371424',NULL,NULL,NULL,NULL,'0'),('1333','齐河县','148','371425',NULL,NULL,NULL,NULL,'0'),('1334','平原县','148','371426',NULL,NULL,NULL,NULL,'0'),('1335','夏津县','148','371427',NULL,NULL,NULL,NULL,'0'),('1336','武城县','148','371428',NULL,NULL,NULL,NULL,'0'),('1337','乐陵市','148','371481',NULL,NULL,NULL,NULL,'0'),('1338','禹城市','148','371482',NULL,NULL,NULL,NULL,'0'),('1339','东昌府区','149','371502',NULL,NULL,NULL,NULL,'0'),('134','易县','8','130633',NULL,NULL,NULL,NULL,'0'),('1340','阳谷县','149','371521',NULL,NULL,NULL,NULL,'0'),('1341','莘县','149','371522',NULL,NULL,NULL,NULL,'0'),('1342','茌平县','149','371523',NULL,NULL,NULL,NULL,'0'),('1343','东阿县','149','371524',NULL,NULL,NULL,NULL,'0'),('1344','冠县','149','371525',NULL,NULL,NULL,NULL,'0'),('1345','高唐县','149','371526',NULL,NULL,NULL,NULL,'0'),('1346','临清市','149','371581',NULL,NULL,NULL,NULL,'0'),('1347','滨城区','150','371602',NULL,NULL,NULL,NULL,'0'),('1348','惠民县','150','371621',NULL,NULL,NULL,NULL,'0'),('1349','阳信县','150','371622',NULL,NULL,NULL,NULL,'0'),('135','曲阳县','8','130634',NULL,NULL,NULL,NULL,'0'),('1350','无棣县','150','371623',NULL,NULL,NULL,NULL,'0'),('1351','沾化县','150','371624',NULL,NULL,NULL,NULL,'0'),('1352','博兴县','150','371625',NULL,NULL,NULL,NULL,'0'),('1353','邹平县','150','371626',NULL,NULL,NULL,NULL,'0'),('1354','牡丹区','151','371702',NULL,NULL,NULL,NULL,'0'),('1355','曹县','151','371721',NULL,NULL,NULL,NULL,'0'),('1356','单县','151','371722',NULL,NULL,NULL,NULL,'0'),('1357','成武县','151','371723',NULL,NULL,NULL,NULL,'0'),('1358','巨野县','151','371724',NULL,NULL,NULL,NULL,'0'),('1359','郓城县','151','371725',NULL,NULL,NULL,NULL,'0'),('136','蠡县','8','130635',NULL,NULL,NULL,NULL,'0'),('1360','鄄城县','151','371726',NULL,NULL,NULL,NULL,'0'),('1361','定陶县','151','371727',NULL,NULL,NULL,NULL,'0'),('1362','东明县','151','371728',NULL,NULL,NULL,NULL,'0'),('1363','中原区','152','410102',NULL,NULL,NULL,NULL,'0'),('1364','二七区','152','410103',NULL,NULL,NULL,NULL,'0'),('1365','管城回族区','152','410104',NULL,NULL,NULL,NULL,'0'),('1366','金水区','152','410105',NULL,NULL,NULL,NULL,'0'),('1367','上街区','152','410106',NULL,NULL,NULL,NULL,'0'),('1368','惠济区','152','410108',NULL,NULL,NULL,NULL,'0'),('1369','中牟县','152','410122',NULL,NULL,NULL,NULL,'0'),('137','顺平县','8','130636',NULL,NULL,NULL,NULL,'0'),('1370','巩义市','152','410181',NULL,NULL,NULL,NULL,'0'),('1371','荥阳市','152','410182',NULL,NULL,NULL,NULL,'0'),('1372','新密市','152','410183',NULL,NULL,NULL,NULL,'0'),('1373','新郑市','152','410184',NULL,NULL,NULL,NULL,'0'),('1374','登封市','152','410185',NULL,NULL,NULL,NULL,'0'),('1375','龙亭区','153','410202',NULL,NULL,NULL,NULL,'0'),('1376','顺河回族区','153','410203',NULL,NULL,NULL,NULL,'0'),('1377','鼓楼区','153','410204',NULL,NULL,NULL,NULL,'0'),('1378','南关区','153','220102',NULL,NULL,NULL,NULL,'0'),('1379','郊区','153',NULL,NULL,NULL,NULL,NULL,'0'),('138','博野县','8','130637',NULL,NULL,NULL,NULL,'0'),('1380','杞县','153','410221',NULL,NULL,NULL,NULL,'0'),('1381','通许县','153','410222',NULL,NULL,NULL,NULL,'0'),('1382','尉氏县','153','410223',NULL,NULL,NULL,NULL,'0'),('1383','开封县','153','410224',NULL,NULL,NULL,NULL,'0'),('1384','兰考县','153','410225',NULL,NULL,NULL,NULL,'0'),('1385','老城区','154','410302',NULL,NULL,NULL,NULL,'0'),('1386','西工区','154','410303',NULL,NULL,NULL,NULL,'0'),('1387','廛河回族区','154','410304',NULL,NULL,NULL,NULL,'0'),('1388','涧西区','154','410305',NULL,NULL,NULL,NULL,'0'),('1389','吉利区','154','410306',NULL,NULL,NULL,NULL,'0'),('139','雄县','8','130638',NULL,NULL,NULL,NULL,'0'),('1390','洛龙区','154','410307',NULL,NULL,NULL,NULL,'0'),('1391','孟津县','154','410322',NULL,NULL,NULL,NULL,'0'),('1392','新安县','154','510724',NULL,NULL,NULL,NULL,'0'),('1393','栾川县','154','410324',NULL,NULL,NULL,NULL,'0'),('1394','嵩县','154','410325',NULL,NULL,NULL,NULL,'0'),('1395','汝阳县','154','410326',NULL,NULL,NULL,NULL,'0'),('1396','宜阳县','154','410327',NULL,NULL,NULL,NULL,'0'),('1397','洛宁县','154','410328',NULL,NULL,NULL,NULL,'0'),('1398','伊川县','154','410329',NULL,NULL,NULL,NULL,'0'),('1399','偃师市','154','410381',NULL,NULL,NULL,NULL,'0'),('14','大兴区','1','110115',NULL,NULL,NULL,NULL,'0'),('140','涿州市','8','130681',NULL,NULL,NULL,NULL,'0'),('1400','新华区','155','410402',NULL,NULL,NULL,NULL,'0'),('1401','卫东区','155','410403',NULL,NULL,NULL,NULL,'0'),('1402','石龙区','155','410404',NULL,NULL,NULL,NULL,'0'),('1403','湛河区','155','410411',NULL,NULL,NULL,NULL,'0'),('1404','宝丰县','155','410421',NULL,NULL,NULL,NULL,'0'),('1405','叶县','155','410422',NULL,NULL,NULL,NULL,'0'),('1406','鲁山县','155','410423',NULL,NULL,NULL,NULL,'0'),('1407','郏县','155','410425',NULL,NULL,NULL,NULL,'0'),('1408','舞钢市','155','410481',NULL,NULL,NULL,NULL,'0'),('1409','汝州市','155','410482',NULL,NULL,NULL,NULL,'0'),('141','定州市','8','130682',NULL,NULL,NULL,NULL,'0'),('1410','文峰区','156','410502',NULL,NULL,NULL,NULL,'0'),('1411','北关区','156','410503',NULL,NULL,NULL,NULL,'0'),('1412','殷都区','156','410505',NULL,NULL,NULL,NULL,'0'),('1413','龙安区','156','410506',NULL,NULL,NULL,NULL,'0'),('1414','安阳县','156','410522',NULL,NULL,NULL,NULL,'0'),('1415','汤阴县','156','410523',NULL,NULL,NULL,NULL,'0'),('1416','滑县','156','410526',NULL,NULL,NULL,NULL,'0'),('1417','内黄县','156','410527',NULL,NULL,NULL,NULL,'0'),('1418','林州市','156','410581',NULL,NULL,NULL,NULL,'0'),('1419','鹤山区','157','410602',NULL,NULL,NULL,NULL,'0'),('142','安国市','8','130683',NULL,NULL,NULL,NULL,'0'),('1420','山城区','157','410603',NULL,NULL,NULL,NULL,'0'),('1421','淇滨区','157','410611',NULL,NULL,NULL,NULL,'0'),('1422','浚县','157','410621',NULL,NULL,NULL,NULL,'0'),('1423','淇县','157','410622',NULL,NULL,NULL,NULL,'0'),('1424','红旗区','158','410702',NULL,NULL,NULL,NULL,'0'),('1425','卫滨区','158','410703',NULL,NULL,NULL,NULL,'0'),('1426','凤泉区','158','410704',NULL,NULL,NULL,NULL,'0'),('1427','牧野区','158','410711',NULL,NULL,NULL,NULL,'0'),('1428','新乡县','158','410721',NULL,NULL,NULL,NULL,'0'),('1429','获嘉县','158','410724',NULL,NULL,NULL,NULL,'0'),('143','高碑店市','8','130684',NULL,NULL,NULL,NULL,'0'),('1430','原阳县','158','410725',NULL,NULL,NULL,NULL,'0'),('1431','延津县','158','410726',NULL,NULL,NULL,NULL,'0'),('1432','封丘县','158','410727',NULL,NULL,NULL,NULL,'0'),('1433','长垣县','158','410728',NULL,NULL,NULL,NULL,'0'),('1434','卫辉市','158','410781',NULL,NULL,NULL,NULL,'0'),('1435','辉县市','158','410782',NULL,NULL,NULL,NULL,'0'),('1436','解放区','159','410802',NULL,NULL,NULL,NULL,'0'),('1437','中站区','159','410803',NULL,NULL,NULL,NULL,'0'),('1438','马村区','159','410804',NULL,NULL,NULL,NULL,'0'),('1439','山阳区','159','410811',NULL,NULL,NULL,NULL,'0'),('144','桥东区','9','130702',NULL,NULL,NULL,NULL,'0'),('1440','修武县','159','410821',NULL,NULL,NULL,NULL,'0'),('1441','博爱县','159','410822',NULL,NULL,NULL,NULL,'0'),('1442','武陟县','159','410823',NULL,NULL,NULL,NULL,'0'),('1443','温县','159','410825',NULL,NULL,NULL,NULL,'0'),('1444','济源市','159','410881',NULL,NULL,NULL,NULL,'0'),('1445','沁阳市','159','410882',NULL,NULL,NULL,NULL,'0'),('1446','孟州市','159','410883',NULL,NULL,NULL,NULL,'0'),('1447','华龙区','160','410902',NULL,NULL,NULL,NULL,'0'),('1448','清丰县','160','410922',NULL,NULL,NULL,NULL,'0'),('1449','南乐县','160','410923',NULL,NULL,NULL,NULL,'0'),('145','桥西区','9','130703',NULL,NULL,NULL,NULL,'0'),('1450','范县','160','410926',NULL,NULL,NULL,NULL,'0'),('1451','台前县','160','410927',NULL,NULL,NULL,NULL,'0'),('1452','濮阳县','160','410928',NULL,NULL,NULL,NULL,'0'),('1453','魏都区','161','411002',NULL,NULL,NULL,NULL,'0'),('1454','许昌县','161','411023',NULL,NULL,NULL,NULL,'0'),('1455','鄢陵县','161','411024',NULL,NULL,NULL,NULL,'0'),('1456','襄城县','161','411025',NULL,NULL,NULL,NULL,'0'),('1457','禹州市','161','411081',NULL,NULL,NULL,NULL,'0'),('1458','长葛市','161','411082',NULL,NULL,NULL,NULL,'0'),('1459','源汇区','162','411102',NULL,NULL,NULL,NULL,'0'),('146','宣化区','9','130705',NULL,NULL,NULL,NULL,'0'),('1460','郾城区','162','411103',NULL,NULL,NULL,NULL,'0'),('1461','召陵区','162','411104',NULL,NULL,NULL,NULL,'0'),('1462','舞阳县','162','411121',NULL,NULL,NULL,NULL,'0'),('1463','临颍县','162','411122',NULL,NULL,NULL,NULL,'0'),('1464','市辖区','163','540101',NULL,NULL,NULL,NULL,'0'),('1465','湖滨区','163','411202',NULL,NULL,NULL,NULL,'0'),('1466','渑池县','163','411221',NULL,NULL,NULL,NULL,'0'),('1467','陕县','163','411222',NULL,NULL,NULL,NULL,'0'),('1468','卢氏县','163','411224',NULL,NULL,NULL,NULL,'0'),('1469','义马市','163','411281',NULL,NULL,NULL,NULL,'0'),('147','下花园区','9','130706',NULL,NULL,NULL,NULL,'0'),('1470','灵宝市','163','411282',NULL,NULL,NULL,NULL,'0'),('1471','宛城区','164','411302',NULL,NULL,NULL,NULL,'0'),('1472','卧龙区','164','411303',NULL,NULL,NULL,NULL,'0'),('1473','南召县','164','411321',NULL,NULL,NULL,NULL,'0'),('1474','方城县','164','411322',NULL,NULL,NULL,NULL,'0'),('1475','西峡县','164','411323',NULL,NULL,NULL,NULL,'0'),('1476','镇平县','164','411324',NULL,NULL,NULL,NULL,'0'),('1477','内乡县','164','411325',NULL,NULL,NULL,NULL,'0'),('1478','淅川县','164','411326',NULL,NULL,NULL,NULL,'0'),('1479','社旗县','164','411327',NULL,NULL,NULL,NULL,'0'),('148','宣化县','9','130721',NULL,NULL,NULL,NULL,'0'),('1480','唐河县','164','411328',NULL,NULL,NULL,NULL,'0'),('1481','新野县','164','411329',NULL,NULL,NULL,NULL,'0'),('1482','桐柏县','164','411330',NULL,NULL,NULL,NULL,'0'),('1483','邓州市','164','411381',NULL,NULL,NULL,NULL,'0'),('1484','梁园区','165','411402',NULL,NULL,NULL,NULL,'0'),('1485','睢阳区','165','411403',NULL,NULL,NULL,NULL,'0'),('1486','民权县','165','411421',NULL,NULL,NULL,NULL,'0'),('1487','睢县','165','411422',NULL,NULL,NULL,NULL,'0'),('1488','宁陵县','165','411423',NULL,NULL,NULL,NULL,'0'),('1489','柘城县','165','411424',NULL,NULL,NULL,NULL,'0'),('149','张北县','9','130722',NULL,NULL,NULL,NULL,'0'),('1490','虞城县','165','411425',NULL,NULL,NULL,NULL,'0'),('1491','夏邑县','165','411426',NULL,NULL,NULL,NULL,'0'),('1492','永城市','165','411481',NULL,NULL,NULL,NULL,'0'),('1493','浉河区','166','411502',NULL,NULL,NULL,NULL,'0'),('1494','平桥区','166','411503',NULL,NULL,NULL,NULL,'0'),('1495','罗山县','166','411521',NULL,NULL,NULL,NULL,'0'),('1496','光山县','166','411522',NULL,NULL,NULL,NULL,'0'),('1497','新县','166','411523',NULL,NULL,NULL,NULL,'0'),('1498','商城县','166','411524',NULL,NULL,NULL,NULL,'0'),('1499','固始县','166','411525',NULL,NULL,NULL,NULL,'0'),('15','怀柔区','1','110116',NULL,NULL,NULL,NULL,'0'),('150','康保县','9','130723',NULL,NULL,NULL,NULL,'0'),('1500','潢川县','166','411526',NULL,NULL,NULL,NULL,'0'),('1501','淮滨县','166','411527',NULL,NULL,NULL,NULL,'0'),('1502','息县','166','411528',NULL,NULL,NULL,NULL,'0'),('1503','川汇区','167','411602',NULL,NULL,NULL,NULL,'0'),('1504','扶沟县','167','411621',NULL,NULL,NULL,NULL,'0'),('1505','西华县','167','411622',NULL,NULL,NULL,NULL,'0'),('1506','商水县','167','411623',NULL,NULL,NULL,NULL,'0'),('1507','沈丘县','167','411624',NULL,NULL,NULL,NULL,'0'),('1508','郸城县','167','411625',NULL,NULL,NULL,NULL,'0'),('1509','淮阳县','167','411626',NULL,NULL,NULL,NULL,'0'),('151','沽源县','9','130724',NULL,NULL,NULL,NULL,'0'),('1510','太康县','167','411627',NULL,NULL,NULL,NULL,'0'),('1511','鹿邑县','167','411628',NULL,NULL,NULL,NULL,'0'),('1512','项城市','167','411681',NULL,NULL,NULL,NULL,'0'),('1513','驿城区','168','411702',NULL,NULL,NULL,NULL,'0'),('1514','西平县','168','411721',NULL,NULL,NULL,NULL,'0'),('1515','上蔡县','168','411722',NULL,NULL,NULL,NULL,'0'),('1516','平舆县','168','411723',NULL,NULL,NULL,NULL,'0'),('1517','正阳县','168','411724',NULL,NULL,NULL,NULL,'0'),('1518','确山县','168','411725',NULL,NULL,NULL,NULL,'0'),('1519','泌阳县','168','411726',NULL,NULL,NULL,NULL,'0'),('152','尚义县','9','130725',NULL,NULL,NULL,NULL,'0'),('1520','汝南县','168','430921',NULL,NULL,NULL,NULL,'0'),('1521','遂平县','168','411728',NULL,NULL,NULL,NULL,'0'),('1522','新蔡县','168','411729',NULL,NULL,NULL,NULL,'0'),('1523','江岸区','169','420102',NULL,NULL,NULL,NULL,'0'),('1524','江汉区','169','420103',NULL,NULL,NULL,NULL,'0'),('1525','硚口区','169','420104',NULL,NULL,NULL,NULL,'0'),('1526','汉阳区','169','420105',NULL,NULL,NULL,NULL,'0'),('1527','武昌区','169','420106',NULL,NULL,NULL,NULL,'0'),('1528','青山区','169','420107',NULL,NULL,NULL,NULL,'0'),('1529','洪山区','169','420111',NULL,NULL,NULL,NULL,'0'),('153','蔚县','9','130726',NULL,NULL,NULL,NULL,'0'),('1530','东西湖区','169','420112',NULL,NULL,NULL,NULL,'0'),('1531','汉南区','169','420113',NULL,NULL,NULL,NULL,'0'),('1532','蔡甸区','169','420114',NULL,NULL,NULL,NULL,'0'),('1533','江夏区','169','420115',NULL,NULL,NULL,NULL,'0'),('1534','黄陂区','169','420116',NULL,NULL,NULL,NULL,'0'),('1535','新洲区','169','420117',NULL,NULL,NULL,NULL,'0'),('1536','黄石港区','170','420202',NULL,NULL,NULL,NULL,'0'),('1537','西塞山区','170','420203',NULL,NULL,NULL,NULL,'0'),('1538','下陆区','170','420204',NULL,NULL,NULL,NULL,'0'),('1539','铁山区','170','420205',NULL,NULL,NULL,NULL,'0'),('154','阳原县','9','130727',NULL,NULL,NULL,NULL,'0'),('1540','阳新县','170','420222',NULL,NULL,NULL,NULL,'0'),('1541','大冶市','170','420281',NULL,NULL,NULL,NULL,'0'),('1542','茅箭区','171','420302',NULL,NULL,NULL,NULL,'0'),('1543','张湾区','171','420303',NULL,NULL,NULL,NULL,'0'),('1544','郧县','171','420321',NULL,NULL,NULL,NULL,'0'),('1545','郧西县','171','420322',NULL,NULL,NULL,NULL,'0'),('1546','竹山县','171','420323',NULL,NULL,NULL,NULL,'0'),('1547','竹溪县','171','420324',NULL,NULL,NULL,NULL,'0'),('1548','房县','171','420325',NULL,NULL,NULL,NULL,'0'),('1549','丹江口市','171','420381',NULL,NULL,NULL,NULL,'0'),('155','怀安县','9','510724',NULL,NULL,NULL,NULL,'0'),('1550','西陵区','172','420502',NULL,NULL,NULL,NULL,'0'),('1551','伍家岗区','172','420503',NULL,NULL,NULL,NULL,'0'),('1552','点军区','172','420504',NULL,NULL,NULL,NULL,'0'),('1553','猇亭区','172','420505',NULL,NULL,NULL,NULL,'0'),('1554','夷陵区','172','420506',NULL,NULL,NULL,NULL,'0'),('1555','远安县','172','510724',NULL,NULL,NULL,NULL,'0'),('1556','兴山县','172','420526',NULL,NULL,NULL,NULL,'0'),('1557','秭归县','172','420527',NULL,NULL,NULL,NULL,'0'),('1558','长阳土家族自治县','172','420528',NULL,NULL,NULL,NULL,'0'),('1559','五峰土家族自治县','172','420529',NULL,NULL,NULL,NULL,'0'),('156','万全县','9','130729',NULL,NULL,NULL,NULL,'0'),('1560','宜都市','172','420581',NULL,NULL,NULL,NULL,'0'),('1561','当阳市','172','420582',NULL,NULL,NULL,NULL,'0'),('1562','枝江市','172','420583',NULL,NULL,NULL,NULL,'0'),('1563','襄城区','173','420602',NULL,NULL,NULL,NULL,'0'),('1564','樊城区','173','420606',NULL,NULL,NULL,NULL,'0'),('1565','襄阳区','173','420607',NULL,NULL,NULL,NULL,'0'),('1566','南漳县','173','420624',NULL,NULL,NULL,NULL,'0'),('1567','谷城县','173','420625',NULL,NULL,NULL,NULL,'0'),('1568','保康县','173','420626',NULL,NULL,NULL,NULL,'0'),('1569','老河口市','173','420682',NULL,NULL,NULL,NULL,'0'),('157','怀来县','9','130730',NULL,NULL,NULL,NULL,'0'),('1570','枣阳市','173','420683',NULL,NULL,NULL,NULL,'0'),('1571','宜城市','173','420684',NULL,NULL,NULL,NULL,'0'),('1572','梁子湖区','174','420702',NULL,NULL,NULL,NULL,'0'),('1573','华容区','174','420703',NULL,NULL,NULL,NULL,'0'),('1574','鄂城区','174','420704',NULL,NULL,NULL,NULL,'0'),('1575','东宝区','175','420802',NULL,NULL,NULL,NULL,'0'),('1576','掇刀区','175','420804',NULL,NULL,NULL,NULL,'0'),('1577','京山县','175','420821',NULL,NULL,NULL,NULL,'0'),('1578','沙洋县','175','420822',NULL,NULL,NULL,NULL,'0'),('1579','钟祥市','175','420881',NULL,NULL,NULL,NULL,'0'),('158','涿鹿县','9','130731',NULL,NULL,NULL,NULL,'0'),('1580','孝南区','176','420902',NULL,NULL,NULL,NULL,'0'),('1581','孝昌县','176','420921',NULL,NULL,NULL,NULL,'0'),('1582','大悟县','176','420922',NULL,NULL,NULL,NULL,'0'),('1583','云梦县','176','420923',NULL,NULL,NULL,NULL,'0'),('1584','应城市','176','420981',NULL,NULL,NULL,NULL,'0'),('1585','安陆市','176','420982',NULL,NULL,NULL,NULL,'0'),('1586','汉川市','176','420984',NULL,NULL,NULL,NULL,'0'),('1587','沙市区','177','421002',NULL,NULL,NULL,NULL,'0'),('1588','荆州区','177','421003',NULL,NULL,NULL,NULL,'0'),('1589','公安县','177','510724',NULL,NULL,NULL,NULL,'0'),('159','赤城县','9','130732',NULL,NULL,NULL,NULL,'0'),('1590','监利县','177','421023',NULL,NULL,NULL,NULL,'0'),('1591','江陵县','177','421024',NULL,NULL,NULL,NULL,'0'),('1592','石首市','177','421081',NULL,NULL,NULL,NULL,'0'),('1593','洪湖市','177','421083',NULL,NULL,NULL,NULL,'0'),('1594','松滋市','177','421087',NULL,NULL,NULL,NULL,'0'),('1595','黄州区','178','421102',NULL,NULL,NULL,NULL,'0'),('1596','团风县','178','421121',NULL,NULL,NULL,NULL,'0'),('1597','红安县','178','510724',NULL,NULL,NULL,NULL,'0'),('1598','罗田县','178','421123',NULL,NULL,NULL,NULL,'0'),('1599','英山县','178','421124',NULL,NULL,NULL,NULL,'0'),('16','平谷区','1','110117',NULL,NULL,NULL,NULL,'0'),('160','崇礼县','9','621226',NULL,NULL,NULL,NULL,'0'),('1600','浠水县','178','421125',NULL,NULL,NULL,NULL,'0'),('1601','蕲春县','178','421126',NULL,NULL,NULL,NULL,'0'),('1602','黄梅县','178','441421',NULL,NULL,NULL,NULL,'0'),('1603','麻城市','178','421181',NULL,NULL,NULL,NULL,'0'),('1604','武穴市','178','421182',NULL,NULL,NULL,NULL,'0'),('1605','咸安区','179','421202',NULL,NULL,NULL,NULL,'0'),('1606','嘉鱼县','179','421221',NULL,NULL,NULL,NULL,'0'),('1607','通城县','179','421222',NULL,NULL,NULL,NULL,'0'),('1608','崇阳县','179','421223',NULL,NULL,NULL,NULL,'0'),('1609','通山县','179','421224',NULL,NULL,NULL,NULL,'0'),('161','双桥区','10','500111',NULL,NULL,NULL,NULL,'0'),('1610','赤壁市','179','421281',NULL,NULL,NULL,NULL,'0'),('1611','曾都区','180','421302',NULL,NULL,NULL,NULL,'0'),('1612','广水市','180','421381',NULL,NULL,NULL,NULL,'0'),('1613','恩施市','181','422801',NULL,NULL,NULL,NULL,'0'),('1614','利川市','181','422802',NULL,NULL,NULL,NULL,'0'),('1615','建始县','181','422822',NULL,NULL,NULL,NULL,'0'),('1616','巴东县','181','422823',NULL,NULL,NULL,NULL,'0'),('1617','宣恩县','181','422825',NULL,NULL,NULL,NULL,'0'),('1618','咸丰县','181','422826',NULL,NULL,NULL,NULL,'0'),('1619','来凤县','181','422827',NULL,NULL,NULL,NULL,'0'),('162','双滦区','10','130803',NULL,NULL,NULL,NULL,'0'),('1620','鹤峰县','181','422828',NULL,NULL,NULL,NULL,'0'),('1621','仙桃市','182','429004',NULL,NULL,NULL,NULL,'0'),('1622','潜江市','182','429005',NULL,NULL,NULL,NULL,'0'),('1623','天门市','182','429006',NULL,NULL,NULL,NULL,'0'),('1624','神农架林区','182','429021',NULL,NULL,NULL,NULL,'0'),('1625','芙蓉区','183','430102',NULL,NULL,NULL,NULL,'0'),('1626','天心区','183','430103',NULL,NULL,NULL,NULL,'0'),('1627','岳麓区','183','430104',NULL,NULL,NULL,NULL,'0'),('1628','开福区','183','430105',NULL,NULL,NULL,NULL,'0'),('1629','雨花区','183','430111',NULL,NULL,NULL,NULL,'0'),('163','鹰手营子矿区','10','130804',NULL,NULL,NULL,NULL,'0'),('1630','长沙县','183','430121',NULL,NULL,NULL,NULL,'0'),('1631','望城县','183','430122',NULL,NULL,NULL,NULL,'0'),('1632','宁乡县','183','430124',NULL,NULL,NULL,NULL,'0'),('1633','浏阳市','183','430181',NULL,NULL,NULL,NULL,'0'),('1634','荷塘区','184','430202',NULL,NULL,NULL,NULL,'0'),('1635','芦淞区','184','430203',NULL,NULL,NULL,NULL,'0'),('1636','石峰区','184','430204',NULL,NULL,NULL,NULL,'0'),('1637','天元区','184','430211',NULL,NULL,NULL,NULL,'0'),('1638','株洲县','184','430221',NULL,NULL,NULL,NULL,'0'),('1639','攸县','184','430223',NULL,NULL,NULL,NULL,'0'),('164','承德县','10','130821',NULL,NULL,NULL,NULL,'0'),('1640','茶陵县','184','430224',NULL,NULL,NULL,NULL,'0'),('1641','炎陵县','184','430225',NULL,NULL,NULL,NULL,'0'),('1642','醴陵市','184','430281',NULL,NULL,NULL,NULL,'0'),('1643','雨湖区','185','430302',NULL,NULL,NULL,NULL,'0'),('1644','岳塘区','185','430304',NULL,NULL,NULL,NULL,'0'),('1645','湘潭县','185','430321',NULL,NULL,NULL,NULL,'0'),('1646','湘乡市','185','430381',NULL,NULL,NULL,NULL,'0'),('1647','韶山市','185','430382',NULL,NULL,NULL,NULL,'0'),('1648','珠晖区','186','430405',NULL,NULL,NULL,NULL,'0'),('1649','雁峰区','186','430406',NULL,NULL,NULL,NULL,'0'),('165','兴隆县','10','130822',NULL,NULL,NULL,NULL,'0'),('1650','石鼓区','186','430407',NULL,NULL,NULL,NULL,'0'),('1651','蒸湘区','186','430408',NULL,NULL,NULL,NULL,'0'),('1652','南岳区','186','430412',NULL,NULL,NULL,NULL,'0'),('1653','衡阳县','186','430421',NULL,NULL,NULL,NULL,'0'),('1654','衡南县','186','430921',NULL,NULL,NULL,NULL,'0'),('1655','衡山县','186','430423',NULL,NULL,NULL,NULL,'0'),('1656','衡东县','186','430424',NULL,NULL,NULL,NULL,'0'),('1657','祁东县','186','430426',NULL,NULL,NULL,NULL,'0'),('1658','耒阳市','186','430481',NULL,NULL,NULL,NULL,'0'),('1659','常宁市','186','430482',NULL,NULL,NULL,NULL,'0'),('166','平泉县','10','130823',NULL,NULL,NULL,NULL,'0'),('1660','双清区','187','430502',NULL,NULL,NULL,NULL,'0'),('1661','大祥区','187','430503',NULL,NULL,NULL,NULL,'0'),('1662','北塔区','187','430511',NULL,NULL,NULL,NULL,'0'),('1663','邵东县','187','430521',NULL,NULL,NULL,NULL,'0'),('1664','新邵县','187','430522',NULL,NULL,NULL,NULL,'0'),('1665','邵阳县','187','430523',NULL,NULL,NULL,NULL,'0'),('1666','隆回县','187','430524',NULL,NULL,NULL,NULL,'0'),('1667','洞口县','187','430525',NULL,NULL,NULL,NULL,'0'),('1668','绥宁县','187','430527',NULL,NULL,NULL,NULL,'0'),('1669','新宁县','187','430528',NULL,NULL,NULL,NULL,'0'),('167','滦平县','10','130824',NULL,NULL,NULL,NULL,'0'),('1670','城步苗族自治县','187','430529',NULL,NULL,NULL,NULL,'0'),('1671','武冈市','187','430581',NULL,NULL,NULL,NULL,'0'),('1672','岳阳楼区','188','430602',NULL,NULL,NULL,NULL,'0'),('1673','云溪区','188','430603',NULL,NULL,NULL,NULL,'0'),('1674','君山区','188','430611',NULL,NULL,NULL,NULL,'0'),('1675','岳阳县','188','430621',NULL,NULL,NULL,NULL,'0'),('1676','华容县','188','450921',NULL,NULL,NULL,NULL,'0'),('1677','湘阴县','188','430624',NULL,NULL,NULL,NULL,'0'),('1678','平江县','188','430626',NULL,NULL,NULL,NULL,'0'),('1679','汨罗市','188','430681',NULL,NULL,NULL,NULL,'0'),('168','隆化县','10','130825',NULL,NULL,NULL,NULL,'0'),('1680','临湘市','188','430682',NULL,NULL,NULL,NULL,'0'),('1681','武陵区','189','430702',NULL,NULL,NULL,NULL,'0'),('1682','鼎城区','189','430703',NULL,NULL,NULL,NULL,'0'),('1683','安乡县','189','430721',NULL,NULL,NULL,NULL,'0'),('1684','汉寿县','189','430722',NULL,NULL,NULL,NULL,'0'),('1685','澧县','189','430723',NULL,NULL,NULL,NULL,'0'),('1686','临澧县','189','430724',NULL,NULL,NULL,NULL,'0'),('1687','桃源县','189','430725',NULL,NULL,NULL,NULL,'0'),('1688','石门县','189','430726',NULL,NULL,NULL,NULL,'0'),('1689','津市市','189','430781',NULL,NULL,NULL,NULL,'0'),('169','丰宁满族自治县','10','130826',NULL,NULL,NULL,NULL,'0'),('1690','永定区','190','430802',NULL,NULL,NULL,NULL,'0'),('1691','武陵源区','190','430811',NULL,NULL,NULL,NULL,'0'),('1692','慈利县','190','430821',NULL,NULL,NULL,NULL,'0'),('1693','桑植县','190','430822',NULL,NULL,NULL,NULL,'0'),('1694','资阳区','191','430902',NULL,NULL,NULL,NULL,'0'),('1695','赫山区','191','430903',NULL,NULL,NULL,NULL,'0'),('1696','南县','191','430921',NULL,NULL,NULL,NULL,'0'),('1697','桃江县','191','430922',NULL,NULL,NULL,NULL,'0'),('1698','安化县','191','430923',NULL,NULL,NULL,NULL,'0'),('1699','沅江市','191','430981',NULL,NULL,NULL,NULL,'0'),('17','密云县','1','530922',NULL,NULL,NULL,NULL,'0'),('170','宽城满族自治县','10','130827',NULL,NULL,NULL,NULL,'0'),('1700','北湖区','192','431002',NULL,NULL,NULL,NULL,'0'),('1701','苏仙区','192','431003',NULL,NULL,NULL,NULL,'0'),('1702','桂阳县','192','431021',NULL,NULL,NULL,NULL,'0'),('1703','宜章县','192','431022',NULL,NULL,NULL,NULL,'0'),('1704','永兴县','192','431023',NULL,NULL,NULL,NULL,'0'),('1705','嘉禾县','192','431024',NULL,NULL,NULL,NULL,'0'),('1706','临武县','192','431025',NULL,NULL,NULL,NULL,'0'),('1707','汝城县','192','431026',NULL,NULL,NULL,NULL,'0'),('1708','桂东县','192','431027',NULL,NULL,NULL,NULL,'0'),('1709','安仁县','192','431028',NULL,NULL,NULL,NULL,'0'),('171','围场满族蒙古族自治县','10',NULL,NULL,NULL,NULL,NULL,'0'),('1710','资兴市','192','431081',NULL,NULL,NULL,NULL,'0'),('1711','芝山区','193',NULL,NULL,NULL,NULL,NULL,'0'),('1712','冷水滩区','193','431103',NULL,NULL,NULL,NULL,'0'),('1713','祁阳县','193','431121',NULL,NULL,NULL,NULL,'0'),('1714','东安县','193','510724',NULL,NULL,NULL,NULL,'0'),('1715','双牌县','193','431123',NULL,NULL,NULL,NULL,'0'),('1716','道县','193','431124',NULL,NULL,NULL,NULL,'0'),('1717','江永县','193','431125',NULL,NULL,NULL,NULL,'0'),('1718','宁远县','193','431126',NULL,NULL,NULL,NULL,'0'),('1719','蓝山县','193','431127',NULL,NULL,NULL,NULL,'0'),('172','新华区','11','410402',NULL,NULL,NULL,NULL,'0'),('1720','新田县','193','431128',NULL,NULL,NULL,NULL,'0'),('1721','江华瑶族自治县','193','431129',NULL,NULL,NULL,NULL,'0'),('1722','鹤城区','194','431202',NULL,NULL,NULL,NULL,'0'),('1723','中方县','194','431221',NULL,NULL,NULL,NULL,'0'),('1724','沅陵县','194','431222',NULL,NULL,NULL,NULL,'0'),('1725','辰溪县','194','431223',NULL,NULL,NULL,NULL,'0'),('1726','溆浦县','194','431224',NULL,NULL,NULL,NULL,'0'),('1727','会同县','194','431225',NULL,NULL,NULL,NULL,'0'),('1728','麻阳苗族自治县','194','431226',NULL,NULL,NULL,NULL,'0'),('1729','新晃侗族自治县','194','431227',NULL,NULL,NULL,NULL,'0'),('173','运河区','11','130903',NULL,NULL,NULL,NULL,'0'),('1730','芷江侗族自治县','194','431228',NULL,NULL,NULL,NULL,'0'),('1731','靖州苗族侗族自治县','194','431229',NULL,NULL,NULL,NULL,'0'),('1732','通道侗族自治县','194','431230',NULL,NULL,NULL,NULL,'0'),('1733','洪江市','194','431281',NULL,NULL,NULL,NULL,'0'),('1734','娄星区','195','431302',NULL,NULL,NULL,NULL,'0'),('1735','双峰县','195','431321',NULL,NULL,NULL,NULL,'0'),('1736','新化县','195','431322',NULL,NULL,NULL,NULL,'0'),('1737','冷水江市','195','431381',NULL,NULL,NULL,NULL,'0'),('1738','涟源市','195','431382',NULL,NULL,NULL,NULL,'0'),('1739','吉首市','196','433101',NULL,NULL,NULL,NULL,'0'),('174','沧县','11','130921',NULL,NULL,NULL,NULL,'0'),('1740','泸溪县','196','433122',NULL,NULL,NULL,NULL,'0'),('1741','凤凰县','196','433123',NULL,NULL,NULL,NULL,'0'),('1742','花垣县','196','433124',NULL,NULL,NULL,NULL,'0'),('1743','保靖县','196','433125',NULL,NULL,NULL,NULL,'0'),('1744','古丈县','196','433126',NULL,NULL,NULL,NULL,'0'),('1745','永顺县','196','433127',NULL,NULL,NULL,NULL,'0'),('1746','龙山县','196','433130',NULL,NULL,NULL,NULL,'0'),('1747','东山区','197','650108',NULL,NULL,NULL,NULL,'0'),('1748','荔湾区','197','440103',NULL,NULL,NULL,NULL,'0'),('1749','越秀区','197','440104',NULL,NULL,NULL,NULL,'0'),('175','青县','11','130922',NULL,NULL,NULL,NULL,'0'),('1750','海珠区','197','440105',NULL,NULL,NULL,NULL,'0'),('1751','天河区','197','440106',NULL,NULL,NULL,NULL,'0'),('1752','芳村区','197',NULL,NULL,NULL,NULL,NULL,'0'),('1753','白云区','197','520113',NULL,NULL,NULL,NULL,'0'),('1754','黄埔区','197','440112',NULL,NULL,NULL,NULL,'0'),('1755','番禺区','197','440113',NULL,NULL,NULL,NULL,'0'),('1756','花都区','197','440114',NULL,NULL,NULL,NULL,'0'),('1757','增城市','197','440183',NULL,NULL,NULL,NULL,'0'),('1758','从化市','197','440184',NULL,NULL,NULL,NULL,'0'),('1759','武江区','198','440203',NULL,NULL,NULL,NULL,'0'),('176','东光县','11','130923',NULL,NULL,NULL,NULL,'0'),('1760','浈江区','198','440204',NULL,NULL,NULL,NULL,'0'),('1761','曲江区','198','440205',NULL,NULL,NULL,NULL,'0'),('1762','始兴县','198','440222',NULL,NULL,NULL,NULL,'0'),('1763','仁化县','198','440224',NULL,NULL,NULL,NULL,'0'),('1764','翁源县','198','440229',NULL,NULL,NULL,NULL,'0'),('1765','乳源瑶族自治县','198','440232',NULL,NULL,NULL,NULL,'0'),('1766','新丰县','198','440233',NULL,NULL,NULL,NULL,'0'),('1767','乐昌市','198','440281',NULL,NULL,NULL,NULL,'0'),('1768','南雄市','198','440282',NULL,NULL,NULL,NULL,'0'),('1769','罗湖区','199','440303',NULL,NULL,NULL,NULL,'0'),('177','海兴县','11','130924',NULL,NULL,NULL,NULL,'0'),('1770','福田区','199','440304',NULL,NULL,NULL,NULL,'0'),('1771','南山区','199','440305',NULL,NULL,NULL,NULL,'0'),('1772','宝安区','199','440306',NULL,NULL,NULL,NULL,'0'),('1773','龙岗区','199','440307',NULL,NULL,NULL,NULL,'0'),('1774','盐田区','199','440308',NULL,NULL,NULL,NULL,'0'),('1775','香洲区','200','440402',NULL,NULL,NULL,NULL,'0'),('1776','斗门区','200','440403',NULL,NULL,NULL,NULL,'0'),('1777','金湾区','200','440404',NULL,NULL,NULL,NULL,'0'),('1778','龙湖区','201','440507',NULL,NULL,NULL,NULL,'0'),('1779','金平区','201','440511',NULL,NULL,NULL,NULL,'0'),('178','盐山县','11','130925',NULL,NULL,NULL,NULL,'0'),('1780','濠江区','201','440512',NULL,NULL,NULL,NULL,'0'),('1781','潮阳区','201','440513',NULL,NULL,NULL,NULL,'0'),('1782','潮南区','201','440514',NULL,NULL,NULL,NULL,'0'),('1783','澄海区','201','440515',NULL,NULL,NULL,NULL,'0'),('1784','南澳县','201','440523',NULL,NULL,NULL,NULL,'0'),('1785','禅城区','202','440604',NULL,NULL,NULL,NULL,'0'),('1786','南海区','202','440605',NULL,NULL,NULL,NULL,'0'),('1787','顺德区','202','440606',NULL,NULL,NULL,NULL,'0'),('1788','三水区','202','440607',NULL,NULL,NULL,NULL,'0'),('1789','高明区','202','440608',NULL,NULL,NULL,NULL,'0'),('179','肃宁县','11','621026',NULL,NULL,NULL,NULL,'0'),('1790','蓬江区','203','440703',NULL,NULL,NULL,NULL,'0'),('1791','江海区','203','440704',NULL,NULL,NULL,NULL,'0'),('1792','新会区','203','440705',NULL,NULL,NULL,NULL,'0'),('1793','台山市','203','440781',NULL,NULL,NULL,NULL,'0'),('1794','开平市','203','440783',NULL,NULL,NULL,NULL,'0'),('1795','鹤山市','203','440784',NULL,NULL,NULL,NULL,'0'),('1796','恩平市','203','440785',NULL,NULL,NULL,NULL,'0'),('1797','赤坎区','204','440802',NULL,NULL,NULL,NULL,'0'),('1798','霞山区','204','440803',NULL,NULL,NULL,NULL,'0'),('1799','坡头区','204','440804',NULL,NULL,NULL,NULL,'0'),('18','延庆县','1','110229',NULL,NULL,NULL,NULL,'0'),('180','南皮县','11','130927',NULL,NULL,NULL,NULL,'0'),('1800','麻章区','204','440811',NULL,NULL,NULL,NULL,'0'),('1801','遂溪县','204','440823',NULL,NULL,NULL,NULL,'0'),('1802','徐闻县','204','440825',NULL,NULL,NULL,NULL,'0'),('1803','廉江市','204','440881',NULL,NULL,NULL,NULL,'0'),('1804','雷州市','204','440882',NULL,NULL,NULL,NULL,'0'),('1805','吴川市','204','440883',NULL,NULL,NULL,NULL,'0'),('1806','茂南区','205','440902',NULL,NULL,NULL,NULL,'0'),('1807','茂港区','205','440903',NULL,NULL,NULL,NULL,'0'),('1808','电白县','205','440923',NULL,NULL,NULL,NULL,'0'),('1809','高州市','205','440981',NULL,NULL,NULL,NULL,'0'),('181','吴桥县','11','130928',NULL,NULL,NULL,NULL,'0'),('1810','化州市','205','440982',NULL,NULL,NULL,NULL,'0'),('1811','信宜市','205','440983',NULL,NULL,NULL,NULL,'0'),('1812','端州区','206','441202',NULL,NULL,NULL,NULL,'0'),('1813','鼎湖区','206','441203',NULL,NULL,NULL,NULL,'0'),('1814','广宁县','206','441223',NULL,NULL,NULL,NULL,'0'),('1815','怀集县','206','441224',NULL,NULL,NULL,NULL,'0'),('1816','封开县','206','500234',NULL,NULL,NULL,NULL,'0'),('1817','德庆县','206','441226',NULL,NULL,NULL,NULL,'0'),('1818','高要市','206','441283',NULL,NULL,NULL,NULL,'0'),('1819','四会市','206','441284',NULL,NULL,NULL,NULL,'0'),('182','献县','11','130929',NULL,NULL,NULL,NULL,'0'),('1820','惠城区','207','441302',NULL,NULL,NULL,NULL,'0'),('1821','惠阳区','207','441303',NULL,NULL,NULL,NULL,'0'),('1822','博罗县','207','441322',NULL,NULL,NULL,NULL,'0'),('1823','惠东县','207','441323',NULL,NULL,NULL,NULL,'0'),('1824','龙门县','207','441324',NULL,NULL,NULL,NULL,'0'),('1825','梅江区','208','441402',NULL,NULL,NULL,NULL,'0'),('1826','梅县','208','441421',NULL,NULL,NULL,NULL,'0'),('1827','大埔县','208','441422',NULL,NULL,NULL,NULL,'0'),('1828','丰顺县','208','441423',NULL,NULL,NULL,NULL,'0'),('1829','五华县','208','441424',NULL,NULL,NULL,NULL,'0'),('183','孟村回族自治县','11','130930',NULL,NULL,NULL,NULL,'0'),('1830','平远县','208','441426',NULL,NULL,NULL,NULL,'0'),('1831','蕉岭县','208','441427',NULL,NULL,NULL,NULL,'0'),('1832','兴宁市','208','441481',NULL,NULL,NULL,NULL,'0'),('1833','城区','209',NULL,NULL,NULL,NULL,NULL,'0'),('1834','海丰县','209','441521',NULL,NULL,NULL,NULL,'0'),('1835','陆河县','209','441523',NULL,NULL,NULL,NULL,'0'),('1836','陆丰市','209','441581',NULL,NULL,NULL,NULL,'0'),('1837','源城区','210','441602',NULL,NULL,NULL,NULL,'0'),('1838','紫金县','210','441621',NULL,NULL,NULL,NULL,'0'),('1839','龙川县','210','441622',NULL,NULL,NULL,NULL,'0'),('184','泊头市','11','130981',NULL,NULL,NULL,NULL,'0'),('1840','连平县','210','441623',NULL,NULL,NULL,NULL,'0'),('1841','和平县','210','441624',NULL,NULL,NULL,NULL,'0'),('1842','东源县','210','441625',NULL,NULL,NULL,NULL,'0'),('1843','江城区','211','441702',NULL,NULL,NULL,NULL,'0'),('1844','阳西县','211','441721',NULL,NULL,NULL,NULL,'0'),('1845','阳东县','211','441723',NULL,NULL,NULL,NULL,'0'),('1846','阳春市','211','441781',NULL,NULL,NULL,NULL,'0'),('1847','清城区','212','441802',NULL,NULL,NULL,NULL,'0'),('1848','佛冈县','212','441821',NULL,NULL,NULL,NULL,'0'),('1849','阳山县','212','441823',NULL,NULL,NULL,NULL,'0'),('185','任丘市','11','130982',NULL,NULL,NULL,NULL,'0'),('1850','连山壮族瑶族自治县','212','441825',NULL,NULL,NULL,NULL,'0'),('1851','连南瑶族自治县','212','441826',NULL,NULL,NULL,NULL,'0'),('1852','清新县','212','441827',NULL,NULL,NULL,NULL,'0'),('1853','英德市','212','441881',NULL,NULL,NULL,NULL,'0'),('1854','连州市','212','441882',NULL,NULL,NULL,NULL,'0'),('1855','湘桥区','215','445102',NULL,NULL,NULL,NULL,'0'),('1856','潮安县','215','510724',NULL,NULL,NULL,NULL,'0'),('1857','饶平县','215','445122',NULL,NULL,NULL,NULL,'0'),('1858','榕城区','216','445202',NULL,NULL,NULL,NULL,'0'),('1859','揭东县','216','445221',NULL,NULL,NULL,NULL,'0'),('186','黄骅市','11','130983',NULL,NULL,NULL,NULL,'0'),('1860','揭西县','216','445222',NULL,NULL,NULL,NULL,'0'),('1861','惠来县','216','445224',NULL,NULL,NULL,NULL,'0'),('1862','普宁市','216','445281',NULL,NULL,NULL,NULL,'0'),('1863','云城区','217','445302',NULL,NULL,NULL,NULL,'0'),('1864','新兴县','217','445321',NULL,NULL,NULL,NULL,'0'),('1865','郁南县','217','445322',NULL,NULL,NULL,NULL,'0'),('1866','云安县','217','510724',NULL,NULL,NULL,NULL,'0'),('1867','罗定市','217','445381',NULL,NULL,NULL,NULL,'0'),('1868','兴宁区','218','450102',NULL,NULL,NULL,NULL,'0'),('1869','青秀区','218','450103',NULL,NULL,NULL,NULL,'0'),('187','河间市','11','130984',NULL,NULL,NULL,NULL,'0'),('1870','江南区','218','450105',NULL,NULL,NULL,NULL,'0'),('1871','西乡塘区','218','450107',NULL,NULL,NULL,NULL,'0'),('1872','良庆区','218','450108',NULL,NULL,NULL,NULL,'0'),('1873','邕宁区','218','450109',NULL,NULL,NULL,NULL,'0'),('1874','武鸣县','218','450122',NULL,NULL,NULL,NULL,'0'),('1875','隆安县','218','510724',NULL,NULL,NULL,NULL,'0'),('1876','马山县','218','450124',NULL,NULL,NULL,NULL,'0'),('1877','上林县','218','450125',NULL,NULL,NULL,NULL,'0'),('1878','宾阳县','218','450126',NULL,NULL,NULL,NULL,'0'),('1879','横县','218','450127',NULL,NULL,NULL,NULL,'0'),('188','安次区','12','131002',NULL,NULL,NULL,NULL,'0'),('1880','城中区','219','450202',NULL,NULL,NULL,NULL,'0'),('1881','鱼峰区','219','450203',NULL,NULL,NULL,NULL,'0'),('1882','柳南区','219','450204',NULL,NULL,NULL,NULL,'0'),('1883','柳北区','219','450205',NULL,NULL,NULL,NULL,'0'),('1884','柳江县','219','450221',NULL,NULL,NULL,NULL,'0'),('1885','柳城县','219','450222',NULL,NULL,NULL,NULL,'0'),('1886','鹿寨县','219','450223',NULL,NULL,NULL,NULL,'0'),('1887','融安县','219','510724',NULL,NULL,NULL,NULL,'0'),('1888','融水苗族自治县','219','450225',NULL,NULL,NULL,NULL,'0'),('1889','三江侗族自治县','219','450226',NULL,NULL,NULL,NULL,'0'),('189','广阳区','12','131003',NULL,NULL,NULL,NULL,'0'),('1890','秀峰区','220','450302',NULL,NULL,NULL,NULL,'0'),('1891','叠彩区','220','450303',NULL,NULL,NULL,NULL,'0'),('1892','象山区','220','450304',NULL,NULL,NULL,NULL,'0'),('1893','七星区','220','450305',NULL,NULL,NULL,NULL,'0'),('1894','雁山区','220','450311',NULL,NULL,NULL,NULL,'0'),('1895','阳朔县','220','450321',NULL,NULL,NULL,NULL,'0'),('1896','临桂县','220','450322',NULL,NULL,NULL,NULL,'0'),('1897','灵川县','220','450323',NULL,NULL,NULL,NULL,'0'),('1898','全州县','220','450324',NULL,NULL,NULL,NULL,'0'),('1899','兴安县','220','510724',NULL,NULL,NULL,NULL,'0'),('19','和平区','2','120101',NULL,NULL,NULL,NULL,'0'),('190','固安县','12','510724',NULL,NULL,NULL,NULL,'0'),('1900','永福县','220','450326',NULL,NULL,NULL,NULL,'0'),('1901','灌阳县','220','450327',NULL,NULL,NULL,NULL,'0'),('1902','龙胜各族自治县','220','450328',NULL,NULL,NULL,NULL,'0'),('1903','资源县','220','450329',NULL,NULL,NULL,NULL,'0'),('1904','平乐县','220','450330',NULL,NULL,NULL,NULL,'0'),('1905','荔蒲县','220','450331',NULL,NULL,NULL,NULL,'0'),('1906','恭城瑶族自治县','220','450332',NULL,NULL,NULL,NULL,'0'),('1907','万秀区','221','450403',NULL,NULL,NULL,NULL,'0'),('1908','蝶山区','221','450404',NULL,NULL,NULL,NULL,'0'),('1909','长洲区','221','450405',NULL,NULL,NULL,NULL,'0'),('191','永清县','12','131023',NULL,NULL,NULL,NULL,'0'),('1910','苍梧县','221','450421',NULL,NULL,NULL,NULL,'0'),('1911','藤县','221','450422',NULL,NULL,NULL,NULL,'0'),('1912','蒙山县','221','450423',NULL,NULL,NULL,NULL,'0'),('1913','岑溪市','221','450481',NULL,NULL,NULL,NULL,'0'),('1914','海城区','222','450502',NULL,NULL,NULL,NULL,'0'),('1915','银海区','222','450503',NULL,NULL,NULL,NULL,'0'),('1916','铁山港区','222','450512',NULL,NULL,NULL,NULL,'0'),('1917','合浦县','222','450521',NULL,NULL,NULL,NULL,'0'),('1918','港口区','223','450602',NULL,NULL,NULL,NULL,'0'),('1919','防城区','223','450603',NULL,NULL,NULL,NULL,'0'),('192','香河县','12','131024',NULL,NULL,NULL,NULL,'0'),('1920','上思县','223','450621',NULL,NULL,NULL,NULL,'0'),('1921','东兴市','223','450681',NULL,NULL,NULL,NULL,'0'),('1922','钦南区','224','450702',NULL,NULL,NULL,NULL,'0'),('1923','钦北区','224','450703',NULL,NULL,NULL,NULL,'0'),('1924','灵山县','224','450721',NULL,NULL,NULL,NULL,'0'),('1925','浦北县','224','450722',NULL,NULL,NULL,NULL,'0'),('1926','港北区','225','450802',NULL,NULL,NULL,NULL,'0'),('1927','港南区','225','450803',NULL,NULL,NULL,NULL,'0'),('1928','覃塘区','225','450804',NULL,NULL,NULL,NULL,'0'),('1929','平南县','225','450821',NULL,NULL,NULL,NULL,'0'),('193','大城县','12','131025',NULL,NULL,NULL,NULL,'0'),('1930','桂平市','225','450881',NULL,NULL,NULL,NULL,'0'),('1931','玉州区','226','450902',NULL,NULL,NULL,NULL,'0'),('1932','容县','226','450921',NULL,NULL,NULL,NULL,'0'),('1933','陆川县','226','450922',NULL,NULL,NULL,NULL,'0'),('1934','博白县','226','450923',NULL,NULL,NULL,NULL,'0'),('1935','兴业县','226','450924',NULL,NULL,NULL,NULL,'0'),('1936','北流市','226','450981',NULL,NULL,NULL,NULL,'0'),('1937','右江区','227','451002',NULL,NULL,NULL,NULL,'0'),('1938','田阳县','227','451021',NULL,NULL,NULL,NULL,'0'),('1939','田东县','227','451022',NULL,NULL,NULL,NULL,'0'),('194','文安县','12','510724',NULL,NULL,NULL,NULL,'0'),('1940','平果县','227','451023',NULL,NULL,NULL,NULL,'0'),('1941','德保县','227','451024',NULL,NULL,NULL,NULL,'0'),('1942','靖西县','227','451025',NULL,NULL,NULL,NULL,'0'),('1943','那坡县','227','451026',NULL,NULL,NULL,NULL,'0'),('1944','凌云县','227','530922',NULL,NULL,NULL,NULL,'0'),('1945','乐业县','227','451028',NULL,NULL,NULL,NULL,'0'),('1946','田林县','227','451029',NULL,NULL,NULL,NULL,'0'),('1947','西林县','227','451030',NULL,NULL,NULL,NULL,'0'),('1948','隆林各族自治县','227','451031',NULL,NULL,NULL,NULL,'0'),('1949','八步区','228','451102',NULL,NULL,NULL,NULL,'0'),('195','大厂回族自治县','12','131028',NULL,NULL,NULL,NULL,'0'),('1950','昭平县','228','451121',NULL,NULL,NULL,NULL,'0'),('1951','钟山县','228','451122',NULL,NULL,NULL,NULL,'0'),('1952','富川瑶族自治县','228','451123',NULL,NULL,NULL,NULL,'0'),('1953','金城江区','229','451202',NULL,NULL,NULL,NULL,'0'),('1954','南丹县','229','451221',NULL,NULL,NULL,NULL,'0'),('1955','天峨县','229','451222',NULL,NULL,NULL,NULL,'0'),('1956','凤山县','229','451223',NULL,NULL,NULL,NULL,'0'),('1957','东兰县','229','451224',NULL,NULL,NULL,NULL,'0'),('1958','罗城仫佬族自治县','229','451225',NULL,NULL,NULL,NULL,'0'),('1959','环江毛南族自治县','229','451226',NULL,NULL,NULL,NULL,'0'),('196','霸州市','12','131081',NULL,NULL,NULL,NULL,'0'),('1960','巴马瑶族自治县','229','451227',NULL,NULL,NULL,NULL,'0'),('1961','都安瑶族自治县','229','451228',NULL,NULL,NULL,NULL,'0'),('1962','大化瑶族自治县','229','451229',NULL,NULL,NULL,NULL,'0'),('1963','宜州市','229','451281',NULL,NULL,NULL,NULL,'0'),('1964','兴宾区','230','451302',NULL,NULL,NULL,NULL,'0'),('1965','忻城县','230','451321',NULL,NULL,NULL,NULL,'0'),('1966','象州县','230','451322',NULL,NULL,NULL,NULL,'0'),('1967','武宣县','230','451323',NULL,NULL,NULL,NULL,'0'),('1968','金秀瑶族自治县','230','451324',NULL,NULL,NULL,NULL,'0'),('1969','合山市','230','451381',NULL,NULL,NULL,NULL,'0'),('197','三河市','12','131082',NULL,NULL,NULL,NULL,'0'),('1970','江洲区','231','451402',NULL,NULL,NULL,NULL,'0'),('1971','扶绥县','231','451421',NULL,NULL,NULL,NULL,'0'),('1972','宁明县','231','451422',NULL,NULL,NULL,NULL,'0'),('1973','龙州县','231','451423',NULL,NULL,NULL,NULL,'0'),('1974','大新县','231','451424',NULL,NULL,NULL,NULL,'0'),('1975','天等县','231','451425',NULL,NULL,NULL,NULL,'0'),('1976','凭祥市','231','451481',NULL,NULL,NULL,NULL,'0'),('1977','秀英区','232','460105',NULL,NULL,NULL,NULL,'0'),('1978','龙华区','232','460106',NULL,NULL,NULL,NULL,'0'),('1979','琼山区','232','460107',NULL,NULL,NULL,NULL,'0'),('198','桃城区','13','131102',NULL,NULL,NULL,NULL,'0'),('1980','美兰区','232','460108',NULL,NULL,NULL,NULL,'0'),('1981','五指山市','233','469001',NULL,NULL,NULL,NULL,'0'),('1982','琼海市','233','469002',NULL,NULL,NULL,NULL,'0'),('1983','儋州市','233','469003',NULL,NULL,NULL,NULL,'0'),('1984','文昌市','233','469005',NULL,NULL,NULL,NULL,'0'),('1985','万宁市','233','469006',NULL,NULL,NULL,NULL,'0'),('1986','东方市','233','469007',NULL,NULL,NULL,NULL,'0'),('1987','定安县','233','510724',NULL,NULL,NULL,NULL,'0'),('1988','屯昌县','233','469026',NULL,NULL,NULL,NULL,'0'),('1989','澄迈县','233','469027',NULL,NULL,NULL,NULL,'0'),('199','枣强县','13','131121',NULL,NULL,NULL,NULL,'0'),('1990','临高县','233','511525',NULL,NULL,NULL,NULL,'0'),('1991','白沙黎族自治县','233','469030',NULL,NULL,NULL,NULL,'0'),('1992','昌江黎族自治县','233','469031',NULL,NULL,NULL,NULL,'0'),('1993','乐东黎族自治县','233','469033',NULL,NULL,NULL,NULL,'0'),('1994','陵水黎族自治县','233','469034',NULL,NULL,NULL,NULL,'0'),('1995','保亭黎族苗族自治县','233','469035',NULL,NULL,NULL,NULL,'0'),('1996','琼中黎族苗族自治县','233','469036',NULL,NULL,NULL,NULL,'0'),('1997','西沙群岛','233','469037',NULL,NULL,NULL,NULL,'0'),('1998','南沙群岛','233','469038',NULL,NULL,NULL,NULL,'0'),('1999','中沙群岛的岛礁及其海域','233','469039',NULL,NULL,NULL,NULL,'0'),('2','西城区','1','110102',NULL,NULL,NULL,NULL,'0'),('20','河东区','2','371312',NULL,NULL,NULL,NULL,'0'),('200','武邑县','13','131122',NULL,NULL,NULL,NULL,'0'),('2000','万州区','234','500101',NULL,NULL,NULL,NULL,'0'),('2001','涪陵区','234','500102',NULL,NULL,NULL,NULL,'0'),('2002','渝中区','234','500103',NULL,NULL,NULL,NULL,'0'),('2003','大渡口区','234','500104',NULL,NULL,NULL,NULL,'0'),('2004','江北区','234','500105',NULL,NULL,NULL,NULL,'0'),('2005','沙坪坝区','234','500106',NULL,NULL,NULL,NULL,'0'),('2006','九龙坡区','234','500107',NULL,NULL,NULL,NULL,'0'),('2007','南岸区','234','500108',NULL,NULL,NULL,NULL,'0'),('2008','北碚区','234','500109',NULL,NULL,NULL,NULL,'0'),('2009','万盛区','234','500110',NULL,NULL,NULL,NULL,'0'),('201','武强县','13','131123',NULL,NULL,NULL,NULL,'0'),('2010','双桥区','234','500111',NULL,NULL,NULL,NULL,'0'),('2011','渝北区','234','500112',NULL,NULL,NULL,NULL,'0'),('2012','巴南区','234','500113',NULL,NULL,NULL,NULL,'0'),('2013','黔江区','234','500114',NULL,NULL,NULL,NULL,'0'),('2014','长寿区','234','500115',NULL,NULL,NULL,NULL,'0'),('2015','綦江县','234','500222',NULL,NULL,NULL,NULL,'0'),('2016','潼南县','234','500223',NULL,NULL,NULL,NULL,'0'),('2017','铜梁县','234','500224',NULL,NULL,NULL,NULL,'0'),('2018','大足县','234','500225',NULL,NULL,NULL,NULL,'0'),('2019','荣昌县','234','500226',NULL,NULL,NULL,NULL,'0'),('202','饶阳县','13','131124',NULL,NULL,NULL,NULL,'0'),('2020','璧山县','234','500227',NULL,NULL,NULL,NULL,'0'),('2021','梁平县','234','500228',NULL,NULL,NULL,NULL,'0'),('2022','城口县','234','500229',NULL,NULL,NULL,NULL,'0'),('2023','丰都县','234','500230',NULL,NULL,NULL,NULL,'0'),('2024','垫江县','234','500231',NULL,NULL,NULL,NULL,'0'),('2025','武隆县','234','500232',NULL,NULL,NULL,NULL,'0'),('2026','忠县','234','500233',NULL,NULL,NULL,NULL,'0'),('2027','开县','234','500234',NULL,NULL,NULL,NULL,'0'),('2028','云阳县','234','500235',NULL,NULL,NULL,NULL,'0'),('2029','奉节县','234','500236',NULL,NULL,NULL,NULL,'0'),('203','安平县','13','131125',NULL,NULL,NULL,NULL,'0'),('2030','巫山县','234','500237',NULL,NULL,NULL,NULL,'0'),('2031','巫溪县','234','500238',NULL,NULL,NULL,NULL,'0'),('2032','石柱土家族自治县','234','500240',NULL,NULL,NULL,NULL,'0'),('2033','秀山土家族苗族自治县','234','500241',NULL,NULL,NULL,NULL,'0'),('2034','酉阳土家族苗族自治县','234','500242',NULL,NULL,NULL,NULL,'0'),('2035','彭水苗族土家族自治县','234','500243',NULL,NULL,NULL,NULL,'0'),('2036','江津市','234',NULL,NULL,NULL,NULL,NULL,'0'),('2037','合川市','234',NULL,NULL,NULL,NULL,NULL,'0'),('2038','永川市','234',NULL,NULL,NULL,NULL,NULL,'0'),('2039','南川市','234',NULL,NULL,NULL,NULL,NULL,'0'),('204','故城县','13','131126',NULL,NULL,NULL,NULL,'0'),('2040','锦江区','235','510104',NULL,NULL,NULL,NULL,'0'),('2041','青羊区','235','510105',NULL,NULL,NULL,NULL,'0'),('2042','金牛区','235','510106',NULL,NULL,NULL,NULL,'0'),('2043','武侯区','235','510107',NULL,NULL,NULL,NULL,'0'),('2044','成华区','235','510108',NULL,NULL,NULL,NULL,'0'),('2045','龙泉驿区','235','510112',NULL,NULL,NULL,NULL,'0'),('2046','青白江区','235','510113',NULL,NULL,NULL,NULL,'0'),('2047','新都区','235','510114',NULL,NULL,NULL,NULL,'0'),('2048','温江区','235','510115',NULL,NULL,NULL,NULL,'0'),('2049','金堂县','235','510121',NULL,NULL,NULL,NULL,'0'),('205','景县','13','131127',NULL,NULL,NULL,NULL,'0'),('2050','双流县','235','510122',NULL,NULL,NULL,NULL,'0'),('2051','郫县','235','510124',NULL,NULL,NULL,NULL,'0'),('2052','大邑县','235','510129',NULL,NULL,NULL,NULL,'0'),('2053','蒲江县','235','510131',NULL,NULL,NULL,NULL,'0'),('2054','新津县','235','510132',NULL,NULL,NULL,NULL,'0'),('2055','都江堰市','235','510181',NULL,NULL,NULL,NULL,'0'),('2056','彭州市','235','510182',NULL,NULL,NULL,NULL,'0'),('2057','邛崃市','235','510183',NULL,NULL,NULL,NULL,'0'),('2058','崇州市','235','510184',NULL,NULL,NULL,NULL,'0'),('2059','自流井区','236','510302',NULL,NULL,NULL,NULL,'0'),('206','阜城县','13','131128',NULL,NULL,NULL,NULL,'0'),('2060','贡井区','236','510303',NULL,NULL,NULL,NULL,'0'),('2061','大安区','236','510304',NULL,NULL,NULL,NULL,'0'),('2062','沿滩区','236','510311',NULL,NULL,NULL,NULL,'0'),('2063','荣县','236','510321',NULL,NULL,NULL,NULL,'0'),('2064','富顺县','236','510322',NULL,NULL,NULL,NULL,'0'),('2065','东区','237',NULL,NULL,NULL,NULL,NULL,'0'),('2066','西区','237',NULL,NULL,NULL,NULL,NULL,'0'),('2067','仁和区','237','510411',NULL,NULL,NULL,NULL,'0'),('2068','米易县','237','510421',NULL,NULL,NULL,NULL,'0'),('2069','盐边县','237','510422',NULL,NULL,NULL,NULL,'0'),('207','冀州市','13','131181',NULL,NULL,NULL,NULL,'0'),('2070','江阳区','238','510502',NULL,NULL,NULL,NULL,'0'),('2071','纳溪区','238','510503',NULL,NULL,NULL,NULL,'0'),('2072','龙马潭区','238','510504',NULL,NULL,NULL,NULL,'0'),('2073','泸县','238','510521',NULL,NULL,NULL,NULL,'0'),('2074','合江县','238','510522',NULL,NULL,NULL,NULL,'0'),('2075','叙永县','238','510524',NULL,NULL,NULL,NULL,'0'),('2076','古蔺县','238','510525',NULL,NULL,NULL,NULL,'0'),('2077','旌阳区','239','510603',NULL,NULL,NULL,NULL,'0'),('2078','中江县','239','510623',NULL,NULL,NULL,NULL,'0'),('2079','罗江县','239','510626',NULL,NULL,NULL,NULL,'0'),('208','深州市','13','131182',NULL,NULL,NULL,NULL,'0'),('2080','广汉市','239','510681',NULL,NULL,NULL,NULL,'0'),('2081','什邡市','239','510682',NULL,NULL,NULL,NULL,'0'),('2082','绵竹市','239','510683',NULL,NULL,NULL,NULL,'0'),('2083','涪城区','240','510703',NULL,NULL,NULL,NULL,'0'),('2084','游仙区','240','510704',NULL,NULL,NULL,NULL,'0'),('2085','三台县','240','510722',NULL,NULL,NULL,NULL,'0'),('2086','盐亭县','240','510723',NULL,NULL,NULL,NULL,'0'),('2087','安县','240','510724',NULL,NULL,NULL,NULL,'0'),('2088','梓潼县','240','510725',NULL,NULL,NULL,NULL,'0'),('2089','北川羌族自治县','240','510726',NULL,NULL,NULL,NULL,'0'),('209','小店区','14','140105',NULL,NULL,NULL,NULL,'0'),('2090','平武县','240','510727',NULL,NULL,NULL,NULL,'0'),('2091','江油市','240','510781',NULL,NULL,NULL,NULL,'0'),('2092','市中区','241','511102',NULL,NULL,NULL,NULL,'0'),('2093','元坝区','241','510811',NULL,NULL,NULL,NULL,'0'),('2094','朝天区','241','510812',NULL,NULL,NULL,NULL,'0'),('2095','旺苍县','241','510821',NULL,NULL,NULL,NULL,'0'),('2096','青川县','241','510822',NULL,NULL,NULL,NULL,'0'),('2097','剑阁县','241','510823',NULL,NULL,NULL,NULL,'0'),('2098','苍溪县','241','510824',NULL,NULL,NULL,NULL,'0'),('2099','船山区','242','510903',NULL,NULL,NULL,NULL,'0'),('21','河西区','2','120103',NULL,NULL,NULL,NULL,'0'),('210','迎泽区','14','140106',NULL,NULL,NULL,NULL,'0'),('2100','安居区','242','510904',NULL,NULL,NULL,NULL,'0'),('2101','蓬溪县','242','510921',NULL,NULL,NULL,NULL,'0'),('2102','射洪县','242','510922',NULL,NULL,NULL,NULL,'0'),('2103','大英县','242','510923',NULL,NULL,NULL,NULL,'0'),('2104','市中区','243','511102',NULL,NULL,NULL,NULL,'0'),('2105','东兴区','243','511011',NULL,NULL,NULL,NULL,'0'),('2106','威远县','243','511024',NULL,NULL,NULL,NULL,'0'),('2107','资中县','243','511025',NULL,NULL,NULL,NULL,'0'),('2108','隆昌县','243','511028',NULL,NULL,NULL,NULL,'0'),('2109','市中区','244','511102',NULL,NULL,NULL,NULL,'0'),('211','杏花岭区','14','140107',NULL,NULL,NULL,NULL,'0'),('2110','沙湾区','244','511111',NULL,NULL,NULL,NULL,'0'),('2111','五通桥区','244','511112',NULL,NULL,NULL,NULL,'0'),('2112','金口河区','244','511113',NULL,NULL,NULL,NULL,'0'),('2113','犍为县','244','511123',NULL,NULL,NULL,NULL,'0'),('2114','井研县','244','511124',NULL,NULL,NULL,NULL,'0'),('2115','夹江县','244','511126',NULL,NULL,NULL,NULL,'0'),('2116','沐川县','244','511129',NULL,NULL,NULL,NULL,'0'),('2117','峨边彝族自治县','244','511132',NULL,NULL,NULL,NULL,'0'),('2118','马边彝族自治县','244','511133',NULL,NULL,NULL,NULL,'0'),('2119','峨眉山市','244','511400',NULL,NULL,NULL,NULL,'0'),('212','尖草坪区','14','140108',NULL,NULL,NULL,NULL,'0'),('2120','顺庆区','245','511302',NULL,NULL,NULL,NULL,'0'),('2121','高坪区','245','511303',NULL,NULL,NULL,NULL,'0'),('2122','嘉陵区','245','511304',NULL,NULL,NULL,NULL,'0'),('2123','南部县','245','511321',NULL,NULL,NULL,NULL,'0'),('2124','营山县','245','511322',NULL,NULL,NULL,NULL,'0'),('2125','蓬安县','245','511323',NULL,NULL,NULL,NULL,'0'),('2126','仪陇县','245','511324',NULL,NULL,NULL,NULL,'0'),('2127','西充县','245','511325',NULL,NULL,NULL,NULL,'0'),('2128','阆中市','245','511381',NULL,NULL,NULL,NULL,'0'),('2129','东坡区','246','511402',NULL,NULL,NULL,NULL,'0'),('213','万柏林区','14','140109',NULL,NULL,NULL,NULL,'0'),('2130','仁寿县','246','511421',NULL,NULL,NULL,NULL,'0'),('2131','彭山县','246','511422',NULL,NULL,NULL,NULL,'0'),('2132','洪雅县','246','511423',NULL,NULL,NULL,NULL,'0'),('2133','丹棱县','246','511424',NULL,NULL,NULL,NULL,'0'),('2134','青神县','246','511425',NULL,NULL,NULL,NULL,'0'),('2135','翠屏区','247','511502',NULL,NULL,NULL,NULL,'0'),('2136','宜宾县','247','511521',NULL,NULL,NULL,NULL,'0'),('2137','南溪县','247','511522',NULL,NULL,NULL,NULL,'0'),('2138','江安县','247','511523',NULL,NULL,NULL,NULL,'0'),('2139','长宁县','247','511524',NULL,NULL,NULL,NULL,'0'),('214','晋源区','14','140110',NULL,NULL,NULL,NULL,'0'),('2140','高县','247','511525',NULL,NULL,NULL,NULL,'0'),('2141','珙县','247','511526',NULL,NULL,NULL,NULL,'0'),('2142','筠连县','247','511527',NULL,NULL,NULL,NULL,'0'),('2143','兴文县','247','511528',NULL,NULL,NULL,NULL,'0'),('2144','屏山县','247','511529',NULL,NULL,NULL,NULL,'0'),('2145','广安区','248','511602',NULL,NULL,NULL,NULL,'0'),('2146','岳池县','248','511621',NULL,NULL,NULL,NULL,'0'),('2147','武胜县','248','511622',NULL,NULL,NULL,NULL,'0'),('2148','邻水县','248','511623',NULL,NULL,NULL,NULL,'0'),('2149','华蓥市','248','511681',NULL,NULL,NULL,NULL,'0'),('215','清徐县','14','140121',NULL,NULL,NULL,NULL,'0'),('2150','通川区','249','511702',NULL,NULL,NULL,NULL,'0'),('2151','达县','249','511721',NULL,NULL,NULL,NULL,'0'),('2152','宣汉县','249','511722',NULL,NULL,NULL,NULL,'0'),('2153','开江县','249','511723',NULL,NULL,NULL,NULL,'0'),('2154','大竹县','249','511724',NULL,NULL,NULL,NULL,'0'),('2155','渠县','249','511725',NULL,NULL,NULL,NULL,'0'),('2156','万源市','249','511781',NULL,NULL,NULL,NULL,'0'),('2157','雨城区','250','511802',NULL,NULL,NULL,NULL,'0'),('2158','名山县','250','511821',NULL,NULL,NULL,NULL,'0'),('2159','荥经县','250','511822',NULL,NULL,NULL,NULL,'0'),('216','阳曲县','14','140122',NULL,NULL,NULL,NULL,'0'),('2160','汉源县','250','511823',NULL,NULL,NULL,NULL,'0'),('2161','石棉县','250','511824',NULL,NULL,NULL,NULL,'0'),('2162','天全县','250','511825',NULL,NULL,NULL,NULL,'0'),('2163','芦山县','250','511826',NULL,NULL,NULL,NULL,'0'),('2164','宝兴县','250','511827',NULL,NULL,NULL,NULL,'0'),('2165','巴州区','251','511902',NULL,NULL,NULL,NULL,'0'),('2166','通江县','251','511921',NULL,NULL,NULL,NULL,'0'),('2167','南江县','251','511922',NULL,NULL,NULL,NULL,'0'),('2168','平昌县','251','511923',NULL,NULL,NULL,NULL,'0'),('2169','雁江区','252','512002',NULL,NULL,NULL,NULL,'0'),('217','娄烦县','14','140123',NULL,NULL,NULL,NULL,'0'),('2170','安岳县','252','512021',NULL,NULL,NULL,NULL,'0'),('2171','乐至县','252','512022',NULL,NULL,NULL,NULL,'0'),('2172','简阳市','252','512081',NULL,NULL,NULL,NULL,'0'),('2173','汶川县','253','513221',NULL,NULL,NULL,NULL,'0'),('2174','理县','253','513222',NULL,NULL,NULL,NULL,'0'),('2175','茂县','253','513223',NULL,NULL,NULL,NULL,'0'),('2176','松潘县','253','513224',NULL,NULL,NULL,NULL,'0'),('2177','九寨沟县','253','513225',NULL,NULL,NULL,NULL,'0'),('2178','金川县','253','513226',NULL,NULL,NULL,NULL,'0'),('2179','小金县','253','513227',NULL,NULL,NULL,NULL,'0'),('218','古交市','14','140181',NULL,NULL,NULL,NULL,'0'),('2180','黑水县','253','513228',NULL,NULL,NULL,NULL,'0'),('2181','马尔康县','253','513229',NULL,NULL,NULL,NULL,'0'),('2182','壤塘县','253','513230',NULL,NULL,NULL,NULL,'0'),('2183','阿坝县','253','513231',NULL,NULL,NULL,NULL,'0'),('2184','若尔盖县','253','513232',NULL,NULL,NULL,NULL,'0'),('2185','红原县','253','513233',NULL,NULL,NULL,NULL,'0'),('2186','康定县','254','513321',NULL,NULL,NULL,NULL,'0'),('2187','泸定县','254','513322',NULL,NULL,NULL,NULL,'0'),('2188','丹巴县','254','513323',NULL,NULL,NULL,NULL,'0'),('2189','九龙县','254','513324',NULL,NULL,NULL,NULL,'0'),('219','城区','15',NULL,NULL,NULL,NULL,NULL,'0'),('2190','雅江县','254','513325',NULL,NULL,NULL,NULL,'0'),('2191','道孚县','254','513326',NULL,NULL,NULL,NULL,'0'),('2192','炉霍县','254','513327',NULL,NULL,NULL,NULL,'0'),('2193','甘孜县','254','513328',NULL,NULL,NULL,NULL,'0'),('2194','新龙县','254','513329',NULL,NULL,NULL,NULL,'0'),('2195','德格县','254','513330',NULL,NULL,NULL,NULL,'0'),('2196','白玉县','254','513331',NULL,NULL,NULL,NULL,'0'),('2197','石渠县','254','513332',NULL,NULL,NULL,NULL,'0'),('2198','色达县','254','513333',NULL,NULL,NULL,NULL,'0'),('2199','理塘县','254','513334',NULL,NULL,NULL,NULL,'0'),('22','南开区','2','120104',NULL,NULL,NULL,NULL,'0'),('220','矿区','15',NULL,NULL,NULL,NULL,NULL,'0'),('2200','巴塘县','254','513335',NULL,NULL,NULL,NULL,'0'),('2201','乡城县','254','513336',NULL,NULL,NULL,NULL,'0'),('2202','稻城县','254','513337',NULL,NULL,NULL,NULL,'0'),('2203','得荣县','254','513338',NULL,NULL,NULL,NULL,'0'),('2204','西昌市','255','513401',NULL,NULL,NULL,NULL,'0'),('2205','木里藏族自治县','255','513422',NULL,NULL,NULL,NULL,'0'),('2206','盐源县','255','513423',NULL,NULL,NULL,NULL,'0'),('2207','德昌县','255','513424',NULL,NULL,NULL,NULL,'0'),('2208','会理县','255','513425',NULL,NULL,NULL,NULL,'0'),('2209','会东县','255','513426',NULL,NULL,NULL,NULL,'0'),('221','南郊区','15','140211',NULL,NULL,NULL,NULL,'0'),('2210','宁南县','255','513427',NULL,NULL,NULL,NULL,'0'),('2211','普格县','255','513428',NULL,NULL,NULL,NULL,'0'),('2212','布拖县','255','513429',NULL,NULL,NULL,NULL,'0'),('2213','金阳县','255','513430',NULL,NULL,NULL,NULL,'0'),('2214','昭觉县','255','513431',NULL,NULL,NULL,NULL,'0'),('2215','喜德县','255','513432',NULL,NULL,NULL,NULL,'0'),('2216','冕宁县','255','513433',NULL,NULL,NULL,NULL,'0'),('2217','越西县','255','513434',NULL,NULL,NULL,NULL,'0'),('2218','甘洛县','255','513435',NULL,NULL,NULL,NULL,'0'),('2219','美姑县','255','513436',NULL,NULL,NULL,NULL,'0'),('222','新荣区','15','140212',NULL,NULL,NULL,NULL,'0'),('2220','雷波县','255','513437',NULL,NULL,NULL,NULL,'0'),('2221','南明区','256','520102',NULL,NULL,NULL,NULL,'0'),('2222','云岩区','256','520103',NULL,NULL,NULL,NULL,'0'),('2223','花溪区','256','520111',NULL,NULL,NULL,NULL,'0'),('2224','乌当区','256','520112',NULL,NULL,NULL,NULL,'0'),('2225','白云区','256','520113',NULL,NULL,NULL,NULL,'0'),('2226','小河区','256','520114',NULL,NULL,NULL,NULL,'0'),('2227','开阳县','256','520121',NULL,NULL,NULL,NULL,'0'),('2228','息烽县','256','520122',NULL,NULL,NULL,NULL,'0'),('2229','修文县','256','520123',NULL,NULL,NULL,NULL,'0'),('223','阳高县','15','511525',NULL,NULL,NULL,NULL,'0'),('2230','清镇市','256','520181',NULL,NULL,NULL,NULL,'0'),('2231','钟山区','257','520201',NULL,NULL,NULL,NULL,'0'),('2232','六枝特区','257','520203',NULL,NULL,NULL,NULL,'0'),('2233','水城县','257','520221',NULL,NULL,NULL,NULL,'0'),('2234','盘县','257','520222',NULL,NULL,NULL,NULL,'0'),('2235','红花岗区','258','520302',NULL,NULL,NULL,NULL,'0'),('2236','汇川区','258','520303',NULL,NULL,NULL,NULL,'0'),('2237','遵义县','258','520321',NULL,NULL,NULL,NULL,'0'),('2238','桐梓县','258','520322',NULL,NULL,NULL,NULL,'0'),('2239','绥阳县','258','520323',NULL,NULL,NULL,NULL,'0'),('224','天镇县','15','140222',NULL,NULL,NULL,NULL,'0'),('2240','正安县','258','520324',NULL,NULL,NULL,NULL,'0'),('2241','道真仡佬族苗族自治县','258','520325',NULL,NULL,NULL,NULL,'0'),('2242','务川仡佬族苗族自治县','258','520326',NULL,NULL,NULL,NULL,'0'),('2243','凤冈县','258','520327',NULL,NULL,NULL,NULL,'0'),('2244','湄潭县','258','520328',NULL,NULL,NULL,NULL,'0'),('2245','余庆县','258','520329',NULL,NULL,NULL,NULL,'0'),('2246','习水县','258','520330',NULL,NULL,NULL,NULL,'0'),('2247','赤水市','258','520381',NULL,NULL,NULL,NULL,'0'),('2248','仁怀市','258','520382',NULL,NULL,NULL,NULL,'0'),('2249','西秀区','259','520402',NULL,NULL,NULL,NULL,'0'),('225','广灵县','15','140223',NULL,NULL,NULL,NULL,'0'),('2250','平坝县','259','520421',NULL,NULL,NULL,NULL,'0'),('2251','普定县','259','520422',NULL,NULL,NULL,NULL,'0'),('2252','镇宁布依族苗族自治县','259','520423',NULL,NULL,NULL,NULL,'0'),('2253','关岭布依族苗族自治县','259','520424',NULL,NULL,NULL,NULL,'0'),('2254','紫云苗族布依族自治县','259','520425',NULL,NULL,NULL,NULL,'0'),('2255','铜仁市','260','522201',NULL,NULL,NULL,NULL,'0'),('2256','江口县','260','522222',NULL,NULL,NULL,NULL,'0'),('2257','玉屏侗族自治县','260','522223',NULL,NULL,NULL,NULL,'0'),('2258','石阡县','260','522224',NULL,NULL,NULL,NULL,'0'),('2259','思南县','260','522225',NULL,NULL,NULL,NULL,'0'),('226','灵丘县','15','140224',NULL,NULL,NULL,NULL,'0'),('2260','印江土家族苗族自治县','260','522226',NULL,NULL,NULL,NULL,'0'),('2261','德江县','260','522227',NULL,NULL,NULL,NULL,'0'),('2262','沿河土家族自治县','260','522228',NULL,NULL,NULL,NULL,'0'),('2263','松桃苗族自治县','260','522229',NULL,NULL,NULL,NULL,'0'),('2264','万山特区','260','522230',NULL,NULL,NULL,NULL,'0'),('2265','兴义市','261','522301',NULL,NULL,NULL,NULL,'0'),('2266','兴仁县','261','522322',NULL,NULL,NULL,NULL,'0'),('2267','普安县','261','522323',NULL,NULL,NULL,NULL,'0'),('2268','晴隆县','261','522324',NULL,NULL,NULL,NULL,'0'),('2269','贞丰县','261','522325',NULL,NULL,NULL,NULL,'0'),('227','浑源县','15','140225',NULL,NULL,NULL,NULL,'0'),('2270','望谟县','261','522326',NULL,NULL,NULL,NULL,'0'),('2271','册亨县','261','522327',NULL,NULL,NULL,NULL,'0'),('2272','安龙县','261','522328',NULL,NULL,NULL,NULL,'0'),('2273','毕节市','262','522401',NULL,NULL,NULL,NULL,'0'),('2274','大方县','262','522422',NULL,NULL,NULL,NULL,'0'),('2275','黔西县','262','522423',NULL,NULL,NULL,NULL,'0'),('2276','金沙县','262','522424',NULL,NULL,NULL,NULL,'0'),('2277','织金县','262','522425',NULL,NULL,NULL,NULL,'0'),('2278','纳雍县','262','522426',NULL,NULL,NULL,NULL,'0'),('2279','威宁彝族回族苗族自治县','262','522427',NULL,NULL,NULL,NULL,'0'),('228','左云县','15','530922',NULL,NULL,NULL,NULL,'0'),('2280','赫章县','262','522428',NULL,NULL,NULL,NULL,'0'),('2281','凯里市','263','522601',NULL,NULL,NULL,NULL,'0'),('2282','黄平县','263','522622',NULL,NULL,NULL,NULL,'0'),('2283','施秉县','263','522623',NULL,NULL,NULL,NULL,'0'),('2284','三穗县','263','522624',NULL,NULL,NULL,NULL,'0'),('2285','镇远县','263','522625',NULL,NULL,NULL,NULL,'0'),('2286','岑巩县','263','522626',NULL,NULL,NULL,NULL,'0'),('2287','天柱县','263','522627',NULL,NULL,NULL,NULL,'0'),('2288','锦屏县','263','522628',NULL,NULL,NULL,NULL,'0'),('2289','剑河县','263','522629',NULL,NULL,NULL,NULL,'0'),('229','大同县','15','140227',NULL,NULL,NULL,NULL,'0'),('2290','台江县','263','522630',NULL,NULL,NULL,NULL,'0'),('2291','黎平县','263','522631',NULL,NULL,NULL,NULL,'0'),('2292','榕江县','263','522632',NULL,NULL,NULL,NULL,'0'),('2293','从江县','263','522633',NULL,NULL,NULL,NULL,'0'),('2294','雷山县','263','522634',NULL,NULL,NULL,NULL,'0'),('2295','麻江县','263','522635',NULL,NULL,NULL,NULL,'0'),('2296','丹寨县','263','522636',NULL,NULL,NULL,NULL,'0'),('2297','都匀市','264','522701',NULL,NULL,NULL,NULL,'0'),('2298','福泉市','264','522702',NULL,NULL,NULL,NULL,'0'),('2299','荔波县','264','522722',NULL,NULL,NULL,NULL,'0'),('23','河北区','2','120105',NULL,NULL,NULL,NULL,'0'),('230','城区','16',NULL,NULL,NULL,NULL,NULL,'0'),('2300','贵定县','264','522723',NULL,NULL,NULL,NULL,'0'),('2301','瓮安县','264','522725',NULL,NULL,NULL,NULL,'0'),('2302','独山县','264','522726',NULL,NULL,NULL,NULL,'0'),('2303','平塘县','264','522727',NULL,NULL,NULL,NULL,'0'),('2304','罗甸县','264','522728',NULL,NULL,NULL,NULL,'0'),('2305','长顺县','264','522729',NULL,NULL,NULL,NULL,'0'),('2306','龙里县','264','522730',NULL,NULL,NULL,NULL,'0'),('2307','惠水县','264','522731',NULL,NULL,NULL,NULL,'0'),('2308','三都水族自治县','264','522732',NULL,NULL,NULL,NULL,'0'),('2309','五华区','265','530102',NULL,NULL,NULL,NULL,'0'),('231','矿区','16',NULL,NULL,NULL,NULL,NULL,'0'),('2310','盘龙区','265','530103',NULL,NULL,NULL,NULL,'0'),('2311','官渡区','265','530111',NULL,NULL,NULL,NULL,'0'),('2312','西山区','265','530112',NULL,NULL,NULL,NULL,'0'),('2313','东川区','265','530113',NULL,NULL,NULL,NULL,'0'),('2314','呈贡县','265','530121',NULL,NULL,NULL,NULL,'0'),('2315','晋宁县','265','530122',NULL,NULL,NULL,NULL,'0'),('2316','富民县','265','530124',NULL,NULL,NULL,NULL,'0'),('2317','宜良县','265','530125',NULL,NULL,NULL,NULL,'0'),('2318','石林彝族自治县','265','530126',NULL,NULL,NULL,NULL,'0'),('2319','嵩明县','265','530127',NULL,NULL,NULL,NULL,'0'),('232','郊区','16',NULL,NULL,NULL,NULL,NULL,'0'),('2320','禄劝彝族苗族自治县','265','530128',NULL,NULL,NULL,NULL,'0'),('2321','寻甸回族彝族自治县','265','530129',NULL,NULL,NULL,NULL,'0'),('2322','安宁市','265','530181',NULL,NULL,NULL,NULL,'0'),('2323','麒麟区','266','530302',NULL,NULL,NULL,NULL,'0'),('2324','马龙县','266','530321',NULL,NULL,NULL,NULL,'0'),('2325','陆良县','266','530322',NULL,NULL,NULL,NULL,'0'),('2326','师宗县','266','530323',NULL,NULL,NULL,NULL,'0'),('2327','罗平县','266','530324',NULL,NULL,NULL,NULL,'0'),('2328','富源县','266','530325',NULL,NULL,NULL,NULL,'0'),('2329','会泽县','266','530326',NULL,NULL,NULL,NULL,'0'),('233','平定县','16','140321',NULL,NULL,NULL,NULL,'0'),('2330','沾益县','266','530328',NULL,NULL,NULL,NULL,'0'),('2331','宣威市','266','530381',NULL,NULL,NULL,NULL,'0'),('2332','红塔区','267','530402',NULL,NULL,NULL,NULL,'0'),('2333','江川县','267','530421',NULL,NULL,NULL,NULL,'0'),('2334','澄江县','267','530422',NULL,NULL,NULL,NULL,'0'),('2335','通海县','267','530423',NULL,NULL,NULL,NULL,'0'),('2336','华宁县','267','530424',NULL,NULL,NULL,NULL,'0'),('2337','易门县','267','530425',NULL,NULL,NULL,NULL,'0'),('2338','峨山彝族自治县','267','530426',NULL,NULL,NULL,NULL,'0'),('2339','新平彝族傣族自治县','267','530427',NULL,NULL,NULL,NULL,'0'),('234','盂县','16','140322',NULL,NULL,NULL,NULL,'0'),('2340','元江哈尼族彝族傣族自治县','267','530428',NULL,NULL,NULL,NULL,'0'),('2341','隆阳区','268','530502',NULL,NULL,NULL,NULL,'0'),('2342','施甸县','268','530521',NULL,NULL,NULL,NULL,'0'),('2343','腾冲县','268','530522',NULL,NULL,NULL,NULL,'0'),('2344','龙陵县','268','530523',NULL,NULL,NULL,NULL,'0'),('2345','昌宁县','268','530524',NULL,NULL,NULL,NULL,'0'),('2346','昭阳区','269','530602',NULL,NULL,NULL,NULL,'0'),('2347','鲁甸县','269','530621',NULL,NULL,NULL,NULL,'0'),('2348','巧家县','269','530622',NULL,NULL,NULL,NULL,'0'),('2349','盐津县','269','530623',NULL,NULL,NULL,NULL,'0'),('235','城区','17',NULL,NULL,NULL,NULL,NULL,'0'),('2350','大关县','269','530624',NULL,NULL,NULL,NULL,'0'),('2351','永善县','269','530625',NULL,NULL,NULL,NULL,'0'),('2352','绥江县','269','530626',NULL,NULL,NULL,NULL,'0'),('2353','镇雄县','269','530627',NULL,NULL,NULL,NULL,'0'),('2354','彝良县','269','530628',NULL,NULL,NULL,NULL,'0'),('2355','威信县','269','530629',NULL,NULL,NULL,NULL,'0'),('2356','水富县','269','530630',NULL,NULL,NULL,NULL,'0'),('2357','古城区','270','530702',NULL,NULL,NULL,NULL,'0'),('2358','玉龙纳西族自治县','270','530721',NULL,NULL,NULL,NULL,'0'),('2359','永胜县','270','530722',NULL,NULL,NULL,NULL,'0'),('236','郊区','17',NULL,NULL,NULL,NULL,NULL,'0'),('2360','华坪县','270','530723',NULL,NULL,NULL,NULL,'0'),('2361','宁蒗彝族自治县','270','530724',NULL,NULL,NULL,NULL,'0'),('2362','翠云区','271','530802',NULL,NULL,NULL,NULL,'0'),('2363','普洱哈尼族彝族自治县','271','530821',NULL,NULL,NULL,NULL,'0'),('2364','墨江哈尼族自治县','271','530822',NULL,NULL,NULL,NULL,'0'),('2365','景东彝族自治县','271','530823',NULL,NULL,NULL,NULL,'0'),('2366','景谷傣族彝族自治县','271','530824',NULL,NULL,NULL,NULL,'0'),('2367','镇沅彝族哈尼族拉祜族自治县','271','530825',NULL,NULL,NULL,NULL,'0'),('2368','江城哈尼族彝族自治县','271','530826',NULL,NULL,NULL,NULL,'0'),('2369','孟连傣族拉祜族佤族自治县','271','530827',NULL,NULL,NULL,NULL,'0'),('237','长治县','17','140421',NULL,NULL,NULL,NULL,'0'),('2370','澜沧拉祜族自治县','271','530828',NULL,NULL,NULL,NULL,'0'),('2371','西盟佤族自治县','271','530829',NULL,NULL,NULL,NULL,'0'),('2372','临翔区','272','530902',NULL,NULL,NULL,NULL,'0'),('2373','凤庆县','272','530921',NULL,NULL,NULL,NULL,'0'),('2374','云县','272','530922',NULL,NULL,NULL,NULL,'0'),('2375','永德县','272','530923',NULL,NULL,NULL,NULL,'0'),('2376','镇康县','272','530924',NULL,NULL,NULL,NULL,'0'),('2377','双江拉祜族佤族布朗族傣族自治县','272','530925',NULL,NULL,NULL,NULL,'0'),('2378','耿马傣族佤族自治县','272','530926',NULL,NULL,NULL,NULL,'0'),('2379','沧源佤族自治县','272','530927',NULL,NULL,NULL,NULL,'0'),('238','襄垣县','17','140423',NULL,NULL,NULL,NULL,'0'),('2380','楚雄市','273','532301',NULL,NULL,NULL,NULL,'0'),('2381','双柏县','273','532322',NULL,NULL,NULL,NULL,'0'),('2382','牟定县','273','532323',NULL,NULL,NULL,NULL,'0'),('2383','南华县','273','532324',NULL,NULL,NULL,NULL,'0'),('2384','姚安县','273','532325',NULL,NULL,NULL,NULL,'0'),('2385','大姚县','273','532326',NULL,NULL,NULL,NULL,'0'),('2386','永仁县','273','532327',NULL,NULL,NULL,NULL,'0'),('2387','元谋县','273','532328',NULL,NULL,NULL,NULL,'0'),('2388','武定县','273','532329',NULL,NULL,NULL,NULL,'0'),('2389','禄丰县','273','532331',NULL,NULL,NULL,NULL,'0'),('239','屯留县','17','140424',NULL,NULL,NULL,NULL,'0'),('2390','个旧市','274','532501',NULL,NULL,NULL,NULL,'0'),('2391','开远市','274','532502',NULL,NULL,NULL,NULL,'0'),('2392','蒙自县','274','532522',NULL,NULL,NULL,NULL,'0'),('2393','屏边苗族自治县','274','532523',NULL,NULL,NULL,NULL,'0'),('2394','建水县','274','532524',NULL,NULL,NULL,NULL,'0'),('2395','石屏县','274','532525',NULL,NULL,NULL,NULL,'0'),('2396','弥勒县','274','532526',NULL,NULL,NULL,NULL,'0'),('2397','泸西县','274','532527',NULL,NULL,NULL,NULL,'0'),('2398','元阳县','274','532528',NULL,NULL,NULL,NULL,'0'),('2399','红河县','274','532529',NULL,NULL,NULL,NULL,'0'),('24','红桥区','2','120106',NULL,NULL,NULL,NULL,'0'),('240','平顺县','17','140425',NULL,NULL,NULL,NULL,'0'),('2400','金平苗族瑶族傣族自治县','274','532530',NULL,NULL,NULL,NULL,'0'),('2401','绿春县','274','532531',NULL,NULL,NULL,NULL,'0'),('2402','河口瑶族自治县','274','532532',NULL,NULL,NULL,NULL,'0'),('2403','文山县','275','532621',NULL,NULL,NULL,NULL,'0'),('2404','砚山县','275','532622',NULL,NULL,NULL,NULL,'0'),('2405','西畴县','275','532623',NULL,NULL,NULL,NULL,'0'),('2406','麻栗坡县','275','532624',NULL,NULL,NULL,NULL,'0'),('2407','马关县','275','532625',NULL,NULL,NULL,NULL,'0'),('2408','丘北县','275','532626',NULL,NULL,NULL,NULL,'0'),('2409','广南县','275','532627',NULL,NULL,NULL,NULL,'0'),('241','黎城县','17','140426',NULL,NULL,NULL,NULL,'0'),('2410','富宁县','275','532628',NULL,NULL,NULL,NULL,'0'),('2411','景洪市','276','532801',NULL,NULL,NULL,NULL,'0'),('2412','勐海县','276','532822',NULL,NULL,NULL,NULL,'0'),('2413','勐腊县','276','532823',NULL,NULL,NULL,NULL,'0'),('2414','大理市','277','532901',NULL,NULL,NULL,NULL,'0'),('2415','漾濞彝族自治县','277','532922',NULL,NULL,NULL,NULL,'0'),('2416','祥云县','277','532923',NULL,NULL,NULL,NULL,'0'),('2417','宾川县','277','532924',NULL,NULL,NULL,NULL,'0'),('2418','弥渡县','277','532925',NULL,NULL,NULL,NULL,'0'),('2419','南涧彝族自治县','277','532926',NULL,NULL,NULL,NULL,'0'),('242','壶关县','17','140427',NULL,NULL,NULL,NULL,'0'),('2420','巍山彝族回族自治县','277','532927',NULL,NULL,NULL,NULL,'0'),('2421','永平县','277','532928',NULL,NULL,NULL,NULL,'0'),('2422','云龙县','277','532929',NULL,NULL,NULL,NULL,'0'),('2423','洱源县','277','532930',NULL,NULL,NULL,NULL,'0'),('2424','剑川县','277','532931',NULL,NULL,NULL,NULL,'0'),('2425','鹤庆县','277','532932',NULL,NULL,NULL,NULL,'0'),('2426','瑞丽市','278','533102',NULL,NULL,NULL,NULL,'0'),('2427','潞西市','278','533103',NULL,NULL,NULL,NULL,'0'),('2428','梁河县','278','533122',NULL,NULL,NULL,NULL,'0'),('2429','盈江县','278','533123',NULL,NULL,NULL,NULL,'0'),('243','长子县','17','140428',NULL,NULL,NULL,NULL,'0'),('2430','陇川县','278','533124',NULL,NULL,NULL,NULL,'0'),('2431','泸水县','279','533321',NULL,NULL,NULL,NULL,'0'),('2432','福贡县','279','533323',NULL,NULL,NULL,NULL,'0'),('2433','贡山独龙族怒族自治县','279','533324',NULL,NULL,NULL,NULL,'0'),('2434','兰坪白族普米族自治县','279','533325',NULL,NULL,NULL,NULL,'0'),('2435','香格里拉县','280','533421',NULL,NULL,NULL,NULL,'0'),('2436','德钦县','280','533422',NULL,NULL,NULL,NULL,'0'),('2437','维西傈僳族自治县','280','533423',NULL,NULL,NULL,NULL,'0'),('2438','城关区','281','540102',NULL,NULL,NULL,NULL,'0'),('2439','林周县','281','540121',NULL,NULL,NULL,NULL,'0'),('244','武乡县','17','140429',NULL,NULL,NULL,NULL,'0'),('2440','当雄县','281','540122',NULL,NULL,NULL,NULL,'0'),('2441','尼木县','281','540123',NULL,NULL,NULL,NULL,'0'),('2442','曲水县','281','540124',NULL,NULL,NULL,NULL,'0'),('2443','堆龙德庆县','281','540125',NULL,NULL,NULL,NULL,'0'),('2444','达孜县','281','540126',NULL,NULL,NULL,NULL,'0'),('2445','墨竹工卡县','281','540127',NULL,NULL,NULL,NULL,'0'),('2446','昌都县','282','542121',NULL,NULL,NULL,NULL,'0'),('2447','江达县','282','542122',NULL,NULL,NULL,NULL,'0'),('2448','贡觉县','282','542123',NULL,NULL,NULL,NULL,'0'),('2449','类乌齐县','282','542124',NULL,NULL,NULL,NULL,'0'),('245','沁县','17','140430',NULL,NULL,NULL,NULL,'0'),('2450','丁青县','282','542125',NULL,NULL,NULL,NULL,'0'),('2451','察雅县','282','542126',NULL,NULL,NULL,NULL,'0'),('2452','八宿县','282','542127',NULL,NULL,NULL,NULL,'0'),('2453','左贡县','282','542128',NULL,NULL,NULL,NULL,'0'),('2454','芒康县','282','621224',NULL,NULL,NULL,NULL,'0'),('2455','洛隆县','282','542132',NULL,NULL,NULL,NULL,'0'),('2456','边坝县','282','542133',NULL,NULL,NULL,NULL,'0'),('2457','乃东县','283','542221',NULL,NULL,NULL,NULL,'0'),('2458','扎囊县','283','542222',NULL,NULL,NULL,NULL,'0'),('2459','贡嘎县','283','542223',NULL,NULL,NULL,NULL,'0'),('246','沁源县','17','140431',NULL,NULL,NULL,NULL,'0'),('2460','桑日县','283','542224',NULL,NULL,NULL,NULL,'0'),('2461','琼结县','283','542225',NULL,NULL,NULL,NULL,'0'),('2462','曲松县','283','542226',NULL,NULL,NULL,NULL,'0'),('2463','措美县','283','542227',NULL,NULL,NULL,NULL,'0'),('2464','洛扎县','283','542228',NULL,NULL,NULL,NULL,'0'),('2465','加查县','283','542229',NULL,NULL,NULL,NULL,'0'),('2466','隆子县','283','542231',NULL,NULL,NULL,NULL,'0'),('2467','错那县','283','542232',NULL,NULL,NULL,NULL,'0'),('2468','浪卡子县','283','542233',NULL,NULL,NULL,NULL,'0'),('2469','日喀则市','284','542301',NULL,NULL,NULL,NULL,'0'),('247','潞城市','17','140481',NULL,NULL,NULL,NULL,'0'),('2470','南木林县','284','542322',NULL,NULL,NULL,NULL,'0'),('2471','江孜县','284','542323',NULL,NULL,NULL,NULL,'0'),('2472','定日县','284','542324',NULL,NULL,NULL,NULL,'0'),('2473','萨迦县','284','542325',NULL,NULL,NULL,NULL,'0'),('2474','拉孜县','284','542326',NULL,NULL,NULL,NULL,'0'),('2475','昂仁县','284','542327',NULL,NULL,NULL,NULL,'0'),('2476','谢通门县','284','542328',NULL,NULL,NULL,NULL,'0'),('2477','白朗县','284','542627',NULL,NULL,NULL,NULL,'0'),('2478','仁布县','284','542330',NULL,NULL,NULL,NULL,'0'),('2479','康马县','284','542331',NULL,NULL,NULL,NULL,'0'),('248','城区','18',NULL,NULL,NULL,NULL,NULL,'0'),('2480','定结县','284','542332',NULL,NULL,NULL,NULL,'0'),('2481','仲巴县','284','542333',NULL,NULL,NULL,NULL,'0'),('2482','亚东县','284','542334',NULL,NULL,NULL,NULL,'0'),('2483','吉隆县','284','542335',NULL,NULL,NULL,NULL,'0'),('2484','聂拉木县','284','542336',NULL,NULL,NULL,NULL,'0'),('2485','萨嘎县','284','542337',NULL,NULL,NULL,NULL,'0'),('2486','岗巴县','284','542338',NULL,NULL,NULL,NULL,'0'),('2487','那曲县','285','542421',NULL,NULL,NULL,NULL,'0'),('2488','嘉黎县','285','542422',NULL,NULL,NULL,NULL,'0'),('2489','比如县','285','542423',NULL,NULL,NULL,NULL,'0'),('249','沁水县','18','140521',NULL,NULL,NULL,NULL,'0'),('2490','聂荣县','285','510321',NULL,NULL,NULL,NULL,'0'),('2491','安多县','285','542425',NULL,NULL,NULL,NULL,'0'),('2492','申扎县','285','542426',NULL,NULL,NULL,NULL,'0'),('2493','索县','285','542427',NULL,NULL,NULL,NULL,'0'),('2494','班戈县','285','542428',NULL,NULL,NULL,NULL,'0'),('2495','巴青县','285','542429',NULL,NULL,NULL,NULL,'0'),('2496','尼玛县','285','542430',NULL,NULL,NULL,NULL,'0'),('2497','普兰县','286','542521',NULL,NULL,NULL,NULL,'0'),('2498','札达县','286','511721',NULL,NULL,NULL,NULL,'0'),('2499','噶尔县','286','542523',NULL,NULL,NULL,NULL,'0'),('25','塘沽区','2','120107',NULL,NULL,NULL,NULL,'0'),('250','阳城县','18','140522',NULL,NULL,NULL,NULL,'0'),('2500','日土县','286','542524',NULL,NULL,NULL,NULL,'0'),('2501','革吉县','286','542525',NULL,NULL,NULL,NULL,'0'),('2502','改则县','286','542526',NULL,NULL,NULL,NULL,'0'),('2503','措勤县','286','542527',NULL,NULL,NULL,NULL,'0'),('2504','林芝县','287','542621',NULL,NULL,NULL,NULL,'0'),('2505','工布江达县','287','542122',NULL,NULL,NULL,NULL,'0'),('2506','米林县','287','542623',NULL,NULL,NULL,NULL,'0'),('2507','墨脱县','287','542624',NULL,NULL,NULL,NULL,'0'),('2508','波密县','287','542625',NULL,NULL,NULL,NULL,'0'),('2509','察隅县','287','542626',NULL,NULL,NULL,NULL,'0'),('251','陵川县','18','140524',NULL,NULL,NULL,NULL,'0'),('2510','朗县','287','542627',NULL,NULL,NULL,NULL,'0'),('2511','新城区','288','610102',NULL,NULL,NULL,NULL,'0'),('2512','碑林区','288','610103',NULL,NULL,NULL,NULL,'0'),('2513','莲湖区','288','610104',NULL,NULL,NULL,NULL,'0'),('2514','灞桥区','288','610111',NULL,NULL,NULL,NULL,'0'),('2515','未央区','288','610112',NULL,NULL,NULL,NULL,'0'),('2516','雁塔区','288','610113',NULL,NULL,NULL,NULL,'0'),('2517','阎良区','288','610114',NULL,NULL,NULL,NULL,'0'),('2518','临潼区','288','610115',NULL,NULL,NULL,NULL,'0'),('2519','长安区','288','610116',NULL,NULL,NULL,NULL,'0'),('252','泽州县','18','140525',NULL,NULL,NULL,NULL,'0'),('2520','蓝田县','288','610122',NULL,NULL,NULL,NULL,'0'),('2521','周至县','288','610124',NULL,NULL,NULL,NULL,'0'),('2522','户县','288','610125',NULL,NULL,NULL,NULL,'0'),('2523','高陵县','288','610126',NULL,NULL,NULL,NULL,'0'),('2524','王益区','289','610202',NULL,NULL,NULL,NULL,'0'),('2525','印台区','289','610203',NULL,NULL,NULL,NULL,'0'),('2526','耀州区','289','610204',NULL,NULL,NULL,NULL,'0'),('2527','宜君县','289','610222',NULL,NULL,NULL,NULL,'0'),('2528','渭滨区','290','610302',NULL,NULL,NULL,NULL,'0'),('2529','金台区','290','610303',NULL,NULL,NULL,NULL,'0'),('253','高平市','18','140581',NULL,NULL,NULL,NULL,'0'),('2530','陈仓区','290','610304',NULL,NULL,NULL,NULL,'0'),('2531','凤翔县','290','610322',NULL,NULL,NULL,NULL,'0'),('2532','岐山县','290','610323',NULL,NULL,NULL,NULL,'0'),('2533','扶风县','290','610324',NULL,NULL,NULL,NULL,'0'),('2534','眉县','290','610326',NULL,NULL,NULL,NULL,'0'),('2535','陇县','290','610327',NULL,NULL,NULL,NULL,'0'),('2536','千阳县','290','610328',NULL,NULL,NULL,NULL,'0'),('2537','麟游县','290','610329',NULL,NULL,NULL,NULL,'0'),('2538','凤县','290','610330',NULL,NULL,NULL,NULL,'0'),('2539','太白县','290','610331',NULL,NULL,NULL,NULL,'0'),('254','朔城区','19','140602',NULL,NULL,NULL,NULL,'0'),('2540','秦都区','291','610402',NULL,NULL,NULL,NULL,'0'),('2541','杨凌区','291','610403',NULL,NULL,NULL,NULL,'0'),('2542','渭城区','291','610404',NULL,NULL,NULL,NULL,'0'),('2543','三原县','291','610422',NULL,NULL,NULL,NULL,'0'),('2544','泾阳县','291','610423',NULL,NULL,NULL,NULL,'0'),('2545','乾县','291','610424',NULL,NULL,NULL,NULL,'0'),('2546','礼泉县','291','610425',NULL,NULL,NULL,NULL,'0'),('2547','永寿县','291','610426',NULL,NULL,NULL,NULL,'0'),('2548','彬县','291','610427',NULL,NULL,NULL,NULL,'0'),('2549','长武县','291','610428',NULL,NULL,NULL,NULL,'0'),('255','平鲁区','19','140603',NULL,NULL,NULL,NULL,'0'),('2550','旬邑县','291','610429',NULL,NULL,NULL,NULL,'0'),('2551','淳化县','291','610430',NULL,NULL,NULL,NULL,'0'),('2552','武功县','291','610431',NULL,NULL,NULL,NULL,'0'),('2553','兴平市','291','610481',NULL,NULL,NULL,NULL,'0'),('2554','临渭区','292','610502',NULL,NULL,NULL,NULL,'0'),('2555','华县','292','610521',NULL,NULL,NULL,NULL,'0'),('2556','潼关县','292','610522',NULL,NULL,NULL,NULL,'0'),('2557','大荔县','292','610523',NULL,NULL,NULL,NULL,'0'),('2558','合阳县','292','610524',NULL,NULL,NULL,NULL,'0'),('2559','澄城县','292','610525',NULL,NULL,NULL,NULL,'0'),('256','山阴县','19','140621',NULL,NULL,NULL,NULL,'0'),('2560','蒲城县','292','610526',NULL,NULL,NULL,NULL,'0'),('2561','白水县','292','610527',NULL,NULL,NULL,NULL,'0'),('2562','富平县','292','610528',NULL,NULL,NULL,NULL,'0'),('2563','韩城市','292','610581',NULL,NULL,NULL,NULL,'0'),('2564','华阴市','292','610582',NULL,NULL,NULL,NULL,'0'),('2565','宝塔区','293','610602',NULL,NULL,NULL,NULL,'0'),('2566','延长县','293','610621',NULL,NULL,NULL,NULL,'0'),('2567','延川县','293','610622',NULL,NULL,NULL,NULL,'0'),('2568','子长县','293','610623',NULL,NULL,NULL,NULL,'0'),('2569','安塞县','293','610624',NULL,NULL,NULL,NULL,'0'),('257','应县','19','140622',NULL,NULL,NULL,NULL,'0'),('2570','志丹县','293','610625',NULL,NULL,NULL,NULL,'0'),('2571','吴旗县','293',NULL,NULL,NULL,NULL,NULL,'0'),('2572','甘泉县','293','610627',NULL,NULL,NULL,NULL,'0'),('2573','富县','293','610628',NULL,NULL,NULL,NULL,'0'),('2574','洛川县','293','610629',NULL,NULL,NULL,NULL,'0'),('2575','宜川县','293','610630',NULL,NULL,NULL,NULL,'0'),('2576','黄龙县','293','610631',NULL,NULL,NULL,NULL,'0'),('2577','黄陵县','293','610632',NULL,NULL,NULL,NULL,'0'),('2578','汉台区','294','610702',NULL,NULL,NULL,NULL,'0'),('2579','南郑县','294','610721',NULL,NULL,NULL,NULL,'0'),('258','右玉县','19','140623',NULL,NULL,NULL,NULL,'0'),('2580','城固县','294','610722',NULL,NULL,NULL,NULL,'0'),('2581','洋县','294','610723',NULL,NULL,NULL,NULL,'0'),('2582','西乡县','294','610724',NULL,NULL,NULL,NULL,'0'),('2583','勉县','294','610725',NULL,NULL,NULL,NULL,'0'),('2584','宁强县','294','610726',NULL,NULL,NULL,NULL,'0'),('2585','略阳县','294','610727',NULL,NULL,NULL,NULL,'0'),('2586','镇巴县','294','610728',NULL,NULL,NULL,NULL,'0'),('2587','留坝县','294','610729',NULL,NULL,NULL,NULL,'0'),('2588','佛坪县','294','610730',NULL,NULL,NULL,NULL,'0'),('2589','榆阳区','295','610802',NULL,NULL,NULL,NULL,'0'),('259','怀仁县','19','140624',NULL,NULL,NULL,NULL,'0'),('2590','神木县','295','610821',NULL,NULL,NULL,NULL,'0'),('2591','府谷县','295','610822',NULL,NULL,NULL,NULL,'0'),('2592','横山县','295','610823',NULL,NULL,NULL,NULL,'0'),('2593','靖边县','295','610824',NULL,NULL,NULL,NULL,'0'),('2594','定边县','295','610825',NULL,NULL,NULL,NULL,'0'),('2595','绥德县','295','610826',NULL,NULL,NULL,NULL,'0'),('2596','米脂县','295','610827',NULL,NULL,NULL,NULL,'0'),('2597','佳县','295','610828',NULL,NULL,NULL,NULL,'0'),('2598','吴堡县','295','610829',NULL,NULL,NULL,NULL,'0'),('2599','清涧县','295','610830',NULL,NULL,NULL,NULL,'0'),('26','汉沽区','2','120108',NULL,NULL,NULL,NULL,'0'),('260','榆次区','20','140702',NULL,NULL,NULL,NULL,'0'),('2600','子洲县','295','610831',NULL,NULL,NULL,NULL,'0'),('2601','汉滨区','296','610902',NULL,NULL,NULL,NULL,'0'),('2602','汉阴县','296','610921',NULL,NULL,NULL,NULL,'0'),('2603','石泉县','296','610922',NULL,NULL,NULL,NULL,'0'),('2604','宁陕县','296','411222',NULL,NULL,NULL,NULL,'0'),('2605','紫阳县','296','610924',NULL,NULL,NULL,NULL,'0'),('2606','岚皋县','296','610925',NULL,NULL,NULL,NULL,'0'),('2607','平利县','296','610926',NULL,NULL,NULL,NULL,'0'),('2608','镇坪县','296','610927',NULL,NULL,NULL,NULL,'0'),('2609','旬阳县','296','610928',NULL,NULL,NULL,NULL,'0'),('261','榆社县','20','140721',NULL,NULL,NULL,NULL,'0'),('2610','白河县','296','610929',NULL,NULL,NULL,NULL,'0'),('2611','商州区','297','611002',NULL,NULL,NULL,NULL,'0'),('2612','洛南县','297','430921',NULL,NULL,NULL,NULL,'0'),('2613','丹凤县','297','611022',NULL,NULL,NULL,NULL,'0'),('2614','商南县','297','430921',NULL,NULL,NULL,NULL,'0'),('2615','山阳县','297','611024',NULL,NULL,NULL,NULL,'0'),('2616','镇安县','297','510724',NULL,NULL,NULL,NULL,'0'),('2617','柞水县','297','611026',NULL,NULL,NULL,NULL,'0'),('2618','城关区','298','540102',NULL,NULL,NULL,NULL,'0'),('2619','七里河区','298','620103',NULL,NULL,NULL,NULL,'0'),('262','左权县','20','140722',NULL,NULL,NULL,NULL,'0'),('2620','西固区','298','620104',NULL,NULL,NULL,NULL,'0'),('2621','安宁区','298','620105',NULL,NULL,NULL,NULL,'0'),('2622','红古区','298','620111',NULL,NULL,NULL,NULL,'0'),('2623','永登县','298','620121',NULL,NULL,NULL,NULL,'0'),('2624','皋兰县','298','620122',NULL,NULL,NULL,NULL,'0'),('2625','榆中县','298','620123',NULL,NULL,NULL,NULL,'0'),('2626','金川区','300','620302',NULL,NULL,NULL,NULL,'0'),('2627','永昌县','300','620321',NULL,NULL,NULL,NULL,'0'),('2628','白银区','301','620402',NULL,NULL,NULL,NULL,'0'),('2629','平川区','301','620403',NULL,NULL,NULL,NULL,'0'),('263','和顺县','20','140723',NULL,NULL,NULL,NULL,'0'),('2630','靖远县','301','620421',NULL,NULL,NULL,NULL,'0'),('2631','会宁县','301','621026',NULL,NULL,NULL,NULL,'0'),('2632','景泰县','301','620423',NULL,NULL,NULL,NULL,'0'),('2633','秦城区','302','620502',NULL,NULL,NULL,NULL,'0'),('2634','北道区','302','620503',NULL,NULL,NULL,NULL,'0'),('2635','清水县','302','620521',NULL,NULL,NULL,NULL,'0'),('2636','秦安县','302','510724',NULL,NULL,NULL,NULL,'0'),('2637','甘谷县','302','620523',NULL,NULL,NULL,NULL,'0'),('2638','武山县','302','620524',NULL,NULL,NULL,NULL,'0'),('2639','张家川回族自治县','302','620525',NULL,NULL,NULL,NULL,'0'),('264','昔阳县','20','140724',NULL,NULL,NULL,NULL,'0'),('2640','凉州区','303','620602',NULL,NULL,NULL,NULL,'0'),('2641','民勤县','303','620621',NULL,NULL,NULL,NULL,'0'),('2642','古浪县','303','620622',NULL,NULL,NULL,NULL,'0'),('2643','天祝藏族自治县','303','620623',NULL,NULL,NULL,NULL,'0'),('2644','甘州区','304','620702',NULL,NULL,NULL,NULL,'0'),('2645','肃南裕固族自治县','304','620721',NULL,NULL,NULL,NULL,'0'),('2646','民乐县','304','620722',NULL,NULL,NULL,NULL,'0'),('2647','临泽县','304','620723',NULL,NULL,NULL,NULL,'0'),('2648','高台县','304','620724',NULL,NULL,NULL,NULL,'0'),('2649','山丹县','304','620725',NULL,NULL,NULL,NULL,'0'),('265','寿阳县','20','140725',NULL,NULL,NULL,NULL,'0'),('2650','崆峒区','305','620802',NULL,NULL,NULL,NULL,'0'),('2651','泾川县','305','620821',NULL,NULL,NULL,NULL,'0'),('2652','灵台县','305','620822',NULL,NULL,NULL,NULL,'0'),('2653','崇信县','305','620823',NULL,NULL,NULL,NULL,'0'),('2654','华亭县','305','620824',NULL,NULL,NULL,NULL,'0'),('2655','庄浪县','305','620825',NULL,NULL,NULL,NULL,'0'),('2656','静宁县','305','621026',NULL,NULL,NULL,NULL,'0'),('2657','肃州区','306','620902',NULL,NULL,NULL,NULL,'0'),('2658','金塔县','306','620921',NULL,NULL,NULL,NULL,'0'),('2659','安西县','306','620922',NULL,NULL,NULL,NULL,'0'),('266','太谷县','20','140726',NULL,NULL,NULL,NULL,'0'),('2660','肃北蒙古族自治县','306','620923',NULL,NULL,NULL,NULL,'0'),('2661','阿克塞哈萨克族自治县','306','620924',NULL,NULL,NULL,NULL,'0'),('2662','玉门市','306','620981',NULL,NULL,NULL,NULL,'0'),('2663','敦煌市','306','620982',NULL,NULL,NULL,NULL,'0'),('2664','西峰区','307','621002',NULL,NULL,NULL,NULL,'0'),('2665','庆城县','307','621021',NULL,NULL,NULL,NULL,'0'),('2666','环县','307','621022',NULL,NULL,NULL,NULL,'0'),('2667','华池县','307','621023',NULL,NULL,NULL,NULL,'0'),('2668','合水县','307','621024',NULL,NULL,NULL,NULL,'0'),('2669','正宁县','307','621026',NULL,NULL,NULL,NULL,'0'),('267','祁县','20','140727',NULL,NULL,NULL,NULL,'0'),('2670','宁县','307','621026',NULL,NULL,NULL,NULL,'0'),('2671','镇原县','307','621027',NULL,NULL,NULL,NULL,'0'),('2672','安定区','308','621102',NULL,NULL,NULL,NULL,'0'),('2673','通渭县','308','621121',NULL,NULL,NULL,NULL,'0'),('2674','陇西县','308','621122',NULL,NULL,NULL,NULL,'0'),('2675','渭源县','308','621123',NULL,NULL,NULL,NULL,'0'),('2676','临洮县','308','621124',NULL,NULL,NULL,NULL,'0'),('2677','漳县','308','621125',NULL,NULL,NULL,NULL,'0'),('2678','岷县','308','621126',NULL,NULL,NULL,NULL,'0'),('2679','武都区','309','621202',NULL,NULL,NULL,NULL,'0'),('268','平遥县','20','140728',NULL,NULL,NULL,NULL,'0'),('2680','成县','309','621221',NULL,NULL,NULL,NULL,'0'),('2681','文县','309','621222',NULL,NULL,NULL,NULL,'0'),('2682','宕昌县','309','621223',NULL,NULL,NULL,NULL,'0'),('2683','康县','309','621224',NULL,NULL,NULL,NULL,'0'),('2684','西和县','309','621225',NULL,NULL,NULL,NULL,'0'),('2685','礼县','309','621226',NULL,NULL,NULL,NULL,'0'),('2686','徽县','309','621227',NULL,NULL,NULL,NULL,'0'),('2687','两当县','309','621228',NULL,NULL,NULL,NULL,'0'),('2688','临夏市','310','622901',NULL,NULL,NULL,NULL,'0'),('2689','临夏县','310','622921',NULL,NULL,NULL,NULL,'0'),('269','灵石县','20','140729',NULL,NULL,NULL,NULL,'0'),('2690','康乐县','310','622922',NULL,NULL,NULL,NULL,'0'),('2691','永靖县','310','622923',NULL,NULL,NULL,NULL,'0'),('2692','广河县','310','622924',NULL,NULL,NULL,NULL,'0'),('2693','和政县','310','622925',NULL,NULL,NULL,NULL,'0'),('2694','东乡族自治县','310','622926',NULL,NULL,NULL,NULL,'0'),('2695','积石山保安族东乡族撒拉族自治县','310','622927',NULL,NULL,NULL,NULL,'0'),('2696','合作市','311','623001',NULL,NULL,NULL,NULL,'0'),('2697','临潭县','311','623021',NULL,NULL,NULL,NULL,'0'),('2698','卓尼县','311','623022',NULL,NULL,NULL,NULL,'0'),('2699','舟曲县','311','623023',NULL,NULL,NULL,NULL,'0'),('27','大港区','2','120109',NULL,NULL,NULL,NULL,'0'),('270','介休市','20','140781',NULL,NULL,NULL,NULL,'0'),('2700','迭部县','311','623024',NULL,NULL,NULL,NULL,'0'),('2701','玛曲县','311','623025',NULL,NULL,NULL,NULL,'0'),('2702','碌曲县','311','623026',NULL,NULL,NULL,NULL,'0'),('2703','夏河县','311','623027',NULL,NULL,NULL,NULL,'0'),('2704','城东区','312','630102',NULL,NULL,NULL,NULL,'0'),('2705','城中区','312','450202',NULL,NULL,NULL,NULL,'0'),('2706','城西区','312','630104',NULL,NULL,NULL,NULL,'0'),('2707','城北区','312','630105',NULL,NULL,NULL,NULL,'0'),('2708','大通回族土族自治县','312','630121',NULL,NULL,NULL,NULL,'0'),('2709','湟中县','312','630122',NULL,NULL,NULL,NULL,'0'),('271','盐湖区','21','140802',NULL,NULL,NULL,NULL,'0'),('2710','湟源县','312','630123',NULL,NULL,NULL,NULL,'0'),('2711','平安县','313','510724',NULL,NULL,NULL,NULL,'0'),('2712','民和回族土族自治县','313','632122',NULL,NULL,NULL,NULL,'0'),('2713','乐都县','313','632123',NULL,NULL,NULL,NULL,'0'),('2714','互助土族自治县','313','632126',NULL,NULL,NULL,NULL,'0'),('2715','化隆回族自治县','313','632127',NULL,NULL,NULL,NULL,'0'),('2716','循化撒拉族自治县','313','632128',NULL,NULL,NULL,NULL,'0'),('2717','门源回族自治县','314','632221',NULL,NULL,NULL,NULL,'0'),('2718','祁连县','314','632222',NULL,NULL,NULL,NULL,'0'),('2719','海晏县','314','632223',NULL,NULL,NULL,NULL,'0'),('272','临猗县','21','140821',NULL,NULL,NULL,NULL,'0'),('2720','刚察县','314','632224',NULL,NULL,NULL,NULL,'0'),('2721','同仁县','315','632321',NULL,NULL,NULL,NULL,'0'),('2722','尖扎县','315','632322',NULL,NULL,NULL,NULL,'0'),('2723','泽库县','315','632323',NULL,NULL,NULL,NULL,'0'),('2724','河南蒙古族自治县','315','632324',NULL,NULL,NULL,NULL,'0'),('2725','共和县','316','632521',NULL,NULL,NULL,NULL,'0'),('2726','同德县','316','632522',NULL,NULL,NULL,NULL,'0'),('2727','贵德县','316','632523',NULL,NULL,NULL,NULL,'0'),('2728','兴海县','316','632524',NULL,NULL,NULL,NULL,'0'),('2729','贵南县','316','430921',NULL,NULL,NULL,NULL,'0'),('273','万荣县','21','510321',NULL,NULL,NULL,NULL,'0'),('2730','玛沁县','317','632621',NULL,NULL,NULL,NULL,'0'),('2731','班玛县','317','632622',NULL,NULL,NULL,NULL,'0'),('2732','甘德县','317','632623',NULL,NULL,NULL,NULL,'0'),('2733','达日县','317','632624',NULL,NULL,NULL,NULL,'0'),('2734','久治县','317','632625',NULL,NULL,NULL,NULL,'0'),('2735','玛多县','317','632626',NULL,NULL,NULL,NULL,'0'),('2736','玉树县','318','632721',NULL,NULL,NULL,NULL,'0'),('2737','杂多县','318','632722',NULL,NULL,NULL,NULL,'0'),('2738','称多县','318','632723',NULL,NULL,NULL,NULL,'0'),('2739','治多县','318','632724',NULL,NULL,NULL,NULL,'0'),('274','闻喜县','21','140823',NULL,NULL,NULL,NULL,'0'),('2740','囊谦县','318','632725',NULL,NULL,NULL,NULL,'0'),('2741','曲麻莱县','318','632726',NULL,NULL,NULL,NULL,'0'),('2742','格尔木市','319','632801',NULL,NULL,NULL,NULL,'0'),('2743','德令哈市','319','632802',NULL,NULL,NULL,NULL,'0'),('2744','乌兰县','319','632821',NULL,NULL,NULL,NULL,'0'),('2745','都兰县','319','632822',NULL,NULL,NULL,NULL,'0'),('2746','天峻县','319','632823',NULL,NULL,NULL,NULL,'0'),('2747','兴庆区','320','640104',NULL,NULL,NULL,NULL,'0'),('2748','西夏区','320','640105',NULL,NULL,NULL,NULL,'0'),('2749','金凤区','320','640106',NULL,NULL,NULL,NULL,'0'),('275','稷山县','21','140824',NULL,NULL,NULL,NULL,'0'),('2750','永宁县','320','640121',NULL,NULL,NULL,NULL,'0'),('2751','贺兰县','320','640122',NULL,NULL,NULL,NULL,'0'),('2752','灵武市','320','640181',NULL,NULL,NULL,NULL,'0'),('2753','大武口区','321','640202',NULL,NULL,NULL,NULL,'0'),('2754','惠农区','321','640205',NULL,NULL,NULL,NULL,'0'),('2755','平罗县','321','640221',NULL,NULL,NULL,NULL,'0'),('2756','利通区','322','640302',NULL,NULL,NULL,NULL,'0'),('2757','盐池县','322','640323',NULL,NULL,NULL,NULL,'0'),('2758','同心县','322','640324',NULL,NULL,NULL,NULL,'0'),('2759','青铜峡市','322','640381',NULL,NULL,NULL,NULL,'0'),('276','新绛县','21','140826',NULL,NULL,NULL,NULL,'0'),('2760','原州区','323','640402',NULL,NULL,NULL,NULL,'0'),('2761','西吉县','323','640422',NULL,NULL,NULL,NULL,'0'),('2762','隆德县','323','640423',NULL,NULL,NULL,NULL,'0'),('2763','泾源县','323','640424',NULL,NULL,NULL,NULL,'0'),('2764','彭阳县','323','640425',NULL,NULL,NULL,NULL,'0'),('2765','沙坡头区','324','440804',NULL,NULL,NULL,NULL,'0'),('2766','中宁县','324','640521',NULL,NULL,NULL,NULL,'0'),('2767','海原县','324','640522',NULL,NULL,NULL,NULL,'0'),('2768','天山区','325','650102',NULL,NULL,NULL,NULL,'0'),('2769','沙依巴克区','325','650103',NULL,NULL,NULL,NULL,'0'),('277','绛县','21','140826',NULL,NULL,NULL,NULL,'0'),('2770','新市区','325','650104',NULL,NULL,NULL,NULL,'0'),('2771','水磨沟区','325','650105',NULL,NULL,NULL,NULL,'0'),('2772','头屯河区','325','650106',NULL,NULL,NULL,NULL,'0'),('2773','达坂城区','325','650107',NULL,NULL,NULL,NULL,'0'),('2774','东山区','325','650108',NULL,NULL,NULL,NULL,'0'),('2775','乌鲁木齐县','325','650121',NULL,NULL,NULL,NULL,'0'),('2776','独山子区','326','650202',NULL,NULL,NULL,NULL,'0'),('2777','克拉玛依区','326','650203',NULL,NULL,NULL,NULL,'0'),('2778','白碱滩区','326','650204',NULL,NULL,NULL,NULL,'0'),('2779','乌尔禾区','326','650205',NULL,NULL,NULL,NULL,'0'),('278','垣曲县','21','140827',NULL,NULL,NULL,NULL,'0'),('2780','吐鲁番市','327','652101',NULL,NULL,NULL,NULL,'0'),('2781','鄯善县','327','652122',NULL,NULL,NULL,NULL,'0'),('2782','托克逊县','327','652123',NULL,NULL,NULL,NULL,'0'),('2783','哈密市','328','652201',NULL,NULL,NULL,NULL,'0'),('2784','巴里坤哈萨克自治县','328','652222',NULL,NULL,NULL,NULL,'0'),('2785','伊吾县','328','652223',NULL,NULL,NULL,NULL,'0'),('2786','昌吉市','329','652301',NULL,NULL,NULL,NULL,'0'),('2787','阜康市','329','652302',NULL,NULL,NULL,NULL,'0'),('2788','米泉市','329','652303',NULL,NULL,NULL,NULL,'0'),('2789','呼图壁县','329','652323',NULL,NULL,NULL,NULL,'0'),('279','夏县','21','140828',NULL,NULL,NULL,NULL,'0'),('2790','玛纳斯县','329','652324',NULL,NULL,NULL,NULL,'0'),('2791','奇台县','329','652325',NULL,NULL,NULL,NULL,'0'),('2792','吉木萨尔县','329','652327',NULL,NULL,NULL,NULL,'0'),('2793','木垒哈萨克自治县','329','652328',NULL,NULL,NULL,NULL,'0'),('2794','博乐市','330','652701',NULL,NULL,NULL,NULL,'0'),('2795','精河县','330','652722',NULL,NULL,NULL,NULL,'0'),('2796','温泉县','330','652723',NULL,NULL,NULL,NULL,'0'),('2797','库尔勒市','331','652801',NULL,NULL,NULL,NULL,'0'),('2798','轮台县','331','652822',NULL,NULL,NULL,NULL,'0'),('2799','尉犁县','331','652823',NULL,NULL,NULL,NULL,'0'),('28','东丽区','2','120110',NULL,NULL,NULL,NULL,'0'),('280','平陆县','21','140829',NULL,NULL,NULL,NULL,'0'),('2800','若羌县','331','652824',NULL,NULL,NULL,NULL,'0'),('2801','且末县','331','652825',NULL,NULL,NULL,NULL,'0'),('2802','焉耆回族自治县','331','652826',NULL,NULL,NULL,NULL,'0'),('2803','和静县','331','652827',NULL,NULL,NULL,NULL,'0'),('2804','和硕县','331','652828',NULL,NULL,NULL,NULL,'0'),('2805','博湖县','331','652829',NULL,NULL,NULL,NULL,'0'),('2806','阿克苏市','332','652901',NULL,NULL,NULL,NULL,'0'),('2807','温宿县','332','652922',NULL,NULL,NULL,NULL,'0'),('2808','库车县','332','652923',NULL,NULL,NULL,NULL,'0'),('2809','沙雅县','332','652924',NULL,NULL,NULL,NULL,'0'),('281','芮城县','21','140830',NULL,NULL,NULL,NULL,'0'),('2810','新和县','332','652925',NULL,NULL,NULL,NULL,'0'),('2811','拜城县','332','652926',NULL,NULL,NULL,NULL,'0'),('2812','乌什县','332','652927',NULL,NULL,NULL,NULL,'0'),('2813','阿瓦提县','332','652928',NULL,NULL,NULL,NULL,'0'),('2814','柯坪县','332','652929',NULL,NULL,NULL,NULL,'0'),('2815','阿图什市','333','653001',NULL,NULL,NULL,NULL,'0'),('2816','阿克陶县','333','653022',NULL,NULL,NULL,NULL,'0'),('2817','阿合奇县','333','653023',NULL,NULL,NULL,NULL,'0'),('2818','乌恰县','333','653024',NULL,NULL,NULL,NULL,'0'),('2819','喀什市','334','653101',NULL,NULL,NULL,NULL,'0'),('282','永济市','21','140881',NULL,NULL,NULL,NULL,'0'),('2820','疏附县','334','653121',NULL,NULL,NULL,NULL,'0'),('2821','疏勒县','334','653122',NULL,NULL,NULL,NULL,'0'),('2822','英吉沙县','334','653123',NULL,NULL,NULL,NULL,'0'),('2823','泽普县','334','653124',NULL,NULL,NULL,NULL,'0'),('2824','莎车县','334','653125',NULL,NULL,NULL,NULL,'0'),('2825','叶城县','334','653126',NULL,NULL,NULL,NULL,'0'),('2826','麦盖提县','334','653127',NULL,NULL,NULL,NULL,'0'),('2827','岳普湖县','334','653128',NULL,NULL,NULL,NULL,'0'),('2828','伽师县','334','653129',NULL,NULL,NULL,NULL,'0'),('2829','巴楚县','334','653130',NULL,NULL,NULL,NULL,'0'),('283','河津市','21','140882',NULL,NULL,NULL,NULL,'0'),('2830','塔什库尔干塔吉克自治县','334','653131',NULL,NULL,NULL,NULL,'0'),('2831','和田市','335','653201',NULL,NULL,NULL,NULL,'0'),('2832','和田县','335','653221',NULL,NULL,NULL,NULL,'0'),('2833','墨玉县','335','653222',NULL,NULL,NULL,NULL,'0'),('2834','皮山县','335','653223',NULL,NULL,NULL,NULL,'0'),('2835','洛浦县','335','653224',NULL,NULL,NULL,NULL,'0'),('2836','策勒县','335','653225',NULL,NULL,NULL,NULL,'0'),('2837','于田县','335','653226',NULL,NULL,NULL,NULL,'0'),('2838','民丰县','335','653227',NULL,NULL,NULL,NULL,'0'),('2839','伊宁市','336','654002',NULL,NULL,NULL,NULL,'0'),('284','忻府区','22','140902',NULL,NULL,NULL,NULL,'0'),('2840','奎屯市','336','654003',NULL,NULL,NULL,NULL,'0'),('2841','伊宁县','336','654021',NULL,NULL,NULL,NULL,'0'),('2842','察布查尔锡伯自治县','336','654022',NULL,NULL,NULL,NULL,'0'),('2843','霍城县','336','654023',NULL,NULL,NULL,NULL,'0'),('2844','巩留县','336','654024',NULL,NULL,NULL,NULL,'0'),('2845','新源县','336','654025',NULL,NULL,NULL,NULL,'0'),('2846','昭苏县','336','654026',NULL,NULL,NULL,NULL,'0'),('2847','特克斯县','336','654027',NULL,NULL,NULL,NULL,'0'),('2848','尼勒克县','336','654028',NULL,NULL,NULL,NULL,'0'),('2849','塔城市','337','654201',NULL,NULL,NULL,NULL,'0'),('285','定襄县','22','140921',NULL,NULL,NULL,NULL,'0'),('2850','乌苏市','337','654202',NULL,NULL,NULL,NULL,'0'),('2851','额敏县','337','654221',NULL,NULL,NULL,NULL,'0'),('2852','沙湾县','337','654223',NULL,NULL,NULL,NULL,'0'),('2853','托里县','337','654224',NULL,NULL,NULL,NULL,'0'),('2854','裕民县','337','654225',NULL,NULL,NULL,NULL,'0'),('2855','和布克赛尔蒙古自治县','337','654226',NULL,NULL,NULL,NULL,'0'),('2856','阿勒泰市','338','654301',NULL,NULL,NULL,NULL,'0'),('2857','布尔津县','338','654321',NULL,NULL,NULL,NULL,'0'),('2858','富蕴县','338','654322',NULL,NULL,NULL,NULL,'0'),('2859','福海县','338','654323',NULL,NULL,NULL,NULL,'0'),('286','五台县','22','140922',NULL,NULL,NULL,NULL,'0'),('2860','哈巴河县','338','654324',NULL,NULL,NULL,NULL,'0'),('2861','青河县','338','654325',NULL,NULL,NULL,NULL,'0'),('2862','吉木乃县','338','654326',NULL,NULL,NULL,NULL,'0'),('2863','东莞市区','213','441900',NULL,NULL,NULL,NULL,'0'),('287','代县','22','140923',NULL,NULL,NULL,NULL,'0'),('288','繁峙县','22','140924',NULL,NULL,NULL,NULL,'0'),('289','宁武县','22','140925',NULL,NULL,NULL,NULL,'0'),('29','西青区','2','120111',NULL,NULL,NULL,NULL,'0'),('290','静乐县','22','140926',NULL,NULL,NULL,NULL,'0'),('291','神池县','22','140927',NULL,NULL,NULL,NULL,'0'),('292','五寨县','22','140928',NULL,NULL,NULL,NULL,'0'),('293','岢岚县','22','141127',NULL,NULL,NULL,NULL,'0'),('294','河曲县','22','140930',NULL,NULL,NULL,NULL,'0'),('295','保德县','22','140931',NULL,NULL,NULL,NULL,'0'),('296','偏关县','22','140932',NULL,NULL,NULL,NULL,'0'),('297','原平市','22','140981',NULL,NULL,NULL,NULL,'0'),('298','尧都区','23','141002',NULL,NULL,NULL,NULL,'0'),('299','曲沃县','23','141021',NULL,NULL,NULL,NULL,'0'),('3','崇文区','1','110103',NULL,NULL,NULL,NULL,'0'),('30','津南区','2','120112',NULL,NULL,NULL,NULL,'0'),('300','翼城县','23','141022',NULL,NULL,NULL,NULL,'0'),('3000','朝阳区','191','110105',NULL,NULL,NULL,NULL,'0'),('3001','中山市','214','442000',NULL,NULL,NULL,NULL,'0'),('3002','石河子市','339','659001',NULL,NULL,NULL,NULL,'0'),('3003','嘉峪关市','299','620200',NULL,NULL,NULL,NULL,'0'),('3004','济源市','346','410881',NULL,NULL,NULL,NULL,'0'),('301','襄汾县','23','141023',NULL,NULL,NULL,NULL,'0'),('302','洪洞县','23','141024',NULL,NULL,NULL,NULL,'0'),('303','古县','23','141025',NULL,NULL,NULL,NULL,'0'),('304','安泽县','23','141026',NULL,NULL,NULL,NULL,'0'),('305','浮山县','23','141027',NULL,NULL,NULL,NULL,'0'),('306','吉县','23','141028',NULL,NULL,NULL,NULL,'0'),('307','乡宁县','23','621026',NULL,NULL,NULL,NULL,'0'),('308','大宁县','23','621026',NULL,NULL,NULL,NULL,'0'),('309','隰县','23','141031',NULL,NULL,NULL,NULL,'0'),('31','北辰区','2','120113',NULL,NULL,NULL,NULL,'0'),('310','永和县','23','341424',NULL,NULL,NULL,NULL,'0'),('311','蒲县','23','141033',NULL,NULL,NULL,NULL,'0'),('312','汾西县','23','141034',NULL,NULL,NULL,NULL,'0'),('313','侯马市','23','141081',NULL,NULL,NULL,NULL,'0'),('314','霍州市','23','141082',NULL,NULL,NULL,NULL,'0'),('315','离石区','24','141102',NULL,NULL,NULL,NULL,'0'),('316','文水县','24','141121',NULL,NULL,NULL,NULL,'0'),('317','交城县','24','141122',NULL,NULL,NULL,NULL,'0'),('318','兴县','24','141123',NULL,NULL,NULL,NULL,'0'),('319','临县','24','141124',NULL,NULL,NULL,NULL,'0'),('32','武清区','2','120114',NULL,NULL,NULL,NULL,'0'),('320','柳林县','24','141125',NULL,NULL,NULL,NULL,'0'),('321','石楼县','24','141126',NULL,NULL,NULL,NULL,'0'),('322','岚县','24','141127',NULL,NULL,NULL,NULL,'0'),('323','方山县','24','141128',NULL,NULL,NULL,NULL,'0'),('324','中阳县','24','141129',NULL,NULL,NULL,NULL,'0'),('325','交口县','24','141130',NULL,NULL,NULL,NULL,'0'),('326','孝义市','24','141181',NULL,NULL,NULL,NULL,'0'),('327','汾阳市','24','141182',NULL,NULL,NULL,NULL,'0'),('328','新城区','25','610102',NULL,NULL,NULL,NULL,'0'),('329','回民区','25','150103',NULL,NULL,NULL,NULL,'0'),('33','宝坻区','2','120115',NULL,NULL,NULL,NULL,'0'),('330','玉泉区','25','150104',NULL,NULL,NULL,NULL,'0'),('331','赛罕区','25','150105',NULL,NULL,NULL,NULL,'0'),('332','土默特左旗','25','150121',NULL,NULL,NULL,NULL,'0'),('333','托克托县','25','150122',NULL,NULL,NULL,NULL,'0'),('334','和林格尔县','25','150123',NULL,NULL,NULL,NULL,'0'),('335','清水河县','25','150124',NULL,NULL,NULL,NULL,'0'),('336','武川县','25','150125',NULL,NULL,NULL,NULL,'0'),('337','东河区','26','150202',NULL,NULL,NULL,NULL,'0'),('338','昆都仑区','26','150203',NULL,NULL,NULL,NULL,'0'),('339','青山区','26','420107',NULL,NULL,NULL,NULL,'0'),('34','宁河县','2','120221',NULL,NULL,NULL,NULL,'0'),('340','石拐区','26','150205',NULL,NULL,NULL,NULL,'0'),('341','白云矿区','26','150206',NULL,NULL,NULL,NULL,'0'),('342','九原区','26','150207',NULL,NULL,NULL,NULL,'0'),('343','土默特右旗','26','150221',NULL,NULL,NULL,NULL,'0'),('344','固阳县','26','150222',NULL,NULL,NULL,NULL,'0'),('345','达尔罕茂明安联合旗','26','150223',NULL,NULL,NULL,NULL,'0'),('346','海勃湾区','27','150302',NULL,NULL,NULL,NULL,'0'),('347','海南区','27','150303',NULL,NULL,NULL,NULL,'0'),('348','乌达区','27','150304',NULL,NULL,NULL,NULL,'0'),('349','红山区','28','150402',NULL,NULL,NULL,NULL,'0'),('35','静海县','2','120223',NULL,NULL,NULL,NULL,'0'),('350','元宝山区','28','310113',NULL,NULL,NULL,NULL,'0'),('351','松山区','28','150404',NULL,NULL,NULL,NULL,'0'),('352','阿鲁科尔沁旗','28','150421',NULL,NULL,NULL,NULL,'0'),('353','巴林左旗','28','150422',NULL,NULL,NULL,NULL,'0'),('354','巴林右旗','28','150423',NULL,NULL,NULL,NULL,'0'),('355','林西县','28','150424',NULL,NULL,NULL,NULL,'0'),('356','克什克腾旗','28','150425',NULL,NULL,NULL,NULL,'0'),('357','翁牛特旗','28','150426',NULL,NULL,NULL,NULL,'0'),('358','喀喇沁旗','28','150428',NULL,NULL,NULL,NULL,'0'),('359','宁城县','28','150429',NULL,NULL,NULL,NULL,'0'),('36','蓟县','2','120225',NULL,NULL,NULL,NULL,'0'),('360','敖汉旗','28','150430',NULL,NULL,NULL,NULL,'0'),('361','科尔沁区','29','150502',NULL,NULL,NULL,NULL,'0'),('362','科尔沁左翼中旗','29','150521',NULL,NULL,NULL,NULL,'0'),('363','科尔沁左翼后旗','29','150522',NULL,NULL,NULL,NULL,'0'),('364','开鲁县','29','150523',NULL,NULL,NULL,NULL,'0'),('365','库伦旗','29','150524',NULL,NULL,NULL,NULL,'0'),('366','奈曼旗','29','150525',NULL,NULL,NULL,NULL,'0'),('367','扎鲁特旗','29','150526',NULL,NULL,NULL,NULL,'0'),('368','霍林郭勒市','29','150581',NULL,NULL,NULL,NULL,'0'),('369','东胜区','30','150602',NULL,NULL,NULL,NULL,'0'),('37','长安区','3','610116',NULL,NULL,NULL,NULL,'0'),('370','达拉特旗','30','150621',NULL,NULL,NULL,NULL,'0'),('371','准格尔旗','30','150622',NULL,NULL,NULL,NULL,'0'),('372','鄂托克前旗','30','150623',NULL,NULL,NULL,NULL,'0'),('373','鄂托克旗','30','150624',NULL,NULL,NULL,NULL,'0'),('374','杭锦旗','30','150625',NULL,NULL,NULL,NULL,'0'),('375','乌审旗','30','150626',NULL,NULL,NULL,NULL,'0'),('376','伊金霍洛旗','30','150627',NULL,NULL,NULL,NULL,'0'),('377','海拉尔区','31','150702',NULL,NULL,NULL,NULL,'0'),('378','阿荣旗','31','150721',NULL,NULL,NULL,NULL,'0'),('379','莫力达瓦达斡尔族自治旗','31','150722',NULL,NULL,NULL,NULL,'0'),('38','桥东区','3','130702',NULL,NULL,NULL,NULL,'0'),('380','鄂伦春自治旗','31','150723',NULL,NULL,NULL,NULL,'0'),('381','鄂温克族自治旗','31','150724',NULL,NULL,NULL,NULL,'0'),('382','陈巴尔虎旗','31','150725',NULL,NULL,NULL,NULL,'0'),('383','新巴尔虎左旗','31','150726',NULL,NULL,NULL,NULL,'0'),('384','新巴尔虎右旗','31','150727',NULL,NULL,NULL,NULL,'0'),('385','满洲里市','31','150781',NULL,NULL,NULL,NULL,'0'),('386','牙克石市','31','150782',NULL,NULL,NULL,NULL,'0'),('387','扎兰屯市','31','150783',NULL,NULL,NULL,NULL,'0'),('388','额尔古纳市','31','150784',NULL,NULL,NULL,NULL,'0'),('389','根河市','31','150785',NULL,NULL,NULL,NULL,'0'),('39','桥西区','3','130703',NULL,NULL,NULL,NULL,'0'),('390','临河区','32','150802',NULL,NULL,NULL,NULL,'0'),('391','五原县','32','150821',NULL,NULL,NULL,NULL,'0'),('392','磴口县','32','150822',NULL,NULL,NULL,NULL,'0'),('393','乌拉特前旗','32','150823',NULL,NULL,NULL,NULL,'0'),('394','乌拉特中旗','32','150824',NULL,NULL,NULL,NULL,'0'),('395','乌拉特后旗','32','150825',NULL,NULL,NULL,NULL,'0'),('396','杭锦后旗','32','150826',NULL,NULL,NULL,NULL,'0'),('397','集宁区','33','150902',NULL,NULL,NULL,NULL,'0'),('398','卓资县','33','150921',NULL,NULL,NULL,NULL,'0'),('399','化德县','33','150922',NULL,NULL,NULL,NULL,'0'),('4','宣武区','1','110104',NULL,NULL,NULL,NULL,'0'),('40','新华区','3','410402',NULL,NULL,NULL,NULL,'0'),('400','商都县','33','150923',NULL,NULL,NULL,NULL,'0'),('401','兴和县','33','341424',NULL,NULL,NULL,NULL,'0'),('402','凉城县','33','150925',NULL,NULL,NULL,NULL,'0'),('403','察哈尔右翼前旗','33','150926',NULL,NULL,NULL,NULL,'0'),('404','察哈尔右翼中旗','33','150927',NULL,NULL,NULL,NULL,'0'),('405','察哈尔右翼后旗','33','150928',NULL,NULL,NULL,NULL,'0'),('406','四子王旗','33','150929',NULL,NULL,NULL,NULL,'0'),('407','丰镇市','33','150981',NULL,NULL,NULL,NULL,'0'),('408','乌兰浩特市','34','152201',NULL,NULL,NULL,NULL,'0'),('409','阿尔山市','34','152202',NULL,NULL,NULL,NULL,'0'),('41','井陉矿区','3','130107',NULL,NULL,NULL,NULL,'0'),('410','科尔沁右翼前旗','34','152221',NULL,NULL,NULL,NULL,'0'),('411','科尔沁右翼中旗','34','152222',NULL,NULL,NULL,NULL,'0'),('412','扎赉特旗','34','152223',NULL,NULL,NULL,NULL,'0'),('413','突泉县','34','152224',NULL,NULL,NULL,NULL,'0'),('414','二连浩特市','35','152501',NULL,NULL,NULL,NULL,'0'),('415','锡林浩特市','35','152502',NULL,NULL,NULL,NULL,'0'),('416','阿巴嘎旗','35','152522',NULL,NULL,NULL,NULL,'0'),('417','苏尼特左旗','35','152523',NULL,NULL,NULL,NULL,'0'),('418','苏尼特右旗','35','152524',NULL,NULL,NULL,NULL,'0'),('419','东乌珠穆沁旗','35','152525',NULL,NULL,NULL,NULL,'0'),('42','裕华区','3','130108',NULL,NULL,NULL,NULL,'0'),('420','西乌珠穆沁旗','35','152526',NULL,NULL,NULL,NULL,'0'),('421','太仆寺旗','35','152527',NULL,NULL,NULL,NULL,'0'),('422','镶黄旗','35','152528',NULL,NULL,NULL,NULL,'0'),('423','正镶白旗','35','152529',NULL,NULL,NULL,NULL,'0'),('424','正蓝旗','35','152530',NULL,NULL,NULL,NULL,'0'),('425','多伦县','35','152531',NULL,NULL,NULL,NULL,'0'),('426','阿拉善左旗','36','152921',NULL,NULL,NULL,NULL,'0'),('427','阿拉善右旗','36','152922',NULL,NULL,NULL,NULL,'0'),('428','额济纳旗','36','152923',NULL,NULL,NULL,NULL,'0'),('429','和平区','37','120101',NULL,NULL,NULL,NULL,'0'),('43','井陉县','3','130121',NULL,NULL,NULL,NULL,'0'),('430','沈河区','37','210103',NULL,NULL,NULL,NULL,'0'),('431','大东区','37','210104',NULL,NULL,NULL,NULL,'0'),('432','皇姑区','37','210105',NULL,NULL,NULL,NULL,'0'),('433','铁西区','37','220302',NULL,NULL,NULL,NULL,'0'),('434','苏家屯区','37','210111',NULL,NULL,NULL,NULL,'0'),('435','东陵区','37','210112',NULL,NULL,NULL,NULL,'0'),('436','新城子区','37','210113',NULL,NULL,NULL,NULL,'0'),('437','于洪区','37','210114',NULL,NULL,NULL,NULL,'0'),('438','辽中县','37','210122',NULL,NULL,NULL,NULL,'0'),('439','康平县','37','210123',NULL,NULL,NULL,NULL,'0'),('44','正定县','3','130123',NULL,NULL,NULL,NULL,'0'),('440','法库县','37','210124',NULL,NULL,NULL,NULL,'0'),('441','新民市','37','210181',NULL,NULL,NULL,NULL,'0'),('442','中山区','38','210202',NULL,NULL,NULL,NULL,'0'),('443','西岗区','38','210203',NULL,NULL,NULL,NULL,'0'),('444','沙河口区','38','370503',NULL,NULL,NULL,NULL,'0'),('445','甘井子区','38','210211',NULL,NULL,NULL,NULL,'0'),('446','旅顺口区','38','210212',NULL,NULL,NULL,NULL,'0'),('447','金州区','38','210213',NULL,NULL,NULL,NULL,'0'),('448','长海县','38','210224',NULL,NULL,NULL,NULL,'0'),('449','瓦房店市','38','210281',NULL,NULL,NULL,NULL,'0'),('45','栾城县','3','130124',NULL,NULL,NULL,NULL,'0'),('450','普兰店市','38','210282',NULL,NULL,NULL,NULL,'0'),('451','庄河市','38','210283',NULL,NULL,NULL,NULL,'0'),('452','铁东区','39','220303',NULL,NULL,NULL,NULL,'0'),('453','铁西区','39','220302',NULL,NULL,NULL,NULL,'0'),('454','立山区','39','210304',NULL,NULL,NULL,NULL,'0'),('455','千山区','39','210311',NULL,NULL,NULL,NULL,'0'),('456','台安县','39','510724',NULL,NULL,NULL,NULL,'0'),('457','岫岩满族自治县','39','210323',NULL,NULL,NULL,NULL,'0'),('458','海城市','39','210381',NULL,NULL,NULL,NULL,'0'),('459','新抚区','40','210402',NULL,NULL,NULL,NULL,'0'),('46','行唐县','3','130627',NULL,NULL,NULL,NULL,'0'),('460','东洲区','40','210403',NULL,NULL,NULL,NULL,'0'),('461','望花区','40','210404',NULL,NULL,NULL,NULL,'0'),('462','顺城区','40','210411',NULL,NULL,NULL,NULL,'0'),('463','抚顺县','40','210421',NULL,NULL,NULL,NULL,'0'),('464','新宾满族自治县','40','210422',NULL,NULL,NULL,NULL,'0'),('465','清原满族自治县','40','210423',NULL,NULL,NULL,NULL,'0'),('466','平山区','41','210502',NULL,NULL,NULL,NULL,'0'),('467','溪湖区','41','210503',NULL,NULL,NULL,NULL,'0'),('468','明山区','41','210504',NULL,NULL,NULL,NULL,'0'),('469','南芬区','41','210505',NULL,NULL,NULL,NULL,'0'),('47','灵寿县','3','130126',NULL,NULL,NULL,NULL,'0'),('470','本溪满族自治县','41','210521',NULL,NULL,NULL,NULL,'0'),('471','桓仁满族自治县','41','210522',NULL,NULL,NULL,NULL,'0'),('472','元宝区','42','210602',NULL,NULL,NULL,NULL,'0'),('473','振兴区','42','210603',NULL,NULL,NULL,NULL,'0'),('474','振安区','42','210604',NULL,NULL,NULL,NULL,'0'),('475','宽甸满族自治县','42','210624',NULL,NULL,NULL,NULL,'0'),('476','东港市','42','210681',NULL,NULL,NULL,NULL,'0'),('477','凤城市','42','210682',NULL,NULL,NULL,NULL,'0'),('478','古塔区','43','210702',NULL,NULL,NULL,NULL,'0'),('479','凌河区','43','210703',NULL,NULL,NULL,NULL,'0'),('48','高邑县','3','130127',NULL,NULL,NULL,NULL,'0'),('480','太和区','43','210711',NULL,NULL,NULL,NULL,'0'),('481','黑山县','43','210726',NULL,NULL,NULL,NULL,'0'),('482','义县','43','210727',NULL,NULL,NULL,NULL,'0'),('483','凌海市','43','210781',NULL,NULL,NULL,NULL,'0'),('484','北宁市','43','210782',NULL,NULL,NULL,NULL,'0'),('485','站前区','44','210802',NULL,NULL,NULL,NULL,'0'),('486','西市区','44','210803',NULL,NULL,NULL,NULL,'0'),('487','鲅鱼圈区','44','210804',NULL,NULL,NULL,NULL,'0'),('488','老边区','44','210811',NULL,NULL,NULL,NULL,'0'),('489','盖州市','44','210881',NULL,NULL,NULL,NULL,'0'),('49','深泽县','3','130128',NULL,NULL,NULL,NULL,'0'),('490','大石桥市','44','210882',NULL,NULL,NULL,NULL,'0'),('491','海州区','45','320706',NULL,NULL,NULL,NULL,'0'),('492','新邱区','45','210903',NULL,NULL,NULL,NULL,'0'),('493','太平区','45','210904',NULL,NULL,NULL,NULL,'0'),('494','清河门区','45','210905',NULL,NULL,NULL,NULL,'0'),('495','细河区','45','210911',NULL,NULL,NULL,NULL,'0'),('496','阜新蒙古族自治县','45','210921',NULL,NULL,NULL,NULL,'0'),('497','彰武县','45','210922',NULL,NULL,NULL,NULL,'0'),('498','白塔区','46','211002',NULL,NULL,NULL,NULL,'0'),('499','文圣区','46','211003',NULL,NULL,NULL,NULL,'0'),('5','朝阳区','1','110105',NULL,NULL,NULL,NULL,'0'),('50','赞皇县','3','130129',NULL,NULL,NULL,NULL,'0'),('500','宏伟区','46','211004',NULL,NULL,NULL,NULL,'0'),('501','弓长岭区','46','211005',NULL,NULL,NULL,NULL,'0'),('502','太子河区','46','211011',NULL,NULL,NULL,NULL,'0'),('503','辽阳县','46','211021',NULL,NULL,NULL,NULL,'0'),('504','灯塔市','46','211081',NULL,NULL,NULL,NULL,'0'),('505','双台子区','47','211102',NULL,NULL,NULL,NULL,'0'),('506','兴隆台区','47','211103',NULL,NULL,NULL,NULL,'0'),('507','大洼县','47','211121',NULL,NULL,NULL,NULL,'0'),('508','盘山县','47','211122',NULL,NULL,NULL,NULL,'0'),('509','银州区','48','211202',NULL,NULL,NULL,NULL,'0'),('51','无极县','3','130130',NULL,NULL,NULL,NULL,'0'),('510','清河区','48','320802',NULL,NULL,NULL,NULL,'0'),('511','铁岭县','48','211221',NULL,NULL,NULL,NULL,'0'),('512','西丰县','48','320321',NULL,NULL,NULL,NULL,'0'),('513','昌图县','48','211224',NULL,NULL,NULL,NULL,'0'),('514','调兵山市','48','211281',NULL,NULL,NULL,NULL,'0'),('515','开原市','48','211282',NULL,NULL,NULL,NULL,'0'),('516','双塔区','49','211302',NULL,NULL,NULL,NULL,'0'),('517','龙城区','49','211303',NULL,NULL,NULL,NULL,'0'),('518','朝阳县','49','211321',NULL,NULL,NULL,NULL,'0'),('519','建平县','49','211322',NULL,NULL,NULL,NULL,'0'),('52','平山县','3','130131',NULL,NULL,NULL,NULL,'0'),('520','喀喇沁左翼蒙古族自治县','49','211324',NULL,NULL,NULL,NULL,'0'),('521','北票市','49','211381',NULL,NULL,NULL,NULL,'0'),('522','凌源市','49','211382',NULL,NULL,NULL,NULL,'0'),('523','连山区','50','211402',NULL,NULL,NULL,NULL,'0'),('524','龙港区','50','211403',NULL,NULL,NULL,NULL,'0'),('525','南票区','50','211404',NULL,NULL,NULL,NULL,'0'),('526','绥中县','50','211421',NULL,NULL,NULL,NULL,'0'),('527','建昌县','50','211422',NULL,NULL,NULL,NULL,'0'),('528','兴城市','50','211481',NULL,NULL,NULL,NULL,'0'),('529','南关区','51','220102',NULL,NULL,NULL,NULL,'0'),('53','元氏县','3','130132',NULL,NULL,NULL,NULL,'0'),('530','宽城区','51','220103',NULL,NULL,NULL,NULL,'0'),('531','朝阳区','51','110105',NULL,NULL,NULL,NULL,'0'),('532','二道区','51','220105',NULL,NULL,NULL,NULL,'0'),('533','绿园区','51','220106',NULL,NULL,NULL,NULL,'0'),('534','双阳区','51','220112',NULL,NULL,NULL,NULL,'0'),('535','农安县','51','510724',NULL,NULL,NULL,NULL,'0'),('536','九台市','51','220181',NULL,NULL,NULL,NULL,'0'),('537','榆树市','51','220182',NULL,NULL,NULL,NULL,'0'),('538','德惠市','51','220183',NULL,NULL,NULL,NULL,'0'),('539','昌邑区','52','220202',NULL,NULL,NULL,NULL,'0'),('54','赵县','3','130133',NULL,NULL,NULL,NULL,'0'),('540','龙潭区','52','220203',NULL,NULL,NULL,NULL,'0'),('541','船营区','52','220204',NULL,NULL,NULL,NULL,'0'),('542','丰满区','52','220211',NULL,NULL,NULL,NULL,'0'),('543','永吉县','52','220221',NULL,NULL,NULL,NULL,'0'),('544','蛟河市','52','220281',NULL,NULL,NULL,NULL,'0'),('545','桦甸市','52','220282',NULL,NULL,NULL,NULL,'0'),('546','舒兰市','52','220283',NULL,NULL,NULL,NULL,'0'),('547','磐石市','52','220284',NULL,NULL,NULL,NULL,'0'),('548','铁西区','53','220302',NULL,NULL,NULL,NULL,'0'),('549','铁东区','53','220303',NULL,NULL,NULL,NULL,'0'),('55','辛集市','3','130181',NULL,NULL,NULL,NULL,'0'),('550','梨树县','53','220322',NULL,NULL,NULL,NULL,'0'),('551','伊通满族自治县','53','220323',NULL,NULL,NULL,NULL,'0'),('552','公主岭市','53','220381',NULL,NULL,NULL,NULL,'0'),('553','双辽市','53','220382',NULL,NULL,NULL,NULL,'0'),('554','龙山区','54','220402',NULL,NULL,NULL,NULL,'0'),('555','西安区','54','231005',NULL,NULL,NULL,NULL,'0'),('556','东丰县','54','320321',NULL,NULL,NULL,NULL,'0'),('557','东辽县','54','220422',NULL,NULL,NULL,NULL,'0'),('558','东昌区','55','220502',NULL,NULL,NULL,NULL,'0'),('559','二道江区','55','220503',NULL,NULL,NULL,NULL,'0'),('56','藁城市','3','130182',NULL,NULL,NULL,NULL,'0'),('560','通化县','55','220521',NULL,NULL,NULL,NULL,'0'),('561','辉南县','55','430921',NULL,NULL,NULL,NULL,'0'),('562','柳河县','55','220524',NULL,NULL,NULL,NULL,'0'),('563','梅河口市','55','220581',NULL,NULL,NULL,NULL,'0'),('564','集安市','55','220582',NULL,NULL,NULL,NULL,'0'),('565','八道江区','56','220602',NULL,NULL,NULL,NULL,'0'),('566','抚松县','56','220621',NULL,NULL,NULL,NULL,'0'),('567','靖宇县','56','220622',NULL,NULL,NULL,NULL,'0'),('568','长白朝鲜族自治县','56','220623',NULL,NULL,NULL,NULL,'0'),('569','江源县','56','220604',NULL,NULL,NULL,NULL,'0'),('57','晋州市','3','130183',NULL,NULL,NULL,NULL,'0'),('570','临江市','56','220681',NULL,NULL,NULL,NULL,'0'),('571','宁江区','57','220702',NULL,NULL,NULL,NULL,'0'),('572','前郭尔罗斯蒙古族自治县','57','220721',NULL,NULL,NULL,NULL,'0'),('573','长岭县','57','220722',NULL,NULL,NULL,NULL,'0'),('574','乾安县','57','510724',NULL,NULL,NULL,NULL,'0'),('575','扶余县','57','220724',NULL,NULL,NULL,NULL,'0'),('576','洮北区','58','220802',NULL,NULL,NULL,NULL,'0'),('577','镇赉县','58','220821',NULL,NULL,NULL,NULL,'0'),('578','通榆县','58','220822',NULL,NULL,NULL,NULL,'0'),('579','洮南市','58','220881',NULL,NULL,NULL,NULL,'0'),('58','新乐市','3','130184',NULL,NULL,NULL,NULL,'0'),('580','大安市','58','220882',NULL,NULL,NULL,NULL,'0'),('581','延吉市','59','222401',NULL,NULL,NULL,NULL,'0'),('582','图们市','59','222402',NULL,NULL,NULL,NULL,'0'),('583','敦化市','59','222403',NULL,NULL,NULL,NULL,'0'),('584','珲春市','59','222404',NULL,NULL,NULL,NULL,'0'),('585','龙井市','59','222405',NULL,NULL,NULL,NULL,'0'),('586','和龙市','59','222406',NULL,NULL,NULL,NULL,'0'),('587','汪清县','59','222424',NULL,NULL,NULL,NULL,'0'),('588','安图县','59','222426',NULL,NULL,NULL,NULL,'0'),('589','道里区','60','230102',NULL,NULL,NULL,NULL,'0'),('59','鹿泉市','3','130185',NULL,NULL,NULL,NULL,'0'),('590','南岗区','60','230103',NULL,NULL,NULL,NULL,'0'),('591','道外区','60','230104',NULL,NULL,NULL,NULL,'0'),('592','香坊区','60','230110',NULL,NULL,NULL,NULL,'0'),('593','动力区','60',NULL,NULL,NULL,NULL,NULL,'0'),('594','平房区','60','230108',NULL,NULL,NULL,NULL,'0'),('595','松北区','60','230109',NULL,NULL,NULL,NULL,'0'),('596','呼兰区','60','230111',NULL,NULL,NULL,NULL,'0'),('597','依兰县','60','230123',NULL,NULL,NULL,NULL,'0'),('598','方正县','60','230124',NULL,NULL,NULL,NULL,'0'),('599','宾县','60','230125',NULL,NULL,NULL,NULL,'0'),('6','丰台区','1','110106',NULL,NULL,NULL,NULL,'0'),('60','路南区','4','130202',NULL,NULL,NULL,NULL,'0'),('600','巴彦县','60','230126',NULL,NULL,NULL,NULL,'0'),('601','木兰县','60','230127',NULL,NULL,NULL,NULL,'0'),('602','通河县','60','230128',NULL,NULL,NULL,NULL,'0'),('603','延寿县','60','341521',NULL,NULL,NULL,NULL,'0'),('604','阿城市','60',NULL,NULL,NULL,NULL,NULL,'0'),('605','双城市','60','230182',NULL,NULL,NULL,NULL,'0'),('606','尚志市','60','230183',NULL,NULL,NULL,NULL,'0'),('607','五常市','60','230184',NULL,NULL,NULL,NULL,'0'),('608','龙沙区','61','230202',NULL,NULL,NULL,NULL,'0'),('609','建华区','61','230203',NULL,NULL,NULL,NULL,'0'),('61','路北区','4','130203',NULL,NULL,NULL,NULL,'0'),('610','铁锋区','61','230204',NULL,NULL,NULL,NULL,'0'),('611','昂昂溪区','61','230205',NULL,NULL,NULL,NULL,'0'),('612','富拉尔基区','61','230206',NULL,NULL,NULL,NULL,'0'),('613','碾子山区','61','230207',NULL,NULL,NULL,NULL,'0'),('614','梅里斯达斡尔族区','61','230208',NULL,NULL,NULL,NULL,'0'),('615','龙江县','61','230221',NULL,NULL,NULL,NULL,'0'),('616','依安县','61','510724',NULL,NULL,NULL,NULL,'0'),('617','泰来县','61','230224',NULL,NULL,NULL,NULL,'0'),('618','甘南县','61','430921',NULL,NULL,NULL,NULL,'0'),('619','富裕县','61','230227',NULL,NULL,NULL,NULL,'0'),('62','古冶区','4','130204',NULL,NULL,NULL,NULL,'0'),('620','克山县','61','230229',NULL,NULL,NULL,NULL,'0'),('621','克东县','61','230230',NULL,NULL,NULL,NULL,'0'),('622','拜泉县','61','230231',NULL,NULL,NULL,NULL,'0'),('623','讷河市','61','230281',NULL,NULL,NULL,NULL,'0'),('624','鸡冠区','62','230302',NULL,NULL,NULL,NULL,'0'),('625','恒山区','62','230303',NULL,NULL,NULL,NULL,'0'),('626','滴道区','62','230304',NULL,NULL,NULL,NULL,'0'),('627','梨树区','62','230305',NULL,NULL,NULL,NULL,'0'),('628','城子河区','62','230306',NULL,NULL,NULL,NULL,'0'),('629','麻山区','62','230307',NULL,NULL,NULL,NULL,'0'),('63','开平区','4','130205',NULL,NULL,NULL,NULL,'0'),('630','鸡东县','62','230321',NULL,NULL,NULL,NULL,'0'),('631','虎林市','62','230381',NULL,NULL,NULL,NULL,'0'),('632','密山市','62','230382',NULL,NULL,NULL,NULL,'0'),('633','向阳区','63','230803',NULL,NULL,NULL,NULL,'0'),('634','工农区','63','230403',NULL,NULL,NULL,NULL,'0'),('635','南山区','63','440305',NULL,NULL,NULL,NULL,'0'),('636','兴安区','63','230405',NULL,NULL,NULL,NULL,'0'),('637','东山区','63','650108',NULL,NULL,NULL,NULL,'0'),('638','兴山区','63','230407',NULL,NULL,NULL,NULL,'0'),('639','萝北县','63','230421',NULL,NULL,NULL,NULL,'0'),('64','丰南区','4','130207',NULL,NULL,NULL,NULL,'0'),('640','绥滨县','63','230422',NULL,NULL,NULL,NULL,'0'),('641','尖山区','64','230502',NULL,NULL,NULL,NULL,'0'),('642','岭东区','64','230503',NULL,NULL,NULL,NULL,'0'),('643','四方台区','64','230505',NULL,NULL,NULL,NULL,'0'),('644','宝山区','64','310113',NULL,NULL,NULL,NULL,'0'),('645','集贤县','64','230521',NULL,NULL,NULL,NULL,'0'),('646','友谊县','64','230522',NULL,NULL,NULL,NULL,'0'),('647','宝清县','64','230523',NULL,NULL,NULL,NULL,'0'),('648','饶河县','64','230524',NULL,NULL,NULL,NULL,'0'),('649','萨尔图区','65','230602',NULL,NULL,NULL,NULL,'0'),('65','丰润区','4','130208',NULL,NULL,NULL,NULL,'0'),('650','龙凤区','65','230603',NULL,NULL,NULL,NULL,'0'),('651','让胡路区','65','230604',NULL,NULL,NULL,NULL,'0'),('652','红岗区','65','230605',NULL,NULL,NULL,NULL,'0'),('653','大同区','65','230606',NULL,NULL,NULL,NULL,'0'),('654','肇州县','65','230621',NULL,NULL,NULL,NULL,'0'),('655','肇源县','65','230622',NULL,NULL,NULL,NULL,'0'),('656','林甸县','65','230623',NULL,NULL,NULL,NULL,'0'),('657','杜尔伯特蒙古族自治县','65','230624',NULL,NULL,NULL,NULL,'0'),('658','伊春区','66','230702',NULL,NULL,NULL,NULL,'0'),('659','南岔区','66','230703',NULL,NULL,NULL,NULL,'0'),('66','滦县','4','130223',NULL,NULL,NULL,NULL,'0'),('660','友好区','66','230704',NULL,NULL,NULL,NULL,'0'),('661','西林区','66','230705',NULL,NULL,NULL,NULL,'0'),('662','翠峦区','66','230706',NULL,NULL,NULL,NULL,'0'),('663','新青区','66','230707',NULL,NULL,NULL,NULL,'0'),('664','美溪区','66','230708',NULL,NULL,NULL,NULL,'0'),('665','金山屯区','66','230709',NULL,NULL,NULL,NULL,'0'),('666','五营区','66','230710',NULL,NULL,NULL,NULL,'0'),('667','乌马河区','66','230711',NULL,NULL,NULL,NULL,'0'),('668','汤旺河区','66','230712',NULL,NULL,NULL,NULL,'0'),('669','带岭区','66','230713',NULL,NULL,NULL,NULL,'0'),('67','滦南县','4','430921',NULL,NULL,NULL,NULL,'0'),('670','乌伊岭区','66','230714',NULL,NULL,NULL,NULL,'0'),('671','红星区','66','230715',NULL,NULL,NULL,NULL,'0'),('672','上甘岭区','66','230716',NULL,NULL,NULL,NULL,'0'),('673','嘉荫县','66','230722',NULL,NULL,NULL,NULL,'0'),('674','铁力市','66','230781',NULL,NULL,NULL,NULL,'0'),('675','永红区','67',NULL,NULL,NULL,NULL,NULL,'0'),('676','向阳区','67','230803',NULL,NULL,NULL,NULL,'0'),('677','前进区','67','230804',NULL,NULL,NULL,NULL,'0'),('678','东风区','67','230805',NULL,NULL,NULL,NULL,'0'),('679','郊区','67',NULL,NULL,NULL,NULL,NULL,'0'),('68','乐亭县','4','130225',NULL,NULL,NULL,NULL,'0'),('680','桦南县','67','430921',NULL,NULL,NULL,NULL,'0'),('681','桦川县','67','230826',NULL,NULL,NULL,NULL,'0'),('682','汤原县','67','230828',NULL,NULL,NULL,NULL,'0'),('683','抚远县','67','230833',NULL,NULL,NULL,NULL,'0'),('684','同江市','67','230881',NULL,NULL,NULL,NULL,'0'),('685','富锦市','67','230882',NULL,NULL,NULL,NULL,'0'),('686','新兴区','68','230902',NULL,NULL,NULL,NULL,'0'),('687','桃山区','68','230903',NULL,NULL,NULL,NULL,'0'),('688','茄子河区','68','230904',NULL,NULL,NULL,NULL,'0'),('689','勃利县','68','230921',NULL,NULL,NULL,NULL,'0'),('69','迁西县','4','130227',NULL,NULL,NULL,NULL,'0'),('690','东安区','69','231002',NULL,NULL,NULL,NULL,'0'),('691','阳明区','69','231003',NULL,NULL,NULL,NULL,'0'),('692','爱民区','69','231004',NULL,NULL,NULL,NULL,'0'),('693','西安区','69','231005',NULL,NULL,NULL,NULL,'0'),('694','东宁县','69','621026',NULL,NULL,NULL,NULL,'0'),('695','林口县','69','231025',NULL,NULL,NULL,NULL,'0'),('696','绥芬河市','69','231081',NULL,NULL,NULL,NULL,'0'),('697','海林市','69','231083',NULL,NULL,NULL,NULL,'0'),('698','宁安市','69','231084',NULL,NULL,NULL,NULL,'0'),('699','穆棱市','69','231085',NULL,NULL,NULL,NULL,'0'),('7','石景山区','1','110107',NULL,NULL,NULL,NULL,'0'),('70','玉田县','4','130229',NULL,NULL,NULL,NULL,'0'),('700','爱辉区','70','231102',NULL,NULL,NULL,NULL,'0'),('701','嫩江县','70','231121',NULL,NULL,NULL,NULL,'0'),('702','逊克县','70','231123',NULL,NULL,NULL,NULL,'0'),('703','孙吴县','70','231124',NULL,NULL,NULL,NULL,'0'),('704','北安市','70','231181',NULL,NULL,NULL,NULL,'0'),('705','五大连池市','70','231182',NULL,NULL,NULL,NULL,'0'),('706','北林区','71','231202',NULL,NULL,NULL,NULL,'0'),('707','望奎县','71','231221',NULL,NULL,NULL,NULL,'0'),('708','兰西县','71','231222',NULL,NULL,NULL,NULL,'0'),('709','青冈县','71','231223',NULL,NULL,NULL,NULL,'0'),('71','唐海县','4','130230',NULL,NULL,NULL,NULL,'0'),('710','庆安县','71','510724',NULL,NULL,NULL,NULL,'0'),('711','明水县','71','231225',NULL,NULL,NULL,NULL,'0'),('712','绥棱县','71','231226',NULL,NULL,NULL,NULL,'0'),('713','安达市','71','231281',NULL,NULL,NULL,NULL,'0'),('714','肇东市','71','231282',NULL,NULL,NULL,NULL,'0'),('715','海伦市','71','231283',NULL,NULL,NULL,NULL,'0'),('716','呼玛县','72','232721',NULL,NULL,NULL,NULL,'0'),('717','塔河县','72','232722',NULL,NULL,NULL,NULL,'0'),('718','漠河县','72','232723',NULL,NULL,NULL,NULL,'0'),('719','黄浦区','73','310101',NULL,NULL,NULL,NULL,'0'),('72','遵化市','4','130281',NULL,NULL,NULL,NULL,'0'),('720','卢湾区','73','310103',NULL,NULL,NULL,NULL,'0'),('721','徐汇区','73','310104',NULL,NULL,NULL,NULL,'0'),('722','长宁区','73','310105',NULL,NULL,NULL,NULL,'0'),('723','静安区','73','310106',NULL,NULL,NULL,NULL,'0'),('724','普陀区','73','310107',NULL,NULL,NULL,NULL,'0'),('725','闸北区','73','310108',NULL,NULL,NULL,NULL,'0'),('726','虹口区','73','310109',NULL,NULL,NULL,NULL,'0'),('727','杨浦区','73','310110',NULL,NULL,NULL,NULL,'0'),('728','闵行区','73','310112',NULL,NULL,NULL,NULL,'0'),('729','宝山区','73','310113',NULL,NULL,NULL,NULL,'0'),('73','迁安市','4','130283',NULL,NULL,NULL,NULL,'0'),('730','嘉定区','73','310114',NULL,NULL,NULL,NULL,'0'),('731','浦东新区','73','310115',NULL,NULL,NULL,NULL,'0'),('732','金山区','73','310116',NULL,NULL,NULL,NULL,'0'),('733','松江区','73','310117',NULL,NULL,NULL,NULL,'0'),('734','青浦区','73','310118',NULL,NULL,NULL,NULL,'0'),('735','南汇区','73','310119',NULL,NULL,NULL,NULL,'0'),('736','奉贤区','73','310120',NULL,NULL,NULL,NULL,'0'),('737','崇明县','73','310230',NULL,NULL,NULL,NULL,'0'),('738','玄武区','74','320102',NULL,NULL,NULL,NULL,'0'),('739','白下区','74','320103',NULL,NULL,NULL,NULL,'0'),('74','海港区','5','130302',NULL,NULL,NULL,NULL,'0'),('740','秦淮区','74','320104',NULL,NULL,NULL,NULL,'0'),('741','建邺区','74','320105',NULL,NULL,NULL,NULL,'0'),('742','鼓楼区','74','410204',NULL,NULL,NULL,NULL,'0'),('743','下关区','74','320107',NULL,NULL,NULL,NULL,'0'),('744','浦口区','74','320111',NULL,NULL,NULL,NULL,'0'),('745','栖霞区','74','320113',NULL,NULL,NULL,NULL,'0'),('746','雨花台区','74','320114',NULL,NULL,NULL,NULL,'0'),('747','江宁区','74','320115',NULL,NULL,NULL,NULL,'0'),('748','六合区','74','320116',NULL,NULL,NULL,NULL,'0'),('749','溧水县','74','320124',NULL,NULL,NULL,NULL,'0'),('75','山海关区','5','130303',NULL,NULL,NULL,NULL,'0'),('750','高淳县','74','320125',NULL,NULL,NULL,NULL,'0'),('751','崇安区','75','320202',NULL,NULL,NULL,NULL,'0'),('752','南长区','75','320203',NULL,NULL,NULL,NULL,'0'),('753','北塘区','75','320204',NULL,NULL,NULL,NULL,'0'),('754','锡山区','75','320205',NULL,NULL,NULL,NULL,'0'),('755','惠山区','75','320206',NULL,NULL,NULL,NULL,'0'),('756','滨湖区','75','320211',NULL,NULL,NULL,NULL,'0'),('757','江阴市','75','320281',NULL,NULL,NULL,NULL,'0'),('758','宜兴市','75','320282',NULL,NULL,NULL,NULL,'0'),('759','鼓楼区','76','410204',NULL,NULL,NULL,NULL,'0'),('76','北戴河区','5','130304',NULL,NULL,NULL,NULL,'0'),('760','云龙区','76','320303',NULL,NULL,NULL,NULL,'0'),('761','九里区','76','320304',NULL,NULL,NULL,NULL,'0'),('762','贾汪区','76','320305',NULL,NULL,NULL,NULL,'0'),('763','泉山区','76','320311',NULL,NULL,NULL,NULL,'0'),('764','丰县','76','320321',NULL,NULL,NULL,NULL,'0'),('765','沛县','76','320322',NULL,NULL,NULL,NULL,'0'),('766','铜山县','76','320323',NULL,NULL,NULL,NULL,'0'),('767','睢宁县','76','621026',NULL,NULL,NULL,NULL,'0'),('768','新沂市','76','320381',NULL,NULL,NULL,NULL,'0'),('769','邳州市','76','320382',NULL,NULL,NULL,NULL,'0'),('77','青龙满族自治县','5','130321',NULL,NULL,NULL,NULL,'0'),('770','天宁区','77','320402',NULL,NULL,NULL,NULL,'0'),('771','钟楼区','77','320404',NULL,NULL,NULL,NULL,'0'),('772','戚墅堰区','77','320405',NULL,NULL,NULL,NULL,'0'),('773','新北区','77','320411',NULL,NULL,NULL,NULL,'0'),('774','武进区','77','320412',NULL,NULL,NULL,NULL,'0'),('775','溧阳市','77','320481',NULL,NULL,NULL,NULL,'0'),('776','金坛市','77','320482',NULL,NULL,NULL,NULL,'0'),('777','沧浪区','78','320502',NULL,NULL,NULL,NULL,'0'),('778','平江区','78','320503',NULL,NULL,NULL,NULL,'0'),('779','金阊区','78','320504',NULL,NULL,NULL,NULL,'0'),('78','昌黎县','5','130322',NULL,NULL,NULL,NULL,'0'),('780','虎丘区','78','320505',NULL,NULL,NULL,NULL,'0'),('781','吴中区','78','320506',NULL,NULL,NULL,NULL,'0'),('782','相城区','78','320507',NULL,NULL,NULL,NULL,'0'),('783','常熟市','78','320581',NULL,NULL,NULL,NULL,'0'),('784','张家港市','78','320582',NULL,NULL,NULL,NULL,'0'),('785','昆山市','78','320583',NULL,NULL,NULL,NULL,'0'),('786','吴江市','78','320584',NULL,NULL,NULL,NULL,'0'),('787','太仓市','78','320585',NULL,NULL,NULL,NULL,'0'),('788','崇川区','79','320602',NULL,NULL,NULL,NULL,'0'),('789','港闸区','79','320611',NULL,NULL,NULL,NULL,'0'),('79','抚宁县','5','621026',NULL,NULL,NULL,NULL,'0'),('790','海安县','79','510724',NULL,NULL,NULL,NULL,'0'),('791','如东县','79','320623',NULL,NULL,NULL,NULL,'0'),('792','启东市','79','320681',NULL,NULL,NULL,NULL,'0'),('793','如皋市','79','320682',NULL,NULL,NULL,NULL,'0'),('794','通州市','79','320683',NULL,NULL,NULL,NULL,'0'),('795','海门市','79','320684',NULL,NULL,NULL,NULL,'0'),('796','连云区','80','320703',NULL,NULL,NULL,NULL,'0'),('797','新浦区','80','320705',NULL,NULL,NULL,NULL,'0'),('798','海州区','80','320706',NULL,NULL,NULL,NULL,'0'),('799','赣榆县','80','320721',NULL,NULL,NULL,NULL,'0'),('8','海淀区','1','110108',NULL,NULL,NULL,NULL,'0'),('80','卢龙县','5','130324',NULL,NULL,NULL,NULL,'0'),('800','东海县','80','320722',NULL,NULL,NULL,NULL,'0'),('801','灌云县','80','530922',NULL,NULL,NULL,NULL,'0'),('802','灌南县','80','430921',NULL,NULL,NULL,NULL,'0'),('803','清河区','81','320802',NULL,NULL,NULL,NULL,'0'),('804','楚州区','81','320803',NULL,NULL,NULL,NULL,'0'),('805','淮阴区','81','320804',NULL,NULL,NULL,NULL,'0'),('806','清浦区','81','320811',NULL,NULL,NULL,NULL,'0'),('807','涟水县','81','320826',NULL,NULL,NULL,NULL,'0'),('808','洪泽县','81','320829',NULL,NULL,NULL,NULL,'0'),('809','盱眙县','81','320830',NULL,NULL,NULL,NULL,'0'),('81','邯山区','6','130402',NULL,NULL,NULL,NULL,'0'),('810','金湖县','81','320831',NULL,NULL,NULL,NULL,'0'),('811','亭湖区','82','320902',NULL,NULL,NULL,NULL,'0'),('812','盐都区','82','320903',NULL,NULL,NULL,NULL,'0'),('813','响水县','82','320921',NULL,NULL,NULL,NULL,'0'),('814','滨海县','82','320922',NULL,NULL,NULL,NULL,'0'),('815','阜宁县','82','621026',NULL,NULL,NULL,NULL,'0'),('816','射阳县','82','320924',NULL,NULL,NULL,NULL,'0'),('817','建湖县','82','320925',NULL,NULL,NULL,NULL,'0'),('818','东台市','82','320981',NULL,NULL,NULL,NULL,'0'),('819','大丰市','82','320982',NULL,NULL,NULL,NULL,'0'),('82','丛台区','6','130403',NULL,NULL,NULL,NULL,'0'),('820','广陵区','83','321002',NULL,NULL,NULL,NULL,'0'),('821','邗江区','83','321003',NULL,NULL,NULL,NULL,'0'),('822','维扬区','83','321011',NULL,NULL,NULL,NULL,'0'),('823','宝应县','83','321023',NULL,NULL,NULL,NULL,'0'),('824','仪征市','83','321081',NULL,NULL,NULL,NULL,'0'),('825','高邮市','83','321084',NULL,NULL,NULL,NULL,'0'),('826','江都市','83','321088',NULL,NULL,NULL,NULL,'0'),('827','京口区','84','321102',NULL,NULL,NULL,NULL,'0'),('828','润州区','84','321111',NULL,NULL,NULL,NULL,'0'),('829','丹徒区','84','321112',NULL,NULL,NULL,NULL,'0'),('83','复兴区','6','130404',NULL,NULL,NULL,NULL,'0'),('830','丹阳市','84','321181',NULL,NULL,NULL,NULL,'0'),('831','扬中市','84','321182',NULL,NULL,NULL,NULL,'0'),('832','句容市','84','321183',NULL,NULL,NULL,NULL,'0'),('833','海陵区','85','321202',NULL,NULL,NULL,NULL,'0'),('834','高港区','85','321203',NULL,NULL,NULL,NULL,'0'),('835','兴化市','85','321281',NULL,NULL,NULL,NULL,'0'),('836','靖江市','85','321282',NULL,NULL,NULL,NULL,'0'),('837','泰兴市','85','321283',NULL,NULL,NULL,NULL,'0'),('838','姜堰市','85','321284',NULL,NULL,NULL,NULL,'0'),('839','宿城区','86','321302',NULL,NULL,NULL,NULL,'0'),('84','峰峰矿区','6','130406',NULL,NULL,NULL,NULL,'0'),('840','宿豫区','86','321311',NULL,NULL,NULL,NULL,'0'),('841','沭阳县','86','321322',NULL,NULL,NULL,NULL,'0'),('842','泗阳县','86','321323',NULL,NULL,NULL,NULL,'0'),('843','泗洪县','86','321324',NULL,NULL,NULL,NULL,'0'),('844','上城区','87','330102',NULL,NULL,NULL,NULL,'0'),('845','下城区','87','330103',NULL,NULL,NULL,NULL,'0'),('846','江干区','87','330104',NULL,NULL,NULL,NULL,'0'),('847','拱墅区','87','330105',NULL,NULL,NULL,NULL,'0'),('848','西湖区','87','360103',NULL,NULL,NULL,NULL,'0'),('849','滨江区','87','330108',NULL,NULL,NULL,NULL,'0'),('85','邯郸县','6','130421',NULL,NULL,NULL,NULL,'0'),('850','萧山区','87','330109',NULL,NULL,NULL,NULL,'0'),('851','余杭区','87','330110',NULL,NULL,NULL,NULL,'0'),('852','桐庐县','87','330122',NULL,NULL,NULL,NULL,'0'),('853','淳安县','87','510724',NULL,NULL,NULL,NULL,'0'),('854','建德市','87','330182',NULL,NULL,NULL,NULL,'0'),('855','富阳市','87','330183',NULL,NULL,NULL,NULL,'0'),('856','临安市','87','330185',NULL,NULL,NULL,NULL,'0'),('857','海曙区','88','330203',NULL,NULL,NULL,NULL,'0'),('858','江东区','88','330204',NULL,NULL,NULL,NULL,'0'),('859','江北区','88','500105',NULL,NULL,NULL,NULL,'0'),('86','临漳县','6','621125',NULL,NULL,NULL,NULL,'0'),('860','北仑区','88','330206',NULL,NULL,NULL,NULL,'0'),('861','镇海区','88','330211',NULL,NULL,NULL,NULL,'0'),('862','鄞州区','88','330212',NULL,NULL,NULL,NULL,'0'),('863','象山县','88','330225',NULL,NULL,NULL,NULL,'0'),('864','宁海县','88','330226',NULL,NULL,NULL,NULL,'0'),('865','余姚市','88','330281',NULL,NULL,NULL,NULL,'0'),('866','慈溪市','88','330282',NULL,NULL,NULL,NULL,'0'),('867','奉化市','88','330283',NULL,NULL,NULL,NULL,'0'),('868','鹿城区','89','330302',NULL,NULL,NULL,NULL,'0'),('869','龙湾区','89','330303',NULL,NULL,NULL,NULL,'0'),('87','成安县','6','510724',NULL,NULL,NULL,NULL,'0'),('870','瓯海区','89','330304',NULL,NULL,NULL,NULL,'0'),('871','洞头县','89','330322',NULL,NULL,NULL,NULL,'0'),('872','永嘉县','89','330324',NULL,NULL,NULL,NULL,'0'),('873','平阳县','89','330326',NULL,NULL,NULL,NULL,'0'),('874','苍南县','89','430921',NULL,NULL,NULL,NULL,'0'),('875','文成县','89','621221',NULL,NULL,NULL,NULL,'0'),('876','泰顺县','89','330329',NULL,NULL,NULL,NULL,'0'),('877','瑞安市','89','330381',NULL,NULL,NULL,NULL,'0'),('878','乐清市','89','330382',NULL,NULL,NULL,NULL,'0'),('879','秀城区','90','330402',NULL,NULL,NULL,NULL,'0'),('88','大名县','6','130425',NULL,NULL,NULL,NULL,'0'),('880','秀洲区','90','330411',NULL,NULL,NULL,NULL,'0'),('881','嘉善县','90','330421',NULL,NULL,NULL,NULL,'0'),('882','海盐县','90','330424',NULL,NULL,NULL,NULL,'0'),('883','海宁市','90','330481',NULL,NULL,NULL,NULL,'0'),('884','平湖市','90','330482',NULL,NULL,NULL,NULL,'0'),('885','桐乡市','90','330483',NULL,NULL,NULL,NULL,'0'),('886','吴兴区','91','330502',NULL,NULL,NULL,NULL,'0'),('887','南浔区','91','330503',NULL,NULL,NULL,NULL,'0'),('888','德清县','91','330521',NULL,NULL,NULL,NULL,'0'),('889','长兴县','91','330522',NULL,NULL,NULL,NULL,'0'),('89','涉县','6','130426',NULL,NULL,NULL,NULL,'0'),('890','安吉县','91','330523',NULL,NULL,NULL,NULL,'0'),('891','越城区','92','330602',NULL,NULL,NULL,NULL,'0'),('892','绍兴县','92','330621',NULL,NULL,NULL,NULL,'0'),('893','新昌县','92','330624',NULL,NULL,NULL,NULL,'0'),('894','诸暨市','92','330681',NULL,NULL,NULL,NULL,'0'),('895','上虞市','92','330682',NULL,NULL,NULL,NULL,'0'),('896','嵊州市','92','330683',NULL,NULL,NULL,NULL,'0'),('897','婺城区','93','330702',NULL,NULL,NULL,NULL,'0'),('898','金东区','93','330703',NULL,NULL,NULL,NULL,'0'),('899','武义县','93','330723',NULL,NULL,NULL,NULL,'0'),('9','门头沟区','1','110109',NULL,NULL,NULL,NULL,'0'),('90','磁县','6','130427',NULL,NULL,NULL,NULL,'0'),('900','浦江县','93','330726',NULL,NULL,NULL,NULL,'0'),('901','磐安县','93','510724',NULL,NULL,NULL,NULL,'0'),('902','兰溪市','93','330781',NULL,NULL,NULL,NULL,'0'),('903','义乌市','93','330782',NULL,NULL,NULL,NULL,'0'),('904','东阳市','93','330783',NULL,NULL,NULL,NULL,'0'),('905','永康市','93','330784',NULL,NULL,NULL,NULL,'0'),('906','柯城区','94','330802',NULL,NULL,NULL,NULL,'0'),('907','衢江区','94','330803',NULL,NULL,NULL,NULL,'0'),('908','常山县','94','330822',NULL,NULL,NULL,NULL,'0'),('909','开化县','94','330824',NULL,NULL,NULL,NULL,'0'),('91','肥乡县','6','130428',NULL,NULL,NULL,NULL,'0'),('910','龙游县','94','330825',NULL,NULL,NULL,NULL,'0'),('911','江山市','94','330881',NULL,NULL,NULL,NULL,'0'),('912','定海区','95','330902',NULL,NULL,NULL,NULL,'0'),('913','普陀区','95','310107',NULL,NULL,NULL,NULL,'0'),('914','岱山县','95','330921',NULL,NULL,NULL,NULL,'0'),('915','嵊泗县','95','341324',NULL,NULL,NULL,NULL,'0'),('916','椒江区','96','331002',NULL,NULL,NULL,NULL,'0'),('917','黄岩区','96','331003',NULL,NULL,NULL,NULL,'0'),('918','路桥区','96','331004',NULL,NULL,NULL,NULL,'0'),('919','玉环县','96','621022',NULL,NULL,NULL,NULL,'0'),('92','永年县','6','130429',NULL,NULL,NULL,NULL,'0'),('920','三门县','96','331022',NULL,NULL,NULL,NULL,'0'),('921','天台县','96','331023',NULL,NULL,NULL,NULL,'0'),('922','仙居县','96','331024',NULL,NULL,NULL,NULL,'0'),('923','温岭市','96','331081',NULL,NULL,NULL,NULL,'0'),('924','临海市','96','331082',NULL,NULL,NULL,NULL,'0'),('925','莲都区','97','331102',NULL,NULL,NULL,NULL,'0'),('926','青田县','97','331121',NULL,NULL,NULL,NULL,'0'),('927','缙云县','97','530922',NULL,NULL,NULL,NULL,'0'),('928','遂昌县','97','331123',NULL,NULL,NULL,NULL,'0'),('929','松阳县','97','331124',NULL,NULL,NULL,NULL,'0'),('93','邱县','6','130430',NULL,NULL,NULL,NULL,'0'),('930','云和县','97','341424',NULL,NULL,NULL,NULL,'0'),('931','庆元县','97','331126',NULL,NULL,NULL,NULL,'0'),('932','景宁畲族自治县','97','331127',NULL,NULL,NULL,NULL,'0'),('933','龙泉市','97','331181',NULL,NULL,NULL,NULL,'0'),('934','瑶海区','98','340102',NULL,NULL,NULL,NULL,'0'),('935','庐阳区','98','340103',NULL,NULL,NULL,NULL,'0'),('936','蜀山区','98','340104',NULL,NULL,NULL,NULL,'0'),('937','包河区','98','340111',NULL,NULL,NULL,NULL,'0'),('938','长丰县','98','320321',NULL,NULL,NULL,NULL,'0'),('939','肥东县','98','340122',NULL,NULL,NULL,NULL,'0'),('94','鸡泽县','6','130431',NULL,NULL,NULL,NULL,'0'),('940','肥西县','98','340123',NULL,NULL,NULL,NULL,'0'),('941','镜湖区','99','340202',NULL,NULL,NULL,NULL,'0'),('942','马塘区','99',NULL,NULL,NULL,NULL,NULL,'0'),('943','新芜区','99',NULL,NULL,NULL,NULL,NULL,'0'),('944','鸠江区','99','340207',NULL,NULL,NULL,NULL,'0'),('945','芜湖县','99','340221',NULL,NULL,NULL,NULL,'0'),('946','繁昌县','99','340222',NULL,NULL,NULL,NULL,'0'),('947','南陵县','99','371421',NULL,NULL,NULL,NULL,'0'),('948','龙子湖区','100','340302',NULL,NULL,NULL,NULL,'0'),('949','蚌山区','100','340303',NULL,NULL,NULL,NULL,'0'),('95','广平县','6','130432',NULL,NULL,NULL,NULL,'0'),('950','禹会区','100','340304',NULL,NULL,NULL,NULL,'0'),('951','淮上区','100','340311',NULL,NULL,NULL,NULL,'0'),('952','怀远县','100','340321',NULL,NULL,NULL,NULL,'0'),('953','五河县','100','340322',NULL,NULL,NULL,NULL,'0'),('954','固镇县','100','340323',NULL,NULL,NULL,NULL,'0'),('955','大通区','101','340402',NULL,NULL,NULL,NULL,'0'),('956','田家庵区','101','340403',NULL,NULL,NULL,NULL,'0'),('957','谢家集区','101','340404',NULL,NULL,NULL,NULL,'0'),('958','八公山区','101','340405',NULL,NULL,NULL,NULL,'0'),('959','潘集区','101','340406',NULL,NULL,NULL,NULL,'0'),('96','馆陶县','6','130433',NULL,NULL,NULL,NULL,'0'),('960','凤台县','101','340421',NULL,NULL,NULL,NULL,'0'),('961','金家庄区','102','340502',NULL,NULL,NULL,NULL,'0'),('962','花山区','102','340503',NULL,NULL,NULL,NULL,'0'),('963','雨山区','102','340504',NULL,NULL,NULL,NULL,'0'),('964','当涂县','102','340521',NULL,NULL,NULL,NULL,'0'),('965','杜集区','103','340602',NULL,NULL,NULL,NULL,'0'),('966','相山区','103','340603',NULL,NULL,NULL,NULL,'0'),('967','烈山区','103','340604',NULL,NULL,NULL,NULL,'0'),('968','濉溪县','103','340621',NULL,NULL,NULL,NULL,'0'),('969','铜官山区','104','340702',NULL,NULL,NULL,NULL,'0'),('97','魏县','6','130434',NULL,NULL,NULL,NULL,'0'),('970','狮子山区','104','340703',NULL,NULL,NULL,NULL,'0'),('971','郊区','104',NULL,NULL,NULL,NULL,NULL,'0'),('972','铜陵县','104','371421',NULL,NULL,NULL,NULL,'0'),('973','迎江区','105','340802',NULL,NULL,NULL,NULL,'0'),('974','大观区','105','340803',NULL,NULL,NULL,NULL,'0'),('975','郊区','105',NULL,NULL,NULL,NULL,NULL,'0'),('976','怀宁县','105','621026',NULL,NULL,NULL,NULL,'0'),('977','枞阳县','105','340823',NULL,NULL,NULL,NULL,'0'),('978','潜山县','105','340824',NULL,NULL,NULL,NULL,'0'),('979','太湖县','105','340825',NULL,NULL,NULL,NULL,'0'),('98','曲周县','6','130435',NULL,NULL,NULL,NULL,'0'),('980','宿松县','105','340826',NULL,NULL,NULL,NULL,'0'),('981','望江县','105','340827',NULL,NULL,NULL,NULL,'0'),('982','岳西县','105','340828',NULL,NULL,NULL,NULL,'0'),('983','桐城市','105','340881',NULL,NULL,NULL,NULL,'0'),('984','屯溪区','106','341002',NULL,NULL,NULL,NULL,'0'),('985','黄山区','106','341003',NULL,NULL,NULL,NULL,'0'),('986','徽州区','106','341004',NULL,NULL,NULL,NULL,'0'),('987','歙县','106','341021',NULL,NULL,NULL,NULL,'0'),('988','休宁县','106','621026',NULL,NULL,NULL,NULL,'0'),('989','黟县','106','341023',NULL,NULL,NULL,NULL,'0'),('99','武安市','6','130481',NULL,NULL,NULL,NULL,'0'),('990','祁门县','106','341024',NULL,NULL,NULL,NULL,'0'),('991','琅琊区','107','341102',NULL,NULL,NULL,NULL,'0'),('992','南谯区','107','341103',NULL,NULL,NULL,NULL,'0'),('993','来安县','107','510724',NULL,NULL,NULL,NULL,'0'),('994','全椒县','107','341124',NULL,NULL,NULL,NULL,'0'),('995','定远县','107','341125',NULL,NULL,NULL,NULL,'0'),('996','凤阳县','107','341126',NULL,NULL,NULL,NULL,'0'),('997','天长市','107','341181',NULL,NULL,NULL,NULL,'0'),('998','明光市','107','341182',NULL,NULL,NULL,NULL,'0'),('999','颍州区','108','341202',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_qud_qux` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_qud_shengf`
--

DROP TABLE IF EXISTS `sys_qud_shengf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_qud_shengf` (
  `id` varchar(150) NOT NULL,
  `mingc` text,
  `jianc` text,
  `guobm` varchar(60) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_qud_shengf`
--

LOCK TABLES `sys_qud_shengf` WRITE;
/*!40000 ALTER TABLE `sys_qud_shengf` DISABLE KEYS */;
INSERT INTO `sys_qud_shengf` VALUES ('1','北京市','北京','110000',NULL,NULL,NULL,NULL,'0'),('10','江苏省','江苏','320000',NULL,NULL,NULL,NULL,'0'),('11','浙江省','浙江','330000',NULL,NULL,NULL,NULL,'0'),('12','安徽省','安徽','340000',NULL,NULL,NULL,NULL,'0'),('13','福建省','福建','350000',NULL,NULL,NULL,NULL,'0'),('14','江西省','江西','360000',NULL,NULL,NULL,NULL,'0'),('15','山东省','山东','370000',NULL,NULL,NULL,NULL,'0'),('16','河南省','河南','410000',NULL,NULL,NULL,NULL,'0'),('17','湖北省','湖北','420000',NULL,NULL,NULL,NULL,'0'),('18','湖南省','湖南','430000',NULL,NULL,NULL,NULL,'0'),('19','广东省','广东','440000',NULL,NULL,NULL,NULL,'0'),('2','天津市','天津','120000',NULL,NULL,NULL,NULL,'0'),('20','广西壮族自治区','广西','450000',NULL,NULL,NULL,NULL,'0'),('21','海南省','海南','460000',NULL,NULL,NULL,NULL,'0'),('22','重庆市','重庆','500000',NULL,NULL,NULL,NULL,'0'),('23','四川省','四川','510000',NULL,NULL,NULL,NULL,'0'),('24','贵州省','贵州','520000',NULL,NULL,NULL,NULL,'0'),('25','云南省','云南','530000',NULL,NULL,NULL,NULL,'0'),('26','西藏自治区','西藏','540000',NULL,NULL,NULL,NULL,'0'),('27','陕西省','陕西','610000',NULL,NULL,NULL,NULL,'0'),('28','甘肃省','甘肃','620000',NULL,NULL,NULL,NULL,'0'),('29','青海省','青海','630000',NULL,NULL,NULL,NULL,'0'),('3','河北省','河北','130000',NULL,NULL,NULL,NULL,'0'),('30','宁夏回族自治区','宁夏','640000',NULL,NULL,NULL,NULL,'0'),('31','新疆维吾尔自治区','新疆','650000',NULL,NULL,NULL,NULL,'0'),('32','香港特别行政区','香港','810000',NULL,NULL,NULL,NULL,'0'),('33','澳门特别行政区','澳门','820000',NULL,NULL,NULL,NULL,'0'),('34','台湾省','台湾','710000',NULL,NULL,NULL,NULL,'0'),('4','山西省','山西','140000',NULL,NULL,NULL,NULL,'0'),('5','内蒙古自治区','内蒙古','150000',NULL,NULL,NULL,NULL,'0'),('6','辽宁省','辽宁','210000',NULL,NULL,NULL,NULL,'0'),('7','吉林省','吉林','220000',NULL,NULL,NULL,NULL,'0'),('8','黑龙江省','黑龙江','230000',NULL,NULL,NULL,NULL,'0'),('9','上海市','上海','310000',NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_qud_shengf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_qud_shengf_arch`
--

DROP TABLE IF EXISTS `sys_qud_shengf_arch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_qud_shengf_arch` (
  `id` varchar(150) NOT NULL,
  `mingc` text,
  `jianc` text,
  `guobm` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_qud_shengf_arch`
--

LOCK TABLES `sys_qud_shengf_arch` WRITE;
/*!40000 ALTER TABLE `sys_qud_shengf_arch` DISABLE KEYS */;
INSERT INTO `sys_qud_shengf_arch` VALUES ('1','北京市','北京','110000'),('2','天津市','天津','120000'),('3','河北省','河北','130000'),('4','山西省','山西','140000'),('5','内蒙古自治区','内蒙古','150000'),('6','辽宁省','辽宁','210000'),('7','吉林省','吉林','220000'),('8','黑龙江省','黑龙江','230000'),('9','上海市','上海','310000'),('10','江苏省','江苏','320000'),('11','浙江省','浙江','330000'),('12','安徽省','安徽','340000'),('13','福建省','福建','350000'),('14','江西省','江西','360000'),('15','山东省','山东','370000'),('16','河南省','河南','410000'),('17','湖北省','湖北','420000'),('18','湖南省','湖南','430000'),('19','广东省','广东','440000'),('20','广西壮族自治区','广西','450000'),('21','海南省','海南','460000'),('22','重庆市','重庆','500000'),('23','四川省','四川','510000'),('24','贵州省','贵州','520000'),('25','云南省','云南','530000'),('26','西藏自治区','西藏','540000'),('27','陕西省','陕西','610000'),('28','甘肃省','甘肃','620000'),('29','青海省','青海','630000'),('30','宁夏回族自治区','宁夏','640000'),('31','新疆维吾尔自治区','新疆','650000'),('32','香港特别行政区','香港','810000'),('33','澳门特别行政区','澳门','820000'),('34','台湾省','台湾','710000');
/*!40000 ALTER TABLE `sys_qud_shengf_arch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_region`
--

DROP TABLE IF EXISTS `sys_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_region` (
  `id` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parentid` varchar(50) NOT NULL,
  `first_letter` varchar(50) NOT NULL,
  `lev` decimal(5,0) NOT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_region`
--

LOCK TABLES `sys_region` WRITE;
/*!40000 ALTER TABLE `sys_region` DISABLE KEYS */;
INSERT INTO `sys_region` VALUES ('1','110000','北京市','0','B',0,NULL,NULL,NULL,NULL,'0'),('10','320000','江苏省','0','J',0,NULL,NULL,NULL,NULL,'0'),('100','230500','双鸭山市','8','S',1,NULL,NULL,NULL,NULL,'0'),('1000','230604','让胡路区','101','R',2,NULL,NULL,NULL,NULL,'0'),('1001','230605','红岗区','101','H',2,NULL,NULL,NULL,NULL,'0'),('1002','230606','大同区','101','D',2,NULL,NULL,NULL,NULL,'0'),('1003','230621','肇州县','101','Z',2,NULL,NULL,NULL,NULL,'0'),('1004','230622','肇源县','101','Z',2,NULL,NULL,NULL,NULL,'0'),('1005','230623','林甸县','101','L',2,NULL,NULL,NULL,NULL,'0'),('1006','230624','杜尔伯特蒙古族自治县','101','D',2,NULL,NULL,NULL,NULL,'0'),('1007','230702','伊春区','102','Y',2,NULL,NULL,NULL,NULL,'0'),('1008','230703','南岔区','102','N',2,NULL,NULL,NULL,NULL,'0'),('1009','230704','友好区','102','Y',2,NULL,NULL,NULL,NULL,'0'),('101','230600','大庆市','8','D',1,NULL,NULL,NULL,NULL,'0'),('1010','230705','西林区','102','X',2,NULL,NULL,NULL,NULL,'0'),('1011','230706','翠峦区','102','C',2,NULL,NULL,NULL,NULL,'0'),('1012','230707','新青区','102','X',2,NULL,NULL,NULL,NULL,'0'),('1013','230708','美溪区','102','M',2,NULL,NULL,NULL,NULL,'0'),('1014','230709','金山屯区','102','J',2,NULL,NULL,NULL,NULL,'0'),('1015','230710','五营区','102','W',2,NULL,NULL,NULL,NULL,'0'),('1016','230711','乌马河区','102','W',2,NULL,NULL,NULL,NULL,'0'),('1017','230712','汤旺河区','102','T',2,NULL,NULL,NULL,NULL,'0'),('1018','230713','带岭区','102','D',2,NULL,NULL,NULL,NULL,'0'),('1019','230714','乌伊岭区','102','W',2,NULL,NULL,NULL,NULL,'0'),('102','230700','伊春市','8','Y',1,NULL,NULL,NULL,NULL,'0'),('1020','230715','红星区','102','H',2,NULL,NULL,NULL,NULL,'0'),('1021','230716','上甘岭区','102','S',2,NULL,NULL,NULL,NULL,'0'),('1022','230722','嘉荫县','102','J',2,NULL,NULL,NULL,NULL,'0'),('1023','230781','铁力市','102','T',2,NULL,NULL,NULL,NULL,'0'),('1024','230804','前进区','103','Q',2,NULL,NULL,NULL,NULL,'0'),('1025','230805','东风区','103','D',2,NULL,NULL,NULL,NULL,'0'),('1026','230822','桦南县','103','H',2,NULL,NULL,NULL,NULL,'0'),('1027','230826','桦川县','103','H',2,NULL,NULL,NULL,NULL,'0'),('1028','230828','汤原县','103','T',2,NULL,NULL,NULL,NULL,'0'),('1029','230833','抚远县','103','F',2,NULL,NULL,NULL,NULL,'0'),('103','230800','佳木斯市','8','J',1,NULL,NULL,NULL,NULL,'0'),('1030','230881','同江市','103','T',2,NULL,NULL,NULL,NULL,'0'),('1031','230882','富锦市','103','F',2,NULL,NULL,NULL,NULL,'0'),('1032','230902','新兴区','104','X',2,NULL,NULL,NULL,NULL,'0'),('1033','230903','桃山区','104','T',2,NULL,NULL,NULL,NULL,'0'),('1034','230904','茄子河区','104','Q',2,NULL,NULL,NULL,NULL,'0'),('1035','230921','勃利县','104','B',2,NULL,NULL,NULL,NULL,'0'),('1036','231002','东安区','105','D',2,NULL,NULL,NULL,NULL,'0'),('1037','231003','阳明区','105','Y',2,NULL,NULL,NULL,NULL,'0'),('1038','231004','爱民区','105','A',2,NULL,NULL,NULL,NULL,'0'),('1039','231024','东宁县','105','D',2,NULL,NULL,NULL,NULL,'0'),('104','230900','七台河市','8','Q',1,NULL,NULL,NULL,NULL,'0'),('1040','231025','林口县','105','L',2,NULL,NULL,NULL,NULL,'0'),('1041','231081','绥芬河市','105','S',2,NULL,NULL,NULL,NULL,'0'),('1042','231083','海林市','105','H',2,NULL,NULL,NULL,NULL,'0'),('1043','231084','宁安市','105','N',2,NULL,NULL,NULL,NULL,'0'),('1044','231085','穆棱市','105','M',2,NULL,NULL,NULL,NULL,'0'),('1045','231102','爱辉区','106','A',2,NULL,NULL,NULL,NULL,'0'),('1046','231121','嫩江县','106','N',2,NULL,NULL,NULL,NULL,'0'),('1047','231123','逊克县','106','X',2,NULL,NULL,NULL,NULL,'0'),('1048','231124','孙吴县','106','S',2,NULL,NULL,NULL,NULL,'0'),('1049','231181','北安市','106','B',2,NULL,NULL,NULL,NULL,'0'),('105','231000','牡丹江市','8','M',1,NULL,NULL,NULL,NULL,'0'),('1050','231182','五大连池市','106','W',2,NULL,NULL,NULL,NULL,'0'),('1051','231202','北林区','107','B',2,NULL,NULL,NULL,NULL,'0'),('1052','231221','望奎县','107','W',2,NULL,NULL,NULL,NULL,'0'),('1053','231222','兰西县','107','L',2,NULL,NULL,NULL,NULL,'0'),('1054','231223','青冈县','107','Q',2,NULL,NULL,NULL,NULL,'0'),('1055','231224','庆安县','107','Q',2,NULL,NULL,NULL,NULL,'0'),('1056','231225','明水县','107','M',2,NULL,NULL,NULL,NULL,'0'),('1057','231226','绥棱县','107','S',2,NULL,NULL,NULL,NULL,'0'),('1058','231281','安达市','107','A',2,NULL,NULL,NULL,NULL,'0'),('1059','231282','肇东市','107','Z',2,NULL,NULL,NULL,NULL,'0'),('106','231100','黑河市','8','H',1,NULL,NULL,NULL,NULL,'0'),('1060','231283','海伦市','107','H',2,NULL,NULL,NULL,NULL,'0'),('1061','232721','呼玛县','108','H',2,NULL,NULL,NULL,NULL,'0'),('1062','232722','塔河县','108','T',2,NULL,NULL,NULL,NULL,'0'),('1063','232723','漠河县','108','M',2,NULL,NULL,NULL,NULL,'0'),('1064','310101','黄浦区','109','H',2,NULL,NULL,NULL,NULL,'0'),('1065','310104','徐汇区','109','X',2,NULL,NULL,NULL,NULL,'0'),('1066','310105','长宁区','109','C',2,NULL,NULL,NULL,NULL,'0'),('1067','310106','静安区','109','J',2,NULL,NULL,NULL,NULL,'0'),('1068','310107','普陀区','109','P',2,NULL,NULL,NULL,NULL,'0'),('1069','310108','闸北区','109','Z',2,NULL,NULL,NULL,NULL,'0'),('107','231200','绥化市','8','S',1,NULL,NULL,NULL,NULL,'0'),('1070','310109','虹口区','109','H',2,NULL,NULL,NULL,NULL,'0'),('1071','310110','杨浦区','109','Y',2,NULL,NULL,NULL,NULL,'0'),('1072','310112','闵行区','109','M',2,NULL,NULL,NULL,NULL,'0'),('1073','310114','嘉定区','109','J',2,NULL,NULL,NULL,NULL,'0'),('1074','310115','浦东新区','109','P',2,NULL,NULL,NULL,NULL,'0'),('1075','310116','金山区','109','J',2,NULL,NULL,NULL,NULL,'0'),('1076','310117','松江区','109','S',2,NULL,NULL,NULL,NULL,'0'),('1077','310118','青浦区','109','Q',2,NULL,NULL,NULL,NULL,'0'),('1078','310120','奉贤区','109','F',2,NULL,NULL,NULL,NULL,'0'),('1079','310230','崇明县','110','C',2,NULL,NULL,NULL,NULL,'0'),('108','232700','大兴安岭地区','8','D',1,NULL,NULL,NULL,NULL,'0'),('1080','320102','玄武区','111','X',2,NULL,NULL,NULL,NULL,'0'),('1081','320104','秦淮区','111','Q',2,NULL,NULL,NULL,NULL,'0'),('1082','320105','建邺区','111','J',2,NULL,NULL,NULL,NULL,'0'),('1083','320106','鼓楼区','111','G',2,NULL,NULL,NULL,NULL,'0'),('1084','320111','浦口区','111','P',2,NULL,NULL,NULL,NULL,'0'),('1085','320113','栖霞区','111','Q',2,NULL,NULL,NULL,NULL,'0'),('1086','320114','雨花台区','111','Y',2,NULL,NULL,NULL,NULL,'0'),('1087','320115','江宁区','111','J',2,NULL,NULL,NULL,NULL,'0'),('1088','320116','六合区','111','L',2,NULL,NULL,NULL,NULL,'0'),('1089','320117','溧水区','111','L',2,NULL,NULL,NULL,NULL,'0'),('109','310100','上海市','9','S',1,NULL,NULL,NULL,NULL,'0'),('1090','320118','高淳区','111','G',2,NULL,NULL,NULL,NULL,'0'),('1091','320202','崇安区','112','C',2,NULL,NULL,NULL,NULL,'0'),('1092','320203','南长区','112','N',2,NULL,NULL,NULL,NULL,'0'),('1093','320204','北塘区','112','B',2,NULL,NULL,NULL,NULL,'0'),('1094','320205','锡山区','112','X',2,NULL,NULL,NULL,NULL,'0'),('1095','320206','惠山区','112','H',2,NULL,NULL,NULL,NULL,'0'),('1096','320211','滨湖区','112','B',2,NULL,NULL,NULL,NULL,'0'),('1097','320281','江阴市','112','J',2,NULL,NULL,NULL,NULL,'0'),('1098','320282','宜兴市','112','Y',2,NULL,NULL,NULL,NULL,'0'),('1099','320303','云龙区','113','Y',2,NULL,NULL,NULL,NULL,'0'),('11','330000','浙江省','0','Z',0,NULL,NULL,NULL,NULL,'0'),('110','310200','上海县','9','S',1,NULL,NULL,NULL,NULL,'0'),('1100','320305','贾汪区','113','J',2,NULL,NULL,NULL,NULL,'0'),('1101','320311','泉山区','113','Q',2,NULL,NULL,NULL,NULL,'0'),('1102','320312','铜山区','113','T',2,NULL,NULL,NULL,NULL,'0'),('1103','320321','丰县','113','F',2,NULL,NULL,NULL,NULL,'0'),('1104','320322','沛县','113','P',2,NULL,NULL,NULL,NULL,'0'),('1105','320324','睢宁县','113','S',2,NULL,NULL,NULL,NULL,'0'),('1106','320381','新沂市','113','X',2,NULL,NULL,NULL,NULL,'0'),('1107','320382','邳州市','113','P',2,NULL,NULL,NULL,NULL,'0'),('1108','320402','天宁区','114','T',2,NULL,NULL,NULL,NULL,'0'),('1109','320404','钟楼区','114','Z',2,NULL,NULL,NULL,NULL,'0'),('111','320100','南京市','10','N',1,NULL,NULL,NULL,NULL,'0'),('1110','320405','戚墅堰区','114','Q',2,NULL,NULL,NULL,NULL,'0'),('1111','320411','新北区','114','X',2,NULL,NULL,NULL,NULL,'0'),('1112','320412','武进区','114','W',2,NULL,NULL,NULL,NULL,'0'),('1113','320481','溧阳市','114','L',2,NULL,NULL,NULL,NULL,'0'),('1114','320482','金坛市','114','J',2,NULL,NULL,NULL,NULL,'0'),('1115','320505','虎丘区','115','H',2,NULL,NULL,NULL,NULL,'0'),('1116','320506','吴中区','115','W',2,NULL,NULL,NULL,NULL,'0'),('1117','320507','相城区','115','X',2,NULL,NULL,NULL,NULL,'0'),('1118','320508','姑苏区','115','G',2,NULL,NULL,NULL,NULL,'0'),('1119','320509','吴江区','115','W',2,NULL,NULL,NULL,NULL,'0'),('112','320200','无锡市','10','W',1,NULL,NULL,NULL,NULL,'0'),('1120','320581','常熟市','115','C',2,NULL,NULL,NULL,NULL,'0'),('1121','320582','张家港市','115','Z',2,NULL,NULL,NULL,NULL,'0'),('1122','320583','昆山市','115','K',2,NULL,NULL,NULL,NULL,'0'),('1123','320585','太仓市','115','T',2,NULL,NULL,NULL,NULL,'0'),('1124','320602','崇川区','116','C',2,NULL,NULL,NULL,NULL,'0'),('1125','320611','港闸区','116','G',2,NULL,NULL,NULL,NULL,'0'),('1126','320612','通州区','116','T',2,NULL,NULL,NULL,NULL,'0'),('1127','320621','海安县','116','H',2,NULL,NULL,NULL,NULL,'0'),('1128','320623','如东县','116','R',2,NULL,NULL,NULL,NULL,'0'),('1129','320681','启东市','116','Q',2,NULL,NULL,NULL,NULL,'0'),('113','320300','徐州市','10','X',1,NULL,NULL,NULL,NULL,'0'),('1130','320682','如皋市','116','R',2,NULL,NULL,NULL,NULL,'0'),('1131','320684','海门市','116','H',2,NULL,NULL,NULL,NULL,'0'),('1132','320703','连云区','117','L',2,NULL,NULL,NULL,NULL,'0'),('1133','320707','赣榆区','117','G',2,NULL,NULL,NULL,NULL,'0'),('1134','320722','东海县','117','D',2,NULL,NULL,NULL,NULL,'0'),('1135','320723','灌云县','117','G',2,NULL,NULL,NULL,NULL,'0'),('1136','320724','灌南县','117','G',2,NULL,NULL,NULL,NULL,'0'),('1137','320803','淮安区','118','H',2,NULL,NULL,NULL,NULL,'0'),('1138','320804','淮阴区','118','H',2,NULL,NULL,NULL,NULL,'0'),('1139','320811','清浦区','118','Q',2,NULL,NULL,NULL,NULL,'0'),('114','320400','常州市','10','C',1,NULL,NULL,NULL,NULL,'0'),('1140','320826','涟水县','118','L',2,NULL,NULL,NULL,NULL,'0'),('1141','320829','洪泽县','118','H',2,NULL,NULL,NULL,NULL,'0'),('1142','320830','盱眙县','118','X',2,NULL,NULL,NULL,NULL,'0'),('1143','320831','金湖县','118','J',2,NULL,NULL,NULL,NULL,'0'),('1144','320902','亭湖区','119','T',2,NULL,NULL,NULL,NULL,'0'),('1145','320903','盐都区','119','Y',2,NULL,NULL,NULL,NULL,'0'),('1146','320921','响水县','119','X',2,NULL,NULL,NULL,NULL,'0'),('1147','320922','滨海县','119','B',2,NULL,NULL,NULL,NULL,'0'),('1148','320923','阜宁县','119','F',2,NULL,NULL,NULL,NULL,'0'),('1149','320924','射阳县','119','S',2,NULL,NULL,NULL,NULL,'0'),('115','320500','苏州市','10','S',1,NULL,NULL,NULL,NULL,'0'),('1150','320925','建湖县','119','J',2,NULL,NULL,NULL,NULL,'0'),('1151','320981','东台市','119','D',2,NULL,NULL,NULL,NULL,'0'),('1152','320982','大丰市','119','D',2,NULL,NULL,NULL,NULL,'0'),('1153','321002','广陵区','120','G',2,NULL,NULL,NULL,NULL,'0'),('1154','321003','邗江区','120','H',2,NULL,NULL,NULL,NULL,'0'),('1155','321012','江都区','120','J',2,NULL,NULL,NULL,NULL,'0'),('1156','321023','宝应县','120','B',2,NULL,NULL,NULL,NULL,'0'),('1157','321081','仪征市','120','Y',2,NULL,NULL,NULL,NULL,'0'),('1158','321084','高邮市','120','G',2,NULL,NULL,NULL,NULL,'0'),('1159','321102','京口区','121','J',2,NULL,NULL,NULL,NULL,'0'),('116','320600','南通市','10','N',1,NULL,NULL,NULL,NULL,'0'),('1160','321111','润州区','121','R',2,NULL,NULL,NULL,NULL,'0'),('1161','321112','丹徒区','121','D',2,NULL,NULL,NULL,NULL,'0'),('1162','321181','丹阳市','121','D',2,NULL,NULL,NULL,NULL,'0'),('1163','321182','扬中市','121','Y',2,NULL,NULL,NULL,NULL,'0'),('1164','321183','句容市','121','J',2,NULL,NULL,NULL,NULL,'0'),('1165','321202','海陵区','122','H',2,NULL,NULL,NULL,NULL,'0'),('1166','321203','高港区','122','G',2,NULL,NULL,NULL,NULL,'0'),('1167','321204','姜堰区','122','J',2,NULL,NULL,NULL,NULL,'0'),('1168','321281','兴化市','122','X',2,NULL,NULL,NULL,NULL,'0'),('1169','321282','靖江市','122','J',2,NULL,NULL,NULL,NULL,'0'),('117','320700','连云港市','10','L',1,NULL,NULL,NULL,NULL,'0'),('1170','321283','泰兴市','122','T',2,NULL,NULL,NULL,NULL,'0'),('1171','321302','宿城区','123','S',2,NULL,NULL,NULL,NULL,'0'),('1172','321311','宿豫区','123','S',2,NULL,NULL,NULL,NULL,'0'),('1173','321322','沭阳县','123','S',2,NULL,NULL,NULL,NULL,'0'),('1174','321323','泗阳县','123','S',2,NULL,NULL,NULL,NULL,'0'),('1175','321324','泗洪县','123','S',2,NULL,NULL,NULL,NULL,'0'),('1176','330102','上城区','124','S',2,NULL,NULL,NULL,NULL,'0'),('1177','330103','下城区','124','X',2,NULL,NULL,NULL,NULL,'0'),('1178','330104','江干区','124','J',2,NULL,NULL,NULL,NULL,'0'),('1179','330105','拱墅区','124','G',2,NULL,NULL,NULL,NULL,'0'),('118','320800','淮安市','10','H',1,NULL,NULL,NULL,NULL,'0'),('1180','330106','西湖区','124','X',2,NULL,NULL,NULL,NULL,'0'),('1181','330108','滨江区','124','B',2,NULL,NULL,NULL,NULL,'0'),('1182','330109','萧山区','124','X',2,NULL,NULL,NULL,NULL,'0'),('1183','330110','余杭区','124','Y',2,NULL,NULL,NULL,NULL,'0'),('1184','330122','桐庐县','124','T',2,NULL,NULL,NULL,NULL,'0'),('1185','330127','淳安县','124','C',2,NULL,NULL,NULL,NULL,'0'),('1186','330182','建德市','124','J',2,NULL,NULL,NULL,NULL,'0'),('1187','330183','富阳市','124','F',2,NULL,NULL,NULL,NULL,'0'),('1188','330185','临安市','124','L',2,NULL,NULL,NULL,NULL,'0'),('1189','330203','海曙区','125','H',2,NULL,NULL,NULL,NULL,'0'),('119','320900','盐城市','10','Y',1,NULL,NULL,NULL,NULL,'0'),('1190','330204','江东区','125','J',2,NULL,NULL,NULL,NULL,'0'),('1191','330205','江北区','125','J',2,NULL,NULL,NULL,NULL,'0'),('1192','330206','北仑区','125','B',2,NULL,NULL,NULL,NULL,'0'),('1193','330211','镇海区','125','Z',2,NULL,NULL,NULL,NULL,'0'),('1194','330212','鄞州区','125','Y',2,NULL,NULL,NULL,NULL,'0'),('1195','330225','象山县','125','X',2,NULL,NULL,NULL,NULL,'0'),('1196','330226','宁海县','125','N',2,NULL,NULL,NULL,NULL,'0'),('1197','330281','余姚市','125','Y',2,NULL,NULL,NULL,NULL,'0'),('1198','330282','慈溪市','125','C',2,NULL,NULL,NULL,NULL,'0'),('1199','330283','奉化市','125','F',2,NULL,NULL,NULL,NULL,'0'),('12','340000','安徽省','0','A',0,NULL,NULL,NULL,NULL,'0'),('120','321000','扬州市','10','Y',1,NULL,NULL,NULL,NULL,'0'),('1200','330302','鹿城区','126','L',2,NULL,NULL,NULL,NULL,'0'),('1201','330303','龙湾区','126','L',2,NULL,NULL,NULL,NULL,'0'),('1202','330304','瓯海区','126','O',2,NULL,NULL,NULL,NULL,'0'),('1203','330322','洞头县','126','D',2,NULL,NULL,NULL,NULL,'0'),('1204','330324','永嘉县','126','Y',2,NULL,NULL,NULL,NULL,'0'),('1205','330326','平阳县','126','P',2,NULL,NULL,NULL,NULL,'0'),('1206','330327','苍南县','126','C',2,NULL,NULL,NULL,NULL,'0'),('1207','330328','文成县','126','W',2,NULL,NULL,NULL,NULL,'0'),('1208','330329','泰顺县','126','T',2,NULL,NULL,NULL,NULL,'0'),('1209','330381','瑞安市','126','R',2,NULL,NULL,NULL,NULL,'0'),('121','321100','镇江市','10','Z',1,NULL,NULL,NULL,NULL,'0'),('1210','330382','乐清市','126','L',2,NULL,NULL,NULL,NULL,'0'),('1211','330402','南湖区','127','N',2,NULL,NULL,NULL,NULL,'0'),('1212','330411','秀洲区','127','X',2,NULL,NULL,NULL,NULL,'0'),('1213','330421','嘉善县','127','J',2,NULL,NULL,NULL,NULL,'0'),('1214','330424','海盐县','127','H',2,NULL,NULL,NULL,NULL,'0'),('1215','330481','海宁市','127','H',2,NULL,NULL,NULL,NULL,'0'),('1216','330482','平湖市','127','P',2,NULL,NULL,NULL,NULL,'0'),('1217','330483','桐乡市','127','T',2,NULL,NULL,NULL,NULL,'0'),('1218','330502','吴兴区','128','W',2,NULL,NULL,NULL,NULL,'0'),('1219','330503','南浔区','128','N',2,NULL,NULL,NULL,NULL,'0'),('122','321200','泰州市','10','T',1,NULL,NULL,NULL,NULL,'0'),('1220','330521','德清县','128','D',2,NULL,NULL,NULL,NULL,'0'),('1221','330522','长兴县','128','C',2,NULL,NULL,NULL,NULL,'0'),('1222','330523','安吉县','128','A',2,NULL,NULL,NULL,NULL,'0'),('1223','330602','越城区','129','Y',2,NULL,NULL,NULL,NULL,'0'),('1224','330603','柯桥区','129','K',2,NULL,NULL,NULL,NULL,'0'),('1225','330604','上虞区','129','S',2,NULL,NULL,NULL,NULL,'0'),('1226','330624','新昌县','129','X',2,NULL,NULL,NULL,NULL,'0'),('1227','330681','诸暨市','129','Z',2,NULL,NULL,NULL,NULL,'0'),('1228','330683','嵊州市','129','S',2,NULL,NULL,NULL,NULL,'0'),('1229','330702','婺城区','130','W',2,NULL,NULL,NULL,NULL,'0'),('123','321300','宿迁市','10','S',1,NULL,NULL,NULL,NULL,'0'),('1230','330703','金东区','130','J',2,NULL,NULL,NULL,NULL,'0'),('1231','330723','武义县','130','W',2,NULL,NULL,NULL,NULL,'0'),('1232','330726','浦江县','130','P',2,NULL,NULL,NULL,NULL,'0'),('1233','330727','磐安县','130','P',2,NULL,NULL,NULL,NULL,'0'),('1234','330781','兰溪市','130','L',2,NULL,NULL,NULL,NULL,'0'),('1235','330782','义乌市','130','Y',2,NULL,NULL,NULL,NULL,'0'),('1236','330783','东阳市','130','D',2,NULL,NULL,NULL,NULL,'0'),('1237','330784','永康市','130','Y',2,NULL,NULL,NULL,NULL,'0'),('1238','330802','柯城区','131','K',2,NULL,NULL,NULL,NULL,'0'),('1239','330803','衢江区','131','Q',2,NULL,NULL,NULL,NULL,'0'),('124','330100','杭州市','11','H',1,NULL,NULL,NULL,NULL,'0'),('1240','330822','常山县','131','C',2,NULL,NULL,NULL,NULL,'0'),('1241','330824','开化县','131','K',2,NULL,NULL,NULL,NULL,'0'),('1242','330825','龙游县','131','L',2,NULL,NULL,NULL,NULL,'0'),('1243','330881','江山市','131','J',2,NULL,NULL,NULL,NULL,'0'),('1244','330902','定海区','132','D',2,NULL,NULL,NULL,NULL,'0'),('1245','330921','岱山县','132','D',2,NULL,NULL,NULL,NULL,'0'),('1246','330922','嵊泗县','132','S',2,NULL,NULL,NULL,NULL,'0'),('1247','331002','椒江区','133','J',2,NULL,NULL,NULL,NULL,'0'),('1248','331003','黄岩区','133','H',2,NULL,NULL,NULL,NULL,'0'),('1249','331004','路桥区','133','L',2,NULL,NULL,NULL,NULL,'0'),('125','330200','宁波市','11','N',1,NULL,NULL,NULL,NULL,'0'),('1250','331021','玉环县','133','Y',2,NULL,NULL,NULL,NULL,'0'),('1251','331022','三门县','133','S',2,NULL,NULL,NULL,NULL,'0'),('1252','331023','天台县','133','T',2,NULL,NULL,NULL,NULL,'0'),('1253','331024','仙居县','133','X',2,NULL,NULL,NULL,NULL,'0'),('1254','331081','温岭市','133','W',2,NULL,NULL,NULL,NULL,'0'),('1255','331082','临海市','133','L',2,NULL,NULL,NULL,NULL,'0'),('1256','331102','莲都区','134','L',2,NULL,NULL,NULL,NULL,'0'),('1257','331121','青田县','134','Q',2,NULL,NULL,NULL,NULL,'0'),('1258','331122','缙云县','134','J',2,NULL,NULL,NULL,NULL,'0'),('1259','331123','遂昌县','134','S',2,NULL,NULL,NULL,NULL,'0'),('126','330300','温州市','11','W',1,NULL,NULL,NULL,NULL,'0'),('1260','331124','松阳县','134','S',2,NULL,NULL,NULL,NULL,'0'),('1261','331125','云和县','134','Y',2,NULL,NULL,NULL,NULL,'0'),('1262','331126','庆元县','134','Q',2,NULL,NULL,NULL,NULL,'0'),('1263','331127','景宁畲族自治县','134','J',2,NULL,NULL,NULL,NULL,'0'),('1264','331181','龙泉市','134','L',2,NULL,NULL,NULL,NULL,'0'),('1265','340102','瑶海区','135','Y',2,NULL,NULL,NULL,NULL,'0'),('1266','340103','庐阳区','135','L',2,NULL,NULL,NULL,NULL,'0'),('1267','340104','蜀山区','135','S',2,NULL,NULL,NULL,NULL,'0'),('1268','340111','包河区','135','B',2,NULL,NULL,NULL,NULL,'0'),('1269','340121','长丰县','135','C',2,NULL,NULL,NULL,NULL,'0'),('127','330400','嘉兴市','11','J',1,NULL,NULL,NULL,NULL,'0'),('1270','340122','肥东县','135','F',2,NULL,NULL,NULL,NULL,'0'),('1271','340123','肥西县','135','F',2,NULL,NULL,NULL,NULL,'0'),('1272','340124','庐江县','135','L',2,NULL,NULL,NULL,NULL,'0'),('1273','340181','巢湖市','135','C',2,NULL,NULL,NULL,NULL,'0'),('1274','340202','镜湖区','136','J',2,NULL,NULL,NULL,NULL,'0'),('1275','340203','弋江区','136','Y',2,NULL,NULL,NULL,NULL,'0'),('1276','340207','鸠江区','136','J',2,NULL,NULL,NULL,NULL,'0'),('1277','340208','三山区','136','S',2,NULL,NULL,NULL,NULL,'0'),('1278','340221','芜湖县','136','W',2,NULL,NULL,NULL,NULL,'0'),('1279','340222','繁昌县','136','F',2,NULL,NULL,NULL,NULL,'0'),('128','330500','湖州市','11','H',1,NULL,NULL,NULL,NULL,'0'),('1280','340223','南陵县','136','N',2,NULL,NULL,NULL,NULL,'0'),('1281','340225','无为县','136','W',2,NULL,NULL,NULL,NULL,'0'),('1282','340302','龙子湖区','137','L',2,NULL,NULL,NULL,NULL,'0'),('1283','340303','蚌山区','137','B',2,NULL,NULL,NULL,NULL,'0'),('1284','340304','禹会区','137','Y',2,NULL,NULL,NULL,NULL,'0'),('1285','340311','淮上区','137','H',2,NULL,NULL,NULL,NULL,'0'),('1286','340321','怀远县','137','H',2,NULL,NULL,NULL,NULL,'0'),('1287','340322','五河县','137','W',2,NULL,NULL,NULL,NULL,'0'),('1288','340323','固镇县','137','G',2,NULL,NULL,NULL,NULL,'0'),('1289','340402','大通区','138','D',2,NULL,NULL,NULL,NULL,'0'),('129','330600','绍兴市','11','S',1,NULL,NULL,NULL,NULL,'0'),('1290','340403','田家庵区','138','T',2,NULL,NULL,NULL,NULL,'0'),('1291','340404','谢家集区','138','X',2,NULL,NULL,NULL,NULL,'0'),('1292','340405','八公山区','138','B',2,NULL,NULL,NULL,NULL,'0'),('1293','340406','潘集区','138','P',2,NULL,NULL,NULL,NULL,'0'),('1294','340421','凤台县','138','F',2,NULL,NULL,NULL,NULL,'0'),('1295','340503','花山区','139','H',2,NULL,NULL,NULL,NULL,'0'),('1296','340504','雨山区','139','Y',2,NULL,NULL,NULL,NULL,'0'),('1297','340506','博望区','139','B',2,NULL,NULL,NULL,NULL,'0'),('1298','340521','当涂县','139','D',2,NULL,NULL,NULL,NULL,'0'),('1299','340522','含山县','139','H',2,NULL,NULL,NULL,NULL,'0'),('13','350000','福建省','0','F',0,NULL,NULL,NULL,NULL,'0'),('130','330700','金华市','11','J',1,NULL,NULL,NULL,NULL,'0'),('1300','340523','和县','139','H',2,NULL,NULL,NULL,NULL,'0'),('1301','340602','杜集区','140','D',2,NULL,NULL,NULL,NULL,'0'),('1302','340603','相山区','140','X',2,NULL,NULL,NULL,NULL,'0'),('1303','340604','烈山区','140','L',2,NULL,NULL,NULL,NULL,'0'),('1304','340621','濉溪县','140','S',2,NULL,NULL,NULL,NULL,'0'),('1305','340702','铜官山区','141','T',2,NULL,NULL,NULL,NULL,'0'),('1306','340703','狮子山区','141','S',2,NULL,NULL,NULL,NULL,'0'),('1307','340721','铜陵县','141','T',2,NULL,NULL,NULL,NULL,'0'),('1308','340802','迎江区','142','Y',2,NULL,NULL,NULL,NULL,'0'),('1309','340803','大观区','142','D',2,NULL,NULL,NULL,NULL,'0'),('131','330800','衢州市','11','Q',1,NULL,NULL,NULL,NULL,'0'),('1310','340811','宜秀区','142','Y',2,NULL,NULL,NULL,NULL,'0'),('1311','340822','怀宁县','142','H',2,NULL,NULL,NULL,NULL,'0'),('1312','340823','枞阳县','142','C',2,NULL,NULL,NULL,NULL,'0'),('1313','340824','潜山县','142','Q',2,NULL,NULL,NULL,NULL,'0'),('1314','340825','太湖县','142','T',2,NULL,NULL,NULL,NULL,'0'),('1315','340826','宿松县','142','S',2,NULL,NULL,NULL,NULL,'0'),('1316','340827','望江县','142','W',2,NULL,NULL,NULL,NULL,'0'),('1317','340828','岳西县','142','Y',2,NULL,NULL,NULL,NULL,'0'),('1318','340881','桐城市','142','T',2,NULL,NULL,NULL,NULL,'0'),('1319','341002','屯溪区','143','T',2,NULL,NULL,NULL,NULL,'0'),('132','330900','舟山市','11','Z',1,NULL,NULL,NULL,NULL,'0'),('1320','341003','黄山区','143','H',2,NULL,NULL,NULL,NULL,'0'),('1321','341004','徽州区','143','H',2,NULL,NULL,NULL,NULL,'0'),('1322','341021','歙县','143','X',2,NULL,NULL,NULL,NULL,'0'),('1323','341022','休宁县','143','X',2,NULL,NULL,NULL,NULL,'0'),('1324','341023','黟县','143','Y',2,NULL,NULL,NULL,NULL,'0'),('1325','341024','祁门县','143','Q',2,NULL,NULL,NULL,NULL,'0'),('1326','341102','琅琊区','144','L',2,NULL,NULL,NULL,NULL,'0'),('1327','341103','南谯区','144','N',2,NULL,NULL,NULL,NULL,'0'),('1328','341122','来安县','144','L',2,NULL,NULL,NULL,NULL,'0'),('1329','341124','全椒县','144','Q',2,NULL,NULL,NULL,NULL,'0'),('133','331000','台州市','11','T',1,NULL,NULL,NULL,NULL,'0'),('1330','341125','定远县','144','D',2,NULL,NULL,NULL,NULL,'0'),('1331','341126','凤阳县','144','F',2,NULL,NULL,NULL,NULL,'0'),('1332','341181','天长市','144','T',2,NULL,NULL,NULL,NULL,'0'),('1333','341182','明光市','144','M',2,NULL,NULL,NULL,NULL,'0'),('1334','341202','颍州区','145','Y',2,NULL,NULL,NULL,NULL,'0'),('1335','341203','颍东区','145','Y',2,NULL,NULL,NULL,NULL,'0'),('1336','341204','颍泉区','145','Y',2,NULL,NULL,NULL,NULL,'0'),('1337','341221','临泉县','145','L',2,NULL,NULL,NULL,NULL,'0'),('1338','341222','太和县','145','T',2,NULL,NULL,NULL,NULL,'0'),('1339','341225','阜南县','145','F',2,NULL,NULL,NULL,NULL,'0'),('134','331100','丽水市','11','L',1,NULL,NULL,NULL,NULL,'0'),('1340','341226','颍上县','145','Y',2,NULL,NULL,NULL,NULL,'0'),('1341','341282','界首市','145','J',2,NULL,NULL,NULL,NULL,'0'),('1342','341302','埇桥区','146','Y',2,NULL,NULL,NULL,NULL,'0'),('1343','341321','砀山县','146','D',2,NULL,NULL,NULL,NULL,'0'),('1344','341322','萧县','146','X',2,NULL,NULL,NULL,NULL,'0'),('1345','341323','灵璧县','146','L',2,NULL,NULL,NULL,NULL,'0'),('1346','341324','泗县','146','S',2,NULL,NULL,NULL,NULL,'0'),('1347','341502','金安区','147','J',2,NULL,NULL,NULL,NULL,'0'),('1348','341503','裕安区','147','Y',2,NULL,NULL,NULL,NULL,'0'),('1349','341521','寿县','147','S',2,NULL,NULL,NULL,NULL,'0'),('135','340100','合肥市','12','H',1,NULL,NULL,NULL,NULL,'0'),('1350','341522','霍邱县','147','H',2,NULL,NULL,NULL,NULL,'0'),('1351','341523','舒城县','147','S',2,NULL,NULL,NULL,NULL,'0'),('1352','341524','金寨县','147','J',2,NULL,NULL,NULL,NULL,'0'),('1353','341525','霍山县','147','H',2,NULL,NULL,NULL,NULL,'0'),('1354','341602','谯城区','148','Q',2,NULL,NULL,NULL,NULL,'0'),('1355','341621','涡阳县','148','W',2,NULL,NULL,NULL,NULL,'0'),('1356','341622','蒙城县','148','M',2,NULL,NULL,NULL,NULL,'0'),('1357','341623','利辛县','148','L',2,NULL,NULL,NULL,NULL,'0'),('1358','341702','贵池区','149','G',2,NULL,NULL,NULL,NULL,'0'),('1359','341721','东至县','149','D',2,NULL,NULL,NULL,NULL,'0'),('136','340200','芜湖市','12','W',1,NULL,NULL,NULL,NULL,'0'),('1360','341722','石台县','149','S',2,NULL,NULL,NULL,NULL,'0'),('1361','341723','青阳县','149','Q',2,NULL,NULL,NULL,NULL,'0'),('1362','341802','宣州区','150','X',2,NULL,NULL,NULL,NULL,'0'),('1363','341821','郎溪县','150','L',2,NULL,NULL,NULL,NULL,'0'),('1364','341822','广德县','150','G',2,NULL,NULL,NULL,NULL,'0'),('1365','341823','泾县','150','J',2,NULL,NULL,NULL,NULL,'0'),('1366','341824','绩溪县','150','J',2,NULL,NULL,NULL,NULL,'0'),('1367','341825','旌德县','150','J',2,NULL,NULL,NULL,NULL,'0'),('1368','341881','宁国市','150','N',2,NULL,NULL,NULL,NULL,'0'),('1369','350103','台江区','151','T',2,NULL,NULL,NULL,NULL,'0'),('137','340300','蚌埠市','12','B',1,NULL,NULL,NULL,NULL,'0'),('1370','350104','仓山区','151','C',2,NULL,NULL,NULL,NULL,'0'),('1371','350105','马尾区','151','M',2,NULL,NULL,NULL,NULL,'0'),('1372','350111','晋安区','151','J',2,NULL,NULL,NULL,NULL,'0'),('1373','350121','闽侯县','151','M',2,NULL,NULL,NULL,NULL,'0'),('1374','350122','连江县','151','L',2,NULL,NULL,NULL,NULL,'0'),('1375','350123','罗源县','151','L',2,NULL,NULL,NULL,NULL,'0'),('1376','350124','闽清县','151','M',2,NULL,NULL,NULL,NULL,'0'),('1377','350125','永泰县','151','Y',2,NULL,NULL,NULL,NULL,'0'),('1378','350128','平潭县','151','P',2,NULL,NULL,NULL,NULL,'0'),('1379','350181','福清市','151','F',2,NULL,NULL,NULL,NULL,'0'),('138','340400','淮南市','12','H',1,NULL,NULL,NULL,NULL,'0'),('1380','350182','长乐市','151','C',2,NULL,NULL,NULL,NULL,'0'),('1381','350203','思明区','152','S',2,NULL,NULL,NULL,NULL,'0'),('1382','350205','海沧区','152','H',2,NULL,NULL,NULL,NULL,'0'),('1383','350206','湖里区','152','H',2,NULL,NULL,NULL,NULL,'0'),('1384','350211','集美区','152','J',2,NULL,NULL,NULL,NULL,'0'),('1385','350212','同安区','152','T',2,NULL,NULL,NULL,NULL,'0'),('1386','350213','翔安区','152','X',2,NULL,NULL,NULL,NULL,'0'),('1387','350302','城厢区','153','C',2,NULL,NULL,NULL,NULL,'0'),('1388','350303','涵江区','153','H',2,NULL,NULL,NULL,NULL,'0'),('1389','350304','荔城区','153','L',2,NULL,NULL,NULL,NULL,'0'),('139','340500','马鞍山市','12','M',1,NULL,NULL,NULL,NULL,'0'),('1390','350305','秀屿区','153','X',2,NULL,NULL,NULL,NULL,'0'),('1391','350322','仙游县','153','X',2,NULL,NULL,NULL,NULL,'0'),('1392','350402','梅列区','154','M',2,NULL,NULL,NULL,NULL,'0'),('1393','350403','三元区','154','S',2,NULL,NULL,NULL,NULL,'0'),('1394','350421','明溪县','154','M',2,NULL,NULL,NULL,NULL,'0'),('1395','350423','清流县','154','Q',2,NULL,NULL,NULL,NULL,'0'),('1396','350424','宁化县','154','N',2,NULL,NULL,NULL,NULL,'0'),('1397','350425','大田县','154','D',2,NULL,NULL,NULL,NULL,'0'),('1398','350426','尤溪县','154','Y',2,NULL,NULL,NULL,NULL,'0'),('1399','350427','沙县','154','S',2,NULL,NULL,NULL,NULL,'0'),('14','360000','江西省','0','J',0,NULL,NULL,NULL,NULL,'0'),('140','340600','淮北市','12','H',1,NULL,NULL,NULL,NULL,'0'),('1400','350428','将乐县','154','J',2,NULL,NULL,NULL,NULL,'0'),('1401','350429','泰宁县','154','T',2,NULL,NULL,NULL,NULL,'0'),('1402','350430','建宁县','154','J',2,NULL,NULL,NULL,NULL,'0'),('1403','350481','永安市','154','Y',2,NULL,NULL,NULL,NULL,'0'),('1404','350502','鲤城区','155','L',2,NULL,NULL,NULL,NULL,'0'),('1405','350503','丰泽区','155','F',2,NULL,NULL,NULL,NULL,'0'),('1406','350504','洛江区','155','L',2,NULL,NULL,NULL,NULL,'0'),('1407','350505','泉港区','155','Q',2,NULL,NULL,NULL,NULL,'0'),('1408','350521','惠安县','155','H',2,NULL,NULL,NULL,NULL,'0'),('1409','350524','安溪县','155','A',2,NULL,NULL,NULL,NULL,'0'),('141','340700','铜陵市','12','T',1,NULL,NULL,NULL,NULL,'0'),('1410','350525','永春县','155','Y',2,NULL,NULL,NULL,NULL,'0'),('1411','350526','德化县','155','D',2,NULL,NULL,NULL,NULL,'0'),('1412','350527','金门县','155','J',2,NULL,NULL,NULL,NULL,'0'),('1413','350581','石狮市','155','S',2,NULL,NULL,NULL,NULL,'0'),('1414','350582','晋江市','155','J',2,NULL,NULL,NULL,NULL,'0'),('1415','350583','南安市','155','N',2,NULL,NULL,NULL,NULL,'0'),('1416','350602','芗城区','156','X',2,NULL,NULL,NULL,NULL,'0'),('1417','350603','龙文区','156','L',2,NULL,NULL,NULL,NULL,'0'),('1418','350622','云霄县','156','Y',2,NULL,NULL,NULL,NULL,'0'),('1419','350623','漳浦县','156','Z',2,NULL,NULL,NULL,NULL,'0'),('142','340800','安庆市','12','A',1,NULL,NULL,NULL,NULL,'0'),('1420','350624','诏安县','156','Z',2,NULL,NULL,NULL,NULL,'0'),('1421','350625','长泰县','156','C',2,NULL,NULL,NULL,NULL,'0'),('1422','350626','东山县','156','D',2,NULL,NULL,NULL,NULL,'0'),('1423','350627','南靖县','156','N',2,NULL,NULL,NULL,NULL,'0'),('1424','350628','平和县','156','P',2,NULL,NULL,NULL,NULL,'0'),('1425','350629','华安县','156','H',2,NULL,NULL,NULL,NULL,'0'),('1426','350681','龙海市','156','L',2,NULL,NULL,NULL,NULL,'0'),('1427','350702','延平区','157','Y',2,NULL,NULL,NULL,NULL,'0'),('1428','350721','顺昌县','157','S',2,NULL,NULL,NULL,NULL,'0'),('1429','350722','浦城县','157','P',2,NULL,NULL,NULL,NULL,'0'),('143','341000','黄山市','12','H',1,NULL,NULL,NULL,NULL,'0'),('1430','350723','光泽县','157','G',2,NULL,NULL,NULL,NULL,'0'),('1431','350724','松溪县','157','S',2,NULL,NULL,NULL,NULL,'0'),('1432','350725','政和县','157','Z',2,NULL,NULL,NULL,NULL,'0'),('1433','350781','邵武市','157','S',2,NULL,NULL,NULL,NULL,'0'),('1434','350782','武夷山市','157','W',2,NULL,NULL,NULL,NULL,'0'),('1435','350783','建瓯市','157','J',2,NULL,NULL,NULL,NULL,'0'),('1436','350784','建阳市','157','J',2,NULL,NULL,NULL,NULL,'0'),('1437','350802','新罗区','158','X',2,NULL,NULL,NULL,NULL,'0'),('1438','350821','长汀县','158','C',2,NULL,NULL,NULL,NULL,'0'),('1439','350822','永定县','158','Y',2,NULL,NULL,NULL,NULL,'0'),('144','341100','滁州市','12','C',1,NULL,NULL,NULL,NULL,'0'),('1440','350823','上杭县','158','S',2,NULL,NULL,NULL,NULL,'0'),('1441','350824','武平县','158','W',2,NULL,NULL,NULL,NULL,'0'),('1442','350825','连城县','158','L',2,NULL,NULL,NULL,NULL,'0'),('1443','350881','漳平市','158','Z',2,NULL,NULL,NULL,NULL,'0'),('1444','350902','蕉城区','159','J',2,NULL,NULL,NULL,NULL,'0'),('1445','350921','霞浦县','159','X',2,NULL,NULL,NULL,NULL,'0'),('1446','350922','古田县','159','G',2,NULL,NULL,NULL,NULL,'0'),('1447','350923','屏南县','159','P',2,NULL,NULL,NULL,NULL,'0'),('1448','350924','寿宁县','159','S',2,NULL,NULL,NULL,NULL,'0'),('1449','350925','周宁县','159','Z',2,NULL,NULL,NULL,NULL,'0'),('145','341200','阜阳市','12','F',1,NULL,NULL,NULL,NULL,'0'),('1450','350926','柘荣县','159','Z',2,NULL,NULL,NULL,NULL,'0'),('1451','350981','福安市','159','F',2,NULL,NULL,NULL,NULL,'0'),('1452','350982','福鼎市','159','F',2,NULL,NULL,NULL,NULL,'0'),('1453','360102','东湖区','160','D',2,NULL,NULL,NULL,NULL,'0'),('1454','360104','青云谱区','160','Q',2,NULL,NULL,NULL,NULL,'0'),('1455','360105','湾里区','160','W',2,NULL,NULL,NULL,NULL,'0'),('1456','360111','青山湖区','160','Q',2,NULL,NULL,NULL,NULL,'0'),('1457','360121','南昌县','160','N',2,NULL,NULL,NULL,NULL,'0'),('1458','360122','新建县','160','X',2,NULL,NULL,NULL,NULL,'0'),('1459','360123','安义县','160','A',2,NULL,NULL,NULL,NULL,'0'),('146','341300','宿州市','12','S',1,NULL,NULL,NULL,NULL,'0'),('1460','360124','进贤县','160','J',2,NULL,NULL,NULL,NULL,'0'),('1461','360202','昌江区','161','C',2,NULL,NULL,NULL,NULL,'0'),('1462','360203','珠山区','161','Z',2,NULL,NULL,NULL,NULL,'0'),('1463','360222','浮梁县','161','F',2,NULL,NULL,NULL,NULL,'0'),('1464','360281','乐平市','161','L',2,NULL,NULL,NULL,NULL,'0'),('1465','360302','安源区','162','A',2,NULL,NULL,NULL,NULL,'0'),('1466','360313','湘东区','162','X',2,NULL,NULL,NULL,NULL,'0'),('1467','360321','莲花县','162','L',2,NULL,NULL,NULL,NULL,'0'),('1468','360322','上栗县','162','S',2,NULL,NULL,NULL,NULL,'0'),('1469','360323','芦溪县','162','L',2,NULL,NULL,NULL,NULL,'0'),('147','341500','六安市','12','L',1,NULL,NULL,NULL,NULL,'0'),('1470','360402','庐山区','163','L',2,NULL,NULL,NULL,NULL,'0'),('1471','360403','浔阳区','163','X',2,NULL,NULL,NULL,NULL,'0'),('1472','360421','九江县','163','J',2,NULL,NULL,NULL,NULL,'0'),('1473','360423','武宁县','163','W',2,NULL,NULL,NULL,NULL,'0'),('1474','360424','修水县','163','X',2,NULL,NULL,NULL,NULL,'0'),('1475','360425','永修县','163','Y',2,NULL,NULL,NULL,NULL,'0'),('1476','360426','德安县','163','D',2,NULL,NULL,NULL,NULL,'0'),('1477','360427','星子县','163','X',2,NULL,NULL,NULL,NULL,'0'),('1478','360428','都昌县','163','D',2,NULL,NULL,NULL,NULL,'0'),('1479','360429','湖口县','163','H',2,NULL,NULL,NULL,NULL,'0'),('148','341600','亳州市','12','B',1,NULL,NULL,NULL,NULL,'0'),('1480','360430','彭泽县','163','P',2,NULL,NULL,NULL,NULL,'0'),('1481','360481','瑞昌市','163','R',2,NULL,NULL,NULL,NULL,'0'),('1482','360482','共青城市','163','G',2,NULL,NULL,NULL,NULL,'0'),('1483','360502','渝水区','164','Y',2,NULL,NULL,NULL,NULL,'0'),('1484','360521','分宜县','164','F',2,NULL,NULL,NULL,NULL,'0'),('1485','360602','月湖区','165','Y',2,NULL,NULL,NULL,NULL,'0'),('1486','360622','余江县','165','Y',2,NULL,NULL,NULL,NULL,'0'),('1487','360681','贵溪市','165','G',2,NULL,NULL,NULL,NULL,'0'),('1488','360702','章贡区','166','Z',2,NULL,NULL,NULL,NULL,'0'),('1489','360703','南康区','166','N',2,NULL,NULL,NULL,NULL,'0'),('149','341700','池州市','12','C',1,NULL,NULL,NULL,NULL,'0'),('1490','360721','赣县','166','G',2,NULL,NULL,NULL,NULL,'0'),('1491','360722','信丰县','166','X',2,NULL,NULL,NULL,NULL,'0'),('1492','360723','大余县','166','D',2,NULL,NULL,NULL,NULL,'0'),('1493','360724','上犹县','166','S',2,NULL,NULL,NULL,NULL,'0'),('1494','360725','崇义县','166','C',2,NULL,NULL,NULL,NULL,'0'),('1495','360726','安远县','166','A',2,NULL,NULL,NULL,NULL,'0'),('1496','360727','龙南县','166','L',2,NULL,NULL,NULL,NULL,'0'),('1497','360728','定南县','166','D',2,NULL,NULL,NULL,NULL,'0'),('1498','360729','全南县','166','Q',2,NULL,NULL,NULL,NULL,'0'),('1499','360730','宁都县','166','N',2,NULL,NULL,NULL,NULL,'0'),('15','370000','山东省','0','S',0,NULL,NULL,NULL,NULL,'0'),('150','341800','宣城市','12','X',1,NULL,NULL,NULL,NULL,'0'),('1500','360731','于都县','166','Y',2,NULL,NULL,NULL,NULL,'0'),('1501','360732','兴国县','166','X',2,NULL,NULL,NULL,NULL,'0'),('1502','360733','会昌县','166','H',2,NULL,NULL,NULL,NULL,'0'),('1503','360734','寻乌县','166','X',2,NULL,NULL,NULL,NULL,'0'),('1504','360735','石城县','166','S',2,NULL,NULL,NULL,NULL,'0'),('1505','360781','瑞金市','166','R',2,NULL,NULL,NULL,NULL,'0'),('1506','360802','吉州区','167','J',2,NULL,NULL,NULL,NULL,'0'),('1507','360803','青原区','167','Q',2,NULL,NULL,NULL,NULL,'0'),('1508','360821','吉安县','167','J',2,NULL,NULL,NULL,NULL,'0'),('1509','360822','吉水县','167','J',2,NULL,NULL,NULL,NULL,'0'),('151','350100','福州市','13','F',1,NULL,NULL,NULL,NULL,'0'),('1510','360823','峡江县','167','X',2,NULL,NULL,NULL,NULL,'0'),('1511','360824','新干县','167','X',2,NULL,NULL,NULL,NULL,'0'),('1512','360825','永丰县','167','Y',2,NULL,NULL,NULL,NULL,'0'),('1513','360826','泰和县','167','T',2,NULL,NULL,NULL,NULL,'0'),('1514','360827','遂川县','167','S',2,NULL,NULL,NULL,NULL,'0'),('1515','360828','万安县','167','W',2,NULL,NULL,NULL,NULL,'0'),('1516','360829','安福县','167','A',2,NULL,NULL,NULL,NULL,'0'),('1517','360830','永新县','167','Y',2,NULL,NULL,NULL,NULL,'0'),('1518','360881','井冈山市','167','J',2,NULL,NULL,NULL,NULL,'0'),('1519','360902','袁州区','168','Y',2,NULL,NULL,NULL,NULL,'0'),('152','350200','厦门市','13','X',1,NULL,NULL,NULL,NULL,'0'),('1520','360921','奉新县','168','F',2,NULL,NULL,NULL,NULL,'0'),('1521','360922','万载县','168','W',2,NULL,NULL,NULL,NULL,'0'),('1522','360923','上高县','168','S',2,NULL,NULL,NULL,NULL,'0'),('1523','360924','宜丰县','168','Y',2,NULL,NULL,NULL,NULL,'0'),('1524','360925','靖安县','168','J',2,NULL,NULL,NULL,NULL,'0'),('1525','360926','铜鼓县','168','T',2,NULL,NULL,NULL,NULL,'0'),('1526','360981','丰城市','168','F',2,NULL,NULL,NULL,NULL,'0'),('1527','360982','樟树市','168','Z',2,NULL,NULL,NULL,NULL,'0'),('1528','360983','高安市','168','G',2,NULL,NULL,NULL,NULL,'0'),('1529','361002','临川区','169','L',2,NULL,NULL,NULL,NULL,'0'),('153','350300','莆田市','13','P',1,NULL,NULL,NULL,NULL,'0'),('1530','361021','南城县','169','N',2,NULL,NULL,NULL,NULL,'0'),('1531','361022','黎川县','169','L',2,NULL,NULL,NULL,NULL,'0'),('1532','361023','南丰县','169','N',2,NULL,NULL,NULL,NULL,'0'),('1533','361024','崇仁县','169','C',2,NULL,NULL,NULL,NULL,'0'),('1534','361025','乐安县','169','L',2,NULL,NULL,NULL,NULL,'0'),('1535','361026','宜黄县','169','Y',2,NULL,NULL,NULL,NULL,'0'),('1536','361027','金溪县','169','J',2,NULL,NULL,NULL,NULL,'0'),('1537','361028','资溪县','169','Z',2,NULL,NULL,NULL,NULL,'0'),('1538','361029','东乡县','169','D',2,NULL,NULL,NULL,NULL,'0'),('1539','361030','广昌县','169','G',2,NULL,NULL,NULL,NULL,'0'),('154','350400','三明市','13','S',1,NULL,NULL,NULL,NULL,'0'),('1540','361102','信州区','170','X',2,NULL,NULL,NULL,NULL,'0'),('1541','361121','上饶县','170','S',2,NULL,NULL,NULL,NULL,'0'),('1542','361122','广丰县','170','G',2,NULL,NULL,NULL,NULL,'0'),('1543','361123','玉山县','170','Y',2,NULL,NULL,NULL,NULL,'0'),('1544','361124','铅山县','170','Q',2,NULL,NULL,NULL,NULL,'0'),('1545','361125','横峰县','170','H',2,NULL,NULL,NULL,NULL,'0'),('1546','361126','弋阳县','170','Y',2,NULL,NULL,NULL,NULL,'0'),('1547','361127','余干县','170','Y',2,NULL,NULL,NULL,NULL,'0'),('1548','361128','鄱阳县','170','P',2,NULL,NULL,NULL,NULL,'0'),('1549','361129','万年县','170','W',2,NULL,NULL,NULL,NULL,'0'),('155','350500','泉州市','13','Q',1,NULL,NULL,NULL,NULL,'0'),('1550','361130','婺源县','170','W',2,NULL,NULL,NULL,NULL,'0'),('1551','361181','德兴市','170','D',2,NULL,NULL,NULL,NULL,'0'),('1552','370102','历下区','171','L',2,NULL,NULL,NULL,NULL,'0'),('1553','370103','市中区','171','S',2,NULL,NULL,NULL,NULL,'0'),('1554','370104','槐荫区','171','H',2,NULL,NULL,NULL,NULL,'0'),('1555','370105','天桥区','171','T',2,NULL,NULL,NULL,NULL,'0'),('1556','370112','历城区','171','L',2,NULL,NULL,NULL,NULL,'0'),('1557','370113','长清区','171','C',2,NULL,NULL,NULL,NULL,'0'),('1558','370124','平阴县','171','P',2,NULL,NULL,NULL,NULL,'0'),('1559','370125','济阳县','171','J',2,NULL,NULL,NULL,NULL,'0'),('156','350600','漳州市','13','Z',1,NULL,NULL,NULL,NULL,'0'),('1560','370126','商河县','171','S',2,NULL,NULL,NULL,NULL,'0'),('1561','370181','章丘市','171','Z',2,NULL,NULL,NULL,NULL,'0'),('1562','370202','市南区','172','S',2,NULL,NULL,NULL,NULL,'0'),('1563','370203','市北区','172','S',2,NULL,NULL,NULL,NULL,'0'),('1564','370211','黄岛区','172','H',2,NULL,NULL,NULL,NULL,'0'),('1565','370212','崂山区','172','L',2,NULL,NULL,NULL,NULL,'0'),('1566','370213','李沧区','172','L',2,NULL,NULL,NULL,NULL,'0'),('1567','370214','城阳区','172','C',2,NULL,NULL,NULL,NULL,'0'),('1568','370281','胶州市','172','J',2,NULL,NULL,NULL,NULL,'0'),('1569','370282','即墨市','172','J',2,NULL,NULL,NULL,NULL,'0'),('157','350700','南平市','13','N',1,NULL,NULL,NULL,NULL,'0'),('1570','370283','平度市','172','P',2,NULL,NULL,NULL,NULL,'0'),('1571','370285','莱西市','172','L',2,NULL,NULL,NULL,NULL,'0'),('1572','370302','淄川区','173','Z',2,NULL,NULL,NULL,NULL,'0'),('1573','370303','张店区','173','Z',2,NULL,NULL,NULL,NULL,'0'),('1574','370304','博山区','173','B',2,NULL,NULL,NULL,NULL,'0'),('1575','370305','临淄区','173','L',2,NULL,NULL,NULL,NULL,'0'),('1576','370306','周村区','173','Z',2,NULL,NULL,NULL,NULL,'0'),('1577','370321','桓台县','173','H',2,NULL,NULL,NULL,NULL,'0'),('1578','370322','高青县','173','G',2,NULL,NULL,NULL,NULL,'0'),('1579','370323','沂源县','173','Y',2,NULL,NULL,NULL,NULL,'0'),('158','350800','龙岩市','13','L',1,NULL,NULL,NULL,NULL,'0'),('1580','370403','薛城区','174','X',2,NULL,NULL,NULL,NULL,'0'),('1581','370404','峄城区','174','Y',2,NULL,NULL,NULL,NULL,'0'),('1582','370405','台儿庄区','174','T',2,NULL,NULL,NULL,NULL,'0'),('1583','370406','山亭区','174','S',2,NULL,NULL,NULL,NULL,'0'),('1584','370481','滕州市','174','T',2,NULL,NULL,NULL,NULL,'0'),('1585','370502','东营区','175','D',2,NULL,NULL,NULL,NULL,'0'),('1586','370503','河口区','175','H',2,NULL,NULL,NULL,NULL,'0'),('1587','370521','垦利县','175','K',2,NULL,NULL,NULL,NULL,'0'),('1588','370522','利津县','175','L',2,NULL,NULL,NULL,NULL,'0'),('1589','370523','广饶县','175','G',2,NULL,NULL,NULL,NULL,'0'),('159','350900','宁德市','13','N',1,NULL,NULL,NULL,NULL,'0'),('1590','370602','芝罘区','176','Z',2,NULL,NULL,NULL,NULL,'0'),('1591','370611','福山区','176','F',2,NULL,NULL,NULL,NULL,'0'),('1592','370612','牟平区','176','M',2,NULL,NULL,NULL,NULL,'0'),('1593','370613','莱山区','176','L',2,NULL,NULL,NULL,NULL,'0'),('1594','370634','长岛县','176','C',2,NULL,NULL,NULL,NULL,'0'),('1595','370681','龙口市','176','L',2,NULL,NULL,NULL,NULL,'0'),('1596','370682','莱阳市','176','L',2,NULL,NULL,NULL,NULL,'0'),('1597','370683','莱州市','176','L',2,NULL,NULL,NULL,NULL,'0'),('1598','370684','蓬莱市','176','P',2,NULL,NULL,NULL,NULL,'0'),('1599','370685','招远市','176','Z',2,NULL,NULL,NULL,NULL,'0'),('16','410000','河南省','0','H',0,NULL,NULL,NULL,NULL,'0'),('160','360100','南昌市','14','N',1,NULL,NULL,NULL,NULL,'0'),('1600','370686','栖霞市','176','Q',2,NULL,NULL,NULL,NULL,'0'),('1601','370687','海阳市','176','H',2,NULL,NULL,NULL,NULL,'0'),('1602','370702','潍城区','177','W',2,NULL,NULL,NULL,NULL,'0'),('1603','370703','寒亭区','177','H',2,NULL,NULL,NULL,NULL,'0'),('1604','370704','坊子区','177','F',2,NULL,NULL,NULL,NULL,'0'),('1605','370705','奎文区','177','K',2,NULL,NULL,NULL,NULL,'0'),('1606','370724','临朐县','177','L',2,NULL,NULL,NULL,NULL,'0'),('1607','370725','昌乐县','177','C',2,NULL,NULL,NULL,NULL,'0'),('1608','370781','青州市','177','Q',2,NULL,NULL,NULL,NULL,'0'),('1609','370782','诸城市','177','Z',2,NULL,NULL,NULL,NULL,'0'),('161','360200','景德镇市','14','J',1,NULL,NULL,NULL,NULL,'0'),('1610','370783','寿光市','177','S',2,NULL,NULL,NULL,NULL,'0'),('1611','370784','安丘市','177','A',2,NULL,NULL,NULL,NULL,'0'),('1612','370785','高密市','177','G',2,NULL,NULL,NULL,NULL,'0'),('1613','370786','昌邑市','177','C',2,NULL,NULL,NULL,NULL,'0'),('1614','370811','任城区','178','R',2,NULL,NULL,NULL,NULL,'0'),('1615','370812','兖州区','178','Y',2,NULL,NULL,NULL,NULL,'0'),('1616','370826','微山县','178','W',2,NULL,NULL,NULL,NULL,'0'),('1617','370827','鱼台县','178','Y',2,NULL,NULL,NULL,NULL,'0'),('1618','370828','金乡县','178','J',2,NULL,NULL,NULL,NULL,'0'),('1619','370829','嘉祥县','178','J',2,NULL,NULL,NULL,NULL,'0'),('162','360300','萍乡市','14','P',1,NULL,NULL,NULL,NULL,'0'),('1620','370830','汶上县','178','W',2,NULL,NULL,NULL,NULL,'0'),('1621','370831','泗水县','178','S',2,NULL,NULL,NULL,NULL,'0'),('1622','370832','梁山县','178','L',2,NULL,NULL,NULL,NULL,'0'),('1623','370881','曲阜市','178','Q',2,NULL,NULL,NULL,NULL,'0'),('1624','370883','邹城市','178','Z',2,NULL,NULL,NULL,NULL,'0'),('1625','370902','泰山区','179','T',2,NULL,NULL,NULL,NULL,'0'),('1626','370911','岱岳区','179','D',2,NULL,NULL,NULL,NULL,'0'),('1627','370921','宁阳县','179','N',2,NULL,NULL,NULL,NULL,'0'),('1628','370923','东平县','179','D',2,NULL,NULL,NULL,NULL,'0'),('1629','370982','新泰市','179','X',2,NULL,NULL,NULL,NULL,'0'),('163','360400','九江市','14','J',1,NULL,NULL,NULL,NULL,'0'),('1630','370983','肥城市','179','F',2,NULL,NULL,NULL,NULL,'0'),('1631','371002','环翠区','180','H',2,NULL,NULL,NULL,NULL,'0'),('1632','371003','文登区','180','W',2,NULL,NULL,NULL,NULL,'0'),('1633','371082','荣成市','180','R',2,NULL,NULL,NULL,NULL,'0'),('1634','371083','乳山市','180','R',2,NULL,NULL,NULL,NULL,'0'),('1635','371102','东港区','181','D',2,NULL,NULL,NULL,NULL,'0'),('1636','371103','岚山区','181','L',2,NULL,NULL,NULL,NULL,'0'),('1637','371121','五莲县','181','W',2,NULL,NULL,NULL,NULL,'0'),('1638','371122','莒县','181','J',2,NULL,NULL,NULL,NULL,'0'),('1639','371202','莱城区','182','L',2,NULL,NULL,NULL,NULL,'0'),('164','360500','新余市','14','X',1,NULL,NULL,NULL,NULL,'0'),('1640','371203','钢城区','182','G',2,NULL,NULL,NULL,NULL,'0'),('1641','371302','兰山区','183','L',2,NULL,NULL,NULL,NULL,'0'),('1642','371311','罗庄区','183','L',2,NULL,NULL,NULL,NULL,'0'),('1643','371321','沂南县','183','Y',2,NULL,NULL,NULL,NULL,'0'),('1644','371322','郯城县','183','T',2,NULL,NULL,NULL,NULL,'0'),('1645','371323','沂水县','183','Y',2,NULL,NULL,NULL,NULL,'0'),('1646','371324','兰陵县','183','L',2,NULL,NULL,NULL,NULL,'0'),('1647','371325','费县','183','F',2,NULL,NULL,NULL,NULL,'0'),('1648','371326','平邑县','183','P',2,NULL,NULL,NULL,NULL,'0'),('1649','371327','莒南县','183','J',2,NULL,NULL,NULL,NULL,'0'),('165','360600','鹰潭市','14','Y',1,NULL,NULL,NULL,NULL,'0'),('1650','371328','蒙阴县','183','M',2,NULL,NULL,NULL,NULL,'0'),('1651','371329','临沭县','183','L',2,NULL,NULL,NULL,NULL,'0'),('1652','371402','德城区','184','D',2,NULL,NULL,NULL,NULL,'0'),('1653','371403','陵城区','184','L',2,NULL,NULL,NULL,NULL,'0'),('1654','371422','宁津县','184','N',2,NULL,NULL,NULL,NULL,'0'),('1655','371423','庆云县','184','Q',2,NULL,NULL,NULL,NULL,'0'),('1656','371424','临邑县','184','L',2,NULL,NULL,NULL,NULL,'0'),('1657','371425','齐河县','184','Q',2,NULL,NULL,NULL,NULL,'0'),('1658','371426','平原县','184','P',2,NULL,NULL,NULL,NULL,'0'),('1659','371427','夏津县','184','X',2,NULL,NULL,NULL,NULL,'0'),('166','360700','赣州市','14','G',1,NULL,NULL,NULL,NULL,'0'),('1660','371428','武城县','184','W',2,NULL,NULL,NULL,NULL,'0'),('1661','371481','乐陵市','184','L',2,NULL,NULL,NULL,NULL,'0'),('1662','371482','禹城市','184','Y',2,NULL,NULL,NULL,NULL,'0'),('1663','371502','东昌府区','185','D',2,NULL,NULL,NULL,NULL,'0'),('1664','371521','阳谷县','185','Y',2,NULL,NULL,NULL,NULL,'0'),('1665','371522','莘县','185','X',2,NULL,NULL,NULL,NULL,'0'),('1666','371523','茌平县','185','C',2,NULL,NULL,NULL,NULL,'0'),('1667','371524','东阿县','185','D',2,NULL,NULL,NULL,NULL,'0'),('1668','371525','冠县','185','G',2,NULL,NULL,NULL,NULL,'0'),('1669','371526','高唐县','185','G',2,NULL,NULL,NULL,NULL,'0'),('167','360800','吉安市','14','J',1,NULL,NULL,NULL,NULL,'0'),('1670','371581','临清市','185','L',2,NULL,NULL,NULL,NULL,'0'),('1671','371602','滨城区','186','B',2,NULL,NULL,NULL,NULL,'0'),('1672','371603','沾化区','186','Z',2,NULL,NULL,NULL,NULL,'0'),('1673','371621','惠民县','186','H',2,NULL,NULL,NULL,NULL,'0'),('1674','371622','阳信县','186','Y',2,NULL,NULL,NULL,NULL,'0'),('1675','371623','无棣县','186','W',2,NULL,NULL,NULL,NULL,'0'),('1676','371625','博兴县','186','B',2,NULL,NULL,NULL,NULL,'0'),('1677','371626','邹平县','186','Z',2,NULL,NULL,NULL,NULL,'0'),('1678','371702','牡丹区','187','M',2,NULL,NULL,NULL,NULL,'0'),('1679','371721','曹县','187','C',2,NULL,NULL,NULL,NULL,'0'),('168','360900','宜春市','14','Y',1,NULL,NULL,NULL,NULL,'0'),('1680','371722','单县','187','D',2,NULL,NULL,NULL,NULL,'0'),('1681','371723','成武县','187','C',2,NULL,NULL,NULL,NULL,'0'),('1682','371724','巨野县','187','J',2,NULL,NULL,NULL,NULL,'0'),('1683','371725','郓城县','187','Y',2,NULL,NULL,NULL,NULL,'0'),('1684','371726','鄄城县','187','J',2,NULL,NULL,NULL,NULL,'0'),('1685','371727','定陶县','187','D',2,NULL,NULL,NULL,NULL,'0'),('1686','371728','东明县','187','D',2,NULL,NULL,NULL,NULL,'0'),('1687','410102','中原区','188','Z',2,NULL,NULL,NULL,NULL,'0'),('1688','410103','二七区','188','E',2,NULL,NULL,NULL,NULL,'0'),('1689','410104','管城回族区','188','G',2,NULL,NULL,NULL,NULL,'0'),('169','361000','抚州市','14','F',1,NULL,NULL,NULL,NULL,'0'),('1690','410105','金水区','188','J',2,NULL,NULL,NULL,NULL,'0'),('1691','410106','上街区','188','S',2,NULL,NULL,NULL,NULL,'0'),('1692','410108','惠济区','188','H',2,NULL,NULL,NULL,NULL,'0'),('1693','410122','中牟县','188','Z',2,NULL,NULL,NULL,NULL,'0'),('1694','410181','巩义市','188','G',2,NULL,NULL,NULL,NULL,'0'),('1695','410182','荥阳市','188','Y',2,NULL,NULL,NULL,NULL,'0'),('1696','410183','新密市','188','X',2,NULL,NULL,NULL,NULL,'0'),('1697','410184','新郑市','188','X',2,NULL,NULL,NULL,NULL,'0'),('1698','410185','登封市','188','D',2,NULL,NULL,NULL,NULL,'0'),('1699','410202','龙亭区','189','L',2,NULL,NULL,NULL,NULL,'0'),('17','420000','湖北省','0','H',0,NULL,NULL,NULL,NULL,'0'),('170','361100','上饶市','14','S',1,NULL,NULL,NULL,NULL,'0'),('1700','410203','顺河回族区','189','S',2,NULL,NULL,NULL,NULL,'0'),('1701','410205','禹王台区','189','Y',2,NULL,NULL,NULL,NULL,'0'),('1702','410211','金明区','189','J',2,NULL,NULL,NULL,NULL,'0'),('1703','410221','杞县','189','Q',2,NULL,NULL,NULL,NULL,'0'),('1704','410222','通许县','189','T',2,NULL,NULL,NULL,NULL,'0'),('1705','410223','尉氏县','189','W',2,NULL,NULL,NULL,NULL,'0'),('1706','410224','开封县','189','K',2,NULL,NULL,NULL,NULL,'0'),('1707','410225','兰考县','189','L',2,NULL,NULL,NULL,NULL,'0'),('1708','410302','老城区','190','L',2,NULL,NULL,NULL,NULL,'0'),('1709','410303','西工区','190','X',2,NULL,NULL,NULL,NULL,'0'),('171','370100','济南市','15','J',1,NULL,NULL,NULL,NULL,'0'),('1710','410304','瀍河回族区','190','C',2,NULL,NULL,NULL,NULL,'0'),('1711','410305','涧西区','190','J',2,NULL,NULL,NULL,NULL,'0'),('1712','410306','吉利区','190','J',2,NULL,NULL,NULL,NULL,'0'),('1713','410311','洛龙区','190','L',2,NULL,NULL,NULL,NULL,'0'),('1714','410322','孟津县','190','M',2,NULL,NULL,NULL,NULL,'0'),('1715','410323','新安县','190','X',2,NULL,NULL,NULL,NULL,'0'),('1716','410324','栾川县','190','L',2,NULL,NULL,NULL,NULL,'0'),('1717','410325','嵩县','190','S',2,NULL,NULL,NULL,NULL,'0'),('1718','410326','汝阳县','190','R',2,NULL,NULL,NULL,NULL,'0'),('1719','410327','宜阳县','190','Y',2,NULL,NULL,NULL,NULL,'0'),('172','370200','青岛市','15','Q',1,NULL,NULL,NULL,NULL,'0'),('1720','410328','洛宁县','190','L',2,NULL,NULL,NULL,NULL,'0'),('1721','410329','伊川县','190','Y',2,NULL,NULL,NULL,NULL,'0'),('1722','410381','偃师市','190','Y',2,NULL,NULL,NULL,NULL,'0'),('1723','410403','卫东区','191','W',2,NULL,NULL,NULL,NULL,'0'),('1724','410404','石龙区','191','S',2,NULL,NULL,NULL,NULL,'0'),('1725','410411','湛河区','191','Z',2,NULL,NULL,NULL,NULL,'0'),('1726','410421','宝丰县','191','B',2,NULL,NULL,NULL,NULL,'0'),('1727','410422','叶县','191','Y',2,NULL,NULL,NULL,NULL,'0'),('1728','410423','鲁山县','191','L',2,NULL,NULL,NULL,NULL,'0'),('1729','410425','郏县','191','J',2,NULL,NULL,NULL,NULL,'0'),('173','370300','淄博市','15','Z',1,NULL,NULL,NULL,NULL,'0'),('1730','410481','舞钢市','191','W',2,NULL,NULL,NULL,NULL,'0'),('1731','410482','汝州市','191','R',2,NULL,NULL,NULL,NULL,'0'),('1732','410502','文峰区','192','W',2,NULL,NULL,NULL,NULL,'0'),('1733','410503','北关区','192','B',2,NULL,NULL,NULL,NULL,'0'),('1734','410505','殷都区','192','Y',2,NULL,NULL,NULL,NULL,'0'),('1735','410506','龙安区','192','L',2,NULL,NULL,NULL,NULL,'0'),('1736','410522','安阳县','192','A',2,NULL,NULL,NULL,NULL,'0'),('1737','410523','汤阴县','192','T',2,NULL,NULL,NULL,NULL,'0'),('1738','410526','滑县','192','H',2,NULL,NULL,NULL,NULL,'0'),('1739','410527','内黄县','192','N',2,NULL,NULL,NULL,NULL,'0'),('174','370400','枣庄市','15','Z',1,NULL,NULL,NULL,NULL,'0'),('1740','410581','林州市','192','L',2,NULL,NULL,NULL,NULL,'0'),('1741','410602','鹤山区','193','H',2,NULL,NULL,NULL,NULL,'0'),('1742','410603','山城区','193','S',2,NULL,NULL,NULL,NULL,'0'),('1743','410611','淇滨区','193','Q',2,NULL,NULL,NULL,NULL,'0'),('1744','410621','浚县','193','J',2,NULL,NULL,NULL,NULL,'0'),('1745','410622','淇县','193','Q',2,NULL,NULL,NULL,NULL,'0'),('1746','410702','红旗区','194','H',2,NULL,NULL,NULL,NULL,'0'),('1747','410703','卫滨区','194','W',2,NULL,NULL,NULL,NULL,'0'),('1748','410704','凤泉区','194','F',2,NULL,NULL,NULL,NULL,'0'),('1749','410711','牧野区','194','M',2,NULL,NULL,NULL,NULL,'0'),('175','370500','东营市','15','D',1,NULL,NULL,NULL,NULL,'0'),('1750','410721','新乡县','194','X',2,NULL,NULL,NULL,NULL,'0'),('1751','410724','获嘉县','194','H',2,NULL,NULL,NULL,NULL,'0'),('1752','410725','原阳县','194','Y',2,NULL,NULL,NULL,NULL,'0'),('1753','410726','延津县','194','Y',2,NULL,NULL,NULL,NULL,'0'),('1754','410727','封丘县','194','F',2,NULL,NULL,NULL,NULL,'0'),('1755','410728','长垣县','194','C',2,NULL,NULL,NULL,NULL,'0'),('1756','410781','卫辉市','194','W',2,NULL,NULL,NULL,NULL,'0'),('1757','410782','辉县市','194','H',2,NULL,NULL,NULL,NULL,'0'),('1758','410802','解放区','195','J',2,NULL,NULL,NULL,NULL,'0'),('1759','410803','中站区','195','Z',2,NULL,NULL,NULL,NULL,'0'),('176','370600','烟台市','15','Y',1,NULL,NULL,NULL,NULL,'0'),('1760','410804','马村区','195','M',2,NULL,NULL,NULL,NULL,'0'),('1761','410811','山阳区','195','S',2,NULL,NULL,NULL,NULL,'0'),('1762','410821','修武县','195','X',2,NULL,NULL,NULL,NULL,'0'),('1763','410822','博爱县','195','B',2,NULL,NULL,NULL,NULL,'0'),('1764','410823','武陟县','195','W',2,NULL,NULL,NULL,NULL,'0'),('1765','410825','温县','195','W',2,NULL,NULL,NULL,NULL,'0'),('1766','410882','沁阳市','195','Q',2,NULL,NULL,NULL,NULL,'0'),('1767','410883','孟州市','195','M',2,NULL,NULL,NULL,NULL,'0'),('1768','410902','华龙区','196','H',2,NULL,NULL,NULL,NULL,'0'),('1769','410922','清丰县','196','Q',2,NULL,NULL,NULL,NULL,'0'),('177','370700','潍坊市','15','W',1,NULL,NULL,NULL,NULL,'0'),('1770','410923','南乐县','196','N',2,NULL,NULL,NULL,NULL,'0'),('1771','410926','范县','196','F',2,NULL,NULL,NULL,NULL,'0'),('1772','410927','台前县','196','T',2,NULL,NULL,NULL,NULL,'0'),('1773','410928','濮阳县','196','P',2,NULL,NULL,NULL,NULL,'0'),('1774','411002','魏都区','197','W',2,NULL,NULL,NULL,NULL,'0'),('1775','411023','许昌县','197','X',2,NULL,NULL,NULL,NULL,'0'),('1776','411024','鄢陵县','197','Y',2,NULL,NULL,NULL,NULL,'0'),('1777','411025','襄城县','197','X',2,NULL,NULL,NULL,NULL,'0'),('1778','411081','禹州市','197','Y',2,NULL,NULL,NULL,NULL,'0'),('1779','411082','长葛市','197','C',2,NULL,NULL,NULL,NULL,'0'),('178','370800','济宁市','15','J',1,NULL,NULL,NULL,NULL,'0'),('1781','411102','源汇区','198','Y',2,NULL,NULL,NULL,NULL,'0'),('1782','411103','郾城区','198','Y',2,NULL,NULL,NULL,NULL,'0'),('1783','411104','召陵区','198','Z',2,NULL,NULL,NULL,NULL,'0'),('1784','411121','舞阳县','198','W',2,NULL,NULL,NULL,NULL,'0'),('1785','411122','临颍县','198','L',2,NULL,NULL,NULL,NULL,'0'),('1786','411202','湖滨区','199','H',2,NULL,NULL,NULL,NULL,'0'),('1787','411221','渑池县','199','S',2,NULL,NULL,NULL,NULL,'0'),('1788','411222','陕县','199','S',2,NULL,NULL,NULL,NULL,'0'),('1789','411224','卢氏县','199','L',2,NULL,NULL,NULL,NULL,'0'),('179','370900','泰安市','15','T',1,NULL,NULL,NULL,NULL,'0'),('1790','411281','义马市','199','Y',2,NULL,NULL,NULL,NULL,'0'),('1791','411282','灵宝市','199','L',2,NULL,NULL,NULL,NULL,'0'),('1792','411302','宛城区','200','W',2,NULL,NULL,NULL,NULL,'0'),('1793','411303','卧龙区','200','W',2,NULL,NULL,NULL,NULL,'0'),('1794','411321','南召县','200','N',2,NULL,NULL,NULL,NULL,'0'),('1795','411322','方城县','200','F',2,NULL,NULL,NULL,NULL,'0'),('1796','411323','西峡县','200','X',2,NULL,NULL,NULL,NULL,'0'),('1797','411324','镇平县','200','Z',2,NULL,NULL,NULL,NULL,'0'),('1798','411325','内乡县','200','N',2,NULL,NULL,NULL,NULL,'0'),('1799','411326','淅川县','200','X',2,NULL,NULL,NULL,NULL,'0'),('18','430000','湖南省','0','H',0,NULL,NULL,NULL,NULL,'0'),('180','371000','威海市','15','W',1,NULL,NULL,NULL,NULL,'0'),('1800','411327','社旗县','200','S',2,NULL,NULL,NULL,NULL,'0'),('1801','411328','唐河县','200','T',2,NULL,NULL,NULL,NULL,'0'),('1802','411329','新野县','200','X',2,NULL,NULL,NULL,NULL,'0'),('1803','411330','桐柏县','200','T',2,NULL,NULL,NULL,NULL,'0'),('1804','411381','邓州市','200','D',2,NULL,NULL,NULL,NULL,'0'),('1805','411402','梁园区','201','L',2,NULL,NULL,NULL,NULL,'0'),('1806','411403','睢阳区','201','S',2,NULL,NULL,NULL,NULL,'0'),('1807','411421','民权县','201','M',2,NULL,NULL,NULL,NULL,'0'),('1808','411422','睢县','201','S',2,NULL,NULL,NULL,NULL,'0'),('1809','411423','宁陵县','201','N',2,NULL,NULL,NULL,NULL,'0'),('181','371100','日照市','15','R',1,NULL,NULL,NULL,NULL,'0'),('1810','411424','柘城县','201','Z',2,NULL,NULL,NULL,NULL,'0'),('1811','411425','虞城县','201','Y',2,NULL,NULL,NULL,NULL,'0'),('1812','411426','夏邑县','201','X',2,NULL,NULL,NULL,NULL,'0'),('1813','411481','永城市','201','Y',2,NULL,NULL,NULL,NULL,'0'),('1814','411502','浉河区','202','S',2,NULL,NULL,NULL,NULL,'0'),('1815','411503','平桥区','202','P',2,NULL,NULL,NULL,NULL,'0'),('1816','411521','罗山县','202','L',2,NULL,NULL,NULL,NULL,'0'),('1817','411522','光山县','202','G',2,NULL,NULL,NULL,NULL,'0'),('1818','411523','新县','202','X',2,NULL,NULL,NULL,NULL,'0'),('1819','411524','商城县','202','S',2,NULL,NULL,NULL,NULL,'0'),('182','371200','莱芜市','15','L',1,NULL,NULL,NULL,NULL,'0'),('1820','411525','固始县','202','G',2,NULL,NULL,NULL,NULL,'0'),('1821','411526','潢川县','202','H',2,NULL,NULL,NULL,NULL,'0'),('1822','411527','淮滨县','202','H',2,NULL,NULL,NULL,NULL,'0'),('1823','411528','息县','202','X',2,NULL,NULL,NULL,NULL,'0'),('1824','411602','川汇区','203','C',2,NULL,NULL,NULL,NULL,'0'),('1825','411621','扶沟县','203','F',2,NULL,NULL,NULL,NULL,'0'),('1826','411622','西华县','203','X',2,NULL,NULL,NULL,NULL,'0'),('1827','411623','商水县','203','S',2,NULL,NULL,NULL,NULL,'0'),('1828','411624','沈丘县','203','S',2,NULL,NULL,NULL,NULL,'0'),('1829','411625','郸城县','203','D',2,NULL,NULL,NULL,NULL,'0'),('183','371300','临沂市','15','L',1,NULL,NULL,NULL,NULL,'0'),('1830','411626','淮阳县','203','H',2,NULL,NULL,NULL,NULL,'0'),('1831','411627','太康县','203','T',2,NULL,NULL,NULL,NULL,'0'),('1832','411628','鹿邑县','203','L',2,NULL,NULL,NULL,NULL,'0'),('1833','411681','项城市','203','X',2,NULL,NULL,NULL,NULL,'0'),('1834','411702','驿城区','204','Y',2,NULL,NULL,NULL,NULL,'0'),('1835','411721','西平县','204','X',2,NULL,NULL,NULL,NULL,'0'),('1836','411722','上蔡县','204','S',2,NULL,NULL,NULL,NULL,'0'),('1837','411723','平舆县','204','P',2,NULL,NULL,NULL,NULL,'0'),('1838','411724','正阳县','204','Z',2,NULL,NULL,NULL,NULL,'0'),('1839','411725','确山县','204','Q',2,NULL,NULL,NULL,NULL,'0'),('184','371400','德州市','15','D',1,NULL,NULL,NULL,NULL,'0'),('1840','411726','泌阳县','204','M',2,NULL,NULL,NULL,NULL,'0'),('1841','411727','汝南县','204','R',2,NULL,NULL,NULL,NULL,'0'),('1842','411728','遂平县','204','S',2,NULL,NULL,NULL,NULL,'0'),('1843','411729','新蔡县','204','X',2,NULL,NULL,NULL,NULL,'0'),('1844','419001','济源市','205','J',2,NULL,NULL,NULL,NULL,'0'),('1845','420102','江岸区','206','J',2,NULL,NULL,NULL,NULL,'0'),('1846','420103','江汉区','206','J',2,NULL,NULL,NULL,NULL,'0'),('1847','420104','硚口区','206','Q',2,NULL,NULL,NULL,NULL,'0'),('1848','420105','汉阳区','206','H',2,NULL,NULL,NULL,NULL,'0'),('1849','420106','武昌区','206','W',2,NULL,NULL,NULL,NULL,'0'),('185','371500','聊城市','15','L',1,NULL,NULL,NULL,NULL,'0'),('1850','420111','洪山区','206','H',2,NULL,NULL,NULL,NULL,'0'),('1851','420112','东西湖区','206','D',2,NULL,NULL,NULL,NULL,'0'),('1852','420113','汉南区','206','H',2,NULL,NULL,NULL,NULL,'0'),('1853','420114','蔡甸区','206','C',2,NULL,NULL,NULL,NULL,'0'),('1854','420115','江夏区','206','J',2,NULL,NULL,NULL,NULL,'0'),('1855','420116','黄陂区','206','H',2,NULL,NULL,NULL,NULL,'0'),('1856','420117','新洲区','206','X',2,NULL,NULL,NULL,NULL,'0'),('1857','420202','黄石港区','207','H',2,NULL,NULL,NULL,NULL,'0'),('1858','420203','西塞山区','207','X',2,NULL,NULL,NULL,NULL,'0'),('1859','420204','下陆区','207','X',2,NULL,NULL,NULL,NULL,'0'),('186','371600','滨州市','15','B',1,NULL,NULL,NULL,NULL,'0'),('1860','420205','铁山区','207','T',2,NULL,NULL,NULL,NULL,'0'),('1861','420222','阳新县','207','Y',2,NULL,NULL,NULL,NULL,'0'),('1862','420281','大冶市','207','D',2,NULL,NULL,NULL,NULL,'0'),('1863','420302','茅箭区','208','M',2,NULL,NULL,NULL,NULL,'0'),('1864','420303','张湾区','208','Z',2,NULL,NULL,NULL,NULL,'0'),('1865','420304','郧阳区','208','Y',2,NULL,NULL,NULL,NULL,'0'),('1866','420322','郧西县','208','Y',2,NULL,NULL,NULL,NULL,'0'),('1867','420323','竹山县','208','Z',2,NULL,NULL,NULL,NULL,'0'),('1868','420324','竹溪县','208','Z',2,NULL,NULL,NULL,NULL,'0'),('1869','420325','房县','208','F',2,NULL,NULL,NULL,NULL,'0'),('187','371700','菏泽市','15','H',1,NULL,NULL,NULL,NULL,'0'),('1870','420381','丹江口市','208','D',2,NULL,NULL,NULL,NULL,'0'),('1871','420502','西陵区','209','X',2,NULL,NULL,NULL,NULL,'0'),('1872','420503','伍家岗区','209','W',2,NULL,NULL,NULL,NULL,'0'),('1873','420504','点军区','209','D',2,NULL,NULL,NULL,NULL,'0'),('1874','420505','猇亭区','209','X',2,NULL,NULL,NULL,NULL,'0'),('1875','420506','夷陵区','209','Y',2,NULL,NULL,NULL,NULL,'0'),('1876','420525','远安县','209','Y',2,NULL,NULL,NULL,NULL,'0'),('1877','420526','兴山县','209','X',2,NULL,NULL,NULL,NULL,'0'),('1878','420527','秭归县','209','Z',2,NULL,NULL,NULL,NULL,'0'),('1879','420528','长阳土家族自治县','209','C',2,NULL,NULL,NULL,NULL,'0'),('188','410100','郑州市','16','Z',1,NULL,NULL,NULL,NULL,'0'),('1880','420529','五峰土家族自治县','209','W',2,NULL,NULL,NULL,NULL,'0'),('1881','420581','宜都市','209','Y',2,NULL,NULL,NULL,NULL,'0'),('1882','420582','当阳市','209','D',2,NULL,NULL,NULL,NULL,'0'),('1883','420583','枝江市','209','Z',2,NULL,NULL,NULL,NULL,'0'),('1884','420602','襄城区','210','X',2,NULL,NULL,NULL,NULL,'0'),('1885','420606','樊城区','210','F',2,NULL,NULL,NULL,NULL,'0'),('1886','420607','襄州区','210','X',2,NULL,NULL,NULL,NULL,'0'),('1887','420624','南漳县','210','N',2,NULL,NULL,NULL,NULL,'0'),('1888','420625','谷城县','210','G',2,NULL,NULL,NULL,NULL,'0'),('1889','420626','保康县','210','B',2,NULL,NULL,NULL,NULL,'0'),('189','410200','开封市','16','K',1,NULL,NULL,NULL,NULL,'0'),('1890','420682','老河口市','210','L',2,NULL,NULL,NULL,NULL,'0'),('1891','420683','枣阳市','210','Z',2,NULL,NULL,NULL,NULL,'0'),('1892','420684','宜城市','210','Y',2,NULL,NULL,NULL,NULL,'0'),('1893','420702','梁子湖区','211','L',2,NULL,NULL,NULL,NULL,'0'),('1894','420703','华容区','211','H',2,NULL,NULL,NULL,NULL,'0'),('1895','420704','鄂城区','211','E',2,NULL,NULL,NULL,NULL,'0'),('1896','420802','东宝区','212','D',2,NULL,NULL,NULL,NULL,'0'),('1897','420804','掇刀区','212','D',2,NULL,NULL,NULL,NULL,'0'),('1898','420821','京山县','212','J',2,NULL,NULL,NULL,NULL,'0'),('1899','420822','沙洋县','212','S',2,NULL,NULL,NULL,NULL,'0'),('19','440000','广东省','0','G',0,NULL,NULL,NULL,NULL,'0'),('190','410300','洛阳市','16','L',1,NULL,NULL,NULL,NULL,'0'),('1900','420881','钟祥市','212','Z',2,NULL,NULL,NULL,NULL,'0'),('1901','420902','孝南区','213','X',2,NULL,NULL,NULL,NULL,'0'),('1902','420921','孝昌县','213','X',2,NULL,NULL,NULL,NULL,'0'),('1903','420922','大悟县','213','D',2,NULL,NULL,NULL,NULL,'0'),('1904','420923','云梦县','213','Y',2,NULL,NULL,NULL,NULL,'0'),('1905','420981','应城市','213','Y',2,NULL,NULL,NULL,NULL,'0'),('1906','420982','安陆市','213','A',2,NULL,NULL,NULL,NULL,'0'),('1907','420984','汉川市','213','H',2,NULL,NULL,NULL,NULL,'0'),('1908','421002','沙市区','214','S',2,NULL,NULL,NULL,NULL,'0'),('1909','421003','荆州区','214','J',2,NULL,NULL,NULL,NULL,'0'),('191','410400','平顶山市','16','P',1,NULL,NULL,NULL,NULL,'0'),('1910','421022','公安县','214','G',2,NULL,NULL,NULL,NULL,'0'),('1911','421023','监利县','214','J',2,NULL,NULL,NULL,NULL,'0'),('1912','421024','江陵县','214','J',2,NULL,NULL,NULL,NULL,'0'),('1913','421081','石首市','214','S',2,NULL,NULL,NULL,NULL,'0'),('1914','421083','洪湖市','214','H',2,NULL,NULL,NULL,NULL,'0'),('1915','421087','松滋市','214','S',2,NULL,NULL,NULL,NULL,'0'),('1916','421102','黄州区','215','H',2,NULL,NULL,NULL,NULL,'0'),('1917','421121','团风县','215','T',2,NULL,NULL,NULL,NULL,'0'),('1918','421122','红安县','215','H',2,NULL,NULL,NULL,NULL,'0'),('1919','421123','罗田县','215','L',2,NULL,NULL,NULL,NULL,'0'),('192','410500','安阳市','16','A',1,NULL,NULL,NULL,NULL,'0'),('1920','421124','英山县','215','Y',2,NULL,NULL,NULL,NULL,'0'),('1921','421125','浠水县','215','X',2,NULL,NULL,NULL,NULL,'0'),('1922','421126','蕲春县','215','Q',2,NULL,NULL,NULL,NULL,'0'),('1923','421127','黄梅县','215','H',2,NULL,NULL,NULL,NULL,'0'),('1924','421181','麻城市','215','M',2,NULL,NULL,NULL,NULL,'0'),('1925','421182','武穴市','215','W',2,NULL,NULL,NULL,NULL,'0'),('1926','421202','咸安区','216','X',2,NULL,NULL,NULL,NULL,'0'),('1927','421221','嘉鱼县','216','J',2,NULL,NULL,NULL,NULL,'0'),('1928','421222','通城县','216','T',2,NULL,NULL,NULL,NULL,'0'),('1929','421223','崇阳县','216','C',2,NULL,NULL,NULL,NULL,'0'),('193','410600','鹤壁市','16','H',1,NULL,NULL,NULL,NULL,'0'),('1930','421224','通山县','216','T',2,NULL,NULL,NULL,NULL,'0'),('1931','421281','赤壁市','216','C',2,NULL,NULL,NULL,NULL,'0'),('1932','421303','曾都区','217','Z',2,NULL,NULL,NULL,NULL,'0'),('1933','421321','随县','217','S',2,NULL,NULL,NULL,NULL,'0'),('1934','421381','广水市','217','G',2,NULL,NULL,NULL,NULL,'0'),('1935','422801','恩施市','218','E',2,NULL,NULL,NULL,NULL,'0'),('1936','422802','利川市','218','L',2,NULL,NULL,NULL,NULL,'0'),('1937','422822','建始县','218','J',2,NULL,NULL,NULL,NULL,'0'),('1938','422823','巴东县','218','B',2,NULL,NULL,NULL,NULL,'0'),('1939','422825','宣恩县','218','X',2,NULL,NULL,NULL,NULL,'0'),('194','410700','新乡市','16','X',1,NULL,NULL,NULL,NULL,'0'),('1940','422826','咸丰县','218','X',2,NULL,NULL,NULL,NULL,'0'),('1941','422827','来凤县','218','L',2,NULL,NULL,NULL,NULL,'0'),('1942','422828','鹤峰县','218','H',2,NULL,NULL,NULL,NULL,'0'),('1943','429004','仙桃市','219','X',2,NULL,NULL,NULL,NULL,'0'),('1944','429005','潜江市','219','Q',2,NULL,NULL,NULL,NULL,'0'),('1945','429006','天门市','219','T',2,NULL,NULL,NULL,NULL,'0'),('1946','429021','神农架林区','219','S',2,NULL,NULL,NULL,NULL,'0'),('1947','430102','芙蓉区','220','F',2,NULL,NULL,NULL,NULL,'0'),('1948','430103','天心区','220','T',2,NULL,NULL,NULL,NULL,'0'),('1949','430104','岳麓区','220','Y',2,NULL,NULL,NULL,NULL,'0'),('195','410800','焦作市','16','J',1,NULL,NULL,NULL,NULL,'0'),('1950','430105','开福区','220','K',2,NULL,NULL,NULL,NULL,'0'),('1951','430111','雨花区','220','Y',2,NULL,NULL,NULL,NULL,'0'),('1952','430112','望城区','220','W',2,NULL,NULL,NULL,NULL,'0'),('1953','430121','长沙县','220','C',2,NULL,NULL,NULL,NULL,'0'),('1954','430124','宁乡县','220','N',2,NULL,NULL,NULL,NULL,'0'),('1955','430181','浏阳市','220','L',2,NULL,NULL,NULL,NULL,'0'),('1956','430202','荷塘区','221','H',2,NULL,NULL,NULL,NULL,'0'),('1957','430203','芦淞区','221','L',2,NULL,NULL,NULL,NULL,'0'),('1958','430204','石峰区','221','S',2,NULL,NULL,NULL,NULL,'0'),('1959','430211','天元区','221','T',2,NULL,NULL,NULL,NULL,'0'),('196','410900','濮阳市','16','P',1,NULL,NULL,NULL,NULL,'0'),('1960','430221','株洲县','221','Z',2,NULL,NULL,NULL,NULL,'0'),('1961','430223','攸县','221','Y',2,NULL,NULL,NULL,NULL,'0'),('1962','430224','茶陵县','221','C',2,NULL,NULL,NULL,NULL,'0'),('1963','430225','炎陵县','221','Y',2,NULL,NULL,NULL,NULL,'0'),('1964','430281','醴陵市','221','L',2,NULL,NULL,NULL,NULL,'0'),('1965','430302','雨湖区','222','Y',2,NULL,NULL,NULL,NULL,'0'),('1966','430304','岳塘区','222','Y',2,NULL,NULL,NULL,NULL,'0'),('1967','430321','湘潭县','222','X',2,NULL,NULL,NULL,NULL,'0'),('1968','430381','湘乡市','222','X',2,NULL,NULL,NULL,NULL,'0'),('1969','430382','韶山市','222','S',2,NULL,NULL,NULL,NULL,'0'),('197','411000','许昌市','16','X',1,NULL,NULL,NULL,NULL,'0'),('1970','430405','珠晖区','223','Z',2,NULL,NULL,NULL,NULL,'0'),('1971','430406','雁峰区','223','Y',2,NULL,NULL,NULL,NULL,'0'),('1972','430407','石鼓区','223','S',2,NULL,NULL,NULL,NULL,'0'),('1973','430408','蒸湘区','223','Z',2,NULL,NULL,NULL,NULL,'0'),('1974','430412','南岳区','223','N',2,NULL,NULL,NULL,NULL,'0'),('1975','430421','衡阳县','223','H',2,NULL,NULL,NULL,NULL,'0'),('1976','430422','衡南县','223','H',2,NULL,NULL,NULL,NULL,'0'),('1977','430423','衡山县','223','H',2,NULL,NULL,NULL,NULL,'0'),('1978','430424','衡东县','223','H',2,NULL,NULL,NULL,NULL,'0'),('1979','430426','祁东县','223','Q',2,NULL,NULL,NULL,NULL,'0'),('198','411100','漯河市','16','L',1,NULL,NULL,NULL,NULL,'0'),('1980','430481','耒阳市','223','L',2,NULL,NULL,NULL,NULL,'0'),('1981','430482','常宁市','223','C',2,NULL,NULL,NULL,NULL,'0'),('1982','430502','双清区','224','S',2,NULL,NULL,NULL,NULL,'0'),('1983','430503','大祥区','224','D',2,NULL,NULL,NULL,NULL,'0'),('1984','430511','北塔区','224','B',2,NULL,NULL,NULL,NULL,'0'),('1985','430521','邵东县','224','S',2,NULL,NULL,NULL,NULL,'0'),('1986','430522','新邵县','224','X',2,NULL,NULL,NULL,NULL,'0'),('1987','430523','邵阳县','224','S',2,NULL,NULL,NULL,NULL,'0'),('1988','430524','隆回县','224','L',2,NULL,NULL,NULL,NULL,'0'),('1989','430525','洞口县','224','D',2,NULL,NULL,NULL,NULL,'0'),('199','411200','三门峡市','16','S',1,NULL,NULL,NULL,NULL,'0'),('1990','430527','绥宁县','224','S',2,NULL,NULL,NULL,NULL,'0'),('1991','430528','新宁县','224','X',2,NULL,NULL,NULL,NULL,'0'),('1992','430529','城步苗族自治县','224','C',2,NULL,NULL,NULL,NULL,'0'),('1993','430581','武冈市','224','W',2,NULL,NULL,NULL,NULL,'0'),('1994','430602','岳阳楼区','225','Y',2,NULL,NULL,NULL,NULL,'0'),('1995','430603','云溪区','225','Y',2,NULL,NULL,NULL,NULL,'0'),('1996','430611','君山区','225','J',2,NULL,NULL,NULL,NULL,'0'),('1997','430621','岳阳县','225','Y',2,NULL,NULL,NULL,NULL,'0'),('1998','430623','华容县','225','H',2,NULL,NULL,NULL,NULL,'0'),('1999','430624','湘阴县','225','X',2,NULL,NULL,NULL,NULL,'0'),('2','120000','天津市','0','T',0,NULL,NULL,NULL,NULL,'0'),('20','450000','广西壮族自治区','0','G',0,NULL,NULL,NULL,NULL,'0'),('200','411300','南阳市','16','N',1,NULL,NULL,NULL,NULL,'0'),('2000','430626','平江县','225','P',2,NULL,NULL,NULL,NULL,'0'),('2001','430681','汨罗市','225','M',2,NULL,NULL,NULL,NULL,'0'),('2002','430682','临湘市','225','L',2,NULL,NULL,NULL,NULL,'0'),('2003','430702','武陵区','226','W',2,NULL,NULL,NULL,NULL,'0'),('2004','430703','鼎城区','226','D',2,NULL,NULL,NULL,NULL,'0'),('2005','430721','安乡县','226','A',2,NULL,NULL,NULL,NULL,'0'),('2006','430722','汉寿县','226','H',2,NULL,NULL,NULL,NULL,'0'),('2007','430723','澧县','226','L',2,NULL,NULL,NULL,NULL,'0'),('2008','430724','临澧县','226','L',2,NULL,NULL,NULL,NULL,'0'),('2009','430725','桃源县','226','T',2,NULL,NULL,NULL,NULL,'0'),('201','411400','商丘市','16','S',1,NULL,NULL,NULL,NULL,'0'),('2010','430726','石门县','226','S',2,NULL,NULL,NULL,NULL,'0'),('2011','430781','津市市','226','J',2,NULL,NULL,NULL,NULL,'0'),('2012','430802','永定区','227','Y',2,NULL,NULL,NULL,NULL,'0'),('2013','430811','武陵源区','227','W',2,NULL,NULL,NULL,NULL,'0'),('2014','430821','慈利县','227','C',2,NULL,NULL,NULL,NULL,'0'),('2015','430822','桑植县','227','S',2,NULL,NULL,NULL,NULL,'0'),('2016','430902','资阳区','228','Z',2,NULL,NULL,NULL,NULL,'0'),('2017','430903','赫山区','228','H',2,NULL,NULL,NULL,NULL,'0'),('2018','430921','南县','228','N',2,NULL,NULL,NULL,NULL,'0'),('2019','430922','桃江县','228','T',2,NULL,NULL,NULL,NULL,'0'),('202','411500','信阳市','16','X',1,NULL,NULL,NULL,NULL,'0'),('2020','430923','安化县','228','A',2,NULL,NULL,NULL,NULL,'0'),('2021','430981','沅江市','228','Y',2,NULL,NULL,NULL,NULL,'0'),('2022','431002','北湖区','229','B',2,NULL,NULL,NULL,NULL,'0'),('2023','431003','苏仙区','229','S',2,NULL,NULL,NULL,NULL,'0'),('2024','431021','桂阳县','229','G',2,NULL,NULL,NULL,NULL,'0'),('2025','431022','宜章县','229','Y',2,NULL,NULL,NULL,NULL,'0'),('2026','431023','永兴县','229','Y',2,NULL,NULL,NULL,NULL,'0'),('2027','431024','嘉禾县','229','J',2,NULL,NULL,NULL,NULL,'0'),('2028','431025','临武县','229','L',2,NULL,NULL,NULL,NULL,'0'),('2029','431026','汝城县','229','R',2,NULL,NULL,NULL,NULL,'0'),('203','411600','周口市','16','Z',1,NULL,NULL,NULL,NULL,'0'),('2030','431027','桂东县','229','G',2,NULL,NULL,NULL,NULL,'0'),('2031','431028','安仁县','229','A',2,NULL,NULL,NULL,NULL,'0'),('2032','431081','资兴市','229','Z',2,NULL,NULL,NULL,NULL,'0'),('2033','431102','零陵区','230','L',2,NULL,NULL,NULL,NULL,'0'),('2034','431103','冷水滩区','230','L',2,NULL,NULL,NULL,NULL,'0'),('2035','431121','祁阳县','230','Q',2,NULL,NULL,NULL,NULL,'0'),('2036','431122','东安县','230','D',2,NULL,NULL,NULL,NULL,'0'),('2037','431123','双牌县','230','S',2,NULL,NULL,NULL,NULL,'0'),('2038','431124','道县','230','D',2,NULL,NULL,NULL,NULL,'0'),('2039','431125','江永县','230','J',2,NULL,NULL,NULL,NULL,'0'),('204','411700','驻马店市','16','Z',1,NULL,NULL,NULL,NULL,'0'),('2040','431126','宁远县','230','N',2,NULL,NULL,NULL,NULL,'0'),('2041','431127','蓝山县','230','L',2,NULL,NULL,NULL,NULL,'0'),('2042','431128','新田县','230','X',2,NULL,NULL,NULL,NULL,'0'),('2043','431129','江华瑶族自治县','230','J',2,NULL,NULL,NULL,NULL,'0'),('2044','431202','鹤城区','231','H',2,NULL,NULL,NULL,NULL,'0'),('2045','431221','中方县','231','Z',2,NULL,NULL,NULL,NULL,'0'),('2046','431222','沅陵县','231','Y',2,NULL,NULL,NULL,NULL,'0'),('2047','431223','辰溪县','231','C',2,NULL,NULL,NULL,NULL,'0'),('2048','431224','溆浦县','231','X',2,NULL,NULL,NULL,NULL,'0'),('2049','431225','会同县','231','H',2,NULL,NULL,NULL,NULL,'0'),('205','419000','省直辖县级行政区划','16','S',1,NULL,NULL,NULL,NULL,'0'),('2050','431226','麻阳苗族自治县','231','M',2,NULL,NULL,NULL,NULL,'0'),('2051','431227','新晃侗族自治县','231','X',2,NULL,NULL,NULL,NULL,'0'),('2052','431228','芷江侗族自治县','231','Z',2,NULL,NULL,NULL,NULL,'0'),('2053','431229','靖州苗族侗族自治县','231','J',2,NULL,NULL,NULL,NULL,'0'),('2054','431230','通道侗族自治县','231','T',2,NULL,NULL,NULL,NULL,'0'),('2055','431281','洪江市','231','H',2,NULL,NULL,NULL,NULL,'0'),('2056','431302','娄星区','232','L',2,NULL,NULL,NULL,NULL,'0'),('2057','431321','双峰县','232','S',2,NULL,NULL,NULL,NULL,'0'),('2058','431322','新化县','232','X',2,NULL,NULL,NULL,NULL,'0'),('2059','431381','冷水江市','232','L',2,NULL,NULL,NULL,NULL,'0'),('206','420100','武汉市','17','W',1,NULL,NULL,NULL,NULL,'0'),('2060','431382','涟源市','232','L',2,NULL,NULL,NULL,NULL,'0'),('2061','433101','吉首市','233','J',2,NULL,NULL,NULL,NULL,'0'),('2062','433122','泸溪县','233','L',2,NULL,NULL,NULL,NULL,'0'),('2063','433123','凤凰县','233','F',2,NULL,NULL,NULL,NULL,'0'),('2064','433124','花垣县','233','H',2,NULL,NULL,NULL,NULL,'0'),('2065','433125','保靖县','233','B',2,NULL,NULL,NULL,NULL,'0'),('2066','433126','古丈县','233','G',2,NULL,NULL,NULL,NULL,'0'),('2067','433127','永顺县','233','Y',2,NULL,NULL,NULL,NULL,'0'),('2068','433130','龙山县','233','L',2,NULL,NULL,NULL,NULL,'0'),('2069','440103','荔湾区','234','L',2,NULL,NULL,NULL,NULL,'0'),('207','420200','黄石市','17','H',1,NULL,NULL,NULL,NULL,'0'),('2070','440104','越秀区','234','Y',2,NULL,NULL,NULL,NULL,'0'),('2071','440105','海珠区','234','H',2,NULL,NULL,NULL,NULL,'0'),('2072','440106','天河区','234','T',2,NULL,NULL,NULL,NULL,'0'),('2073','440111','白云区','234','B',2,NULL,NULL,NULL,NULL,'0'),('2074','440112','黄埔区','234','H',2,NULL,NULL,NULL,NULL,'0'),('2075','440113','番禺区','234','F',2,NULL,NULL,NULL,NULL,'0'),('2076','440114','花都区','234','H',2,NULL,NULL,NULL,NULL,'0'),('2077','440115','南沙区','234','N',2,NULL,NULL,NULL,NULL,'0'),('2078','440116','萝岗区','234','L',2,NULL,NULL,NULL,NULL,'0'),('2079','440117','从化区','234','C',2,NULL,NULL,NULL,NULL,'0'),('208','420300','十堰市','17','S',1,NULL,NULL,NULL,NULL,'0'),('2080','440118','增城区','234','Z',2,NULL,NULL,NULL,NULL,'0'),('2081','440203','武江区','235','W',2,NULL,NULL,NULL,NULL,'0'),('2082','440204','浈江区','235','Z',2,NULL,NULL,NULL,NULL,'0'),('2083','440205','曲江区','235','Q',2,NULL,NULL,NULL,NULL,'0'),('2084','440222','始兴县','235','S',2,NULL,NULL,NULL,NULL,'0'),('2085','440224','仁化县','235','R',2,NULL,NULL,NULL,NULL,'0'),('2086','440229','翁源县','235','W',2,NULL,NULL,NULL,NULL,'0'),('2087','440232','乳源瑶族自治县','235','R',2,NULL,NULL,NULL,NULL,'0'),('2088','440233','新丰县','235','X',2,NULL,NULL,NULL,NULL,'0'),('2089','440281','乐昌市','235','L',2,NULL,NULL,NULL,NULL,'0'),('209','420500','宜昌市','17','Y',1,NULL,NULL,NULL,NULL,'0'),('2090','440282','南雄市','235','N',2,NULL,NULL,NULL,NULL,'0'),('2091','440303','罗湖区','236','L',2,NULL,NULL,NULL,NULL,'0'),('2092','440304','福田区','236','F',2,NULL,NULL,NULL,NULL,'0'),('2093','440306','宝安区','236','B',2,NULL,NULL,NULL,NULL,'0'),('2094','440307','龙岗区','236','L',2,NULL,NULL,NULL,NULL,'0'),('2095','440308','盐田区','236','Y',2,NULL,NULL,NULL,NULL,'0'),('2096','440402','香洲区','237','X',2,NULL,NULL,NULL,NULL,'0'),('2097','440403','斗门区','237','D',2,NULL,NULL,NULL,NULL,'0'),('2098','440404','金湾区','237','J',2,NULL,NULL,NULL,NULL,'0'),('2099','440507','龙湖区','238','L',2,NULL,NULL,NULL,NULL,'0'),('21','460000','海南省','0','H',0,NULL,NULL,NULL,NULL,'0'),('210','420600','襄阳市','17','X',1,NULL,NULL,NULL,NULL,'0'),('2100','440511','金平区','238','J',2,NULL,NULL,NULL,NULL,'0'),('2101','440512','濠江区','238','H',2,NULL,NULL,NULL,NULL,'0'),('2102','440513','潮阳区','238','C',2,NULL,NULL,NULL,NULL,'0'),('2103','440514','潮南区','238','C',2,NULL,NULL,NULL,NULL,'0'),('2104','440515','澄海区','238','C',2,NULL,NULL,NULL,NULL,'0'),('2105','440523','南澳县','238','N',2,NULL,NULL,NULL,NULL,'0'),('2106','440604','禅城区','239','C',2,NULL,NULL,NULL,NULL,'0'),('2107','440605','南海区','239','N',2,NULL,NULL,NULL,NULL,'0'),('2108','440606','顺德区','239','S',2,NULL,NULL,NULL,NULL,'0'),('2109','440607','三水区','239','S',2,NULL,NULL,NULL,NULL,'0'),('211','420700','鄂州市','17','E',1,NULL,NULL,NULL,NULL,'0'),('2110','440608','高明区','239','G',2,NULL,NULL,NULL,NULL,'0'),('2111','440703','蓬江区','240','P',2,NULL,NULL,NULL,NULL,'0'),('2112','440704','江海区','240','J',2,NULL,NULL,NULL,NULL,'0'),('2113','440705','新会区','240','X',2,NULL,NULL,NULL,NULL,'0'),('2114','440781','台山市','240','T',2,NULL,NULL,NULL,NULL,'0'),('2115','440783','开平市','240','K',2,NULL,NULL,NULL,NULL,'0'),('2116','440784','鹤山市','240','H',2,NULL,NULL,NULL,NULL,'0'),('2117','440785','恩平市','240','E',2,NULL,NULL,NULL,NULL,'0'),('2118','440802','赤坎区','241','C',2,NULL,NULL,NULL,NULL,'0'),('2119','440803','霞山区','241','X',2,NULL,NULL,NULL,NULL,'0'),('212','420800','荆门市','17','J',1,NULL,NULL,NULL,NULL,'0'),('2120','440804','坡头区','241','P',2,NULL,NULL,NULL,NULL,'0'),('2121','440811','麻章区','241','M',2,NULL,NULL,NULL,NULL,'0'),('2122','440823','遂溪县','241','S',2,NULL,NULL,NULL,NULL,'0'),('2123','440825','徐闻县','241','X',2,NULL,NULL,NULL,NULL,'0'),('2124','440881','廉江市','241','L',2,NULL,NULL,NULL,NULL,'0'),('2125','440882','雷州市','241','L',2,NULL,NULL,NULL,NULL,'0'),('2126','440883','吴川市','241','W',2,NULL,NULL,NULL,NULL,'0'),('2127','440902','茂南区','242','M',2,NULL,NULL,NULL,NULL,'0'),('2128','440904','电白区','242','D',2,NULL,NULL,NULL,NULL,'0'),('2129','440981','高州市','242','G',2,NULL,NULL,NULL,NULL,'0'),('213','420900','孝感市','17','X',1,NULL,NULL,NULL,NULL,'0'),('2130','440982','化州市','242','H',2,NULL,NULL,NULL,NULL,'0'),('2131','440983','信宜市','242','X',2,NULL,NULL,NULL,NULL,'0'),('2132','441202','端州区','243','D',2,NULL,NULL,NULL,NULL,'0'),('2133','441203','鼎湖区','243','D',2,NULL,NULL,NULL,NULL,'0'),('2134','441223','广宁县','243','G',2,NULL,NULL,NULL,NULL,'0'),('2135','441224','怀集县','243','H',2,NULL,NULL,NULL,NULL,'0'),('2136','441225','封开县','243','F',2,NULL,NULL,NULL,NULL,'0'),('2137','441226','德庆县','243','D',2,NULL,NULL,NULL,NULL,'0'),('2138','441283','高要市','243','G',2,NULL,NULL,NULL,NULL,'0'),('2139','441284','四会市','243','S',2,NULL,NULL,NULL,NULL,'0'),('214','421000','荆州市','17','J',1,NULL,NULL,NULL,NULL,'0'),('2140','441302','惠城区','244','H',2,NULL,NULL,NULL,NULL,'0'),('2141','441303','惠阳区','244','H',2,NULL,NULL,NULL,NULL,'0'),('2142','441322','博罗县','244','B',2,NULL,NULL,NULL,NULL,'0'),('2143','441323','惠东县','244','H',2,NULL,NULL,NULL,NULL,'0'),('2144','441324','龙门县','244','L',2,NULL,NULL,NULL,NULL,'0'),('2145','441402','梅江区','245','M',2,NULL,NULL,NULL,NULL,'0'),('2146','441403','梅县区','245','M',2,NULL,NULL,NULL,NULL,'0'),('2147','441422','大埔县','245','D',2,NULL,NULL,NULL,NULL,'0'),('2148','441423','丰顺县','245','F',2,NULL,NULL,NULL,NULL,'0'),('2149','441424','五华县','245','W',2,NULL,NULL,NULL,NULL,'0'),('215','421100','黄冈市','17','H',1,NULL,NULL,NULL,NULL,'0'),('2150','441426','平远县','245','P',2,NULL,NULL,NULL,NULL,'0'),('2151','441427','蕉岭县','245','J',2,NULL,NULL,NULL,NULL,'0'),('2152','441481','兴宁市','245','X',2,NULL,NULL,NULL,NULL,'0'),('2153','441521','海丰县','246','H',2,NULL,NULL,NULL,NULL,'0'),('2154','441523','陆河县','246','L',2,NULL,NULL,NULL,NULL,'0'),('2155','441581','陆丰市','246','L',2,NULL,NULL,NULL,NULL,'0'),('2156','441602','源城区','247','Y',2,NULL,NULL,NULL,NULL,'0'),('2157','441621','紫金县','247','Z',2,NULL,NULL,NULL,NULL,'0'),('2158','441622','龙川县','247','L',2,NULL,NULL,NULL,NULL,'0'),('2159','441623','连平县','247','L',2,NULL,NULL,NULL,NULL,'0'),('216','421200','咸宁市','17','X',1,NULL,NULL,NULL,NULL,'0'),('2160','441624','和平县','247','H',2,NULL,NULL,NULL,NULL,'0'),('2161','441625','东源县','247','D',2,NULL,NULL,NULL,NULL,'0'),('2162','441702','江城区','248','J',2,NULL,NULL,NULL,NULL,'0'),('2163','441721','阳西县','248','Y',2,NULL,NULL,NULL,NULL,'0'),('2164','441723','阳东县','248','Y',2,NULL,NULL,NULL,NULL,'0'),('2165','441781','阳春市','248','Y',2,NULL,NULL,NULL,NULL,'0'),('2166','441802','清城区','249','Q',2,NULL,NULL,NULL,NULL,'0'),('2167','441803','清新区','249','Q',2,NULL,NULL,NULL,NULL,'0'),('2168','441821','佛冈县','249','F',2,NULL,NULL,NULL,NULL,'0'),('2169','441823','阳山县','249','Y',2,NULL,NULL,NULL,NULL,'0'),('217','421300','随州市','17','S',1,NULL,NULL,NULL,NULL,'0'),('2170','441825','连山壮族瑶族自治县','249','L',2,NULL,NULL,NULL,NULL,'0'),('2171','441826','连南瑶族自治县','249','L',2,NULL,NULL,NULL,NULL,'0'),('2172','441881','英德市','249','Y',2,NULL,NULL,NULL,NULL,'0'),('2173','441882','连州市','249','L',2,NULL,NULL,NULL,NULL,'0'),('2174','445102','湘桥区','252','X',2,NULL,NULL,NULL,NULL,'0'),('2175','445103','潮安区','252','C',2,NULL,NULL,NULL,NULL,'0'),('2176','445122','饶平县','252','R',2,NULL,NULL,NULL,NULL,'0'),('2177','445202','榕城区','253','R',2,NULL,NULL,NULL,NULL,'0'),('2178','445203','揭东区','253','J',2,NULL,NULL,NULL,NULL,'0'),('2179','445222','揭西县','253','J',2,NULL,NULL,NULL,NULL,'0'),('218','422800','恩施土家族苗族自治州','17','E',1,NULL,NULL,NULL,NULL,'0'),('2180','445224','惠来县','253','H',2,NULL,NULL,NULL,NULL,'0'),('2181','445281','普宁市','253','P',2,NULL,NULL,NULL,NULL,'0'),('2182','445302','云城区','254','Y',2,NULL,NULL,NULL,NULL,'0'),('2183','445303','云安区','254','Y',2,NULL,NULL,NULL,NULL,'0'),('2184','445321','新兴县','254','X',2,NULL,NULL,NULL,NULL,'0'),('2185','445322','郁南县','254','Y',2,NULL,NULL,NULL,NULL,'0'),('2186','445381','罗定市','254','L',2,NULL,NULL,NULL,NULL,'0'),('2187','450102','兴宁区','255','X',2,NULL,NULL,NULL,NULL,'0'),('2188','450103','青秀区','255','Q',2,NULL,NULL,NULL,NULL,'0'),('2189','450105','江南区','255','J',2,NULL,NULL,NULL,NULL,'0'),('219','429000','省直辖县级行政区划','17','S',1,NULL,NULL,NULL,NULL,'0'),('2190','450107','西乡塘区','255','X',2,NULL,NULL,NULL,NULL,'0'),('2191','450108','良庆区','255','L',2,NULL,NULL,NULL,NULL,'0'),('2192','450109','邕宁区','255','Y',2,NULL,NULL,NULL,NULL,'0'),('2193','450122','武鸣县','255','W',2,NULL,NULL,NULL,NULL,'0'),('2194','450123','隆安县','255','L',2,NULL,NULL,NULL,NULL,'0'),('2195','450124','马山县','255','M',2,NULL,NULL,NULL,NULL,'0'),('2196','450125','上林县','255','S',2,NULL,NULL,NULL,NULL,'0'),('2197','450126','宾阳县','255','B',2,NULL,NULL,NULL,NULL,'0'),('2198','450127','横县','255','H',2,NULL,NULL,NULL,NULL,'0'),('2199','450202','城中区','256','C',2,NULL,NULL,NULL,NULL,'0'),('22','500000','重庆市','0','Z',0,NULL,NULL,NULL,NULL,'0'),('220','430100','长沙市','18','C',1,NULL,NULL,NULL,NULL,'0'),('2200','450203','鱼峰区','256','Y',2,NULL,NULL,NULL,NULL,'0'),('2201','450204','柳南区','256','L',2,NULL,NULL,NULL,NULL,'0'),('2202','450205','柳北区','256','L',2,NULL,NULL,NULL,NULL,'0'),('2203','450221','柳江县','256','L',2,NULL,NULL,NULL,NULL,'0'),('2204','450222','柳城县','256','L',2,NULL,NULL,NULL,NULL,'0'),('2205','450223','鹿寨县','256','L',2,NULL,NULL,NULL,NULL,'0'),('2206','450224','融安县','256','R',2,NULL,NULL,NULL,NULL,'0'),('2207','450225','融水苗族自治县','256','R',2,NULL,NULL,NULL,NULL,'0'),('2208','450226','三江侗族自治县','256','S',2,NULL,NULL,NULL,NULL,'0'),('2209','450302','秀峰区','257','X',2,NULL,NULL,NULL,NULL,'0'),('221','430200','株洲市','18','Z',1,NULL,NULL,NULL,NULL,'0'),('2210','450303','叠彩区','257','D',2,NULL,NULL,NULL,NULL,'0'),('2211','450304','象山区','257','X',2,NULL,NULL,NULL,NULL,'0'),('2212','450305','七星区','257','Q',2,NULL,NULL,NULL,NULL,'0'),('2213','450311','雁山区','257','Y',2,NULL,NULL,NULL,NULL,'0'),('2214','450312','临桂区','257','L',2,NULL,NULL,NULL,NULL,'0'),('2215','450321','阳朔县','257','Y',2,NULL,NULL,NULL,NULL,'0'),('2216','450323','灵川县','257','L',2,NULL,NULL,NULL,NULL,'0'),('2217','450324','全州县','257','Q',2,NULL,NULL,NULL,NULL,'0'),('2218','450325','兴安县','257','X',2,NULL,NULL,NULL,NULL,'0'),('2219','450326','永福县','257','Y',2,NULL,NULL,NULL,NULL,'0'),('222','430300','湘潭市','18','X',1,NULL,NULL,NULL,NULL,'0'),('2220','450327','灌阳县','257','G',2,NULL,NULL,NULL,NULL,'0'),('2221','450328','龙胜各族自治县','257','L',2,NULL,NULL,NULL,NULL,'0'),('2222','450329','资源县','257','Z',2,NULL,NULL,NULL,NULL,'0'),('2223','450330','平乐县','257','P',2,NULL,NULL,NULL,NULL,'0'),('2224','450331','荔浦县','257','L',2,NULL,NULL,NULL,NULL,'0'),('2225','450332','恭城瑶族自治县','257','G',2,NULL,NULL,NULL,NULL,'0'),('2226','450403','万秀区','258','W',2,NULL,NULL,NULL,NULL,'0'),('2227','450405','长洲区','258','C',2,NULL,NULL,NULL,NULL,'0'),('2228','450406','龙圩区','258','L',2,NULL,NULL,NULL,NULL,'0'),('2229','450421','苍梧县','258','C',2,NULL,NULL,NULL,NULL,'0'),('223','430400','衡阳市','18','H',1,NULL,NULL,NULL,NULL,'0'),('2230','450422','藤县','258','T',2,NULL,NULL,NULL,NULL,'0'),('2231','450423','蒙山县','258','M',2,NULL,NULL,NULL,NULL,'0'),('2232','450481','岑溪市','258','C',2,NULL,NULL,NULL,NULL,'0'),('2233','450502','海城区','259','H',2,NULL,NULL,NULL,NULL,'0'),('2234','450503','银海区','259','Y',2,NULL,NULL,NULL,NULL,'0'),('2235','450512','铁山港区','259','T',2,NULL,NULL,NULL,NULL,'0'),('2236','450521','合浦县','259','H',2,NULL,NULL,NULL,NULL,'0'),('2237','450602','港口区','260','G',2,NULL,NULL,NULL,NULL,'0'),('2238','450603','防城区','260','F',2,NULL,NULL,NULL,NULL,'0'),('2239','450621','上思县','260','S',2,NULL,NULL,NULL,NULL,'0'),('224','430500','邵阳市','18','S',1,NULL,NULL,NULL,NULL,'0'),('2240','450681','东兴市','260','D',2,NULL,NULL,NULL,NULL,'0'),('2241','450702','钦南区','261','Q',2,NULL,NULL,NULL,NULL,'0'),('2242','450703','钦北区','261','Q',2,NULL,NULL,NULL,NULL,'0'),('2243','450721','灵山县','261','L',2,NULL,NULL,NULL,NULL,'0'),('2244','450722','浦北县','261','P',2,NULL,NULL,NULL,NULL,'0'),('2245','450802','港北区','262','G',2,NULL,NULL,NULL,NULL,'0'),('2246','450803','港南区','262','G',2,NULL,NULL,NULL,NULL,'0'),('2247','450804','覃塘区','262','T',2,NULL,NULL,NULL,NULL,'0'),('2248','450821','平南县','262','P',2,NULL,NULL,NULL,NULL,'0'),('2249','450881','桂平市','262','G',2,NULL,NULL,NULL,NULL,'0'),('225','430600','岳阳市','18','Y',1,NULL,NULL,NULL,NULL,'0'),('2250','450902','玉州区','263','Y',2,NULL,NULL,NULL,NULL,'0'),('2251','450903','福绵区','263','F',2,NULL,NULL,NULL,NULL,'0'),('2252','450921','容县','263','R',2,NULL,NULL,NULL,NULL,'0'),('2253','450922','陆川县','263','L',2,NULL,NULL,NULL,NULL,'0'),('2254','450923','博白县','263','B',2,NULL,NULL,NULL,NULL,'0'),('2255','450924','兴业县','263','X',2,NULL,NULL,NULL,NULL,'0'),('2256','450981','北流市','263','B',2,NULL,NULL,NULL,NULL,'0'),('2257','451002','右江区','264','Y',2,NULL,NULL,NULL,NULL,'0'),('2258','451021','田阳县','264','T',2,NULL,NULL,NULL,NULL,'0'),('2259','451022','田东县','264','T',2,NULL,NULL,NULL,NULL,'0'),('226','430700','常德市','18','C',1,NULL,NULL,NULL,NULL,'0'),('2260','451023','平果县','264','P',2,NULL,NULL,NULL,NULL,'0'),('2261','451024','德保县','264','D',2,NULL,NULL,NULL,NULL,'0'),('2262','451025','靖西县','264','J',2,NULL,NULL,NULL,NULL,'0'),('2263','451026','那坡县','264','N',2,NULL,NULL,NULL,NULL,'0'),('2264','451027','凌云县','264','L',2,NULL,NULL,NULL,NULL,'0'),('2265','451028','乐业县','264','L',2,NULL,NULL,NULL,NULL,'0'),('2266','451029','田林县','264','T',2,NULL,NULL,NULL,NULL,'0'),('2267','451030','西林县','264','X',2,NULL,NULL,NULL,NULL,'0'),('2268','451031','隆林各族自治县','264','L',2,NULL,NULL,NULL,NULL,'0'),('2269','451102','八步区','265','B',2,NULL,NULL,NULL,NULL,'0'),('227','430800','张家界市','18','Z',1,NULL,NULL,NULL,NULL,'0'),('2270','451121','昭平县','265','Z',2,NULL,NULL,NULL,NULL,'0'),('2271','451122','钟山县','265','Z',2,NULL,NULL,NULL,NULL,'0'),('2272','451123','富川瑶族自治县','265','F',2,NULL,NULL,NULL,NULL,'0'),('2273','451202','金城江区','266','J',2,NULL,NULL,NULL,NULL,'0'),('2274','451221','南丹县','266','N',2,NULL,NULL,NULL,NULL,'0'),('2275','451222','天峨县','266','T',2,NULL,NULL,NULL,NULL,'0'),('2276','451223','凤山县','266','F',2,NULL,NULL,NULL,NULL,'0'),('2277','451224','东兰县','266','D',2,NULL,NULL,NULL,NULL,'0'),('2278','451225','罗城仫佬族自治县','266','L',2,NULL,NULL,NULL,NULL,'0'),('2279','451226','环江毛南族自治县','266','H',2,NULL,NULL,NULL,NULL,'0'),('228','430900','益阳市','18','Y',1,NULL,NULL,NULL,NULL,'0'),('2280','451227','巴马瑶族自治县','266','B',2,NULL,NULL,NULL,NULL,'0'),('2281','451228','都安瑶族自治县','266','D',2,NULL,NULL,NULL,NULL,'0'),('2282','451229','大化瑶族自治县','266','D',2,NULL,NULL,NULL,NULL,'0'),('2283','451281','宜州市','266','Y',2,NULL,NULL,NULL,NULL,'0'),('2284','451302','兴宾区','267','X',2,NULL,NULL,NULL,NULL,'0'),('2285','451321','忻城县','267','X',2,NULL,NULL,NULL,NULL,'0'),('2286','451322','象州县','267','X',2,NULL,NULL,NULL,NULL,'0'),('2287','451323','武宣县','267','W',2,NULL,NULL,NULL,NULL,'0'),('2288','451324','金秀瑶族自治县','267','J',2,NULL,NULL,NULL,NULL,'0'),('2289','451381','合山市','267','H',2,NULL,NULL,NULL,NULL,'0'),('229','431000','郴州市','18','C',1,NULL,NULL,NULL,NULL,'0'),('2290','451402','江州区','268','J',2,NULL,NULL,NULL,NULL,'0'),('2291','451421','扶绥县','268','F',2,NULL,NULL,NULL,NULL,'0'),('2292','451422','宁明县','268','N',2,NULL,NULL,NULL,NULL,'0'),('2293','451423','龙州县','268','L',2,NULL,NULL,NULL,NULL,'0'),('2294','451424','大新县','268','D',2,NULL,NULL,NULL,NULL,'0'),('2295','451425','天等县','268','T',2,NULL,NULL,NULL,NULL,'0'),('2296','451481','凭祥市','268','P',2,NULL,NULL,NULL,NULL,'0'),('2297','460105','秀英区','269','X',2,NULL,NULL,NULL,NULL,'0'),('2298','460106','龙华区','269','L',2,NULL,NULL,NULL,NULL,'0'),('2299','460107','琼山区','269','Q',2,NULL,NULL,NULL,NULL,'0'),('23','510000','四川省','0','S',0,NULL,NULL,NULL,NULL,'0'),('230','431100','永州市','18','Y',1,NULL,NULL,NULL,NULL,'0'),('2300','460108','美兰区','269','M',2,NULL,NULL,NULL,NULL,'0'),('2301','460202','海棠区','270','H',2,NULL,NULL,NULL,NULL,'0'),('2302','460203','吉阳区','270','J',2,NULL,NULL,NULL,NULL,'0'),('2303','460204','天涯区','270','T',2,NULL,NULL,NULL,NULL,'0'),('2304','460205','崖州区','270','Y',2,NULL,NULL,NULL,NULL,'0'),('2305','469001','五指山市','272','W',2,NULL,NULL,NULL,NULL,'0'),('2306','469002','琼海市','272','Q',2,NULL,NULL,NULL,NULL,'0'),('2307','469003','儋州市','272','D',2,NULL,NULL,NULL,NULL,'0'),('2308','469005','文昌市','272','W',2,NULL,NULL,NULL,NULL,'0'),('2309','469006','万宁市','272','W',2,NULL,NULL,NULL,NULL,'0'),('231','431200','怀化市','18','H',1,NULL,NULL,NULL,NULL,'0'),('2310','469007','东方市','272','D',2,NULL,NULL,NULL,NULL,'0'),('2311','469021','定安县','272','D',2,NULL,NULL,NULL,NULL,'0'),('2312','469022','屯昌县','272','T',2,NULL,NULL,NULL,NULL,'0'),('2313','469023','澄迈县','272','C',2,NULL,NULL,NULL,NULL,'0'),('2314','469024','临高县','272','L',2,NULL,NULL,NULL,NULL,'0'),('2315','469025','白沙黎族自治县','272','B',2,NULL,NULL,NULL,NULL,'0'),('2316','469026','昌江黎族自治县','272','C',2,NULL,NULL,NULL,NULL,'0'),('2317','469027','乐东黎族自治县','272','L',2,NULL,NULL,NULL,NULL,'0'),('2318','469028','陵水黎族自治县','272','L',2,NULL,NULL,NULL,NULL,'0'),('2319','469029','保亭黎族苗族自治县','272','B',2,NULL,NULL,NULL,NULL,'0'),('232','431300','娄底市','18','L',1,NULL,NULL,NULL,NULL,'0'),('2320','469030','琼中黎族苗族自治县','272','Q',2,NULL,NULL,NULL,NULL,'0'),('2321','500101','万州区','273','W',2,NULL,NULL,NULL,NULL,'0'),('2322','500102','涪陵区','273','F',2,NULL,NULL,NULL,NULL,'0'),('2323','500103','渝中区','273','Y',2,NULL,NULL,NULL,NULL,'0'),('2324','500104','大渡口区','273','D',2,NULL,NULL,NULL,NULL,'0'),('2325','500106','沙坪坝区','273','S',2,NULL,NULL,NULL,NULL,'0'),('2326','500107','九龙坡区','273','J',2,NULL,NULL,NULL,NULL,'0'),('2327','500108','南岸区','273','N',2,NULL,NULL,NULL,NULL,'0'),('2328','500109','北碚区','273','B',2,NULL,NULL,NULL,NULL,'0'),('2329','500110','綦江区','273','Q',2,NULL,NULL,NULL,NULL,'0'),('233','433100','湘西土家族苗族自治州','18','X',1,NULL,NULL,NULL,NULL,'0'),('2330','500111','大足区','273','D',2,NULL,NULL,NULL,NULL,'0'),('2331','500112','渝北区','273','Y',2,NULL,NULL,NULL,NULL,'0'),('2332','500113','巴南区','273','B',2,NULL,NULL,NULL,NULL,'0'),('2333','500114','黔江区','273','Q',2,NULL,NULL,NULL,NULL,'0'),('2334','500115','长寿区','273','C',2,NULL,NULL,NULL,NULL,'0'),('2335','500116','江津区','273','J',2,NULL,NULL,NULL,NULL,'0'),('2336','500117','合川区','273','H',2,NULL,NULL,NULL,NULL,'0'),('2337','500118','永川区','273','Y',2,NULL,NULL,NULL,NULL,'0'),('2338','500119','南川区','273','N',2,NULL,NULL,NULL,NULL,'0'),('2339','500120','璧山区','273','B',2,NULL,NULL,NULL,NULL,'0'),('234','440100','广州市','19','G',1,NULL,NULL,NULL,NULL,'0'),('2340','500151','铜梁区','273','T',2,NULL,NULL,NULL,NULL,'0'),('2341','500223','潼南县','274','T',2,NULL,NULL,NULL,NULL,'0'),('2342','500226','荣昌县','274','R',2,NULL,NULL,NULL,NULL,'0'),('2343','500228','梁平县','274','L',2,NULL,NULL,NULL,NULL,'0'),('2344','500229','城口县','274','C',2,NULL,NULL,NULL,NULL,'0'),('2345','500230','丰都县','274','F',2,NULL,NULL,NULL,NULL,'0'),('2346','500231','垫江县','274','D',2,NULL,NULL,NULL,NULL,'0'),('2347','500232','武隆县','274','W',2,NULL,NULL,NULL,NULL,'0'),('2348','500233','忠县','274','Z',2,NULL,NULL,NULL,NULL,'0'),('2349','500234','开县','274','K',2,NULL,NULL,NULL,NULL,'0'),('235','440200','韶关市','19','S',1,NULL,NULL,NULL,NULL,'0'),('2350','500235','云阳县','274','Y',2,NULL,NULL,NULL,NULL,'0'),('2351','500236','奉节县','274','F',2,NULL,NULL,NULL,NULL,'0'),('2352','500237','巫山县','274','W',2,NULL,NULL,NULL,NULL,'0'),('2353','500238','巫溪县','274','W',2,NULL,NULL,NULL,NULL,'0'),('2354','500240','石柱土家族自治县','274','S',2,NULL,NULL,NULL,NULL,'0'),('2355','500241','秀山土家族苗族自治县','274','X',2,NULL,NULL,NULL,NULL,'0'),('2356','500242','酉阳土家族苗族自治县','274','Y',2,NULL,NULL,NULL,NULL,'0'),('2357','500243','彭水苗族土家族自治县','274','P',2,NULL,NULL,NULL,NULL,'0'),('2358','510104','锦江区','275','J',2,NULL,NULL,NULL,NULL,'0'),('2359','510105','青羊区','275','Q',2,NULL,NULL,NULL,NULL,'0'),('236','440300','深圳市','19','S',1,NULL,NULL,NULL,NULL,'0'),('2360','510106','金牛区','275','J',2,NULL,NULL,NULL,NULL,'0'),('2361','510107','武侯区','275','W',2,NULL,NULL,NULL,NULL,'0'),('2362','510108','成华区','275','C',2,NULL,NULL,NULL,NULL,'0'),('2363','510112','龙泉驿区','275','L',2,NULL,NULL,NULL,NULL,'0'),('2364','510113','青白江区','275','Q',2,NULL,NULL,NULL,NULL,'0'),('2365','510114','新都区','275','X',2,NULL,NULL,NULL,NULL,'0'),('2366','510115','温江区','275','W',2,NULL,NULL,NULL,NULL,'0'),('2367','510121','金堂县','275','J',2,NULL,NULL,NULL,NULL,'0'),('2368','510122','双流县','275','S',2,NULL,NULL,NULL,NULL,'0'),('2369','510124','郫县','275','P',2,NULL,NULL,NULL,NULL,'0'),('237','440400','珠海市','19','Z',1,NULL,NULL,NULL,NULL,'0'),('2370','510129','大邑县','275','D',2,NULL,NULL,NULL,NULL,'0'),('2371','510131','蒲江县','275','P',2,NULL,NULL,NULL,NULL,'0'),('2372','510132','新津县','275','X',2,NULL,NULL,NULL,NULL,'0'),('2373','510181','都江堰市','275','D',2,NULL,NULL,NULL,NULL,'0'),('2374','510182','彭州市','275','P',2,NULL,NULL,NULL,NULL,'0'),('2375','510183','邛崃市','275','Q',2,NULL,NULL,NULL,NULL,'0'),('2376','510184','崇州市','275','C',2,NULL,NULL,NULL,NULL,'0'),('2377','510302','自流井区','276','Z',2,NULL,NULL,NULL,NULL,'0'),('2378','510303','贡井区','276','G',2,NULL,NULL,NULL,NULL,'0'),('2379','510304','大安区','276','D',2,NULL,NULL,NULL,NULL,'0'),('238','440500','汕头市','19','S',1,NULL,NULL,NULL,NULL,'0'),('2380','510311','沿滩区','276','Y',2,NULL,NULL,NULL,NULL,'0'),('2381','510321','荣县','276','R',2,NULL,NULL,NULL,NULL,'0'),('2382','510322','富顺县','276','F',2,NULL,NULL,NULL,NULL,'0'),('2383','510402','东区','277','D',2,NULL,NULL,NULL,NULL,'0'),('2384','510403','西区','277','X',2,NULL,NULL,NULL,NULL,'0'),('2385','510411','仁和区','277','R',2,NULL,NULL,NULL,NULL,'0'),('2386','510421','米易县','277','M',2,NULL,NULL,NULL,NULL,'0'),('2387','510422','盐边县','277','Y',2,NULL,NULL,NULL,NULL,'0'),('2388','510502','江阳区','278','J',2,NULL,NULL,NULL,NULL,'0'),('2389','510503','纳溪区','278','N',2,NULL,NULL,NULL,NULL,'0'),('239','440600','佛山市','19','F',1,NULL,NULL,NULL,NULL,'0'),('2390','510504','龙马潭区','278','L',2,NULL,NULL,NULL,NULL,'0'),('2391','510521','泸县','278','L',2,NULL,NULL,NULL,NULL,'0'),('2392','510522','合江县','278','H',2,NULL,NULL,NULL,NULL,'0'),('2393','510524','叙永县','278','X',2,NULL,NULL,NULL,NULL,'0'),('2394','510525','古蔺县','278','G',2,NULL,NULL,NULL,NULL,'0'),('2395','510603','旌阳区','279','J',2,NULL,NULL,NULL,NULL,'0'),('2396','510623','中江县','279','Z',2,NULL,NULL,NULL,NULL,'0'),('2397','510626','罗江县','279','L',2,NULL,NULL,NULL,NULL,'0'),('2398','510681','广汉市','279','G',2,NULL,NULL,NULL,NULL,'0'),('2399','510682','什邡市','279','S',2,NULL,NULL,NULL,NULL,'0'),('24','520000','贵州省','0','G',0,NULL,NULL,NULL,NULL,'0'),('240','440700','江门市','19','J',1,NULL,NULL,NULL,NULL,'0'),('2400','510683','绵竹市','279','M',2,NULL,NULL,NULL,NULL,'0'),('2401','510703','涪城区','280','F',2,NULL,NULL,NULL,NULL,'0'),('2402','510704','游仙区','280','Y',2,NULL,NULL,NULL,NULL,'0'),('2403','510722','三台县','280','S',2,NULL,NULL,NULL,NULL,'0'),('2404','510723','盐亭县','280','Y',2,NULL,NULL,NULL,NULL,'0'),('2405','510724','安县','280','A',2,NULL,NULL,NULL,NULL,'0'),('2406','510725','梓潼县','280','Z',2,NULL,NULL,NULL,NULL,'0'),('2407','510726','北川羌族自治县','280','B',2,NULL,NULL,NULL,NULL,'0'),('2408','510727','平武县','280','P',2,NULL,NULL,NULL,NULL,'0'),('2409','510781','江油市','280','J',2,NULL,NULL,NULL,NULL,'0'),('241','440800','湛江市','19','Z',1,NULL,NULL,NULL,NULL,'0'),('2410','510802','利州区','281','L',2,NULL,NULL,NULL,NULL,'0'),('2411','510811','昭化区','281','Z',2,NULL,NULL,NULL,NULL,'0'),('2412','510812','朝天区','281','C',2,NULL,NULL,NULL,NULL,'0'),('2413','510821','旺苍县','281','W',2,NULL,NULL,NULL,NULL,'0'),('2414','510822','青川县','281','Q',2,NULL,NULL,NULL,NULL,'0'),('2415','510823','剑阁县','281','J',2,NULL,NULL,NULL,NULL,'0'),('2416','510824','苍溪县','281','C',2,NULL,NULL,NULL,NULL,'0'),('2417','510903','船山区','282','C',2,NULL,NULL,NULL,NULL,'0'),('2418','510904','安居区','282','A',2,NULL,NULL,NULL,NULL,'0'),('2419','510921','蓬溪县','282','P',2,NULL,NULL,NULL,NULL,'0'),('242','440900','茂名市','19','M',1,NULL,NULL,NULL,NULL,'0'),('2420','510922','射洪县','282','S',2,NULL,NULL,NULL,NULL,'0'),('2421','510923','大英县','282','D',2,NULL,NULL,NULL,NULL,'0'),('2422','511011','东兴区','283','D',2,NULL,NULL,NULL,NULL,'0'),('2423','511024','威远县','283','W',2,NULL,NULL,NULL,NULL,'0'),('2424','511025','资中县','283','Z',2,NULL,NULL,NULL,NULL,'0'),('2425','511028','隆昌县','283','L',2,NULL,NULL,NULL,NULL,'0'),('2427','511102','市中区','284','S',2,NULL,NULL,NULL,NULL,'0'),('2428','511111','沙湾区','284','S',2,NULL,NULL,NULL,NULL,'0'),('2429','511112','五通桥区','284','W',2,NULL,NULL,NULL,NULL,'0'),('243','441200','肇庆市','19','Z',1,NULL,NULL,NULL,NULL,'0'),('2430','511113','金口河区','284','J',2,NULL,NULL,NULL,NULL,'0'),('2431','511123','犍为县','284','J',2,NULL,NULL,NULL,NULL,'0'),('2432','511124','井研县','284','J',2,NULL,NULL,NULL,NULL,'0'),('2433','511126','夹江县','284','J',2,NULL,NULL,NULL,NULL,'0'),('2434','511129','沐川县','284','M',2,NULL,NULL,NULL,NULL,'0'),('2435','511132','峨边彝族自治县','284','E',2,NULL,NULL,NULL,NULL,'0'),('2436','511133','马边彝族自治县','284','M',2,NULL,NULL,NULL,NULL,'0'),('2437','511181','峨眉山市','284','E',2,NULL,NULL,NULL,NULL,'0'),('2438','511302','顺庆区','285','S',2,NULL,NULL,NULL,NULL,'0'),('2439','511303','高坪区','285','G',2,NULL,NULL,NULL,NULL,'0'),('244','441300','惠州市','19','H',1,NULL,NULL,NULL,NULL,'0'),('2440','511304','嘉陵区','285','J',2,NULL,NULL,NULL,NULL,'0'),('2441','511321','南部县','285','N',2,NULL,NULL,NULL,NULL,'0'),('2442','511322','营山县','285','Y',2,NULL,NULL,NULL,NULL,'0'),('2443','511323','蓬安县','285','P',2,NULL,NULL,NULL,NULL,'0'),('2444','511324','仪陇县','285','Y',2,NULL,NULL,NULL,NULL,'0'),('2445','511325','西充县','285','X',2,NULL,NULL,NULL,NULL,'0'),('2446','511381','阆中市','285','L',2,NULL,NULL,NULL,NULL,'0'),('2447','511402','东坡区','286','D',2,NULL,NULL,NULL,NULL,'0'),('2448','511421','仁寿县','286','R',2,NULL,NULL,NULL,NULL,'0'),('2449','511422','彭山县','286','P',2,NULL,NULL,NULL,NULL,'0'),('245','441400','梅州市','19','M',1,NULL,NULL,NULL,NULL,'0'),('2450','511423','洪雅县','286','H',2,NULL,NULL,NULL,NULL,'0'),('2451','511424','丹棱县','286','D',2,NULL,NULL,NULL,NULL,'0'),('2452','511425','青神县','286','Q',2,NULL,NULL,NULL,NULL,'0'),('2453','511502','翠屏区','287','C',2,NULL,NULL,NULL,NULL,'0'),('2454','511503','南溪区','287','N',2,NULL,NULL,NULL,NULL,'0'),('2455','511521','宜宾县','287','Y',2,NULL,NULL,NULL,NULL,'0'),('2456','511523','江安县','287','J',2,NULL,NULL,NULL,NULL,'0'),('2457','511524','长宁县','287','C',2,NULL,NULL,NULL,NULL,'0'),('2458','511525','高县','287','G',2,NULL,NULL,NULL,NULL,'0'),('2459','511526','珙县','287','G',2,NULL,NULL,NULL,NULL,'0'),('246','441500','汕尾市','19','S',1,NULL,NULL,NULL,NULL,'0'),('2460','511527','筠连县','287','J',2,NULL,NULL,NULL,NULL,'0'),('2461','511528','兴文县','287','X',2,NULL,NULL,NULL,NULL,'0'),('2462','511529','屏山县','287','P',2,NULL,NULL,NULL,NULL,'0'),('2463','511602','广安区','288','G',2,NULL,NULL,NULL,NULL,'0'),('2464','511603','前锋区','288','Q',2,NULL,NULL,NULL,NULL,'0'),('2465','511621','岳池县','288','Y',2,NULL,NULL,NULL,NULL,'0'),('2466','511622','武胜县','288','W',2,NULL,NULL,NULL,NULL,'0'),('2467','511623','邻水县','288','L',2,NULL,NULL,NULL,NULL,'0'),('2468','511681','华蓥市','288','H',2,NULL,NULL,NULL,NULL,'0'),('2469','511702','通川区','289','T',2,NULL,NULL,NULL,NULL,'0'),('247','441600','河源市','19','H',1,NULL,NULL,NULL,NULL,'0'),('2470','511703','达川区','289','D',2,NULL,NULL,NULL,NULL,'0'),('2471','511722','宣汉县','289','X',2,NULL,NULL,NULL,NULL,'0'),('2472','511723','开江县','289','K',2,NULL,NULL,NULL,NULL,'0'),('2473','511724','大竹县','289','D',2,NULL,NULL,NULL,NULL,'0'),('2474','511725','渠县','289','Q',2,NULL,NULL,NULL,NULL,'0'),('2475','511781','万源市','289','W',2,NULL,NULL,NULL,NULL,'0'),('2476','511802','雨城区','290','Y',2,NULL,NULL,NULL,NULL,'0'),('2477','511803','名山区','290','M',2,NULL,NULL,NULL,NULL,'0'),('2478','511822','荥经县','290','Y',2,NULL,NULL,NULL,NULL,'0'),('2479','511823','汉源县','290','H',2,NULL,NULL,NULL,NULL,'0'),('248','441700','阳江市','19','Y',1,NULL,NULL,NULL,NULL,'0'),('2480','511824','石棉县','290','S',2,NULL,NULL,NULL,NULL,'0'),('2481','511825','天全县','290','T',2,NULL,NULL,NULL,NULL,'0'),('2482','511826','芦山县','290','L',2,NULL,NULL,NULL,NULL,'0'),('2483','511827','宝兴县','290','B',2,NULL,NULL,NULL,NULL,'0'),('2484','511902','巴州区','291','B',2,NULL,NULL,NULL,NULL,'0'),('2485','511903','恩阳区','291','E',2,NULL,NULL,NULL,NULL,'0'),('2486','511921','通江县','291','T',2,NULL,NULL,NULL,NULL,'0'),('2487','511922','南江县','291','N',2,NULL,NULL,NULL,NULL,'0'),('2488','511923','平昌县','291','P',2,NULL,NULL,NULL,NULL,'0'),('2489','512002','雁江区','292','Y',2,NULL,NULL,NULL,NULL,'0'),('249','441800','清远市','19','Q',1,NULL,NULL,NULL,NULL,'0'),('2490','512021','安岳县','292','A',2,NULL,NULL,NULL,NULL,'0'),('2491','512022','乐至县','292','L',2,NULL,NULL,NULL,NULL,'0'),('2492','512081','简阳市','292','J',2,NULL,NULL,NULL,NULL,'0'),('2493','513221','汶川县','293','W',2,NULL,NULL,NULL,NULL,'0'),('2494','513222','理县','293','L',2,NULL,NULL,NULL,NULL,'0'),('2495','513223','茂县','293','M',2,NULL,NULL,NULL,NULL,'0'),('2496','513224','松潘县','293','S',2,NULL,NULL,NULL,NULL,'0'),('2497','513225','九寨沟县','293','J',2,NULL,NULL,NULL,NULL,'0'),('2498','513226','金川县','293','J',2,NULL,NULL,NULL,NULL,'0'),('2499','513227','小金县','293','X',2,NULL,NULL,NULL,NULL,'0'),('25','530000','云南省','0','Y',0,NULL,NULL,NULL,NULL,'0'),('250','441900','东莞市','19','D',1,NULL,NULL,NULL,NULL,'0'),('2500','513228','黑水县','293','H',2,NULL,NULL,NULL,NULL,'0'),('2501','513229','马尔康县','293','M',2,NULL,NULL,NULL,NULL,'0'),('2502','513230','壤塘县','293','R',2,NULL,NULL,NULL,NULL,'0'),('2503','513231','阿坝县','293','A',2,NULL,NULL,NULL,NULL,'0'),('2504','513232','若尔盖县','293','R',2,NULL,NULL,NULL,NULL,'0'),('2505','513233','红原县','293','H',2,NULL,NULL,NULL,NULL,'0'),('2506','513321','康定县','294','K',2,NULL,NULL,NULL,NULL,'0'),('2507','513322','泸定县','294','L',2,NULL,NULL,NULL,NULL,'0'),('2508','513323','丹巴县','294','D',2,NULL,NULL,NULL,NULL,'0'),('2509','513324','九龙县','294','J',2,NULL,NULL,NULL,NULL,'0'),('251','442000','中山市','19','Z',1,NULL,NULL,NULL,NULL,'0'),('2510','513325','雅江县','294','Y',2,NULL,NULL,NULL,NULL,'0'),('2511','513326','道孚县','294','D',2,NULL,NULL,NULL,NULL,'0'),('2512','513327','炉霍县','294','L',2,NULL,NULL,NULL,NULL,'0'),('2513','513328','甘孜县','294','G',2,NULL,NULL,NULL,NULL,'0'),('2514','513329','新龙县','294','X',2,NULL,NULL,NULL,NULL,'0'),('2515','513330','德格县','294','D',2,NULL,NULL,NULL,NULL,'0'),('2516','513331','白玉县','294','B',2,NULL,NULL,NULL,NULL,'0'),('2517','513332','石渠县','294','S',2,NULL,NULL,NULL,NULL,'0'),('2518','513333','色达县','294','S',2,NULL,NULL,NULL,NULL,'0'),('2519','513334','理塘县','294','L',2,NULL,NULL,NULL,NULL,'0'),('252','445100','潮州市','19','C',1,NULL,NULL,NULL,NULL,'0'),('2520','513335','巴塘县','294','B',2,NULL,NULL,NULL,NULL,'0'),('2521','513336','乡城县','294','X',2,NULL,NULL,NULL,NULL,'0'),('2522','513337','稻城县','294','D',2,NULL,NULL,NULL,NULL,'0'),('2523','513338','得荣县','294','D',2,NULL,NULL,NULL,NULL,'0'),('2524','513401','西昌市','295','X',2,NULL,NULL,NULL,NULL,'0'),('2525','513422','木里藏族自治县','295','M',2,NULL,NULL,NULL,NULL,'0'),('2526','513423','盐源县','295','Y',2,NULL,NULL,NULL,NULL,'0'),('2527','513424','德昌县','295','D',2,NULL,NULL,NULL,NULL,'0'),('2528','513425','会理县','295','H',2,NULL,NULL,NULL,NULL,'0'),('2529','513426','会东县','295','H',2,NULL,NULL,NULL,NULL,'0'),('253','445200','揭阳市','19','J',1,NULL,NULL,NULL,NULL,'0'),('2530','513427','宁南县','295','N',2,NULL,NULL,NULL,NULL,'0'),('2531','513428','普格县','295','P',2,NULL,NULL,NULL,NULL,'0'),('2532','513429','布拖县','295','B',2,NULL,NULL,NULL,NULL,'0'),('2533','513430','金阳县','295','J',2,NULL,NULL,NULL,NULL,'0'),('2534','513431','昭觉县','295','Z',2,NULL,NULL,NULL,NULL,'0'),('2535','513432','喜德县','295','X',2,NULL,NULL,NULL,NULL,'0'),('2536','513433','冕宁县','295','M',2,NULL,NULL,NULL,NULL,'0'),('2537','513434','越西县','295','Y',2,NULL,NULL,NULL,NULL,'0'),('2538','513435','甘洛县','295','G',2,NULL,NULL,NULL,NULL,'0'),('2539','513436','美姑县','295','M',2,NULL,NULL,NULL,NULL,'0'),('254','445300','云浮市','19','Y',1,NULL,NULL,NULL,NULL,'0'),('2540','513437','雷波县','295','L',2,NULL,NULL,NULL,NULL,'0'),('2541','520102','南明区','296','N',2,NULL,NULL,NULL,NULL,'0'),('2542','520103','云岩区','296','Y',2,NULL,NULL,NULL,NULL,'0'),('2543','520111','花溪区','296','H',2,NULL,NULL,NULL,NULL,'0'),('2544','520112','乌当区','296','W',2,NULL,NULL,NULL,NULL,'0'),('2545','520113','白云区','296','B',2,NULL,NULL,NULL,NULL,'0'),('2546','520115','观山湖区','296','G',2,NULL,NULL,NULL,NULL,'0'),('2547','520121','开阳县','296','K',2,NULL,NULL,NULL,NULL,'0'),('2548','520122','息烽县','296','X',2,NULL,NULL,NULL,NULL,'0'),('2549','520123','修文县','296','X',2,NULL,NULL,NULL,NULL,'0'),('255','450100','南宁市','20','N',1,NULL,NULL,NULL,NULL,'0'),('2550','520181','清镇市','296','Q',2,NULL,NULL,NULL,NULL,'0'),('2551','520201','钟山区','297','Z',2,NULL,NULL,NULL,NULL,'0'),('2552','520203','六枝特区','297','L',2,NULL,NULL,NULL,NULL,'0'),('2553','520221','水城县','297','S',2,NULL,NULL,NULL,NULL,'0'),('2554','520222','盘县','297','P',2,NULL,NULL,NULL,NULL,'0'),('2555','520302','红花岗区','298','H',2,NULL,NULL,NULL,NULL,'0'),('2556','520303','汇川区','298','H',2,NULL,NULL,NULL,NULL,'0'),('2557','520321','遵义县','298','Z',2,NULL,NULL,NULL,NULL,'0'),('2558','520322','桐梓县','298','T',2,NULL,NULL,NULL,NULL,'0'),('2559','520323','绥阳县','298','S',2,NULL,NULL,NULL,NULL,'0'),('256','450200','柳州市','20','L',1,NULL,NULL,NULL,NULL,'0'),('2560','520324','正安县','298','Z',2,NULL,NULL,NULL,NULL,'0'),('2561','520325','道真仡佬族苗族自治县','298','D',2,NULL,NULL,NULL,NULL,'0'),('2562','520326','务川仡佬族苗族自治县','298','W',2,NULL,NULL,NULL,NULL,'0'),('2563','520327','凤冈县','298','F',2,NULL,NULL,NULL,NULL,'0'),('2564','520328','湄潭县','298','M',2,NULL,NULL,NULL,NULL,'0'),('2565','520329','余庆县','298','Y',2,NULL,NULL,NULL,NULL,'0'),('2566','520330','习水县','298','X',2,NULL,NULL,NULL,NULL,'0'),('2567','520381','赤水市','298','C',2,NULL,NULL,NULL,NULL,'0'),('2568','520382','仁怀市','298','R',2,NULL,NULL,NULL,NULL,'0'),('2569','520402','西秀区','299','X',2,NULL,NULL,NULL,NULL,'0'),('257','450300','桂林市','20','G',1,NULL,NULL,NULL,NULL,'0'),('2570','520421','平坝县','299','P',2,NULL,NULL,NULL,NULL,'0'),('2571','520422','普定县','299','P',2,NULL,NULL,NULL,NULL,'0'),('2572','520423','镇宁布依族苗族自治县','299','Z',2,NULL,NULL,NULL,NULL,'0'),('2573','520424','关岭布依族苗族自治县','299','G',2,NULL,NULL,NULL,NULL,'0'),('2574','520425','紫云苗族布依族自治县','299','Z',2,NULL,NULL,NULL,NULL,'0'),('2575','520502','七星关区','300','Q',2,NULL,NULL,NULL,NULL,'0'),('2576','520521','大方县','300','D',2,NULL,NULL,NULL,NULL,'0'),('2577','520522','黔西县','300','Q',2,NULL,NULL,NULL,NULL,'0'),('2578','520523','金沙县','300','J',2,NULL,NULL,NULL,NULL,'0'),('2579','520524','织金县','300','Z',2,NULL,NULL,NULL,NULL,'0'),('258','450400','梧州市','20','W',1,NULL,NULL,NULL,NULL,'0'),('2580','520525','纳雍县','300','N',2,NULL,NULL,NULL,NULL,'0'),('2581','520526','威宁彝族回族苗族自治县','300','W',2,NULL,NULL,NULL,NULL,'0'),('2582','520527','赫章县','300','H',2,NULL,NULL,NULL,NULL,'0'),('2583','520602','碧江区','301','B',2,NULL,NULL,NULL,NULL,'0'),('2584','520603','万山区','301','W',2,NULL,NULL,NULL,NULL,'0'),('2585','520621','江口县','301','J',2,NULL,NULL,NULL,NULL,'0'),('2586','520622','玉屏侗族自治县','301','Y',2,NULL,NULL,NULL,NULL,'0'),('2587','520623','石阡县','301','S',2,NULL,NULL,NULL,NULL,'0'),('2588','520624','思南县','301','S',2,NULL,NULL,NULL,NULL,'0'),('2589','520625','印江土家族苗族自治县','301','Y',2,NULL,NULL,NULL,NULL,'0'),('259','450500','北海市','20','B',1,NULL,NULL,NULL,NULL,'0'),('2590','520626','德江县','301','D',2,NULL,NULL,NULL,NULL,'0'),('2591','520627','沿河土家族自治县','301','Y',2,NULL,NULL,NULL,NULL,'0'),('2592','520628','松桃苗族自治县','301','S',2,NULL,NULL,NULL,NULL,'0'),('2593','522301','兴义市','302','X',2,NULL,NULL,NULL,NULL,'0'),('2594','522322','兴仁县','302','X',2,NULL,NULL,NULL,NULL,'0'),('2595','522323','普安县','302','P',2,NULL,NULL,NULL,NULL,'0'),('2596','522324','晴隆县','302','Q',2,NULL,NULL,NULL,NULL,'0'),('2597','522325','贞丰县','302','Z',2,NULL,NULL,NULL,NULL,'0'),('2598','522326','望谟县','302','W',2,NULL,NULL,NULL,NULL,'0'),('2599','522327','册亨县','302','C',2,NULL,NULL,NULL,NULL,'0'),('26','540000','西藏自治区','0','X',0,NULL,NULL,NULL,NULL,'0'),('260','450600','防城港市','20','F',1,NULL,NULL,NULL,NULL,'0'),('2600','522328','安龙县','302','A',2,NULL,NULL,NULL,NULL,'0'),('2601','522601','凯里市','303','K',2,NULL,NULL,NULL,NULL,'0'),('2602','522622','黄平县','303','H',2,NULL,NULL,NULL,NULL,'0'),('2603','522623','施秉县','303','S',2,NULL,NULL,NULL,NULL,'0'),('2604','522624','三穗县','303','S',2,NULL,NULL,NULL,NULL,'0'),('2605','522625','镇远县','303','Z',2,NULL,NULL,NULL,NULL,'0'),('2606','522626','岑巩县','303','C',2,NULL,NULL,NULL,NULL,'0'),('2607','522627','天柱县','303','T',2,NULL,NULL,NULL,NULL,'0'),('2608','522628','锦屏县','303','J',2,NULL,NULL,NULL,NULL,'0'),('2609','522629','剑河县','303','J',2,NULL,NULL,NULL,NULL,'0'),('261','450700','钦州市','20','Q',1,NULL,NULL,NULL,NULL,'0'),('2610','522630','台江县','303','T',2,NULL,NULL,NULL,NULL,'0'),('2611','522631','黎平县','303','L',2,NULL,NULL,NULL,NULL,'0'),('2612','522632','榕江县','303','R',2,NULL,NULL,NULL,NULL,'0'),('2613','522633','从江县','303','C',2,NULL,NULL,NULL,NULL,'0'),('2614','522634','雷山县','303','L',2,NULL,NULL,NULL,NULL,'0'),('2615','522635','麻江县','303','M',2,NULL,NULL,NULL,NULL,'0'),('2616','522636','丹寨县','303','D',2,NULL,NULL,NULL,NULL,'0'),('2617','522701','都匀市','304','D',2,NULL,NULL,NULL,NULL,'0'),('2618','522702','福泉市','304','F',2,NULL,NULL,NULL,NULL,'0'),('2619','522722','荔波县','304','L',2,NULL,NULL,NULL,NULL,'0'),('262','450800','贵港市','20','G',1,NULL,NULL,NULL,NULL,'0'),('2620','522723','贵定县','304','G',2,NULL,NULL,NULL,NULL,'0'),('2621','522725','瓮安县','304','W',2,NULL,NULL,NULL,NULL,'0'),('2622','522726','独山县','304','D',2,NULL,NULL,NULL,NULL,'0'),('2623','522727','平塘县','304','P',2,NULL,NULL,NULL,NULL,'0'),('2624','522728','罗甸县','304','L',2,NULL,NULL,NULL,NULL,'0'),('2625','522729','长顺县','304','C',2,NULL,NULL,NULL,NULL,'0'),('2626','522730','龙里县','304','L',2,NULL,NULL,NULL,NULL,'0'),('2627','522731','惠水县','304','H',2,NULL,NULL,NULL,NULL,'0'),('2628','522732','三都水族自治县','304','S',2,NULL,NULL,NULL,NULL,'0'),('2629','530102','五华区','305','W',2,NULL,NULL,NULL,NULL,'0'),('263','450900','玉林市','20','Y',1,NULL,NULL,NULL,NULL,'0'),('2630','530103','盘龙区','305','P',2,NULL,NULL,NULL,NULL,'0'),('2631','530111','官渡区','305','G',2,NULL,NULL,NULL,NULL,'0'),('2632','530112','西山区','305','X',2,NULL,NULL,NULL,NULL,'0'),('2633','530113','东川区','305','D',2,NULL,NULL,NULL,NULL,'0'),('2634','530114','呈贡区','305','C',2,NULL,NULL,NULL,NULL,'0'),('2635','530122','晋宁县','305','J',2,NULL,NULL,NULL,NULL,'0'),('2636','530124','富民县','305','F',2,NULL,NULL,NULL,NULL,'0'),('2637','530125','宜良县','305','Y',2,NULL,NULL,NULL,NULL,'0'),('2638','530126','石林彝族自治县','305','S',2,NULL,NULL,NULL,NULL,'0'),('2639','530127','嵩明县','305','S',2,NULL,NULL,NULL,NULL,'0'),('264','451000','百色市','20','B',1,NULL,NULL,NULL,NULL,'0'),('2640','530128','禄劝彝族苗族自治县','305','L',2,NULL,NULL,NULL,NULL,'0'),('2641','530129','寻甸回族彝族自治县','305','X',2,NULL,NULL,NULL,NULL,'0'),('2642','530181','安宁市','305','A',2,NULL,NULL,NULL,NULL,'0'),('2643','530302','麒麟区','306','Q',2,NULL,NULL,NULL,NULL,'0'),('2644','530321','马龙县','306','M',2,NULL,NULL,NULL,NULL,'0'),('2645','530322','陆良县','306','L',2,NULL,NULL,NULL,NULL,'0'),('2646','530323','师宗县','306','S',2,NULL,NULL,NULL,NULL,'0'),('2647','530324','罗平县','306','L',2,NULL,NULL,NULL,NULL,'0'),('2648','530325','富源县','306','F',2,NULL,NULL,NULL,NULL,'0'),('2649','530326','会泽县','306','H',2,NULL,NULL,NULL,NULL,'0'),('265','451100','贺州市','20','H',1,NULL,NULL,NULL,NULL,'0'),('2650','530328','沾益县','306','Z',2,NULL,NULL,NULL,NULL,'0'),('2651','530381','宣威市','306','X',2,NULL,NULL,NULL,NULL,'0'),('2652','530402','红塔区','307','H',2,NULL,NULL,NULL,NULL,'0'),('2653','530421','江川县','307','J',2,NULL,NULL,NULL,NULL,'0'),('2654','530422','澄江县','307','C',2,NULL,NULL,NULL,NULL,'0'),('2655','530423','通海县','307','T',2,NULL,NULL,NULL,NULL,'0'),('2656','530424','华宁县','307','H',2,NULL,NULL,NULL,NULL,'0'),('2657','530425','易门县','307','Y',2,NULL,NULL,NULL,NULL,'0'),('2658','530426','峨山彝族自治县','307','E',2,NULL,NULL,NULL,NULL,'0'),('2659','530427','新平彝族傣族自治县','307','X',2,NULL,NULL,NULL,NULL,'0'),('266','451200','河池市','20','H',1,NULL,NULL,NULL,NULL,'0'),('2660','530428','元江哈尼族彝族傣族自治县','307','Y',2,NULL,NULL,NULL,NULL,'0'),('2661','530502','隆阳区','308','L',2,NULL,NULL,NULL,NULL,'0'),('2662','530521','施甸县','308','S',2,NULL,NULL,NULL,NULL,'0'),('2663','530522','腾冲县','308','T',2,NULL,NULL,NULL,NULL,'0'),('2664','530523','龙陵县','308','L',2,NULL,NULL,NULL,NULL,'0'),('2665','530524','昌宁县','308','C',2,NULL,NULL,NULL,NULL,'0'),('2666','530602','昭阳区','309','Z',2,NULL,NULL,NULL,NULL,'0'),('2667','530621','鲁甸县','309','L',2,NULL,NULL,NULL,NULL,'0'),('2668','530622','巧家县','309','Q',2,NULL,NULL,NULL,NULL,'0'),('2669','530623','盐津县','309','Y',2,NULL,NULL,NULL,NULL,'0'),('267','451300','来宾市','20','L',1,NULL,NULL,NULL,NULL,'0'),('2670','530624','大关县','309','D',2,NULL,NULL,NULL,NULL,'0'),('2671','530625','永善县','309','Y',2,NULL,NULL,NULL,NULL,'0'),('2672','530626','绥江县','309','S',2,NULL,NULL,NULL,NULL,'0'),('2673','530627','镇雄县','309','Z',2,NULL,NULL,NULL,NULL,'0'),('2674','530628','彝良县','309','Y',2,NULL,NULL,NULL,NULL,'0'),('2675','530629','威信县','309','W',2,NULL,NULL,NULL,NULL,'0'),('2676','530630','水富县','309','S',2,NULL,NULL,NULL,NULL,'0'),('2677','530702','古城区','310','G',2,NULL,NULL,NULL,NULL,'0'),('2678','530721','玉龙纳西族自治县','310','Y',2,NULL,NULL,NULL,NULL,'0'),('2679','530722','永胜县','310','Y',2,NULL,NULL,NULL,NULL,'0'),('268','451400','崇左市','20','C',1,NULL,NULL,NULL,NULL,'0'),('2680','530723','华坪县','310','H',2,NULL,NULL,NULL,NULL,'0'),('2681','530724','宁蒗彝族自治县','310','N',2,NULL,NULL,NULL,NULL,'0'),('2682','530802','思茅区','311','S',2,NULL,NULL,NULL,NULL,'0'),('2683','530821','宁洱哈尼族彝族自治县','311','N',2,NULL,NULL,NULL,NULL,'0'),('2684','530822','墨江哈尼族自治县','311','M',2,NULL,NULL,NULL,NULL,'0'),('2685','530823','景东彝族自治县','311','J',2,NULL,NULL,NULL,NULL,'0'),('2686','530824','景谷傣族彝族自治县','311','J',2,NULL,NULL,NULL,NULL,'0'),('2687','530825','镇沅彝族哈尼族拉祜族自治县','311','Z',2,NULL,NULL,NULL,NULL,'0'),('2688','530826','江城哈尼族彝族自治县','311','J',2,NULL,NULL,NULL,NULL,'0'),('2689','530827','孟连傣族拉祜族佤族自治县','311','M',2,NULL,NULL,NULL,NULL,'0'),('269','460100','海口市','21','H',1,NULL,NULL,NULL,NULL,'0'),('2690','530828','澜沧拉祜族自治县','311','L',2,NULL,NULL,NULL,NULL,'0'),('2691','530829','西盟佤族自治县','311','X',2,NULL,NULL,NULL,NULL,'0'),('2692','530902','临翔区','312','L',2,NULL,NULL,NULL,NULL,'0'),('2693','530921','凤庆县','312','F',2,NULL,NULL,NULL,NULL,'0'),('2694','530922','云县','312','Y',2,NULL,NULL,NULL,NULL,'0'),('2695','530923','永德县','312','Y',2,NULL,NULL,NULL,NULL,'0'),('2696','530924','镇康县','312','Z',2,NULL,NULL,NULL,NULL,'0'),('2697','530925','双江拉祜族佤族布朗族傣族自治县','312','S',2,NULL,NULL,NULL,NULL,'0'),('2698','530926','耿马傣族佤族自治县','312','G',2,NULL,NULL,NULL,NULL,'0'),('2699','530927','沧源佤族自治县','312','C',2,NULL,NULL,NULL,NULL,'0'),('27','610000','陕西省','0','S',0,NULL,NULL,NULL,NULL,'0'),('270','460200','三亚市','21','S',1,NULL,NULL,NULL,NULL,'0'),('2700','532301','楚雄市','313','C',2,NULL,NULL,NULL,NULL,'0'),('2701','532322','双柏县','313','S',2,NULL,NULL,NULL,NULL,'0'),('2702','532323','牟定县','313','M',2,NULL,NULL,NULL,NULL,'0'),('2703','532324','南华县','313','N',2,NULL,NULL,NULL,NULL,'0'),('2704','532325','姚安县','313','Y',2,NULL,NULL,NULL,NULL,'0'),('2705','532326','大姚县','313','D',2,NULL,NULL,NULL,NULL,'0'),('2706','532327','永仁县','313','Y',2,NULL,NULL,NULL,NULL,'0'),('2707','532328','元谋县','313','Y',2,NULL,NULL,NULL,NULL,'0'),('2708','532329','武定县','313','W',2,NULL,NULL,NULL,NULL,'0'),('2709','532331','禄丰县','313','L',2,NULL,NULL,NULL,NULL,'0'),('271','460300','三沙市','21','S',1,NULL,NULL,NULL,NULL,'0'),('2710','532501','个旧市','314','G',2,NULL,NULL,NULL,NULL,'0'),('2711','532502','开远市','314','K',2,NULL,NULL,NULL,NULL,'0'),('2712','532503','蒙自市','314','M',2,NULL,NULL,NULL,NULL,'0'),('2713','532504','弥勒市','314','M',2,NULL,NULL,NULL,NULL,'0'),('2714','532523','屏边苗族自治县','314','P',2,NULL,NULL,NULL,NULL,'0'),('2715','532524','建水县','314','J',2,NULL,NULL,NULL,NULL,'0'),('2716','532525','石屏县','314','S',2,NULL,NULL,NULL,NULL,'0'),('2717','532527','泸西县','314','L',2,NULL,NULL,NULL,NULL,'0'),('2718','532528','元阳县','314','Y',2,NULL,NULL,NULL,NULL,'0'),('2719','532529','红河县','314','H',2,NULL,NULL,NULL,NULL,'0'),('272','469000','省直辖县级行政区划','21','S',1,NULL,NULL,NULL,NULL,'0'),('2720','532530','金平苗族瑶族傣族自治县','314','J',2,NULL,NULL,NULL,NULL,'0'),('2721','532531','绿春县','314','L',2,NULL,NULL,NULL,NULL,'0'),('2722','532532','河口瑶族自治县','314','H',2,NULL,NULL,NULL,NULL,'0'),('2723','532601','文山市','315','W',2,NULL,NULL,NULL,NULL,'0'),('2724','532622','砚山县','315','Y',2,NULL,NULL,NULL,NULL,'0'),('2725','532623','西畴县','315','X',2,NULL,NULL,NULL,NULL,'0'),('2726','532624','麻栗坡县','315','M',2,NULL,NULL,NULL,NULL,'0'),('2727','532625','马关县','315','M',2,NULL,NULL,NULL,NULL,'0'),('2728','532626','丘北县','315','Q',2,NULL,NULL,NULL,NULL,'0'),('2729','532627','广南县','315','G',2,NULL,NULL,NULL,NULL,'0'),('273','500100','重庆市','22','C',1,NULL,NULL,NULL,NULL,'0'),('2730','532628','富宁县','315','F',2,NULL,NULL,NULL,NULL,'0'),('2731','532801','景洪市','316','J',2,NULL,NULL,NULL,NULL,'0'),('2732','532822','勐海县','316','M',2,NULL,NULL,NULL,NULL,'0'),('2733','532823','勐腊县','316','M',2,NULL,NULL,NULL,NULL,'0'),('2734','532901','大理市','317','D',2,NULL,NULL,NULL,NULL,'0'),('2735','532922','漾濞彝族自治县','317','Y',2,NULL,NULL,NULL,NULL,'0'),('2736','532923','祥云县','317','X',2,NULL,NULL,NULL,NULL,'0'),('2737','532924','宾川县','317','B',2,NULL,NULL,NULL,NULL,'0'),('2738','532925','弥渡县','317','M',2,NULL,NULL,NULL,NULL,'0'),('2739','532926','南涧彝族自治县','317','N',2,NULL,NULL,NULL,NULL,'0'),('274','500200','重庆县','22','C',1,NULL,NULL,NULL,NULL,'0'),('2740','532927','巍山彝族回族自治县','317','W',2,NULL,NULL,NULL,NULL,'0'),('2741','532928','永平县','317','Y',2,NULL,NULL,NULL,NULL,'0'),('2742','532929','云龙县','317','Y',2,NULL,NULL,NULL,NULL,'0'),('2743','532930','洱源县','317','E',2,NULL,NULL,NULL,NULL,'0'),('2744','532931','剑川县','317','J',2,NULL,NULL,NULL,NULL,'0'),('2745','532932','鹤庆县','317','H',2,NULL,NULL,NULL,NULL,'0'),('2746','533102','瑞丽市','318','R',2,NULL,NULL,NULL,NULL,'0'),('2747','533103','芒市','318','M',2,NULL,NULL,NULL,NULL,'0'),('2748','533122','梁河县','318','L',2,NULL,NULL,NULL,NULL,'0'),('2749','533123','盈江县','318','Y',2,NULL,NULL,NULL,NULL,'0'),('275','510100','成都市','23','C',1,NULL,NULL,NULL,NULL,'0'),('2750','533124','陇川县','318','L',2,NULL,NULL,NULL,NULL,'0'),('2751','533321','泸水县','319','L',2,NULL,NULL,NULL,NULL,'0'),('2752','533323','福贡县','319','F',2,NULL,NULL,NULL,NULL,'0'),('2753','533324','贡山独龙族怒族自治县','319','G',2,NULL,NULL,NULL,NULL,'0'),('2754','533325','兰坪白族普米族自治县','319','L',2,NULL,NULL,NULL,NULL,'0'),('2755','533421','香格里拉县','320','X',2,NULL,NULL,NULL,NULL,'0'),('2756','533422','德钦县','320','D',2,NULL,NULL,NULL,NULL,'0'),('2757','533423','维西傈僳族自治县','320','W',2,NULL,NULL,NULL,NULL,'0'),('2758','540102','城关区','321','C',2,NULL,NULL,NULL,NULL,'0'),('2759','540121','林周县','321','L',2,NULL,NULL,NULL,NULL,'0'),('276','510300','自贡市','23','Z',1,NULL,NULL,NULL,NULL,'0'),('2760','540122','当雄县','321','D',2,NULL,NULL,NULL,NULL,'0'),('2761','540123','尼木县','321','N',2,NULL,NULL,NULL,NULL,'0'),('2762','540124','曲水县','321','Q',2,NULL,NULL,NULL,NULL,'0'),('2763','540125','堆龙德庆县','321','D',2,NULL,NULL,NULL,NULL,'0'),('2764','540126','达孜县','321','D',2,NULL,NULL,NULL,NULL,'0'),('2765','540127','墨竹工卡县','321','M',2,NULL,NULL,NULL,NULL,'0'),('2766','540202','桑珠孜区','322','S',2,NULL,NULL,NULL,NULL,'0'),('2767','540221','南木林县','322','N',2,NULL,NULL,NULL,NULL,'0'),('2768','540222','江孜县','322','J',2,NULL,NULL,NULL,NULL,'0'),('2769','540223','定日县','322','D',2,NULL,NULL,NULL,NULL,'0'),('277','510400','攀枝花市','23','P',1,NULL,NULL,NULL,NULL,'0'),('2770','540224','萨迦县','322','S',2,NULL,NULL,NULL,NULL,'0'),('2771','540225','拉孜县','322','L',2,NULL,NULL,NULL,NULL,'0'),('2772','540226','昂仁县','322','A',2,NULL,NULL,NULL,NULL,'0'),('2773','540227','谢通门县','322','X',2,NULL,NULL,NULL,NULL,'0'),('2774','540228','白朗县','322','B',2,NULL,NULL,NULL,NULL,'0'),('2775','540229','仁布县','322','R',2,NULL,NULL,NULL,NULL,'0'),('2776','540230','康马县','322','K',2,NULL,NULL,NULL,NULL,'0'),('2777','540231','定结县','322','D',2,NULL,NULL,NULL,NULL,'0'),('2778','540232','仲巴县','322','Z',2,NULL,NULL,NULL,NULL,'0'),('2779','540233','亚东县','322','Y',2,NULL,NULL,NULL,NULL,'0'),('278','510500','泸州市','23','L',1,NULL,NULL,NULL,NULL,'0'),('2780','540234','吉隆县','322','J',2,NULL,NULL,NULL,NULL,'0'),('2781','540235','聂拉木县','322','N',2,NULL,NULL,NULL,NULL,'0'),('2782','540236','萨嘎县','322','S',2,NULL,NULL,NULL,NULL,'0'),('2783','540237','岗巴县','322','G',2,NULL,NULL,NULL,NULL,'0'),('2784','542121','昌都县','323','C',2,NULL,NULL,NULL,NULL,'0'),('2785','542122','江达县','323','J',2,NULL,NULL,NULL,NULL,'0'),('2786','542123','贡觉县','323','G',2,NULL,NULL,NULL,NULL,'0'),('2787','542124','类乌齐县','323','L',2,NULL,NULL,NULL,NULL,'0'),('2788','542125','丁青县','323','D',2,NULL,NULL,NULL,NULL,'0'),('2789','542126','察雅县','323','C',2,NULL,NULL,NULL,NULL,'0'),('279','510600','德阳市','23','D',1,NULL,NULL,NULL,NULL,'0'),('2790','542127','八宿县','323','B',2,NULL,NULL,NULL,NULL,'0'),('2791','542128','左贡县','323','Z',2,NULL,NULL,NULL,NULL,'0'),('2792','542129','芒康县','323','M',2,NULL,NULL,NULL,NULL,'0'),('2793','542132','洛隆县','323','L',2,NULL,NULL,NULL,NULL,'0'),('2794','542133','边坝县','323','B',2,NULL,NULL,NULL,NULL,'0'),('2795','542221','乃东县','324','N',2,NULL,NULL,NULL,NULL,'0'),('2796','542222','扎囊县','324','Z',2,NULL,NULL,NULL,NULL,'0'),('2797','542223','贡嘎县','324','G',2,NULL,NULL,NULL,NULL,'0'),('2798','542224','桑日县','324','S',2,NULL,NULL,NULL,NULL,'0'),('2799','542225','琼结县','324','Q',2,NULL,NULL,NULL,NULL,'0'),('28','620000','甘肃省','0','G',0,NULL,NULL,NULL,NULL,'0'),('280','510700','绵阳市','23','M',1,NULL,NULL,NULL,NULL,'0'),('2800','542226','曲松县','324','Q',2,NULL,NULL,NULL,NULL,'0'),('2801','542227','措美县','324','C',2,NULL,NULL,NULL,NULL,'0'),('2802','542228','洛扎县','324','L',2,NULL,NULL,NULL,NULL,'0'),('2803','542229','加查县','324','J',2,NULL,NULL,NULL,NULL,'0'),('2804','542231','隆子县','324','L',2,NULL,NULL,NULL,NULL,'0'),('2805','542232','错那县','324','C',2,NULL,NULL,NULL,NULL,'0'),('2806','542233','浪卡子县','324','L',2,NULL,NULL,NULL,NULL,'0'),('2807','542421','那曲县','325','N',2,NULL,NULL,NULL,NULL,'0'),('2808','542422','嘉黎县','325','J',2,NULL,NULL,NULL,NULL,'0'),('2809','542423','比如县','325','B',2,NULL,NULL,NULL,NULL,'0'),('281','510800','广元市','23','G',1,NULL,NULL,NULL,NULL,'0'),('2810','542424','聂荣县','325','N',2,NULL,NULL,NULL,NULL,'0'),('2811','542425','安多县','325','A',2,NULL,NULL,NULL,NULL,'0'),('2812','542426','申扎县','325','S',2,NULL,NULL,NULL,NULL,'0'),('2813','542427','索县','325','S',2,NULL,NULL,NULL,NULL,'0'),('2814','542428','班戈县','325','B',2,NULL,NULL,NULL,NULL,'0'),('2815','542429','巴青县','325','B',2,NULL,NULL,NULL,NULL,'0'),('2816','542430','尼玛县','325','N',2,NULL,NULL,NULL,NULL,'0'),('2817','542431','双湖县','325','S',2,NULL,NULL,NULL,NULL,'0'),('2818','542521','普兰县','326','P',2,NULL,NULL,NULL,NULL,'0'),('2819','542522','札达县','326','Z',2,NULL,NULL,NULL,NULL,'0'),('282','510900','遂宁市','23','S',1,NULL,NULL,NULL,NULL,'0'),('2820','542523','噶尔县','326','G',2,NULL,NULL,NULL,NULL,'0'),('2821','542524','日土县','326','R',2,NULL,NULL,NULL,NULL,'0'),('2822','542525','革吉县','326','G',2,NULL,NULL,NULL,NULL,'0'),('2823','542526','改则县','326','G',2,NULL,NULL,NULL,NULL,'0'),('2824','542527','措勤县','326','C',2,NULL,NULL,NULL,NULL,'0'),('2825','542621','林芝县','327','L',2,NULL,NULL,NULL,NULL,'0'),('2826','542622','工布江达县','327','G',2,NULL,NULL,NULL,NULL,'0'),('2827','542623','米林县','327','M',2,NULL,NULL,NULL,NULL,'0'),('2828','542624','墨脱县','327','M',2,NULL,NULL,NULL,NULL,'0'),('2829','542625','波密县','327','B',2,NULL,NULL,NULL,NULL,'0'),('283','511000','内江市','23','N',1,NULL,NULL,NULL,NULL,'0'),('2830','542626','察隅县','327','C',2,NULL,NULL,NULL,NULL,'0'),('2831','542627','朗县','327','L',2,NULL,NULL,NULL,NULL,'0'),('2832','610103','碑林区','328','B',2,NULL,NULL,NULL,NULL,'0'),('2833','610104','莲湖区','328','L',2,NULL,NULL,NULL,NULL,'0'),('2834','610111','灞桥区','328','B',2,NULL,NULL,NULL,NULL,'0'),('2835','610112','未央区','328','W',2,NULL,NULL,NULL,NULL,'0'),('2836','610113','雁塔区','328','Y',2,NULL,NULL,NULL,NULL,'0'),('2837','610114','阎良区','328','Y',2,NULL,NULL,NULL,NULL,'0'),('2838','610115','临潼区','328','L',2,NULL,NULL,NULL,NULL,'0'),('2839','610122','蓝田县','328','L',2,NULL,NULL,NULL,NULL,'0'),('284','511100','乐山市','23','L',1,NULL,NULL,NULL,NULL,'0'),('2840','610124','周至县','328','Z',2,NULL,NULL,NULL,NULL,'0'),('2841','610125','户县','328','H',2,NULL,NULL,NULL,NULL,'0'),('2842','610126','高陵县','328','G',2,NULL,NULL,NULL,NULL,'0'),('2843','610202','王益区','329','W',2,NULL,NULL,NULL,NULL,'0'),('2844','610203','印台区','329','Y',2,NULL,NULL,NULL,NULL,'0'),('2845','610204','耀州区','329','Y',2,NULL,NULL,NULL,NULL,'0'),('2846','610222','宜君县','329','Y',2,NULL,NULL,NULL,NULL,'0'),('2847','610302','渭滨区','330','W',2,NULL,NULL,NULL,NULL,'0'),('2848','610303','金台区','330','J',2,NULL,NULL,NULL,NULL,'0'),('2849','610304','陈仓区','330','C',2,NULL,NULL,NULL,NULL,'0'),('285','511300','南充市','23','N',1,NULL,NULL,NULL,NULL,'0'),('2850','610322','凤翔县','330','F',2,NULL,NULL,NULL,NULL,'0'),('2851','610323','岐山县','330','Q',2,NULL,NULL,NULL,NULL,'0'),('2852','610324','扶风县','330','F',2,NULL,NULL,NULL,NULL,'0'),('2853','610326','眉县','330','M',2,NULL,NULL,NULL,NULL,'0'),('2854','610327','陇县','330','L',2,NULL,NULL,NULL,NULL,'0'),('2855','610328','千阳县','330','Q',2,NULL,NULL,NULL,NULL,'0'),('2856','610329','麟游县','330','L',2,NULL,NULL,NULL,NULL,'0'),('2857','610330','凤县','330','F',2,NULL,NULL,NULL,NULL,'0'),('2858','610331','太白县','330','T',2,NULL,NULL,NULL,NULL,'0'),('2859','610402','秦都区','331','Q',2,NULL,NULL,NULL,NULL,'0'),('286','511400','眉山市','23','M',1,NULL,NULL,NULL,NULL,'0'),('2860','610403','杨陵区','331','Y',2,NULL,NULL,NULL,NULL,'0'),('2861','610404','渭城区','331','W',2,NULL,NULL,NULL,NULL,'0'),('2862','610422','三原县','331','S',2,NULL,NULL,NULL,NULL,'0'),('2863','610423','泾阳县','331','J',2,NULL,NULL,NULL,NULL,'0'),('2864','610424','乾县','331','Q',2,NULL,NULL,NULL,NULL,'0'),('2865','610425','礼泉县','331','L',2,NULL,NULL,NULL,NULL,'0'),('2866','610426','永寿县','331','Y',2,NULL,NULL,NULL,NULL,'0'),('2867','610427','彬县','331','B',2,NULL,NULL,NULL,NULL,'0'),('2868','610428','长武县','331','C',2,NULL,NULL,NULL,NULL,'0'),('2869','610429','旬邑县','331','X',2,NULL,NULL,NULL,NULL,'0'),('287','511500','宜宾市','23','Y',1,NULL,NULL,NULL,NULL,'0'),('2870','610430','淳化县','331','C',2,NULL,NULL,NULL,NULL,'0'),('2871','610431','武功县','331','W',2,NULL,NULL,NULL,NULL,'0'),('2872','610481','兴平市','331','X',2,NULL,NULL,NULL,NULL,'0'),('2873','610502','临渭区','332','L',2,NULL,NULL,NULL,NULL,'0'),('2874','610521','华县','332','H',2,NULL,NULL,NULL,NULL,'0'),('2875','610522','潼关县','332','T',2,NULL,NULL,NULL,NULL,'0'),('2876','610523','大荔县','332','D',2,NULL,NULL,NULL,NULL,'0'),('2877','610524','合阳县','332','H',2,NULL,NULL,NULL,NULL,'0'),('2878','610525','澄城县','332','C',2,NULL,NULL,NULL,NULL,'0'),('2879','610526','蒲城县','332','P',2,NULL,NULL,NULL,NULL,'0'),('288','511600','广安市','23','G',1,NULL,NULL,NULL,NULL,'0'),('2880','610527','白水县','332','B',2,NULL,NULL,NULL,NULL,'0'),('2881','610528','富平县','332','F',2,NULL,NULL,NULL,NULL,'0'),('2882','610581','韩城市','332','H',2,NULL,NULL,NULL,NULL,'0'),('2883','610582','华阴市','332','H',2,NULL,NULL,NULL,NULL,'0'),('2884','610602','宝塔区','333','B',2,NULL,NULL,NULL,NULL,'0'),('2885','610621','延长县','333','Y',2,NULL,NULL,NULL,NULL,'0'),('2886','610622','延川县','333','Y',2,NULL,NULL,NULL,NULL,'0'),('2887','610623','子长县','333','Z',2,NULL,NULL,NULL,NULL,'0'),('2888','610624','安塞县','333','A',2,NULL,NULL,NULL,NULL,'0'),('2889','610625','志丹县','333','Z',2,NULL,NULL,NULL,NULL,'0'),('289','511700','达州市','23','D',1,NULL,NULL,NULL,NULL,'0'),('2890','610626','吴起县','333','W',2,NULL,NULL,NULL,NULL,'0'),('2891','610627','甘泉县','333','G',2,NULL,NULL,NULL,NULL,'0'),('2892','610628','富县','333','F',2,NULL,NULL,NULL,NULL,'0'),('2893','610629','洛川县','333','L',2,NULL,NULL,NULL,NULL,'0'),('2894','610630','宜川县','333','Y',2,NULL,NULL,NULL,NULL,'0'),('2895','610631','黄龙县','333','H',2,NULL,NULL,NULL,NULL,'0'),('2896','610632','黄陵县','333','H',2,NULL,NULL,NULL,NULL,'0'),('2897','610702','汉台区','334','H',2,NULL,NULL,NULL,NULL,'0'),('2898','610721','南郑县','334','N',2,NULL,NULL,NULL,NULL,'0'),('2899','610722','城固县','334','C',2,NULL,NULL,NULL,NULL,'0'),('29','630000','青海省','0','Q',0,NULL,NULL,NULL,NULL,'0'),('290','511800','雅安市','23','Y',1,NULL,NULL,NULL,NULL,'0'),('2900','610723','洋县','334','Y',2,NULL,NULL,NULL,NULL,'0'),('2901','610724','西乡县','334','X',2,NULL,NULL,NULL,NULL,'0'),('2902','610725','勉县','334','M',2,NULL,NULL,NULL,NULL,'0'),('2903','610726','宁强县','334','N',2,NULL,NULL,NULL,NULL,'0'),('2904','610727','略阳县','334','L',2,NULL,NULL,NULL,NULL,'0'),('2905','610728','镇巴县','334','Z',2,NULL,NULL,NULL,NULL,'0'),('2906','610729','留坝县','334','L',2,NULL,NULL,NULL,NULL,'0'),('2907','610730','佛坪县','334','F',2,NULL,NULL,NULL,NULL,'0'),('2908','610802','榆阳区','335','Y',2,NULL,NULL,NULL,NULL,'0'),('2909','610821','神木县','335','S',2,NULL,NULL,NULL,NULL,'0'),('291','511900','巴中市','23','B',1,NULL,NULL,NULL,NULL,'0'),('2910','610822','府谷县','335','F',2,NULL,NULL,NULL,NULL,'0'),('2911','610823','横山县','335','H',2,NULL,NULL,NULL,NULL,'0'),('2912','610824','靖边县','335','J',2,NULL,NULL,NULL,NULL,'0'),('2913','610825','定边县','335','D',2,NULL,NULL,NULL,NULL,'0'),('2914','610826','绥德县','335','S',2,NULL,NULL,NULL,NULL,'0'),('2915','610827','米脂县','335','M',2,NULL,NULL,NULL,NULL,'0'),('2916','610828','佳县','335','J',2,NULL,NULL,NULL,NULL,'0'),('2917','610829','吴堡县','335','W',2,NULL,NULL,NULL,NULL,'0'),('2918','610830','清涧县','335','Q',2,NULL,NULL,NULL,NULL,'0'),('2919','610831','子洲县','335','Z',2,NULL,NULL,NULL,NULL,'0'),('292','512000','资阳市','23','Z',1,NULL,NULL,NULL,NULL,'0'),('2920','610902','汉滨区','336','H',2,NULL,NULL,NULL,NULL,'0'),('2921','610921','汉阴县','336','H',2,NULL,NULL,NULL,NULL,'0'),('2922','610922','石泉县','336','S',2,NULL,NULL,NULL,NULL,'0'),('2923','610923','宁陕县','336','N',2,NULL,NULL,NULL,NULL,'0'),('2924','610924','紫阳县','336','Z',2,NULL,NULL,NULL,NULL,'0'),('2925','610925','岚皋县','336','L',2,NULL,NULL,NULL,NULL,'0'),('2926','610926','平利县','336','P',2,NULL,NULL,NULL,NULL,'0'),('2927','610927','镇坪县','336','Z',2,NULL,NULL,NULL,NULL,'0'),('2928','610928','旬阳县','336','X',2,NULL,NULL,NULL,NULL,'0'),('2929','610929','白河县','336','B',2,NULL,NULL,NULL,NULL,'0'),('293','513200','阿坝藏族羌族自治州','23','A',1,NULL,NULL,NULL,NULL,'0'),('2930','611002','商州区','337','S',2,NULL,NULL,NULL,NULL,'0'),('2931','611021','洛南县','337','L',2,NULL,NULL,NULL,NULL,'0'),('2932','611022','丹凤县','337','D',2,NULL,NULL,NULL,NULL,'0'),('2933','611023','商南县','337','S',2,NULL,NULL,NULL,NULL,'0'),('2934','611024','山阳县','337','S',2,NULL,NULL,NULL,NULL,'0'),('2935','611025','镇安县','337','Z',2,NULL,NULL,NULL,NULL,'0'),('2936','611026','柞水县','337','Z',2,NULL,NULL,NULL,NULL,'0'),('2937','620103','七里河区','338','Q',2,NULL,NULL,NULL,NULL,'0'),('2938','620104','西固区','338','X',2,NULL,NULL,NULL,NULL,'0'),('2939','620105','安宁区','338','A',2,NULL,NULL,NULL,NULL,'0'),('294','513300','甘孜藏族自治州','23','G',1,NULL,NULL,NULL,NULL,'0'),('2940','620111','红古区','338','H',2,NULL,NULL,NULL,NULL,'0'),('2941','620121','永登县','338','Y',2,NULL,NULL,NULL,NULL,'0'),('2942','620122','皋兰县','338','G',2,NULL,NULL,NULL,NULL,'0'),('2943','620123','榆中县','338','Y',2,NULL,NULL,NULL,NULL,'0'),('2944','620302','金川区','340','J',2,NULL,NULL,NULL,NULL,'0'),('2945','620321','永昌县','340','Y',2,NULL,NULL,NULL,NULL,'0'),('2946','620402','白银区','341','B',2,NULL,NULL,NULL,NULL,'0'),('2947','620403','平川区','341','P',2,NULL,NULL,NULL,NULL,'0'),('2948','620421','靖远县','341','J',2,NULL,NULL,NULL,NULL,'0'),('2949','620422','会宁县','341','H',2,NULL,NULL,NULL,NULL,'0'),('295','513400','凉山彝族自治州','23','L',1,NULL,NULL,NULL,NULL,'0'),('2950','620423','景泰县','341','J',2,NULL,NULL,NULL,NULL,'0'),('2951','620502','秦州区','342','Q',2,NULL,NULL,NULL,NULL,'0'),('2952','620503','麦积区','342','M',2,NULL,NULL,NULL,NULL,'0'),('2953','620521','清水县','342','Q',2,NULL,NULL,NULL,NULL,'0'),('2954','620522','秦安县','342','Q',2,NULL,NULL,NULL,NULL,'0'),('2955','620523','甘谷县','342','G',2,NULL,NULL,NULL,NULL,'0'),('2956','620524','武山县','342','W',2,NULL,NULL,NULL,NULL,'0'),('2957','620525','张家川回族自治县','342','Z',2,NULL,NULL,NULL,NULL,'0'),('2958','620602','凉州区','343','L',2,NULL,NULL,NULL,NULL,'0'),('2959','620621','民勤县','343','M',2,NULL,NULL,NULL,NULL,'0'),('296','520100','贵阳市','24','G',1,NULL,NULL,NULL,NULL,'0'),('2960','620622','古浪县','343','G',2,NULL,NULL,NULL,NULL,'0'),('2961','620623','天祝藏族自治县','343','T',2,NULL,NULL,NULL,NULL,'0'),('2962','620702','甘州区','344','G',2,NULL,NULL,NULL,NULL,'0'),('2963','620721','肃南裕固族自治县','344','S',2,NULL,NULL,NULL,NULL,'0'),('2964','620722','民乐县','344','M',2,NULL,NULL,NULL,NULL,'0'),('2965','620723','临泽县','344','L',2,NULL,NULL,NULL,NULL,'0'),('2966','620724','高台县','344','G',2,NULL,NULL,NULL,NULL,'0'),('2967','620725','山丹县','344','S',2,NULL,NULL,NULL,NULL,'0'),('2968','620802','崆峒区','345','K',2,NULL,NULL,NULL,NULL,'0'),('2969','620821','泾川县','345','J',2,NULL,NULL,NULL,NULL,'0'),('297','520200','六盘水市','24','L',1,NULL,NULL,NULL,NULL,'0'),('2970','620822','灵台县','345','L',2,NULL,NULL,NULL,NULL,'0'),('2971','620823','崇信县','345','C',2,NULL,NULL,NULL,NULL,'0'),('2972','620824','华亭县','345','H',2,NULL,NULL,NULL,NULL,'0'),('2973','620825','庄浪县','345','Z',2,NULL,NULL,NULL,NULL,'0'),('2974','620826','静宁县','345','J',2,NULL,NULL,NULL,NULL,'0'),('2975','620902','肃州区','346','S',2,NULL,NULL,NULL,NULL,'0'),('2976','620921','金塔县','346','J',2,NULL,NULL,NULL,NULL,'0'),('2977','620922','瓜州县','346','G',2,NULL,NULL,NULL,NULL,'0'),('2978','620923','肃北蒙古族自治县','346','S',2,NULL,NULL,NULL,NULL,'0'),('2979','620924','阿克塞哈萨克族自治县','346','A',2,NULL,NULL,NULL,NULL,'0'),('298','520300','遵义市','24','Z',1,NULL,NULL,NULL,NULL,'0'),('2980','620981','玉门市','346','Y',2,NULL,NULL,NULL,NULL,'0'),('2981','620982','敦煌市','346','D',2,NULL,NULL,NULL,NULL,'0'),('2982','621002','西峰区','347','X',2,NULL,NULL,NULL,NULL,'0'),('2983','621021','庆城县','347','Q',2,NULL,NULL,NULL,NULL,'0'),('2984','621022','环县','347','H',2,NULL,NULL,NULL,NULL,'0'),('2985','621023','华池县','347','H',2,NULL,NULL,NULL,NULL,'0'),('2986','621024','合水县','347','H',2,NULL,NULL,NULL,NULL,'0'),('2987','621025','正宁县','347','Z',2,NULL,NULL,NULL,NULL,'0'),('2988','621026','宁县','347','N',2,NULL,NULL,NULL,NULL,'0'),('2989','621027','镇原县','347','Z',2,NULL,NULL,NULL,NULL,'0'),('299','520400','安顺市','24','A',1,NULL,NULL,NULL,NULL,'0'),('2990','621102','安定区','348','A',2,NULL,NULL,NULL,NULL,'0'),('2991','621121','通渭县','348','T',2,NULL,NULL,NULL,NULL,'0'),('2992','621122','陇西县','348','L',2,NULL,NULL,NULL,NULL,'0'),('2993','621123','渭源县','348','W',2,NULL,NULL,NULL,NULL,'0'),('2994','621124','临洮县','348','L',2,NULL,NULL,NULL,NULL,'0'),('2995','621125','漳县','348','Z',2,NULL,NULL,NULL,NULL,'0'),('2996','621126','岷县','348','M',2,NULL,NULL,NULL,NULL,'0'),('2997','621202','武都区','349','W',2,NULL,NULL,NULL,NULL,'0'),('2998','621221','成县','349','C',2,NULL,NULL,NULL,NULL,'0'),('2999','621222','文县','349','W',2,NULL,NULL,NULL,NULL,'0'),('3','130000','河北省','0','H',0,NULL,NULL,NULL,NULL,'0'),('30','640000','宁夏回族自治区','0','N',0,NULL,NULL,NULL,NULL,'0'),('300','520500','毕节市','24','B',1,NULL,NULL,NULL,NULL,'0'),('3000','621223','宕昌县','349','D',2,NULL,NULL,NULL,NULL,'0'),('3001','621224','康县','349','K',2,NULL,NULL,NULL,NULL,'0'),('3002','621225','西和县','349','X',2,NULL,NULL,NULL,NULL,'0'),('3003','621226','礼县','349','L',2,NULL,NULL,NULL,NULL,'0'),('3004','621227','徽县','349','H',2,NULL,NULL,NULL,NULL,'0'),('3005','621228','两当县','349','L',2,NULL,NULL,NULL,NULL,'0'),('3006','622901','临夏市','350','L',2,NULL,NULL,NULL,NULL,'0'),('3007','622921','临夏县','350','L',2,NULL,NULL,NULL,NULL,'0'),('3008','622922','康乐县','350','K',2,NULL,NULL,NULL,NULL,'0'),('3009','622923','永靖县','350','Y',2,NULL,NULL,NULL,NULL,'0'),('301','520600','铜仁市','24','T',1,NULL,NULL,NULL,NULL,'0'),('3010','622924','广河县','350','G',2,NULL,NULL,NULL,NULL,'0'),('3011','622925','和政县','350','H',2,NULL,NULL,NULL,NULL,'0'),('3012','622926','东乡族自治县','350','D',2,NULL,NULL,NULL,NULL,'0'),('3013','622927','积石山保安族东乡族撒拉族自治县','350','J',2,NULL,NULL,NULL,NULL,'0'),('3014','623001','合作市','351','H',2,NULL,NULL,NULL,NULL,'0'),('3015','623021','临潭县','351','L',2,NULL,NULL,NULL,NULL,'0'),('3016','623022','卓尼县','351','Z',2,NULL,NULL,NULL,NULL,'0'),('3017','623023','舟曲县','351','Z',2,NULL,NULL,NULL,NULL,'0'),('3018','623024','迭部县','351','D',2,NULL,NULL,NULL,NULL,'0'),('3019','623025','玛曲县','351','M',2,NULL,NULL,NULL,NULL,'0'),('302','522300','黔西南布依族苗族自治州','24','Q',1,NULL,NULL,NULL,NULL,'0'),('3020','623026','碌曲县','351','L',2,NULL,NULL,NULL,NULL,'0'),('3021','623027','夏河县','351','X',2,NULL,NULL,NULL,NULL,'0'),('3022','630102','城东区','352','C',2,NULL,NULL,NULL,NULL,'0'),('3023','630104','城西区','352','C',2,NULL,NULL,NULL,NULL,'0'),('3024','630105','城北区','352','C',2,NULL,NULL,NULL,NULL,'0'),('3025','630121','大通回族土族自治县','352','D',2,NULL,NULL,NULL,NULL,'0'),('3026','630122','湟中县','352','H',2,NULL,NULL,NULL,NULL,'0'),('3027','630123','湟源县','352','H',2,NULL,NULL,NULL,NULL,'0'),('3028','630202','乐都区','353','L',2,NULL,NULL,NULL,NULL,'0'),('3029','630221','平安县','353','P',2,NULL,NULL,NULL,NULL,'0'),('303','522600','黔东南苗族侗族自治州','24','Q',1,NULL,NULL,NULL,NULL,'0'),('3030','630222','民和回族土族自治县','353','M',2,NULL,NULL,NULL,NULL,'0'),('3031','630223','互助土族自治县','353','H',2,NULL,NULL,NULL,NULL,'0'),('3032','630224','化隆回族自治县','353','H',2,NULL,NULL,NULL,NULL,'0'),('3033','630225','循化撒拉族自治县','353','X',2,NULL,NULL,NULL,NULL,'0'),('3034','632221','门源回族自治县','354','M',2,NULL,NULL,NULL,NULL,'0'),('3035','632222','祁连县','354','Q',2,NULL,NULL,NULL,NULL,'0'),('3036','632223','海晏县','354','H',2,NULL,NULL,NULL,NULL,'0'),('3037','632224','刚察县','354','G',2,NULL,NULL,NULL,NULL,'0'),('3038','632321','同仁县','355','T',2,NULL,NULL,NULL,NULL,'0'),('3039','632322','尖扎县','355','J',2,NULL,NULL,NULL,NULL,'0'),('304','522700','黔南布依族苗族自治州','24','Q',1,NULL,NULL,NULL,NULL,'0'),('3040','632323','泽库县','355','Z',2,NULL,NULL,NULL,NULL,'0'),('3041','632324','河南蒙古族自治县','355','H',2,NULL,NULL,NULL,NULL,'0'),('3042','632521','共和县','356','G',2,NULL,NULL,NULL,NULL,'0'),('3043','632522','同德县','356','T',2,NULL,NULL,NULL,NULL,'0'),('3044','632523','贵德县','356','G',2,NULL,NULL,NULL,NULL,'0'),('3045','632524','兴海县','356','X',2,NULL,NULL,NULL,NULL,'0'),('3046','632525','贵南县','356','G',2,NULL,NULL,NULL,NULL,'0'),('3047','632621','玛沁县','357','M',2,NULL,NULL,NULL,NULL,'0'),('3048','632622','班玛县','357','B',2,NULL,NULL,NULL,NULL,'0'),('3049','632623','甘德县','357','G',2,NULL,NULL,NULL,NULL,'0'),('305','530100','昆明市','25','K',1,NULL,NULL,NULL,NULL,'0'),('3050','632624','达日县','357','D',2,NULL,NULL,NULL,NULL,'0'),('3051','632625','久治县','357','J',2,NULL,NULL,NULL,NULL,'0'),('3052','632626','玛多县','357','M',2,NULL,NULL,NULL,NULL,'0'),('3053','632701','玉树市','358','Y',2,NULL,NULL,NULL,NULL,'0'),('3054','632722','杂多县','358','Z',2,NULL,NULL,NULL,NULL,'0'),('3055','632723','称多县','358','C',2,NULL,NULL,NULL,NULL,'0'),('3056','632724','治多县','358','Z',2,NULL,NULL,NULL,NULL,'0'),('3057','632725','囊谦县','358','N',2,NULL,NULL,NULL,NULL,'0'),('3058','632726','曲麻莱县','358','Q',2,NULL,NULL,NULL,NULL,'0'),('3059','632801','格尔木市','359','G',2,NULL,NULL,NULL,NULL,'0'),('306','530300','曲靖市','25','Q',1,NULL,NULL,NULL,NULL,'0'),('3060','632802','德令哈市','359','D',2,NULL,NULL,NULL,NULL,'0'),('3061','632821','乌兰县','359','W',2,NULL,NULL,NULL,NULL,'0'),('3062','632822','都兰县','359','D',2,NULL,NULL,NULL,NULL,'0'),('3063','632823','天峻县','359','T',2,NULL,NULL,NULL,NULL,'0'),('3064','640104','兴庆区','360','X',2,NULL,NULL,NULL,NULL,'0'),('3065','640105','西夏区','360','X',2,NULL,NULL,NULL,NULL,'0'),('3066','640106','金凤区','360','J',2,NULL,NULL,NULL,NULL,'0'),('3067','640121','永宁县','360','Y',2,NULL,NULL,NULL,NULL,'0'),('3068','640122','贺兰县','360','H',2,NULL,NULL,NULL,NULL,'0'),('3069','640181','灵武市','360','L',2,NULL,NULL,NULL,NULL,'0'),('307','530400','玉溪市','25','Y',1,NULL,NULL,NULL,NULL,'0'),('3070','640202','大武口区','361','D',2,NULL,NULL,NULL,NULL,'0'),('3071','640205','惠农区','361','H',2,NULL,NULL,NULL,NULL,'0'),('3072','640221','平罗县','361','P',2,NULL,NULL,NULL,NULL,'0'),('3073','640302','利通区','362','L',2,NULL,NULL,NULL,NULL,'0'),('3074','640303','红寺堡区','362','H',2,NULL,NULL,NULL,NULL,'0'),('3075','640323','盐池县','362','Y',2,NULL,NULL,NULL,NULL,'0'),('3076','640324','同心县','362','T',2,NULL,NULL,NULL,NULL,'0'),('3077','640381','青铜峡市','362','Q',2,NULL,NULL,NULL,NULL,'0'),('3078','640402','原州区','363','Y',2,NULL,NULL,NULL,NULL,'0'),('3079','640422','西吉县','363','X',2,NULL,NULL,NULL,NULL,'0'),('308','530500','保山市','25','B',1,NULL,NULL,NULL,NULL,'0'),('3080','640423','隆德县','363','L',2,NULL,NULL,NULL,NULL,'0'),('3081','640424','泾源县','363','J',2,NULL,NULL,NULL,NULL,'0'),('3082','640425','彭阳县','363','P',2,NULL,NULL,NULL,NULL,'0'),('3083','640502','沙坡头区','364','S',2,NULL,NULL,NULL,NULL,'0'),('3084','640521','中宁县','364','Z',2,NULL,NULL,NULL,NULL,'0'),('3085','640522','海原县','364','H',2,NULL,NULL,NULL,NULL,'0'),('3086','650102','天山区','365','T',2,NULL,NULL,NULL,NULL,'0'),('3087','650103','沙依巴克区','365','S',2,NULL,NULL,NULL,NULL,'0'),('3088','650105','水磨沟区','365','S',2,NULL,NULL,NULL,NULL,'0'),('3089','650106','头屯河区','365','T',2,NULL,NULL,NULL,NULL,'0'),('309','530600','昭通市','25','Z',1,NULL,NULL,NULL,NULL,'0'),('3090','650107','达坂城区','365','D',2,NULL,NULL,NULL,NULL,'0'),('3091','650109','米东区','365','M',2,NULL,NULL,NULL,NULL,'0'),('3092','650121','乌鲁木齐县','365','W',2,NULL,NULL,NULL,NULL,'0'),('3093','650202','独山子区','366','D',2,NULL,NULL,NULL,NULL,'0'),('3094','650203','克拉玛依区','366','K',2,NULL,NULL,NULL,NULL,'0'),('3095','650204','白碱滩区','366','B',2,NULL,NULL,NULL,NULL,'0'),('3096','650205','乌尔禾区','366','W',2,NULL,NULL,NULL,NULL,'0'),('3097','652101','吐鲁番市','367','T',2,NULL,NULL,NULL,NULL,'0'),('3098','652122','鄯善县','367','S',2,NULL,NULL,NULL,NULL,'0'),('3099','652123','托克逊县','367','T',2,NULL,NULL,NULL,NULL,'0'),('31','650000','新疆维吾尔自治区','0','X',0,NULL,NULL,NULL,NULL,'0'),('310','530700','丽江市','25','L',1,NULL,NULL,NULL,NULL,'0'),('3100','652201','哈密市','368','H',2,NULL,NULL,NULL,NULL,'0'),('3101','652222','巴里坤哈萨克自治县','368','B',2,NULL,NULL,NULL,NULL,'0'),('3102','652223','伊吾县','368','Y',2,NULL,NULL,NULL,NULL,'0'),('3103','652301','昌吉市','369','C',2,NULL,NULL,NULL,NULL,'0'),('3104','652302','阜康市','369','F',2,NULL,NULL,NULL,NULL,'0'),('3105','652323','呼图壁县','369','H',2,NULL,NULL,NULL,NULL,'0'),('3106','652324','玛纳斯县','369','M',2,NULL,NULL,NULL,NULL,'0'),('3107','652325','奇台县','369','Q',2,NULL,NULL,NULL,NULL,'0'),('3108','652327','吉木萨尔县','369','J',2,NULL,NULL,NULL,NULL,'0'),('3109','652328','木垒哈萨克自治县','369','M',2,NULL,NULL,NULL,NULL,'0'),('311','530800','普洱市','25','P',1,NULL,NULL,NULL,NULL,'0'),('3110','652701','博乐市','370','B',2,NULL,NULL,NULL,NULL,'0'),('3111','652702','阿拉山口市','370','A',2,NULL,NULL,NULL,NULL,'0'),('3112','652722','精河县','370','J',2,NULL,NULL,NULL,NULL,'0'),('3113','652723','温泉县','370','W',2,NULL,NULL,NULL,NULL,'0'),('3114','652801','库尔勒市','371','K',2,NULL,NULL,NULL,NULL,'0'),('3115','652822','轮台县','371','L',2,NULL,NULL,NULL,NULL,'0'),('3116','652823','尉犁县','371','W',2,NULL,NULL,NULL,NULL,'0'),('3117','652824','若羌县','371','R',2,NULL,NULL,NULL,NULL,'0'),('3118','652825','且末县','371','Q',2,NULL,NULL,NULL,NULL,'0'),('3119','652826','焉耆回族自治县','371','Y',2,NULL,NULL,NULL,NULL,'0'),('312','530900','临沧市','25','L',1,NULL,NULL,NULL,NULL,'0'),('3120','652827','和静县','371','H',2,NULL,NULL,NULL,NULL,'0'),('3121','652828','和硕县','371','H',2,NULL,NULL,NULL,NULL,'0'),('3122','652829','博湖县','371','B',2,NULL,NULL,NULL,NULL,'0'),('3123','652901','阿克苏市','372','A',2,NULL,NULL,NULL,NULL,'0'),('3124','652922','温宿县','372','W',2,NULL,NULL,NULL,NULL,'0'),('3125','652923','库车县','372','K',2,NULL,NULL,NULL,NULL,'0'),('3126','652924','沙雅县','372','S',2,NULL,NULL,NULL,NULL,'0'),('3127','652925','新和县','372','X',2,NULL,NULL,NULL,NULL,'0'),('3128','652926','拜城县','372','B',2,NULL,NULL,NULL,NULL,'0'),('3129','652927','乌什县','372','W',2,NULL,NULL,NULL,NULL,'0'),('313','532300','楚雄彝族自治州','25','C',1,NULL,NULL,NULL,NULL,'0'),('3130','652928','阿瓦提县','372','A',2,NULL,NULL,NULL,NULL,'0'),('3131','652929','柯坪县','372','K',2,NULL,NULL,NULL,NULL,'0'),('3132','653001','阿图什市','373','A',2,NULL,NULL,NULL,NULL,'0'),('3133','653022','阿克陶县','373','A',2,NULL,NULL,NULL,NULL,'0'),('3134','653023','阿合奇县','373','A',2,NULL,NULL,NULL,NULL,'0'),('3135','653024','乌恰县','373','W',2,NULL,NULL,NULL,NULL,'0'),('3136','653101','喀什市','374','K',2,NULL,NULL,NULL,NULL,'0'),('3137','653121','疏附县','374','S',2,NULL,NULL,NULL,NULL,'0'),('3138','653122','疏勒县','374','S',2,NULL,NULL,NULL,NULL,'0'),('3139','653123','英吉沙县','374','Y',2,NULL,NULL,NULL,NULL,'0'),('314','532500','红河哈尼族彝族自治州','25','H',1,NULL,NULL,NULL,NULL,'0'),('3140','653124','泽普县','374','Z',2,NULL,NULL,NULL,NULL,'0'),('3141','653125','莎车县','374','S',2,NULL,NULL,NULL,NULL,'0'),('3142','653126','叶城县','374','Y',2,NULL,NULL,NULL,NULL,'0'),('3143','653127','麦盖提县','374','M',2,NULL,NULL,NULL,NULL,'0'),('3144','653128','岳普湖县','374','Y',2,NULL,NULL,NULL,NULL,'0'),('3145','653129','伽师县','374','G',2,NULL,NULL,NULL,NULL,'0'),('3146','653130','巴楚县','374','B',2,NULL,NULL,NULL,NULL,'0'),('3147','653131','塔什库尔干塔吉克自治县','374','T',2,NULL,NULL,NULL,NULL,'0'),('3148','653201','和田市','375','H',2,NULL,NULL,NULL,NULL,'0'),('3149','653221','和田县','375','H',2,NULL,NULL,NULL,NULL,'0'),('315','532600','文山壮族苗族自治州','25','W',1,NULL,NULL,NULL,NULL,'0'),('3150','653222','墨玉县','375','M',2,NULL,NULL,NULL,NULL,'0'),('3151','653223','皮山县','375','P',2,NULL,NULL,NULL,NULL,'0'),('3152','653224','洛浦县','375','L',2,NULL,NULL,NULL,NULL,'0'),('3153','653225','策勒县','375','C',2,NULL,NULL,NULL,NULL,'0'),('3154','653226','于田县','375','Y',2,NULL,NULL,NULL,NULL,'0'),('3155','653227','民丰县','375','M',2,NULL,NULL,NULL,NULL,'0'),('3156','654002','伊宁市','376','Y',2,NULL,NULL,NULL,NULL,'0'),('3157','654003','奎屯市','376','K',2,NULL,NULL,NULL,NULL,'0'),('3158','654021','伊宁县','376','Y',2,NULL,NULL,NULL,NULL,'0'),('3159','654022','察布查尔锡伯自治县','376','C',2,NULL,NULL,NULL,NULL,'0'),('316','532800','西双版纳傣族自治州','25','X',1,NULL,NULL,NULL,NULL,'0'),('3160','654023','霍城县','376','H',2,NULL,NULL,NULL,NULL,'0'),('3161','654024','巩留县','376','G',2,NULL,NULL,NULL,NULL,'0'),('3162','654025','新源县','376','X',2,NULL,NULL,NULL,NULL,'0'),('3163','654026','昭苏县','376','Z',2,NULL,NULL,NULL,NULL,'0'),('3164','654027','特克斯县','376','T',2,NULL,NULL,NULL,NULL,'0'),('3165','654028','尼勒克县','376','N',2,NULL,NULL,NULL,NULL,'0'),('3166','654201','塔城市','377','T',2,NULL,NULL,NULL,NULL,'0'),('3167','654202','乌苏市','377','W',2,NULL,NULL,NULL,NULL,'0'),('3168','654221','额敏县','377','E',2,NULL,NULL,NULL,NULL,'0'),('3169','654223','沙湾县','377','S',2,NULL,NULL,NULL,NULL,'0'),('317','532900','大理白族自治州','25','D',1,NULL,NULL,NULL,NULL,'0'),('3170','654224','托里县','377','T',2,NULL,NULL,NULL,NULL,'0'),('3171','654225','裕民县','377','Y',2,NULL,NULL,NULL,NULL,'0'),('3172','654226','和布克赛尔蒙古自治县','377','H',2,NULL,NULL,NULL,NULL,'0'),('3173','654301','阿勒泰市','378','A',2,NULL,NULL,NULL,NULL,'0'),('3174','654321','布尔津县','378','B',2,NULL,NULL,NULL,NULL,'0'),('3175','654322','富蕴县','378','F',2,NULL,NULL,NULL,NULL,'0'),('3176','654323','福海县','378','F',2,NULL,NULL,NULL,NULL,'0'),('3177','654324','哈巴河县','378','H',2,NULL,NULL,NULL,NULL,'0'),('3178','654325','青河县','378','Q',2,NULL,NULL,NULL,NULL,'0'),('3179','654326','吉木乃县','378','J',2,NULL,NULL,NULL,NULL,'0'),('318','533100','德宏傣族景颇族自治州','25','D',1,NULL,NULL,NULL,NULL,'0'),('3180','659001','石河子市','379','S',2,NULL,NULL,NULL,NULL,'0'),('3181','659002','阿拉尔市','379','A',2,NULL,NULL,NULL,NULL,'0'),('3182','659003','图木舒克市','379','T',2,NULL,NULL,NULL,NULL,'0'),('3183','659004','五家渠市','379','W',2,NULL,NULL,NULL,NULL,'0'),('3184','440305','南山区','236','N',2,NULL,NULL,NULL,NULL,'0'),('3185','441502','城区','246','C',2,NULL,NULL,NULL,NULL,'0'),('319','533300','怒江傈僳族自治州','25','N',1,NULL,NULL,NULL,NULL,'0'),('32','710000','台湾省','0','T',0,NULL,NULL,NULL,NULL,'0'),('320','533400','迪庆藏族自治州','25','D',1,NULL,NULL,NULL,NULL,'0'),('321','540100','拉萨市','26','L',1,NULL,NULL,NULL,NULL,'0'),('322','540200','日喀则市','26','R',1,NULL,NULL,NULL,NULL,'0'),('323','542100','昌都地区','26','C',1,NULL,NULL,NULL,NULL,'0'),('324','542200','山南地区','26','S',1,NULL,NULL,NULL,NULL,'0'),('325','542400','那曲地区','26','N',1,NULL,NULL,NULL,NULL,'0'),('326','542500','阿里地区','26','A',1,NULL,NULL,NULL,NULL,'0'),('327','542600','林芝地区','26','L',1,NULL,NULL,NULL,NULL,'0'),('328','610100','西安市','27','X',1,NULL,NULL,NULL,NULL,'0'),('329','610200','铜川市','27','T',1,NULL,NULL,NULL,NULL,'0'),('33','810000','香港特别行政区','0','X',0,NULL,NULL,NULL,NULL,'0'),('330','610300','宝鸡市','27','B',1,NULL,NULL,NULL,NULL,'0'),('331','610400','咸阳市','27','X',1,NULL,NULL,NULL,NULL,'0'),('332','610500','渭南市','27','W',1,NULL,NULL,NULL,NULL,'0'),('333','610600','延安市','27','Y',1,NULL,NULL,NULL,NULL,'0'),('334','610700','汉中市','27','H',1,NULL,NULL,NULL,NULL,'0'),('335','610800','榆林市','27','Y',1,NULL,NULL,NULL,NULL,'0'),('336','610900','安康市','27','A',1,NULL,NULL,NULL,NULL,'0'),('337','611000','商洛市','27','S',1,NULL,NULL,NULL,NULL,'0'),('338','620100','兰州市','28','L',1,NULL,NULL,NULL,NULL,'0'),('339','620200','嘉峪关市','28','J',1,NULL,NULL,NULL,NULL,'0'),('34','820000','澳门特别行政区','0','A',0,NULL,NULL,NULL,NULL,'0'),('340','620300','金昌市','28','J',1,NULL,NULL,NULL,NULL,'0'),('341','620400','白银市','28','B',1,NULL,NULL,NULL,NULL,'0'),('342','620500','天水市','28','T',1,NULL,NULL,NULL,NULL,'0'),('343','620600','武威市','28','W',1,NULL,NULL,NULL,NULL,'0'),('344','620700','张掖市','28','Z',1,NULL,NULL,NULL,NULL,'0'),('345','620800','平凉市','28','P',1,NULL,NULL,NULL,NULL,'0'),('346','620900','酒泉市','28','J',1,NULL,NULL,NULL,NULL,'0'),('347','621000','庆阳市','28','Q',1,NULL,NULL,NULL,NULL,'0'),('348','621100','定西市','28','D',1,NULL,NULL,NULL,NULL,'0'),('349','621200','陇南市','28','L',1,NULL,NULL,NULL,NULL,'0'),('35','110100','北京市','1','B',1,NULL,NULL,NULL,NULL,'0'),('350','622900','临夏回族自治州','28','L',1,NULL,NULL,NULL,NULL,'0'),('351','623000','甘南藏族自治州','28','G',1,NULL,NULL,NULL,NULL,'0'),('352','630100','西宁市','29','X',1,NULL,NULL,NULL,NULL,'0'),('353','630200','海东市','29','H',1,NULL,NULL,NULL,NULL,'0'),('354','632200','海北藏族自治州','29','H',1,NULL,NULL,NULL,NULL,'0'),('355','632300','黄南藏族自治州','29','H',1,NULL,NULL,NULL,NULL,'0'),('356','632500','海南藏族自治州','29','H',1,NULL,NULL,NULL,NULL,'0'),('357','632600','果洛藏族自治州','29','G',1,NULL,NULL,NULL,NULL,'0'),('358','632700','玉树藏族自治州','29','Y',1,NULL,NULL,NULL,NULL,'0'),('359','632800','海西蒙古族藏族自治州','29','H',1,NULL,NULL,NULL,NULL,'0'),('36','110200','北京县','1','B',1,NULL,NULL,NULL,NULL,'0'),('360','640100','银川市','30','Y',1,NULL,NULL,NULL,NULL,'0'),('361','640200','石嘴山市','30','S',1,NULL,NULL,NULL,NULL,'0'),('362','640300','吴忠市','30','W',1,NULL,NULL,NULL,NULL,'0'),('363','640400','固原市','30','G',1,NULL,NULL,NULL,NULL,'0'),('364','640500','中卫市','30','Z',1,NULL,NULL,NULL,NULL,'0'),('365','650100','乌鲁木齐市','31','W',1,NULL,NULL,NULL,NULL,'0'),('366','650200','克拉玛依市','31','K',1,NULL,NULL,NULL,NULL,'0'),('367','652100','吐鲁番地区','31','T',1,NULL,NULL,NULL,NULL,'0'),('368','652200','哈密地区','31','H',1,NULL,NULL,NULL,NULL,'0'),('369','652300','昌吉回族自治州','31','C',1,NULL,NULL,NULL,NULL,'0'),('37','120100','天津市','2','T',1,NULL,NULL,NULL,NULL,'0'),('370','652700','博尔塔拉蒙古自治州','31','B',1,NULL,NULL,NULL,NULL,'0'),('371','652800','巴音郭楞蒙古自治州','31','B',1,NULL,NULL,NULL,NULL,'0'),('372','652900','阿克苏地区','31','A',1,NULL,NULL,NULL,NULL,'0'),('373','653000','克孜勒苏柯尔克孜自治州','31','K',1,NULL,NULL,NULL,NULL,'0'),('374','653100','喀什地区','31','K',1,NULL,NULL,NULL,NULL,'0'),('375','653200','和田地区','31','H',1,NULL,NULL,NULL,NULL,'0'),('376','654000','伊犁哈萨克自治州','31','Y',1,NULL,NULL,NULL,NULL,'0'),('377','654200','塔城地区','31','T',1,NULL,NULL,NULL,NULL,'0'),('378','654300','阿勒泰地区','31','A',1,NULL,NULL,NULL,NULL,'0'),('379','659000','自治区直辖县级行政区划','31','Z',1,NULL,NULL,NULL,NULL,'0'),('38','120200','天津县','2','T',1,NULL,NULL,NULL,NULL,'0'),('380','110101','东城区','35','D',2,NULL,NULL,NULL,NULL,'0'),('381','110102','西城区','35','X',2,NULL,NULL,NULL,NULL,'0'),('382','110105','朝阳区','35','C',2,NULL,NULL,NULL,NULL,'0'),('383','110106','丰台区','35','F',2,NULL,NULL,NULL,NULL,'0'),('384','110107','石景山区','35','S',2,NULL,NULL,NULL,NULL,'0'),('385','110108','海淀区','35','H',2,NULL,NULL,NULL,NULL,'0'),('386','110109','门头沟区','35','M',2,NULL,NULL,NULL,NULL,'0'),('387','110111','房山区','35','F',2,NULL,NULL,NULL,NULL,'0'),('388','110112','通州区','35','T',2,NULL,NULL,NULL,NULL,'0'),('389','110113','顺义区','35','S',2,NULL,NULL,NULL,NULL,'0'),('39','130100','石家庄市','3','S',1,NULL,NULL,NULL,NULL,'0'),('390','110114','昌平区','35','C',2,NULL,NULL,NULL,NULL,'0'),('391','110115','大兴区','35','D',2,NULL,NULL,NULL,NULL,'0'),('392','110116','怀柔区','35','H',2,NULL,NULL,NULL,NULL,'0'),('393','110117','平谷区','35','P',2,NULL,NULL,NULL,NULL,'0'),('394','110228','密云县','36','M',2,NULL,NULL,NULL,NULL,'0'),('395','110229','延庆县','36','Y',2,NULL,NULL,NULL,NULL,'0'),('396','120101','和平区','37','H',2,NULL,NULL,NULL,NULL,'0'),('397','120102','河东区','37','H',2,NULL,NULL,NULL,NULL,'0'),('398','120103','河西区','37','H',2,NULL,NULL,NULL,NULL,'0'),('399','120104','南开区','37','N',2,NULL,NULL,NULL,NULL,'0'),('4','140000','山西省','0','S',0,NULL,NULL,NULL,NULL,'0'),('40','130200','唐山市','3','T',1,NULL,NULL,NULL,NULL,'0'),('400','120105','河北区','37','H',2,NULL,NULL,NULL,NULL,'0'),('401','120106','红桥区','37','H',2,NULL,NULL,NULL,NULL,'0'),('402','120110','东丽区','37','D',2,NULL,NULL,NULL,NULL,'0'),('403','120111','西青区','37','X',2,NULL,NULL,NULL,NULL,'0'),('404','120112','津南区','37','J',2,NULL,NULL,NULL,NULL,'0'),('405','120113','北辰区','37','B',2,NULL,NULL,NULL,NULL,'0'),('406','120114','武清区','37','W',2,NULL,NULL,NULL,NULL,'0'),('407','120115','宝坻区','37','B',2,NULL,NULL,NULL,NULL,'0'),('408','120116','滨海新区','37','B',2,NULL,NULL,NULL,NULL,'0'),('409','120221','宁河县','38','N',2,NULL,NULL,NULL,NULL,'0'),('41','130300','秦皇岛市','3','Q',1,NULL,NULL,NULL,NULL,'0'),('410','120223','静海县','38','J',2,NULL,NULL,NULL,NULL,'0'),('411','120225','蓟县','38','J',2,NULL,NULL,NULL,NULL,'0'),('413','130102','长安区','39','C',2,NULL,NULL,NULL,NULL,'0'),('414','130104','桥西区','39','Q',2,NULL,NULL,NULL,NULL,'0'),('415','130123','正定县','39','Z',2,NULL,NULL,NULL,NULL,'0'),('416','130125','行唐县','39','X',2,NULL,NULL,NULL,NULL,'0'),('417','130126','灵寿县','39','L',2,NULL,NULL,NULL,NULL,'0'),('418','130127','高邑县','39','G',2,NULL,NULL,NULL,NULL,'0'),('419','130181','辛集市','39','X',2,NULL,NULL,NULL,NULL,'0'),('42','130400','邯郸市','3','H',1,NULL,NULL,NULL,NULL,'0'),('420','130183','晋州市','39','J',2,NULL,NULL,NULL,NULL,'0'),('421','130184','新乐市','39','X',2,NULL,NULL,NULL,NULL,'0'),('422','130202','路南区','40','L',2,NULL,NULL,NULL,NULL,'0'),('423','130203','路北区','40','L',2,NULL,NULL,NULL,NULL,'0'),('424','130204','古冶区','40','G',2,NULL,NULL,NULL,NULL,'0'),('425','130205','开平区','40','K',2,NULL,NULL,NULL,NULL,'0'),('426','130207','丰南区','40','F',2,NULL,NULL,NULL,NULL,'0'),('427','130208','丰润区','40','F',2,NULL,NULL,NULL,NULL,'0'),('428','130209','曹妃甸区','40','C',2,NULL,NULL,NULL,NULL,'0'),('429','130223','滦县','40','L',2,NULL,NULL,NULL,NULL,'0'),('43','130500','邢台市','3','X',1,NULL,NULL,NULL,NULL,'0'),('430','130224','滦南县','40','L',2,NULL,NULL,NULL,NULL,'0'),('431','130225','乐亭县','40','L',2,NULL,NULL,NULL,NULL,'0'),('432','130227','迁西县','40','Q',2,NULL,NULL,NULL,NULL,'0'),('433','130229','玉田县','40','Y',2,NULL,NULL,NULL,NULL,'0'),('434','130281','遵化市','40','Z',2,NULL,NULL,NULL,NULL,'0'),('435','130283','迁安市','40','Q',2,NULL,NULL,NULL,NULL,'0'),('436','130302','海港区','41','H',2,NULL,NULL,NULL,NULL,'0'),('437','130303','山海关区','41','S',2,NULL,NULL,NULL,NULL,'0'),('438','130304','北戴河区','41','B',2,NULL,NULL,NULL,NULL,'0'),('439','130321','青龙满族自治县','41','Q',2,NULL,NULL,NULL,NULL,'0'),('44','130600','保定市','3','B',1,NULL,NULL,NULL,NULL,'0'),('440','130322','昌黎县','41','C',2,NULL,NULL,NULL,NULL,'0'),('441','130323','抚宁县','41','F',2,NULL,NULL,NULL,NULL,'0'),('442','130324','卢龙县','41','L',2,NULL,NULL,NULL,NULL,'0'),('443','130402','邯山区','42','H',2,NULL,NULL,NULL,NULL,'0'),('444','130403','丛台区','42','C',2,NULL,NULL,NULL,NULL,'0'),('445','130404','复兴区','42','F',2,NULL,NULL,NULL,NULL,'0'),('446','130406','峰峰矿区','42','F',2,NULL,NULL,NULL,NULL,'0'),('447','130421','邯郸县','42','H',2,NULL,NULL,NULL,NULL,'0'),('448','130423','临漳县','42','L',2,NULL,NULL,NULL,NULL,'0'),('449','130424','成安县','42','C',2,NULL,NULL,NULL,NULL,'0'),('45','130700','张家口市','3','Z',1,NULL,NULL,NULL,NULL,'0'),('450','130425','大名县','42','D',2,NULL,NULL,NULL,NULL,'0'),('451','130426','涉县','42','S',2,NULL,NULL,NULL,NULL,'0'),('452','130427','磁县','42','C',2,NULL,NULL,NULL,NULL,'0'),('453','130428','肥乡县','42','F',2,NULL,NULL,NULL,NULL,'0'),('454','130429','永年县','42','Y',2,NULL,NULL,NULL,NULL,'0'),('455','130430','邱县','42','Q',2,NULL,NULL,NULL,NULL,'0'),('456','130431','鸡泽县','42','J',2,NULL,NULL,NULL,NULL,'0'),('457','130432','广平县','42','G',2,NULL,NULL,NULL,NULL,'0'),('458','130433','馆陶县','42','G',2,NULL,NULL,NULL,NULL,'0'),('459','130434','魏县','42','W',2,NULL,NULL,NULL,NULL,'0'),('46','130800','承德市','3','C',1,NULL,NULL,NULL,NULL,'0'),('460','130435','曲周县','42','Q',2,NULL,NULL,NULL,NULL,'0'),('461','130481','武安市','42','W',2,NULL,NULL,NULL,NULL,'0'),('462','130502','桥东区','43','Q',2,NULL,NULL,NULL,NULL,'0'),('463','130521','邢台县','43','X',2,NULL,NULL,NULL,NULL,'0'),('464','130522','临城县','43','L',2,NULL,NULL,NULL,NULL,'0'),('465','130523','内丘县','43','N',2,NULL,NULL,NULL,NULL,'0'),('466','130524','柏乡县','43','B',2,NULL,NULL,NULL,NULL,'0'),('467','130525','隆尧县','43','L',2,NULL,NULL,NULL,NULL,'0'),('468','130526','任县','43','R',2,NULL,NULL,NULL,NULL,'0'),('469','130527','南和县','43','N',2,NULL,NULL,NULL,NULL,'0'),('47','130900','沧州市','3','C',1,NULL,NULL,NULL,NULL,'0'),('470','130528','宁晋县','43','N',2,NULL,NULL,NULL,NULL,'0'),('471','130529','巨鹿县','43','J',2,NULL,NULL,NULL,NULL,'0'),('472','130530','新河县','43','X',2,NULL,NULL,NULL,NULL,'0'),('473','130531','广宗县','43','G',2,NULL,NULL,NULL,NULL,'0'),('474','130532','平乡县','43','P',2,NULL,NULL,NULL,NULL,'0'),('475','130533','威县','43','W',2,NULL,NULL,NULL,NULL,'0'),('476','130534','清河县','43','Q',2,NULL,NULL,NULL,NULL,'0'),('477','130535','临西县','43','L',2,NULL,NULL,NULL,NULL,'0'),('478','130581','南宫市','43','N',2,NULL,NULL,NULL,NULL,'0'),('479','130582','沙河市','43','S',2,NULL,NULL,NULL,NULL,'0'),('48','131000','廊坊市','3','L',1,NULL,NULL,NULL,NULL,'0'),('480','130602','新市区','44','X',2,NULL,NULL,NULL,NULL,'0'),('481','130603','北市区','44','B',2,NULL,NULL,NULL,NULL,'0'),('482','130604','南市区','44','N',2,NULL,NULL,NULL,NULL,'0'),('483','130621','满城县','44','M',2,NULL,NULL,NULL,NULL,'0'),('484','130622','清苑县','44','Q',2,NULL,NULL,NULL,NULL,'0'),('485','130623','涞水县','44','L',2,NULL,NULL,NULL,NULL,'0'),('486','130624','阜平县','44','F',2,NULL,NULL,NULL,NULL,'0'),('487','130625','徐水县','44','X',2,NULL,NULL,NULL,NULL,'0'),('488','130626','定兴县','44','D',2,NULL,NULL,NULL,NULL,'0'),('489','130627','唐县','44','T',2,NULL,NULL,NULL,NULL,'0'),('49','131100','衡水市','3','H',1,NULL,NULL,NULL,NULL,'0'),('490','130628','高阳县','44','G',2,NULL,NULL,NULL,NULL,'0'),('491','130629','容城县','44','R',2,NULL,NULL,NULL,NULL,'0'),('492','130630','涞源县','44','L',2,NULL,NULL,NULL,NULL,'0'),('493','130631','望都县','44','W',2,NULL,NULL,NULL,NULL,'0'),('494','130632','安新县','44','A',2,NULL,NULL,NULL,NULL,'0'),('495','130633','易县','44','Y',2,NULL,NULL,NULL,NULL,'0'),('496','130634','曲阳县','44','Q',2,NULL,NULL,NULL,NULL,'0'),('497','130635','蠡县','44','L',2,NULL,NULL,NULL,NULL,'0'),('498','130636','顺平县','44','S',2,NULL,NULL,NULL,NULL,'0'),('499','130637','博野县','44','B',2,NULL,NULL,NULL,NULL,'0'),('5','150000','内蒙古自治区','0','N',0,NULL,NULL,NULL,NULL,'0'),('50','140100','太原市','4','T',1,NULL,NULL,NULL,NULL,'0'),('500','130638','雄县','44','X',2,NULL,NULL,NULL,NULL,'0'),('501','130681','涿州市','44','Z',2,NULL,NULL,NULL,NULL,'0'),('502','130682','定州市','44','D',2,NULL,NULL,NULL,NULL,'0'),('503','130683','安国市','44','A',2,NULL,NULL,NULL,NULL,'0'),('504','130684','高碑店市','44','G',2,NULL,NULL,NULL,NULL,'0'),('505','130705','宣化区','45','X',2,NULL,NULL,NULL,NULL,'0'),('506','130706','下花园区','45','X',2,NULL,NULL,NULL,NULL,'0'),('507','130721','宣化县','45','X',2,NULL,NULL,NULL,NULL,'0'),('508','130722','张北县','45','Z',2,NULL,NULL,NULL,NULL,'0'),('509','130723','康保县','45','K',2,NULL,NULL,NULL,NULL,'0'),('51','140200','大同市','4','D',1,NULL,NULL,NULL,NULL,'0'),('510','130724','沽源县','45','G',2,NULL,NULL,NULL,NULL,'0'),('511','130725','尚义县','45','S',2,NULL,NULL,NULL,NULL,'0'),('512','130726','蔚县','45','W',2,NULL,NULL,NULL,NULL,'0'),('513','130727','阳原县','45','Y',2,NULL,NULL,NULL,NULL,'0'),('514','130728','怀安县','45','H',2,NULL,NULL,NULL,NULL,'0'),('515','130729','万全县','45','W',2,NULL,NULL,NULL,NULL,'0'),('516','130730','怀来县','45','H',2,NULL,NULL,NULL,NULL,'0'),('517','130731','涿鹿县','45','Z',2,NULL,NULL,NULL,NULL,'0'),('518','130732','赤城县','45','C',2,NULL,NULL,NULL,NULL,'0'),('519','130733','崇礼县','45','C',2,NULL,NULL,NULL,NULL,'0'),('52','140300','阳泉市','4','Y',1,NULL,NULL,NULL,NULL,'0'),('520','130802','双桥区','46','S',2,NULL,NULL,NULL,NULL,'0'),('521','130803','双滦区','46','S',2,NULL,NULL,NULL,NULL,'0'),('522','130804','鹰手营子矿区','46','Y',2,NULL,NULL,NULL,NULL,'0'),('523','130821','承德县','46','C',2,NULL,NULL,NULL,NULL,'0'),('524','130822','兴隆县','46','X',2,NULL,NULL,NULL,NULL,'0'),('525','130823','平泉县','46','P',2,NULL,NULL,NULL,NULL,'0'),('526','130824','滦平县','46','L',2,NULL,NULL,NULL,NULL,'0'),('527','130825','隆化县','46','L',2,NULL,NULL,NULL,NULL,'0'),('528','130826','丰宁满族自治县','46','F',2,NULL,NULL,NULL,NULL,'0'),('529','130827','宽城满族自治县','46','K',2,NULL,NULL,NULL,NULL,'0'),('53','140400','长治市','4','C',1,NULL,NULL,NULL,NULL,'0'),('530','130828','围场满族蒙古族自治县','46','W',2,NULL,NULL,NULL,NULL,'0'),('531','130903','运河区','47','Y',2,NULL,NULL,NULL,NULL,'0'),('532','130921','沧县','47','C',2,NULL,NULL,NULL,NULL,'0'),('533','130922','青县','47','Q',2,NULL,NULL,NULL,NULL,'0'),('534','130923','东光县','47','D',2,NULL,NULL,NULL,NULL,'0'),('535','130924','海兴县','47','H',2,NULL,NULL,NULL,NULL,'0'),('536','130925','盐山县','47','Y',2,NULL,NULL,NULL,NULL,'0'),('537','130926','肃宁县','47','S',2,NULL,NULL,NULL,NULL,'0'),('538','130927','南皮县','47','N',2,NULL,NULL,NULL,NULL,'0'),('539','130928','吴桥县','47','W',2,NULL,NULL,NULL,NULL,'0'),('54','140500','晋城市','4','J',1,NULL,NULL,NULL,NULL,'0'),('540','130929','献县','47','X',2,NULL,NULL,NULL,NULL,'0'),('541','130930','孟村回族自治县','47','M',2,NULL,NULL,NULL,NULL,'0'),('542','130981','泊头市','47','B',2,NULL,NULL,NULL,NULL,'0'),('543','130982','任丘市','47','R',2,NULL,NULL,NULL,NULL,'0'),('544','130983','黄骅市','47','H',2,NULL,NULL,NULL,NULL,'0'),('545','130984','河间市','47','H',2,NULL,NULL,NULL,NULL,'0'),('546','131002','安次区','48','A',2,NULL,NULL,NULL,NULL,'0'),('547','131003','广阳区','48','G',2,NULL,NULL,NULL,NULL,'0'),('548','131022','固安县','48','G',2,NULL,NULL,NULL,NULL,'0'),('549','131023','永清县','48','Y',2,NULL,NULL,NULL,NULL,'0'),('55','140600','朔州市','4','S',1,NULL,NULL,NULL,NULL,'0'),('550','131024','香河县','48','X',2,NULL,NULL,NULL,NULL,'0'),('551','131025','大城县','48','D',2,NULL,NULL,NULL,NULL,'0'),('552','131026','文安县','48','W',2,NULL,NULL,NULL,NULL,'0'),('553','131028','大厂回族自治县','48','D',2,NULL,NULL,NULL,NULL,'0'),('554','131081','霸州市','48','B',2,NULL,NULL,NULL,NULL,'0'),('555','131082','三河市','48','S',2,NULL,NULL,NULL,NULL,'0'),('556','131102','桃城区','49','T',2,NULL,NULL,NULL,NULL,'0'),('557','131121','枣强县','49','Z',2,NULL,NULL,NULL,NULL,'0'),('558','131122','武邑县','49','W',2,NULL,NULL,NULL,NULL,'0'),('559','131123','武强县','49','W',2,NULL,NULL,NULL,NULL,'0'),('56','140700','晋中市','4','J',1,NULL,NULL,NULL,NULL,'0'),('560','131124','饶阳县','49','R',2,NULL,NULL,NULL,NULL,'0'),('561','131125','安平县','49','A',2,NULL,NULL,NULL,NULL,'0'),('562','131126','故城县','49','G',2,NULL,NULL,NULL,NULL,'0'),('563','131127','景县','49','J',2,NULL,NULL,NULL,NULL,'0'),('564','131128','阜城县','49','F',2,NULL,NULL,NULL,NULL,'0'),('565','131181','冀州市','49','J',2,NULL,NULL,NULL,NULL,'0'),('566','131182','深州市','49','S',2,NULL,NULL,NULL,NULL,'0'),('567','140105','小店区','50','X',2,NULL,NULL,NULL,NULL,'0'),('568','140106','迎泽区','50','Y',2,NULL,NULL,NULL,NULL,'0'),('569','140107','杏花岭区','50','X',2,NULL,NULL,NULL,NULL,'0'),('57','140800','运城市','4','Y',1,NULL,NULL,NULL,NULL,'0'),('570','140108','尖草坪区','50','J',2,NULL,NULL,NULL,NULL,'0'),('571','140109','万柏林区','50','W',2,NULL,NULL,NULL,NULL,'0'),('572','140110','晋源区','50','J',2,NULL,NULL,NULL,NULL,'0'),('573','140121','清徐县','50','Q',2,NULL,NULL,NULL,NULL,'0'),('574','140122','阳曲县','50','Y',2,NULL,NULL,NULL,NULL,'0'),('575','140123','娄烦县','50','L',2,NULL,NULL,NULL,NULL,'0'),('576','140181','古交市','50','G',2,NULL,NULL,NULL,NULL,'0'),('577','140202','城区','51','C',2,NULL,NULL,NULL,NULL,'0'),('578','140203','矿区','51','K',2,NULL,NULL,NULL,NULL,'0'),('579','140211','南郊区','51','N',2,NULL,NULL,NULL,NULL,'0'),('58','140900','忻州市','4','X',1,NULL,NULL,NULL,NULL,'0'),('580','140212','新荣区','51','X',2,NULL,NULL,NULL,NULL,'0'),('581','140221','阳高县','51','Y',2,NULL,NULL,NULL,NULL,'0'),('582','140222','天镇县','51','T',2,NULL,NULL,NULL,NULL,'0'),('583','140223','广灵县','51','G',2,NULL,NULL,NULL,NULL,'0'),('584','140224','灵丘县','51','L',2,NULL,NULL,NULL,NULL,'0'),('585','140225','浑源县','51','H',2,NULL,NULL,NULL,NULL,'0'),('586','140226','左云县','51','Z',2,NULL,NULL,NULL,NULL,'0'),('587','140227','大同县','51','D',2,NULL,NULL,NULL,NULL,'0'),('588','140311','郊区','52','J',2,NULL,NULL,NULL,NULL,'0'),('589','140321','平定县','52','P',2,NULL,NULL,NULL,NULL,'0'),('59','141000','临汾市','4','L',1,NULL,NULL,NULL,NULL,'0'),('590','140322','盂县','52','Y',2,NULL,NULL,NULL,NULL,'0'),('591','140421','长治县','53','C',2,NULL,NULL,NULL,NULL,'0'),('592','140423','襄垣县','53','X',2,NULL,NULL,NULL,NULL,'0'),('593','140424','屯留县','53','T',2,NULL,NULL,NULL,NULL,'0'),('594','140425','平顺县','53','P',2,NULL,NULL,NULL,NULL,'0'),('595','140426','黎城县','53','L',2,NULL,NULL,NULL,NULL,'0'),('596','140427','壶关县','53','H',2,NULL,NULL,NULL,NULL,'0'),('597','140428','长子县','53','C',2,NULL,NULL,NULL,NULL,'0'),('598','140429','武乡县','53','W',2,NULL,NULL,NULL,NULL,'0'),('599','140430','沁县','53','Q',2,NULL,NULL,NULL,NULL,'0'),('6','210000','辽宁省','0','L',0,NULL,NULL,NULL,NULL,'0'),('60','141100','吕梁市','4','L',1,NULL,NULL,NULL,NULL,'0'),('600','140431','沁源县','53','Q',2,NULL,NULL,NULL,NULL,'0'),('601','140481','潞城市','53','L',2,NULL,NULL,NULL,NULL,'0'),('602','140521','沁水县','54','Q',2,NULL,NULL,NULL,NULL,'0'),('603','140522','阳城县','54','Y',2,NULL,NULL,NULL,NULL,'0'),('604','140524','陵川县','54','L',2,NULL,NULL,NULL,NULL,'0'),('605','140525','泽州县','54','Z',2,NULL,NULL,NULL,NULL,'0'),('606','140581','高平市','54','G',2,NULL,NULL,NULL,NULL,'0'),('607','140602','朔城区','55','S',2,NULL,NULL,NULL,NULL,'0'),('608','140603','平鲁区','55','P',2,NULL,NULL,NULL,NULL,'0'),('609','140621','山阴县','55','S',2,NULL,NULL,NULL,NULL,'0'),('61','150100','呼和浩特市','5','H',1,NULL,NULL,NULL,NULL,'0'),('610','140622','应县','55','Y',2,NULL,NULL,NULL,NULL,'0'),('611','140623','右玉县','55','Y',2,NULL,NULL,NULL,NULL,'0'),('612','140624','怀仁县','55','H',2,NULL,NULL,NULL,NULL,'0'),('613','140702','榆次区','56','Y',2,NULL,NULL,NULL,NULL,'0'),('614','140721','榆社县','56','Y',2,NULL,NULL,NULL,NULL,'0'),('615','140722','左权县','56','Z',2,NULL,NULL,NULL,NULL,'0'),('616','140723','和顺县','56','H',2,NULL,NULL,NULL,NULL,'0'),('617','140724','昔阳县','56','X',2,NULL,NULL,NULL,NULL,'0'),('618','140725','寿阳县','56','S',2,NULL,NULL,NULL,NULL,'0'),('619','140726','太谷县','56','T',2,NULL,NULL,NULL,NULL,'0'),('62','150200','包头市','5','B',1,NULL,NULL,NULL,NULL,'0'),('620','140727','祁县','56','Q',2,NULL,NULL,NULL,NULL,'0'),('621','140728','平遥县','56','P',2,NULL,NULL,NULL,NULL,'0'),('622','140729','灵石县','56','L',2,NULL,NULL,NULL,NULL,'0'),('623','140781','介休市','56','J',2,NULL,NULL,NULL,NULL,'0'),('624','140802','盐湖区','57','Y',2,NULL,NULL,NULL,NULL,'0'),('625','140821','临猗县','57','L',2,NULL,NULL,NULL,NULL,'0'),('626','140822','万荣县','57','W',2,NULL,NULL,NULL,NULL,'0'),('627','140823','闻喜县','57','W',2,NULL,NULL,NULL,NULL,'0'),('628','140824','稷山县','57','J',2,NULL,NULL,NULL,NULL,'0'),('629','140825','新绛县','57','X',2,NULL,NULL,NULL,NULL,'0'),('63','150300','乌海市','5','W',1,NULL,NULL,NULL,NULL,'0'),('630','140826','绛县','57','J',2,NULL,NULL,NULL,NULL,'0'),('631','140827','垣曲县','57','Y',2,NULL,NULL,NULL,NULL,'0'),('632','140828','夏县','57','X',2,NULL,NULL,NULL,NULL,'0'),('633','140829','平陆县','57','P',2,NULL,NULL,NULL,NULL,'0'),('634','140830','芮城县','57','R',2,NULL,NULL,NULL,NULL,'0'),('635','140881','永济市','57','Y',2,NULL,NULL,NULL,NULL,'0'),('636','140882','河津市','57','H',2,NULL,NULL,NULL,NULL,'0'),('637','140902','忻府区','58','X',2,NULL,NULL,NULL,NULL,'0'),('638','140921','定襄县','58','D',2,NULL,NULL,NULL,NULL,'0'),('639','140922','五台县','58','W',2,NULL,NULL,NULL,NULL,'0'),('64','150400','赤峰市','5','C',1,NULL,NULL,NULL,NULL,'0'),('640','140923','代县','58','D',2,NULL,NULL,NULL,NULL,'0'),('641','140924','繁峙县','58','F',2,NULL,NULL,NULL,NULL,'0'),('642','140925','宁武县','58','N',2,NULL,NULL,NULL,NULL,'0'),('643','140926','静乐县','58','J',2,NULL,NULL,NULL,NULL,'0'),('644','140927','神池县','58','S',2,NULL,NULL,NULL,NULL,'0'),('645','140928','五寨县','58','W',2,NULL,NULL,NULL,NULL,'0'),('646','140929','岢岚县','58','K',2,NULL,NULL,NULL,NULL,'0'),('647','140930','河曲县','58','H',2,NULL,NULL,NULL,NULL,'0'),('648','140931','保德县','58','B',2,NULL,NULL,NULL,NULL,'0'),('649','140932','偏关县','58','P',2,NULL,NULL,NULL,NULL,'0'),('65','150500','通辽市','5','T',1,NULL,NULL,NULL,NULL,'0'),('650','140981','原平市','58','Y',2,NULL,NULL,NULL,NULL,'0'),('651','141002','尧都区','59','Y',2,NULL,NULL,NULL,NULL,'0'),('652','141021','曲沃县','59','Q',2,NULL,NULL,NULL,NULL,'0'),('653','141022','翼城县','59','Y',2,NULL,NULL,NULL,NULL,'0'),('654','141023','襄汾县','59','X',2,NULL,NULL,NULL,NULL,'0'),('655','141024','洪洞县','59','H',2,NULL,NULL,NULL,NULL,'0'),('656','141025','古县','59','G',2,NULL,NULL,NULL,NULL,'0'),('657','141026','安泽县','59','A',2,NULL,NULL,NULL,NULL,'0'),('658','141027','浮山县','59','F',2,NULL,NULL,NULL,NULL,'0'),('659','141028','吉县','59','J',2,NULL,NULL,NULL,NULL,'0'),('66','150600','鄂尔多斯市','5','E',1,NULL,NULL,NULL,NULL,'0'),('660','141029','乡宁县','59','X',2,NULL,NULL,NULL,NULL,'0'),('661','141030','大宁县','59','D',2,NULL,NULL,NULL,NULL,'0'),('662','141031','隰县','59','X',2,NULL,NULL,NULL,NULL,'0'),('663','141032','永和县','59','Y',2,NULL,NULL,NULL,NULL,'0'),('664','141033','蒲县','59','P',2,NULL,NULL,NULL,NULL,'0'),('665','141034','汾西县','59','F',2,NULL,NULL,NULL,NULL,'0'),('666','141081','侯马市','59','H',2,NULL,NULL,NULL,NULL,'0'),('667','141082','霍州市','59','H',2,NULL,NULL,NULL,NULL,'0'),('668','141102','离石区','60','L',2,NULL,NULL,NULL,NULL,'0'),('669','141121','文水县','60','W',2,NULL,NULL,NULL,NULL,'0'),('67','150700','呼伦贝尔市','5','H',1,NULL,NULL,NULL,NULL,'0'),('670','141122','交城县','60','J',2,NULL,NULL,NULL,NULL,'0'),('671','141123','兴县','60','X',2,NULL,NULL,NULL,NULL,'0'),('672','141124','临县','60','L',2,NULL,NULL,NULL,NULL,'0'),('673','141125','柳林县','60','L',2,NULL,NULL,NULL,NULL,'0'),('674','141126','石楼县','60','S',2,NULL,NULL,NULL,NULL,'0'),('675','141127','岚县','60','L',2,NULL,NULL,NULL,NULL,'0'),('676','141128','方山县','60','F',2,NULL,NULL,NULL,NULL,'0'),('677','141129','中阳县','60','Z',2,NULL,NULL,NULL,NULL,'0'),('678','141130','交口县','60','J',2,NULL,NULL,NULL,NULL,'0'),('679','141181','孝义市','60','X',2,NULL,NULL,NULL,NULL,'0'),('68','150800','巴彦淖尔市','5','B',1,NULL,NULL,NULL,NULL,'0'),('680','141182','汾阳市','60','F',2,NULL,NULL,NULL,NULL,'0'),('681','150102','新城区','61','X',2,NULL,NULL,NULL,NULL,'0'),('682','150103','回民区','61','H',2,NULL,NULL,NULL,NULL,'0'),('683','150104','玉泉区','61','Y',2,NULL,NULL,NULL,NULL,'0'),('684','150105','赛罕区','61','S',2,NULL,NULL,NULL,NULL,'0'),('685','150121','土默特左旗','61','T',2,NULL,NULL,NULL,NULL,'0'),('686','150122','托克托县','61','T',2,NULL,NULL,NULL,NULL,'0'),('687','150123','和林格尔县','61','H',2,NULL,NULL,NULL,NULL,'0'),('688','150124','清水河县','61','Q',2,NULL,NULL,NULL,NULL,'0'),('689','150125','武川县','61','W',2,NULL,NULL,NULL,NULL,'0'),('69','150900','乌兰察布市','5','W',1,NULL,NULL,NULL,NULL,'0'),('690','150202','东河区','62','D',2,NULL,NULL,NULL,NULL,'0'),('691','150203','昆都仑区','62','K',2,NULL,NULL,NULL,NULL,'0'),('692','150204','青山区','62','Q',2,NULL,NULL,NULL,NULL,'0'),('693','150205','石拐区','62','S',2,NULL,NULL,NULL,NULL,'0'),('694','150206','白云鄂博矿区','62','B',2,NULL,NULL,NULL,NULL,'0'),('695','150207','九原区','62','J',2,NULL,NULL,NULL,NULL,'0'),('696','150221','土默特右旗','62','T',2,NULL,NULL,NULL,NULL,'0'),('697','150222','固阳县','62','G',2,NULL,NULL,NULL,NULL,'0'),('698','150223','达尔罕茂明安联合旗','62','D',2,NULL,NULL,NULL,NULL,'0'),('699','150302','海勃湾区','63','H',2,NULL,NULL,NULL,NULL,'0'),('7','220000','吉林省','0','J',0,NULL,NULL,NULL,NULL,'0'),('70','152200','兴安盟','5','X',1,NULL,NULL,NULL,NULL,'0'),('700','150303','海南区','63','H',2,NULL,NULL,NULL,NULL,'0'),('701','150304','乌达区','63','W',2,NULL,NULL,NULL,NULL,'0'),('702','150402','红山区','64','H',2,NULL,NULL,NULL,NULL,'0'),('703','150403','元宝山区','64','Y',2,NULL,NULL,NULL,NULL,'0'),('704','150404','松山区','64','S',2,NULL,NULL,NULL,NULL,'0'),('705','150421','阿鲁科尔沁旗','64','A',2,NULL,NULL,NULL,NULL,'0'),('706','150422','巴林左旗','64','B',2,NULL,NULL,NULL,NULL,'0'),('707','150423','巴林右旗','64','B',2,NULL,NULL,NULL,NULL,'0'),('708','150424','林西县','64','L',2,NULL,NULL,NULL,NULL,'0'),('709','150425','克什克腾旗','64','K',2,NULL,NULL,NULL,NULL,'0'),('71','152500','锡林郭勒盟','5','X',1,NULL,NULL,NULL,NULL,'0'),('710','150426','翁牛特旗','64','W',2,NULL,NULL,NULL,NULL,'0'),('711','150428','喀喇沁旗','64','K',2,NULL,NULL,NULL,NULL,'0'),('712','150429','宁城县','64','N',2,NULL,NULL,NULL,NULL,'0'),('713','150430','敖汉旗','64','A',2,NULL,NULL,NULL,NULL,'0'),('714','150502','科尔沁区','65','K',2,NULL,NULL,NULL,NULL,'0'),('715','150521','科尔沁左翼中旗','65','K',2,NULL,NULL,NULL,NULL,'0'),('716','150522','科尔沁左翼后旗','65','K',2,NULL,NULL,NULL,NULL,'0'),('717','150523','开鲁县','65','K',2,NULL,NULL,NULL,NULL,'0'),('718','150524','库伦旗','65','K',2,NULL,NULL,NULL,NULL,'0'),('719','150525','奈曼旗','65','N',2,NULL,NULL,NULL,NULL,'0'),('72','152900','阿拉善盟','5','A',1,NULL,NULL,NULL,NULL,'0'),('720','150526','扎鲁特旗','65','Z',2,NULL,NULL,NULL,NULL,'0'),('721','150581','霍林郭勒市','65','H',2,NULL,NULL,NULL,NULL,'0'),('722','150602','东胜区','66','D',2,NULL,NULL,NULL,NULL,'0'),('723','150621','达拉特旗','66','D',2,NULL,NULL,NULL,NULL,'0'),('724','150622','准格尔旗','66','Z',2,NULL,NULL,NULL,NULL,'0'),('725','150623','鄂托克前旗','66','E',2,NULL,NULL,NULL,NULL,'0'),('726','150624','鄂托克旗','66','E',2,NULL,NULL,NULL,NULL,'0'),('727','150625','杭锦旗','66','H',2,NULL,NULL,NULL,NULL,'0'),('728','150626','乌审旗','66','W',2,NULL,NULL,NULL,NULL,'0'),('729','150627','伊金霍洛旗','66','Y',2,NULL,NULL,NULL,NULL,'0'),('73','210100','沈阳市','6','S',1,NULL,NULL,NULL,NULL,'0'),('730','150702','海拉尔区','67','H',2,NULL,NULL,NULL,NULL,'0'),('731','150703','扎赉诺尔区','67','Z',2,NULL,NULL,NULL,NULL,'0'),('732','150721','阿荣旗','67','A',2,NULL,NULL,NULL,NULL,'0'),('733','150722','莫力达瓦达斡尔族自治旗','67','M',2,NULL,NULL,NULL,NULL,'0'),('734','150723','鄂伦春自治旗','67','E',2,NULL,NULL,NULL,NULL,'0'),('735','150724','鄂温克族自治旗','67','E',2,NULL,NULL,NULL,NULL,'0'),('736','150725','陈巴尔虎旗','67','C',2,NULL,NULL,NULL,NULL,'0'),('737','150726','新巴尔虎左旗','67','X',2,NULL,NULL,NULL,NULL,'0'),('738','150727','新巴尔虎右旗','67','X',2,NULL,NULL,NULL,NULL,'0'),('739','150781','满洲里市','67','M',2,NULL,NULL,NULL,NULL,'0'),('74','210200','大连市','6','D',1,NULL,NULL,NULL,NULL,'0'),('740','150782','牙克石市','67','Y',2,NULL,NULL,NULL,NULL,'0'),('741','150783','扎兰屯市','67','Z',2,NULL,NULL,NULL,NULL,'0'),('742','150784','额尔古纳市','67','E',2,NULL,NULL,NULL,NULL,'0'),('743','150785','根河市','67','G',2,NULL,NULL,NULL,NULL,'0'),('744','150802','临河区','68','L',2,NULL,NULL,NULL,NULL,'0'),('745','150821','五原县','68','W',2,NULL,NULL,NULL,NULL,'0'),('746','150822','磴口县','68','D',2,NULL,NULL,NULL,NULL,'0'),('747','150823','乌拉特前旗','68','W',2,NULL,NULL,NULL,NULL,'0'),('748','150824','乌拉特中旗','68','W',2,NULL,NULL,NULL,NULL,'0'),('749','150825','乌拉特后旗','68','W',2,NULL,NULL,NULL,NULL,'0'),('75','210300','鞍山市','6','A',1,NULL,NULL,NULL,NULL,'0'),('750','150826','杭锦后旗','68','H',2,NULL,NULL,NULL,NULL,'0'),('751','150902','集宁区','69','J',2,NULL,NULL,NULL,NULL,'0'),('752','150921','卓资县','69','Z',2,NULL,NULL,NULL,NULL,'0'),('753','150922','化德县','69','H',2,NULL,NULL,NULL,NULL,'0'),('754','150923','商都县','69','S',2,NULL,NULL,NULL,NULL,'0'),('755','150924','兴和县','69','X',2,NULL,NULL,NULL,NULL,'0'),('756','150925','凉城县','69','L',2,NULL,NULL,NULL,NULL,'0'),('757','150926','察哈尔右翼前旗','69','C',2,NULL,NULL,NULL,NULL,'0'),('758','150927','察哈尔右翼中旗','69','C',2,NULL,NULL,NULL,NULL,'0'),('759','150928','察哈尔右翼后旗','69','C',2,NULL,NULL,NULL,NULL,'0'),('76','210400','抚顺市','6','F',1,NULL,NULL,NULL,NULL,'0'),('760','150929','四子王旗','69','S',2,NULL,NULL,NULL,NULL,'0'),('761','150981','丰镇市','69','F',2,NULL,NULL,NULL,NULL,'0'),('762','152201','乌兰浩特市','70','W',2,NULL,NULL,NULL,NULL,'0'),('763','152202','阿尔山市','70','A',2,NULL,NULL,NULL,NULL,'0'),('764','152221','科尔沁右翼前旗','70','K',2,NULL,NULL,NULL,NULL,'0'),('765','152222','科尔沁右翼中旗','70','K',2,NULL,NULL,NULL,NULL,'0'),('766','152223','扎赉特旗','70','Z',2,NULL,NULL,NULL,NULL,'0'),('767','152224','突泉县','70','T',2,NULL,NULL,NULL,NULL,'0'),('768','152501','二连浩特市','71','E',2,NULL,NULL,NULL,NULL,'0'),('769','152502','锡林浩特市','71','X',2,NULL,NULL,NULL,NULL,'0'),('77','210500','本溪市','6','B',1,NULL,NULL,NULL,NULL,'0'),('770','152522','阿巴嘎旗','71','A',2,NULL,NULL,NULL,NULL,'0'),('771','152523','苏尼特左旗','71','S',2,NULL,NULL,NULL,NULL,'0'),('772','152524','苏尼特右旗','71','S',2,NULL,NULL,NULL,NULL,'0'),('773','152525','东乌珠穆沁旗','71','D',2,NULL,NULL,NULL,NULL,'0'),('774','152526','西乌珠穆沁旗','71','X',2,NULL,NULL,NULL,NULL,'0'),('775','152527','太仆寺旗','71','T',2,NULL,NULL,NULL,NULL,'0'),('776','152528','镶黄旗','71','X',2,NULL,NULL,NULL,NULL,'0'),('777','152529','正镶白旗','71','Z',2,NULL,NULL,NULL,NULL,'0'),('778','152530','正蓝旗','71','Z',2,NULL,NULL,NULL,NULL,'0'),('779','152531','多伦县','71','D',2,NULL,NULL,NULL,NULL,'0'),('78','210600','丹东市','6','D',1,NULL,NULL,NULL,NULL,'0'),('780','152921','阿拉善左旗','72','A',2,NULL,NULL,NULL,NULL,'0'),('781','152922','阿拉善右旗','72','A',2,NULL,NULL,NULL,NULL,'0'),('782','152923','额济纳旗','72','E',2,NULL,NULL,NULL,NULL,'0'),('783','210103','沈河区','73','S',2,NULL,NULL,NULL,NULL,'0'),('784','210104','大东区','73','D',2,NULL,NULL,NULL,NULL,'0'),('785','210105','皇姑区','73','H',2,NULL,NULL,NULL,NULL,'0'),('786','210106','铁西区','73','T',2,NULL,NULL,NULL,NULL,'0'),('787','210111','苏家屯区','73','S',2,NULL,NULL,NULL,NULL,'0'),('788','210112','浑南区','73','H',2,NULL,NULL,NULL,NULL,'0'),('789','210113','沈北新区','73','S',2,NULL,NULL,NULL,NULL,'0'),('79','210700','锦州市','6','J',1,NULL,NULL,NULL,NULL,'0'),('790','210114','于洪区','73','Y',2,NULL,NULL,NULL,NULL,'0'),('791','210122','辽中县','73','L',2,NULL,NULL,NULL,NULL,'0'),('792','210123','康平县','73','K',2,NULL,NULL,NULL,NULL,'0'),('793','210124','法库县','73','F',2,NULL,NULL,NULL,NULL,'0'),('794','210181','新民市','73','X',2,NULL,NULL,NULL,NULL,'0'),('795','210202','中山区','74','Z',2,NULL,NULL,NULL,NULL,'0'),('796','210203','西岗区','74','X',2,NULL,NULL,NULL,NULL,'0'),('797','210204','沙河口区','74','S',2,NULL,NULL,NULL,NULL,'0'),('798','210211','甘井子区','74','G',2,NULL,NULL,NULL,NULL,'0'),('799','210212','旅顺口区','74','L',2,NULL,NULL,NULL,NULL,'0'),('8','230000','黑龙江省','0','H',0,NULL,NULL,NULL,NULL,'0'),('80','210800','营口市','6','Y',1,NULL,NULL,NULL,NULL,'0'),('800','210213','金州区','74','J',2,NULL,NULL,NULL,NULL,'0'),('801','210224','长海县','74','C',2,NULL,NULL,NULL,NULL,'0'),('802','210281','瓦房店市','74','W',2,NULL,NULL,NULL,NULL,'0'),('803','210282','普兰店市','74','P',2,NULL,NULL,NULL,NULL,'0'),('804','210283','庄河市','74','Z',2,NULL,NULL,NULL,NULL,'0'),('805','210302','铁东区','75','T',2,NULL,NULL,NULL,NULL,'0'),('806','210304','立山区','75','L',2,NULL,NULL,NULL,NULL,'0'),('807','210311','千山区','75','Q',2,NULL,NULL,NULL,NULL,'0'),('808','210321','台安县','75','T',2,NULL,NULL,NULL,NULL,'0'),('809','210323','岫岩满族自治县','75','X',2,NULL,NULL,NULL,NULL,'0'),('81','210900','阜新市','6','F',1,NULL,NULL,NULL,NULL,'0'),('810','210381','海城市','75','H',2,NULL,NULL,NULL,NULL,'0'),('811','210402','新抚区','76','X',2,NULL,NULL,NULL,NULL,'0'),('812','210403','东洲区','76','D',2,NULL,NULL,NULL,NULL,'0'),('813','210404','望花区','76','W',2,NULL,NULL,NULL,NULL,'0'),('814','210411','顺城区','76','S',2,NULL,NULL,NULL,NULL,'0'),('815','210421','抚顺县','76','F',2,NULL,NULL,NULL,NULL,'0'),('816','210422','新宾满族自治县','76','X',2,NULL,NULL,NULL,NULL,'0'),('817','210423','清原满族自治县','76','Q',2,NULL,NULL,NULL,NULL,'0'),('818','210502','平山区','77','P',2,NULL,NULL,NULL,NULL,'0'),('819','210503','溪湖区','77','X',2,NULL,NULL,NULL,NULL,'0'),('82','211000','辽阳市','6','L',1,NULL,NULL,NULL,NULL,'0'),('820','210504','明山区','77','M',2,NULL,NULL,NULL,NULL,'0'),('821','210505','南芬区','77','N',2,NULL,NULL,NULL,NULL,'0'),('822','210521','本溪满族自治县','77','B',2,NULL,NULL,NULL,NULL,'0'),('823','210522','桓仁满族自治县','77','H',2,NULL,NULL,NULL,NULL,'0'),('824','210602','元宝区','78','Y',2,NULL,NULL,NULL,NULL,'0'),('825','210603','振兴区','78','Z',2,NULL,NULL,NULL,NULL,'0'),('826','210604','振安区','78','Z',2,NULL,NULL,NULL,NULL,'0'),('827','210624','宽甸满族自治县','78','K',2,NULL,NULL,NULL,NULL,'0'),('828','210681','东港市','78','D',2,NULL,NULL,NULL,NULL,'0'),('829','210682','凤城市','78','F',2,NULL,NULL,NULL,NULL,'0'),('83','211100','盘锦市','6','P',1,NULL,NULL,NULL,NULL,'0'),('830','210702','古塔区','79','G',2,NULL,NULL,NULL,NULL,'0'),('831','210703','凌河区','79','L',2,NULL,NULL,NULL,NULL,'0'),('832','210711','太和区','79','T',2,NULL,NULL,NULL,NULL,'0'),('833','210726','黑山县','79','H',2,NULL,NULL,NULL,NULL,'0'),('834','210727','义县','79','Y',2,NULL,NULL,NULL,NULL,'0'),('835','210781','凌海市','79','L',2,NULL,NULL,NULL,NULL,'0'),('836','210782','北镇市','79','B',2,NULL,NULL,NULL,NULL,'0'),('837','210802','站前区','80','Z',2,NULL,NULL,NULL,NULL,'0'),('838','210803','西市区','80','X',2,NULL,NULL,NULL,NULL,'0'),('839','210804','鲅鱼圈区','80','B',2,NULL,NULL,NULL,NULL,'0'),('84','211200','铁岭市','6','T',1,NULL,NULL,NULL,NULL,'0'),('840','210811','老边区','80','L',2,NULL,NULL,NULL,NULL,'0'),('841','210881','盖州市','80','G',2,NULL,NULL,NULL,NULL,'0'),('842','210882','大石桥市','80','D',2,NULL,NULL,NULL,NULL,'0'),('843','210902','海州区','81','H',2,NULL,NULL,NULL,NULL,'0'),('844','210903','新邱区','81','X',2,NULL,NULL,NULL,NULL,'0'),('845','210904','太平区','81','T',2,NULL,NULL,NULL,NULL,'0'),('846','210905','清河门区','81','Q',2,NULL,NULL,NULL,NULL,'0'),('847','210911','细河区','81','X',2,NULL,NULL,NULL,NULL,'0'),('848','210921','阜新蒙古族自治县','81','F',2,NULL,NULL,NULL,NULL,'0'),('849','210922','彰武县','81','Z',2,NULL,NULL,NULL,NULL,'0'),('85','211300','朝阳市','6','C',1,NULL,NULL,NULL,NULL,'0'),('850','211002','白塔区','82','B',2,NULL,NULL,NULL,NULL,'0'),('851','211003','文圣区','82','W',2,NULL,NULL,NULL,NULL,'0'),('852','211004','宏伟区','82','H',2,NULL,NULL,NULL,NULL,'0'),('853','211005','弓长岭区','82','G',2,NULL,NULL,NULL,NULL,'0'),('854','211011','太子河区','82','T',2,NULL,NULL,NULL,NULL,'0'),('855','211021','辽阳县','82','L',2,NULL,NULL,NULL,NULL,'0'),('856','211081','灯塔市','82','D',2,NULL,NULL,NULL,NULL,'0'),('858','211102','双台子区','83','S',2,NULL,NULL,NULL,NULL,'0'),('859','211103','兴隆台区','83','X',2,NULL,NULL,NULL,NULL,'0'),('86','211400','葫芦岛市','6','H',1,NULL,NULL,NULL,NULL,'0'),('860','211121','大洼县','83','D',2,NULL,NULL,NULL,NULL,'0'),('861','211122','盘山县','83','P',2,NULL,NULL,NULL,NULL,'0'),('862','211202','银州区','84','Y',2,NULL,NULL,NULL,NULL,'0'),('863','211204','清河区','84','Q',2,NULL,NULL,NULL,NULL,'0'),('864','211221','铁岭县','84','T',2,NULL,NULL,NULL,NULL,'0'),('865','211223','西丰县','84','X',2,NULL,NULL,NULL,NULL,'0'),('866','211224','昌图县','84','C',2,NULL,NULL,NULL,NULL,'0'),('867','211281','调兵山市','84','D',2,NULL,NULL,NULL,NULL,'0'),('868','211282','开原市','84','K',2,NULL,NULL,NULL,NULL,'0'),('869','211302','双塔区','85','S',2,NULL,NULL,NULL,NULL,'0'),('87','220100','长春市','7','C',1,NULL,NULL,NULL,NULL,'0'),('870','211303','龙城区','85','L',2,NULL,NULL,NULL,NULL,'0'),('871','211321','朝阳县','85','C',2,NULL,NULL,NULL,NULL,'0'),('872','211322','建平县','85','J',2,NULL,NULL,NULL,NULL,'0'),('873','211324','喀喇沁左翼蒙古族自治县','85','K',2,NULL,NULL,NULL,NULL,'0'),('874','211381','北票市','85','B',2,NULL,NULL,NULL,NULL,'0'),('875','211382','凌源市','85','L',2,NULL,NULL,NULL,NULL,'0'),('876','211402','连山区','86','L',2,NULL,NULL,NULL,NULL,'0'),('877','211403','龙港区','86','L',2,NULL,NULL,NULL,NULL,'0'),('878','211404','南票区','86','N',2,NULL,NULL,NULL,NULL,'0'),('879','211421','绥中县','86','S',2,NULL,NULL,NULL,NULL,'0'),('88','220200','吉林市','7','J',1,NULL,NULL,NULL,NULL,'0'),('880','211422','建昌县','86','J',2,NULL,NULL,NULL,NULL,'0'),('881','211481','兴城市','86','X',2,NULL,NULL,NULL,NULL,'0'),('882','220102','南关区','87','N',2,NULL,NULL,NULL,NULL,'0'),('883','220103','宽城区','87','K',2,NULL,NULL,NULL,NULL,'0'),('884','220105','二道区','87','E',2,NULL,NULL,NULL,NULL,'0'),('885','220106','绿园区','87','L',2,NULL,NULL,NULL,NULL,'0'),('886','220112','双阳区','87','S',2,NULL,NULL,NULL,NULL,'0'),('887','220113','九台区','87','J',2,NULL,NULL,NULL,NULL,'0'),('888','220122','农安县','87','N',2,NULL,NULL,NULL,NULL,'0'),('889','220182','榆树市','87','Y',2,NULL,NULL,NULL,NULL,'0'),('89','220300','四平市','7','S',1,NULL,NULL,NULL,NULL,'0'),('890','220183','德惠市','87','D',2,NULL,NULL,NULL,NULL,'0'),('891','220202','昌邑区','88','C',2,NULL,NULL,NULL,NULL,'0'),('892','220203','龙潭区','88','L',2,NULL,NULL,NULL,NULL,'0'),('893','220204','船营区','88','C',2,NULL,NULL,NULL,NULL,'0'),('894','220211','丰满区','88','F',2,NULL,NULL,NULL,NULL,'0'),('895','220221','永吉县','88','Y',2,NULL,NULL,NULL,NULL,'0'),('896','220281','蛟河市','88','J',2,NULL,NULL,NULL,NULL,'0'),('897','220282','桦甸市','88','H',2,NULL,NULL,NULL,NULL,'0'),('898','220283','舒兰市','88','S',2,NULL,NULL,NULL,NULL,'0'),('899','220284','磐石市','88','P',2,NULL,NULL,NULL,NULL,'0'),('9','310000','上海市','0','S',0,NULL,NULL,NULL,NULL,'0'),('90','220400','辽源市','7','L',1,NULL,NULL,NULL,NULL,'0'),('900','220322','梨树县','89','L',2,NULL,NULL,NULL,NULL,'0'),('901','220323','伊通满族自治县','89','Y',2,NULL,NULL,NULL,NULL,'0'),('902','220381','公主岭市','89','G',2,NULL,NULL,NULL,NULL,'0'),('903','220382','双辽市','89','S',2,NULL,NULL,NULL,NULL,'0'),('904','220402','龙山区','90','L',2,NULL,NULL,NULL,NULL,'0'),('905','220403','西安区','90','X',2,NULL,NULL,NULL,NULL,'0'),('906','220421','东丰县','90','D',2,NULL,NULL,NULL,NULL,'0'),('907','220422','东辽县','90','D',2,NULL,NULL,NULL,NULL,'0'),('908','220502','东昌区','91','D',2,NULL,NULL,NULL,NULL,'0'),('909','220503','二道江区','91','E',2,NULL,NULL,NULL,NULL,'0'),('91','220500','通化市','7','T',1,NULL,NULL,NULL,NULL,'0'),('910','220521','通化县','91','T',2,NULL,NULL,NULL,NULL,'0'),('911','220523','辉南县','91','H',2,NULL,NULL,NULL,NULL,'0'),('912','220524','柳河县','91','L',2,NULL,NULL,NULL,NULL,'0'),('913','220581','梅河口市','91','M',2,NULL,NULL,NULL,NULL,'0'),('914','220582','集安市','91','J',2,NULL,NULL,NULL,NULL,'0'),('915','220602','浑江区','92','H',2,NULL,NULL,NULL,NULL,'0'),('916','220605','江源区','92','J',2,NULL,NULL,NULL,NULL,'0'),('917','220621','抚松县','92','F',2,NULL,NULL,NULL,NULL,'0'),('918','220622','靖宇县','92','J',2,NULL,NULL,NULL,NULL,'0'),('919','220623','长白朝鲜族自治县','92','C',2,NULL,NULL,NULL,NULL,'0'),('92','220600','白山市','7','B',1,NULL,NULL,NULL,NULL,'0'),('920','220681','临江市','92','L',2,NULL,NULL,NULL,NULL,'0'),('921','220702','宁江区','93','N',2,NULL,NULL,NULL,NULL,'0'),('922','220721','前郭尔罗斯蒙古族自治县','93','Q',2,NULL,NULL,NULL,NULL,'0'),('923','220722','长岭县','93','C',2,NULL,NULL,NULL,NULL,'0'),('924','220723','乾安县','93','Q',2,NULL,NULL,NULL,NULL,'0'),('925','220781','扶余市','93','F',2,NULL,NULL,NULL,NULL,'0'),('926','220802','洮北区','94','T',2,NULL,NULL,NULL,NULL,'0'),('927','220821','镇赉县','94','Z',2,NULL,NULL,NULL,NULL,'0'),('928','220822','通榆县','94','T',2,NULL,NULL,NULL,NULL,'0'),('929','220881','洮南市','94','T',2,NULL,NULL,NULL,NULL,'0'),('93','220700','松原市','7','S',1,NULL,NULL,NULL,NULL,'0'),('930','220882','大安市','94','D',2,NULL,NULL,NULL,NULL,'0'),('931','222401','延吉市','95','Y',2,NULL,NULL,NULL,NULL,'0'),('932','222402','图们市','95','T',2,NULL,NULL,NULL,NULL,'0'),('933','222403','敦化市','95','D',2,NULL,NULL,NULL,NULL,'0'),('934','222404','珲春市','95','H',2,NULL,NULL,NULL,NULL,'0'),('935','222405','龙井市','95','L',2,NULL,NULL,NULL,NULL,'0'),('936','222406','和龙市','95','H',2,NULL,NULL,NULL,NULL,'0'),('937','222424','汪清县','95','W',2,NULL,NULL,NULL,NULL,'0'),('938','222426','安图县','95','A',2,NULL,NULL,NULL,NULL,'0'),('939','230102','道里区','96','D',2,NULL,NULL,NULL,NULL,'0'),('94','220800','白城市','7','B',1,NULL,NULL,NULL,NULL,'0'),('940','230103','南岗区','96','N',2,NULL,NULL,NULL,NULL,'0'),('941','230104','道外区','96','D',2,NULL,NULL,NULL,NULL,'0'),('942','230108','平房区','96','P',2,NULL,NULL,NULL,NULL,'0'),('943','230109','松北区','96','S',2,NULL,NULL,NULL,NULL,'0'),('944','230110','香坊区','96','X',2,NULL,NULL,NULL,NULL,'0'),('945','230111','呼兰区','96','H',2,NULL,NULL,NULL,NULL,'0'),('946','230112','阿城区','96','A',2,NULL,NULL,NULL,NULL,'0'),('947','230123','依兰县','96','Y',2,NULL,NULL,NULL,NULL,'0'),('948','230124','方正县','96','F',2,NULL,NULL,NULL,NULL,'0'),('949','230125','宾县','96','B',2,NULL,NULL,NULL,NULL,'0'),('95','222400','延边朝鲜族自治州','7','Y',1,NULL,NULL,NULL,NULL,'0'),('950','230126','巴彦县','96','B',2,NULL,NULL,NULL,NULL,'0'),('951','230127','木兰县','96','M',2,NULL,NULL,NULL,NULL,'0'),('952','230128','通河县','96','T',2,NULL,NULL,NULL,NULL,'0'),('953','230129','延寿县','96','Y',2,NULL,NULL,NULL,NULL,'0'),('954','230182','双城市','96','S',2,NULL,NULL,NULL,NULL,'0'),('955','230183','尚志市','96','S',2,NULL,NULL,NULL,NULL,'0'),('956','230184','五常市','96','W',2,NULL,NULL,NULL,NULL,'0'),('957','230202','龙沙区','97','L',2,NULL,NULL,NULL,NULL,'0'),('958','230203','建华区','97','J',2,NULL,NULL,NULL,NULL,'0'),('959','230204','铁锋区','97','T',2,NULL,NULL,NULL,NULL,'0'),('96','230100','哈尔滨市','8','H',1,NULL,NULL,NULL,NULL,'0'),('960','230205','昂昂溪区','97','A',2,NULL,NULL,NULL,NULL,'0'),('961','230206','富拉尔基区','97','F',2,NULL,NULL,NULL,NULL,'0'),('962','230207','碾子山区','97','N',2,NULL,NULL,NULL,NULL,'0'),('963','230208','梅里斯达斡尔族区','97','M',2,NULL,NULL,NULL,NULL,'0'),('964','230221','龙江县','97','L',2,NULL,NULL,NULL,NULL,'0'),('965','230223','依安县','97','Y',2,NULL,NULL,NULL,NULL,'0'),('966','230224','泰来县','97','T',2,NULL,NULL,NULL,NULL,'0'),('967','230225','甘南县','97','G',2,NULL,NULL,NULL,NULL,'0'),('968','230227','富裕县','97','F',2,NULL,NULL,NULL,NULL,'0'),('969','230229','克山县','97','K',2,NULL,NULL,NULL,NULL,'0'),('97','230200','齐齐哈尔市','8','Q',1,NULL,NULL,NULL,NULL,'0'),('970','230230','克东县','97','K',2,NULL,NULL,NULL,NULL,'0'),('971','230231','拜泉县','97','B',2,NULL,NULL,NULL,NULL,'0'),('972','230281','讷河市','97','N',2,NULL,NULL,NULL,NULL,'0'),('973','230302','鸡冠区','98','J',2,NULL,NULL,NULL,NULL,'0'),('974','230303','恒山区','98','H',2,NULL,NULL,NULL,NULL,'0'),('975','230304','滴道区','98','D',2,NULL,NULL,NULL,NULL,'0'),('976','230305','梨树区','98','L',2,NULL,NULL,NULL,NULL,'0'),('977','230306','城子河区','98','C',2,NULL,NULL,NULL,NULL,'0'),('978','230307','麻山区','98','M',2,NULL,NULL,NULL,NULL,'0'),('979','230321','鸡东县','98','J',2,NULL,NULL,NULL,NULL,'0'),('98','230300','鸡西市','8','J',1,NULL,NULL,NULL,NULL,'0'),('980','230381','虎林市','98','H',2,NULL,NULL,NULL,NULL,'0'),('981','230382','密山市','98','M',2,NULL,NULL,NULL,NULL,'0'),('982','230402','向阳区','99','X',2,NULL,NULL,NULL,NULL,'0'),('983','230403','工农区','99','G',2,NULL,NULL,NULL,NULL,'0'),('984','230404','南山区','99','N',2,NULL,NULL,NULL,NULL,'0'),('985','230405','兴安区','99','X',2,NULL,NULL,NULL,NULL,'0'),('986','230406','东山区','99','D',2,NULL,NULL,NULL,NULL,'0'),('987','230407','兴山区','99','X',2,NULL,NULL,NULL,NULL,'0'),('988','230421','萝北县','99','L',2,NULL,NULL,NULL,NULL,'0'),('989','230422','绥滨县','99','S',2,NULL,NULL,NULL,NULL,'0'),('99','230400','鹤岗市','8','H',1,NULL,NULL,NULL,NULL,'0'),('990','230502','尖山区','100','J',2,NULL,NULL,NULL,NULL,'0'),('991','230503','岭东区','100','L',2,NULL,NULL,NULL,NULL,'0'),('992','230505','四方台区','100','S',2,NULL,NULL,NULL,NULL,'0'),('993','230506','宝山区','100','B',2,NULL,NULL,NULL,NULL,'0'),('994','230521','集贤县','100','J',2,NULL,NULL,NULL,NULL,'0'),('995','230522','友谊县','100','Y',2,NULL,NULL,NULL,NULL,'0'),('996','230523','宝清县','100','B',2,NULL,NULL,NULL,NULL,'0'),('997','230524','饶河县','100','R',2,NULL,NULL,NULL,NULL,'0'),('998','230602','萨尔图区','101','S',2,NULL,NULL,NULL,NULL,'0'),('999','230603','龙凤区','101','L',2,NULL,NULL,NULL,NULL,'0');
/*!40000 ALTER TABLE `sys_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_info`
--

DROP TABLE IF EXISTS `sys_role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_info` (
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `role_name` text COMMENT '角色名称',
  `org_id` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `dr` varchar(1) DEFAULT NULL,
  `is_action` char(1) DEFAULT NULL COMMENT '是否有效',
  `remark` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_info`
--

LOCK TABLES `sys_role_info` WRITE;
/*!40000 ALTER TABLE `sys_role_info` DISABLE KEYS */;
INSERT INTO `sys_role_info` VALUES ('1189530258996936705','管理员_设备资产',NULL,'0','Y',NULL,'2019-10-30 21:11:29','sys','2020-02-20 00:44:57','sys'),('1193214328922677250','开发角色',NULL,'0','Y','','2019-11-10 01:10:40','sys','2020-02-03 01:24:30','1151420235196588033'),('1193454934454403073','维护角色_设备资产',NULL,'0','Y',NULL,'2019-11-10 17:06:44','1151420235196588033','2020-02-20 00:45:12','sys'),('1196644452732612609','游客_查询角色',NULL,'0','Y','1','2019-11-19 04:20:45','1151420235196588033','2020-08-01 05:38:14','1276368794897408001'),('1224133759469621249','只读角色_设备资产',NULL,'0','Y',NULL,'2020-02-03 00:53:26','sys','2020-02-20 00:45:27','sys'),('1224133874641014785','只读角色_运维管理',NULL,'0','Y',NULL,'2020-02-03 00:53:54','sys','2020-02-20 00:45:36','sys'),('1224134024180535298','管理员_运维系统',NULL,'0','Y',NULL,'2020-02-03 00:54:29','sys','2020-05-19 05:20:03','sys'),('1224142375878762497','超级管理员',NULL,'0','Y','1','2020-02-03 01:27:41','1151420235196588033','2020-08-01 05:38:08','1276368794897408001');
/*!40000 ALTER TABLE `sys_role_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_module`
--

DROP TABLE IF EXISTS `sys_role_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_module` (
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `module_id` varchar(50) DEFAULT NULL COMMENT '模块ID',
  KEY `sys_role_module_ind1` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_module`
--

LOCK TABLES `sys_role_module` WRITE;
/*!40000 ALTER TABLE `sys_role_module` DISABLE KEYS */;
INSERT INTO `sys_role_module` VALUES ('1193214328922677250','241'),('1193214328922677250','243'),('1193214328922677250','244'),('1193214328922677250','245'),('1193214328922677250','246'),('1193214328922677250','247'),('1193214328922677250','248'),('1193214328922677250','250'),('1193214328922677250','255'),('1193214328922677250','256'),('1193214328922677250','189'),('1193214328922677250','190'),('1193214328922677250','191'),('1193214328922677250','192'),('1193214328922677250','193'),('1193214328922677250','195'),('1193214328922677250','206'),('1193214328922677250','114'),('1193214328922677250','116'),('1193214328922677250','117'),('1193214328922677250','118'),('1193214328922677250','119'),('1193214328922677250','120'),('1193214328922677250','121'),('1193214328922677250','122'),('1193214328922677250','123'),('1193214328922677250','124'),('1193214328922677250','125'),('1193214328922677250','147'),('1193214328922677250','148'),('1193214328922677250','149'),('1193214328922677250','150'),('1193214328922677250','151'),('1193214328922677250','152'),('1193214328922677250','153'),('1193214328922677250','163'),('1193214328922677250','164'),('1193214328922677250','165'),('1193214328922677250','166'),('1193214328922677250','167'),('1193214328922677250','169'),('1193214328922677250','171'),('1193214328922677250','173'),('1193214328922677250','174'),('1193214328922677250','178'),('1193214328922677250','179'),('1193214328922677250','180'),('1193214328922677250','181'),('1193214328922677250','182'),('1193214328922677250','183'),('1193214328922677250','185'),('1193214328922677250','186'),('1193214328922677250','187'),('1193214328922677250','188'),('1193214328922677250','2'),('1193214328922677250','3'),('1193214328922677250','16'),('1193214328922677250','17'),('1193214328922677250','22'),('1193214328922677250','56'),('1193214328922677250','57'),('1193214328922677250','70'),('1193214328922677250','76'),('1193214328922677250','77'),('1193214328922677250','89'),('1193214328922677250','90'),('1193214328922677250','91'),('1193214328922677250','96'),('1193214328922677250','129'),('1193214328922677250','130'),('1193214328922677250','131'),('1193214328922677250','134'),('1193214328922677250','135'),('1193214328922677250','136'),('1193214328922677250','137'),('1193214328922677250','138'),('1193214328922677250','139'),('1193214328922677250','142'),('1193214328922677250','143'),('1193214328922677250','154'),('1193214328922677250','157'),('1193214328922677250','176'),('1193214328922677250','197'),('1193214328922677250','198'),('1193214328922677250','257'),('1193214328922677250','258'),('1193214328922677250','259'),('1193214328922677250','283'),('1193214328922677250','284'),('1193214328922677250','285'),('1193214328922677250','286'),('1193214328922677250','287'),('1193214328922677250','288'),('1193214328922677250','289'),('1193214328922677250','290'),('1193214328922677250','291'),('1193214328922677250','292'),('1193214328922677250','293'),('1193214328922677250','294'),('1193214328922677250','295'),('1193214328922677250','296'),('1193214328922677250','297'),('1193214328922677250','300'),('1193214328922677250','301'),('1193214328922677250','302'),('1193214328922677250','303'),('1193214328922677250','304'),('1193214328922677250','305'),('1193214328922677250','306'),('1193214328922677250','307'),('1193214328922677250','308'),('1193214328922677250','309'),('1193214328922677250','310'),('1193214328922677250','311'),('1193214328922677250','312'),('1193214328922677250','313'),('1193214328922677250','314'),('1193214328922677250','315'),('1193214328922677250','316'),('1193214328922677250','317'),('1193214328922677250','318'),('1193214328922677250','319'),('1193214328922677250','321'),('1193214328922677250','322'),('1193214328922677250','323'),('1193214328922677250','326'),('1193214328922677250','327'),('1193214328922677250','328'),('1193214328922677250','329'),('1193214328922677250','330'),('1193214328922677250','331'),('1193214328922677250','332'),('1193214328922677250','378'),('1193214328922677250','379'),('1193214328922677250','380'),('1193214328922677250','410'),('1193214328922677250','411'),('1193214328922677250','412'),('1193214328922677250','413'),('1193214328922677250','414'),('1193214328922677250','415'),('1189530258996936705','2'),('1189530258996936705','3'),('1189530258996936705','16'),('1189530258996936705','17'),('1189530258996936705','22'),('1189530258996936705','56'),('1189530258996936705','57'),('1189530258996936705','70'),('1189530258996936705','77'),('1189530258996936705','91'),('1189530258996936705','96'),('1189530258996936705','129'),('1189530258996936705','130'),('1189530258996936705','134'),('1189530258996936705','135'),('1189530258996936705','136'),('1189530258996936705','137'),('1189530258996936705','138'),('1189530258996936705','142'),('1189530258996936705','143'),('1189530258996936705','154'),('1189530258996936705','157'),('1189530258996936705','176'),('1189530258996936705','197'),('1189530258996936705','198'),('1189530258996936705','257'),('1189530258996936705','258'),('1189530258996936705','259'),('1189530258996936705','283'),('1189530258996936705','284'),('1189530258996936705','285'),('1189530258996936705','286'),('1189530258996936705','287'),('1189530258996936705','288'),('1189530258996936705','289'),('1189530258996936705','290'),('1189530258996936705','291'),('1189530258996936705','292'),('1189530258996936705','293'),('1189530258996936705','294'),('1189530258996936705','295'),('1189530258996936705','296'),('1189530258996936705','297'),('1189530258996936705','300'),('1189530258996936705','301'),('1189530258996936705','302'),('1189530258996936705','303'),('1189530258996936705','304'),('1189530258996936705','305'),('1189530258996936705','306'),('1189530258996936705','307'),('1189530258996936705','308'),('1189530258996936705','309'),('1189530258996936705','310'),('1189530258996936705','311'),('1189530258996936705','312'),('1189530258996936705','313'),('1189530258996936705','314'),('1189530258996936705','315'),('1189530258996936705','316'),('1189530258996936705','317'),('1189530258996936705','318'),('1189530258996936705','319'),('1189530258996936705','321'),('1189530258996936705','322'),('1189530258996936705','323'),('1189530258996936705','326'),('1189530258996936705','327'),('1189530258996936705','328'),('1189530258996936705','329'),('1189530258996936705','330'),('1189530258996936705','331'),('1189530258996936705','332'),('1189530258996936705','378'),('1189530258996936705','379'),('1189530258996936705','380'),('1189530258996936705','410'),('1189530258996936705','415'),('1189530258996936705','417'),('1189530258996936705','459'),('1189530258996936705','464'),('1189530258996936705','465'),('1189530258996936705','466'),('1189530258996936705','467'),('1189530258996936705','468'),('1193454934454403073','16'),('1193454934454403073','17'),('1193454934454403073','70'),('1193454934454403073','91'),('1193454934454403073','96'),('1193454934454403073','142'),('1193454934454403073','143'),('1193454934454403073','154'),('1193454934454403073','157'),('1193454934454403073','176'),('1193454934454403073','197'),('1193454934454403073','257'),('1193454934454403073','258'),('1193454934454403073','259'),('1193454934454403073','283'),('1193454934454403073','284'),('1193454934454403073','286'),('1193454934454403073','288'),('1193454934454403073','290'),('1193454934454403073','291'),('1193454934454403073','292'),('1193454934454403073','293'),('1193454934454403073','294'),('1193454934454403073','295'),('1193454934454403073','296'),('1193454934454403073','297'),('1193454934454403073','300'),('1193454934454403073','301'),('1193454934454403073','302'),('1193454934454403073','303'),('1193454934454403073','304'),('1193454934454403073','305'),('1193454934454403073','309'),('1193454934454403073','311'),('1193454934454403073','313'),('1193454934454403073','314'),('1193454934454403073','316'),('1193454934454403073','317'),('1193454934454403073','318'),('1193454934454403073','321'),('1193454934454403073','323'),('1193454934454403073','327'),('1193454934454403073','328'),('1193454934454403073','330'),('1193454934454403073','332'),('1193454934454403073','378'),('1193454934454403073','379'),('1193454934454403073','380'),('1193454934454403073','410'),('1193454934454403073','459'),('1193454934454403073','464'),('1193454934454403073','465'),('1193454934454403073','466'),('1193454934454403073','467'),('1193454934454403073','468'),('1196644452732612609','380'),('1196644452732612609','284'),('1196644452732612609','291'),('1196644452732612609','294'),('1196644452732612609','296'),('1196644452732612609','197'),('1196644452732612609','16'),('1196644452732612609','17'),('1196644452732612609','378'),('1196644452732612609','90'),('1196644452732612609','91'),('1196644452732612609','154'),('1196644452732612609','313'),('1196644452732612609','135'),('1196644452732612609','317'),('1196644452732612609','309'),('1196644452732612609','328'),('1196644452732612609','410'),('1196644452732612609','305'),('1196644452732612609','392'),('1196644452732612609','393'),('1196644452732612609','396'),('1196644452732612609','398'),('1196644452732612609','399'),('1196644452732612609','400'),('1196644452732612609','425'),('1196644452732612609','429'),('1196644452732612609','431'),('1196644452732612609','453'),('1196644452732612609','457'),('1196644452732612609','469'),('1196644452732612609','514'),('1196644452732612609','515'),('1224133874641014785','496'),('1224133874641014785','513'),('1224133874641014785','514'),('1224133874641014785','515'),('1224133874641014785','516'),('1224133874641014785','517'),('1224133874641014785','518'),('1224133874641014785','519'),('1224133874641014785','578'),('1224133874641014785','579'),('1224133874641014785','583'),('1224133874641014785','584'),('1224133874641014785','585'),('1224133874641014785','586'),('1224133874641014785','587'),('1224133874641014785','588'),('1224133874641014785','589'),('1224133874641014785','590'),('1224133874641014785','591'),('1224133874641014785','592'),('1224133874641014785','593'),('1224133874641014785','594'),('1224133874641014785','595'),('1224133874641014785','596'),('1224133874641014785','597'),('1224134024180535298','496'),('1224134024180535298','513'),('1224134024180535298','514'),('1224134024180535298','515'),('1224134024180535298','516'),('1224134024180535298','517'),('1224134024180535298','518'),('1224134024180535298','519'),('1224134024180535298','578'),('1224134024180535298','579'),('1224134024180535298','583'),('1224134024180535298','584'),('1224134024180535298','585'),('1224134024180535298','586'),('1224134024180535298','587'),('1224134024180535298','588'),('1224134024180535298','589'),('1224134024180535298','590'),('1224134024180535298','591'),('1224134024180535298','592'),('1224134024180535298','593'),('1224134024180535298','594'),('1224134024180535298','595'),('1224134024180535298','596'),('1224134024180535298','597'),('1224134024180535298','598'),('1193454934454403073','333'),('1193454934454403073','335'),('1193454934454403073','336'),('1193454934454403073','337'),('1193454934454403073','339'),('1193454934454403073','340'),('1193454934454403073','349'),('1193454934454403073','350'),('1193454934454403073','352'),('1193454934454403073','355'),('1193454934454403073','356'),('1193454934454403073','357'),('1193454934454403073','367'),('1193454934454403073','368'),('1193454934454403073','369'),('1193454934454403073','373'),('1193454934454403073','374'),('1193454934454403073','375'),('1193454934454403073','381'),('1193454934454403073','382'),('1193454934454403073','385'),('1193454934454403073','387'),('1193454934454403073','388'),('1193454934454403073','389'),('1193454934454403073','392'),('1193454934454403073','393'),('1193454934454403073','396'),('1193454934454403073','398'),('1193454934454403073','399'),('1193454934454403073','400'),('1193454934454403073','420'),('1193454934454403073','424'),('1193454934454403073','425'),('1193454934454403073','426'),('1193454934454403073','429'),('1193454934454403073','440'),('1193454934454403073','443'),('1193454934454403073','444'),('1193454934454403073','453'),('1193454934454403073','454'),('1193454934454403073','456'),('1193454934454403073','457'),('1193454934454403073','458'),('1193454934454403073','469'),('1193454934454403073','470'),('1193454934454403073','471'),('1193454934454403073','472'),('1193454934454403073','473'),('1193454934454403073','524'),('1193454934454403073','525'),('1193454934454403073','526'),('1193454934454403073','528'),('1193454934454403073','529'),('1193454934454403073','530'),('1193454934454403073','531'),('1193454934454403073','532'),('1193454934454403073','534'),('1193454934454403073','535'),('1193454934454403073','536'),('1193454934454403073','537'),('1193454934454403073','538'),('1193454934454403073','539'),('1193454934454403073','542'),('1193454934454403073','543'),('1193454934454403073','544'),('1193454934454403073','547'),('1193454934454403073','548'),('1193454934454403073','559'),('1193454934454403073','560'),('1193454934454403073','561'),('1193454934454403073','562'),('1193454934454403073','565'),('1193454934454403073','566'),('1193454934454403073','567'),('1193454934454403073','574'),('1193454934454403073','599'),('1193454934454403073','600'),('1193454934454403073','601'),('1193454934454403073','602'),('1193454934454403073','603'),('1193454934454403073','604'),('1193454934454403073','605'),('1193454934454403073','606'),('1193454934454403073','607'),('1193454934454403073','609'),('1193454934454403073','610'),('1193454934454403073','611'),('1193454934454403073','612'),('1193454934454403073','613'),('1193454934454403073','614'),('1193454934454403073','615'),('1193454934454403073','616'),('1193454934454403073','617'),('1193454934454403073','618'),('1193454934454403073','619'),('1193454934454403073','620'),('1193454934454403073','621'),('1193454934454403073','622'),('1193454934454403073','623'),('1224142375878762497','496'),('1224142375878762497','513'),('1224142375878762497','514'),('1224142375878762497','515'),('1224142375878762497','516'),('1224142375878762497','517'),('1224142375878762497','518'),('1224142375878762497','519'),('1224142375878762497','578'),('1224142375878762497','579'),('1224142375878762497','583'),('1224142375878762497','584'),('1224142375878762497','585'),('1224142375878762497','586'),('1224142375878762497','587'),('1224142375878762497','588'),('1224142375878762497','589'),('1224142375878762497','590'),('1224142375878762497','591'),('1224142375878762497','592'),('1224142375878762497','593'),('1224142375878762497','594'),('1224142375878762497','595'),('1224142375878762497','596'),('1224142375878762497','597'),('1224142375878762497','598'),('1224142375878762497','2'),('1224142375878762497','3'),('1224142375878762497','16'),('1224142375878762497','17'),('1224142375878762497','22'),('1224142375878762497','56'),('1224142375878762497','57'),('1224142375878762497','70'),('1224142375878762497','76'),('1224142375878762497','77'),('1224142375878762497','89'),('1224142375878762497','90'),('1224142375878762497','91'),('1224142375878762497','96'),('1224142375878762497','129'),('1224142375878762497','130'),('1224142375878762497','131'),('1224142375878762497','134'),('1224142375878762497','135'),('1224142375878762497','136'),('1224142375878762497','137'),('1224142375878762497','138'),('1224142375878762497','139'),('1224142375878762497','142'),('1224142375878762497','143'),('1224142375878762497','154'),('1224142375878762497','157'),('1224142375878762497','176'),('1224142375878762497','197'),('1224142375878762497','198'),('1224142375878762497','257'),('1224142375878762497','258'),('1224142375878762497','259'),('1224142375878762497','283'),('1224142375878762497','284'),('1224142375878762497','285'),('1224142375878762497','286'),('1224142375878762497','287'),('1224142375878762497','288'),('1224142375878762497','289'),('1224142375878762497','290'),('1224142375878762497','291'),('1224142375878762497','292'),('1224142375878762497','293'),('1224142375878762497','294'),('1224142375878762497','295'),('1224142375878762497','296'),('1224142375878762497','297'),('1224142375878762497','300'),('1224142375878762497','301'),('1224142375878762497','302'),('1224142375878762497','303'),('1224142375878762497','304'),('1224142375878762497','305'),('1224142375878762497','306'),('1224142375878762497','307'),('1224142375878762497','308'),('1224142375878762497','309'),('1224142375878762497','310'),('1224142375878762497','311'),('1224142375878762497','312'),('1224142375878762497','313'),('1224142375878762497','314'),('1224142375878762497','315'),('1224142375878762497','316'),('1224142375878762497','317'),('1224142375878762497','318'),('1224142375878762497','319'),('1224142375878762497','321'),('1224142375878762497','322'),('1224142375878762497','323'),('1224142375878762497','326'),('1224142375878762497','327'),('1224142375878762497','328'),('1224142375878762497','329'),('1224142375878762497','330'),('1224142375878762497','331'),('1224142375878762497','332'),('1224142375878762497','378'),('1224142375878762497','379'),('1224142375878762497','380'),('1224142375878762497','410'),('1224142375878762497','411'),('1224142375878762497','412'),('1224142375878762497','413'),('1224142375878762497','414'),('1224142375878762497','415'),('1224142375878762497','417'),('1224142375878762497','459'),('1224142375878762497','464'),('1224142375878762497','465'),('1224142375878762497','466'),('1224142375878762497','467'),('1224142375878762497','468'),('1224142375878762497','512'),('1224142375878762497','520'),('1224142375878762497','521'),('1224142375878762497','572'),('1224142375878762497','635'),('1189530258996936705','233'),('1189530258996936705','234'),('1189530258996936705','236'),('1189530258996936705','260'),('1189530258996936705','263'),('1189530258996936705','265'),('1189530258996936705','266'),('1189530258996936705','269'),('1189530258996936705','270'),('1189530258996936705','273'),('1189530258996936705','333'),('1189530258996936705','334'),('1189530258996936705','335'),('1189530258996936705','336'),('1189530258996936705','337'),('1189530258996936705','338'),('1189530258996936705','339'),('1189530258996936705','340'),('1189530258996936705','349'),('1189530258996936705','350'),('1189530258996936705','351'),('1189530258996936705','352'),('1189530258996936705','355'),('1189530258996936705','356'),('1189530258996936705','357'),('1189530258996936705','361'),('1189530258996936705','362'),('1189530258996936705','363'),('1189530258996936705','367'),('1189530258996936705','368'),('1189530258996936705','369'),('1189530258996936705','373'),('1189530258996936705','374'),('1189530258996936705','375'),('1189530258996936705','381'),('1189530258996936705','382'),('1189530258996936705','385'),('1189530258996936705','387'),('1189530258996936705','388'),('1189530258996936705','389'),('1189530258996936705','392'),('1189530258996936705','393'),('1189530258996936705','396'),('1189530258996936705','398'),('1189530258996936705','399'),('1189530258996936705','400'),('1189530258996936705','418'),('1189530258996936705','419'),('1189530258996936705','420'),('1189530258996936705','421'),('1189530258996936705','424'),('1189530258996936705','425'),('1189530258996936705','426'),('1189530258996936705','428'),('1189530258996936705','429'),('1189530258996936705','431'),('1189530258996936705','432'),('1189530258996936705','433'),('1189530258996936705','434'),('1189530258996936705','438'),('1189530258996936705','439'),('1189530258996936705','440'),('1189530258996936705','442'),('1189530258996936705','443'),('1189530258996936705','444'),('1189530258996936705','452'),('1189530258996936705','453'),('1189530258996936705','454'),('1189530258996936705','455'),('1189530258996936705','456'),('1189530258996936705','457'),('1189530258996936705','458'),('1189530258996936705','469'),('1189530258996936705','470'),('1189530258996936705','471'),('1189530258996936705','472'),('1189530258996936705','473'),('1189530258996936705','522'),('1189530258996936705','523'),('1189530258996936705','524'),('1189530258996936705','525'),('1189530258996936705','526'),('1189530258996936705','528'),('1189530258996936705','529'),('1189530258996936705','530'),('1189530258996936705','531'),('1189530258996936705','532'),('1189530258996936705','534'),('1189530258996936705','535'),('1189530258996936705','536'),('1189530258996936705','537'),('1189530258996936705','538'),('1189530258996936705','539'),('1189530258996936705','542'),('1189530258996936705','543'),('1189530258996936705','544'),('1189530258996936705','547'),('1189530258996936705','548'),('1189530258996936705','549'),('1189530258996936705','550'),('1189530258996936705','551'),('1189530258996936705','552'),('1189530258996936705','553'),('1189530258996936705','554'),('1189530258996936705','557'),('1189530258996936705','559'),('1189530258996936705','560'),('1189530258996936705','561'),('1189530258996936705','562'),('1189530258996936705','565'),('1189530258996936705','566'),('1189530258996936705','567'),('1189530258996936705','571'),('1189530258996936705','574'),('1189530258996936705','575'),('1189530258996936705','576'),('1189530258996936705','577'),('1189530258996936705','599'),('1189530258996936705','600'),('1189530258996936705','601'),('1189530258996936705','602'),('1189530258996936705','603'),('1189530258996936705','604'),('1189530258996936705','605'),('1189530258996936705','606'),('1189530258996936705','607'),('1189530258996936705','609'),('1189530258996936705','610'),('1189530258996936705','611'),('1189530258996936705','612'),('1189530258996936705','613'),('1189530258996936705','614'),('1189530258996936705','615'),('1189530258996936705','616'),('1189530258996936705','617'),('1189530258996936705','618'),('1189530258996936705','619'),('1189530258996936705','620'),('1189530258996936705','621'),('1189530258996936705','622'),('1189530258996936705','623'),('1189530258996936705','624'),('1189530258996936705','625'),('1189530258996936705','626'),('1189530258996936705','628'),('1189530258996936705','630'),('1189530258996936705','631'),('1189530258996936705','632'),('1189530258996936705','633'),('1189530258996936705','636'),('1189530258996936705','637'),('1189530258996936705','638'),('1189530258996936705','639'),('1189530258996936705','640'),('1189530258996936705','641'),('1189530258996936705','642'),('1189530258996936705','643'),('1189530258996936705','644'),('1189530258996936705','645'),('1189530258996936705','646'),('1189530258996936705','647'),('1189530258996936705','648'),('1189530258996936705','649'),('1189530258996936705','650'),('1189530258996936705','651'),('1189530258996936705','652'),('1224142375878762497','656'),('1224142375878762497','658'),('1224142375878762497','660'),('1224142375878762497','661'),('1224142375878762497','662'),('1224142375878762497','665'),('1224142375878762497','666'),('1224142375878762497','667'),('1224142375878762497','668'),('1224142375878762497','669'),('1224142375878762497','670'),('1224142375878762497','671'),('1224142375878762497','672'),('1224142375878762497','673'),('1224142375878762497','674'),('1224142375878762497','675'),('1224142375878762497','676'),('1224142375878762497','677'),('1224142375878762497','678'),('1224142375878762497','679'),('1224142375878762497','680'),('1224142375878762497','681'),('1224142375878762497','233'),('1224142375878762497','234'),('1224142375878762497','236'),('1224142375878762497','260'),('1224142375878762497','263'),('1224142375878762497','265'),('1224142375878762497','266'),('1224142375878762497','269'),('1224142375878762497','270'),('1224142375878762497','273'),('1224142375878762497','333'),('1224142375878762497','334'),('1224142375878762497','335'),('1224142375878762497','336'),('1224142375878762497','337'),('1224142375878762497','338'),('1224142375878762497','339'),('1224142375878762497','340'),('1224142375878762497','349'),('1224142375878762497','350'),('1224142375878762497','351'),('1224142375878762497','352'),('1224142375878762497','355'),('1224142375878762497','356'),('1224142375878762497','357'),('1224142375878762497','361'),('1224142375878762497','362'),('1224142375878762497','363'),('1224142375878762497','367'),('1224142375878762497','368'),('1224142375878762497','369'),('1224142375878762497','373'),('1224142375878762497','374'),('1224142375878762497','375'),('1224142375878762497','381'),('1224142375878762497','382'),('1224142375878762497','385'),('1224142375878762497','387'),('1224142375878762497','388'),('1224142375878762497','389'),('1224142375878762497','392'),('1224142375878762497','393'),('1224142375878762497','396'),('1224142375878762497','398'),('1224142375878762497','399'),('1224142375878762497','400'),('1224142375878762497','418'),('1224142375878762497','419'),('1224142375878762497','420'),('1224142375878762497','421'),('1224142375878762497','424'),('1224142375878762497','425'),('1224142375878762497','426'),('1224142375878762497','428'),('1224142375878762497','429'),('1224142375878762497','431'),('1224142375878762497','432'),('1224142375878762497','433'),('1224142375878762497','434'),('1224142375878762497','438'),('1224142375878762497','439'),('1224142375878762497','440'),('1224142375878762497','442'),('1224142375878762497','443'),('1224142375878762497','444'),('1224142375878762497','452'),('1224142375878762497','453'),('1224142375878762497','454'),('1224142375878762497','455'),('1224142375878762497','456'),('1224142375878762497','457'),('1224142375878762497','458'),('1224142375878762497','469'),('1224142375878762497','470'),('1224142375878762497','471'),('1224142375878762497','472'),('1224142375878762497','473'),('1224142375878762497','523'),('1224142375878762497','524'),('1224142375878762497','525'),('1224142375878762497','526'),('1224142375878762497','528'),('1224142375878762497','529'),('1224142375878762497','530'),('1224142375878762497','531'),('1224142375878762497','532'),('1224142375878762497','534'),('1224142375878762497','535'),('1224142375878762497','536'),('1224142375878762497','537'),('1224142375878762497','538'),('1224142375878762497','539'),('1224142375878762497','542'),('1224142375878762497','543'),('1224142375878762497','544'),('1224142375878762497','547'),('1224142375878762497','548'),('1224142375878762497','549'),('1224142375878762497','550'),('1224142375878762497','551'),('1224142375878762497','552'),('1224142375878762497','553'),('1224142375878762497','554'),('1224142375878762497','557'),('1224142375878762497','559'),('1224142375878762497','560'),('1224142375878762497','561'),('1224142375878762497','562'),('1224142375878762497','565'),('1224142375878762497','566'),('1224142375878762497','567'),('1224142375878762497','571'),('1224142375878762497','574'),('1224142375878762497','575'),('1224142375878762497','576'),('1224142375878762497','577'),('1224142375878762497','599'),('1224142375878762497','600'),('1224142375878762497','601'),('1224142375878762497','602'),('1224142375878762497','603'),('1224142375878762497','604'),('1224142375878762497','605'),('1224142375878762497','606'),('1224142375878762497','607'),('1224142375878762497','609'),('1224142375878762497','610'),('1224142375878762497','611'),('1224142375878762497','612'),('1224142375878762497','613'),('1224142375878762497','614'),('1224142375878762497','615'),('1224142375878762497','616'),('1224142375878762497','617'),('1224142375878762497','618'),('1224142375878762497','619'),('1224142375878762497','620'),('1224142375878762497','621'),('1224142375878762497','622'),('1224142375878762497','623'),('1224142375878762497','624'),('1224142375878762497','625'),('1224142375878762497','626'),('1224142375878762497','628'),('1224142375878762497','630'),('1224142375878762497','631'),('1224142375878762497','632'),('1224142375878762497','633'),('1224142375878762497','636'),('1224142375878762497','637'),('1224142375878762497','641'),('1224142375878762497','642'),('1224142375878762497','643'),('1224142375878762497','644'),('1224142375878762497','645'),('1224142375878762497','646'),('1224142375878762497','647'),('1224142375878762497','648'),('1224142375878762497','649'),('1224142375878762497','650'),('1224142375878762497','651'),('1224142375878762497','652'),('1224142375878762497','654'),('1224142375878762497','655'),('1224142375878762497','683'),('1224142375878762497','684');
/*!40000 ALTER TABLE `sys_role_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_module_bak`
--

DROP TABLE IF EXISTS `sys_role_module_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_module_bak` (
  `role_id` varchar(50) DEFAULT NULL,
  `module_id` decimal(5,0) DEFAULT NULL,
  KEY `sys_role_module_bak_ind1` (`role_id`),
  KEY `sys_role_module_bak_ind2` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_module_bak`
--

LOCK TABLES `sys_role_module_bak` WRITE;
/*!40000 ALTER TABLE `sys_role_module_bak` DISABLE KEYS */;
INSERT INTO `sys_role_module_bak` VALUES ('815f9d61-38bd-4ee9-a603-011f3864ac9f',20),('815f9d61-38bd-4ee9-a603-011f3864ac9f',29),('b505dd9d-9809-4129-917f-1526436ddf75',27),('b505dd9d-9809-4129-917f-1526436ddf75',62),('b505dd9d-9809-4129-917f-1526436ddf75',4),('b505dd9d-9809-4129-917f-1526436ddf75',63),('b505dd9d-9809-4129-917f-1526436ddf75',65),('b505dd9d-9809-4129-917f-1526436ddf75',66),('b505dd9d-9809-4129-917f-1526436ddf75',67),('b505dd9d-9809-4129-917f-1526436ddf75',68),('b505dd9d-9809-4129-917f-1526436ddf75',69),('815f9d61-38bd-4ee9-a603-011f3864ac9f',31),('815f9d61-38bd-4ee9-a603-011f3864ac9f',36),('815f9d61-38bd-4ee9-a603-011f3864ac9f',44),('815f9d61-38bd-4ee9-a603-011f3864ac9f',46),('815f9d61-38bd-4ee9-a603-011f3864ac9f',8),('815f9d61-38bd-4ee9-a603-011f3864ac9f',16),('815f9d61-38bd-4ee9-a603-011f3864ac9f',56),('9420e12c-3003-441a-be79-d2d6f61e5478',114),('9420e12c-3003-441a-be79-d2d6f61e5478',116),('9420e12c-3003-441a-be79-d2d6f61e5478',117),('9420e12c-3003-441a-be79-d2d6f61e5478',118),('9420e12c-3003-441a-be79-d2d6f61e5478',119),('9420e12c-3003-441a-be79-d2d6f61e5478',120),('9420e12c-3003-441a-be79-d2d6f61e5478',121),('9420e12c-3003-441a-be79-d2d6f61e5478',122),('9420e12c-3003-441a-be79-d2d6f61e5478',123),('9420e12c-3003-441a-be79-d2d6f61e5478',124),('9420e12c-3003-441a-be79-d2d6f61e5478',125),('9420e12c-3003-441a-be79-d2d6f61e5478',147),('9420e12c-3003-441a-be79-d2d6f61e5478',148),('9420e12c-3003-441a-be79-d2d6f61e5478',149),('9420e12c-3003-441a-be79-d2d6f61e5478',150),('9420e12c-3003-441a-be79-d2d6f61e5478',151),('9420e12c-3003-441a-be79-d2d6f61e5478',152),('9420e12c-3003-441a-be79-d2d6f61e5478',153),('9420e12c-3003-441a-be79-d2d6f61e5478',163),('9420e12c-3003-441a-be79-d2d6f61e5478',164),('9420e12c-3003-441a-be79-d2d6f61e5478',71),('9420e12c-3003-441a-be79-d2d6f61e5478',72),('9420e12c-3003-441a-be79-d2d6f61e5478',165),('9420e12c-3003-441a-be79-d2d6f61e5478',166),('9420e12c-3003-441a-be79-d2d6f61e5478',167),('9420e12c-3003-441a-be79-d2d6f61e5478',169),('9420e12c-3003-441a-be79-d2d6f61e5478',171),('9420e12c-3003-441a-be79-d2d6f61e5478',99),('9420e12c-3003-441a-be79-d2d6f61e5478',100),('9420e12c-3003-441a-be79-d2d6f61e5478',101),('9420e12c-3003-441a-be79-d2d6f61e5478',56),('9420e12c-3003-441a-be79-d2d6f61e5478',16),('9420e12c-3003-441a-be79-d2d6f61e5478',92),('815f9d61-38bd-4ee9-a603-011f3864ac9f',4),('815f9d61-38bd-4ee9-a603-011f3864ac9f',22),('9420e12c-3003-441a-be79-d2d6f61e5478',157),('815f9d61-38bd-4ee9-a603-011f3864ac9f',27),('9420e12c-3003-441a-be79-d2d6f61e5478',22),('9420e12c-3003-441a-be79-d2d6f61e5478',93),('9420e12c-3003-441a-be79-d2d6f61e5478',98),('9420e12c-3003-441a-be79-d2d6f61e5478',85),('9420e12c-3003-441a-be79-d2d6f61e5478',66),('815f9d61-38bd-4ee9-a603-011f3864ac9f',3),('815f9d61-38bd-4ee9-a603-011f3864ac9f',42),('815f9d61-38bd-4ee9-a603-011f3864ac9f',43),('815f9d61-38bd-4ee9-a603-011f3864ac9f',58),('815f9d61-38bd-4ee9-a603-011f3864ac9f',59),('9420e12c-3003-441a-be79-d2d6f61e5478',69),('9420e12c-3003-441a-be79-d2d6f61e5478',63),('9420e12c-3003-441a-be79-d2d6f61e5478',27),('9420e12c-3003-441a-be79-d2d6f61e5478',68),('9420e12c-3003-441a-be79-d2d6f61e5478',67),('9420e12c-3003-441a-be79-d2d6f61e5478',57),('9420e12c-3003-441a-be79-d2d6f61e5478',70),('9420e12c-3003-441a-be79-d2d6f61e5478',156),('9420e12c-3003-441a-be79-d2d6f61e5478',52),('9420e12c-3003-441a-be79-d2d6f61e5478',34),('9420e12c-3003-441a-be79-d2d6f61e5478',35),('9420e12c-3003-441a-be79-d2d6f61e5478',133),('9420e12c-3003-441a-be79-d2d6f61e5478',134),('9420e12c-3003-441a-be79-d2d6f61e5478',135),('9420e12c-3003-441a-be79-d2d6f61e5478',136),('9420e12c-3003-441a-be79-d2d6f61e5478',96),('9420e12c-3003-441a-be79-d2d6f61e5478',154),('9420e12c-3003-441a-be79-d2d6f61e5478',155),('9420e12c-3003-441a-be79-d2d6f61e5478',90),('9420e12c-3003-441a-be79-d2d6f61e5478',91),('9420e12c-3003-441a-be79-d2d6f61e5478',143),('9420e12c-3003-441a-be79-d2d6f61e5478',78),('9420e12c-3003-441a-be79-d2d6f61e5478',87),('9420e12c-3003-441a-be79-d2d6f61e5478',17),('9420e12c-3003-441a-be79-d2d6f61e5478',88),('9420e12c-3003-441a-be79-d2d6f61e5478',112),('9420e12c-3003-441a-be79-d2d6f61e5478',111),('9420e12c-3003-441a-be79-d2d6f61e5478',110),('9420e12c-3003-441a-be79-d2d6f61e5478',109),('9420e12c-3003-441a-be79-d2d6f61e5478',108),('9420e12c-3003-441a-be79-d2d6f61e5478',107),('9420e12c-3003-441a-be79-d2d6f61e5478',106),('9420e12c-3003-441a-be79-d2d6f61e5478',77),('9420e12c-3003-441a-be79-d2d6f61e5478',65),('9420e12c-3003-441a-be79-d2d6f61e5478',86),('9420e12c-3003-441a-be79-d2d6f61e5478',137),('9420e12c-3003-441a-be79-d2d6f61e5478',128),('9420e12c-3003-441a-be79-d2d6f61e5478',62),('9420e12c-3003-441a-be79-d2d6f61e5478',4),('9420e12c-3003-441a-be79-d2d6f61e5478',127),('9420e12c-3003-441a-be79-d2d6f61e5478',104),('9420e12c-3003-441a-be79-d2d6f61e5478',105),('9420e12c-3003-441a-be79-d2d6f61e5478',146),('9420e12c-3003-441a-be79-d2d6f61e5478',141),('9420e12c-3003-441a-be79-d2d6f61e5478',139),('9420e12c-3003-441a-be79-d2d6f61e5478',113),('9420e12c-3003-441a-be79-d2d6f61e5478',76),('9420e12c-3003-441a-be79-d2d6f61e5478',142),('9420e12c-3003-441a-be79-d2d6f61e5478',89),('9420e12c-3003-441a-be79-d2d6f61e5478',2),('9420e12c-3003-441a-be79-d2d6f61e5478',129),('9420e12c-3003-441a-be79-d2d6f61e5478',131),('9420e12c-3003-441a-be79-d2d6f61e5478',3),('9420e12c-3003-441a-be79-d2d6f61e5478',140),('9420e12c-3003-441a-be79-d2d6f61e5478',126),('9420e12c-3003-441a-be79-d2d6f61e5478',33),('9420e12c-3003-441a-be79-d2d6f61e5478',103),('9420e12c-3003-441a-be79-d2d6f61e5478',102),('815f9d61-38bd-4ee9-a603-011f3864ac9f',21),('815f9d61-38bd-4ee9-a603-011f3864ac9f',2),('815f9d61-38bd-4ee9-a603-011f3864ac9f',17),('815f9d61-38bd-4ee9-a603-011f3864ac9f',33),('815f9d61-38bd-4ee9-a603-011f3864ac9f',41),('815f9d61-38bd-4ee9-a603-011f3864ac9f',47),('815f9d61-38bd-4ee9-a603-011f3864ac9f',57),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',2),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',3),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',4),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',16),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',17),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',22),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',27),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',33),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',34),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',35),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',52),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',56),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',57),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',62),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',63),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',65),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',66),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',67),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',68),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',69),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',70),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',71),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',72),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',76),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',77),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',78),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',84),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',85),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',86),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',87),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',88),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',89),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',90),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',91),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',95),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',96),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',99),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',100),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',101),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',102),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',103),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',104),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',105),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',106),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',107),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',108),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',109),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',110),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',111),('2ea5b10d-d0db-46e8-8d60-0df175cb39f3',112),('a1a1fb5b-12ee-4558-b822-5b58d26b205b',79),('815f9d61-38bd-4ee9-a603-011f3864ac9f',49),('a1a1fb5b-12ee-4558-b822-5b58d26b205b',80),('9420e12c-3003-441a-be79-d2d6f61e5478',79),('9420e12c-3003-441a-be79-d2d6f61e5478',144),('9420e12c-3003-441a-be79-d2d6f61e5478',145),('815f9d61-38bd-4ee9-a603-011f3864ac9f',18),('815f9d61-38bd-4ee9-a603-011f3864ac9f',34),('f4749e47-7bd0-4a8a-a412-38c800daaa62',2),('f4749e47-7bd0-4a8a-a412-38c800daaa62',8),('f4749e47-7bd0-4a8a-a412-38c800daaa62',11),('f4749e47-7bd0-4a8a-a412-38c800daaa62',18),('f4749e47-7bd0-4a8a-a412-38c800daaa62',19),('f4749e47-7bd0-4a8a-a412-38c800daaa62',20),('f4749e47-7bd0-4a8a-a412-38c800daaa62',21),('f4749e47-7bd0-4a8a-a412-38c800daaa62',22),('f4749e47-7bd0-4a8a-a412-38c800daaa62',31),('f4749e47-7bd0-4a8a-a412-38c800daaa62',33),('f4749e47-7bd0-4a8a-a412-38c800daaa62',34),('f4749e47-7bd0-4a8a-a412-38c800daaa62',35),('f4749e47-7bd0-4a8a-a412-38c800daaa62',47),('f4749e47-7bd0-4a8a-a412-38c800daaa62',49),('f4749e47-7bd0-4a8a-a412-38c800daaa62',52),('f4749e47-7bd0-4a8a-a412-38c800daaa62',57),('f4749e47-7bd0-4a8a-a412-38c800daaa62',70),('f4749e47-7bd0-4a8a-a412-38c800daaa62',71),('f4749e47-7bd0-4a8a-a412-38c800daaa62',72),('0664ef6a-bfa6-4a66-b876-63defcd388cd',67),('815f9d61-38bd-4ee9-a603-011f3864ac9f',30),('815f9d61-38bd-4ee9-a603-011f3864ac9f',35),('0664ef6a-bfa6-4a66-b876-63defcd388cd',27),('815f9d61-38bd-4ee9-a603-011f3864ac9f',45),('815f9d61-38bd-4ee9-a603-011f3864ac9f',52),('0664ef6a-bfa6-4a66-b876-63defcd388cd',68),('815f9d61-38bd-4ee9-a603-011f3864ac9f',11),('815f9d61-38bd-4ee9-a603-011f3864ac9f',19),('0664ef6a-bfa6-4a66-b876-63defcd388cd',99),('0664ef6a-bfa6-4a66-b876-63defcd388cd',100),('0664ef6a-bfa6-4a66-b876-63defcd388cd',101),('0664ef6a-bfa6-4a66-b876-63defcd388cd',17),('0664ef6a-bfa6-4a66-b876-63defcd388cd',16),('0664ef6a-bfa6-4a66-b876-63defcd388cd',133),('0664ef6a-bfa6-4a66-b876-63defcd388cd',134),('0664ef6a-bfa6-4a66-b876-63defcd388cd',135),('0664ef6a-bfa6-4a66-b876-63defcd388cd',136),('0664ef6a-bfa6-4a66-b876-63defcd388cd',96),('0664ef6a-bfa6-4a66-b876-63defcd388cd',90),('0664ef6a-bfa6-4a66-b876-63defcd388cd',91),('0664ef6a-bfa6-4a66-b876-63defcd388cd',78),('0664ef6a-bfa6-4a66-b876-63defcd388cd',87),('0664ef6a-bfa6-4a66-b876-63defcd388cd',88),('0664ef6a-bfa6-4a66-b876-63defcd388cd',86),('0664ef6a-bfa6-4a66-b876-63defcd388cd',129);
/*!40000 ALTER TABLE `sys_role_module_bak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_session`
--

DROP TABLE IF EXISTS `sys_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_session` (
  `id` varchar(50) NOT NULL,
  `lastaccess` datetime DEFAULT NULL COMMENT '最后过期时间',
  `expire` decimal(10,0) DEFAULT NULL,
  `cookie` varchar(200) DEFAULT NULL,
  `dtsession` text,
  `token` text COMMENT '令牌',
  `user_id` varchar(50) DEFAULT NULL COMMENT '访问用户',
  `start_time` varchar(200) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `agent` text,
  `client` varchar(100) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_sessionind1` (`cookie`),
  KEY `sys_sessionidx` (`dr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_session`
--

LOCK TABLES `sys_session` WRITE;
/*!40000 ALTER TABLE `sys_session` DISABLE KEYS */;
INSERT INTO `sys_session` VALUES ('28c7a3a29826d835fc4cfa2b52cdccf6','2020-08-06 23:18:04',NULL,'d88f0750-ed3d-4ac7-8f57-b4504154d07b','rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIA23QAJGQ4OGYwNzUwLWVkM2QtNGFjNy04ZjU3LWI0NTA0MTU0ZDA3YnNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAXPGEGj/eHNxAH4AA3cIAAABc8YROHJ4dxkAAAAAAG3dAAAPMDowOjA6MDowOjA6MDoxc3IAEWphdmEudXRpbC5IYXNoTWFwBQfawcMWYNEDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAADHcIAAAAEAAAAAV0AAtzZXNzaW9uRmxhZ3NyABFqYXZhLmxhbmcuQm9vbGVhbs0gcoDVnPruAgABWgAFdmFsdWV4cAF0AAd1c2VyX2lkdAATMTE1MTQyMDIzNTE5NjU4ODAzM3QAUG9yZy5hcGFjaGUuc2hpcm8uc3ViamVjdC5zdXBwb3J0LkRlZmF1bHRTdWJqZWN0Q29udGV4dF9BVVRIRU5USUNBVEVEX1NFU1NJT05fS0VZcQB+AAp0AE1vcmcuYXBhY2hlLnNoaXJvLnN1YmplY3Quc3VwcG9ydC5EZWZhdWx0U3ViamVjdENvbnRleHRfUFJJTkNJUEFMU19TRVNTSU9OX0tFWXNyADJvcmcuYXBhY2hlLnNoaXJvLnN1YmplY3QuU2ltcGxlUHJpbmNpcGFsQ29sbGVjdGlvbqh/WCXGowhKAwABTAAPcmVhbG1QcmluY2lwYWxzdAAPTGphdmEvdXRpbC9NYXA7eHBzcgAXamF2YS51dGlsLkxpbmtlZEhhc2hNYXA0wE5cEGzA+wIAAVoAC2FjY2Vzc09yZGVyeHEAfgAGP0AAAAAAAAx3CAAAABAAAAABdAAgY29tLmR0LmNvcmUuc2hpcm8uU2hpcm9EYlJlYWxtXzBzcgAXamF2YS51dGlsLkxpbmtlZEhhc2hTZXTYbNdald0qHgIAAHhyABFqYXZhLnV0aWwuSGFzaFNldLpEhZWWuLc0AwAAeHB3DAAAABA/QAAAAAAAAXNyABtjb20uZHQuY29yZS5zaGlyby5TaGlyb1VzZXIAAAAAAAAAAQIABUwAB2FjY291bnR0ABJMamF2YS9sYW5nL1N0cmluZztMAAJpZHEAfgAZTAAEbmFtZXEAfgAZTAAIcm9sZUxpc3R0ABBMamF2YS91dGlsL0xpc3Q7TAAJcm9sZU5hbWVzcQB+ABp4cHQABWFkbWlucQB+AAxxAH4AHHNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAABdwQAAAABdAATMTIyNDE0MjM3NTg3ODc2MjQ5N3hzcQB+AB0AAAABdwQAAAABdAAP6LaF57qn566h55CG5ZGYeHh4AHcBAXEAfgATeHQACXNoaXJvVXNlcnEAfgAbeHg=','d88f0750-ed3d-4ac7-8f57-b4504154d07b',NULL,'2020-08-07 07:17:11',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,'0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uflo_tpl`
--

DROP TABLE IF EXISTS `sys_uflo_tpl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uflo_tpl` (
  `id` varchar(50) NOT NULL,
  `filename` varchar(500) DEFAULT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uflo_tpl`
--

LOCK TABLES `sys_uflo_tpl` WRITE;
/*!40000 ALTER TABLE `sys_uflo_tpl` DISABLE KEYS */;
INSERT INTO `sys_uflo_tpl` VALUES ('1276371514035331073','资产领用流程模版.uflo.xml','<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"zcly\"><end name=\"end\" x=\"285\" y=\"346\" width=\"40\" height=\"70\"  terminate=\"true\"></end><task name=\"user1\" x=\"174\" y=\"211\" width=\"35\" height=\"67\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"???\" id=\"1274968965654900737\"/> <description><![CDATA[user1 任务审批]]></description><sequence-flow g=\"\" type=\"line\" to=\"user2\"></sequence-flow></task><task name=\"user2\" x=\"290\" y=\"211\" width=\"39\" height=\"70\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"??\" id=\"1276368794897408001\"/> <description><![CDATA[user2任务审批\n]]></description><sequence-flow g=\"\" type=\"line\" to=\"end\"></sequence-flow></task><start name=\"start1\" x=\"169\" y=\"87\" width=\"40\" height=\"70\" ><sequence-flow g=\"\" type=\"line\" to=\"user1\"></sequence-flow></start></uflo-process>','0','null','2020-06-26 04:27:37','null','2020-06-26 04:27:37');
/*!40000 ALTER TABLE `sys_uflo_tpl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_group`
--

DROP TABLE IF EXISTS `sys_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_group` (
  `group_id` varchar(50) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '组名称',
  `sort` decimal(5,0) DEFAULT NULL COMMENT '排序',
  `mark` text COMMENT '备注',
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_group`
--

LOCK TABLES `sys_user_group` WRITE;
/*!40000 ALTER TABLE `sys_user_group` DISABLE KEYS */;
INSERT INTO `sys_user_group` VALUES ('1022864359243059201','测试组',1211,'1','1151420235196588033','sys','2018-07-27 23:20:41','2019-11-06 18:56:16','0'),('1022864392755548162','开发组',1,'2','sys','sys','2018-07-27 23:20:49','2018-07-27 23:20:49','0');
/*!40000 ALTER TABLE `sys_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_group_item`
--

DROP TABLE IF EXISTS `sys_user_group_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_group_item` (
  `id` varchar(50) NOT NULL,
  `group_id` varchar(50) DEFAULT NULL COMMENT '组ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_group_item`
--

LOCK TABLES `sys_user_group_item` WRITE;
/*!40000 ALTER TABLE `sys_user_group_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_group_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_homeaddr`
--

DROP TABLE IF EXISTS `sys_user_homeaddr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_homeaddr` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `provinceid` varchar(50) DEFAULT NULL,
  `provincename` text,
  `cityid` varchar(50) DEFAULT NULL,
  `cityname` text,
  `areaid` varchar(50) DEFAULT NULL,
  `areaname` text,
  `ct` text,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_homeaddr`
--

LOCK TABLES `sys_user_homeaddr` WRITE;
/*!40000 ALTER TABLE `sys_user_homeaddr` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_homeaddr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_info`
--

DROP TABLE IF EXISTS `sys_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_info` (
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `empl_id` varchar(50) NOT NULL COMMENT '工号',
  `user_name` varchar(100) NOT NULL COMMENT 'user_name,建议唯一',
  `user_type` varchar(50) NOT NULL COMMENT '用户类型:sys,crm,wx',
  `nickname` varchar(200) DEFAULT NULL COMMENT '昵称',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `org_id` varchar(50) DEFAULT NULL COMMENT '组织Id',
  `locked` char(1) NOT NULL COMMENT '是否允许登陆 Y|N',
  `token` varchar(100) DEFAULT NULL COMMENT '用户令牌',
  `tel` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `profile` text COMMENT '介绍',
  `mark` varchar(200) DEFAULT NULL COMMENT '备注',
  `homeaddr_def` varchar(50) DEFAULT NULL COMMENT '家庭地址',
  `receaddr_def` varchar(50) DEFAULT NULL COMMENT '收货地址',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `weixin` varchar(50) DEFAULT NULL COMMENT '微信号',
  `sex` char(1) DEFAULT NULL COMMENT '1男,2女',
  `native_place` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `photo` varchar(50) DEFAULT NULL COMMENT '头像',
  `self_evaluate` text COMMENT '自我评价',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '创建时IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登陆的IP',
  `system_id` varchar(50) DEFAULT NULL COMMENT '系统默认主菜单ID',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信open_id',
  `score` decimal(10,0) DEFAULT NULL COMMENT '积分',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '我的店铺',
  `avatarurl` text COMMENT '微信头像',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `card` varchar(50) DEFAULT NULL COMMENT '结算卡ID',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `famount` decimal(10,2) DEFAULT NULL COMMENT '其他冻结金额',
  `tixamount` decimal(10,2) DEFAULT NULL COMMENT '提现金额',
  `credit_score` decimal(6,0) DEFAULT NULL COMMENT '信用分',
  `identity_card` varchar(100) DEFAULT NULL COMMENT '身份证',
  `driver_card` text COMMENT '驾照',
  `shortmobile` varchar(100) DEFAULT NULL COMMENT '手机短号',
  `nation` text COMMENT '民族',
  `ali_pay_username` text COMMENT '支付宝姓名',
  `ali_pay_account` text COMMENT '支付宝账户',
  `dr` varchar(1) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_info`
--

LOCK TABLES `sys_user_info` WRITE;
/*!40000 ALTER TABLE `sys_user_info` DISABLE KEYS */;
INSERT INTO `sys_user_info` VALUES ('1151420235196588033','65c33c0492274cb3aec013008f335bb9','admin','system',NULL,'管理员','oracle',NULL,NULL,'N',NULL,'00000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1079008526457073665',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','sys','2019-07-17 09:15:51','1151420235196588033','2020-06-27 07:07:02'),('1274968734037045250','000297','user2','empl',NULL,'王小二','oracle',NULL,NULL,'N',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1151420235196588033','2020-06-22 07:33:29','1151420235196588033','2020-06-22 07:33:29'),('1274968798834847746','000298','user3','empl',NULL,'李斌','oracle',NULL,NULL,'N',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1151420235196588033','2020-06-22 07:33:44','1151420235196588033','2020-07-30 20:57:08'),('1274968965654900737','000299','user4','empl',NULL,'金歆明','oracle',NULL,NULL,'N',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1151420235196588033','2020-06-22 07:34:24','1151420235196588033','2020-06-22 07:34:24'),('1276368794897408001','000300','user1','empl',NULL,'龚晓','oracle',NULL,NULL,'N',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1151420235196588033','2020-06-26 04:16:49','1151420235196588033','2020-06-26 04:16:49'),('1290895160515846145','000301','65bce09bcbd546eab54f2cefe782927a','empl',NULL,'测试','oracle',NULL,NULL,'N',NULL,'12345612345',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1151420235196588033','2020-08-05 06:19:25','1151420235196588033','2020-08-05 06:19:25'),('1290895269458698241','64e6e351a8524af59244d2fc6251658f','ceshi','system',NULL,'ceshi','oracle',NULL,NULL,'N',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1151420235196588033','2020-08-05 06:19:51','1151420235196588033','2020-08-05 06:19:51');
/*!40000 ALTER TABLE `sys_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_receivingaddr`
--

DROP TABLE IF EXISTS `sys_user_receivingaddr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_receivingaddr` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `provinceid` varchar(50) DEFAULT NULL,
  `provincecode` varchar(50) DEFAULT NULL,
  `provincename` text,
  `cityid` varchar(50) DEFAULT NULL,
  `citycode` varchar(50) DEFAULT NULL,
  `cityname` text,
  `areaid` varchar(50) DEFAULT NULL,
  `areacode` varchar(50) DEFAULT NULL,
  `areaname` text,
  `ct` text,
  `contactuser` text,
  `contact` text,
  `zcode` varchar(50) DEFAULT NULL,
  `od` decimal(10,0) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_receivingaddr`
--

LOCK TABLES `sys_user_receivingaddr` WRITE;
/*!40000 ALTER TABLE `sys_user_receivingaddr` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_receivingaddr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('1276369501876707329','1151420235196588033','1224142375878762497'),('1276369506238783489','1274968798834847746','1224142375878762497'),('1276369508063305729','1274968965654900737','1224142375878762497'),('1276369509824913410','1276368794897408001','1224142375878762497'),('1288371696231890945','1274968734037045250','1224133874641014785'),('1288371696345137153','1274968734037045250','1189530258996936705'),('1288371696458383361','1274968734037045250','1196644452732612609'),('1288371696575823874','1274968734037045250','1224134024180535298'),('1288371696689070082','1274968734037045250','1193214328922677250'),('1288371696806510593','1274968734037045250','1193454934454403073'),('1288371696919756801','1274968734037045250','1224133759469621249'),('1288371697033003010','1274968734037045250','1224142375878762497');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_blob`
--

DROP TABLE IF EXISTS `uflo_blob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_blob` (
  `ID_` bigint(20) NOT NULL,
  `BLOB_VALUE_` longblob,
  `NAME_` varchar(60) DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_blob`
--

LOCK TABLES `uflo_blob` WRITE;
/*!40000 ALTER TABLE `uflo_blob` DISABLE KEYS */;
INSERT INTO `uflo_blob` VALUES (303,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"请假审批\"><start name=\"开始\" x=\"119\" y=\"147\" width=\"39\" height=\"69\"  label=\"请假单填写\" task-name=\"holiday_task\"><sequence-flow g=\"\" type=\"line\" to=\"部门审批\"></sequence-flow></start><end name=\"结束流程1\" x=\"491\" y=\"124\" width=\"41\" height=\"67\"  terminate=\"true\"></end><task name=\"经理审批\" x=\"375\" y=\"209\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><sequence-flow g=\"\" type=\"line\" to=\"结束流程1\"></sequence-flow></task><task name=\"部门审批\" x=\"267\" y=\"188\" width=\"40\" height=\"70\"  label=\"部门审批2\" task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><sequence-flow g=\"\" type=\"line\" to=\"经理审批\"></sequence-flow></task></uflo-process>','请假审批.uflo.xml',302),(305,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"请假审批\"><start name=\"开始\" x=\"119\" y=\"147\" width=\"39\" height=\"69\"  label=\"请假单填写\" task-name=\"holiday_task\"><sequence-flow g=\"\" type=\"line\" to=\"部门审批\"></sequence-flow></start><end name=\"结束流程1\" x=\"491\" y=\"124\" width=\"41\" height=\"67\"  terminate=\"true\"></end><task name=\"经理审批\" x=\"375\" y=\"209\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><sequence-flow g=\"\" type=\"line\" to=\"结束流程1\"></sequence-flow></task><task name=\"部门审批\" x=\"267\" y=\"188\" width=\"40\" height=\"70\"  label=\"部门审批2\" task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><sequence-flow g=\"\" type=\"line\" to=\"经理审批\"></sequence-flow></task></uflo-process>','请假审批.uflo.xml',304),(332,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"请假审批2222\"><start name=\"开始\" x=\"118\" y=\"147\" width=\"39\" height=\"69\"  label=\"请假单填写\" task-name=\"holiday_task\"><sequence-flow g=\"\" type=\"line\" to=\"部门审批\"></sequence-flow></start><end name=\"结束流程1\" x=\"490\" y=\"124\" width=\"41\" height=\"67\"  terminate=\"true\"></end><task name=\"经理审批\" x=\"376\" y=\"209\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><sequence-flow g=\"\" type=\"line\" to=\"结束流程1\"></sequence-flow></task><task name=\"部门审批\" x=\"267\" y=\"188\" width=\"40\" height=\"70\"  label=\"部门审批2\" task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><sequence-flow g=\"\" type=\"line\" to=\"经理审批\"></sequence-flow></task></uflo-process>','请假审批2222.uflo.xml',331),(337,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"请假审批\" category-id=\"holidy\" start-process-url=\"http://www.baidu.com\"><start name=\"开始\" x=\"118\" y=\"147\" width=\"39\" height=\"69\"  label=\"请假单填写\" task-name=\"holiday_task\"><sequence-flow g=\"\" type=\"line\" to=\"部门审批\"></sequence-flow></start><end name=\"结束流程1\" x=\"490\" y=\"124\" width=\"41\" height=\"67\"  terminate=\"true\"></end><task name=\"经理审批\" x=\"376\" y=\"209\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><user-data value=\"审批意见\" key=\"suggest\"/><sequence-flow g=\"\" type=\"line\" to=\"结束流程1\"></sequence-flow></task><task name=\"部门审批\" x=\"268\" y=\"188\" width=\"40\" height=\"70\"  label=\"部门审批2\" task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><user-data value=\"审批意见\" key=\"suggest\"/><sequence-flow g=\"\" type=\"line\" to=\"经理审批\"></sequence-flow></task></uflo-process>','请假审批.uflo.xml',336),(402,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"请假审批\" category-id=\"holidy\" start-process-url=\"http://www.baidu.com\" event-handler-bean=\"FlowProcessEventHandler\"><start name=\"开始\" x=\"118\" y=\"147\" width=\"39\" height=\"69\"  label=\"请假单填写\" task-name=\"holiday_task\"><sequence-flow g=\"\" type=\"line\" to=\"部门审批\"></sequence-flow></start><end name=\"结束流程1\" x=\"490\" y=\"124\" width=\"41\" height=\"67\"  terminate=\"true\"></end><task name=\"经理审批\" x=\"376\" y=\"209\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><user-data value=\"审批意见\" key=\"suggest\"/><sequence-flow g=\"\" type=\"line\" to=\"结束流程1\"></sequence-flow></task><task name=\"部门审批\" x=\"268\" y=\"188\" width=\"40\" height=\"70\"  label=\"部门审批2\" task-type=\"Normal\" assignment-type=\"ProcessPromoter\" allow-specify-assignee=\"false\"><user-data value=\"审批意见\" key=\"suggest\"/><sequence-flow g=\"\" type=\"line\" to=\"经理审批\"></sequence-flow></task></uflo-process>','请假审批.uflo.xml',401),(502,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"zcly_flow\"><start name=\"start\" x=\"44\" y=\"114\" width=\"40\" height=\"70\" ><sequence-flow g=\"\" type=\"line\" to=\"user1\"></sequence-flow></start><end name=\"end\" x=\"387\" y=\"118\" width=\"40\" height=\"70\"  terminate=\"true\"></end><task name=\"user1\" x=\"145\" y=\"114\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"user1\" id=\"1248062045821173762\"/><sequence-flow g=\"\" type=\"line\" to=\"user2\"></sequence-flow></task><task name=\"user2\" x=\"269\" y=\"123\" width=\"65\" height=\"57\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"user2\" id=\"1248062124036554754\"/><sequence-flow g=\"\" type=\"line\" to=\"end\"></sequence-flow></task></uflo-process>','zcly_flow.uflo.xml',501),(2302,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"DEMO\"><start name=\"开始1\" x=\"23\" y=\"132\" width=\"42\" height=\"77\" ><sequence-flow g=\"\" type=\"line\" to=\"人工任务1\"></sequence-flow></start><end name=\"结束流程1\" x=\"426\" y=\"140\" width=\"40\" height=\"70\"  terminate=\"true\"></end><task name=\"人工任务1\" x=\"136\" y=\"141\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"金歆明\" id=\"1274968965654900737\"/><sequence-flow g=\"\" type=\"line\" to=\"人工任务2\"></sequence-flow></task><task name=\"人工任务2\" x=\"285\" y=\"138\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"王小二\" id=\"1274968734037045250\"/><sequence-flow g=\"\" type=\"line\" to=\"结束流程1\"></sequence-flow></task></uflo-process>','DEMO.uflo.xml',2301),(2502,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"zcly\"><end name=\"end\" x=\"285\" y=\"346\" width=\"40\" height=\"70\"  terminate=\"true\"></end><task name=\"user1\" x=\"174\" y=\"211\" width=\"35\" height=\"67\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"???\" id=\"1274968965654900737\"/><sequence-flow g=\"\" type=\"line\" to=\"user2\"></sequence-flow></task><task name=\"user2\" x=\"288\" y=\"211\" width=\"40\" height=\"70\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"??\" id=\"1276368794897408001\"/><sequence-flow g=\"\" type=\"line\" to=\"end\"></sequence-flow></task><start name=\"start\" x=\"170\" y=\"87\" width=\"40\" height=\"70\" ><sequence-flow g=\"\" type=\"line\" to=\"user1\"></sequence-flow></start></uflo-process>','zcly.uflo.xml',2501),(2809,_binary '<?xml version=\"1.0\" encoding=\"utf-8\"?><uflo-process name=\"zcly\"><end name=\"end\" x=\"285\" y=\"346\" width=\"40\" height=\"70\"  terminate=\"true\"></end><task name=\"user1\" x=\"174\" y=\"211\" width=\"35\" height=\"67\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"???\" id=\"1274968965654900737\"/> <description><![CDATA[user1 任务审批]]></description><sequence-flow g=\"\" type=\"line\" to=\"user2\"></sequence-flow></task><task name=\"user2\" x=\"290\" y=\"211\" width=\"39\" height=\"70\"  task-type=\"Normal\" assignment-type=\"Assignee\" allow-specify-assignee=\"false\"><assignee provider-id=\"ufloUserInfoAssigneeProvider\" name=\"??\" id=\"1276368794897408001\"/> <description><![CDATA[user2任务审批\n]]></description><sequence-flow g=\"\" type=\"line\" to=\"end\"></sequence-flow></task><start name=\"start1\" x=\"169\" y=\"87\" width=\"40\" height=\"70\" ><sequence-flow g=\"\" type=\"line\" to=\"user1\"></sequence-flow></start></uflo-process>','zcly.uflo.xml',2808);
/*!40000 ALTER TABLE `uflo_blob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_calendar`
--

DROP TABLE IF EXISTS `uflo_calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_calendar` (
  `ID_` bigint(20) NOT NULL,
  `CATEGORY_ID_` varchar(60) DEFAULT NULL,
  `DESC_` varchar(120) DEFAULT NULL,
  `NAME_` varchar(60) DEFAULT NULL,
  `TYPE_` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_calendar`
--

LOCK TABLES `uflo_calendar` WRITE;
/*!40000 ALTER TABLE `uflo_calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_calendar_date`
--

DROP TABLE IF EXISTS `uflo_calendar_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_calendar_date` (
  `ID_` bigint(20) NOT NULL,
  `CALENDAR_DATE_` datetime DEFAULT NULL,
  `CALENDAR_ID_` bigint(20) DEFAULT NULL,
  `DAY_OF_MONTH_` int(11) DEFAULT NULL,
  `DAY_OF_WEEK_` int(11) DEFAULT NULL,
  `MONTH_OF_YEAR_` int(11) DEFAULT NULL,
  `NAME_` varchar(60) DEFAULT NULL,
  `RANGE_END_TIME_` varchar(60) DEFAULT NULL,
  `RANGE_START_TIME_` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_calendar_date`
--

LOCK TABLES `uflo_calendar_date` WRITE;
/*!40000 ALTER TABLE `uflo_calendar_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_calendar_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_context_property`
--

DROP TABLE IF EXISTS `uflo_context_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_context_property` (
  `KEY_` varchar(120) NOT NULL,
  `VALUE_` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_context_property`
--

LOCK TABLES `uflo_context_property` WRITE;
/*!40000 ALTER TABLE `uflo_context_property` DISABLE KEYS */;
INSERT INTO `uflo_context_property` VALUES ('dbid','3600');
/*!40000 ALTER TABLE `uflo_context_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_his_activity`
--

DROP TABLE IF EXISTS `uflo_his_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_his_activity` (
  `ID_` bigint(20) NOT NULL,
  `DESCRIPTION_` varchar(120) DEFAULT NULL,
  `NODE_NAME_` varchar(60) DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  `CREATE_DATE_` datetime DEFAULT NULL,
  `END_DATE_` datetime DEFAULT NULL,
  `HIS_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `LEAVE_FLOW_NAME_` varchar(60) DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `ROOT_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_his_activity`
--

LOCK TABLES `uflo_his_activity` WRITE;
/*!40000 ALTER TABLE `uflo_his_activity` DISABLE KEYS */;
INSERT INTO `uflo_his_activity` VALUES (308,NULL,'开始',304,'2019-09-02 10:15:49',NULL,307,NULL,306,306),(311,NULL,'部门审批',304,'2019-09-02 10:15:49','2019-09-02 10:17:50',307,NULL,306,306),(315,NULL,'经理审批',304,'2019-09-02 10:17:50','2019-09-02 10:18:24',307,NULL,306,306),(318,NULL,'结束流程1',304,'2019-09-02 10:18:24',NULL,307,NULL,306,306),(322,NULL,'开始',302,'2019-09-02 10:22:50',NULL,320,NULL,319,319),(325,NULL,'部门审批',302,'2019-09-02 10:22:50','2019-09-02 10:24:24',320,NULL,319,319),(329,NULL,'经理审批',302,'2019-09-02 10:24:24','2019-09-02 10:31:18',320,NULL,319,319),(335,NULL,'结束流程1',302,'2019-09-02 10:31:18',NULL,320,NULL,319,319),(340,NULL,'开始',336,'2019-09-02 10:35:16',NULL,339,NULL,338,338),(343,NULL,'部门审批',336,'2019-09-02 10:35:16','2019-09-02 10:35:37',339,NULL,338,338),(347,NULL,'经理审批',336,'2019-09-02 10:35:37','2019-09-02 10:35:52',339,NULL,338,338),(350,NULL,'结束流程1',336,'2019-09-02 10:35:52',NULL,339,NULL,338,338),(405,NULL,'开始',401,'2019-09-02 11:18:03',NULL,404,NULL,403,403),(408,NULL,'部门审批',401,'2019-09-02 11:18:03',NULL,404,NULL,403,403),(505,NULL,'start',501,'2020-04-09 01:50:01',NULL,504,NULL,503,503),(508,NULL,'user1',501,'2020-04-09 01:50:01','2020-04-09 01:51:34',504,NULL,503,503),(511,NULL,'user2',501,'2020-04-09 01:51:34','2020-04-09 01:51:52',504,NULL,503,503),(514,NULL,'end',501,'2020-04-09 01:51:52',NULL,504,NULL,503,503),(519,NULL,'start',501,'2020-04-09 05:15:50',NULL,518,NULL,517,517),(522,NULL,'user1',501,'2020-04-09 05:15:50',NULL,518,NULL,517,517),(526,NULL,'start',501,'2020-04-09 05:15:57',NULL,525,NULL,524,524),(529,NULL,'user1',501,'2020-04-09 05:15:57',NULL,525,NULL,524,524),(603,NULL,'start',501,'2020-04-11 07:07:32',NULL,602,NULL,601,601),(606,NULL,'user1',501,'2020-04-11 07:07:33',NULL,602,NULL,601,601),(610,NULL,'start',501,'2020-04-11 07:09:42',NULL,609,NULL,608,608),(613,NULL,'user1',501,'2020-04-11 07:09:43',NULL,609,NULL,608,608),(617,NULL,'start',501,'2020-04-11 07:11:36',NULL,616,NULL,615,615),(620,NULL,'user1',501,'2020-04-11 07:11:37',NULL,616,NULL,615,615),(703,NULL,'start',501,'2020-04-14 01:45:13',NULL,702,NULL,701,701),(706,NULL,'user1',501,'2020-04-14 01:45:13',NULL,702,NULL,701,701),(803,NULL,'start',501,'2020-04-16 11:38:06',NULL,802,NULL,801,801),(806,NULL,'user1',501,'2020-04-16 11:38:06',NULL,802,NULL,801,801),(810,NULL,'start',501,'2020-04-16 11:41:15',NULL,809,NULL,808,808),(813,NULL,'user1',501,'2020-04-16 11:41:15',NULL,809,NULL,808,808),(1003,NULL,'start',501,'2020-04-17 16:25:21',NULL,1002,NULL,1001,1001),(1006,NULL,'user1',501,'2020-04-17 16:25:22',NULL,1002,NULL,1001,1001),(1103,NULL,'start',501,'2020-04-20 02:37:51',NULL,1102,NULL,1101,1101),(1106,NULL,'user1',501,'2020-04-20 02:37:51',NULL,1102,NULL,1101,1101),(1110,NULL,'start',501,'2020-04-20 02:43:20',NULL,1109,NULL,1108,1108),(1113,NULL,'user1',501,'2020-04-20 02:43:21',NULL,1109,NULL,1108,1108),(1117,NULL,'start',501,'2020-04-20 03:05:13',NULL,1116,NULL,1115,1115),(1120,NULL,'user1',501,'2020-04-20 03:05:14',NULL,1116,NULL,1115,1115),(1203,NULL,'start',501,'2020-04-20 05:08:17',NULL,1202,NULL,1201,1201),(1206,NULL,'user1',501,'2020-04-20 05:08:18',NULL,1202,NULL,1201,1201),(1303,NULL,'start',501,'2020-04-21 05:28:16',NULL,1302,NULL,1301,1301),(1306,NULL,'user1',501,'2020-04-21 05:28:16',NULL,1302,NULL,1301,1301),(1410,NULL,'start',501,'2020-04-23 02:25:52',NULL,1409,NULL,1408,1408),(1413,NULL,'user1',501,'2020-04-23 02:25:52','2020-04-23 02:26:16',1409,NULL,1408,1408),(1416,NULL,'user2',501,'2020-04-23 02:26:16',NULL,1409,NULL,1408,1408),(1503,NULL,'开始',331,'2020-04-26 06:19:58',NULL,1502,NULL,1501,1501),(1506,NULL,'部门审批',331,'2020-04-26 06:19:58',NULL,1502,NULL,1501,1501),(1603,NULL,'start',501,'2020-04-26 09:08:18',NULL,1602,NULL,1601,1601),(1606,NULL,'user1',501,'2020-04-26 09:08:18','2020-05-09 07:39:09',1602,NULL,1601,1601),(1704,NULL,'开始',304,'2020-04-27 07:26:49',NULL,1703,NULL,1702,1702),(1707,NULL,'部门审批',304,'2020-04-27 07:26:49','2020-04-27 07:26:58',1703,NULL,1702,1702),(1710,NULL,'经理审批',304,'2020-04-27 07:26:58','2020-04-27 07:27:05',1703,NULL,1702,1702),(1713,NULL,'结束流程1',304,'2020-04-27 07:27:05',NULL,1703,NULL,1702,1702),(1803,NULL,'start',501,'2020-04-30 02:09:55',NULL,1802,NULL,1801,1801),(1806,NULL,'user1',501,'2020-04-30 02:09:55','2020-05-07 14:32:56',1802,'__temp_flow_d8d5f7ee-4673-4f16-b084-317b8ca1cf61',1801,1801),(1904,NULL,'user2',501,'2020-05-09 07:39:09',NULL,1602,NULL,1601,1601),(2003,NULL,'start',501,'2020-05-15 15:04:45',NULL,2002,NULL,2001,2001),(2006,NULL,'user1',501,'2020-05-15 15:04:45',NULL,2002,NULL,2001,2001),(2103,NULL,'start',501,'2020-05-19 10:02:34',NULL,2102,NULL,2101,2101),(2106,NULL,'user1',501,'2020-05-19 10:02:34',NULL,2102,NULL,2101,2101),(2203,NULL,'start',501,'2020-05-26 03:46:23',NULL,2202,NULL,2201,2201),(2206,NULL,'user1',501,'2020-05-26 03:46:23',NULL,2202,NULL,2201,2201),(2405,NULL,'开始1',2301,'2020-06-24 23:02:44',NULL,2404,NULL,2401,2401),(2406,NULL,'开始1',2301,'2020-06-24 23:02:44',NULL,2403,NULL,2402,2402),(2411,NULL,'人工任务1',2301,'2020-06-24 23:02:44',NULL,2404,NULL,2401,2401),(2412,NULL,'人工任务1',2301,'2020-06-24 23:02:44',NULL,2403,NULL,2402,2402),(2603,NULL,'start',2501,'2020-06-26 08:19:58',NULL,2602,NULL,2601,2601),(2606,NULL,'user1',2501,'2020-06-26 08:19:58','2020-06-26 08:22:30',2602,NULL,2601,2601),(2609,NULL,'user2',2501,'2020-06-26 08:22:30',NULL,2602,NULL,2601,2601),(2613,NULL,'start',2501,'2020-06-26 08:29:49',NULL,2612,NULL,2611,2611),(2616,NULL,'user1',2501,'2020-06-26 08:29:49',NULL,2612,NULL,2611,2611),(2703,NULL,'start',2501,'2020-06-26 08:44:59',NULL,2702,NULL,2701,2701),(2706,NULL,'user1',2501,'2020-06-26 08:44:59','2020-06-26 09:18:31',2702,NULL,2701,2701),(2709,NULL,'user2',2501,'2020-06-26 09:18:31','2020-06-26 09:21:03',2702,'__temp_flow_c01d2ffd-aeb3-4e38-9236-c64c479ae100',2701,2701),(2715,NULL,'start',2501,'2020-06-26 09:22:11',NULL,2714,NULL,2713,2713),(2718,NULL,'user1',2501,'2020-06-26 09:22:12','2020-06-26 09:26:48',2714,'__temp_flow_e69f3814-9632-4df3-9a61-6b416d753d4f',2713,2713),(2721,NULL,'end',2501,'2020-06-26 09:26:49',NULL,2714,NULL,2713,2713),(2725,NULL,'start',2501,'2020-06-26 09:32:31',NULL,2724,NULL,2723,2723),(2728,NULL,'user1',2501,'2020-06-26 09:32:31','2020-06-26 09:32:44',2724,NULL,2723,2723),(2731,NULL,'user2',2501,'2020-06-26 09:32:44','2020-06-26 09:33:12',2724,'__temp_flow_1498e1ad-ae2d-434f-bf91-a494583ecb82',2723,2723),(2734,NULL,'end',2501,'2020-06-26 09:33:12',NULL,2724,NULL,2723,2723),(2803,NULL,'start',2501,'2020-06-27 06:18:52',NULL,2802,NULL,2801,2801),(2806,NULL,'user1',2501,'2020-06-27 06:18:52','2020-06-27 07:39:41',2802,NULL,2801,2801),(2811,NULL,'user2',2501,'2020-06-27 07:39:41',NULL,2802,NULL,2801,2801),(2815,NULL,'start1',2808,'2020-06-27 07:42:40',NULL,2814,NULL,2813,2813),(2818,'user1 任务审批','user1',2808,'2020-06-27 07:42:40','2020-06-27 07:45:38',2814,NULL,2813,2813),(2821,'user2任务审批','user2',2808,'2020-06-27 07:45:38',NULL,2814,NULL,2813,2813),(2825,NULL,'start1',2808,'2020-06-27 07:47:18',NULL,2824,NULL,2823,2823),(2828,'user1 任务审批','user1',2808,'2020-06-27 07:47:18','2020-06-27 07:47:46',2824,NULL,2823,2823),(2831,'user2任务审批','user2',2808,'2020-06-27 07:47:46',NULL,2824,NULL,2823,2823),(2903,NULL,'start1',2808,'2020-06-27 08:43:52',NULL,2902,NULL,2901,2901),(2906,'user1 任务审批','user1',2808,'2020-06-27 08:43:52','2020-06-27 09:07:45',2902,NULL,2901,2901),(3002,'user2任务审批','user2',2808,'2020-06-27 09:07:45','2020-06-27 09:18:12',2902,NULL,2901,2901),(3019,NULL,'end',2808,'2020-06-27 09:18:12',NULL,2902,NULL,2901,2901),(3022,NULL,'start1',2808,'2020-06-27 09:28:16',NULL,3021,NULL,3020,3020),(3025,'user1 任务审批','user1',2808,'2020-06-27 09:28:17','2020-06-27 09:28:52',3021,NULL,3020,3020),(3028,'user2任务审批','user2',2808,'2020-06-27 09:28:52','2020-06-27 09:31:15',3021,NULL,3020,3020),(3031,NULL,'end',2808,'2020-06-27 09:31:15',NULL,3021,NULL,3020,3020),(3102,NULL,'user2',2501,'2020-07-26 15:44:40','2020-07-29 06:48:09',3116,NULL,3115,3115),(3106,NULL,'start',2501,'2020-07-26 15:51:57',NULL,3105,NULL,3104,3104),(3109,NULL,'user1',2501,'2020-07-26 15:51:57',NULL,3105,NULL,3104,3104),(3110,NULL,'start1',2808,'2020-07-20 12:24:40',NULL,3109,NULL,3108,3108),(3113,'user1 任务审批','user1',2808,'2020-07-20 12:24:40',NULL,3109,NULL,3108,3108),(3117,NULL,'start',2501,'2020-07-22 01:22:25',NULL,3116,NULL,3115,3115),(3120,NULL,'user1',2501,'2020-07-22 01:22:25','2020-07-26 15:44:40',3116,NULL,3115,3115),(3203,NULL,'start1',2808,'2020-07-29 06:35:30',NULL,3202,NULL,3201,3201),(3206,'user1 任务审批','user1',2808,'2020-07-29 06:35:30',NULL,3202,NULL,3201,3201),(3209,NULL,'end',2501,'2020-07-29 06:48:09',NULL,3116,NULL,3115,3115),(3303,NULL,'start',2501,'2020-08-01 05:57:25',NULL,3302,NULL,3301,3301),(3306,NULL,'user1',2501,'2020-08-01 05:57:25',NULL,3302,NULL,3301,3301),(3310,NULL,'start1',2808,'2020-08-01 08:12:30',NULL,3309,NULL,3308,3308),(3313,'user1 任务审批','user1',2808,'2020-08-01 08:12:30',NULL,3309,NULL,3308,3308),(3403,NULL,'start',2501,'2020-08-03 07:49:47',NULL,3402,NULL,3401,3401),(3406,NULL,'user1',2501,'2020-08-03 07:49:47',NULL,3402,NULL,3401,3401),(3503,NULL,'start',2501,'2020-08-04 09:49:31',NULL,3502,NULL,3501,3501),(3506,NULL,'user1',2501,'2020-08-04 09:49:31',NULL,3502,NULL,3501,3501),(3510,NULL,'start1',2808,'2020-08-05 05:57:39',NULL,3509,NULL,3508,3508),(3513,'user1 任务审批','user1',2808,'2020-08-05 05:57:39',NULL,3509,NULL,3508,3508);
/*!40000 ALTER TABLE `uflo_his_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_his_blob`
--

DROP TABLE IF EXISTS `uflo_his_blob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_his_blob` (
  `ID_` bigint(20) NOT NULL,
  `BLOB_VALUE_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_his_blob`
--

LOCK TABLES `uflo_his_blob` WRITE;
/*!40000 ALTER TABLE `uflo_his_blob` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_his_blob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_his_process_instance`
--

DROP TABLE IF EXISTS `uflo_his_process_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_his_process_instance` (
  `ID_` bigint(20) NOT NULL,
  `BUSINESS_ID_` varchar(60) DEFAULT NULL,
  `CREATE_DATE_` datetime DEFAULT NULL,
  `END_DATE_` datetime DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `PROMOTER_` varchar(60) DEFAULT NULL,
  `SUBJECT_` varchar(200) DEFAULT NULL,
  `TAG_` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_his_process_instance`
--

LOCK TABLES `uflo_his_process_instance` WRITE;
/*!40000 ALTER TABLE `uflo_his_process_instance` DISABLE KEYS */;
INSERT INTO `uflo_his_process_instance` VALUES (307,NULL,'2019-09-02 10:15:49','2019-09-02 10:18:25',304,306,'anonymous',NULL,NULL),(320,NULL,'2019-09-02 10:22:49','2019-09-02 10:31:18',302,319,'anonymous',NULL,NULL),(339,NULL,'2019-09-02 10:35:16','2019-09-02 10:35:52',336,338,'anonymous',NULL,NULL),(404,NULL,'2019-09-02 11:18:03',NULL,401,403,'anonymous',NULL,NULL),(504,'LY23B8-45A9-AC3C','2020-04-09 01:50:01','2020-04-09 01:51:52',501,503,'1151420235196588033','12',NULL),(518,NULL,'2020-04-09 05:15:50',NULL,501,517,'1151420235196588033',NULL,NULL),(525,NULL,'2020-04-09 05:15:57',NULL,501,524,'1151420235196588033',NULL,NULL),(602,'LYE8FF-4F40-83B0','2020-04-11 07:07:32',NULL,501,601,'1151420235196588033','12',NULL),(609,'LYD931-4996-9DE2','2020-04-11 07:09:42',NULL,501,608,'1151420235196588033','1',NULL),(616,'LY0E19-4E45-89DE','2020-04-11 07:11:36',NULL,501,615,'1151420235196588033','adfasdf',NULL),(702,'LY3CC7-4DC6-87CB','2020-04-14 01:45:13',NULL,501,701,'1151420235196588033','测试',NULL),(802,'JYD5B2-467C-A7F8','2020-04-16 11:38:06',NULL,501,801,'1151420235196588033','测试',NULL),(809,'JYEACB-4C13-BDDD','2020-04-16 11:41:15',NULL,501,808,'1151420235196588033','这次',NULL),(902,NULL,'2020-04-16 12:57:57',NULL,331,901,'1151420235196588033',NULL,NULL),(1002,'JY39CF-428D-BE62','2020-04-17 16:25:21',NULL,501,1001,'1151420235196588033','1',NULL),(1102,'LY3DE3-43BD-BDCF','2020-04-20 02:37:51',NULL,501,1101,'1151420235196588033','adf',NULL),(1109,'LYDB3B-4BFA-BD48','2020-04-20 02:43:20',NULL,501,1108,'1151420235196588033','12',NULL),(1116,'LY7021-47F8-872A','2020-04-20 03:05:13',NULL,501,1115,'1151420235196588033','12',NULL),(1202,'JY3EF9-4633-8FA4','2020-04-20 05:08:17',NULL,501,1201,'1151420235196588033','adf','zc'),(1302,NULL,'2020-04-21 05:28:16',NULL,501,1301,'1151420235196588033',NULL,NULL),(1402,NULL,'2020-04-22 15:21:58',NULL,302,1401,'1151420235196588033',NULL,NULL),(1409,'LY2A05-4D69-8DE7','2020-04-23 02:25:52',NULL,501,1408,'1248062045821173762','。。部门用电脑','zc'),(1502,NULL,'2020-04-26 06:19:58',NULL,331,1501,'1248062045821173762',NULL,NULL),(1602,'LY244A-4D61-B132','2020-04-26 09:08:18',NULL,501,1601,'1151420235196588033','12','zc'),(1703,NULL,'2020-04-27 07:26:49','2020-04-27 07:27:05',304,1702,'1151420235196588033',NULL,NULL),(1802,'LY4F5C-4925-B838','2020-04-30 02:09:55',NULL,501,1801,'1151420235196588033','12','zc'),(2002,'JY15BD-4924-A4F5','2020-05-15 15:04:45',NULL,501,2001,'1151420235196588033','阿斯顿发','zc'),(2102,'LY32DF-4F4F-B4FB','2020-05-19 10:02:34',NULL,501,2101,'1151420235196588033','111','zc'),(2202,'LYF476-4F17-8D4C','2020-05-26 03:46:23',NULL,501,2201,'1151420235196588033','asdf','zc'),(2403,'LY34A4-402A-9E06','2020-06-24 23:02:44',NULL,2301,2402,'1151420235196588033','12','zc'),(2404,'LY34A4-402A-9E06','2020-06-24 23:02:44',NULL,2301,2401,'1151420235196588033','12','zc'),(2602,'LY3370-4253-BACE','2020-06-26 08:19:57',NULL,2501,2601,'1151420235196588033','','zc'),(2612,'LY31B0-49D2-AD4E','2020-06-26 08:29:49',NULL,2501,2611,'1151420235196588033','11212','zc'),(2702,'LY87A5-496B-81FD','2020-06-26 08:44:59',NULL,2501,2701,'1151420235196588033','12','zc'),(2714,'LYB735-4110-B87D','2020-06-26 09:22:11','2020-06-26 09:26:49',2501,2713,'1151420235196588033','12','zc'),(2724,'LYF2D9-4B35-BD93','2020-06-26 09:32:31','2020-06-26 09:33:13',2501,2723,'1274968965654900737','12','zc'),(2802,'JY0112-4AE8-A190','2020-06-27 06:18:52',NULL,2501,2801,'1151420235196588033','','zc'),(2814,'LYD210-4FCE-8EFC','2020-06-27 07:42:40',NULL,2808,2813,'1151420235196588033','这是测试','zc'),(2824,'LYD1DE-4111-875C','2020-06-27 07:47:18',NULL,2808,2823,'1151420235196588033','12','zc'),(2902,'LY91E1-469D-BDC8','2020-06-27 08:43:52','2020-06-27 09:18:12',2808,2901,'1151420235196588033','这是测试流程3','zc'),(3021,'LY9C81-4621-8FBD','2020-06-27 09:28:16','2020-06-27 09:31:16',2808,3020,'1151420235196588033','12','zc'),(3105,'JY6447-4274-8F2E','2020-07-26 15:51:57',NULL,2501,3104,'1276368794897408001','','zc'),(3109,'LY48F2-4015-A1A7','2020-07-20 12:24:40',NULL,2808,3108,'1151420235196588033','','zc'),(3116,'JY2611-4A53-8ED5','2020-07-22 01:22:25','2020-07-29 06:48:10',2501,3115,'1151420235196588033','','zc'),(3202,'LY974E-42C8-BAE3','2020-07-29 06:35:30',NULL,2808,3201,'1276368794897408001','aa','zc'),(3302,'JYFF29-44F6-9D7E','2020-08-01 05:57:25',NULL,2501,3301,'1151420235196588033','','zc'),(3309,'LY305D-4FAD-A051','2020-08-01 08:12:30',NULL,2808,3308,'1151420235196588033','','zc'),(3402,'JY650C-433D-A9BA','2020-08-03 07:49:47',NULL,2501,3401,'1276368794897408001','','zc'),(3502,'JY3A05-44A7-896E','2020-08-04 09:49:31',NULL,2501,3501,'1151420235196588033','','zc'),(3509,'LY5A59-4F70-AA34','2020-08-05 05:57:39',NULL,2808,3508,'1151420235196588033','44','zc');
/*!40000 ALTER TABLE `uflo_his_process_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_his_task`
--

DROP TABLE IF EXISTS `uflo_his_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_his_task` (
  `ID_` bigint(20) NOT NULL,
  `DESCRIPTION_` varchar(120) DEFAULT NULL,
  `NODE_NAME_` varchar(60) DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  `ASSIGNEE_` varchar(60) DEFAULT NULL,
  `BUSINESS_ID_` varchar(60) DEFAULT NULL,
  `CREATE_DATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `END_DATE_` datetime DEFAULT NULL,
  `HIS_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `OPINION_` varchar(200) DEFAULT NULL,
  `OWNER_` varchar(60) DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `ROOT_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(20) DEFAULT NULL,
  `SUBJECT_` varchar(200) DEFAULT NULL,
  `TASK_ID_` bigint(20) DEFAULT NULL,
  `TASK_NAME_` varchar(60) DEFAULT NULL,
  `TYPE_` varchar(20) DEFAULT NULL,
  `URL_` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_his_task`
--

LOCK TABLES `uflo_his_task` WRITE;
/*!40000 ALTER TABLE `uflo_his_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_his_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_his_variable`
--

DROP TABLE IF EXISTS `uflo_his_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_his_variable` (
  `ID_` bigint(20) NOT NULL,
  `HIS_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `KEY_` varchar(60) DEFAULT NULL,
  `VALUE_` varchar(255) DEFAULT NULL,
  `TYPE_` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_his_variable`
--

LOCK TABLES `uflo_his_variable` WRITE;
/*!40000 ALTER TABLE `uflo_his_variable` DISABLE KEYS */;
INSERT INTO `uflo_his_variable` VALUES (314,307,'k','v','String'),(328,320,'v','同意','String'),(346,339,'title','标题','String');
/*!40000 ALTER TABLE `uflo_his_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_job_heartbeat`
--

DROP TABLE IF EXISTS `uflo_job_heartbeat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_job_heartbeat` (
  `ID_` varchar(60) NOT NULL,
  `DATE_` datetime DEFAULT NULL,
  `INSTANCE_NAME_` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_job_heartbeat`
--

LOCK TABLES `uflo_job_heartbeat` WRITE;
/*!40000 ALTER TABLE `uflo_job_heartbeat` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_job_heartbeat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_process`
--

DROP TABLE IF EXISTS `uflo_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_process` (
  `ID_` bigint(20) NOT NULL,
  `CATEGORY_` varchar(60) DEFAULT NULL,
  `CATEGORY_ID_` varchar(60) DEFAULT NULL,
  `CREATE_DATE_` datetime DEFAULT NULL,
  `DESCRIPTION_` varchar(255) DEFAULT NULL,
  `EFFECT_DATE_` datetime DEFAULT NULL,
  `KEY_` varchar(60) DEFAULT NULL,
  `NAME_` varchar(60) DEFAULT NULL,
  `START_PROCESS_URL_` varchar(120) DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_process`
--

LOCK TABLES `uflo_process` WRITE;
/*!40000 ALTER TABLE `uflo_process` DISABLE KEYS */;
INSERT INTO `uflo_process` VALUES (2501,NULL,NULL,'2020-06-26 04:32:40',NULL,NULL,'zcly-1','zcly',NULL,1),(2808,NULL,NULL,'2020-06-27 07:33:27',NULL,NULL,'zcly-2','zcly',NULL,2);
/*!40000 ALTER TABLE `uflo_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_process_instance`
--

DROP TABLE IF EXISTS `uflo_process_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_process_instance` (
  `ID_` bigint(20) NOT NULL,
  `BUSINESS_ID_` varchar(60) DEFAULT NULL,
  `CREATE_DATE_` datetime DEFAULT NULL,
  `CURRENT_NODE_` varchar(60) DEFAULT NULL,
  `CURRENT_TASK_` varchar(60) DEFAULT NULL,
  `HIS_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `PARALLEL_INSTANCE_COUNT_` int(11) DEFAULT NULL,
  `PARENT_ID_` bigint(20) DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  `PROMOTER_` varchar(60) DEFAULT NULL,
  `ROOT_ID_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(20) DEFAULT NULL,
  `SUBJECT_` varchar(200) DEFAULT NULL,
  `TAG_` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_process_instance`
--

LOCK TABLES `uflo_process_instance` WRITE;
/*!40000 ALTER TABLE `uflo_process_instance` DISABLE KEYS */;
INSERT INTO `uflo_process_instance` VALUES (2601,'LY3370-4253-BACE','2020-06-26 08:19:57','user2','user2',2602,0,0,2501,'1151420235196588033',2601,'Start','','zc'),(2611,'LY31B0-49D2-AD4E','2020-06-26 08:29:49','user1','user1',2612,0,0,2501,'1151420235196588033',2611,'Start','11212','zc'),(2701,'LY87A5-496B-81FD','2020-06-26 08:44:59','start','start',2702,0,0,2501,'1151420235196588033',2701,'Start','12','zc'),(2801,'JY0112-4AE8-A190','2020-06-27 06:18:52','user2','user2',2802,0,0,2501,'1151420235196588033',2801,'Start','','zc'),(2813,'LYD210-4FCE-8EFC','2020-06-27 07:42:40','user2','user2',2814,0,0,2808,'1151420235196588033',2813,'Start','这是测试','zc'),(2823,'LYD1DE-4111-875C','2020-06-27 07:47:18','user2','user2',2824,0,0,2808,'1151420235196588033',2823,'Start','12','zc'),(3104,'JY6447-4274-8F2E','2020-07-26 15:51:57','user1','user1',3105,0,0,2501,'1276368794897408001',3104,'Start','','zc'),(3108,'LY48F2-4015-A1A7','2020-07-20 12:24:40','user1','user1',3109,0,0,2808,'1151420235196588033',3108,'Start','','zc'),(3201,'LY974E-42C8-BAE3','2020-07-29 06:35:30','user1','user1',3202,0,0,2808,'1276368794897408001',3201,'Start','aa','zc'),(3301,'JYFF29-44F6-9D7E','2020-08-01 05:57:25','user1','user1',3302,0,0,2501,'1151420235196588033',3301,'Start','','zc'),(3308,'LY305D-4FAD-A051','2020-08-01 08:12:30','user1','user1',3309,0,0,2808,'1151420235196588033',3308,'Start','','zc'),(3401,'JY650C-433D-A9BA','2020-08-03 07:49:47','user1','user1',3402,0,0,2501,'1276368794897408001',3401,'Start','','zc'),(3501,'JY3A05-44A7-896E','2020-08-04 09:49:31','user1','user1',3502,0,0,2501,'1151420235196588033',3501,'Start','','zc'),(3508,'LY5A59-4F70-AA34','2020-08-05 05:57:39','user1','user1',3509,0,0,2808,'1151420235196588033',3508,'Start','44','zc');
/*!40000 ALTER TABLE `uflo_process_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_task`
--

DROP TABLE IF EXISTS `uflo_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_task` (
  `ID_` bigint(20) NOT NULL,
  `DESCRIPTION_` varchar(120) DEFAULT NULL,
  `NODE_NAME_` varchar(60) DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  `ASSIGNEE_` varchar(60) DEFAULT NULL,
  `BUSINESS_ID_` varchar(60) DEFAULT NULL,
  `COUNTERSIGN_COUNT_` int(11) DEFAULT NULL,
  `CREATE_DATE_` datetime DEFAULT NULL,
  `DATE_UNIT_` varchar(20) DEFAULT NULL,
  `DUE_ACTION_DATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `END_DATE_` datetime DEFAULT NULL,
  `OPINION_` varchar(200) DEFAULT NULL,
  `OWNER_` varchar(60) DEFAULT NULL,
  `PREV_STATE_` varchar(20) DEFAULT NULL,
  `PREV_TASK_` varchar(60) DEFAULT NULL,
  `PRIORITY_` varchar(20) DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `PROGRESS_` int(11) DEFAULT NULL,
  `ROOT_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(20) DEFAULT NULL,
  `SUBJECT_` varchar(200) DEFAULT NULL,
  `TASK_NAME_` varchar(60) DEFAULT NULL,
  `TYPE_` varchar(20) DEFAULT NULL,
  `URL_` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_task`
--

LOCK TABLES `uflo_task` WRITE;
/*!40000 ALTER TABLE `uflo_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_task_appointor`
--

DROP TABLE IF EXISTS `uflo_task_appointor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_task_appointor` (
  `ID_` bigint(20) NOT NULL,
  `APPOINTOR_` varchar(60) DEFAULT NULL,
  `APPOINTOR_NODE_` varchar(60) DEFAULT NULL,
  `OWNER_` varchar(60) DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `TASK_NODE_NAME_` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_task_appointor`
--

LOCK TABLES `uflo_task_appointor` WRITE;
/*!40000 ALTER TABLE `uflo_task_appointor` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_task_appointor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_task_participator`
--

DROP TABLE IF EXISTS `uflo_task_participator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_task_participator` (
  `ID_` bigint(20) NOT NULL,
  `TASK_ID_` bigint(20) NOT NULL,
  `USER_` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `FKpqe63u3gnbwpjhvf8996md6ip` (`TASK_ID_`),
  CONSTRAINT `FKpqe63u3gnbwpjhvf8996md6ip` FOREIGN KEY (`TASK_ID_`) REFERENCES `uflo_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_task_participator`
--

LOCK TABLES `uflo_task_participator` WRITE;
/*!40000 ALTER TABLE `uflo_task_participator` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_task_participator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_task_reminder`
--

DROP TABLE IF EXISTS `uflo_task_reminder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_task_reminder` (
  `ID_` bigint(20) NOT NULL,
  `CRON_` varchar(60) DEFAULT NULL,
  `PROCESS_ID_` bigint(20) DEFAULT NULL,
  `REMINDER_HANDLER_BEAN_` varchar(120) DEFAULT NULL,
  `START_DATE_` datetime DEFAULT NULL,
  `TASK_ID_` bigint(20) DEFAULT NULL,
  `TASK_NODE_NAME_` varchar(60) DEFAULT NULL,
  `REMINDER_TYPE_` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_task_reminder`
--

LOCK TABLES `uflo_task_reminder` WRITE;
/*!40000 ALTER TABLE `uflo_task_reminder` DISABLE KEYS */;
/*!40000 ALTER TABLE `uflo_task_reminder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uflo_variable`
--

DROP TABLE IF EXISTS `uflo_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uflo_variable` (
  `TYPE_` varchar(30) NOT NULL,
  `ID_` bigint(20) NOT NULL,
  `KEY_` varchar(60) DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `ROOT_PROCESS_INSTANCE_ID_` bigint(20) DEFAULT NULL,
  `BLOB_ID_` bigint(20) DEFAULT NULL,
  `BOOLEAN_VALUE_` bit(1) DEFAULT NULL,
  `BYTE_VALUE_` tinyint(4) DEFAULT NULL,
  `CHARACTER_VALUE_` char(1) DEFAULT NULL,
  `DATE_VALUE_` datetime DEFAULT NULL,
  `DOUBLE_VALUE_` double DEFAULT NULL,
  `FLOAT_VALUE_` float DEFAULT NULL,
  `INTEGER_VALUE_` int(11) DEFAULT NULL,
  `LONG_VALUE_` bigint(20) DEFAULT NULL,
  `SHORT_VALUE_` smallint(6) DEFAULT NULL,
  `STRING_VALUE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uflo_variable`
--

LOCK TABLES `uflo_variable` WRITE;
/*!40000 ALTER TABLE `uflo_variable` DISABLE KEYS */;
INSERT INTO `uflo_variable` VALUES ('String',334,'status',319,319,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'通过');
/*!40000 ALTER TABLE `uflo_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_apps`
--

DROP TABLE IF EXISTS `wx_apps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_apps` (
  `id` varchar(50) DEFAULT NULL,
  `name` text,
  `app_id` varchar(50) DEFAULT NULL,
  `dr` decimal(10,0) DEFAULT NULL,
  `mark` text,
  `menu` text,
  `secret` text,
  `cdate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_apps`
--

LOCK TABLES `wx_apps` WRITE;
/*!40000 ALTER TABLE `wx_apps` DISABLE KEYS */;
INSERT INTO `wx_apps` VALUES ('e415775e7b684b389dac3282edea6ae6','wx_app','wx8fc3340c90ec5d53',0,NULL,'{\n	\"button\": [{\n		\"name\": \"品牌\",\n		\"sub_button\": [{\n			\"name\": \"授权模式模拟登录\",\n			\"sub_button\": [],\n			\"type\": \"view\",\n			\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fc3340c90ec5d53&redirect_uri=http://lank.free.ngrok.cc/mayormp/api/toLtPage.do &response_type=code&scope=snsapi_base&state=12#wechat_redirect\"\n		}, {\n			\"name\": \"直接跳转\",\n			\"sub_button\": [],\n			\"type\": \"view\",\n			\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2efe61461d02a5a&redirect_uri=http://mayorwx.youngor.com/mayormp/api/toLtPage.do&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"\n		}]\n	}, {\n		\"name\": \"产品\",\n		\"sub_button\": [{\n			\"name\": \"shop\",\n			\"sub_button\": [],\n			\"type\": \"view\",\n			\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fc3340c90ec5d53&redirect_uri=http://shop.happygroup2015.com/shop/api/wx/webOauth2.do&response_type=code&scope=snsapi_base&state=18#wechat_redirect\"\n		}]\n	}, {\n		\"name\": \"我的\",\n		\"sub_button\": [{\n			\"name\": \"计划\",\n			\"sub_button\": [],\n			\"type\": \"view\",\n			\"url\": \"http://dw.happygroup2015.com/dt/console/views/me/dk.html\"\n		}]\n	}]\n}','secret',NULL);
/*!40000 ALTER TABLE `wx_apps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_msg_def`
--

DROP TABLE IF EXISTS `wx_msg_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_msg_def` (
  `id` varchar(50) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `msgtype` varchar(100) DEFAULT NULL,
  `value` text,
  `dr` decimal(10,0) DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `group_id` varchar(50) DEFAULT NULL,
  `funtype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_msg_def`
--

LOCK TABLES `wx_msg_def` WRITE;
/*!40000 ALTER TABLE `wx_msg_def` DISABLE KEYS */;
INSERT INTO `wx_msg_def` VALUES ('6dd9772a-9091-47b1-8b30-d8a4aaf2650f','1','1212fk','text',NULL,1,NULL,'8433fd64-74ee-44d7-aa28-7f67b8293217','action'),('afebe9b7-4d59-41ed-b8b9-67f84d1f34fb','1','12','6',NULL,0,NULL,'e558f09e-019f-447e-9e87-73c5ad10025a','push'),('ca37d473-9aca-42a0-9038-c0408ff4746f','1212','12','text',NULL,0,NULL,'454e8669-dee6-4337-9425-82abc6f32724','action'),('cc3713ac-71c6-4529-9927-35716e2039f4','12','fk','text','12',0,'123','38cd559c-3c53-4336-97b2-c0cb1036ffbc','action'),('DZJT','DZJT','DZJT121212121212','text','你好1',0,'11212','22','push');
/*!40000 ALTER TABLE `wx_msg_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_msg_imgitem`
--

DROP TABLE IF EXISTS `wx_msg_imgitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_msg_imgitem` (
  `id` varchar(50) NOT NULL,
  `title` text,
  `msgdesc` text,
  `docurl` text,
  `imgurl` text,
  `group_id` varchar(50) DEFAULT NULL,
  `dr` decimal(10,0) DEFAULT NULL,
  `mark` text,
  `rn` decimal(4,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_msg_imgitem`
--

LOCK TABLES `wx_msg_imgitem` WRITE;
/*!40000 ALTER TABLE `wx_msg_imgitem` DISABLE KEYS */;
INSERT INTO `wx_msg_imgitem` VALUES ('1','标题','描述1','http://www.baidu.com','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525684745221&di=77683e4d1d8f4b4353a24f7f96b200c1&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F01%2F48%2F07a58PICF5r.jpg','22',1,NULL,1),('1067240f-9ca2-441b-b6c3-df2012819259','12','121212',NULL,NULL,'8433fd64-74ee-44d7-aa28-7f67b8293217',0,'12',NULL),('14480efd-4821-46da-ac06-ae0e5cc38999',NULL,NULL,NULL,NULL,'22',0,NULL,12),('2','2','·1','·1','·1','22',1,'2',21),('28154669-e17a-41cf-84e1-d58ace8f412b','1','123测试','1','1212','8433fd64-74ee-44d7-aa28-7f67b8293217',0,'1',12),('3','标题C11211111','描述13','http://www.qq.com','026442A1734F2BF475CFFDFBE08826B4','22',0,NULL,3),('4ba94a2f-95a6-459f-8b60-52bf67e9b917','121','1212',NULL,NULL,'8433fd64-74ee-44d7-aa28-7f67b8293217',0,'12',NULL),('67440e70-2f6a-4679-9d08-39b8e79aae88','12','12','121212','42EB389DC83F4ED7C82AF09A1607B8B2','22',1,'12',1),('6f71a832-6595-47e2-9f6f-a9a3182ff584','123',NULL,NULL,NULL,'22',0,'123',12),('75a7ee62-6576-4d25-aab2-b6b6e8811712','`',NULL,NULL,NULL,NULL,0,'`',NULL),('9ba4dbde-bbb7-4385-be84-c1eb2d548140','1','1','1','076443E88797E50CC3307EB206EFCBBB','e558f09e-019f-447e-9e87-73c5ad10025a',0,'1',12),('e669caf6-0502-43a9-8d55-467f5a862650','121','1212','121212','12','8433fd64-74ee-44d7-aa28-7f67b8293217',0,'12',NULL);
/*!40000 ALTER TABLE `wx_msg_imgitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_msg_sc`
--

DROP TABLE IF EXISTS `wx_msg_sc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_msg_sc` (
  `id` varchar(50) NOT NULL,
  `pic_id` varchar(50) DEFAULT NULL,
  `sctype` varchar(50) DEFAULT NULL,
  `dr` decimal(10,0) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_msg_sc`
--

LOCK TABLES `wx_msg_sc` WRITE;
/*!40000 ALTER TABLE `wx_msg_sc` DISABLE KEYS */;
INSERT INTO `wx_msg_sc` VALUES ('00700983-ded6-46c6-b2cb-a69127e76231','76BDFF82A5ADE74CFB9BB0312872AC2D','image',0,'2019-09-02 23:27:52'),('199629ba-f0a1-4395-b696-4ad6f25d1fb5','4D3DBC5DE5DF903ED57ED00EA69252F9','image',0,'2019-09-02 22:35:13'),('1aa78abd-bcf3-4c6d-a932-b2e122e4cdbc','C8E875C73709E92837F52AB08846C630','image',0,'2019-09-02 20:56:36'),('244433d4-0264-4dff-8a11-7035363ca8a9','32D2FF1F778CDF3E76FF7040C18E72AD','image',0,'2019-09-02 22:59:23'),('28874213-6726-440c-b2b5-4aee36f0b42b','A1AF97A9F04DD9D65CEA7446FED4B195','image',0,NULL),('2c1daf2b-27cd-4619-83b7-ba8c0fb1b68f','6F8609E0F6675587C28425E3ABC8A98F','image',0,'2019-09-02 22:44:34'),('2d34f765-2bc3-48c1-bbc6-390909895e6f','351C5B76D5538541AC031FD89202C150','image',0,'2019-09-02 21:24:01'),('2f324d63-93ca-4310-bc6e-eaa6ad4e36d5','6A4B682566EF45F6E7395DFC3229F83C','image',0,'2019-09-02 20:56:22'),('34705db2-4147-40f4-8a99-983e2769a337','7EE524A07AC6E2648DF7BDD6B8E5DA0A','image',0,'2019-09-02 21:41:36'),('3b96b671-e5e8-41cf-9bb3-c99125285288','2C1DFC11A2F820173E75C1CEDEEADBBF','image',0,'2019-09-02 23:25:11'),('3ce96052-5bb4-4be0-8715-158a0142ac1d','42EB389DC83F4ED7C82AF09A1607B8B2','image',0,'2018-05-08 12:49:27'),('40214572-d8ef-4a50-8f1c-53a8c93ce481','98E22912950177EE2173101C64CFD200','image',0,'2019-09-02 23:16:01'),('4182b6cc-ddc4-4ac8-bb40-3aa811910419','8ABDBC1641DC731068CD76DA4F1ECF45','image',0,'2019-09-04 12:56:36'),('5b64039e-de94-4449-b324-426fce1b9d88','076443E88797E50CC3307EB206EFCBBB','image',0,'2019-09-02 20:38:14'),('694d1511-e136-4fc3-bb19-02c5039722b7','8759719B4F1A23EF38D13F45456CF311','image',0,'2019-09-02 23:02:54'),('6e5da20e-ba1e-4dda-99f3-1fcfba72ab45','6BAD72B3699E63EC882C0D4AF3FE6DEF','image',0,'2019-09-02 21:38:22'),('7fe1d04c-126a-4770-81a3-23c7fd6a779a','F7016B170601A866CAD6154FC84F9EBD','image',0,'2019-09-02 21:27:55'),('8813d965-12d7-4688-99c1-fdc6cd0cfec1','672529D276ED5F2151C56A17178F86EF','image',0,'2019-09-02 23:10:44'),('8c67143f-0de0-41a0-a168-2233628d12a2','7A8E8C841170B0C9495403CA725FD304','image',0,'2019-09-02 21:46:20'),('8ca49621-eda6-4f6c-8376-96a43b0adadf','E4F6AFECEA88589823F3EDE546AF4B33','image',0,'2019-09-02 22:54:20'),('90ac2f38-6607-4944-a5a9-2ce42b0202fa','2CA9233EFC21CFBBFA985ADD85A217A9','image',0,'2019-09-02 22:38:15'),('9483083d-cb2a-40be-a52e-221c6879537f','0BD9506CB8D2249D600E9B41C11EA38A','image',0,'2019-09-02 23:02:36'),('9d3d79b7-3109-4459-84c9-0ddbdfb8a06d','F2D6E8AFCD964341FD148F8DF9DC4976','image',0,'2019-09-04 18:30:15'),('a0fc405d-d6e7-4975-a9e1-515a96c48a89','4AFAA2540CBCE9879D13D4804F0091D0','image',0,'2019-09-02 22:18:43'),('b2450f61-2161-4cc7-b32f-b892e91a52ff','4B20214658E33D8A76B528673054C043','image',0,'2019-09-02 21:35:05'),('b2630ae1-c88b-4489-a280-c0c547b37580','1E84C93FE09B1332A423EB3612C19887','image',0,'2019-09-04 13:33:12'),('b6507d1d-3381-45c3-b69f-f532d92188ff','9F2FAFC4AEC8FBE1639FB3D8D396E9D8','image',0,'2019-09-02 22:57:11'),('be1936dc-1caa-42f1-a142-43f74b796c99','D24E79029FB71A12DB337367EEF460F3','image',0,'2019-09-02 22:35:15'),('c1588d96-d3bf-48fb-9a0f-b1fc60a2a31e','AA8973604BFE13976DEE26F506BB900B','image',0,'2018-05-08 13:27:35'),('c578402e-4752-442f-9d5a-fcc82faa9897','BF2AABE23FD623D972554588099D8DE6','image',0,NULL),('c5cb42af-0408-4014-9f36-b73507091996','8A2F8C4FFE71197C0EBDFF02ED05AB3C','image',0,'2019-09-02 23:13:23'),('ceeff505-eac7-4543-8496-c38ae1706c3f','86E750CE03167DAD957D1C9634A09353','image',0,'2019-09-04 12:52:58'),('d7ff5f90-763d-494f-abc6-fec5e1510b65','026442A1734F2BF475CFFDFBE08826B4','image',0,'2018-05-09 06:55:53'),('de9daa99-e0a2-49e5-b5fd-c6dc6eca749e','2EEFE01DF67048BB556015C84DCDE048','image',0,'2019-09-02 22:57:51'),('e32b8470-db48-4eec-884f-d76dc9c321b4','F2BB525E63D49C3C4C81ACDC189106CC','image',0,'2019-09-02 22:40:47'),('e5a13333-c2a7-4947-8d3b-ee77b9024825','42AB940FA23F4068CF1E2EB821DB690E','image',0,'2019-09-02 23:43:01'),('e65fe259-c907-41be-99a6-6b884294a646','DDDCF9823A2E482828A15E30975F09CD','image',0,'2019-09-02 22:54:56'),('e87f8cde-f4f2-4bee-b698-94da51827911','D720C02E8986BF6E68F89D8F7F5442ED','image',0,'2019-09-02 21:02:14'),('f24a89cc-11e7-48a8-ab3f-9f23aa70df66','1673BDEE7B75012E1A6E14B4A4FD09E9','image',0,'2019-09-02 23:10:01'),('f71df4a9-86ac-4857-9fac-bf9bc0eee8cb','F83C0A08293A4D35F4682C9BB56E7E05','image',0,'2019-09-02 20:38:41'),('f8c3ba05-ce50-46aa-b8e3-467c2b608c3f','2BA7DDE7309D53A1BF122F45DC7EA497','image',0,'2019-09-02 21:07:03');
/*!40000 ALTER TABLE `wx_msg_sc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_web_auth`
--

DROP TABLE IF EXISTS `wx_web_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_web_auth` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `dr` decimal(10,0) DEFAULT NULL,
  `login` decimal(10,0) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wx_web_auth_state_uindex` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_web_auth`
--

LOCK TABLES `wx_web_auth` WRITE;
/*!40000 ALTER TABLE `wx_web_auth` DISABLE KEYS */;
INSERT INTO `wx_web_auth` VALUES ('0c90c189-6b1e-4573-b436-21bc81cfd94c','xjj','sdaf',1,0,'adfa'),('2a3ce9c9-902e-4592-8203-3a85e03a4a6b','1212','1212',1,1,'1212'),('ad42d4bc-ce2c-489d-8a0c-0d1b650bda58','shop','/shop/wx/loc3.html',0,1,'10'),('fd741f5e-88f5-44e3-91c2-5dc3aea20af1','测试','/dt/wx/loc3.html',0,0,'12');
/*!40000 ALTER TABLE `wx_web_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zb_group_mapper`
--

DROP TABLE IF EXISTS `zb_group_mapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zb_group_mapper` (
  `id` varchar(50) NOT NULL,
  `name` text,
  `value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zb_group_mapper`
--

LOCK TABLES `zb_group_mapper` WRITE;
/*!40000 ALTER TABLE `zb_group_mapper` DISABLE KEYS */;
INSERT INTO `zb_group_mapper` VALUES ('1','主机','10'),('2','应用','12'),('3','数据库','13'),('4','中间件','14');
/*!40000 ALTER TABLE `zb_group_mapper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zbx_object_group`
--

DROP TABLE IF EXISTS `zbx_object_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zbx_object_group` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `mark` varchar(500) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `sort` decimal(5,0) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zbx_object_group`
--

LOCK TABLES `zbx_object_group` WRITE;
/*!40000 ALTER TABLE `zbx_object_group` DISABLE KEYS */;
INSERT INTO `zbx_object_group` VALUES ('1289549704565571586','操作系统',NULL,'os',NULL,NULL,'0','2020-08-01 13:13:03','1151420235196588033','2020-08-01 13:17:52','1151420235196588033'),('1289549988427677697','数据库',NULL,'database',NULL,NULL,'0','2020-08-01 13:14:11','1151420235196588033','2020-08-01 13:14:11','1151420235196588033'),('1289550170695352321','中间件',NULL,'middleware',NULL,NULL,'0','2020-08-01 13:14:54','1151420235196588033','2020-08-01 13:14:54','1151420235196588033'),('1289550976228212738','网络设备',NULL,'network',NULL,NULL,'0','2020-08-01 13:18:06','1151420235196588033','2020-08-01 13:18:06','1151420235196588033'),('1289551294932402178','防火墙',NULL,'firewall',NULL,NULL,'0','2020-08-01 13:19:22','1151420235196588033','2020-08-01 13:19:22','1151420235196588033'),('1289551334610518017','WEB',NULL,'web',NULL,NULL,'0','2020-08-01 13:19:32','1151420235196588033','2020-08-01 13:19:32','1151420235196588033'),('1289551768926502913','虚拟化',NULL,'virtualization',NULL,NULL,'0','2020-08-01 13:21:15','1151420235196588033','2020-08-01 13:21:15','1151420235196588033'),('1289551907229483010','测试',NULL,'12',NULL,NULL,'1','2020-08-01 13:21:48','1151420235196588033','2020-08-01 13:21:48','1151420235196588033'),('1289675477431566338',NULL,NULL,NULL,NULL,NULL,'1','2020-08-01 21:32:49','1151420235196588033','2020-08-01 21:32:49','1151420235196588033');
/*!40000 ALTER TABLE `zbx_object_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zbx_object_item`
--

DROP TABLE IF EXISTS `zbx_object_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zbx_object_item` (
  `id` varchar(50) NOT NULL,
  `groupid` varchar(50) DEFAULT NULL,
  `objid` varchar(50) DEFAULT NULL,
  `objname` varchar(100) DEFAULT NULL,
  `dr` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zbx_object_item`
--

LOCK TABLES `zbx_object_item` WRITE;
/*!40000 ALTER TABLE `zbx_object_item` DISABLE KEYS */;
INSERT INTO `zbx_object_item` VALUES ('1289720985934655490','1289549704565571586','7','Hypervisors','0','2020-08-02 00:33:40','1151420235196588033','2020-08-02 00:33:40','1151420235196588033'),('1289720985984987138','1289549704565571586','2','Linux servers','0','2020-08-02 00:33:40','1151420235196588033','2020-08-02 00:33:40','1151420235196588033'),('1289735887755702274','1289549704565571586','5','Discovered hosts','0','2020-08-02 01:32:52','1151420235196588033','2020-08-02 01:32:52','1151420235196588033');
/*!40000 ALTER TABLE `zbx_object_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-06 23:18:14
