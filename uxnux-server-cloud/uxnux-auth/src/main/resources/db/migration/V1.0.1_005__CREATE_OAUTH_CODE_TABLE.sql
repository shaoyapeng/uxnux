SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `OAUTH_CODE_ID` varchar(32) NOT NULL COMMENT '主键',
  `OAUTH_CODE` varchar(64) DEFAULT NULL COMMENT '用户名',
  `USER_ID` varchar(64) DEFAULT NULL COMMENT '用户名',
  `USERNAME` varchar(64) DEFAULT NULL COMMENT '用户名',
  `CLIENT_ID` varchar(64) DEFAULT NULL COMMENT '用户名',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL COMMENT '更新人',
  `STATE` char(1) default '0' COMMENT '用户状态: 0:正常, 1:锁定',
  `REMARKS` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`OAUTH_CODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
