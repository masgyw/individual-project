package cn.gyw.backend.system.domain.permission.resource;

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
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.math.BigDecimal;

@CgVo(pkgName = "cn.gyw.backend.system.domain.permission.resource.vo")
@CgCreator(pkgName = "cn.gyw.backend.system.domain.permission.resource.creator")
@CgUpdater(pkgName = "cn.gyw.backend.system.domain.permission.resource.updater")
@CgRepository(pkgName = "cn.gyw.backend.system.domain.permission.resource.repository")
@CgService(pkgName = "cn.gyw.backend.system.domain.permission.resource.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.system.domain.permission.resource.service")
@CgQuery(pkgName = "cn.gyw.backend.system.domain.permission.resource.query")
@CgMapper(pkgName = "cn.gyw.backend.system.domain.permission.resource.mapper")
@CgController(pkgName = "cn.gyw.backend.system.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.system.api.request")
@CgResponse(pkgName = "cn.gyw.backend.system.api.response")
@Entity
@Table(name = "resource")
@Data
public class Resource extends BaseJpaAggregate {

    @FieldDesc(name = "资源名称")
    @QueryItem
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @FieldDesc(name = "资源链接")
    private String url;

    @FieldDesc(name = "资源编码")
    @QueryItem
    @NotBlank(message = "资源编码不能为空")
    private String code;

    @FieldDesc(name = "前端路由")
    private String router;

    @FieldDesc(name = "父节点")
    private Long pid;

    @FieldDesc(name = "排序号")
    private BigDecimal sortNum;

    @FieldDesc(name = "icon class")
    private String iconClass;

    @Convert(converter = ResourceTypeConverter.class)
    @FieldDesc(name = "资源类型")
    private ResourceType resourceType;

    @FieldDesc(name = "资源描述")
    private String resourceDesc;

    @FieldDesc(name = "平台id")
    @QueryItem
    private Long platformId;

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
