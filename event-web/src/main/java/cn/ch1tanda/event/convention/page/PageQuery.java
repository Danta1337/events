package cn.ch1tanda.event.convention.page;

import java.io.Serializable;
import java.util.Objects;

/**
 * 分页查询公共规约请求对象
 * @param <T> 查询条件
 */
public class PageQuery<T> implements Serializable {
    private static final long serialVersionUID = -9209818098642983682L;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 每页数据量
     */
    private Integer pageNumber;

    /**
     * 查询条件
     */
    private T condition;

    public PageQuery() {

    }

    public PageQuery(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public PageQuery(Integer pageSize, Integer pageNumber, T condition) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.condition = condition;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageQuery<?> pageQuery = (PageQuery<?>) o;
        return Objects.equals(pageSize, pageQuery.pageSize) && Objects.equals(pageNumber, pageQuery.pageNumber) && Objects.equals(condition, pageQuery.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageSize, pageNumber, condition);
    }
}
