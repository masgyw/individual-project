package cn.gyw.individual.starters.extension.executor;

import lombok.Setter;

/**
 * @date 2023/7/21
 */
public interface BizScene {

    String getBizId();

    @Setter
    class DefaultBizScene implements BizScene {

        private String bizId;

        public static DefaultBizScene of(String bizId) {
            DefaultBizScene defaultBizScene = new DefaultBizScene();
            defaultBizScene.setBizId(bizId);
            return defaultBizScene;
        }

        @Override
        public String getBizId() {
            return bizId;
        }
    }
}
