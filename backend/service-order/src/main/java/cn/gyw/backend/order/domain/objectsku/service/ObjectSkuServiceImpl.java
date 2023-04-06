// ---Auto Generated ---
package cn.gyw.backend.order.domain.objectsku.service;

import cn.gyw.backend.order.domain.objectsku.ObjectSku;
import cn.gyw.backend.order.domain.objectsku.creator.ObjectSkuCreator;
import cn.gyw.backend.order.domain.objectsku.mapper.ObjectSkuMapper;
import cn.gyw.backend.order.domain.objectsku.query.ObjectSkuQuery;
import cn.gyw.backend.order.domain.objectsku.repository.ObjectSkuRepository;
import cn.gyw.backend.order.domain.objectsku.updater.ObjectSkuUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.ObjectSkuVO;
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
public class ObjectSkuServiceImpl implements ObjectSkuService {
  private final ObjectSkuRepository objectSkuRepository;

  /**
   * createImpl
   */
  @Override
  public Long createObjectSku(ObjectSkuCreator creator) {
    Optional<ObjectSku> objectSku = EntityOperations.doCreate(objectSkuRepository)
    .create(() -> ObjectSkuMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return objectSku.isPresent() ? objectSku.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateObjectSku(ObjectSkuUpdater updater) {
    EntityOperations.doUpdate(objectSkuRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateObjectSku(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validObjectSku(Long id) {
    EntityOperations.doUpdate(objectSkuRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidObjectSku(Long id) {
    EntityOperations.doUpdate(objectSkuRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public ObjectSkuVO findById(Long id) {
    Optional<ObjectSku> objectSku =  objectSkuRepository.findById(id);
    return new ObjectSkuVO(objectSku.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<ObjectSkuVO> findByPage(PageRequestWrapper<ObjectSkuQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<ObjectSku> example = Example.of(ObjectSkuMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<ObjectSku> page = objectSkuRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new ObjectSkuVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
