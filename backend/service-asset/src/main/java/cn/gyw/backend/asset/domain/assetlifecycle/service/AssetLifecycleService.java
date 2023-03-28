// ---Auto Generated ---
package cn.gyw.backend.asset.domain.assetlifecycle.service;

import cn.gyw.backend.asset.domain.assetlifecycle.creator.AssetLifecycleCreator;
import cn.gyw.backend.asset.domain.assetlifecycle.query.AssetLifecycleQuery;
import cn.gyw.backend.asset.domain.assetlifecycle.updater.AssetLifecycleUpdater;
import cn.gyw.backend.asset.domain.assetlifecycle.vo.AssetLifecycleVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

public interface AssetLifecycleService {
    /**
     * create
     */
    Long createAssetLifecycle(AssetLifecycleCreator creator);

    /**
     * batch create
     * @param batchNo 批次号
     * @param creatorList 列表
     */
    void batchCreateLifecycle(String batchNo, List<AssetLifecycleCreator> creatorList);

    /**
     * update
     */
    void updateAssetLifecycle(AssetLifecycleUpdater updater);

    /**
     * valid
     */
    void validAssetLifecycle(Long id);

    /**
     * invalid
     */
    void invalidAssetLifecycle(Long id);

    /**
     * findById
     */
    AssetLifecycleVO findById(Long id);

    /**
     * findByPage
     */
    Page<AssetLifecycleVO> findByPage(PageRequestWrapper<AssetLifecycleQuery> query);
}
