package com.xian.domain.req;

import java.util.List;
import java.util.Map;

/**
 * @author lgx
 */
public class PageReq extends QueryReq{

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 每页数量
     */
    private int pageSize;

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
}
