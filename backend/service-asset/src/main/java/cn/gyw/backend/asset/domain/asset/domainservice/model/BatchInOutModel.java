package cn.gyw.backend.asset.domain.asset.domainservice.model;

import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Data;

import java.util.List;

/**
 * 批量出入库模型
 *
 * @date 2023/3/28
 */
@Data
public class BatchInOutModel {

    private String name;

    @FieldDesc(name = "出入库类型")
    private InOutBizType inOutBizType;

    @FieldDesc(name = "操作用户")
    private String operateUser;

    @FieldDesc(name = "仓库ID")
    private Long houseId;

    @FieldDesc(name = "唯一编码")
    private List<String> uniqueCodes;

    @FieldDesc(name = "批次号")
    private String batchNo;

    @FieldDesc(name = "skuId")
    private Long skuId;
}
