// ---Auto Generated ---
package cn.gyw.backend.template.controller;

import cn.gyw.backend.template.api.request.TemplateCategoryCreateRequest;
import cn.gyw.backend.template.api.request.TemplateCategoryQueryRequest;
import cn.gyw.backend.template.api.request.TemplateCategoryUpdateRequest;
import cn.gyw.backend.template.domain.objecttemplate.creator.TemplateCategoryCreator;
import cn.gyw.backend.template.domain.objecttemplate.mapper.TemplateCategoryMapper;
import cn.gyw.backend.template.domain.objecttemplate.query.TemplateCategoryQuery;
import cn.gyw.backend.template.domain.objecttemplate.service.ITemplateCategoryService;
import cn.gyw.backend.template.domain.objecttemplate.updater.TemplateCategoryUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.TemplateCategoryVO;
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
@RequestMapping("templateCategory/v1")
@RequiredArgsConstructor
public class TemplateCategoryController {
  private final ITemplateCategoryService templateCategoryService;

  /**
   * createRequest
   */
  @PostMapping("createTemplateCategory")
  public DataResponse<Long> createTemplateCategory(
      @RequestBody TemplateCategoryCreateRequest request) {
    TemplateCategoryCreator creator = TemplateCategoryMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(templateCategoryService.createTemplateCategory(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateTemplateCategory")
  public DataResponse<String> updateTemplateCategory(
      @RequestBody TemplateCategoryUpdateRequest request) {
    TemplateCategoryUpdater updater = TemplateCategoryMapper.INSTANCE.request2Updater(request);
    templateCategoryService.updateTemplateCategory(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validTemplateCategory(@PathVariable Long id) {
    templateCategoryService.validTemplateCategory(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidTemplateCategory(@PathVariable Long id) {
    templateCategoryService.invalidTemplateCategory(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<TemplateCategoryVO> findById(@PathVariable Long id) {
    TemplateCategoryVO vo = templateCategoryService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<TemplateCategoryVO>> findByPage(
      @RequestBody PageRequestWrapper<TemplateCategoryQueryRequest> request) {
    PageRequestWrapper<TemplateCategoryQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(TemplateCategoryMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<TemplateCategoryVO> page = templateCategoryService.findByPage(wrapper);
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
