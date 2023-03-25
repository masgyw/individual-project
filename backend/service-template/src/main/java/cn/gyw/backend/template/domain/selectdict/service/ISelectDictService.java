// ---Auto Generated ---
package cn.gyw.backend.template.domain.selectdict.service;

import cn.gyw.backend.template.domain.selectdict.creator.SelectDictCreator;
import cn.gyw.backend.template.domain.selectdict.query.SelectDictQuery;
import cn.gyw.backend.template.domain.selectdict.updater.SelectDictUpdater;
import cn.gyw.backend.template.domain.selectdict.vo.SelectDictVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import org.springframework.data.domain.Page;

public interface ISelectDictService {
  /**
   * create
   */
  Long createSelectDict(SelectDictCreator creator);

  /**
   * update
   */
  void updateSelectDict(SelectDictUpdater updater);

  /**
   * valid
   */
  void validSelectDict(Long id);

  /**
   * invalid
   */
  void invalidSelectDict(Long id);

  /**
   * findById
   */
  SelectDictVO findById(Long id);

  /**
   * findByPage
   */
  Page<SelectDictVO> findByPage(PageRequestWrapper<SelectDictQuery> query);
}
