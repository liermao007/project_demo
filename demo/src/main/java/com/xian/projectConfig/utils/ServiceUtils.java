package com.xian.projectConfig.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.xian.projectConfig.domain.ServiceInfo;
import com.xian.utils.CacheUtils;

import java.util.List;

/**
 * @author lgx
 */
public class ServiceUtils {

    private static String cacheName = "config";
    private static String key = "list";

    public static boolean active(String key) {
        List<ServiceInfo> list = getList();
        if(StrUtil.isNotBlank(key) && CollUtil.isNotEmpty(list)) {
            for (ServiceInfo serviceInfo : list) {
                if(key.equals(serviceInfo.getKey())) {
                    return "1".equals(serviceInfo.getStatus());
                }
            }
        }
        return false;
    }

    private static List<ServiceInfo> getList() {
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
}
