package cn.gyw.individual.commons.order.filter;

import cn.gyw.individual.commons.order.model.BaseContext;

import java.util.Objects;

/**
 * @date 2023/3/31
 */
public class DefaultFilterChain<T extends BaseContext> implements FilterChain<T> {

    private Filter<T> filter;

    private FilterChain<T> next;

    public DefaultFilterChain(Filter<T> filter, FilterChain<T> next) {
        this.filter = filter;
        this.next = next;
    }

    /**
     * 处理上下文
     *
     * @param context 参数
     */
    @Override
    public void handle(T context) {
        filter.doFilter(context, this);
    }

    /**
     * 执行下一个Filter
     *
     * @param context 参数
     */
    @Override
    public void fireNext(T context) {
        FilterChain<T> nextChain = this.next;
        if (Objects.nonNull(nextChain)) {
            nextChain.handle(context);
        }
    }
}
