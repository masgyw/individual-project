// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.orderitem.service;

import cn.gyw.backend.order.domain.trade.orderitem.OrderItem;
import cn.gyw.backend.order.domain.trade.orderitem.creator.OrderItemCreator;
import cn.gyw.backend.order.domain.trade.orderitem.mapper.OrderItemMapper;
import cn.gyw.backend.order.domain.trade.orderitem.query.OrderItemQuery;
import cn.gyw.backend.order.domain.trade.orderitem.repository.OrderItemRepository;
import cn.gyw.backend.order.domain.trade.orderitem.updater.OrderItemUpdater;
import cn.gyw.backend.order.domain.trade.orderitem.vo.OrderItemVO;
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
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    /**
     * createImpl
     */
    @Override
    public Long createOrderItem(OrderItemCreator creator) {
        Optional<OrderItem> orderItem = EntityOperations.doCreate(orderItemRepository)
                .create(() -> OrderItemMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> {
                })
                .execute();
        return orderItem.isPresent() ? orderItem.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateOrderItem(OrderItemUpdater updater) {
        EntityOperations.doUpdate(orderItemRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateOrderItem(e))
                .execute();
    }

    /**
     * findById
     */
    @Override
    public OrderItemVO findById(Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return new OrderItemVO(orderItem.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<OrderItemVO> findByPage(PageRequestWrapper<OrderItemQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<OrderItem> example = Example.of(OrderItemMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<OrderItem> page = orderItemRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new OrderItemVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
