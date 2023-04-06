package cn.gyw.backend.order.domain.trade.order.events;

import cn.gyw.backend.order.domain.trade.order.OrderBase;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCompleteModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCreateModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderReviseModel;
import lombok.Value;

public interface OrderEvents {

    /**
     * 订单创建事件
     */
    @Value
    class OrderCreateEvent {

        OrderBase orderBase;
        OrderCreateModel createModel;
    }

    /**
     * 订单完成事件
     */
    @Value
    class OrderCompleteEvent {

        OrderBase orderBase;
        OrderCompleteModel completeModel;
    }

    /**
     * 订单修订事件
     */
    @Value
    class OrderReviseEvent {

        OrderBase orderBase;
        OrderReviseModel reviseModel;
    }

    /**
     * 订单取消事件
     */
    @Value
    class OrderCancelEvent {

        OrderBase orderBase;
    }


}
