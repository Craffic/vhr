DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `parentId` int(11) DEFAULT NULL,
  `depPath` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `isParent` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`,`parentId`,`depPath`,`enabled`,`isParent`) values (1,'股东会',-1,'.1',1,1),(4,'董事会',1,'.1.4',1,1),(5,'总办',4,'.1.4.5',1,1),(8,'财务部',5,'.1.4.5.8',1,0),(78,'市场部',5,'.1.4.5.78',1,1),(81,'华北市场部',78,'.1.4.5.78.81',1,1),(82,'华南市场部',78,'.1.4.5.78.82',1,0),(85,'石家庄市场部',81,'.1.4.5.78.81.85',1,0),(86,'西北市场部',78,'.1.4.5.78.86',1,1),(87,'西安市场',86,'.1.4.5.78.86.87',1,1),(89,'莲湖区市场',87,'.1.4.5.78.86.87.89',1,0),(91,'技术部',5,'.1.4.5.91',1,0),(92,'运维部',5,'.1.4.5.92',1,1),(93,'运维1部',92,'.1.4.5.92.93',1,0),(94,'运维2部',92,'.1.4.5.92.94',1,0),(96,'bbb',1,'.1.96',1,1),(104,'111',96,'.1.96.104',1,0);
