/*Table structure for table `politicsstatus` */
DROP TABLE IF EXISTS `politicsstatus`;
CREATE TABLE `politicsstatus` (
                                  `id` INT(11) NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(32) DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;



/*Data for the table `politicsstatus` */
INSERT  INTO `politicsstatus`(`id`,`name`) VALUES (1,'中共党员'),(2,'中共预备党员'),(3,'共青团员'),(4,'民革党员'),(5,'民盟盟员'),(6,'民建会员'),(7,'民进会员'),(8,'农工党党员'),(9,'致公党党员'),(10,'九三学社社员'),(11,'台盟盟员'),(12,'无党派民主人士'),(13,'普通公民');

