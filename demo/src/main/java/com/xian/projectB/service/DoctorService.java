package com.xian.projectB.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.TableDataInfo;
import com.xian.projectB.domain.Doctor;
import com.xian.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
@Service
public class DoctorService {

    private String cacheName = "doctor";
    private String key = "list";

    public Long add(Doctor doctor) {
        List<Doctor> list = CacheUtils.get(cacheName, key);
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(doctor);
        doctor.setId(IdUtil.getSnowflakeNextId());
        CacheUtils.put(cacheName, key, list);
        return doctor.getId();
    }

    public int update(Doctor doctor) {
        int result = 0;
        List<Doctor> list = CacheUtils.get(cacheName, key);
        if(doctor.getId() != null && CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getId().equals(doctor.getId())) {
                    list.set(i, doctor);
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
        List<Doctor> list = CacheUtils.get(cacheName, key);
        if(id != null && CollUtil.isNotEmpty(list)) {
            for (Doctor p : list) {
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

    public List<Doctor> query(Doctor doctor) {
        List<Doctor> list = CacheUtils.get(cacheName, key);
        if(CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(p -> checkFilter(doctor, p)).collect(Collectors.toList());
    }

    public TableDataInfo<Doctor> page(Doctor doctor, PageQuery pageQuery) {
        List<Doctor> list = query(doctor);
        TableDataInfo<Doctor> result = TableDataInfo.build();
        result.setTotal(list.size());
        result.setRows(ListUtil.sub(list, (pageQuery.getPageNum() - 1) * pageQuery.getPageSize(), pageQuery.getPageNum() * pageQuery.getPageSize()));
        return result;
    }

    public List<ColumnConfig> getColumns() {
        return ListUtil.of(
            new ColumnConfig("id", "主键", "long", "20", "doctor"),
            new ColumnConfig("name", "姓名", "string", "40", "doctor"),
            new ColumnConfig("sex", "性别", "string", "10", "doctor"),
            new ColumnConfig("age", "年龄", "int", "4", "doctor"),
            new ColumnConfig("phone", "手机号", "string", "20", "doctor"),
            new ColumnConfig("idCard", "身份证号", "string", "20", "doctor")
        );
    }

    private boolean checkFilter(Doctor source, Doctor target) {
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
