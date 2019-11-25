SET FOREIGN_KEY_CHECKS=0;

-- 创建用户角色关系表

DROP TABLE IF EXISTS `SYS_USER_ROLE_REL`;
CREATE TABLE `SYS_USER_ROLE_REL` (
  `PK_ID` varchar(32) NOT NULL COMMENT '主键',
  `USER_ID` varchar(64) DEFAULT NULL COMMENT '用户id',
  `ROLE_ID` varchar(64) DEFAULT NULL COMMENT '角色id',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '状态 0 正常 1 停用',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';


SET FOREIGN_KEY_CHECKS=1;
