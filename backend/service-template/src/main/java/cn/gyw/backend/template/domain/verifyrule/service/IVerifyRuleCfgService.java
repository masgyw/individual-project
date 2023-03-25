// ---Auto Generated ---
package cn.gyw.backend.template.domain.verifyrule.service;

import cn.gyw.backend.template.domain.verifyrule.creator.VerifyRuleCfgCreator;
import cn.gyw.backend.template.domain.verifyrule.query.VerifyRuleCfgQuery;
import cn.gyw.backend.template.domain.verifyrule.updater.VerifyRuleCfgUpdater;
import cn.gyw.backend.template.domain.verifyrule.vo.VerifyRuleCfgVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IVerifyRuleCfgService {
  /**
   * create
   */
  Long createVerifyRuleCfg(VerifyRuleCfgCreator creator);

  /**
   * update
   */
  void updateVerifyRuleCfg(VerifyRuleCfgUpdater updater);

  /**
   * valid
   */
  void validVerifyRuleCfg(Long id);

  /**
   * invalid
   */
  void invalidVerifyRuleCfg(Long id);

  /**
   * findById
   */
  VerifyRuleCfgVO findById(Long id);

  /**
   * findByPage
   */
  Page<VerifyRuleCfgVO> findByPage(PageRequestWrapper<VerifyRuleCfgQuery> query);
}
