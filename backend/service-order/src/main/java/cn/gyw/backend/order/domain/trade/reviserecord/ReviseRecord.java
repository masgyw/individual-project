package cn.gyw.backend.order.domain.trade.reviserecord;

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

/**
 * @Description
 * @createdTime 2023/4/6 22:53
 */
@CgVo(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.vo")
@CgCreator(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.creator")
@CgUpdater(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.updater")
@CgRepository(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.repository")
@CgService(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.service")
@CgQuery(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.query")
@CgMapper(pkgName = "cn.gyw.backend.order.domain.trade.reviserecord.mapper")
@CgController(pkgName = "cn.gyw.backend.order.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgResponse(pkgName = "cn.gyw.backend.order.api.response")
@Entity
@Table(name = "revise_record")
@Data
public class ReviseRecord extends BaseJpaAggregate {

    @FieldDesc(name = "操作人")
    private String operateUser;

    @QueryItem
    @FieldDesc(name = "唯一流水")
    private Long flowNo;

    @FieldDesc(name = "修订前")
    private String reviseBefore;

    @FieldDesc(name = "修订后")
    private String reviseAfter;

    @FieldDesc(name = "差别")
    private String diff;

    @FieldDesc(name = "表名")
    @QueryItem
    private String tableName;

    @FieldDesc(name = "修订原因")
    private String reviseReason;

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
