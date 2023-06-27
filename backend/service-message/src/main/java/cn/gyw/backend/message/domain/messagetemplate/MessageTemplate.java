package cn.gyw.backend.message.domain.messagetemplate;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
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
 * @createdTime 2023/6/15 23:20
 */
@CgVo(pkgName = "cn.gyw.backend.message.domain.messagetemplate.vo")
@CgCreator(pkgName = "cn.gyw.backend.message.domain.messagetemplate.creator")
@CgUpdater(pkgName = "cn.gyw.backend.message.domain.messagetemplate.updater")
@CgRepository(pkgName = "cn.gyw.backend.message.domain.messagetemplate.repository")
@CgService(pkgName = "cn.gyw.backend.message.domain.messagetemplate.service")
@CgServiceImpl(pkgName = "cn.gyw.backend.message.domain.messagetemplate.service")
@CgQuery(pkgName = "cn.gyw.backend.message.domain.messagetemplate.query")
@CgMapper(pkgName = "cn.gyw.backend.message.domain.messagetemplate.mapper")
@CgController(pkgName = "cn.gyw.backend.message.controller")
@CgCreateRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgUpdateRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgQueryRequest(pkgName = "cn.gyw.backend.message.api.request")
@CgResponse(pkgName = "cn.gyw.backend.message.api.response")
@Entity
@Table(name = "message_template")
@Data
public class MessageTemplate extends BaseJpaAggregate {

    @FieldDesc(name = "模板编码")
    private String templateCode;

    @FieldDesc(name = "名称")
    private String name;

    @FieldDesc(name = "模板")
    @Column(columnDefinition = "text")
    private String template;

    @FieldDesc(name = "模板类型")
    @Convert(converter = TemplateTypeConverter.class)
    private TemplateType templateType;

    @FieldDesc(name = "描述信息")
    private String description;

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

