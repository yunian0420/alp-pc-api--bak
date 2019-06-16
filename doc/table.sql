
# 主体表
DROP TABLE IF EXISTS `t_enterprise`;
CREATE TABLE `t_enterprise` (
   `id` int(11) NOT NULL COMMENT '主键ID',
   `user_id` int(11) DEFAULT NULL COMMENT '主体管理员用户ID',
   `enterprise_name` varchar(10) DEFAULT NULL COMMENT '主体名称',
   `principal` varchar(10) DEFAULT NULL COMMENT '主体负责人',
   `contact_way` varchar(10) DEFAULT NULL COMMENT '联系方式',
   `create_user` int(11) DEFAULT NULL,
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `update_user` int(11) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `state` tinyint(4) DEFAULT '1',
   `flag_del` tinyint(4) DEFAULT '0',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主体表';

# 门店表
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
   `id` int(11) NOT NULL COMMENT '主键ID',
   `shop_name` varchar(50) DEFAULT NULL COMMENT '门店名称',
   `contact_person` varchar(20) DEFAULT NULL COMMENT '联系人',
   `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
   `address` varchar(50) DEFAULT NULL COMMENT '详细地址',
   `province_id` int(11) DEFAULT NULL COMMENT '省份ID',
   `province_name` varchar(20) DEFAULT NULL COMMENT '省份名称',
   `city_id` int(11) DEFAULT NULL COMMENT '城市ID',
   `city_name` varchar(20) DEFAULT NULL COMMENT '城市名称',
   `area_id` int(11) DEFAULT NULL COMMENT '地区ID',
   `area_name` varchar(20) DEFAULT NULL COMMENT '地区名称',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `create_user` int(11) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` int(11) DEFAULT NULL,
   `sate` tinyint(4) DEFAULT '1',
   `flag_delete` tinyint(4) DEFAULT '0',
   `version` int(11) DEFAULT '1',
   `memo` varchar(125) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店表';

# 门店员工表
CREATE TABLE `t_shop_employee` (
   `id` int(11) NOT NULL COMMENT '主键ID',
   `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
   `employee_no` varchar(20) DEFAULT NULL COMMENT '员工编号',
   `shop_id` int(11) DEFAULT NULL COMMENT '门店ID',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `create_user` int(11) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` int(11) DEFAULT NULL,
   `sate` tinyint(4) DEFAULT '1',
   `flag_delete` tinyint(4) DEFAULT '0',
   `version` int(11) DEFAULT '1',
   `memo` varchar(125) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店员工表';

# 会员等级表
CREATE TABLE `t_member_level` (
   `id` int(11) NOT NULL COMMENT '主键ID',
   `sname` varchar(20) DEFAULT NULL COMMENT '等级名称',
   `level` varchar(20) DEFAULT NULL COMMENT '等级：1,2,3,4,5',
   `sdescription` varchar(125) DEFAULT NULL COMMENT '描述',
   `icon_url` varchar(20) DEFAULT NULL COMMENT '会员图标',
   `sort_num` int(11) DEFAULT NULL COMMENT '排序号',
   `experience_num` int(11) DEFAULT NULL COMMENT '经验值',
   `flag_special` tinyint(4) DEFAULT '1' COMMENT '会员特价：0否1是',
   `points_multiple` int(11) DEFAULT NULL COMMENT '积分倍数',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `create_user` int(11) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` int(11) DEFAULT NULL,
   `sate` tinyint(4) DEFAULT '1',
   `flag_delete` tinyint(4) DEFAULT '0',
   `version` int(11) DEFAULT '1',
   `memo` varchar(125) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';


# 会员表
CREATE TABLE `t_member` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `user_id` int(11) DEFAULT NULL COMMENT '会员ID',
   `member_no` varchar(30) DEFAULT NULL COMMENT '会员卡号',
   `shop_id` int(11) DEFAULT NULL COMMENT '发卡门店ID',
   `employee_id` int(11) DEFAULT NULL COMMENT '发卡人员ID',
   `money` decimal(11,2) DEFAULT '0.00' COMMENT '用户余额',
   `total_cost_money` decimal(11,2) DEFAULT '0.00' COMMENT '累计消费金额',
   `total_cost_times` int(11) DEFAULT '0' COMMENT '累计消费次数',
   `last_cost_time` datetime DEFAULT NULL COMMENT '最后消费时间',
   `points` int(11) DEFAULT '0' COMMENT '可用积分',
   `member_level` varchar(20) DEFAULT NULL COMMENT '会员等级',
   `consume_average` decimal(11,2) DEFAULT NULL COMMENT '消费均值',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `create_user` int(11) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` int(11) DEFAULT NULL,
   `state` tinyint(4) DEFAULT '1' COMMENT '会员状态：1启用2禁用',
   `flag_delete` tinyint(4) DEFAULT '0',
   `version` int(11) DEFAULT '1',
   `memo` varchar(125) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='会员表';

# 会员积分变动表
CREATE TABLE `t_points_change` (
   `id` int(11) NOT NULL COMMENT '主键ID',
   `user_id` int(11) DEFAULT NULL COMMENT '会员ID',
   `change_time` datetime DEFAULT NULL COMMENT '变动时间',
   `change_num` int(11) DEFAULT NULL COMMENT '变动数量',
   `reason` varchar(125) DEFAULT NULL COMMENT '变动原因',
   `change_type` tinyint(4) DEFAULT NULL COMMENT '变动类型：1收入2支出',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `create_user` int(11) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` int(11) DEFAULT NULL,
   `sate` tinyint(4) DEFAULT '1',
   `flag_delete` tinyint(4) DEFAULT '0',
   `version` int(11) DEFAULT '1',
   `memo` varchar(125) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分变动表';







