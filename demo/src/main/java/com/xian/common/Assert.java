package com.xian.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.xian.exception.ServiceException;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author lgx
 */
public class Assert {

    public static <T extends CharSequence> void notBlank(T text, String errMsg) {
        if(StrUtil.isBlank(text)) {
            throw new ServiceException(errMsg);
        }
    }

    public static <E, T extends Iterable<E>> void notEmpty(T collection, String errMsg) {
        if(CollUtil.isEmpty(collection)) {
            throw new ServiceException(errMsg);
        }
    }

    public static <K, V, T extends Map<K, V>> void notEmpty(T map, String errMsg) {
        if (MapUtil.isEmpty(map)) {
            throw new ServiceException(errMsg);
        }
    }
}
