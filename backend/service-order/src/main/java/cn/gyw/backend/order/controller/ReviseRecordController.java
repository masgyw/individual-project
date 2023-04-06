// ---Auto Generated ---
package cn.gyw.backend.order.controller;

import cn.gyw.backend.order.api.request.ReviseRecordCreateRequest;
import cn.gyw.backend.order.api.request.ReviseRecordQueryRequest;
import cn.gyw.backend.order.api.request.ReviseRecordUpdateRequest;
import cn.gyw.backend.order.domain.trade.reviserecord.creator.ReviseRecordCreator;
import cn.gyw.backend.order.domain.trade.reviserecord.mapper.ReviseRecordMapper;
import cn.gyw.backend.order.domain.trade.reviserecord.query.ReviseRecordQuery;
import cn.gyw.backend.order.domain.trade.reviserecord.service.ReviseRecordService;
import cn.gyw.backend.order.domain.trade.reviserecord.updater.ReviseRecordUpdater;
import cn.gyw.backend.order.domain.trade.reviserecord.vo.ReviseRecordVO;
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
@RequestMapping("reviseRecord/v1")
@RequiredArgsConstructor
public class ReviseRecordController {
  private final ReviseRecordService reviseRecordService;

  /**
   * createRequest
   */
  @PostMapping("createReviseRecord")
  public DataResponse<Long> createReviseRecord(@RequestBody ReviseRecordCreateRequest request) {
    ReviseRecordCreator creator = ReviseRecordMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(reviseRecordService.createReviseRecord(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateReviseRecord")
  public DataResponse<String> updateReviseRecord(@RequestBody ReviseRecordUpdateRequest request) {
    ReviseRecordUpdater updater = ReviseRecordMapper.INSTANCE.request2Updater(request);
    reviseRecordService.updateReviseRecord(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validReviseRecord(@PathVariable Long id) {
    reviseRecordService.validReviseRecord(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidReviseRecord(@PathVariable Long id) {
    reviseRecordService.invalidReviseRecord(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<ReviseRecordVO> findById(@PathVariable Long id) {
    ReviseRecordVO vo = reviseRecordService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<ReviseRecordVO>> findByPage(
      @RequestBody PageRequestWrapper<ReviseRecordQueryRequest> request) {
    PageRequestWrapper<ReviseRecordQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(ReviseRecordMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<ReviseRecordVO> page = reviseRecordService.findByPage(wrapper);
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
