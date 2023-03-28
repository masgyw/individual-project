package cn.gyw.backend.asset.domain.asset.domainservice.model;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Data;

import java.util.List;

/**
 * 资产转移模型
 *
 * @date 2023/3/28
 */
@Data
public class TransferModel {

    @FieldDesc(name = "skuId")
    private Long skuId;

    @FieldDesc(name = "操作用户")
    private String operateUser;

    @FieldDesc(name = "转入仓库id")
    private Long transferInHouseId;

    @FieldDesc(name = "转出仓库id")
    private Long transferOutHouseId;

    @FieldDesc(name = "唯一编码")
    private List<String> uniqueCodes;

    @FieldDesc(name = "批次号")
    private String batchNo;
}
