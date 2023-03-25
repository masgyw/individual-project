package cn.gyw.backend.template.domain.templateitem;

import cn.gyw.individual.commons.converter.DictValueListConverter;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.DictValue;
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
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
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
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @createdTime 2023/3/25 09:22
 */
@CgVo(pkgName = "cn.gyw.backend.template.domain.templateitem.vo")
@CgCreator(pkgName = "cn.gyw.backend.template.domain.templateitem.creator")
@CgUpdater(pkgName = "cn.gyw.backend.template.domain.templateitem.updater")
@CgRepository(pkgName = "cn.gyw.backend.template.domain.templateitem.repository")
@CgService(pkgName = "cn.gyw.backend.template.domain.templateitem.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.template.domain.templateitem.service")
@CgQuery(pkgName = "cn.gyw.backend.template.domain.templateitem.query")
@CgMapper(pkgName = "cn.gyw.backend.template.domain.templateitem.mapper")
@CgController(pkgName = "cn.gyw.backend.template.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgResponse(pkgName = "cn.gyw.backend.template.api.response")
@Entity
@Table(name = "template_item")
@Data
public class TemplateItem extends BaseJpaAggregate {

    @QueryItem
    @NotEmpty(message = "模板项名称不能为空")
    @FieldDesc(name = "名称")
    private String name;

    @FieldDesc(name = "输入类型")
    @Convert(converter = InputTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private InputType inputType;

    @FieldDesc(name = "占位符")
    private String placeholder;

    @FieldDesc(name = "编码")
    private String code;

    @FieldDesc(name = "创建人")
    private String createUser;

    @FieldDesc(name = "排序号")
    private BigDecimal sortNum;

    @FieldDesc(name = "备注")
    private String remark;

    @FieldDesc(name = "是否必填")
    @Convert(converter = ValidStatusConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private ValidStatus requireFlag;

    @Convert(converter = ValidStatusConverter.class)
    @FieldDesc(name = "是否需要审批")
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private ValidStatus auditFlag;

    @FieldDesc(name = "关联字典编码")
    private String selectCode;

    @FieldDesc(name = "关联字典的id")
    private Long dictId;

    @FieldDesc(name = "开发扩展字段")
    @Convert(converter = DictValueListConverter.class)
    @Column(columnDefinition = "text")
    private List<DictValue> extList;

    private Long categoryId;

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

