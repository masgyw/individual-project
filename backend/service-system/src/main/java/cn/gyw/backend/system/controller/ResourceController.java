// ---Auto Generated ---
package cn.gyw.backend.system.controller;

import cn.gyw.backend.system.api.request.ResourceCreateRequest;
import cn.gyw.backend.system.api.request.ResourceQueryRequest;
import cn.gyw.backend.system.api.request.ResourceUpdateRequest;
import cn.gyw.backend.system.domain.permission.resource.creator.ResourceCreator;
import cn.gyw.backend.system.domain.permission.resource.mapper.ResourceMapper;
import cn.gyw.backend.system.domain.permission.resource.query.ResourceQuery;
import cn.gyw.backend.system.domain.permission.resource.service.ResourceService;
import cn.gyw.backend.system.domain.permission.resource.updater.ResourceUpdater;
import cn.gyw.backend.system.domain.permission.resource.vo.ResourceVO;
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
@RequestMapping("resource/v1")
@RequiredArgsConstructor
public class ResourceController {
  private final ResourceService resourceService;

  /**
   * createRequest
   */
  @PostMapping("createResource")
  public DataResponse<Long> createResource(@RequestBody ResourceCreateRequest request) {
    ResourceCreator creator = ResourceMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(resourceService.createResource(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateResource")
  public DataResponse<String> updateResource(@RequestBody ResourceUpdateRequest request) {
    ResourceUpdater updater = ResourceMapper.INSTANCE.request2Updater(request);
    resourceService.updateResource(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validResource(@PathVariable Long id) {
    resourceService.validResource(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidResource(@PathVariable Long id) {
    resourceService.invalidResource(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<ResourceVO> findById(@PathVariable Long id) {
    ResourceVO vo = resourceService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<ResourceVO>> findByPage(
      @RequestBody PageRequestWrapper<ResourceQueryRequest> request) {
    PageRequestWrapper<ResourceQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(ResourceMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<ResourceVO> page = resourceService.findByPage(wrapper);
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
