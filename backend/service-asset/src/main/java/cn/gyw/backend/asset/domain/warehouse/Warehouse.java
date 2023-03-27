package cn.gyw.backend.asset.domain.warehouse;

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

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @createdTime 2023/3/27 23:03
 */
@CgVo(pkgName = "cn.gyw.backend.asset.domain.warehouse.vo")
@CgCreator(pkgName = "cn.gyw.backend.asset.domain.warehouse.creator")
@CgUpdater(pkgName = "cn.gyw.backend.asset.domain.warehouse.updater")
@CgRepository(pkgName = "cn.gyw.backend.asset.domain.warehouse.repository")
@CgService(pkgName = "cn.gyw.backend.asset.domain.warehouse.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.asset.domain.warehouse.service")
@CgQuery(pkgName = "cn.gyw.backend.asset.domain.warehouse.query")
@CgMapper(pkgName = "cn.gyw.backend.asset.domain.warehouse.mapper")
@CgController(pkgName = "cn.gyw.backend.asset.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.asset.api.request")
@CgResponse(pkgName = "cn.gyw.backend.asset.api.response")
@Entity
@Table(name = "warehouse")
@Data
public class Warehouse extends BaseJpaAggregate {

    @FieldDesc(name = "仓库名称")
    private String name;

    @FieldDesc(name = "仓库编码")
    private String code;

    @FieldDesc(name = "创建人")
    private String createUser;

    @FieldDesc(name = "仓库地址")
    private String address;
    
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

