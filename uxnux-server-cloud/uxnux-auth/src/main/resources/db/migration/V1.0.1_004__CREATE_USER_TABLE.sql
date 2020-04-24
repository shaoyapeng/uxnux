SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `USER_ID` varchar(32) NOT NULL COMMENT '主键',
  `USER_NAME` varchar(128) DEFAULT NULL COMMENT '用户名',
  `LOGIN_NAME` varchar(64) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `USER_SEX` char(1) DEFAULT NULL COMMENT '用户性别: 0:男, 1:女',
  `USER_AGE` varchar(3) DEFAULT NULL COMMENT '用户年龄',
  `USER_PHONE` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `USER_EMAIL` varchar(11) DEFAULT NULL COMMENT '用户邮箱 ',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL COMMENT '更新人',
  `STATE` char(1) default '0' COMMENT '用户状态: 0:正常, 1:锁定',
  `REMARKS` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY (`LOGIN_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
