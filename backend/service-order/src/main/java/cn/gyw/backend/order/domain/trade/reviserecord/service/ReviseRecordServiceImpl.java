// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.reviserecord.service;

import cn.gyw.backend.order.domain.trade.reviserecord.ReviseRecord;
import cn.gyw.backend.order.domain.trade.reviserecord.creator.ReviseRecordCreator;
import cn.gyw.backend.order.domain.trade.reviserecord.mapper.ReviseRecordMapper;
import cn.gyw.backend.order.domain.trade.reviserecord.query.ReviseRecordQuery;
import cn.gyw.backend.order.domain.trade.reviserecord.repository.ReviseRecordRepository;
import cn.gyw.backend.order.domain.trade.reviserecord.updater.ReviseRecordUpdater;
import cn.gyw.backend.order.domain.trade.reviserecord.vo.ReviseRecordVO;
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
public class ReviseRecordServiceImpl implements ReviseRecordService {
  private final ReviseRecordRepository reviseRecordRepository;

  /**
   * createImpl
   */
  @Override
  public Long createReviseRecord(ReviseRecordCreator creator) {
    Optional<ReviseRecord> reviseRecord = EntityOperations.doCreate(reviseRecordRepository)
    .create(() -> ReviseRecordMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return reviseRecord.isPresent() ? reviseRecord.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateReviseRecord(ReviseRecordUpdater updater) {
    EntityOperations.doUpdate(reviseRecordRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateReviseRecord(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validReviseRecord(Long id) {
    EntityOperations.doUpdate(reviseRecordRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidReviseRecord(Long id) {
    EntityOperations.doUpdate(reviseRecordRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public ReviseRecordVO findById(Long id) {
    Optional<ReviseRecord> reviseRecord =  reviseRecordRepository.findById(id);
    return new ReviseRecordVO(reviseRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<ReviseRecordVO> findByPage(PageRequestWrapper<ReviseRecordQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<ReviseRecord> example = Example.of(ReviseRecordMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<ReviseRecord> page = reviseRecordRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new ReviseRecordVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
