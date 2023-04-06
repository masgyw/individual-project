package cn.gyw.backend.order;

import cn.gyw.backend.order.domain.trade.order.OrderType;
import cn.gyw.backend.order.domain.trade.order.domainservice.OrderDomainService;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderCreateModel;
import cn.gyw.backend.order.domain.trade.order.domainservice.model.OrderItemModel;
import cn.gyw.individual.commons.enums.AccountType;
import cn.gyw.individual.commons.model.DictValue;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderServiceTest {

    @Autowired
    private OrderDomainService orderDomainService;

    @Test
    public void testOrderCreate() {
        OrderCreateModel model = new OrderCreateModel();
        model.setAccountId(2L);
        model.setOrderType(OrderType.SHOP);
        model.setAccountType(AccountType.CORP);
        model.setOperateUser("管理员");
        model.setPayList(Collections.EMPTY_LIST);
        List<DictValue> attrs = Lists.newArrayList();
        DictValue kv = new DictValue();
        kv.setK("channel");
        kv.setV("ios");
        kv.setL("渠道");
        attrs.add(kv);

        DictValue kv1 = new DictValue();
        kv1.setK("city");
        kv1.setV("河北");
        kv1.setL("城市");
        attrs.add(kv1);

        DictValue kv2 = new DictValue();
        kv2.setK("shop");
        kv2.setV("二号店");
        kv2.setL("二号店");
        attrs.add(kv2);
        model.setAttrs(attrs);

        List<OrderItemModel> itemModels = Lists.newArrayList();
        OrderItemModel orderItemModel = new OrderItemModel();
        orderItemModel.setSkuId(1L);
        orderItemModel.setItemCount(1);
        orderItemModel.setFeeRemark("衣服一件");
        orderItemModel.setGoodsName("衣服");
        orderItemModel.setRealAmount(new BigDecimal(String.valueOf(103.2)));
        OrderItemModel orderItemModel2 = new OrderItemModel();
        orderItemModel2.setSkuId(2L);
        orderItemModel2.setItemCount(1);
        orderItemModel2.setFeeRemark("鞋子");
        orderItemModel2.setGoodsName("鞋子");
        orderItemModel2.setRealAmount(new BigDecimal(String.valueOf(106.4)));
        itemModels.add(orderItemModel);
        itemModels.add(orderItemModel2);
        model.setItemInfoList(itemModels);
        orderDomainService.orderCreate(model);
    }

}
