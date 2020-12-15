DROP TABLE IF EXISTS `account`;

CREATE TABLE `account_rmb` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(128) NOT NULL,
                           `balance` decimal(10,0) NOT NULL COMMENT '用户人民币账户',
                           `create_time` datetime NOT NULL,
                           `update_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

insert  into `account_rmb`(`id`,`user_id`,`balance`,`create_time`,`update_time`) values
(1,'1', 1000,'2020-12-15 14:54:22',NULL);
insert  into `account_rmb`(`id`,`user_id`,`balance`,`create_time`,`update_time`) values
(2,'2', 0,'2020-12-15 14:54:22',NULL);

CREATE TABLE `account_usd` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(128) NOT NULL,
                           `balance` decimal(10,0) NOT NULL COMMENT '用户人民币账户',
                           `create_time` datetime NOT NULL,
                           `update_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

insert  into `account_usd`(`id`,`user_id`,`balance`,`create_time`,`update_time`) values
(1, '1', 0, '2020-12-15 14:54:22', NULL);
insert  into `account_usd`(`id`,`user_id`,`balance`,`create_time`,`update_time`) values
(2, '2', 100, '2020-12-15 14:54:22', NULL);

create table `account_freeze` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(128) NOT NULL,
                           `freeze_rmb` decimal(10, 0) not null,
                           `freeze_usd` decimal(10, 0) not null,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;