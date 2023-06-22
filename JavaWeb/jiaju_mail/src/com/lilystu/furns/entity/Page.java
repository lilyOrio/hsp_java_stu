package com.lilystu.furns.entity;

import java.util.List;

/**
 * 是一个分页数据模型
 * 包含分页数据信息
 */
public class Page<T> {
    //每页显示多少数据其它地方也需要使用
    //CTRL+shift+u切换大小写
    public static final Integer PAGE_SIZE = 3;

    //显示第几页
    private Integer pageNo;
    //每页显示多少行
    private Integer pageSize = PAGE_SIZE;
    //一共多少页,由计算得到
    private Integer pageTotalCount;
    //一共多少条记录==》DAO
    private Integer totalRow;
    //当前页显示的数据==》DAO
    private List<T> items;
    //分页导航字符串url
    private String url;

    public Page(Integer pageNo, Integer pageSize, Integer pageTotalCount, Integer totalRow, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.totalRow = totalRow;
        this.items = items;
        this.url = url;
    }

    public Page() {

    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", totalRow=" + totalRow +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    //Page哪些属性是可以直接从数据库获取
    //就把这个任务放到DAO层
}
