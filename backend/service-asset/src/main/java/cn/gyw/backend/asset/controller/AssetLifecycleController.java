// ---Auto Generated ---
package cn.gyw.backend.asset.controller;

import cn.gyw.backend.asset.api.request.AssetLifecycleCreateRequest;
import cn.gyw.backend.asset.api.request.AssetLifecycleQueryRequest;
import cn.gyw.backend.asset.api.request.AssetLifecycleUpdateRequest;
import cn.gyw.backend.asset.domain.assetlifecycle.creator.AssetLifecycleCreator;
import cn.gyw.backend.asset.domain.assetlifecycle.mapper.AssetLifecycleMapper;
import cn.gyw.backend.asset.domain.assetlifecycle.query.AssetLifecycleQuery;
import cn.gyw.backend.asset.domain.assetlifecycle.service.AssetLifecycleService;
import cn.gyw.backend.asset.domain.assetlifecycle.updater.AssetLifecycleUpdater;
import cn.gyw.backend.asset.domain.assetlifecycle.vo.AssetLifecycleVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("assetLifecycle/v1")
@RequiredArgsConstructor
public class AssetLifecycleController {
    private final AssetLifecycleService assetLifecycleService;

    /**
     * createRequest
     */
    @PostMapping("createAssetLifecycle")
    public DataResponse<Long> createAssetLifecycle(@RequestBody AssetLifecycleCreateRequest request) {
        AssetLifecycleCreator creator = AssetLifecycleMapper.INSTANCE.request2Dto(request);
        return DataResponse.success(assetLifecycleService.createAssetLifecycle(creator));
    }

    /**
     * update request
     */
    @PostMapping("updateAssetLifecycle")
    public DataResponse<String> updateAssetLifecycle(
            @RequestBody AssetLifecycleUpdateRequest request) {
        AssetLifecycleUpdater updater = AssetLifecycleMapper.INSTANCE.request2Updater(request);
        assetLifecycleService.updateAssetLifecycle(updater);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public DataResponse<String> validAssetLifecycle(@PathVariable Long id) {
        assetLifecycleService.validAssetLifecycle(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public DataResponse<String> invalidAssetLifecycle(@PathVariable Long id) {
        assetLifecycleService.invalidAssetLifecycle(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<AssetLifecycleVO> findById(@PathVariable Long id) {
        AssetLifecycleVO vo = assetLifecycleService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<AssetLifecycleVO>> findByPage(
            @RequestBody PageRequestWrapper<AssetLifecycleQueryRequest> request) {
        PageRequestWrapper<AssetLifecycleQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(AssetLifecycleMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<AssetLifecycleVO> page = assetLifecycleService.findByPage(wrapper);
        return DataResponse.success(
                PageData.of(
                        page.getContent(),
                        page.getTotalElements(),
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalPages())
        );
    }
}
