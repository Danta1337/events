package cn.ch1tanda.event.convention.page;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 分页查询公共响应对象
 * @param <T> 响应数据
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -9052105891606353196L;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 每页数据量
     */
    private Integer pageNumber;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 总页数
     */
    private Integer totalPageSize;

    /**
     * 响应数据
     */
    private List<T> data;

    public PageResult() {
    }

    public PageResult(Integer pageSize, Integer pageNumber, Integer totalPageSize, List<T> data) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPageSize = totalPageSize;
        this.data = data;
        this.hasNext = totalPageSize > pageSize;
    }

    public PageResult(Integer pageSize, Integer pageNumber, Boolean hasNext, Integer totalPageSize, List<T> data) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.hasNext = hasNext;
        this.totalPageSize = totalPageSize;
        this.data = data;
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

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(Integer totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResult<?> that = (PageResult<?>) o;
        return Objects.equals(pageSize, that.pageSize) && Objects.equals(pageNumber, that.pageNumber) && Objects.equals(hasNext, that.hasNext) && Objects.equals(totalPageSize, that.totalPageSize) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageSize, pageNumber, hasNext, totalPageSize, data);
    }
}
