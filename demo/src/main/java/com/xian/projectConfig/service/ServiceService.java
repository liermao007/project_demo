package com.xian.projectConfig.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ReflectUtil;
import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.TableDataInfo;
import com.xian.projectConfig.domain.ServiceInfo;
import com.xian.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
@Service
public class ServiceService {

    private String cacheName = "config";
    private String key = "list";

    public List<ServiceInfo> query(ServiceInfo config) {
        List<ServiceInfo> list = getList();
        if(CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(p -> checkFilter(config, p)).collect(Collectors.toList());
    }

    public TableDataInfo<ServiceInfo> page(ServiceInfo config, PageQuery pageQuery) {
        List<ServiceInfo> list = query(config);
        TableDataInfo<ServiceInfo> result = TableDataInfo.build();
        result.setTotal(list.size());
        result.setRows(ListUtil.sub(list, (pageQuery.getPageNum() - 1) * pageQuery.getPageSize(), pageQuery.getPageNum() * pageQuery.getPageSize()));
        return result;
    }

    public List<ColumnConfig> getColumns() {
        return ListUtil.of(
            new ColumnConfig("key", "关键字", "string", "20", "service"),
            new ColumnConfig("name", "姓名", "string", "40","service"),
            new ColumnConfig("status", "订阅状态", "string", "10","service")
        );
    }

    public int update(ServiceInfo config) {
        int result = 0;
        List<ServiceInfo> list = getList();
        if(config != null && config.getKey() != null && CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getKey().equals(config.getKey())) {
                    list.get(i).setStatus(config.getStatus());
                    CacheUtils.put(cacheName, key, list);
                    result = 1;
                    break;
                }
            }
        }
        return result;
    }

    private List<ServiceInfo> getList() {
        List<ServiceInfo> list = CacheUtils.get(cacheName, key);
        if(CollUtil.isEmpty(list)) {
            list = ListUtil.of(
                new ServiceInfo("patient", "患者服务", "1"),
                new ServiceInfo("doctor", "医生服务", "1")
            );
            CacheUtils.put(cacheName, key, list);
        }
        return list;
    }

    private boolean checkFilter(ServiceInfo source, ServiceInfo target) {
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
