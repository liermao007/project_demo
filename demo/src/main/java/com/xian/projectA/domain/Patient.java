package com.xian.projectA.domain;

import lombok.Data;

/**
 *
 * @author lgx
 */
@Data
public class Patient {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 医生主键
     */
    private Long doctorId;
}
