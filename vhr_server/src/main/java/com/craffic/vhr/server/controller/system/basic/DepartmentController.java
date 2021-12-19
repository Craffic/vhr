package com.craffic.vhr.server.controller.system.basic;

import com.craffic.vhr.server.domain.Department;
import com.craffic.vhr.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有部门数据
     */
    @GetMapping("/query/all_depts")
    public List<Department> queryAllDepartments(){
        return departmentService.queryAllDepartments();
    }

}
