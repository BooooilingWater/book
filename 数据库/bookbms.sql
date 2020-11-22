/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.26 : Database - bookbms
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookbms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bookbms`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`name`,`password`,`email`) values (1,'admin','123456','1340007091@qq.com');

/*Table structure for table `t_book` */

DROP TABLE IF EXISTS `t_book`;

CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书籍id',
  `name` varchar(20) NOT NULL COMMENT '书名',
  `author` varchar(20) DEFAULT NULL COMMENT '作者名字',
  `publish` varchar(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL COMMENT '类别id',
  `price` double DEFAULT NULL COMMENT '价格',
  `introduction` varchar(100) DEFAULT NULL COMMENT '书籍的简介',
  `cover` varchar(255) DEFAULT NULL COMMENT '书籍封面',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `book_category` (`category_id`) USING BTREE,
  CONSTRAINT `book_category` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_book` */

insert  into `t_book`(`id`,`name`,`author`,`publish`,`category_id`,`price`,`introduction`,`cover`) values (1,'巨人的陨落','肯.福莱特','江苏凤凰文艺出版社',1,129,'在第一次世界大战中发生的故事',NULL),(2,'三体','刘慈欣','南京大学出版社',1,68,'科幻小说',NULL),(3,'复活','列夫.托尔斯泰','上海译文出版社',1,19,'俄国小说',NULL),(6,'平凡的世界','路遥','上海文艺出版社',1,88,'孙少平和孙少安两兄弟...',NULL),(11,'计算机网络','寒冬','广东金融出版社',3,100.22,'由当代顶级教授编写的书',NULL),(15,'白鹿原','陈忠实','南京出版社',1,36,'当代小说',NULL),(16,'计算机网络','谢希仁','电子工业出版社',3,49,'计算机专业书籍',NULL),(17,'霍乱时期的爱情','加西亚·马尔克斯','译林出版社',9,39,'外国小说',NULL),(18,'天才在左疯子在右','高铭','北京联合出版公司',1,39.8,'心理学',NULL),(19,'废都','贾平凹','商务印书馆',8,29,'当代小说',NULL),(20,'jQuery','Ryan','中国电力出版社',3,78,'js库',NULL),(21,'ss','ss','ss',NULL,11,'ss',NULL),(22,'java EE 企业级应用开发教程','黑马程序员','人民邮电出版社',3,49.8,'本书共18章, 涵盖了轻量级javaEE框架spring+ springmvc+ mybatis的实际应用技术, 每个知识点都配备了实战案例, 共36道思考题.',NULL),(26,'圣墟','辰东','',1,50.9,'超级水的一本书, 日更到周更到月更......',NULL),(27,'33','33','33',1,3,'33',NULL);

/*Table structure for table `t_borrowing` */

DROP TABLE IF EXISTS `t_borrowing`;

CREATE TABLE `t_borrowing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `book_id` (`book_id`) USING BTREE,
  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_borrowing` */

insert  into `t_borrowing`(`id`,`user_id`,`book_id`,`create_time`) values (3,1,3,'2020-10-22 20:21:05'),(9,5,1,'2020-10-22 20:21:05'),(24,1,6,'2020-10-22 20:21:05'),(25,1,15,'2020-10-22 20:21:05'),(26,1,2,'2020-10-22 20:21:05'),(28,5,19,'2020-10-22 20:21:05'),(29,1,16,'2020-10-22 20:21:05'),(31,2,20,'2020-10-22 20:21:05'),(47,19,17,'2020-10-22 20:21:05');

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_category` */

insert  into `t_category`(`id`,`name`) values (1,'小说'),(2,'历史'),(3,'计算机'),(4,'哲学'),(5,'社会科学'),(6,'政治法律'),(7,'军事科学'),(8,'中国文学'),(9,'外国文学'),(10,'外国传记'),(11,'英语'),(12,'俄国小说'),(13,'心理学'),(14,'言情小说'),(15,'武侠小说'),(16,'环境科学'),(17,'纪实文学');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL,
  `truename` varchar(255) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`truename`,`password`,`email`,`phone`) values (1,'kht','张康大','123456','for1@163.com','15565575333'),(2,'zhangfei',NULL,'123456','forzf@163.com',NULL),(5,'李四',NULL,'123456',NULL,NULL),(6,'LeBronJames',NULL,'123456','1111@qq.com',NULL),(7,'科比',NULL,'123456',NULL,NULL),(8,'柏拉图',NULL,'123456',NULL,NULL),(9,'拿破仑',NULL,'123456',NULL,NULL),(10,'欧文',NULL,'123456',NULL,NULL),(11,'库兹马',NULL,'123456',NULL,NULL),(12,'球哥',NULL,'123456',NULL,NULL),(13,'魔术师',NULL,'123456',NULL,NULL),(16,'周杰伦',NULL,'123456',NULL,NULL),(17,'aa',NULL,'aaaaaa',NULL,NULL),(18,'adsf',NULL,'adfasdf',NULL,NULL),(19,'零厂房',NULL,'1111111','111@qq.cm',NULL),(20,'adfa',NULL,'asdfasfas',NULL,NULL),(21,'adfaasdf',NULL,'asdfasfas',NULL,NULL),(22,'aaaaaa',NULL,'aaaaaa',NULL,NULL),(30,' 1',NULL,'111111',NULL,NULL),(31,'1',NULL,'111111','1adf@qq.com',NULL),(32,'刘辉彬',NULL,'1111111','111@qq.com',NULL),(33,'11',NULL,'222222',NULL,NULL),(34,'aaaa',NULL,'aaaaaa',NULL,NULL),(35,'lily',NULL,'111111','111W@qq.com',NULL),(36,'啊啊啊',NULL,'111111','aaa@qq.com',NULL),(37,'adf',NULL,'aaaaaa',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
