// ---Auto Generated ---
package cn.gyw.backend.asset.controller;

import cn.gyw.backend.asset.api.request.AssetInOutRecordQueryRequest;
import cn.gyw.backend.asset.domain.assetrecord.mapper.AssetInOutRecordMapper;
import cn.gyw.backend.asset.domain.assetrecord.query.AssetInOutRecordQuery;
import cn.gyw.backend.asset.domain.assetrecord.service.AssetInOutRecordService;
import cn.gyw.backend.asset.domain.assetrecord.vo.AssetInOutRecordVO;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("assetInOutRecord/v1")
@RequiredArgsConstructor
public class AssetInOutRecordController {
    private final AssetInOutRecordService assetInOutRecordService;

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<AssetInOutRecordVO> findById(@PathVariable Long id) {
        AssetInOutRecordVO vo = assetInOutRecordService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<AssetInOutRecordVO>> findByPage(
            @RequestBody PageRequestWrapper<AssetInOutRecordQueryRequest> request) {
        PageRequestWrapper<AssetInOutRecordQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(AssetInOutRecordMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<AssetInOutRecordVO> page = assetInOutRecordService.findByPage(wrapper);
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
