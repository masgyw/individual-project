package cn.gyw.backend.order.domain.trade.order.domainservice.model;

import cn.gyw.backend.order.domain.trade.order.OrderType;
import cn.gyw.individual.commons.enums.AccountType;
import cn.gyw.individual.commons.model.DictValue;
import cn.gyw.individual.commons.pay.PayItem;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Data;

import java.util.List;

/**
 * 订单创建模型
 */
@Data
public class OrderCreateModel {

    @FieldDesc(name = "账号Id")
    private Long accountId;

    @FieldDesc(name = "账号类型")
    private AccountType accountType;

    @FieldDesc(name = "订单类型、订单类型创建不同的状态机")
    private OrderType orderType;

    @FieldDesc(name = "支付详情")
    private List<PayItem> payList;

    @FieldDesc(name = "订单属性信息")
    private List<DictValue> attrs;

    @FieldDesc(name = "订单项列表")
    private List<OrderItemModel> itemInfoList;

    @FieldDesc(name = "操作人")
    private String operateUser;
}
