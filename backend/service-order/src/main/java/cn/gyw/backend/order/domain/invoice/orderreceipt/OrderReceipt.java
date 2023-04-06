// package cn.gyw.backend.order.domain.invoice.orderreceipt;
//
// import cn.gyw.individual.commons.enums.ValidStatus;
// import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
// import cn.gyw.individual.plugin.codegen.processor.api.CgCreateRequest;
// import cn.gyw.individual.plugin.codegen.processor.api.CgQueryRequest;
// import cn.gyw.individual.plugin.codegen.processor.api.CgResponse;
// import cn.gyw.individual.plugin.codegen.processor.api.CgUpdateRequest;
// import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
// import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
// import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
// import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
// import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
// import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
// import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
// import cn.gyw.individual.plugin.codegen.processor.service.CgService;
// import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
// import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
// import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
// import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
// import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
// import cn.gyw.individual.starters.jpa.converter.ValidStatusConverter;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import lombok.Data;
//
// import javax.persistence.Column;
// import javax.persistence.Convert;
// import javax.persistence.Entity;
// import javax.persistence.Table;
// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
//
// /**
//  * @Description
//  * @createdTime 2023/4/6 23:06
//  */
// @CgVo(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.vo")
// @CgCreator(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.creator")
// @CgUpdater(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.updater")
// @CgRepository(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.repository")
// @CgService(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.service")
// @CgServiceImpl(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.service")
// @CgQuery(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.query")
// @CgMapper(pkgName = "cn.gyw.backend.order.domain.invoice.orderreceipt.mapper")
// @CgController(pkgName = "cn.gyw.backend.order.controller")
// @CgCreateRequest(pkgName = "cn.gyw.backend.order.api.request")
// @CgUpdateRequest(pkgName = "cn.gyw.backend.order.api.request")
// @CgQueryRequest(pkgName = "cn.gyw.backend.order.api.request")
// @CgResponse(pkgName = "cn.gyw.backend.order.api.response")
// @Entity
// @Table(name = "order_receipt")
// @Data
// public class OrderReceipt extends BaseJpaAggregate {
//
//     @Convert(converter = ValidStatusConverter.class)
//     @IgnoreUpdater
//     @IgnoreCreator
//     private ValidStatus validStatus;
//
//     public void init() {
//         setValidStatus(ValidStatus.VALID);
//     }
//
//     public void valid() {
//         setValidStatus(ValidStatus.VALID);
//     }
//
//     public void invalid() {
//         setValidStatus(ValidStatus.INVALID);
//     }
//
// }
