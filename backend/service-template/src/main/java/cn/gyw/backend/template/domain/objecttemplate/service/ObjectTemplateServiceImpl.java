// ---Auto Generated ---
package cn.gyw.backend.template.domain.objecttemplate.service;

import cn.gyw.backend.template.domain.objecttemplate.ObjectTemplate;
import cn.gyw.backend.template.domain.objecttemplate.creator.ObjectTemplateCreator;
import cn.gyw.backend.template.domain.objecttemplate.mapper.ObjectTemplateMapper;
import cn.gyw.backend.template.domain.objecttemplate.query.ObjectTemplateQuery;
import cn.gyw.backend.template.domain.objecttemplate.repository.ObjectTemplateRepository;
import cn.gyw.backend.template.domain.objecttemplate.updater.ObjectTemplateUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.ObjectTemplateVO;
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
public class ObjectTemplateServiceImpl implements IObjectTemplateService {
  private final ObjectTemplateRepository objectTemplateRepository;

  /**
   * createImpl
   */
  @Override
  public Long createObjectTemplate(ObjectTemplateCreator creator) {
    Optional<ObjectTemplate> objectTemplate = EntityOperations.doCreate(objectTemplateRepository)
    .create(() -> ObjectTemplateMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return objectTemplate.isPresent() ? objectTemplate.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateObjectTemplate(ObjectTemplateUpdater updater) {
    EntityOperations.doUpdate(objectTemplateRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateObjectTemplate(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validObjectTemplate(Long id) {
    EntityOperations.doUpdate(objectTemplateRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidObjectTemplate(Long id) {
    EntityOperations.doUpdate(objectTemplateRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public ObjectTemplateVO findById(Long id) {
    Optional<ObjectTemplate> objectTemplate =  objectTemplateRepository.findById(id);
    return new ObjectTemplateVO(objectTemplate.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<ObjectTemplateVO> findByPage(PageRequestWrapper<ObjectTemplateQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<ObjectTemplate> example = Example.of(ObjectTemplateMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<ObjectTemplate> page = objectTemplateRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new ObjectTemplateVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
