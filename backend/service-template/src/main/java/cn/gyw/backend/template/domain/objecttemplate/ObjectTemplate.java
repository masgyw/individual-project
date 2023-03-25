package cn.gyw.backend.template.domain.objecttemplate;

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
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @createdTime
 */
@CgVo(pkgName = "cn.gyw.backend.template.domain.objecttemplate.vo")
@CgCreator(pkgName = "cn.gyw.backend.template.domain.objecttemplate.creator")
@CgUpdater(pkgName = "cn.gyw.backend.template.domain.objecttemplate.updater")
@CgRepository(pkgName = "cn.gyw.backend.template.domain.objecttemplate.repository")
@CgService(pkgName = "cn.gyw.backend.template.domain.objecttemplate.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.template.domain.objecttemplate.service")
@CgQuery(pkgName = "cn.gyw.backend.template.domain.objecttemplate.query")
@CgMapper(pkgName = "cn.gyw.backend.template.domain.objecttemplate.mapper")
@CgController(pkgName = "cn.gyw.backend.template.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgResponse(pkgName = "cn.gyw.backend.template.api.response")
@Entity
@Table(name = "object_template")
@Data
public class ObjectTemplate extends BaseJpaAggregate {

    @FieldDesc(name = "模板名称")
    @NotEmpty(message = "模板名称不能为空")
    private String name;

    @FieldDesc(name = "模板编码")
    @NotEmpty(message = "模板编码不能为空")
    private String code;

    @FieldDesc(name = "创建人")
    private String createUser;

    @FieldDesc(name = "模板code")
    private String categoryCode;

    @FieldDesc(name = "模板id")
    private Long categoryId;

    @FieldDesc(name = "描述信息")
    private String description;

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
