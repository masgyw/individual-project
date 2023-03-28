package cn.gyw.backend.asset.domain.asset.events;

import cn.gyw.backend.asset.domain.asset.Asset;
import cn.gyw.backend.asset.domain.asset.domainservice.model.AssetBizInfo;
import cn.gyw.backend.asset.domain.assetlifecycle.creator.AssetLifecycleCreator;
import cn.gyw.backend.asset.domain.assetlifecycle.service.AssetLifecycleService;
import cn.gyw.backend.asset.domain.assetrecord.InOutType;
import cn.gyw.backend.asset.domain.assetrecord.creator.AssetInOutRecordCreator;
import cn.gyw.backend.asset.domain.assetrecord.service.AssetInOutRecordService;
import cn.gyw.backend.asset.domain.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2023/3/27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AssetEventProcessor {

    private final AssetInOutRecordService assetInOutRecordService;
    private final AssetLifecycleService assetLifecycleService;
    private final WarehouseService warehouseService;

    /**
     * 入库事件
     *
     * @param inEvent
     */
    @EventListener
    public void handleAssetInForLifecycle(AssetEvents.AssetInEvent inEvent) {
        AssetBizInfo bizInfo = inEvent.getBizInfo();
        Asset asset = inEvent.getAsset();

        List<AssetLifecycleCreator> lifecycleCreators = bizInfo.getUniqueCodes().stream()
                .map(code -> {
                    AssetLifecycleCreator creator = new AssetLifecycleCreator();
                    creator.setAssetsId(asset.getId());
                    creator.setHouseId(asset.getHouseId());
                    creator.setName(asset.getName());
                    creator.setInOutBizType(bizInfo.getInOutBizType());
                    creator.setUniqueCode(code);
                    creator.setRemark("入库");
                    creator.setBatchNo(bizInfo.getBatchNo());
                    creator.setGenBatchNo(bizInfo.getGenBatchNo());
                    creator.setInOutType(InOutType.IN);
                    creator.setSkuId(asset.getSkuId());
                    creator.setOperateUser(bizInfo.getOperateUser());
                    creator.setHouseName(warehouseService.findById(asset.getHouseId()).getName());
                    return creator;
                }).collect(Collectors.toList());
        assetLifecycleService.batchCreateLifecycle(bizInfo.getGenBatchNo(), lifecycleCreators);
    }

    @EventListener
    public void handleAssetOutForLifecycle(AssetEvents.AssetOutEvent outEvent) {
        AssetBizInfo bizInfo = outEvent.getBizInfo();
        Asset asset = outEvent.getAsset();

        List<AssetLifecycleCreator> lifecycleCreators = bizInfo.getUniqueCodes().stream()
                .map(code -> {
                    AssetLifecycleCreator creator = new AssetLifecycleCreator();
                    creator.setAssetsId(asset.getId());
                    creator.setHouseId(asset.getHouseId());
                    creator.setName(asset.getName());
                    creator.setInOutBizType(bizInfo.getInOutBizType());
                    creator.setUniqueCode(code);
                    creator.setBatchNo(bizInfo.getBatchNo());
                    creator.setGenBatchNo(bizInfo.getGenBatchNo());
                    creator.setSkuId(asset.getSkuId());
                    creator.setOperateUser(bizInfo.getOperateUser());
                    creator.setHouseName(warehouseService.findById(asset.getHouseId()).getName());
                    creator.setRemark("出库");
                    creator.setInOutType(InOutType.OUT);
                    return creator;
                }).collect(Collectors.toList());
        assetLifecycleService.batchCreateLifecycle(bizInfo.getGenBatchNo(), lifecycleCreators);
    }

    /**
     * 保存出库记录
     */
    @EventListener
    public void handleAssetOutForRecord(AssetEvents.AssetOutEvent outEvent) {
        AssetBizInfo bizInfo = outEvent.getBizInfo();
        AssetInOutRecordCreator creator = new AssetInOutRecordCreator();
        creator.setInOutBizType(bizInfo.getInOutBizType());
        creator.setInOutType(InOutType.OUT);
        creator.setBatchNo(bizInfo.getBatchNo());
        creator.setGenBatchNo(bizInfo.getGenBatchNo());
        creator.setOperateUser(bizInfo.getOperateUser());
        creator.setTotalCount(bizInfo.getUniqueCodes().size());
        assetInOutRecordService.createAssetInOutRecord(bizInfo.getUniqueCodes(), creator);
    }

}
