package cn.gyw.backend.message.domain.messagerecord;

import cn.gyw.backend.message.domain.messagerecord.events.MessageRecordEvents;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.annotations.TypeConverter;
import cn.gyw.individual.plugin.codegen.processor.api.CgCreateRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgQueryRequest;
import cn.gyw.individual.plugin.codegen.processor.api.CgResponse;
import cn.gyw.individual.plugin.codegen.processor.api.CgUpdateRequest;
import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description
 * @createdTime 2023/6/15 23:04
 */
@CgVo(pkgName = "cn.gyw.backend.message.domain.messagerecord.vo")
@CgCreator(pkgName = "cn.gyw.backend.message.domain.messagerecord.creator")
@CgUpdater(pkgName = "cn.gyw.backend.message.domain.messagerecord.updater")
@CgRepository(pkgName = "cn.gyw.backend.message.domain.messagerecord.repository")
@CgService(pkgName = "cn.gyw.backend.message.domain.messagerecord.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.message.domain.messagerecord.service")
@CgQuery(pkgName = "cn.gyw.backend.message.domain.messagerecord.query")
@CgMapper(pkgName = "cn.gyw.backend.message.domain.messagerecord.mapper")
@CgController(pkgName = "cn.gyw.backend.message.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgResponse(pkgName = "cn.gyw.backend.message.api.response")
@Entity
@Table(name = "message_record")
@Data
public class MessageRecord extends BaseJpaAggregate {

    @FieldDesc(name = "模板编码")
    private String templateCode;

    @Column(columnDefinition = "text")
    private String content;

    @FieldDesc(name = "发送参数")
    private String params;

    @FieldDesc(name = "消息类型")
    @Convert(converter = MsgTypeEnumConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private MsgTypeEnum msgType;

    @Convert(converter = NoticeTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private NoticeType noticeType;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    /**
     * 初始化发送事件
     * @param content 内容
     */
    public void init(String content) {
        setContent(content);
        setValidStatus(ValidStatus.VALID);
        registerEvent(new MessageRecordEvents.MessageRecordCreateEvent(this));
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }

}

