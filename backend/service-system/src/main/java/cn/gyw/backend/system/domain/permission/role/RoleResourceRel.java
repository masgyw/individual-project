package cn.gyw.backend.system.domain.permission.role;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@CgVo(pkgName = "cn.gyw.backend.system.domain.permission.role.vo")
@CgCreator(pkgName = "cn.gyw.backend.system.domain.permission.role.creator")
@CgUpdater(pkgName = "cn.gyw.backend.system.domain.permission.role.updater")
@CgRepository(pkgName = "cn.gyw.backend.system.domain.permission.role.repository")
@Entity
@Table(name = "role_resource_rel")
@Data
public class RoleResourceRel extends BaseJpaAggregate {

    @FieldDesc(name = "角色ID")
    private Long roleId;

    @FieldDesc(name = "资源ID")
    private Long resourceId;
}