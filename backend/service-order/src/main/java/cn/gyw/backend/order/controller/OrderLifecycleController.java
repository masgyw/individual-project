// ---Auto Generated ---
package cn.gyw.backend.order.controller;

import cn.gyw.backend.order.api.request.OrderLifecycleCreateRequest;
import cn.gyw.backend.order.api.request.OrderLifecycleQueryRequest;
import cn.gyw.backend.order.api.request.OrderLifecycleUpdateRequest;
import cn.gyw.backend.order.domain.trade.orderlifecycle.creator.OrderLifecycleCreator;
import cn.gyw.backend.order.domain.trade.orderlifecycle.mapper.OrderLifecycleMapper;
import cn.gyw.backend.order.domain.trade.orderlifecycle.query.OrderLifecycleQuery;
import cn.gyw.backend.order.domain.trade.orderlifecycle.service.OrderLifecycleService;
import cn.gyw.backend.order.domain.trade.orderlifecycle.updater.OrderLifecycleUpdater;
import cn.gyw.backend.order.domain.trade.orderlifecycle.vo.OrderLifecycleVO;
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
@RequestMapping("orderLifecycle/v1")
@RequiredArgsConstructor
public class OrderLifecycleController {
  private final OrderLifecycleService orderLifecycleService;

  /**
   * createRequest
   */
  @PostMapping("createOrderLifecycle")
  public DataResponse<Long> createOrderLifecycle(@RequestBody OrderLifecycleCreateRequest request) {
    OrderLifecycleCreator creator = OrderLifecycleMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(orderLifecycleService.createOrderLifecycle(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateOrderLifecycle")
  public DataResponse<String> updateOrderLifecycle(
      @RequestBody OrderLifecycleUpdateRequest request) {
    OrderLifecycleUpdater updater = OrderLifecycleMapper.INSTANCE.request2Updater(request);
    orderLifecycleService.updateOrderLifecycle(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validOrderLifecycle(@PathVariable Long id) {
    orderLifecycleService.validOrderLifecycle(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidOrderLifecycle(@PathVariable Long id) {
    orderLifecycleService.invalidOrderLifecycle(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<OrderLifecycleVO> findById(@PathVariable Long id) {
    OrderLifecycleVO vo = orderLifecycleService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<OrderLifecycleVO>> findByPage(
      @RequestBody PageRequestWrapper<OrderLifecycleQueryRequest> request) {
    PageRequestWrapper<OrderLifecycleQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(OrderLifecycleMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<OrderLifecycleVO> page = orderLifecycleService.findByPage(wrapper);
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
