// ---Auto Generated ---
package cn.gyw.backend.message.controller;

import cn.gyw.backend.message.api.request.MessageRecordCreateRequest;
import cn.gyw.backend.message.api.request.MessageRecordQueryRequest;
import cn.gyw.backend.message.api.request.MessageRecordUpdateRequest;
import cn.gyw.backend.message.domain.messagerecord.creator.MessageRecordCreator;
import cn.gyw.backend.message.domain.messagerecord.mapper.MessageRecordMapper;
import cn.gyw.backend.message.domain.messagerecord.query.MessageRecordQuery;
import cn.gyw.backend.message.domain.messagerecord.service.MessageRecordService;
import cn.gyw.backend.message.domain.messagerecord.updater.MessageRecordUpdater;
import cn.gyw.backend.message.domain.messagerecord.vo.MessageRecordVO;
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
@RequestMapping("messageRecord/v1")
@RequiredArgsConstructor
public class MessageRecordController {
  private final MessageRecordService messageRecordService;

  /**
   * createRequest
   */
  @PostMapping("createMessageRecord")
  public DataResponse<Long> createMessageRecord(@RequestBody MessageRecordCreateRequest request) {
    MessageRecordCreator creator = MessageRecordMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(messageRecordService.createMessageRecord(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateMessageRecord")
  public DataResponse<String> updateMessageRecord(@RequestBody MessageRecordUpdateRequest request) {
    MessageRecordUpdater updater = MessageRecordMapper.INSTANCE.request2Updater(request);
    messageRecordService.updateMessageRecord(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validMessageRecord(@PathVariable Long id) {
    messageRecordService.validMessageRecord(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidMessageRecord(@PathVariable Long id) {
    messageRecordService.invalidMessageRecord(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<MessageRecordVO> findById(@PathVariable Long id) {
    MessageRecordVO vo = messageRecordService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<MessageRecordVO>> findByPage(
      @RequestBody PageRequestWrapper<MessageRecordQueryRequest> request) {
    PageRequestWrapper<MessageRecordQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(MessageRecordMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<MessageRecordVO> page = messageRecordService.findByPage(wrapper);
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
