// ---Auto Generated ---
package cn.gyw.backend.asset.domain.assetrecord.service;

import cn.gyw.backend.asset.domain.assetrecord.creator.AssetInOutRecordCreator;
import cn.gyw.backend.asset.domain.assetrecord.query.AssetInOutRecordQuery;
import cn.gyw.backend.asset.domain.assetrecord.vo.AssetInOutRecordVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AssetInOutRecordService {
    /**
     * create
     */
    Long createAssetInOutRecord(List<String> uniqueCodes, AssetInOutRecordCreator creator);

    /**
     * findById
     */
    AssetInOutRecordVO findById(Long id);

    /**
     * findByPage
     */
    Page<AssetInOutRecordVO> findByPage(PageRequestWrapper<AssetInOutRecordQuery> query);
}
