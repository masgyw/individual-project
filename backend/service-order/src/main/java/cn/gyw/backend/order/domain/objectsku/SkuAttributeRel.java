package cn.gyw.backend.order.domain.objectsku;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@CgVo(pkgName = "cn.gyw.backend.order.domain.objectsku.vo")
@CgCreator(pkgName = "cn.gyw.backend.order.domain.objectsku.creator")
@CgUpdater(pkgName = "cn.gyw.backend.order.domain.objectsku.updater")
@CgRepository(pkgName = "cn.gyw.backend.order.domain.objectsku.repository")
@Entity
@Table(name = "sku_attribute_rel")
@Data
public class SkuAttributeRel extends BaseJpaAggregate {

    private Long skuId;

    private String attributeId;

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