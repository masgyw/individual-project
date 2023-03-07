package cn.gyw.individual.backend.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@FieldNameConstants
public class HouseInfoDto {

    private Long id;
    // 爬取日期
    private String crawlDate;
    // 省份
    private String province;
    // 城市
    private String city;
    // 小区的名字
    private String villageName;
    // 价格
    private String price;
    // 周边价格
    private String aroundPrice;
    // 几居。列表
    private String rooms;
    // 面积
    private String area;
    // 地址
    private String address;
    // 行政区
    private String district;
    // 是否在售
    private String sale;
    // 详情页面url
    private String detailUrl;
    // 标签
    private String tags;
    // 开盘时间
    private String openTime;
    // 房屋类型：1->新房；2->二手房；
    private String houseType;
    // 用途：0->购房；1->租房
    private String usage;
    // 数据来源
    private String originType;
    // 源数据文件名
    private String sourceFile;
    // 创建时间
    private LocalDateTime createdTime;
    // 维度：年|月|日
    private String dim;
}
