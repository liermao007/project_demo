package com.xian.projectA.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.TableDataInfo;
import com.xian.projectA.domain.Patient;
import com.xian.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
@Service
public class PatientService {

    private String cacheName = "patient";
    private String key = "list";

    public Long add(Patient patient) {
        List<Patient> list = CacheUtils.get(cacheName, key);
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(patient);
        patient.setId(IdUtil.getSnowflakeNextId());
        CacheUtils.put(cacheName, key, list);
        return patient.getId();
    }

    public int update(Patient patient) {
        int result = 0;
        List<Patient> list = CacheUtils.get(cacheName, key);
        if(patient.getId() != null && CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getId().equals(patient.getId())) {
                    list.set(i, patient);
                    CacheUtils.put(cacheName, key, list);
                    result = 1;
                    break;
                }
            }
        }
        return result;
    }

    public int delete(Long id) {
        int result = 0;
        List<Patient> list = CacheUtils.get(cacheName, key);
        if(id != null && CollUtil.isNotEmpty(list)) {
            for (Patient p : list) {
                if(p.getId().equals(id)) {
                    list.remove(p);
                    CacheUtils.put(cacheName, key, list);
                    result = 1;
                    break;
                }
            }
        }
        return result;
    }

    public List<Patient> query(Patient patient) {
        List<Patient> list = CacheUtils.get(cacheName, key);
        if(CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(p -> checkFilter(patient, p)).collect(Collectors.toList());
    }

    public TableDataInfo<Patient> page(Patient patient, PageQuery pageQuery) {
        List<Patient> list = query(patient);
        TableDataInfo<Patient> result = TableDataInfo.build();
        result.setTotal(list.size());
        result.setRows(ListUtil.sub(list, (pageQuery.getPageNum() - 1) * pageQuery.getPageSize(), pageQuery.getPageNum() * pageQuery.getPageSize()));
        return result;
    }

    public List<ColumnConfig> getColumns() {
        return ListUtil.of(
            new ColumnConfig("id", "主键", "long", "20", "patient"),
            new ColumnConfig("name", "姓名", "string", "40", "patient"),
            new ColumnConfig("sex", "性别", "string", "10", "patient"),
            new ColumnConfig("age", "年龄", "int", "4", "patient"),
            new ColumnConfig("phone", "手机号", "string", "20", "patient"),
            new ColumnConfig("idCard", "身份证号", "string", "20", "patient"),
            new ColumnConfig("doctorId", "看病医生ID", "long", "20", "patient")
        );
    }

    private boolean checkFilter(Patient source, Patient target) {
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
