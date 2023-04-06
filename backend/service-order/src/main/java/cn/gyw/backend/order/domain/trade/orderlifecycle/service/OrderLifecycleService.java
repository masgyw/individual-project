// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.orderlifecycle.service;

import cn.gyw.backend.order.domain.trade.orderlifecycle.creator.OrderLifecycleCreator;
import cn.gyw.backend.order.domain.trade.orderlifecycle.query.OrderLifecycleQuery;
import cn.gyw.backend.order.domain.trade.orderlifecycle.updater.OrderLifecycleUpdater;
import cn.gyw.backend.order.domain.trade.orderlifecycle.vo.OrderLifecycleVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface OrderLifecycleService {
  /**
   * create
   */
  Long createOrderLifecycle(OrderLifecycleCreator creator);

  /**
   * update
   */
  void updateOrderLifecycle(OrderLifecycleUpdater updater);

  /**
   * valid
   */
  void validOrderLifecycle(Long id);

  /**
   * invalid
   */
  void invalidOrderLifecycle(Long id);

  /**
   * findById
   */
  OrderLifecycleVO findById(Long id);

  /**
   * findByPage
   */
  Page<OrderLifecycleVO> findByPage(PageRequestWrapper<OrderLifecycleQuery> query);
}
