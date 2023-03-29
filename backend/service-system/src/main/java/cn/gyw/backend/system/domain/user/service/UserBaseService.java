// ---Auto Generated ---
package cn.gyw.backend.system.domain.user.service;

import cn.gyw.backend.system.domain.user.creator.UserBaseCreator;
import cn.gyw.backend.system.domain.user.query.UserBaseQuery;
import cn.gyw.backend.system.domain.user.updater.UserBaseUpdater;
import cn.gyw.backend.system.domain.user.vo.UserBaseVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface UserBaseService {
  /**
   * create
   */
  Long createUserBase(UserBaseCreator creator);

  /**
   * update
   */
  void updateUserBase(UserBaseUpdater updater);

  /**
   * valid
   */
  void validUserBase(Long id);

  /**
   * invalid
   */
  void invalidUserBase(Long id);

  /**
   * findById
   */
  UserBaseVO findById(Long id);

  /**
   * findByPage
   */
  Page<UserBaseVO> findByPage(PageRequestWrapper<UserBaseQuery> query);
}
