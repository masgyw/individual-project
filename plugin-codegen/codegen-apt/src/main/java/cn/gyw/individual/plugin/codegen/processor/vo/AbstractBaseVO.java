package cn.gyw.individual.plugin.codegen.processor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AbstractBaseVO {
    @Schema(
            title = "数据版本"
    )
    private int version;
    @Schema(
        title = "主键"
    )
    private Long id;
    @Schema(
            title = "创建时间"
    )
    private Long createdAt;
    @Schema(
            title = "修改时间"
    )
    private Long updatedAt;

    protected AbstractBaseVO() {
    }
}
