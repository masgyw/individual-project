// ---Auto Generated ---
package cn.gyw.backend.template.controller;

import cn.gyw.backend.template.api.request.VerifyRuleCfgCreateRequest;
import cn.gyw.backend.template.api.request.VerifyRuleCfgQueryRequest;
import cn.gyw.backend.template.api.request.VerifyRuleCfgUpdateRequest;
import cn.gyw.backend.template.domain.verifyrule.creator.VerifyRuleCfgCreator;
import cn.gyw.backend.template.domain.verifyrule.mapper.VerifyRuleCfgMapper;
import cn.gyw.backend.template.domain.verifyrule.query.VerifyRuleCfgQuery;
import cn.gyw.backend.template.domain.verifyrule.service.IVerifyRuleCfgService;
import cn.gyw.backend.template.domain.verifyrule.updater.VerifyRuleCfgUpdater;
import cn.gyw.backend.template.domain.verifyrule.vo.VerifyRuleCfgVO;
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
@RequestMapping("verifyRuleCfg/v1")
@RequiredArgsConstructor
public class VerifyRuleCfgController {
  private final IVerifyRuleCfgService verifyRuleCfgService;

  /**
   * createRequest
   */
  @PostMapping("createVerifyRuleCfg")
  public DataResponse<Long> createVerifyRuleCfg(@RequestBody VerifyRuleCfgCreateRequest request) {
    VerifyRuleCfgCreator creator = VerifyRuleCfgMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(verifyRuleCfgService.createVerifyRuleCfg(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateVerifyRuleCfg")
  public DataResponse<String> updateVerifyRuleCfg(@RequestBody VerifyRuleCfgUpdateRequest request) {
    VerifyRuleCfgUpdater updater = VerifyRuleCfgMapper.INSTANCE.request2Updater(request);
    verifyRuleCfgService.updateVerifyRuleCfg(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validVerifyRuleCfg(@PathVariable Long id) {
    verifyRuleCfgService.validVerifyRuleCfg(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidVerifyRuleCfg(@PathVariable Long id) {
    verifyRuleCfgService.invalidVerifyRuleCfg(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<VerifyRuleCfgVO> findById(@PathVariable Long id) {
    VerifyRuleCfgVO vo = verifyRuleCfgService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<VerifyRuleCfgVO>> findByPage(
      @RequestBody PageRequestWrapper<VerifyRuleCfgQueryRequest> request) {
    PageRequestWrapper<VerifyRuleCfgQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(VerifyRuleCfgMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<VerifyRuleCfgVO> page = verifyRuleCfgService.findByPage(wrapper);
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
