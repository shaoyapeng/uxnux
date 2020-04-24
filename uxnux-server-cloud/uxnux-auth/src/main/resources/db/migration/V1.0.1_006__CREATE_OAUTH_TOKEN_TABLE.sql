SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_token`;
CREATE TABLE `oauth_token` (
  `OAUTH_TOKEN_ID` varchar(32) NOT NULL COMMENT '主键',
  `OAUTH_TOKEN` varchar(64) DEFAULT NULL COMMENT 'token',
  `TOKEN_EXPIRED_SECONDS` int(11) DEFAULT '-1' COMMENT 'access_token 的有效时长',
  `AUTHENTICATION_ID` varchar(64) DEFAULT NULL COMMENT '认证ID（由appid+用户名+scope，MD5加密生成，用于验证）',
  `CLIENT_ID` varchar(64) DEFAULT NULL COMMENT 'CLIENT_ID',
  `CLIENT_NAME` varchar(32) DEFAULT NULL COMMENT '应用名称',
  `REFRESH_TOKEN_EXPIRED_SECONDS` int(11) DEFAULT '-1' COMMENT 'refresh_token 的有效时长',
  `REFRESH_TOKEN` varchar(64) DEFAULT NULL COMMENT 'refresh_token',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL COMMENT '更新人',
  `STATE` char(1) default '0' COMMENT '用户状态: 0:正常, 1:锁定',
  `REMARKS` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`OAUTH_TOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of oauth_token
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
