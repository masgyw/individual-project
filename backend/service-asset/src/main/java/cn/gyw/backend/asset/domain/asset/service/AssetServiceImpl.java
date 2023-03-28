// ---Auto Generated ---
package cn.gyw.backend.asset.domain.asset.service;

import cn.gyw.backend.asset.domain.asset.Asset;
import cn.gyw.backend.asset.domain.asset.mapper.AssetMapper;
import cn.gyw.backend.asset.domain.asset.query.AssetQuery;
import cn.gyw.backend.asset.domain.asset.repository.AssetRepository;
import cn.gyw.backend.asset.domain.asset.vo.AssetVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;

    /**
     * findById
     */
    @Override
    public AssetVO findById(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        return new AssetVO(asset.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<AssetVO> findByPage(PageRequestWrapper<AssetQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<Asset> example = Example.of(AssetMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<Asset> page = assetRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new AssetVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
