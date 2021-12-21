package com.craffic.vhr.server.controller.emp;

import com.craffic.vhr.server.common.RespBean;
import com.craffic.vhr.server.common.RespPageBean;
import com.craffic.vhr.server.domain.Employee;
import com.craffic.vhr.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    private EmployeeService employeeService;

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
}
