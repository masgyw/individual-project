package cn.gyw.backend.template.domain.verifyrule.rule;

import java.util.Optional;

public interface VerifyRule {

    /**
     * 验证
     * @param input 输入值
     * @return 验证结果
     */
    Optional<String> verify(String input);

    /**
     * 获取验证类型
     * @return 验证类型枚举
     */
    VerifyTypeEnum getVerifyType();
}
