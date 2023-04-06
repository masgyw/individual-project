// ---Auto Generated ---
package cn.gyw.backend.order.controller;

import cn.gyw.backend.order.api.request.ObjectSkuCreateRequest;
import cn.gyw.backend.order.api.request.ObjectSkuQueryRequest;
import cn.gyw.backend.order.api.request.ObjectSkuUpdateRequest;
import cn.gyw.backend.order.domain.objectsku.creator.ObjectSkuCreator;
import cn.gyw.backend.order.domain.objectsku.mapper.ObjectSkuMapper;
import cn.gyw.backend.order.domain.objectsku.query.ObjectSkuQuery;
import cn.gyw.backend.order.domain.objectsku.service.ObjectSkuService;
import cn.gyw.backend.order.domain.objectsku.updater.ObjectSkuUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.ObjectSkuVO;
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
@RequestMapping("objectSku/v1")
@RequiredArgsConstructor
public class ObjectSkuController {
  private final ObjectSkuService objectSkuService;

  /**
   * createRequest
   */
  @PostMapping("createObjectSku")
  public DataResponse<Long> createObjectSku(@RequestBody ObjectSkuCreateRequest request) {
    ObjectSkuCreator creator = ObjectSkuMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(objectSkuService.createObjectSku(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateObjectSku")
  public DataResponse<String> updateObjectSku(@RequestBody ObjectSkuUpdateRequest request) {
    ObjectSkuUpdater updater = ObjectSkuMapper.INSTANCE.request2Updater(request);
    objectSkuService.updateObjectSku(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validObjectSku(@PathVariable Long id) {
    objectSkuService.validObjectSku(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidObjectSku(@PathVariable Long id) {
    objectSkuService.invalidObjectSku(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<ObjectSkuVO> findById(@PathVariable Long id) {
    ObjectSkuVO vo = objectSkuService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<ObjectSkuVO>> findByPage(
      @RequestBody PageRequestWrapper<ObjectSkuQueryRequest> request) {
    PageRequestWrapper<ObjectSkuQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(ObjectSkuMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<ObjectSkuVO> page = objectSkuService.findByPage(wrapper);
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
