// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.orderitem.mapper;

import cn.gyw.backend.order.api.request.OrderItemCreateRequest;
import cn.gyw.backend.order.api.request.OrderItemQueryRequest;
import cn.gyw.backend.order.api.request.OrderItemUpdateRequest;
import cn.gyw.backend.order.api.response.OrderItemResponse;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderItemModel;
import cn.gyw.backend.order.domain.trade.orderitem.OrderItem;
import cn.gyw.backend.order.domain.trade.orderitem.creator.OrderItemCreator;
import cn.gyw.backend.order.domain.trade.orderitem.query.OrderItemQuery;
import cn.gyw.backend.order.domain.trade.orderitem.updater.OrderItemUpdater;
import cn.gyw.backend.order.domain.trade.orderitem.vo.OrderItemVO;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    uses = {
        GenericEnumMapper.class,
        DateMapper.class
    }
)
public interface OrderItemMapper {
  OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

  OrderItem dtoToEntity(OrderItemCreator dto);

  OrderItemUpdater request2Updater(OrderItemUpdateRequest request);

  OrderItemCreator request2Dto(OrderItemCreateRequest request);

  OrderItemQuery request2Query(OrderItemQueryRequest request);

  OrderItem queryToEntity(OrderItemQuery query);

  OrderItemResponse vo2Response(OrderItemVO vo);

  default OrderItemResponse vo2CustomResponse(OrderItemVO vo) {
    OrderItemResponse response = vo2Response(vo);
    return response;
  }

  OrderItemCreator model2Creator(OrderItemModel model);
}
