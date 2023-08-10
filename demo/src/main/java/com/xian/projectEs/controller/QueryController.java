package com.xian.projectEs.controller;

import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.R;
import com.xian.common.TableDataInfo;
import com.xian.projectEs.domain.PatientInfo;
import com.xian.projectEs.service.QueryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 综合查询
 * @author lgx
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Resource
    private QueryService queryService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("page")
    public TableDataInfo<PatientInfo> page(PatientInfo patient, PageQuery pageQuery){
        return queryService.page(patient, pageQuery);
    }

    /**
     * 查询
     * @return
     */
    @PostMapping("list")
    public R<List<PatientInfo>> list(PatientInfo patient){
        return R.ok(queryService.query(patient));
    }

    /**
     * 获取对象元素
     * @return
     */
    @PostMapping("columns")
    public R<List<ColumnConfig>> getColumns() {
        return R.ok(queryService.getColumns());
    }
}
