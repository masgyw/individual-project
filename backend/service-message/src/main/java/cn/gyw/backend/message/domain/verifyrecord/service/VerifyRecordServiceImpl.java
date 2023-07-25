// ---Auto Generated ---
package cn.gyw.backend.message.domain.verifyrecord.service;

import cn.gyw.backend.message.config.MessageProperties;
import cn.gyw.backend.message.domain.verifyrecord.VerifyRecord;
import cn.gyw.backend.message.domain.verifyrecord.creator.VerifyRecordCreator;
import cn.gyw.backend.message.domain.verifyrecord.mapper.VerifyRecordMapper;
import cn.gyw.backend.message.domain.verifyrecord.query.VerifyRecordQuery;
import cn.gyw.backend.message.domain.verifyrecord.repository.VerifyRecordRepository;
import cn.gyw.backend.message.domain.verifyrecord.updater.VerifyRecordUpdater;
import cn.gyw.backend.message.domain.verifyrecord.vo.VerifyRecordVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class VerifyRecordServiceImpl implements VerifyRecordService {
    private final VerifyRecordRepository verifyRecordRepository;

    private final MessageProperties messageProperties;

    /**
     * createImpl
     */
    @Override
    public Long createVerifyRecord(VerifyRecordCreator creator) {
        Optional<VerifyRecord> verifyRecord = EntityOperations.doCreate(verifyRecordRepository)
                .create(() -> VerifyRecordMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return verifyRecord.isPresent() ? verifyRecord.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateVerifyRecord(VerifyRecordUpdater updater) {
        EntityOperations.doUpdate(verifyRecordRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateVerifyRecord(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validVerifyRecord(Long id) {
        EntityOperations.doUpdate(verifyRecordRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidVerifyRecord(Long id) {
        EntityOperations.doUpdate(verifyRecordRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public VerifyRecordVO findById(Long id) {
        Optional<VerifyRecord> verifyRecord = verifyRecordRepository.findById(id);
        return new VerifyRecordVO(verifyRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<VerifyRecordVO> findByPage(PageRequestWrapper<VerifyRecordQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<VerifyRecord> example = Example.of(VerifyRecordMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<VerifyRecord> page = verifyRecordRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new VerifyRecordVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Override
    public boolean checkSendInterval(String account, String templateCode) {
        Assert.notNull(account);
        Assert.notNull(templateCode);
        //找到最新发送的一条
        Collection<VerifyRecord> lastDayRecord = lastDayRecord(account, templateCode);
        if (CollectionUtils.isEmpty(lastDayRecord)) {
            return true;
        } else {
            VerifyRecord verifyRecord = lastDayRecord.stream().findFirst().orElse(null);
            Objects.requireNonNull(verifyRecord);
            if (new BigDecimal(Instant.now().toEpochMilli()).compareTo(new BigDecimal(verifyRecord.getEndTime())) > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean checkSendMaxTimes(String account, String templateCode) {
        //找到过去一天发送的条数
        Collection<VerifyRecord> lastDayRecord = lastDayRecord(account, templateCode);
        if (CollectionUtils.isEmpty(lastDayRecord)) {
            return true;
        } else {
            if (lastDayRecord.size() > messageProperties.getSendMaxTimes()) {
                return false;
            } else {
                return true;
            }
        }
    }

    private Collection<VerifyRecord> lastDayRecord(String account, String templateCode) {
        // createAt >=
        Instant instant = Instant.now().minus(1, ChronoUnit.DAYS);

        List<VerifyRecord> lastDayRecord = verifyRecordRepository.findAll((Specification<VerifyRecord>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(3);
            predicates.add(criteriaBuilder.equal(root.get("account"), account));
            predicates.add(criteriaBuilder.equal(root.get("templateCode"), templateCode));
            predicates.add(criteriaBuilder.greaterThan(root.get("createdAt"), instant));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(Sort.Direction.DESC, "createdAt"));
        return lastDayRecord;
    }

}
