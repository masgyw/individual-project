package cn.gyw.backend.order.domain.trade.order.domainservice;

import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCompleteModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCreateModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderReviseModel;

/**
 * @date 2023/4/6
 */
public interface OrderDomainService {

    /**
     * 创建订单
     * @param createModel
     * @return
     */
    boolean orderCreate(OrderCreateModel createModel);

    /**
     * 订单修订
     * @param reviseModel
     * @return
     */
    boolean orderRevise(OrderReviseModel reviseModel);

    /**
     * 订单完成
     * @return
     */
    boolean orderComplete(OrderCompleteModel completeModel);

    /**
     * 订单取消
     * @return
     */
    boolean orderCancel(Long flowNo);

}
