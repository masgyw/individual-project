package cn.gyw.backend.system.domain.permission.platform;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @createdTime 2023/9/2 16:26
 */
@CgVo(pkgName = "cn.gyw.backend.system.domain.permission.platform.vo")
@CgCreator(pkgName = "cn.gyw.backend.system.domain.permission.platform.creator")
@CgUpdater(pkgName = "cn.gyw.backend.system.domain.permission.platform.updater")
@CgQuery(pkgName = "cn.gyw.backend.system.domain.permission.platform.query")
@Entity
@Table(name = "platform")
@Data
public class Platform extends BaseJpaAggregate {

    @FieldDesc(name = "编码")
    private String code;

    @FieldDesc(name = "平台名称")
    @QueryItem(desc = "平台名称")
    private String name;

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