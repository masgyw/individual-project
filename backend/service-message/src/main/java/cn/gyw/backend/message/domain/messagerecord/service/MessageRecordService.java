// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagerecord.service;

import cn.gyw.backend.message.domain.messagerecord.creator.MessageRecordCreator;
import cn.gyw.backend.message.domain.messagerecord.query.MessageRecordQuery;
import cn.gyw.backend.message.domain.messagerecord.updater.MessageRecordUpdater;
import cn.gyw.backend.message.domain.messagerecord.vo.MessageRecordVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface MessageRecordService {
  /**
   * create
   */
  Long createMessageRecord(MessageRecordCreator creator);

  /**
   * update
   */
  void updateMessageRecord(MessageRecordUpdater updater);

  /**
   * valid
   */
  void validMessageRecord(Long id);

  /**
   * invalid
   */
  void invalidMessageRecord(Long id);

  /**
   * findById
   */
  MessageRecordVO findById(Long id);

  /**
   * findByPage
   */
  Page<MessageRecordVO> findByPage(PageRequestWrapper<MessageRecordQuery> query);
}
