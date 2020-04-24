SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource_info
-- ----------------------------
DROP TABLE IF EXISTS `resource_info`;
CREATE TABLE `resource_info` (
  `RESOURCE_ID` varchar(32) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(32) NOT NULL COMMENT '父资源主键',
  `PARENT_IDS` varchar(1024) NOT NULL COMMENT '所有父级编号',
  `RESOURCE_NAME` varchar(128) NOT NULL COMMENT '名称',
  `RESOURCE_HREF` varchar(256) DEFAULT NULL COMMENT '链接',
  `PERMISSION` varchar(128) DEFAULT NULL COMMENT '权限标识',
  `RESOURCE_SORT` decimal(10,0) NOT NULL COMMENT '排序',
  `RESOURCE_ICON` varchar(80) DEFAULT NULL COMMENT '图标',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL COMMENT '更新人',
  `STATE` char(1) default '0' COMMENT '角色状态: 0:正常, 1:锁定',
  `REMARKS` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of resource_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
