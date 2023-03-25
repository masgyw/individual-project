package cn.gyw.backend.template.domain.selectdict;

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
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @Description
 * @createdTime 2023/3/25 08:52
 */
@CgVo(pkgName = "cn.gyw.backend.template.domain.selectdict.vo")
@CgCreator(pkgName = "cn.gyw.backend.template.domain.selectdict.creator")
@CgUpdater(pkgName = "cn.gyw.backend.template.domain.selectdict.updater")
@CgRepository(pkgName = "cn.gyw.backend.template.domain.selectdict.repository")
@CgService(pkgName = "cn.gyw.backend.template.domain.selectdict.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.template.domain.selectdict.service")
@CgQuery(pkgName = "cn.gyw.backend.template.domain.selectdict.query")
@CgMapper(pkgName = "cn.gyw.backend.template.domain.selectdict.mapper")
@CgController(pkgName = "cn.gyw.backend.template.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgResponse(pkgName = "cn.gyw.backend.template.api.response")
@Entity
@Table(name = "select_dict")
@Data
public class SelectDict extends BaseJpaAggregate {

    @FieldDesc(name = "字典编码")
    private String code;

    @FieldDesc(name = "名称")
    private String name;

    @FieldDesc(name = "描述信息")
    private String description;

    @FieldDesc(name = "字典类型")
    @Convert(converter = DictTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private DictType dictType;

    @FieldDesc(name = "http 接口地址")
    private String httpUrl;

    @FieldDesc(name = "选择值列表")
    @Convert(converter = DictValueListConverter.class)
    private List<DictValue> selectValues;

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

