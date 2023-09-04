// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.resource.service;

import cn.gyw.backend.system.api.response.ResourceTree;
import cn.gyw.backend.system.domain.permission.resource.creator.ResourceCreator;
import cn.gyw.backend.system.domain.permission.resource.query.ResourceQuery;
import cn.gyw.backend.system.domain.permission.resource.updater.ResourceUpdater;
import cn.gyw.backend.system.domain.permission.resource.vo.ResourceVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResourceService {
    /**
     * create
     */
    Long createResource(ResourceCreator creator);

    /**
     * update
     */
    void updateResource(ResourceUpdater updater);

    /**
     * valid
     */
    void validResource(Long id);

    /**
     * invalid
     */
    void invalidResource(Long id);

    /**
     * findById
     */
    ResourceVO findById(Long id);

    /**
     * findByPage
     */
    Page<ResourceVO> findByPage(PageRequestWrapper<ResourceQuery> query);

    List<ResourceTree> getResourceTreeByPlatform(Long platformId);
}
