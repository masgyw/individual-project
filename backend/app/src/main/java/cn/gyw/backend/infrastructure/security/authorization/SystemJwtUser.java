package cn.gyw.backend.infrastructure.security.authorization;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.starters.security.base.BaseJwtUser;
import lombok.Data;

/**
 * @date 2023/5/23
 */
@Data
public class SystemJwtUser extends BaseJwtUser {

    @FieldDesc(name = "部门ID")
    private Long departmentId;
}
