/**chapterthree start**/
/**multi-tenant-spring-cloud-alibaba**/
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice`;
use `spring_cloud_alibaba_practice`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint NOT NULL COMMENT '主键ID',
                        `tenant_id` bigint NOT NULL COMMENT '租户ID',
                        `name` varchar(30) DEFAULT NULL COMMENT '姓名',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,1,'张三'),(2,1,'李四'),(3,1,'王五'),(4,0,'胡弦'),(5,0,'胡辰昱');
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice`;
DROP TABLE IF EXISTS `USER_ADDR`;
CREATE TABLE `USER_ADDR` (
                             `id` bigint NOT NULL COMMENT '主键ID',
                             `user_id` bigint NOT NULL COMMENT 'user.id',
                             `name` varchar(30) DEFAULT NULL COMMENT '地址名称',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `USER_ADDR` WRITE;
INSERT INTO `USER_ADDR` VALUES (1,1,'浙江省杭州市西湖区'),(2,4,'浙江省杭州市滨江区');
UNLOCK TABLES;
commit;
/**multi-tenant-spring-cloud-alibaba**/

/**multidata-source-spring-cloud-alibaba**/
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice_master`;
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice_slave1`;
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice_slave2`;

use `spring_cloud_alibaba_practice_master`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';

LOCK TABLES `example2_product` WRITE;
INSERT INTO `example2_product` VALUES (7823734,5678,100,'master库测试商品',0);
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice_slave1`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';

LOCK TABLES `example2_product` WRITE;

INSERT INTO `example2_product` VALUES (3457676,5678,100,'slave1库测试商品',0);
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice_slave2`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';

LOCK TABLES `example2_product` WRITE;
INSERT INTO `example2_product` VALUES (2334343434,5678,100,'slave2测试商品',0);
UNLOCK TABLES;
commit;
/**multidata-source-spring-cloud-alibaba**/

/**optimistic-locking-spring-cloud-alibaba**/
use `spring_cloud_alibaba_practice`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';
LOCK TABLES `example2_product` WRITE;
INSERT INTO `example2_product` VALUES (3467374334,6778,983848034626479,'手机',0),(3477374334,7878,120,'苹果笔记本电脑',2);
UNLOCK TABLES;
commit;
/**optimistic-locking-spring-cloud-alibaba**/

/**replace-table-spring-cloud-alibaba**/
use `spring_cloud_alibaba_practice`;
DROP TABLE IF EXISTS `example5_order_6767`;
CREATE TABLE `example5_order_6767` (
                                       `id` bigint NOT NULL,
                                       `order_name` varchar(20) NOT NULL,
                                       `order_id` bigint NOT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `example5_order_6767` WRITE;
INSERT INTO `example5_order_6767` VALUES (2323233,'租户6767的订单',2388923923);
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice`;
DROP TABLE IF EXISTS `example5_order_7878`;
CREATE TABLE `example5_order_7878` (
                                       `id` bigint NOT NULL,
                                       `order_name` varchar(20) NOT NULL,
                                       `order_id` bigint NOT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `example5_order_7878` WRITE;
INSERT INTO `example5_order_7878` VALUES (2323231111,'租户7878的订单',232323232323);
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice`;
DROP TABLE IF EXISTS `example5_order_8989`;
CREATE TABLE `example5_order_8989` (
                                       `id` bigint NOT NULL,
                                       `order_name` varchar(20) NOT NULL,
                                       `order_id` bigint NOT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `example5_order_8989` WRITE;
INSERT INTO `example5_order_8989` VALUES (2233443,'租户8989的订单',34333434343);
UNLOCK TABLES;
commit;
/**replace-table-spring-cloud-alibaba**/
/**chapterthree end**/


/**chapter four start**/
/**idempotent-design-spring-cloud-alibaba中的表在前面已经创建了**/
/**nacos-sync**/
/**搭建Nacos Sync环境的时候需要执行nacosSync.sql语句**/
/******************************************/
/*   DB name = nacos_Sync   */
/*   Table name = cluster   */
/******************************************/
CREATE DATABASE IF NOT EXISTS `nacos_sync`;
use `nacos_sync`;
CREATE TABLE `cluster` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `cluster_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                           `cluster_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                           `cluster_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                           `connect_key_list` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/******************************************/
/*   DB name = nacos_Sync   */
/*   Table name = system_config   */
/******************************************/
use `nacos_sync`;
CREATE TABLE `system_config` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `config_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                 `config_key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                 `config_value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/******************************************/
/*   DB name = nacos_Sync   */
/*   Table name = task   */
/******************************************/
use `nacos_sync`;
CREATE TABLE `task` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `dest_cluster_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `group_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `name_space` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `operation_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `service_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `source_cluster_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `task_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `task_status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `version` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        `worker_ip` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/**chapter four end**/

/**chapter five start**/
/**没有SQL执行**/
/**chapter five end**/

/**chapter six start**/
/**没有SQL执行**/
/**chapter six end**/

/**chapter seven start**/
/**7.3.1搭建Seata Server高可用的环境时需要执行的SQL语句**/
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
CREATE DATABASE IF NOT EXISTS `seata_server`;
use `seata_server`;
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- the table to store BranchSession data
use `seata_server`;
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- the table to store lock data
use `seata_server`;
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(96),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
commit;

/**业务实例需要的SQL语句**/
use `seata_server`;
CREATE TABLE IF NOT EXISTS `at_account` (
                              `id` bigint NOT NULL,
                              `account_name` varchar(50) DEFAULT NULL,
                              `account_id` bigint DEFAULT NULL,
                              `amount` bigint DEFAULT NULL,
                              `is_deleted` int NOT NULL,
                              `gmt_create` timestamp NOT NULL,
                              `gmt_modified` timestamp NULL DEFAULT NULL,
                              `user_id` bigint DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `seata_server`;
CREATE TABLE IF NOT EXISTS `at_logistics` (
                                `id` bigint NOT NULL,
                                `logistics_id` bigint NOT NULL,
                                `logistics_name` varchar(50) DEFAULT NULL,
                                `status` int NOT NULL DEFAULT '0',
                                `order_id` bigint NOT NULL,
                                `is_deleted` int NOT NULL DEFAULT '0',
                                `gmt_create` timestamp NULL DEFAULT NULL,
                                `gmt_modified` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `at_logistics_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `seata_server`;
CREATE TABLE IF NOT EXISTS `at_order` (
                            `id` bigint NOT NULL,
                            `order_name` varchar(50) DEFAULT NULL,
                            `order_id` bigint DEFAULT NULL,
                            `order_amount` bigint DEFAULT NULL,
                            `is_deleted` int NOT NULL,
                            `gmt_create` timestamp NULL DEFAULT NULL,
                            `gmt_modified` timestamp NULL DEFAULT NULL,
                            `good_id` bigint DEFAULT NULL,
                            `user_id` bigint NOT NULL,
                            `order_status` int NOT NULL DEFAULT '0',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `seata_server`;
CREATE TABLE IF NOT EXISTS `at_storage` (
                              `id` bigint NOT NULL,
                              `num` bigint NOT NULL,
                              `good_id` bigint DEFAULT NULL,
                              `is_deleted` int DEFAULT NULL,
                              `gmt_create` timestamp NULL DEFAULT NULL,
                              `gmt_modified` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `seata_server`;
CREATE TABLE IF NOT EXISTS `at_warehouse` (
                                `id` bigint NOT NULL,
                                `warehouse_name` varchar(50) NOT NULL,
                                `status` int NOT NULL DEFAULT '0',
                                `order_id` bigint NOT NULL DEFAULT '0',
                                `good_id` bigint NOT NULL DEFAULT '0',
                                `is_deleted` int NOT NULL DEFAULT '0',
                                `gmt_create` datetime DEFAULT NULL,
                                `gmt_modified` datetime DEFAULT NULL,
                                `warehouse_id` bigint DEFAULT NULL,
                                `num` int NOT NULL DEFAULT '0',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `at_warehouse_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

/**Seata 客户端 undo log**/
-- for AT mode you must to init this sql for you business database. the seata server not need it.
use `seata_server`;
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
    ) ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
commit;

use `seata_server`;
CREATE TABLE `tcc_account` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `user_id` bigint DEFAULT NULL COMMENT '用户id',
                               `residue` decimal(11,2) DEFAULT '0.00' COMMENT '剩余可用额度',
                               `frozen` decimal(11,2) DEFAULT '0.00' COMMENT 'TCC事务锁定的金额',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

use `seata_server`;
CREATE TABLE `tcc_order` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `order_no` varchar(128) NOT NULL COMMENT '订单号',
                             `user_id` bigint DEFAULT NULL COMMENT '用户id',
                             `product_id` bigint DEFAULT NULL COMMENT '产品id',
                             `amount` int DEFAULT NULL COMMENT '数量',
                             `money` decimal(11,2) DEFAULT NULL COMMENT '金额',
                             `status` int DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

use `seata_server`;
CREATE TABLE `tcc_storage` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `product_id` bigint DEFAULT NULL COMMENT '产品id',
                               `residue` int DEFAULT NULL COMMENT '剩余库存',
                               `frozen` int DEFAULT '0' COMMENT 'TCC事务锁定的库存',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
commit;
/**chapter seven end**/

/**chapter eight start**/
/**没有SQL执行**/
/**chapter eight end**/

/**chapter nine start**/
/**没有SQL执行**/
/**chapter nine end**/

/**chapter ten start**/
/**没有SQL执行**/
/**chapter ten end**/

/**chapter eleven start**/
/**没有SQL执行**/
/**chapter eleven end**/

/**chapter twelve start**/
CREATE DATABASE IF NOT EXISTS `datasource_0`;
use `datasource_0`;
CREATE TABLE `t_address` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `address_name` varchar(50) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=701971395235167233 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_2` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_item` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `order_id` bigint DEFAULT NULL,
                                `user_id` bigint NOT NULL,
                                `status` int NOT NULL DEFAULT '0',
                                `good_id` bigint DEFAULT NULL,
                                `order_item_id` bigint DEFAULT NULL,
                                `is_deleted` int DEFAULT NULL,
                                `gmt_create` datetime DEFAULT NULL,
                                `gmt_modified` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9109275335699899393 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_item_0` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_item_1` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_0`;
CREATE TABLE `t_order_item_2` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `datasource_1`;
use `datasource_1`;
CREATE TABLE `t_address` (
                             `id` bigint DEFAULT NULL,
                             `address_name` varchar(50) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_2` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_item` (
                                `id` bigint DEFAULT NULL,
                                `order_id` bigint DEFAULT NULL,
                                `user_id` bigint DEFAULT NULL,
                                `status` int DEFAULT NULL,
                                `good_id` bigint DEFAULT NULL,
                                `order_item_id` bigint DEFAULT NULL,
                                `is_deleted` int DEFAULT NULL,
                                `gmt_create` datetime DEFAULT NULL,
                                `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_item_0` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_item_1` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_1`;
CREATE TABLE `t_order_item_2` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `datasource_2`;
use `datasource_2`;
CREATE TABLE `t_address` (
                             `id` bigint DEFAULT NULL,
                             `address_name` varchar(50) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_2` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_item` (
                                `id` bigint DEFAULT NULL,
                                `order_id` bigint DEFAULT NULL,
                                `user_id` bigint DEFAULT NULL,
                                `status` int DEFAULT NULL,
                                `good_id` bigint DEFAULT NULL,
                                `order_item_id` bigint DEFAULT NULL,
                                `is_deleted` int DEFAULT NULL,
                                `gmt_create` datetime DEFAULT NULL,
                                `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_item_0` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_item_1` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datasource_2`;
CREATE TABLE `t_order_item_2` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `ds`;
use `ds`;
CREATE TABLE `t_address` (
                             `id` bigint DEFAULT NULL,
                             `address_name` varchar(50) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_2` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_item` (
                                `id` bigint DEFAULT NULL,
                                `order_id` bigint DEFAULT NULL,
                                `user_id` bigint DEFAULT NULL,
                                `status` int DEFAULT NULL,
                                `good_id` bigint DEFAULT NULL,
                                `order_item_id` bigint DEFAULT NULL,
                                `is_deleted` int DEFAULT NULL,
                                `gmt_create` datetime DEFAULT NULL,
                                `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_item_0` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_item_1` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `ds`;
CREATE TABLE `t_order_item_2` (
                                  `id` bigint DEFAULT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `good_id` bigint DEFAULT NULL,
                                  `order_item_id` bigint DEFAULT NULL,
                                  `is_deleted` int DEFAULT NULL,
                                  `gmt_create` datetime DEFAULT NULL,
                                  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds`;
use `primary_ds`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `replica_ds_0`;
use `replica_ds_0`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `replica_ds_1`;
use `replica_ds_1`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds_0`;
use `primary_ds_0`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_0`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_0`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds_0_replica_0`;
use `primary_ds_0_replica_0`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_0_replica_0`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_0_replica_0`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds_0_replica_1`;
use `primary_ds_0_replica_1`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_0_replica_1`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_0_replica_1`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds_1`;
use `primary_ds_1`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_1`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_1`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds_1_replica_0`;
use `primary_ds_1_replica_0`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_1_replica_0`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_1_replica_0`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `primary_ds_1_replica_1`;
use `primary_ds_1_replica_1`;
CREATE TABLE `t_order` (
                           `id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `order_name` varchar(30) DEFAULT NULL,
                           `address_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `is_deleted` int DEFAULT NULL,
                           `gmt_create` datetime DEFAULT NULL,
                           `gmt_modified` datetime DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_1_replica_1`;
CREATE TABLE `t_order_0` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `primary_ds_1_replica_1`;
CREATE TABLE `t_order_1` (
                             `id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             `order_name` varchar(30) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             `status` int DEFAULT NULL,
                             `is_deleted` int DEFAULT NULL,
                             `gmt_create` datetime DEFAULT NULL,
                             `gmt_modified` datetime DEFAULT NULL,
                             `order_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;
/**chapter twelve end**/

/**chapter thirteen start**/
  /**NO SQL**/
/**chapter thirteen end**/

/**chapter fourteen start**/
/**NO SQL**/
/**chapter fourteen end**/

/**chapter fiveteen start**/
/**NO SQL**/
/**chapter fiveteen end**/

/**chapter sixteen start**/
/**NO SQL**/
/**chapter sixteen end**/

/**chapter seventeen start**/
CREATE DATABASE IF NOT EXISTS `datax_write`;
use `datax_write`;
CREATE TABLE `base_product_info` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                     `product_name` varchar(45) NOT NULL COMMENT '商品名称',
                                     `product_id` bigint NOT NULL COMMENT '商品ID',
                                     `brand_id` bigint NOT NULL COMMENT '品牌ID',
                                     `supplier_id` bigint NOT NULL COMMENT '供应商ID',
                                     `cate_id` bigint NOT NULL COMMENT '类目ID',
                                     `num` int NOT NULL COMMENT '库存',
                                     `shop_price` int NOT NULL COMMENT '市场商品价格',
                                     `cost_price` int NOT NULL COMMENT '成本商品价格',
                                     `status` int NOT NULL COMMENT '商品状态',
                                     `is_deleted` int NOT NULL COMMENT ' 1 表示删除，0 表示未删除',
                                     `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
                                     `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5087727 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datax_write`;
CREATE TABLE `base_user_info` (
                                  `id` int NOT NULL COMMENT '主键ID',
                                  `user_name` varchar(45) NOT NULL COMMENT '用户名称',
                                  `sex` int NOT NULL COMMENT '0-男，1-女',
                                  `age` varchar(45) NOT NULL COMMENT '用户年龄',
                                  `phone` varchar(45) NOT NULL COMMENT '用户手机号码',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datax_write`;
CREATE TABLE `product_detail` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `product_id` bigint NOT NULL COMMENT '商品ID',
                                  `product_title` varchar(100) NOT NULL COMMENT '商品标题',
                                  `product_short_title` varchar(45) NOT NULL COMMENT '商品短标题',
                                  `product_content` varchar(200) NOT NULL COMMENT '商品内容',
                                  `is_deleted` int NOT NULL COMMENT ' 1 表示删除，0 表示未删除',
                                  `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
                                  `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
                                  `product_desc` varchar(100) NOT NULL COMMENT '商品描述',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;

CREATE DATABASE IF NOT EXISTS `datax_read`;
use `datax_read`;
CREATE TABLE `base_user_info` (
                                  `id` int NOT NULL COMMENT '主键ID',
                                  `user_name` varchar(45) NOT NULL COMMENT '用户名称',
                                  `sex` int NOT NULL COMMENT '0-男，1-女',
                                  `age` varchar(45) NOT NULL COMMENT '用户年龄',
                                  `phone` varchar(45) NOT NULL COMMENT '用户手机号码',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use `datax_read`;
CREATE TABLE `product_info` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                `product_name` varchar(45) NOT NULL COMMENT '商品名称',
                                `product_id` bigint NOT NULL COMMENT '商品ID',
                                `brand_id` bigint NOT NULL COMMENT '品牌ID',
                                `supplier_id` bigint NOT NULL COMMENT '供应商ID',
                                `cate_id` bigint NOT NULL COMMENT '类目ID',
                                `num` int NOT NULL COMMENT '库存',
                                `shop_price` int NOT NULL COMMENT '市场商品价格',
                                `cost_price` int NOT NULL COMMENT '成本商品价格',
                                `status` int NOT NULL COMMENT '商品状态',
                                `product_desc` varchar(200) NOT NULL COMMENT '商品描述',
                                `product_title` varchar(100) NOT NULL COMMENT '商品标题',
                                `product_short_title` varchar(45) NOT NULL COMMENT '商品短标题',
                                `product_content` varchar(200) NOT NULL COMMENT '商品内容',
                                `is_deleted` int NOT NULL COMMENT ' 1 表示删除，0 表示未删除',
                                `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                `gmt_modified` datetime NOT NULL COMMENT '更新时间',
                                `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `un_product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10253979 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;
/**chapter seventeen end**/

/**chapter eightteen start**/

/**NO SQL **/
/**chapter eightteen end**/