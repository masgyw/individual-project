package cn.gyw.individual.commons.utils;

import cn.gyw.individual.commons.model.PageData;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageHelperUtil {

    /**
     * 将PageHelper分页后的Page转为分页信息
     */
    public static <T> PageData<T> resetPage(List<T> dataList) {
        PageInfo<T> pageInfo = new PageInfo<>(dataList);
        return new PageData<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(),
                pageInfo.getPageSize(), pageInfo.getPages());
    }

    private PageHelperUtil() {
    }
}
