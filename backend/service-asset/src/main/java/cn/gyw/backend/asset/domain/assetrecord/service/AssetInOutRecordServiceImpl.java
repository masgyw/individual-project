// ---Auto Generated ---
package cn.gyw.backend.asset.domain.assetrecord.service;

import cn.gyw.backend.asset.domain.assetrecord.AssetInOutRecord;
import cn.gyw.backend.asset.domain.assetrecord.AssetRecordDetail;
import cn.gyw.backend.asset.domain.assetrecord.creator.AssetInOutRecordCreator;
import cn.gyw.backend.asset.domain.assetrecord.mapper.AssetInOutRecordMapper;
import cn.gyw.backend.asset.domain.assetrecord.query.AssetInOutRecordQuery;
import cn.gyw.backend.asset.domain.assetrecord.repository.AssetInOutRecordRepository;
import cn.gyw.backend.asset.domain.assetrecord.repository.AssetRecordDetailRepository;
import cn.gyw.backend.asset.domain.assetrecord.vo.AssetInOutRecordVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
@RequiredArgsConstructor
public class AssetInOutRecordServiceImpl implements AssetInOutRecordService {
    private final AssetInOutRecordRepository assetInOutRecordRepository;
    private final AssetRecordDetailRepository assetRecordDetailRepository;

    /**
     * createImpl
     */
    @Override
    public Long createAssetInOutRecord(List<String> uniqueCodes, AssetInOutRecordCreator creator) {
        String genBatchNo = creator.getGenBatchNo();
        List<AssetInOutRecord> recordList = assetInOutRecordRepository.findAll((Specification<AssetInOutRecord>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(1);
            predicates.add(criteriaBuilder.equal(root.get("genBatchNo"), genBatchNo));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        if (CollectionUtils.isEmpty(recordList)) {
            return 0L;
        }
        Optional<AssetInOutRecord> assetInOutRecord = EntityOperations.doCreate(assetInOutRecordRepository)
                .create(() -> AssetInOutRecordMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        List<AssetRecordDetail> details = uniqueCodes.stream().map(code -> {
            AssetRecordDetail detail = new AssetRecordDetail();
            detail.setRecordId(assetInOutRecord.map(BaseJpaAggregate::getId).orElse(null));
            detail.setUniqueCode(code);
            return detail;
        }).collect(Collectors.toList());
        assetRecordDetailRepository.saveAll(details);
        return assetInOutRecord.isPresent() ? assetInOutRecord.get().getId() : 0;
    }

    /**
     * findById
     */
    @Override
    public AssetInOutRecordVO findById(Long id) {
        Optional<AssetInOutRecord> assetInOutRecord = assetInOutRecordRepository.findById(id);
        return new AssetInOutRecordVO(assetInOutRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<AssetInOutRecordVO> findByPage(PageRequestWrapper<AssetInOutRecordQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<AssetInOutRecord> example = Example.of(AssetInOutRecordMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<AssetInOutRecord> page = assetInOutRecordRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new AssetInOutRecordVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
