// ---Auto Generated ---
package cn.gyw.backend.asset.domain.assetlifecycle.service;

import cn.gyw.backend.asset.domain.assetlifecycle.AssetLifecycle;
import cn.gyw.backend.asset.domain.assetlifecycle.creator.AssetLifecycleCreator;
import cn.gyw.backend.asset.domain.assetlifecycle.mapper.AssetLifecycleMapper;
import cn.gyw.backend.asset.domain.assetlifecycle.query.AssetLifecycleQuery;
import cn.gyw.backend.asset.domain.assetlifecycle.repository.AssetLifecycleRepository;
import cn.gyw.backend.asset.domain.assetlifecycle.updater.AssetLifecycleUpdater;
import cn.gyw.backend.asset.domain.assetlifecycle.vo.AssetLifecycleVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AssetLifecycleServiceImpl implements AssetLifecycleService {
    private final AssetLifecycleRepository assetLifecycleRepository;

    /**
     * createImpl
     */
    @Override
    public Long createAssetLifecycle(AssetLifecycleCreator creator) {
        Optional<AssetLifecycle> assetLifecycle = EntityOperations.doCreate(assetLifecycleRepository)
                .create(() -> AssetLifecycleMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return assetLifecycle.isPresent() ? assetLifecycle.get().getId() : 0;
    }

    /**
     * batch create
     *
     * @param batchNo     批次号
     * @param creatorList 列表
     */
    @Override
    public void batchCreateLifecycle(String batchNo, List<AssetLifecycleCreator> creatorList) {
        AssetLifecycle query = new AssetLifecycle();
        query.setBatchNo(batchNo);
        Optional<AssetLifecycle> lifecycle = assetLifecycleRepository.findOne(Example.of(query));
        if (lifecycle.isPresent()) {
            return;
        }
        List<AssetLifecycle> assetLifecycles = creatorList.stream()
                .map(c -> {
                    AssetLifecycle assetLifecycle = AssetLifecycleMapper.INSTANCE.dtoToEntity(c);
                    assetLifecycle.setValidStatus(ValidStatus.VALID);
                    return assetLifecycle;
                })
                .collect(Collectors.toList());
        assetLifecycleRepository.saveAll(assetLifecycles);
    }

    /**
     * update
     */
    @Override
    public void updateAssetLifecycle(AssetLifecycleUpdater updater) {
        EntityOperations.doUpdate(assetLifecycleRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateAssetLifecycle(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validAssetLifecycle(Long id) {
        EntityOperations.doUpdate(assetLifecycleRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidAssetLifecycle(Long id) {
        EntityOperations.doUpdate(assetLifecycleRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public AssetLifecycleVO findById(Long id) {
        Optional<AssetLifecycle> assetLifecycle = assetLifecycleRepository.findById(id);
        return new AssetLifecycleVO(assetLifecycle.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<AssetLifecycleVO> findByPage(PageRequestWrapper<AssetLifecycleQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<AssetLifecycle> example = Example.of(AssetLifecycleMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<AssetLifecycle> page = assetLifecycleRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new AssetLifecycleVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
