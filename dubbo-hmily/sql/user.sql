DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(128) NOT NULL,
                           `balance_rmb` decimal(10,0) NOT NULL COMMENT '用户人民币账户',
                           `balance_usd` decimal(10,0) NOT NULL COMMENT '用户美元账户',
                           `create_time` datetime NOT NULL,
                           `update_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

insert  into `account`(`id`,`user_id`,`balance_rmb`, `balance_usd`,`create_time`,`update_time`) values
(1,'1', 10000000,0,'2020-12-15 14:54:22',NULL);
