// ---Auto Generated ---
package cn.gyw.backend.system.controller;

import cn.gyw.backend.system.api.request.RoleCreateRequest;
import cn.gyw.backend.system.api.request.RoleQueryRequest;
import cn.gyw.backend.system.api.request.RoleUpdateRequest;
import cn.gyw.backend.system.domain.permission.role.creator.RoleCreator;
import cn.gyw.backend.system.domain.permission.role.mapper.RoleMapper;
import cn.gyw.backend.system.domain.permission.role.query.RoleQuery;
import cn.gyw.backend.system.domain.permission.role.service.RoleService;
import cn.gyw.backend.system.domain.permission.role.updater.RoleUpdater;
import cn.gyw.backend.system.domain.permission.role.vo.RoleVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import java.lang.String;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("role/v1")
@RequiredArgsConstructor
public class RoleController {
  private final RoleService roleService;

  /**
   * createRequest
   */
  @PostMapping("createRole")
  public DataResponse<Long> createRole(@RequestBody RoleCreateRequest request) {
    RoleCreator creator = RoleMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(roleService.createRole(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateRole")
  public DataResponse<String> updateRole(@RequestBody RoleUpdateRequest request) {
    RoleUpdater updater = RoleMapper.INSTANCE.request2Updater(request);
    roleService.updateRole(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validRole(@PathVariable Long id) {
    roleService.validRole(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidRole(@PathVariable Long id) {
    roleService.invalidRole(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<RoleVO> findById(@PathVariable Long id) {
    RoleVO vo = roleService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<RoleVO>> findByPage(
      @RequestBody PageRequestWrapper<RoleQueryRequest> request) {
    PageRequestWrapper<RoleQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(RoleMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<RoleVO> page = roleService.findByPage(wrapper);
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
