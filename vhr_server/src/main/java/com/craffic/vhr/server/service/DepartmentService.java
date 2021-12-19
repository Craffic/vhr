package com.craffic.vhr.server.service;

import com.craffic.vhr.server.dao.DepartmentMapper;
import com.craffic.vhr.server.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> queryAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public Department addDept(Department department) {
        department.setEnabled(true);
        departmentMapper.addDept(department);
        return null;
    }
}
