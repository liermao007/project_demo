package com.xian.domain.req;

/**
 * @author lgx
 */
public class ConfigSaveReq {

    /**
     * 关键字
     */
    private String key;

    /**
     * 页面配置
     */
    private String config;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
