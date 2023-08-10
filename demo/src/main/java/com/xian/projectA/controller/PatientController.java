package com.xian.projectA.controller;
import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.R;
import com.xian.common.TableDataInfo;
import com.xian.projectA.domain.Patient;
import com.xian.projectA.service.PatientService;
import com.xian.projectConfig.utils.ServiceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 单对象维护/患者信息
 * @author lgx
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    private String key = "patient";

    @Resource
    private PatientService patientService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("page")
    public TableDataInfo<Patient> page(Patient patient, PageQuery pageQuery){
        return ServiceUtils.active(key) ? patientService.page(patient, pageQuery) : TableDataInfo.build();
    }

    /**
     * 查询
     * @return
     */
    @PostMapping("list")
    public R<List<Patient>> list(Patient patient){
        return ServiceUtils.active(key) ? R.ok(patientService.query(patient)) : R.ok(new ArrayList<>());
    }

    /**
     * 新增数据
     * @param patient
     * @return
     */
    @PostMapping("add")
    public R<Long> add(Patient patient){
        return ServiceUtils.active(key) ? R.ok(patientService.add(patient)) : R.fail();
    }

    /**
     * 修改数据
     * @return
     */
    @PostMapping("update")
    public R<Void> update(Patient patient) {
        if(!ServiceUtils.active(key)) {
            return R.fail();
        }
        int result = patientService.update(patient);
        return result > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除数据
     * @param id 主键
     * @return
     */
    @PostMapping("delete")
    public R<Void> delete(Long id){
        if(!ServiceUtils.active(key)) {
            return R.fail();
        }
        int result = patientService.delete(id);
        return result > 0 ? R.ok() : R.fail();
    }

    /**
     * 获取对象元素
     * @return
     */
    @PostMapping("columns")
    public R<List<ColumnConfig>> getColumns() {
        if(!ServiceUtils.active(key)) {
            return R.ok(new ArrayList<>());
        }
        return R.ok(patientService.getColumns());
    }
}
