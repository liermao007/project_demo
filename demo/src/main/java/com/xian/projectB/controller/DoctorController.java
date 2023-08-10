package com.xian.projectB.controller;

import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.R;
import com.xian.common.TableDataInfo;
import com.xian.projectB.domain.Doctor;
import com.xian.projectB.service.DoctorService;
import com.xian.projectConfig.utils.ServiceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 单对象维护/医生信息
 * @author lgx
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private String key = "doctor";

    @Resource
    private DoctorService doctorService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("page")
    public TableDataInfo<Doctor> page(Doctor doctor, PageQuery pageQuery){
        return ServiceUtils.active(key) ? doctorService.page(doctor, pageQuery) : TableDataInfo.build();
    }

    /**
     * 查询
     * @return
     */
    @PostMapping("list")
    public R<List<Doctor>> list(Doctor doctor){
        return ServiceUtils.active(key) ? R.ok(doctorService.query(doctor)) : R.ok(new ArrayList<>());
    }

    /**
     * 新增数据
     * @param doctor
     * @return
     */
    @PostMapping("add")
    public R<Long> add(Doctor doctor){
        if(!ServiceUtils.active(key)) {
            return R.fail();
        }
        return R.ok(doctorService.add(doctor));
    }

    /**
     * 修改数据
     * @return
     */
    @PostMapping("update")
    public R<Void> update(Doctor doctor) {
        if(!ServiceUtils.active(key)) {
            return R.fail();
        }
        int result = doctorService.update(doctor);
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
        int result = doctorService.delete(id);
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
        return R.ok(doctorService.getColumns());
    }
}
