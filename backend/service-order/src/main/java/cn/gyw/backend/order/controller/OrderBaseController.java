// ---Auto Generated ---
package cn.gyw.backend.order.controller;

import cn.gyw.backend.order.api.request.OrderBaseCreateRequest;
import cn.gyw.backend.order.api.request.OrderBaseQueryRequest;
import cn.gyw.backend.order.api.request.OrderBaseUpdateRequest;
import cn.gyw.backend.order.domain.trade.order.creator.OrderBaseCreator;
import cn.gyw.backend.order.domain.trade.order.mapper.OrderBaseMapper;
import cn.gyw.backend.order.domain.trade.order.query.OrderBaseQuery;
import cn.gyw.backend.order.domain.trade.order.service.OrderBaseService;
import cn.gyw.backend.order.domain.trade.order.updater.OrderBaseUpdater;
import cn.gyw.backend.order.domain.trade.order.vo.OrderBaseVO;
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
@RequestMapping("orderBase/v1")
@RequiredArgsConstructor
public class OrderBaseController {
  private final OrderBaseService orderBaseService;

  /**
   * createRequest
   */
  @PostMapping("createOrderBase")
  public DataResponse<Long> createOrderBase(@RequestBody OrderBaseCreateRequest request) {
    OrderBaseCreator creator = OrderBaseMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(orderBaseService.createOrderBase(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateOrderBase")
  public DataResponse<String> updateOrderBase(@RequestBody OrderBaseUpdateRequest request) {
    OrderBaseUpdater updater = OrderBaseMapper.INSTANCE.request2Updater(request);
    orderBaseService.updateOrderBase(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validOrderBase(@PathVariable Long id) {
    orderBaseService.validOrderBase(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidOrderBase(@PathVariable Long id) {
    orderBaseService.invalidOrderBase(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<OrderBaseVO> findById(@PathVariable Long id) {
    OrderBaseVO vo = orderBaseService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<OrderBaseVO>> findByPage(
      @RequestBody PageRequestWrapper<OrderBaseQueryRequest> request) {
    PageRequestWrapper<OrderBaseQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(OrderBaseMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<OrderBaseVO> page = orderBaseService.findByPage(wrapper);
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
