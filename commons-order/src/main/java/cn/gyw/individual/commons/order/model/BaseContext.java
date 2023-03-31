package cn.gyw.individual.commons.order.model;

import cn.gyw.individual.commons.order.constants.SceneEnum;
import cn.gyw.individual.commons.order.filter.selector.FilterSelector;

/**
 * @date 2023/3/30
 */
public interface BaseContext {
    /**
     * 获取上下文场景类型
     *
     * @return
     */
    SceneEnum getScene();

    /**
     * 获取过滤器选择器
     *
     * @return
     */
    FilterSelector getFilterSelector();

    /**
     * 是否继续链
     *
     * @return true/false
     */
    boolean continueChain();
}
