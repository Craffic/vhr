package com.craffic.vhr.server.controller.emp;

import com.craffic.vhr.server.common.RespBean;
import com.craffic.vhr.server.common.RespPageBean;
import com.craffic.vhr.server.domain.*;
import com.craffic.vhr.server.service.*;
import com.craffic.vhr.server.util.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;
    /**
     * 分页查询
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword, Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size,keyword, employee, beginDateScope);
    }

    /**
     * 添加员工
     */
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        if (employeeService.addEmp(employee) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }


    /**
     * 查询所有民族
     */
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    /**
     * 查询所有政治面貌
     */
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    /**
     * 查询所有职称
     */
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    /**
     * 查询所有职位
     */
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    /**
     * 员工id自增1
     */
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        RespBean respBean = RespBean.build().setStatus(200).setObj(String.format("%08d", employeeService.maxWorkID() + 1));
        return respBean;
    }

    /**
     * 获取所有部门节点 - 用于员工添加页面的所属部门的部门树展示
     * @return
     */
    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return departmentService.queryAllDepartments();
    }

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteEmpByEid(@PathVariable Integer id) {
        if (employeeService.deleteEmpByEid(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 到处员工信息到excel表
     */
    @GetMapping("/export/emp_info")
    public ResponseEntity<byte[]> exportEmpsData(){
        List<Employee> employeeList = (List<Employee>) employeeService.getEmployeeByPage(null, null, null, new Employee(), null).getData();
        return POIUtils.exportEmpData(employeeList);
    }

    /**
     * 导入员工信息
     */
    @PostMapping("/import/emp_info")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file, nationService.getAllNations(), politicsstatusService.getAllPoliticsstatus(), departmentService.getAllDepartmentsWithOutChildren(), positionService.getAllPositions(), jobLevelService.getAllJobLevels());
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}
