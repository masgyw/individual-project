// ---Auto Generated ---
package cn.gyw.backend.order.domain.objectsku.service;

import cn.gyw.backend.order.domain.objectsku.creator.SkuAttributeCreator;
import cn.gyw.backend.order.domain.objectsku.query.SkuAttributeQuery;
import cn.gyw.backend.order.domain.objectsku.updater.SkuAttributeUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.SkuAttributeVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface SkuAttributeService {
  /**
   * create
   */
  Long createSkuAttribute(SkuAttributeCreator creator);

  /**
   * update
   */
  void updateSkuAttribute(SkuAttributeUpdater updater);

  /**
   * valid
   */
  void validSkuAttribute(Long id);

  /**
   * invalid
   */
  void invalidSkuAttribute(Long id);

  /**
   * findById
   */
  SkuAttributeVO findById(Long id);

  /**
   * findByPage
   */
  Page<SkuAttributeVO> findByPage(PageRequestWrapper<SkuAttributeQuery> query);
}
