package cn.gyw.individual.commons.order.filter.selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @date 2023/3/31
 */
public class LocalListBasedFilterSelector implements FilterSelector {

    /**
     * Filter Simple 类名集合
     */
    private List<String> filterNameList = new ArrayList<>();

    /**
     * 当前过滤器是否匹配
     *
     * @param classSimpleName 当前过滤器名称
     * @return
     */
    @Override
    public boolean matchFilter(String classSimpleName) {
        return filterNameList.stream().anyMatch(filterName -> Objects.equals(filterName, classSimpleName));
    }

    /**
     * 获取所有过滤器名称
     *
     * @return
     */
    @Override
    public List<String> getFilterNames() {
        return filterNameList;
    }

    public void addFilter(Class<?> clz) {
        filterNameList.add(clz.getSimpleName());
    }

    public void addFilter(String className) {
        filterNameList.add(className);
    }

    public void addFilters(List<String> filterNames) {
        filterNameList.addAll(filterNames);
    }

    public LocalListBasedFilterSelector() {
    }

    public LocalListBasedFilterSelector(List<String> filterNameList) {
        this.filterNameList = filterNameList;
    }
}
