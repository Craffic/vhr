package com.craffic.vhr.server.controller.emp;

import com.craffic.vhr.server.common.RespBean;
import com.craffic.vhr.server.common.RespPageBean;
import com.craffic.vhr.server.domain.*;
import com.craffic.vhr.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emp/basic")
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
}
