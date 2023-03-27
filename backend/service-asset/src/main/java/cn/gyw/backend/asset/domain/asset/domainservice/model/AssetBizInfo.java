package cn.gyw.backend.asset.domain.asset.domainservice.model;

import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @date 2023/3/27
 */
@Builder
@Getter
public class AssetBizInfo {

    private InOutBizType inOutBizType;

    @FieldDesc(name = "唯一编码")
    private List<String> uniqueCodes;

    @FieldDesc(name = "批次号")
    private String batchNo;

    @FieldDesc(name = "自动生成批次号")
    private String genBatchNo;
    /**
     * 操作人
     */
    private String operateUser;
}
