package cn.gyw.backend.order.domain.objectsku;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.annotations.TypeConverter;
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
import java.util.List;

@CgVo(pkgName = "cn.gyw.backend.order.domain.objectsku.vo")
@CgCreator(pkgName = "cn.gyw.backend.order.domain.objectsku.creator")
@CgUpdater(pkgName = "cn.gyw.backend.order.domain.objectsku.updater")
@CgRepository(pkgName = "cn.gyw.backend.order.domain.objectsku.repository")
@CgService(pkgName = "cn.gyw.backend.order.domain.objectsku.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.order.domain.objectsku.service")
@CgQuery(pkgName = "cn.gyw.backend.order.domain.objectsku.query")
@CgMapper(pkgName = "cn.gyw.backend.order.domain.objectsku.mapper")
@CgController(pkgName = "cn.gyw.backend.order.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgResponse(pkgName = "cn.gyw.backend.order.api.response")
@Entity
@Table(name = "object_sku")
@Data
public class ObjectSku extends BaseJpaAggregate {

    @FieldDesc(name = "sku名称")
    private String skuName;

    @FieldDesc(name = "模板ID")
    private Long templateId;

    @FieldDesc(name = "编码")
    private String code;

    @FieldDesc(name = "备注")
    private String remark;

    @FieldDesc(name = "sku类型")
    @Convert(converter = SkuTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private SkuType skuType;

    /**
     * 用于开票归类，按照国家编码目录配置
     */
    @FieldDesc(name = "税务分类编码")
    private String taxCategoryNo;

    @FieldDesc(name = "计量单位")
    private String measureUnit;

    @FieldDesc(name = "子sku ID list")
    @Convert(converter = ListLongConverter.class)
    private List<Long> children;

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