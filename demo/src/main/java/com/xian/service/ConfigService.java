package com.xian.service;

import com.xian.common.Assert;
import com.xian.domain.req.ConfigSaveReq;
import com.xian.utils.ConfigUtils;
import org.springframework.stereotype.Service;

/**
 * @author lgx
 */
@Service
public class ConfigService {

    public int save(ConfigSaveReq req) {
        Assert.notBlank(req.getKey(), "key不能为空");
        return ConfigUtils.save(req.getKey(), req.getConfig()) ? 1 : 0;
    }

    public String getConfig(String key) {
        return ConfigUtils.getConfigByKey(key);
    }
}
