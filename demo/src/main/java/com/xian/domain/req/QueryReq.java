package com.xian.domain.req;

import java.util.List;
import java.util.Map;

/**
 * @author lgx
 */
public class QueryReq {

    /**
     * 保存值对应KEY
     */
   private String key;
   /**
    * 查询条件
    */
   private Map<String, String> params;

    /**
     * 查询需要的结果
     */
   private List<String> columns;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
