// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.platform.service;

import cn.gyw.backend.system.domain.permission.platform.Platform;
import cn.gyw.backend.system.domain.permission.platform.creator.PlatformCreator;
import cn.gyw.backend.system.domain.permission.platform.mapper.PlatformMapper;
import cn.gyw.backend.system.domain.permission.platform.query.PlatformQuery;
import cn.gyw.backend.system.domain.permission.platform.repository.PlatformRepository;
import cn.gyw.backend.system.domain.permission.platform.updater.PlatformUpdater;
import cn.gyw.backend.system.domain.permission.platform.vo.PlatformVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;

import java.lang.Long;
import java.lang.Override;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;

    /**
     * createImpl
     */
    @Override
    public Long createPlatform(PlatformCreator creator) {
        Optional<Platform> platform = EntityOperations.doCreate(platformRepository)
                .create(() -> PlatformMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return platform.isPresent() ? platform.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updatePlatform(PlatformUpdater updater) {
        EntityOperations.doUpdate(platformRepository)
                .loadById(updater.getId())
                .update(e -> updater.updatePlatform(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validPlatform(Long id) {
        EntityOperations.doUpdate(platformRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidPlatform(Long id) {
        EntityOperations.doUpdate(platformRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public PlatformVO findById(Long id) {
        Optional<Platform> platform = platformRepository.findById(id);
        return new PlatformVO(platform.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<PlatformVO> findByPage(PageRequestWrapper<PlatformQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<Platform> example = Example.of(PlatformMapper.INSTANCE.queryToEntity(query.getBean()));
        if (StringUtils.isEmpty(example.getProbe().getName())) {
            example.getProbe().setName(null);
        }
        Page<Platform> page = platformRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new PlatformVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<PlatformVO> findAll() {
        List<Platform> dataList = platformRepository.findAll();
        return dataList.stream().map(PlatformVO::new).collect(Collectors.toList());
    }
}
