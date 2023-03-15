// ---Auto Generated ---
package cn.gyw.individual.backend.service.domain.admin.service;

import cn.gyw.individual.backend.service.domain.admin.AdminUser;
import cn.gyw.individual.backend.service.domain.admin.creator.AdminUserCreator;
import cn.gyw.individual.backend.service.domain.admin.mapper.AdminUserMapper;
import cn.gyw.individual.backend.service.domain.admin.query.AdminUserQuery;
import cn.gyw.individual.backend.service.domain.admin.repository.AdminUserRepository;
import cn.gyw.individual.backend.service.domain.admin.updater.AdminUserUpdater;
import cn.gyw.individual.backend.service.domain.admin.vo.AdminUserVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import java.lang.Long;
import java.lang.Override;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminUserServiceImpl implements IAdminUserService {
  private final AdminUserRepository adminUserRepository;

  /**
   * createImpl
   */
  @Override
  public Long createAdminUser(AdminUserCreator creator) {
    Optional<AdminUser> adminUser = EntityOperations.doCreate(adminUserRepository)
    .create(() -> AdminUserMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return adminUser.isPresent() ? adminUser.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateAdminUser(AdminUserUpdater updater) {
    EntityOperations.doUpdate(adminUserRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateAdminUser(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validAdminUser(Long id) {
    EntityOperations.doUpdate(adminUserRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidAdminUser(Long id) {
    EntityOperations.doUpdate(adminUserRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public AdminUserVO findById(Long id) {
    Optional<AdminUser> adminUser =  adminUserRepository.findById(id);
    return new AdminUserVO(adminUser.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<AdminUserVO> findByPage(PageRequestWrapper<AdminUserQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<AdminUser> example = Example.of(AdminUserMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<AdminUser> page = adminUserRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new AdminUserVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
