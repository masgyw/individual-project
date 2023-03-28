// ---Auto Generated ---
package cn.gyw.backend.asset.controller;

import cn.gyw.backend.asset.api.request.WarehouseCreateRequest;
import cn.gyw.backend.asset.api.request.WarehouseQueryRequest;
import cn.gyw.backend.asset.api.request.WarehouseUpdateRequest;
import cn.gyw.backend.asset.domain.warehouse.creator.WarehouseCreator;
import cn.gyw.backend.asset.domain.warehouse.mapper.WarehouseMapper;
import cn.gyw.backend.asset.domain.warehouse.query.WarehouseQuery;
import cn.gyw.backend.asset.domain.warehouse.service.WarehouseService;
import cn.gyw.backend.asset.domain.warehouse.updater.WarehouseUpdater;
import cn.gyw.backend.asset.domain.warehouse.vo.WarehouseVO;
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
@RequestMapping("warehouse/v1")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    /**
     * createRequest
     */
    @PostMapping("createWarehouse")
    public DataResponse<Long> createWarehouse(@RequestBody WarehouseCreateRequest request) {
        WarehouseCreator creator = WarehouseMapper.INSTANCE.request2Dto(request);
        return DataResponse.success(warehouseService.createWarehouse(creator));
    }

    /**
     * update request
     */
    @PostMapping("updateWarehouse")
    public DataResponse<String> updateWarehouse(@RequestBody WarehouseUpdateRequest request) {
        WarehouseUpdater updater = WarehouseMapper.INSTANCE.request2Updater(request);
        warehouseService.updateWarehouse(updater);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public DataResponse<String> validWarehouse(@PathVariable Long id) {
        warehouseService.validWarehouse(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public DataResponse<String> invalidWarehouse(@PathVariable Long id) {
        warehouseService.invalidWarehouse(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<WarehouseVO> findById(@PathVariable Long id) {
        WarehouseVO vo = warehouseService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<WarehouseVO>> findByPage(
            @RequestBody PageRequestWrapper<WarehouseQueryRequest> request) {
        PageRequestWrapper<WarehouseQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(WarehouseMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<WarehouseVO> page = warehouseService.findByPage(wrapper);
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
