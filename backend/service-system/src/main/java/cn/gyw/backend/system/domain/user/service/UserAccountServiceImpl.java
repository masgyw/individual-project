// ---Auto Generated ---
package cn.gyw.backend.system.domain.user.service;

import cn.gyw.backend.system.domain.user.UserAccount;
import cn.gyw.backend.system.domain.user.creator.UserAccountCreator;
import cn.gyw.backend.system.domain.user.mapper.UserAccountMapper;
import cn.gyw.backend.system.domain.user.query.UserAccountQuery;
import cn.gyw.backend.system.domain.user.repository.UserAccountRepository;
import cn.gyw.backend.system.domain.user.updater.UserAccountUpdater;
import cn.gyw.backend.system.domain.user.vo.UserAccountVO;
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
public class UserAccountServiceImpl implements UserAccountService {
  private final UserAccountRepository userAccountRepository;

  /**
   * createImpl
   */
  @Override
  public Long createUserAccount(UserAccountCreator creator) {
    Optional<UserAccount> userAccount = EntityOperations.doCreate(userAccountRepository)
    .create(() -> UserAccountMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return userAccount.isPresent() ? userAccount.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateUserAccount(UserAccountUpdater updater) {
    EntityOperations.doUpdate(userAccountRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateUserAccount(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validUserAccount(Long id) {
    EntityOperations.doUpdate(userAccountRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidUserAccount(Long id) {
    EntityOperations.doUpdate(userAccountRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public UserAccountVO findById(Long id) {
    Optional<UserAccount> userAccount =  userAccountRepository.findById(id);
    return new UserAccountVO(userAccount.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<UserAccountVO> findByPage(PageRequestWrapper<UserAccountQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<UserAccount> example = Example.of(UserAccountMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<UserAccount> page = userAccountRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new UserAccountVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
