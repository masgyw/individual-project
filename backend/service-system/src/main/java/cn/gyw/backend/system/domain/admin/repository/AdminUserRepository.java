// ---Auto Generated ---
package cn.gyw.backend.system.domain.admin.repository;

import cn.gyw.backend.system.domain.admin.AdminUser;
import cn.gyw.individual.starters.jpa.support.BaseRepository;
import java.lang.Long;
import java.util.Optional;

public interface AdminUserRepository extends BaseRepository<AdminUser, Long> {

    Optional<AdminUser> findByPhone(String phone);
}
