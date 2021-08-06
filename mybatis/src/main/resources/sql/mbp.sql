
-- ----------------------------
-- mysql install
-- ----------------------------
sudo apt update
sudo apt install mysql-server -y
service mysql start
--配置mysql，并设置root 密码，此处密码为root1234
sudo mysql_secure_installation
sudo mysql -u root -p
-- 建立用户mbp,并指定密码为mbp1234
mysql> create user 'mbp'@'%' identified by 'mbp1234';
-- 赋予hive用户对所有数据库所有表的所有权限且任何地址都能建立连接“%”，并具有授予权
mysql> grant all privileges on *.* to 'mbp'@'%' with grant option;
-- flush privileges刷新MySQL的系统权限相关表
mysql> flush privileges;
-- mysql重启
sudo service mysql restart
-- mysql 关闭
service mysql stop

-- ----------------------------
-- create database
-- ----------------------------
create database mbp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- ----------------------------
-- Table structure for mbp_datasource
-- ----------------------------
DROP TABLE IF EXISTS `mbp_datasource`;
CREATE TABLE `mbp_datasource`
(
    `id`                bigint   NOT NULL AUTO_INCREMENT,
    `gmt_create`        datetime NOT NULL COMMENT '创建时间',
    `create_operator`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
    `gmt_modified`      datetime NOT NULL COMMENT '修改时间',
    `modified_operator` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
    `datasource_id`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据源ID',
    `server`            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '连接服务器(IP)',
    `port`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '端口',
    `project`           varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目名称',
    `user_name`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户',
    `password`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
    `name`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
    `type`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '数据源类型(MYSQL/KYLIN/*)',
    `remarks`           varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    `relation`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联的VIP对应的datasourceId',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `idx_datasource_id` (`datasource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='mybatis-plus-数据源表';



-- ----------------------------
-- Records of mbp_datasource
-- ----------------------------
INSERT INTO `mbp_datasource` VALUES (1,now(),'system',now(),'system','11', '172.16.0.66', '3306', 'bix1', 'bix', 'bix','bix1','MYSQL','备注1',NULL);
INSERT INTO `mbp_datasource` VALUES (2,now(),'system',now(),'system','12', '172.16.0.66', '3306', 'bix2', 'bix', 'bix','bix2','MYSQL','备注2',NULL);
INSERT INTO `mbp_datasource` VALUES (3,now(),'system',now(),'system','13', '172.16.0.66', '7070', 'bix3', 'bix', 'bix','bix3','KYLIN','备注3','vipKylin');
INSERT INTO `mbp_datasource` VALUES (4,now(),'system',now(),'system','14', '172.16.0.66', '7070', 'bix4', 'bix', 'bix','bix4','KYLIN','备注4','vipKylin');
INSERT INTO `mbp_datasource` VALUES (5,now(),'system',now(),'system','vipKylin', 'ke.study.net', '7070', 'kylin', 'kylin', 'kylin','kylin','KYLIN','vip',NULL);


-- ----------------------------
-- Table structure for mbp_idempotent
-- ----------------------------
DROP TABLE IF EXISTS `mbp_idempotent`;

CREATE TABLE `mbp_idempotent`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT,
    `gmt_create`     datetime                                                      NOT NULL COMMENT '创建时间',
    `gmt_modified`   datetime                                                      NOT NULL COMMENT '修改时间',
    `tnt_inst_id`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '租户id',
    `db_split_key`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '分库分表键',
    `idempotent_key` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '幂等健',
    `status`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
    `biz_no`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_tid_dsk_ik` (`tnt_inst_id`,`db_split_key`,`idempotent_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='mybatis-plus-幂等表';