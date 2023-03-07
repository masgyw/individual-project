// ---Auto Generated ---
package cn.gyw.individual.backend.service.service;

import cn.gyw.individual.backend.service.creator.HouseCreator;
import cn.gyw.individual.backend.service.query.HouseQuery;
import cn.gyw.individual.backend.service.updater.HouseUpdater;
import cn.gyw.individual.backend.service.vo.HouseVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
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
}
