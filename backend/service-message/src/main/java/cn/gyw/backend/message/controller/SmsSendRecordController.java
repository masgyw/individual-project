// ---Auto Generated ---
package cn.gyw.backend.message.controller;

import cn.gyw.backend.message.api.request.SmsSendRecordCreateRequest;
import cn.gyw.backend.message.api.request.SmsSendRecordQueryRequest;
import cn.gyw.backend.message.api.request.SmsSendRecordUpdateRequest;
import cn.gyw.backend.message.domain.smssendrecord.creator.SmsSendRecordCreator;
import cn.gyw.backend.message.domain.smssendrecord.mapper.SmsSendRecordMapper;
import cn.gyw.backend.message.domain.smssendrecord.query.SmsSendRecordQuery;
import cn.gyw.backend.message.domain.smssendrecord.service.SmsSendRecordService;
import cn.gyw.backend.message.domain.smssendrecord.updater.SmsSendRecordUpdater;
import cn.gyw.backend.message.domain.smssendrecord.vo.SmsSendRecordVO;
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
@RequestMapping("smsSendRecord/v1")
@RequiredArgsConstructor
public class SmsSendRecordController {
    private final SmsSendRecordService smsSendRecordService;

    /**
     * createRequest
     */
    @PostMapping("createSmsSendRecord")
    public DataResponse<Long> createSmsSendRecord(@RequestBody SmsSendRecordCreateRequest request) {
        SmsSendRecordCreator creator = SmsSendRecordMapper.INSTANCE.request2Dto(request);
        return DataResponse.success(smsSendRecordService.createSmsSendRecord(creator));
    }

    /**
     * update request
     */
    @PostMapping("updateSmsSendRecord")
    public DataResponse<String> updateSmsSendRecord(@RequestBody SmsSendRecordUpdateRequest request) {
        SmsSendRecordUpdater updater = SmsSendRecordMapper.INSTANCE.request2Updater(request);
        smsSendRecordService.updateSmsSendRecord(updater);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public DataResponse<String> validSmsSendRecord(@PathVariable Long id) {
        smsSendRecordService.validSmsSendRecord(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public DataResponse<String> invalidSmsSendRecord(@PathVariable Long id) {
        smsSendRecordService.invalidSmsSendRecord(id);
        return DataResponse.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public DataResponse<SmsSendRecordVO> findById(@PathVariable Long id) {
        SmsSendRecordVO vo = smsSendRecordService.findById(id);
        return DataResponse.success(vo);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public DataResponse<PageData<SmsSendRecordVO>> findByPage(
            @RequestBody PageRequestWrapper<SmsSendRecordQueryRequest> request) {
        PageRequestWrapper<SmsSendRecordQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setBean(SmsSendRecordMapper.INSTANCE.request2Query(request.getBean()));
        wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
        Page<SmsSendRecordVO> page = smsSendRecordService.findByPage(wrapper);
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
