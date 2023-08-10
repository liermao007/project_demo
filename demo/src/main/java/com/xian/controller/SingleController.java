package com.xian.controller;

import com.xian.common.PageResult;
import com.xian.common.Result;
import com.xian.domain.req.*;
import com.xian.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 单模块控制
 * @author lgx
 */
@RestController
@RequestMapping("/single")
public class SingleController {

    @Autowired
    private SingleService singleService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("page")
    public Result<PageResult<Map<String, String>>> page(@RequestBody PageReq req){
        return Result.success(singleService.page(req));
    }

    /**
     * 查询
     * @return
     */
    @PostMapping("list")
    public Result<List<Map<String, String>>> list(@RequestBody QueryReq req){
        return Result.success(singleService.query(req));
    }

    /**
     * 新增数据
     * @param req
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody AddReq req){
        return Result.of(singleService.add(req));
    }

    /**
     * 修改数据
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody UpdateReq req) {
        return Result.of(singleService.update(req));
    }

    /**
     * 删除数据
     * @return
     */
    @PostMapping("delete")
    public Result delete(@RequestBody DeleteReq req){
        return Result.of(singleService.delete(req));
    }
}
