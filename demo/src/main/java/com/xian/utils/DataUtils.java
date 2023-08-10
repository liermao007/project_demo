package com.xian.utils;

import cn.hutool.core.util.IdUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgx
 */
public class DataUtils {

    public static Map<String, List<Map<String, String>>> datas = new HashMap<>();

    public static boolean add(String key, List<Map<String, String>> data) {
        data.forEach(obj -> {
            add(key, obj);
        });
        return true;
    }

    public static boolean add(String key, Map<String, String> data) {
        if(!datas.containsKey(key)) {
            datas.put(key, new ArrayList<>());
        }
        data.put("id", IdUtil.getSnowflakeNextIdStr());
        datas.get(key).add(data);
        return true;
    }

    public static boolean update(String key, List<Map<String, String>> data) {
        for (Map<String, String> obj : data) {
            if(!update(key, obj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean update(String key, Map<String, String> data) {
        if(!datas.containsKey(key)) {
            return false;
        }
        datas.get(key).stream().filter(d -> d.get("id").equals(data.get("id"))).forEach(d -> {
            d.putAll(data);
        });
        return true;
    }

    public static List<Map<String, String>> getByKey(String key) {
        if(datas.containsKey(key)) {
            return datas.get(key);
        }
        return new ArrayList<>();
    }
}
