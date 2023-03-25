package cn.gyw.backend.template.domain.verifyrule;

import cn.gyw.backend.template.domain.verifyrule.rule.VerifyRule;
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
import java.util.List;

/**
 * @Description
 * @createdTime 2023/3/25 09:27
 */
@CgVo(pkgName = "cn.gyw.backend.template.domain.verifyrule.vo")
@CgCreator(pkgName = "cn.gyw.backend.template.domain.verifyrule.creator")
@CgUpdater(pkgName = "cn.gyw.backend.template.domain.verifyrule.updater")
@CgRepository(pkgName = "cn.gyw.backend.template.domain.verifyrule.repository")
@CgService(pkgName = "cn.gyw.backend.template.domain.verifyrule.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.template.domain.verifyrule.service")
@CgQuery(pkgName = "cn.gyw.backend.template.domain.verifyrule.query")
@CgMapper(pkgName = "cn.gyw.backend.template.domain.verifyrule.mapper")
@CgController(pkgName = "cn.gyw.backend.template.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.template.api.request")
@CgResponse(pkgName = "cn.gyw.backend.template.api.response")
@Entity
@Table(name = "cfg_verify_rule")
@Data
public class VerifyRuleCfg extends BaseJpaAggregate {

    @FieldDesc(name = "描述信息")
    private String descInfo;

    @FieldDesc(name = "规则名称")
    private String name;

    @FieldDesc(name = "模板项ID")
    private Long itemId;

    @FieldDesc(name = "校验规则列表")
    @Convert(converter = VerifyRuleListConverter.class)
    @Column(columnDefinition = "text")
    private List<VerifyRule> ruleList;

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
