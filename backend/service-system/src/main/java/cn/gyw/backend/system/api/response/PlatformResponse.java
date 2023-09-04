// ---Auto Generated ---
package cn.gyw.backend.system.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.String;

@Schema
public class PlatformResponse extends AbstractResponse {
    @Schema(
            title = "编码"
    )
    private String code;

    @Schema(
            title = "平台名称"
    )
    private String name;

    @Schema(
            title = "validStatus"
    )
    private ValidStatus validStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }
}
