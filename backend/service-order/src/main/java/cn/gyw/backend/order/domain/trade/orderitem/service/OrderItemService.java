// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.orderitem.service;

import cn.gyw.backend.order.domain.trade.orderitem.creator.OrderItemCreator;
import cn.gyw.backend.order.domain.trade.orderitem.query.OrderItemQuery;
import cn.gyw.backend.order.domain.trade.orderitem.updater.OrderItemUpdater;
import cn.gyw.backend.order.domain.trade.orderitem.vo.OrderItemVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface OrderItemService {
  /**
   * create
   */
  Long createOrderItem(OrderItemCreator creator);

  /**
   * update
   */
  void updateOrderItem(OrderItemUpdater updater);

  /**
   * valid
   */
  void validOrderItem(Long id);

  /**
   * invalid
   */
  void invalidOrderItem(Long id);

  /**
   * findById
   */
  OrderItemVO findById(Long id);

  /**
   * findByPage
   */
  Page<OrderItemVO> findByPage(PageRequestWrapper<OrderItemQuery> query);
}
