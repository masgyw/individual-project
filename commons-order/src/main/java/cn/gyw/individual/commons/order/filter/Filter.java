package cn.gyw.individual.commons.order.filter;

import cn.gyw.individual.commons.order.model.BaseContext;

/**
 * 过滤器
 * 作用：仅做过滤逻辑用
 *
 * @date 2023/3/31
 */
public interface Filter<T extends BaseContext> {

    /**
     * 执行过滤逻辑
     *
     * @param context     参数上下文
     * @param filterChain 过滤器链
     */
    void doFilter(T context, FilterChain<T> filterChain);

}
