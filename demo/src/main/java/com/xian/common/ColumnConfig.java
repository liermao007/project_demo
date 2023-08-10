package com.xian.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lgx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnConfig {

    /**
     * 列名称
     */
    private String name;

    /**
     * 列描述
     */
    private String comment;

    /**
     * 类型
     */
    private String type;

    /**
     * 字段长度
     */
    private String len;

    /**
     * 对象名称
     */
    private String objectName;
}
