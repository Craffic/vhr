/*Table structure for table `joblevel` */

DROP TABLE IF EXISTS `joblevel`;

CREATE TABLE `joblevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '职称名称',
  `titleLevel` enum('正高级','副高级','中级','初级','员级') DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `joblevel` */

insert  into `joblevel`(`id`,`name`,`titleLevel`,`createDate`,`enabled`) values (9,'教授','正高级','2018-01-11 00:00:00',1),(10,'副教授','副高级','2018-01-11 21:19:20',1),(12,'助教','初级','2018-01-11 21:35:39',1),(13,'讲师','中级','2018-01-11 00:00:00',0),(14,'初级工程师','初级','2018-01-14 00:00:00',1),(15,'中级工程师66','中级','2018-01-14 00:00:00',1),(16,'高级工程师','副高级','2018-01-14 16:19:14',1),(17,'骨灰级工程师','正高级','2018-01-14 16:19:24',1);
