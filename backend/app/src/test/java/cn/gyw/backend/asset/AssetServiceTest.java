package cn.gyw.backend.asset;

import cn.gyw.backend.Application;
import cn.gyw.backend.asset.domain.asset.domainservice.AssetDomainService;
import cn.gyw.backend.asset.domain.asset.domainservice.model.BatchInOutModel;
import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.backend.asset.domain.warehouse.creator.WarehouseCreator;
import cn.gyw.backend.asset.domain.warehouse.service.WarehouseService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产测试类
 *
 * @date 2023/3/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Application.class)
public class AssetServiceTest {

    @Resource
    private AssetDomainService assetDomainService;

    @Resource
    private WarehouseService warehouseService;

    @Test
    public void testWarehouseCreate() {
        WarehouseCreator creator = new WarehouseCreator();
        creator.setCreateUser("system");
        creator.setAddress("测试地址1");
        creator.setCode("warehouse-1");
        creator.setName("仓库-1");
        warehouseService.createWarehouse(creator);
    }

    @Test
    public void testSkuIn() {
        List<String> uniqueCodes = Lists.newArrayList();
        uniqueCodes.add("123456");
        uniqueCodes.add("234567");
        BatchInOutModel model = new BatchInOutModel();
        model.setSkuId(3L);
        model.setBatchNo("11111");
        model.setInOutBizType(InOutBizType.IN_FIRST);
        model.setOperateUser("用户2");
        model.setHouseId(1L);
        model.setUniqueCodes(uniqueCodes);
        model.setName("白色苹果手机");
        assetDomainService.assetIn(model);
    }
}
