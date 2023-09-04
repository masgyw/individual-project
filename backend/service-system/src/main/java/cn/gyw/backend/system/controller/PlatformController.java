// ---Auto Generated ---
package cn.gyw.backend.system.controller;

import cn.gyw.backend.system.api.request.PlatformCreateRequest;
import cn.gyw.backend.system.api.request.PlatformQueryRequest;
import cn.gyw.backend.system.api.request.PlatformUpdateRequest;
import cn.gyw.backend.system.api.response.PlatformResponse;
import cn.gyw.backend.system.domain.permission.platform.creator.PlatformCreator;
import cn.gyw.backend.system.domain.permission.platform.mapper.PlatformMapper;
import cn.gyw.backend.system.domain.permission.platform.query.PlatformQuery;
import cn.gyw.backend.system.domain.permission.platform.service.PlatformService;
import cn.gyw.backend.system.domain.permission.platform.updater.PlatformUpdater;
import cn.gyw.backend.system.domain.permission.platform.vo.PlatformVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("platform/v1")
@RequiredArgsConstructor
public class PlatformController {
    private final PlatformService platformService;

    /**
     * createRequest
     */
    @PostMapping("createPlatform")
    public DataResponse<Long> createPlatform(@RequestBody PlatformCreateRequest request) {
        PlatformCreator creator = PlatformMapper.INSTANCE.request2Dto(request);
        return DataResponse.success(platformService.createPlatform(creator));
    }

    /**
     * update request
     */
    @PostMapping("updatePlatform")
    public DataResponse<String> updatePlatform(@RequestBody PlatformUpdateRequest request) {
        PlatformUpdater updater = PlatformMapper.INSTANCE.request2Updater(request);
        platformService.updatePlatform(updater);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public DataResponse<String> validPlatform(@PathVariable Long id) {
        platformService.validPlatform(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public DataResponse<String> invalidPlatform(@PathVariable Long id) {
        platformService.invalidPlatform(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<PlatformVO> findById(@PathVariable Long id) {
        PlatformVO vo = platformService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<PlatformVO>> findByPage(
            @RequestBody PageRequestWrapper<PlatformQueryRequest> request) {
        PageRequestWrapper<PlatformQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(PlatformMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<PlatformVO> page = platformService.findByPage(wrapper);
        return DataResponse.success(
                PageData.of(
                        page.getContent(),
                        page.getTotalElements(),
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalPages())
        );
    }

    @RequestMapping("findAll")
    public DataResponse<List<PlatformResponse>> findAll() {
        List<PlatformVO> voList = platformService.findAll();

        List<PlatformResponse> collect = voList.stream().filter(vo -> ValidStatus.VALID.equals(vo.getValidStatus()))
                .map(PlatformMapper.INSTANCE::vo2Response).collect(Collectors.toList());
        return DataResponse.success(collect);
    }
}
