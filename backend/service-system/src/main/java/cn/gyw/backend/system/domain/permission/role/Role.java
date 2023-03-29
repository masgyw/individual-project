package cn.gyw.backend.system.domain.permission.role;

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
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@CgVo(pkgName = "cn.gyw.backend.system.domain.permission.role.vo")
@CgCreator(pkgName = "cn.gyw.backend.system.domain.permission.role.creator")
@CgUpdater(pkgName = "cn.gyw.backend.system.domain.permission.role.updater")
@CgRepository(pkgName = "cn.gyw.backend.system.domain.permission.role.repository")
@CgService(pkgName = "cn.gyw.backend.system.domain.permission.role.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.system.domain.permission.role.service")
@CgQuery(pkgName = "cn.gyw.backend.system.domain.permission.role.query")
@CgMapper(pkgName = "cn.gyw.backend.system.domain.permission.role.mapper")
@CgController(pkgName = "cn.gyw.backend.system.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgResponse(pkgName = "cn.gyw.backend.system.api.response")
@Entity
@Table(name = "role")
@Data
public class Role extends BaseJpaAggregate {

    @FieldDesc(name = "角色编码")
    private String role;

    @FieldDesc(name = "角色名称")
    @QueryItem
    private String name;

    @QueryItem
    @FieldDesc(name = "平台Id")
    private Long platformId;

    @FieldDesc(name = "备注")
    private String remark;

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