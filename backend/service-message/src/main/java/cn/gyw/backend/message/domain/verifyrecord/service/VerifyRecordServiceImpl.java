// ---Auto Generated ---
package cn.gyw.backend.message.domain.verifyrecord.service;

import cn.gyw.backend.message.domain.verifyrecord.VerifyRecord;
import cn.gyw.backend.message.domain.verifyrecord.creator.VerifyRecordCreator;
import cn.gyw.backend.message.domain.verifyrecord.mapper.VerifyRecordMapper;
import cn.gyw.backend.message.domain.verifyrecord.query.VerifyRecordQuery;
import cn.gyw.backend.message.domain.verifyrecord.repository.VerifyRecordRepository;
import cn.gyw.backend.message.domain.verifyrecord.updater.VerifyRecordUpdater;
import cn.gyw.backend.message.domain.verifyrecord.vo.VerifyRecordVO;
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
public class VerifyRecordServiceImpl implements VerifyRecordService {
  private final VerifyRecordRepository verifyRecordRepository;

  /**
   * createImpl
   */
  @Override
  public Long createVerifyRecord(VerifyRecordCreator creator) {
    Optional<VerifyRecord> verifyRecord = EntityOperations.doCreate(verifyRecordRepository)
    .create(() -> VerifyRecordMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return verifyRecord.isPresent() ? verifyRecord.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateVerifyRecord(VerifyRecordUpdater updater) {
    EntityOperations.doUpdate(verifyRecordRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateVerifyRecord(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validVerifyRecord(Long id) {
    EntityOperations.doUpdate(verifyRecordRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidVerifyRecord(Long id) {
    EntityOperations.doUpdate(verifyRecordRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public VerifyRecordVO findById(Long id) {
    Optional<VerifyRecord> verifyRecord =  verifyRecordRepository.findById(id);
    return new VerifyRecordVO(verifyRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<VerifyRecordVO> findByPage(PageRequestWrapper<VerifyRecordQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<VerifyRecord> example = Example.of(VerifyRecordMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<VerifyRecord> page = verifyRecordRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new VerifyRecordVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
