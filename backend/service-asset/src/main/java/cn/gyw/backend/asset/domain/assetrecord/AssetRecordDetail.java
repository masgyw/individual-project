package cn.gyw.backend.asset.domain.assetrecord;

import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.starters.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @createdTime 2023/3/27 23:00
 */
@CgVo(pkgName = "cn.gyw.backend.asset.domain.assetrecord.vo")
@CgCreator(pkgName = "cn.gyw.backend.asset.domain.assetrecord.creator")
@CgUpdater(pkgName = "cn.gyw.backend.asset.domain.assetrecord.updater")
@CgRepository(pkgName = "cn.gyw.backend.asset.domain.assetrecord.repository")
@Entity
@Table(name = "asset_record_detail")
@Data
public class AssetRecordDetail extends BaseJpaAggregate {

    private Long recordId;

    private String uniqueCode;
}

