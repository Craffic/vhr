package com.craffic.vhr.web.controller.system.basic;

import com.craffic.vhr.model.Department;
import com.craffic.vhr.model.RespBean;
import com.craffic.vhr.service.DepartmentService;
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

    /**
     * 删除部门
     */
    @DeleteMapping("/del_dept/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下有子部门，删除失败");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
