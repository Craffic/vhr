/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.26 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `hr` (
	`id` int (11),
	`name` varchar (96),
	`phone` char (33),
	`telephone` varchar (48),
	`address` varchar (192),
	`enabled` tinyint (1),
	`username` varchar (765),
	`password` varchar (765),
	`userface` varchar (765),
	`remark` varchar (765)
); 
insert into `hr` (`id`, `name`, `phone`, `telephone`, `address`, `enabled`, `username`, `password`, `userface`, `remark`) values('3','系统管理员','18568887789','029-82881234','深圳南山','1','admin','$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm','https://c-ssl.duitang.com/uploads/blog/202101/31/20210131161028_f9bd0.jpg',NULL);
insert into `hr` (`id`, `name`, `phone`, `telephone`, `address`, `enabled`, `username`, `password`, `userface`, `remark`) values('5','李白','18568123489','029-82123434','海口美兰','1','libai','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','http://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F0720%2F871f1b56j00qwjdg6001rc000hs00hsc.jpg&thumbnail=650x2147483647&quality=80&type=jpg',NULL);
insert into `hr` (`id`, `name`, `phone`, `telephone`, `address`, `enabled`, `username`, `password`, `userface`, `remark`) values('10','韩愈','18568123666','029-82111555','广州番禺','1','hanyu','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','http://qqpublic.qpic.cn/qq_public/0/0-2234924125-8D9B3F7C8A1498176B3DE8594D516760/0?fmt=jpg&size=80&h=500&w=750&ppv=1/0',NULL);
insert into `hr` (`id`, `name`, `phone`, `telephone`, `address`, `enabled`, `username`, `password`, `userface`, `remark`) values('11','柳宗元','18568123377','029-82111333','广州天河','1','liuzongyuan','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','http://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F0604%2F11232f65j00qu6esn0011c000hq00hqc.jpg&thumbnail=650x2147483647&quality=80&type=jpg',NULL);
insert into `hr` (`id`, `name`, `phone`, `telephone`, `address`, `enabled`, `username`, `password`, `userface`, `remark`) values('12','曾巩','18568128888','029-82111222','广州越秀','1','zenggong','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','http://qqpublic.qpic.cn/qq_public/0/0-339883651-FE42E85CAD1657665CCF2BED5A632B76/0?fmt=jpg&size=67&h=720&w=720&ppv=1.jpg',NULL);
