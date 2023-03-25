// ---Auto Generated ---
package cn.gyw.backend.template.controller;

import cn.gyw.backend.template.api.request.TemplateItemCreateRequest;
import cn.gyw.backend.template.api.request.TemplateItemQueryRequest;
import cn.gyw.backend.template.api.request.TemplateItemUpdateRequest;
import cn.gyw.backend.template.domain.templateitem.creator.TemplateItemCreator;
import cn.gyw.backend.template.domain.templateitem.mapper.TemplateItemMapper;
import cn.gyw.backend.template.domain.templateitem.query.TemplateItemQuery;
import cn.gyw.backend.template.domain.templateitem.service.ITemplateItemService;
import cn.gyw.backend.template.domain.templateitem.updater.TemplateItemUpdater;
import cn.gyw.backend.template.domain.templateitem.vo.TemplateItemVO;
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
@RequestMapping("templateItem/v1")
@RequiredArgsConstructor
public class TemplateItemController {
  private final ITemplateItemService templateItemService;

  /**
   * createRequest
   */
  @PostMapping("createTemplateItem")
  public DataResponse<Long> createTemplateItem(@RequestBody TemplateItemCreateRequest request) {
    TemplateItemCreator creator = TemplateItemMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(templateItemService.createTemplateItem(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateTemplateItem")
  public DataResponse<String> updateTemplateItem(@RequestBody TemplateItemUpdateRequest request) {
    TemplateItemUpdater updater = TemplateItemMapper.INSTANCE.request2Updater(request);
    templateItemService.updateTemplateItem(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validTemplateItem(@PathVariable Long id) {
    templateItemService.validTemplateItem(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidTemplateItem(@PathVariable Long id) {
    templateItemService.invalidTemplateItem(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<TemplateItemVO> findById(@PathVariable Long id) {
    TemplateItemVO vo = templateItemService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<TemplateItemVO>> findByPage(
      @RequestBody PageRequestWrapper<TemplateItemQueryRequest> request) {
    PageRequestWrapper<TemplateItemQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(TemplateItemMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<TemplateItemVO> page = templateItemService.findByPage(wrapper);
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
