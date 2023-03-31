package cn.gyw.individual.commons.order.model;

import cn.gyw.individual.commons.order.constants.SceneEnum;
import cn.gyw.individual.commons.order.filter.selector.FilterSelector;

/**
 * @date 2023/3/31
 */
public abstract class AbstractBaseContext implements BaseContext {

    private final SceneEnum sceneEnum;

    private final FilterSelector selector;

    public AbstractBaseContext(SceneEnum sceneEnum, FilterSelector selector) {
        this.sceneEnum = sceneEnum;
        this.selector = selector;
    }

    @Override
    public SceneEnum getScene() {
        return sceneEnum;
    }

    @Override
    public FilterSelector getFilterSelector() {
        return selector;
    }
}
