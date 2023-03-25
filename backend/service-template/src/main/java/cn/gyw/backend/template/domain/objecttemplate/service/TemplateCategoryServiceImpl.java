// ---Auto Generated ---
package cn.gyw.backend.template.domain.objecttemplate.service;

import cn.gyw.backend.template.domain.objecttemplate.TemplateCategory;
import cn.gyw.backend.template.domain.objecttemplate.creator.TemplateCategoryCreator;
import cn.gyw.backend.template.domain.objecttemplate.mapper.TemplateCategoryMapper;
import cn.gyw.backend.template.domain.objecttemplate.query.TemplateCategoryQuery;
import cn.gyw.backend.template.domain.objecttemplate.repository.TemplateCategoryRepository;
import cn.gyw.backend.template.domain.objecttemplate.updater.TemplateCategoryUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.TemplateCategoryVO;
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
public class TemplateCategoryServiceImpl implements ITemplateCategoryService {
  private final TemplateCategoryRepository templateCategoryRepository;

  /**
   * createImpl
   */
  @Override
  public Long createTemplateCategory(TemplateCategoryCreator creator) {
    Optional<TemplateCategory> templateCategory = EntityOperations.doCreate(templateCategoryRepository)
    .create(() -> TemplateCategoryMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return templateCategory.isPresent() ? templateCategory.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateTemplateCategory(TemplateCategoryUpdater updater) {
    EntityOperations.doUpdate(templateCategoryRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateTemplateCategory(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validTemplateCategory(Long id) {
    EntityOperations.doUpdate(templateCategoryRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidTemplateCategory(Long id) {
    EntityOperations.doUpdate(templateCategoryRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public TemplateCategoryVO findById(Long id) {
    Optional<TemplateCategory> templateCategory =  templateCategoryRepository.findById(id);
    return new TemplateCategoryVO(templateCategory.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<TemplateCategoryVO> findByPage(PageRequestWrapper<TemplateCategoryQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<TemplateCategory> example = Example.of(TemplateCategoryMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<TemplateCategory> page = templateCategoryRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new TemplateCategoryVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
