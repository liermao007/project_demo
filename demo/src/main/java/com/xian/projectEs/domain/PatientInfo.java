package com.xian.projectEs.domain;

import lombok.Data;

/**
 *
 * @author lgx
 */
@Data
public class PatientInfo {

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

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 医生性别
     */
    private String doctorSex;

    /**
     * 医生年龄
     */
    private Integer doctorAge;

    /**
     * 医生手机号
     */
    private String doctorPhone;

    /**
     * 医生身份证号
     */
    private String doctorIdCard;
}
