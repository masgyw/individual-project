// ---Auto Generated ---
package cn.gyw.backend.template.controller;

import cn.gyw.backend.template.api.request.ObjectTemplateCreateRequest;
import cn.gyw.backend.template.api.request.ObjectTemplateQueryRequest;
import cn.gyw.backend.template.api.request.ObjectTemplateUpdateRequest;
import cn.gyw.backend.template.domain.objecttemplate.creator.ObjectTemplateCreator;
import cn.gyw.backend.template.domain.objecttemplate.mapper.ObjectTemplateMapper;
import cn.gyw.backend.template.domain.objecttemplate.query.ObjectTemplateQuery;
import cn.gyw.backend.template.domain.objecttemplate.service.IObjectTemplateService;
import cn.gyw.backend.template.domain.objecttemplate.updater.ObjectTemplateUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.ObjectTemplateVO;
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
@RequestMapping("objectTemplate/v1")
@RequiredArgsConstructor
public class ObjectTemplateController {
  private final IObjectTemplateService objectTemplateService;

  /**
   * createRequest
   */
  @PostMapping("createObjectTemplate")
  public DataResponse<Long> createObjectTemplate(@RequestBody ObjectTemplateCreateRequest request) {
    ObjectTemplateCreator creator = ObjectTemplateMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(objectTemplateService.createObjectTemplate(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateObjectTemplate")
  public DataResponse<String> updateObjectTemplate(
      @RequestBody ObjectTemplateUpdateRequest request) {
    ObjectTemplateUpdater updater = ObjectTemplateMapper.INSTANCE.request2Updater(request);
    objectTemplateService.updateObjectTemplate(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validObjectTemplate(@PathVariable Long id) {
    objectTemplateService.validObjectTemplate(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidObjectTemplate(@PathVariable Long id) {
    objectTemplateService.invalidObjectTemplate(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<ObjectTemplateVO> findById(@PathVariable Long id) {
    ObjectTemplateVO vo = objectTemplateService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<ObjectTemplateVO>> findByPage(
      @RequestBody PageRequestWrapper<ObjectTemplateQueryRequest> request) {
    PageRequestWrapper<ObjectTemplateQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(ObjectTemplateMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<ObjectTemplateVO> page = objectTemplateService.findByPage(wrapper);
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
