// ---Auto Generated ---
package cn.gyw.backend.order.controller;

import cn.gyw.backend.order.api.request.SkuAttributeCreateRequest;
import cn.gyw.backend.order.api.request.SkuAttributeQueryRequest;
import cn.gyw.backend.order.api.request.SkuAttributeUpdateRequest;
import cn.gyw.backend.order.domain.objectsku.creator.SkuAttributeCreator;
import cn.gyw.backend.order.domain.objectsku.mapper.SkuAttributeMapper;
import cn.gyw.backend.order.domain.objectsku.query.SkuAttributeQuery;
import cn.gyw.backend.order.domain.objectsku.service.SkuAttributeService;
import cn.gyw.backend.order.domain.objectsku.updater.SkuAttributeUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.SkuAttributeVO;
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
@RequestMapping("skuAttribute/v1")
@RequiredArgsConstructor
public class SkuAttributeController {
  private final SkuAttributeService skuAttributeService;

  /**
   * createRequest
   */
  @PostMapping("createSkuAttribute")
  public DataResponse<Long> createSkuAttribute(@RequestBody SkuAttributeCreateRequest request) {
    SkuAttributeCreator creator = SkuAttributeMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(skuAttributeService.createSkuAttribute(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateSkuAttribute")
  public DataResponse<String> updateSkuAttribute(@RequestBody SkuAttributeUpdateRequest request) {
    SkuAttributeUpdater updater = SkuAttributeMapper.INSTANCE.request2Updater(request);
    skuAttributeService.updateSkuAttribute(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validSkuAttribute(@PathVariable Long id) {
    skuAttributeService.validSkuAttribute(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidSkuAttribute(@PathVariable Long id) {
    skuAttributeService.invalidSkuAttribute(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<SkuAttributeVO> findById(@PathVariable Long id) {
    SkuAttributeVO vo = skuAttributeService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<SkuAttributeVO>> findByPage(
      @RequestBody PageRequestWrapper<SkuAttributeQueryRequest> request) {
    PageRequestWrapper<SkuAttributeQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(SkuAttributeMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<SkuAttributeVO> page = skuAttributeService.findByPage(wrapper);
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
