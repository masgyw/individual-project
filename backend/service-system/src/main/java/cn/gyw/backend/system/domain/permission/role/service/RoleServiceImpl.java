// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.role.service;

import cn.gyw.backend.system.domain.permission.role.Role;
import cn.gyw.backend.system.domain.permission.role.creator.RoleCreator;
import cn.gyw.backend.system.domain.permission.role.mapper.RoleMapper;
import cn.gyw.backend.system.domain.permission.role.query.RoleQuery;
import cn.gyw.backend.system.domain.permission.role.repository.RoleRepository;
import cn.gyw.backend.system.domain.permission.role.updater.RoleUpdater;
import cn.gyw.backend.system.domain.permission.role.vo.RoleVO;
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
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  /**
   * createImpl
   */
  @Override
  public Long createRole(RoleCreator creator) {
    Optional<Role> role = EntityOperations.doCreate(roleRepository)
    .create(() -> RoleMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return role.isPresent() ? role.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateRole(RoleUpdater updater) {
    EntityOperations.doUpdate(roleRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateRole(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validRole(Long id) {
    EntityOperations.doUpdate(roleRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidRole(Long id) {
    EntityOperations.doUpdate(roleRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public RoleVO findById(Long id) {
    Optional<Role> role =  roleRepository.findById(id);
    return new RoleVO(role.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<RoleVO> findByPage(PageRequestWrapper<RoleQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<Role> example = Example.of(RoleMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<Role> page = roleRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new RoleVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
