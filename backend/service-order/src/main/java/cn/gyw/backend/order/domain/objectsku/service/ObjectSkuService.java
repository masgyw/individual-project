// ---Auto Generated ---
package cn.gyw.backend.order.domain.objectsku.service;

import cn.gyw.backend.order.domain.objectsku.creator.ObjectSkuCreator;
import cn.gyw.backend.order.domain.objectsku.query.ObjectSkuQuery;
import cn.gyw.backend.order.domain.objectsku.updater.ObjectSkuUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.ObjectSkuVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface ObjectSkuService {
  /**
   * create
   */
  Long createObjectSku(ObjectSkuCreator creator);

  /**
   * update
   */
  void updateObjectSku(ObjectSkuUpdater updater);

  /**
   * valid
   */
  void validObjectSku(Long id);

  /**
   * invalid
   */
  void invalidObjectSku(Long id);

  /**
   * findById
   */
  ObjectSkuVO findById(Long id);

  /**
   * findByPage
   */
  Page<ObjectSkuVO> findByPage(PageRequestWrapper<ObjectSkuQuery> query);
}
