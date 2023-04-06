package cn.gyw.backend.order.domain.trade.orderlifecycle;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.annotations.TypeConverter;
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
 * @createdTime 2023/4/6 22:51
 */
@CgVo(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.vo")
@CgCreator(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.creator")
@CgUpdater(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.updater")
@CgRepository(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.repository")
@CgService(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.service")
@CgQuery(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.query")
@CgMapper(pkgName = "cn.gyw.backend.order.domain.trade.orderlifecycle.mapper")
@CgController(pkgName = "cn.gyw.backend.order.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgResponse(pkgName = "cn.gyw.backend.order.api.response")
@Entity
@Table(name = "order_lifecycle")
@Data
public class OrderLifecycle extends BaseJpaAggregate {

    private Long flowNo;

    @FieldDesc(name = "操作类型")
    @Convert(converter = OrderOperateTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private OrderOperateType operateType;

    private String remark;

    private String operateUser;

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
