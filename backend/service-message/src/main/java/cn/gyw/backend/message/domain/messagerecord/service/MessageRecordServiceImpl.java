// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagerecord.service;

import cn.gyw.backend.message.domain.messagerecord.MessageRecord;
import cn.gyw.backend.message.domain.messagerecord.creator.MessageRecordCreator;
import cn.gyw.backend.message.domain.messagerecord.mapper.MessageRecordMapper;
import cn.gyw.backend.message.domain.messagerecord.query.MessageRecordQuery;
import cn.gyw.backend.message.domain.messagerecord.repository.MessageRecordRepository;
import cn.gyw.backend.message.domain.messagerecord.updater.MessageRecordUpdater;
import cn.gyw.backend.message.domain.messagerecord.vo.MessageRecordVO;
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
public class MessageRecordServiceImpl implements MessageRecordService {
  private final MessageRecordRepository messageRecordRepository;

  /**
   * createImpl
   */
  @Override
  public Long createMessageRecord(MessageRecordCreator creator) {
    Optional<MessageRecord> messageRecord = EntityOperations.doCreate(messageRecordRepository)
    .create(() -> MessageRecordMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init("ss"))
    .execute();
    return messageRecord.isPresent() ? messageRecord.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateMessageRecord(MessageRecordUpdater updater) {
    EntityOperations.doUpdate(messageRecordRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateMessageRecord(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validMessageRecord(Long id) {
    EntityOperations.doUpdate(messageRecordRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidMessageRecord(Long id) {
    EntityOperations.doUpdate(messageRecordRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public MessageRecordVO findById(Long id) {
    Optional<MessageRecord> messageRecord =  messageRecordRepository.findById(id);
    return new MessageRecordVO(messageRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<MessageRecordVO> findByPage(PageRequestWrapper<MessageRecordQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<MessageRecord> example = Example.of(MessageRecordMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<MessageRecord> page = messageRecordRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new MessageRecordVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
