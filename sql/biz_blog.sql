# 新增字典表
CREATE TABLE `b_dictionary` (
  `group_code` varchar(32) NOT NULL COMMENT '组编码',
  `group_name` varchar(100) DEFAULT NULL COMMENT '组名字',
  `code` varchar(32) NOT NULL COMMENT '编码',
  `name` varchar(512) DEFAULT NULL COMMENT '名称',
  `order` int DEFAULT NULL COMMENT '排序',
  `language` varchar(8) NOT NULL COMMENT '语言',
  `status` tinyint(1) DEFAULT '1' COMMENT '0无效，1有效',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_code`,`code`,`language`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典表';