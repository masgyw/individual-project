// ---Auto Generated ---
package cn.gyw.backend.system.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.Long;
import java.lang.String;

@Schema
public class PlatformUpdateRequest implements Request {
    @Schema(
            title = "编码"
    )
    private String code;

    @Schema(
            title = "平台名称"
    )
    private String name;

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
