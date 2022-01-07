/*Table structure for table `salary` */
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `basicSalary` int(11) DEFAULT NULL COMMENT '基本工资',
                          `bonus` int(11) DEFAULT NULL COMMENT '奖金',
                          `lunchSalary` int(11) DEFAULT NULL COMMENT '午餐补助',
                          `trafficSalary` int(11) DEFAULT NULL COMMENT '交通补助',
                          `allSalary` int(11) DEFAULT NULL COMMENT '应发工资',
                          `pensionBase` int(11) DEFAULT NULL COMMENT '养老金基数',
                          `pensionPer` float DEFAULT NULL COMMENT '养老金比率',
                          `createDate` timestamp NULL DEFAULT NULL COMMENT '启用时间',
                          `medicalBase` int(11) DEFAULT NULL COMMENT '医疗基数',
                          `medicalPer` float DEFAULT NULL COMMENT '医疗保险比率',
                          `accumulationFundBase` int(11) DEFAULT NULL COMMENT '公积金基数',
                          `accumulationFundPer` float DEFAULT NULL COMMENT '公积金比率',
                          `name` varchar(32) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


/*Data for the table `salary` */

insert  into `salary`(`id`,`basicSalary`,`bonus`,`lunchSalary`,`trafficSalary`,`allSalary`,`pensionBase`,`pensionPer`,`createDate`,`medicalBase`,`medicalPer`,`accumulationFundBase`,`accumulationFundPer`,`name`) values (9,9000,4000,800,500,NULL,2000,0.07,'2018-01-24 00:00:00',2000,0.07,2000,0.07,'市场部工资账套'),(10,2000,2000,400,1000,NULL,2000,0.07,'2018-01-01 00:00:00',2000,0.07,2000,0.07,'人事部工资账套'),(13,20000,3000,500,500,NULL,4000,0.07,'2018-01-25 00:00:00',4000,0.07,4000,0.07,'运维部工资账套');
