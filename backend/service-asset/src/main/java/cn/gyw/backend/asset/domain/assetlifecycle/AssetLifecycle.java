package cn.gyw.backend.asset.domain.assetlifecycle;

import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.backend.asset.domain.assetrecord.InOutBizTypeConverter;
import cn.gyw.backend.asset.domain.assetrecord.InOutType;
import cn.gyw.backend.asset.domain.assetrecord.InOutTypeConverter;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.processor.api.CgCreateRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgQueryRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgResponse;
import cn.gyw.individual.plugin.codegen.processor.api.CgUpdateRequest;
import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @createdTime 2023/3/27 22:49
 */
@CgVo(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.vo")
@CgCreator(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.creator")
@CgUpdater(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.updater")
@CgRepository(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.repository")
@CgService(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.service")
@CgQuery(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.query")
@CgMapper(pkgName = "cn.gyw.backend.asset.domain.assetlifecycle.mapper")
@CgController(pkgName = "cn.gyw.backend.asset.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgResponse(pkgName = "cn.gyw.backend.asset.api.response")
@Entity
@Table(name = "asset_lifecycle")
@Data
public class AssetLifecycle extends BaseJpaAggregate {

    @FieldDesc(name = "资产名称")
    private String name;

    @FieldDesc(name = "资产Id")
    private Long assetsId;

    private Long skuId;

    @FieldDesc(name = "唯一编码")
    private String uniqueCode;

    @FieldDesc(name = "备注")
    @Column(columnDefinition = "varchar(500)")
    private String remark;

    @FieldDesc(name = "仓库名称")
    private String houseName;

    @FieldDesc(name = "仓库id")
    private Long houseId;

    @FieldDesc(name = "出入库业务类型")
    @Convert(converter = InOutBizTypeConverter.class)
    private InOutBizType inOutBizType;

    @FieldDesc(name = "出入类型")
    @Convert(converter = InOutTypeConverter.class)
    private InOutType inOutType;

    @FieldDesc(name = "操作人")
    private String operateUser;

    @FieldDesc(name = "唯一批次号")
    private String genBatchNo;

    @FieldDesc(name = "批次号")
    private String batchNo;

    @Convert(converter = ValidStatusConverter.class)
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

