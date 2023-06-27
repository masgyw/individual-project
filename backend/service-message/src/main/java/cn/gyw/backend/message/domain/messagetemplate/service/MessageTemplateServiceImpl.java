// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagetemplate.service;

import cn.gyw.backend.message.domain.messagetemplate.MessageTemplate;
import cn.gyw.backend.message.domain.messagetemplate.creator.MessageTemplateCreator;
import cn.gyw.backend.message.domain.messagetemplate.mapper.MessageTemplateMapper;
import cn.gyw.backend.message.domain.messagetemplate.query.MessageTemplateQuery;
import cn.gyw.backend.message.domain.messagetemplate.repository.MessageTemplateRepository;
import cn.gyw.backend.message.domain.messagetemplate.updater.MessageTemplateUpdater;
import cn.gyw.backend.message.domain.messagetemplate.vo.MessageTemplateVO;
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
public class MessageTemplateServiceImpl implements MessageTemplateService {
  private final MessageTemplateRepository messageTemplateRepository;

  /**
   * createImpl
   */
  @Override
  public Long createMessageTemplate(MessageTemplateCreator creator) {
    Optional<MessageTemplate> messageTemplate = EntityOperations.doCreate(messageTemplateRepository)
    .create(() -> MessageTemplateMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return messageTemplate.isPresent() ? messageTemplate.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateMessageTemplate(MessageTemplateUpdater updater) {
    EntityOperations.doUpdate(messageTemplateRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateMessageTemplate(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validMessageTemplate(Long id) {
    EntityOperations.doUpdate(messageTemplateRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidMessageTemplate(Long id) {
    EntityOperations.doUpdate(messageTemplateRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public MessageTemplateVO findById(Long id) {
    Optional<MessageTemplate> messageTemplate =  messageTemplateRepository.findById(id);
    return new MessageTemplateVO(messageTemplate.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<MessageTemplateVO> findByPage(PageRequestWrapper<MessageTemplateQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<MessageTemplate> example = Example.of(MessageTemplateMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<MessageTemplate> page = messageTemplateRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new MessageTemplateVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
