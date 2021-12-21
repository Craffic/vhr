package com.craffic.vhr.server.controller.emp;

import com.craffic.vhr.server.common.RespPageBean;
import com.craffic.vhr.server.domain.Employee;
import com.craffic.vhr.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
    }

}
