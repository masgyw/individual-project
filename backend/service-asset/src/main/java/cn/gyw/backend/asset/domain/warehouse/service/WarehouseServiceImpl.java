// ---Auto Generated ---
package cn.gyw.backend.asset.domain.warehouse.service;

import cn.gyw.backend.asset.domain.warehouse.Warehouse;
import cn.gyw.backend.asset.domain.warehouse.creator.WarehouseCreator;
import cn.gyw.backend.asset.domain.warehouse.mapper.WarehouseMapper;
import cn.gyw.backend.asset.domain.warehouse.query.WarehouseQuery;
import cn.gyw.backend.asset.domain.warehouse.repository.WarehouseRepository;
import cn.gyw.backend.asset.domain.warehouse.updater.WarehouseUpdater;
import cn.gyw.backend.asset.domain.warehouse.vo.WarehouseVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    /**
     * createImpl
     */
    @Override
    public Long createWarehouse(WarehouseCreator creator) {
        Optional<Warehouse> warehouse = EntityOperations.doCreate(warehouseRepository)
                .create(() -> WarehouseMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return warehouse.isPresent() ? warehouse.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateWarehouse(WarehouseUpdater updater) {
        EntityOperations.doUpdate(warehouseRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateWarehouse(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validWarehouse(Long id) {
        EntityOperations.doUpdate(warehouseRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidWarehouse(Long id) {
        EntityOperations.doUpdate(warehouseRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public WarehouseVO findById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        return new WarehouseVO(warehouse.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<WarehouseVO> findByPage(PageRequestWrapper<WarehouseQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<Warehouse> example = Example.of(WarehouseMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<Warehouse> page = warehouseRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new WarehouseVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
