// ---Auto Generated ---
package cn.gyw.backend.system.domain.admin.service;

import cn.gyw.backend.system.domain.admin.creator.AdminUserCreator;
import cn.gyw.backend.system.domain.admin.query.AdminUserQuery;
import cn.gyw.backend.system.domain.admin.updater.AdminUserUpdater;
import cn.gyw.backend.system.domain.admin.vo.AdminUserVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface IAdminUserService {
  /**
   * create
   */
  Long createAdminUser(AdminUserCreator creator);

  /**
   * update
   */
  void updateAdminUser(AdminUserUpdater updater);

  /**
   * valid
   */
  void validAdminUser(Long id);

  /**
   * invalid
   */
  void invalidAdminUser(Long id);

  /**
   * findById
   */
  AdminUserVO findById(Long id);

  /**
   * findByPage
   */
  Page<AdminUserVO> findByPage(PageRequestWrapper<AdminUserQuery> query);
}
