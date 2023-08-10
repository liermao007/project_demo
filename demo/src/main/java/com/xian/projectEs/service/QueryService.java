package com.xian.projectEs.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ReflectUtil;
import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.TableDataInfo;
import com.xian.projectEs.domain.PatientInfo;
import com.xian.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
@Service
public class QueryService {

    private String cacheName = "doctor";
    private String key = "list";

    public List<PatientInfo> query(PatientInfo patient) {
        List<PatientInfo> list = CacheUtils.get(cacheName, key);
        if(CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(p -> checkFilter(patient, p)).collect(Collectors.toList());
    }

    public TableDataInfo<PatientInfo> page(PatientInfo patient, PageQuery pageQuery) {
        List<PatientInfo> list = query(patient);
        TableDataInfo<PatientInfo> result = TableDataInfo.build();
        result.setTotal(list.size());
        result.setRows(ListUtil.sub(list, (pageQuery.getPageNum() - 1) * pageQuery.getPageSize(), pageQuery.getPageNum() * pageQuery.getPageSize()));
        return result;
    }

    public List<ColumnConfig> getColumns() {
        return ListUtil.of(
            new ColumnConfig("id", "主键", "long", "20","patientDoctor"),
            new ColumnConfig("name", "姓名", "string", "40","patientDoctor"),
            new ColumnConfig("sex", "性别", "string", "10","patientDoctor"),
            new ColumnConfig("age", "年龄", "int", "4","patientDoctor"),
            new ColumnConfig("phone", "手机号", "string", "20","patientDoctor"),
            new ColumnConfig("idCard", "身份证号", "string", "20","patientDoctor"),
            new ColumnConfig("doctorId", "医生主键", "long", "20","patientDoctor"),
            new ColumnConfig("doctorName", "医生姓名", "string", "40","patientDoctor"),
            new ColumnConfig("doctorSex", "医生性别", "string", "10","patientDoctor"),
            new ColumnConfig("doctorAge", "医生年龄", "int", "4","patientDoctor"),
            new ColumnConfig("doctorPhone", "医生手机号", "string", "20","patientDoctor"),
            new ColumnConfig("doctorIdCard", "医生身份证号", "string", "20","patientDoctor")
        );
    }

    private boolean checkFilter(PatientInfo source, PatientInfo target) {
        if(source == null || target == null) {
            return false;
        }

        for (ColumnConfig column : getColumns()) {
            if(!equals(ReflectUtil.getFieldValue(source, column.getName()), ReflectUtil.getFieldValue(target, column.getName()))) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(Object source, Object target) {
        if(source == null || source.equals(target)) {
            return true;
        }
        return false;
    }
}
