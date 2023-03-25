// ---Auto Generated ---
package cn.gyw.backend.template.domain.objecttemplate.service;

import cn.gyw.backend.template.domain.objecttemplate.creator.ObjectTemplateCreator;
import cn.gyw.backend.template.domain.objecttemplate.query.ObjectTemplateQuery;
import cn.gyw.backend.template.domain.objecttemplate.updater.ObjectTemplateUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.ObjectTemplateVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IObjectTemplateService {
  /**
   * create
   */
  Long createObjectTemplate(ObjectTemplateCreator creator);

  /**
   * update
   */
  void updateObjectTemplate(ObjectTemplateUpdater updater);

  /**
   * valid
   */
  void validObjectTemplate(Long id);

  /**
   * invalid
   */
  void invalidObjectTemplate(Long id);

  /**
   * findById
   */
  ObjectTemplateVO findById(Long id);

  /**
   * findByPage
   */
  Page<ObjectTemplateVO> findByPage(PageRequestWrapper<ObjectTemplateQuery> query);
}
