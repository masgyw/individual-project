// ---Auto Generated ---
package cn.gyw.backend.message.domain.smssendrecord.service;

import cn.gyw.backend.message.domain.smssendrecord.SmsSendRecord;
import cn.gyw.backend.message.domain.smssendrecord.creator.SmsSendRecordCreator;
import cn.gyw.backend.message.domain.smssendrecord.mapper.SmsSendRecordMapper;
import cn.gyw.backend.message.domain.smssendrecord.query.SmsSendRecordQuery;
import cn.gyw.backend.message.domain.smssendrecord.repository.SmsSendRecordRepository;
import cn.gyw.backend.message.domain.smssendrecord.updater.SmsSendRecordUpdater;
import cn.gyw.backend.message.domain.smssendrecord.vo.SmsSendRecordVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;

import java.lang.Long;
import java.lang.Override;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class SmsSendRecordServiceImpl implements SmsSendRecordService {
    private final SmsSendRecordRepository smsSendRecordRepository;

    /**
     * createImpl
     */
    @Override
    public Long createSmsSendRecord(SmsSendRecordCreator creator) {
        Optional<SmsSendRecord> smsSendRecord = EntityOperations.doCreate(smsSendRecordRepository)
                .create(() -> SmsSendRecordMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return smsSendRecord.isPresent() ? smsSendRecord.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateSmsSendRecord(SmsSendRecordUpdater updater) {
        EntityOperations.doUpdate(smsSendRecordRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateSmsSendRecord(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validSmsSendRecord(Long id) {
        EntityOperations.doUpdate(smsSendRecordRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidSmsSendRecord(Long id) {
        EntityOperations.doUpdate(smsSendRecordRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public SmsSendRecordVO findById(Long id) {
        Optional<SmsSendRecord> smsSendRecord = smsSendRecordRepository.findById(id);
        return new SmsSendRecordVO(smsSendRecord.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<SmsSendRecordVO> findByPage(PageRequestWrapper<SmsSendRecordQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<SmsSendRecord> example = Example.of(SmsSendRecordMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<SmsSendRecord> page = smsSendRecordRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new SmsSendRecordVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
}
