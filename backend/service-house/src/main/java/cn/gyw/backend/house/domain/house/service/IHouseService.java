// ---Auto Generated ---
package cn.gyw.backend.house.domain.house.service;

import cn.gyw.backend.house.domain.house.query.HouseQuery;
import cn.gyw.backend.house.domain.house.creator.HouseCreator;
import cn.gyw.backend.house.domain.house.updater.HouseUpdater;
import cn.gyw.backend.house.domain.house.vo.HouseVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IHouseService {
    /**
     * create
     */
    Long createHouse(HouseCreator creator);

    /**
     * update
     */
    void updateHouse(HouseUpdater updater);

    /**
     * valid
     */
    void validHouse(Long id);

    /**
     * invalid
     */
    void invalidHouse(Long id);

    /**
     * findById
     */
    HouseVO findById(Long id);

    /**
     * findByPage
     */
    Page<HouseVO> findByPage(PageRequestWrapper<HouseQuery> query);

    List<HouseVO> findByRange(HouseQuery query);

    boolean batchInsert(List<HouseCreator> dataList);
}
