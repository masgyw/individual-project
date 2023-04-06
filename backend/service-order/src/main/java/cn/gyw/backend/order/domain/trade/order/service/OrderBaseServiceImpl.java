// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.order.service;

import cn.gyw.backend.order.domain.trade.order.OrderBase;
import cn.gyw.backend.order.domain.trade.order.creator.OrderBaseCreator;
import cn.gyw.backend.order.domain.trade.order.mapper.OrderBaseMapper;
import cn.gyw.backend.order.domain.trade.order.query.OrderBaseQuery;
import cn.gyw.backend.order.domain.trade.order.repository.OrderBaseRepository;
import cn.gyw.backend.order.domain.trade.order.updater.OrderBaseUpdater;
import cn.gyw.backend.order.domain.trade.order.vo.OrderBaseVO;
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
public class OrderBaseServiceImpl implements OrderBaseService {
  private final OrderBaseRepository orderBaseRepository;

  /**
   * createImpl
   */
  @Override
  public Long createOrderBase(OrderBaseCreator creator) {
    Optional<OrderBase> orderBase = EntityOperations.doCreate(orderBaseRepository)
    .create(() -> OrderBaseMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return orderBase.isPresent() ? orderBase.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateOrderBase(OrderBaseUpdater updater) {
    EntityOperations.doUpdate(orderBaseRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateOrderBase(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validOrderBase(Long id) {
    EntityOperations.doUpdate(orderBaseRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidOrderBase(Long id) {
    EntityOperations.doUpdate(orderBaseRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public OrderBaseVO findById(Long id) {
    Optional<OrderBase> orderBase =  orderBaseRepository.findById(id);
    return new OrderBaseVO(orderBase.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<OrderBaseVO> findByPage(PageRequestWrapper<OrderBaseQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<OrderBase> example = Example.of(OrderBaseMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<OrderBase> page = orderBaseRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new OrderBaseVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
