// ---Auto Generated ---
package cn.gyw.backend.template.controller;

import cn.gyw.backend.template.api.request.SelectDictCreateRequest;
import cn.gyw.backend.template.api.request.SelectDictQueryRequest;
import cn.gyw.backend.template.api.request.SelectDictUpdateRequest;
import cn.gyw.backend.template.domain.selectdict.creator.SelectDictCreator;
import cn.gyw.backend.template.domain.selectdict.mapper.SelectDictMapper;
import cn.gyw.backend.template.domain.selectdict.query.SelectDictQuery;
import cn.gyw.backend.template.domain.selectdict.service.ISelectDictService;
import cn.gyw.backend.template.domain.selectdict.updater.SelectDictUpdater;
import cn.gyw.backend.template.domain.selectdict.vo.SelectDictVO;
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
@RequestMapping("selectDict/v1")
@RequiredArgsConstructor
public class SelectDictController {
  private final ISelectDictService selectDictService;

  /**
   * createRequest
   */
  @PostMapping("createSelectDict")
  public DataResponse<Long> createSelectDict(@RequestBody SelectDictCreateRequest request) {
    SelectDictCreator creator = SelectDictMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(selectDictService.createSelectDict(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateSelectDict")
  public DataResponse<String> updateSelectDict(@RequestBody SelectDictUpdateRequest request) {
    SelectDictUpdater updater = SelectDictMapper.INSTANCE.request2Updater(request);
    selectDictService.updateSelectDict(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validSelectDict(@PathVariable Long id) {
    selectDictService.validSelectDict(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidSelectDict(@PathVariable Long id) {
    selectDictService.invalidSelectDict(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<SelectDictVO> findById(@PathVariable Long id) {
    SelectDictVO vo = selectDictService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<SelectDictVO>> findByPage(
      @RequestBody PageRequestWrapper<SelectDictQueryRequest> request) {
    PageRequestWrapper<SelectDictQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(SelectDictMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<SelectDictVO> page = selectDictService.findByPage(wrapper);
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
