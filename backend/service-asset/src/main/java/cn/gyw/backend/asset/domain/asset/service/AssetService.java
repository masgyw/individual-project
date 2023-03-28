// ---Auto Generated ---
package cn.gyw.backend.asset.domain.asset.service;

import cn.gyw.backend.asset.domain.asset.query.AssetQuery;
import cn.gyw.backend.asset.domain.asset.vo.AssetVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import org.springframework.data.domain.Page;

public interface AssetService {
    /**
     * findById
     */
    AssetVO findById(Long id);

    /**
     * findByPage
     */
    Page<AssetVO> findByPage(PageRequestWrapper<AssetQuery> query);
}
