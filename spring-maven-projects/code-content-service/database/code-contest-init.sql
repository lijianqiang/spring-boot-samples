#CREATE DATABASE IF NOT EXISTS op_code_content DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#use code_content;
#grant all privileges on code_content.* to 'code_content_user'@'%' identified by 'code_content_password' with grant option;
#flush privileges;

DROP TABLE IF EXISTS `tbl_foo`;
CREATE TABLE `tbl_foo` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NULL DEFAULT '',
  `age` bigint NULL DEFAULT 0,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `tbl_foo` (`name`, `age`, `created`) VALUES ('john', 28, now());

--
-- 占位码表，全局用，
--
DROP TABLE IF EXISTS `tbl_placeholder`;
CREATE TABLE `tbl_placeholder` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL DEFAULT '' comment '外部业务id',
  `placer_id` int unsigned NOT NULL DEFAULT 0 comment '场地id',
  `province_no` int unsigned NOT NULL comment '省级码',
  `city_no` int unsigned NOT NULL comment '地市级码',
  `region_no` int unsigned NOT NULL comment '县区级码',
  `longitude` varchar(16) NULL DEFAULT '' comment '经度',
  `latitude` varchar(16) NULL DEFAULT '' comment '纬度',
  `geohash` varchar(16) NULL DEFAULT '' comment '经纬度hash',
  `address` varchar(128) NOT NULL DEFAULT "工业路523号",
  `enable` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否启用',
  `type` smallint(1) unsigned NOT NULL DEFAULT 0 comment '类型，1-建筑物内，2-建筑物外，3-十字路口，4-路边',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 二维码表，全局用，
--
DROP TABLE IF EXISTS `tbl_qrcoder`;
CREATE TABLE `tbl_qrcoder` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL DEFAULT '' comment '外部业务id',
  `name` varchar(128) NULL DEFAULT '' comment '名称',
  `placer_id` int unsigned NOT NULL DEFAULT 0 comment '场地id',
  `province_no` int unsigned NOT NULL comment '省级码',
  `city_no` int unsigned NOT NULL comment '地市级码',
  `region_no` int unsigned NOT NULL comment '县区级码',
  `longitude` varchar(16) NULL DEFAULT '' comment '经度',
  `latitude` varchar(16) NULL DEFAULT '' comment '纬度',
  `geohash` varchar(16) NULL DEFAULT '' comment '经纬度hash',
  `address` varchar(128) NOT NULL DEFAULT "工业路523号",
  `enable` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否启用',
  `indoor` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否室内',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 场地,一个场地包括场地内及场地周边。类型：广场，街道，小区
--
DROP TABLE IF EXISTS `tbl_placer`;
CREATE TABLE `tbl_placer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `marker` varchar(64) NOT NULL DEFAULT '' comment '识别码',
  `name` varchar(128) NULL DEFAULT '' comment '名称',
  `intro` varchar(256) NULL DEFAULT '' comment '简介',
  `place_type` smallint unsigned NOT NULL DEFAULT 0 comment '场地类型',
  `level` smallint unsigned NOT NULL DEFAULT 0 comment '级别',
  `province_no` int unsigned NOT NULL comment '省级码',
  `city_no` int unsigned NOT NULL comment '地市级码',
  `region_no` int unsigned NOT NULL comment '县区级码',
  `province_name` varchar(32) NOT NULL DEFAULT "福建省",
  `city_name` varchar(32) NOT NULL DEFAULT "福州市",
  `region_name` varchar(32) NOT NULL DEFAULT "鼓楼区",
  `address` varchar(128) NOT NULL DEFAULT "工业路523号",
  `xlength` int unsigned NOT NULL comment '区域x轴长，东西方向，米',
  `ylength` int unsigned NOT NULL comment '区域y轴长，南北方向，米',
  `longitude` varchar(16) NULL DEFAULT '' comment '经度',
  `latitude` varchar(16) NULL DEFAULT '' comment '纬度',
  `geohash` varchar(32) NULL DEFAULT '' comment '经纬度hash',
  `rented` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否租令承包出去',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否启用',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tbl_consumer_category`;
CREATE TABLE `tbl_consumer_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `consumer_type` smallint unsigned NOT NULL DEFAULT 1 comment 'category类型',
  `name` varchar(128) NOT NULL DEFAULT '' comment '名称',
  `intro` varchar(256) NULL DEFAULT '' comment '简介',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tbl_category_item`;
CREATE TABLE `tbl_category_item` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int unsigned NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '' comment '名称',
  `intro` varchar(256) NULL DEFAULT '' comment '简介',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tbl_corporation`;
CREATE TABLE `tbl_corporation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL DEFAULT '' comment '外部业务id',
  `name` varchar(128) NOT NULL DEFAULT '' comment '名称',
  `intro` varchar(256) NULL DEFAULT '' comment '简介',
  `avatar_url` varchar(256) NULL DEFAULT '' comment '简介图片',
  `company_type` smallint unsigned NOT NULL DEFAULT 0 comment '场地类型',
  `level` smallint unsigned NOT NULL DEFAULT 0 comment '级别',
  `province_no` int unsigned NOT NULL comment '省级码',
  `city_no` int unsigned NOT NULL comment '地市级码',
  `region_no` int unsigned NOT NULL comment '县区级码',
  `province_name` varchar(32) NOT NULL DEFAULT "福建省",
  `city_name` varchar(32) NOT NULL DEFAULT "福州市",
  `region_name` varchar(32) NOT NULL DEFAULT "鼓楼区",
  `address` varchar(128) NOT NULL DEFAULT "工业路523号",
  `longitude` varchar(16) NULL DEFAULT '' comment '经度',
  `latitude` varchar(16) NULL DEFAULT '' comment '纬度',
  `geohash` varchar(32) NULL DEFAULT '' comment '经纬度hash',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否启用',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tbl_placer_registry`;
CREATE TABLE `tbl_placer_registry` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL DEFAULT '' comment '外部业务id',
  `placer_id` int unsigned NOT NULL,
  `corporation_id` int unsigned NOT NULL,
  `enable` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否启用',
  `create_at` datetime NOT NULL,
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


