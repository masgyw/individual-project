package cn.gyw.backend.message.domain.smssendrecord;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.processor.api.CgCreateRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgQueryRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgResponse;
import cn.gyw.individual.plugin.codegen.processor.api.CgUpdateRequest;
import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @createdTime 2023/6/14 22:12
 */
@CgVo(pkgName = "cn.gyw.backend.message.domain.smssendrecord.vo")
@CgCreator(pkgName = "cn.gyw.backend.message.domain.smssendrecord.creator")
@CgUpdater(pkgName = "cn.gyw.backend.message.domain.smssendrecord.updater")
@CgRepository(pkgName = "cn.gyw.backend.message.domain.smssendrecord.repository")
@CgService(pkgName = "cn.gyw.backend.message.domain.smssendrecord.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.message.domain.smssendrecord.service")
@CgQuery(pkgName = "cn.gyw.backend.message.domain.smssendrecord.query")
@CgMapper(pkgName = "cn.gyw.backend.message.domain.smssendrecord.mapper")
@CgController(pkgName = "cn.gyw.backend.message.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgResponse(pkgName = "cn.gyw.backend.message.api.response")
@Entity
@Table(name = "sms_send_record")
@Data
public class SmsSendRecord extends BaseJpaAggregate {

    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String verifyCode;
    /**
     * 短信类型
     */
    @Convert(converter = SmsTypeConverter.class)
    private SmsType smsType;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
