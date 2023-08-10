package com.xian.domain.req;

import java.util.List;
import java.util.Map;

/**
 * @author lgx
 */
public class AddReq {

    /**
     * 保存值对应KEY
     */
   private String key;
   /**
    * 保存值
    */
   private List<Map<String, String>> data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}
