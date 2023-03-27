package cn.gyw.backend.asset.domain.assetrecord;

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
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description
 * @createdTime 2023/3/27 22:58
 */
@CgVo(pkgName = "cn.gyw.backend.asset.domain.assetrecord.vo")
@CgCreator(pkgName = "cn.gyw.backend.asset.domain.assetrecord.creator")
@CgUpdater(pkgName = "cn.gyw.backend.asset.domain.assetrecord.updater")
@CgRepository(pkgName = "cn.gyw.backend.asset.domain.assetrecord.repository")
@CgService(pkgName = "cn.gyw.backend.asset.domain.assetrecord.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.asset.domain.assetrecord.service")
@CgQuery(pkgName = "cn.gyw.backend.asset.domain.assetrecord.query")
@CgMapper(pkgName = "cn.gyw.backend.asset.domain.assetrecord.mapper")
@CgController(pkgName = "cn.gyw.backend.asset.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgResponse(pkgName = "cn.gyw.backend.asset.api.response")
@Entity
@Table(name = "asset_in_out_record")
@Data
public class AssetInOutRecord extends BaseJpaAggregate {

    @FieldDesc(name = "手动录入批次号,仅用于展示")
    private String batchNo;

    @FieldDesc(name = "自动生成的批次号，防止重复")
    private String genBatchNo;

    @FieldDesc(name = "出入库业务类型")
    @Convert(converter = InOutBizTypeConverter.class)
    private InOutBizType inOutBizType;

    @FieldDesc(name = "操作人")
    private String operateUser;

    @FieldDesc(name = "出入类型")
    @Convert(converter = InOutTypeConverter.class)
    private InOutType inOutType;

    @FieldDesc(name = "总数")
    private Integer totalCount;

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
