package com.xian.projectConfig.controller;

import cn.hutool.core.collection.ListUtil;
import com.xian.common.ColumnConfig;
import com.xian.common.PageQuery;
import com.xian.common.R;
import com.xian.common.TableDataInfo;
import com.xian.projectConfig.domain.ServiceInfo;
import com.xian.projectConfig.service.ServiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订阅配置
 * @author lgx
 */
@RestController
@RequestMapping("/config")
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("page")
    public TableDataInfo<ServiceInfo> page(ServiceInfo config, PageQuery pageQuery){
        return serviceService.page(config, pageQuery);
    }

    /**
     * 查询
     * @return
     */
    @PostMapping("list")
    public R<List<ServiceInfo>> list(ServiceInfo config){
        return R.ok(serviceService.query(config));
    }

    /**
     * 获取配置元素
     * @return
     */
    @PostMapping("columns")
    public R<List<ColumnConfig>> getColumns() {
        return R.ok(serviceService.getColumns());
    }

    /**
     * 根据Key修改状态
     * @return
     */
    @PostMapping("update")
    public R<Void> update(ServiceInfo config) {
        int result = serviceService.update(config);
        return result > 0 ? R.ok() : R.fail();
    }

    /**
     * 获取所有对象元素
     * @return
     */
    @PostMapping("getAllModalColumns")
    public R<List<ColumnConfig>> getAllModalColumns() {
        List list = ListUtil.of(
                new ColumnConfig("key", "关键字", "string", "20", "service"),
                new ColumnConfig("name", "姓名", "string", "40","service"),
                new ColumnConfig("status", "订阅状态", "string", "10","service"),
                new ColumnConfig("id", "主键", "long", "20", "patient"),
                new ColumnConfig("name", "姓名", "string", "40", "patient"),
                new ColumnConfig("sex", "性别", "string", "10", "patient"),
                new ColumnConfig("age", "年龄", "int", "4", "patient"),
                new ColumnConfig("phone", "手机号", "string", "20", "patient"),
                new ColumnConfig("idCard", "身份证号", "string", "20", "patient"),
                new ColumnConfig("doctorId", "看病医生ID", "long", "20", "patient"),
                new ColumnConfig("id", "主键", "long", "20", "doctor"),
                new ColumnConfig("name", "姓名", "string", "40", "doctor"),
                new ColumnConfig("sex", "性别", "string", "10", "doctor"),
                new ColumnConfig("age", "年龄", "int", "4", "doctor"),
                new ColumnConfig("phone", "手机号", "string", "20", "doctor"),
                new ColumnConfig("idCard", "身份证号", "string", "20", "doctor"),
                new ColumnConfig("id", "主键", "long", "20","patientDoctor"),
                new ColumnConfig("name", "姓名", "string", "40","patientDoctor"),
                new ColumnConfig("sex", "性别", "string", "10","patientDoctor"),
                new ColumnConfig("age", "年龄", "int", "4","patientDoctor"),
                new ColumnConfig("phone", "手机号", "string", "20","patientDoctor"),
                new ColumnConfig("idCard", "身份证号", "string", "20","patientDoctor"),
                new ColumnConfig("doctorId", "医生主键", "long", "20","patientDoctor"),
                new ColumnConfig("doctorName", "医生姓名", "string", "40","patientDoctor"),
                new ColumnConfig("doctorSex", "医生性别", "string", "10","patientDoctor"),
                new ColumnConfig("doctorAge", "医生年龄", "int", "4","patientDoctor"),
                new ColumnConfig("doctorPhone", "医生手机号", "string", "20","patientDoctor"),
                new ColumnConfig("doctorIdCard", "医生身份证号", "string", "20","patientDoctor"));
        return R.ok(list);
    }
}
