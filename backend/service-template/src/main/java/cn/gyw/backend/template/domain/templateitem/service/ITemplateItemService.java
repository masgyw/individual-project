// ---Auto Generated ---
package cn.gyw.backend.template.domain.templateitem.service;

import cn.gyw.backend.template.domain.templateitem.creator.TemplateItemCreator;
import cn.gyw.backend.template.domain.templateitem.query.TemplateItemQuery;
import cn.gyw.backend.template.domain.templateitem.updater.TemplateItemUpdater;
import cn.gyw.backend.template.domain.templateitem.vo.TemplateItemVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface ITemplateItemService {
  /**
   * create
   */
  Long createTemplateItem(TemplateItemCreator creator);

  /**
   * update
   */
  void updateTemplateItem(TemplateItemUpdater updater);

  /**
   * valid
   */
  void validTemplateItem(Long id);

  /**
   * invalid
   */
  void invalidTemplateItem(Long id);

  /**
   * findById
   */
  TemplateItemVO findById(Long id);

  /**
   * findByPage
   */
  Page<TemplateItemVO> findByPage(PageRequestWrapper<TemplateItemQuery> query);
}
