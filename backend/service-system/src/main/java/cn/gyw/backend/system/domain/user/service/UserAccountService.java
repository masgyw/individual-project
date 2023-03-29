// ---Auto Generated ---
package cn.gyw.backend.system.domain.user.service;

import cn.gyw.backend.system.domain.user.creator.UserAccountCreator;
import cn.gyw.backend.system.domain.user.query.UserAccountQuery;
import cn.gyw.backend.system.domain.user.updater.UserAccountUpdater;
import cn.gyw.backend.system.domain.user.vo.UserAccountVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface UserAccountService {
  /**
   * create
   */
  Long createUserAccount(UserAccountCreator creator);

  /**
   * update
   */
  void updateUserAccount(UserAccountUpdater updater);

  /**
   * valid
   */
  void validUserAccount(Long id);

  /**
   * invalid
   */
  void invalidUserAccount(Long id);

  /**
   * findById
   */
  UserAccountVO findById(Long id);

  /**
   * findByPage
   */
  Page<UserAccountVO> findByPage(PageRequestWrapper<UserAccountQuery> query);
}
