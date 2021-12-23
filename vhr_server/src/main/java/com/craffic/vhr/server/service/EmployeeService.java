package com.craffic.vhr.server.service;

import com.craffic.vhr.server.common.RespPageBean;
import com.craffic.vhr.server.dao.EmployeeMapper;
import com.craffic.vhr.server.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询员工
     */
    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, keyword, employee, beginDateScope);
        Long total = employeeMapper.getTotal(keyword, employee, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmp(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    public Integer maxWorkID() {
        return employeeMapper.maxWorkID();
    }
}
