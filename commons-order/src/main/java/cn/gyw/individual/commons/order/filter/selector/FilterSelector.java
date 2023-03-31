package cn.gyw.individual.commons.order.filter.selector;

import java.util.List;

/**
 * @date 2023/3/31
 */
public interface FilterSelector {

    /**
     * 当前过滤器是否匹配
     *
     * @param curFilterName 当前过滤器名称
     * @return
     */
    boolean matchFilter(String curFilterName);

    /**
     * 获取所有过滤器名称
     *
     * @return
     */
    List<String> getFilterNames();
}
