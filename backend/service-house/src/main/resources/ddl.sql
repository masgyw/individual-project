-- backend_res.house_info definition

CREATE TABLE `house_info`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标志',
    `crawl_date`    date           DEFAULT NULL COMMENT '爬取日期',
    `province`      varchar(64)    DEFAULT NULL COMMENT '省份',
    `city`          varchar(64)    DEFAULT NULL COMMENT '城市',
    `village_name`  varchar(500)   DEFAULT NULL COMMENT '小区名称',
    `price`         decimal(12, 2) DEFAULT NULL COMMENT '价格',
    `price_unit`    varchar(16)    DEFAULT NULL COMMENT '价格单位',
    `around_price`  varchar(16)    DEFAULT NULL COMMENT '周边价格',
    `rooms`         varchar(200)   DEFAULT NULL COMMENT '几居',
    `area`          varchar(500)   DEFAULT NULL COMMENT '面积',
    `address`       varchar(500)   DEFAULT NULL COMMENT '地址',
    `district`      varchar(500)   DEFAULT NULL COMMENT '行政区',
    `sale`          varchar(4)     DEFAULT NULL COMMENT '是否在售：0-在售;1-不在售',
    `detail_url`    varchar(512)   DEFAULT NULL COMMENT '详情页面url',
    `tags`          varchar(256)   DEFAULT NULL COMMENT '标签',
    `open_time`     varchar(32)    DEFAULT NULL COMMENT '开盘日期 YYYYMMDD',
    `usage`         int(1) DEFAULT '0' COMMENT '用途：0-购房；1-租房',
    `house_source`  int(2) DEFAULT '0' COMMENT '房屋来源：0-个人；1-中介；2-开发商',
    `house_type`    int(1) DEFAULT NULL COMMENT '房屋类型：1-新房；2-二手房',
    `origin_type`   int(1) DEFAULT '0' COMMENT '数据来源：1-房天下；2-58同城',
    `source_file`   varchar(256)   DEFAULT NULL COMMENT '源数据文件名',
    `created_time`  datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_time` datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `created_at`    bigint(20) NOT NULL,
    `updated_at`    bigint(20) NOT NULL,
    `version`       int(11) DEFAULT NULL,
    `valid_status`  int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `crawl_date` (`crawl_date`,`province`,`city`,`village_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房屋信息表';