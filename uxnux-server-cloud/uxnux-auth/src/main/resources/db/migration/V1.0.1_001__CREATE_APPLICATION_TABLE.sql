SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client_info
-- ----------------------------
DROP TABLE IF EXISTS `client_info`;
CREATE TABLE `client_info` (
  `CLIENT_ID` varchar(32) NOT NULL COMMENT 'client_id',
  `CLIENT_SECRET` varchar(32) DEFAULT NULL COMMENT 'secret',
  `CLIENT_NAME` varchar(32) DEFAULT NULL COMMENT '应用名称',
  `SCOPE` varchar(32) DEFAULT NULL COMMENT '授权作用域',
  `GRANT_TYPES` varchar(128) DEFAULT NULL COMMENT '授权方式：\r\authorization_code -- 授权码模式 \r\password-- 密码模式 \r\refresh_token -- 刷新access_token \r\client_credentials -- 客户端模式',
  `CLIENT_TYPE` char(1) DEFAULT NULL COMMENT '客户端类型: 0:pc, 1:app',
  `TRUSTED` char(1) DEFAULT NULL COMMENT '是否信任: 0:是, 1:否',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL COMMENT '更新人',
  `STATE` char(1) default '0' COMMENT '角色状态: 0:正常, 1:锁定',
  `REMARKS` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of client_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
