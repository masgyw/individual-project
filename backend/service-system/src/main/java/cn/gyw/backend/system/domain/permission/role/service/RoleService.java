// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.role.service;

import cn.gyw.backend.system.domain.permission.role.creator.RoleCreator;
import cn.gyw.backend.system.domain.permission.role.query.RoleQuery;
import cn.gyw.backend.system.domain.permission.role.updater.RoleUpdater;
import cn.gyw.backend.system.domain.permission.role.vo.RoleVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface RoleService {
  /**
   * create
   */
  Long createRole(RoleCreator creator);

  /**
   * update
   */
  void updateRole(RoleUpdater updater);

  /**
   * valid
   */
  void validRole(Long id);

  /**
   * invalid
   */
  void invalidRole(Long id);

  /**
   * findById
   */
  RoleVO findById(Long id);

  /**
   * findByPage
   */
  Page<RoleVO> findByPage(PageRequestWrapper<RoleQuery> query);
}
