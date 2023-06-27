// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagetemplate.service;

import cn.gyw.backend.message.domain.messagetemplate.creator.MessageTemplateCreator;
import cn.gyw.backend.message.domain.messagetemplate.query.MessageTemplateQuery;
import cn.gyw.backend.message.domain.messagetemplate.updater.MessageTemplateUpdater;
import cn.gyw.backend.message.domain.messagetemplate.vo.MessageTemplateVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface MessageTemplateService {
  /**
   * create
   */
  Long createMessageTemplate(MessageTemplateCreator creator);

  /**
   * update
   */
  void updateMessageTemplate(MessageTemplateUpdater updater);

  /**
   * valid
   */
  void validMessageTemplate(Long id);

  /**
   * invalid
   */
  void invalidMessageTemplate(Long id);

  /**
   * findById
   */
  MessageTemplateVO findById(Long id);

  /**
   * findByPage
   */
  Page<MessageTemplateVO> findByPage(PageRequestWrapper<MessageTemplateQuery> query);
}
