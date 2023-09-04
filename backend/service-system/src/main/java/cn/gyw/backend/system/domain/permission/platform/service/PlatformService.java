// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.platform.service;

import cn.gyw.backend.system.domain.permission.platform.creator.PlatformCreator;
import cn.gyw.backend.system.domain.permission.platform.query.PlatformQuery;
import cn.gyw.backend.system.domain.permission.platform.updater.PlatformUpdater;
import cn.gyw.backend.system.domain.permission.platform.vo.PlatformVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

public interface PlatformService {
    /**
     * create
     */
    Long createPlatform(PlatformCreator creator);

    /**
     * update
     */
    void updatePlatform(PlatformUpdater updater);

    /**
     * valid
     */
    void validPlatform(Long id);

    /**
     * invalid
     */
    void invalidPlatform(Long id);

    /**
     * findById
     */
    PlatformVO findById(Long id);

    /**
     * findByPage
     */
    Page<PlatformVO> findByPage(PageRequestWrapper<PlatformQuery> query);

    List<PlatformVO> findAll();
}
