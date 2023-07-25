package cn.gyw.backend.message.domain.verifyrecord.service.checker;

import cn.gyw.backend.message.domain.verifyrecord.service.VerifyRecordService;
import cn.gyw.individual.commons.utils.SpringContextUtil;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * @author yuewu.guan
 * @date 2023/7/24
 */
public class SendIntervalChecker implements ElementMatcher<CheckContext> {
    @Override
    public boolean matches(CheckContext checkContext) {
        VerifyRecordService verifyRecordService = SpringContextUtil.getBean(VerifyRecordService.class);
        return verifyRecordService.checkSendInterval(checkContext.getAccount(), checkContext.getTemplateCode());
    }
}
