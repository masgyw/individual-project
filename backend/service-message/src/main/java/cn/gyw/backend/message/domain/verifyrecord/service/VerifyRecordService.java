// ---Auto Generated ---
package cn.gyw.backend.message.domain.verifyrecord.service;

import cn.gyw.backend.message.domain.verifyrecord.creator.VerifyRecordCreator;
import cn.gyw.backend.message.domain.verifyrecord.query.VerifyRecordQuery;
import cn.gyw.backend.message.domain.verifyrecord.updater.VerifyRecordUpdater;
import cn.gyw.backend.message.domain.verifyrecord.vo.VerifyRecordVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;

import java.lang.Long;

import org.springframework.data.domain.Page;

public interface VerifyRecordService {
    /**
     * create
     */
    Long createVerifyRecord(VerifyRecordCreator creator);

    /**
     * update
     */
    void updateVerifyRecord(VerifyRecordUpdater updater);

    /**
     * valid
     */
    void validVerifyRecord(Long id);

    /**
     * invalid
     */
    void invalidVerifyRecord(Long id);

    /**
     * findById
     */
    VerifyRecordVO findById(Long id);

    /**
     * findByPage
     */
    Page<VerifyRecordVO> findByPage(PageRequestWrapper<VerifyRecordQuery> query);

    /**
     * 校验发送间隔
     *
     * @param account
     * @param templateCode
     * @return
     */
    boolean checkSendInterval(String account, String templateCode);

    /**
     * 校验最多发送次数
     *
     * @param account
     * @param templateCode
     * @return
     */
    boolean checkSendMaxTimes(String account, String templateCode);
}
