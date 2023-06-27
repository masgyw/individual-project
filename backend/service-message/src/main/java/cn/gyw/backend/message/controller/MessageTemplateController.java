// ---Auto Generated ---
package cn.gyw.backend.message.controller;

import cn.gyw.backend.message.api.request.MessageTemplateCreateRequest;
import cn.gyw.backend.message.api.request.MessageTemplateQueryRequest;
import cn.gyw.backend.message.api.request.MessageTemplateUpdateRequest;
import cn.gyw.backend.message.domain.messagetemplate.creator.MessageTemplateCreator;
import cn.gyw.backend.message.domain.messagetemplate.mapper.MessageTemplateMapper;
import cn.gyw.backend.message.domain.messagetemplate.query.MessageTemplateQuery;
import cn.gyw.backend.message.domain.messagetemplate.service.MessageTemplateService;
import cn.gyw.backend.message.domain.messagetemplate.updater.MessageTemplateUpdater;
import cn.gyw.backend.message.domain.messagetemplate.vo.MessageTemplateVO;
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
@RequestMapping("messageTemplate/v1")
@RequiredArgsConstructor
public class MessageTemplateController {
  private final MessageTemplateService messageTemplateService;

  /**
   * createRequest
   */
  @PostMapping("createMessageTemplate")
  public DataResponse<Long> createMessageTemplate(
      @RequestBody MessageTemplateCreateRequest request) {
    MessageTemplateCreator creator = MessageTemplateMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(messageTemplateService.createMessageTemplate(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateMessageTemplate")
  public DataResponse<String> updateMessageTemplate(
      @RequestBody MessageTemplateUpdateRequest request) {
    MessageTemplateUpdater updater = MessageTemplateMapper.INSTANCE.request2Updater(request);
    messageTemplateService.updateMessageTemplate(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validMessageTemplate(@PathVariable Long id) {
    messageTemplateService.validMessageTemplate(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidMessageTemplate(@PathVariable Long id) {
    messageTemplateService.invalidMessageTemplate(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<MessageTemplateVO> findById(@PathVariable Long id) {
    MessageTemplateVO vo = messageTemplateService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<MessageTemplateVO>> findByPage(
      @RequestBody PageRequestWrapper<MessageTemplateQueryRequest> request) {
    PageRequestWrapper<MessageTemplateQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(MessageTemplateMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<MessageTemplateVO> page = messageTemplateService.findByPage(wrapper);
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
