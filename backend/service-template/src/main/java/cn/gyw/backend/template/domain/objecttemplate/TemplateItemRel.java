package cn.gyw.backend.template.domain.objecttemplate;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @createdTime 2023/3/25 08:48
 */
@CgVo(pkgName = "cn.gyw.backend.template.domain.objecttemplate.vo")
@CgCreator(pkgName = "cn.gyw.backend.template.domain.objecttemplate.creator")
@CgUpdater(pkgName = "cn.gyw.backend.template.domain.objecttemplate.updater")
@CgRepository(pkgName = "cn.gyw.backend.template.domain.objecttemplate.repository")
@Entity
@Table(name = "template_item_rel")
@Data
public class TemplateItemRel extends BaseJpaAggregate {

    @FieldDesc(name = "模板id")
    private Long templateId;

    @FieldDesc(name = "模板项id")
    private Long itemId;

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

