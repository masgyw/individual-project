package cn.gyw.backend.asset.domain.asset.events;

import cn.gyw.backend.asset.domain.asset.Asset;
import cn.gyw.backend.asset.domain.asset.domainservice.model.AssetBizInfo;
import lombok.Value;

/**
 * @date 2023/3/27
 */
public interface AssetEvents {

    @Value
    class AssetInEvent {
        Asset asset;
        AssetBizInfo bizInfo;
    }

    @Value
    class AssetOutEvent {
        Asset asset;
        AssetBizInfo bizInfo;
    }
}
