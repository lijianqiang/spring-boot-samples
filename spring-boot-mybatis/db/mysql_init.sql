#CREATE DATABASE IF NOT EXISTS test_webapp DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#use test_webapp;
#grant all privileges on test_webapp.* to 'tw_user123'@'%' identified by 'tw_pwd123' with grant option;
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
-- 用户表，全局用，只提供id、unid、password、mobile、email
-- 开户简单，非注册用户密码可以为空
-- 第三方登录，手机校验码登录时也可以创建用户
--
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL DEFAULT '' comment '外部业务id',
  `password` varchar(64) NULL DEFAULT '' comment '密码',
  `is_password_null` tinyint(1) unsigned DEFAULT 0 NOT NULL comment '是否设置密码',
  `salt` varchar(12) NOT NULL comment '随机串',
  `mobile` varchar(16) NULL DEFAULT '' comment 'mobile',
  `email` varchar(64) NULL DEFAULT '' comment 'email',
  `is_thridparty` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否第三方',
  `thridparty_type` int unsigned NOT NULL DEFAULT 0 comment '第三方类型',
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 在线用户表
-- 登录后有token，并记录过期时间，是否运行多个终端同时在线写在配置文件里，业务层控制
--
DROP TABLE IF EXISTS `tbl_user_online`;
CREATE TABLE `tbl_user_online` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL comment '用户表id',
  `login_id` bigint NOT NULL comment '登陆表id',
  `token` varchar(64) NULL DEFAULT '' comment '业务token',
  `expire_at` datetime NOT NULL comment '过期时间',
  `refresh_at` datetime NOT NULL comment 'token刷新时间',
  `is_online` tinyint(1) unsigned NOT NULL DEFAULT 0 comment '是否在线（缓存有则在线）',
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 登陆历史表
-- 如果多终端模式，某个设备登出，所有设备都下线
--
DROP TABLE IF EXISTS `tbl_login_history`;
CREATE TABLE `tbl_login_history` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL comment '用户id',
  `login_ip` varchar(20) NULL DEFAULT '' comment '登录ip',
  `login_type` int unsigned NOT NULL DEFAULT 0 comment '登录方式(密码、验证码、第三方)',
  `login_device` int unsigned NOT NULL DEFAULT 0 comment '登录终端,ios,android,web(pc,mobile)',
  `device_imei` varchar(20) NULL DEFAULT '' comment '设备imei，小写',
  `device_mac` varchar(20) NULL DEFAULT '' comment '设备mac，小写',
  `http_agent` varchar(128) NULL DEFAULT '' comment '浏览器头',
  `login_at` datetime NOT NULL comment '登录时间',
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;