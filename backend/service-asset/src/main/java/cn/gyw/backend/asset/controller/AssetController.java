// ---Auto Generated ---
package cn.gyw.backend.asset.controller;

import cn.gyw.backend.asset.api.request.AssetQueryRequest;
import cn.gyw.backend.asset.domain.asset.mapper.AssetMapper;
import cn.gyw.backend.asset.domain.asset.query.AssetQuery;
import cn.gyw.backend.asset.domain.asset.service.AssetService;
import cn.gyw.backend.asset.domain.asset.vo.AssetVO;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("asset/v1")
@RequiredArgsConstructor
public class AssetController {
    private final AssetService assetService;

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<AssetVO> findById(@PathVariable Long id) {
        AssetVO vo = assetService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<AssetVO>> findByPage(
            @RequestBody PageRequestWrapper<AssetQueryRequest> request) {
        PageRequestWrapper<AssetQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(AssetMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<AssetVO> page = assetService.findByPage(wrapper);
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
