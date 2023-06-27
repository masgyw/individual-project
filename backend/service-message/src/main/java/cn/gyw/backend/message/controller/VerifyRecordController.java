// ---Auto Generated ---
package cn.gyw.backend.message.controller;

import cn.gyw.backend.message.api.request.VerifyRecordCreateRequest;
import cn.gyw.backend.message.api.request.VerifyRecordQueryRequest;
import cn.gyw.backend.message.api.request.VerifyRecordUpdateRequest;
import cn.gyw.backend.message.domain.verifyrecord.creator.VerifyRecordCreator;
import cn.gyw.backend.message.domain.verifyrecord.mapper.VerifyRecordMapper;
import cn.gyw.backend.message.domain.verifyrecord.query.VerifyRecordQuery;
import cn.gyw.backend.message.domain.verifyrecord.service.VerifyRecordService;
import cn.gyw.backend.message.domain.verifyrecord.updater.VerifyRecordUpdater;
import cn.gyw.backend.message.domain.verifyrecord.vo.VerifyRecordVO;
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
@RequestMapping("verifyRecord/v1")
@RequiredArgsConstructor
public class VerifyRecordController {
  private final VerifyRecordService verifyRecordService;

  /**
   * createRequest
   */
  @PostMapping("createVerifyRecord")
  public DataResponse<Long> createVerifyRecord(@RequestBody VerifyRecordCreateRequest request) {
    VerifyRecordCreator creator = VerifyRecordMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(verifyRecordService.createVerifyRecord(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateVerifyRecord")
  public DataResponse<String> updateVerifyRecord(@RequestBody VerifyRecordUpdateRequest request) {
    VerifyRecordUpdater updater = VerifyRecordMapper.INSTANCE.request2Updater(request);
    verifyRecordService.updateVerifyRecord(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validVerifyRecord(@PathVariable Long id) {
    verifyRecordService.validVerifyRecord(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidVerifyRecord(@PathVariable Long id) {
    verifyRecordService.invalidVerifyRecord(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<VerifyRecordVO> findById(@PathVariable Long id) {
    VerifyRecordVO vo = verifyRecordService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<VerifyRecordVO>> findByPage(
      @RequestBody PageRequestWrapper<VerifyRecordQueryRequest> request) {
    PageRequestWrapper<VerifyRecordQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(VerifyRecordMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<VerifyRecordVO> page = verifyRecordService.findByPage(wrapper);
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
