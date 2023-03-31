package cn.gyw.individual.commons.order.filter;

import cn.gyw.individual.commons.order.model.BaseContext;

/**
 * 过滤器链
 * 作用：
 * 1）维护Filter关系
 *
 * @date 2023/3/31
 */
public interface FilterChain<T extends BaseContext> {

    /**
     * 处理上下文
     *
     * @param context 参数
     */
    void handle(T context);

    /**
     * 执行下一个Filter
     *
     * @param context 参数
     */
    void fireNext(T context);
}
