// ---Auto Generated ---
package cn.gyw.backend.template.domain.templateitem.service;

import cn.gyw.backend.template.domain.templateitem.TemplateItem;
import cn.gyw.backend.template.domain.templateitem.creator.TemplateItemCreator;
import cn.gyw.backend.template.domain.templateitem.mapper.TemplateItemMapper;
import cn.gyw.backend.template.domain.templateitem.query.TemplateItemQuery;
import cn.gyw.backend.template.domain.templateitem.repository.TemplateItemRepository;
import cn.gyw.backend.template.domain.templateitem.updater.TemplateItemUpdater;
import cn.gyw.backend.template.domain.templateitem.vo.TemplateItemVO;
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
public class TemplateItemServiceImpl implements ITemplateItemService {
  private final TemplateItemRepository templateItemRepository;

  /**
   * createImpl
   */
  @Override
  public Long createTemplateItem(TemplateItemCreator creator) {
    Optional<TemplateItem> templateItem = EntityOperations.doCreate(templateItemRepository)
    .create(() -> TemplateItemMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return templateItem.isPresent() ? templateItem.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateTemplateItem(TemplateItemUpdater updater) {
    EntityOperations.doUpdate(templateItemRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateTemplateItem(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validTemplateItem(Long id) {
    EntityOperations.doUpdate(templateItemRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidTemplateItem(Long id) {
    EntityOperations.doUpdate(templateItemRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public TemplateItemVO findById(Long id) {
    Optional<TemplateItem> templateItem =  templateItemRepository.findById(id);
    return new TemplateItemVO(templateItem.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<TemplateItemVO> findByPage(PageRequestWrapper<TemplateItemQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<TemplateItem> example = Example.of(TemplateItemMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<TemplateItem> page = templateItemRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new TemplateItemVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
