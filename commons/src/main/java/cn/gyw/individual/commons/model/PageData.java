package cn.gyw.individual.commons.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据对象
 */
@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 是否更多
     */
    private Boolean hasMore = false;
    /**
     * 数据列表
     */
    private List<T> records;

    public PageData(List<T> records, Long total, Integer pageNum, Integer pageSize, Integer totalPage) {
        this.records = records;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
    }

    public static <T> PageData of(List<T> list, Long total, Integer pageNum, Integer pageSize, Integer totalPage) {
        return new PageData(list, total, pageNum, pageSize, totalPage);
    }
}
