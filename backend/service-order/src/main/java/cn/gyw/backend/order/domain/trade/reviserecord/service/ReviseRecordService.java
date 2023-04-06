// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.reviserecord.service;

import cn.gyw.backend.order.domain.trade.reviserecord.creator.ReviseRecordCreator;
import cn.gyw.backend.order.domain.trade.reviserecord.query.ReviseRecordQuery;
import cn.gyw.backend.order.domain.trade.reviserecord.updater.ReviseRecordUpdater;
import cn.gyw.backend.order.domain.trade.reviserecord.vo.ReviseRecordVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface ReviseRecordService {
  /**
   * create
   */
  Long createReviseRecord(ReviseRecordCreator creator);

  /**
   * update
   */
  void updateReviseRecord(ReviseRecordUpdater updater);

  /**
   * valid
   */
  void validReviseRecord(Long id);

  /**
   * invalid
   */
  void invalidReviseRecord(Long id);

  /**
   * findById
   */
  ReviseRecordVO findById(Long id);

  /**
   * findByPage
   */
  Page<ReviseRecordVO> findByPage(PageRequestWrapper<ReviseRecordQuery> query);
}
