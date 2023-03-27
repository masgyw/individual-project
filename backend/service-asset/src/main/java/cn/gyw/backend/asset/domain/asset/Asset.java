package cn.gyw.backend.asset.domain.asset;

import cn.gyw.backend.asset.constant.AssetErrorCode;
import cn.gyw.backend.asset.domain.asset.domainservice.model.AssetBizInfo;
import cn.gyw.backend.asset.domain.asset.events.AssetEvents;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.exceptions.BusinessException;
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

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @Description 资产
 * @createdTime 2023/3/27 22:16
 */
@CgVo(pkgName = "cn.gyw.backend.asset.domain.asset.vo")
@CgCreator(pkgName = "cn.gyw.backend.asset.domain.asset.creator")
@CgUpdater(pkgName = "cn.gyw.backend.asset.domain.asset.updater")
@CgRepository(pkgName = "cn.gyw.backend.asset.domain.asset.repository")
@CgService(pkgName = "cn.gyw.backend.asset.domain.asset.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.asset.domain.asset.service")
@CgQuery(pkgName = "cn.gyw.backend.asset.domain.asset.query")
@CgMapper(pkgName = "cn.gyw.backend.asset.domain.asset.mapper")
@CgController(pkgName = "cn.gyw.backend.asset.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgResponse(pkgName = "cn.gyw.backend.asset.api.response")
@Entity
@Table(name = "asset")
@Data
public class Asset extends BaseJpaAggregate {

    @FieldDesc(name = "仓库id")
    private Long houseId;

    @FieldDesc(name = "资产名称")
    private String name;

    @FieldDesc(name = "唯一编码")
    private String uniqueCode;

    @FieldDesc(name = "skuId")
    private Long skuId;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    /**
     * 入库
     *
     * @param bizInfo
     */
    public void in(AssetBizInfo bizInfo) {
        if (Objects.equals(ValidStatus.VALID, this.getValidStatus())) {
            throw new BusinessException(AssetErrorCode.ASSET_HAS_IN);
        }
        setValidStatus(ValidStatus.VALID);
        registerEvent(new AssetEvents.AssetInEvent(this, bizInfo));
    }

    /**
     * 出库
     *
     * @param bizInfo
     */
    public void out(AssetBizInfo bizInfo) {
        if (Objects.equals(ValidStatus.INVALID, this.getValidStatus())) {
            throw new BusinessException(AssetErrorCode.ASSET_HAS_OUT);
        }
        setValidStatus(ValidStatus.INVALID);
        registerEvent(new AssetEvents.AssetOutEvent(this, bizInfo));
    }

}

