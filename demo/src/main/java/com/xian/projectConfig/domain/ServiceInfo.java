package com.xian.projectConfig.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lgx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo {

    /**
     * 关键字
     */
    private String key;

    /**
     * 名称
     */
    private String name;

    /**
     * 订阅状态
     */
    private String status;
}
