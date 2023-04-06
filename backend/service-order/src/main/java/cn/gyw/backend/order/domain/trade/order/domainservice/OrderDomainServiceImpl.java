package cn.gyw.backend.order.domain.trade.order.domainservice;

import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCompleteModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCreateModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderReviseModel;

/**
 * @date 2023/4/6
 */
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public boolean orderCreate(OrderCreateModel createModel) {
        return false;
    }

    @Override
    public boolean orderRevise(OrderReviseModel reviseModel) {
        return false;
    }

    @Override
    public boolean orderComplete(OrderCompleteModel completeModel) {
        return false;
    }

    @Override
    public boolean orderCancel(Long flowNo) {
        return false;
    }
}
