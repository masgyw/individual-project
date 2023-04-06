package cn.gyw.backend.order.domain.trade.orderitem;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.processor.api.CgCreateRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgQueryRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgResponse;
import cn.gyw.individual.plugin.codegen.processor.api.CgUpdateRequest;
import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description
 * @createdTime 2023/4/6 22:49
 */
@CgVo(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.vo")
@CgCreator(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.creator")
@CgUpdater(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.updater")
@CgRepository(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.repository")
@CgService(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.service")
@CgQuery(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.query")
@CgMapper(pkgName = "cn.gyw.backend.order.domain.trade.orderitem.mapper")
@CgController(pkgName = "cn.gyw.backend.order.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgResponse(pkgName = "cn.gyw.backend.order.api.response")
@Entity
@Table(name = "order_item")
@Data
public class OrderItem extends BaseJpaAggregate {
    @FieldDesc(name = "订单id")
    private Long orderId;

    @FieldDesc(name = "唯一流水号")
    private Long flowNo;

    @FieldDesc(name = "真实金额")
    private BigDecimal realAmount;

    @FieldDesc(name = "计量数量")
    private BigDecimal itemCount;

    @FieldDesc(name = "skuId")
    private String skuId;

    @FieldDesc(name = "商品名称")
    private String goodsName;

    @FieldDesc(name = "费用描述")
    private String feeRemark;

}
