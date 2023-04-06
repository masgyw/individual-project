// ---Auto Generated ---
package cn.gyw.backend.order.domain.objectsku.service;

import cn.gyw.backend.order.domain.objectsku.SkuAttribute;
import cn.gyw.backend.order.domain.objectsku.creator.SkuAttributeCreator;
import cn.gyw.backend.order.domain.objectsku.mapper.SkuAttributeMapper;
import cn.gyw.backend.order.domain.objectsku.query.SkuAttributeQuery;
import cn.gyw.backend.order.domain.objectsku.repository.SkuAttributeRepository;
import cn.gyw.backend.order.domain.objectsku.updater.SkuAttributeUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.SkuAttributeVO;
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
public class SkuAttributeServiceImpl implements SkuAttributeService {
  private final SkuAttributeRepository skuAttributeRepository;

  /**
   * createImpl
   */
  @Override
  public Long createSkuAttribute(SkuAttributeCreator creator) {
    Optional<SkuAttribute> skuAttribute = EntityOperations.doCreate(skuAttributeRepository)
    .create(() -> SkuAttributeMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return skuAttribute.isPresent() ? skuAttribute.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateSkuAttribute(SkuAttributeUpdater updater) {
    EntityOperations.doUpdate(skuAttributeRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateSkuAttribute(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validSkuAttribute(Long id) {
    EntityOperations.doUpdate(skuAttributeRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidSkuAttribute(Long id) {
    EntityOperations.doUpdate(skuAttributeRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public SkuAttributeVO findById(Long id) {
    Optional<SkuAttribute> skuAttribute =  skuAttributeRepository.findById(id);
    return new SkuAttributeVO(skuAttribute.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<SkuAttributeVO> findByPage(PageRequestWrapper<SkuAttributeQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<SkuAttribute> example = Example.of(SkuAttributeMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<SkuAttribute> page = skuAttributeRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new SkuAttributeVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
