package com.xian.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.xian.common.Assert;
import com.xian.common.PageResult;
import com.xian.domain.req.*;
import com.xian.utils.DataUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
@Service
public class SingleService {

    public int add(AddReq req) {
        Assert.notBlank(req.getKey(), "key不能为空");
        Assert.notEmpty(req.getData(), "保存数据不能为空");

        return DataUtils.add(req.getKey(), req.getData()) ? 1 : 0;
    }

    public int update(UpdateReq req) {
        Assert.notBlank(req.getKey(), "key不能为空");
        Assert.notEmpty(req.getData(), "保存数据不能为空");

        return DataUtils.update(req.getKey(), req.getData()) ? 1 : 0;
    }

    public int delete(DeleteReq req) {
        Assert.notBlank(req.getKey(), "key不能为空");
        Assert.notEmpty(req.getParams(), "条件不能为空");

        List<Map<String, String>> datas = DataUtils.getByKey(req.getKey());
        for (int i = 0; i < datas.size(); i++) {
            if(checkFilter(datas.get(i), req.getParams())) {
                datas.remove(datas.get(i));
                i--;
            }
        }
        return 1;
    }

    public List<Map<String, String>> query(QueryReq req) {
        Assert.notBlank(req.getKey(), "key不能为空");
        Assert.notEmpty(req.getColumns(), "返回响应列要求不能为空");

        List<Map<String, String>> datas = DataUtils.getByKey(req.getKey());
        return datas.stream()
                .filter(data -> checkFilter(data, req.getParams()))
                .map(data -> MapUtil.getAny(data, req.getColumns().stream().toArray(String[]::new)))
                .collect(Collectors.toList());
    }

    public PageResult<Map<String, String>> page(PageReq req) {
        List<Map<String, String>> result = query(req);
        return new PageResult<>(result, req.getPageNo(), req.getPageSize());
    }

    private boolean checkFilter(Map<String, String> data, Map<String, String> params) {
        if(MapUtil.isEmpty(params)) {
            return true;
        }
        for (Map.Entry<String, String> param : params.entrySet()) {
            if(StrUtil.isNotBlank(param.getValue()) && !param.getValue().equals(data.get(param.getKey()))) {
                return false;
            }
        }
        return true;
    }
}
