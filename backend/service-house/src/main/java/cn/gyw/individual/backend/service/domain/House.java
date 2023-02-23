package cn.gyw.individual.backend.service.domain;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.processor.api.CgCreateRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgQueryRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgUpdateRequest;
import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description 房屋信息
 * @createdTime 2021/9/30 15:17
 */
@CgVo(pkgName = "cn.gyw.individual.backend.service.vo")
@CgCreator(pkgName = "cn.gyw.individual.backend.service.creator")
@CgUpdater(pkgName = "cn.gyw.individual.backend.service.updater")
@CgRepository(pkgName = "cn.gyw.individual.backend.service.repository")
@CgService(pkgName = "cn.gyw.individual.backend.service.service")
@CgServiceImpl(pkgName = "cn.gyw.individual.backend.service.service")
@CgQuery(pkgName = "cn.gyw.individual.backend.service.query")
@CgMapper(pkgName = "cn.gyw.individual.backend.service.mapper")
@CgController(pkgName = "cn.gyw.individual.backend.service.controller")
@CgCreateRequest(pkgName = "cn.gyw.individual.backend.service.request")
@CgUpdateRequest(pkgName = "cn.gyw.individual.backend.service.request")
@CgQueryRequest(pkgName = "cn.gyw.individual.backend.service.request")
@Entity
@Table(name = "house_info")
@Data
public class House extends BaseJpaAggregate {

    // 爬取日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate crawlDate;
    // 省份
    @QueryItem
    private String province;
    // 城市
    @QueryItem
    private String city;
    // 小区的名字
    @QueryItem
    private String villageName;
    // 价格
    @QueryItem
    private BigDecimal price;
    // 价格单位
    private String priceUnit;
    // 几居。列表
    private String rooms;
    // 面积
    private String area;
    // 地址
    private String address;
    // 行政区
    private String district;
    // 周边价格
    private String aroundPrice;
    // 是否在售
    private String sale;
    // 详情页面url
    private String detailUrl;
    // 标签
    private String tags;
    // 开盘时间
    private String openTime;
    // 房屋类型：1->新房；2->二手房；
    private Integer houseType;
    // 用途：0->购房；1->租房
    @Column(name = "`usage`")
    private Integer usage;
    // 数据来源 1->房天下；2->58同城
    private Integer originType;
    // 源数据文件名
    private String sourceFile;
    @FieldDesc(name = "创建时间")
    private LocalDateTime createdTime;
    @FieldDesc(name = "修改时间")
    private LocalDateTime modifiedTime;

    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
