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

/**
 * @Description
 * @createdTime 2023/3/25 08:45
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
@Table(name = "template_category")
@Data
public class TemplateCategory extends BaseJpaAggregate {

    @FieldDesc(name = "分类名称")
    private String name;

    /**
     * 分类编码约定规律，这时业务系统好使用
     */
    @FieldDesc(name = "分类编码")
    private String code;

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
