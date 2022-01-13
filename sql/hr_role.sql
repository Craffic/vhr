
/*Table structure for table `hr_role` */

DROP TABLE IF EXISTS `hr_role`;

CREATE TABLE `hr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hrid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `hr_role_ibfk_1` (`hrid`),
  CONSTRAINT `hr_role_ibfk_1` FOREIGN KEY (`hrid`) REFERENCES `hr` (`id`) ON DELETE CASCADE,
  CONSTRAINT `hr_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

/*Data for the table `hr_role` */

insert  into `hr_role`(`id`,`hrid`,`rid`) values (1,3,6),(35,12,4),(36,12,3),(37,12,2),(43,11,3),(44,11,2),(45,11,4),(46,11,5),(48,10,3),(49,10,4),(72,5,1),(73,5,2),(74,5,3);