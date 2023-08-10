package com.xian.domain.req;

import java.util.List;
import java.util.Map;

/**
 * @author lgx
 */
public class DeleteReq {

    /**
     * 保存值对应KEY
     */
   private String key;

    /**
     * 删除条件
     */
    private Map<String, String> params;

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
}
