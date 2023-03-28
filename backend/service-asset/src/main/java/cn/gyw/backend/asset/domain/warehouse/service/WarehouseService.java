// ---Auto Generated ---
package cn.gyw.backend.asset.domain.warehouse.service;

import cn.gyw.backend.asset.domain.warehouse.creator.WarehouseCreator;
import cn.gyw.backend.asset.domain.warehouse.query.WarehouseQuery;
import cn.gyw.backend.asset.domain.warehouse.updater.WarehouseUpdater;
import cn.gyw.backend.asset.domain.warehouse.vo.WarehouseVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import org.springframework.data.domain.Page;

public interface WarehouseService {
    /**
     * create
     */
    Long createWarehouse(WarehouseCreator creator);

    /**
     * update
     */
    void updateWarehouse(WarehouseUpdater updater);

    /**
     * valid
     */
    void validWarehouse(Long id);

    /**
     * invalid
     */
    void invalidWarehouse(Long id);

    /**
     * findById
     */
    WarehouseVO findById(Long id);

    /**
     * findByPage
     */
    Page<WarehouseVO> findByPage(PageRequestWrapper<WarehouseQuery> query);
}
