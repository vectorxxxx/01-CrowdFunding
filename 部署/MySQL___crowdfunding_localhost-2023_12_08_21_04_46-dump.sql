-- MySQL dump 10.13  Distrib 5.7.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: crowdfunding
-- ------------------------------------------------------
-- Server version	5.7.33-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='257a497e-3211-11ee-a7c9-6c240813fb00:1-41993';

--
-- Table structure for table `inner_admin_role`
--

DROP TABLE IF EXISTS `inner_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inner_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员-角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inner_admin_role`
--

LOCK TABLES `inner_admin_role` WRITE;
/*!40000 ALTER TABLE `inner_admin_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `inner_admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inner_role_auth`
--

DROP TABLE IF EXISTS `inner_role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inner_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-授权表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inner_role_auth`
--

LOCK TABLES `inner_role_auth` WRITE;
/*!40000 ALTER TABLE `inner_role_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `inner_role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_address`
--

DROP TABLE IF EXISTS `t_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址信息主键',
  `consignee_name` varchar(50) DEFAULT NULL COMMENT '收货人',
  `phone_num` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_address`
--

LOCK TABLES `t_address` WRITE;
/*!40000 ALTER TABLE `t_address` DISABLE KEYS */;
INSERT INTO `t_address` VALUES (4,'Nellie Herzog','16758832705','New Ryan 515 Trudie Green',4),(5,'Jan Hessel','15320475198','Kohlerfort 511 Karina Way',4);
/*!40000 ALTER TABLE `t_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_acct` varchar(255) NOT NULL COMMENT '登录账号',
  `user_pswd` char(100) CHARACTER SET utf8 NOT NULL,
  `user_name` varchar(255) NOT NULL COMMENT '昵称',
  `email` varchar(255) NOT NULL COMMENT '邮件地址',
  `create_time` char(19) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_acct` (`login_acct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth`
--

DROP TABLE IF EXISTS `t_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '授权模块',
  `title` varchar(200) DEFAULT NULL COMMENT '授权抬头',
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='授权表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth`
--

LOCK TABLES `t_auth` WRITE;
/*!40000 ALTER TABLE `t_auth` DISABLE KEYS */;
INSERT INTO `t_auth` VALUES (1,'','用户模块',NULL),(2,'user:delete','删除',1),(3,'user:get','查询',1),(4,'','角色模块',NULL),(5,'role:delete','删除',4),(6,'role:get','查询',4),(7,'role:add','新增',4);
/*!40000 ALTER TABLE `t_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member`
--

DROP TABLE IF EXISTS `t_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) NOT NULL,
  `userpswd` char(200) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `authstatus` int(4) DEFAULT NULL COMMENT '实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证',
  `usertype` int(4) DEFAULT NULL COMMENT ' 0 - 个人， 1 - 企业',
  `realname` varchar(255) DEFAULT NULL,
  `cardnum` varchar(255) DEFAULT NULL,
  `accttype` int(4) DEFAULT NULL COMMENT '0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_member_loginacct_uindex` (`loginacct`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member`
--

LOCK TABLES `t_member` WRITE;
/*!40000 ALTER TABLE `t_member` DISABLE KEYS */;
INSERT INTO `t_member` VALUES (4,'test','$2a$10$2wPoTXs.dSH6nMV4Yo9DcOSChYwdoPcPsLvGm0KmeWcQ8CWvKON06','Test','test@test.com',NULL,NULL,NULL,NULL,NULL),(6,'test2','$2a$10$DOoEEa4RonFOkdSMqn7X6ewiwKf35TxZzJm0rT3SyrmWcVdI7Bc3m','Test2','test@test.com',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member_confirm_info`
--

DROP TABLE IF EXISTS `t_member_confirm_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member_confirm_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL COMMENT '会员 id',
  `paynum` varchar(200) DEFAULT NULL COMMENT '易付宝企业账号',
  `cardnum` varchar(200) DEFAULT NULL COMMENT '法人身份证号',
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='发起人确认信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member_confirm_info`
--

LOCK TABLES `t_member_confirm_info` WRITE;
/*!40000 ALTER TABLE `t_member_confirm_info` DISABLE KEYS */;
INSERT INTO `t_member_confirm_info` VALUES (11,4,'U6679479R5LMGW3M43','237813200703247264',12),(12,4,'406405028840122','533124200703252951',13),(13,4,'2Q039102CDT1YACYK8','970705201012233527',14),(14,4,'9A0084019RR316CTJ1','816594199503099280',15),(15,4,'447787935773920','333530200404167003',16),(16,4,'943066894063848','694834200211255237',17),(17,4,'190830599176236','239013201804229840',20),(18,4,'850848493074873','810519201802105884',21);
/*!40000 ALTER TABLE `t_member_confirm_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member_launch_info`
--

DROP TABLE IF EXISTS `t_member_launch_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member_launch_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL COMMENT '会员 id',
  `description_simple` varchar(255) DEFAULT NULL COMMENT '简单介绍',
  `description_detail` longtext COMMENT '详细介绍',
  `phone_num` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `service_num` varchar(255) DEFAULT NULL COMMENT '客服电话',
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='项目发起人信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member_launch_info`
--

LOCK TABLES `t_member_launch_info` WRITE;
/*!40000 ALTER TABLE `t_member_launch_info` DISABLE KEYS */;
INSERT INTO `t_member_launch_info` VALUES (11,4,'自我介绍111111','详细自我介绍111111Eligendi rerum et eos occaecati voluptatem non sapiente fugiat. Nam exercitationem ad eligendi commodi veritatis sit atque quia. Tempora corporis quisquam ut nihil amet aut repellendus voluptatem in. Provident aut sit voluptate est illum ex suscipit inventore. Aut voluptatem voluptas laboriosam. Impedit eius adipisci voluptas et. Nisi repellat doloribus eum voluptatem voluptatum unde fugiat ipsum. Delectus sed ex itaque. Pariatur recusandae tempora est ea dolor. Aut ullam laudantium alias laborum consequatur. Reiciendis nihil nisi sit.','18888888888','666666',12),(12,4,'自我介绍111111','详细自我介绍111111Corporis optio modi. Occaecati omnis sint amet delectus quo. Eaque quia minima eius architecto nihil vero. Autem maxime odio libero accusamus dolores molestias sed dolor. Quidem repellendus nesciunt debitis rerum esse asperiores voluptas et sit. Deserunt corporis et aliquid ab. Officia eveniet hic quae consectetur qui voluptatem. Ad amet vero suscipit vel quis reiciendis rerum. Ex reiciendis aut architecto voluptatem rerum rerum voluptas. Unde aperiam et.','18888888888','666666',13),(13,4,'Jeff Herman','Reprehenderit quidem et eligendi eum nihil. Deleniti suscipit voluptas consequuntur aut in deleniti distinctio omnis. Nemo enim dolor animi rerum consequatur quisquam. Qui repudiandae reprehenderit ullam molestiae reiciendis commodi doloribus. Ea ut quasi error esse sapiente numquam et. Sint esse quos natus. Sapiente repellendus nam earum illo distinctio maiores quas. Quam consequuntur dolor maiores ratione quas non illo mollitia. Optio nostrum quia assumenda aliquam. Assumenda et ut in suscipit pariatur debitis dolore.','16730585605','15266005271',14),(14,4,'Bernice Keebler','Sint laboriosam voluptas occaecati omnis ut ipsam voluptas eius. Reprehenderit autem architecto earum harum quis consectetur. Tempora fugiat reprehenderit ut ipsum qui molestias. Consequatur ratione consequatur voluptates. Porro ex consequatur dolores et totam non. Dolorem quis reprehenderit voluptas mollitia sunt adipisci. Totam aut beatae eum qui maxime alias. Ea molestias voluptas earum. Consequatur ullam rem sunt est nulla. Facere dolores est qui est vero atque accusamus. Perferendis esse et nihil placeat tenetur possimus. Qui dolores rerum autem.','17180609767','13171535091',15),(15,4,'Marcos Steuber','Quibusdam et enim. Ad mollitia voluptas nam. Dolores tempora consequatur enim quaerat. Praesentium perspiciatis aut ab omnis velit aut distinctio. Quo ea qui maxime voluptas quod est sint iste aspernatur. Enim ipsam quis vero excepturi. Nobis laborum aut consectetur similique libero sequi vel enim laborum. Blanditiis molestiae mollitia perferendis nobis consequuntur. Reprehenderit quos quis laudantium illum et. Sunt asperiores architecto iure deleniti laborum quibusdam dolore. Eveniet sequi vel ad temporibus excepturi et. Velit consequuntur possimus assumenda molestias debitis inventore.','18094503626','666666',16),(16,4,'Milton Crooks','Nesciunt atque rerum saepe qui delectus rem commodi. Est cumque ut. Reprehenderit nesciunt ipsam tempore quisquam recusandae facilis. Cum quibusdam est ut fugiat aliquam quia. Sit sunt fugit sunt maiores eum quia. Vel vel nam accusantium hic itaque dolorem voluptas. Ad eum aperiam culpa eum voluptatem cumque hic error. Qui sed sapiente cumque. Enim dignissimos voluptas nesciunt culpa itaque doloribus. Fuga dolorem veritatis laudantium ut voluptas accusantium sed.','15696633876','666666',17),(17,4,'Preston Bins','Pariatur saepe ipsum possimus aut accusantium reprehenderit. Sit eveniet qui eum eos minima qui ullam. Sapiente blanditiis sunt libero ea ipsam omnis qui asperiores ut. Eaque cum quam quisquam rem. Odit rerum voluptas aut vero deserunt nam dolorem. Dolore ut tempora qui exercitationem ipsa minima fugiat quod commodi. Dolor consectetur ipsam ut ut necessitatibus. Dolores blanditiis ducimus et. Ipsam dolorem quia sit nam error. Blanditiis dicta ab eligendi praesentium. Accusantium nostrum amet corporis similique magni. Ducimus et voluptatibus enim sed voluptas repudiandae quia ut porro. Omnis inventore sed.','15530879977','666666',20),(18,4,'Delores Schumm','Qui in laborum nulla libero eveniet et. Ea ut quis sint. Ea est corrupti commodi autem quia inventore provident iste dignissimos. Autem corporis a fugiat cumque quam debitis facere non dolor. Possimus nam debitis accusamus est iure consectetur. Voluptatem quia quod exercitationem id voluptatum ut dignissimos. Tempora et illo qui. Rerum corrupti qui fugiat suscipit architecto dolorem non corrupti. Minus ea aut et eos. Consequatur aliquid dolorem blanditiis ab possimus. Ea placeat molestiae et labore sed possimus.','13503130808','666666',21);
/*!40000 ALTER TABLE `t_member_launch_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(200) DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键',
  `order_num` varchar(100) DEFAULT NULL COMMENT '订单号',
  `pay_order_num` varchar(100) DEFAULT NULL COMMENT '支付宝流水号',
  `order_amount` int(11) DEFAULT NULL COMMENT '订单金额',
  `invoice` int(11) DEFAULT NULL COMMENT '是否需要发票（0: 不开，1: 开）',
  `Invoice_title` varchar(50) DEFAULT NULL COMMENT '发票抬头',
  `order_remark` varchar(100) DEFAULT NULL COMMENT '订单备注',
  `address_id` int(11) DEFAULT NULL COMMENT '收货地址ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (3,NULL,NULL,NULL,1,'Gerald Green','',4),(4,'20231208135215b86bd6a65d244b22a270a8d1eda7f3ba','2023120822001438290501687928',15552,1,'阿达尴尬啊地方','第三方滚吧水电费',5),(5,'20231208135527cf16f988dd9f44488c803a106beb3fb9','2023120822001438290501694615',6446,1,'Nicholas Padberg','杀伐果断巴萨地方噶手动阀',5),(6,'20231208140105962979624fbc4e13b489b178ef132016','2023120822001438290501689506',9669,0,'','撒旦法噶水电费',5),(8,'2023120814152540982363463b4a1587c9ccc03c44570c','2023120822001438290501692441',16115,0,'','大改革大发给',5),(9,'2023120814152540982363463b4a1587c9ccc03c44570c','2023120822001438290501692441',16115,0,'','大改革大发给',5);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_project`
--

DROP TABLE IF EXISTS `t_order_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目信息主键',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `launch_name` varchar(100) DEFAULT NULL COMMENT '发起人',
  `return_content` varchar(1000) DEFAULT NULL COMMENT '回报内容',
  `return_count` int(11) DEFAULT NULL COMMENT '回报数量',
  `support_unit_price` int(11) DEFAULT NULL COMMENT '支持单价',
  `delivery_charge` int(11) DEFAULT NULL COMMENT '配送费用',
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='订单项目信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_project`
--

LOCK TABLES `t_order_project` WRITE;
/*!40000 ALTER TABLE `t_order_project` DISABLE KEYS */;
INSERT INTO `t_order_project` VALUES (1,'第三方公对公','Bernice Keebler','Nemo aliquam autem repellendus eos amet quis consequatur rerum beatae. Magnam dolorem ut. Placeat vel id dolor aut qui autem molestias et. Est ab commodi enim consequatur iusto neque quod voluptatum consequatur. Quas reprehenderit id numquam unde rerum ipsam repellat saepe laboriosam. Repudiandae sequi repudiandae quam ratione dolorem nihil in. Beatae fugit ut similique. Ea sed accusamus est dolorem ut architecto tempora sed eaque. Natus et assumenda. Necessitatibus sit voluptatem excepturi qui ipsam soluta dolor. Minima a ullam et excepturi omnis id. Fugiat odio autem unde aut dolores nihil sed. Incidunt totam aut quos tempore.',1,15544,8,NULL),(2,'第三方公对公','Bernice Keebler','Nemo aliquam autem repellendus eos amet quis consequatur rerum beatae. Magnam dolorem ut. Placeat vel id dolor aut qui autem molestias et. Est ab commodi enim consequatur iusto neque quod voluptatum consequatur. Quas reprehenderit id numquam unde rerum ipsam repellat saepe laboriosam. Repudiandae sequi repudiandae quam ratione dolorem nihil in. Beatae fugit ut similique. Ea sed accusamus est dolorem ut architecto tempora sed eaque. Natus et assumenda. Necessitatibus sit voluptatem excepturi qui ipsam soluta dolor. Minima a ullam et excepturi omnis id. Fugiat odio autem unde aut dolores nihil sed. Incidunt totam aut quos tempore.',1,15544,8,NULL),(3,'该方法挂电话梵蒂冈','Marcos Steuber','Saepe quidem dolor illo natus. Fuga soluta laudantium velit aut. Et et dolores qui temporibus. Aut iure voluptate fugiat. Quis autem consequatur id fugit dicta modi cumque. Qui est ut fugiat praesentium dolor. Dolorem fugit autem qui nostrum incidunt voluptatibus aliquam praesentium. Quia quia in consequuntur cupiditate explicabo exercitationem et voluptatum. Qui repellendus dolorum rerum sequi eius rerum. Ad est eveniet suscipit. Explicabo rerum perspiciatis ab iure aut.',2,3223,0,NULL),(4,'该方法挂电话梵蒂冈','Marcos Steuber','Saepe quidem dolor illo natus. Fuga soluta laudantium velit aut. Et et dolores qui temporibus. Aut iure voluptate fugiat. Quis autem consequatur id fugit dicta modi cumque. Qui est ut fugiat praesentium dolor. Dolorem fugit autem qui nostrum incidunt voluptatibus aliquam praesentium. Quia quia in consequuntur cupiditate explicabo exercitationem et voluptatum. Qui repellendus dolorum rerum sequi eius rerum. Ad est eveniet suscipit. Explicabo rerum perspiciatis ab iure aut.',3,3223,0,NULL),(6,'该方法挂电话梵蒂冈','Marcos Steuber','Saepe quidem dolor illo natus. Fuga soluta laudantium velit aut. Et et dolores qui temporibus. Aut iure voluptate fugiat. Quis autem consequatur id fugit dicta modi cumque. Qui est ut fugiat praesentium dolor. Dolorem fugit autem qui nostrum incidunt voluptatibus aliquam praesentium. Quia quia in consequuntur cupiditate explicabo exercitationem et voluptatum. Qui repellendus dolorum rerum sequi eius rerum. Ad est eveniet suscipit. Explicabo rerum perspiciatis ab iure aut.',5,3223,0,NULL),(7,'该方法挂电话梵蒂冈','Marcos Steuber','Saepe quidem dolor illo natus. Fuga soluta laudantium velit aut. Et et dolores qui temporibus. Aut iure voluptate fugiat. Quis autem consequatur id fugit dicta modi cumque. Qui est ut fugiat praesentium dolor. Dolorem fugit autem qui nostrum incidunt voluptatibus aliquam praesentium. Quia quia in consequuntur cupiditate explicabo exercitationem et voluptatum. Qui repellendus dolorum rerum sequi eius rerum. Ad est eveniet suscipit. Explicabo rerum perspiciatis ab iure aut.',5,3223,0,9);
/*!40000 ALTER TABLE `t_order_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_project`
--

DROP TABLE IF EXISTS `t_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `project_description` longtext COMMENT '项目描述',
  `money` int(11) DEFAULT NULL COMMENT '筹集金额',
  `day` int(11) DEFAULT NULL COMMENT '筹集天数',
  `status` int(4) DEFAULT NULL COMMENT '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败',
  `deploydate` varchar(19) DEFAULT NULL COMMENT '项目发起时间',
  `supportmoney` bigint(11) DEFAULT NULL COMMENT '已筹集到的金额',
  `supporter` int(11) DEFAULT NULL COMMENT '支持人数',
  `completion` int(3) DEFAULT NULL COMMENT '百分比完成度',
  `memberid` int(11) DEFAULT NULL COMMENT '发起人的会员 id',
  `createdate` varchar(19) DEFAULT NULL COMMENT '项目创建时间',
  `follower` int(11) DEFAULT NULL COMMENT '关注人数',
  `header_picture_path` varchar(255) DEFAULT NULL COMMENT '头图路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_project`
--

LOCK TABLES `t_project` WRITE;
/*!40000 ALTER TABLE `t_project` DISABLE KEYS */;
INSERT INTO `t_project` VALUES (12,'项目名称1111','简介1111Omnis voluptates animi tenetur placeat cumque voluptas accusantium et. Tenetur vitae adipisci deserunt illum. Itaque vitae accusamus natus perspiciatis. Ut et adipisci in itaque. Voluptates inventore quia. Quia nostrum delectus minima in mollitia voluptas porro sequi aperiam. Neque est numquam quis est. Alias perspiciatis quia exercitationem ratione et non. Omnis placeat consectetur deserunt architecto distinctio maiores quibusdam voluptatem modi. Iste sunt perferendis ad laborum harum sint facere hic aspernatur. Praesentium quo impedit enim et minima. Nam aut autem ut quisquam et quam modi nihil animi. Commodi eligendi reiciendis alias.',88888,30,0,'2023-12-01 12:54',44444,5554,56,4,'2023-12-02 18:22',1777,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231202/ca0db9f3a2eb4225aeed8978cfc00528.jpg'),(13,'项目名称1111','简介1111Ab dolorum ut quis laudantium et voluptas dolores. Sed qui praesentium enim esse maiores totam dolorem quas libero. Minima eum eos temporibus id perspiciatis. Eligendi omnis sequi natus error ea eaque. Accusamus ab libero minima in vel recusandae beatae fugiat eaque. Nulla voluptatem distinctio facere commodi voluptates eius dolor itaque qui. Voluptas aut beatae eos explicabo quis repudiandae tenetur. Saepe est quod aspernatur ipsa ullam. Hic rerum qui dolorem et molestias. Voluptatem fugiat quibusdam similique a aut repellat in. Ipsa mollitia quibusdam aliquid.',6666666,30,0,'2023-12-02 09:30',4200000,33332,70,4,'2023-12-02 18:28',4323,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231202/017cd4cf3f18481286e3466af86a4e33.gif'),(14,'很快，接触过好几年甲方共和国很大几哈','简介1111Necessitatibus delectus corrupti incidunt nobis aut sit. Blanditiis provident eaque deleniti blanditiis dolor a. Dicta quis nulla dolores beatae aut. Unde et dolorum deleniti quasi labore. Sit aut laboriosam voluptates vel dicta qui enim in harum. Voluptas animi sit. Nihil at autem placeat quos. Voluptas sint delectus corporis occaecati voluptatem libero. Excepturi placeat dolore corporis consequuntur doloribus. Rerum ut quis. Voluptas placeat fugit magnam aut dolorem dolor sint. Architecto quod esse.',3245333,15,0,'2023-12-03 09:30',324533,332,11,4,'2023-12-03 13:03:26',3245345,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/3a9920d533444dd696189d75768575fc.jpg'),(15,'第三方公对公','Quae est est quo deleniti quia. Quae unde quam. Sit unde consequatur excepturi. Vero et officia tempora sunt eveniet facilis qui dolore. Natus sit est sunt quo dolores aut deleniti sint. Nihil est expedita. Rem maiores saepe voluptatum at hic. Maiores dicta maiores et exercitationem praesentium architecto itaque. Autem dicta nesciunt corporis. Aspernatur fugit occaecati in reprehenderit similique facilis est quia error. Rem earum voluptate eos. Sint consectetur dolore tenetur consectetur.',21556,30,0,'2023-12-03 09:30',2155,6444,8,4,'2023-12-03 13:06:34',3334,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/048b067b30a7418089d7d01311be96a0.jpg'),(16,'该方法挂电话梵蒂冈','Ut eligendi totam eos unde sequi vel atque doloribus. Quas beatae atque nulla blanditiis asperiores. Nulla inventore eaque accusantium cum. Sit quam et quis. Facere eos sit consequatur architecto sunt iusto tempore excepturi. Et sequi magnam velit esse explicabo rerum ex. Repellat labore necessitatibus nihil aut labore ratione. Eum rem nostrum quo inventore optio ipsam. Molestias aut voluptatem omnis ducimus autem. Cumque odio inventore omnis repudiandae doloribus et. Nesciunt aut corporis libero hic excepturi et et. Assumenda atque enim qui.',34532332,30,0,'2023-12-01 09:30',3453233,5533,9,4,'2023-12-03 13:08:11',3453,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/ff43521795c14f2cbfb4a1ce7f1a6f25.jpg'),(17,'项目名称1111','Dicta qui quae excepturi amet provident aliquid placeat ad est. Dolores debitis porro consectetur maiores quia. Soluta odit numquam nihil qui unde suscipit. Esse at ea delectus quia deserunt molestiae culpa quos non. Ullam dolor alias et a voluptatum facilis nobis quisquam dicta. Animi ut nesciunt quasi dolorem. Dolore facere harum nesciunt velit et. Placeat rerum vel tempora nihil modi quaerat sed. Eaque quasi ad consequatur consequatur nemo enim. Alias culpa quia laborum. Ducimus ut ut quisquam similique perferendis assumenda quos quis. Et nisi non totam assumenda nostrum.',67454,15,0,'2023-12-03 09:30',6745,1222,10,4,'2023-12-03 13:09:33',33455,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/e779b8108b6e4a48b4917e9c475e8dde.jpg'),(20,'规范摆放根本大法','Sit autem sed in. Incidunt et et animi ducimus magni et nesciunt asperiores. Illo sed asperiores ad qui sint. Exercitationem perferendis et quidem sunt aperiam. Voluptate soluta ut cum repellat. Id molestiae dolorem magni vel quis error. Velit reprehenderit iure quia unde. Sint rerum ut eius quia. Neque magni modi. Enim qui vel et ut et et possimus.',35433,30,0,'2023-11-30 09:30',35433,4543,100,4,'2023-12-03 13:11:23',676867,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/7ae08fb09125474ab6a5b85f4aa18613.jpg'),(21,'海南供电公司电饭锅手动阀','Soluta aperiam sit harum soluta. Quaerat voluptatem atque iusto consequatur corporis et dolor eius. Delectus exercitationem minus tempora. Sit eos doloribus. Quis possimus dolor eaque. Omnis illum voluptatibus architecto dolorem excepturi in autem quia. Aliquid sequi et cupiditate. Harum eius qui eos. Est quos asperiores et saepe voluptatem. Ex recusandae dolorum voluptatem enim sit eveniet ea iste.',13232322,30,0,'2023-12-03 09:30',13232322,665,100,4,'2023-12-03 13:12:30',55432,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/f057d456bcd0457e85e560660a2c34f4.jpg');
/*!40000 ALTER TABLE `t_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_project_item_pic`
--

DROP TABLE IF EXISTS `t_project_item_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_project_item_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `item_pic_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='项目表项目详情图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_project_item_pic`
--

LOCK TABLES `t_project_item_pic` WRITE;
/*!40000 ALTER TABLE `t_project_item_pic` DISABLE KEYS */;
INSERT INTO `t_project_item_pic` VALUES (12,12,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231202/674be425b559478e986f326cf8d390a5.png'),(13,13,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231202/917a007488b74de49e0643d1c1b908a3.gif'),(14,14,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/ce392dac768a4f02afe6bfda45bbcee3.jpg'),(15,15,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/3a2bb13e706d42f7815534f515dbf44d.jpg'),(16,16,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/e631a43946cd4d9db0af628a1651687c.jpg'),(17,17,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/21dcec00b0644cfabc5b5be05943e1de.jpg'),(18,20,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/d20355c722764c53b0c200452195fbbc.jpg'),(19,21,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/195149ceed2c4c5eb119d933eb6137b9.jpg');
/*!40000 ALTER TABLE `t_project_item_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_project_tag`
--

DROP TABLE IF EXISTS `t_project_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_project_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `tagid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COMMENT='项目标签中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_project_tag`
--

LOCK TABLES `t_project_tag` WRITE;
/*!40000 ALTER TABLE `t_project_tag` DISABLE KEYS */;
INSERT INTO `t_project_tag` VALUES (38,12,5),(39,12,7),(40,12,9),(41,13,5),(42,13,7),(43,13,9),(44,14,6),(45,14,4),(46,14,8),(47,14,9),(48,15,10),(49,15,4),(50,15,7),(51,16,7),(52,16,9),(53,16,10),(54,16,5),(55,17,6),(56,17,7),(57,17,9),(58,17,10),(59,20,5),(60,20,7),(61,20,10),(62,21,8),(63,21,7),(64,21,9);
/*!40000 ALTER TABLE `t_project_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_project_type`
--

DROP TABLE IF EXISTS `t_project_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_project_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='项目分类中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_project_type`
--

LOCK TABLES `t_project_type` WRITE;
/*!40000 ALTER TABLE `t_project_type` DISABLE KEYS */;
INSERT INTO `t_project_type` VALUES (24,12,1),(25,13,2),(26,13,3),(27,14,4),(28,15,1),(29,16,2),(30,17,2),(33,20,4),(34,21,2);
/*!40000 ALTER TABLE `t_project_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_return`
--

DROP TABLE IF EXISTS `t_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `type` int(4) DEFAULT NULL COMMENT '0 - 实物回报， 1 虚拟物品回报',
  `supportmoney` int(11) DEFAULT NULL COMMENT '支持金额',
  `content` longtext COMMENT '回报内容',
  `count` int(11) DEFAULT NULL COMMENT '回报产品限额，“0”为不限回报数量',
  `signalpurchase` int(11) DEFAULT NULL COMMENT '是否设置单笔限购',
  `purchase` int(11) DEFAULT NULL COMMENT '具体限购数量',
  `freight` int(11) DEFAULT NULL COMMENT '运费，“0”为包邮',
  `invoice` int(4) DEFAULT NULL COMMENT '0 - 不开发票， 1 - 开发票',
  `returndate` int(11) DEFAULT NULL COMMENT '项目结束后多少天向支持者发送回报',
  `describ_pic_path` varchar(255) DEFAULT NULL COMMENT '说明图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='回报信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_return`
--

LOCK TABLES `t_return` WRITE;
/*!40000 ALTER TABLE `t_return` DISABLE KEYS */;
INSERT INTO `t_return` VALUES (8,12,0,10000,'简单描述回报内容Numquam similique explicabo nostrum voluptates qui id quidem qui consequatur. Alias esse tempore veniam aut deleniti dolores. Soluta id quia. Aut cumque consequatur eaque nesciunt qui tenetur. Accusantium qui asperiores ratione est. Sapiente omnis quia placeat commodi quia. Error dolor praesentium sunt animi consequatur. Possimus ut id perspiciatis expedita. Odit voluptatem et vero iusto enim et qui. Sunt consectetur dolorum laborum perspiciatis exercitationem vel animi eveniet nihil. Qui ipsum corporis reprehenderit fuga.',0,0,1,10,1,7,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231202/f3db5944265d4e2b9623a62a6afbf3d0.jpg'),(9,13,0,10000000,'简单描述回报内容Necessitatibus repellat ipsam suscipit quibusdam. Et ea consequatur nihil dolor cumque ipsum. Repellat voluptatem veritatis quis veritatis voluptatum aut aut ipsam. Et id ut voluptas rerum eius repudiandae perferendis. Distinctio et vitae quia molestias delectus. Ipsum sed quasi. Dignissimos quae reiciendis et cum ea odit reiciendis. Aut dolor ea ut dolorum provident qui et magnam. Aspernatur illo eum molestias tempora vitae. Voluptas quas alias dignissimos perspiciatis autem saepe tempore. Aut consequatur non qui sunt tempore omnis at quo rem. Sequi et delectus vel eius.',0,0,1,6,1,15,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231202/5d21d73fe3b143e5ab9d311619eec561.jpg'),(10,14,0,1000,'Est natus nostrum animi corrupti et. Sit voluptas quia ut fugiat eos consequatur. Omnis ut tempore illum adipisci omnis. Vel corrupti quia consectetur neque ducimus dolorem. Sint ducimus aut consectetur consequuntur. Esse ut odio et. Aspernatur qui voluptas amet nihil quia placeat facilis porro. Et accusantium beatae omnis ut unde. Veniam id et voluptate eveniet amet corporis consectetur incidunt eveniet. Vero quae ipsum enim debitis aut qui sapiente consectetur eum. Dolor aspernatur dolores soluta laborum neque sit quibusdam laborum. Dolor voluptatibus quia voluptatem.',0,0,1,5,1,5,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/69b4450fbc324ddfbe9bb24320b03914.jpg'),(11,15,0,15544,'Nemo aliquam autem repellendus eos amet quis consequatur rerum beatae. Magnam dolorem ut. Placeat vel id dolor aut qui autem molestias et. Est ab commodi enim consequatur iusto neque quod voluptatum consequatur. Quas reprehenderit id numquam unde rerum ipsam repellat saepe laboriosam. Repudiandae sequi repudiandae quam ratione dolorem nihil in. Beatae fugit ut similique. Ea sed accusamus est dolorem ut architecto tempora sed eaque. Natus et assumenda. Necessitatibus sit voluptatem excepturi qui ipsam soluta dolor. Minima a ullam et excepturi omnis id. Fugiat odio autem unde aut dolores nihil sed. Incidunt totam aut quos tempore.',1,1,1,8,0,7,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/4c05b83b75b84671a3f8d5ffb184a467.jpg'),(12,16,1,3223,'Saepe quidem dolor illo natus. Fuga soluta laudantium velit aut. Et et dolores qui temporibus. Aut iure voluptate fugiat. Quis autem consequatur id fugit dicta modi cumque. Qui est ut fugiat praesentium dolor. Dolorem fugit autem qui nostrum incidunt voluptatibus aliquam praesentium. Quia quia in consequuntur cupiditate explicabo exercitationem et voluptatum. Qui repellendus dolorum rerum sequi eius rerum. Ad est eveniet suscipit. Explicabo rerum perspiciatis ab iure aut.',0,0,1,0,0,30,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/d64da8af7c57426197f4b3a915699f5e.jpg'),(13,17,0,34423,'简单描述回报内容',0,0,1,15,1,7,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/c5de8b99f0f949e590389d294c470534.jpg'),(14,20,0,34543,'Beatae quos et quibusdam voluptatem accusamus esse. Quas perferendis excepturi sint labore alias enim quia tempore nam. Odit numquam earum sunt. Et perspiciatis dolor sit vel qui. Qui harum ut. Rerum rerum ut placeat rem debitis voluptas totam quia totam. Voluptatum eius qui consequatur minima. Autem inventore voluptatum cumque aut neque. Modi est vero ex. Sint suscipit corporis repellat aut et quos at non sint. Velit aspernatur inventore expedita rerum dignissimos natus totam aperiam quia.',0,0,1,0,1,7,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/aca8fd41fc1d4b7a876b12579643c04b.jpg'),(15,20,0,453,'Vel autem ipsa cupiditate et sit fugit. Occaecati dolor qui non quaerat. Est amet ipsa sint similique ut rerum. Dolores ipsum aut provident saepe perspiciatis sunt velit tempora. Sit velit repellat molestiae qui velit. Omnis harum laudantium temporibus occaecati voluptas recusandae doloribus. Laudantium sed ducimus enim voluptates beatae consequatur earum. Est laboriosam et eligendi. Totam repudiandae ut voluptate exercitationem quia est. Voluptatum rerum pariatur quo ipsum alias. Voluptas quia qui.',0,0,1,25,1,7,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/b39d0eb041824138b2fd8c6fa6eb5fe8.jpg'),(16,21,0,43332,'Perspiciatis et assumenda voluptas molestiae nam sit odit. Consectetur libero voluptas. Error id eveniet enim sint molestiae et rem sint ea. Non non aspernatur rem sed. Inventore consequatur natus nesciunt eveniet dolorem. Quia perferendis non reiciendis dolore aperiam. Ut voluptatem fugiat iure eum iusto sequi ut. Sunt quae magni molestiae voluptatem. Impedit natus aliquam aliquid quibusdam. Inventore occaecati et.',0,0,1,30,1,7,'https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com/20231203/f8644bc021fc4a82aa1da19dfb508e72.jpg');
/*!40000 ALTER TABLE `t_return` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(100) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '分类介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (1,'科技','科技，改变生活'),(2,'设计','设计，塑造未来'),(3,'公益','公益，温暖人心'),(4,'农业','农业，滋养生命');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-08 21:04:46
