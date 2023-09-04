// ---Auto Generated ---
package cn.gyw.backend.system.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.String;

@Schema
public class PlatformQueryRequest implements Request {
    @Schema(
            title = "平台名称"
    )
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
