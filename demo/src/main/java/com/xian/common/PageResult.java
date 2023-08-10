package com.xian.common;

import cn.hutool.core.collection.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lgx
 */
public class PageResult<T> {

    private int pageNo;

    private int pageSize;

    private int count;

    private List<T> list;

    public PageResult() {
    }

    public PageResult(List<T> list, int pageNo, int pageSize) {
        if(list == null) {
            list = new ArrayList<>();
        }
        this.count = list.size();
        this.list = ListUtil.sub(list, (pageNo - 1) * pageSize, pageNo * pageSize);
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
