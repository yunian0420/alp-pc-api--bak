# 场景任务表
# 用于记录平台（默认抖音）场景采集子任务：关联业务ID biz_id、场景 scene_code，并保存接口返回的 job_id
DROP TABLE IF EXISTS `t_scene_job`;
CREATE TABLE `t_scene_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_id` varchar(64) DEFAULT NULL COMMENT '接口返回的任务ID',
  `scene_code` varchar(64) NOT NULL COMMENT '场景编码，如蓝词 blue_word',
  `biz_id` varchar(64) NOT NULL COMMENT '业务ID',
  `platform` varchar(32) NOT NULL DEFAULT 'douyin' COMMENT '平台，默认抖音 douyin',
  `type` varchar(16) NOT NULL COMMENT '任务类型：url-链接，keyword-关键词',
  `url` varchar(512) DEFAULT NULL COMMENT '链接地址，蓝词等场景且 type=url 时记录',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词，type=keyword 时记录',
  `status` varchar(32) NOT NULL DEFAULT 'submitted' COMMENT '任务状态：submitted-提交，running-运行，timeout-超时，success-成功，fail-失败',
  `browser_runtime` varchar(32) NOT NULL DEFAULT 'scrapling' COMMENT '浏览器运行时：scrapling、playwright',
  `auth` varchar(256) NOT NULL DEFAULT 'Bearer G4r8HrQFIFEphVDwxKjU9Z0E_m_sA3MY-F3gSwsZzWw' COMMENT '鉴权信息',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识：0未删除 1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，随记录变更自动更新',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_job_id` (`job_id`),
  KEY `idx_biz_id` (`biz_id`),
  KEY `idx_scene_code` (`scene_code`),
  KEY `idx_status` (`status`),
  KEY `idx_biz_scene` (`biz_id`, `scene_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='场景任务表';
