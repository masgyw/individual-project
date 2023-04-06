package cn.gyw.backend.order.domain.trade.order.events;

import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderItemModel;
import cn.gyw.backend.order.domain.trade.orderitem.creator.OrderItemCreator;
import cn.gyw.backend.order.domain.trade.orderitem.mapper.OrderItemMapper;
import cn.gyw.backend.order.domain.trade.orderitem.service.OrderItemService;
import cn.gyw.backend.order.domain.trade.orderlifecycle.OrderOperateType;
import cn.gyw.backend.order.domain.trade.orderlifecycle.creator.OrderLifecycleCreator;
import cn.gyw.backend.order.domain.trade.orderlifecycle.service.OrderLifecycleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProcessor {

    private final OrderItemService orderItemService;

    private final OrderLifecycleService orderLifecycleService;

    @EventListener
    public void handleOrderCreateForItem(OrderEvents.OrderCreateEvent orderCreateEvent) {
        List<OrderItemModel> itemInfoList = orderCreateEvent.getCreateModel().getItemInfoList();
        itemInfoList.stream().forEach(model -> {
            OrderItemCreator creator = OrderItemMapper.INSTANCE.model2Creator(model);
            creator.setOrderId(orderCreateEvent.getOrderBase().getId());
            creator.setFlowNo(orderCreateEvent.getOrderBase().getFlowNo());
            orderItemService.createOrderItem(creator);
        });
    }

    @EventListener
    public void handleOrderCreateForLifecycle(OrderEvents.OrderCreateEvent createEvent) {
        OrderLifecycleCreator creator = new OrderLifecycleCreator();
        creator.setFlowNo(createEvent.getOrderBase().getFlowNo());
        creator.setOperateType(OrderOperateType.ORDER_CREATE);
        creator.setOperateUser(createEvent.getCreateModel().getOperateUser());
        creator.setRemark("订单创建成功");
        orderLifecycleService.createOrderLifecycle(creator);
    }
}
