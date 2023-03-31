package cn.gyw.individual.commons.order.filter;

import cn.gyw.individual.commons.order.model.BaseContext;

/**
 * @date 2023/3/31
 */
public abstract class AbstractFilter<T extends BaseContext> implements Filter<T> {

    @Override
    public void doFilter(T context, FilterChain<T> filterChain) {
        if (context.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
            handle(context);
        }
        if (context.continueChain()) {
            filterChain.fireNext(context);
        }
    }

    protected abstract void handle(T context);
}
