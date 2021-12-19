package com.craffic.vhr.server.controller.system.basic;

import com.craffic.vhr.server.common.RespBean;
import com.craffic.vhr.server.domain.Department;
import com.craffic.vhr.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加部门
     */
    @PostMapping("/add_dept")
    public RespBean addDepartment(@RequestBody Department department){
        departmentService.addDept(department);
        if (department.getResult() == 1) {
            return RespBean.ok("添加成功！", department);
        }
        return RespBean.error("添加部门失败！");
    }
}
