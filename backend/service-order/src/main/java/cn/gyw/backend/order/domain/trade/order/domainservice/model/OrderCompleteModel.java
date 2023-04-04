package cn.gyw.backend.order.domain.trade.order.domainservice.model;

import cn.gyw.individual.commons.pay.PayItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderCompleteModel {
    /**
     * 流水号
     */
    private Long flowNo;
    /**
     * 支付项
     */
    private List<PayItem> payItemList;
    /**
     * 支付时间戳
     */
    private Long payTime;
}
