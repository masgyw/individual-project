package cn.gyw.backend.asset.domain.asset.domainservice;

import cn.gyw.backend.asset.constant.AssetErrorCode;
import cn.gyw.backend.asset.domain.asset.Asset;
import cn.gyw.backend.asset.domain.asset.creator.AssetCreator;
import cn.gyw.backend.asset.domain.asset.domainservice.model.AssetBizInfo;
import cn.gyw.backend.asset.domain.asset.domainservice.model.BatchInOutModel;
import cn.gyw.backend.asset.domain.asset.domainservice.model.TransferModel;
import cn.gyw.backend.asset.domain.asset.mapper.AssetMapper;
import cn.gyw.backend.asset.domain.asset.repository.AssetRepository;
import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

/**
 * 领域服务
 *
 * @date 2023/3/28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AssetDomainServiceImpl implements AssetDomainService {

    private final AssetRepository assetRepository;

    /**
     * 资产入库
     *
     * @param inModel
     */
    @Override
    public void assetIn(BatchInOutModel inModel) {
        Assert.notEmpty(inModel.getUniqueCodes(), "唯一编号不能为空");
        String genBatchNo = UUID.randomUUID().toString();
        AssetBizInfo bizInfo = AssetBizInfo.builder()
                .batchNo(inModel.getBatchNo())
                .genBatchNo(genBatchNo)
                .inOutBizType(inModel.getInOutBizType())
                .uniqueCodes(inModel.getUniqueCodes())
                .operateUser(inModel.getOperateUser())
                .build();
        inModel.getUniqueCodes()
                .forEach(code -> {
                    Asset queryParam = new Asset();
                    queryParam.setHouseId(inModel.getHouseId());
                    queryParam.setUniqueCode(code);
                    queryParam.setSkuId(inModel.getSkuId());
                    Optional<Asset> old = assetRepository.findOne(Example.of(queryParam));
                    old.ifPresent(o -> {
                        EntityOperations.doUpdate(assetRepository)
                                .load(old::get)
                                .update(asset -> asset.in(bizInfo)).execute();
                    });
                    if (!old.isPresent()) {
                        AssetCreator creator = new AssetCreator();
                        creator.setHouseId(inModel.getHouseId());
                        creator.setName(inModel.getName());
                        creator.setSkuId(inModel.getSkuId());
                        creator.setUniqueCode(code);
                        EntityOperations.doCreate(assetRepository)
                                .create(() -> AssetMapper.INSTANCE.dtoToEntity(creator))
                                .update(asset -> asset.in(bizInfo))
                                .execute();
                    }
                });
    }

    /**
     * 资产出库
     *
     * @param outModel
     */
    @Override
    public void assetOut(BatchInOutModel outModel) {
        Assert.notEmpty(outModel.getUniqueCodes(), "唯一编号不能为空");
        String genBatchNo = UUID.randomUUID().toString();
        AssetBizInfo bizInfo = AssetBizInfo.builder()
                .batchNo(outModel.getBatchNo())
                .genBatchNo(genBatchNo)
                .inOutBizType(outModel.getInOutBizType())
                .uniqueCodes(outModel.getUniqueCodes())
                .operateUser(outModel.getOperateUser())
                .build();
        outModel.getUniqueCodes().forEach(code -> {
            Asset queryParam = new Asset();
            queryParam.setHouseId(outModel.getHouseId());
            queryParam.setUniqueCode(code);
            queryParam.setSkuId(outModel.getSkuId());
            Optional<Asset> old = assetRepository.findOne(Example.of(queryParam));
            if (!old.isPresent()) {
                throw new BusinessException(AssetErrorCode.ASSET_CODE_NOT_EXIST, "资产编号：" + code);
            }
            EntityOperations.doUpdate(assetRepository)
                    .load(old::get)
                    .update(asset -> asset.out(bizInfo))
                    .execute();
        });
    }

    /**
     * 资产转移
     *
     * @param transferModel
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assetTransfer(TransferModel transferModel) {
        Assert.notEmpty(transferModel.getUniqueCodes(), "唯一编号不能为空");
        String genBatchNo = UUID.randomUUID().toString();
        BatchInOutModel outModel = new BatchInOutModel();
        outModel.setBatchNo(transferModel.getBatchNo());
        outModel.setInOutBizType(InOutBizType.OUT_TRANSFER);
        outModel.setOperateUser(transferModel.getOperateUser());
        outModel.setHouseId(transferModel.getTransferOutHouseId());
        outModel.setUniqueCodes(transferModel.getUniqueCodes());
        assetOut(outModel);
        log.info("处理出库完成，仓库id:{},批次号:{},自动批号:{}", transferModel.getTransferOutHouseId(), transferModel.getBatchNo(), genBatchNo);

        Asset queryParam = new Asset();
        queryParam.setUniqueCode(transferModel.getUniqueCodes().get(0));
        Optional<Asset> old = assetRepository.findOne(Example.of(queryParam));
        BatchInOutModel inModel = new BatchInOutModel();
        inModel.setUniqueCodes(transferModel.getUniqueCodes());
        inModel.setName(old.map(Asset::getName).orElse(null));
        inModel.setInOutBizType(InOutBizType.IN_TRANSFER);
        inModel.setSkuId(transferModel.getSkuId());
        inModel.setOperateUser(transferModel.getOperateUser());
        inModel.setBatchNo(transferModel.getBatchNo());
        assetIn(inModel);
        log.info("处理入库完成，仓库id:{},批次号:{},自动批号:{}", transferModel.getTransferOutHouseId(), transferModel.getBatchNo(), genBatchNo);
    }
}
