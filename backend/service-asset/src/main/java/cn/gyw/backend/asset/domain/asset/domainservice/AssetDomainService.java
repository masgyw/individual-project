package cn.gyw.backend.asset.domain.asset.domainservice;

import cn.gyw.backend.asset.domain.asset.domainservice.model.BatchInOutModel;
import cn.gyw.backend.asset.domain.asset.domainservice.model.TransferModel;

/**
 * 资产领域 抽象方法
 *
 * @date 2023/3/28
 */
public interface AssetDomainService {

    /**
     * 资产入库
     */
    void assetIn(BatchInOutModel inModel);

    /**
     * 资产出库
     */
    void assetOut(BatchInOutModel outModel);

    /**
     * 资产转移
     */
    void assetTransfer(TransferModel transferModel);
}
