// ---Auto Generated ---
package cn.gyw.backend.template.domain.objecttemplate.service;

import cn.gyw.backend.template.domain.objecttemplate.creator.TemplateCategoryCreator;
import cn.gyw.backend.template.domain.objecttemplate.query.TemplateCategoryQuery;
import cn.gyw.backend.template.domain.objecttemplate.updater.TemplateCategoryUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.TemplateCategoryVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface ITemplateCategoryService {
  /**
   * create
   */
  Long createTemplateCategory(TemplateCategoryCreator creator);

  /**
   * update
   */
  void updateTemplateCategory(TemplateCategoryUpdater updater);

  /**
   * valid
   */
  void validTemplateCategory(Long id);

  /**
   * invalid
   */
  void invalidTemplateCategory(Long id);

  /**
   * findById
   */
  TemplateCategoryVO findById(Long id);

  /**
   * findByPage
   */
  Page<TemplateCategoryVO> findByPage(PageRequestWrapper<TemplateCategoryQuery> query);
}
