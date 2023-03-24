package cn.gyw.backend.system.domain.admin;

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
import cn.gyw.individual.plugin.codegen.processor.vo.IgnoreVo;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@CgVo(pkgName = "cn.gyw.backend.system.domain.admin.vo")
@CgCreator(pkgName = "cn.gyw.backend.system.domain.admin.creator")
@CgUpdater(pkgName = "cn.gyw.backend.system.domain.admin.updater")
@CgRepository(pkgName = "cn.gyw.backend.system.domain.admin.repository")
@CgService(pkgName = "cn.gyw.backend.system.domain.admin.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.system.domain.admin.service")
@CgQuery(pkgName = "cn.gyw.backend.system.domain.admin.query")
@CgMapper(pkgName = "cn.gyw.backend.system.domain.admin.mapper")
@CgController(pkgName = "cn.gyw.backend.system.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgResponse(pkgName = "cn.gyw.backend.system.api.response")
@Entity
@Table(name = "admin_user")
@Data
public class AdminUser extends BaseJpaAggregate {

    @FieldDesc(name = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @FieldDesc(name = "密码")
    @IgnoreVo
    @NotBlank(message = "密码不能为空")
    private String password;

    @FieldDesc(name = "用户名")
    private String username;

    @FieldDesc(name = "部门ID")
    private Long departmentId;

    @FieldDesc(name = "额外信息")
    @Column(columnDefinition = "text")
    private String extInfo;

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
