// ---Auto Generated ---
package cn.gyw.backend.template.domain.verifyrule.service;

import cn.gyw.backend.template.domain.verifyrule.VerifyRuleCfg;
import cn.gyw.backend.template.domain.verifyrule.creator.VerifyRuleCfgCreator;
import cn.gyw.backend.template.domain.verifyrule.mapper.VerifyRuleCfgMapper;
import cn.gyw.backend.template.domain.verifyrule.query.VerifyRuleCfgQuery;
import cn.gyw.backend.template.domain.verifyrule.repository.VerifyRuleCfgRepository;
import cn.gyw.backend.template.domain.verifyrule.updater.VerifyRuleCfgUpdater;
import cn.gyw.backend.template.domain.verifyrule.vo.VerifyRuleCfgVO;
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
public class VerifyRuleCfgServiceImpl implements IVerifyRuleCfgService {
  private final VerifyRuleCfgRepository verifyRuleCfgRepository;

  /**
   * createImpl
   */
  @Override
  public Long createVerifyRuleCfg(VerifyRuleCfgCreator creator) {
    Optional<VerifyRuleCfg> verifyRuleCfg = EntityOperations.doCreate(verifyRuleCfgRepository)
    .create(() -> VerifyRuleCfgMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return verifyRuleCfg.isPresent() ? verifyRuleCfg.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateVerifyRuleCfg(VerifyRuleCfgUpdater updater) {
    EntityOperations.doUpdate(verifyRuleCfgRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateVerifyRuleCfg(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validVerifyRuleCfg(Long id) {
    EntityOperations.doUpdate(verifyRuleCfgRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidVerifyRuleCfg(Long id) {
    EntityOperations.doUpdate(verifyRuleCfgRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public VerifyRuleCfgVO findById(Long id) {
    Optional<VerifyRuleCfg> verifyRuleCfg =  verifyRuleCfgRepository.findById(id);
    return new VerifyRuleCfgVO(verifyRuleCfg.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<VerifyRuleCfgVO> findByPage(PageRequestWrapper<VerifyRuleCfgQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<VerifyRuleCfg> example = Example.of(VerifyRuleCfgMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<VerifyRuleCfg> page = verifyRuleCfgRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new VerifyRuleCfgVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
