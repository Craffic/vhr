/*Table structure for table `empsalary` */

DROP TABLE IF EXISTS `empsalary`;

CREATE TABLE `empsalary` (
                             `id` INT(11) NOT NULL AUTO_INCREMENT,
                             `eid` INT(11) DEFAULT NULL,
                             `sid` INT(11) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `eid` (`eid`),
                             KEY `empsalary_ibfk_2` (`sid`),
                             CONSTRAINT `empsalary_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`),
                             CONSTRAINT `empsalary_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `salary` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `empsalary` */

INSERT  INTO `empsalary`(`id`,`eid`,`sid`) VALUES (6,4,10),(10,5,9),(11,6,13),(12,7,13),(14,8,10),(15,9,10),(20,10,13),(21,11,9),(22,3,13),(24,2,9),(25,1,13),(26,33,10),(28,34,9),(29,44,10),(30,45,10),(31,43,10),(32,47,10),(33,52,13),(34,53,10),(35,54,10),(36,56,10),(38,21,9);
