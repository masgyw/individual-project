// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.order.service;

import cn.gyw.backend.order.domain.trade.order.creator.OrderBaseCreator;
import cn.gyw.backend.order.domain.trade.order.query.OrderBaseQuery;
import cn.gyw.backend.order.domain.trade.order.updater.OrderBaseUpdater;
import cn.gyw.backend.order.domain.trade.order.vo.OrderBaseVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface OrderBaseService {
  /**
   * create
   */
  Long createOrderBase(OrderBaseCreator creator);

  /**
   * update
   */
  void updateOrderBase(OrderBaseUpdater updater);

  /**
   * valid
   */
  void validOrderBase(Long id);

  /**
   * invalid
   */
  void invalidOrderBase(Long id);

  /**
   * findById
   */
  OrderBaseVO findById(Long id);

  /**
   * findByPage
   */
  Page<OrderBaseVO> findByPage(PageRequestWrapper<OrderBaseQuery> query);
}
