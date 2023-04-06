// ---Auto Generated ---
package cn.gyw.backend.order.controller;

import cn.gyw.backend.order.api.request.OrderItemCreateRequest;
import cn.gyw.backend.order.api.request.OrderItemQueryRequest;
import cn.gyw.backend.order.api.request.OrderItemUpdateRequest;
import cn.gyw.backend.order.domain.trade.orderitem.creator.OrderItemCreator;
import cn.gyw.backend.order.domain.trade.orderitem.mapper.OrderItemMapper;
import cn.gyw.backend.order.domain.trade.orderitem.query.OrderItemQuery;
import cn.gyw.backend.order.domain.trade.orderitem.service.OrderItemService;
import cn.gyw.backend.order.domain.trade.orderitem.updater.OrderItemUpdater;
import cn.gyw.backend.order.domain.trade.orderitem.vo.OrderItemVO;
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
@RequestMapping("orderItem/v1")
@RequiredArgsConstructor
public class OrderItemController {
  private final OrderItemService orderItemService;

  /**
   * createRequest
   */
  @PostMapping("createOrderItem")
  public DataResponse<Long> createOrderItem(@RequestBody OrderItemCreateRequest request) {
    OrderItemCreator creator = OrderItemMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(orderItemService.createOrderItem(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateOrderItem")
  public DataResponse<String> updateOrderItem(@RequestBody OrderItemUpdateRequest request) {
    OrderItemUpdater updater = OrderItemMapper.INSTANCE.request2Updater(request);
    orderItemService.updateOrderItem(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validOrderItem(@PathVariable Long id) {
    orderItemService.validOrderItem(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidOrderItem(@PathVariable Long id) {
    orderItemService.invalidOrderItem(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<OrderItemVO> findById(@PathVariable Long id) {
    OrderItemVO vo = orderItemService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<OrderItemVO>> findByPage(
      @RequestBody PageRequestWrapper<OrderItemQueryRequest> request) {
    PageRequestWrapper<OrderItemQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(OrderItemMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<OrderItemVO> page = orderItemService.findByPage(wrapper);
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
