package com.xian.controller;

import com.xian.common.Result;
import com.xian.domain.req.ConfigSaveReq;
import com.xian.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配置
 * @author lgx
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 保存
     * @param req
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ConfigSaveReq req) {
        return Result.of(configService.save(req));
    }

    /**
     * 根据key获取配置
     * @param key
     * @return
     */
    @PostMapping("getByKey")
    public Result getByKey(@RequestParam(value = "key") String key) {
        return Result.success(configService.getConfig(key));
    }
}
