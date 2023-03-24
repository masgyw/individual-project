// ---Auto Generated ---
package cn.gyw.backend.house.controller;

import cn.gyw.backend.house.api.request.HouseCreateRequest;
import cn.gyw.backend.house.api.request.HouseQueryRequest;
import cn.gyw.backend.house.api.request.HouseUpdateRequest;
import cn.gyw.backend.house.domain.house.creator.HouseCreator;
import cn.gyw.backend.house.domain.house.mapper.HouseMapper;
import cn.gyw.backend.house.domain.house.query.HouseQuery;
import cn.gyw.backend.house.domain.house.service.IHouseService;
import cn.gyw.backend.house.domain.house.updater.HouseUpdater;
import cn.gyw.backend.house.domain.house.vo.HouseVO;
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
@RequestMapping("house/v1")
@RequiredArgsConstructor
public class HouseController {
    private final IHouseService houseService;

    /**
     * createRequest
     */
    @PostMapping("createHouse")
    public DataResponse<Long> createHouse(@RequestBody HouseCreateRequest request) {
        HouseCreator creator = HouseMapper.INSTANCE.request2Dto(request);
        return DataResponse.success(houseService.createHouse(creator));
    }

    /**
     * update request
     */
    @PostMapping("updateHouse")
    public DataResponse<String> updateHouse(@RequestBody HouseUpdateRequest request) {
        HouseUpdater updater = HouseMapper.INSTANCE.request2Updater(request);
        houseService.updateHouse(updater);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public DataResponse<String> validHouse(@PathVariable Long id) {
        houseService.validHouse(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public DataResponse<String> invalidHouse(@PathVariable Long id) {
        houseService.invalidHouse(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<HouseVO> findById(@PathVariable Long id) {
        HouseVO vo = houseService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<HouseVO>> findByPage(
            @RequestBody PageRequestWrapper<HouseQueryRequest> request) {
        PageRequestWrapper<HouseQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(HouseMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<HouseVO> page = houseService.findByPage(wrapper);
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
