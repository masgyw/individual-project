package cn.gyw.backend.order.domain.trade.order;

import cn.gyw.backend.order.constant.OrderErrorCode;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCompleteModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCreateModel;
import cn.gyw.backend.order.domain.trade.order.events.OrderEvents;
import cn.gyw.individual.commons.converter.AccountTypeConverter;
import cn.gyw.individual.commons.converter.DictValueListConverter;
import cn.gyw.individual.commons.converter.PayItemListConverter;
import cn.gyw.individual.commons.enums.AccountType;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.DictValue;
import cn.gyw.individual.commons.pay.PayItem;
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
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@CgVo(pkgName = "cn.gyw.backend.order.domain.trade.order.vo")
@CgCreator(pkgName = "cn.gyw.backend.order.domain.trade.order.creator")
@CgUpdater(pkgName = "cn.gyw.backend.order.domain.trade.order.updater")
@CgRepository(pkgName = "cn.gyw.backend.order.domain.trade.order.repository")
@CgService(pkgName = "cn.gyw.backend.order.domain.trade.order.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.order.domain.trade.order.service")
@CgQuery(pkgName = "cn.gyw.backend.order.domain.trade.order.query")
@CgMapper(pkgName = "cn.gyw.backend.order.domain.trade.order.mapper")
@CgController(pkgName = "cn.gyw.backend.order.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.order.api.request")
@CgResponse(pkgName = "cn.gyw.backend.order.api.response")
@Entity
@Table(name = "order_base")
@Data
public class OrderBase extends BaseJpaAggregate {

    @FieldDesc(name = "唯一流水号")
    @IgnoreUpdater
    private Long flowNo;

    @NotNull(message = "订单金额不能为空")
    @FieldDesc(name = "订单金额")
    private BigDecimal totalAmount;

    @FieldDesc(name = "账号Id")
    private Long accountId;

    @FieldDesc(name = "账号类型")
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;

    @FieldDesc(name = "订单类型、订单类型创建不同的状态机")
    @Convert(converter = OrderTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    @IgnoreUpdater
    @QueryItem
    private OrderType orderType;

    @FieldDesc(name = "支付详情")
    @IgnoreCreator
    @IgnoreUpdater
    @Convert(converter = PayItemListConverter.class)
    @Column(columnDefinition = "text")
    private List<PayItem> payList;

    @FieldDesc(name = "待支付金额")
    private BigDecimal waitPay;

    @IgnoreCreator
    @IgnoreUpdater
    @FieldDesc(name = "支付时间")
    private Long payTime;

    @FieldDesc(name = "订单状态")
    @Convert(converter = OrderStateConverter.class)
    @IgnoreCreator
    @IgnoreUpdater
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private OrderState orderState;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    /**
     * 系统压力不大的时候可以这里放，压力大千万不要放这里，额外的表或者es都可以
     */
    @FieldDesc(name = "订单信息")
    @Convert(converter = DictValueListConverter.class)
    private List<DictValue> attrs;

    @FieldDesc(name = "是否开票")
    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus invoiceFlag;

    /**
     * 订单初始化
     */
    public void init(OrderCreateModel createModel) {
        setValidStatus(ValidStatus.VALID);
        setInvoiceFlag(ValidStatus.INVALID);
        BigDecimal total = getTotalAmount();
        if (CollectionUtils.isNotEmpty(createModel.getPayList())) {
            setPayList(createModel.getPayList());
            BigDecimal hasPay = payList.stream().map(p -> p.getMoney())
                    .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
            if (hasPay.compareTo(total) > 0) {
                throw new BusinessException(OrderErrorCode.PAY_TOO_BIG);
            }
            if (hasPay.compareTo(total) == 0) {
                setOrderState(OrderState.PAY_SUCCESS);
                setWaitPay(BigDecimal.ZERO);
            } else {
                setOrderState(OrderState.WAIT_PAY);
                setWaitPay(total.subtract(hasPay));
            }
        } else {
            setPayList(Lists.newArrayList());
            if (BigDecimal.ZERO.compareTo(total) == 0) {
                setOrderState(OrderState.PAY_SUCCESS);
                setWaitPay(BigDecimal.ZERO);
            } else {
                setWaitPay(total);
                setOrderState(OrderState.WAIT_PAY);
            }
        }
        registerEvent(new OrderEvents.OrderCreateEvent(this, createModel));
    }

    /**
     * 订单完成
     */
    public void complete(OrderCompleteModel completeModel) {
        if (!Objects.equals(OrderState.WAIT_PAY, getOrderState())) {
            throw new BusinessException(OrderErrorCode.ORDER_NOT_WAIT_PAY);
        }
        if (CollectionUtils.isEmpty(completeModel.getPayItemList())) {
            throw new BusinessException(OrderErrorCode.PAY_LIST_IS_NULL);
        }
        BigDecimal hasPay = completeModel.getPayItemList().stream().map(PayItem::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!(hasPay.compareTo(getWaitPay()) == 0)) {
            throw new BusinessException(OrderErrorCode.PAY_AMOUNT_NOT_EQUAL_WAIT_PAY);
        }
        setPayTime(completeModel.getPayTime());
        List<PayItem> payItemList = getPayList();
        payItemList.addAll(completeModel.getPayItemList());
        setPayList(payItemList);
        setOrderState(OrderState.PAY_SUCCESS);
    }

    /**
     * 取消订单
     */
    public void cancel() {
        if (!Objects.equals(OrderState.WAIT_PAY, getOrderState())) {
            throw new BusinessException(OrderErrorCode.ORDER_NOT_WAIT_PAY);
        }
        setOrderState(OrderState.CANCEL);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
