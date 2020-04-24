SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `ROLE_ID` varchar(32) NOT NULL COMMENT '主键',
  `ROLE_NAME` varchar(128) DEFAULT NULL COMMENT '角色名称',
  `EN_ROLE_NAME` varchar(128) DEFAULT NULL COMMENT '英文角色名称',
  `ROLE_TYPE` char(1) default '0' COMMENT '角色类型: 0:系统角色, 1:应用角色',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL COMMENT '更新人',
  `STATE` char(1) default '0' COMMENT '角色状态: 0:正常, 1:锁定',
  `REMARKS` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of role_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
