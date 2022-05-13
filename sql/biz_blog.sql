-- 数据变更记录表
CREATE TABLE `b_data_change_log`
(
    `id`          bigint        NOT NULL COMMENT '主键',
    `change_type` bigint        NOT NULL COMMENT '数据变更记录类型. 0-基本数据变更, 1-状态数据变更',
    `module_code` varchar(64)   NOT NULL COMMENT '业务模块编号',
    `business_id` bigint        NOT NULL COMMENT '业务数据主键id',
    `create_id`   bigint        NOT NULL COMMENT '记录创建人id',
    `create_time` datetime      NOT NULL COMMENT '记录创建时间',
    `data_diff`   varchar(1024) NOT NULL COMMENT '数据变动内容',
    `reason`      varchar(512) DEFAULT NULL COMMENT '操作理由',
    `remark`      varchar(512) DEFAULT NULL COMMENT '操作备注',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `tsp_data_change_log_change_type` (`change_type`) USING BTREE,
    KEY           `tsp_data_change_log_module_code` (`module_code`) USING BTREE,
    KEY           `tsp_data_change_log_business_id` (`business_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='数据变更记录';

-- 字典表
CREATE TABLE `b_dictionary`
(
    `group_code` varchar(32) NOT NULL COMMENT '组编码',
    `group_name` varchar(100) DEFAULT NULL COMMENT '组名字',
    `code`       varchar(32) NOT NULL COMMENT '编码',
    `name`       varchar(512) DEFAULT NULL COMMENT '名称',
    `order`      int          DEFAULT NULL COMMENT '排序',
    `language`   varchar(8)  NOT NULL COMMENT '语言',
    `status`     tinyint(1) DEFAULT '1' COMMENT '0无效，1有效',
    `remark`     varchar(256) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`group_code`, `code`, `language`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典表';

--  点赞表
CREATE TABLE `b_like`
(
    `id`             bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `module_code`    tinyint      DEFAULT NULL COMMENT '业务模块编号: 0-博客，1-评论，2-留言，3-图片',
    `business_id`    bigint NOT NULL COMMENT '业务数据主键id',
    `user_id`        bigint NOT NULL COMMENT '操作用户id',
    `operation_type` tinyint(1) DEFAULT NULL COMMENT '操作类型: 0-点赞，1-点踩',
    `remark`         varchar(512) DEFAULT NULL COMMENT '操作备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='点赞表';

-- 角色表
CREATE TABLE `b_role`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`        varchar(64)  DEFAULT NULL COMMENT '角色名',
    `status`      tinyint(1) NOT NULL DEFAULT '1' COMMENT '0无效，1有效',
    `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
    `create_id`   bigint   NOT NULL COMMENT '创建人主键',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_id`   bigint       DEFAULT NULL COMMENT '更新人主键',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- 用户信息表
CREATE TABLE `b_user`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`            varchar(128) DEFAULT NULL COMMENT '用户名',
    `nickname`        varchar(128) DEFAULT NULL COMMENT '用户昵称',
    `mobile`          varchar(32)  NOT NULL COMMENT '手机号',
    `mail`            varchar(255) DEFAULT NULL COMMENT '邮箱',
    `avatar`          varchar(255) DEFAULT NULL COMMENT '头像图片',
    `birthday`        datetime     DEFAULT NULL COMMENT '生日',
    `gender`          tinyint(1) DEFAULT NULL COMMENT '性别: 0-男，1-女',
    `password`        varchar(255) NOT NULL COMMENT '密文密码',
    `user_type`       tinyint      DEFAULT '0' COMMENT '用户分类: 0-游客，1-注册用户，2-管理严',
    `create_time`     datetime     DEFAULT NULL COMMENT '注册时间',
    `create_ip`       varchar(30)  DEFAULT NULL COMMENT '注册IP',
    `last_login_ip`   varchar(30)  DEFAULT NULL COMMENT '最近登录IP',
    `last_login_time` datetime     DEFAULT NULL COMMENT '最近登录时间',
    `login_count`     bigint       DEFAULT '0' COMMENT '登录次数',
    `update_time`     datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`          tinyint(1) DEFAULT '1' COMMENT '用户状态: 0无效，1有效',
    `delete_flag`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    `language_code`   varchar(8)   DEFAULT 'zh_CN' COMMENT '语言编号',
    `remark`          varchar(256) DEFAULT NULL COMMENT '用户备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

-- 用户操作日志表
CREATE TABLE `b_user_action_log`
(
    `id`              bigint NOT NULL COMMENT '主键',
    `url`             varchar(255)  DEFAULT NULL COMMENT '请求链接',
    `action_name`     varchar(255)  DEFAULT NULL COMMENT '操作名',
    `params`          varchar(1024) DEFAULT NULL COMMENT '提交内容（json）',
    `user_account_id` bigint        DEFAULT NULL COMMENT '操作人',
    `create_time`     datetime      DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户操作日志表';