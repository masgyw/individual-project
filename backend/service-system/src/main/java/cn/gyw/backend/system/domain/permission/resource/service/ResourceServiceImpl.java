// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.resource.service;

import cn.gyw.backend.system.api.response.ResourceTree;
import cn.gyw.backend.system.domain.permission.resource.Resource;
import cn.gyw.backend.system.domain.permission.resource.ResourceUtil;
import cn.gyw.backend.system.domain.permission.resource.creator.ResourceCreator;
import cn.gyw.backend.system.domain.permission.resource.mapper.ResourceMapper;
import cn.gyw.backend.system.domain.permission.resource.query.ResourceQuery;
import cn.gyw.backend.system.domain.permission.resource.repository.ResourceRepository;
import cn.gyw.backend.system.domain.permission.resource.updater.ResourceUpdater;
import cn.gyw.backend.system.domain.permission.resource.vo.ResourceVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    /**
     * createImpl
     */
    @Override
    public Long createResource(ResourceCreator creator) {
        Optional<Resource> resource = EntityOperations.doCreate(resourceRepository)
                .create(() -> ResourceMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return resource.isPresent() ? resource.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateResource(ResourceUpdater updater) {
        EntityOperations.doUpdate(resourceRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateResource(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validResource(Long id) {
        EntityOperations.doUpdate(resourceRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidResource(Long id) {
        EntityOperations.doUpdate(resourceRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public ResourceVO findById(Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);
        return new ResourceVO(resource.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<ResourceVO> findByPage(PageRequestWrapper<ResourceQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<Resource> example = Example.of(ResourceMapper.INSTANCE.queryToEntity(query.getBean()));
        Resource probe = example.getProbe();
        if (StringUtils.isEmpty(probe.getCode())) {
            probe.setCode(null);
        }
        if (StringUtils.isEmpty(probe.getName())) {
            probe.setName(null);
        }

        Page<Resource> page = resourceRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new ResourceVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<ResourceTree> getResourceTreeByPlatform(Long platformId) {
        Resource resource = new Resource();
        resource.setPlatformId(platformId);
        resource.setValidStatus(ValidStatus.VALID);
        Iterable<Resource> all = resourceRepository.findAll(Example.of(resource));
        return ResourceUtil.resourceList2Tree(all);
    }
}
