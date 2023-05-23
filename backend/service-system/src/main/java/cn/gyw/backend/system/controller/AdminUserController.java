// ---Auto Generated ---
package cn.gyw.backend.system.controller;

import cn.gyw.backend.system.api.request.AdminUserCreateRequest;
import cn.gyw.backend.system.api.request.AdminUserQueryRequest;
import cn.gyw.backend.system.api.request.AdminUserUpdateRequest;
import cn.gyw.backend.system.api.request.UserLoginParam;
import cn.gyw.backend.system.api.response.AdminUserResponse;
import cn.gyw.backend.system.domain.admin.creator.AdminUserCreator;
import cn.gyw.backend.system.domain.admin.mapper.AdminUserMapper;
import cn.gyw.backend.system.domain.admin.query.AdminUserQuery;
import cn.gyw.backend.system.domain.admin.service.IAdminUserService;
import cn.gyw.backend.system.domain.admin.updater.AdminUserUpdater;
import cn.gyw.backend.system.domain.admin.vo.AdminUserVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("adminUser/v1")
@RequiredArgsConstructor
public class AdminUserController {
    private final IAdminUserService adminUserService;

    // /**
    //  * 用户注册
    //  *
    //  * @param adminDto
    //  * @return
    //  */
    // @PostMapping(value = "/register")
    // public BaseResponse register(@Validated @RequestBody AdminDto adminDto) {
    //     Admin umsAdmin = adminService.register(adminDto);
    //     if (umsAdmin == null) {
    //         return BaseResponse.error(SystemRespEnum.REGISTER_FAILED);
    //     }
    //     return DataResponse.success(umsAdmin);
    // }

    @PostMapping(value = "/login")
    public DataResponse<Map<String, String>> login(@Validated @RequestBody UserLoginParam userLoginParam) {
        String token = adminUserService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        CodeEnum.TokenIllegal.assertNotNull(token);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return DataResponse.success(tokenMap);
    }

    /**
     * createRequest
     */
    @PostMapping("createAdminUser")
    public DataResponse<Long> createAdminUser(@RequestBody AdminUserCreateRequest request) {
        AdminUserCreator creator = AdminUserMapper.INSTANCE.request2Dto(request);
        return DataResponse.success(adminUserService.createAdminUser(creator));
    }

    /**
     * update request
     */
    @PostMapping("updateAdminUser")
    public DataResponse<String> updateAdminUser(@RequestBody AdminUserUpdateRequest request) {
        AdminUserUpdater updater = AdminUserMapper.INSTANCE.request2Updater(request);
        adminUserService.updateAdminUser(updater);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public DataResponse<String> validAdminUser(@PathVariable Long id) {
        adminUserService.validAdminUser(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public DataResponse<String> invalidAdminUser(@PathVariable Long id) {
        adminUserService.invalidAdminUser(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<AdminUserVO> findById(@PathVariable Long id) {
        AdminUserVO vo = adminUserService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<AdminUserVO>> findByPage(
            @RequestBody PageRequestWrapper<AdminUserQueryRequest> request) {
        PageRequestWrapper<AdminUserQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(AdminUserMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<AdminUserVO> page = adminUserService.findByPage(wrapper);
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
