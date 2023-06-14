// ---Auto Generated ---
package cn.gyw.backend.message.domain.smssendrecord.service;

import cn.gyw.backend.message.domain.smssendrecord.creator.SmsSendRecordCreator;
import cn.gyw.backend.message.domain.smssendrecord.query.SmsSendRecordQuery;
import cn.gyw.backend.message.domain.smssendrecord.updater.SmsSendRecordUpdater;
import cn.gyw.backend.message.domain.smssendrecord.vo.SmsSendRecordVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface SmsSendRecordService {
  /**
   * create
   */
  Long createSmsSendRecord(SmsSendRecordCreator creator);

  /**
   * update
   */
  void updateSmsSendRecord(SmsSendRecordUpdater updater);

  /**
   * valid
   */
  void validSmsSendRecord(Long id);

  /**
   * invalid
   */
  void invalidSmsSendRecord(Long id);

  /**
   * findById
   */
  SmsSendRecordVO findById(Long id);

  /**
   * findByPage
   */
  Page<SmsSendRecordVO> findByPage(PageRequestWrapper<SmsSendRecordQuery> query);
}
